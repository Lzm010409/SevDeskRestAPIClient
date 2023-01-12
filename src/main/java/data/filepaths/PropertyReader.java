package data.filepaths;

import data.entity.other.Part;
import data.entity.other.TagName;
import org.jboss.logging.Logger;
import restfulapi.requests.Request;
import restfulapi.requests.url.Token;
import restfulapi.requests.url.URL;
import restfulapi.requests.url.UrlBuilder;
import restfulapi.response.ResponseParser;

import javax.mail.MessagingException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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


    public Map<String, Part> readPartsData(String partsPath, String serverResponse) {
        String zeile;
        StringBuilder builder = new StringBuilder();
        File filePaths = new File(partsPath);
        try {
            if (!filePaths.exists()) {
                FileWriter fileWriter = new FileWriter(filePaths);
                fileWriter.write(serverResponse);
                fileWriter.flush();
                fileWriter.close();
                logger.log(Logger.Level.INFO, "Datei " + filePaths.getName() + " wurde neu angelegt und der übergebene File Path:" + filePaths.getAbsolutePath() + " gespeichert.");
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePaths));
            while ((zeile = bufferedReader.readLine()) != null) {
                builder.append(zeile);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Part> partList = new ResponseParser().parseParts(builder.toString());
        return partList;
    }


    public static void main(String[] args) throws MessagingException {
        Token token = new Token();
        token.setToken("");
        UrlBuilder builder = new UrlBuilder();
        Request request = new Request();

        PropertyReader propertyReader = new PropertyReader();
        Map<String, Part> partsList = propertyReader.readPartsData("src/main/resources/parts.json",
                request.httpGet(builder.buildUrl(URL.GETALLPARTS), token.getToken()));
        for (String key : partsList.keySet()) {
            String output = String.format("Id: %s, name: %s, preis:%s", partsList.get(key).getId(), partsList.get(key).getName(), String.valueOf(partsList.get(key).getPrice()));
            System.out.println(output);
        }

    }

}



