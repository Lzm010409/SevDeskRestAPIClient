package text.object;

import data.entity.contact.ContactAddress;
import data.entity.other.Country;

import java.util.List;

public class ContactAdressBuilder implements ObjectBuilder {
    char[] abcShort = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ä', 'ü', 'ö'};
    char[] abcBig = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ü', 'Ö'};
    char[] number = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};


    @Override
    public Object build(List<String> e) {
        Country country = new Country(0);
        ContactAddress contactAdress = new ContactAddress(country);
        String street = e.get(0);
        char[] cityZip = e.get(1).toCharArray();
        StringBuilder zip = new StringBuilder();
        StringBuilder city = new StringBuilder();
        int zipLength = 0;
        while (zip.toString().length() < 5) {
            for (int i = 0; i < cityZip.length; i++) {
                for (int j = 0; j < number.length; j++) {
                    if (cityZip[i] == number[j]) {
                        zip.append(cityZip[i]);
                        cityZip[i] = ' ';
                        zipLength += 1;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < cityZip.length; i++) {
            if (cityZip[i] != ' ') {
                city.append(cityZip[i]);
            }
        }


        contactAdress.setStreet(street);
        contactAdress.setZip(zip.toString());
        contactAdress.setCity(city.toString());


        return contactAdress;
    }
}
