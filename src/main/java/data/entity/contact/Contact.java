package data.entity.contact;

import java.util.HashMap;
import java.util.Map;

public class Contact {
    private String name;
    private int status;
    private String customerNumber;
    private String surename;
    private String familyname;
    private String titel;
    private Category category;
    private String description;
    private String academicTitle;
    private String gender;
    private String bankAccount;
    private String taxType = "default";

    Map<String, Object> contactMap = new HashMap<>();

    public Contact(Category category) {
        this.category = category;

    }

    public void postContactMapBuilder() {
        if (getName() != null) {
            contactMap.put("name", "\"" + getName() + "\"");
        } else {
            contactMap.put("name", null);
        }
        if (getStatus() != 0) {
            contactMap.put("status", getStatus());
        } else {
            contactMap.put("status", null);
        }
        if (getCustomerNumber() != null) {
            contactMap.put("customerNumber", "\"" + getCustomerNumber() + "\"");
        } else {
            contactMap.put("customerNumber", null);
        }
        if (getSurename() != null) {
            contactMap.put("surname", "\"" + getSurename() + "\"");
        } else {
            contactMap.put("surname", null);
        }
        if (getFamilyname() != null) {
            contactMap.put("familyName", "\"" + getFamilyname() + "\"");
        } else {
            contactMap.put("familyName", null);
        }
        if (getTitel() != null) {
            contactMap.put("titel", "\"" + getTitel() + "\"");
        } else {
            contactMap.put("titel", null);
        }
        contactMap.put("categoryId", this.category.getId());
        contactMap.put("categoryObjectName", "\"" + this.category.getObjectName() + "\"");

        if (getDescription() != null) {
            contactMap.put("description", "\"" + getDescription() + "\"");
        } else {
            contactMap.put("description", null);
        }
        if (getAcademicTitle() != null) {
            contactMap.put("academicTitle", "\"" + getAcademicTitle() + "\"");
        } else {
            contactMap.put("academicTitle", null);
        }
        if (getGender() != null) {
            contactMap.put("gender", "\"" + getGender() + "\"");
        } else {
            contactMap.put("gender", null);
        }
        if (getBankAccount() != null) {
            contactMap.put("bankAccount", "\"" + getBankAccount() + "\"");
        } else {
            contactMap.put("bankAccount", null);
        }
        contactMap.put("taxType", "\"" + this.taxType + "\"");
    }

    public Map<String, Object> getContactMap() {
        return contactMap;
    }

    public void setContactMap(Map<String, Object> contactMap) {
        this.contactMap = contactMap;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
}
