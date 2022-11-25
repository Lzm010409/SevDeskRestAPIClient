package data.filepaths;

import org.jboss.logging.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyReader {
    private final Logger logger = Logger.getLogger(PropertyReader.class);

    public List<String> readProperties() {
        String zeile;
        List<String> filePaths;
        File file = new File("ApplicationProperties.txt");
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            zeile = bufferedReader.readLine();
            filePaths = new ArrayList<>();
            filePaths = Arrays.asList(zeile.split("#"));
            for (int i = 0; i < filePaths.size(); i++) {
                logger.log(Logger.Level.INFO, "Es wurden folgende Dateipfade als Property-Pfade erkannt:" + filePaths.get(i));
            }
            return filePaths;
        } catch (FileNotFoundException e) {
            logger.log(Logger.Level.WARN, "Die folgende Datei: " + file.getAbsolutePath() + " konnte nicht gelesen werden, oder ist nicht vorhanden!");
            return null;
        } catch (IOException e) {
            logger.log(Logger.Level.WARN, "Die Datei: " + file.getAbsolutePath() + " konnte zwar gefunden werden, ist aber nicht lesbar!");
            return null;
        }

    }
}
