package text.object;

import data.entity.contact.Category;
import data.entity.contact.Contact;

import java.util.List;

public class ContactBuilder implements ObjectBuilder {

    char[] abcShort = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z','ä', 'ü', 'ö'};
    char[] abcBig = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','Ä', 'Ü', 'Ö'};

    @Override
    public Object build(List<String> e) {
        boolean nachname = false;
        Category category = new Category(3);
        Contact contact = new Contact(category);
        char[] name = e.get(1).toCharArray();
        StringBuilder builder = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        builder.append(name[0]);
        for (int i = 1; i < name.length; i++) {
            for (int j = 0; j < abcShort.length; j++) {
                if (name[i] == abcShort[j] && nachname == false) {
                    builder.append(name[i]);
                }
                if (name[i] == abcBig[j]) {
                    builder2.append(name[i]);
                    nachname=true;
                }
                if (name[i] == abcShort[j] && nachname == true) {
                    builder2.append(name[i]);
                }
            }
        }
        contact.setSurename(builder.toString());
        contact.setFamilyname(builder2.toString());
        return contact;
    }
}
