package restfulapi.requests.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;
import data.entity.invoice.Invoice;
import data.entity.invoice.InvoicePos;
import data.entity.other.Supplier;
import data.entity.other.Tag;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class PostBuilder implements RequestBuilder {
    private Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private final RootUrl ROOTURL = new RootUrl();

    @Override
    public String builder(Object voucher) {
        String input = builder.toJson(voucher);
        return input;
    }


    public String postNewVoucher(Voucher voucher, VoucherPosSave voucherPosSave, String creditDebit, Token TOKEN) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher/Factory/saveVoucher?" + TOKEN.getToken());
            request.accept("application/json");
            if (creditDebit.equalsIgnoreCase("d")) {
                Supplier supplier = new Supplier(54378628);
                voucher.setSupplier(supplier);

            } else {
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

    public String postNewVoucherFile(File file, Token TOKEN) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher/Factory/uploadTempFile?" + TOKEN.getToken());
            request.accept("application/json");
            request.body(MediaType.MULTIPART_FORM_DATA_TYPE, file);
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

    public String postNewContact(Contact contact, Token TOKEN) {
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

    public String postNewContactAdress(ContactAddress contact, long contactId, Token TOKEN) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/ContactAddress?" + TOKEN.getToken());
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

    public String postNewTag(Tag tag, Token TOKEN) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Tag/Factory/create?" + TOKEN.getToken());
            request.accept("application/json");
            String input = builder(tag);
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

    public String postNewInvoice(Invoice invoice, List<InvoicePos> invoicePos, Token TOKEN) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Invoice/Factory/saveInvoice?" + TOKEN.getToken());
            request.accept("application/json");
            String input = "{\"invoice\":" + builder(invoice) + ",\"invoicePosSave\":" + builder(invoicePos) + "}";
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


    }
}
