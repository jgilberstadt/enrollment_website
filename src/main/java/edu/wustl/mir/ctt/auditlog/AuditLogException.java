package edu.wustl.mir.ctt.auditlog;

public class AuditLogException extends Exception {
	
	public AuditLogException( String msg, Exception e) {
		super( msg, e);
	}

	public AuditLogException( String msg) {
		super( msg);
	}

}
