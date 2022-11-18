package data.entity.other;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Supplier {

    @Expose
    private int id;
    @Expose
    private String objectName = "Contact";

    public Supplier(int id) {
        this.id = id;
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

    public static void main (String[]args){
        Gson g= new Gson();
        Supplier supplier= new Supplier(1);
        System.out.println(g.toJson(supplier));
    }
}
