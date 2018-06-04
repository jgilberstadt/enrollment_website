package edu.wustl.mir.ctt;

import edu.wustl.mir.ctt.action.Action;
import edu.wustl.mir.ctt.action.ActionException;
import edu.wustl.mir.ctt.action.ActionListener;
import edu.wustl.mir.ctt.action.ActionManager;
import edu.wustl.mir.ctt.beans.BannerBean;
import edu.wustl.mir.ctt.beans.EnrollmentBean;
import edu.wustl.mir.ctt.beans.StyleBean;
import edu.wustl.mir.ctt.directory.DirectoryManager;
import edu.wustl.mir.ctt.directory.DirectoryManagerException;
import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.AnnualFollowUpForm;
import edu.wustl.mir.ctt.form.BaselineTherapyForm;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.ChangeTherapyForm;
import edu.wustl.mir.ctt.form.CrossoverEligibilityWorkSheet;
import edu.wustl.mir.ctt.form.CrossoverSafetyCheckForm;
import edu.wustl.mir.ctt.form.DSCForm;
import edu.wustl.mir.ctt.form.DemoMedHistForm;
import edu.wustl.mir.ctt.form.ECPTreatmentForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EndOfStudyForm;
import edu.wustl.mir.ctt.form.HospitalizationForm;
import edu.wustl.mir.ctt.form.ObservePulmEvalLogForm;
import edu.wustl.mir.ctt.form.PulmEvalForm;
import edu.wustl.mir.ctt.form.QualityOfLifeForm;
import edu.wustl.mir.ctt.form.SimpleEligibilityForm;
import edu.wustl.mir.ctt.form.SimpleForm;
import edu.wustl.mir.ctt.model.Attribute;
import edu.wustl.mir.ctt.model.Document;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.ECPEvents;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.EventStatus;
import edu.wustl.mir.ctt.model.FormStatus;
import edu.wustl.mir.ctt.model.PIDGeneratorECP;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.model.ParticipantStatus;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.Site;
import edu.wustl.mir.ctt.model.StudyArmStatus;
import edu.wustl.mir.ctt.notification.NotificationContent;
import edu.wustl.mir.ctt.notification.NotificationException;
import edu.wustl.mir.ctt.notification.NotificationManager;
import edu.wustl.mir.ctt.notification.NotificationType;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
//import org.primefaces.event.FileUploadEvent;
import org.apache.log4j.Logger;

@ManagedBean
@SessionScoped
public class Controller implements Serializable {
    private List<Site> sites;
    private Site selectedSite;
    private List<Participant> participants;
    private Participant selectedParticipant;
    private List<Event> events;
    private Event selectedEvent;
    private BasicForm selectedForm;
    private SimpleForm selectedSimpleForm;
    private SimpleEligibilityForm simpleEligibilityForm;
    private EligibilityForm eligibilityForm;
    private ECPTreatmentForm ecpTreatmentForm;
    private DemoMedHistForm demoMedHistForm;
    private PulmEvalForm pulmEvalForm;
    private CrossoverSafetyCheckForm crossoverSafetyCheckForm;
    private ObservePulmEvalLogForm observePulmEvalLogForm;
    private AdverseEventWorksheetSAEForm adverseEventWorksheetSAEForm;
    private BaselineTherapyForm baselineTherapyForm;
    private ChangeTherapyForm changeTherapyForm;
    private EndOfStudyForm endOfStudyForm;
    private AnnualFollowUpForm annualFollowUpForm;
    private QualityOfLifeForm qualityOfLifeForm;
    private HospitalizationForm hospitalizationForm;
    private DSCForm dscForm;
    private Part sourceDocument;
    private transient FormStateTransitionValidator fstv;
    private CrossoverEligibilityWorkSheet crossoverEligibilityWorkSheet;
    private CalcController calcController;
    private SecurityManager securityManager;
    private Document document;
    private boolean respiration = false;
    private BannerBean banner;
    private StyleBean style;
    
    private transient ActionManager am = new ActionManager();
    private transient Logger logger;
    
    public Controller() {
        fstv = new FormStateTransitionValidator();
        document = new Document();
        logger = Logger.getLogger(Controller.class);
        
        banner = new BannerBean();
        banner.init();
        
        style = new StyleBean();
        style.init();
        
        try {
            this.determineSiteBasedOnUsersLoginInfo();
        } catch (PersistenceException ex) {
            logger.error("Error determining user's site based on login info", ex);
        } catch (DirectoryManagerException ex) {
            logger.error("Error determining user's site based on login info", ex);
        }

        // add action listener that detects form updates and updates the 
        // associated event's status accordingly.
        am.addListener(new ActionListener() { 
            public void fire( Action a) throws ActionException {
                switch (a) {
                    case SAVE_FORM:
                    case SUBMIT_FORM:
                        try {
                            System.out.println("\nIn the FIRE Action and about to run 'updateEventStatus'\n");
                            updateEventStatus( selectedEvent);
                        }
                        catch( PersistenceException e) {
                            throw new ActionException("",e);
                        }
                        am.fire( Action.UPDATE_EVENT);
                        break;   
                }
            }
        });
    }

    public boolean getResp() {
        return respiration;
    }
    
    public Site getSelectedSite() {
        return selectedSite;
    }
    
    public Participant getParticipantSite() {
        return selectedParticipant;
    }
    
    public Event getSelectedEvent() {
        return selectedEvent;
    }
    
    public Participant getSelectedParticipant() {
        return selectedParticipant;
    }
    
    public BasicForm getSelectedForm() {
//        System.out.println("In the Controller, BasicForm has a CRF Form Type of: " + selectedForm.getFormType().getName() );
        return selectedForm;
    }
    
    public SimpleForm getSelectedSimpleForm() {
        return selectedSimpleForm;
    }
    
    public SimpleEligibilityForm getSimpleEligibilityForm() {
        return simpleEligibilityForm;
    }
    
    public EligibilityForm getEligibilityForm() {
        return eligibilityForm;
    }
    
    public ECPTreatmentForm getEcpTreatmentForm() {
        return ecpTreatmentForm;
    }
    
    public DemoMedHistForm getDemoMedHistForm() {
        return demoMedHistForm;
    }
    
    public PulmEvalForm getPulmEvalForm() {
        return pulmEvalForm;
    }
    
    public AdverseEventWorksheetSAEForm getAdverseEventWorksheetSAEForm() {
        return adverseEventWorksheetSAEForm;
    }
    
    public BaselineTherapyForm getBaselineTherapyForm() {
        return baselineTherapyForm;
    }
    
    public ChangeTherapyForm getChangeTherapyForm() {
        return changeTherapyForm;
    }
    
    public EndOfStudyForm getEndOfStudyForm() {
        return endOfStudyForm;
    }
    
    public QualityOfLifeForm getQualityOfLifeForm() {
        return qualityOfLifeForm;
    }
    
    public AnnualFollowUpForm getAnnualFollowUpForm() {
        return annualFollowUpForm;
    }
    public HospitalizationForm getHospitalizationForm() {
        return hospitalizationForm;
    }
    
    public DSCForm getDSCForm() {
        return dscForm;
    }
    
    public ObservePulmEvalLogForm getObservePulmEvalLogForm() {
        return observePulmEvalLogForm;
    }
    
    public CrossoverSafetyCheckForm getCrossoverSafetyForm() {
        return crossoverSafetyCheckForm;
    }
    
    public CrossoverEligibilityWorkSheet getCrossoverEligibilityWorkSheet() {
        return crossoverEligibilityWorkSheet;
    }
    
    public CalcController getCalcController() {
        return calcController;
    }
    
    public BannerBean getBanner() {
        return banner;
    }
    
    public StyleBean getStyle() {
        return style;
    }
     
    public String keyContacts() {
        return "/keyContacts.xhtml?faces-redirect=true";
    }
    
    public String newsletters() {
        return "/newsletters.xhtml?faces-redirect=true";
    }
    
    public String supportStaff() {
        return "/supportStaff.xhtml?faces-redirect=true";
    }
    
    public String about() {
        return "/about.xhtml?faces-redirect=true";
    }
    
    public String getCurrentUserName() {
        String userName = "";
        
        try {
        DirectoryManager dm = ServiceRegistry.getDirectoryManager();
        userName = dm.getUserName();
        } catch (DirectoryManagerException dme) {
            logger.error("Error retrieving username", dme);
        }
        
        return userName;
    }
    
    public List<Site> getSites() throws PersistenceException {

        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        sites = pm.getSites();

        return sites;
    }
    
    public List<Participant> getParticipants() throws PersistenceException {

        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        participants = pm.getParticipants( selectedSite);

        return participants;
    }
    
    public List<Event> getEvents() throws PersistenceException {

        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        events = pm.getEvents( selectedParticipant);
        Collections.sort(events);

        return events;
    }
    
    public List<BasicForm> getForms() throws PersistenceException {

        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        List<BasicForm> forms = pm.getForms(selectedEvent);

        return forms;
    }

    public List<Document> getDocuments() throws PersistenceException {

        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        List<Document> docs = pm.getDocuments(selectedForm);

        return docs;
    }

//    public List<BasicForm> getForms(Event e) throws PersistenceException {
//
//        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
//        List<BasicForm> forms = pm.getForms(e);
//
//        return forms;
//    }

    public Part getSourceDocument() {
        return sourceDocument;
    }

    public void setSourceDocument(Part sourceDocument) {
        this.sourceDocument = sourceDocument;
    }
    
    public void saveForm(BasicForm form) throws PersistenceException, ActionException {
        System.out.println("Controller saveForm method: " + form);
    }

    public void cancelForm(BasicForm form) {
        form.clear();
        System.out.println("Cancel: " + form);
    }
    
    public void viewEvent( ActionEvent ae) {
        String eventId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedEventId");
        System.out.println( eventId);
    }
    
