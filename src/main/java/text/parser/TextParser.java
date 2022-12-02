package text.parser;

import org.jboss.logging.Logger;
import text.extractor.InvoiceTextExtractor;
import text.object.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {
    private final Logger logger = Logger.getLogger(TextParser.class);

    public List<Object> parseInvoice(String invoiceText) {
        List<String> list = Arrays.asList(invoiceText.split("\n\n"));
        List<String> contact = new ArrayList<>();
        List<String> contactAdress = new ArrayList<>();
        List<String> voucherInfo = new ArrayList<>();
        List<String> voucherPos = new ArrayList<>();
        List<String> tag = new ArrayList<>();
        List<String> invoiceInfoList = new ArrayList<>();
        String invoiceDate = "";
        String gutachtennummer = "";

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == "") {
                list.set(i, null);
            }
        }

        if (list.get(0).equalsIgnoreCase("frau") || list.get(0).equalsIgnoreCase("herr")) {
            contact.add(parseName(list.get(0)));
            contact.add(parseName(list.get(1)));
            contactAdress.add(parseAdress(list.get(2)));
            contactAdress.add(list.get(3));
            for (int i = 0; i < (contact.size() + contactAdress.size()); i++) {
                list.set(i, null);
            }
        } else {
            contact.add(parseName(list.get(0)));
            contactAdress.add(parseAdress(list.get(1)));
            contactAdress.add(list.get(2));
            for (int i = 0; i < (contact.size() + contactAdress.size()); i++) {
                list.set(i, null);
            }
        }
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                char[] invoiceDateArray = list.get(i).toCharArray();
                invoiceDate = parseInvoiceDate(invoiceDateArray);
                list.set(i, null);
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                gutachtennummer = list.get(i);
                list.set(i, null);
                break;
            }
        }
        voucherInfo.add(invoiceDate);
        voucherInfo.add(gutachtennummer);


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                voucherPos = Arrays.asList(list.get(i).split("\n"));
                list.set(i, null);
                break;
            }
        }

        float max = parseMaxAmount(voucherPos);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                tag = Arrays.asList(list.get(i).split("\n"));
                list.set(i, null);
                break;
            }
        }

        invoiceInfoList = Arrays.asList(list.get(list.size() - 1).split("\n"));


        List<Object> objectList = new ArrayList<>();
        objectList.add(new ContactBuilder().build(contact));
        objectList.add(new ContactAdressBuilder().build(contactAdress));
        objectList.add(new VoucherBuilder().build(voucherInfo));
        objectList.add(new VoucherPosBuilder().build(max));
        objectList.add(new TagBuilder().build(tag));
        objectList.add(new InvoicePosBuilder().build(invoiceInfoList));

        logger.log(Logger.Level.INFO, "Alle Daten korrekt ausgelesen!");
        return objectList;
    }

    public String parseId(String toParse) {
        StringBuilder builder1 = new StringBuilder();
        String[] array = toParse.split("id\":\"");
        char[] string = array[1].toCharArray();

        for (int i = 0; i < 8; i++) {
            builder1.append(string[i]);
        }

        String id = builder1.toString();
        logger.log(Logger.Level.INFO, "Die Id des neu angelegten Kunden ist: " + id);
        return id;

    }


    public String parseInvoiceDate(char[] invoiceDateArray) {
        StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < invoiceDateArray.length; i++) {
            if (Character.isDigit(invoiceDateArray[i]) || invoiceDateArray[i] == '.') {
                builder1.append(invoiceDateArray[i]);
            }
        }
        String invoiceDate = builder1.toString();
        return invoiceDate;
    }

    public float parseMaxAmount(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("EUR")) {
                String element = list.get(i).replace("EUR", "");
                list.set(i, element);
            }
        }

        float max = 0;
        for (int i = 0; i < list.size(); i++) {
            try {
                char[] numberArray = list.get(i).toCharArray();
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < numberArray.length; j++) {
                    if (numberArray[j] == ',') {
                        numberArray[j] = '.';

                    }
                    builder.append(numberArray[j]);
                    list.set(i, builder.toString());
                }


                if (max < Float.parseFloat(list.get(i))) {
                    max = Float.parseFloat(list.get(i));
                }
            } catch (Exception e) {
                list.set(i, null);
            }
        }
        return max;
    }

    public String parseAdress(String adress) {
        char[] tempChar = adress.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tempChar.length; i++) {
            if (Character.isLetter(tempChar[i]) || Character.isDigit(tempChar[i])) {
                builder.append(tempChar[i]);
            } else {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public String parseName(String name) {
        char[] tempChar = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tempChar.length; i++) {
            if (Character.isLetter(tempChar[i]) || Character.isDigit(tempChar[i])) {
                builder.append(tempChar[i]);
            } else {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        InvoiceTextExtractor invoiceTextExtractor = new InvoiceTextExtractor();
        String string = new String();
        try {
            string = invoiceTextExtractor.extractTextFromDoc(new File("/Users/lukegollenstede/Desktop/Rechnung_0722_581TG01.pdf"));
            System.out.println(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TextParser textParser = new TextParser();
        List<Object> list = textParser.parseInvoice(string);
    }

}
