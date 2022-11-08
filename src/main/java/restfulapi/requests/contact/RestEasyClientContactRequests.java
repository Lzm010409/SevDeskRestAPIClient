package restfulapi.requests.contact;

import data.entity.contact.Contact;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.builder.ContactPostRequestBuilder;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class RestEasyClientContactRequests {
    private final RootUrl rootUrl = new RootUrl();
    private final Token token = new Token();


    public void contactPostNewContactRequest(Contact contact) {
        try {
            contact.postContactMapBuilder();
            ContactPostRequestBuilder contactPostRequestBuilder = new ContactPostRequestBuilder();
            ClientRequest contactRequest = new ClientRequest(rootUrl.getROOTURL() + "/Contact?" + token.getToken());
            contactRequest.accept("application/json");
            String input = contactPostRequestBuilder.buildContactPostRequest(contact);
            System.out.println(input);
            contactRequest.body("application/json", input);
            ClientResponse<String> clientResponse = contactRequest.post(String.class);
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
}
