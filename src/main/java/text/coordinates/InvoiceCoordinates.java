package text.coordinates;

import java.util.List;

public enum InvoiceCoordinates {
    ANREDE(60, 700, 250, 13),
    NAME(60, 687, 250, 13),

    STREET(60, 674, 250, 13),

    ZIPCITY(60, 650, 250, 15),

    GUTACHTENNUMMER(380, 561, 170, 30),
    BETRAG(440, 75, 130, 250),
    RECHNUNGSDATUM(415, 600, 150, 15),

    ANDERE(50, 291, 510, 270),

    RECHNUNGSPOSITIONEN(51, 40, 500, 300),

    ;


    float x, y, width, height;
    List<InvoiceCoordinates> list;


    InvoiceCoordinates(float a, float b, float c, float d) {
        this.x = a;
        this.y = b;
        this.width = c;
        this.height = d;


    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
