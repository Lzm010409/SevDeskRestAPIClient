package data.entity.other;

import com.google.gson.annotations.Expose;

public class Part {
    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    private String objectName ="Part";
    @Expose
    private String name;
    @Expose
    private String partNumber;
    @Expose
    private String text;
    @Expose
    private Category category;
    @Expose
    private int stock;
    @Expose
    private int stockEnabled;
    @Expose
    private Unity unity;
    @Expose
    private double price;
    @Expose
    private double priceNet;
    @Expose
    private double priceGross;
    @Expose
    private double pricePurchase;
    @Expose
    private int taxRate;
    @Expose
    private int status;
    @Expose
    private String internalComment;

    public Part() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockEnabled() {
        return stockEnabled;
    }

    public void setStockEnabled(int stockEnabled) {
        this.stockEnabled = stockEnabled;
    }

    public Unity getUnity() {
        return unity;
    }

    public void setUnity(Unity unity) {
        this.unity = unity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(double priceNet) {
        this.priceNet = priceNet;
    }

    public double getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(double priceGross) {
        this.priceGross = priceGross;
    }

    public double getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(double pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInternalComment() {
        return internalComment;
    }

    public void setInternalComment(String internalComment) {
        this.internalComment = internalComment;
    }
}
