package data.auftrag.invoices;

import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;
import data.entity.invoice.InvoicePos;
import data.entity.other.TagName;
import data.entity.voucher.Voucher;
import data.entity.voucher.VoucherPosSave;

import java.util.List;

public class Auftrag {
    private Contact contact;
    private ContactAddress contactAddress;

    private Voucher voucher;
    private VoucherPosSave voucherPosSave;

    private String invoiceNumber;
    private String invoiceDate;

    private String invoiceHeadText;
    private List<InvoicePos> rechnungsPositions;
    private float value;

    private TagName loyer;

    public Auftrag(


    ) {


    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ContactAddress getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(ContactAddress contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getRechnungsNummer() {
        return invoiceNumber;
    }

    public void setRechnungsNummer(String rechnungsNummer) {
        this.invoiceNumber = rechnungsNummer;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<InvoicePos> getRechnungsPositions() {
        return rechnungsPositions;
    }

    public void setRechnungsPositions(List<InvoicePos> rechnungsPositions) {
        this.rechnungsPositions = rechnungsPositions;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public TagName getLoyer() {
        return loyer;
    }

    public void setLoyer(TagName loyer) {
        this.loyer = loyer;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public VoucherPosSave getVoucherPosSave() {
        return voucherPosSave;
    }

    public void setVoucherPosSave(VoucherPosSave voucherPosSave) {
        this.voucherPosSave = voucherPosSave;
    }

    public String getInvoiceHeadText() {
        return invoiceHeadText;
    }

    public void setInvoiceHeadText(String invoiceHeadText) {
        this.invoiceHeadText = invoiceHeadText;
    }
}
