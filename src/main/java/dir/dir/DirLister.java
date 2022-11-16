package dir.dir;

import org.jboss.logging.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirLister {
    private final Logger logger = Logger.getLogger(DirLister.class);
    private String invoiceDir;
    private List<String> fileArray = new ArrayList<>();


    public List<String> listDir(File dir, List<String> list) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    logger.log(Logger.Level.WARN, files[i] + ": ist keine Datei!");
                    listDir(files[i], list);
                    continue;
                }
                if (!files[i].getAbsolutePath().contains("@eaDir") && !list.contains(files[i].getAbsolutePath())) {

                    list.add(files[i].getAbsolutePath());
                    logger.log(Logger.Level.INFO, files[i] + ": wurde der Liste hinzugefügt.");
                }

                continue;

            }
        }
        return fileArray;
    }

    public static void main(String[] args) {
        DirLister dirLister = new DirLister();

        //dirLister.listDir(new File("/Volumes/Sachverständigerei/Sachverständigerei/bezahlte RG/2022"));
    }
}
