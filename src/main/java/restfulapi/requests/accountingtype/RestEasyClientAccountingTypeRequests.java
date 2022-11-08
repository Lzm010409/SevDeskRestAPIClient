package restfulapi.requests.accountingtype;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import restfulapi.requests.url.RootUrl;
import restfulapi.requests.url.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestEasyClientAccountingTypeRequests {
    private List<Object> list = new ArrayList<>();
    private final RootUrl rootUrl = new RootUrl();
    private final Token token = new Token();

    Map<String, Integer> accountingTypeMap = new HashMap<>();
    public RestEasyClientAccountingTypeRequests() {
    }


    public Map<String, Integer> accountingTypeGetAllRequest() {
        try {
            ClientRequest request = new ClientRequest(rootUrl.getROOTURL() + "/AccountingType?" + token.getToken());
            request.accept("application/json");
            ClientResponse<String> response = request.get(String.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed: Http error code: " + response.getStatus());

            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
            String output = bufferedReader.readLine();
            AccountingTypeParser parser = new AccountingTypeParser();
            this.accountingTypeMap = parser.buildingAccountingTypeMap(output);
            System.out.println("Output from Server: \n");
            return this.accountingTypeMap;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Map<String, Integer> getAccountingTypeMap() {
        return accountingTypeMap;
    }

    public void setAccountingTypeMap(Map<String, Integer> accountingTypeMap) {
        this.accountingTypeMap = accountingTypeMap;
    }
}
