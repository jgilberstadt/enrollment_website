package edu.wustl.mir.ctt.model;

import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.AnnualFollowUpForm;
import edu.wustl.mir.ctt.form.BaselineTherapyForm;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.ChangeTherapyForm;
import edu.wustl.mir.ctt.form.CrossoverEligibilityWorkSheet;
import edu.wustl.mir.ctt.form.CrossoverSafetyCheckForm;
import edu.wustl.mir.ctt.form.DemoMedHistForm;
import edu.wustl.mir.ctt.form.ECPTreatmentForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EndOfStudyForm;
import edu.wustl.mir.ctt.form.HospitalizationForm;
import edu.wustl.mir.ctt.form.QualityOfLifeForm;
import edu.wustl.mir.ctt.form.ObservePulmEvalLogForm;
import edu.wustl.mir.ctt.form.PulmEvalForm;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author drm
 */
public class ECPEvents {
    
    public static Event getInstance( ECPEventTypes type) {
        Event e = new Event(null);
        e.setName(type.getName());
        e.setType(type);
        e.setStatus(EventStatus.NEW);
        e.setExpected(false);
        e.setActualDate( new Date());
        return e;
    }
    
    /**
     * Determine the label for a new instance of the specified event type.
     * 
     * This label will likely depend on the events that already exist for
     * the specified participant.
     * 
     * Only SAEs have non-null labels.
     * 
     * @param type
     * @param p
     * @return
     * @throws PersistenceException 
     */
    public static String getLabel( ECPEventTypes type, Participant p) throws PersistenceException {
        String label = null;

        switch (type) {
            case SERIOUS_ADVERSE_EVENT:
                PersistenceManager pm = ServiceRegistry.getPersistenceManager();
                List<Event> events = pm.getEvents(p);
                int count = 0;
                for( Event e: events) {
                    if( ECPEventTypes.SERIOUS_ADVERSE_EVENT.equals( e.getType())) {
                        count++;
                    }
                }
                label = "SAE - " + (count+1);
                break;
            default:
                break;
        }

        return label;
    }
    
    public static List<BasicForm> getForms( ECPEventTypes type) {
        List<BasicForm> forms = new ArrayList<BasicForm>();
        BasicForm f;
        
        switch( type) {
            case ELIGIBILITY:
//                SimpleEligibilityForm sf = new SimpleEligibilityForm();
                EligibilityForm sf = new EligibilityForm();
                forms.add(sf);
                break;
            case DEMOGRAPHICS:
                f = new DemoMedHistForm();
                forms.add(f);
                break;
            case ECP_TREATMENT:
                f = new ECPTreatmentForm();
                forms.add(f);
                break;
            case PULMONARY_EVAL:
                f = new PulmEvalForm();
//                System.out.println("The PULMONARY_EVAL form title is: " + f.getTitle());
                forms.add(f);
                break;
            case BASELINE_THERAPY:
                f = new BaselineTherapyForm();
//                System.out.println("The CHANGE_THERAPY form title is: " + f.getTitle());
                forms.add(f);
                break;
            case CHANGE_THERAPY:
                f = new ChangeTherapyForm();
//                System.out.println("The CHANGE_THERAPY form title is: " + f.getTitle());
                forms.add(f);
                break;
            case ADVERSE_EVENT_WORKSHEET_SAE:
                f = new AdverseEventWorksheetSAEForm();
                forms.add(f);
                break;
            case SERIOUS_ADVERSE_EVENT:
                f = new AdverseEventWorksheetSAEForm();
                forms.add(f);
                break;
            case END_OF_STUDY:
                f = new EndOfStudyForm();
                forms.add(f);
                break;
            case QUALITY_OF_LIFE:
                f = new QualityOfLifeForm();
                forms.add(f);
                break;
            case ANNUAL_FOLLOW_UP:
                f = new AnnualFollowUpForm();
                forms.add(f);
                break;
            case CROSSOVER_ELIGIBILITY_WORKSHEET:
                f = new CrossoverEligibilityWorkSheet();
                forms.add(f);
                break;
            case CROSSOVER_SAFETY_CHECK:
                f = new CrossoverSafetyCheckForm();
                forms.add(f);
                break;
            case HOSPITALIZATION:
                f = new HospitalizationForm();
                forms.add(f);
                break;
            default:
                BasicForm bf = new BasicForm();
//                System.out.println("The default BASIC Form title is: " + bf.getTitle());
                forms.add(bf);
        }
        
        return forms;
    }
    
