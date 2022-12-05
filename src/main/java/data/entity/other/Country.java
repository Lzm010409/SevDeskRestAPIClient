package data.entity.other;

import com.google.gson.annotations.Expose;

public class Country {
    @Expose
    private long id;
    @Expose
    private String objectName = "StaticCountry";

    public Country(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
