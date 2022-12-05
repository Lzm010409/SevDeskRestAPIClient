package data.entity.other;

import com.google.gson.annotations.Expose;

public class AbstractEntity {

    @Expose
    protected long id;

    public AbstractEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
