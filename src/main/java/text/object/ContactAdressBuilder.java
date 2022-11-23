package text.object;

import data.entity.contact.ContactAddress;
import data.entity.other.Country;

import java.util.ArrayList;
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
        char[] adress = e.get(1).toCharArray();
        StringBuilder zip = new StringBuilder();
        StringBuilder city = new StringBuilder();
        List<String> endAdress = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < number.length; j++) {
                if (adress[i] == number[j] & zip.toString().length() <= 5) {
                    zip.append(adress[i]);
                    adress[i] = ' ';
                    break;
                }
            }
        }
        boolean firstBigLetter = true;
        for (int i = 0; i < adress.length; i++) {
            if(adress[i]!='\u00AD' && adress[i]!=' '){
                city.append(adress[i]);
            }if(adress[i]=='\u00AD'){
                city.append(' ');
            }
           /* for (int j = 0; j < abcBig.length; j++) {
                if (adress[i] == abcShort[j]) {
                    city.append(adress[i]);
                    break;
                }
                if (adress[i] == abcBig[j] && firstBigLetter) {
                    city.append(adress[i]);
                    firstBigLetter = false;
                    break;
                }
                if (adress[i] == abcBig[j] && !firstBigLetter) {
                    city.append(' ');
                    city.append(adress[i]);
                    break;
                }
            }*/
        }


        contactAdress.setStreet(street);
        contactAdress.setZip(zip.toString());
        contactAdress.setCity(city.toString());


        return contactAdress;
    }
}
