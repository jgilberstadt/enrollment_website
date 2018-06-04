package edu.wustl.mir.ctt.notification.properties;

import edu.wustl.mir.ctt.notification.NotificationContent;
import edu.wustl.mir.ctt.notification.NotificationException;
import edu.wustl.mir.ctt.notification.NotificationManager;
import edu.wustl.mir.ctt.notification.NotificationType;
import edu.wustl.mir.ctt.notification.Postman;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author drm
 */
public class EmailNotificationManager implements NotificationManager {
    
    private List<Address> enrollmentEmailAddressList;
    private List<Address> saeEmailAddressList;
    private Address fromAddress;
    private Postman postman;
    private static final Logger logger = Logger.getLogger(EmailNotificationManager.class);
    
    public EmailNotificationManager( String enrollmentListFileName, String saeListFileName, String smtpHost) throws NotificationException {
        List<String> enrollmentList = new ArrayList<String>();
        List<String> saeList = new ArrayList<String>();
        //BufferedReader reader = null;
        
        InputStream enrollmentListFile = null;
        InputStream saeListFile = null;
        
        try {
            fromAddress = new InternetAddress("notify@mir.wustl.edu");
            
            logger.info("The enrollment notification list is at " + enrollmentListFileName);

            enrollmentListFile = new FileInputStream(enrollmentListFileName);
                
            for (String addr : IOUtils.readLines(enrollmentListFile, StandardCharsets.UTF_8)) {
                if( ! ("".equals(addr) || addr.startsWith("#"))) {
                    enrollmentList.add(addr);
                }
            }
            
            logger.info("The SAE notification list is at " + saeListFileName);
            
            saeListFile = new FileInputStream(saeListFileName);
            
            for (String addr : IOUtils.readLines(saeListFile, StandardCharsets.UTF_8)) {
                if( ! ("".equals(addr) || addr.startsWith("#"))) {
                    saeList.add(addr);
                }
            }
            
            /*
//            ClassLoader loader = Util.getCurrentLoader(this);
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = loader.getResourceAsStream(enrollmentListFileName);
            reader = new BufferedReader( new InputStreamReader(is));
            
            String s;
            while( (s = reader.readLine()) != null) {
                if( ! ("".equals(s) || s.startsWith("#"))) {
                    enrollmentList.add(s);
                }
            }
            reader.close();
            
            is = loader.getResourceAsStream(saeListFileName);
            reader = new BufferedReader( new InputStreamReader(is));
            while( (s = reader.readLine()) != null) {
                if( ! ("".equals(s) || s.startsWith("#"))) {
                    saeList.add(s);
                }
            }
            reader.close();
            */
            
            init( enrollmentList, saeList, smtpHost);
                        
        }
        catch( AddressException | IOException e) {
            String msg = "Failure initializing.";
            logger.error( msg, e);
            throw new NotificationException( msg, e);
        }
        finally {
            IOUtils.closeQuietly(enrollmentListFile);
            IOUtils.closeQuietly(saeListFile);
            
            /*
            if( reader != null) {
                try { reader.close();} catch( Exception ignore) {};
            }
            */
        }
    }
    
    public EmailNotificationManager( List<String> enrollmentList, List<String> saeList, String smtpHost) {
                
        init( enrollmentList, saeList, smtpHost);
    }
    
    private void init( List<String> enrollmentList, List<String> saeList, String smtpHost) {
        enrollmentEmailAddressList = new ArrayList<Address>(enrollmentList.size());
        saeEmailAddressList = new ArrayList<Address>(saeList.size());
        postman = new Postman( smtpHost);
        
        for( String address: enrollmentList) {
            try {
                InternetAddress iaddr = new InternetAddress(address, true);
                enrollmentEmailAddressList.add(iaddr);
            }
            catch( AddressException e) {
                logger.warn("Skipping invalid email address in enrollment notification list: " + address);
            }
        }
        
        for( String address: saeList) {
            try {
                InternetAddress iaddr = new InternetAddress(address, true);
                saeEmailAddressList.add(iaddr);
            }
            catch( AddressException e) {
                logger.warn("Skipping invalid email address in SAE notification list: " + address);
            }
        }
    }

