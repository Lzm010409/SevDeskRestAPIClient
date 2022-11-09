package data.entity.voucher;

import data.entity.accountingType.AccountingTypeRequest;

public class VoucherPosSaveRequest {

    private String objectName = "VoucherPos";
    private boolean mapAll = true;
    private AccountingTypeRequest accountingType;
    private int taxRate;
    private boolean net = true;
    private float sumNet;
    private float sumGross;
    private String isAsset=null;
    private String comment = null;

    public VoucherPosSaveRequest(AccountingTypeRequest accountingType, int taxRate, boolean net, float sumNet, float sumGross) {
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
}
