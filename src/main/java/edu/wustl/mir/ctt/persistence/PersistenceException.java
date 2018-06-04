package edu.wustl.mir.ctt.persistence;

public class PersistenceException extends Exception {
	
	public PersistenceException( String msg, Exception e) {
		super( msg, e);
	}

	public PersistenceException( String msg) {
		super( msg);
	}

}