    @Override
    public void send(NotificationType type, NotificationContent content) throws NotificationException {
        switch (type) {
            case PARTICIPANT_ENROLLED:
                if( enrollmentEmailAddressList.isEmpty()) {
                    logger.warn("Enrollment notification address list is empty. No notifications will be sent.");
                    break;
                }
                postman.setFrom( fromAddress);
                postman.setRecipients( enrollmentEmailAddressList);
                postman.setSubject("ECP Registry New BOS: " + content.getSiteName() + " has enrolled a new participant.");
                String msg = "A New BOS participant has been enrolled.\n";
                msg += "Site: " + content.getSiteName() + "\n";
                msg += "Participant ID: " + content.getParticipantId() + "\n";
                //msg += "Study Arm: " + content.getStudyArm() + "\n";
                msg += "User: " + content.getUserName() + "\n";
                postman.setContent(msg);
                postman.send();
                break;
            case PARTICIPANT_CROSSED_OVER:
                if( enrollmentEmailAddressList.isEmpty()) {
                    logger.warn("Enrollment notification address list is empty. No notifications will be sent.");
                    break;
                }
                postman.setFrom( fromAddress);
                postman.setRecipients( enrollmentEmailAddressList);
                postman.setSubject("ECP Registry New BOS: " + content.getSiteName() + " has crossed over a participant to the " + content.getStudyArm() + ".");
                msg = "A New BOS participant has been crossed over to the " + content.getStudyArm() + ".\n";
                msg += "Site: " + content.getSiteName() + "\n";
                msg += "Participant ID: " + content.getParticipantId() + "\n";
                msg += "Study Arm: " + content.getStudyArm() + "\n";
                msg += "User: " + content.getUserName() + "\n";
                postman.setContent(msg);
                postman.send();
                break;
            case SAE_CREATED:
                if( saeEmailAddressList.isEmpty()) {
                    logger.warn("SAE notification address list is empty. No notifications will be sent.");
                    break;
                }
                postman.setFrom( fromAddress);
                postman.setRecipients( saeEmailAddressList);
                postman.setSubject("ECP Registry New BOS: New Serious Adverse Event for participant " + content.getParticipantId() + " at site " + content.getSiteName());
                msg = "A New BOS Serious Adverse Event has been created.\n";
                msg += "Site: " + content.getSiteName() + "\n";
                msg += "Participant ID: " + content.getParticipantId() + "\n";
                msg += "Study Arm: " + content.getStudyArm() + "\n";
                msg += "Title: " + content.getTitle() + "\n";
                msg += "User: " + content.getUserName() + "\n";
                postman.setContent(msg);
                postman.send();
                break;
            case SAE_UPDATED:
                if( saeEmailAddressList.isEmpty()) {
                    logger.warn("SAE notification address list is empty. No notifications will be sent.");
                    break;
                }
                postman.setFrom( fromAddress);
                postman.setRecipients( saeEmailAddressList);
                postman.setSubject("ECP Registry New BOS: Update to Serious Adverse Event for participant " + content.getParticipantId() + " at site " + content.getSiteName());
                msg = "A New BOS Serious Adverse Event has been updated.\n";
                msg += "Site: " + content.getSiteName() + "\n";
                msg += "Participant ID: " + content.getParticipantId() + "\n";
                msg += "Study Arm: " + content.getStudyArm() + "\n";
                msg += "Title: " + content.getTitle() + "\n";
                msg += "User: " + content.getUserName() + "\n";
                postman.setContent(msg);
                postman.send();
                break;
            default:
                logger.warn("Notification Manager ignoring notification type: " + type);
        }
    }
    
}
