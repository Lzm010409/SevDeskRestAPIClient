package data.filepaths;

import data.entity.other.TagName;
import org.jboss.logging.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyReader {
    private final Logger logger = Logger.getLogger(PropertyReader.class);

    public List<String> readProperties(String path) {
        String zeile;
        List<String> filePaths;
        File file = new File(path);
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


    public List<TagName> readRechtsanwälte(List<String> list) {
        List<TagName> tagNames = new ArrayList<>();
        for (int i = 0; i < list.size(); i += 2) {
            TagName tagName = new TagName(list.get(i), list.get(i + 1));
            tagNames.add(tagName);
            tagName = null;

        }
        return tagNames;
    }
}
