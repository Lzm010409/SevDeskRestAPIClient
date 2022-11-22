package restfulapi.requests.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.entity.contact.Category;
import data.entity.contact.Contact;
import data.entity.contact.ContactAdress;
import data.entity.other.Supplier;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class PostBuilder implements RequestBuilder {
    private Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private final RootUrl ROOTURL = new RootUrl();
    private final Token TOKEN = new Token();

    @Override
    public String builder(Object voucher) {
        String input = builder.toJson(voucher);
        return input;
    }


    public String postNewVoucher(Voucher voucher, VoucherPosSave voucherPosSave, String creditDebit) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher/Factory/saveVoucher?" + TOKEN.getToken());
            request.accept("application/json");
            if(creditDebit.equalsIgnoreCase("c")){
                Supplier supplier = new Supplier(54378628);
                voucher.setSupplier(supplier);

            }else{
                Supplier supplier = new Supplier(54445210);
                voucher.setSupplier(supplier);

            }
            String input = "{\"voucher\":" + builder(voucher) + ",\"voucherPosSave\":[" + builder(voucherPosSave) + "]}";
            System.out.println(input);
            request.body("application/json", input);
            ClientResponse<String> clientResponse = request.post(String.class);
            if (clientResponse.getStatus() != 200 && clientResponse.getStatus() != 201) {
                throw new RuntimeException("Failed: Http error code: " + clientResponse.getStatus());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(clientResponse.getEntity().getBytes())));
            String output = bufferedReader.readLine();


            request.clear();

            return output;
        } catch (Exception e) {
            return null;
        }
    }

    public String postNewVoucherFile() {
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
            String output = bufferedReader.readLine();
            request.clear();
            return output;
        } catch (Exception e) {
            return null;
        }

    }

    public String postNewContact(Contact contact) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Contact?" + TOKEN.getToken());
            request.accept("application/json");
            String input = builder(contact);
            System.out.println(input);
            request.body("application/json", input);
            ClientResponse<String> clientResponse = request.post(String.class);
            if (clientResponse.getStatus() != 200 && clientResponse.getStatus() != 201) {
                throw new RuntimeException("Failed: Http error code: " + clientResponse.getStatus());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(clientResponse.getEntity().getBytes())));
            String output = bufferedReader.readLine();


            request.clear();

            return output;
        } catch (Exception e) {
            return null;
        }

    }

    public String postNewContactAdress(ContactAdress contact, long contactId) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/ContactAdress?" + TOKEN.getToken());
            request.accept("application/json");
            String input = "{\"contact\":{" +
                    "\"id\":" + contactId + ", \"objectName\":\"Contact\"}," +
                    "\"street\":" + ("\"" + contact.getStreet() + "\",") +
                    "\"zip\":" + ("\"" + contact.getZip() + "\",") +
                    "\"city\":" + ("\"" + contact.getCity() + "\",") +
                    "\"country\":{ \"id\":1,\"objectName\":\"StaticCountry\"}}";


            System.out.println(input);
            request.body("application/json", input);
            ClientResponse<String> clientResponse = request.post(String.class);
            if (clientResponse.getStatus() != 200 && clientResponse.getStatus() != 201) {
                throw new RuntimeException("Failed: Http error code: " + clientResponse.getStatus());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(clientResponse.getEntity().getBytes())));
            String output = bufferedReader.readLine();


            request.clear();

            return output;
        } catch (Exception e) {
            return null;
        }

    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        PostBuilder postBuilder = new PostBuilder();
        Category category = new Category(2);
        Contact contact = new Contact(category);
        String output = postBuilder.postNewContact(contact);

        StringBuilder builder1 = new StringBuilder();


        String[] array = output.split("id\":\"");
        char[] string = array[1].toCharArray();

        for (int i = 0; i < 8; i++) {
            builder1.append(string[i]);
        }
        String id = builder1.toString();


    }
}
