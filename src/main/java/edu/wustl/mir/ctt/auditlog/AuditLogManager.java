package edu.wustl.mir.ctt.auditlog;

/**
 *
 * @author drm
 */
public interface AuditLogManager {
    
//    void log( String userName, String siteName, String participantID, Object obj) throws AuditLogException;
    
//    void log( String userName, Object obj) throws AuditLogException;
    
    void log(Object obj) throws AuditLogException;
    
}
