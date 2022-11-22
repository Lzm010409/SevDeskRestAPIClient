package main;

import com.sun.istack.logging.Logger;
import data.entity.contact.Contact;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;
import data.filepaths.PathReader;
import data.filepaths.PathWriter;
import dir.dir.DirLister;
import restfulapi.requests.input.PostBuilder;
import text.extractor.InvoiceTextExtractor;
import text.parser.TextParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationMain {
    private boolean runApplication = true;
    private final Logger logger = Logger.getLogger(ApplicationMain.class);


    private String invoiceDir = "/Users/lukegollenstede/Desktop/02";

    public static void main(String[] args) {
        ApplicationMain applicationMain = new ApplicationMain();
        DirLister dirLister = new DirLister();
        ThreadSleeper threadSleeper = new ThreadSleeper();
        List<String> fileList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        List<String> readedFileList = new ArrayList<>();
        TextParser textParser = new TextParser();
        InvoiceTextExtractor invoiceTextExtractor = new InvoiceTextExtractor();
        PostBuilder postBuilder = new PostBuilder();
        PathWriter pathWriter = new PathWriter();
        PathReader pathReader = new PathReader();
        pathWriter.setFilePathName("ReadedFiles.txt");

        while (applicationMain.isRunApplication() == true) {


            dirLister.listDir(new File(applicationMain.getInvoiceDir()));
            fileList = pathReader.readFile(dirLister.getPathWriter().getFilePaths());
            readedFileList = pathReader.readFile(new File(pathWriter.getFilePathName()));

            for (int i = 0; i < fileList.size(); i++) {
                try {
                    if (!readedFileList.contains(fileList.get(i))) {
                        objectList = textParser.parseInvoice(invoiceTextExtractor.extractTextFromDoc(new File(fileList.get(i))));
                        try {
                            //Country country = new Country(0);
                            String output = postBuilder.postNewContact((Contact) objectList.get(0));
                            //String id= textParser.parseId(output);
                            //ContactAdress contactAdress= new ContactAdress(country);
                            //postBuilder.postNewContactAdress((ContactAdress) objectList.get(1), Long.parseLong(id));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        postBuilder.postNewVoucher((Voucher) objectList.get(2), (VoucherPosSave) objectList.get(3),"C");

                        pathWriter.writeData(fileList.get(i));
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
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

}
