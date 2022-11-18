package data.filepaths;

import org.jboss.logging.Logger;

import java.io.*;

public class PathWriter {
    File filePaths;
    String filePathName;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private final Logger logger = Logger.getLogger(PathWriter.class);

    public int writeData(String data) {
        StringBuilder builder = new StringBuilder();
        int exitcode = 1;
        filePaths = new File(filePathName);
        if (!filePaths.exists()) {
            try {
                fileWriter = new FileWriter(filePaths);
                builder.append(data + "\n");
                fileWriter.write(builder.toString());
                fileWriter.flush();
                fileWriter.close();
                logger.log(Logger.Level.INFO, "Datei " + filePathName + " wurde neu angelegt und der übergebene File Path:" + data + " gespeichert.");
            } catch (Exception e) {
                logger.log(Logger.Level.ERROR, "Es ist folgender Fehler aufgetreten: " + e.getMessage());
            }
        } else {
            try {

                String fileData = readFile();

                if (!fileData.contains(data)) {
                    fileWriter = new FileWriter(filePaths);
                    fileWriter.write(fileData);
                    builder.append(data + "\n");
                    fileWriter.write(builder.toString());
                    fileWriter.flush();
                    fileWriter.close();
                    logger.log(Logger.Level.INFO, "Übergebener File Path:" + data + " gespeichert.");
                    exitcode = 1;
                } else {
                    exitcode = 0;
                    //logger.log(Logger.Level.INFO, "Übergebener File Path" + data + " wurde nicht gespeichert, da bereits vorhanden.");
                }


            } catch (IOException e) {
                logger.log(Logger.Level.ERROR, "Es ist folgender Fehler aufgetreten: " + e.getMessage());
                exitcode = 500;
            }

        }
        return exitcode;
    }

    public String readFile() {
        try {
            bufferedReader = new BufferedReader(new FileReader(filePaths));
            String output = "";
            String zeile = null;
            while ((zeile = bufferedReader.readLine()) != null) {
                output += zeile + "\n";
            }
            return output;

        } catch (FileNotFoundException e) {
            logger.log(Logger.Level.ERROR, "Die Datei wurde nicht gefunden!");
            return null;
        } catch (IOException e) {
            logger.log(Logger.Level.ERROR, "Es ist folgender Fehler aufgetreten: " + e.getMessage());
            return null;
        }
    }

    public File getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(File filePaths) {
        this.filePaths = filePaths;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }
}
