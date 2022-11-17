package text.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {

    public void parseInvoice(String invoiceText) {
        List<String> list = Arrays.asList(invoiceText.split("\n\n"));
        List<String> contact = Arrays.asList(list.get(0).split("\n"));
        char[] invoiceDateArray = list.get(1).toCharArray();
        StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < invoiceDateArray.length; i++) {
            if (Character.isLetter(invoiceDateArray[i]) == true || invoiceDateArray[i]==',') {
                invoiceDateArray[i] = ' ';
            }
            if (invoiceDateArray[i] != ' ') {
                builder1.append(invoiceDateArray[i]);
            }
        }
        String invoiceDate = builder1.toString();


        String gutachtennummer = list.get(2);
        list.set(2, null);


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


    }
}
