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


    public void listDir(File dir, String filePaths) {
        pathWriter.setFilePathName(filePaths);
        int count = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    logger.log(Logger.Level.WARN, files[i] + ": ist keine Datei!");
                    listDir(files[i], filePaths);
                    continue;
                }
                if (!files[i].getAbsolutePath().contains("@eaDir")) {
                    if (files[i].getAbsolutePath().contains("Ausgaben") || files[i].getAbsolutePath().contains("Rechnung") || files[i].getAbsolutePath().contains("RG")) {
                        int j = pathWriter.writeData(files[i].getAbsolutePath());
                        if (j == 1) {
                            count += 1;
                        }
                    }
                }

            }
            logger.log(Logger.Level.INFO, "Es wurden " + count + " neue Dateien hinzugefügt!");
        }

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
