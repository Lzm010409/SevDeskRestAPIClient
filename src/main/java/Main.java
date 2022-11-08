import data.entity.contact.Category;
import data.entity.contact.Contact;
import restfulapi.requests.contact.RestEasyClientContactRequests;
import restfulapi.requests.voucher.RestEasyClientVoucherRequests;

public class Main {

    public static void main(String[] args) {
        RestEasyClientVoucherRequests restEasyClientGet = new RestEasyClientVoucherRequests();
        RestEasyClientContactRequests restEasyClientContactRequests = new RestEasyClientContactRequests();
        //restEasyClientGet.voucherGetAllRequest();
/*        RestEasyClientAccountingTypeRequests restEasyClientAccountingTypeRequests = new RestEasyClientAccountingTypeRequests();
        AccountingType accountingType = new AccountingType(1);
        Voucher voucher = new Voucher(50, "default", "C", "VOU");
        VoucherPosSave voucherPosSave = new VoucherPosSave(accountingType, 19, true, 200    , 238);
        voucher.setDescritption("0922/684TG02");


        restEasyClientGet.voucherPostNewVoucherRequest(voucher, voucherPosSave);*/

        //restEasyClientGet.voucherPostFileRequest();
        Category customer = new Category(2,"Category");
        Contact contact = new Contact(customer);
        contact.setStatus(1000);
        contact.setSurename("Thorsten");
        contact.setFamilyname("Gollenstede");
        contact.setCustomerNumber("999");
        contact.setTitel("Herr");
        contact.setDescription("Papa von Luke");
        contact.setGender("Männlich");

        restEasyClientContactRequests.contactPostNewContactRequest(contact);



    }

}
