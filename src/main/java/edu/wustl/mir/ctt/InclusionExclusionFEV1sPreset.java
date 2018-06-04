/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wustl.mir.ctt;

import edu.wustl.mir.ctt.form.EnrollmentForm;
import edu.wustl.mir.ctt.form.StudyArmEligibilityForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul K. Commean
 */
public class InclusionExclusionFEV1sPreset {
    
    public InclusionExclusionFEV1sPreset( EnrollmentForm enrollmentForm, StudyArmEligibilityForm studyArmEligibilityForm) {
        // Inclusion Criteria questions with radio button values preset to YES which has a true value
        enrollmentForm.setAge("true");
        enrollmentForm.setMedicare("true");
        enrollmentForm.setLungTransplant("true");
        enrollmentForm.setFiveFEV1sPostTrans("true");
//        System.out.println("The enrollmentForm.getAge value is: " + enrollmentForm.getAge());

        // Exclusion Criteria questions with radio button values preset to NO which has a false value
        enrollmentForm.setAnotherTrial("false");
        enrollmentForm.setInterfereCondition("false");
        enrollmentForm.setKnownAllergy("false");
        enrollmentForm.setAcuteCondition("false");
        enrollmentForm.setOtherCondition("false");
        enrollmentForm.setAphakia("false");
        enrollmentForm.setPregnancy("false");
        enrollmentForm.setNoInformedConsent("false");

        // Pulmonary Evaluations DATES preset to dates given below
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        Date date4 = null;
        Date date5 = null;
        try {
            // Observational Arm dates
//            date1 = sdf.parse("1/02/2015");
//            date2 = sdf.parse("1/09/2015");
//            date3 = sdf.parse("1/16/2015");
//            date4 = sdf.parse("1/23/2015");
//            date5 = sdf.parse("4/15/2015");
            // ECP Treatment Arm dates
            date1 = sdf.parse("5/02/2015");
            date2 = sdf.parse("5/09/2015");
            date3 = sdf.parse("5/16/2015");
            date4 = sdf.parse("5/23/2015");
            date5 = sdf.parse("6/12/2015");
            
        } catch (ParseException ex) {
            Logger.getLogger(CalcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studyArmEligibilityForm.setDate1(date1);
        studyArmEligibilityForm.setDate2(date2);
        studyArmEligibilityForm.setDate3(date3);
        studyArmEligibilityForm.setDate4(date4);
        studyArmEligibilityForm.setDate5(date5);
        
        // Pulmonary Evaluations FEV1s preset to Observational Arm values given below
//        studyArmEligibilityForm.setFev11(2.0f);
//        studyArmEligibilityForm.setFev12(2.0f);
//        studyArmEligibilityForm.setFev13(2.0f);
//        studyArmEligibilityForm.setFev14(2.0f);
//        studyArmEligibilityForm.setFev15(1.95f);

        // Pulmonary Evaluations FEV1s preset to ECP Treatment Arm values given below
        studyArmEligibilityForm.setFev11(6.0f);
        studyArmEligibilityForm.setFev12(5.0f);
        studyArmEligibilityForm.setFev13(4.0f);
        studyArmEligibilityForm.setFev14(3.0f);
        studyArmEligibilityForm.setFev15(2.0f);

        // Pulmonary Evaluations FVCs preset to values given below
        studyArmEligibilityForm.setFvc1(6.0f);
        studyArmEligibilityForm.setFvc2(5.0f);
        studyArmEligibilityForm.setFvc3(4.0f);
        studyArmEligibilityForm.setFvc4(3.0f);
        studyArmEligibilityForm.setFvc5(2.0f);

        // Conformation of Enrollment consent form dates
        enrollmentForm.setDateInformedConsentSigned(date5);
        enrollmentForm.setDateInformedConsentVersion(date5);
    }

}
