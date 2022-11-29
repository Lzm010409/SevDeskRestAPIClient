package data.entity.voucher;

import com.google.gson.annotations.Expose;
import data.entity.other.AbstractEntity;

public class VoucherLink extends AbstractEntity {

    @Expose
    private String objectName ="Voucher";


    public VoucherLink() {

    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
