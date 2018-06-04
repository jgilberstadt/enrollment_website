package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeBoolean;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="SeriousAdverseEventBean")
@SessionScoped
/**
 *
 * @author pkc
 */
public class SeriousAdverseEventForm extends BasicForm implements Serializable {
    
    public SeriousAdverseEventForm() {
        // constructor
        super();
        formType = ECPFormTypes.SERIOUS_ADVERSE_EVENT;
        
        attributes.put("centerId", new AttributeString("centerId"));
        attributes.put("participantID", new AttributeString("participantID"));
        attributes.put("qcInitials", new AttributeString("qcInitials"));
        attributes.put("todaysDate", new AttributeDate("todaysDate"));        
        attributes.put("reportType", new AttributeString("reportType"));
        attributes.put("dateEventStarted", new AttributeDate("dateEventStarted"));   
        attributes.put("timeEventStarted", new AttributeDate("timeEventStarted"));   
        attributes.put("dateEventSerious", new AttributeDate("dateEventSerious"));   
        attributes.put("timeEventSerious", new AttributeDate("timeEventSerious"));   
        attributes.put("dateEventKnown", new AttributeDate("dateEventKnown"));   
        attributes.put("timeEventKnown", new AttributeDate("timeEventKnown"));   
        attributes.put("dateEventResolved", new AttributeDate("dateEventResolved"));   
        attributes.put("timeEventResolved", new AttributeDate("timeEventResolved"));   
        attributes.put("ongoing", new AttributeBoolean("ongoing"));
        attributes.put("didParticipantDie", new AttributeBoolean("didParticipantDie"));
        attributes.put("dateOfDeath", new AttributeDate("dateOfDeath"));   
        attributes.put("timeOfDeath", new AttributeDate("timeOfDeath"));   
        attributes.put("dateOfDeathUnknown", new AttributeBoolean("dateOfDeathUnknown"));
        attributes.put("causeOfDeath", new AttributeString("causeOfDeath"));
        attributes.put("autopsyPerformed", new AttributeBoolean("autopsyPerformed"));
        attributes.put("fullChronologicalDescription", new AttributeString("fullChronologicalDescription"));
        attributes.put("specificDiagnosis", new AttributeString("specificDiagnosis"));
        attributes.put("specificDiagnosisNoChange", new AttributeBoolean("specificDiagnosisNoChange"));
        attributes.put("noTreatment", new AttributeBoolean("noTreatment"));
        attributes.put("nonInvasiveTreatment", new AttributeBoolean("nonInvasiveTreatment"));
        attributes.put("minimallyInvasiveTreatment", new AttributeBoolean("minimallyInvasiveTreatment"));
        attributes.put("openSurgery", new AttributeBoolean("openSurgery"));
        attributes.put("treatmentGivenSpecify", new AttributeString("treatmentGivenSpecify"));
        attributes.put("relevantMedicalHistory", new AttributeString("relevantMedicalHistory"));
        attributes.put("relevantLabImageFindings", new AttributeString("relevantLabImageFindings"));
        attributes.put("outcome", new AttributeString("outcome"));
        attributes.put("dateOfEnrollment", new AttributeDate("dateOfEnrollment"));        
        attributes.put("dateOfLastECP", new AttributeDate("dateOfLastECP"));   
        attributes.put("timeOfLastECP", new AttributeDate("timeOfLastECP"));   
        attributes.put("didEventOccurWithinECP", new AttributeBoolean("didEventOccurWithinECP"));
        attributes.put("wasEventRelatedToUseECP", new AttributeBoolean("wasEventRelatedToUseECP"));
        attributes.put("methoxsalen", new AttributeString("methoxsalen"));
        attributes.put("wasEventRelatedToUseMethoxsalen", new AttributeString("wasEventRelatedToUseMethoxsalen"));
   
        this.clear();
        attributes.get("centerId").setValue("101");
        System.out.println("The Serious Adverse Event Form center id is: " + (String) attributes.get("centerId").getValue());
    }
    
    public SeriousAdverseEventForm( BasicForm bf) {
        super(bf);		
        formType = ECPFormTypes.SERIOUS_ADVERSE_EVENT;
    }
	
    public String getCenterID() {
        return (String) attributes.get("centerId").getValue();
    }
    
    public void setCenterID(String centerId) {
        attributes.get("centerId").setValue(centerId);
    }

    public String getParticipantID() {
        return (String) attributes.get("participantID").getValue();
    }
    
    public void setParticipantID(String participantID) {
        attributes.get("participantID").setValue(participantID);
    }

    public String getQcInitials() {
        return (String) attributes.get("qcInitials").getValue();
    }
    
    public void setQcInitials(String qcInitials) {
        attributes.get("qcInitials").setValue(qcInitials);
    }

    public Date getTodaysDate() {
        return (Date) attributes.get("todaysDate").getValue();
    }
    
