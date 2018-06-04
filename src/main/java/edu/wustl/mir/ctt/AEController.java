package edu.wustl.mir.ctt;

import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.ECPEvents;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.EventStatus;
import edu.wustl.mir.ctt.model.FormStatus;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.notification.NotificationException;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import org.primefaces.context.RequestContext;
//import wsclient.WebServiceException_Exception;

@ManagedBean
@ViewScoped
public class AEController implements Serializable {
    private AdverseEventWorksheetSAEForm aeForm;
    private Participant participant;
    private int i = 0;
    private int j = 0;
    
    // The participant or patient id (pid) is a string of format xxxxxx and is not the participant table id 
    //which is an autogenerated integer returned by the enrollment webservice.
    private String pid = "";

    @ManagedProperty(value="#{controller}")
    private Controller controller;
    
    public AEController() {
        // initialization is moved to 'PostConstruct' because that
        // is when the injection is gauranteed to be complete.
    }
    
    @PostConstruct
    protected void init() {
        if( controller.getSelectedForm() == null) {
            aeForm = new AdverseEventWorksheetSAEForm();
            aeForm.setCrfVersion(controller.getSelectedSite().getCrfVersion());
            aeForm.versionControl();
        } else {
            aeForm = (AdverseEventWorksheetSAEForm) controller.getSelectedForm();
        }
        
//        aeForm.setReportType("initial");
        participant = controller.getSelectedParticipant();
        pid = participant.getParticipantID();
        
        Calendar calendar = Calendar.getInstance();
        if(aeForm.getCurrentDate() == null){
            aeForm.setCurrentDate( calendar.getTime());
        }
        aeForm.setDateParticipantEnrollment(participant.getEnrolledDate());
        aeForm.setMethoxsalen("");
    }
    
    public Controller getController() {
        return controller;
    }
    
    public void setController( Controller controller) {
        this.controller = controller;
    }
    
    public AdverseEventWorksheetSAEForm getAdverseEventWorksheetSAEForm() {
        return aeForm;
    }
    
    public void setAdverseEventWorksheetSAEForm(AdverseEventWorksheetSAEForm eform) {
        this.aeForm = eform;
    }
    
    public String getParticipantID() {
        // The participant or patient id (pid) is a string of format xxxxxx 
        // and is not the participant table id which is an autogenerated integer returned by the 
        // enrollment webservice.
        return pid;
    }
    
    public boolean isFormSummaryControlRendered() {
        return true;
    }
    
    public boolean isSourceDocControlRendered() {
        return true;
    }
    
    public boolean isSectionOneAttestationRendered() {
        return isAESectionComplete() && (! isSeriousAdverseEvent()) && ( ! isUnanticipatedProblem()) && ( ! isQualifyingSAE());
    }
    
    public boolean isSectionTwoAttestationRendered() {
        System.out.println("In isSectionTwoAttestationRendered, aeForm.isSAESectionComplete() value is: " + isSAESectionComplete() + " !!");
        System.out.println("In isSectionTwoAttestationRendered, !isQualifyingSAE() value is: " + !isQualifyingSAE());
        System.out.println("In isSectionTwoAttestationRendered, !isUnanticipatedProblem() value is: " + !isUnanticipatedProblem() + " and count = " + i++ + "\n");
        return isSAESectionComplete() && ( !isQualifyingSAE()) && ( ! isUnanticipatedProblem());
    }

    // The following method renders AE/SAE Section 1
    public boolean isAESectionRendered() {
        return true;
    }

    // The following method renders AE/SAE Section 2
    public boolean isSAESectionRendered() {
        boolean isQualifyingSAE = isQualifyingSAE();
        boolean isSAE = isSeriousAdverseEvent();
        boolean isUP = isUnanticipatedProblem();
        if((isQualifyingSAE == false) && (isSAE == false) && (isUP == false)){
            // The RequestContext context in the reset below does not want to work in reseting the date/time if a p:ajax component is used in the calendar.
            resetSection2Items();
            // It appears the context reset does not work on the questions.  The RequestContext context is primefaces origin which may explain the not working issue.
            // Instead, the questions must be cleared.
            clearSection2Questions();
            clearSection3Questions();
        }
//        System.out.println("In isSAESectionRendered, getWasEventRelatedToUseECP value is: " + aeForm.getWasEventRelatedToUseECP() + " !!");
//        System.out.println("In isSAESectionRendered, isSeriousAdverseEvent value is: " + isSAE);
//        System.out.println("In isSAESectionRendered, isUnanticipatedProblem value is: " + isUP + " and count = " + i++ + "\n");
        return  isQualifyingSAE || isSAE || isUP;
    }
    
