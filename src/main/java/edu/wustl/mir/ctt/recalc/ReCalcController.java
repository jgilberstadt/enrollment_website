package edu.wustl.mir.ctt.recalc;

import edu.wustl.mir.ctt.calc.StudyArmEligibilityCalculator;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.Site;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
@SessionScoped
public class ReCalcController implements Serializable {
//    private StudyArmEligibilityForm eform;
    private StudyArmEligibilityCalculator calc;
    private Site site;
    private final Site noSite;  // use this site when "None" is selected in dropdown.
    private final List<Site> sites;
    private final Converter siteConverter;
    private Participant participant;
    private final Participant noParticipant;  // use this participant when "None" is selected in dropdown.
    private List<Participant> participants;
    private final Converter participantConverter;
    // the list of evaluations used for form I/O.
    private List<PulmonaryEvaluation> inputEvaluations;
    
    // the list of inputEvaluations with data (non-null contents).
    // this list is passed to the calculator.
    private List<PulmonaryEvaluation> activeEvaluations;
    private boolean renderChart;
    
    public ReCalcController() throws PersistenceException {
        renderChart = false;
        
        inputEvaluations = new ArrayList<PulmonaryEvaluation>();
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        inputEvaluations.add( new PulmonaryEvaluation( null, null, null));
        
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        sites = pm.getSites();
        noSite = new Site();
        noSite.setName("None");
        sites.add(noSite);
        site = noSite;
        siteConverter = new SiteConverter();

        participants = new ArrayList<Participant>();
        noParticipant = new Participant();
        noParticipant.setParticipantID("None");
        participants.add(noParticipant);
        participant = noParticipant;
        participantConverter = new ParticipantConverter();

    }
    
    private void clearEvaluations() {
        for( PulmonaryEvaluation e: inputEvaluations) {
            e.setDate(null);
            e.setFev1(null);
            e.setFvc(null);
        }
    }
    
    public String eligibilityCalcAction() {
        activeEvaluations = initActiveEvaluations();
        
        if( activeEvaluations.size() < 3) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage("Submission Error:", "At least 3 measurements are required."));
            // return null so browser stays on form that failed to submit.
            // forms will detect failed submit and display warning.
            return null;
        }
        calc = new StudyArmEligibilityCalculator( activeEvaluations, new Date());
        // Do not rencer the chart if there are less than 5 valid FEV1s
        if(calc.isDataTooFew() || calc.isDataStale()) {
            renderChart = false;
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage("Submission Error:", "At least 5 measurements within the last 6 months from today's date are required, and the last measurement must be within the last 7 days."));
            // return null so browser stays on form that failed to submit.
            // forms will detect failed submit and display warning.
            return null;
        } else {
            renderChart = true;
        }
        
        return "/recalc/calculator.xhtml";
    }

    public String registryAction() {
        return "/recalc/calculator.xhtml";
    }

//    public StudyArmEligibilityForm getEligibilityForm() {
//        return eform;
//    }
//
//    public void setEligibilityForm( StudyArmEligibilityForm eform) {
//        this.eform = eform;
//    }

    public StudyArmEligibilityCalculator getCalculator() {
        return calc;
    }

    public void setCalculator(StudyArmEligibilityCalculator calc) {
        this.calc = calc;
    }
    
    public List<PulmonaryEvaluation> getInputEvaluations() {
        System.out.println("I am in the getInputEvaluations method!!!!!!!!!!!!!!!");
//        return eform.getPulmEvaluations();
        return inputEvaluations;
    }
    
    public List<PulmonaryEvaluation> getActiveEvaluations() {
        return activeEvaluations;
    }
    
    public List<PulmonaryEvaluation> getEvalsWithQualifyingDates() {
        return calc.getEvalsWithQualifyingDates();
    }
    
    public List<Site> getSites() {
        return sites;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
    
    private List<PulmonaryEvaluation> initActiveEvaluations() {
        List<PulmonaryEvaluation> evals = new ArrayList<PulmonaryEvaluation>();
        
        for( PulmonaryEvaluation e: inputEvaluations) {
            Date d = e.getDate();
            Float fev = e.getFev1();
            if( d != null && fev != null) {
                PulmonaryEvaluation newe = new PulmonaryEvaluation( d, fev, 0f);
                evals.add(newe);
            }
        }
        return evals;
    }

    public void selectSiteListener(AjaxBehaviorEvent event) throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        participants = pm.getParticipants(site);
        participant = noParticipant;
        participants.add( participant);
        clearEvaluations();
    }
 
    public List<Participant> getParticipants() {
        
        return participants;
    }
    
    public Participant getParticipant() {
        return participant;
    }
    
    public void setParticipant( Participant p) {
        participant = p;
    }
    
    public void selectParticipantListener(AjaxBehaviorEvent event) throws PersistenceException {
        
        if( "None".equals(participant.getParticipantID())) {
            clearEvaluations();
        }
        else {
            inputEvaluations = getEvaluationsForParticipant( participant);
        }
    }
    
    private List<PulmonaryEvaluation> getEvaluationsForParticipant( Participant p) throws PersistenceException {
            
            clearEvaluations();
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
            List<Event> events = pm.getEvents(p);
            
            Event event = null;
            for( Event e: events) {
                if( e.getType().equals( ECPEventTypes.ELIGIBILITY)) {
                    event = e;
                    break;
                }
            }
            
            EligibilityForm eligibilityForm = null;
            if( event != null) {
                List<BasicForm> forms = pm.getForms(event);
                for( BasicForm form: forms) {
                    if( form.getFormType().equals(ECPFormTypes.ELIGIBILITY)) {
                        eligibilityForm = pm.getEligibilityForm(form);
                        break;
                    }
                }
            }
            
            if( eligibilityForm != null) {
                List<PulmonaryEvaluation> evals = eligibilityForm.getEvaluations();
                if( evals.size() > inputEvaluations.size()) {
                    inputEvaluations = evals;
                }
                else {
                    for( int i = 0; i < evals.size(); i++) {
                       inputEvaluations.set(i, evals.get(i));
                    }
                }
            }
                    
        return inputEvaluations;
    }
    
    public boolean isRenderChart() {
        return renderChart;
    }
    
    public Converter getSiteConverter() {
        return siteConverter;
    }
    
    public class SiteConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            Site site = noSite;
            for (Site s : sites) {
                if( s.getName().equals(value)) {
                    site = s;
                    break;
                }
            }
            return site ;
       }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            Site site = (Site) value;
            return site.getName();
        }
    }
 
    public Converter getParticipantConverter() {
        return participantConverter;
    }
    
    public class ParticipantConverter implements Converter {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            Participant participant = noParticipant;
            for( Participant p : participants) {
                if( p.getParticipantID().equals(value)) {
                    participant = p;
                    break;
                }
            }
            return participant ;
       }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            Participant participant = (Participant) value;
            return participant.getParticipantID();
        }
    }
 
}
