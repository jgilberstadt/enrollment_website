package edu.wustl.mir.ctt.beans;

import edu.wustl.mir.ctt.Controller;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author drm
 */
@ManagedBean
@RequestScoped
public class EnrollmentBean implements Serializable {
    private int siteId;
    private int participantId;
    private Date firstTreatmentDate;
    
    @ManagedProperty(value="#{controller}")
    private Controller controller;
    
    public EnrollmentBean() {
    }
    
    public void setController(Controller c) {
        this.controller = c;
    }
    
    public Controller getController() {
        return controller;
    }
    
    public boolean isElligible() {
        return true;
    }

    public Date getFirstTreatmentDate() {
        return firstTreatmentDate;
    }

    public void setFirstTreatmentDate(Date firstTreatmentDate) {
        this.firstTreatmentDate = firstTreatmentDate;
    }

    public int getSiteId() {
        return siteId;
    }
    
    public void setSiteId( int id) {
        siteId = id;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }
    
//    public void load() throws PersistenceException {
//        if( site.getId() > 0) {
//            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
//            site = pm.getSite( site.getId());
//        }
//    }
    
}
