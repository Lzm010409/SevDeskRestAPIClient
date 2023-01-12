package mail.data;

public enum ServerData {

    SMTPHOST("smtp.strato.de"),
    SMTPPORT("465");
    private String data;

    ServerData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
