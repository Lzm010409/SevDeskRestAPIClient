package data.entity.contact;

public class Category {
    private int id;
    private String objectName;

    public Category(int id, String objectName) {
        this.id = id;
        this.objectName = objectName;
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
