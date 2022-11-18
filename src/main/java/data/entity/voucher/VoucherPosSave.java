package data.entity.voucher;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import data.entity.accountingType.AccountingTypeRequest;

public class VoucherPosSave {
    @Expose(serialize = false, deserialize = true)
    private int id;

    @Expose(serialize = false, deserialize = true)
    private Voucher voucher;

    @Expose
    private String objectName = "VoucherPos";
    @Expose
    private boolean mapAll = true;
    @Expose
    private AccountingTypeRequest accountingType;
    @Expose
    private int taxRate;
    @Expose
    private boolean net = true;
    @Expose
    private float sumNet;
    @Expose(serialize = false, deserialize = true)
    private float sumTax;

    @Expose
    private float sumGross;
    @Expose
    private String isAsset = null;
    @Expose
    private String comment = null;

    public VoucherPosSave(AccountingTypeRequest accountingType, int taxRate, boolean net, float sumNet, float sumGross) {
        this.accountingType = accountingType;
        this.taxRate = taxRate;
        this.net = net;
        this.sumNet = sumNet;
        this.sumGross = sumGross;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public boolean isMapAll() {
        return mapAll;
    }

    public void setMapAll(boolean mapAll) {
        this.mapAll = mapAll;
    }

    public AccountingTypeRequest getAccountingType() {
        return accountingType;
    }

    public void setAccountingType(AccountingTypeRequest accountingType) {
        this.accountingType = accountingType;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public boolean isNet() {
        return net;
    }

    public void setNet(boolean net) {
        this.net = net;
    }

    public float getSumNet() {
        return sumNet;
    }

    public void setSumNet(float sumNet) {
        this.sumNet = sumNet;
    }

    public float getSumGross() {
        return sumGross;
    }

    public void setSumGross(float sumGross) {
        this.sumGross = sumGross;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static void main(String[] args) {
        AccountingTypeRequest accountingTypeRequest = new AccountingTypeRequest(1);
        VoucherPosSave voucherPosSaveRequest = new VoucherPosSave(accountingTypeRequest, 19, true, 100, 119);
        Gson builder = new Gson();
        String output = builder.toJson(voucherPosSaveRequest);

        System.out.println(output);
    }
}
