
package text.object;

import data.entity.invoice.InvoicePos;
import data.entity.other.Unity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoicePosBuilder {

    Map<String, Long> unityMap = new HashMap<String, Long>();

    public Object build(List<String> e) {
        unityMap.put("km", Long.valueOf(10));
        unityMap.put("stück", Long.valueOf(1));
        unityMap.put("pauschal", Long.valueOf(7));
        unityMap.put("seiten", Long.valueOf(371137));
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
            int quantity = 0;
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
                        quantity = Integer.parseInt(stringArray[k]);
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

    public boolean isAmount(String amount) {
        char[] charAmount = amount.toCharArray();
        boolean isAmount = false;
        for (int i = 0; i < charAmount.length; i++) {
            if (charAmount[i] == ',' && Character.isDigit(charAmount[i + 1]) && Character.isDigit(charAmount[i + 2])) {
                isAmount = true;
                break;
            }
        }
        return isAmount;
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