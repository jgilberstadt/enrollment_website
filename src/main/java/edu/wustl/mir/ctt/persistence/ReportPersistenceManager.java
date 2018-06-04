package edu.wustl.mir.ctt.persistence;

import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.BaselineTherapyForm;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.ChangeTherapyForm;
import edu.wustl.mir.ctt.form.CrossoverSafetyCheckForm;
import edu.wustl.mir.ctt.form.DemoMedHistForm;
import edu.wustl.mir.ctt.form.ECPTreatmentForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EndOfStudyForm;
import edu.wustl.mir.ctt.form.ObservePulmEvalLogForm;
import edu.wustl.mir.ctt.form.PulmEvalForm;
import edu.wustl.mir.ctt.form.SimpleEligibilityForm;
import edu.wustl.mir.ctt.form.SimpleForm;
import edu.wustl.mir.ctt.model.Document;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.EventStatus;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.model.Site;
import edu.wustl.mir.ctt.reports.ReportItem;
import edu.wustl.mir.ctt.reports.ReportItems;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface ReportPersistenceManager {

//    void initForm(BasicForm f) throws PersistenceException;
       
    List<Site> getSites() throws PersistenceException;
    
    Site getSite(int id) throws PersistenceException;
    
    Site getSite(String siteName) throws PersistenceException;
    
    List<Participant> getParticipants(Site site) throws PersistenceException;

    Event getEvent(String pid, String eventTitle, String label) throws PersistenceException;

    List<BasicForm> getForms(Event e) throws PersistenceException;
    
    int getEnrolledParticipantsCount(int id) throws PersistenceException;
    
    List<String> getAllParticipantIDs(Site s) throws PersistenceException;
    
    int getEventCount(Participant p, EventStatus status) throws PersistenceException;
    
    int getEventCount(Site s, EventStatus status) throws PersistenceException;
    
    int getEventTypeCount(Participant p, Event e) throws PersistenceException;
    
    // pid is the trial pid and not the table pid.
    boolean isOnHold(String pid) throws PersistenceException;

    Participant getParticipant(String pid) throws PersistenceException;

    List<ReportItem> getTotalAccruals() throws PersistenceException;

    List<ReportItem> getTotalAccrualsLast30Days() throws PersistenceException;
    
    List<ReportItem> getTotalAccrualsBySite() throws PersistenceException;
    
    ReportItems getTotalAccrualsBySiteByMonthRawDatabaseRetrieval() throws PersistenceException;
    
    ReportItems getTotalAccrualsBySiteByMonth(String studyArm) throws PersistenceException;
    
    int getParticipantFirstPaymentStatus(int site, String pid, String eventName) throws PersistenceException;

}
