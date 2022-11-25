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

    public boolean isPresent(File file, String toSearchFor) {
        try {
            boolean isPresent = false;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String zeile = "";
            while ((zeile = bufferedReader.readLine()) != null) {
                if (zeile.equalsIgnoreCase(toSearchFor)) {
                    isPresent = true;
                }

            }
            return isPresent;

        } catch (Exception e) {
            return false;
        }
    }

    public int countRows(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String zeile = "";
            int count = 0;
            while ((zeile = bufferedReader.readLine()) != null) {
                count += 1;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }
}
