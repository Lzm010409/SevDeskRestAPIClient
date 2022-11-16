package main;

import com.sun.istack.logging.Logger;
import dir.dir.DirLister;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplicationMain {
    private boolean runApplication = true;
    private final Logger logger = Logger.getLogger(ApplicationMain.class);


    private String invoiceDir = "/Volumes/Sachverständigerei/Sachverständigerei/bezahlte RG/2022";

    public static void main(String[] args) {
        ApplicationMain applicationMain = new ApplicationMain();
        DirLister dirLister = new DirLister();
        ThreadSleeper threadSleeper = new ThreadSleeper();
        List<String> fileList = new ArrayList<>();

        while (applicationMain.isRunApplication() == true) {

            dirLister.listDir(new File(applicationMain.getInvoiceDir()), fileList);
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
