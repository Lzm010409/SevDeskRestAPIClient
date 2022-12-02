package text.object;

import data.entity.other.Unity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoicePosBuilder {

    Map<String, Long> unityMap = new HashMap<String, Long>();

    public Object build(List<String> e) {
        unityMap.put("Km", Long.valueOf(10));
        unityMap.put("Stück", Long.valueOf(1));
        unityMap.put("Pauschal", Long.valueOf(7));
        unityMap.put("Seiten", Long.valueOf(371137));
        List<Object> invoiceList = new ArrayList<>();
        for (int i = 0; i < e.size(); i++) {
            if (!e.get(i).contains("Gesamtpreis")) {
                e.set(i, null);
            } else {
                e.set(i, null);
                break;
            }
        }
        List<String> tempList = new ArrayList<>();
        int pos;
        int quantity;
        Unity unity;
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

        //Möglichkeit finden, um die Strings korrekt zu trennen!


        return null;
    }
}
