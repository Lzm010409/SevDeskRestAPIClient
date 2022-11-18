package data.entity.other;

import com.google.gson.annotations.Expose;

public class AbstractEntity {

    @Expose
    protected int id;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
