package edu.wustl.mir.ctt.persistence;

import java.util.List;
import java.util.Date;

import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.AnnualFollowUpForm;
import edu.wustl.mir.ctt.form.BaselineTherapyForm;
import edu.wustl.mir.ctt.form.ChangeTherapyForm;
import edu.wustl.mir.ctt.form.DemoMedHistForm;
import edu.wustl.mir.ctt.form.ECPTreatmentForm;
import edu.wustl.mir.ctt.model.Document;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EndOfStudyForm;
import edu.wustl.mir.ctt.form.ObservePulmEvalLogForm;
import edu.wustl.mir.ctt.form.PulmEvalForm;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.form.SimpleEligibilityForm;
import edu.wustl.mir.ctt.form.SimpleForm;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.EventStatus;
import edu.wustl.mir.ctt.model.Site;
import java.io.InputStream;
import java.io.OutputStream;
import edu.wustl.mir.ctt.form.CrossoverSafetyCheckForm;
import edu.wustl.mir.ctt.form.DSCForm;
import edu.wustl.mir.ctt.form.HospitalizationForm;
import edu.wustl.mir.ctt.form.QualityOfLifeForm;
import edu.wustl.mir.ctt.model.DeclineStrata;
import edu.wustl.mir.ctt.model.SpirometryStrata;
import edu.wustl.mir.ctt.model.StudyArmStatus;

public interface PersistenceManager {

//    void initForm(BasicForm f) throws PersistenceException;

    int insertForm(BasicForm form) throws PersistenceException;
    
    int insertSite(Site s) throws PersistenceException;
    
    List<Site> getSites() throws PersistenceException;
    
    Site getSite(int id) throws PersistenceException;
    
    Site getSite(String siteName) throws PersistenceException;
    
    StudyArmStatus randomizeParticipant(int siteId, String participantId, DeclineStrata d, SpirometryStrata s) throws PersistenceException;
    
    int addParticipant(Participant p, Event e, List<BasicForm> forms) throws PersistenceException;
    
    int addParticipant(Participant p) throws PersistenceException;
            
    int addParticipantEnrollment(Participant p, Event e, List<BasicForm> forms) throws PersistenceException;
    
    int addEvent(Participant p, Event e, List<BasicForm> forms, Site selectedSite) throws PersistenceException;
    
    int addInitialSAEEvent(Participant p, Event e, List<BasicForm> forms, Site selectedSite) throws PersistenceException;
    
    void updateEvent(Event event) throws PersistenceException;

    void updateEventStatus(Event event) throws PersistenceException;

    void updateEvents(List<Event> events) throws PersistenceException;
    
    void enrollParticipant(Participant p, List<Event> e, Site selectedSite) throws PersistenceException;
    
    List<Participant> getParticipants(Site site) throws PersistenceException;

    List<Event> getEvents(Participant p) throws PersistenceException;
    
    Event getEvent(String pid, String eventTitle, String label) throws PersistenceException;

    List<BasicForm> getForms(Event e) throws PersistenceException;
    
    Integer saveForm(BasicForm f) throws PersistenceException;
    
//    BasicForm getForm( int formId) throws PersistenceException;

    SimpleForm getSimpleForm(BasicForm f) throws PersistenceException;

    SimpleEligibilityForm getSimpleEligibilityForm(BasicForm f) throws PersistenceException;

    EligibilityForm getEligibilityForm(BasicForm f) throws PersistenceException;

    ECPTreatmentForm getECPTreatmentForm(BasicForm f) throws PersistenceException;

    DemoMedHistForm getDemoMedHistForm(BasicForm f) throws PersistenceException;

    PulmEvalForm getPulmEvalForm(BasicForm f) throws PersistenceException;
    
    AdverseEventWorksheetSAEForm getAdverseEventWorksheetSAEForm(BasicForm bf) throws PersistenceException;

    ChangeTherapyForm getChangeTherapyForm(BasicForm bf) throws PersistenceException;

    BaselineTherapyForm getBaselineTherapyForm(BasicForm bf) throws PersistenceException;

    EndOfStudyForm getEndOfStudyForm(BasicForm f) throws PersistenceException;

    QualityOfLifeForm getQualityOfLifeForm(BasicForm f) throws PersistenceException;

    HospitalizationForm getHospitalizationForm(BasicForm f) throws PersistenceException;
    
    DSCForm getDiseaseSpecificCategorizationForm(BasicForm f) throws PersistenceException;
    
    AnnualFollowUpForm getAnnualFollowUpForm( BasicForm bf) throws PersistenceException;

    ObservePulmEvalLogForm getObservePulmEvalLogForm(BasicForm bf) throws PersistenceException;

    CrossoverSafetyCheckForm getCrossoverSafetyCheckForm(BasicForm bf) throws PersistenceException;
    
//    MedicalHistoryForm getMedicalHistoryForm(int id) throws PersistenceException;

    int getEnrolledParticipantsCount(int id) throws PersistenceException;
    
    List<String> getAllParticipantIDs(Site s) throws PersistenceException;
    
    int saveDocument(BasicForm f, String name, InputStream is, String sourceDocType) throws PersistenceException;
    
    List<Document> getDocuments(BasicForm f) throws PersistenceException;
    
    void writeDocument(int id, OutputStream os) throws PersistenceException;
    
    public void deleteDocument(int id) throws PersistenceException;
        
    int getEventCount(Participant p, EventStatus status) throws PersistenceException;
    
    int getEventCount(Site s, EventStatus status) throws PersistenceException;
    
    int getEventTypeCount(Participant p, Event e) throws PersistenceException;
    
    // pid is the trial pid and not the table pid.
    boolean isOnHold(String pid) throws PersistenceException;

    void setOnHold(String pid, boolean b, Date holdStartDate) throws PersistenceException;
    
    Participant getParticipant(String pid) throws PersistenceException;
    
    void insertUserLoginInformation(int siteIdDeterminedFromUsername, String loginUsername, String remoteIPAddress, Date loginDate) throws PersistenceException;

    int getVersionControlId(Date protocolDate, String protocolVersionNumber, Date crfReleaseDate, Date crfUpdate, String crfVersionNumber, Date softwareReleaseDate, String softwareVersionNumber) throws PersistenceException ;

    void insertVersionControlId(Date protocolDate, String protocolVersionNumber, Date crfReleaseDate, Date crfUpdate, String crfVersionNumber, Date softwareReleaseDate, String softwareVersionNumber) throws PersistenceException ;

    public void setShowArm( String pid, boolean showStudyArm) throws PersistenceException;
}
