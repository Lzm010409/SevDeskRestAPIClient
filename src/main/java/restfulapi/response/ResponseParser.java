package restfulapi.response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
}
