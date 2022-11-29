package mail.send;


import org.jboss.logging.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MailSender {

    private final org.jboss.logging.Logger logger = Logger.getLogger(MailSender.class);


    private Session mailSession;
    private String senderAdress;
    private String senderName;
    private String receiverAdress;
    private String subject;
    private String message;

    public void sendMail(String senderAdress, String senderName, String receiverAdress, String subject, File file) throws MessagingException, IOException, IllegalStateException {
        if (mailSession == null) {
            throw new IllegalStateException("Erst einloggen!");
        }
        try {
            MimeMessage msg = new MimeMessage(mailSession);
            /*msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8-bit");
*/
            msg.setFrom(new InternetAddress(senderAdress, senderName));
            msg.setReplyTo(InternetAddress.parse(senderAdress, false));
            msg.setSubject(subject, "UTF-8");
            //msg.setText(message, "UTF-8");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("");
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(file);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            msg.setContent(multipart);
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverAdress, false));


            Transport.send(msg);
            logger.log(Logger.Level.INFO, "Mail versendet!");
        } catch (Exception e) {
            logger.log(Logger.Level.WARN, "Es ist ein Fehler beim Versand aufgetreten!" + e.getMessage());
        }
    }


    public Session getMailSession() {
        return mailSession;
    }

    public void setMailSession(Session mailSession) {
        this.mailSession = mailSession;
    }

    public String getSenderAdress() {
        return senderAdress;
    }

    public void setSenderAdress(String senderAdress) {
        this.senderAdress = senderAdress;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverAdress() {
        return receiverAdress;
    }

    public void setReceiverAdress(String receiverAdress) {
        this.receiverAdress = receiverAdress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
