package data.entity.accountingType;

import data.entity.other.AbstractEntity;


public class AccountingTypeRequest extends AbstractEntity {
    private String objectName;
    public AccountingTypeRequest(int id){
        this.id=id;
        this.objectName="AccountingType";
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
