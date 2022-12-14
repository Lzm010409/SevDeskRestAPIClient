package dir.dir;

import data.filepaths.PathWriter;
import org.jboss.logging.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirLister {
    private final Logger logger = Logger.getLogger(DirLister.class);
    private String invoiceDir;
    private List<String> fileArray = new ArrayList<>();
    PathWriter pathWriter = new PathWriter();


    private void listDir(File dir, List<File> list) {

        int count = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    logger.log(Logger.Level.WARN, files[i] + ": ist keine Datei!");
                    listDir(files[i], list);
                }
                if (!files[i].getAbsolutePath().contains("DS_Store") && !files[i].isDirectory()) {
                    list.add(files[i]);
                }
            }

        }

    }

    public List<String> getInvoices(File dir) {
        List<File> fileList = new ArrayList<>();
        List<String> invoices = new ArrayList<>();
        listDir(dir, fileList);

        for (int i = 0; i < fileList.size(); i++) {
            if (!dir.getAbsolutePath().contains("Ausgaben")) {
                if (fileList.get(i).getAbsolutePath().contains("Rechnung") || fileList.get(i).getAbsolutePath().contains("RG")) {
                    invoices.add(fileList.get(i).getAbsolutePath());
                }
            } else {
                invoices.add(fileList.get(i).getAbsolutePath());
            }

        }
        return invoices;
    }

    public static void main(String[] args) {
        DirLister dirLister = new DirLister();

        //dirLister.listDir(new File("/Volumes/Sachverständigerei/Sachverständigerei/bezahlte RG/2022"));
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

    public List<String> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<String> fileArray) {
        this.fileArray = fileArray;
    }

    public PathWriter getPathWriter() {
        return pathWriter;
    }

    public void setPathWriter(PathWriter pathWriter) {
        this.pathWriter = pathWriter;
    }
}
