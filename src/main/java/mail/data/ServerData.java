package mail.data;

public enum ServerData {

    SMTPHOST("smtp-mail.outlook.com"),
    SMTPPORT("587");
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