    // This method uses the formTitle because for some CRFs there are multiple CRFs with different titles.
    // The different title needs to be set in the new CRF that is created.
    public static List<BasicForm> getForms( ECPEventTypes type, String formTitle) {
        List<BasicForm> forms = new ArrayList<BasicForm>();
        BasicForm f;
        
        switch( type) {
            case ELIGIBILITY:
//                SimpleEligibilityForm sf = new SimpleEligibilityForm();
                EligibilityForm sf = new EligibilityForm();
                forms.add(sf);
                break;
            case DEMOGRAPHICS:
                f = new DemoMedHistForm();
                forms.add(f);
                break;
            case ECP_TREATMENT:
                f = new ECPTreatmentForm();
                f.setTitle(formTitle);
                forms.add(f);
                break;
            case PULMONARY_EVAL:
                f = new PulmEvalForm();
                f.setTitle(formTitle);
                forms.add(f);
                break;
            case BASELINE_THERAPY:
                f = new BaselineTherapyForm();
                f.setTitle(formTitle);
                forms.add(f);
                break;
            case CHANGE_THERAPY:
                f = new ChangeTherapyForm();
                f.setTitle(formTitle);
                forms.add(f);
                break;
            case ADVERSE_EVENT_WORKSHEET_SAE:
                f = new AdverseEventWorksheetSAEForm();
                f.setTitle(formTitle);
                forms.add(f);
                break;
            case END_OF_STUDY:
                f = new EndOfStudyForm();
                forms.add(f);
                break;
            case QUALITY_OF_LIFE:
                f = new QualityOfLifeForm();
                f.setTitle(formTitle);
                forms.add(f);
                break;
            case ANNUAL_FOLLOW_UP:
                f = new AnnualFollowUpForm();
                forms.add(f);
                break;
            case OBSERVATION_PULMONARY_EVAL_LOG:
                f = new ObservePulmEvalLogForm();
                forms.add(f);
                break;
            default:
                BasicForm bf = new BasicForm();
                forms.add(bf);
        }
        
        return forms;
    }
    
