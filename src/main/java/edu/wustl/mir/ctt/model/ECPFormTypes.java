package edu.wustl.mir.ctt.model;

import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.BaselineTherapyForm;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.ChangeTherapyForm;
import edu.wustl.mir.ctt.form.CrossoverSafetyCheckForm;
import edu.wustl.mir.ctt.form.DSCForm;
import edu.wustl.mir.ctt.form.DemoMedHistForm;
import edu.wustl.mir.ctt.form.ECPTreatmentForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EndOfStudyForm;
import edu.wustl.mir.ctt.form.ObservePulmEvalLogForm;
import edu.wustl.mir.ctt.form.PulmEvalForm;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.util.List;

/**
 *
 * @author drm
 */
public enum ECPFormTypes {
    BASIC("Basic"),
    SERIOUS_ADVERSE_EVENT("Serious Adverse Event"),
    ELIGIBILITY("Confirmation of Eligibility"),
    DEMOGRAPHICS("Demographics"),
    ECP_TREATMENT("ECP Treatment"),
    ENROLLMENT_STUDY_ARM_ASSIGNMENT("Enrollment and Arm Assignment"),
    ENROLLMENT("Enrollment"),
    PULMONARY_EVAL("Pulmonary Evaluation"),
    END_OF_STUDY("End of Study"),
    BASELINE_THERAPY("Baseline Therapy"),
    CHANGE_THERAPY("Change in Therapy"),
    CROSSOVER_ELIGIBILITY_WORKSHEET("Crossover Eligibility"),
    CROSSOVER_SAFETY_CHECK("Crossover Safety Check"),
    SIMPLE_ELIGIBILITY("Simple Eligibility"),
    SIMPLE("Simple"),
    ADVERSE_EVENT_WORKSHEET("Serious Adverse Event"),
    STUDY_ARM_ELIGIBILITY("Confirmation of Eligibility"),
    PULMONARY_EVAL_LOG("Pulmonary Evaluation Log"),
    QUALITY_OF_LIFE("Quality of Life"),
    HOSPITALIZATION("Hospitalization"),
    DISEASE_SPECIFIC_CATEGORIZATION("Disease Specific Categorization"),
    ANNUAL_FOLLOW_UP("Annual Follow-Up"),
    UNKNOWN("Unknown");
    
    private final String name;
    
    private ECPFormTypes( String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static BasicForm getInstance( ECPFormTypes type, Event e) throws PersistenceException {
        BasicForm form;
        
        switch( type) {
            case SERIOUS_ADVERSE_EVENT:
                PersistenceManager pm = ServiceRegistry.getPersistenceManager();
                List<BasicForm> existingForms = pm.getForms(e);
                
                BasicForm mostRecentSAEForm = null;
                int saeFormCount = 0;
                for( BasicForm f: existingForms) {
                    if( ECPFormTypes.SERIOUS_ADVERSE_EVENT.equals(f.getFormType())) {
                        saeFormCount++;
                        if( mostRecentSAEForm == null) {
                            mostRecentSAEForm = f;
                        }
//                        else if( f.getDate().after(mostRecentSAEForm.getDate())) {
                        else if( f.getSequenceNumber() > mostRecentSAEForm.getSequenceNumber()) {
                            mostRecentSAEForm = f;
                        }
                    }
                }

                if( mostRecentSAEForm != null) {
                    form = pm.getAdverseEventWorksheetSAEForm(mostRecentSAEForm);
                }
                else {
                    form = new AdverseEventWorksheetSAEForm();
                }
                saeFormCount++;
                String title = "SAE Form - " + saeFormCount;
                form.setTitle(title);
                form.setStatus(FormStatus.NEW);
                form.setSequenceNumber(saeFormCount);
                
                return form;
            case DISEASE_SPECIFIC_CATEGORIZATION:
                form = new DSCForm();
                
                form.setTitle("DSC Form");
                form.setStatus(FormStatus.NEW);
                form.setSequenceNumber(99); // Display at end of list
                
                return form;
            default:
                return  new BasicForm();
        }
    }
    
}
