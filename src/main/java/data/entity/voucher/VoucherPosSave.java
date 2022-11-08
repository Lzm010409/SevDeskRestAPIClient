package data.entity.voucher;

import data.entity.accountingType.AccountingType;

import java.util.HashMap;
import java.util.Map;

public class VoucherPosSave {

    private String objectName = "VoucherPos";
    private boolean mapAll = true;
    private AccountingType accountingType;
    private int taxRate;
    private boolean net = true;
    private float sumNet;
    private float sumGross;
    private String comment = null;
    Map<String, Object> voucherPosSaveMap = new HashMap<>();


    public VoucherPosSave(AccountingType accountingType, int taxRate, boolean net, float sumNet, float sumGross) {
        this.accountingType = accountingType;
        this.taxRate = taxRate;
        this.net = net;
        this.sumNet = sumNet;
        this.sumGross = sumGross;


    }

    public void postVoucherPosSaveMapBuilder() {
        if (getObjectName() != null) {
            voucherPosSaveMap.put("objectName", "\"" + getObjectName() + "\"");
        } else {
            voucherPosSaveMap.put("objectName", null);
        }
        if (isMapAll() == true) {
            voucherPosSaveMap.put("mapAll", isMapAll());
        } else {
            voucherPosSaveMap.put("mapAll", null);
        }
        if (getAccountingType() != null) {
            voucherPosSaveMap.put("accountingTypeId", getAccountingType().getId());
            voucherPosSaveMap.put("accountingTypeObjectName", "\"" + "AccountingType" + "\"");
        } else {
            voucherPosSaveMap.put("accountingTypeId", null);
            voucherPosSaveMap.put("accountingTypeObjectName", null);
        }
        if (getTaxRate() != 0) {
            voucherPosSaveMap.put("taxRate", getTaxRate());
        } else {
            voucherPosSaveMap.put("taxRate", null);
        }
        if (isNet() == true) {
            voucherPosSaveMap.put("net", isNet());
        } else {
            voucherPosSaveMap.put("net", null);
        }
        if (getSumNet() != 0) {
            voucherPosSaveMap.put("sumNet", getSumNet());
        } else {
            voucherPosSaveMap.put("sumNet", null);
        }
        if (getSumGross() != 0) {
            voucherPosSaveMap.put("sumGross", getSumGross());
        } else {
            voucherPosSaveMap.put("sumGross", null);
        }
        if (getComment() != null) {
            voucherPosSaveMap.put("comment", "\"" + getComment() + "\"");
        } else {
            voucherPosSaveMap.put("comment", null);
        }
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

    public AccountingType getAccountingType() {
        return accountingType;
    }

    public void setAccountingType(AccountingType accountingType) {
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

    public Map<String, Object> getVoucherPosSaveMap() {
        return voucherPosSaveMap;
    }

    public void setVoucherPosSaveMap(Map<String, Object> voucherPosSaveMap) {
        this.voucherPosSaveMap = voucherPosSaveMap;
    }
}
