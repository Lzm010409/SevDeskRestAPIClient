package data.entity.link;

import com.google.gson.annotations.Expose;

public class Contact {

    @Expose
    private long id;
    @Expose
    private String objectName = "Contact";


    public Contact(long id) {
        this.id = id;

    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
