package text.object;

import data.entity.contact.Category;
import data.entity.contact.Contact;

import java.util.List;

public class ContactBuilder implements ObjectBuilder{
    @Override
    public Object build(List<String> e) {
    ??    Category category = new Category(3);
        Contact contact = new Contact(category);
        char []name= e.get(1).toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<name[i];i++){
            if(name[i]==' '){
                name[i]='/';
            }
            if (name[i]!=' '){
                builder.append(name[i]);
            }
        }
        String []ouput = builder.toString().split("/");
        contact.setSurename(ouput[0]);
        contact.setFamilyname(ouput[1]);
        return contact;
    }
}
