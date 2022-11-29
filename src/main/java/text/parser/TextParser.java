package text.parser;

import org.jboss.logging.Logger;
import text.object.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {
    private final Logger logger = Logger.getLogger(TextParser.class);

    public List<Object> parseInvoice(String invoiceText) {
        List<String> list = Arrays.asList(invoiceText.split("\n\n"));
        List<String> contact = Arrays.asList(list.get(0).split("\n"));
        List<String> contactAdress = new ArrayList<>();
        List<String> tag = Arrays.asList(list.get(4).split("\n"));
        contactAdress.add(contact.get(2));
        contactAdress.add(contact.get(3));
        char[] invoiceDateArray = list.get(1).toCharArray();
        StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < invoiceDateArray.length; i++) {
            if (Character.isLetter(invoiceDateArray[i]) == true || invoiceDateArray[i] == ',') {
                invoiceDateArray[i] = ' ';
            }
            if (invoiceDateArray[i] != ' ') {
                builder1.append(invoiceDateArray[i]);
            }
        }
        String invoiceDate = builder1.toString();
        //Calendar calendar = new DateParser().parseDate(invoiceDate);


        String gutachtennummer = list.get(2);
        list.set(2, null);

        List<String> voucherInfo = new ArrayList<>();
        voucherInfo.add(invoiceDate);
        voucherInfo.add(gutachtennummer);

        List<String> voucherPos = new ArrayList<>();
        if (list.get(3).contains("EUR")) {
            String element = list.get(3).replace("EUR", "");
            list.set(3, element);
        }

        voucherPos = Arrays.asList(list.get(3).split("\n"));

        float max = 0;
        for (int i = 0; i < voucherPos.size(); i++) {
            try {
                char[] numberArray = voucherPos.get(i).toCharArray();
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < numberArray.length; j++) {
                    if (numberArray[j] == ',') {
                        numberArray[j] = '.';

                    }
                    builder.append(numberArray[j]);
                    voucherPos.set(i, builder.toString());
                }


                if (max < Float.parseFloat(voucherPos.get(i))) {
                    max = Float.parseFloat(voucherPos.get(i));
                }
            } catch (Exception e) {
                voucherPos.set(i, null);
            }
        }

        List<Object> objectList = new ArrayList<>();
        objectList.add(new ContactBuilder().build(contact));
        objectList.add(new ContactAdressBuilder().build(contactAdress));
        objectList.add(new InvoiceBuilder().build(voucherInfo));
        objectList.add(new VoucherPosBuilder().build(max));
        objectList.add(new TagBuilder().build(tag));

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

    public String parseName(String toParse) {
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

}
