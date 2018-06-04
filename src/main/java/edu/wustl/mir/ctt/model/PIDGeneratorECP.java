package edu.wustl.mir.ctt.model;

import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.util.List;

/**
 *
 * @author drm
 */
public class PIDGeneratorECP {
    
    public static String getNewParticipantID( Site s) throws PersistenceException {
        String pid = null;
        
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        List<String> pids = pm.getAllParticipantIDs(s);
        
        int i = 1;
        do {
            pid = Integer.toString(s.getSiteID() * 10000 + 5000 + pids.size() + i);
            i++;
        } while( pids.contains(pid));
        
        return pid;
    }
}
