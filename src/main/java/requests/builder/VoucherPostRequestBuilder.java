package requests.builder;

import Entity.Voucher;

import java.util.HashMap;
import java.util.Map;

public class VoucherPostRequestBuilder {

    private final Map<String, String> varMap = new HashMap<>();

    public VoucherPostRequestBuilder() {
        varMap.put("StartVar", "\"requests.voucher\"");
        varMap.put("objectName", "\"objectName\"");
        varMap.put("mapAll", "\"mapAll\"");
        varMap.put("voucherDate", "\"voucherDate\"");
        varMap.put("supplier", "\"supplier\"");
        varMap.put("supplierName", "\"supplierName\"");
        varMap.put("description", "\"description\"");
        varMap.put("payDate", "\"payDate\"");
        varMap.put("status", "\"status\"");
        varMap.put("taxType", "\"taxType\"");
        varMap.put("creditDebit", "\"creditDebit\"");
        varMap.put("voucherType", "\"voucherType\"");
        varMap.put("currency", "\"currency\"");
        varMap.put("propertyForeignCurrencyDeadline", "\"propertyForeignCurrencyDeadline\"");
        varMap.put("propertyExchangeRate", "\"propertyExchangeRate\"");
        varMap.put("taxSet", "\"taxSet\"");
        varMap.put("paymentDeadLine", "\"paymentDeadline\"");
        varMap.put("deliveryDate", "\"deliveryDate\"");
        varMap.put("document", "\"document\"");
        varMap.put("costCentre", "\"costCentre\"");

    }

    public String buildVoucherPostRequestShort(Voucher voucher) {

        String query = "{" +
                varMap.get("StartVar") + ":{" + varMap.get("objectName") + ":" + voucher.getVoucherMap().get("objectName") + "," +
                varMap.get("mapAll") + ":" + voucher.getVoucherMap().get("mapAll") + "," +
                varMap.get("voucherDate") + ":" + voucher.getVoucherMap().get("voucherDate") + "," +
                varMap.get("supplier") + ":" + voucher.getVoucherMap().get("supplier") + "," +
                varMap.get("supplierName") + ":" + voucher.getVoucherMap().get("supplierName") + "," +
                varMap.get("description") + ":" + voucher.getVoucherMap().get("description") + "," +
                varMap.get("payDate") + ":" + voucher.getVoucherMap().get("payDate") + "," +
                varMap.get("status") + ":" + voucher.getVoucherMap().get("status") + "," +
                varMap.get("taxType") + ":" + voucher.getVoucherMap().get("taxType") + "," +
                varMap.get("creditDebit") + ":" + voucher.getVoucherMap().get("creditDebit") + "," +
                varMap.get("voucherType") + ":" + voucher.getVoucherMap().get("voucherType") + "," +
                varMap.get("propertyForeignCurrencyDeadline") + ":" + voucher.getVoucherMap().get("propertyForeignCurrencyDeadline") + "," +
                varMap.get("propertyExchangeRate") + ":" + voucher.getVoucherMap().get("propertyExchangeRate") + "," +
                varMap.get("taxSet") + ":" + voucher.getVoucherMap().get("taxSet") + "," +
                varMap.get("paymentDeadLine") + ":" + voucher.getVoucherMap().get("paymentDeadLine") + "," +
                varMap.get("deliveryDate") + ":" + voucher.getVoucherMap().get("deliveryDate") + "," +
                varMap.get("document") + ":" + voucher.getVoucherMap().get("document") + "," +
                varMap.get("costCentre") + ":" + voucher.getVoucherMap().get("costCentre") + "}}";
        return query;
    }

    public String buildVoucherPostReqeustExtendet(Voucher voucher){
       return null;
    }

}
