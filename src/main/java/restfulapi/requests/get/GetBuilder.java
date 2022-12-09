package restfulapi.requests.get;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.entity.contact.Contact;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetBuilder {
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

    public List<Contact> getAllContacts(Token TOKEN) {
        try {
            request = new ClientRequest(ROOTURL.getROOTURL() + "/Contact?" + TOKEN.getToken());
            request.accept("application/json");
            ClientResponse<String> response = request.get(String.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed: Http error code: " + response.getStatus());

            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
            String output = bufferedReader.readLine();
            String test=output.replace("{\"objects\":", "");
            char[] chars = test.toCharArray();
            chars[chars.length - 1] = ' ';
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                stringBuilder.append(chars[i]);
            }
            output = stringBuilder.toString();
            Gson gson = new Gson();

            Type contactList = new TypeToken<ArrayList<Contact>>() {
            }.getType();

            List<Contact> list = gson.fromJson(output, contactList);
            return list;

        } catch (Exception e) {
            return null;
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

        GetBuilder getVoucher = new GetBuilder();



    }
}
