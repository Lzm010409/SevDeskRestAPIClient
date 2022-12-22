package database;

import data.auftrag.invoices.Rechtsanwalt;
import data.entity.contact.Contact;
import data.entity.contact.ContactAddress;
import database.entities.tables.Kunden;
import database.entities.tables.Rechtsanwälte;
import database.entities.tables.records.FilesRecord;
import database.entities.tables.records.KundenRecord;
import database.entities.tables.records.RechtsanwälteRecord;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbManager {
    Logger logger = Logger.getLogger(DbManager.class);


    public DbManager() {

    }

    public Connection connect(String url) throws ClassNotFoundException {
        Connection connection;
        Class.forName("org.postgresql.Driver");
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                logger.info("Verbindung zu: " + url + " hergestellt.");
            } else {
                logger.log(Logger.Level.WARN, "Fehler bei Verbindung!");
            }
        } catch (SQLException e) {
            logger.log(Logger.Level.ERROR, "Es ist ein Fehler beim der Verbindung mit der Datenbank aufgetretetn!" + e.getMessage());
            throw new RuntimeException(e);
        }

        return connection;
    }


    public Connection connect(String url, String user, String password) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                logger.info("Verbindung zu: " + url + " hergestellt.");
            } else {
                logger.log(Logger.Level.WARN, "Fehler bei Verbindung!");
            }
        } catch (SQLException e) {
            logger.log(Logger.Level.ERROR, "Es ist ein Fehler beim der Verbindung mit der Datenbank aufgetretetn!" + e.getMessage());
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void transmitContacts(List<Contact> list, List<ContactAddress> contactAddressList, DSLContext context) {

        KundenRecord kundenRecord;

        for (int i = 0; i < list.size(); i++) {
            kundenRecord = context.fetchOne(Kunden.KUNDEN, Kunden.KUNDEN.ID.eq(Long.valueOf(list.get(i).getId())));
            if (kundenRecord == null) {
                ContactAddress contactAddress = findContactAdress(contactAddressList, list.get(i).getId());
                if (contactAddress != null) {
                    kundenRecord = context.newRecord(Kunden.KUNDEN);
                    kundenRecord.setId(Long.valueOf(list.get(i).getId()));
                    kundenRecord.setGender(list.get(i).getGender());
                    kundenRecord.setFirstname(list.get(i).getSurename());
                    kundenRecord.setFamilyname(list.get(i).getFamilyname());
                    kundenRecord.setStreet(contactAddress.getStreet());
                    kundenRecord.setZip(contactAddress.getZip());
                    kundenRecord.setCity(contactAddress.getCity());
                    kundenRecord.store();
                } else {
                    kundenRecord = context.newRecord(Kunden.KUNDEN);
                    kundenRecord.setId(Long.valueOf(list.get(i).getId()));
                    kundenRecord.setGender(list.get(i).getGender());
                    kundenRecord.setFirstname(list.get(i).getSurename());
                    kundenRecord.setFamilyname(list.get(i).getFamilyname());
                    kundenRecord.store();
                }
            } else {
                logger.log(Logger.Level.WARN, "Kunde mit ID: " + list.get(i).getId() + " bereits vorhanden!");
            }
        }

    }

    public void createContact(Contact contact, ContactAddress contactAddress, DSLContext context) {
        KundenRecord kundenRecord;
        kundenRecord = context.newRecord(Kunden.KUNDEN);
        kundenRecord.setId(Long.valueOf(contact.getId()));
        kundenRecord.setGender(contact.getGender());
        kundenRecord.setFirstname(contact.getSurename());
        kundenRecord.setFamilyname(contact.getFamilyname());
        kundenRecord.setStreet(contactAddress.getStreet());
        kundenRecord.setZip(contactAddress.getZip());
        kundenRecord.setCity(contactAddress.getCity());
        kundenRecord.store();
    }

    public void createFilePath(String path, DSLContext context) {
        FilesRecord filesRecord;
        filesRecord = context.newRecord(database.entities.tables.Files.FILES);
        filesRecord.setPath(path);
        filesRecord.store();
    }

    public void createRechtsanwalt(Rechtsanwalt rechtsanwalt, DSLContext context) {
        RechtsanwälteRecord rechtsanwälteRecord;
        rechtsanwälteRecord = context.newRecord(Rechtsanwälte.RECHTSANWÄLTE);
        rechtsanwälteRecord.setName(rechtsanwalt.getName());
        rechtsanwälteRecord.setStreet(rechtsanwalt.getStreet());
        rechtsanwälteRecord.setZip(rechtsanwalt.getZip());
        rechtsanwälteRecord.setCity(rechtsanwalt.getCity());
        rechtsanwälteRecord.store();
    }

    public boolean contactIsPresent(long id, DSLContext context) {
        KundenRecord kundenRecord = context.fetchOne(Kunden.KUNDEN, Kunden.KUNDEN.ID.eq(id));
        return (kundenRecord == null ? true : false);
    }

    public boolean fileIsPresent(String path, DSLContext context) {
        FilesRecord filesRecord = context.fetchOne(database.entities.tables.Files.FILES, database.entities.tables.Files.FILES.PATH.eq(path));
        return (filesRecord == null ? false : true);
    }

    public boolean rechtsanwaltIsPresent(String tag, DSLContext context) {
        RechtsanwälteRecord rechtsanwälteRecord = context.fetchOne(Rechtsanwälte.RECHTSANWÄLTE, Rechtsanwälte.RECHTSANWÄLTE.NAME.eq(tag));
        return (rechtsanwälteRecord == null ? false : true);
    }


    public ContactAddress findContactAdress(List<ContactAddress> list, long id) {

        ContactAddress contactAddress = null;
        for (int i = 0; i < list.size(); i++) {

            if ((list.get(i).getContact()).getId() == id) {
                contactAddress = list.get(i);
                break;

            }

        }
        return contactAddress;
    }

    public static void main(String[] args) {




      /*  dbManager.transmitContacts(new ResponseParser().parseContacts(new Request().httpGet(new UrlBuilder().buildUrl(URL.RETRIEVEALLCONTACTS), token.getToken())),
                new ResponseParser().parseContactAdresses(new Request().httpGet(new UrlBuilder().buildUrl(URL.RETRIEVEALLCONTACTADRESSES), token.getToken())), context);*/
    }
}
