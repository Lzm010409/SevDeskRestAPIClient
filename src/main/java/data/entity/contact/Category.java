package data.entity.contact;

public class Category {
    private int id;
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
