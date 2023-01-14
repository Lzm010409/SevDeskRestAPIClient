
package text.object;

import data.entity.invoice.InvoicePos;
import data.entity.other.Part;
import data.entity.other.Unity;
import data.filepaths.PropertyReader;
import restfulapi.requests.Request;
import restfulapi.requests.url.Token;
import restfulapi.requests.url.URL;
import restfulapi.requests.url.UrlBuilder;

import java.util.*;
import java.util.regex.Pattern;

public class InvoicePosBuilder {

    Map<String, Long> unityMap = new HashMap<String, Long>();

    Map<String, Part> partMap = new PropertyReader().readPartsData();

    public Object build(List<String> e) {
        Map<String, Part> partMap = new PropertyReader().readPartsData();
        List<InvoicePos> invoiceList = new ArrayList<>();
        for (int i = 0; i < e.size(); i++) {
            if (!e.get(i).contains("Gesamtpreis")) {
                e.set(i, null);
            } else {
                e.set(i, null);
                break;
            }
        }
        List<String> tempList = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i) != null) {
                char[] tempArr = e.get(i).toCharArray();
                if (Character.isDigit(tempArr[0])) {
                    for (int j = 0; j < tempArr.length; j++) {
                        if (!(Character.isDigit(tempArr[j]) || Character.isLetter(tempArr[j]) || tempArr[j] == ',' || tempArr[j] == '.' || tempArr[j] == '-' || tempArr[j] == '/')) {
                            builder.append(" ");
                        } else {
                            builder.append(tempArr[j]);
                        }
                    }
                }
                if (builder.length() != 0) {
                    tempList.add(builder.toString());
                    builder.setLength(0);
                }
            }

        }
        for (int i = 0; i < tempList.size(); i++) {
            String[] stringArray = tempList.get(i).split(" ");
            int position = Integer.parseInt(stringArray[0]);
            stringArray[0] = null;
            float quantity = 0;
            float zahl1 = 0;
            float zahl2 = 0;
            String quantityName = "";
            StringBuilder invoicePosName = new StringBuilder();
            InvoicePos invoicePos = new InvoicePos();
            for (int k = 0; k < stringArray.length; k++) {
                if (stringArray[k] != null) {
                    if (isAmount(stringArray[k])) {
                        float convertedAmount = convertToFloat(stringArray[k]);
                        if (zahl1 == 0) {
                            zahl1 = convertedAmount;
                        } else {
                            zahl2 = convertedAmount;
                        }
                    }
                    if (isSecondPosition(stringArray[k])) {
                        invoicePosName.append(stringArray[k]);
                    }
                    if (isQuantity(stringArray[k])) {
                        quantity = Float.parseFloat(stringArray[k]);
                    }
                    if (isQuantityName(stringArray[k])) {
                        quantityName = stringArray[k];
                    }
                    if (isPositionName(stringArray[k]) && !(stringArray[k].equalsIgnoreCase("eur"))) {
                        invoicePosName.append(stringArray[k] + " ");
                    }
                }
            }
            List<Float> invoicePosAmounts = new ArrayList<>();
            invoicePosAmounts.add(zahl1);
            invoicePosAmounts.add(zahl2);
            Unity unity = new Unity(0);
            boolean quantityNameSet = false;
            if (quantityName.equalsIgnoreCase("km") && quantityNameSet == false) {
                unity = new Unity(unityMap.get("km"));
                quantityNameSet = true;

            }
            if (quantityName.equalsIgnoreCase("stück") && quantityNameSet == false) {
                unity = new Unity(unityMap.get("stück"));
                quantityNameSet = true;
            }
            if (quantityName.equalsIgnoreCase("seiten") && quantityNameSet == false) {
                unity = new Unity(unityMap.get("seiten"));
                quantityNameSet = true;

            }
            if (quantityNameSet == false) {
                unity = new Unity(unityMap.get("pauschal"));
            }
            invoicePos.setPositionNumber(position);
            invoicePos.setUnity(unity);
            invoicePos.setQuantity(quantity);

            invoicePos.setPriceGross(returnGrosSum(invoicePosAmounts));
            invoicePos.setPrice(invoicePos.getPriceGross() / quantity);

            invoicePos.setTaxRate(19);
            invoicePos.setPriceTax((double) (invoicePos.getPriceGross() - (invoicePos.getPriceGross() / 1.19)));
            invoicePos.setName(invoicePosName.toString());
            invoiceList.add(invoicePos);
        }


        return invoiceList;
    }

    public Object testBuild(List<String> e) {

        List<String> tempList = new LinkedList<>(Arrays.asList(e.get(0).split("\n")));
        int index = 0;
        while (!tempList.get(index).contains("Gesamtpreis")) {
            tempList.remove(index);
        }
        tempList.remove(index);
        List<InvoicePos> invoicePosList = new LinkedList<>();
        // Map<String, Part> partMap = new PropertyReader().readPartsData();

        while (index < tempList.size()) {
            InvoicePos invoicePos = new InvoicePos();
            List<Double> amountList = new ArrayList<>();
            List<String> stringList = new LinkedList<>(Arrays.asList(tempList.get(index).split(" ")));
            stringList.remove(0);
            int secIndex = 0;
            int runtime = 0;
            for (int i = 0; i < stringList.size(); i++) {
                stringList.set(i, reworkStrings(stringList.get(i)));
            }
            if (stringList.contains("")) {
                stringList.remove("");
            }
            while (!invoicePosReady(invoicePos)) {
                if (runtime > 10) {
                    secIndex += 1;
                    runtime = 0;
                }
                if (secIndex < stringList.size() && containsPart(stringList.get(secIndex)) != "") {
                    String key = containsPart(stringList.get(secIndex));
                    data.entity.link.Part tempPart = new data.entity.link.Part(partMap.get(key).getId());
                    invoicePos.setPart(tempPart);
                    invoicePos.setUnity(partMap.get(key).getUnity());
                    secIndex += 1;
                }
                if (secIndex < stringList.size() && isAmount(stringList.get(secIndex))) {
                    amountList.add(Double.valueOf(new String(removeSpacesFromAmount(stringList.get(secIndex)).replace(",", "."))));
                    secIndex += 1;
                }
                if (secIndex < stringList.size() && isQuantity(stringList.get(secIndex))) {
                    invoicePos.setQuantity(Float.parseFloat(stringList.get(secIndex)));
                    secIndex += 1;
                }
                if (amountList.size() == 2) {
                    if (amountList.get(0) > amountList.get(1)) {
                        invoicePos.setPriceGross(amountList.get(0) * 1.19);
                        invoicePos.setPrice(amountList.get(1));
                        invoicePos.setPriceTax(invoicePos.getPriceGross() - (invoicePos.getPriceGross() / 1.19));
                        secIndex += 1;
                    } else {
                        invoicePos.setPriceGross(amountList.get(1) * 1.19);
                        invoicePos.setPrice(amountList.get(0));
                        invoicePos.setPriceTax(invoicePos.getPriceGross() - (invoicePos.getPriceGross() / 1.19));
                        secIndex += 1;
                    }
                }
                runtime += 1;
            }
            invoicePosList.add(invoicePos);
            index += 1;
        }


        return null;
    }

    private String containsPart(String input) {
        char[] charArray = input.toCharArray();
        int hitCounter = 0;
        float averageChar = 0;
        float charLength = 0;
        String returnKey = "";
        for (String key : partMap.keySet()) {
            char[] partChars = key.toCharArray();
            int runner = 0;
            if (partChars.length < charArray.length) {
                runner = partChars.length;
            } else {
                runner = charArray.length;
            }
            for (int i = 0; i < runner; i++) {
                if (charArray[i] == partChars[i]) {
                    hitCounter += 1;
                }
            }
            averageChar = ((hitCounter * 100) / runner);
            System.out.println(averageChar);
            if (averageChar > 75) {
                returnKey = key;
                break;
            }
            hitCounter = 0;
        }


        return returnKey;
    }

    private String reworkStrings(String input) {

        if (input.contains("\u00AD")) {
            input = input.replace("\u00AD", "-");
        }
        if (input.contains("EUR")) {
            input = input.replace("EUR", "");
        }
        return input;
    }


    private boolean invoicePosReady(InvoicePos invoicePos) {
        if (invoicePos.getPart() != null && invoicePos.getPrice() != 0.0 && invoicePos.getQuantity() != 0
                && invoicePos.getUnity() != null) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        InvoicePosBuilder invoicePosBuilder = new InvoicePosBuilder();
        Token token = new Token();
        token.setToken("");
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.requestPartsData(new Request().httpGet(new UrlBuilder().buildUrl(URL.GETALLPARTS), token.getToken()));
        List<String> test = new ArrayList<>();
        test.add("Sehr geehrte Damen und Herren,\n" +
                "wir danken für Ihren Auftrag und erlauben uns in obiger Sache zu berechnen:\n" +
                "Pos Bezeichnung Anz. Einheit Einzelpreis Gesamtpreis\n" +
                "1 SV\u00ADHonorar 1 620,00 EUR EUR 620,00\n" +
                "2 Fahrtkosten erh. Energiekosten 42 km 0,80 EUR EUR 33,60\n" +
                "3 Fotos 22 Stück 2,00 EUR EUR 44,00\n" +
                "4 Porto/Telefon 1 7,50 EUR EUR 7,50\n" +
                "Systemgeb. m. Bewertung + erh. \n" +
                "5 1 30,00 EUR EUR 30,00\n" +
                "Transakt.\u00ADKosten\n" +
                "6 Schreibkosten (Original) 20 Seiten 1,80 EUR EUR 36,00\n" +
                "7 Digitalisierungspauschale 1 Stück 3,00 EUR EUR 3,00\n" +
                "Rechnungsbetrag exkl. MwSt EUR 774,10\n" +
                "MwSt. 19 % EUR 147,08\n" +
                "EUR 921,18\n" +
                "Rechnungsbetrag inkl. MwSt.\n" +
                "Bankverbindung: Geschäftsführer: Thorsten Gollenstede\n" +
                "Bank: Postbank Steuernummer: 117/5114/2808");
        invoicePosBuilder.testBuild(test);
    }

    private String removeSpacesFromAmount(String amount) {
        char[] chars = amount.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]) || chars[i] == ',') {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

    public boolean isAmount(String amount) {
     /*   char[] charAmount = amount.toCharArray();
        boolean isAmount = false;
        for (int i = 0; i < charAmount.length; i++) {
            if (charAmount[i] == ',' && Character.isDigit(charAmount[i + 1]) && Character.isDigit(charAmount[i + 2])) {
                isAmount = true;
                break;
            }
        }
        return isAmount;*/
        char[] chars = amount.toCharArray();
        StringBuilder builder = new StringBuilder();
        if (chars.length != 1) {
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i]) || chars[i] == ',') {
                    builder.append(chars[i]);
                }
            }
        }
        Pattern pattern = Pattern.compile("[0-9]*\\,(\\d{2})$");
        if (pattern.matcher(builder.toString()).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public float convertToFloat(String amount) {
        char[] charAmount = amount.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charAmount.length; i++) {
            if (Character.isDigit(charAmount[i])) {
                builder.append(charAmount[i]);
            }
            if (charAmount[i] == ',') {
                builder.append(".");
            }
        }
        float floatAmount = Float.parseFloat(builder.toString());
        return floatAmount;
    }

    public boolean isSecondPosition(String position) {
        char[] charPosition = position.toCharArray();
        boolean isSecondPosition = false;
        if (charPosition.length == 2) {
            if (charPosition[1] == '.') {
                isSecondPosition = true;
            }
        }

        return isSecondPosition;
    }

    public boolean isQuantity(String quantity) {
        char[] charPosition = quantity.toCharArray();
        boolean isQuantity = false;
        if (charPosition.length == 1 && Character.isDigit(charPosition[0])) {
            isQuantity = true;
        }
        if (charPosition.length == 2) {
            if (Character.isDigit(charPosition[0]) && Character.isDigit(charPosition[1])) {
                isQuantity = true;
            }
        }
        if (charPosition.length > 1 && charPosition[charPosition.length - 2] == '.') {
            isQuantity = true;
        }


        return isQuantity;
    }


    public boolean isQuantityName(String name) {
        boolean isQuantityName = false;
        if (name.equalsIgnoreCase("km") || name.equalsIgnoreCase("stück") || name.equalsIgnoreCase("seiten")) {
            isQuantityName = true;
        }
        return isQuantityName;
    }

    public boolean isPositionName(String name) {
        boolean isPositionName = false;
        char[] positionName = name.toCharArray();
        int length = 0;
        if (!(name.equalsIgnoreCase("km") || name.equalsIgnoreCase("stück") || name.equalsIgnoreCase("seiten"))) {
            for (int i = 0; i < positionName.length; i++) {
                if (Character.isLetter(positionName[i])) {
                    length += 1;
                }
            }
        }
        if (length != 0) {
            isPositionName = true;
        }
        return isPositionName;
    }

    public float returnGrosSum(List<Float> list) {
        float net = 0;
        float brut = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > brut) {
                brut = list.get(i);
            } else {
                net = list.get(i);
            }
        }
        return brut;
    }

    public float returnOtherAmount(List<Float> list) {
        float net = 0;
        float brut = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < net) {
                net = list.get(i);
            } else {
                brut = list.get(i);
            }
        }
        return net;
    }
}