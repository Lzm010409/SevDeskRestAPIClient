package database;

import data.entity.contact.Contact;
import database.entities.tables.Kunden;
import database.entities.tables.records.KundenRecord;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbManager {
    Logger logger = Logger.getLogger(DbManager.class);


    public DbManager() {

    }

    public Connection connect(String url) {
        Connection connection;
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


    public Connection connect(String url, String user, String password) {
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

    public void transmitContacts(List<Contact> list, DSLContext context) {

        KundenRecord kundenRecord;

        for (int i = 0; i < list.size(); i++) {
            kundenRecord = context.fetchOne(Kunden.KUNDEN, Kunden.KUNDEN.ID.eq(Long.valueOf(list.get(i).getId())));
            if (kundenRecord == null) {
                kundenRecord = context.newRecord(Kunden.KUNDEN);
                kundenRecord.setId(Long.valueOf(list.get(i).getId()));
                kundenRecord.setGender(list.get(i).getGender());
                kundenRecord.setFirstname(list.get(i).getSurename());
                kundenRecord.setFamilyname(list.get(i).getFamilyname());
                kundenRecord.store();
            } else {
                logger.log(Logger.Level.WARN, "Kunde mit ID: " + list.get(i).getId() + " bereits vorhanden!");
            }
        }

    }

    public static void main(String[] args) {
        DbManager dbManager = new DbManager();
        DSLContext context = DSL.using(dbManager.connect("jdbc:postgresql://127.0.0.1:5432/", "", ""), SQLDialect.POSTGRES);
      /*  try {
            GenerationTool.generate(Files.readString(Path.of("/Users/lukegollenstede/IdeaProjects/SevDeskRestAPIClient/jooq-config.xml")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
}
