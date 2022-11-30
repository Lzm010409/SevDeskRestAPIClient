package text.object;

import data.entity.contact.Category;
import data.entity.contact.Contact;

import java.util.List;

public class ContactBuilder implements ObjectBuilder {

    @Override
    public Object build(List<String> e) {
        Category category = new Category(3);
        Contact contact = new Contact(category);
        StringBuilder builder = new StringBuilder();
        if (e.size() != 1) {
            contact.setGender(e.get(0));
            String[] arr = e.get(1).split(" ");
            contact.setSurename(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                builder.append(arr[i]);
                builder.append(" ");
            }
            contact.setFamilyname(builder.toString());
        } else {
            contact.setSurename(e.get(0));
        }

        return contact;
    }


}
