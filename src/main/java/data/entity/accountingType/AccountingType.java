package data.entity.accountingType;

import data.entity.other.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class AccountingType extends AbstractEntity {
    @NotBlank
    private String name;
    @NotBlank
    private String objectName = "AccountingType";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
