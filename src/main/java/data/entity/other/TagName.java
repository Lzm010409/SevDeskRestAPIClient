package data.entity.other;

public class TagName {


    private String name;
    private String kürzel;

    public TagName(String name, String kürzel) {
        this.kürzel = kürzel;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKürzel() {
        return kürzel;
    }

    public void setKürzel(String kürzel) {
        this.kürzel = kürzel;
    }
}

