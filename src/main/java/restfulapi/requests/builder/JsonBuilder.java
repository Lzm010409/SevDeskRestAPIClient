package restfulapi.requests.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;
import data.entity.invoice.Invoice;
import data.entity.invoice.InvoicePos;
import data.entity.other.Country;
import data.entity.other.Supplier;
import data.entity.other.Tag;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;

import java.util.List;

public class JsonBuilder {

    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public String build(Voucher voucher, VoucherPosSave voucherPosSave, String creditDebit) {

        if (creditDebit.equalsIgnoreCase("d")) {
            Supplier supplier = new Supplier(54378628);
            voucher.setSupplier(supplier);

        } else {
            Supplier supplier = new Supplier(54445210);
            voucher.setSupplier(supplier);

        }
        String input = "{\"voucher\":" + gson.toJson(voucher) + ",\"voucherPosSave\":[" + gson.toJson(voucherPosSave) + "]}";
        return input;
    }

    public String build(Voucher voucher, String fileName) {


        String input = "{\"voucher\":" + gson.toJson(voucher) + ",\"filename\":" + "\"" + fileName + "\"" + "}";
        return input;
    }

    public String build(Contact contact) {


        String input = gson.toJson(contact);
        return input;
    }

    public String build(ContactAddress contactAdress, data.entity.link.Contact contact) {
        Country country = new Country(1);
        contactAdress.setContact(contact);
        contactAdress.setCountry(country);
        String input = gson.toJson(contactAdress);
        return input;
    }

    public String build(Tag tag) {
        String input = gson.toJson(tag);
        return input;
    }

    public String build(Invoice invoice, List<InvoicePos> invoicePos) {
        String input = "{\"invoice\":" + gson.toJson(invoice) + ",\"invoicePosSave\":" + gson.toJson(invoicePos) + "}";
        return input;
    }


}
