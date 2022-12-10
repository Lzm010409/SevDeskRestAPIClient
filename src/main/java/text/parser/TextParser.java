package text.parser;

import data.auftrag.invoices.Auftrag;
import data.auftrag.invoices.Rechtsanwalt;
import data.entity.accountingType.AccountingTypeRequest;
import data.entity.contact.Category;
import data.entity.contact.ContactAddress;
import data.entity.invoice.InvoicePos;
import data.entity.other.Country;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;
import org.jboss.logging.Logger;
import restfulapi.requests.url.Token;
import text.extractor.InvoiceTextExtractor;
import text.object.InvoicePosBuilder;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TextParser {
    private final Logger logger = Logger.getLogger(TextParser.class);

    public Auftrag parseInvoice(String invoiceText) {
        List<String> list = Arrays.asList(invoiceText.split("\n\n"));
        List<String> contact = new ArrayList<>();
        List<String> contactAdress = new ArrayList<>();
        List<String> voucherInfo = new ArrayList<>();
        List<String> voucherPos = new ArrayList<>();
        List<String> tag = new ArrayList<>();
        List<String> invoiceInfoList = new ArrayList<>();
        String invoiceDate = "";
        String gutachtennummer = "";

        Auftrag rechnung = new Auftrag();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == "") {
                list.set(i, null);
            }
        }

        if (list.get(0).equalsIgnoreCase("frau") || list.get(0).equalsIgnoreCase("herr")) {
            contact.add(parseName(list.get(0)));
            contact.add(parseName(list.get(1)));
            contactAdress.add(parseAdress(list.get(2)));
            contactAdress.add(list.get(3));

            for (int i = 0; i < (contact.size() + contactAdress.size()); i++) {
                list.set(i, null);
            }
        } else {
            contact.add(parseName(list.get(0)));
            contactAdress.add(parseAdress(list.get(1)));
            contactAdress.add(list.get(2));
            for (int i = 0; i < (contact.size() + contactAdress.size()); i++) {
                list.set(i, null);
            }
        }

        rechnung.setContact(buildContact(contact));
        rechnung.setContactAddress(buildContactAdress(contactAdress));
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                char[] invoiceDateArray = list.get(i).toCharArray();
                invoiceDate = parseInvoiceDate(invoiceDateArray);
                list.set(i, null);
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                gutachtennummer = list.get(i);
                list.set(i, null);
                break;
            }
        }
        voucherInfo.add(invoiceDate);
        voucherInfo.add(gutachtennummer);

        rechnung.setVoucher(buildVoucher(voucherInfo));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                voucherPos = Arrays.asList(list.get(i).split("\n"));
                list.set(i, null);
                break;
            }
        }

        float max = parseMaxAmount(voucherPos);
        rechnung.setVoucherPosSave(buildVoucherPos(max));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                tag = Arrays.asList(list.get(i).split("\n"));
                list.set(i, null);
                break;
            }
        }

        rechnung.setInvoiceHeadText(buildHeadText(tag));
        rechnung.setLoyer(buildLoyer(tag));

        invoiceInfoList = Arrays.asList(list.get(list.size() - 1).split("\n"));

        rechnung.setRechnungsPositions((List<InvoicePos>) new InvoicePosBuilder().build(invoiceInfoList));

        logger.log(Logger.Level.INFO, "Alle Daten korrekt ausgelesen!");
        return rechnung;
    }

    public String parseId(String toParse) {
        StringBuilder builder1 = new StringBuilder();
        String[] array = toParse.split("id\":\"");
        char[] string = array[1].toCharArray();

        for (int i = 0; i < 8; i++) {
            builder1.append(string[i]);
        }

        String id = builder1.toString();
        logger.log(Logger.Level.INFO, "Die Id des neu angelegten Kunden ist: " + id);
        return id;

    }


    public String parseInvoiceDate(char[] invoiceDateArray) {
        StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < invoiceDateArray.length; i++) {
            if (Character.isDigit(invoiceDateArray[i]) || invoiceDateArray[i] == '.') {
                builder1.append(invoiceDateArray[i]);
            }
        }
        String invoiceDate = builder1.toString();
        return invoiceDate;
    }

    public float parseMaxAmount(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("EUR")) {
                String element = list.get(i).replace("EUR", "");
                list.set(i, element);
            }
        }

        float max = 0;
        for (int i = 0; i < list.size(); i++) {
            try {
                char[] numberArray = list.get(i).toCharArray();
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < numberArray.length; j++) {
                    if (numberArray[j] == ',') {
                        numberArray[j] = '.';

                    }
                    builder.append(numberArray[j]);
                    list.set(i, builder.toString());
                }


                if (max < Float.parseFloat(list.get(i))) {
                    max = Float.parseFloat(list.get(i));
                }
            } catch (Exception e) {
                list.set(i, null);
            }
        }
        return max;
    }

    public String parseAdress(String adress) {
        char[] tempChar = adress.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tempChar.length; i++) {
            if (Character.isLetter(tempChar[i]) || Character.isDigit(tempChar[i])) {
                builder.append(tempChar[i]);
            } else {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public String parseName(String name) {
        char[] tempChar = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tempChar.length; i++) {
            if (Character.isLetter(tempChar[i]) || Character.isDigit(tempChar[i])) {
                builder.append(tempChar[i]);
            } else {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    private data.entity.contact.Contact buildContact(List<String> e) {
        Category category = new Category(3);
        data.entity.contact.Contact contact = new data.entity.contact.Contact(category);
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

    private ContactAddress buildContactAdress(List<String> e) {
        char[] abcShort = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ä', 'ü', 'ö'};
        char[] abcBig = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ü', 'Ö'};
        char[] number = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
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

    private Voucher buildVoucher(List<String> e) {
        Voucher returnVoucher = new Voucher(50, "D", "VOU");
        Calendar calendar = new DateParser().parseDate(e.get(0));
        Date voucherDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 14);

        Date paymentDate = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        returnVoucher.setVoucherDate(dateFormat.format(voucherDate));
        returnVoucher.setDeliveryDate(dateFormat.format(voucherDate));
        returnVoucher.setPaymentDeadline(dateFormat.format(paymentDate));
        returnVoucher.setDescritption((String) e.get(1));
        return returnVoucher;
    }


    private VoucherPosSave buildVoucherPos(float max) {
        AccountingTypeRequest accountingTypeRequest = new AccountingTypeRequest(26);
        int taxRate = 19;
        boolean net = false;
        float netSum = (float) (max / 1.19);
        VoucherPosSave voucherPosSaveRequest = new VoucherPosSave(accountingTypeRequest, taxRate, net, netSum, max);
        return voucherPosSaveRequest;
    }

    private String buildHeadText(List<String> e) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).contains("Rückfragen")) {
                continue;
            }
            if (e.get(i).contains("Sehr")) {
                break;
            } else {
                builder.append(e.get(i) + "\n");
            }
        }

        return builder.toString();
    }

    private Rechtsanwalt buildLoyer(List<String> e) {
        Rechtsanwalt rechtsanwalt = new Rechtsanwalt();
        rechtsanwalt.setName("Kein Rechtsanwalt");
        StringBuilder zipBuilder = new StringBuilder();
        StringBuilder cityBuilder = new StringBuilder();
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).contains("anwalt")) {
                String newRechtsanwalt = e.get(i).replace("Rechtsanwalt ", "");
                rechtsanwalt.setName(newRechtsanwalt);
                String[] tempArray = e.get(i + 1).split(",");
                rechtsanwalt.setStreet(tempArray[0]);
                char[] chars = tempArray[1].toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    if (Character.isDigit(chars[j])) {
                        zipBuilder.append(chars[j]);
                    }
                    if (Character.isLetter(chars[j])) {
                        cityBuilder.append(chars[j]);
                    }
                }
                String zip = zipBuilder.toString();
                String city = cityBuilder.toString();
                zipBuilder = null;
                cityBuilder = null;
                rechtsanwalt.setZip(zip);
                rechtsanwalt.setCity(city);
            }
        }


        return rechtsanwalt;
    }

    public String parseVoucherFileName(String input) {
        String[] array = input.split(",");
        String fileName = "";
        for (int i = 0; i < array.length; i++) {
            if (array[i].contains("filename")) {
                fileName = array[i];
                break;
            }
        }

        String newFileName = fileName.replace("\"filename\":\"", "");
        char[] chars = newFileName.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\"') {
                break;
            } else {
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();

    }


    public static void main(String[] args) {
        InvoiceTextExtractor invoiceTextExtractor = new InvoiceTextExtractor();
        String string = new String();
        try {
            string = invoiceTextExtractor.extractTextFromDoc(new File("/Users/lukegollenstede/Downloads/Rechnung_1022_651TG01.pdf"));
            System.out.println(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Token TOKEN = new Token();
        TextParser textParser = new TextParser();
        Auftrag auftrag = textParser.parseInvoice(string);


    }


}

