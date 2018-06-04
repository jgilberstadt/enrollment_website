package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.Attribute;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;

public class EnrollmentForm extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"History and Physical or Consultation Note", "Operative Report of Transplant Procedure"};
    
    public EnrollmentForm() {
        super();
        this.formType = ECPFormTypes.ENROLLMENT;
        title = "Enrollment Form";
        this.sourceDocumentTypes = SourceDocumentTypes;
//        this.crfVersion = "1.0";  // The CRF version is the same as the irb version in which this form was either created or manually changed to after creation in the basic form table.
        
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
        attributes.put("dateInformedConsentSigned", new AttributeDate("dateInformedConsentSigned"));
        attributes.put("dateInformedConsentVersion", new AttributeDate("dateInformedConsentVersion"));
        attributes.put("dateStudyRegistration", new AttributeDate("dateStudyRegistration"));
        attributes.put("baselineFEV1", new AttributeFloat("baselineFEV1", true, false, true));
        attributes.put("firstComponentFEV1Date", new AttributeDate("firstComponentFEV1Date", true, false, true));
        attributes.put("firstComponentFEV1", new AttributeFloat("firstComponentFEV1", true, false, true));
        attributes.put("firstComponentFVC", new AttributeFloat("firstComponentFVC", true, false, true));
        attributes.put("secondComponentFEV1Date", new AttributeDate("secondComponentFEV1Date", true, false, true));
        attributes.put("secondComponentFEV1", new AttributeFloat("secondComponentFEV1", true, false, true));
        attributes.put("secondComponentFVC", new AttributeFloat("secondComponentFVC", true, false, true));
        
        attributes.put("labBasedNewBOSDiagnosis", new AttributeString("labBasedNewBOSDiagnosis"));
        attributes.put("preDiagnosisMonitoring", new AttributeString("preDiagnosisMonitoring"));
        attributes.put("documentedClinicalAssessment", new AttributeString("documentedClinicalAssessment"));
        attributes.put("leukopenia3000", new AttributeString("leukopenia3000"));
        attributes.put("declineNotBOS", new AttributeString("declineNotBOS"));
        attributes.put("acuteDeclineTreatment", new AttributeString("acuteDeclineTreatment"));
        
        this.clear();
    }
    
    public EnrollmentForm( BasicForm bf) {
        super(bf);
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
    
    public void addLeukopeniaAttribute() {
        attributes.put("leukopenia", new AttributeString("leukopenia"));
    }
    
    public Date getEnrollmentDate() {
        this.setEnrollmentDate(new Date());
        return (Date) attributes.get("enrollmentDate").getValue();
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        attributes.get("enrollmentDate").setValue(enrollmentDate);
    }

    public String getAge() {
//        System.out.println("The getAge value is: " + attributes.get("age").getValue());
        return (String) attributes.get("age").getValue();
    }
    
    public void setAge(String age) {
//        System.out.println("The setAge value is: " + age);
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
//        System.out.println("The getMedicare value is: " + attributes.get("medicare").getValue() + "\n");
        return (String) attributes.get("medicare").getValue();
    }
    
    public void setMedicare(String medicare) {
//        System.out.println("The setMedicare value is: " + medicare + "\n");
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
    
    public void setPreDiagnosisMonitoring(String preDiagnosisMonitoring) {
        attributes.get("preDiagnosisMonitoring").setValue(preDiagnosisMonitoring);
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
}
