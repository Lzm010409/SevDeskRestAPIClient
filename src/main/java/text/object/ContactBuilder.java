package text.object;

import data.entity.contact.Category;
import data.entity.contact.Contact;

import java.util.List;

public class ContactBuilder implements ObjectBuilder {

    char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public Object build(List<String> e) {
        Category category = new Category(3);
        Contact contact = new Contact(category);
        char[] name = e.get(1).toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < name.length; i++) {
            for (int j = 0; j < abc.length; j++) {
                if (name[i] == abc[j]) {
                    builder.append(name[i]);
                }
                if (name[i] == ' ') {
                    name[i] = '/';
                    builder.append(name[i]);
                }
            }
        }
        String [] arr= builder.toString().split("/");
        contact.setSurename(arr[0]);
        contact.setFamilyname(arr[1]);
        return contact;
    }
}
