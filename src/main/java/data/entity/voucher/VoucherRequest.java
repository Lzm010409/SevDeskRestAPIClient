package data.entity.voucher;

import data.entity.other.Supplier;

public class VoucherRequest {

    private String objectName = "Voucher";
    private boolean mapAll = true;

    private String voucherDate = null;

    private Supplier supplier;

    private String supplierName = null;
    private String descritption = null;
    private String payDate = null;
    private int status;
    private String taxType = "default";
    private String creditDebit = null;
    private String voucherType = null;
    private String currency = "EUR";

    private String propertyForeignCurrencyDeadline = null;
    private String taxSet = null;
    private String paymentDeadline = null;
    private String deliveryDate = null;
    private String deliverDateUntil = null;
    private String document = null;
    private String costCentre = null;


    public VoucherRequest(int status, String creditDebit, String voucherType, Supplier supplier) {
        this.supplier = supplier;
        this.status = status;
        this.creditDebit = creditDebit;
        this.voucherType = voucherType;

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

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }


    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getCreditDebit() {
        return creditDebit;
    }

    public void setCreditDebit(String creditDebit) {
        this.creditDebit = creditDebit;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPropertyForeignCurrencyDeadline() {
        return propertyForeignCurrencyDeadline;
    }

    public void setPropertyForeignCurrencyDeadline(String propertyForeignCurrencyDeadline) {
        this.propertyForeignCurrencyDeadline = propertyForeignCurrencyDeadline;
    }

    public String getTaxSet() {
        return taxSet;
    }

    public void setTaxSet(String taxSet) {
        this.taxSet = taxSet;
    }

    public String getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(String paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliverDateUntil() {
        return deliverDateUntil;
    }

    public void setDeliverDateUntil(String deliverDateUntil) {
        this.deliverDateUntil = deliverDateUntil;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCostCentre() {
        return costCentre;
    }

    public void setCostCentre(String costCentre) {
        this.costCentre = costCentre;
    }
}
