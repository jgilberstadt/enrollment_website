package edu.wustl.mir.ctt.directory;

import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author drm
 */
public interface DirectoryManager {
    String getUserName();
    
    String getSiteName() throws DirectoryManagerException;
    
    List<User> getAccounts() throws DirectoryManagerException;
    
    void printAccounts(PrintStream ps) throws DirectoryManagerException;
    
    User getAccount(String username) throws DirectoryManagerException;
}