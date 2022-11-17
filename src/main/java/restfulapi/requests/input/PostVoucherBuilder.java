package restfulapi.requests.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PostVoucherBuilder implements RequestBuilder {
    private Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    public String builder(Object voucher) {
        String input = builder.toJson(voucher);
        return input;
    }
}
