package data.entity.link;

import com.google.gson.annotations.Expose;
import data.entity.other.AbstractEntity;

public class InvoiceLink extends AbstractEntity {
    @Expose
    private String objectName = "Invoice";

    public InvoiceLink(long id) {
        super(id);
    }


    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}

