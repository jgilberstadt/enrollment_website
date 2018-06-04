package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.Attribute;
import edu.wustl.mir.ctt.model.AttributeBoolean;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class EligibilityForm extends BasicForm {
    
    private static final int MAX_NUMBER_FEV1S = 26;
    public static final String[] SourceDocumentTypes = new String[]{"A Signed Confirmation of Eligibility Form must be uploaded", "History and Physical or Consultation Note", "Operative Report of Transplant Procedure", "Pulmonary Function Test Reports (for each FEV-1 submitted)", "Complete Blood Count (CBC) Report", "Source or FEV1 noting date BOS diagnosed" };
    
    public EligibilityForm() {
        super();
        this.formType = ECPFormTypes.ELIGIBILITY;
        title = "Confirmation of Eligibility Form";
        
        // Remove unused source documents from older version
        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion())) {
           List<String> a = new ArrayList(Arrays.asList(SourceDocumentTypes));
           
           a.remove("Complete Blood Count (CBC) Report");
           a.remove("Source or FEV1 noting date BOS diagnosed and Refractory BOS diagnosed");

           this.sourceDocumentTypes = a.toArray(new String[a.size()]);
        } else {
           this.sourceDocumentTypes = SourceDocumentTypes;
        }

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
        attributes.put("leukopenia", new AttributeString("leukopenia"));
//        attributes.put("dateEligConfirmed", new AttributeDate("dateEligConfirmed"));
        attributes.put("dateInformedConsentSigned",  new AttributeDate("dateInformedConsentSigned"));
        attributes.put("dateInformedConsentVersion", new AttributeDate("dateInformedConsentVersion"));
        attributes.put("wasCoeUploaded", new AttributeString("wasCoeUploaded", true, false, true));
