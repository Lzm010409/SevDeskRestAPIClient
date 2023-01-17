
package text.object;

import data.entity.invoice.InvoicePos;
import data.entity.other.Part;
import data.entity.other.Unity;
import data.filepaths.PropertyReader;
import money.AmountConverter;

import java.util.*;
import java.util.regex.Pattern;

public class InvoicePosBuilder {

    Map<String, Long> unityMap = new HashMap<String, Long>();

    Map<String, Part> partMap = new PropertyReader().readPartsData();

    @Deprecated
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

    AmountConverter amountConverter = new AmountConverter();

    public List<InvoicePos> buildInvoicePos(List<String> e) {

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
            int finalRuntime = 0;
            boolean brokenUp = false;
            for (int i = 0; i < stringList.size(); i++) {
                stringList.set(i, reworkStrings(stringList.get(i)));
            }
            if (stringList.contains("")) {
                stringList.remove("");
            }
            while (!invoicePosReady(invoicePos)) {
                invoicePos.setTaxRate(19);
                if (runtime > 10) {
                    secIndex += 1;
                    runtime = 0;
                    finalRuntime += 1;
                }
                if (finalRuntime > 5) {
                    brokenUp = true;
                    break;
                }
                if (secIndex < stringList.size() && containsPart(stringList.get(secIndex)) != "") {
                    String key = containsPart(stringList.get(secIndex));
                    data.entity.link.Part tempPart = new data.entity.link.Part(partMap.get(key).getId());
                    invoicePos.setPart(tempPart);
                    invoicePos.setUnity(partMap.get(key).getUnity());
                    invoicePos.setName(partMap.get(key).getName());
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
                    if (amountConverter.euroToCents(amountList.get(0)) > amountConverter.euroToCents(amountList.get(1))) {
                        invoicePos.setPriceGross(amountConverter.roundDoubleNumber(amountConverter.centsToEuro(amountConverter.euroToCents(amountList.get(0)) * 1.19)));
                        invoicePos.setPrice(amountList.get(1));
                        invoicePos.setPriceTax(amountConverter.centsToEuro(calculateTaxPrice(invoicePos.getPriceGross(), invoicePos.getPrice(), invoicePos.getQuantity())));
                        secIndex += 1;
                    } else {
                        invoicePos.setPriceGross(amountConverter.roundDoubleNumber(amountConverter.centsToEuro(amountConverter.euroToCents(amountList.get(1)) * 1.19)));
                        invoicePos.setPrice(amountList.get(0));
                        invoicePos.setPriceTax(amountConverter.centsToEuro(calculateTaxPrice(invoicePos.getPriceGross(), invoicePos.getPrice(), invoicePos.getQuantity())));
                        secIndex += 1;
                    }
                }
                runtime += 1;
            }
            if (brokenUp != true) {
                invoicePosList.add(invoicePos);
            }
            index += 1;
        }
        return invoicePosList;
    }

    private double calculateTaxPrice(double priceGross, double price, float quantity) {
        AmountConverter amountConverter1 = new AmountConverter();
        double returnAmount = amountConverter1.roundDoubleNumber(amountConverter1.euroToCents(priceGross) - (amountConverter1.euroToCents(price) * quantity));
        return returnAmount;
    }

    /**
     * Es gibt leider immer noch Rundungsfehler...
     *
     * @param list
     * @param amount
     * @return
     */
    public List<InvoicePos> checkIfPosListIsFull(List<InvoicePos> list, float amount) {
        double amountNow = 0;
        double maxAmount = amountConverter.euroToCents(amount / 1.19);
        AmountConverter amountConverter = new AmountConverter();
        for (InvoicePos invoicePos : list) {
            amountNow += amountConverter.euroToCents(invoicePos.getPrice() * invoicePos.getQuantity());
        }
        if (amountNow < maxAmount) {
            double difference = maxAmount - amountNow;
            for (String key : partMap.keySet()) {
                if (amountConverter.euroToCents(partMap.get(key).getPrice()) == difference) {
                    float quantitiy = 0;
                    int modulo = (int) (amountConverter.euroToCents(partMap.get(key).getPrice()) % difference);
                    if (modulo == 0) {
                        quantitiy = (float) (amountConverter.euroToCents(partMap.get(key).getPriceGross() / 1.19) / difference);
                    } else {
                        quantitiy = 1;
                    }
                    InvoicePos invoicePos = new InvoicePos();
                    data.entity.link.Part part = new data.entity.link.Part(partMap.get(key).getId());
                    invoicePos.setPart(part);
                    invoicePos.setQuantity(quantitiy);
                    invoicePos.setUnity(partMap.get(key).getUnity());
                    invoicePos.setPrice(partMap.get(key).getPrice());
                    invoicePos.setPriceGross(partMap.get(key).getPriceGross());
                    invoicePos.setPriceTax(amountConverter.centsToEuro(calculateTaxPrice(invoicePos.getPriceGross(), invoicePos.getPrice(), invoicePos.getQuantity())));
                    invoicePos.setTaxRate(19);
                    invoicePos.setName(partMap.get(key).getName());
                    list.add(invoicePos);
                    break;
                }
            }
        }
        return list;
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
            averageChar = ((hitCounter * 100) / charArray.length);
            if (averageChar > 75) {
                returnKey = key;
                break;
            }
            hitCounter = 0;
        }


        return returnKey;
    }

    private float compareStringAndGiveAverage(String input, String key) {
        int hitCounter = 0;
        float averageChar = 0;
        char[] inputArray = input.toCharArray();
        char[] keyArray = key.toCharArray();

        int runner = 0;
        if (keyArray.length < inputArray.length) {
            runner = keyArray.length;
        } else {
            runner = inputArray.length;
        }
        for (int i = 0; i < runner; i++) {
            if (inputArray[i] == keyArray[i]) {
                hitCounter += 1;
            }
        }
        averageChar = ((hitCounter * 100) / runner);
        return averageChar;
    }


    public void reworkStringList(List<String> list) {
        List<List<String>> tempList = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            tempList.add(Arrays.asList(list.get(i).split(" ")));
        }
        Map<String, Float> hitMap = new HashMap<>();
        int hitCounter = 0;
        float averageChar = 0;
        boolean containsPart = false;
        String highestAverageHit = "";
        for (int i = 0; i < tempList.size(); i++) {
            for (int j = 0; j < tempList.get(i).size(); j++) {
                if (containsPart(tempList.get(i).get(j)) != "") {
                    containsPart = true;
                    break;
                }
            }
            if (containsPart == false) {
                float max = 0;
                for (String key : partMap.keySet()) {
                    for (int k = 0; k < tempList.get(i).size(); k++) {
                        if (compareStringAndGiveAverage(tempList.get(i).get(k), key.toString()) > max) {
                            max = compareStringAndGiveAverage(tempList.get(i).get(k), key.toString());
                            highestAverageHit = tempList.get(i).get(k);
                        }
                    }
                }

                for (String key : partMap.keySet()) {
                    for (int z = 0; z < tempList.size(); z++) {
                        for (int k = 0; k < tempList.get(z).size(); k++) {
                            StringBuilder builder = new StringBuilder();
                            builder.append(highestAverageHit);
                            builder.append(tempList.get(z).get(k));
                            hitMap.put(builder.toString(), compareStringAndGiveAverage(builder.toString(), key.toString()));
                        }
                    }
                }


            }
        }
        for (String key : hitMap.keySet()) {
            if (hitMap.get(key) > 75) {
                System.out.println("Folgendes Wort wurde erkannt: " + key + " Mit folgender Übereinstimmung zu einem Key in der PartMap: " + hitMap.get(key));
            }
        }

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