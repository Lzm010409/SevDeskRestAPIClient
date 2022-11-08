package restfulapi.requests.accountingtype;

import org.apache.commons.codec.binary.StringUtils;

import java.util.*;

public class AccountingTypeParser {

    Map<String, Integer> accountingTypeMap = new HashMap<>();

    public AccountingTypeParser() {

    }


    public List<String> parseAccountingTypeGetResponse(String response) {
        String[] split = response.split(",");
        List<String> listFirstRun = Arrays.asList(split);
        List<String> listSecondRun = new ArrayList<>();

        String temp = listFirstRun.get(0);
        String[] tempSplit = temp.split("\\{");
        listFirstRun.set(0, tempSplit[2
                ]);
        temp = "";
        for (int i = 0; i < listFirstRun.size(); i++) {
            temp += listFirstRun.get(i);
        }

        listSecondRun = Arrays.asList(temp.split("\\}\\{"));


        return listSecondRun;
    }

    public Map<String, Integer> buildingAccountingTypeMap(String response) {
        List<String> tempList = parseAccountingTypeGetResponse(response);
        for (int i = 0; i < tempList.size(); i++) {
            String[] tempArr1 = tempList.get(i).split("\"id\":\"", 2);
            String[] tempArr2 = tempArr1[1].split("\"",2);
            String tempId = tempArr2[0];
            String[] tempArr3 = tempArr2[1].split("\"name\":\"", 2);
            String[] tempArr4 = tempArr3[1].split("\"", 2);
            byte [] bytes = StringUtils.getBytesUtf8(tempArr4[0]);
            String tempName = StringUtils.newStringUtf8(bytes);

            System.out.println(tempId + " " + tempName);
            accountingTypeMap.put(tempName, Integer.parseInt(tempId));

        }

        return accountingTypeMap;
    }
}
