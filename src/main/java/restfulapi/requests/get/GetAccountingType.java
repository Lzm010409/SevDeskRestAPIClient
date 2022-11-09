package restfulapi.requests.get;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class GetAccountingType {
private final RootUrl ROOTURL= new RootUrl();
private final Token TOKEN = new Token();

private ClientRequest request;
    public String getAllAccountingTypes() {
        try {
            request = new ClientRequest(ROOTURL.getROOTURL() + "/AccountingType?" + TOKEN.getToken());
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
}
