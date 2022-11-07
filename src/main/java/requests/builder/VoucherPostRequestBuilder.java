package requests.builder;

import Entity.voucher.Voucher;
import Entity.voucher.VoucherPosSave;

import java.util.HashMap;
import java.util.Map;

public class VoucherPostRequestBuilder {

    private final Map<String, String> varVoucherMap = new HashMap<>();
    private final Map<String, String> varVoucherPosSaveMap = new HashMap<>();

    public VoucherPostRequestBuilder() {
        varVoucherMap.put("StartVar", "\"voucher\"");
        varVoucherMap.put("objectName", "\"objectName\"");
        varVoucherMap.put("mapAll", "\"mapAll\"");
        varVoucherMap.put("voucherDate", "\"voucherDate\"");
        varVoucherMap.put("supplier", "\"supplier\"");
        varVoucherMap.put("supplierName", "\"supplierName\"");
        varVoucherMap.put("description", "\"description\"");
        varVoucherMap.put("payDate", "\"payDate\"");
        varVoucherMap.put("status", "\"status\"");
        varVoucherMap.put("taxType", "\"taxType\"");
        varVoucherMap.put("creditDebit", "\"creditDebit\"");
        varVoucherMap.put("voucherType", "\"voucherType\"");
        varVoucherMap.put("currency", "\"currency\"");
        varVoucherMap.put("propertyForeignCurrencyDeadline", "\"propertyForeignCurrencyDeadline\"");
        varVoucherMap.put("propertyExchangeRate", "\"propertyExchangeRate\"");
        varVoucherMap.put("taxSet", "\"taxSet\"");
        varVoucherMap.put("paymentDeadLine", "\"paymentDeadline\"");
        varVoucherMap.put("deliveryDate", "\"deliveryDate\"");
        varVoucherMap.put("document", "\"document\"");
        varVoucherMap.put("costCentre", "\"costCentre\"");

        varVoucherPosSaveMap.put("StartVar", "\"voucherPosSave\"");
        varVoucherPosSaveMap.put("objectName", "\"objectName\"");
        varVoucherPosSaveMap.put("mapAll", "\"mapAll\"");
        varVoucherPosSaveMap.put("accountingType", "\"accountingType\"");
        varVoucherPosSaveMap.put("accountingTypeId", "\"id\"");
        varVoucherPosSaveMap.put("accountingTypeObjectName", "\"objectName\"");
        varVoucherPosSaveMap.put("taxRate", "\"taxRate\"");
        varVoucherPosSaveMap.put("net", "\"net\"");
        varVoucherPosSaveMap.put("sumNet", "\"sumNet\"");
        varVoucherPosSaveMap.put("sumGross", "\"sumGross\"");
        varVoucherPosSaveMap.put("comment", "\"comment\"");
    }

    public String buildVoucherPostRequestShort(Voucher voucher) {

        String query = "{" +
                varVoucherMap.get("StartVar") + ":{" + varVoucherMap.get("objectName") + ":" + voucher.getVoucherMap().get("objectName") + "," +
                varVoucherMap.get("mapAll") + ":" + voucher.getVoucherMap().get("mapAll") + "," +
                varVoucherMap.get("voucherDate") + ":" + voucher.getVoucherMap().get("voucherDate") + "," +
                varVoucherMap.get("supplier") + ":" + voucher.getVoucherMap().get("supplier") + "," +
                varVoucherMap.get("supplierName") + ":" + voucher.getVoucherMap().get("supplierName") + "," +
                varVoucherMap.get("description") + ":" + voucher.getVoucherMap().get("description") + "," +
                varVoucherMap.get("payDate") + ":" + voucher.getVoucherMap().get("payDate") + "," +
                varVoucherMap.get("status") + ":" + voucher.getVoucherMap().get("status") + "," +
                varVoucherMap.get("taxType") + ":" + voucher.getVoucherMap().get("taxType") + "," +
                varVoucherMap.get("creditDebit") + ":" + voucher.getVoucherMap().get("creditDebit") + "," +
                varVoucherMap.get("voucherType") + ":" + voucher.getVoucherMap().get("voucherType") + "," +
                varVoucherMap.get("propertyForeignCurrencyDeadline") + ":" + voucher.getVoucherMap().get("propertyForeignCurrencyDeadline") + "," +
                varVoucherMap.get("propertyExchangeRate") + ":" + voucher.getVoucherMap().get("propertyExchangeRate") + "," +
                varVoucherMap.get("taxSet") + ":" + voucher.getVoucherMap().get("taxSet") + "," +
                varVoucherMap.get("paymentDeadLine") + ":" + voucher.getVoucherMap().get("paymentDeadLine") + "," +
                varVoucherMap.get("deliveryDate") + ":" + voucher.getVoucherMap().get("deliveryDate") + "," +
                varVoucherMap.get("document") + ":" + voucher.getVoucherMap().get("document") + "," +
                varVoucherMap.get("costCentre") + ":" + voucher.getVoucherMap().get("costCentre") + "}";
        return query;
    }

    public String buildVoucherPostReqeustExtendet(VoucherPosSave voucher) {
        String voucherPosSaveQuery = varVoucherPosSaveMap.get("StartVar") + ":[{" + varVoucherPosSaveMap.get("objectName") + ":" + voucher.getVoucherPosSaveMap().get("objectName") + "," +
                varVoucherPosSaveMap.get("mapAll") + ":" + voucher.getVoucherPosSaveMap().get("mapAll") + "," +
                varVoucherPosSaveMap.get("accountingType") + ":{" + varVoucherPosSaveMap.get("accountingTypeId") + ":" + voucher.getVoucherPosSaveMap().get("accountingTypeId") + "," + varVoucherPosSaveMap.get("accountingTypeObjectName") + ":" + voucher.getVoucherPosSaveMap().get("accountingTypeObjectName") + "}," +
                varVoucherPosSaveMap.get("taxRate") + ":" + voucher.getVoucherPosSaveMap().get("taxRate") + "," +
                varVoucherPosSaveMap.get("net") + ":" + voucher.getVoucherPosSaveMap().get("net") + "," +
                varVoucherPosSaveMap.get("sumNet") + ":" + voucher.getVoucherPosSaveMap().get("sumNet") + "," +
                varVoucherPosSaveMap.get("sumGross") + ":" + voucher.getVoucherPosSaveMap().get("sumGross") + "," +
                varVoucherPosSaveMap.get("comment") + ":" + voucher.getVoucherPosSaveMap().get("comment") + "}]";

        return voucherPosSaveQuery;
    }
}
