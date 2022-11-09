package data.entity.contact;

public class ContactRequest {
    private String name = null;
    private int status;
    private String customerNumber;
    private String surename = null;
    private String familyname = null;
    private String titel = null;
    private Category category;
    private String description = null;
    private String academicTitle = null;
    private String gender = null;
    private String name2 = null;
    private String vatNumber = null;

    private String bankAccount = null;
    private int defaultCashbackTime;
    private int defaultTimeToPay;
    private String taxNumber = null;
    private String taxOffice = null;
    private boolean exemptVat;
    private String taxType = "default";
    private float defaultDiscountAmount;
    private boolean defaultDiscountPercentage;
    private String buyerReference;
    private boolean governmentAgency;

    public ContactRequest(Category category) {
        this.category = category;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getDefaultCashbackTime() {
        return defaultCashbackTime;
    }

    public void setDefaultCashbackTime(int defaultCashbackTime) {
        this.defaultCashbackTime = defaultCashbackTime;
    }

    public int getDefaultTimeToPay() {
        return defaultTimeToPay;
    }

    public void setDefaultTimeToPay(int defaultTimeToPay) {
        this.defaultTimeToPay = defaultTimeToPay;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public boolean isExemptVat() {
        return exemptVat;
    }

    public void setExemptVat(boolean exemptVat) {
        this.exemptVat = exemptVat;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public float getDefaultDiscountAmount() {
        return defaultDiscountAmount;
    }

    public void setDefaultDiscountAmount(float defaultDiscountAmount) {
        this.defaultDiscountAmount = defaultDiscountAmount;
    }

    public boolean isDefaultDiscountPercentage() {
        return defaultDiscountPercentage;
    }

    public void setDefaultDiscountPercentage(boolean defaultDiscountPercentage) {
        this.defaultDiscountPercentage = defaultDiscountPercentage;
    }

    public String getBuyerReference() {
        return buyerReference;
    }

    public void setBuyerReference(String buyerReference) {
        this.buyerReference = buyerReference;
    }

    public boolean isGovernmentAgency() {
        return governmentAgency;
    }

    public void setGovernmentAgency(boolean governmentAgency) {
        this.governmentAgency = governmentAgency;
    }
}