package edu.wustl.mir.ctt.directory;

import javax.naming.NamingException;

/**
 *
 * @author drm
 */
public class DirectoryManagerException extends Exception {

    public DirectoryManagerException( Exception e) {
        super(e);
    }

    DirectoryManagerException(String string, Exception ex) {
        super( string, ex);
    }

    DirectoryManagerException(String string) {
        super( string);
    }

}
