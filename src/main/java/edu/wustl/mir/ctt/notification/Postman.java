package edu.wustl.mir.ctt.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author drm
 */
public class Postman {
    private String host;
    private List<Address> recipients;
    private Address from;
    private String subject;
    private String content;
    private Properties properties;
    
    private static final Logger logger = Logger.getLogger( Postman.class);
    
    public Postman(String host) {
        recipients = new ArrayList<Address>();
        this.host = host;

        // Get system properties
        properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        
        try {
            from = new InternetAddress("noreply@noreply.com");
        } catch (AddressException ex) {
            logger.error( "Bad from address.", ex);
        }
    }

    public List<Address> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Address> recipients) {
        this.recipients = recipients;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }
    
    public void addRecipient( Address a) {
        recipients.add(a);
    }
    
    public void removeRecipient( Address a) {
        recipients.remove(a);
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getSubject() {
        return subject;
    }

    public String getHost() {
        return host;
    }
    
    public void send() {    

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
         
            message.setRecipients(Message.RecipientType.TO, recipients.toArray(new Address[0]));
            message.setFrom( from);
            message.setSubject( subject);
            message.setText( content);

            Transport.send(message);
         
            logger.info("Sent message successfully.");
      } catch (MessagingException mex) {
          logger.error("Error sending notification.", mex);
      }
   }
   
   public static void main( String[] args) {
      // Assuming you are sending email from localhost
//      String nghost = "localhost";
//        String host = "172.20.175.93";
       Postman sendMail = new Postman( "172.20.175.93");
       
        try {
            sendMail.addRecipient( new InternetAddress("maffittd@mir.wustl.edu"));
            sendMail.setFrom( new InternetAddress("maffittd@mir.wustl.edu"));
            sendMail.setSubject("This is the subject");
            sendMail.setContent("This is the message.");
            
            sendMail.send();
            
        } catch (AddressException ex) {
            ex.printStackTrace();
        }
   }

}
