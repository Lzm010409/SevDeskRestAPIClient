package data.entity.invoice;

import com.google.gson.annotations.Expose;
import data.entity.other.Unity;

public class InvoicePos {
    @Expose
    private int id;

    @Expose(serialize = false, deserialize = true)
    private Invoice invoice;

    @Expose
    private String objectName = "invoicePos";
    @Expose
    private boolean mapAll = true;
    @Expose
    private Object part;
    @Expose
    private int quantity;
    @Expose
    private float price;
    @Expose
    private String name;
    @Expose
    private Unity unity;

    @Expose
    private int positionNumber;
    @Expose
    private String text;
    @Expose
    private float discount;
    @Expose
    private float taxRate;
    @Expose
    private float priceGross;
    @Expose
    private float priceTax;

    public InvoicePos(int quantity, Unity unity, float taxRate) {
        this.quantity = quantity;
        this.unity = unity;
        this.taxRate = taxRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    public Object getPart() {
        return part;
    }

    public void setPart(Object part) {
        this.part = part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unity getUnity() {
        return unity;
    }

    public void setUnity(Unity unity) {
        this.unity = unity;
    }

    public int getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(int positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public float getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(float priceGross) {
        this.priceGross = priceGross;
    }

    public float getPriceTax() {
        return priceTax;
    }

    public void setPriceTax(float priceTax) {
        this.priceTax = priceTax;
    }
}