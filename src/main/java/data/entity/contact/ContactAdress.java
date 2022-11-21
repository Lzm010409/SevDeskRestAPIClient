package data.entity.contact;

import com.google.gson.annotations.Expose;
import data.entity.other.Country;

public class ContactAdress {
    @Expose(serialize = false, deserialize = true)
    private long id;

    @Expose
    private Contact contact;
    @Expose
    private String street;
    @Expose
    private String zip;
    @Expose
    private String city;
    @Expose
    private Country country;
    @Expose
    private Category category;
    @Expose
    private String name;
    @Expose
    private String name2;
    @Expose
    private String name3;
    @Expose
    private String name4;

    public ContactAdress(Contact contact, Country country) {
        this.contact = contact;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }
}
