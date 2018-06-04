package edu.wustl.mir.ctt.persistence;

import edu.wustl.mir.ctt.auditlog.AuditLogException;
import edu.wustl.mir.ctt.auditlog.AuditLogManager;
import edu.wustl.mir.ctt.auditlog.JDBCAuditLogManager;
import edu.wustl.mir.ctt.directory.DirectoryManager;
import edu.wustl.mir.ctt.directory.DirectoryManagerException;
import edu.wustl.mir.ctt.directory.LDAPDirectoryManager;
import edu.wustl.mir.ctt.notification.NotificationException;
import edu.wustl.mir.ctt.notification.NotificationManager;
import edu.wustl.mir.ctt.notification.properties.EmailNotificationManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

public class ServiceRegistry {

    private static PersistenceManager pm;
    private static ReportPersistenceManager rpm;
    private static AuditLogManager alm;
    private static NotificationManager nm;
    private static DirectoryManager dm;
    
    private static final Logger logger = Logger.getLogger(ServiceRegistry.class);

    public static PersistenceManager getPersistenceManager() throws PersistenceException {
        if(pm == null) {
            pm = new JDBCPersistenceManager();
        }
        return pm;
    }

    public static ReportPersistenceManager getReportPersistenceManager() throws PersistenceException {
        if(rpm == null) {
            rpm = new JDBCReportPersistenceManager();
        }
        return rpm;
    }

    public static AuditLogManager getAuditLogManager() throws AuditLogException {
        if(alm == null) {
            alm = new JDBCAuditLogManager();
        }
        return alm;
    }

    public static NotificationManager getNotificationManager() throws NotificationException {
        if(nm == null) {
            try {
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                String smtphost = (String)env.lookup("smtpHost");
                
                String enrollmentListFileName = (String)env.lookup("enrollmentNotificationEmailList");
                String saeListFileName = (String)env.lookup("saeNotificationEmailList");
                
                nm = new EmailNotificationManager( enrollmentListFileName, saeListFileName, smtphost);
            }
            catch( NamingException | NotificationException e) {
                String msg = "Failed to initialize Notification Manager.";
                logger.error(msg, e);
                throw new NotificationException( msg, e);
            }
        }
        return nm;
    }

    public static DirectoryManager getDirectoryManager() throws DirectoryManagerException {
        if( dm == null) {
            dm = LDAPDirectoryManager.getDirectoryManager();
        }
        return dm;
    }
}
