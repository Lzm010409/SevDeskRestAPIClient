package main;

import com.sun.istack.logging.Logger;
import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;
import data.entity.other.Country;
import data.entity.other.Tag;
import data.entity.other.TagName;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherLink;
import data.entity.voucher.VoucherPosSave;
import data.filepaths.PathReader;
import data.filepaths.PathWriter;
import data.filepaths.PropertyReader;
import dir.dir.DirLister;
import mail.login.Login;
import mail.send.MailSender;
import restfulapi.requests.post.PostBuilder;
import restfulapi.requests.url.Token;
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


    private String invoiceDir;
    private String voucherDir;

    public static void main(String[] args) throws MessagingException{
        ApplicationMain applicationMain = new ApplicationMain();
        DirLister invoiceDirLister = new DirLister();
        DirLister voucherDirLister = new DirLister();
        ThreadSleeper threadSleeper = new ThreadSleeper();
        List<String> invoiceFileList = new ArrayList<>();
        List<String> voucherFileList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        List<String> readedFileList = new ArrayList<>();
        List<String> applicationProperties = new PropertyReader().readProperties("ApplicationProperties.txt");
        TextParser textParser = new TextParser();
        InvoiceTextExtractor invoiceTextExtractor = new InvoiceTextExtractor();
        PostBuilder postBuilder = new PostBuilder();
        PathWriter readedFilesWriter = new PathWriter();
        PathReader pathReader = new PathReader();
        readedFilesWriter.setFilePathName(applicationProperties.get(0));
        File readedFiles = new File(applicationProperties.get(0));
        applicationMain.setInvoiceDir(applicationProperties.get(1));
        applicationMain.setVoucherDir(applicationProperties.get(2));
        Token TOKEN = new Token();
        TOKEN.setToken(applicationProperties.get(3));

        String output = "";

        while (applicationMain.isRunApplication() == true) {


            invoiceFileList = invoiceDirLister.getInvoices(new File(applicationMain.getInvoiceDir()));

            for (int i = 0; i < invoiceFileList.size(); i++) {
                try {
                    if (pathReader.isPresent(readedFiles, invoiceFileList.get(i)) == false) {
                        objectList = textParser.parseInvoice(invoiceTextExtractor.extractTextFromDoc(new File(invoiceFileList.get(i))));
                        try {
                            Country country = new Country(0);
                            output = postBuilder.postNewContact((Contact) objectList.get(0),TOKEN);
                            String id = textParser.parseId(output);
                            postBuilder.postNewContactAdress((ContactAddress) objectList.get(1), Long.parseLong(id),TOKEN);
                            output = null;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        output = postBuilder.postNewVoucher((Voucher) objectList.get(2), (VoucherPosSave) objectList.get(3), "D",TOKEN);
                        String id = textParser.parseId(output);
                        VoucherLink voucherLink = new VoucherLink();
                        voucherLink.setId(Integer.parseInt(id));
                        Tag tag = new Tag(((TagName) objectList.get(4)).getName());
                        tag.setObject(voucherLink);
                        postBuilder.postNewTag(tag,TOKEN);
                        readedFilesWriter.writeData(invoiceFileList.get(i));
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            voucherFileList = voucherDirLister.getInvoices(new File(applicationMain.getVoucherDir()));

            Login login = new Login();
            MailSender mailSender = new MailSender();

            try {
                login.login(applicationProperties.get(4), applicationProperties.get(6)+"#"+applicationProperties.get(7));
                mailSender.setMailSession(login.getMailSession());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < voucherFileList.size(); i++) {
                if (pathReader.isPresent(readedFiles, voucherFileList.get(i)) == false) {
                    try {
                        mailSender.sendMail(applicationProperties.get(4), applicationProperties.get(5), "autobox@sevdesk.email", "Upload", new File(voucherFileList.get(i)));
                        readedFilesWriter.writeData(voucherFileList.get(i));
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }


            threadSleeper.threadSleep(3600000);


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
