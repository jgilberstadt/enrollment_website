package edu.wustl.mir.ctt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author drm
 */
public enum ECPEventTypes {
    ELIGIBILITY(                    "Confirmation of Eligibility",false, false, new ArrayList<ECPFormTypes>()),
    CONFIRM_ENROLLMENT(             "Confirm Enrollment", false, false, new ArrayList<ECPFormTypes>()),
    DEMOGRAPHICS(                   "Demographics", false, false, new ArrayList<ECPFormTypes>()),
    ECP_TREATMENT(                  "ECP Treatment", true, false, new ArrayList<ECPFormTypes>()),
    PULMONARY_EVAL(                 "Pulmonary Evaluation", true, false, new ArrayList<ECPFormTypes>()),
    END_OF_STUDY(                   "End of Study", true, false, new ArrayList<ECPFormTypes>()),
    QUALITY_OF_LIFE(                "Quality of Life", false, false, new ArrayList<ECPFormTypes>()),
    HOSPITALIZATION(                "Hospitalization", true, false, new ArrayList<ECPFormTypes>()),
    ANNUAL_FOLLOW_UP(               "Annual Follow-Up", false, false, new ArrayList<ECPFormTypes>()),
    BASELINE_THERAPY(               "Baseline Therapy", false, false, new ArrayList<ECPFormTypes>()),
    CHANGE_THERAPY(                 "Change in Therapy", true, false, new ArrayList<ECPFormTypes>()),
    ADVERSE_EVENT_WORKSHEET_SAE(    "Adverse Event Worksheet", true, false, new ArrayList<ECPFormTypes>()),
    SERIOUS_ADVERSE_EVENT(          "Serious Adverse Event", false, true, Arrays.asList(ECPFormTypes.SERIOUS_ADVERSE_EVENT, ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION)),
    OBSERVATION_PULMONARY_EVAL_LOG( "Observation Pulmonary Evaluation Log", false, false, new ArrayList<ECPFormTypes>()),
    CROSSOVER_ELIGIBILITY_WORKSHEET("Crossover Eligibility Worksheet", true, true, new ArrayList<ECPFormTypes>()),
    CROSSOVER_SAFETY_CHECK(         "Crossover Safety Check", false, false, new ArrayList<ECPFormTypes>()),
//    CROSSOVER_TO_TREATMENT_ARM(     "Crossover to Treatment Arm", false, false, new ArrayList<ECPFormTypes>()),
    UNKNOWN(                        "Unknown", false, false, new ArrayList<ECPFormTypes>());
    
    private final String name;
    private final boolean manual;  // User can add an event as needed if this variable is set to true.
    private final boolean unscheduledFormAllowed;  // user can add forms to this event type if true.
    private final List<ECPFormTypes> unscheduledFormTypes;  // list of form types that can be created as needed for this event type.
    
    private ECPEventTypes( String name, boolean manual, boolean unscheduledFormAllowed, List<ECPFormTypes> formTypes) {
        this.name = name;
        this.manual = manual;
        this.unscheduledFormAllowed = unscheduledFormAllowed;
        this.unscheduledFormTypes = formTypes;
    }
    
    public String getName() {
        return name;
    }

    public boolean isManual() {
        return manual;
    }

    public boolean isUnscheduledFormAllowed() {
        return unscheduledFormAllowed;
    } 
    
    public static List<ECPEventTypes> getUnscheduledEvents() {
        List<ECPEventTypes> events = new ArrayList<ECPEventTypes>();
        
        for( ECPEventTypes et: ECPEventTypes.values()) {
            if( et.isManual()) {
                if(!("End of Study".equals(et.getName()))){
                    events.add(et);
                }
            }
        }
        
        return events;
    }
    
    public static List<ECPEventTypes> getObservationalArmUnscheduledEvents() {
        List<ECPEventTypes> events = new ArrayList<ECPEventTypes>();
        
        for( ECPEventTypes et: ECPEventTypes.values()) {
            if( et.isManual()) {
                if(!("ECP Treatment".equals(et.getName()))){
                    events.add(et);
                }
            }
        }
        
        return events;
    }
    
    public List<ECPFormTypes> getUnscheduledFormTypes() {
        return unscheduledFormTypes;
    }
    
}
