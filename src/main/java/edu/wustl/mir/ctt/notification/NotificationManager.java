package edu.wustl.mir.ctt.notification;

/**
 *
 * @author drm
 */
public interface NotificationManager {
    
    void send(NotificationType type, NotificationContent content) throws NotificationException;
    
}
