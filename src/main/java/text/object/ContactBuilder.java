package text.object;

import data.entity.contact.Category;
import data.entity.contact.Contact;

import java.util.List;

public class ContactBuilder implements ObjectBuilder{
    @Override
    public Object build(List<String> e) {
        Category category = new Category(3);
        Contact contact = new Contact(category);
        //String [] arr= e.get(1).split("\\s");
        contact.setSurename(e.get(1));
        //contact.setFamilyname(arr[1]);
        return contact;
    }
}
