package data.entity.link;

import com.google.gson.annotations.Expose;

public class Part {
    @Expose
    private long id;
    @Expose
    private String objectName = "Part";


    public Part(long id) {
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

