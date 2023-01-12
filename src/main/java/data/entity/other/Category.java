package data.entity.other;

import com.google.gson.annotations.Expose;

public class Category {
    @Expose
    private int id;
    @Expose
    private String objectName;

    public Category(int id) {
        this.id = id;
        this.objectName = "Category";
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
}
