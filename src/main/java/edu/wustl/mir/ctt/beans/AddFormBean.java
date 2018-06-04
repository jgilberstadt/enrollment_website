package edu.wustl.mir.ctt.beans;

import edu.wustl.mir.ctt.AEController;
import edu.wustl.mir.ctt.Controller;
import edu.wustl.mir.ctt.SecurityManager;
import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.ECPEvents;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.EventStatus;
import edu.wustl.mir.ctt.model.FormStatus;
import edu.wustl.mir.ctt.notification.NotificationException;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author drm
 */
@ManagedBean
@RequestScoped
public class AddFormBean implements Serializable {
    private Event event;
    private ECPFormTypes formType;
    private Date dateOfOccurance;
    private String title;
    
    @ManagedProperty(value="#{controller}")
    private Controller controller;
    
    public AddFormBean() {
    }
    
    @PostConstruct
    public void init() {
        // Automatically put today's date into the Add SAE Follow-up Form popup window.
        dateOfOccurance = new Date();
        event = controller.getSelectedEvent();
    }
    
    
    public List<ECPFormTypes> getFormTypes() {
        List<ECPFormTypes> ecpt = event.getType().getUnscheduledFormTypes();
        
        if (ecpt.contains(ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION) && !canAddDSCForm()) {
            ecpt = new ArrayList(ecpt);
            ecpt.remove(ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION);
        }
        
        return ecpt;
    }

    public ECPFormTypes getFormType() {
        return formType;
    }

    public void setFormType(ECPFormTypes formType) {
        this.formType = formType;
    }
    
    public String addFormAction() throws PersistenceException, NotificationException {
        String eventSummaryPage;
        
        BasicForm form = ECPFormTypes.getInstance(formType, event);
        form.setDate(dateOfOccurance);
        
        if (formType == ECPFormTypes.ADVERSE_EVENT_WORKSHEET) {
            // Set the current date to be the same as the Form Date (dateOfOccurrance) found in the Add SAE Follow-up Form popup window.
            AdverseEventWorksheetSAEForm aews = (AdverseEventWorksheetSAEForm) form;
            aews.setCurrentDate(dateOfOccurance);
            
            // The newly created form needs to be inserted into the database before the event status can be updated.
            eventSummaryPage = controller.insertFormAction( event, aews);
        } else if (formType == ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION) {
            if (canAddDSCForm()) {
                eventSummaryPage = controller.insertFormAction( event, form);
            } else {
                eventSummaryPage = null;
            }
        } else {
            eventSummaryPage = controller.insertFormAction( event, form);
        }
        
        // If you try to update the event status before inserting the new form, the status will be only from the
        // previously created forms.  The idea is to update the event status to be NEW when a new form is added.
        // The updateEventStatus gets the forms from the database for the particular event passed to it.  The 
        // status of each form is checked to determine the lowest status precedence, and the lowest status is 
        // used to determine the event status.
        controller.updateEventStatus(event);
        
        return eventSummaryPage;
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
    
    public boolean canAddDSCForm() {
        boolean canAddDSCForm = true;

        if ( SecurityManager.canVerifyForms() ) {
            try {
                PersistenceManager pm = ServiceRegistry.getPersistenceManager();

                List<BasicForm> le = pm.getForms(event);

                // Only allow one DSC form
                for (BasicForm f : le) {
                    if (f.getFormType() == ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION) {
                        canAddDSCForm = false;
                    }
                }
            } catch (PersistenceException pe) {
                // fail
            }
        } else {
            canAddDSCForm = false;
        }
        
        return canAddDSCForm;
    }

}
