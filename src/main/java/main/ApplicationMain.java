package main;

import com.sun.istack.logging.Logger;
import data.auftrag.invoices.Auftrag;
import data.entity.invoice.Invoice;
import data.entity.invoice.InvoiceText;
import data.entity.link.Contact;
import data.entity.link.InvoiceLink;
import data.entity.other.Country;
import data.entity.other.Tag;
import data.filepaths.PropertyReader;
import database.DbManager;
import dir.dir.DirLister;
import mail.login.Login;
import mail.send.MailSender;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import restfulapi.requests.Request;
import restfulapi.requests.builder.JsonBuilder;
import restfulapi.requests.url.Token;
import restfulapi.requests.url.URL;
import restfulapi.requests.url.UrlBuilder;
import text.extractor.InvoiceTextExtractor;
import text.parser.TextParser;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ApplicationMain {
    private boolean runApplication = true;
    private final Logger logger = Logger.getLogger(ApplicationMain.class);


    private String invoiceDir;
    private String voucherDir;

    public static void main(String[] args) throws MessagingException {
        ApplicationMain applicationMain = new ApplicationMain();
        DirLister invoiceDirLister = new DirLister();
        DirLister voucherDirLister = new DirLister();
        ThreadSleeper threadSleeper = new ThreadSleeper();
        List<String> invoiceFileList = new ArrayList<>();
        List<String> voucherFileList = new ArrayList<>();
        Auftrag auftrag = new Auftrag();
        List<String> applicationProperties = new PropertyReader().readProperties("ApplicationProperties.txt");
        TextParser textParser = new TextParser();
        InvoiceTextExtractor invoiceTextExtractor = new InvoiceTextExtractor();
        applicationMain.setInvoiceDir(applicationProperties.get(0));
        applicationMain.setVoucherDir(applicationProperties.get(1));
        Token TOKEN = new Token();
        TOKEN.setToken(applicationProperties.get(2));
        String mail = applicationProperties.get(6);
        String mailPassword = applicationProperties.get(7);
        Request request = new Request();
        JsonBuilder jsonBuilder = new JsonBuilder();
        UrlBuilder urlBuilder = new UrlBuilder();
        DbManager dbManager = new DbManager();
        DSLContext context = null;
        try {
            context = DSL.using(dbManager.connect(applicationProperties.get(3), applicationProperties.get(4), applicationProperties.get(5)), SQLDialect.POSTGRES);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String output = "";

        while (applicationMain.isRunApplication() == true) {


            invoiceFileList = invoiceDirLister.getInvoices(new File(applicationMain.getInvoiceDir()));
            String contactId = "";
            String invoiceId = "";
            for (int i = 0; i < invoiceFileList.size(); i++) {
                try {
                    if (dbManager.fileIsPresent(invoiceFileList.get(i), context) == false) {
                        auftrag = textParser.parseInvoice(invoiceTextExtractor.extractTextFromDoc(new File(invoiceFileList.get(i))));

                        try {
                            Country country = new Country(0);
                            output = request.httpPost(jsonBuilder.build(auftrag.getContact()), urlBuilder.buildUrl(URL.CREATENEWCONTACT), TOKEN.getToken());
                            contactId = textParser.parseId(output);
                            Contact contactLink = new Contact(Long.parseLong(contactId));
                            request.httpPost(jsonBuilder.build(auftrag.getContactAddress(), contactLink), urlBuilder.buildUrl(URL.CREATENEWCONTACTADRESS), TOKEN.getToken());
                            contactLink = null;
                            output = null;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Contact contact = new Contact(Long.parseLong(contactId));
                        Invoice invoice = new Invoice(contact, auftrag.getVoucher().getVoucherDate(), "100", InvoiceText.FOOTERTEXT.getText());
                        invoice.setAdress(auftrag.getContact().getGender() + "\n" + auftrag.getContact().getSurename() + " " + auftrag.getContact().getFamilyname()
                                + "\n" + auftrag.getContactAddress().getStreet() + "\n" + auftrag.getContactAddress().getZip() + " " + auftrag.getContactAddress().getCity());
                        invoice.setTimeToPay(14);
                        invoice.setSendDate(auftrag.getVoucher().getDeliveryDate());
                        invoice.setDeliveryDate(auftrag.getVoucher().getDeliveryDate());
                        invoice.setInvoiceNumber(auftrag.getVoucher().getDescritption());
                        invoice.setHeader("Rechnung");
                        invoice.setHeadText(auftrag.getInvoiceHeadText());
                        output = request.httpPost(jsonBuilder.build(invoice, auftrag.getRechnungsPositions()), urlBuilder.buildUrl(URL.CREATEINVOICE), TOKEN.getToken());

                        invoiceId = textParser.parseId(output);
                        output = null;
                        InvoiceLink invoiceLink = new InvoiceLink(Integer.parseInt(invoiceId));
                        if (dbManager.rechtsanwaltIsPresent(auftrag.getLoyer().getName(), context) == false) {
                            dbManager.createRechtsanwalt(auftrag.getLoyer(), context);
                        }
                        Tag tag = new Tag((auftrag.getLoyer()).getName());
                        tag.setObject(invoiceLink);
                        request.httpPost(jsonBuilder.build(tag), urlBuilder.buildUrl(URL.CREATETAG), TOKEN.getToken());
                        dbManager.createFilePath(invoiceFileList.get(i), context);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    applicationMain.rollbackChanges(contactId, TOKEN.getToken());
                }
            }
            voucherFileList = voucherDirLister.getInvoices(new File(applicationMain.getVoucherDir()));

           /* for (int j = 0; j < voucherFileList.size(); j++) {
                if (dbManager.fileIsPresent(voucherFileList.get(j), context) == false) {
                    output = request.httpPost(new File(voucherFileList.get(j)), urlBuilder.buildUrl(URL.UPLOADVOUCHERFILE), TOKEN.getToken());
                    String fileName = new TextParser().parseVoucherFileName(output);
                    Voucher voucher = new Voucher(50, "c", "VOU");
                    request.httpPost(jsonBuilder.build(voucher, fileName), urlBuilder.buildUrl(URL.CREATEVOUCHER), TOKEN.getToken());
                    dbManager.createFilePath(voucherFileList.get(j), context);
                }
            }*/


            Login login = new Login();
            MailSender mailSender = new MailSender();

            try {
                login.login(mail, mailPassword);
                mailSender.setMailSession(login.getMailSession());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < voucherFileList.size(); i++) {
                if (dbManager.fileIsPresent(voucherFileList.get(i), context) == false) {
                    try {
                        mailSender.sendMail(mail, mail, "autobox@sevdesk.email", "Upload", new File(voucherFileList.get(i)));
                        dbManager.createFilePath(voucherFileList.get(i), context);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }



            threadSleeper.threadSleep(3600000);


        }
    }

    private void rollbackChanges(String contactId, String token) {
        try {
            String buildUrl = new UrlBuilder().buildUrl(URL.DELETECONTACT) + contactId;
            new Request().httpDelete(buildUrl, token);
            logger.log(Level.WARNING, "Der obige Kontakt mit der ID: " + contactId + ", wurde wegen eines " +
                    "Fehlers nicht angelegt und daher wieder gelöscht!");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Bem Rollback des Kontaktes ist ein schwerwiegender Fehler aufgetreten!");
        }
    }

    public boolean isRunApplication() {
        return runApplication;
    }

    public void setRunApplication(boolean runApplication) {
        this.runApplication = runApplication;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getInvoiceDir() {
        return invoiceDir;
    }

    public void setInvoiceDir(String invoiceDir) {
        this.invoiceDir = invoiceDir;
    }

    public String getVoucherDir() {
        return voucherDir;
    }

    public void setVoucherDir(String voucherDir) {
        this.voucherDir = voucherDir;
    }
}
