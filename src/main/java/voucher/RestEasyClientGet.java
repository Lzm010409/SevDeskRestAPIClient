package voucher;

import Entity.Voucher;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import javax.ws.rs.Path;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class RestEasyClientGet {
    private final String TOKEN = "";
    private final String ROOTURL = "https://my.sevdesk.de/api/v1";

    public void voucherGetAllRequest() {
        try {
            ClientRequest request = new ClientRequest(ROOTURL + "/Voucher" + TOKEN);
            request.accept("application/json");
            ClientResponse<String> response = request.get(String.class);
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

    public void voucherPostNewVoucherRequest(Voucher voucher) {
        try {
            ClientRequest request = new ClientRequest(ROOTURL + "/Voucher/Factory/saveVoucher?" + TOKEN);
            request.accept("application/json");
            String input = "{\"voucher\":{" +
                    "\"objectName\":" + "\"" + voucher.getObjectName() + "\"" + "," +
                    "\"mapAll\":" + voucher.isMapAll() + "," +
                    "\"status\":" + voucher.getStatus() + "," +
                    "\"taxType\":" + "\"" + voucher.getTaxType() + "\"" + "," +
                    "\"creditDebit\":" + "\"" + voucher.getCreditDebit() + "\"" + "," +
                    "\"voucherType\":" + "\"" + voucher.getVoucherType() + "\"" + "}}";

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

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        RestEasyClientGet restEasyClientGet = new RestEasyClientGet();
        //restEasyClientGet.voucherGetAllRequest();
        Voucher voucher = new Voucher(50, "default", "C", "VOU");
        restEasyClientGet.voucherPostNewVoucherRequest(voucher);
    }
}
