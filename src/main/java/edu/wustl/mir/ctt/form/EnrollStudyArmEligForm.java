package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;

public class EnrollStudyArmEligForm extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"History and Physical or Consultation Note", "Operative Report of Transplant Procedure"};
    
    public EnrollStudyArmEligForm() {
        super();
        this.formType = ECPFormTypes.ENROLLMENT_STUDY_ARM_ASSIGNMENT;
        title = "Enrollment and Arm Eligibility Form";
        this.sourceDocumentTypes = SourceDocumentTypes;
        
        attributes.put("enrollmentDate", new AttributeDate("enrollmentDate"));        
        attributes.put("age", new AttributeString("age", true, false, true));
        attributes.put("medicare", new AttributeString("medicare"));
        attributes.put("lungTransplant", new AttributeString("lungTransplant", true, false, true));
        attributes.put("fiveFEV1sPostTrans", new AttributeString("fiveFEV1sPostTrans"));
        attributes.put("anotherTrial", new AttributeString("anotherTrial"));
        attributes.put("interfereCondition", new AttributeString("interfereCondition"));
        attributes.put("knownAllergy", new AttributeString("knownAllergy"));
        attributes.put("acuteCondition", new AttributeString("acuteCondition"));
        attributes.put("otherCondition", new AttributeString("otherCondition"));
        attributes.put("aphakia", new AttributeString("aphakia"));
        attributes.put("pregnancy", new AttributeString("pregnancy"));
        attributes.put("noInformedConsent", new AttributeString("noInformedConsent"));
//        attributes.put("dateEligConfirmed", new AttributeDate("dateEligConfirmed"));
        attributes.put("dateInformedConsentSigned", new AttributeDate("dateInformedConsentSigned"));
        attributes.put("dateInformedConsentVersion", new AttributeDate("dateInformedConsentVersion"));
        attributes.put("dateStudyRegistration", new AttributeDate("dateStudyRegistration"));
        
        
        attributes.put("date1", new AttributeDate("date1", true, false, true));
        attributes.put("date2", new AttributeDate("date2", true, false, true));
        attributes.put("date3", new AttributeDate("date3", true, false, true));
        attributes.put("date4", new AttributeDate("date4", true, false, true));
        attributes.put("date5", new AttributeDate("date5", true, false, true));
        attributes.put("date6", new AttributeDate("date6"));
        attributes.put("date7", new AttributeDate("date7"));
        attributes.put("date8", new AttributeDate("date8"));
        attributes.put("date9", new AttributeDate("date9"));
        attributes.put("date10", new AttributeDate("date10"));
        attributes.put("fev11", new AttributeFloat("fev11", true, false, true));
        attributes.put("fev12", new AttributeFloat("fev12", true, false, true));
        attributes.put("fev13", new AttributeFloat("fev13", true, false, true));
        attributes.put("fev14", new AttributeFloat("fev14", true, false, true));
        attributes.put("fev15", new AttributeFloat("fev15", true, false, true));
        attributes.put("fev16", new AttributeFloat("fev16"));
        attributes.put("fev17", new AttributeFloat("fev17"));
        attributes.put("fev18", new AttributeFloat("fev18"));
        attributes.put("fev19", new AttributeFloat("fev19"));
        attributes.put("fev110", new AttributeFloat("fev110"));
        attributes.put("fvc1", new AttributeFloat("fvc1", true, false, true));
        attributes.put("fvc2", new AttributeFloat("fvc2", true, false, true));
        attributes.put("fvc3", new AttributeFloat("fvc3", true, false, true));
        attributes.put("fvc4", new AttributeFloat("fvc4", true, false, true));
        attributes.put("fvc5", new AttributeFloat("fvc5", true, false, true));
        attributes.put("fvc6", new AttributeFloat("fvc6"));
        attributes.put("fvc7", new AttributeFloat("fvc7"));
        attributes.put("fvc8", new AttributeFloat("fvc8"));
        attributes.put("fvc9", new AttributeFloat("fvc9"));
        attributes.put("fvc10", new AttributeFloat("fvc10"));
    }
	
    public EnrollStudyArmEligForm( BasicForm bf) {
        super(bf);
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
    
    public Date getEnrollmentDate() {
        return (Date) attributes.get("enrollmentDate").getValue();
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        attributes.get("enrollmentDate").setValue(enrollmentDate);
    }

    public String getAge() {
        return (String) attributes.get("age").getValue();
    }
    
    public void setAge(String age) {
        attributes.get("age").setValue(age);
    }

    public VerificationStatus getAgeVerificationStatus() {
//        System.out.println("getAgeVerificationStatus was called containing: " + attributes.get("age").getVerificationStatus());
        return attributes.get("age").getVerificationStatus();
    }
    
    public void setAgeVerificationStatus(VerificationStatus verificationStatus) {
//        System.out.println("SET AgeVerificationStatus was called containing: " + verificationStatus);
        attributes.get("age").setVerificationStatus(verificationStatus);
    }

    public String getAgeDccComment() {
        return (String) attributes.get("age").getDccComment();
    }
    
    public void setAgeDccComment(String dccComment) {
        attributes.get("age").setDccComment(dccComment);
    }

    public String getMedicare() {
        return (String) attributes.get("medicare").getValue();
    }
    
    public void setMedicare(String medicare) {
        attributes.get("medicare").setValue(medicare);
    }

    public String getLungTransplant() {
        return (String) attributes.get("lungTransplant").getValue();
    }
    
    public void setLungTransplant(String lungTransplant) {
        attributes.get("lungTransplant").setValue(lungTransplant);
    }

    public VerificationStatus getLungTransplantVerificationStatus() {
        return attributes.get("lungTransplant").getVerificationStatus();
    }
    
    public void setLungTransplantVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("lungTransplant").setVerificationStatus(verificationStatus);
    }

    public String getLungTransplantDccComment() {
        return (String) attributes.get("lungTransplant").getDccComment();
    }
    
    public void setLungTransplantDccComment(String dccComment) {
        attributes.get("lungTransplant").setDccComment(dccComment);
    }
    public String getFiveFEV1sPostTrans() {
        return (String) attributes.get("fiveFEV1sPostTrans").getValue();
    }
    
    public void setFiveFEV1sPostTrans(String fiveFEV1sPostTrans) {
        attributes.get("fiveFEV1sPostTrans").setValue(fiveFEV1sPostTrans);
    }

    public String getAnotherTrial() {
        return (String) attributes.get("anotherTrial").getValue();
    }
    
    public void setAnotherTrial(String anotherTrial) {
        attributes.get("anotherTrial").setValue(anotherTrial);
    }

    public String getInterfereCondition() {
        return (String) attributes.get("interfereCondition").getValue();
    }
    
    public void setInterfereCondition(String integererfereCondition) {
        attributes.get("interfereCondition").setValue(integererfereCondition);
    }

    public String getKnownAllergy() {
        return (String) attributes.get("knownAllergy").getValue();
    }
    
    public void setKnownAllergy(String knownAllergy) {
        attributes.get("knownAllergy").setValue(knownAllergy);
    }

    public String getAcuteCondition() {
        return (String) attributes.get("acuteCondition").getValue();
    }
    
    public void setAcuteCondition(String acuteCondition) {
        attributes.get("acuteCondition").setValue(acuteCondition);
    }

    public String getOtherCondition() {
        return (String) attributes.get("otherCondition").getValue();
    }
    
    public void setOtherCondition(String otherCondition) {
        attributes.get("otherCondition").setValue(otherCondition);
    }

    public String getNoInformedConsent() {
        return (String) attributes.get("noInformedConsent").getValue();
    }
    
    public void setNoInformedConsent(String noInformedConsent) {
        attributes.get("noInformedConsent").setValue(noInformedConsent);
    }

    public String getAphakia() {
        return (String) attributes.get("aphakia").getValue();
    }
    
    public void setAphakia(String aphakia) {
        attributes.get("aphakia").setValue(aphakia);
    }

    public String getPregnancy() {
        return (String) attributes.get("pregnancy").getValue();
    }
    
    public void setPregnancy(String pregnancy) {
        attributes.get("pregnancy").setValue(pregnancy);
    }
