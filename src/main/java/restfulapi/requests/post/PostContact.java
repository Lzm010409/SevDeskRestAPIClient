package restfulapi.requests.post;

import com.google.gson.Gson;
import data.entity.contact.Contact;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class PostContact {
    private final RootUrl ROOTURL = new RootUrl();
    private final Token TOKEN = new Token();

    private ClientRequest request;

    public void postNewContact(Contact contactRequest) {
        try {
            Gson builder = new Gson();
            ClientRequest request = new ClientRequest(ROOTURL.getROOTURL() + "/Contact?" + TOKEN.getToken());
            request.accept("application/json");
            String input = builder.toJson(contactRequest);
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

            request.clear();
        } catch (Exception e) {

        }
    }
}
