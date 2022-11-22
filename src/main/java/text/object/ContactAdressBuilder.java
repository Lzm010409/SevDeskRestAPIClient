package text.object;

import data.entity.contact.ContactAdress;
import data.entity.other.Country;

import java.util.List;

public class ContactAdressBuilder implements ObjectBuilder{

    @Override
    public Object build(List<String> e) {
        Country country = new Country(0);
        ContactAdress contactAdress = new ContactAdress(country);
        contactAdress.setStreet(e.get(0));
        contactAdress.setCity(e.get(1));
        return contactAdress;
    }
}
