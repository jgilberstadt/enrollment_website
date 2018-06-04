package edu.wustl.mir.ctt.action;

public class ActionException extends Exception {
	
	public ActionException( String msg, Exception e) {
		super( msg, e);
	}

	public ActionException( String msg) {
		super( msg);
	}

}
