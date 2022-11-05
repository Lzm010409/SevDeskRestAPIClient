package Entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Voucher {

    private String objectName = "Voucher";
    private boolean mapAll = true;
    private long id;

    private Supplier supplier = null;
    private String taxType = null;
    private int taxRate;
    private String voucherType = null;
    private Date voucherDate = null;
    private Date deliveryDate = null;
    private Timestamp deliverDateUntil = null;
    private int status;
    private String creditDebit = null;
    private boolean enshrined = true;
    private Timestamp paymentDeadline = null;
    private String supplierNameAtSave = null;

    private String currency = null;


    private String descritption = null;
    private Document document = null;
    private Timestamp payDate = null;
    private int reccurringIntervall;
    private Timestamp recurringStartDate = null;
    private Timestamp recurringNextVoucher = null;
    private Timestamp recurringLastVoucher = null;
    private Timestamp recurringEndDate = null;

    Map<String, Object> voucherMap = new HashMap<>();
    Map<String, Object> voucherPosSaveMap = new HashMap<>();


    public Voucher(int status, String taxType, String creditDebit, String voucherType) {
        this.status = status;
        this.taxType = taxType;
        this.creditDebit = creditDebit;
        this.voucherType = voucherType;

    }

    public void postVoucherMapBuilder() {
        voucherMap.put("StartVar", "\"voucher\"");
        if (getObjectName() != null) {
            voucherMap.put("objectName", "\"" + getObjectName() + "\"");
        } else {
            voucherMap.put("objectName", null);
        }
        if (isMapAll() == true) {
            voucherMap.put("mapAll", isMapAll());
        } else {
            voucherMap.put("mapAll", null);
        }
        if (getVoucherDate() != null) {
            voucherMap.put("voucherDate", "\"" + getVoucherDate() + "\"");
        } else {
            voucherMap.put("voucherDate", null);
        }
        if (getSupplier() != null) {
            voucherMap.put("supplier", "\"" + getSupplier() + "\"");
        } else {
            voucherMap.put("supplier", null);
        }
        if (getSupplierNameAtSave() != null) {
            voucherMap.put("supplierName", "\"" + getSupplierNameAtSave() + "\"");
        } else {
            voucherMap.put("supplierName", null);
        }
        if (getDescritption() != null) {
            voucherMap.put("description", "\"" + getDescritption() + "\"");
        } else {
            voucherMap.put("description", null);
        }
        if (getPayDate() != null) {
            voucherMap.put("payDate", "\"" + getPayDate() + "\"");
        } else {
            voucherMap.put("payDate", null);
        }
        if (getStatus() == 50 || getStatus() == 100 || getStatus() == 1000) {
            voucherMap.put("status", getStatus());
        } else {
            voucherMap.put("status", null);
        }
        if (getTaxType() != null) {
            voucherMap.put("taxType", "\"" + getTaxType() + "\"");
        } else {
            voucherMap.put("taxType", null);
        }
        if (getCreditDebit() != null) {
            voucherMap.put("creditDebit", "\"" + getCreditDebit() + "\"");
        } else {
            voucherMap.put("creditDebit", null);
        }
        if (getVoucherType() != null) {
            voucherMap.put("voucherType", "\"" + getVoucherType() + "\"");
        } else {
            voucherMap.put("voucherType", null);
        }
        if (getCurrency() != null) {
            voucherMap.put("currency", "\"" + getCurrency() + "\"");
        } else {
            voucherMap.put("currency", null);
        }

        voucherMap.put("propertyForeignCurrencyDeadline", null);


        voucherMap.put("propertyExchangeRate", null);


        voucherMap.put("taxSet", null);

        if (getPaymentDeadline() != null) {
            voucherMap.put("paymentDeadLine", "\"" + getPaymentDeadline() + "\"");
        } else {
            voucherMap.put("paymentDeadLine", null);
        }
        if (getDeliveryDate() != null) {
            voucherMap.put("deliveryDate", "\"" + getDeliveryDate() + "\"");
        } else {
            voucherMap.put("deliveryDate", null);
        }
        if (getDocument() != null) {
            voucherMap.put("document", "\"" + getDocument() + "\"");
        } else {
            voucherMap.put("document", null);
        }

        voucherMap.put("costCentre", null);

    }

    public void postVoucherPosSaveMapBuilder() {

    }


    public Map<String, Object> getVoucherMap() {
        return voucherMap;
    }

    public void setVoucherMap(Map<String, Object> voucherMap) {
        this.voucherMap = voucherMap;
    }

    private Supplier getSupplier() {
        return this.supplier;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {

        this.taxType = "\"" + taxType + "\"";
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

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Timestamp getDeliverDateUntil() {
        return deliverDateUntil;
    }

    public void setDeliverDateUntil(Timestamp deliverDateUntil) {
        this.deliverDateUntil = deliverDateUntil;
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

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public int getReccurringIntervall() {
        return reccurringIntervall;
    }

    public void setReccurringIntervall(int reccurringIntervall) {
        this.reccurringIntervall = reccurringIntervall;
    }

    public Timestamp getRecurringStartDate() {
        return recurringStartDate;
    }

    public void setRecurringStartDate(Timestamp recurringStartDate) {
        this.recurringStartDate = recurringStartDate;
    }

    public Timestamp getRecurringNextVoucher() {
        return recurringNextVoucher;
    }

    public void setRecurringNextVoucher(Timestamp recurringNextVoucher) {
        this.recurringNextVoucher = recurringNextVoucher;
    }

    public Timestamp getRecurringLastVoucher() {
        return recurringLastVoucher;
    }

    public void setRecurringLastVoucher(Timestamp recurringLastVoucher) {
        this.recurringLastVoucher = recurringLastVoucher;
    }

    public Timestamp getRecurringEndDate() {
        return recurringEndDate;
    }

    public void setRecurringEndDate(Timestamp recurringEndDate) {
        this.recurringEndDate = recurringEndDate;
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


}