    public static List<Event> getEPIScheduledEvents( Participant participant) {
        List<Event> newEvents = new ArrayList<Event>();
        List<BasicForm> forms = new ArrayList<BasicForm>();
        ECPVisitDateOffsets ecpVisitDateOffsets = new ECPVisitDateOffsets();
//        ECPVisitLastDateOffsets ecpVisitLastDateOffsets = new ECPVisitLastDateOffsets();
        PulmEvalVisitDateOffsets pulmEvalVisitDateOffsets = new PulmEvalVisitDateOffsets();
        PulmEvalVisitLastDateOffsets pulmEvalVisitLastDateOffsets = new PulmEvalVisitLastDateOffsets();
        ChangeTherapyDateOffsets changeTherapyDateOffsets = new ChangeTherapyDateOffsets();
        QualityOfLifeDateOffsets qualityOfLifeDateOffsets = new QualityOfLifeDateOffsets();
        
        Event e = new Event(null);
        e.setType(ECPEventTypes.DEMOGRAPHICS);
        e.setName("Demographics/Medical History");
        e.setParticipantId(participant.getId());
        e.setStatus(EventStatus.NEW);
        // The following statment returns just a NEW Demographics Form.
        forms = ECPEvents.getForms(ECPEventTypes.DEMOGRAPHICS);
        // Get the Demographics form (get(0)) and set the date to be the enrollment date.
        forms.get(0).setDate(participant.getEnrolledDate());
        e.setForms(forms);
        e.setExpected(false);
//        e.setBaseDate(new Date());  // Since the Confirmation of Eligibility form cannot be completed excepted when all of the eligibility criteria are accepted
                                    // so the date of when the participant was determined to be eligible will always be the date when the confirmation occurred.
                                    // Therefore, today's date makes sense for setting the Actual Date Confirmation of Eligibility form and for settig the basedate
                                    // for the Demographics form which can not be completed because the participant is enrolled in the study now.
        e.setOffsetFromBaseDateInDays(0);
        e.setActualDate(participant.getEnrolledDate()); // The demographics actual date will be identical to the Confirmation of Eligibility actual date because the 
                                    // demographics form uses the participant's enrollment date as the date.
        newEvents.add(e);

        forms = new ArrayList<BasicForm>();
        e = new Event(null);
        e.setType(ECPEventTypes.BASELINE_THERAPY);
        e.setName("Baseline Therapy");
        e.setParticipantId(participant.getId());
        e.setStatus(EventStatus.NEW);
        // The following statement returns just a NEW Baseline Therapy form.
        forms = ECPEvents.getForms(ECPEventTypes.BASELINE_THERAPY);
        // Get the Baseline Therapy form (get(0)) and set the date to be the Baseline Therapy date.
        forms.get(0).setDate(participant.getEnrolledDate());
        e.setForms(forms);
        e.setExpected(false);
        e.setOffsetFromBaseDateInDays(0);
        e.setActualDate(participant.getEnrolledDate()); // The Baseline Therapy actual date will be identical to the Confirmation of Eligibility actual date because the 
                                    // Baseline Therapy form uses the participant's enrollment date as the date.
        newEvents.add(e);
//        
//        e = new Event(null);
//        e.setType(ECPEventTypes.ECP_TREATMENT.getName());
//        e.setName("ECP Treatment 1");
//        e.setParticipantId(participantID);
//        e.setStatus(EventStatus.PENDING);
//        e.setForms(ECPEvents.getForms(ECPEventTypes.ECP_TREATMENT));
//        e.setExpected(true);
//        newEvents.add(e);
//        
//        e = new Event(null);
//        e.setType(ECPEventTypes.ECP_TREATMENT.getName());
//        e.setName("ECP Treatment 2");
//        e.setParticipantId(participantID);
//        e.setStatus(EventStatus.PENDING);
//        e.setForms(ECPEvents.getForms(ECPEventTypes.ECP_TREATMENT));
//        e.setExpected(true);
//        e.setOffsetFromBaseDateInDays(3);
//        newEvents.add(e);
//        
//        e = new Event(null);
//        e.setType(ECPEventTypes.ECP_TREATMENT.getName());
//        e.setName("ECP Treatment 3");
//        e.setParticipantId(participantID);
//        e.setStatus(EventStatus.PENDING);
//        e.setForms(ECPEvents.getForms(ECPEventTypes.ECP_TREATMENT));
//        e.setExpected(true);
//        e.setOffsetFromBaseDateInDays(6);
//        newEvents.add(e);

        for(int i = 1; i < ecpVisitDateOffsets.getDateOffsets().length; i++){
            String formTitle = "ECP Treatment Visit " + i + " Form";
            e = new Event(null);
            e.setType(ECPEventTypes.ECP_TREATMENT);
            e.setName("ECP Treatment " + i);
            e.setParticipantId(participant.getId());
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.ECP_TREATMENT, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(ecpVisitDateOffsets.getDateOffset(i));
            e.setShowLastAvailableDate(false);  // Turn off the showing of the date in the Participant Summary page under the Overdue Date column.
//            System.out.println("The showLastAvailableDate value is: " + e.getShowLastAvailableDate());
//            e.setOffsetToLastAvailableDayInDays(ecpVisitLastDateOffsets.getLastDateOffset(i));
            newEvents.add(e);
        }
        
//        e = new Event(null);
//        e.setType(ECPEventTypes.PULMONARY_EVAL.getName());
//        e.setName("Pulmonary Eval: 30 day assessment");
//        e.setParticipantId(participantID);
//        e.setStatus(EventStatus.PENDING);
//        e.setForms(ECPEvents.getForms(ECPEventTypes.PULMONARY_EVAL));
//        e.setExpected(true);
//        e.setOffsetFromBaseDateInDays(30);
//        newEvents.add(e);
        
        for(int i = 1; i < pulmEvalVisitDateOffsets.getDateOffsets().length; i++){
            String formTitle = "Pulmonary Evaluation: " + pulmEvalVisitDateOffsets.getDateOffset(i) + " Day Assessment Form";
            e = new Event(null);
            e.setType(ECPEventTypes.PULMONARY_EVAL);
            e.setName("Pulmonary Evaluation: " + pulmEvalVisitDateOffsets.getDateOffset(i) + " day assessment");
            e.setParticipantId(participant.getId());
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.PULMONARY_EVAL, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(pulmEvalVisitDateOffsets.getDateOffset(i));
            e.setOffsetToLastAvailableDayInDays(pulmEvalVisitLastDateOffsets.getLastDateOffset(i));
            newEvents.add(e);
        }
        
        for(int i = 1; i < changeTherapyDateOffsets.getDateOffsets().length; i++){
            String formTitle = "Change in Therapy Day " + changeTherapyDateOffsets.getDateOffset(i) + " Form";
            e = new Event(null);
            e.setType(ECPEventTypes.CHANGE_THERAPY);
            e.setName("Change in Therapy Day " + changeTherapyDateOffsets.getDateOffset(i));
            e.setParticipantId(participant.getId());
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.CHANGE_THERAPY, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(changeTherapyDateOffsets.getDateOffset(i));
            e.setShowLastAvailableDate(false);  // Turn off the showing of the date in the Participant Summary page under the Overdue Date column.
            newEvents.add(e);
        }
        
        for(int i = 1; i < qualityOfLifeDateOffsets.getDateOffsets().length; i++){
            int dateOffset = qualityOfLifeDateOffsets.getDateOffset(i);
            String formTitle;
            
            e = new Event(null);
            e.setType(ECPEventTypes.QUALITY_OF_LIFE);
            
            if (dateOffset == 0) {
                formTitle = "Quality of Life Baseline Form";
                e.setName("Quality of Life Baseline");
            } else {
                formTitle = "Quality of Life Day " + qualityOfLifeDateOffsets.getDateOffset(i) + " Form";
                e.setName("Quality of Life Day " + qualityOfLifeDateOffsets.getDateOffset(i));
            }
            
            e.setParticipantId(participant.getId());
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.QUALITY_OF_LIFE, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(qualityOfLifeDateOffsets.getDateOffset(i));
            e.setShowLastAvailableDate(false);  // Turn off the showing of the date in the Participant Summary page under the Overdue Date column.
            newEvents.add(e);
        }
        
        e = new Event(null);
        e.setType(ECPEventTypes.END_OF_STUDY);
        e.setName("End Of Study");
        e.setParticipantId(participant.getId());
        e.setStatus(EventStatus.NEW);
        e.setForms(ECPEvents.getForms(ECPEventTypes.END_OF_STUDY));
        e.setExpected(true);
//        e.setOffsetFromBaseDateInDays(393);
//        e.setOffsetToLastAvailableDayInDays(15);
        newEvents.add(e);
        
//        e = new Event(null);
//        e.setType(ECPEventTypes.ANNUAL_FOLLOW_UP);
//        e.setName("Annual Follow-Up");
//        e.setParticipantId(participant.getId());
//        e.setStatus(EventStatus.NEW);
//        e.setForms(ECPEvents.getForms(ECPEventTypes.ANNUAL_FOLLOW_UP));
//        e.setExpected(true);
////        e.setOffsetFromBaseDateInDays(393);
////        e.setOffsetToLastAvailableDayInDays(15);
//        newEvents.add(e);

        return newEvents;
    }

