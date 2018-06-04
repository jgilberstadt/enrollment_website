package edu.wustl.mir.ctt.notification;

/**
 *
 * @author drm
 */
public class NotificationException extends Exception {
	
	public NotificationException( String msg, Exception e) {
		super( msg, e);
	}

	public NotificationException( String msg) {
		super( msg);
	}

}
