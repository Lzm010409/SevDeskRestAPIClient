package requests.voucher;

import Entity.accountingType.AccountingType;
import Entity.voucher.Voucher;
import Entity.voucher.VoucherPosSave;
import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import requests.accountingtype.RestEasyClientAccountingTypeRequests;
import requests.builder.VoucherPostRequestBuilder;
import requests.url.RootUrl;
import requests.url.Token;

import java.io.*;
import java.util.Base64;
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

    public static void main(String[] args) {
       RestEasyClientVoucherRequests restEasyClientGet = new RestEasyClientVoucherRequests();
        //restEasyClientGet.voucherGetAllRequest();
/*        RestEasyClientAccountingTypeRequests restEasyClientAccountingTypeRequests = new RestEasyClientAccountingTypeRequests();
        AccountingType accountingType = new AccountingType(1);
        Voucher voucher = new Voucher(50, "default", "C", "VOU");
        VoucherPosSave voucherPosSave = new VoucherPosSave(accountingType, 19, true, 200    , 238);
        voucher.setDescritption("0922/684TG02");


        restEasyClientGet.voucherPostNewVoucherRequest(voucher, voucherPosSave);*/

           restEasyClientGet.voucherPostFileRequest();
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
