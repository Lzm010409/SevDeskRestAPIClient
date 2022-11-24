package main;

import com.sun.istack.logging.Logger;
import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;
import data.entity.other.Country;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;
import data.filepaths.PathReader;
import data.filepaths.PathWriter;
import dir.dir.DirLister;
import mail.login.Login;
import mail.send.MailSender;
import restfulapi.requests.input.PostBuilder;
import text.extractor.InvoiceTextExtractor;
import text.parser.TextParser;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationMain {
    private boolean runApplication = true;
    private final Logger logger = Logger.getLogger(ApplicationMain.class);


    private String invoiceDir = "/Users/lukegollenstede/Desktop/02/Gutachten";
    private String voucherDir = "/Users/lukegollenstede/Desktop/02/Ausgaben/2022/08/BAR";

    public static void main(String[] args) {
        ApplicationMain applicationMain = new ApplicationMain();
        DirLister invoiceDirLister = new DirLister();
        DirLister voucherDirLister = new DirLister();
        ThreadSleeper threadSleeper = new ThreadSleeper();
        List<String> invoiceFileList = new ArrayList<>();
        List<String> voucherFileList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        List<String> readedFileList = new ArrayList<>();
        TextParser textParser = new TextParser();
        InvoiceTextExtractor invoiceTextExtractor = new InvoiceTextExtractor();
        PostBuilder postBuilder = new PostBuilder();
        PathWriter readedFilesWriter = new PathWriter();
        PathReader pathReader = new PathReader();
        readedFilesWriter.setFilePathName("ReadedFiles.txt");

        while (applicationMain.isRunApplication() == true) {


            invoiceDirLister.listDir(new File(applicationMain.getInvoiceDir()), "InvoiceFilePaths.txt");
            invoiceFileList = pathReader.readFile(invoiceDirLister.getPathWriter().getFilePaths());
            readedFileList = pathReader.readFile(new File(readedFilesWriter.getFilePathName()));

            for (int i = 0; i < invoiceFileList.size(); i++) {
                try {
                    if (!readedFileList.contains(invoiceFileList.get(i))) {
                        objectList = textParser.parseInvoice(invoiceTextExtractor.extractTextFromDoc(new File(invoiceFileList.get(i))));
                        try {
                            Country country = new Country(0);
                            String output = postBuilder.postNewContact((Contact) objectList.get(0));
                            String id = textParser.parseId(output);
                            postBuilder.postNewContactAdress((ContactAddress) objectList.get(1), Long.parseLong(id));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        postBuilder.postNewVoucher((Voucher) objectList.get(2), (VoucherPosSave) objectList.get(3), "C");

                        readedFilesWriter.writeData(invoiceFileList.get(i));
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            voucherDirLister.listDir(new File(applicationMain.getVoucherDir()), "VoucherFilePaths.txt");
            voucherFileList = pathReader.readFile(voucherDirLister.getPathWriter().getFilePaths());
            Login login = new Login();
            MailSender mailSender = new MailSender();

            try {
                login.login("", "");
                mailSender.setMailSession(login.getMailSession());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < voucherFileList.size(); i++) {
                if (!readedFileList.contains(voucherFileList.get(i))) {
                    try {
                        mailSender.sendMail("", "", "autobox@sevdesk.email", "Upload", new File(voucherFileList.get(i)));
                        readedFilesWriter.writeData(voucherFileList.get(i));
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }


            threadSleeper.threadSleep(10000);


        }
    }

    public boolean isRunApplication() {
        return runApplication;
    }

    public void setRunApplication(boolean runApplication) {
        this.runApplication = runApplication;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getInvoiceDir() {
        return invoiceDir;
    }

    public void setInvoiceDir(String invoiceDir) {
        this.invoiceDir = invoiceDir;
    }

    public String getVoucherDir() {
        return voucherDir;
    }

    public void setVoucherDir(String voucherDir) {
        this.voucherDir = voucherDir;
    }
}
