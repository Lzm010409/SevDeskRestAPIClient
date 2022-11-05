package Entity;

import java.util.Date;

public class Voucher {

    private String objectName = "Voucher";
    private boolean mapAll = true;
    private long id;
    private String taxType;
    private int taxRate;
    private String voucherType;
    private Date voucherDate;
    private Date deliveryDate;
    // private Timestamp deliverDateUntil;
    private int status;
    private String creditDebit;
    private boolean enshrined;
    //  private Timestamp paymentDeadline;
    private String supplierNameAtSave;
    private String descritption;
    // private Document document;
   /* private Timestamp payDate;
    private int reccurringIntervall;
    private Timestamp recurringStartDate;
    private Timestamp recurringNextVoucher;
    private Timestamp recurringLastVoucher;
    private Timestamp recurringEndDate;*/

    public Voucher(int status, String taxType, String creditDebit, String voucherType) {
        this.status = status;
        this.taxType = taxType;
        this.creditDebit = creditDebit;
        this.voucherType = voucherType;
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

        this.taxType = "\""+taxType+"\"";
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

  /*  public Timestamp getDeliverDateUntil() {
        return deliverDateUntil;
    }

    public void setDeliverDateUntil(Timestamp deliverDateUntil) {
        this.deliverDateUntil = deliverDateUntil;
    }*/

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

   /* public Timestamp getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Timestamp paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }*/

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

    /*    public Timestamp getPayDate() {
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
    */
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
