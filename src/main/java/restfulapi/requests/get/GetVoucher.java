package restfulapi.requests.get;

import com.google.gson.Gson;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class GetVoucher {
    private final RootUrl ROOTURL = new RootUrl();
    private final Token TOKEN = new Token();

    private ClientRequest request;

    public void getAllVouchers() {
        try {
            request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher?" + TOKEN.getToken());
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

    public String getVoucherById(int id) {
        try {
            request = new ClientRequest(ROOTURL.getROOTURL() + "/Voucher/" + id + "?" + TOKEN.getToken());
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
            return output;
        } catch (Exception e) {
            return null;
        }

    }

    public static void main(String[] args) {
        Gson g = new Gson();
        GetVoucher getVoucher = new GetVoucher();
        GetAccountingType getAccountingType = new GetAccountingType();

        /*VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse = g.fromJson(getVoucher.getVoucherById(51628775), VoucherResponse.class);
        System.out.println(voucherResponse.getId());*/
        Gson test = new Gson();


    }
}
