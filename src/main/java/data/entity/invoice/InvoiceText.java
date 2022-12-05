package data.entity.invoice;

public enum InvoiceText {

    FOOTERTEXT("Die Leistungen wurden zwischen der Auftragsannahme am 19.01.2022 und dem Rechnungsdatum gem. § 14 Abs. 4 UStG erbracht. Bitte überweisen Sie den Rechnungsbetrag unter Angabe der Rechnungsnummer innerhalb von 14 Tagen auf das unten angegebene Konto.");
    private String text;

    InvoiceText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
