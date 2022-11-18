package data.filepaths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PathReader {

    private BufferedReader bufferedReader;
    private FileReader fileReader;

    public List<String> readFile(File data) {
        List<String> tempList = new ArrayList<>();
        String zeile = null;

        try {
            if (!data.exists()) {
                FileWriter fileWriter = new FileWriter(data);
                bufferedReader = new BufferedReader(new FileReader(data));
                if (!((zeile = bufferedReader.readLine()) != null)) {
                    tempList.add(zeile);
                }
            } else {
                bufferedReader = new BufferedReader(new FileReader(data));
                while ((zeile = bufferedReader.readLine()) != null) {

                    tempList.add(zeile);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return tempList;
    }
}
