package requests.accountingtype;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import requests.url.RootUrl;
import requests.url.Token;
import response.parser.Parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RestEasyClientAccountingTypeRequests {
    private List<Object> list = new ArrayList<>();
    private final RootUrl rootUrl = new RootUrl();
    private final Token token = new Token();

    public RestEasyClientAccountingTypeRequests() {

    }


    public void accountingTypeGetAllRequest() {
        try {
            ClientRequest request = new ClientRequest(rootUrl.getROOTURL() + "/AccountingType?token=" + token.getToken());
            request.accept("application/json");
            ClientResponse<String> response = request.get(String.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed: Http error code: " + response.getStatus());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
            String output = bufferedReader.readLine();

            Parser parser = new Parser();
            parser.buildingAccountingTypeMap(output);

            System.out.println("Output from Server: \n");

          /*  while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }*/



        } catch (Exception e) {

        }
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
