package edu.wustl.mir.ctt.beans;

import edu.wustl.mir.ctt.Controller;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.ECPEvents;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.StudyArmStatus;
import edu.wustl.mir.ctt.notification.NotificationException;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author drm
 */
@ManagedBean
@RequestScoped
public class AddEventBean implements Serializable {
    private ECPEventTypes eventType;
    private Date dateOfOccurance;
    private String title;
    
    @ManagedProperty(value="#{controller}")
    private Controller controller;
    
    public AddEventBean() {
    }
    
    public List<ECPEventTypes> getEventTypes() {
        StudyArmStatus sas = controller.getSelectedParticipant().getStudyArmStatus();
        if(sas.equals(StudyArmStatus.CONTROL_ARM)){
            return ECPEventTypes.getObservationalArmUnscheduledEvents();
        } else {
            return ECPEventTypes.getUnscheduledEvents();
        }
    }

    public ECPEventTypes getEventType() {
        return eventType;
    }

    public void setEventType(ECPEventTypes eventType) {
        this.eventType = eventType;
    }
    
    public String addEventAction() throws PersistenceException, NotificationException {
        // NOTE: If the event happens to be a Change in Therapy, the Change in Therapy form does not get populated with a copy of most of the baseline therapy values
        // or the Change in Therapy until the Change in Therapy form is read (See JDBCPersistenceManager.getChangeTherapyForm for details relating to copying the Baseline
        // or Change values to the newly created Change in Therapy form).
        Event e = ECPEvents.getInstance(eventType);
        System.out.println("The addEventAction eventType is: " + eventType.getName() + "  " + eventType.name());
        e.setActualDate( dateOfOccurance);
        
        return controller.insertEventAction( e);
    }

    public Date getDateOfOccurance() {
        return dateOfOccurance;
    }

    public void setDateOfOccurance(Date dateOfOccurance) {
        this.dateOfOccurance = dateOfOccurance;
    }
    
    public void setController(Controller c) {
        this.controller = c;
    }
    
    public Controller getController() {
        return controller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
