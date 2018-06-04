package edu.wustl.mir.ctt.calc;

import edu.wustl.mir.ctt.form.EnrollmentForm;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author drm
 */
public class EnrollmentDetermination implements Serializable {
    private enum ScreeningStatus {
        SCREEN_PASS,
        SCREEN_FAIL
    }
    
    // Use status enumeration as key for message map for readability
    private static final Map<ScreeningStatus, String> msgs;
    static { // Initialize static map, see https://stackoverflow.com/a/507658
        Map<ScreeningStatus, String> m = new EnumMap<>(ScreeningStatus.class);
        m.put(ScreeningStatus.SCREEN_PASS, "Patient is enrolled into the study. Enter FEV1s next to confirm eligibility");
        m.put(ScreeningStatus.SCREEN_FAIL, "Patient not eligible for enrollment into the study");
        msgs = Collections.unmodifiableMap(m);
    }
    
    private final ScreeningStatus status;
    
    public EnrollmentDetermination( EnrollmentForm form) {
        status = screen(form);
    }
    
    public String getOutcomeMessage() {
        return msgs.get(status);
    }
    
    public boolean isEligibilityScreeningPassed() {
        boolean b = false;
        if( status == ScreeningStatus.SCREEN_PASS) b = true;
        return b;
    }
    
    public boolean isEligibilityScreeningFailed() {
        boolean b = false;
        if( status == ScreeningStatus.SCREEN_FAIL) b = true;
        return b;
    }
    
    private ScreeningStatus screen(EnrollmentForm form) {
        // If i == ScreeningStatus.SCREEN_PASS at the end, inclusion and exclusion criteria all passed.
        // Otherwise, if i == ScreeningStatus.SCREEN_FAIL at the end of the ifs, one or more criteria failed.
        ScreeningStatus i = ScreeningStatus.SCREEN_PASS;
        
        // Inclusion criteria cannot be false
        if( form.getAge() != null ) {
            if("false".equals(form.getAge())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getMedicare() != null ) {
            if("false".equals(form.getMedicare())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getLungTransplant() != null ) {
            if("false".equals(form.getLungTransplant())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getLabBasedNewBOSDiagnosis() != null ) {
            if("false".equals(form.getLabBasedNewBOSDiagnosis())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getFiveFEV1sPostTrans() != null ) {
            if("false".equals(form.getFiveFEV1sPostTrans())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if ( form.getPreDiagnosisMonitoring() != null) {
            if("false".equals(form.getPreDiagnosisMonitoring())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if ( form.getDocumentedClinicalAssessment() != null) {
            if("false".equals(form.getDocumentedClinicalAssessment())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        
        // Exclusion criteria cannot be true
        if( form.getAnotherTrial() != null ) {
            if("true".equals(form.getAnotherTrial())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getInterfereCondition() != null ) {
            if("true".equals(form.getInterfereCondition())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getKnownAllergy() != null ) {
            if("true".equals(form.getKnownAllergy())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getAcuteCondition() != null ) {
            if("true".equals(form.getAcuteCondition())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getOtherCondition() != null ) {
            if("true".equals(form.getOtherCondition())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getAphakia() != null ) {
            if("true".equals(form.getAphakia())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getPregnancy() != null ) {
            if("true".equals(form.getPregnancy())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getNoInformedConsent() != null ) {
            if("true".equals(form.getNoInformedConsent())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getLeukopenia3000() != null ) {
            if("true".equals(form.getLeukopenia3000())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getDeclineNotBOS() != null ) {
            if("true".equals(form.getDeclineNotBOS())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        if( form.getAcuteDeclineTreatment() != null ) {
            if("true".equals(form.getAcuteDeclineTreatment())){
                i = ScreeningStatus.SCREEN_FAIL;
            }
        }
        
        return i;
    }
}