package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.AttributeTime;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import java.util.Date;
import org.primefaces.context.RequestContext;

/**
 *
 * @author pkc
 */
public class AdverseEventWorksheetSAEForm extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"A Signed Serious Adverse Event Form must be uploaded", "Clinical Note - Admission or Progress Note - ED Note - Discharge Summary", "Autopsy Report"};

   public AdverseEventWorksheetSAEForm() {
        // constructor
        super();
        formType = ECPFormTypes.SERIOUS_ADVERSE_EVENT;
        title = "Serious Adverse Event Form";
        this.sourceDocumentTypes = SourceDocumentTypes;
        
        // Section I of CRF
        attributes.put("currentDate", new AttributeDate("currentDate"));        
        attributes.put("aeTitle", new AttributeString("aeTitle"));
        attributes.put("onsetDate", new AttributeDate("onsetDate"));        
        attributes.put("onsetTime", new AttributeTime("onsetTime"));        
        attributes.put("resolutionDate", new AttributeDate("resolutionDate"));        
        attributes.put("ongoing", new AttributeString("ongoing"));
        attributes.put("fatal", new AttributeString("fatal"));
        attributes.put("lifeThreatening", new AttributeString("lifeThreatening"));
        attributes.put("seriousInjury", new AttributeString("seriousInjury"));
        attributes.put("hospitalAdmission", new AttributeString("hospitalAdmission"));
        attributes.put("pregnancyAbortion", new AttributeString("pregnancyAbortion"));
        attributes.put("congenitalAnomaly", new AttributeString("congenitalAnomaly"));
        attributes.put("cancerNeonateInfant", new AttributeString("cancerNeonateInfant"));
        attributes.put("aggressiveMedicalIntervention", new AttributeString("aggressiveMedicalIntervention"));
        attributes.put("seriouslyJeopardizedHealth", new AttributeString("seriouslyJeopardizedHealth"));
        attributes.put("emergencyDepartmentVisit", new AttributeString("emergencyDepartmentVisit"));
        attributes.put("isUnanticpatedProblem", new AttributeString("isUnanticpatedProblem"));
        // Section II of CRF        
        attributes.put("reportType", new AttributeString("reportType"));
        attributes.put("dateParticipantEnrollment", new AttributeDate("dateParticipantEnrollment"));   
        attributes.put("dateEventStarted", new AttributeDate("dateEventStarted"));   
        attributes.put("timeEventStarted", new AttributeTime("timeEventStarted"));   
        attributes.put("dateEventSerious", new AttributeDate("dateEventSerious"));   
        attributes.put("timeEventSerious", new AttributeTime("timeEventSerious"));   
        attributes.put("dateEventKnown", new AttributeDate("dateEventKnown"));   
        attributes.put("timeEventKnown", new AttributeTime("timeEventKnown"));   
        attributes.put("dateLastECPProcedure", new AttributeDate("dateLastECPProcedure"));   
        attributes.put("timeLastECPProcedure", new AttributeTime("timeLastECPProcedure"));   
        attributes.put("ecpProcedureNotRelated", new AttributeString("ecpProcedureNotRelated"));
        attributes.put("dateEventResolved", new AttributeDate("dateEventResolved"));   
        attributes.put("timeEventResolved", new AttributeTime("timeEventResolved"));   
        attributes.put("eventOngoing", new AttributeString("eventOngoing"));
//        attributes.put("didParticipantDie", new AttributeString("didParticipantDie"));
        attributes.put("dateOfDeath", new AttributeDate("dateOfDeath"));   
        attributes.put("timeOfDeath", new AttributeTime("timeOfDeath"));   
        attributes.put("dateOfDeathUnknown", new AttributeString("dateOfDeathUnknown"));
        attributes.put("causeOfDeath", new AttributeString("causeOfDeath"));
        attributes.put("autopsyPerformed", new AttributeString("autopsyPerformed"));
//        attributes.put("eventLifeThreatening", new AttributeString("eventLifeThreatening"));
        attributes.put("wasEventRelatedToUseECP", new AttributeString("wasEventRelatedToUseECP"));
        attributes.put("didEventOccurWithinECP", new AttributeString("didEventOccurWithinECP"));
        attributes.put("wasEventRelatedToCatheterUse", new AttributeString("wasEventRelatedToCatheterUse"));
        attributes.put("wasEventRelatedToMethoxsalenUse", new AttributeString("wasEventRelatedToMethoxsalenUse"));
        attributes.put("methoxsalen", new AttributeString("methoxsalen"));
        // Section III of CRF        
        attributes.put("fullChronologicalDescription", new AttributeString("fullChronologicalDescription"));
        attributes.put("specificDiagnosis", new AttributeString("specificDiagnosis"));
        attributes.put("specificDiagnosisNoChange", new AttributeString("specificDiagnosisNoChange"));
        attributes.put("expectedness", new AttributeString("expectedness"));
        attributes.put("notApplicable", new AttributeString("notApplicable"));
        attributes.put("dateLastAdminMethoxsalen", new AttributeDate("dateLastAdminMethoxsalen"));   
        attributes.put("timeLastAdminMethoxsalen", new AttributeTime("timeLastAdminMethoxsalen"));   
        attributes.put("methoxsalenDose", new AttributeString("methoxsalenDose"));
        attributes.put("noTreatment", new AttributeString("noTreatment"));
        attributes.put("nonInvasiveTreatment", new AttributeString("nonInvasiveTreatment"));
        attributes.put("minimallyInvasiveTreatment", new AttributeString("minimallyInvasiveTreatment"));
        attributes.put("openSurgery", new AttributeString("openSurgery"));
        attributes.put("treatmentGivenSpecify", new AttributeString("treatmentGivenSpecify"));
        attributes.put("relevantMedicalHistory", new AttributeString("relevantMedicalHistory"));
        attributes.put("relevantLabImageFindings", new AttributeString("relevantLabImageFindings"));
        attributes.put("outcome", new AttributeString("outcome"));
        
        attributes.put("comment", new AttributeString("comment"));

        this.clear();
        // The following line of code is used to initialize the title and questions in Section 1 of the SAE.
//        this.initializeQuestions();
        // The above function has moved to the AEController
    }

    public AdverseEventWorksheetSAEForm( BasicForm bf) {
        super(bf);		
        formType = ECPFormTypes.SERIOUS_ADVERSE_EVENT;
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
	
    @Override
    public void versionControl() {
        if ("7.1".equals(this.getCrfVersion())) {
            attributes.put("symptomaticPulmonaryEmbolism", new AttributeString("symptomaticPulmonaryEmbolism"));
            attributes.put("deepVeinThrombosis", new AttributeString("deepVeinThrombosis"));
            attributes.put("dvtLocation", new AttributeString("dvtLocation"));
            
            attributes.put("relatedToResearch", new AttributeString("relatedToResearch"));
            
            attributes.put("otherTreatmentGiven", new AttributeString("otherTreatmentGiven"));
        }
    }
	
    public String getAeTitle() {
        return (String) attributes.get("aeTitle").getValue();
    }
    
    public void setAeTitle(String aeTitle) {
        attributes.get("aeTitle").setValue(aeTitle);
    }

    public Date getCurrentDate() {
        return (Date) attributes.get("currentDate").getValue();
    }
    
    public void setCurrentDate(Date currentDate) {
        attributes.get("currentDate").setValue(currentDate);
    }

    public Date getOnsetDate() {
        return (Date) attributes.get("onsetDate").getValue();
    }
    
    public void setOnsetDate(Date onsetDate) {
        attributes.get("onsetDate").setValue(onsetDate);
    }

    public Date getOnsetTime() {
        return (Date) attributes.get("onsetTime").getValue();
    }
    
    public void setOnsetTime(Date onsetTime) {
        attributes.get("onsetTime").setValue(onsetTime);
    }

    public Date getResolutionDate() {
        return (Date) attributes.get("resolutionDate").getValue();
    }
    
    public void setResolutionDate(Date resolutionDate) {
        attributes.get("resolutionDate").setValue(resolutionDate);
    }

    public String getOngoing() {
        return (String) attributes.get("ongoing").getValue();
    }
    
    public void setOngoing(String ongoing) {
        attributes.get("ongoing").setValue(ongoing);
    }

    public String getFatal() {
        String s = (String) attributes.get("fatal").getValue();
//        System.out.println("The getFatal value is: " + s);
        return (String) attributes.get("fatal").getValue();
    }
    
    public void setFatal(String fatal) {
        String s = (String) attributes.get("fatal").getValue();
        System.out.println("The getFatal value BEFORE setting is : " + s);
        System.out.println("The setFatal value is: " + fatal);
        attributes.get("fatal").setValue(fatal);
        s = (String) attributes.get("fatal").getValue();
        System.out.println("The getFatal value AFTER being set is : " + s + "\n");
    }

    public String getLifeThreatening() {
        return (String) attributes.get("lifeThreatening").getValue();
    }
    
    public void setLifeThreatening(String lifeThreatening) {
        String s = (String) attributes.get("lifeThreatening").getValue();
        System.out.println("The getLifeThreatening value BEFORE setting is : " + s);
        System.out.println("The setLifeThreatening value is: " + lifeThreatening);
        attributes.get("lifeThreatening").setValue(lifeThreatening);
        s = (String) attributes.get("lifeThreatening").getValue();
        System.out.println("The getLifeThreatening value AFTER being set is : " + s + "\n");
    }

    public String getSeriousInjury() {
        return (String) attributes.get("seriousInjury").getValue();
    }
    
    public void setSeriousInjury(String seriousInjury) {
        attributes.get("seriousInjury").setValue(seriousInjury);
    }

    public String getHospitalAdmission() {
        return (String) attributes.get("hospitalAdmission").getValue();
    }
    
    public void setHospitalAdmission(String hospitalAdmission) {
        attributes.get("hospitalAdmission").setValue(hospitalAdmission);
    }

    public String getPregnancyAbortion() {
        return (String) attributes.get("pregnancyAbortion").getValue();
    }
    
    public void setPregnancyAbortion(String pregnancyAbortion) {
        attributes.get("pregnancyAbortion").setValue(pregnancyAbortion);
    }

    public String getCongenitalAnomaly() {
        return (String) attributes.get("congenitalAnomaly").getValue();
    }
    
    public void setCongenitalAnomaly(String congenitalAnomaly) {
        attributes.get("congenitalAnomaly").setValue(congenitalAnomaly);
    }

    public String getCancerNeonateInfant() {
        return (String) attributes.get("cancerNeonateInfant").getValue();
    }
    
    public void setCancerNeonateInfant(String cancerNeonateInfant) {
        attributes.get("cancerNeonateInfant").setValue(cancerNeonateInfant);
    }

    public String getAggressiveMedicalIntervention() {
        return (String) attributes.get("aggressiveMedicalIntervention").getValue();
    }
    
    public void setAggressiveMedicalIntervention(String aggressiveMedicalIntervention) {
        attributes.get("aggressiveMedicalIntervention").setValue(aggressiveMedicalIntervention);
    }

    public String getSeriouslyJeopardizedHealth() {
        return (String) attributes.get("seriouslyJeopardizedHealth").getValue();
    }
    
    public void setSeriouslyJeopardizedHealth(String seriouslyJeopardizedHealth) {
        attributes.get("seriouslyJeopardizedHealth").setValue(seriouslyJeopardizedHealth);
    }

    public String getEmergencyDepartmentVisit() {
        return (String) attributes.get("emergencyDepartmentVisit").getValue();
    }
    
    public void setEmergencyDepartmentVisit(String emergencyDepartmentVisit) {
        attributes.get("emergencyDepartmentVisit").setValue(emergencyDepartmentVisit);
    }

    public String getIsUnanticipatedProblem() {
        return (String) attributes.get("isUnanticpatedProblem").getValue();
    }
    
    public void setIsUnanticipatedProblem(String isUnanticpatedProblem) {
        attributes.get("isUnanticpatedProblem").setValue(isUnanticpatedProblem);
    }

    public boolean isUnanticipatedProblem() {
       return "true".equals(getIsUnanticipatedProblem());
   }
    
    public String getReportType() {
        return (String) attributes.get("reportType").getValue();
    }
    
    public void setReportType(String reportType) {
        attributes.get("reportType").setValue(reportType);
    }

    public Date getDateParticipantEnrollment() {
        return (Date) attributes.get("dateParticipantEnrollment").getValue();
    }
    
    public void setDateParticipantEnrollment(Date dateParticipantEnrollment) {
        attributes.get("dateParticipantEnrollment").setValue(dateParticipantEnrollment);
    }

    public Date getDateEventStarted() {
        return (Date) attributes.get("dateEventStarted").getValue();
    }
    
    public void setDateEventStarted(Date dateEventStarted) {
        attributes.get("dateEventStarted").setValue(dateEventStarted);
    }

    public Date getTimeEventStarted() {
        return (Date) attributes.get("timeEventStarted").getValue();
    }
    
    public void setTimeEventStarted(Date timeEventStarted) {
        attributes.get("timeEventStarted").setValue(timeEventStarted);
    }

    public Date getDateEventSerious() {
        return (Date) attributes.get("dateEventSerious").getValue();
    }
    
    public void setDateEventSerious(Date dateEventSerious) {
        attributes.get("dateEventSerious").setValue(dateEventSerious);
    }

    public Date getTimeEventSerious() {
        return (Date) attributes.get("timeEventSerious").getValue();
    }
    
    public void setTimeEventSerious(Date timeEventSerious) {
        attributes.get("timeEventSerious").setValue(timeEventSerious);
    }

    public Date getDateEventKnown() {
        return (Date) attributes.get("dateEventKnown").getValue();
    }
    
    public void setDateEventKnown(Date dateEventKnown) {
        attributes.get("dateEventKnown").setValue(dateEventKnown);
    }

    public Date getTimeEventKnown() {
        return (Date) attributes.get("timeEventKnown").getValue();
    }
    
    public void setTimeEventKnown(Date timeEventKnown) {
        attributes.get("timeEventKnown").setValue(timeEventKnown);
    }

    public Date getDateLastECPProcedure() {
        return (Date) attributes.get("dateLastECPProcedure").getValue();
    }
    
    public void setDateLastECPProcedure(Date dateLastECPProcedure) {
        attributes.get("dateLastECPProcedure").setValue(dateLastECPProcedure);
    }

    public Date getTimeLastECPProcedure() {
        return (Date) attributes.get("timeLastECPProcedure").getValue();
    }
    
    public void setTimeLastECPProcedure(Date timeLastECPProcedure) {
        attributes.get("timeLastECPProcedure").setValue(timeLastECPProcedure);
    }

    public String getEcpProcedureNotRelated() {
        return (String) attributes.get("ecpProcedureNotRelated").getValue();
    }
    
    public void setEcpProcedureNotRelated(String ecpProcedureNotRelated) {
        attributes.get("ecpProcedureNotRelated").setValue(ecpProcedureNotRelated);
    }

    public Date getDateEventResolved() {
        return (Date) attributes.get("dateEventResolved").getValue();
    }
    
    public void setDateEventResolved(Date dateEventResolved) {
        attributes.get("dateEventResolved").setValue(dateEventResolved);
    }

    public Date getTimeEventResolved() {
        return (Date) attributes.get("timeEventResolved").getValue();
    }
    
    public void setTimeEventResolved(Date timeEventResolved) {
        attributes.get("timeEventResolved").setValue(timeEventResolved);
    }

    public String getEventOngoing() {
        return (String) attributes.get("eventOngoing").getValue();
    }
    
    public void setEventOngoing(String eventOngoing) {
        attributes.get("eventOngoing").setValue(eventOngoing);
    }
/*
    public String getDidParticipantDie() {
        return (String) attributes.get("didParticipantDie").getValue();
    }
    
    public void setDidParticipantDie(String didParticipantDie) {
        attributes.get("didParticipantDie").setValue(didParticipantDie);
    }
*/
    public Date getDateOfDeath() {
        return (Date) attributes.get("dateOfDeath").getValue();
    }
    
    public void setDateOfDeath(Date dateOfDeath) {
        attributes.get("dateOfDeath").setValue(dateOfDeath);
    }

    public Date getTimeOfDeath() {
        return (Date) attributes.get("timeOfDeath").getValue();
    }
    
    public void setTimeOfDeath(Date timeOfDeath) {
        attributes.get("timeOfDeath").setValue(timeOfDeath);
    }

    public String getDateOfDeathUnknown() {
        return (String) attributes.get("dateOfDeathUnknown").getValue();
    }
    
    public void setDateOfDeathUnknown(String dateOfDeathUnknown) {
        attributes.get("dateOfDeathUnknown").setValue(dateOfDeathUnknown);
    }

    public String getCauseOfDeath() {
        return (String) attributes.get("causeOfDeath").getValue();
    }
    
    public void setCauseOfDeath(String causeOfDeath) {
        attributes.get("causeOfDeath").setValue(causeOfDeath);
    }

    public String getAutopsyPerformed() {
        return (String) attributes.get("autopsyPerformed").getValue();
    }
    
    public void setAutopsyPerformed(String autopsyPerformed) {
        attributes.get("autopsyPerformed").setValue(autopsyPerformed);
    }
/*
    public String getEventLifeThreatening() {
        return (String) attributes.get("eventLifeThreatening").getValue();
    }
    
    public void setEventLifeThreatening(String eventLifeThreatening) {
        attributes.get("eventLifeThreatening").setValue(eventLifeThreatening);
    }
*/
    public String getWasEventRelatedToUseECP() {
        return (String) attributes.get("wasEventRelatedToUseECP").getValue();
    }
    
    public void setWasEventRelatedToUseECP(String wasEventRelatedToUseECP) {
        attributes.get("wasEventRelatedToUseECP").setValue(wasEventRelatedToUseECP);
    }

    public String getDidEventOccurWithinECP() {
        return (String) attributes.get("didEventOccurWithinECP").getValue();
    }
    
    public void setDidEventOccurWithinECP(String didEventOccurWithinECP) {
        attributes.get("didEventOccurWithinECP").setValue(didEventOccurWithinECP);
    }

    public String getWasEventRelatedToCatheterUse() {
        return (String) attributes.get("wasEventRelatedToCatheterUse").getValue();
    }
    
    public void setWasEventRelatedToCatheterUse(String wasEventRelatedToCatheterUse) {
        attributes.get("wasEventRelatedToCatheterUse").setValue(wasEventRelatedToCatheterUse);
    }

    public String getWasEventRelatedToMethoxsalenUse() {
        return (String) attributes.get("wasEventRelatedToMethoxsalenUse").getValue();
    }
    
    public void setWasEventRelatedToMethoxsalenUse(String wasEventRelatedToMethoxsalenUse) {
        attributes.get("wasEventRelatedToMethoxsalenUse").setValue(wasEventRelatedToMethoxsalenUse);
    }

    public String getMethoxsalen() {
        return (String) attributes.get("methoxsalen").getValue();
    }
    
    public void setMethoxsalen(String methoxsalen) {
        attributes.get("methoxsalen").setValue(methoxsalen);
    }

    
    public String getFullChronologicalDescription() {
        return (String) attributes.get("fullChronologicalDescription").getValue();
    }
    
    public void setFullChronologicalDescription(String fullChronologicalDescription) {
        attributes.get("fullChronologicalDescription").setValue(fullChronologicalDescription);
    }

    public String getSpecificDiagnosis() {
        return (String) attributes.get("specificDiagnosis").getValue();
    }
    
    public void setSpecificDiagnosis(String specificDiagnosis) {
        attributes.get("specificDiagnosis").setValue(specificDiagnosis);
    }

    public String getSpecificDiagnosisNoChange() {
        return (String) attributes.get("specificDiagnosisNoChange").getValue();
    }
    
    public void setSpecificDiagnosisNoChange(String specificDiagnosisNoChange) {
        attributes.get("specificDiagnosisNoChange").setValue(specificDiagnosisNoChange);
    }

    public String getExpectedness() {
        return (String) attributes.get("expectedness").getValue();
    }
    
    public void setExpectedness(String expectedness) {
        attributes.get("expectedness").setValue(expectedness);
    }

    public String getNotApplicable() {
        return (String) attributes.get("notApplicable").getValue();
    }
    
    public void setNotApplicable(String notApplicable) {
        attributes.get("notApplicable").setValue(notApplicable);
    }

    public Date getDateLastAdminMethoxsalen() {
        return (Date) attributes.get("dateLastAdminMethoxsalen").getValue();
    }
    
    public void setDateLastAdminMethoxsalen(Date dateLastAdminMethoxsalen) {
        attributes.get("dateLastAdminMethoxsalen").setValue(dateLastAdminMethoxsalen);
    }

    public Date getTimeLastAdminMethoxsalen() {
        return (Date) attributes.get("timeLastAdminMethoxsalen").getValue();
    }
    
    public void setTimeLastAdminMethoxsalen(Date timeLastAdminMethoxsalen) {
        attributes.get("timeLastAdminMethoxsalen").setValue(timeLastAdminMethoxsalen);
    }

    public String getMethoxsalenDose() {
        return (String) attributes.get("methoxsalenDose").getValue();
    }
    
    public void setMethoxsalenDose(String methoxsalenDose) {
        attributes.get("methoxsalenDose").setValue(methoxsalenDose);
    }

            
    public String getNoTreatment() {
        return (String) attributes.get("noTreatment").getValue();
    }
    
    public void setNoTreatment(String noTreatment) {
        attributes.get("noTreatment").setValue(noTreatment);
    }

    public String getNonInvasiveTreatment() {
        return (String) attributes.get("nonInvasiveTreatment").getValue();
    }
    
    public void setNonInvasiveTreatment(String nonInvasiveTreatment) {
        attributes.get("nonInvasiveTreatment").setValue(nonInvasiveTreatment);
    }

    public String getMinimallyInvasiveTreatment() {
        return (String) attributes.get("minimallyInvasiveTreatment").getValue();
    }
    
    public void setMinimallyInvasiveTreatment(String minimallyInvasiveTreatment) {
        attributes.get("minimallyInvasiveTreatment").setValue(minimallyInvasiveTreatment);
    }

    public String getOpenSurgery() {
        return (String) attributes.get("openSurgery").getValue();
    }
    
    public void setOpenSurgery(String openSurgery) {
        attributes.get("openSurgery").setValue(openSurgery);
    }

    public String getTreatmentGivenSpecify() {
        return (String) attributes.get("treatmentGivenSpecify").getValue();
    }
    
    public void setTreatmentGivenSpecify(String treatmentGivenSpecify) {
        attributes.get("treatmentGivenSpecify").setValue(treatmentGivenSpecify);
    }

    public String getRelevantMedicalHistory() {
        return (String) attributes.get("relevantMedicalHistory").getValue();
    }
    
    public void setRelevantMedicalHistory(String relevantMedicalHistory) {
        attributes.get("relevantMedicalHistory").setValue(relevantMedicalHistory);
    }

    public String getRelevantLabImageFindings() {
        return (String) attributes.get("relevantLabImageFindings").getValue();
    }
    
    public void setRelevantLabImageFindings(String relevantLabImageFindings) {
        attributes.get("relevantLabImageFindings").setValue(relevantLabImageFindings);
    }

    public String getOutcome() {
        return (String) attributes.get("outcome").getValue();
    }
    
    public void setOutcome(String outcome) {
        attributes.get("outcome").setValue(outcome);
    }

    public Date getDateOfLastECP() {
        return (Date) attributes.get("dateOfLastECP").getValue();
    }
    
    public void setDateOfLastECP(Date dateOfLastECP) {
        attributes.get("dateOfLastECP").setValue(dateOfLastECP);
    }

    public Date getTimeOfLastECP() {
        return (Date) attributes.get("timeOfLastECP").getValue();
    }
    
    public void setTimeOfLastECP(Date timeOfLastECP) {
        attributes.get("timeOfLastECP").setValue(timeOfLastECP);
    }
    
    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }
    
    public String getSymptomaticPulmonaryEmbolism() {
        return (String) attributes.get("symptomaticPulmonaryEmbolism").getValue();
    }
    
    public void setSymptomaticPulmonaryEmbolism(String symptomaticPulmonaryEmbolism) {
        attributes.get("symptomaticPulmonaryEmbolism").setValue(symptomaticPulmonaryEmbolism);
    }
	
    public String getDeepVeinThrombosis() {
        return (String) attributes.get("deepVeinThrombosis").getValue();
    }
    
    public void setDeepVeinThrombosis(String deepVeinThrombosis) {
        attributes.get("deepVeinThrombosis").setValue(deepVeinThrombosis);
    }
	
    public String getDvtLocation() {
        return (String) attributes.get("dvtLocation").getValue();
    }
    
    public void setDvtLocation(String dvtLocation) {
        attributes.get("dvtLocation").setValue(dvtLocation);
    }
	
    public String getRelatedToResearch() {
        return (String) attributes.get("relatedToResearch").getValue();
    }
    
    public void setRelatedToResearch(String relatedToResearch) {
        attributes.get("relatedToResearch").setValue(relatedToResearch);
    }
	
    public String getOtherTreatmentGiven() {
        return (String) attributes.get("otherTreatmentGiven").getValue();
    }
    
    public void setOtherTreatmentGiven(String otherTreatmentGiven) {
        attributes.get("otherTreatmentGiven").setValue(otherTreatmentGiven);
    }

    public String getFatalAnswer() {
        
        if( "true".equals(getFatal()))
            return "YES";
        if( "false".equals(getFatal()))
            return "NO";
        else
            return "";
    }
    
    public String getLifeThreateningAnswer() {
        
        if( "true".equals(getLifeThreatening()))
            return "YES";
        if( "false".equals(getLifeThreatening()))
            return "NO";
        else
            return "";
    }
    
    public boolean isMethoxsalenUsed() {
        if(this.getWasEventRelatedToMethoxsalenUse() == null || this.getWasEventRelatedToMethoxsalenUse() == ""){
            System.out.println("The value of AEWorksheetSAEForm getWasEventRelatedToMethoxsalenUse() is NULL!!!!!!!!!!!!!!!!!!!!!!!)");
            return false;
        }
//            System.out.println("The value of AEWorksheetSAEForm getWasEventRelatedToMethoxsalenUse() is:" + this.getWasEventRelatedToMethoxsalenUse().equals("true") + "!!");
        return this.getWasEventRelatedToMethoxsalenUse().equals("true");
    }
}