    // The following method renders AE/SAE Section 3
    public boolean isSAEDetailSectionRendered() {
//        System.out.println("In isSAEDetailedSectionRendered, getWasEventRelatedToUseECP value is: " + aeForm.getWasEventRelatedToUseECP() + " !!");
//        System.out.println("In isSAEDetailedSectionRendered, isQualifyingSAE value is: " + isQualifyingSAE());
//        System.out.println("In isSAEDetailedSectionRendered, isSAESectionRendered value is: " + isSAESectionRendered() + " and count = " + j++ + "\n");
        return (isQualifyingSAE() && isSAESectionRendered()) || (isUnanticipatedProblem() && isSAESectionRendered());
    }

        //================================================================================================
    // In Section 1 of the AE/SAE form, if you answer YES to Question 1 or 2 or 3 or any combination,
    // then the event is a Serious Adverse Event (SAE).  If the answer to Question 1 or 2 is YES,
    // then the SAE is a Qualifying event requiring Sections 1, 2 and 3 to be completed,
    // and the SAE should be submitted to Washington University.
    //
    // If you answer YES to one or more of the parts in Question 3 in Section 1, then the event
    // is an SAE, but may not be a Qualifying SAE.  Whether the event in Question 3 is a Qualifying
    // SAE depends on how you answer the questions in Section 2.
    //
    // In the isSeriousAdverseEvent method below, the getters for Questions 1, 2 and 3 in
    // Section 1 are listed.
    //================================================================================================
    public boolean isSeriousAdverseEvent() {
        if ("true".equals(aeForm.getSeriousInjury())) {
            return true;
        } else if ("true".equals(aeForm.getHospitalAdmission())) {
            return true;
        } else if ("true".equals(aeForm.getPregnancyAbortion())) {
            return true;
        } else if ("true".equals(aeForm.getCongenitalAnomaly())) {
            return true;
        } else if ("true".equals(aeForm.getCancerNeonateInfant())) {
            return true;
        } else if ("true".equals(aeForm.getAggressiveMedicalIntervention())) {
            return true;
        } else if ("true".equals(aeForm.getSeriouslyJeopardizedHealth())) {
            return true;
        } else if ("true".equals(aeForm.getEmergencyDepartmentVisit())) {
            return true;
        } else {
            return false;
        }
    }
    
    //================================================================================================
    // In Section 1 of the AE/SAE form, if you answer YES to Question 4, then the event is an
    // Unanticipated Problem, and must be reported to Washington University.  All three sections of
    // the Serious Adverse Event (SAE) must be completed and the SAE should be submitted to Washington 
    // University..
    //================================================================================================
    public boolean isUnanticipatedProblem() {
       return "true".equals(aeForm.getIsUnanticipatedProblem());
    }
    
    //================================================================================================
    // In Section 1 of the AE/SAE form, if you answer YES to either Question 1 or 2, then the event
    // is a Qualifying Serious Adverse Event (SAE) requiring Sections 1, 2 and 3 to be completed,
    // and the SAE should be submitted to Washington University.  
    //
    // If you answer YES to Question 3 in Section 1, then the event is an SAE, but may not be
    // a Qualifying SAE, but depends on how you answer the questions in Section 2.  Your answer to 
    // Question 1 or 2 in Section 2 is directly linked to the answer given to Question 1 or 2 in
    // Section 1.  If the answer is YES to any one of the six questions in Section 2, then the SAE
    // is a Qualifying SAE, requiring all three sections to be completed and the SAE should be
    // submitted to Washington University.
    //
    // In the isQualifyingSAE method below, the getters for the six questions in Section 2 are listed.
    //================================================================================================
    public boolean isQualifyingSAE() {
       if( "true".equals(aeForm.getFatal()))
            return true;
        else if( "true".equals(aeForm.getLifeThreatening()))
            return true;
        else if( "true".equals(aeForm.getWasEventRelatedToUseECP()))
            return true;
        else if( "true".equals(aeForm.getDidEventOccurWithinECP()))
            return true;
        else if( "true".equals(aeForm.getWasEventRelatedToCatheterUse()))
            return true;
        else if( "true".equals(aeForm.getWasEventRelatedToMethoxsalenUse()))
            return true;
        else if( "true".equals(aeForm.getDeepVeinThrombosis()))
            return true;
        else if( "true".equals(aeForm.getSymptomaticPulmonaryEmbolism()))
            return true;
        else if( "true".equals(aeForm.getRelatedToResearch()))
            return true;
        else
            return false;
    }
    
