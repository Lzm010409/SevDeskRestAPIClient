package restfulapi.requests.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.entity.contact.Contact;
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


    public String postNewVoucher(Voucher voucher, VoucherPosSave voucherPosSave) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher/Factory/saveVoucher?" + TOKEN.getToken());
            request.accept("application/json");
            String input = "{\"voucher\":" + builder(voucher) + ",\"voucherPosSave\":[" + builder(voucherPosSave) + "]}";
            System.out.println(input);
            request.body("application/json", input);
            ClientResponse<String> clientResponse = request.post(String.class);
            if (clientResponse.getStatus() != 200 && clientResponse.getStatus() != 201) {
                throw new RuntimeException("Failed: Http error code: " + clientResponse.getStatus());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(clientResponse.getEntity().getBytes())));
            String output=bufferedReader.readLine();


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
            String output=bufferedReader.readLine();
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
}
