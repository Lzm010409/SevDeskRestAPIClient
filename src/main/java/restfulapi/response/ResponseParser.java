package restfulapi.response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;
import data.entity.other.Part;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseParser {


    public List<Contact> parseContacts(String input) {
        String test = input.replace("{\"objects\":", "");
        char[] chars = test.toCharArray();
        chars[chars.length - 1] = ' ';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
        }
        input = stringBuilder.toString();
        Gson gson = new Gson();

        Type contactList = new TypeToken<ArrayList<Contact>>() {
        }.getType();

        List<Contact> list = gson.fromJson(input, contactList);
        return list;
    }

    public List<ContactAddress> parseContactAdresses(String input) {
        String test = input.replace("{\"objects\":", "");
        char[] chars = test.toCharArray();
        chars[chars.length - 1] = ' ';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
        }
        input = stringBuilder.toString();
        Gson gson = new Gson();

        Type contactList = new TypeToken<ArrayList<ContactAddress>>() {
        }.getType();

        List<ContactAddress> list = gson.fromJson(input, contactList);
        return list;
    }

    public Map<String, Part> parseParts(String input) {
        String test = input.replace("{\"objects\":", "");
        char[] chars = test.toCharArray();
        chars[chars.length - 1] = ' ';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
        }
        input = stringBuilder.toString();
        Gson gson = new Gson();

        Type partsList = new TypeToken<ArrayList<Part>>() {
        }.getType();

        List<Part> list = gson.fromJson(input, partsList);
        Map<String, Part> partMap = new HashMap<>();
        for (Part part : list) {
            partMap.put(part.getName(), part);
        }
        return partMap;
    }
}