    public boolean isSubmitButtonRendered() {
        return isQualifyingSAE();
    }
    
    public boolean isMethoxsalenRelated() {
        if(aeForm.getWasEventRelatedToMethoxsalenUse() == null || aeForm.getWasEventRelatedToMethoxsalenUse() == ""){
            System.out.println("The value of AEWorksheetSAEForm getWasEventRelatedToMethoxsalenUse() is NULL!!!!!!!!!!!!!!!!!!!!!!!)");
            return false;
        }
//            System.out.println("The value of AEWorksheetSAEForm getWasEventRelatedToMethoxsalenUse() is:" + aeForm.getWasEventRelatedToMethoxsalenUse().equals("true") + "!!");
        return aeForm.getWasEventRelatedToMethoxsalenUse().equals("true");
    }

    public void disableDateCompletionECPProcedureListener(AjaxBehaviorEvent event) {
        System.out.println("disableDateCompletionECPProcedureListener");
        System.out.println( "Called by: " + event.getComponent().getClass().getName());
        System.out.println(event.getComponent().getId());
        boolean b = (boolean) ((UIOutput)event.getSource()).getValue();
        System.out.println("The value of the Not Applicable is: " + aeForm.getEcpProcedureNotRelated() + "\n");
        if(b == true){
            aeForm.setEcpProcedureNotRelated("true");
            aeForm.setDateLastECPProcedure(null);
            aeForm.setTimeLastECPProcedure(null);
        }
        System.out.println("The value of the Fatal component is: " + aeForm.getEcpProcedureNotRelated() + "\n");
    }
            
    public void clearFatalListener(AjaxBehaviorEvent event) {
        System.out.println("clearFatalListener");
        System.out.println( "Called by: " + event.getComponent().getClass().getName() + "  Number: " + i++);
        System.out.println(event.getComponent().getId());
        String s = (String) ((UIOutput)event.getSource()).getValue();
        System.out.println("The value of the component is: " + s);
        System.out.println("The value of the Fatal component is: " + aeForm.getFatal() + "\n");
//        if(s.equals("false")){
//            aeForm.setFatal("false");
//        } else {
//            aeForm.setFatal("true");
//        }
//        System.out.println("The value of the Fatal component is: " + aeForm.getFatal() + "\n");
    }
    
    public void clearMethoxsalenListener(AjaxBehaviorEvent event) {
        System.out.println("clearMethoxsalenListener");
        System.out.println( "Called by: " + event.getComponent().getClass().getName());
        System.out.println(event.getComponent().getId());
        String s = (String) ((UIOutput)event.getSource()).getValue();
        System.out.println("The value of the component is: " + s + "\n");
        System.out.println("The value of the Methoxsalen component is: ++" + aeForm.getMethoxsalen() + "==\n");
        if(s.equals("false")){
            aeForm.setMethoxsalen("");
        }
        System.out.println("The value of the Methoxsalen component is: --" + aeForm.getMethoxsalen() + "!!!\n");
    }
    
    public void updateListener(AjaxBehaviorEvent event) {
        System.out.println("Listener");
        System.out.println( "Called by: " + event.getComponent().getClass().getName());
        System.out.println(event.getComponent().getId());
        String s = (String) ((UIOutput)event.getSource()).getValue();
        System.out.println("The value of the component is: " + s + "\n");
        System.out.println("The value of the Life Threatening component is: " + aeForm.getLifeThreatening() + "\n");
    }
    
    public void titleUpdateListener(AjaxBehaviorEvent event) {
        System.out.println("listener");
        System.out.println( "called by " + event.getComponent().getClass().getName());
        System.out.println(event.getComponent().getId());
    }
    
