package edu.wustl.mir.ctt.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drm
 */
public class ActionManager implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<ActionListener> listeners;
    
    public ActionManager() {
        listeners = new ArrayList<ActionListener>();
    }
    
    public void addListener( ActionListener l) {
        listeners.add(l);
    }
    
    public void removeListener( ActionListener l) {
        listeners.remove(l);
    }
    
    public void fire( Action a) throws ActionException {
        for( ActionListener l: listeners) {
            l.fire(a);
        }
    }
}