    public String viewSiteAction( Site site) {
        selectedSite = site;
        return "/site.xhtml?faces-redirect=true";
    }
    
    public String editSiteAction() {
        return "/editSite.xhtml?faces-redirect=true";
    }
    
    public String insertSiteAction(Site s) throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        pm.insertSite(s);
        
        return "/allSites.xhtml?faces-redirect=true";
    }
    
    /**
     * It is assumed this is called from the participant summary view so
     * selectedParticipant and events are valid.
     * 
     * @param e
     * @return
     * @throws PersistenceException 
     */
    public String insertEventAction(Event e) throws PersistenceException, NotificationException {
        String action;
        PersistenceManager pm;
        List<BasicForm> fs;
        int i;
        String name;
        
        switch (e.getType()) {
            case CROSSOVER_ELIGIBILITY_WORKSHEET:
                selectedEvent = e;
                
                pm = ServiceRegistry.getPersistenceManager();
                /*
                    // ToDo: This smells like a kludge. Refactor this into the calculator.
                if( selectedParticipant.isHoldStatus()) {
                    if( CrossoverEligibilityCalculator.isHoldExpired(selectedParticipant, new Date())) {
                        selectedParticipant.setHoldStatus(false);
                        pm.setOnHold(selectedParticipant.getParticipantID(), false, null);
                    }
                }*/

                List<PulmonaryEvaluation> evals = new ArrayList<>();      
                
                try {
                    List<Event> allEvents = pm.getEvents(selectedParticipant);

                    logger.debug("Retrieved " + allEvents.size() + " events");

                    for (Event ev : allEvents) {
                        if (ev.getType() == ECPEventTypes.PULMONARY_EVAL) {
                            logger.debug("Found pulmonary eval event " + ev.getName());

                            List<BasicForm> basicforms = pm.getForms(ev);
                            logger.debug("Contains " + basicforms.size() + " forms");

                            BasicForm basicpe = basicforms.get(0);
                            logger.debug("Form title? " + basicforms.get(0).getTitle());

                            PulmEvalForm pef = pm.getPulmEvalForm(basicpe);
                            logger.debug("PE form status? " + pef.getStatus());

                            if (pef.getStatus() == FormStatus.DCC_VERIFIED) {
                                logger.debug("PE form was verified");
                                logger.debug("PE Date " + pef.getDate() + " FEV1 "+ pef.getFev1() + " FVC "+ pef.getFvc());
                                PulmonaryEvaluation pe = new PulmonaryEvaluation(pef.getDate(), pef.getFev1(), pef.getFvc());
                                evals.add(pe);
                            }
                        }
                    }
                }
                catch (Exception exc) {
                    logger.error("Error fetching evals", exc);
                }
                
               // selectedForm = new CrossoverEligibilityWorkSheet(selectedParticipant.getParticipantID(), evals);
                crossoverEligibilityWorkSheet = new CrossoverEligibilityWorkSheet(selectedParticipant.getParticipantID(), evals);
                
                action = "/crossoverEligibilityWorkSheet.xhtml?faces-redirect=true";
                break;
            case ADVERSE_EVENT_WORKSHEET_SAE:
                selectedEvent = e;  // Event is not in DB yet. Use this to pass event's date to worksheet.

                // aEcontroller will create a new form if the current form is null.
                selectedForm = null;

                String formSuffix = "";
                
                if ("7.1".equals(selectedSite.getCrfVersion())) {
                    formSuffix = "V7_1";
                }
                
                action = "/adverseEventWorksheet" + formSuffix + ".xhtml?faces-redirect=true";
                break;
            case SERIOUS_ADVERSE_EVENT:
                pm = ServiceRegistry.getPersistenceManager();

                System.out.println("The event type is: " + e.getType());
                // the forms were created by the worksheet.
                fs = e.getForms();
                
                pm.addInitialSAEEvent(selectedParticipant, e, fs, selectedSite);
                selectedEvent = e;
                
                // The following code is required to update the Event Date to be the AdverseEventWorksheetSAEForm onset date when the SAE is first created by selecting the button Add Event.
                for( BasicForm f: fs) {
                    // Suresh agreed the Event Date should be the same as the SAE Onset date, but the Onset date may not be known initially.
                    // So Dave M. and I (Paul K. Commean) agreed to use the Current date as the Event date until the Onset date is known.
                    AdverseEventWorksheetSAEForm fae = (AdverseEventWorksheetSAEForm) f;
                    System.out.println("The onset date is: " + fae.getOnsetDate());
                    if(fae.getOnsetDate() != null){
                        selectedEvent.setActualDate(fae.getOnsetDate());  // This changes the event date to be the onset date so the event date is set by the next command to display the onset date.
                        updateEventSchedule( selectedParticipant, fae.getOnsetDate(), selectedEvent);
                    } else if(f.getTitle().equals("SAE Form - 1")){  // If the onset date is not known, you only want the current date to be set into the Event date for the first SAE form.
                        updateEventSchedule( selectedParticipant, fae.getCurrentDate(), selectedEvent);
                    }
                }

                NotificationManager nm = ServiceRegistry.getNotificationManager();
                NotificationContent content = new NotificationContent();
                content.setSiteName( selectedSite.getName());
                content.setParticipantId( selectedParticipant.getParticipantID());
                content.setStudyArm(selectedParticipant.getStudyArmStatus().getName());
//                content.setTitle(tl selectedForm.getTitle());
                content.setTitle( e.getName());
                content.setUserName(getCurrentUserName());
                nm.send( NotificationType.SAE_CREATED, content);
                
                action = "/event.xhtml?faces-redirect=true";
                break;
            default:
                pm = ServiceRegistry.getPersistenceManager();

                System.out.println("The event type is: " + e.getType());
                fs = ECPEvents.getForms(e.getType());

                // Get the number of events of a particular type and add the number to the Event Title
                i = pm.getEventTypeCount(selectedParticipant, e);
                System.out.println("The returned integer count for i is: " + i);
                System.out.println("The ECPEventType is: " + e.getType().getName() + "\n");
                // Add the number plus one to the end of the Event Title.
                name = e.getType().getName() + " " + ++i;
                e.setName(name);

                pm.addEvent(selectedParticipant, e, fs, selectedSite);
                selectedEvent = e;

                action = "/event.xhtml?faces-redirect=true";
                break;
        }
        return action;
    }
    
    public String insertFormAction(Event e, BasicForm f) throws PersistenceException, NotificationException {
        String action;
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();

        f.setEventId( e.getId());
        f.setCrfVersion(selectedSite.getCrfVersion());
        f.setIrbVersion(selectedSite.getIrbVersion());
        f.setIrbSubmittedDate(selectedSite.getIrbSubmittedDate());
        System.out.println("Insert form type " + f.getType() + " into event type " + e.getType());
                
        pm.insertForm( f);
        
        if( ECPEventTypes.SERIOUS_ADVERSE_EVENT.equals(e.getType()) && !ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION.equals(f.getFormType())) {
            NotificationManager nm = ServiceRegistry.getNotificationManager();
            NotificationContent content = new NotificationContent();
            content.setSiteName( selectedSite.getName());
            content.setParticipantId( selectedParticipant.getParticipantID());
            content.setStudyArm(selectedParticipant.getStudyArmStatus().getName());
            content.setTitle( e.getName());
            content.setUserName(getCurrentUserName());
            nm.send( NotificationType.SAE_UPDATED, content);
        }
        
        return "/event.xhtml?faces-redirect=true";
    }
    
    public String cancelEditSiteAction() {
        return "/allSites.xhtml?faces-redirect=true";
    }
    
    public String addParticipantAction() throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        
        Participant p = new Participant();
        p.setSiteId( selectedSite.getId());
        p.setEnrolledDate(new Date());  // When the Add Participant button is selected, the participant, event, and basicform tables have a single record added
                                        // containing the participant number, Eligibility Event, and Eligibility Basic Form added all with today's date.
                                        // Since the Confirmation of Eligibility form cannot be completed excepted when all of the eligibility criteria are accepted
                                        // so the date of when the participant was determined to be eligible will always be the date when the confirmation occurred.
                                        // Therefore, today's date makes sense for setting the Actual Date Confirmation of Eligibility form and for settig the basedate
                                        // for the Demographics form which can not be completed because the participant is enrolled in the study now.

        p.setStatus(ParticipantStatus.ENROLLED);
        p.setParticipantID( PIDGeneratorECP.getNewParticipantID(selectedSite));
        p.setShowStudyArm(false);
        
        Event e = ECPEvents.getInstance(ECPEventTypes.ELIGIBILITY);
        selectedEvent = e;  // When the Add Participant button is selected, the selectedEvent has not been selected and needs to be initialized.
//        e.setBaseDate(new Date());
        
        List<BasicForm> forms = ECPEvents.getForms(ECPEventTypes.ELIGIBILITY);
                
        pm.addParticipant( p, e, forms);
        
        selectedParticipant = p;
        
        BasicForm form = forms.get(0);
        
        return viewFormAction(form);
    }
    
    public String viewParticipantAction( Participant participant) {
        selectedParticipant = participant;
        return "/participant.xhtml?faces-redirect=true";
    }
    
    public String viewEventAction( int eventId) {
        selectedEvent = null;
        for( Event e: events) {
            if( e.getId() == eventId) {
                selectedEvent = e;
                break;
            }
        }
        return "/event.xhtml?faces-redirect=true";
    }
    