    public String saveFormAction() throws PersistenceException, NotificationException {
        Event e = ECPEvents.getInstance(ECPEventTypes.SERIOUS_ADVERSE_EVENT);
        e.setActualDate( aeForm.getCurrentDate());
        e.setName( aeForm.getAeTitle());
        e.setLabel( ECPEvents.getLabel(ECPEventTypes.SERIOUS_ADVERSE_EVENT, controller.getSelectedParticipant()));
        e.setStatus(EventStatus.STARTED);
        
        aeForm.setTitle("SAE Form - 1");
        aeForm.setStatus(FormStatus.STARTED);
        aeForm.setDate(aeForm.getCurrentDate());

        e.getForms().add( aeForm);

        return controller.insertEventAction( e);
    }
    
    public String submitFormAction() throws PersistenceException, NotificationException {
        Event e = ECPEvents.getInstance(ECPEventTypes.SERIOUS_ADVERSE_EVENT);
        e.setActualDate( aeForm.getCurrentDate());
        e.setName( aeForm.getAeTitle());
        e.setLabel( ECPEvents.getLabel(ECPEventTypes.SERIOUS_ADVERSE_EVENT, controller.getSelectedParticipant()));
        e.setStatus(EventStatus.SUBMITTED);
        
        aeForm.setTitle("SAE Form - 1");
        aeForm.setStatus(FormStatus.SUBMITTED);
        aeForm.setDate(aeForm.getCurrentDate());

        e.getForms().add( aeForm);

        return controller.insertEventAction( e);
    }
    
    // disadvantage is that it is run everytime the form is submitted but
    // advantage that the error doesn't go away until the user fixes it.
    public void validateTitle( FacesContext context, UIComponent component, Object value) throws ValidatorException {
//        String title = (String) value;
//        try {
//            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
//            Event e = pm.getEvent( pid, title);
//            System.out.println(value);
//            if( e != null) {
//                throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//                "Validation Error", "This title is already in use."));
//            }
//        }
//        catch( PersistenceException e) {
//            throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_FATAL, 
//           "Internal Error", "Unable to validate event title: " + e.getMessage() + "."));
//        }
    }
    
    // A value change listener has the advantage that it will only be executed when the value
    // changes, but since the page is revalidated when every input attribute is changed via
    // ajax, the error will not persist if the user does not fix an error immediately after it is 
    // first displayed.
    public void titleChanged( ValueChangeEvent vce ) throws PersistenceException {
//        String title = (String) vce.getNewValue();
//
//        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
//        Event e = pm.getEvent( pid, title);
//        System.out.println(title);
//        if( e != null) {
//            FacesContext context = FacesContext.getCurrentInstance();
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//            "Validation Error", "This title is already in use.");
//            context.addMessage( vce.getComponent().getClientId(), msg);
//        }
   }

    public void initializeQuestions() {
        aeForm.setAeTitle("AE number");
        aeForm.setFatal("false");
        aeForm.setLifeThreatening("false");
        aeForm.setSeriousInjury("false");
        aeForm.setHospitalAdmission("false");
        aeForm.setPregnancyAbortion("false");
        aeForm.setCongenitalAnomaly("false");
        aeForm.setCancerNeonateInfant("false");
        aeForm.setAggressiveMedicalIntervention("false");
        aeForm.setSeriouslyJeopardizedHealth("false");
        aeForm.setEmergencyDepartmentVisit("false");
        aeForm.setIsUnanticipatedProblem("false");
    }

