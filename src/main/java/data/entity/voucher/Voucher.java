package data.entity.voucher;

import com.google.gson.annotations.Expose;
import data.entity.other.Supplier;

import java.sql.Timestamp;

public class Voucher {

    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    private String objectName = "Voucher";
    @Expose
    private boolean mapAll = true;
    @Expose
    private String voucherDate;
    @Expose
    private Supplier supplier;
    @Expose
    private String supplierName;
    @Expose
    private String description;
    @Expose
    private String payDate;
    @Expose
    private int status;
    @Expose
    private String taxType = "default";
    @Expose
    private String creditDebit = null;
    @Expose
    private String voucherType = null;
    @Expose
    private String currency = "EUR";
    @Expose
    private String propertyForeignCurrencyDeadline;
    @Expose
    private String taxSet;
    @Expose
    private String paymentDeadline;
    @Expose
    private String deliveryDate;
    @Expose
    private String deliverDateUntil;
    @Expose
    private String document;
    @Expose
    private String costCentre;
    @Expose(serialize = false, deserialize = true)
    private boolean enshrined;
    @Expose(serialize = false, deserialize = true)
    private int taxRate;
    @Expose
    private String supplierNameAtSave;
    @Expose(serialize = false, deserialize = true)
    private int recurringIntervall;
    @Expose(serialize = false, deserialize = true)
    private Timestamp reccuringStartDate;
    @Expose(serialize = false, deserialize = true)
    private Timestamp recurringNextVoucher;
    @Expose(serialize = false, deserialize = true)
    private Timestamp reccuringLastVoucher;
    @Expose(serialize = false, deserialize = true)
    private Timestamp reccuringEndDate;


    public Voucher(int status, String creditDebit, String voucherType, Supplier supplier) {
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
        return description;
    }

    public void setDescritption(String descritption) {
        this.description = descritption;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean isEnshrined() {
        return enshrined;
    }

    public void setEnshrined(boolean enshrined) {
        this.enshrined = enshrined;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public String getSupplierNameAtSave() {
        return supplierNameAtSave;
    }

    public void setSupplierNameAtSave(String supplierNameAtSave) {
        this.supplierNameAtSave = supplierNameAtSave;
    }

    public int getRecurringIntervall() {
        return recurringIntervall;
    }

    public void setRecurringIntervall(int recurringIntervall) {
        this.recurringIntervall = recurringIntervall;
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
}
