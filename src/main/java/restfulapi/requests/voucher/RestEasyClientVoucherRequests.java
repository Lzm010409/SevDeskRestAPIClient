package restfulapi.requests.voucher;

import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.builder.VoucherPostRequestBuilder;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RestEasyClientVoucherRequests {
    private final RootUrl rootUrl = new RootUrl();
    private final Token token = new Token();

    Map<String, Integer> accountingTypeMap = new HashMap<>();


    public void voucherGetAllRequest() {
        try {
            ClientRequest request = new ClientRequest(rootUrl.getROOTURL() + "/Voucher?token=" + token.getToken());
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
    public void voucherPostFileRequest() {
        try {

            ClientRequest request = new ClientRequest(rootUrl.getROOTURL() + "/Voucher/Factory/uploadTempFile?" + token.getToken());
            String input = "file:\"/Users/lukegollenstede/Downloads/Rechnung_3676083653.pdf\"";
            request.accept("application/json");
            request.header("Content-Type","multipart/form-data");
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

    public void voucherPostNewVoucherRequest(Voucher voucher, VoucherPosSave voucherPosSave) {
        try {
            voucher.postVoucherMapBuilder();
            voucherPosSave.postVoucherPosSaveMapBuilder();
            VoucherPostRequestBuilder voucherPostRequestBuilder = new VoucherPostRequestBuilder();
            ClientRequest request = new ClientRequest(rootUrl.getROOTURL() + "/Voucher/Factory/saveVoucher?" + token.getToken());
            request.accept("application/json");
            String input = voucherPostRequestBuilder.buildVoucherPostRequestShort(voucher) + "," + voucherPostRequestBuilder.buildVoucherPostReqeustExtendet(voucherPosSave) + "}";
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






    public RootUrl getRootUrl() {
        return rootUrl;
    }

    public Token getToken() {
        return token;
    }

    public Map<String, Integer> getAccountingTypeMap() {
        return accountingTypeMap;
    }

    public void setAccountingTypeMap(Map<String, Integer> accountingTypeMap) {
        this.accountingTypeMap = accountingTypeMap;
    }
}