    //================================================================================================
    // The isSAESectionComplete method below is used to determine if Section 2 of the AE/SAE form,
    // has been completed.  For Section 2 to be complete, all of the questions with getters listed
    // below must be answered.
    //================================================================================================
    public boolean isSAESectionComplete() {
        if ("".equals(aeForm.getReportType()) || aeForm.getReportType() == null) {
            return false;
        }
        if (aeForm.getDateParticipantEnrollment() == null) {
            return false;
        }
        if (aeForm.getDateEventKnown() == null) {
            return false;
        }
        if (aeForm.getTimeEventKnown() == null) {
            return false;
        }
        System.out.println("In isSAESectionComplete, getWasEventRelatedToUseECP() value is:" + aeForm.getWasEventRelatedToUseECP() + "!!");
        System.out.println("In isSAESectionComplete, getDidEventOccurWithinECP() value is:" + aeForm.getDidEventOccurWithinECP() + "!!");
        System.out.println("In isSAESectionComplete, getWasEventRelatedToCatheterUse() value is:" + aeForm.getWasEventRelatedToCatheterUse() + "!!");
        System.out.println("In isSAESectionComplete, getWasEventRelatedToMethoxsalenUse() value is:" + aeForm.getWasEventRelatedToMethoxsalenUse() + "!!");
        if ("".equals(aeForm.getWasEventRelatedToUseECP()) || aeForm.getWasEventRelatedToUseECP() == null) {
            return false;
        }
        if ("".equals(aeForm.getDidEventOccurWithinECP()) || aeForm.getDidEventOccurWithinECP() == null) {
            return false;
        }
        if ("".equals(aeForm.getWasEventRelatedToCatheterUse()) || aeForm.getWasEventRelatedToCatheterUse() == null) {
            return false;
        }
        if ("".equals(aeForm.getWasEventRelatedToMethoxsalenUse()) || aeForm.getWasEventRelatedToMethoxsalenUse() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void resetSection2Items() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.reset("form:quesRT");
        aeForm.setReportType("");
        context.reset("form:dateSerious");
        aeForm.setDateEventSerious(null);
        context.reset("form:timeSerious");
        aeForm.setTimeEventSerious(null);
        context.reset("form:dateKnown");
        aeForm.setDateEventKnown(null);
        context.reset("form:timeKnown");
        aeForm.setTimeEventKnown(null);
        context.reset("form:dateTimeCompletion");
        aeForm.setDateLastECPProcedure(null);
        aeForm.setTimeLastECPProcedure(null);
        context.reset("form:dateTimeResolved");
        aeForm.setDateEventResolved(null);
        aeForm.setTimeEventResolved(null);
        context.reset("form:dateTimeOfDeath");
        aeForm.setDateOfDeath(null);
        aeForm.setTimeOfDeath(null);
        context.reset("form:causeOfDeath");
        aeForm.setCauseOfDeath("");
        context.reset("form:autopsyReport");
        aeForm.setAutopsyPerformed("");
        System.out.println("Just ran AEController resetSection2Items() method!!!!!!!!!!!!!!");
    }

    public void clearSection2Questions() {
        aeForm.setWasEventRelatedToUseECP("");
        aeForm.setDidEventOccurWithinECP("");
        aeForm.setWasEventRelatedToCatheterUse("");
        aeForm.setWasEventRelatedToMethoxsalenUse("");
        aeForm.setMethoxsalen("");
        System.out.println("AEController clearSection2() method was just run!!!!!!\n");
    }

    public void clearSection3Questions() {
        aeForm.setMethoxsalenDose("");
        aeForm.setFullChronologicalDescription("");
        aeForm.setExpectedness("");
        System.out.println("AEController clearSection3() method was just run!!!!!!\n");
    }

    //================================================================================================
    // The isAESectionComplete method below is used to determine if Section 1 of the AE/SAE form,
    // has been completed.  For Section 1 to be complete, all of the questions must be answered.
    //
    // The isAESectionComplete method below contains the getters forQuestions 1, 2, 3 and 4 in
    // Section 1.  All of the question must not be null, but answered either YES or NO.
    //================================================================================================
    public boolean isAESectionComplete() {
        if (aeForm.getFatal() == null) {
            return false;
        }
        if (aeForm.getLifeThreatening() == null) {
            return false;
        }
        if (aeForm.getSeriousInjury() == null) {
            return false;
        }
        if (aeForm.getHospitalAdmission() == null) {
            return false;
        }
        if (aeForm.getPregnancyAbortion() == null) {
            return false;
        }
        if (aeForm.getCongenitalAnomaly() == null) {
            return false;
        }
        if (aeForm.getCancerNeonateInfant() == null) {
            return false;
        }
        if (aeForm.getAggressiveMedicalIntervention() == null) {
            return false;
        }
        if (aeForm.getSeriouslyJeopardizedHealth() == null) {
            return false;
        }
        if (aeForm.getEmergencyDepartmentVisit() == null) {
            return false;
        }
        if (aeForm.getIsUnanticipatedProblem() == null) {
            return false;
        } else {
            return true;
        }
    }


    
}