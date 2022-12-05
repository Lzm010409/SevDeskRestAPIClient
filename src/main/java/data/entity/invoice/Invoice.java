package data.entity.invoice;

import com.google.gson.annotations.Expose;
import data.entity.contact.ContactPerson;
import data.entity.link.Contact;
import data.entity.other.AdressCountry;

public class Invoice {

    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    private String objectName = "Invoice";
    @Expose
    private boolean mapAll = true;
    @Expose
    private String invoiceNumber;
    //private data.entity.contact.Contact contact;

    @Expose
    private Contact contact;
    @Expose
    private ContactPerson contactPerson = new ContactPerson(851050);
    @Expose
    private String invoiceDate;
    @Expose
    private String header;
    @Expose
    private String headText;
    @Expose
    private String footText;
    @Expose
    private int timeToPay;
    @Expose
    private int discount;
    @Expose
    private String address;
    @Expose
    private AdressCountry adressCountry = new AdressCountry(1);
    @Expose
    private String payDate;
    @Expose
    private String deliveryDate;

    private boolean deliveryDateUntil;
    @Expose
    private String status;
    @Expose
    private String deliverDateUntil;

    private boolean smallSettlement;
    @Expose
    private int taxRate = 19;
    @Expose
    private String taxText;
    @Expose
    private String taxType = "default";
    @Expose
    private Object taxSet;
    @Expose
    private Object paymentMethod;
    @Expose
    private String sendDate;
    @Expose
    private String invoiceType = "RE";
    @Expose
    private String currency = "EUR";
    @Expose
    private String showNet;
    @Expose
    private String sendType;
    @Expose
    private Object origin;
    @Expose
    private String customeerInternalNote;


    public Invoice(Contact contact, String invoiceDate, String status, String taxText) {
        this.contact = contact;
        this.invoiceDate = invoiceDate;
        this.status = status;
        this.taxText = taxText;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }


    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeadText() {
        return headText;
    }

    public void setHeadText(String headText) {
        this.headText = headText;
    }

    public String getFootText() {
        return footText;
    }

    public void setFootText(String footText) {
        this.footText = footText;
    }

    public int getTimeToPay() {
        return timeToPay;
    }

    public void setTimeToPay(int timeToPay) {
        this.timeToPay = timeToPay;
    }

    public int getDiscount() {
        return discount;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isDeliveryDateUntil() {
        return deliveryDateUntil;
    }

    public void setDeliveryDateUntil(boolean deliveryDateUntil) {
        this.deliveryDateUntil = deliveryDateUntil;
    }

    public String getDeliverDateUntil() {
        return deliverDateUntil;
    }

    public void setDeliverDateUntil(String deliverDateUntil) {
        this.deliverDateUntil = deliverDateUntil;
    }

    public boolean isSmallSettlement() {
        return smallSettlement;
    }

    public void setSmallSettlement(boolean smallSettlement) {
        this.smallSettlement = smallSettlement;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Object getTaxSet() {
        return taxSet;
    }

    public void setTaxSet(Object taxSet) {
        this.taxSet = taxSet;
    }

    public Object getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Object paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getShowNet() {
        return showNet;
    }

    public void setShowNet(String showNet) {
        this.showNet = showNet;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public Object getOrigin() {
        return origin;
    }

    public void setOrigin(Object origin) {
        this.origin = origin;
    }

    public String getCustomeerInternalNote() {
        return customeerInternalNote;
    }

    public void setCustomeerInternalNote(String customeerInternalNote) {
        this.customeerInternalNote = customeerInternalNote;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public AdressCountry getAdressCountry() {
        return adressCountry;
    }

    public void setAdressCountry(AdressCountry adressCountry) {
        this.adressCountry = adressCountry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxText() {
        return taxText;
    }

    public void setTaxText(String taxText) {
        this.taxText = taxText;
    }

    public Contact getContactLink() {
        return contact;
    }

    public void setContactLink(Contact contactLink) {
        this.contact = contactLink;
    }
}