//        attributes.put("dateStudyRegistration", new AttributeDate("dateStudyRegistration"));

        attributes.put("date1", new AttributeDate("date1", true, false, true));
        attributes.put("date2", new AttributeDate("date2", true, false, true));
        attributes.put("date3", new AttributeDate("date3", true, false, true));
        attributes.put("date4", new AttributeDate("date4", true, false, true));
        attributes.put("date5", new AttributeDate("date5", true, false, true));
        attributes.put("date6", new AttributeDate("date6", true, true, true));
        attributes.put("date7", new AttributeDate("date7", true, true, true));
        attributes.put("date8", new AttributeDate("date8", true, true, true));
        attributes.put("date9", new AttributeDate("date9", true, true, true));
        attributes.put("date10", new AttributeDate("date10", true, true, true));
        attributes.put("date11", new AttributeDate("date11", true, true, true));
        attributes.put("date12", new AttributeDate("date12", true, true, true));
        attributes.put("date13", new AttributeDate("date13", true, true, true));
        attributes.put("date14", new AttributeDate("date14", true, true, true));
        attributes.put("date15", new AttributeDate("date15", true, true, true));
        attributes.put("date16", new AttributeDate("date16", true, true, true));
        attributes.put("date17", new AttributeDate("date17", true, true, true));
        attributes.put("date18", new AttributeDate("date18", true, true, true));
        attributes.put("date19", new AttributeDate("date19", true, true, true));
        attributes.put("date20", new AttributeDate("date20", true, true, true));
        attributes.put("date21", new AttributeDate("date21", true, true, true));
        attributes.put("date22", new AttributeDate("date22", true, true, true));
        attributes.put("date23", new AttributeDate("date23", true, true, true));
        attributes.put("date24", new AttributeDate("date24", true, true, true));
        attributes.put("date25", new AttributeDate("date25", true, true, true));
        attributes.put("date26", new AttributeDate("date26", true, true, true));
        attributes.put("fev11", new AttributeFloat("fev11", true, false, true));
        attributes.put("fev12", new AttributeFloat("fev12", true, false, true));
        attributes.put("fev13", new AttributeFloat("fev13", true, false, true));
        attributes.put("fev14", new AttributeFloat("fev14", true, false, true));
        attributes.put("fev15", new AttributeFloat("fev15", true, false, true));
        attributes.put("fev16", new AttributeFloat("fev16", true, true, true));
        attributes.put("fev17", new AttributeFloat("fev17", true, true, true));
        attributes.put("fev18", new AttributeFloat("fev18", true, true, true));
        attributes.put("fev19", new AttributeFloat("fev19", true, true, true));
        attributes.put("fev110", new AttributeFloat("fev110", true, true, true));
        attributes.put("fev111", new AttributeFloat("fev111", true, true, true));
        attributes.put("fev112", new AttributeFloat("fev112", true, true, true));
        attributes.put("fev113", new AttributeFloat("fev113", true, true, true));
        attributes.put("fev114", new AttributeFloat("fev114", true, true, true));
        attributes.put("fev115", new AttributeFloat("fev115", true, true, true));
        attributes.put("fev116", new AttributeFloat("fev116", true, true, true));
        attributes.put("fev117", new AttributeFloat("fev117", true, true, true));
        attributes.put("fev118", new AttributeFloat("fev118", true, true, true));
        attributes.put("fev119", new AttributeFloat("fev119", true, true, true));
        attributes.put("fev120", new AttributeFloat("fev120", true, true, true));
        attributes.put("fev121", new AttributeFloat("fev121", true, true, true));
        attributes.put("fev122", new AttributeFloat("fev122", true, true, true));
        attributes.put("fev123", new AttributeFloat("fev123", true, true, true));
        attributes.put("fev124", new AttributeFloat("fev124", true, true, true));
        attributes.put("fev125", new AttributeFloat("fev125", true, true, true));
        attributes.put("fev126", new AttributeFloat("fev126", true, true, true));
        attributes.put("fvc1", new AttributeFloat("fvc1", true, false, true));
        attributes.put("fvc2", new AttributeFloat("fvc2", true, false, true));
        attributes.put("fvc3", new AttributeFloat("fvc3", true, false, true));
        attributes.put("fvc4", new AttributeFloat("fvc4", true, false, true));
        attributes.put("fvc5", new AttributeFloat("fvc5", true, false, true));
        attributes.put("fvc6", new AttributeFloat("fvc6", true, true, true));
        attributes.put("fvc7", new AttributeFloat("fvc7", true, true, true));
        attributes.put("fvc8", new AttributeFloat("fvc8", true, true, true));
        attributes.put("fvc9", new AttributeFloat("fvc9", true, true, true));
        attributes.put("fvc10", new AttributeFloat("fvc10", true, true, true));
        attributes.put("fvc11", new AttributeFloat("fvc11", true, true, true));
        attributes.put("fvc12", new AttributeFloat("fvc12", true, true, true));
        attributes.put("fvc13", new AttributeFloat("fvc13", true, true, true));
        attributes.put("fvc14", new AttributeFloat("fvc14", true, true, true));
        attributes.put("fvc15", new AttributeFloat("fvc15", true, true, true));
        attributes.put("fvc16", new AttributeFloat("fvc16", true, true, true));
        attributes.put("fvc17", new AttributeFloat("fvc17", true, true, true));
        attributes.put("fvc18", new AttributeFloat("fvc18", true, true, true));
        attributes.put("fvc19", new AttributeFloat("fvc19", true, true, true));
        attributes.put("fvc20", new AttributeFloat("fvc20", true, true, true));
        attributes.put("fvc21", new AttributeFloat("fvc21", true, true, true));
        attributes.put("fvc22", new AttributeFloat("fvc22", true, true, true));
        attributes.put("fvc23", new AttributeFloat("fvc23", true, true, true));
        attributes.put("fvc24", new AttributeFloat("fvc24", true, true, true));
        attributes.put("fvc25", new AttributeFloat("fvc25", true, true, true));
        attributes.put("fvc26", new AttributeFloat("fvc26", true, true, true));
        
        attributes.put("comment", new AttributeString("comment"));
        attributes.put("slope", new AttributeFloat("slope"));
        attributes.put("significance", new AttributeFloat("significance"));

        attributes.put("baselineFEV1", new AttributeFloat("baselineFEV1", true, false, true));
        attributes.put("firstComponentFEV1Date", new AttributeDate("firstComponentFEV1Date", true, false, true));
        attributes.put("firstComponentFEV1", new AttributeFloat("firstComponentFEV1", true, false, true));
        attributes.put("firstComponentFVC", new AttributeFloat("firstComponentFVC", true, false, true));
        attributes.put("secondComponentFEV1Date", new AttributeDate("secondComponentFEV1Date", true, false, true));
        attributes.put("secondComponentFEV1", new AttributeFloat("secondComponentFEV1", true, false, true));
        attributes.put("secondComponentFVC", new AttributeFloat("secondComponentFVC", true, false, true));
        
        attributes.put("postTransBOSDiagDate", new AttributeDate("postTransBOSDiagDate"));
        attributes.put("firstPostTransBOSDiagFEV1Date", new AttributeDate("firstPostTransBOSDiagFEV1Date", true, false, true));
        attributes.put("firstPostTransBOSDiagFEV1", new AttributeFloat("firstPostTransBOSDiagFEV1", true, false, true));
        attributes.put("firstPostTransBOSDiagFVC", new AttributeFloat("firstPostTransBOSDiagFVC", true, false, true));
        attributes.put("secondPostTransBOSDiagFEV1Date", new AttributeDate("secondPostTransBOSDiagFEV1Date", true, false, true));
        attributes.put("secondPostTransBOSDiagFEV1", new AttributeFloat("secondPostTransBOSDiagFEV1", true, false, true));
        attributes.put("secondPostTransBOSDiagFVC", new AttributeFloat("secondPostTransBOSDiagFVC", true, false, true));
        attributes.put("thirdPostTransBOSDiagFEV1Date", new AttributeDate("thirdPostTransBOSDiagFEV1Date", true, true, true));
        attributes.put("thirdPostTransBOSDiagFEV1", new AttributeFloat("thirdPostTransBOSDiagFEV1", true, true, true));
        attributes.put("thirdPostTransBOSDiagFVC", new AttributeFloat("thirdPostTransBOSDiagFVC", true, true, true));

        attributes.put("lungTransplantationDate", new AttributeDate("lungTransplantationDate", true, false, true));
        
        attributes.put("mostRecentExamDate", new AttributeDate("mostRecentExamDate"));
        
        attributes.put("completeBloodCountDate", new AttributeDate("completeBloodCountDate", true, false, true));
        attributes.put("wbcs", new AttributeFloat("wbcs", true, false, true));
        attributes.put("rbcs", new AttributeFloat("rbcs", true, false, true));
        attributes.put("hemoglobin", new AttributeFloat("hemoglobin", true, false, true));
        attributes.put("hematocrit", new AttributeFloat("hematocrit", true, false, true));
        attributes.put("platelets", new AttributeFloat("platelets", true, false, true));
        
        attributes.put("labBasedNewBOSDiagnosis", new AttributeString("labBasedNewBOSDiagnosis"));
        attributes.put("preDiagnosisMonitoring", new AttributeString("preDiagnosisMonitoring"));
        attributes.put("documentedClinicalAssessment", new AttributeString("documentedClinicalAssessment"));
        attributes.put("leukopenia3000", new AttributeString("leukopenia3000"));
        attributes.put("declineNotBOS", new AttributeString("declineNotBOS"));
        attributes.put("acuteDeclineTreatment", new AttributeString("acuteDeclineTreatment"));
    }
	
    public EligibilityForm( BasicForm bf) {
        super(bf);
        title = bf.getTitle();
        
        // Remove unused source documents from older version
        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion())) {
           List<String> a = new ArrayList(Arrays.asList(SourceDocumentTypes));
           
           a.remove("Complete Blood Count (CBC) Report");
           a.remove("Source or FEV1 noting date BOS diagnosed and Refractory BOS diagnosed");

           this.sourceDocumentTypes = a.toArray(new String[a.size()]);
        } else {
           this.sourceDocumentTypes = SourceDocumentTypes;
        }
    }
    
    @Override
    public void versionControl() {
        if ("7.1".equals(this.crfVersion)) {
            attributes.put("baselineQualityOfLifeCompleted", new AttributeBoolean("baselineQualityOfLifeCompleted"));
            attributes.put("baselineQualityOfLifeDate", new AttributeDate("baselineQualityOfLifeDate"));
            attributes.put("baselineQualityOfLifeScreenId", new AttributeString("baselineQualityOfLifeScreenId"));
        }
    }
    
    public void addLeukopeniaAttribute() {
        attributes.put("leukopenia", new AttributeString("leukopenia"));
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
    
    public String getLeukopenia() {
        return (String) attributes.get("leukopenia").getValue();
    }
    
    public void setLeukopenia(String leukopenia) {
        attributes.get("leukopenia").setValue(leukopenia);
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


    public String getWasCoeUploaded() {
        return (String) attributes.get("wasCoeUploaded").getValue();
    }
    
    public void setWasCoeUploaded(String wasCoeUploaded) {
        attributes.get("wasCoeUploaded").setValue(wasCoeUploaded);
    }

    public VerificationStatus getWasCoeUploadedVerificationStatus() {
        System.out.println("getWasCoeUploadedVerificationStatus was called containing: " + attributes.get("wasCoeUploaded").getVerificationStatus());
        return attributes.get("wasCoeUploaded").getVerificationStatus();
    }
    
    public void setWasCoeUploadedVerificationStatus(VerificationStatus verificationStatus) {
        System.out.println("SET WasCoeUploadedVerificationStatus was called containing: " + verificationStatus);
        attributes.get("wasCoeUploaded").setVerificationStatus(verificationStatus);
    }

    public String getWasCoeUploadedDccComment() {
        return (String) attributes.get("wasCoeUploaded").getDccComment();
    }
    
    public void setWasCoeUploadedDccComment(String dccComment) {
        attributes.get("wasCoeUploaded").setDccComment(dccComment);
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

    public VerificationStatus getDate6VerificationStatus() {
        return attributes.get("date6").getVerificationStatus();
    }
    
    public void setDate6VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date6").setVerificationStatus(verificationStatus);
    }

    public String getDate6DccComment() {
        return (String) attributes.get("date6").getDccComment();
    }
    
    public void setDate6DccComment(String dccComment) {
        attributes.get("date6").setDccComment(dccComment);
    }

    public Date getDate7() {
        return (Date) attributes.get("date7").getValue();
    }
    
    public void setDate7(Date date7) {
        attributes.get("date7").setValue(date7);
    }

    public VerificationStatus getDate7VerificationStatus() {
        return attributes.get("date7").getVerificationStatus();
    }
    
    public void setDate7VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date7").setVerificationStatus(verificationStatus);
    }

    public String getDate7DccComment() {
        return (String) attributes.get("date7").getDccComment();
    }
    
    public void setDate7DccComment(String dccComment) {
        attributes.get("date7").setDccComment(dccComment);
    }

    public Date getDate8() {
        return (Date) attributes.get("date8").getValue();
    }
    
    public void setDate8(Date date8) {
        attributes.get("date8").setValue(date8);
    }

    public VerificationStatus getDate8VerificationStatus() {
        return attributes.get("date8").getVerificationStatus();
    }
    
    public void setDate8VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date8").setVerificationStatus(verificationStatus);
    }

    public String getDate8DccComment() {
        return (String) attributes.get("date8").getDccComment();
    }
    
    public void setDate8DccComment(String dccComment) {
        attributes.get("date8").setDccComment(dccComment);
    }

    public Date getDate9() {
        return (Date) attributes.get("date9").getValue();
    }
    
    public void setDate9(Date date9) {
        attributes.get("date9").setValue(date9);
    }

    public VerificationStatus getDate9VerificationStatus() {
        return attributes.get("date9").getVerificationStatus();
    }
    
    public void setDate9VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date9").setVerificationStatus(verificationStatus);
    }

    public String getDate9DccComment() {
        return (String) attributes.get("date9").getDccComment();
    }
    
    public void setDate9DccComment(String dccComment) {
        attributes.get("date9").setDccComment(dccComment);
    }

    public Date getDate10() {
        return (Date) attributes.get("date10").getValue();
    }
    
    public void setDate10(Date date10) {
        attributes.get("date10").setValue(date10);
    }

    public VerificationStatus getDate10VerificationStatus() {
        return attributes.get("date10").getVerificationStatus();
    }
    
    public void setDate10VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date10").setVerificationStatus(verificationStatus);
    }

    public String getDate10DccComment() {
        return (String) attributes.get("date10").getDccComment();
    }
    
    public void setDate10DccComment(String dccComment) {
        attributes.get("date10").setDccComment(dccComment);
    }

    public Date getDate11() {
        return (Date) attributes.get("date11").getValue();
    }
    
    public void setDate11(Date date11) {
        attributes.get("date11").setValue(date11);
    }

    public VerificationStatus getDate11VerificationStatus() {
        return attributes.get("date11").getVerificationStatus();
    }
    
    public void setDate11VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date11").setVerificationStatus(verificationStatus);
    }

    public String getDate11DccComment() {
        return (String) attributes.get("date11").getDccComment();
    }
    
    public void setDate11DccComment(String dccComment) {
        attributes.get("date11").setDccComment(dccComment);
    }

    public Date getDate12() {
        return (Date) attributes.get("date12").getValue();
    }
    
    public void setDate12(Date date12) {
        attributes.get("date12").setValue(date12);
    }

    public VerificationStatus getDate12VerificationStatus() {
        return attributes.get("date12").getVerificationStatus();
    }
    
    public void setDate12VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date12").setVerificationStatus(verificationStatus);
    }

    public String getDate12DccComment() {
        return (String) attributes.get("date12").getDccComment();
    }
    
    public void setDate12DccComment(String dccComment) {
        attributes.get("date12").setDccComment(dccComment);
    }

    public Date getDate13() {
        return (Date) attributes.get("date13").getValue();
    }
    
    public void setDate13(Date date13) {
        attributes.get("date13").setValue(date13);
    }

    public VerificationStatus getDate13VerificationStatus() {
        return attributes.get("date13").getVerificationStatus();
    }
    
    public void setDate13VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date13").setVerificationStatus(verificationStatus);
    }

    public String getDate13DccComment() {
        return (String) attributes.get("date13").getDccComment();
    }
    
    public void setDate13DccComment(String dccComment) {
        attributes.get("date13").setDccComment(dccComment);
    }

    public Date getDate14() {
        return (Date) attributes.get("date14").getValue();
    }
    
    public void setDate14(Date date14) {
        attributes.get("date14").setValue(date14);
    }

    public VerificationStatus getDate14VerificationStatus() {
        return attributes.get("date14").getVerificationStatus();
    }
    
    public void setDate14VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date14").setVerificationStatus(verificationStatus);
    }

    public String getDate14DccComment() {
        return (String) attributes.get("date14").getDccComment();
    }
    
    public void setDate14DccComment(String dccComment) {
        attributes.get("date14").setDccComment(dccComment);
    }

    public Date getDate15() {
        return (Date) attributes.get("date15").getValue();
    }
    
    public void setDate15(Date date15) {
        attributes.get("date15").setValue(date15);
    }

    public VerificationStatus getDate15VerificationStatus() {
        return attributes.get("date15").getVerificationStatus();
    }
    
    public void setDate15VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date15").setVerificationStatus(verificationStatus);
    }

    public String getDate15DccComment() {
        return (String) attributes.get("date15").getDccComment();
    }
    
    public void setDate15DccComment(String dccComment) {
        attributes.get("date15").setDccComment(dccComment);
    }

    public Date getDate16() {
        return (Date) attributes.get("date16").getValue();
    }

    public void setDate16(Date date16) {
        attributes.get("date16").setValue(date16);
    }

    public VerificationStatus getDate16VerificationStatus() {
        return attributes.get("date16").getVerificationStatus();
    }

    public void setDate16VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date16").setVerificationStatus(verificationStatus);
    }

    public String getDate16DccComment() {
        return (String) attributes.get("date16").getDccComment();
    }

    public void setDate16DccComment(String dccComment) {
        attributes.get("date16").setDccComment(dccComment);
    }
    public Date getDate17() {
        return (Date) attributes.get("date17").getValue();
    }

    public void setDate17(Date date17) {
        attributes.get("date17").setValue(date17);
    }

    public VerificationStatus getDate17VerificationStatus() {
        return attributes.get("date17").getVerificationStatus();
    }

    public void setDate17VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date17").setVerificationStatus(verificationStatus);
    }

    public String getDate17DccComment() {
        return (String) attributes.get("date17").getDccComment();
    }

    public void setDate17DccComment(String dccComment) {
        attributes.get("date17").setDccComment(dccComment);
    }
    public Date getDate18() {
        return (Date) attributes.get("date18").getValue();
    }

    public void setDate18(Date date18) {
        attributes.get("date18").setValue(date18);
    }

    public VerificationStatus getDate18VerificationStatus() {
        return attributes.get("date18").getVerificationStatus();
    }

    public void setDate18VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date18").setVerificationStatus(verificationStatus);
    }

    public String getDate18DccComment() {
        return (String) attributes.get("date18").getDccComment();
    }

    public void setDate18DccComment(String dccComment) {
        attributes.get("date18").setDccComment(dccComment);
    }
    public Date getDate19() {
        return (Date) attributes.get("date19").getValue();
    }

    public void setDate19(Date date19) {
        attributes.get("date19").setValue(date19);
    }

    public VerificationStatus getDate19VerificationStatus() {
        return attributes.get("date19").getVerificationStatus();
    }

    public void setDate19VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date19").setVerificationStatus(verificationStatus);
    }

    public String getDate19DccComment() {
        return (String) attributes.get("date19").getDccComment();
    }

    public void setDate19DccComment(String dccComment) {
        attributes.get("date19").setDccComment(dccComment);
    }
    public Date getDate20() {
        return (Date) attributes.get("date20").getValue();
    }

    public void setDate20(Date date20) {
        attributes.get("date20").setValue(date20);
    }

    public VerificationStatus getDate20VerificationStatus() {
        return attributes.get("date20").getVerificationStatus();
    }

    public void setDate20VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date20").setVerificationStatus(verificationStatus);
    }

    public String getDate20DccComment() {
        return (String) attributes.get("date20").getDccComment();
    }

    public void setDate20DccComment(String dccComment) {
        attributes.get("date20").setDccComment(dccComment);
    }
    public Date getDate21() {
        return (Date) attributes.get("date21").getValue();
    }

    public void setDate21(Date date21) {
        attributes.get("date21").setValue(date21);
    }

    public VerificationStatus getDate21VerificationStatus() {
        return attributes.get("date21").getVerificationStatus();
    }

    public void setDate21VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date21").setVerificationStatus(verificationStatus);
    }

    public String getDate21DccComment() {
        return (String) attributes.get("date21").getDccComment();
    }

    public void setDate21DccComment(String dccComment) {
        attributes.get("date21").setDccComment(dccComment);
    }
    public Date getDate22() {
        return (Date) attributes.get("date22").getValue();
    }

    public void setDate22(Date date22) {
        attributes.get("date22").setValue(date22);
    }

    public VerificationStatus getDate22VerificationStatus() {
        return attributes.get("date22").getVerificationStatus();
    }

    public void setDate22VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date22").setVerificationStatus(verificationStatus);
    }

    public String getDate22DccComment() {
        return (String) attributes.get("date22").getDccComment();
    }

    public void setDate22DccComment(String dccComment) {
        attributes.get("date22").setDccComment(dccComment);
    }
    public Date getDate23() {
        return (Date) attributes.get("date23").getValue();
    }

    public void setDate23(Date date23) {
        attributes.get("date23").setValue(date23);
    }

    public VerificationStatus getDate23VerificationStatus() {
        return attributes.get("date23").getVerificationStatus();
    }

    public void setDate23VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date23").setVerificationStatus(verificationStatus);
    }

    public String getDate23DccComment() {
        return (String) attributes.get("date23").getDccComment();
    }

    public void setDate23DccComment(String dccComment) {
        attributes.get("date23").setDccComment(dccComment);
    }
    public Date getDate24() {
        return (Date) attributes.get("date24").getValue();
    }

    public void setDate24(Date date24) {
        attributes.get("date24").setValue(date24);
    }

    public VerificationStatus getDate24VerificationStatus() {
        return attributes.get("date24").getVerificationStatus();
    }

    public void setDate24VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date24").setVerificationStatus(verificationStatus);
    }

    public String getDate24DccComment() {
        return (String) attributes.get("date24").getDccComment();
    }

    public void setDate24DccComment(String dccComment) {
        attributes.get("date24").setDccComment(dccComment);
    }
    public Date getDate25() {
        return (Date) attributes.get("date25").getValue();
    }

    public void setDate25(Date date25) {
        attributes.get("date25").setValue(date25);
    }

    public VerificationStatus getDate25VerificationStatus() {
        return attributes.get("date25").getVerificationStatus();
    }

    public void setDate25VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date25").setVerificationStatus(verificationStatus);
    }

    public String getDate25DccComment() {
        return (String) attributes.get("date25").getDccComment();
    }

    public void setDate25DccComment(String dccComment) {
        attributes.get("date25").setDccComment(dccComment);
    }
    public Date getDate26() {
        return (Date) attributes.get("date26").getValue();
    }

    public void setDate26(Date date26) {
        attributes.get("date26").setValue(date26);
    }

    public VerificationStatus getDate26VerificationStatus() {
        return attributes.get("date26").getVerificationStatus();
    }

    public void setDate26VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date26").setVerificationStatus(verificationStatus);
    }

    public String getDate26DccComment() {
        return (String) attributes.get("date26").getDccComment();
    }

    public void setDate26DccComment(String dccComment) {
        attributes.get("date26").setDccComment(dccComment);
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

    public VerificationStatus getFev16VerificationStatus() {
        return attributes.get("fev16").getVerificationStatus();
    }
    
    public void setFev16VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev16").setVerificationStatus(verificationStatus);
    }

    public String getFev16DccComment() {
        return (String) attributes.get("fev16").getDccComment();
    }
    
    public void setFev16DccComment(String dccComment) {
        attributes.get("fev16").setDccComment(dccComment);
    }

    public Float getFev17() {
        return (Float) attributes.get("fev17").getValue();
    }
    
    public void setFev17(Float fev17) {
        attributes.get("fev17").setValue(fev17);
    }

    public VerificationStatus getFev17VerificationStatus() {
        return attributes.get("fev17").getVerificationStatus();
    }
    
    public void setFev17VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev17").setVerificationStatus(verificationStatus);
    }

    public String getFev17DccComment() {
        return (String) attributes.get("fev17").getDccComment();
    }
    
    public void setFev17DccComment(String dccComment) {
        attributes.get("fev17").setDccComment(dccComment);
    }

    public Float getFev18() {
        return (Float) attributes.get("fev18").getValue();
    }
    
    public void setFev18(Float fev18) {
        attributes.get("fev18").setValue(fev18);
    }

    public VerificationStatus getFev18VerificationStatus() {
        return attributes.get("fev18").getVerificationStatus();
    }
    
    public void setFev18VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev18").setVerificationStatus(verificationStatus);
    }

    public String getFev18DccComment() {
        return (String) attributes.get("fev18").getDccComment();
    }
    
    public void setFev18DccComment(String dccComment) {
        attributes.get("fev18").setDccComment(dccComment);
    }

    public Float getFev19() {
        return (Float) attributes.get("fev19").getValue();
    }
    
    public void setFev19(Float fev19) {
        attributes.get("fev19").setValue(fev19);
    }

    public VerificationStatus getFev19VerificationStatus() {
        return attributes.get("fev19").getVerificationStatus();
    }
    
    public void setFev19VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev19").setVerificationStatus(verificationStatus);
    }

    public String getFev19DccComment() {
        return (String) attributes.get("fev19").getDccComment();
    }
    
    public void setFev19DccComment(String dccComment) {
        attributes.get("fev19").setDccComment(dccComment);
    }

    public Float getFev110() {
        return (Float) attributes.get("fev110").getValue();
    }
    
    public void setFev110(Float fev110) {
        attributes.get("fev110").setValue(fev110);
    }

    public VerificationStatus getFev110VerificationStatus() {
        return attributes.get("fev110").getVerificationStatus();
    }
    
    public void setFev110VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev110").setVerificationStatus(verificationStatus);
    }

    public String getFev110DccComment() {
        return (String) attributes.get("fev110").getDccComment();
    }
    
    public void setFev110DccComment(String dccComment) {
        attributes.get("fev110").setDccComment(dccComment);
    }

    public Float getFev111() {
        return (Float) attributes.get("fev111").getValue();
    }
    
    public void setFev111(Float fev111) {
        attributes.get("fev111").setValue(fev111);
    }

    public VerificationStatus getFev111VerificationStatus() {
        return attributes.get("fev111").getVerificationStatus();
    }
    
    public void setFev111VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev111").setVerificationStatus(verificationStatus);
    }

    public String getFev111DccComment() {
        return (String) attributes.get("fev111").getDccComment();
    }
    
    public void setFev111DccComment(String dccComment) {
        attributes.get("fev111").setDccComment(dccComment);
    }

    public Float getFev112() {
        return (Float) attributes.get("fev112").getValue();
    }
    
    public void setFev112(Float fev112) {
        attributes.get("fev112").setValue(fev112);
    }

    public VerificationStatus getFev112VerificationStatus() {
        return attributes.get("fev112").getVerificationStatus();
    }
    
    public void setFev112VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev112").setVerificationStatus(verificationStatus);
    }

    public String getFev112DccComment() {
        return (String) attributes.get("fev112").getDccComment();
    }
    
    public void setFev112DccComment(String dccComment) {
        attributes.get("fev112").setDccComment(dccComment);
    }

    public Float getFev113() {
        return (Float) attributes.get("fev113").getValue();
    }
    
    public void setFev113(Float fev113) {
        attributes.get("fev113").setValue(fev113);
    }

    public VerificationStatus getFev113VerificationStatus() {
        return attributes.get("fev113").getVerificationStatus();
    }
    
    public void setFev113VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev113").setVerificationStatus(verificationStatus);
    }

    public String getFev113DccComment() {
        return (String) attributes.get("fev113").getDccComment();
    }
    
    public void setFev113DccComment(String dccComment) {
        attributes.get("fev113").setDccComment(dccComment);
    }

    public Float getFev114() {
        return (Float) attributes.get("fev114").getValue();
    }
    
    public void setFev114(Float fev114) {
        attributes.get("fev114").setValue(fev114);
    }

    public VerificationStatus getFev114VerificationStatus() {
        return attributes.get("fev114").getVerificationStatus();
    }
    
    public void setFev114VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev114").setVerificationStatus(verificationStatus);
    }

    public String getFev114DccComment() {
        return (String) attributes.get("fev114").getDccComment();
    }
    
    public void setFev114DccComment(String dccComment) {
        attributes.get("fev114").setDccComment(dccComment);
    }

    public Float getFev115() {
        return (Float) attributes.get("fev115").getValue();
    }
    
    public void setFev115(Float fev115) {
        attributes.get("fev115").setValue(fev115);
    }

    public VerificationStatus getFev115VerificationStatus() {
        return attributes.get("fev115").getVerificationStatus();
    }
    
    public void setFev115VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev115").setVerificationStatus(verificationStatus);
    }

    public String getFev115DccComment() {
        return (String) attributes.get("fev115").getDccComment();
    }
    
    public void setFev115DccComment(String dccComment) {
        attributes.get("fev115").setDccComment(dccComment);
    }

    public Float getFev116() {
        return (Float) attributes.get("fev116").getValue();
    }

    public void setFev116(Float fev116) {
        attributes.get("fev116").setValue(fev116);
    }

    public VerificationStatus getFev116VerificationStatus() {
        return attributes.get("fev116").getVerificationStatus();
    }

    public void setFev116VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev116").setVerificationStatus(verificationStatus);
    }

    public String getFev116DccComment() {
        return (String) attributes.get("fev116").getDccComment();
    }

    public void setFev116DccComment(String dccComment) {
        attributes.get("fev116").setDccComment(dccComment);
    }
    public Float getFev117() {
        return (Float) attributes.get("fev117").getValue();
    }

    public void setFev117(Float fev117) {
        attributes.get("fev117").setValue(fev117);
    }

    public VerificationStatus getFev117VerificationStatus() {
        return attributes.get("fev117").getVerificationStatus();
    }

    public void setFev117VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev117").setVerificationStatus(verificationStatus);
    }

    public String getFev117DccComment() {
        return (String) attributes.get("fev117").getDccComment();
    }

    public void setFev117DccComment(String dccComment) {
        attributes.get("fev117").setDccComment(dccComment);
    }
    public Float getFev118() {
        return (Float) attributes.get("fev118").getValue();
    }

    public void setFev118(Float fev118) {
        attributes.get("fev118").setValue(fev118);
    }

    public VerificationStatus getFev118VerificationStatus() {
        return attributes.get("fev118").getVerificationStatus();
    }

    public void setFev118VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev118").setVerificationStatus(verificationStatus);
    }

    public String getFev118DccComment() {
        return (String) attributes.get("fev118").getDccComment();
    }

    public void setFev118DccComment(String dccComment) {
        attributes.get("fev118").setDccComment(dccComment);
    }
    public Float getFev119() {
        return (Float) attributes.get("fev119").getValue();
    }

    public void setFev119(Float fev119) {
        attributes.get("fev119").setValue(fev119);
    }

    public VerificationStatus getFev119VerificationStatus() {
        return attributes.get("fev119").getVerificationStatus();
    }

    public void setFev119VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev119").setVerificationStatus(verificationStatus);
    }

    public String getFev119DccComment() {
        return (String) attributes.get("fev119").getDccComment();
    }

    public void setFev119DccComment(String dccComment) {
        attributes.get("fev119").setDccComment(dccComment);
    }
    public Float getFev120() {
        return (Float) attributes.get("fev120").getValue();
    }

    public void setFev120(Float fev120) {
        attributes.get("fev120").setValue(fev120);
    }

    public VerificationStatus getFev120VerificationStatus() {
        return attributes.get("fev120").getVerificationStatus();
    }

    public void setFev120VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev120").setVerificationStatus(verificationStatus);
    }

    public String getFev120DccComment() {
        return (String) attributes.get("fev120").getDccComment();
    }

    public void setFev120DccComment(String dccComment) {
        attributes.get("fev120").setDccComment(dccComment);
    }
    public Float getFev121() {
        return (Float) attributes.get("fev121").getValue();
    }

    public void setFev121(Float fev121) {
        attributes.get("fev121").setValue(fev121);
    }

    public VerificationStatus getFev121VerificationStatus() {
        return attributes.get("fev121").getVerificationStatus();
    }

    public void setFev121VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev121").setVerificationStatus(verificationStatus);
    }

    public String getFev121DccComment() {
        return (String) attributes.get("fev121").getDccComment();
    }

    public void setFev121DccComment(String dccComment) {
        attributes.get("fev121").setDccComment(dccComment);
    }
    public Float getFev122() {
        return (Float) attributes.get("fev122").getValue();
    }

    public void setFev122(Float fev122) {
        attributes.get("fev122").setValue(fev122);
    }

    public VerificationStatus getFev122VerificationStatus() {
        return attributes.get("fev122").getVerificationStatus();
    }

    public void setFev122VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev122").setVerificationStatus(verificationStatus);
    }

    public String getFev122DccComment() {
        return (String) attributes.get("fev122").getDccComment();
    }

    public void setFev122DccComment(String dccComment) {
        attributes.get("fev122").setDccComment(dccComment);
    }
    public Float getFev123() {
        return (Float) attributes.get("fev123").getValue();
    }

    public void setFev123(Float fev123) {
        attributes.get("fev123").setValue(fev123);
    }

    public VerificationStatus getFev123VerificationStatus() {
        return attributes.get("fev123").getVerificationStatus();
    }

    public void setFev123VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev123").setVerificationStatus(verificationStatus);
    }

    public String getFev123DccComment() {
        return (String) attributes.get("fev123").getDccComment();
    }

    public void setFev123DccComment(String dccComment) {
        attributes.get("fev123").setDccComment(dccComment);
    }
    public Float getFev124() {
        return (Float) attributes.get("fev124").getValue();
    }

    public void setFev124(Float fev124) {
        attributes.get("fev124").setValue(fev124);
    }

    public VerificationStatus getFev124VerificationStatus() {
        return attributes.get("fev124").getVerificationStatus();
    }

    public void setFev124VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev124").setVerificationStatus(verificationStatus);
    }

    public String getFev124DccComment() {
        return (String) attributes.get("fev124").getDccComment();
    }

    public void setFev124DccComment(String dccComment) {
        attributes.get("fev124").setDccComment(dccComment);
    }
    public Float getFev125() {
        return (Float) attributes.get("fev125").getValue();
    }

    public void setFev125(Float fev125) {
        attributes.get("fev125").setValue(fev125);
    }

    public VerificationStatus getFev125VerificationStatus() {
        return attributes.get("fev125").getVerificationStatus();
    }

    public void setFev125VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev125").setVerificationStatus(verificationStatus);
    }

    public String getFev125DccComment() {
        return (String) attributes.get("fev125").getDccComment();
    }

    public void setFev125DccComment(String dccComment) {
        attributes.get("fev125").setDccComment(dccComment);
    }
    public Float getFev126() {
        return (Float) attributes.get("fev126").getValue();
    }

    public void setFev126(Float fev126) {
        attributes.get("fev126").setValue(fev126);
    }

    public VerificationStatus getFev126VerificationStatus() {
        return attributes.get("fev126").getVerificationStatus();
    }

    public void setFev126VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev126").setVerificationStatus(verificationStatus);
    }

    public String getFev126DccComment() {
        return (String) attributes.get("fev126").getDccComment();
    }

    public void setFev126DccComment(String dccComment) {
        attributes.get("fev126").setDccComment(dccComment);
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

    public VerificationStatus getFvc6VerificationStatus() {
        return attributes.get("fvc6").getVerificationStatus();
    }
    
    public void setFvc6VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc6").setVerificationStatus(verificationStatus);
    }

    public String getFvc6DccComment() {
        return (String) attributes.get("fvc6").getDccComment();
    }
    
    public void setFvc6DccComment(String dccComment) {
        attributes.get("fvc6").setDccComment(dccComment);
    }

    public Float getFvc7() {
        return (Float) attributes.get("fvc7").getValue();
    }
    
    public void setFvc7(Float fvc7) {
        attributes.get("fvc7").setValue(fvc7);
    }

    public VerificationStatus getFvc7VerificationStatus() {
        return attributes.get("fvc7").getVerificationStatus();
    }
    
    public void setFvc7VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc7").setVerificationStatus(verificationStatus);
    }

    public String getFvc7DccComment() {
        return (String) attributes.get("fvc7").getDccComment();
    }
    
    public void setFvc7DccComment(String dccComment) {
        attributes.get("fvc7").setDccComment(dccComment);
    }

    public Float getFvc8() {
        return (Float) attributes.get("fvc8").getValue();
    }
    
    public void setFvc8(Float fvc8) {
        attributes.get("fvc8").setValue(fvc8);
    }

    public VerificationStatus getFvc8VerificationStatus() {
        return attributes.get("fvc8").getVerificationStatus();
    }
    
    public void setFvc8VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc8").setVerificationStatus(verificationStatus);
    }

    public String getFvc8DccComment() {
        return (String) attributes.get("fvc8").getDccComment();
    }
    
    public void setFvc8DccComment(String dccComment) {
        attributes.get("fvc8").setDccComment(dccComment);
    }

    public Float getFvc9() {
        return (Float) attributes.get("fvc9").getValue();
    }
    
    public void setFvc9(Float fvc9) {
        attributes.get("fvc9").setValue(fvc9);
    }

    public VerificationStatus getFvc9VerificationStatus() {
        return attributes.get("fvc9").getVerificationStatus();
    }
    
    public void setFvc9VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc9").setVerificationStatus(verificationStatus);
    }

    public String getFvc9DccComment() {
        return (String) attributes.get("fvc9").getDccComment();
    }
    
    public void setFvc9DccComment(String dccComment) {
        attributes.get("fvc9").setDccComment(dccComment);
    }

    public Float getFvc10() {
        return (Float) attributes.get("fvc10").getValue();
    }
    
    public void setFvc10(Float fvc10) {
        attributes.get("fvc10").setValue(fvc10);
    }

    public VerificationStatus getFvc10VerificationStatus() {
        return attributes.get("fvc10").getVerificationStatus();
    }
    
    public void setFvc10VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc10").setVerificationStatus(verificationStatus);
    }

    public String getFvc10DccComment() {
        return (String) attributes.get("fvc10").getDccComment();
    }
    
    public void setFvc10DccComment(String dccComment) {
        attributes.get("fvc10").setDccComment(dccComment);
    }

    public Float getFvc11() {
        return (Float) attributes.get("fvc11").getValue();
    }
    
    public void setFvc11(Float fvc11) {
        attributes.get("fvc11").setValue(fvc11);
    }

    public VerificationStatus getFvc11VerificationStatus() {
        return attributes.get("fvc11").getVerificationStatus();
    }
    
    public void setFvc11VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc11").setVerificationStatus(verificationStatus);
    }

    public String getFvc11DccComment() {
        return (String) attributes.get("fvc11").getDccComment();
    }
    
    public void setFvc11DccComment(String dccComment) {
        attributes.get("fvc11").setDccComment(dccComment);
    }

    public Float getFvc12() {
        return (Float) attributes.get("fvc12").getValue();
    }
    
    public void setFvc12(Float fvc12) {
        attributes.get("fvc12").setValue(fvc12);
    }

    public VerificationStatus getFvc12VerificationStatus() {
        return attributes.get("fvc12").getVerificationStatus();
    }
    
    public void setFvc12VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc12").setVerificationStatus(verificationStatus);
    }

    public String getFvc12DccComment() {
        return (String) attributes.get("fvc12").getDccComment();
    }
    
    public void setFvc12DccComment(String dccComment) {
        attributes.get("fvc12").setDccComment(dccComment);
    }

    public Float getFvc13() {
        return (Float) attributes.get("fvc13").getValue();
    }
    
    public void setFvc13(Float fvc13) {
        attributes.get("fvc13").setValue(fvc13);
    }

    public VerificationStatus getFvc13VerificationStatus() {
        return attributes.get("fvc13").getVerificationStatus();
    }
    
    public void setFvc13VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc13").setVerificationStatus(verificationStatus);
    }

    public String getFvc13DccComment() {
        return (String) attributes.get("fvc13").getDccComment();
    }
    
    public void setFvc13DccComment(String dccComment) {
        attributes.get("fvc13").setDccComment(dccComment);
    }

    public Float getFvc14() {
        return (Float) attributes.get("fvc14").getValue();
    }
    
    public void setFvc14(Float fvc14) {
        attributes.get("fvc14").setValue(fvc14);
    }

    public VerificationStatus getFvc14VerificationStatus() {
        return attributes.get("fvc14").getVerificationStatus();
    }
    
    public void setFvc14VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc14").setVerificationStatus(verificationStatus);
    }

    public String getFvc14DccComment() {
        return (String) attributes.get("fvc14").getDccComment();
    }
    
    public void setFvc14DccComment(String dccComment) {
        attributes.get("fvc14").setDccComment(dccComment);
    }

    public Float getFvc15() {
        return (Float) attributes.get("fvc15").getValue();
    }
    
    public void setFvc15(Float fvc15) {
        attributes.get("fvc15").setValue(fvc15);
    }

    public VerificationStatus getFvc15VerificationStatus() {
        return attributes.get("fvc15").getVerificationStatus();
    }
    
    public void setFvc15VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc15").setVerificationStatus(verificationStatus);
    }

    public String getFvc15DccComment() {
        return (String) attributes.get("fvc15").getDccComment();
    }
    
    public void setFvc15DccComment(String dccComment) {
        attributes.get("fvc15").setDccComment(dccComment);
    }
    
    public Float getFvc16() {
        return (Float) attributes.get("fvc16").getValue();
    }

    public void setFvc16(Float fvc16) {
        attributes.get("fvc16").setValue(fvc16);
    }

    public VerificationStatus getFvc16VerificationStatus() {
        return attributes.get("fvc16").getVerificationStatus();
    }

    public void setFvc16VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc16").setVerificationStatus(verificationStatus);
    }

    public String getFvc16DccComment() {
        return (String) attributes.get("fvc16").getDccComment();
    }

    public void setFvc16DccComment(String dccComment) {
        attributes.get("fvc16").setDccComment(dccComment);
    }
    public Float getFvc17() {
        return (Float) attributes.get("fvc17").getValue();
    }

    public void setFvc17(Float fvc17) {
        attributes.get("fvc17").setValue(fvc17);
    }

    public VerificationStatus getFvc17VerificationStatus() {
        return attributes.get("fvc17").getVerificationStatus();
    }

    public void setFvc17VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc17").setVerificationStatus(verificationStatus);
    }

    public String getFvc17DccComment() {
        return (String) attributes.get("fvc17").getDccComment();
    }

    public void setFvc17DccComment(String dccComment) {
        attributes.get("fvc17").setDccComment(dccComment);
    }
    public Float getFvc18() {
        return (Float) attributes.get("fvc18").getValue();
    }

    public void setFvc18(Float fvc18) {
        attributes.get("fvc18").setValue(fvc18);
    }

    public VerificationStatus getFvc18VerificationStatus() {
        return attributes.get("fvc18").getVerificationStatus();
    }

    public void setFvc18VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc18").setVerificationStatus(verificationStatus);
    }

    public String getFvc18DccComment() {
        return (String) attributes.get("fvc18").getDccComment();
    }

    public void setFvc18DccComment(String dccComment) {
        attributes.get("fvc18").setDccComment(dccComment);
    }
    public Float getFvc19() {
        return (Float) attributes.get("fvc19").getValue();
    }

    public void setFvc19(Float fvc19) {
        attributes.get("fvc19").setValue(fvc19);
    }

    public VerificationStatus getFvc19VerificationStatus() {
        return attributes.get("fvc19").getVerificationStatus();
    }

    public void setFvc19VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc19").setVerificationStatus(verificationStatus);
    }

    public String getFvc19DccComment() {
        return (String) attributes.get("fvc19").getDccComment();
    }

    public void setFvc19DccComment(String dccComment) {
        attributes.get("fvc19").setDccComment(dccComment);
    }
    public Float getFvc20() {
        return (Float) attributes.get("fvc20").getValue();
    }

    public void setFvc20(Float fvc20) {
        attributes.get("fvc20").setValue(fvc20);
    }

    public VerificationStatus getFvc20VerificationStatus() {
        return attributes.get("fvc20").getVerificationStatus();
    }

    public void setFvc20VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc20").setVerificationStatus(verificationStatus);
    }

    public String getFvc20DccComment() {
        return (String) attributes.get("fvc20").getDccComment();
    }

    public void setFvc20DccComment(String dccComment) {
        attributes.get("fvc20").setDccComment(dccComment);
    }
    public Float getFvc21() {
        return (Float) attributes.get("fvc21").getValue();
    }

    public void setFvc21(Float fvc21) {
        attributes.get("fvc21").setValue(fvc21);
    }

    public VerificationStatus getFvc21VerificationStatus() {
        return attributes.get("fvc21").getVerificationStatus();
    }

    public void setFvc21VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc21").setVerificationStatus(verificationStatus);
    }

    public String getFvc21DccComment() {
        return (String) attributes.get("fvc21").getDccComment();
    }

    public void setFvc21DccComment(String dccComment) {
        attributes.get("fvc21").setDccComment(dccComment);
    }
    public Float getFvc22() {
        return (Float) attributes.get("fvc22").getValue();
    }

    public void setFvc22(Float fvc22) {
        attributes.get("fvc22").setValue(fvc22);
    }

    public VerificationStatus getFvc22VerificationStatus() {
        return attributes.get("fvc22").getVerificationStatus();
    }

    public void setFvc22VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc22").setVerificationStatus(verificationStatus);
    }

    public String getFvc22DccComment() {
        return (String) attributes.get("fvc22").getDccComment();
    }

    public void setFvc22DccComment(String dccComment) {
        attributes.get("fvc22").setDccComment(dccComment);
    }
    public Float getFvc23() {
        return (Float) attributes.get("fvc23").getValue();
    }

    public void setFvc23(Float fvc23) {
        attributes.get("fvc23").setValue(fvc23);
    }

    public VerificationStatus getFvc23VerificationStatus() {
        return attributes.get("fvc23").getVerificationStatus();
    }

    public void setFvc23VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc23").setVerificationStatus(verificationStatus);
    }

    public String getFvc23DccComment() {
        return (String) attributes.get("fvc23").getDccComment();
    }

    public void setFvc23DccComment(String dccComment) {
        attributes.get("fvc23").setDccComment(dccComment);
    }
    public Float getFvc24() {
        return (Float) attributes.get("fvc24").getValue();
    }

    public void setFvc24(Float fvc24) {
        attributes.get("fvc24").setValue(fvc24);
    }

    public VerificationStatus getFvc24VerificationStatus() {
        return attributes.get("fvc24").getVerificationStatus();
    }

    public void setFvc24VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc24").setVerificationStatus(verificationStatus);
    }

    public String getFvc24DccComment() {
        return (String) attributes.get("fvc24").getDccComment();
    }

    public void setFvc24DccComment(String dccComment) {
        attributes.get("fvc24").setDccComment(dccComment);
    }
    public Float getFvc25() {
        return (Float) attributes.get("fvc25").getValue();
    }

    public void setFvc25(Float fvc25) {
        attributes.get("fvc25").setValue(fvc25);
    }

    public VerificationStatus getFvc25VerificationStatus() {
        return attributes.get("fvc25").getVerificationStatus();
    }

    public void setFvc25VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc25").setVerificationStatus(verificationStatus);
    }

    public String getFvc25DccComment() {
        return (String) attributes.get("fvc25").getDccComment();
    }

    public void setFvc25DccComment(String dccComment) {
        attributes.get("fvc25").setDccComment(dccComment);
    }
    public Float getFvc26() {
        return (Float) attributes.get("fvc26").getValue();
    }

    public void setFvc26(Float fvc26) {
        attributes.get("fvc26").setValue(fvc26);
    }

    public VerificationStatus getFvc26VerificationStatus() {
        return attributes.get("fvc26").getVerificationStatus();
    }

    public void setFvc26VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc26").setVerificationStatus(verificationStatus);
    }

    public String getFvc26DccComment() {
        return (String) attributes.get("fvc26").getDccComment();
    }

    public void setFvc26DccComment(String dccComment) {
        attributes.get("fvc26").setDccComment(dccComment);
    }

    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }

    
    public Float getSlope() {
        return (Float) attributes.get("slope").getValue();
    }
    
    public void setSlope(Float slope) {
        attributes.get("slope").setValue(slope);
    }

    public Float getSignificance() {
        return (Float) attributes.get("significance").getValue();
    }
    
    public void setSignificance(Float significance) {
        attributes.get("significance").setValue(significance);
    }

	public Float getBaselineFEV1() {
        return (Float) attributes.get("baselineFEV1").getValue();
    }
    
    public void setBaselineFEV1(Float baselineFEV1) {
        attributes.get("baselineFEV1").setValue(baselineFEV1);
    }

    public VerificationStatus getBaselineFEV1VerificationStatus() {
        return attributes.get("baselineFEV1").getVerificationStatus();
    }
    
    public void setBaselineFEV1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("baselineFEV1").setVerificationStatus(verificationStatus);
    }

    public String getBaselineFEV1DccComment() {
        return (String) attributes.get("baselineFEV1").getDccComment();
    }
    
    public void setBaselineFEV1DccComment(String dccComment) {
        attributes.get("baselineFEV1").setDccComment(dccComment);
    }

    public Date getFirstComponentFEV1Date() {
        return (Date) attributes.get("firstComponentFEV1Date").getValue();
    }
    
    public void setFirstComponentFEV1Date(Date firstComponentFEV1Date) {
        attributes.get("firstComponentFEV1Date").setValue(firstComponentFEV1Date);
    }

    public VerificationStatus getFirstComponentFEV1DateVerificationStatus() {
        return attributes.get("firstComponentFEV1Date").getVerificationStatus();
    }
    
    public void setFirstComponentFEV1DateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("firstComponentFEV1Date").setVerificationStatus(verificationStatus);
    }

    public String getFirstComponentFEV1DateDccComment() {
        return (String) attributes.get("firstComponentFEV1Date").getDccComment();
    }
    
    public void setFirstComponentFEV1DateDccComment(String dccComment) {
        attributes.get("firstComponentFEV1Date").setDccComment(dccComment);
    }

    public Float getFirstComponentFEV1() {
        return (Float) attributes.get("firstComponentFEV1").getValue();
    }
    
    public void setFirstComponentFEV1(Float firstComponentFEV1) {
        attributes.get("firstComponentFEV1").setValue(firstComponentFEV1);
    }

    public VerificationStatus getFirstComponentFEV1VerificationStatus() {
        return attributes.get("firstComponentFEV1").getVerificationStatus();
    }
    
    public void setFirstComponentFEV1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("firstComponentFEV1").setVerificationStatus(verificationStatus);
    }

    public String getFirstComponentFEV1DccComment() {
        return (String) attributes.get("firstComponentFEV1").getDccComment();
    }
    
    public void setFirstComponentFEV1DccComment(String dccComment) {
        attributes.get("firstComponentFEV1").setDccComment(dccComment);
    }

    public Float getFirstComponentFVC() {
        return (Float) attributes.get("firstComponentFVC").getValue();
    }
    
    public void setFirstComponentFVC(Float firstComponentFVC) {
        attributes.get("firstComponentFVC").setValue(firstComponentFVC);
    }

    public VerificationStatus getFirstComponentFVCVerificationStatus() {
        return attributes.get("firstComponentFVC").getVerificationStatus();
    }
    
    public void setFirstComponentFVCVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("firstComponentFVC").setVerificationStatus(verificationStatus);
    }

    public String getFirstComponentFVCDccComment() {
        return (String) attributes.get("firstComponentFVC").getDccComment();
    }
    
    public void setFirstComponentFVCDccComment(String dccComment) {
        attributes.get("firstComponentFVC").setDccComment(dccComment);
    }

    public Date getSecondComponentFEV1Date() {
        return (Date) attributes.get("secondComponentFEV1Date").getValue();
    }
    
    public void setSecondComponentFEV1Date(Date secondComponentFEV1Date) {
        attributes.get("secondComponentFEV1Date").setValue(secondComponentFEV1Date);
    }

    public VerificationStatus getSecondComponentFEV1DateVerificationStatus() {
        return attributes.get("secondComponentFEV1Date").getVerificationStatus();
    }
    
    public void setSecondComponentFEV1DateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("secondComponentFEV1Date").setVerificationStatus(verificationStatus);
    }

    public String getSecondComponentFEV1DateDccComment() {
        return (String) attributes.get("secondComponentFEV1Date").getDccComment();
    }
    
    public void setSecondComponentFEV1DateDccComment(String dccComment) {
        attributes.get("secondComponentFEV1Date").setDccComment(dccComment);
    }

    public Float getSecondComponentFEV1() {
        return (Float) attributes.get("secondComponentFEV1").getValue();
    }
    
    public void setSecondComponentFEV1(Float secondComponentFEV1) {
        attributes.get("secondComponentFEV1").setValue(secondComponentFEV1);
    }

    public VerificationStatus getSecondComponentFEV1VerificationStatus() {
        return attributes.get("secondComponentFEV1").getVerificationStatus();
    }
    
    public void setSecondComponentFEV1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("secondComponentFEV1").setVerificationStatus(verificationStatus);
    }

    public String getSecondComponentFEV1DccComment() {
        return (String) attributes.get("secondComponentFEV1").getDccComment();
    }
    
    public void setSecondComponentFEV1DccComment(String dccComment) {
        attributes.get("secondComponentFEV1").setDccComment(dccComment);
    }

    public Float getSecondComponentFVC() {
        return (Float) attributes.get("secondComponentFVC").getValue();
    }
    
    public void setSecondComponentFVC(Float secondComponentFVC) {
        attributes.get("secondComponentFVC").setValue(secondComponentFVC);
    }

    public VerificationStatus getSecondComponentFVCVerificationStatus() {
        return attributes.get("secondComponentFVC").getVerificationStatus();
    }
    
    public void setSecondComponentFVCVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("secondComponentFVC").setVerificationStatus(verificationStatus);
    }

    public String getSecondComponentFVCDccComment() {
        return (String) attributes.get("secondComponentFVC").getDccComment();
    }
    
    public void setSecondComponentFVCDccComment(String dccComment) {
        attributes.get("secondComponentFVC").setDccComment(dccComment);
    }
	
    public Date getLungTransplantationDate() {
        return (Date) attributes.get("lungTransplantationDate").getValue();
    }
    
    public void setLungTransplantationDate(Date lungTransplantationDate) {
        attributes.get("lungTransplantationDate").setValue(lungTransplantationDate);
    }

    public VerificationStatus getLungTransplantationDateVerificationStatus() {
        return attributes.get("lungTransplantationDate").getVerificationStatus();
    }
    
    public void setLungTransplantationDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("lungTransplantationDate").setVerificationStatus(verificationStatus);
    }

    public String getLungTransplantationDateDccComment() {
        return (String) attributes.get("lungTransplantationDate").getDccComment();
    }
    
    public void setLungTransplantationDateDccComment(String dccComment) {
        attributes.get("lungTransplantationDate").setDccComment(dccComment);
    }
    
    public Date getMostRecentExamDate() {
        return (Date) attributes.get("mostRecentExamDate").getValue();
    }

    public void setMostRecentExamDate(Date mostRecentExamDate) {
        attributes.get("mostRecentExamDate").setValue(mostRecentExamDate);
    }
    
    public Date getCompleteBloodCountDate() {
        return (Date) attributes.get("completeBloodCountDate").getValue();
    }
    
    public void setCompleteBloodCountDate(Date completeBloodCountDate) {
        attributes.get("completeBloodCountDate").setValue(completeBloodCountDate);
    }

    public VerificationStatus getCompleteBloodCountDateVerificationStatus() {
        return attributes.get("completeBloodCountDate").getVerificationStatus();
    }
    
    public void setCompleteBloodCountDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("completeBloodCountDate").setVerificationStatus(verificationStatus);
    }

    public String getCompleteBloodCountDateDccComment() {
        return (String) attributes.get("completeBloodCountDate").getDccComment();
    }
    
    public void setCompleteBloodCountDateDccComment(String dccComment) {
        attributes.get("completeBloodCountDate").setDccComment(dccComment);
    }

    public Float getWbcs() {
        return (Float) attributes.get("wbcs").getValue();
    }
    
    public void setWbcs(Float wbcs) {
        attributes.get("wbcs").setValue(wbcs);
    }

    public VerificationStatus getWbcsVerificationStatus() {
        return attributes.get("wbcs").getVerificationStatus();
    }
    
    public void setWbcsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("wbcs").setVerificationStatus(verificationStatus);
    }

    public String getWbcsDccComment() {
        return (String) attributes.get("wbcs").getDccComment();
    }
    
    public void setWbcsDccComment(String dccComment) {
        attributes.get("wbcs").setDccComment(dccComment);
    }

    public Float getRbcs() {
        return (Float) attributes.get("rbcs").getValue();
    }
    
    public void setRbcs(Float rbcs) {
        attributes.get("rbcs").setValue(rbcs);
    }

    public VerificationStatus getRbcsVerificationStatus() {
        return attributes.get("rbcs").getVerificationStatus();
    }
    
    public void setRbcsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("rbcs").setVerificationStatus(verificationStatus);
    }

    public String getRbcsDccComment() {
        return (String) attributes.get("rbcs").getDccComment();
    }
    
    public void setRbcsDccComment(String dccComment) {
        attributes.get("rbcs").setDccComment(dccComment);
    }

    public Float getHemoglobin() {
        return (Float) attributes.get("hemoglobin").getValue();
    }
    
    public void setHemoglobin(Float hemoglobin) {
        attributes.get("hemoglobin").setValue(hemoglobin);
    }

    public VerificationStatus getHemoglobinVerificationStatus() {
        return attributes.get("hemoglobin").getVerificationStatus();
    }
    
    public void setHemoglobinVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("hemoglobin").setVerificationStatus(verificationStatus);
    }

    public String getHemoglobinDccComment() {
        return (String) attributes.get("hemoglobin").getDccComment();
    }
    
    public void setHemoglobinDccComment(String dccComment) {
        attributes.get("hemoglobin").setDccComment(dccComment);
    }
    
    public Float getHematocrit() {
        return (Float) attributes.get("hematocrit").getValue();
    }
    
    public void setHematocrit(Float hematocrit) {
        attributes.get("hematocrit").setValue(hematocrit);
    }

    public VerificationStatus getHematocritVerificationStatus() {
        return attributes.get("hematocrit").getVerificationStatus();
    }
    
    public void setHematocritVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("hematocrit").setVerificationStatus(verificationStatus);
    }

    public String getHematocritDccComment() {
        return (String) attributes.get("hematocrit").getDccComment();
    }
    
    public void setHematocritDccComment(String dccComment) {
        attributes.get("hematocrit").setDccComment(dccComment);
    }
    
    public Float getPlatelets() {
        return (Float) attributes.get("platelets").getValue();
    }
    
    public void setPlatelets(Float platelets) {
        attributes.get("platelets").setValue(platelets);
    }

    public VerificationStatus getPlateletsVerificationStatus() {
        return attributes.get("platelets").getVerificationStatus();
    }
    
    public void setPlateletsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("platelets").setVerificationStatus(verificationStatus);
    }

    public String getPlateletsDccComment() {
        return (String) attributes.get("platelets").getDccComment();
    }
    
    public void setPlateletsDccComment(String dccComment) {
        attributes.get("platelets").setDccComment(dccComment);
    }
    
    public String getLabBasedNewBOSDiagnosis() {
        return (String) attributes.get("labBasedNewBOSDiagnosis").getValue();
    }
    
    public void setLabBasedNewBOSDiagnosis(String labBasedNewBOSDiagnosis) {
        attributes.get("labBasedNewBOSDiagnosis").setValue(labBasedNewBOSDiagnosis);
    }

    public VerificationStatus getLabBasedNewBOSDiagnosisVerificationStatus() {
        return attributes.get("labBasedNewBOSDiagnosis").getVerificationStatus();
    }
    
    public void setLabBasedNewBOSDiagnosisVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("labBasedNewBOSDiagnosis").setVerificationStatus(verificationStatus);
    }

    public String getLabBasedNewBOSDiagnosisDccComment() {
        return (String) attributes.get("labBasedNewBOSDiagnosis").getDccComment();
    }
    
    public void setLabBasedNewBOSDiagnosisDccComment(String dccComment) {
        attributes.get("labBasedNewBOSDiagnosis").setDccComment(dccComment);
    }

    public String getPreDiagnosisMonitoring() {
        return (String) attributes.get("preDiagnosisMonitoring").getValue();
    }
    
    public void setPreDiagnosisMonitoring(String PreDiagnosisMonitoring) {
        attributes.get("preDiagnosisMonitoring").setValue(PreDiagnosisMonitoring);
    }

    public VerificationStatus getPreDiagnosisMonitoringVerificationStatus() {
        return attributes.get("preDiagnosisMonitoring").getVerificationStatus();
    }
    
    public void setPreDiagnosisMonitoringVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("preDiagnosisMonitoring").setVerificationStatus(verificationStatus);
    }

    public String getPreDiagnosisMonitoringDccComment() {
        return (String) attributes.get("preDiagnosisMonitoring").getDccComment();
    }
    
    public void setPreDiagnosisMonitoringDccComment(String dccComment) {
        attributes.get("preDiagnosisMonitoring").setDccComment(dccComment);
    }
    
    public String getDocumentedClinicalAssessment() {
        return (String) attributes.get("documentedClinicalAssessment").getValue();
    }
    
    public void setDocumentedClinicalAssessment(String documentedClinicalAssessment) {
        attributes.get("documentedClinicalAssessment").setValue(documentedClinicalAssessment);
    }

    public VerificationStatus getDocumentedClinicalAssessmentVerificationStatus() {
        return attributes.get("documentedClinicalAssessment").getVerificationStatus();
    }
    
    public void setDocumentedClinicalAssessmentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("documentedClinicalAssessment").setVerificationStatus(verificationStatus);
    }

    public String getDocumentedClinicalAssessmentDccComment() {
        return (String) attributes.get("documentedClinicalAssessment").getDccComment();
    }
    
    public void setDocumentedClinicalAssessmentDccComment(String dccComment) {
        attributes.get("documentedClinicalAssessment").setDccComment(dccComment);
    }

    public String getDeclineNotBOS() {
        return (String) attributes.get("declineNotBOS").getValue();
    }
    
    public void setDeclineNotBOS(String declineNotBOS) {
        attributes.get("declineNotBOS").setValue(declineNotBOS);
    }

    public VerificationStatus getDeclineNotBOSVerificationStatus() {
        return attributes.get("declineNotBOS").getVerificationStatus();
    }
    
    public void setDeclineNotBOSVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("declineNotBOS").setVerificationStatus(verificationStatus);
    }

    public String getDeclineNotBOSDccComment() {
        return (String) attributes.get("declineNotBOS").getDccComment();
    }
    
    public void setDeclineNotBOSDccComment(String dccComment) {
        attributes.get("declineNotBOS").setDccComment(dccComment);
    }

    public String getLeukopenia3000() {
        return (String) attributes.get("leukopenia3000").getValue();
    }
    
    public void setLeukopenia3000(String leukopenia3000) {
        attributes.get("leukopenia3000").setValue(leukopenia3000);
    }

    public VerificationStatus getLeukopenia3000VerificationStatus() {
        return attributes.get("leukopenia3000").getVerificationStatus();
    }
    
    public void setLeukopenia3000VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("leukopenia3000").setVerificationStatus(verificationStatus);
    }

    public String getLeukopenia3000DccComment() {
        return (String) attributes.get("leukopenia3000").getDccComment();
    }
    
    public void setLeukopenia3000DccComment(String dccComment) {
        attributes.get("leukopenia3000").setDccComment(dccComment);
    }
    
    public String getAcuteDeclineTreatment() {
        return (String) attributes.get("acuteDeclineTreatment").getValue();
    }
    
    public void setAcuteDeclineTreatment(String acuteDeclineTreatment) {
        attributes.get("acuteDeclineTreatment").setValue(acuteDeclineTreatment);
    }

    public VerificationStatus getAcuteDeclineTreatmentVerificationStatus() {
        return attributes.get("acuteDeclineTreatment").getVerificationStatus();
    }
    
    public void setAcuteDeclineTreatmentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("acuteDeclineTreatment").setVerificationStatus(verificationStatus);
    }

    public String getAcuteDeclineTreatmentDccComment() {
        return (String) attributes.get("acuteDeclineTreatment").getDccComment();
    }
    
    public void setAcuteDeclineTreatmentDccComment(String dccComment) {
        attributes.get("acuteDeclineTreatment").setDccComment(dccComment);
    }
    
    public Date getPostTransBOSDiagDate() {
       return (Date) attributes.get("postTransBOSDiagDate").getValue();
   }

   public void setPostTransBOSDiagDate(Date postTransBOSDiagDate) {
       attributes.get("postTransBOSDiagDate").setValue(postTransBOSDiagDate);
   }
   
    public VerificationStatus getPostTransBOSDiagDateVerificationStatus() {
        return attributes.get("postTransBOSDiagDate").getVerificationStatus();
    }

    public void setPostTransBOSDiagDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("postTransBOSDiagDate").setVerificationStatus(verificationStatus);
    }

    public String getPostTransBOSDiagDateDccComment() {
        return (String) attributes.get("postTransBOSDiagDate").getDccComment();
    }

    public void setPostTransBOSDiagDateDccComment(String dccComment) {
        attributes.get("postTransBOSDiagDate").setDccComment(dccComment);
    }

   public Date getFirstPostTransBOSDiagFEV1Date() {
       return (Date) attributes.get("firstPostTransBOSDiagFEV1Date").getValue();
   }

   public void setFirstPostTransBOSDiagFEV1Date(Date firstPostTransBOSDiagFEV1Date) {
       attributes.get("firstPostTransBOSDiagFEV1Date").setValue(firstPostTransBOSDiagFEV1Date);
   }
   
    public VerificationStatus getFirstPostTransBOSDiagFEV1DateVerificationStatus() {
       return attributes.get("firstPostTransBOSDiagFEV1Date").getVerificationStatus();
    }

    public void setFirstPostTransBOSDiagFEV1DateVerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("firstPostTransBOSDiagFEV1Date").setVerificationStatus(verificationStatus);
    }

    public String getFirstPostTransBOSDiagFEV1DateDccComment() {
       return (String) attributes.get("firstPostTransBOSDiagFEV1Date").getDccComment();
    }

    public void setFirstPostTransBOSDiagFEV1DateDccComment(String dccComment) {
       attributes.get("firstPostTransBOSDiagFEV1Date").setDccComment(dccComment);
    }
   
   public Float getFirstPostTransBOSDiagFEV1() {
       return (Float) attributes.get("firstPostTransBOSDiagFEV1").getValue();
   }

   public void setFirstPostTransBOSDiagFEV1(Float firstPostTransBOSDiagFEV1) {
       attributes.get("firstPostTransBOSDiagFEV1").setValue(firstPostTransBOSDiagFEV1);
   }

   public VerificationStatus getFirstPostTransBOSDiagFEV1VerificationStatus() {
       return attributes.get("firstPostTransBOSDiagFEV1").getVerificationStatus();
    }

    public void setFirstPostTransBOSDiagFEV1VerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("firstPostTransBOSDiagFEV1").setVerificationStatus(verificationStatus);
    }

    public String getFirstPostTransBOSDiagFEV1DccComment() {
       return (String) attributes.get("firstPostTransBOSDiagFEV1").getDccComment();
    }

    public void setFirstPostTransBOSDiagFEV1DccComment(String dccComment) {
       attributes.get("firstPostTransBOSDiagFEV1").setDccComment(dccComment);
    }
   
   public Float getFirstPostTransBOSDiagFVC() {
       return (Float) attributes.get("firstPostTransBOSDiagFVC").getValue();
   }

   public void setFirstPostTransBOSDiagFVC(Float firstPostTransBOSDiagFVC) {
       attributes.get("firstPostTransBOSDiagFVC").setValue(firstPostTransBOSDiagFVC);
   }
   
    public VerificationStatus getFirstPostTransBOSDiagFVCVerificationStatus() {
       return attributes.get("firstPostTransBOSDiagFVC").getVerificationStatus();
    }

    public void setFirstPostTransBOSDiagFVCVerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("firstPostTransBOSDiagFVC").setVerificationStatus(verificationStatus);
    }

    public String getFirstPostTransBOSDiagFVCDccComment() {
       return (String) attributes.get("firstPostTransBOSDiagFVC").getDccComment();
    }

    public void setFirstPostTransBOSDiagFVCDccComment(String dccComment) {
       attributes.get("firstPostTransBOSDiagFVC").setDccComment(dccComment);
    }

   public Date getSecondPostTransBOSDiagFEV1Date() {
       return (Date) attributes.get("secondPostTransBOSDiagFEV1Date").getValue();
   }

   public void setSecondPostTransBOSDiagFEV1Date(Date secondPostTransBOSDiagFEV1Date) {
       attributes.get("secondPostTransBOSDiagFEV1Date").setValue(secondPostTransBOSDiagFEV1Date);
   }
   
   public VerificationStatus getSecondPostTransBOSDiagFEV1DateVerificationStatus() {
       return attributes.get("secondPostTransBOSDiagFEV1Date").getVerificationStatus();
    }

    public void setSecondPostTransBOSDiagFEV1DateVerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("secondPostTransBOSDiagFEV1Date").setVerificationStatus(verificationStatus);
    }

    public String getSecondPostTransBOSDiagFEV1DateDccComment() {
       return (String) attributes.get("secondPostTransBOSDiagFEV1Date").getDccComment();
    }

    public void setSecondPostTransBOSDiagFEV1DateDccComment(String dccComment) {
       attributes.get("secondPostTransBOSDiagFEV1Date").setDccComment(dccComment);
    }

   public Float getSecondPostTransBOSDiagFEV1() {
       return (Float) attributes.get("secondPostTransBOSDiagFEV1").getValue();
   }

   public void setSecondPostTransBOSDiagFEV1(Float secondPostTransBOSDiagFEV1) {
       attributes.get("secondPostTransBOSDiagFEV1").setValue(secondPostTransBOSDiagFEV1);
   }
   
    public VerificationStatus getSecondPostTransBOSDiagFEV1VerificationStatus() {
       return attributes.get("secondPostTransBOSDiagFEV1").getVerificationStatus();
    }

    public void setSecondPostTransBOSDiagFEV1VerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("secondPostTransBOSDiagFEV1").setVerificationStatus(verificationStatus);
    }

    public String getSecondPostTransBOSDiagFEV1DccComment() {
       return (String) attributes.get("secondPostTransBOSDiagFEV1").getDccComment();
    }

    public void setSecondPostTransBOSDiagFEV1DccComment(String dccComment) {
       attributes.get("secondPostTransBOSDiagFEV1").setDccComment(dccComment);
    }

   public Float getSecondPostTransBOSDiagFVC() {
       return (Float) attributes.get("secondPostTransBOSDiagFVC").getValue();
   }

   public void setSecondPostTransBOSDiagFVC(Float secondPostTransBOSDiagFVC) {
       attributes.get("secondPostTransBOSDiagFVC").setValue(secondPostTransBOSDiagFVC);
   }
   
    public VerificationStatus getSecondPostTransBOSDiagFVCVerificationStatus() {
       return attributes.get("secondPostTransBOSDiagFVC").getVerificationStatus();
    }

    public void setSecondPostTransBOSDiagFVCVerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("secondPostTransBOSDiagFVC").setVerificationStatus(verificationStatus);
    }

    public String getSecondPostTransBOSDiagFVCDccComment() {
       return (String) attributes.get("secondPostTransBOSDiagFVC").getDccComment();
    }

    public void setSecondPostTransBOSDiagFVCDccComment(String dccComment) {
       attributes.get("secondPostTransBOSDiagFVC").setDccComment(dccComment);
    }

   public Date getThirdPostTransBOSDiagFEV1Date() {
       return (Date) attributes.get("thirdPostTransBOSDiagFEV1Date").getValue();
   }

   public void setThirdPostTransBOSDiagFEV1Date(Date thirdPostTransBOSDiagFEV1Date) {
       attributes.get("thirdPostTransBOSDiagFEV1Date").setValue(thirdPostTransBOSDiagFEV1Date);
   }

    public VerificationStatus getThirdPostTransBOSDiagFEV1DateVerificationStatus() {
       return attributes.get("thirdPostTransBOSDiagFEV1Date").getVerificationStatus();
    }

    public void setThirdPostTransBOSDiagFEV1DateVerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("thirdPostTransBOSDiagFEV1Date").setVerificationStatus(verificationStatus);
    }

    public String getThirdPostTransBOSDiagFEV1DateDccComment() {
       return (String) attributes.get("thirdPostTransBOSDiagFEV1Date").getDccComment();
    }

    public void setThirdPostTransBOSDiagFEV1DateDccComment(String dccComment) {
       attributes.get("thirdPostTransBOSDiagFEV1Date").setDccComment(dccComment);
    }
   
   public Float getThirdPostTransBOSDiagFEV1() {
       return (Float) attributes.get("thirdPostTransBOSDiagFEV1").getValue();
   }

   public void setThirdPostTransBOSDiagFEV1(Float thirdPostTransBOSDiagFEV1) {
       attributes.get("thirdPostTransBOSDiagFEV1").setValue(thirdPostTransBOSDiagFEV1);
   }
   
    public VerificationStatus getThirdPostTransBOSDiagFEV1VerificationStatus() {
       return attributes.get("thirdPostTransBOSDiagFEV1").getVerificationStatus();
    }

    public void setThirdPostTransBOSDiagFEV1VerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("thirdPostTransBOSDiagFEV1").setVerificationStatus(verificationStatus);
    }

    public String getThirdPostTransBOSDiagFEV1DccComment() {
       return (String) attributes.get("thirdPostTransBOSDiagFEV1").getDccComment();
    }

    public void setThirdPostTransBOSDiagFEV1DccComment(String dccComment) {
       attributes.get("thirdPostTransBOSDiagFEV1").setDccComment(dccComment);
    }

   public Float getThirdPostTransBOSDiagFVC() {
       return (Float) attributes.get("thirdPostTransBOSDiagFVC").getValue();
   }

   public void setThirdPostTransBOSDiagFVC(Float thirdPostTransBOSDiagFVC) {
       attributes.get("thirdPostTransBOSDiagFVC").setValue(thirdPostTransBOSDiagFVC);
   }
   
   public VerificationStatus getThirdPostTransBOSDiagFVCVerificationStatus() {
       return attributes.get("thirdPostTransBOSDiagFVC").getVerificationStatus();
    }

    public void setThirdPostTransBOSDiagFVCVerificationStatus(VerificationStatus verificationStatus) {
       attributes.get("thirdPostTransBOSDiagFVC").setVerificationStatus(verificationStatus);
    }

    public String getThirdPostTransBOSDiagFVCDccComment() {
       return (String) attributes.get("thirdPostTransBOSDiagFVC").getDccComment();
    }

    public void setThirdPostTransBOSDiagFVCDccComment(String dccComment) {
       attributes.get("thirdPostTransBOSDiagFVC").setDccComment(dccComment);
    }
  
    // Track baseline QOL completion
    public void setBaselineQualityOfLifeCompleted(boolean baselineQualityOfLifeCompleted) {
        attributes.get("baselineQualityOfLifeCompleted").setValue(baselineQualityOfLifeCompleted);
    }
    
    public boolean isBaselineQualityOfLifeCompleted() {
         return (Boolean) attributes.get("baselineQualityOfLifeCompleted").getValue();
    }
    
    public void setBaselineQualityOfLifeDate(Date baselineQualityOfLifeDate) {
        attributes.get("baselineQualityOfLifeDate").setValue(baselineQualityOfLifeDate);
    }
    
    public Date getBaselineQualityOfLifeDate() {
         return (Date) attributes.get("baselineQualityOfLifeDate").getValue();
    }
    
    public void setBaselineQualityOfLifeScreenId(String baselineQualityOfLifeScreenId) {
        attributes.get("baselineQualityOfLifeScreenId").setValue(baselineQualityOfLifeScreenId);
    }
    
    public String getBaselineQualityOfLifeScreenId() {
         return (String) attributes.get("baselineQualityOfLifeScreenId").getValue();
    }
    
    public List<PulmonaryEvaluation> getEvaluations() {
        String dateKey = "date";
        String fev1Key = "fev1";
        String fvcKey = "fvc";
        Date d;
        List<PulmonaryEvaluation> evals = new ArrayList<PulmonaryEvaluation>();
        
        // Copy all of the attributes into an array containing PulmonaryEvaluation objects.
        for(int i = 1; i <= MAX_NUMBER_FEV1S; i++){
            d = (Date) attributes.get(dateKey + i).getValue();
            if(d != null){
                Float fev1 = (Float) attributes.get(fev1Key + i).getValue();
                Float fvc = (Float) attributes.get(fvcKey + i).getValue();
                PulmonaryEvaluation pulmEval = new PulmonaryEvaluation(d, fev1, fvc);
                evals.add(pulmEval);
            }
        }
        return evals;
    }

    /*
     * Call this method when the form is submitted.  This method determines if
     * more than 5 FEV1s have been entered and need to be verified.
    */
    
    public void onNew(){
        if(getDate6() != null) {
            Attribute att_date = attributes.get("date6");
            att_date.setVerify(true);
//            att_date.setVerificationStatus("Unverified");
            Attribute att_fev1 = attributes.get("fev16");
            att_fev1.setVerify(true);
//            att_fev1.setVerificationStatus("Unverified");
            Attribute att_fvc = attributes.get("fvc6");
            att_fvc.setVerify(true);
//            att_fvc.setVerificationStatus("Unverified");
        }
        if(getDate7() != null) {
            Attribute att = attributes.get("date7");
            att.setVerify(true);
//            att.setVerificationStatus("Unverified");
        }
    }
    
    /*
     * Call this method when the form is submitted.  This method determines if
     * more than 5 FEV1s have been entered and need to be verified.
    */
    
    public void onSubmit(){
        if(getDate6() != null) {
            Attribute att_date = attributes.get("date6");
            att_date.setVerify(true);
            Attribute att_fev1 = attributes.get("fev16");
            att_fev1.setVerify(true);
            Attribute att_fvc = attributes.get("fvc6");
            att_fvc.setVerify(true);
        }
        if(getDate7() != null) {
            Attribute att = attributes.get("date7");
            att.setVerify(true);
        }
    }

    public Float getLastFev1() {
        Date d;
        SortedMap<Date, Float> fev1s = new TreeMap<>();
        
        // Put all fev1s into map that is sorted by date
        for(int i = 1; i <= MAX_NUMBER_FEV1S; i++){
            d = (Date) attributes.get("date" + i).getValue();
            if(d != null){
                fev1s.put(d, (Float) attributes.get("fev1" + i).getValue());
            } 
       }
       
       return fev1s.get(fev1s.lastKey());
    }
    
    public Float getMinimumFev1() {
        Float f;
        List<Float> fev1s = new ArrayList<>();
        
        // Put all fev1s into map that is sorted by date
        for(int i = 1; i <= MAX_NUMBER_FEV1S; i++){
            f = (Float) attributes.get("fev1" + i).getValue();
            if(f != null){
                fev1s.add(f);
            } 
        }
        
       Collections.sort(fev1s);
       
       return fev1s.get(0);
    }
}
