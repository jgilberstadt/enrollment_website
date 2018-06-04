/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;

/**
 *
 * @author lwalla01
 */
public class DSCForm extends BasicForm {
    
     public DSCForm() {
        super();
         
        this.formType = ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION;
        title = "Disease Specific Categorization Form";
        
        attributes.put("dscDate", new AttributeDate("dscDate", true, false, true));
        
        // For CCC Use Only variables
        attributes.put("centralNervousSysTIA", new AttributeString("centralNervousSysTIA", false, false, true));
        attributes.put("centralNervousSysCVA", new AttributeString("centralNervousSysCVA", false, false, true));
        attributes.put("centralNervousSysIntracranialHemorrhage", new AttributeString("centralNervousSysIntracranialHemorrhage", false, false, true));
        attributes.put("centralNervousSysAcuteNeurDeficit", new AttributeString("centralNervousSysAcuteNeurDeficit", false, false, true));
        attributes.put("centralNervousSysOther", new AttributeString("centralNervousSysOther", false, false, true));
        
        attributes.put("myocardialIschemia", new AttributeString("myocardialIschemia", false, false, true));
        attributes.put("myocardialInfarction", new AttributeString("myocardialInfarction", false, false, true));
        attributes.put("myocardialArrythmia", new AttributeString("myocardialArrythmia", false, false, true));
        attributes.put("myocardialOther", new AttributeString("myocardialOther", false, false, true));
        
        attributes.put("renalAcuteFailure", new AttributeString("renalAcuteFailure", false, false, true));
        attributes.put("renalChronicFailure", new AttributeString("renalChronicFailure", false, false, true));
        attributes.put("renalOther", new AttributeString("renalOther", false, false, true));
        
        attributes.put("pulmonaryAcuteRespiratoryFailure", new AttributeString("pulmonaryAcuteRespiratoryFailure", false, false, true));
        attributes.put("pulmonaryBOS", new AttributeString("pulmonaryBOS", false, false, true));
        attributes.put("pulmonaryAcuteHypoxemia", new AttributeString("pulmonaryAcuteHypoxemia", false, false, true));
        attributes.put("pulmonaryInfection", new AttributeString("pulmonaryInfection", false, false, true));
        attributes.put("pulmonaryOther", new AttributeString("pulmonaryOther", false, false, true));

        attributes.put("hepaticAcuteFailure", new AttributeString("hepaticAcuteFailure", false, false, true));
        attributes.put("iseInEnzymes", new AttributeString("hepaticAcuteRiseInEnzymes", false, false, true));
        attributes.put("hepaticOther", new AttributeString("hepaticOther", false, false, true));
        
        attributes.put("diabetesNewDiagnosis", new AttributeString("diabetesNewDiagnosis", false, false, true));
        attributes.put("diabetesAcuteExacerbation", new AttributeString("diabetesAcuteExacerbation", false, false, true));
        attributes.put("diabetesOther", new AttributeString("diabetesOther", false, false, true));
        
        attributes.put("hematologicAnemia", new AttributeString("hematologicAnemia", false, false, true));
        attributes.put("hematologicThrombocytopenia", new AttributeString("hematologicThrombocytopenia", false, false, true));
        attributes.put("hematologicLeukopenia", new AttributeString("hematologicLeukopenia", false, false, true));
        attributes.put("hematologicLeukocystosis", new AttributeString("hematologicLeukocystosis", false, false, true));
        attributes.put("hematologicOther", new AttributeString("hematologicOther", false, false, true));

        attributes.put("infectionRequiringTreatment", new AttributeString("infectionRequiringTreatment", false, false, true));
        attributes.put("infectionRequiringHospitalization", new AttributeString("infectionRequiringHospitalization", false, false, true));
        attributes.put("infectionRequiringICUAdmission", new AttributeString("infectionRequiringICUAdmission", false, false, true));
        attributes.put("infectionSepsis", new AttributeString("infectionSepsis", false, false, true));
        attributes.put("infectionOther", new AttributeString("infectionOther", false, false, true));

        attributes.put("other", new AttributeString("other"));

        attributes.put("comment", new AttributeString("comment"));
        
        this.clear();
     }
     
     public DSCForm( BasicForm bf) {
        super(bf);		
        formType = ECPFormTypes.DISEASE_SPECIFIC_CATEGORIZATION;
        title = bf.getTitle();
    }
     
    public Date getDscDate() {
        return (Date) attributes.get("dscDate").getValue();
    }
    
    public void setDscDate(Date dscDate) {
        attributes.get("dscDate").setValue(dscDate);
    }

    public VerificationStatus getDscDateVerificationStatus() {
        return attributes.get("dscDate").getVerificationStatus();
    }
    
