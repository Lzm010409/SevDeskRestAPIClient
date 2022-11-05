package voucher;

import Entity.Voucher;
import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import requests.VoucherPostRequestBuilder;

import java.io.*;
import java.util.Base64;

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
            voucher.postVoucherMapBuilder();
            VoucherPostRequestBuilder voucherPostRequestBuilder = new VoucherPostRequestBuilder();
            ClientRequest request = new ClientRequest(ROOTURL + "/Voucher/Factory/saveVoucher?" + TOKEN);
            request.accept("application/json");
            String input = voucherPostRequestBuilder.buildVoucherPostRequestShort(voucher);
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

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        RestEasyClientGet restEasyClientGet = new RestEasyClientGet();
        //restEasyClientGet.voucherGetAllRequest();
        Voucher voucher = new Voucher(50, "default", "C", "VOU");
        voucher.setDescritption("0922/684TG02");
        restEasyClientGet.voucherPostNewVoucherRequest(voucher);
        /*try {

            File file = new File("/Users/lukegollenstede/Downloads/Rechnung_3676083653.pdf");
            byte[] bytes = FileUtils.readFileToByteArray(file);
            String s = Base64.getEncoder().encodeToString(bytes);

            System.out.println(s);

            ClientRequest request = new ClientRequest("https://my.sevdesk.de/api/v1/Voucher/Factory/uploadTempFile?token=b135d867554ab439a014eafb84078349");
            request.accept("application/json");
            request.body("application/form-data", new String (bytes));
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

        }*/
    }
}