    public static List<Event> getControlScheduledEvents( Participant participant) {
        List<Event> newEvents = new ArrayList<Event>();
        List<BasicForm> forms = new ArrayList<BasicForm>();
//        ECPVisitLastDateOffsets ecpVisitLastDateOffsets = new ECPVisitLastDateOffsets();
        PulmEvalVisitDateOffsets pulmEvalVisitDateOffsets = new PulmEvalVisitDateOffsets();
        PulmEvalVisitLastDateOffsets pulmEvalVisitLastDateOffsets = new PulmEvalVisitLastDateOffsets();
        ChangeTherapyDateOffsets changeTherapyDateOffsets = new ChangeTherapyDateOffsets();
        QualityOfLifeDateOffsets qualityOfLifeDateOffsets = new QualityOfLifeDateOffsets();
        
        Event e = new Event(null);
        e.setType(ECPEventTypes.DEMOGRAPHICS);
        e.setName("Demographics/Medical History");
        e.setParticipantId(participant.getId());
        e.setStatus(EventStatus.NEW);
        // The following statment returns just a NEW Demographics Form.
        forms = ECPEvents.getForms(ECPEventTypes.DEMOGRAPHICS);
        // Get the Demographics form (get(0)) and set the date to be the enrollment date.
        forms.get(0).setDate(participant.getEnrolledDate());
        e.setForms(forms);
        e.setExpected(false);
//        e.setBaseDate(new Date());  // Since the Confirmation of Eligibility form cannot be completed excepted when all of the eligibility criteria are accepted
                                    // so the date of when the participant was determined to be eligible will always be the date when the confirmation occurred.
                                    // Therefore, today's date makes sense for setting the Actual Date Confirmation of Eligibility form and for settig the basedate
                                    // for the Demographics form which can not be completed because the participant is enrolled in the study now.
        e.setOffsetFromBaseDateInDays(0);
        e.setActualDate(participant.getEnrolledDate()); // The demographics actual date will be identical to the Confirmation of Eligibility actual date because the 
                                    // demographics form uses the participant's enrollment date as the date.
        newEvents.add(e);

        forms = new ArrayList<BasicForm>();
        e = new Event(null);
        e.setType(ECPEventTypes.BASELINE_THERAPY);
        e.setName("Baseline Therapy");
        e.setParticipantId(participant.getId());
        e.setStatus(EventStatus.NEW);
        // The following statement returns just a NEW Baseline Therapy form.
        forms = ECPEvents.getForms(ECPEventTypes.BASELINE_THERAPY);
        // Get the Baseline Therapy form (get(0)) and set the date to be the Baseline Therapy date.
        forms.get(0).setDate(participant.getEnrolledDate());
        e.setForms(forms);
        e.setExpected(false);
        e.setOffsetFromBaseDateInDays(0);
        e.setActualDate(participant.getEnrolledDate()); // The Baseline Therapy actual date will be identical to the Confirmation of Eligibility actual date because the 
                                    // Baseline Therapy form uses the participant's enrollment date as the date.
        newEvents.add(e);
        
        for(int i = 1; i < pulmEvalVisitDateOffsets.getDateOffsets().length; i++){
            String formTitle = "Pulmonary Evaluation " + pulmEvalVisitDateOffsets.getDateOffset(i) + " Form";
            e = new Event(null);
            e.setType(ECPEventTypes.PULMONARY_EVAL);
            e.setName("Pulmonary Evaluation: " + pulmEvalVisitDateOffsets.getDateOffset(i) + " Day Assessment Form");
            e.setParticipantId(participant.getId());
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.PULMONARY_EVAL, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(pulmEvalVisitDateOffsets.getDateOffset(i));
            e.setOffsetToLastAvailableDayInDays(pulmEvalVisitLastDateOffsets.getLastDateOffset(i));
            newEvents.add(e);
        }
        
        for(int i = 1; i < changeTherapyDateOffsets.getDateOffsets().length; i++){
            String formTitle = "Change in Therapy Day " + changeTherapyDateOffsets.getDateOffset(i) + " Form";
            e = new Event(null);
            e.setType(ECPEventTypes.CHANGE_THERAPY);
            e.setName("Change in Therapy Day " + changeTherapyDateOffsets.getDateOffset(i));
            e.setParticipantId(participant.getId());
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.CHANGE_THERAPY, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(changeTherapyDateOffsets.getDateOffset(i));
            e.setShowLastAvailableDate(false);  // Turn off the showing of the date in the Participant Summary page under the Overdue Date column.
            newEvents.add(e);
        }
        
        for(int i = 1; i < qualityOfLifeDateOffsets.getDateOffsets().length; i++){
            int dateOffset = qualityOfLifeDateOffsets.getDateOffset(i);
            String formTitle;
            
            e = new Event(null);
            e.setType(ECPEventTypes.QUALITY_OF_LIFE);
            
            if (dateOffset == 0) {
                formTitle = "Quality of Life Baseline Form";
                e.setName("Quality of Life Baseline");
            } else {
                formTitle = "Quality of Life Day " + qualityOfLifeDateOffsets.getDateOffset(i) + " Form";
                e.setName("Quality of Life Day " + qualityOfLifeDateOffsets.getDateOffset(i));
            }
            
            e.setParticipantId(participant.getId());
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.QUALITY_OF_LIFE, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(qualityOfLifeDateOffsets.getDateOffset(i));
            e.setShowLastAvailableDate(false);  // Turn off the showing of the date in the Participant Summary page under the Overdue Date column.
            newEvents.add(e);
        }
        
        e = new Event(null);
        e.setType(ECPEventTypes.END_OF_STUDY);
        e.setName("End Of Study");
        e.setParticipantId(participant.getId());
        e.setStatus(EventStatus.NEW);
        e.setForms(ECPEvents.getForms(ECPEventTypes.END_OF_STUDY));
        e.setExpected(true);
        //e.setOffsetFromBaseDateInDays(393);
        //e.setOffsetToLastAvailableDayInDays(15);
        newEvents.add(e);
        
        return newEvents;
    }
    