//    public String viewFormAction(int formId) {
//        try {
//            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
////            selectedForm = pm.getForm(formId);
//            String type = selectedForm.getType();
//            if( "simple".equals(type)) {
//                selectedSimpleForm = (SimpleForm) selectedForm;
//                return "/simpleForm.xhtml";
//            }
//            else if( "medicalHistory".equals(type)) {
//                return "/medicalHistory.xhtml";
//            }
//            else {
//                return "/error.xhtml";
//            }
//        }
//        catch(Exception ex) {
//            return "/error.xhtml";  
//        }
//    }
    
    public String viewFormAction(BasicForm form) {
        try {
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
            ECPFormTypes formType = form.getFormType();
            
            System.out.println("Form type is " + formType.getName());
            
            // Some forms will have different screens based on the protocol version
            String formSuffix = "";
            
            // Either lock all the questions on a CRF or unlock all of the questions when viewing them.
            if(FormStatus.SUBMITTED == form.getStatus()){
                form.setLocked(true);
                form.setCommentLocked(true);
            } else if(FormStatus.DCC_VERIFIED == form.getStatus()){
                form.setLocked(true);
                form.setCommentLocked(true);
            } else if(FormStatus.PI_APPROVED == form.getStatus()){
                form.setLocked(true);
                form.setCommentLocked(true);
            } else {
                form.setLocked(false);
                form.setCommentLocked(false);
            }
            
            if( formType.equals(ECPFormTypes.SIMPLE)) {
                selectedSimpleForm = pm.getSimpleForm(form);
                selectedForm = selectedSimpleForm;
                return "/simpleForm.xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.SIMPLE_ELIGIBILITY)) {
                simpleEligibilityForm = pm.getSimpleEligibilityForm(form);
                selectedForm = simpleEligibilityForm;
                return "/simpleEligibilityForm.xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.ELIGIBILITY)) {
                // Either lock all the questions on a CRF if the Confirmation of Eligibility CRF is
                // either NEW or STARTED because the study coordinator should not be able to change
                // the values entered into the form unless a CRF_QUERY has occurred.  Then the study
                // coordinator can change the form.
                if(FormStatus.NEW == form.getStatus()){
                    form.setLocked(true);
                } else if(FormStatus.STARTED == form.getStatus()){
                    form.setLocked(true);
                }
                eligibilityForm = pm.getEligibilityForm(form);
                System.out.println("In the controller, the EligbilityForm crfversion is: " + eligibilityForm.getCrfVersion() + "\n");
               
                selectedForm = eligibilityForm;
//                String returnForm = null;
//                if(eligibilityForm.getCrfVersion().equals("1.0")){
//                    returnForm = "/eligibilityForm.xhtml?faces-redirect=true";
//                }
//                if(eligibilityForm.getCrfVersion().equals("5.0")){
//                    returnForm = "/eligibilityForm.xhtml?faces-redirect=true";
//                }
                if ("5.0".equals(form.getCrfVersion())) {
                    formSuffix = "V5";
                } else if ("7.0".equals(form.getCrfVersion())) {
                    formSuffix = "V7";
                } else if ("7.1".equals(form.getCrfVersion())) {
                    formSuffix = "V7_1";
                }
                
                return "/eligibilityForm" + formSuffix + ".xhtml?faces-redirect=true";

            }
            else if( formType.equals(ECPFormTypes.DEMOGRAPHICS)) {
                demoMedHistForm = pm.getDemoMedHistForm(form);
                demoMedHistForm.setEnrollmentDate(demoMedHistForm.getDate());
                selectedForm = demoMedHistForm;
                
                if ("7.0".equals(form.getCrfVersion())) {
                    formSuffix = "V7";
                } else if ("7.1".equals(form.getCrfVersion())) {
                    formSuffix = "V7_1";
                }
                
                return "/demoMedHistForm" + formSuffix + ".xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.ECP_TREATMENT)) {
                ecpTreatmentForm = pm.getECPTreatmentForm(form);
                selectedForm = ecpTreatmentForm;
                
                if ("7.0".equals(form.getCrfVersion())) {
                    formSuffix = "V7";
                } else if ("7.1".equals(form.getCrfVersion())) {
                    formSuffix = "V7_1";
                }
                
                return "/ecpTreatmentForm" + formSuffix + ".xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.PULMONARY_EVAL)) {
                pulmEvalForm = pm.getPulmEvalForm(form);
                selectedForm = pulmEvalForm;
                return "/pulmEvalForm.xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.SERIOUS_ADVERSE_EVENT) || formType.equals(ECPFormTypes.ADVERSE_EVENT_WORKSHEET)) {
                adverseEventWorksheetSAEForm = pm.getAdverseEventWorksheetSAEForm(form);
                selectedForm = adverseEventWorksheetSAEForm;
                System.out.println("View the form AdverseEventWorksheetSAEForm \n" );
                
                if ("7.0".equals(form.getCrfVersion())) {
                    formSuffix = "V7";
                } else if ("7.1".equals(form.getCrfVersion())) {
                    formSuffix = "V7_1";
                }
                
                return "/adverseEventForm" + formSuffix + ".xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.BASELINE_THERAPY)) {
                baselineTherapyForm = pm.getBaselineTherapyForm(form);
                baselineTherapyForm.setBaselineTherapyDate(baselineTherapyForm.getDate());
                selectedForm = baselineTherapyForm;
                
                if ("7.0".equals(form.getCrfVersion())) {
                    formSuffix = "V7";
                } else if ("7.1".equals(form.getCrfVersion())) {
                    formSuffix = "V7_1";
                }
                
                return "/baselineTherapy" + formSuffix + ".xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.CHANGE_THERAPY)) {
                changeTherapyForm = pm.getChangeTherapyForm(form);
                selectedForm = changeTherapyForm;
                
                if ("7.0".equals(form.getCrfVersion())) {
                    formSuffix = "V7";
                } else if ("7.1".equals(form.getCrfVersion())) {
                    formSuffix = "V7_1";
                }
                
                return "/changeTherapy" + formSuffix + ".xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.END_OF_STUDY)) {
                endOfStudyForm = pm.getEndOfStudyForm(form);
                selectedForm = endOfStudyForm;
                return "/endOfStudyForm.xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.QUALITY_OF_LIFE)) {
                qualityOfLifeForm = pm.getQualityOfLifeForm(form);
                selectedForm = qualityOfLifeForm;
                return "/qualityOfLifeForm.xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.ANNUAL_FOLLOW_UP)) {
                annualFollowUpForm = pm.getAnnualFollowUpForm(form);
                selectedForm = annualFollowUpForm;
                return "/annualFollowUpForm.xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.PULMONARY_EVAL_LOG)) {
                observePulmEvalLogForm = pm.getObservePulmEvalLogForm(form);
                selectedForm = observePulmEvalLogForm;
                return "/observePulmEvalLogForm.xhtml?faces-redirect=true";
            }
            else if( formType.equals(ECPFormTypes.CROSSOVER_SAFETY_CHECK)) {
                crossoverSafetyCheckForm = pm.getCrossoverSafetyCheckForm(form);
                selectedForm = crossoverSafetyCheckForm;
                
                if ("7.0".equals(form.getCrfVersion()) || "7.1".equals(form.getCrfVersion())) {
                    formSuffix = "V7";
                }
                
                return "/crossoverSafetyForm" + formSuffix + ".xhtml?faces-redirect=true";
            }
            else if ( formType.equals(ECPFormTypes.HOSPITALIZATION)) {
                hospitalizationForm = pm.getHospitalizationForm(form);
                selectedForm = hospitalizationForm;
                
                return "/hospitalizationForm.xhtml?faces-redirect=true";
            }
            else if ( formType.equals(ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION)) {
                dscForm = pm.getDiseaseSpecificCategorizationForm(form);
                selectedForm = dscForm;
                
                return "/diseaseSpecificCategorizationForm.xhtml?faces-redirect=true";
            }
            else {
                return "/error.xhtml?faces-redirect=true";
            }
        }
        catch(Exception ex) {
            logger.error("System error", ex);
            return "/error.xhtml?faces-redirect=true";  
        }
    }
    
    public String recordUserLoginInformation() throws PersistenceException, DirectoryManagerException{
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        String loginUsername = null;
        String remoteIPAddress = null;
        int siteIdDeterminedFromUsername = 0;
        securityManager = new SecurityManager();
        loginUsername = securityManager.getLoginUserName();
        remoteIPAddress = securityManager.getRemoteIPAddress();
        Date loginDate = new Date();
        this.determineSiteBasedOnUsersLoginInfo();
        siteIdDeterminedFromUsername = selectedSite.getId();
        pm.insertUserLoginInformation(siteIdDeterminedFromUsername, loginUsername, remoteIPAddress, loginDate);
        return loginUsername;
    }
    
    
    /**
     * Redirect the user to the appropriate landing page based on their 
    */
    public String registryLoginAction() throws PersistenceException, DirectoryManagerException {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        
        if( SecurityUtils.getSubject().isPermitted("allSites:view")) {
            return "allSites.xhtml?faces-redirect=true";
        }
        else {
            this.determineSiteBasedOnUsersLoginInfo();
            return "/site.xhtml?faces-redirect=true";
        }
    }
    
    public void determineSiteBasedOnUsersLoginInfo() throws PersistenceException, DirectoryManagerException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        // send everyone to Wash U site for now.
