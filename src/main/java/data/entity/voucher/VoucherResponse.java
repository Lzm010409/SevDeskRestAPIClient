package data.entity.voucher;

import data.entity.other.Supplier;

import java.sql.Timestamp;

public class VoucherResponse {
    private int id;
    private String taxType=null;
    private int taxRate;
    private String voucherType;
    private String voucherDate;
    private String deliveryDate;
    private String deliveryDateUntil;
    private int status;
    private String creditDebit;
    private boolean enshrined;
    private Timestamp paymentDeadline;
    private String supplierNameAtSave;
    private String description;
    private Supplier supplier;
    private Timestamp payDate;
    private int reccuringIntervall;
    private Timestamp reccuringStartDate;
    private Timestamp recurringNextVoucher;
    private Timestamp reccuringLastVoucher;
    private Timestamp reccuringEndDate;


    public VoucherResponse(){

    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDateUntil() {
        return deliveryDateUntil;
    }

    public void setDeliveryDateUntil(String deliveryDateUntil) {
        this.deliveryDateUntil = deliveryDateUntil;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreditDebit() {
        return creditDebit;
    }

    public void setCreditDebit(String creditDebit) {
        this.creditDebit = creditDebit;
    }

    public boolean isEnshrined() {
        return enshrined;
    }

    public void setEnshrined(boolean enshrined) {
        this.enshrined = enshrined;
    }

    public Timestamp getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Timestamp paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public String getSupplierNameAtSave() {
        return supplierNameAtSave;
    }

    public void setSupplierNameAtSave(String supplierNameAtSave) {
        this.supplierNameAtSave = supplierNameAtSave;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public int getReccuringIntervall() {
        return reccuringIntervall;
    }

    public void setReccuringIntervall(int reccuringIntervall) {
        this.reccuringIntervall = reccuringIntervall;
    }

    public Timestamp getReccuringStartDate() {
        return reccuringStartDate;
    }

    public void setReccuringStartDate(Timestamp reccuringStartDate) {
        this.reccuringStartDate = reccuringStartDate;
    }

    public Timestamp getRecurringNextVoucher() {
        return recurringNextVoucher;
    }

    public void setRecurringNextVoucher(Timestamp recurringNextVoucher) {
        this.recurringNextVoucher = recurringNextVoucher;
    }

    public Timestamp getReccuringLastVoucher() {
        return reccuringLastVoucher;
    }

    public void setReccuringLastVoucher(Timestamp reccuringLastVoucher) {
        this.reccuringLastVoucher = reccuringLastVoucher;
    }

    public Timestamp getReccuringEndDate() {
        return reccuringEndDate;
    }

    public void setReccuringEndDate(Timestamp reccuringEndDate) {
        this.reccuringEndDate = reccuringEndDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