/*
    public Date getDateEligConfirmed() {
        System.out.println("The EligibilityForm getDateEligConfirmed is: " + attributes.get("dateEligConfirmed").getValue());
        return (Date) attributes.get("dateEligConfirmed").getValue();
    }
    
    public void setDateEligConfirmed(Date dateEligConfirmed) {
        System.out.println("SET DateEligConfirmed is: " + dateEligConfirmed);
        attributes.get("dateEligConfirmed").setValue(dateEligConfirmed);
    }
*/
    public Date getDateInformedConsentSigned() {
        return (Date) attributes.get("dateInformedConsentSigned").getValue();
    }
    
    public void setDateInformedConsentSigned(Date dateInformedConsentSigned) {
        attributes.get("dateInformedConsentSigned").setValue(dateInformedConsentSigned);
    }

    public Date getDateInformedConsentVersion() {
        return (Date) attributes.get("dateInformedConsentVersion").getValue();
    }
    
    public void setDateInformedConsentVersion(Date dateInformedConsentVersion) {
        attributes.get("dateInformedConsentVersion").setValue(dateInformedConsentVersion);
    }

    
    
    
    
    public Date getDate1() {
        return (Date) attributes.get("date1").getValue();
    }
    
    public void setDate1(Date date1) {
        attributes.get("date1").setValue(date1);
    }

    public VerificationStatus getDate1VerificationStatus() {
        return attributes.get("date1").getVerificationStatus();
    }
    
    public void setDate1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date1").setVerificationStatus(verificationStatus);
    }

    public String getDate1DccComment() {
        return (String) attributes.get("date1").getDccComment();
    }
    
    public void setDate1DccComment(String dccComment) {
        attributes.get("date1").setDccComment(dccComment);
    }

    public Date getDate2() {
        return (Date) attributes.get("date2").getValue();
    }
    
    public void setDate2(Date date2) {
        attributes.get("date2").setValue(date2);
    }

    public VerificationStatus getDate2VerificationStatus() {
        return attributes.get("date2").getVerificationStatus();
    }
    
    public void setDate2VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date2").setVerificationStatus(verificationStatus);
    }

    public String getDate2DccComment() {
        return (String) attributes.get("date2").getDccComment();
    }
    
    public void setDate2DccComment(String dccComment) {
        attributes.get("date2").setDccComment(dccComment);
    }

    public Date getDate3() {
        return (Date) attributes.get("date3").getValue();
    }
    
    public void setDate3(Date date3) {
        attributes.get("date3").setValue(date3);
    }

    public VerificationStatus getDate3VerificationStatus() {
        return attributes.get("date3").getVerificationStatus();
    }
    
    public void setDate3VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date3").setVerificationStatus(verificationStatus);
    }

    public String getDate3DccComment() {
        return (String) attributes.get("date3").getDccComment();
    }
    
    public void setDate3DccComment(String dccComment) {
        attributes.get("date3").setDccComment(dccComment);
    }

    public Date getDate4() {
        return (Date) attributes.get("date4").getValue();
    }
    
    public void setDate4(Date date4) {
        attributes.get("date4").setValue(date4);
    }

    public VerificationStatus getDate4VerificationStatus() {
        return attributes.get("date4").getVerificationStatus();
    }
    
    public void setDate4VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date4").setVerificationStatus(verificationStatus);
    }

    public String getDate4DccComment() {
        return (String) attributes.get("date4").getDccComment();
    }
    
    public void setDate4DccComment(String dccComment) {
        attributes.get("date4").setDccComment(dccComment);
    }

    public Date getDate5() {
        return (Date) attributes.get("date5").getValue();
    }
    
    public void setDate5(Date date5) {
        attributes.get("date5").setValue(date5);
    }

    public VerificationStatus getDate5VerificationStatus() {
        return attributes.get("date5").getVerificationStatus();
    }
    
    public void setDate5VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date5").setVerificationStatus(verificationStatus);
    }

    public String getDate5DccComment() {
        return (String) attributes.get("date5").getDccComment();
    }
    
    public void setDate5DccComment(String dccComment) {
        attributes.get("date5").setDccComment(dccComment);
    }

    public Date getDate6() {
        return (Date) attributes.get("date6").getValue();
    }
    
    public void setDate6(Date date6) {
        attributes.get("date6").setValue(date6);
    }

    public Date getDate7() {
        return (Date) attributes.get("date7").getValue();
    }
    
    public void setDate7(Date date7) {
        attributes.get("date7").setValue(date7);
    }

    public Date getDate8() {
        return (Date) attributes.get("date8").getValue();
    }
    
    public void setDate8(Date date8) {
        attributes.get("date8").setValue(date8);
    }

    public Date getDate9() {
        return (Date) attributes.get("date9").getValue();
    }
    
    public void setDate9(Date date9) {
        attributes.get("date9").setValue(date9);
    }

    public Date getDate10() {
        return (Date) attributes.get("date10").getValue();
    }
    
    public void setDate10(Date date10) {
        attributes.get("date10").setValue(date10);
    }

    public Float getFev11() {
        return (Float) attributes.get("fev11").getValue();
    }
    
    public void setFev11(Float fev11) {
        attributes.get("fev11").setValue(fev11);
    }

    public VerificationStatus getFev11VerificationStatus() {
        return attributes.get("fev11").getVerificationStatus();
    }
    
    public void setFev11VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev11").setVerificationStatus(verificationStatus);
    }

    public String getFev11DccComment() {
        return (String) attributes.get("fev11").getDccComment();
    }
    
    public void setFev11DccComment(String dccComment) {
        attributes.get("fev11").setDccComment(dccComment);
    }

    public Float getFev12() {
        return (Float) attributes.get("fev12").getValue();
    }
    
    public void setFev12(Float fev12) {
        attributes.get("fev12").setValue(fev12);
    }

    public VerificationStatus getFev12VerificationStatus() {
        return attributes.get("fev12").getVerificationStatus();
    }
    
    public void setFev12VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev12").setVerificationStatus(verificationStatus);
    }

    public String getFev12DccComment() {
        return (String) attributes.get("fev12").getDccComment();
    }
    
    public void setFev12DccComment(String dccComment) {
        attributes.get("fev12").setDccComment(dccComment);
    }

    public Float getFev13() {
        return (Float) attributes.get("fev13").getValue();
    }
    
    public void setFev13(Float fev13) {
        attributes.get("fev13").setValue(fev13);
    }

    public VerificationStatus getFev13VerificationStatus() {
        return attributes.get("fev13").getVerificationStatus();
    }
    
    public void setFev13VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev13").setVerificationStatus(verificationStatus);
    }

    public String getFev13DccComment() {
        return (String) attributes.get("fev13").getDccComment();
    }
    
    public void setFev13DccComment(String dccComment) {
        attributes.get("fev13").setDccComment(dccComment);
    }

    public Float getFev14() {
        return (Float) attributes.get("fev14").getValue();
    }
    
    public void setFev14(Float fev14) {
        attributes.get("fev14").setValue(fev14);
    }

    public VerificationStatus getFev14VerificationStatus() {
        return attributes.get("fev14").getVerificationStatus();
    }
    
    public void setFev14VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev14").setVerificationStatus(verificationStatus);
    }

    public String getFev14DccComment() {
        return (String) attributes.get("fev14").getDccComment();
    }
    
    public void setFev14DccComment(String dccComment) {
        attributes.get("fev14").setDccComment(dccComment);
    }

    public Float getFev15() {
        return (Float) attributes.get("fev15").getValue();
    }
    
    public void setFev15(Float fev15) {
        attributes.get("fev15").setValue(fev15);
    }

    public VerificationStatus getFev15VerificationStatus() {
        return attributes.get("fev15").getVerificationStatus();
    }
    
    public void setFev15VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev15").setVerificationStatus(verificationStatus);
    }

    public String getFev15DccComment() {
        return (String) attributes.get("fev15").getDccComment();
    }
    
    public void setFev15DccComment(String dccComment) {
        attributes.get("fev15").setDccComment(dccComment);
    }

    public Float getFev16() {
        return (Float) attributes.get("fev16").getValue();
    }
    
    public void setFev16(Float fev16) {
        attributes.get("fev16").setValue(fev16);
    }

    public Float getFev17() {
        return (Float) attributes.get("fev17").getValue();
    }
    
    public void setFev17(Float fev17) {
        attributes.get("fev17").setValue(fev17);
    }

    public Float getFev18() {
        return (Float) attributes.get("fev18").getValue();
    }
    
    public void setFev18(Float fev18) {
        attributes.get("fev18").setValue(fev18);
    }

    public Float getFev19() {
        return (Float) attributes.get("fev19").getValue();
    }
    
    public void setFev19(Float fev19) {
        attributes.get("fev19").setValue(fev19);
    }

    public Float getFev110() {
        return (Float) attributes.get("fev110").getValue();
    }
    
    public void setFev110(Float fev110) {
        attributes.get("fev110").setValue(fev110);
    }

    public Float getFvc1() {
        return (Float) attributes.get("fvc1").getValue();
    }
    
    public void setFvc1(Float fvc1) {
        attributes.get("fvc1").setValue(fvc1);
    }

    public VerificationStatus getFvc1VerificationStatus() {
        return attributes.get("fvc1").getVerificationStatus();
    }
    
    public void setFvc1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc1").setVerificationStatus(verificationStatus);
    }
  
    public String getFvc1DccComment() {
        return (String) attributes.get("fvc1").getDccComment();
    }
    
    public void setFvc1DccComment(String dccComment) {
        attributes.get("fvc1").setDccComment(dccComment);
    }

    public Float getFvc2() {
        return (Float) attributes.get("fvc2").getValue();
    }
    
    public void setFvc2(Float fvc2) {
        attributes.get("fvc2").setValue(fvc2);
    }

    public VerificationStatus getFvc2VerificationStatus() {
        return attributes.get("fvc2").getVerificationStatus();
    }
    
    public void setFvc2VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc2").setVerificationStatus(verificationStatus);
    }

    public String getFvc2DccComment() {
        return (String) attributes.get("fvc2").getDccComment();
    }
    
    public void setFvc2DccComment(String dccComment) {
        attributes.get("fvc2").setDccComment(dccComment);
    }

    public Float getFvc3() {
        return (Float) attributes.get("fvc3").getValue();
    }
    
    public void setFvc3(Float fvc3) {
        attributes.get("fvc3").setValue(fvc3);
    }

    public VerificationStatus getFvc3VerificationStatus() {
        return attributes.get("fvc3").getVerificationStatus();
    }
    
    public void setFvc3VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc3").setVerificationStatus(verificationStatus);
    }

    public String getFvc3DccComment() {
        return (String) attributes.get("fvc3").getDccComment();
    }
    
    public void setFvc3DccComment(String dccComment) {
        attributes.get("fvc3").setDccComment(dccComment);
    }

    public Float getFvc4() {
        return (Float) attributes.get("fvc4").getValue();
    }
    
    public void setFvc4(Float fvc4) {
        attributes.get("fvc4").setValue(fvc4);
    }

    public VerificationStatus getFvc4VerificationStatus() {
        return attributes.get("fvc4").getVerificationStatus();
    }
    
    public void setFvc4VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc4").setVerificationStatus(verificationStatus);
    }

    public String getFvc4DccComment() {
        return (String) attributes.get("fvc4").getDccComment();
    }
    
    public void setFvc4DccComment(String dccComment) {
        attributes.get("fvc4").setDccComment(dccComment);
    }

    public Float getFvc5() {
        return (Float) attributes.get("fvc5").getValue();
    }
    
    public void setFvc5(Float fvc5) {
        attributes.get("fvc5").setValue(fvc5);
    }

    public VerificationStatus getFvc5VerificationStatus() {
        return attributes.get("fvc5").getVerificationStatus();
    }
    
    public void setFvc5VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc5").setVerificationStatus(verificationStatus);
    }

    public String getFvc5DccComment() {
        return (String) attributes.get("fvc5").getDccComment();
    }
    
    public void setFvc5DccComment(String dccComment) {
        attributes.get("fvc5").setDccComment(dccComment);
    }

    public Float getFvc6() {
        return (Float) attributes.get("fvc6").getValue();
    }
    
    public void setFvc6(Float fvc6) {
        attributes.get("fvc6").setValue(fvc6);
    }

    public Float getFvc7() {
        return (Float) attributes.get("fvc7").getValue();
    }
    
    public void setFvc7(Float fvc7) {
        attributes.get("fvc7").setValue(fvc7);
    }

    public Float getFvc8() {
        return (Float) attributes.get("fvc8").getValue();
    }
    
    public void setFvc8(Float fvc8) {
        attributes.get("fvc8").setValue(fvc8);
    }

    public Float getFvc9() {
        return (Float) attributes.get("fvc9").getValue();
    }
    
    public void setFvc9(Float fvc9) {
        attributes.get("fvc9").setValue(fvc9);
    }

    public Float getFvc10() {
        return (Float) attributes.get("fvc10").getValue();
    }
    
    public void setFvc10(Float fvc10) {
        attributes.get("fvc10").setValue(fvc10);
    }


    
}