    public void setTodaysDate(Date todaysDate) {
        attributes.get("todaysDate").setValue(todaysDate);
    }

    public String getReportType() {
        return (String) attributes.get("reportType").getValue();
    }
    
    public void setReportType(String reportType) {
        attributes.get("reportType").setValue(reportType);
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

    public Boolean getOngoing() {
        return (Boolean) attributes.get("ongoing").getValue();
    }
    
    public void setOngoing(Boolean ongoing) {
        attributes.get("ongoing").setValue(ongoing);
    }

    public Boolean getDidParticipantDie() {
        return (Boolean) attributes.get("didParticipantDie").getValue();
    }
    
    public void setDidParticipantDie(Boolean didParticipantDie) {
        attributes.get("didParticipantDie").setValue(didParticipantDie);
    }

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

    public Boolean getDateOfDeathUnknown() {
        return (Boolean) attributes.get("dateOfDeathUnknown").getValue();
    }
    
    public void setDateOfDeathUnknown(Boolean dateOfDeathUnknown) {
        attributes.get("dateOfDeathUnknown").setValue(dateOfDeathUnknown);
    }

    public String getCauseOfDeath() {
        return (String) attributes.get("causeOfDeath").getValue();
    }
    
    public void setCauseOfDeath(String causeOfDeath) {
        attributes.get("causeOfDeath").setValue(causeOfDeath);
    }

    public Boolean getAutopsyPerformed() {
        return (Boolean) attributes.get("autopsyPerformed").getValue();
    }
    
    public void setAutopsyPerformed(Boolean autopsyPerformed) {
        attributes.get("autopsyPerformed").setValue(autopsyPerformed);
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

    public Boolean getSpecificDiagnosisNoChange() {
        return (Boolean) attributes.get("specificDiagnosisNoChange").getValue();
    }
    
    public void setSpecificDiagnosisNoChange(Boolean specificDiagnosisNoChange) {
        attributes.get("specificDiagnosisNoChange").setValue(specificDiagnosisNoChange);
    }

    public Boolean getNoTreatment() {
        return (Boolean) attributes.get("noTreatment").getValue();
    }
    
    public void setNoTreatment(Boolean noTreatment) {
        attributes.get("noTreatment").setValue(noTreatment);
    }

    public Boolean getNonInvasiveTreatment() {
        return (Boolean) attributes.get("nonInvasiveTreatment").getValue();
    }
    
    public void setNonInvasiveTreatment(Boolean nonInvasiveTreatment) {
        attributes.get("nonInvasiveTreatment").setValue(nonInvasiveTreatment);
    }

    public Boolean getMinimallyInvasiveTreatment() {
        return (Boolean) attributes.get("minimallyInvasiveTreatment").getValue();
    }
    
    public void setMinimallyInvasiveTreatment(Boolean minimallyInvasiveTreatment) {
        attributes.get("minimallyInvasiveTreatment").setValue(minimallyInvasiveTreatment);
    }

    public Boolean getOpenSurgery() {
        return (Boolean) attributes.get("openSurgery").getValue();
    }
    
    public void setOpenSurgery(Boolean openSurgery) {
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

    public Date getDateOfEnrollment() {
        return (Date) attributes.get("dateOfEnrollment").getValue();
    }
    
    public void setDateOfEnrollment(Date dateOfEnrollment) {
        attributes.get("dateOfEnrollment").setValue(dateOfEnrollment);
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
    
    public Boolean getDidEventOccurWithinECP() {
        return (Boolean) attributes.get("didEventOccurWithinECP").getValue();
    }
    
    public void setDidEventOccurWithinECP(Boolean didEventOccurWithinECP) {
        attributes.get("didEventOccurWithinECP").setValue(didEventOccurWithinECP);
    }

    public Boolean getWasEventRelatedToUseECP() {
        return (Boolean) attributes.get("wasEventRelatedToUseECP").getValue();
    }
    
    public void setWasEventRelatedToUseECP(Boolean wasEventRelatedToUseECP) {
        attributes.get("wasEventRelatedToUseECP").setValue(wasEventRelatedToUseECP);
    }

    public String getMethoxsalen() {
        return (String) attributes.get("methoxsalen").getValue();
    }
    
    public void setMethoxsalen(String methoxsalen) {
        attributes.get("methoxsalen").setValue(methoxsalen);
    }

    public Boolean getWasEventRelatedToUseMethoxsalen() {
        return (Boolean) attributes.get("wasEventRelatedToUseMethoxsalen").getValue();
    }
    
    public void setWasEventRelatedToUseMethoxsalen(Boolean wasEventRelatedToUseMethoxsalen) {
        attributes.get("wasEventRelatedToUseMethoxsalen").setValue(wasEventRelatedToUseMethoxsalen);
    }



}