//            String siteName = "WashU";
        // Users must be associated with a single site and 
        // the site's name must match the name in the ldap.
        DirectoryManager dm = ServiceRegistry.getDirectoryManager();
        String siteName = dm.getSiteName();
        Site site = pm.getSite( siteName);
        selectedSite = site;
        System.out.println("The determineSiteBasedOnUsersLoginInfo selectedSite.getName is: " + selectedSite.getName() + "\n");

    }

    public String saveFormAction() throws PersistenceException, ActionException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        if( FormStatus.NEW.equals(selectedForm.getStatus())) {
            selectedForm.setStatus(FormStatus.STARTED);
        }
        pm.saveForm(selectedForm);
        am.fire(Action.SAVE_FORM);
        
        String nextPage = null;
        
        if( ECPFormTypes.ELIGIBILITY.equals(selectedForm.getFormType())) {
            EligibilityForm f = (EligibilityForm) selectedForm;

            if (selectedParticipant.isShowStudyArm() == false && f.isBaselineQualityOfLifeCompleted() == true && f.getBaselineQualityOfLifeDate() != null) {
                selectedParticipant.setShowStudyArm(true);
                pm.setShowArm(selectedParticipant.getParticipantID(), true);
                nextPage = "/armAssignmentResults.xhtml?faces-redirect=true";
            }
        }
        
        System.out.println("The saveFormAction method was run \n");
        return nextPage;
    }
    
    public String submitFormAction() throws PersistenceException, ActionException, NotificationException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        System.out.println("Inside submitFormAction");
        System.out.println("The selectedForm formType is: " + selectedForm.getFormType() + "\n\n");
        System.out.println("The STUDY ARM STATUS is: " + selectedParticipant.getStudyArmStatus() + "\n\n");
        
        fstv.setForm(selectedForm);
        if( ! fstv.isValid()) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage("Submission Error", fstv.getMessage()));
            // return null so browser stays on form that failed to submit.
            // forms will detect failed submit and display warning.

            return null;
        }
        selectedForm.setLastSubmittedDate(new Date());
        
        if( ECPFormTypes.SIMPLE_ELIGIBILITY.equals(selectedForm.getFormType())) {
            selectedForm.setStatus( fstv.getNewStatus());
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);
        
            return "/enrollment.xhtml?faces-redirect=true";
        }
        else if( ECPFormTypes.ELIGIBILITY.equals(selectedForm.getFormType())) {
            
//            System.out.println("The selectForm.getStatus for Eligibility is: " + selectedForm.getStatus().toString() + "   " + selectedForm.getStatus().getName());
            // Get the current form status to test below to determine if the status is NEW or not.
            FormStatus currentStatus = fstv.getOldStatus();
                        
            selectedForm.setStatus(fstv.getNewStatus());
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            System.out.println("The selected form is: " + selectedForm.getTitle() + "\n\n");

            EligibilityForm f = (EligibilityForm) selectedForm;
            
            // When the Eligibility form is submitted for the first time, you need to enroll the participant,
            // but after enrollment, the eligibility form needs to be updated.
//            if(FormStatus.NEW.equals(currentStatus) || FormStatus.STARTED.equals(currentStatus)) {
            if(FormStatus.DCC_VERIFIED.equals(selectedForm.getStatus())) {
                enrollParticipant();
                
                updateEnrollmentEventSchedule(selectedParticipant, f.getEnrollmentDate());
                
            } else if( selectedEvent.getName().equals("Confirmation of Eligibility")){
//                if(FormStatus.NEW.equals(f.getStatus())) {
//                    f.onNew();
//                    pm.saveForm(f);
//                }
//                if(FormStatus.SUBMITTED.equals(f.getStatus())) {
//                    f.onSubmit();
//                    pm.saveForm(f);
//                }
                System.out.println("The selectedEvent is Eligibility \n");
                System.out.println("controller.submitFormAction with selectedEvent name of: " + selectedEvent.getName());
                System.out.println("controller.submitFormAction with selectedEvent id of: " + selectedEvent.getId());
                System.out.println("controller.submitFormAction with selectedEvent basedate of: " + selectedEvent.getBaseDate());
                System.out.println("controller.submitFormAction with selectedEvent actualdate of: " + selectedEvent.getActualDate() + "\n\n");
//                updateEventSchedule( selectedParticipant, f.getDateEligConfirmed(), selectedEvent);
                updateEventSchedule( selectedParticipant, f.getEnrollmentDate(), selectedEvent);
            }
            
            String nextPage = "/participant.xhtml?faces-redirect=true";
            
            if (selectedParticipant.isShowStudyArm() == false) {
                if (f.isBaselineQualityOfLifeCompleted() == true && f.getBaselineQualityOfLifeDate() != null) {
                selectedParticipant.setShowStudyArm(true);
                pm.setShowArm(selectedParticipant.getParticipantID(), true);
                nextPage = "/armAssignmentResults.xhtml?faces-redirect=true";
                } else {
                    // Don't allow form to go into non-editable SUBMITTED status if study arm isn't revealed yet
                    selectedForm.setStatus(FormStatus.STARTED);
                    pm.saveForm(selectedForm);
                    am.fire(Action.SAVE_FORM);
                }
            }
            
            return nextPage;
        }
        else if( ECPFormTypes.DEMOGRAPHICS.equals(selectedForm.getFormType())) {
            
            selectedForm.setStatus( fstv.getNewStatus());
            DemoMedHistForm f = (DemoMedHistForm) selectedForm;
            // Set the form date to be the same as the enrollment date.
            if(f.getEnrollmentDate() != null){
                f.setDate(f.getEnrollmentDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            System.out.println("The selected form is: " + selectedForm.getTitle() + "\n\n");

            if( selectedEvent.getName().equals("Demographics/Medical History")) {
                System.out.println("The selectedEvent is Demographics \n");
                updateEventSchedule( selectedParticipant, f.getEnrollmentDate(), selectedEvent);
            }
            
            return "/participant.xhtml?faces-redirect=true";
        }
        else if( ECPFormTypes.BASELINE_THERAPY.equals(selectedForm.getFormType())) {
            
            selectedForm.setStatus( fstv.getNewStatus());
            BaselineTherapyForm f = (BaselineTherapyForm) selectedForm;
            // Set the form date to be the same as the baseline therapy date.
            if(f.getBaselineTherapyDate() != null){
                f.setDate(f.getBaselineTherapyDate());
            }
            pm.saveForm(f);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            
            System.out.println("The selected form is BaselineTherapyForm \n");
            updateEventSchedule( selectedParticipant, f.getBaselineTherapyDate(), selectedEvent);
            
            // If participant is in control arm, update all events based on bsaeline therapy date
            if (selectedParticipant.getStudyArmStatus() == StudyArmStatus.CONTROL_ARM) {
                updateEventSchedule( selectedParticipant, f.getBaselineTherapyDate());
            }
            
            return "/participant.xhtml?faces-redirect=true";
        }   
        else if( ECPFormTypes.ECP_TREATMENT.equals(selectedForm.getFormType())) {
            
            selectedForm.setStatus( fstv.getNewStatus());
            ECPTreatmentForm f = (ECPTreatmentForm) selectedForm;
            // Set the form date to be the same as the visit date.
            if(f.getEcpTreatVisitDate() != null){
                f.setDate(f.getEcpTreatVisitDate());
            }
            pm.saveForm(f);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            
            // The updateEventSchedule method below updates all (24 events) of the events if the form type is ECP Treatment 1
            // Else it updateEventSchedule method updates only a single event.
            if( selectedEvent.getName().equals("ECP Treatment 1")) {
                System.out.println("\n the EVENT name is ECP Treatment 1 so update all events\n");
                updateEventSchedule( selectedParticipant, f.getEcpTreatVisitDate()); // Update all of the events.
                updateEventSchedule( selectedParticipant, f.getEcpTreatVisitDate(), selectedEvent);// Update only a single selected event.
            } else {
                System.out.println("\n The EVENT name IS NOT ECP Treatment 1 so update a SINGLE EVENT\n");
                updateEventSchedule( selectedParticipant, f.getEcpTreatVisitDate(), selectedEvent);// Update only a single selected event.
            } 
            
            return "/participant.xhtml?faces-redirect=true";
        }   
        else if( ECPFormTypes.PULMONARY_EVAL.equals(selectedForm.getFormType())) {
            
            selectedForm.setStatus( fstv.getNewStatus());
            PulmEvalForm f = (PulmEvalForm) selectedForm;
            // Set the form date to be the same as the testing date.
            if(f.getPulmonaryFunctionTestingDate() != null){
                f.setDate(f.getPulmonaryFunctionTestingDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            
            System.out.println("The selected form is Pulmonary Evaluation \n");
            updateEventSchedule( selectedParticipant, f.getPulmonaryFunctionTestingDate(), selectedEvent);
            
            // The following code is for participants who are in the Observational Arm of the study.
            // Write the Pulmonary Function Test date, FEV1, and FVC into the Observational Pulmonary Evaluation Log
            // when the new Pulmonary Function Test form has been DCC verified.  Only verified pulmonary function
            // evaluations should be put into the Observational Pulmonary Evaluation Log to reduce FEV1 errors in data entry.
            
            /*
            if(selectedParticipant.getStudyArmStatus().equals(StudyArmStatus.CONTROL_ARM)){  // Participant must be in the Observational Arm to write pulmonary function test (PFT) to Log
                if(FormStatus.DCC_VERIFIED.equals(selectedForm.getStatus())) {  // The pulmonary function test (PFT) must be DCC_VERIFIED to make sure all of the entries in the PFT are correct before writing to the log.
                    List<Event> eventsList = pm.getEvents(selectedParticipant);  // Get the list of events for the participant since there isn't currently any software written to get the form directly from the participant.
                    Event e = getEventBasedOnType(eventsList, ECPEventTypes.OBSERVATION_PULMONARY_EVAL_LOG); // From the list of events, get the Log event.
                    List<BasicForm> forms = pm.getForms(e);  // Get the forms based on the Observational Log Event. Currently, only one form is returned for most all events except for SAEs.
                    BasicForm bf = forms.get(0);  // Get the Observational Log form.  Currently, only one form (Observational Log) is returned in the list of forms.
                    observePulmEvalLogForm = pm.getObservePulmEvalLogForm(bf);  // Get the Observation Pulmonary Evaluation Log Form.
                    int currentNumberOfFev1s = observePulmEvalLogForm.getNumberOfObservationPulmEvaluations(); // Count the number of FEV1s that have been entered into the Observational Log form.
                    System.out.println("The number of observational pulmonary evaluations is: " + currentNumberOfFev1s + "\n");
                    observePulmEvalLogForm.writePulmFuncTestValuesIntoLog(selectedForm, currentNumberOfFev1s);  // Write the PFT Date, FEV1 and FVC from the selectedForm into the Observational Log Form.
                    if(observePulmEvalLogForm.getStatus().equals(FormStatus.NEW)){
                        observePulmEvalLogForm.setStatus(FormStatus.STARTED);
                    }
                    pm.saveForm(observePulmEvalLogForm);  // Write the Observational Log Form into the database.
                    if(e.getStatus().equals(EventStatus.NEW)){
                        e.setStatus(EventStatus.STARTED);
                        updateEventStatus( e);
                    }
                }
            }
            */
            
            return "/participant.xhtml?faces-redirect=true";
        }   
        else if( ECPFormTypes.ADVERSE_EVENT_WORKSHEET.equals(selectedForm.getFormType())) {
            
            selectedForm.setStatus( fstv.getNewStatus());
            AdverseEventWorksheetSAEForm f = (AdverseEventWorksheetSAEForm) selectedForm;
            // Set the form date to be the same as the current date.
            if(f.getCurrentDate() != null){
                f.setDate(f.getCurrentDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.

            System.out.println("The selected form is Adverse Event Worksheet SAE \n");
            updateEventSchedule( selectedParticipant, f.getCurrentDate(), selectedEvent);
            
            return "/participant.xhtml?faces-redirect=true";
        }   
        else if( ECPFormTypes.SERIOUS_ADVERSE_EVENT.equals(selectedForm.getFormType())) {
            
            selectedForm.setStatus( fstv.getNewStatus());
            AdverseEventWorksheetSAEForm f = (AdverseEventWorksheetSAEForm) selectedForm;
            // Set the form date to be the same as the current date.
            if(f.getCurrentDate() != null){
                f.setDate(f.getCurrentDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.

            System.out.println("The selected form is Adverse Event Worksheet SAE \n");
            // Suresh agreed the Event Date should be the same as the SAE Onset date, but the Onset date may not be known initially.
            // So Dave M. and I (Paul K. Commean) agreed to use the Current date as the Event date until the Onset date is known.
            if(f.getOnsetDate() != null){
                updateEventSchedule( selectedParticipant, f.getOnsetDate(), selectedEvent);
            } else if(f.getTitle().equals("SAE Form - 1")){  // If the onset date is not known, you only want the current date to be set into the Event date for the first SAE form.
                updateEventSchedule( selectedParticipant, f.getCurrentDate(), selectedEvent);
            }
            updateEventTitle( selectedEvent, f.getAeTitle());
            
            return "/participant.xhtml?faces-redirect=true";
        }   
        else if( ECPFormTypes.CHANGE_THERAPY.equals(selectedForm.getFormType())) {
            
            selectedForm.setStatus( fstv.getNewStatus());
            ChangeTherapyForm f = (ChangeTherapyForm) selectedForm;
            // Set the form date to be the same as the change of therapy date.
            if(f.getChangeTherapyDate() != null){
                f.setDate(f.getChangeTherapyDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            
            System.out.println("The selected form is ChangeTherapyForm \n");
            updateEventSchedule( selectedParticipant, f.getChangeTherapyDate(), selectedEvent);
            
            return "/participant.xhtml?faces-redirect=true";
        }   
        else if( ECPFormTypes.END_OF_STUDY.equals(selectedForm.getFormType())) {
//            System.out.println("submitFormAction of End of Study " + selectedForm.getType() + "\n");
            selectedForm.setStatus( fstv.getNewStatus());
            EndOfStudyForm f = (EndOfStudyForm) selectedForm;
            // Set the form date to be the same as the termination date.
            if(f.getTerminationDate() != null){
                f.setDate(f.getTerminationDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.

            if( selectedEvent.getName().equals("End Of Study")) {
                System.out.println("The selected form is End Of Study \n");
                updateEventSchedule( selectedParticipant, f.getTerminationDate(), selectedEvent);
            }
            
            return "/participant.xhtml?faces-redirect=true";
        }
        else if( ECPFormTypes.QUALITY_OF_LIFE.equals(selectedForm.getFormType())) {
//            System.out.println("submitFormAction of End of Study " + selectedForm.getType() + "\n");
            selectedForm.setStatus( fstv.getNewStatus());
            QualityOfLifeForm f = (QualityOfLifeForm) selectedForm;
            // Set the form date to be the same as the quality of life date.
            if(f.getQualityOfLifeDate() != null){
                f.setDate(f.getQualityOfLifeDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.

            System.out.println("The selected form is QualityOfLifeForm \n");
            updateEventSchedule( selectedParticipant, f.getQualityOfLifeDate(), selectedEvent);
            
            return "/participant.xhtml?faces-redirect=true";
        }
        else if( ECPFormTypes.ANNUAL_FOLLOW_UP.equals(selectedForm.getFormType())) {
//            System.out.println("submitFormAction of Annual Follow-Up Form " + selectedForm.getType() + "\n");
            selectedForm.setStatus( fstv.getNewStatus());
            AnnualFollowUpForm f = (AnnualFollowUpForm) selectedForm;
            // Set the form date to be the same as the Annual Follow-Up Form date.
            if(f.getAnnualFollowUpDate() != null){
                f.setDate(f.getAnnualFollowUpDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.

            System.out.println("The selected form is AnnualFollowUpForm \n");
            updateEventSchedule( selectedParticipant, f.getAnnualFollowUpDate(), selectedEvent);
            
            return "/participant.xhtml?faces-redirect=true";
        }
        else if( ECPFormTypes.HOSPITALIZATION.equals(selectedForm.getFormType())) {
//            System.out.println("submitFormAction of End of Study " + selectedForm.getType() + "\n");
            selectedForm.setStatus( fstv.getNewStatus());
            HospitalizationForm f = (HospitalizationForm) selectedForm;
            // Set the form date to be the same as the admission date.
            if(f.getAdmissionEDDate() != null){
                f.setDate(f.getAdmissionEDDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            
            System.out.println("The selected form is HospitalizationForm \n");
            updateEventSchedule( selectedParticipant, f.getAdmissionEDDate(), selectedEvent);
            
            return "/participant.xhtml?faces-redirect=true";
        }   
        else if( ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION.equals(selectedForm.getFormType())) {
//            System.out.println("submitFormAction of End of Study " + selectedForm.getType() + "\n");
            selectedForm.setStatus( fstv.getNewStatus());
            DSCForm f = (DSCForm) selectedForm;
            // Set the form date to be the same as the admission date.
            if(f.getDscDate() != null){
                f.setDate(f.getDscDate());
            }
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.
            
            System.out.println("The selected form is DSCForm \n");
            
            return "/participant.xhtml?faces-redirect=true";
        } 
        else if( ECPFormTypes.CROSSOVER_SAFETY_CHECK.equals(selectedForm.getFormType())) { 
            
            String currentStatus = fstv.getOldStatus().toString();
            
            selectedForm.setStatus( fstv.getNewStatus());
            pm.saveForm(selectedForm);
            am.fire(Action.SUBMIT_FORM);  // am.fire needs to be run before the updateEventSchedule because it sets the basedate to null and does not update the actual data.

            // If the patient has a YES answer to the contraindication to ECP Treatment question, do not allow enrollment into the ECP Treatment Arm.
            for( Attribute a: selectedForm.getAttributes()) {
                if( a.isPersistent() && a.getValue() != null && a.getName().equals("safeToCrossover") ) {
                    String s = a.getValue().toString();  // Crossover attribute value should be either true for YES or false for NO.
                    System.out.println("The crossover attribute value is: " + s + "/n");
                    if(s.equals("true")) {  // Do not allow crossover into the ECP Treatment Arm if the answer to the question is YES.
                        return "/crossoverSafetyResults.xhtml?faces-redirect=true";
                    }
                }
            }

            // When the form is created for the first time and submitted, send out an email and crossover the participant to their new arm
            if(currentStatus.equals("NEW") || currentStatus.equals("STARTED")) {
                crossoverParticipant();
                
                // Send out email to CCC staff to let them know a participant has crossed over.
                // This email needs to be sent out after the crossoverParticipant() method is run in order to update the StudyArm status in the
                // selectedParticipant variable.
                NotificationManager nm = ServiceRegistry.getNotificationManager();
                NotificationContent content = new NotificationContent();
                content.setSiteName( selectedSite.getName());
                content.setParticipantId( selectedParticipant.getParticipantID());
                content.setStudyArm(selectedParticipant.getStudyArmStatus().getName());
                content.setUserName(getCurrentUserName());
                nm.send( NotificationType.PARTICIPANT_CROSSED_OVER, content);

            }

            CrossoverSafetyCheckForm f = (CrossoverSafetyCheckForm) selectedForm;
            System.out.println("The selected form is " + f.getType());
            updateEventSchedule( selectedParticipant, f.getDate(), selectedEvent);

            return "/participant.xhtml?faces-redirect=true";
        }   
        else {
            return null;
        }
    }
    
    // Get an event based on the type of event requested.
    public Event getEventBasedOnType(List<Event> events, ECPEventTypes eType){
        Event e = null;
        Iterator iterator = events.iterator();
        while(iterator.hasNext()){
            e = (Event) iterator.next();
            if( eType.equals(e.getType())){
                return e;
            }
        }
        return e;
    }
    
    // Update schedule for CRFs based on Enrollment date
    // The Quality of Life and Change in Therapy event schedule is based on the enrollment date not the first ECP Treatment date
    public void updateEnrollmentEventSchedule( Participant p, Date d) throws PersistenceException {  
      List<ECPEventTypes> fromEnrollmentEventTypes = Arrays.asList(ECPEventTypes.QUALITY_OF_LIFE, ECPEventTypes.PULMONARY_EVAL, ECPEventTypes.CHANGE_THERAPY);
        
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        List<Event> evs = pm.getEvents(p);

        for( Event e: evs) {
            // Quality of life offset is calculated based on enrollment date
            if( fromEnrollmentEventTypes.contains(e.getType())) {
                e.setBaseDate(d);
            }
        }
        
        pm.updateEvents( evs);
    }
    
    // Update most of the subject's events in the Event database table based on the first ECP Treatment date with some exceptions.
    public void updateEventSchedule( Participant p, Date d) throws PersistenceException {
        List<ECPEventTypes> fromEnrollmentEventTypes = Arrays.asList(ECPEventTypes.QUALITY_OF_LIFE, ECPEventTypes.PULMONARY_EVAL, ECPEventTypes.CHANGE_THERAPY);
        
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        List<Event> evs = pm.getEvents(p);

        for( Event e: evs) {
            // Exclude updating the Quality of life and Change in Therapy
            // Quality of life and Change in Therapy offsets are calculated based on enrollment date, not first treatment date
            // Exclude updating the End of Study CRF because it does not have a scheduled/projected date
            if( e.isExpected() && (!fromEnrollmentEventTypes.contains(e.getType())) && (e.getType() != ECPEventTypes.END_OF_STUDY)) {
                e.setBaseDate(d);
            }
        }
        
        pm.updateEvents( evs);
    }
    
    // Update only one event in the Event database table.
    public void updateEventSchedule( Participant p, Date d, Event event) throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        List<Event> evs = pm.getEvents(p);
        System.out.println("In the controller.updateEventSchedule method \n");
        System.out.println("controller.updateEventSchedule method with event name of: " + event.getName());
        System.out.println("controller.updateEventSchedule method with event id of: " + event.getId());
        System.out.println("controller.updateEventSchedule method with event basedate of: " + event.getBaseDate());
        System.out.println("controller.updateEventSchedule method with event actualdate of: " + event.getActualDate() + "\n\n");
        int i = 0;

        for( Event e: evs) {
            System.out.println("The updateEventSchedule i value is: " + i);
//            System.out.println("The event isExpected value is: " + e.isExpected() + "\n");
            System.out.println("The e name is: " + e.getName() + "\n");
            System.out.println("The evs event name is: " + evs.get(i).getName());
//            if( e.isExpected() && event.getName().equals(e.getName())) {
            if( event.getName().equals(e.getName())) {
                e.setActualDate(d);
                evs.set(i, e);
                System.out.println("The actual date for EVS is: " + evs.get(i).getActualDate() + "\n");
                System.out.println("The actual date for e is: " + e.getActualDate() + "\n");
                System.out.println("The e name is: " + e.getName() + "\n");
                pm.updateEvent( e);
            }
            i++;
        }
        
//        pm.updateEvents( evs);
    }
    
    public void updateEventStatus( Event e) throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        
        List<BasicForm> forms = pm.getForms(e);
        EventStatus status;
        FormStatus lowestState = FormStatus.PI_APPROVED;
        for( BasicForm f: forms) {
            if( f.getStatus().getPrecedence() < lowestState.getPrecedence()) {
                lowestState = f.getStatus();
            }
        }
        switch (lowestState) {
            case NEW:
                status = EventStatus.NEW;
                break;
            case STARTED:
                status = EventStatus.STARTED;
                break;
            case SUBMITTED:
                status = EventStatus.SUBMITTED;
                break;
            case CRF_QUERY:
                status = EventStatus.CRF_QUERY;
                break;
            case DCC_VERIFIED:
                status = EventStatus.DCC_VERIFIED;
                break;
            case PI_APPROVED:
                status = EventStatus.PI_APPROVED;
                break;
            case NOT_REQUIRED:
                status = EventStatus.NOT_REQUIRED;
                break;
            case MISSED_VISIT:
                status = EventStatus.MISSED_VISIT;
                break;
            case UNKNOWN:
            default:
                status = EventStatus.UNKNOWN;
                break;
        }
        e.setStatus(status);
        System.out.println("The EventStatus is: " + e.getStatus());
        // I commented out the following method because it was causing the basedate and actual date
        // in the database table named events to get set to null.
//        pm.updateEvent(e);
        pm.updateEventStatus(e);
    }
    
    protected void updateEventTitle( Event e, String title) throws PersistenceException {
        if( ! e.getName().equals(title)) {
            e.setName(title);
            
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
            pm.updateEvent(e);
        }
    }   
    
    public String clearFormAction() {
        return null;
    }
    
    public void enrollParticipant() throws PersistenceException {
        List<Event> newEvents = new ArrayList<Event>();
        selectedParticipant.setStatus(ParticipantStatus.ACTIVE);
        System.out.println("\nThe STUDY ARM STATUS is: " + selectedParticipant.getStudyArmStatus().getName() + "\n\n");
        if(selectedParticipant.getStudyArmStatus().equals(StudyArmStatus.EPI_ARM)) {
            newEvents = ECPEvents.getEPIScheduledEvents(selectedParticipant);
        } else if(selectedParticipant.getStudyArmStatus().equals(StudyArmStatus.CONTROL_ARM)){
            newEvents = ECPEvents.getControlScheduledEvents(selectedParticipant);
                   }

        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        pm.enrollParticipant( selectedParticipant, newEvents, selectedSite);
    }
    
    public void addCrossoverEvent() throws PersistenceException, NotificationException {
        // Create the Crossover Safety Check Event and Form
        Event e = new Event();
        e.setType(ECPEventTypes.CROSSOVER_SAFETY_CHECK);
        e.setStatus(EventStatus.NEW);
        e.setExpected(false);
        e.setActualDate(new Date());
        List<BasicForm> crossoverForms = ECPEvents.getForms(ECPEventTypes.CROSSOVER_SAFETY_CHECK);
        // Load the CrossoverSafetyCheckForm with the dates, FEV1 and FVC values from the CrossoverEligibilityWorkSheet.
        // The datas, FEV1 and FVC values in the CrossoverEligibilityWorkSheet were used to calculate the crossover eligibility for the participant.
        for( BasicForm bf: crossoverForms){
            CrossoverEligibilityWorkSheet cews = this.getCrossoverEligibilityWorkSheet();
            CrossoverSafetyCheckForm cscf = (CrossoverSafetyCheckForm) bf;
            cscf.setPulmonaryEvaluationValues(cews);
            cscf.setSlope(cews.getSlope());
            cscf.setSignificance(cews.getSignificance());
        }
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        int i = pm.getEventTypeCount(selectedParticipant, e);
        String name = ECPEventTypes.CROSSOVER_SAFETY_CHECK.getName() + " " + ++i;
        e.setName(name);
        pm.addEvent(selectedParticipant, e, crossoverForms, selectedSite);
        selectedEvent = e;
    }
    
    public void crossoverParticipant() throws PersistenceException {
        List<Event> newEvents = new ArrayList<Event>();
        selectedParticipant.setStatus(ParticipantStatus.ACTIVE);
        
        if (selectedParticipant.getStudyArmStatus() == StudyArmStatus.CONTROL_ARM) {
            selectedParticipant.setStudyArmStatus(StudyArmStatus.CONTROL_PLUS_EPI);
            newEvents = ECPEvents.getCrossoverScheduledTreatmentEvents(selectedParticipant.getId());
        } else if (selectedParticipant.getStudyArmStatus() == StudyArmStatus.EPI_ARM) {
            selectedParticipant.setStudyArmStatus(StudyArmStatus.EPI_PLUS_CONTROL);
            // Control just uses change in therapy forms, so doesn't need additional forms to be created
        }

        selectedParticipant.setOverrideDate( new Date());
        
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        pm.enrollParticipant( selectedParticipant, newEvents, selectedSite);
    }
    
    public String crossoverParticipantAction() throws PersistenceException, NotificationException {
        Event e = ECPEvents.getInstance(ECPEventTypes.CROSSOVER_SAFETY_CHECK);
        Date dateOfOccurance = new Date();
        e.setActualDate( dateOfOccurance);
        System.out.println("The Event Title is: " + e.getName());
        e.setName( "Crossover to New Arm.");
        
        return insertEventAction( e);
    }
    
    public String cancelEnrollParticipantAction(EnrollmentBean eb) {
        return "/site.xhtml?faces-redirect=true";
    }
    
    public String acknowledgeIneligibleParticipantAction(EnrollmentBean eb) {
        return "/site.xhtml?faces-redirect=true";
    }
    
//    public void fileUploadListener( FileUploadEvent event) {
//        FacesMessage msg = null;
// 
//        OutputStream out = null;
//        InputStream in = null;
//
//        String fileName = event.getFile().getFileName();
//        try {
//            in = event.getFile().getInputstream();
//            out = new FileOutputStream(new File("/tmp/foo/" + fileName));
//
//            IOUtils.copy(in, out);
//
//            in.close();
//            out.flush();
//            out.close();
//
//            msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        } catch (IOException e) {
//            msg = new FacesMessage("Error", event.getFile().getFileName() + " failed to upload: " + e.getMessage());
//        } finally {
//            IOUtils.closeQuietly(in);
//            IOUtils.closeQuietly(out);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
    
//    public void fileUploadListener( FileUploadEvent event) {
//        FacesMessage msg = null;
// 
//        InputStream in = null;
//
//        String fileName = event.getFile().getFileName();
//        try {
//            in = event.getFile().getInputstream();
//
//            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
//            int documentId = pm.saveDocument(selectedForm, fileName, in);
//
//            in.close();
//
//            msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        } catch (Exception e) {
//            msg = new FacesMessage("Error", event.getFile().getFileName() + " failed to upload: " + e.getMessage());
//        } finally {
//            IOUtils.closeQuietly(in);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
    
    public void uploadFileAction() {
        FacesMessage msg = null;
 
        InputStream in = null;

        String fileName = null;
        try {
            // all we would have to do is call sourceDocument.getSubmittedFileName(), if
            // we were using Servlet 3.1 instead of 3.0. 
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            fileName = getFilename( request);
            
            in = sourceDocument.getInputStream();

            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
            int documentId = pm.saveDocument(selectedForm, fileName, in, selectedForm.getSourceDocumentType());

            in.close();

            msg = new FacesMessage("Successful", fileName + " is uploaded.");
        } catch (Exception e) {
            msg = new FacesMessage("Error", fileName + " failed to upload: " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
      
    /*
     * based on code from http://balusc.blogspot.com/2009/12/uploading-files-in-servlet-30.html
    */
    private String getFilename( HttpServletRequest request) throws IOException, IllegalStateException, ServletException {
        String fname = null;
        for( Part part: request.getParts()) {
            fname = getFilename(part);
            if( fname != null) break;
        }
        return (fname == null)? "null": fname;
    }
    
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String CONTENT_DISPOSITION_FILENAME = "filename";
    /**
     * Returns the filename from the content-disposition header of the given part.
     */
    private String getFilename(Part part) {
        final String partHeader = part.getHeader("content-disposition");
//        System.out.println("######## in controller.getFilename the partHeader is: " + partHeader + "\n\n");
        for (String cd : part.getHeader(CONTENT_DISPOSITION).split(";")) {
            if (cd.trim().startsWith(CONTENT_DISPOSITION_FILENAME)) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public String viewDocumentAction( Document doc) throws IOException, PersistenceException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        FileNameMap fnm = URLConnection.getFileNameMap();
        String fileType = fnm.getContentTypeFor(doc.getName().replace('#', '-'));
        logger.debug("The file type that is being viewed is: " + fileType + "\n\n");
        // Set response headers
        if("application/msword".equals(fileType)){
            fileType = "application/msword";
        } else if("image/tiff".equals(fileType)){
            fileType = "image/tiff";
        } else if("image/jpeg".equals(fileType)){
            fileType = "image/jpeg";
        }
        response.reset();
        response.setHeader("Content-Type", fileType);
//        response.setHeader("Content-Type", "text");
//        response.setHeader("Content-Type", "application/pdf");
//        response.setHeader("Content-Length", "application/pdf");
        
        OutputStream ros = response.getOutputStream();
        
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        pm.writeDocument(doc.getId(), ros);
                 
        // Make sure that everything is out
        ros.flush();
          
        ros.close();
         
        // JSF doc:
        // Signal the JavaServer Faces implementation that the HTTP response for this request has already been generated
        // (such as an HTTP redirect), and that the request processing lifecycle should be terminated
        // as soon as the current phase is completed.
        facesContext.responseComplete();
        
        return null;
    }

    public String deleteDocumentAction( Document doc) throws IOException, PersistenceException {
        
//        System.out.println("The doc name in the deleteDocumentAction( Document doc) is: " + doc.getName() + "\n");
//        System.out.println("The doc id in the deleteDocumentAction( Document doc) is: " + doc.getId() + "\n");

        this.document = doc;
        
//        System.out.println("The document name in the deleteDocumentAction( Document doc) is: " + document.getName() + "\n");
        
//        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
//        pm.deleteDocument(doc.getId());
        
        return null;
    }
    
    public String deleteDocumentAction() throws IOException, PersistenceException {
        
//        System.out.println("The document name in the deleteDocumentAction() is: " + document.getName() + "\n");
//        System.out.println("The document id in the deleteDocumentAction() is: " + document.getId() + "\n");
        
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        pm.deleteDocument(document.getId());
        
        return null;
    }
    
    public boolean isRenderSourceDocumentDeleteButton() throws PersistenceException, ActionException {
        System.out.println("The isRenderSourceDocumentDeleteButton method was run \n");
        if( FormStatus.NEW.equals(selectedForm.getStatus())) {
            return true;
        } else if( FormStatus.STARTED.equals(selectedForm.getStatus())) {
            return true;
        } else if( SecurityManager.canVerifyForms()) {
            return true;
        }
        return false;
    }

    public boolean isReadOnlyForm() {
        boolean authenticated = SecurityUtils.getSubject().isAuthenticated();
        return authenticated;
    }
    
    public int getEventCount( Participant p, EventStatus status) throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        return pm.getEventCount(p, status);
    }
    
    public int getSiteEventCount( Site s, EventStatus status) throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        return pm.getEventCount(s, status);
    }
    
    public EventStatus[] getEventStatus() {
        EventStatus[] stat = EventStatus.values();
        stat = ArrayUtils.remove(stat, 7);
        return stat;
    }
    
    public void homeWordPressPageAction() throws IOException{
        System.out.println("The home page button was selected directing you to ECP Registry WordPress home page!!");

        // The following two lines of code will log you out of the Investigator's Resource website.
//        SecurityManager sm = new SecurityManager();
//        sm.logoutAction();
        
        // Stackoverflow http://stackoverflow.com/questions/5092439/jsf-external-site-redirect with BalusC answer helped
        // with the following two lines of code.
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect("http://ecpregdev.wustl.edu/wordpress/");  // URL for ECP Test Server  http://128.252.175.92/wordpress/
//        ec.redirect("http://ecpregtraining.wustl.edu/wordpress/"); // URL for ECP Training Server   http://128.252.175.94/wordpress/
//        ec.redirect("http://ecpregistry.wustl.edu/wordpress/"); // URL for ECP Production Server   http://128.252.175.93/wordpress/
        ec.redirect("/");  // URL for ECP Registry Home Page on the WordPress Server
    }
    
    /**
     * Indicate if the attribute verification panel for the named attribute on 
     * the selectedForm should be rendered.
     * 
     * True if the attribute is questioned or the user has verification privileges.
     * 
     * @param attributeName
     * @return 
     */
    public boolean renderVerificationPanel( String attributeName) {
        //System.out.println("Attribute name is: " + attributeName);

        return (selectedForm.isAttributeQuestioned(attributeName) || (SecurityManager.canVerifyForms() && selectedForm.isAttributeVerifiable(attributeName)) );
    }
    
    /**
     * Indicate if the Add Form button should be rendered on the event page.
     * 
     * True if the selected event is a type with its unscheduledFormsAllowed flag set.
     * 
     * @return 
     */
    public boolean renderAddFormButton() {
        // The following line of code is called a ternary operator or conditional operator.
        return (selectedEvent == null)? false: (selectedEvent.getType().isUnscheduledFormAllowed());
    }
    
    /**
     * Indicate if the Save Form button should be rendered on the Form page.
     * 
     * True if the form status is not 'SUBMITTED' or 'DCC_VERIFIED' and a study coordinator is logged in.
     * True also if the person logged on can verify forms.
     * 
     * @return 
     */
    public boolean isRenderSaveFormButton() {
//        fstv.setForm(selectedForm);
//        String currentStatus = fstv.getOldStatus().toString();
//        // Do not allow a study coordinator to see the Save button if they have already submitted the crossover safety check form
//        // or the status is DCC_VERIFIED.  Only the PI should be able to change the status from DCC_VERIFIED to PI_APPROVED.
//        if(((!currentStatus.equals("DCC_VERIFIED") && !(SecurityManager.canVerifyForms())) || SecurityManager.canVerifyForms())) {
//            if(((!currentStatus.equals("SUBMITTED") && !(SecurityManager.canVerifyForms())) || SecurityManager.canVerifyForms())) {
//                return true;
//            }
//        }
//        return false;
        
        FormStatus formStatus = selectedForm.getStatus();
        // Do not allow a study coordinator to see the Save/Submit buttons if they have already submitted the SUBMITTED a form
        // or the status is DCC_VERIFIED.  The technical coordinator should not be able to change the status of a form from 
        // DCC_VERIFIED to PI_APPROVED.  Only the PI should be able to change the status from DCC_VERIFIED to PI_APPROVED.
        // The SecurityManager is the technical coordinator for now.
        if( SecurityManager.canVerifyForms() && !FormStatus.DCC_VERIFIED.equals(formStatus)) {
            return true;
        }
        // Currently as of 2/13/2015, there are only 2 roles (study coordinator and techinical coordinator) the !SecurityManager.canVerifyForms()
        // means the the role must be a study coordinator.  The SecurityManager is the technical coordinator for now.
        if( !FormStatus.DCC_VERIFIED.equals(formStatus) && !FormStatus.SUBMITTED.equals(formStatus) && !SecurityManager.canVerifyForms()) {
            return true;
        }
        return false;
    }

    /**
     * Indicate if the Submit Form button should be rendered on the Form page.
     * 
     * True if the form status is not 'SUBMITTED' or 'DCC_VERIFIED' and a study coordinator is logged in.
     * True also if the person logged on can verify forms.
     * 
     * @return 
     */
    public boolean isRenderSubmitFormButton() {
//        fstv.setForm(selectedForm);
//        String currentStatus = fstv.getOldStatus().toString();
//        // Do not allow a study coordinator to see the Save button if they have already submitted the crossover safety check form
//        // or the status is DCC_VERIFIED.  Only the PI should be able to change the status from DCC_VERIFIED to PI_APPROVED.
//        if(((!currentStatus.equals("DCC_VERIFIED") && !(SecurityManager.canVerifyForms())) || SecurityManager.canVerifyForms())) {
//            if(((!currentStatus.equals("SUBMITTED") && !(SecurityManager.canVerifyForms())) || SecurityManager.canVerifyForms())) {
//                return true;
//            }
//        }
//        return false;
        
        FormStatus formStatus = selectedForm.getStatus();
        // Do not allow a study coordinator to see the Save/Submit buttons if they have already submitted the SUBMITTED a form
        // or the status is DCC_VERIFIED.  The technical coordinator should not be able to change the status of a form from 
        // DCC_VERIFIED to PI_APPROVED.  Only the PI should be able to change the status from DCC_VERIFIED to PI_APPROVED.
        // The SecurityManager is the technical coordinator for now.
        if( SecurityManager.canVerifyForms() && !FormStatus.DCC_VERIFIED.equals(formStatus)) {
            return true;
        }
        // Currently as of 2/13/2015, there are only 2 roles (study coordinator and techinical coordinator) the !SecurityManager.canVerifyForms()
        // means the the role must be a study coordinator.  The SecurityManager is the technical coordinator for now.
        if( !FormStatus.DCC_VERIFIED.equals(formStatus) && !FormStatus.SUBMITTED.equals(formStatus) && !SecurityManager.canVerifyForms()) {
            return true;
        }
        return false;
    }

    /**
     * Indicate if the ECP Report Menu items can be rendered if you are a technical coordinator.
     * 
     * True if you are logged in as a technical coordinator.
     * 
     * @return 
     */
    public boolean renderECPReportMenuItems() { 
        return SecurityManager.canVerifyForms();
    }
    
    /**
     * Indicate if the IRB Version 4.0 Menu items can be rendered if the site has approved version 4.0.
     * 
     * True if the IRB Version in the site table is 4.0.
     * 
     * @return
     */
    public boolean renderIRBVersion4CRFsFromMenuItems() {    
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("4.0");
        } else {
            return false;
        }
    }
    
    /**
     * Indicate if the IRB Version 5.0 Menu items can be rendered if the site has approved version 5.0.
     * 
     * True if the IRB Version in the site table is 5.0.
     * 
     * @return 
     */
    public boolean renderIRBVersion5CRFsFromMenuItems() {    
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("5.0");
        } else {
            return false;
        }
    }
    
    /**
     * Indicate if the IRB Version 7.0 Menu items can be rendered if the site has approved version 7.0.
     * 
     * True if the IRB Version in the site table is 7.0.
     * 
     * @return 
     */
    public boolean renderIRBVersion7CRFsFromMenuItems() {    
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("7.0");
        } else {
            return false;
        }
    }
    
    /**
     * Indicate if the IRB Version 7.1 Menu items can be rendered if the site has approved version 7.1.
     * 
     * True if the IRB Version in the site table is 7.1.
     * 
     * @return 
     */
    public boolean renderIRBVersion7_1CRFsFromMenuItems() {    
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("7.1");
        } else {
            return false;
        }
    }

    /**
     * Control display of V5 protocol
     * 
     * True if the IRB Version in the site table is 5.0.
     * 
     * @return 
     */
    public boolean renderV5Docs() {
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("5.0");
        } else {
            return false;
        }
    }
    
    /**
     * Control display of V7 protocol
     * 
     * True if the IRB Version in the site table is 7.0.
     * 
     * @return 
     */
    public boolean renderV7Docs() {
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("7.0");
        } else {
            return false;
        }
    }
    
    /**
     * Control display of V7.1 protocol
     * 
     * True if the IRB Version in the site table is 7.1.
     * 
     * @return 
     */
    public boolean renderV7_1Docs() {
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("7.1");
        } else {
            return false;
        }
    }

    
    /**
     * Indicate if the site can enroll patients
     * 
     * True if the IRB Version in the site table is 7.1.
     * 
     * @return 
     */
    public boolean renderEnrollmentButton() {
        if(selectedSite != null){
//            System.out.println("The selected site is: " + selectedSite.getName() + "\n\n");
            return selectedSite.getIrbVersion().equals("7.1");
        } else {
            return false;
        }
    }
    
    // disadvantage is that it is run everytime the form is submitted but
    // advantage that the error doesn't go away until the user fixes it.
    public void validateAge( FacesContext context, UIComponent component, Object value) throws ValidatorException {
        int age = (int) value;
//        try {
//            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
//            Event e = pm.getEvent( pid, title);
        if(Integer.toString(age) == null){
            System.out.println("No data was found in the text box!! \n\n");
        } else {
            System.out.println("The following value was found in the text box: " + age + "\n\n");
        }
//            if( e != null) {
//                throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "This title is already in use."));
//           }
//        }
//        catch( PersistenceException e) {
//            throw new ValidatorException( new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", "Unable to validate event title: " + e.getMessage() + "."));
//        }
    }
    
    
    
    public void clearChangeTherapyDrugDosageIfNoListener(AjaxBehaviorEvent event) {
        ChangeTherapyForm ctForm = (ChangeTherapyForm) getSelectedForm();
        String componentID = event.getComponent().getId();
        System.out.println("The controller.clearChangeTherapyDrugDosageIfNoListener is being executed!!!");
        System.out.println("Print the component type (ie. SelectOneRadio): " + event.getComponent().getClass().getName());
        System.out.println("The component ID is: " + event.getComponent().getId());
        String componentValue = (String) ((UIOutput)event.getSource()).getValue();
        System.out.println("The value of the component is: " + componentValue + "\n");
        System.out.println("The value of the Tacrolimus component is: ++" + ctForm.getTacrolimusCurrent() + "++\n");
        
        switch(componentID){
            case "tacrolimus":  if(componentValue.equals("false")){
                                    ctForm.setTacrolimusCurrentDosage("");
                                }
                                break;
            case "prednisone":  if(componentValue.equals("false")){
                                    ctForm.setPrednisoneCurrentDosage(0);
                                }
                                break;
            case "alemtuzumab":  if(componentValue.equals("false")){
                                    ctForm.setAlemtuzumabCurrentDosage("");
                                }
                                break;
            case "sirolimus":  if(componentValue.equals("false")){
                                    ctForm.setSirolimusCurrentDosage("");
                                }
                                break;
            case "everolimus":  if(componentValue.equals("false")){
                                    ctForm.setEverolimusCurrentDosage("");
                                }
                                break;
            case "azathioprine":  if(componentValue.equals("false")){
                                    ctForm.setAzathioprineCurrentDosage("");
                                }
                                break;
            case "cyclosporineA":  if(componentValue.equals("false")){
                                    ctForm.setCyclosporineACurrentDosage("");
                                }
                                break;
            case "methotrexate":  if(componentValue.equals("false")){
                                    ctForm.setMethotrexateCurrentDosage("");
                                }
                                break;
            case "macrolideAntibiotic":  if(componentValue.equals("false")){
                                    ctForm.setMacrolideAntibioticCurrentDosage("");
                                }
                                break;
            case "mycophenolateMofetil":  if(componentValue.equals("false")){
                                    ctForm.setMycophenolateMofetilCurrentDosage("");
                                }
                                break;
            case "antiThymocyteGlobulin":  if(componentValue.equals("false")){
                                    ctForm.setAntiThymocyteGlobulinCurrentDosage("");
                                }
                                break;
            case "otherDrugs":  if(componentValue.equals("false")){
                                    ctForm.setOtherDrugNamesCurrent("");
                                }
                                break;
            case "ques2anticoagulants":  if(componentValue.equals("false")){
                                    ctForm.setAntiCoagulantName1("");
                                    ctForm.setAntiCoagulantName2("");
                                    ctForm.setAntiCoagulantName3("");
                                }
                                break;
            case "ques3antiplatelets":  if(componentValue.equals("false")){
                                    ctForm.setAntiPlateletName1("");
                                    ctForm.setAntiPlateletName2("");
                                    ctForm.setAntiPlateletName3("");
                                }
                                break;
            case "ques4ecpdiscontinued":  if(componentValue.equals("false") || componentValue.equals("na")){
                                    ctForm.setEcpTherapyDiscontinuedDate(null);
                                    ctForm.setEcpTherapyDiscontinuedReason("");
                                }
                                break;
        }

//        System.out.println("The value of the Methoxsalen component is: --" + ctForm.getMethoxsalen() + "!!!\n");
    }

    public void clearBaselineTherapyDrugsIfNoListener(AjaxBehaviorEvent event) {
        BaselineTherapyForm btForm = (BaselineTherapyForm) getSelectedForm();
        String componentID = event.getComponent().getId();
        System.out.println("The controller.clearBaselineTherapyDrugsIfNoListener is being executed!!!");
        System.out.println("Print the component type (ie. SelectOneRadio): " + event.getComponent().getClass().getName());
        System.out.println("The component ID is: " + event.getComponent().getId());
        String componentValue = (String) ((UIOutput)event.getSource()).getValue();
        System.out.println("The value of the component is: " + componentValue + "\n");
        System.out.println("The value of the Tacrolimus component is: ++" + btForm.getTacrolimusCurrent() + "++\n");
        
        switch(componentID){
            case "ques1bprednisone":  if(componentValue.equals("false")){
                                    btForm.setPrednisoneCurrentDosage(0);
                                }
                                break;
            case "quest1nOtherDrugs":  if(componentValue.equals("false")){
                                    btForm.setOtherDrugNamesCurrent("");
                                }
                                break;
            case "ques2anticoagulants":  if(componentValue.equals("false")){
                                    btForm.setAntiCoagulantName1("");
                                    btForm.setAntiCoagulantName2("");
                                    btForm.setAntiCoagulantName3("");
                                }
                                break;
            case "ques3antiplatelets":  if(componentValue.equals("false")){
                                    btForm.setAntiPlateletName1("");
                                    btForm.setAntiPlateletName2("");
                                    btForm.setAntiPlateletName3("");
                                }
                                break;
        }

//        System.out.println("The value of the Methoxsalen component is: --" + ctForm.getMethoxsalen() + "!!!\n");
    }
    
        public void updateListener(AjaxBehaviorEvent event) {
        System.out.println("controller updateListener called!!!!!!");
        System.out.println( "Called by: " + event.getComponent().getClass().getName());
        System.out.println(event.getComponent().getId());
        Integer i = (Integer) ((UIOutput)event.getSource()).getValue();
        System.out.println("The value of the component is: " + i + "\n");
        if(i == -1){
            respiration = true;
        }
    }


}