    public void setDscDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("dscDate").setVerificationStatus(verificationStatus);
    }

    public String getDscDateDccComment() {
        return (String) attributes.get("dscDate").getDccComment();
    }
    
    public void setDscDateDccComment(String dccComment) {
        attributes.get("dscDate").setDccComment(dccComment);
    }
     
    // For CCC Use Only Getters and Setters

    public String getCentralNervousSysTIA() {
        return (String) attributes.get("centralNervousSysTIA").getValue();
    }
    
    public void setCentralNervousSysTIA(String centralNervousSysTIA) {
        attributes.get("centralNervousSysTIA").setValue(centralNervousSysTIA);
    }
    
    public String getCentralNervousSysCVA() {
        return (String) attributes.get("centralNervousSysCVA").getValue();
    }
    
    public void setCentralNervousSysCVA(String centralNervousSysCVA) {
        attributes.get("centralNervousSysCVA").setValue(centralNervousSysCVA);
    }
    
    public String getCentralNervousSysIntracranialHemorrhage() {
        return (String) attributes.get("centralNervousSysIntracranialHemorrhage").getValue();
    }
    
    public void setCentralNervousSysIntracranialHemorrhage(String centralNervousSysIntracranialHemorrhage) {
        attributes.get("centralNervousSysIntracranialHemorrhage").setValue(centralNervousSysIntracranialHemorrhage);
    }
    
    public String getCentralNervousSysAcuteNeurDeficit() {
        return (String) attributes.get("centralNervousSysAcuteNeurDeficit").getValue();
    }
    
    public void setCentralNervousSysAcuteNeurDeficit(String centralNervousSysAcuteNeurDeficit) {
        attributes.get("centralNervousSysAcuteNeurDeficit").setValue(centralNervousSysAcuteNeurDeficit);
    }
    
    public String getCentralNervousSysOther() {
        return (String) attributes.get("centralNervousSysOther").getValue();
    }
    
    public void setCentralNervousSysOther(String centralNervousSysOther) {
        attributes.get("centralNervousSysOther").setValue(centralNervousSysOther);
    }

    
    public String getMyocardialIschemia() {
        return (String) attributes.get("myocardialIschemia").getValue();
    }
    
    public void setMyocardialIschemia(String myocardialIschemia) {
        attributes.get("myocardialIschemia").setValue(myocardialIschemia);
    }
    
    public String getMyocardialInfarction() {
        return (String) attributes.get("myocardialInfarction").getValue();
    }
    
    public void setMyocardialInfarction(String myocardialInfarction) {
        attributes.get("myocardialInfarction").setValue(myocardialInfarction);
    }
    
    public String getMyocardialArrythmia() {
        return (String) attributes.get("myocardialArrythmia").getValue();
    }
    
    public void setMyocardialArrythmia(String myocardialArrythmia) {
        attributes.get("myocardialArrythmia").setValue(myocardialArrythmia);
    }
    
    public String getMyocardialOther() {
        return (String) attributes.get("myocardialOther").getValue();
    }
    
    public void setMyocardialOther(String myocardialOther) {
        attributes.get("myocardialOther").setValue(myocardialOther);
    }

    
    public String getRenalAcuteFailure() {
        return (String) attributes.get("renalAcuteFailure").getValue();
    }
    
    public void setRenalAcuteFailure(String renalAcuteFailure) {
        attributes.get("renalAcuteFailure").setValue(renalAcuteFailure);
    }
    
    public String getRenalChronicFailure() {
        return (String) attributes.get("renalChronicFailure").getValue();
    }
    
    public void setRenalChronicFailure(String renalChronicFailure) {
        attributes.get("renalChronicFailure").setValue(renalChronicFailure);
    }
    
    public String getRenalOther() {
        return (String) attributes.get("renalOther").getValue();
    }
    
    public void setRenalOther(String renalOther) {
        attributes.get("renalOther").setValue(renalOther);
    }

    
    public String getPulmonaryAcuteRespiratoryFailure() {
        return (String) attributes.get("pulmonaryAcuteRespiratoryFailure").getValue();
    }
    
    public void setPulmonaryAcuteRespiratoryFailure(String pulmonaryAcuteRespiratoryFailure) {
        attributes.get("pulmonaryAcuteRespiratoryFailure").setValue(pulmonaryAcuteRespiratoryFailure);
    }
    
    public String getPulmonaryBOS() {
        return (String) attributes.get("pulmonaryBOS").getValue();
    }
    
    public void setPulmonaryBOS(String pulmonaryBOS) {
        attributes.get("pulmonaryBOS").setValue(pulmonaryBOS);
    }
    
    public String getPulmonaryAcuteHypoxemia() {
        return (String) attributes.get("pulmonaryAcuteHypoxemia").getValue();
    }
    
    public void setPulmonaryAcuteHypoxemia(String pulmonaryAcuteHypoxemia) {
        attributes.get("pulmonaryAcuteHypoxemia").setValue(pulmonaryAcuteHypoxemia);
    }
    
    public String getPulmonaryInfection() {
        return (String) attributes.get("pulmonaryInfection").getValue();
    }
    
    public void setPulmonaryInfection(String pulmonaryInfection) {
        attributes.get("pulmonaryInfection").setValue(pulmonaryInfection);
    }
    
    public String getPulmonaryOther() {
        return (String) attributes.get("pulmonaryOther").getValue();
    }
    
    public void setPulmonaryOther(String pulmonaryOther) {
        attributes.get("pulmonaryOther").setValue(pulmonaryOther);
    }


    public String getHepaticAcuteFailure() {
        return (String) attributes.get("hepaticAcuteFailure").getValue();
    }
    
    public void setHepaticAcuteFailure(String hepaticAcuteFailure) {
        attributes.get("hepaticAcuteFailure").setValue(hepaticAcuteFailure);
    }
    
    public String getHepaticAcuteRiseInEnzymes() {
        return (String) attributes.get("hepaticAcuteRiseInEnzymes").getValue();
    }
    
    public void setHepaticAcuteRiseInEnzymes(String hepaticAcuteRiseInEnzymes) {
        attributes.get("hepaticAcuteRiseInEnzymes").setValue(hepaticAcuteRiseInEnzymes);
    }
    
    public String getHepaticChronicFailure() {
        return (String) attributes.get("hepaticChronicFailure").getValue();
    }
    
    public void setHepaticChronicFailure(String hepaticChronicFailure) {
        attributes.get("hepaticChronicFailure").setValue(hepaticChronicFailure);
    }
    
    public String getHepaticOther() {
        return (String) attributes.get("hepaticOther").getValue();
    }
    
    public void setHepaticOther(String hepaticOther) {
        attributes.get("hepaticOther").setValue(hepaticOther);
    }

    
    public String getDiabetesNewDiagnosis() {
        return (String) attributes.get("diabetesNewDiagnosis").getValue();
    }
    
    public void setDiabetesNewDiagnosis(String diabetesNewDiagnosis) {
        attributes.get("diabetesNewDiagnosis").setValue(diabetesNewDiagnosis);
    }
    
    public String getDiabetesAcuteExacerbation() {
        return (String) attributes.get("diabetesAcuteExacerbation").getValue();
    }
    
    public void setDiabetesAcuteExacerbation(String diabetesAcuteExacerbation) {
        attributes.get("diabetesAcuteExacerbation").setValue(diabetesAcuteExacerbation);
    }
    
    public String getDiabetesOther() {
        return (String) attributes.get("diabetesOther").getValue();
    }
    
    public void setDiabetesOther(String diabetesOther) {
        attributes.get("diabetesOther").setValue(diabetesOther);
    }

    
    public String getHematologicAnemia() {
        return (String) attributes.get("hematologicAnemia").getValue();
    }
    
    public void setHematologicAnemia(String hematologicAnemia) {
        attributes.get("hematologicAnemia").setValue(hematologicAnemia);
    }
    
    public String getHematologicThrombocytopenia() {
        return (String) attributes.get("hematologicThrombocytopenia").getValue();
    }
    
    public void setHematologicThrombocytopenia(String hematologicThrombocytopenia) {
        attributes.get("hematologicThrombocytopenia").setValue(hematologicThrombocytopenia);
    }
    
    public String getHematologicLeukopenia() {
        return (String) attributes.get("hematologicLeukopenia").getValue();
    }
    
    public void setHematologicLeukopenia(String hematologicLeukopenia) {
        attributes.get("hematologicLeukopenia").setValue(hematologicLeukopenia);
    }
    
    public String getHematologicLeukocystosis() {
        return (String) attributes.get("hematologicLeukocystosis").getValue();
    }
    
    public void setHematologicLeukocystosis(String hematologicLeukocystosis) {
        attributes.get("hematologicLeukocystosis").setValue(hematologicLeukocystosis);
    }
    
    public String getHematologicOther() {
        return (String) attributes.get("hematologicOther").getValue();
    }
    
    public void setHematologicOther(String hematologicOther) {
        attributes.get("hematologicOther").setValue(hematologicOther);
    }


    public String getInfectionRequiringTreatment() {
        return (String) attributes.get("infectionRequiringTreatment").getValue();
    }
    
    public void setInfectionRequiringTreatment(String infectionRequiringTreatment) {
        attributes.get("infectionRequiringTreatment").setValue(infectionRequiringTreatment);
    }
    
    public String getInfectionRequiringHospitalization() {
        return (String) attributes.get("infectionRequiringHospitalization").getValue();
    }
    
    public void setInfectionRequiringHospitalization(String infectionRequiringHospitalization) {
        attributes.get("infectionRequiringHospitalization").setValue(infectionRequiringHospitalization);
    }
    
    public String getInfectionRequiringICUAdmission() {
        return (String) attributes.get("infectionRequiringICUAdmission").getValue();
    }
    
    public void setInfectionRequiringICUAdmission(String infectionRequiringICUAdmission) {
        attributes.get("infectionRequiringICUAdmission").setValue(infectionRequiringICUAdmission);
    }
    
    public String getInfectionSepsis() {
        return (String) attributes.get("infectionSepsis").getValue();
    }
    
    public void setInfectionSepsis(String infectionSepsis) {
        attributes.get("infectionSepsis").setValue(infectionSepsis);
    }
    
    public String getInfectionOther() {
        return (String) attributes.get("infectionOther").getValue();
    }
    
    public void setInfectionOther(String infectionOther) {
        attributes.get("infectionOther").setValue(infectionOther);
    }

    public String getOther() {
        return (String) attributes.get("other").getValue();
    }
    
    public void setOther(String other) {
        attributes.get("other").setValue(other);
    }

    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }
}
