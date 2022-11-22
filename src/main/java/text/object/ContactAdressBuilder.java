package text.object;

import data.entity.contact.ContactAdress;
import data.entity.other.Country;

import java.util.ArrayList;
import java.util.List;

public class ContactAdressBuilder implements ObjectBuilder {
    char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ä', 'ü', 'ö', 'Ä', 'Ü', 'Ö',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Override
    public Object build(List<String> e) {
        Country country = new Country(0);
        ContactAdress contactAdress = new ContactAdress(country);
        char[][] adress = {
                e.get(0).toCharArray(),
                e.get(1).toCharArray()
        };

        List<String> endAdress = new ArrayList<>();
        for (int i = 0; i < adress.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < adress[i].length; j++) {
                for (int k = 0; k < abc.length; k++) {
                    if (adress[i][j] == abc[k]) {
                        builder.append(adress[i][j]);
                    }
                    if (adress[i][j] == ' ') {
                        adress[i][j] = '/';
                        builder.append(adress[i][j]);
                    }
                }
            }
            endAdress.add(builder.toString());
            builder = null;
        }

        contactAdress.setStreet(endAdress.get(0).replace('/', ' '));

        String[] zipCity = endAdress.get(1).split("/");
        contactAdress.setZip(zipCity[0]);
        contactAdress.setCity(zipCity[1]);


        return contactAdress;
    }
}
