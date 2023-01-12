package data.entity.invoice;

import com.google.gson.annotations.Expose;
import data.entity.other.Part;
import data.entity.other.Unity;

public class InvoicePos {
    @Expose(serialize = false, deserialize = true)
    private int id;

    @Expose(serialize = false, deserialize = true)
    private Invoice invoice;

    @Expose
    private String objectName = "InvoicePos";
    @Expose
    private boolean mapAll = true;
    @Expose
    private Part part;
    @Expose
    private float quantity;
    @Expose
    private double price;
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
    private double priceGross;
    @Expose
    private double priceTax;

    public InvoicePos() {

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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public double getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(double priceGross) {
        this.priceGross = priceGross;
    }

    public double getPriceTax() {
        return priceTax;
    }

    public void setPriceTax(double priceTax) {
        this.priceTax = priceTax;
    }
}