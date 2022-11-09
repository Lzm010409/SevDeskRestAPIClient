package restfulapi.requests.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.entity.accountingType.AccountingTypeRequest;
import data.entity.contact.Category;
import data.entity.contact.ContactRequest;
import data.entity.other.Supplier;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSaveRequest;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class PostVoucher {

    private final RootUrl ROOTURL = new RootUrl();
    private final Token TOKEN = new Token();

    private Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public PostVoucher() {

    }

    public void postNewVoucher(Voucher voucher, VoucherPosSaveRequest voucherPosSave) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher/Factory/saveVoucher?" + TOKEN.getToken());
            request.accept("application/json");
            String input = "{\"voucher\":" + builder.toJson(voucher) + ",\"voucherPosSave\":[" + builder.toJson(voucherPosSave) + "]}";
            System.out.println(input);
            request.body("application/json", input);
            ClientResponse<String> clientResponse = request.post(String.class);
            if (clientResponse.getStatus() != 200 && clientResponse.getStatus() != 201) {
                throw new RuntimeException("Failed: Http error code: " + clientResponse.getStatus());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(clientResponse.getEntity().getBytes())));
            String output;

            System.out.println("Output from Server: \n");

            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }

            request.clear();
        } catch (Exception e) {

        }
    }

    public void voucherPostFileRequest() {
        try {

            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher/Factory/uploadTempFile?" + TOKEN.getToken());
            String input = "file:\"/Users/lukegollenstede/Downloads/Rechnung_3676083653.pdf\"";
            request.accept("application/json");
            request.header("Content-Type", "multipart/form-data");
            request.body("multipart/form-data", input);
            ClientResponse<String> response = request.post(String.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed: Http error code: " + response.getStatus());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
            String output;

            System.out.println("Output from Server: \n");

            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        Category category = new Category(2);
        ContactRequest contactRequest = new ContactRequest(category);
        Supplier supplier = new Supplier(200);
        Voucher voucher = new Voucher(100, "C", "VOU", null);
        AccountingTypeRequest accountingType = new AccountingTypeRequest(1);
        VoucherPosSaveRequest voucherPosSave = new VoucherPosSaveRequest(accountingType, 19, true, 200, 238);
        PostVoucher postVoucher = new PostVoucher();
        //PostContact postContact = new PostContact();
       // postContact.postNewContact(contactRequest);
        postVoucher.postNewVoucher(voucher, voucherPosSave);
    }
}
