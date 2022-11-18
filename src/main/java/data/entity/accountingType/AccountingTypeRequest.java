package data.entity.accountingType;

import com.google.gson.annotations.Expose;


public class AccountingTypeRequest {
    @Expose(serialize = false, deserialize = true)
    private int id;
    @Expose
    private String objectName;

    public AccountingTypeRequest(int id) {
        this.id = id;
        this.objectName = "AccountingType";
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
