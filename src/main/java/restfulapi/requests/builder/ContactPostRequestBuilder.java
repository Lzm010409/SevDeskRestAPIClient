package restfulapi.requests.builder;

import data.entity.contact.Contact;

import java.util.HashMap;
import java.util.Map;

public class ContactPostRequestBuilder {

    private final Map<String, String> varContactMap = new HashMap<>();

    public ContactPostRequestBuilder() {
        varContactMap.put("name", "\"name\"");
        varContactMap.put("status", "\"status\"");
        varContactMap.put("customerNumber", "\"customerNumber\"");
        varContactMap.put("sureName", "\"surename\"");
        varContactMap.put("familyName", "\"familyname\"");
        varContactMap.put("titel", "\"titel\"");
        varContactMap.put("category", "\"category\"");
        varContactMap.put("categoryId", "\"id\"");
        varContactMap.put("categoryObjectName", "\"objectName\"");
        varContactMap.put("description", "\"description\"");
        varContactMap.put("academicTitle", "\"academicTitle\"");
        varContactMap.put("gender", "\"gender\"");
        varContactMap.put("bankAccount", "\"bankAccount\"");
        varContactMap.put("taxType", "\"taxType\"");

    }

    public String buildContactPostRequest(Contact contact) {
        String query = "{" +
                varContactMap.get("name") + ":" + contact.getContactMap().get("name") + "," +
                varContactMap.get("status") + ":" + contact.getContactMap().get("status") + "," +
                varContactMap.get("customerNumber") + ":" + contact.getContactMap().get("customerNumber") + "," +
                varContactMap.get("sureName") + ":" + contact.getContactMap().get("sureName") + "," +
                varContactMap.get("familyName") + ":" + contact.getContactMap().get("familyName") + "," +
                varContactMap.get("titel") + ":" + contact.getContactMap().get("titel") + "," +
                varContactMap.get("category") + ":{" + varContactMap.get("categoryId") + ":" + contact.getContactMap().get("categoryId") + "," + varContactMap.get("categoryObjectName") + ":" + contact.getContactMap().get("categoryObjectName") + "}," +
                varContactMap.get("description") + ":" + contact.getContactMap().get("description") + "," +
                varContactMap.get("academicTitle") + ":" + contact.getContactMap().get("academicTitle") + "," +
                varContactMap.get("gender") + ":" + contact.getContactMap().get("gender") + "," +
                varContactMap.get("bankAccount") + ":" + contact.getContactMap().get("bankAccount") + "," +
                varContactMap.get("taxType") + ":" + contact.getContactMap().get("taxType") + "}";

        return query;
    }
}