    public static List<Event> getCrossoverScheduledTreatmentEvents( int participantID) {
        List<Event> newEvents = new ArrayList<Event>();
        ECPVisitDateOffsets ecpVisitDateOffsets = new ECPVisitDateOffsets();
        
        Event e;

        for(int i = 1; i < ecpVisitDateOffsets.getDateOffsets().length; i++){
            String formTitle = "ECP Treatment Visit " + i + " Form";
            e = new Event(null);
            e.setType(ECPEventTypes.ECP_TREATMENT);
            e.setName("ECP Treatment " + i);
            e.setParticipantId(participantID);
            e.setStatus(EventStatus.NEW);
            e.setForms(ECPEvents.getForms(ECPEventTypes.ECP_TREATMENT, formTitle));
            e.setExpected(true);
            e.setOffsetFromBaseDateInDays(ecpVisitDateOffsets.getDateOffset(i));
            e.setShowLastAvailableDate(false);  // Turn off the showing of the date in the Participant Summary page under the Overdue Date column.
//            System.out.println("The showLastAvailableDate value is: " + e.getShowLastAvailableDate());
//            e.setOffsetToLastAvailableDayInDays(ecpVisitLastDateOffsets.getLastDateOffset(i));
            newEvents.add(e);
        }

        return newEvents;
    }

}
