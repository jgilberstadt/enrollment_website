package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.Attribute;
import edu.wustl.mir.ctt.model.AttributeBoolean;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeInteger;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;

/**
 *
 * @author Paul K. Commean
 */
public class DemoMedHistForm extends BasicForm {
    
    public DemoMedHistForm() {
        // constructor
        super();
        this.formType = ECPFormTypes.DEMOGRAPHICS;
        title = "Demographics / Medical History Form";

        attributes.put("enrollmentDate", new AttributeDate("enrollmentDate"));        
        attributes.put("age", new AttributeInteger("age"));
        attributes.put("gender", new AttributeString("gender", true, false, true));
        attributes.put("race", new AttributeString("race"));
        attributes.put("origin", new AttributeString("origin"));
        attributes.put("lungTransplantationCause", new AttributeString("lungTransplantationCause", true, false, true));
        attributes.put("lungTransplantationOther", new AttributeString("lungTransplantationOther"));
        attributes.put("lungTransplantationDate", new AttributeDate("lungTransplantationDate", true, false, true));
        attributes.put("operationPerformed", new AttributeString("operationPerformed", true, false, true));
        attributes.put("operationPerformedOther", new AttributeString("operationPerformedOther"));
        attributes.put("weightAtTransplant", new AttributeFloat("weightAtTransplant"));
        attributes.put("height", new AttributeFloat("height"));
        attributes.put("hypertension", new AttributeString("hypertension"));
        attributes.put("diabetes", new AttributeString("diabetes"));
        attributes.put("gerd", new AttributeString("gerd"));
        attributes.put("gerdTreatment", new AttributeString("gerdTreatment"));
        attributes.put("highCholesterol", new AttributeString("highCholesterol"));
        attributes.put("currentSmoker", new AttributeString("currentSmoker"));
        attributes.put("previousSmoker", new AttributeString("previousSmoker"));
        attributes.put("coronaryArteryDisease", new AttributeString("coronaryArteryDisease"));
        attributes.put("coronaryArteryDiseaseType", new AttributeString("coronaryArteryDiseaseType"));
        attributes.put("congestiveHeartFailure", new AttributeString("congestiveHeartFailure"));
        attributes.put("chronicKidneyDisease", new AttributeString("chronicKidneyDisease"));
        attributes.put("stroke", new AttributeString("stroke"));
        attributes.put("neurologicDisorder", new AttributeString("neurologicDisorder"));
        attributes.put("antiThrombotic", new AttributeString("antiThrombotic"));
        attributes.put("antiPlateletAgent", new AttributeString("antiPlateletAgent"));
        attributes.put("otherActiveConditions", new AttributeString("otherActiveConditions"));
        attributes.put("otherActiveConditionsDescribe", new AttributeString("otherActiveConditionsDescribe"));
        attributes.put("tacrolimus", new AttributeString("tacrolimus"));
        attributes.put("prednisone", new AttributeString("prednisone"));
        attributes.put("alemtuzumab", new AttributeString("alemtuzumab"));
        attributes.put("sirolimus", new AttributeString("sirolimus"));
        attributes.put("everolimus", new AttributeString("everolimus"));
        attributes.put("azathioprine", new AttributeString("azathioprine"));
        attributes.put("cyclosporineA", new AttributeString("cyclosporineA"));
        attributes.put("methotrexate", new AttributeString("methotrexate"));
        attributes.put("macrolideAntibiotic", new AttributeString("macrolideAntibiotic"));
        attributes.put("mycophenolateMofetil", new AttributeString("mycophenolateMofetil"));
        attributes.put("antiThymocyteGlobulin", new AttributeString("antiThymocyteGlobulin"));
        attributes.put("totalLymphoidIrradiation", new AttributeString("totalLymphoidIrradiation"));
        attributes.put("otherDrugs", new AttributeString("otherDrugs"));        
        attributes.put("otherDrugNames", new AttributeString("otherDrugNames"));        
        attributes.put("tacrolimusActiveTreat", new AttributeString("tacrolimusActiveTreat"));
        attributes.put("prednisoneActiveTreat", new AttributeString("prednisoneActiveTreat"));
        attributes.put("alemtuzumabActiveTreat", new AttributeString("alemtuzumabActiveTreat"));
        attributes.put("sirolimusActiveTreat", new AttributeString("sirolimusActiveTreat"));
        attributes.put("everolimusActiveTreat", new AttributeString("everolimusActiveTreat"));
        attributes.put("azathioprineActiveTreat", new AttributeString("azathioprineActiveTreat"));
        attributes.put("cyclosporineAActiveTreat", new AttributeString("cyclosporineAActiveTreat"));
        attributes.put("methotrexateActiveTreat", new AttributeString("methotrexateActiveTreat"));
        attributes.put("macrolideAntibioticActiveTreat", new AttributeString("macrolideAntibioticActiveTreat"));
        attributes.put("mycophenolateMofetilActiveTreat", new AttributeString("mycophenolateMofetilActiveTreat"));
        attributes.put("antiThymocyteGlobulinActiveTreat", new AttributeString("antiThymocyteGlobulinActiveTreat"));
        attributes.put("totalLymphoidIrradiationActiveTreat", new AttributeString("totalLymphoidIrradiationActiveTreat"));
        attributes.put("otherDrugsActiveTreat", new AttributeString("otherDrugsActiveTreat"));        
        attributes.put("otherDrugNamesActiveTreat", new AttributeString("otherDrugNamesActiveTreat"));        
        attributes.put("prednisoneTherapySixMonths", new AttributeString("prednisoneTherapySixMonths"));
        attributes.put("prednisoneStartingDose", new AttributeInteger("prednisoneStartingDose"));
        attributes.put("prednisoneEscalation", new AttributeString("prednisoneEscalation"));
        attributes.put("prednisoneIncreasedDailyDose", new AttributeInteger("prednisoneIncreasedDailyDose"));
        attributes.put("prednisoneAverageDose", new AttributeInteger("prednisoneAverageDose"));
        
        
        
        attributes.put("postTransBOSDiagDate", new AttributeDate("postTransBOSDiagDate"));
        attributes.put("postTransBosStageDiag", new AttributeString("postTransBosStageDiag", true, false, true));
        attributes.put("baselineFEV1", new AttributeFloat("baselineFEV1", true, false, true));
        attributes.put("firstComponentFEV1Date", new AttributeDate("firstComponentFEV1Date", true, false, true));
        attributes.put("firstComponentFEV1", new AttributeFloat("firstComponentFEV1", true, false, true));
        attributes.put("firstComponentFVC", new AttributeFloat("firstComponentFVC", true, false, true));
        attributes.put("secondComponentFEV1Date", new AttributeDate("secondComponentFEV1Date", true, false, true));
        attributes.put("secondComponentFEV1", new AttributeFloat("secondComponentFEV1", true, false, true));
        attributes.put("secondComponentFVC", new AttributeFloat("secondComponentFVC", true, false, true));
        attributes.put("donorSpecificAntibody", new AttributeString("donorSpecificAntibody"));

        attributes.put("onAntiCoagPlatelet", new AttributeString("onAntiCoagPlatelet"));
        attributes.put("antiCoagPlateletName1", new AttributeString("antiCoagPlateletName1"));
        attributes.put("antiCoagPlateletName2", new AttributeString("antiCoagPlateletName2"));
        attributes.put("antiCoagPlateletName3", new AttributeString("antiCoagPlateletName3"));

        attributes.put("baselineVitalSignsDate", new AttributeDate("baselineVitalSignsDate"));
        attributes.put("weight", new AttributeFloat("weight"));
        
        attributes.put("systolic", new AttributeInteger("systolic"));
        attributes.put("diastolic", new AttributeInteger("diastolic"));
        attributes.put("heartRate", new AttributeInteger("heartRate"));
        attributes.put("respiratoryRate", new AttributeInteger("respiratoryRate"));
        attributes.put("oxygenSaturation", new AttributeInteger("oxygenSaturation"));
        attributes.put("receivingSupplementalOxygen", new AttributeString("receivingSupplementalOxygen"));
        attributes.put("receivingSupplementalOxygenAmount", new AttributeFloat("receivingSupplementalOxygenAmount"));
        attributes.put("receivingSupplementalOxygenDelivery", new AttributeString("receivingSupplementalOxygenDelivery"));

        attributes.put("comment", new AttributeString("comment"));
        
        attributes.put("transplantCauseCopdIncludingEmphysema", new AttributeBoolean("transplantCauseCopdIncludingEmphysema"));
        attributes.put("transplantCauseInterstitialLungDisease", new AttributeBoolean("transplantCauseInterstitialLungDisease"));
        attributes.put("transplantCauseCysticFibrosis", new AttributeBoolean("transplantCauseCysticFibrosis"));
        attributes.put("transplantCausePulmonaryHypertension", new AttributeBoolean("transplantCausePulmonaryHypertension"));
        attributes.put("transplantCauseA1ADEmphysema", new AttributeBoolean("transplantCauseA1ADEmphysema"));
        attributes.put("transplantCauseReplacingPrevTransplant", new AttributeBoolean("transplantCauseReplacingPrevTransplant"));
        attributes.put("transplantCauseOther", new AttributeBoolean("transplantCauseOther"));
        
        attributes.put("heightNotAvailable", new AttributeBoolean("heightNotAvailable"));
        attributes.put("weightAtTransplantNA", new AttributeBoolean("weightAtTransplantNA"));
        
        attributes.put("comorbidConditions", new AttributeBoolean("comorbidConditions", true, false, true));
        this.setComorbidConditions(true);
        
        attributes.put("maintenancePrevention", new AttributeBoolean("maintenancePrevention", true, false, true));
        this.setMaintenancePrevention(true);
        
        attributes.put("activeTreatment", new AttributeBoolean("activeTreatment", true, false, true));
        this.setActiveTreatment(true);

        this.clear();
    }
    
    public DemoMedHistForm( BasicForm bf) {
        super(bf);	
        this.formType = ECPFormTypes.DEMOGRAPHICS;
        title = bf.getTitle();
        
        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion())) {
            this.sourceDocumentTypes = new String[]{"History and Physical or Consultation Note",
                                                    "Operative Report of Transplant Procedure",
                                                    "Pulmonary Function Test Reports (for each FEV-1 submitted)"};
        } else {
            this.sourceDocumentTypes = new String[]{"History and Physical or Consultation Note",
                                                    "Operative Report of Transplant Procedure",
                                                    "Pulmonary Function Test Reports (for each FEV-1 submitted)",
                                                    "Medication List"};
        }
    }
	
    public Date getEnrollmentDate() {
        return (Date) attributes.get("enrollmentDate").getValue();
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        attributes.get("enrollmentDate").setValue(enrollmentDate);
    }

    public Integer getAge() {
        return (Integer) attributes.get("age").getValue();
    }
    
    public void setAge(Integer age) {
        attributes.get("age").setValue(age);
    }

    public String getGender() {
        return (String) attributes.get("gender").getValue();
    }
    
    public void setGender(String gender) {
        attributes.get("gender").setValue(gender);
    }

    public VerificationStatus getGenderVerificationStatus() {
//        System.out.println("getGenderVerificationStatus was called containing: " + attributes.get("gender").getVerificationStatus());
        return attributes.get("gender").getVerificationStatus();
    }
    
    public void setGenderVerificationStatus(VerificationStatus verificationStatus) {
//        System.out.println("SET GenderVerificationStatus was called containing: " + verificationStatus);
        attributes.get("gender").setVerificationStatus(verificationStatus);
    }

    public String getGenderDccComment() {
        return (String) attributes.get("gender").getDccComment();
    }
    
    public void setGenderDccComment(String dccComment) {
        attributes.get("gender").setDccComment(dccComment);
    }

    public String getRace() {
        return (String) attributes.get("race").getValue();
    }
    
    public void setRace(String race) {
        attributes.get("race").setValue(race);
    }

    public String getOrigin() {
        return (String) attributes.get("origin").getValue();
    }
    
    public void setOrigin(String origin) {
        attributes.get("origin").setValue(origin);
    }

    public String getLungTransplantationCause() {
        return (String) attributes.get("lungTransplantationCause").getValue();
    }
    
    public void setLungTransplantationCause(String lungTransplantationCause) {
        attributes.get("lungTransplantationCause").setValue(lungTransplantationCause);
    }

    public VerificationStatus getLungTransplantationCauseVerificationStatus() {
        return attributes.get("lungTransplantationCause").getVerificationStatus();
    }
    
    public void setLungTransplantationCauseVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("lungTransplantationCause").setVerificationStatus(verificationStatus);
    }

    public String getLungTransplantationCauseDccComment() {
        return (String) attributes.get("lungTransplantationCause").getDccComment();
    }
    
    public void setLungTransplantationCauseDccComment(String dccComment) {
        attributes.get("lungTransplantationCause").setDccComment(dccComment);
    }

    public String getLungTransplantationOther() {
        return (String) attributes.get("lungTransplantationOther").getValue();
    }
    
    public void setLungTransplantationOther(String lungTransplantationOther) {
        attributes.get("lungTransplantationOther").setValue(lungTransplantationOther);
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

    public String getOperationPerformed() {
        return (String) attributes.get("operationPerformed").getValue();
    }
    
    public void setOperationPerformed(String operationPerformed) {
        attributes.get("operationPerformed").setValue(operationPerformed);
    }

    public VerificationStatus getOperationPerformedVerificationStatus() {
        return attributes.get("operationPerformed").getVerificationStatus();
    }
    
    public void setOperationPerformedVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("operationPerformed").setVerificationStatus(verificationStatus);
    }

    public String getOperationPerformedDccComment() {
        return (String) attributes.get("operationPerformed").getDccComment();
    }
    
    public void setOperationPerformedDccComment(String dccComment) {
        attributes.get("operationPerformed").setDccComment(dccComment);
    }

    public String getOperationPerformedOther() {
        return (String) attributes.get("operationPerformedOther").getValue();
    }
    
    public void setOperationPerformedOther(String operationPerformedOther) {
        attributes.get("operationPerformedOther").setValue(operationPerformedOther);
    }

    public Float getWeightAtTransplant() {
        return (Float) attributes.get("weightAtTransplant").getValue();
    }
    
    public void setWeightAtTransplant(Float weightAtTransplant) {
        attributes.get("weightAtTransplant").setValue(weightAtTransplant);
    }

    public Float getHeight() {
        return (Float) attributes.get("height").getValue();
    }
    
    public void setHeight(Float height) {
        attributes.get("height").setValue(height);
    }

    public String getHypertension() {
        return (String) attributes.get("hypertension").getValue();
    }
    
    public void setHypertension(String hypertension) {
        attributes.get("hypertension").setValue(hypertension);
    }

    public String getDiabetes() {
        return (String) attributes.get("diabetes").getValue();
    }
    
    public void setDiabetes(String diabetes) {
        attributes.get("diabetes").setValue(diabetes);
    }

    public String getGerd() {
        return (String) attributes.get("gerd").getValue();
    }
    
    public void setGerd(String gerd) {
        attributes.get("gerd").setValue(gerd);
        
        // If gerd is false, clear the dependent value
        if ("false".equals(gerd)) {
            attributes.get("gerdTreatment").setValue(null); // clear gerdTreatment value
        }
    }

    public String getGerdTreatment() {
        return (String) attributes.get("gerdTreatment").getValue();
    }
    
    public void setGerdTreatment(String gerdTreatment) {
        attributes.get("gerdTreatment").setValue(gerdTreatment);
    }

    public String getHighCholesterol() {
        return (String) attributes.get("highCholesterol").getValue();
    }
    
    public void setHighCholesterol(String highCholesterol) {
        attributes.get("highCholesterol").setValue(highCholesterol);
    }

    public String getCurrentSmoker() {
        return (String) attributes.get("currentSmoker").getValue();
    }
    
    public void setCurrentSmoker(String currentSmoker) {
        attributes.get("currentSmoker").setValue(currentSmoker);
    }

    public String getPreviousSmoker() {
        return (String) attributes.get("previousSmoker").getValue();
    }
    
    public void setPreviousSmoker(String previousSmoker) {
        attributes.get("previousSmoker").setValue(previousSmoker);
    }

    public String getCoronaryArteryDisease() {
        return (String) attributes.get("coronaryArteryDisease").getValue();
    }
    
    public void setCoronaryArteryDisease(String coronaryArteryDisease) {
        attributes.get("coronaryArteryDisease").setValue(coronaryArteryDisease);
        
        if ("false".equals(coronaryArteryDisease)) {
            attributes.get("coronaryArteryDiseaseType").setValue(null); // clear coronaryArteryDiseaseType value
        }
    }

    public String getCoronaryArteryDiseaseType() {
        return (String) attributes.get("coronaryArteryDiseaseType").getValue();
    }
    
    public void setCoronaryArteryDiseaseType(String coronaryArteryDiseaseType) {
        attributes.get("coronaryArteryDiseaseType").setValue(coronaryArteryDiseaseType);
    }

    public String getCongestiveHeartFailure() {
        return (String) attributes.get("congestiveHeartFailure").getValue();
    }
    
    public void setCongestiveHeartFailure(String congestiveHeartFailure) {
        attributes.get("congestiveHeartFailure").setValue(congestiveHeartFailure);
    }

    public String getChronicKidneyDisease() {
        return (String) attributes.get("chronicKidneyDisease").getValue();
    }
    
    public void setChronicKidneyDisease(String chronicKidneyDisease) {
        attributes.get("chronicKidneyDisease").setValue(chronicKidneyDisease);
    }

    public String getStroke() {
        return (String) attributes.get("stroke").getValue();
    }
    
    public void setStroke(String stroke) {
        attributes.get("stroke").setValue(stroke);
    }

    public String getNeurologicDisorder() {
        return (String) attributes.get("neurologicDisorder").getValue();
    }
    
    public void setNeurologicDisorder(String neurologicDisorder) {
        attributes.get("neurologicDisorder").setValue(neurologicDisorder);
    }
    
    public String getAntiThrombotic() {
        return (String) attributes.get("antiThrombotic").getValue();
    }
    
    public void setAntiThrombotic(String antiThrombotic) {
        attributes.get("antiThrombotic").setValue(antiThrombotic);
    }

    public String getAntiPlateletAgent() {
        return (String) attributes.get("antiPlateletAgent").getValue();
    }
    
    public void setAntiPlateletAgent(String antiPlateletAgent) {
        attributes.get("antiPlateletAgent").setValue(antiPlateletAgent);
    }

    public String getOtherActiveConditions() {
        return (String) attributes.get("otherActiveConditions").getValue();
    }
    
    public void setOtherActiveConditions(String otherActiveConditions) {
        attributes.get("otherActiveConditions").setValue(otherActiveConditions);
    }

    public String getOtherActiveConditionsDescribe() {
        return (String) attributes.get("otherActiveConditionsDescribe").getValue();
    }
    
    public void setOtherActiveConditionsDescribe(String otherActiveConditionsDescribe) {
        attributes.get("otherActiveConditionsDescribe").setValue(otherActiveConditionsDescribe);
    }
    
    public String getTacrolimus() {
        return (String) attributes.get("tacrolimus").getValue();
    }
    
    public void setTacrolimus(String tacrolimus) {
        attributes.get("tacrolimus").setValue(tacrolimus);
    }

    public String getPrednisone() {
        return (String) attributes.get("prednisone").getValue();
    }
    
    public void setPrednisone(String prednisone) {
        attributes.get("prednisone").setValue(prednisone);
    }

    public String getAlemtuzumab() {
        return (String) attributes.get("alemtuzumab").getValue();
    }
    
    public void setAlemtuzumab(String alemtuzumab) {
        attributes.get("alemtuzumab").setValue(alemtuzumab);
    }

    public String getSirolimus() {
        return (String) attributes.get("sirolimus").getValue();
    }
    
    public void setSirolimus(String sirolimus) {
        attributes.get("sirolimus").setValue(sirolimus);
    }

    public String getEverolimus() {
        return (String) attributes.get("everolimus").getValue();
    }
    
    public void setEverolimus(String everolimus) {
        attributes.get("everolimus").setValue(everolimus);
    }

    public String getAzathioprine() {
        return (String) attributes.get("azathioprine").getValue();
    }
    
    public void setAzathioprine(String azathioprine) {
        attributes.get("azathioprine").setValue(azathioprine);
    }

    public String getCyclosporineA() {
        return (String) attributes.get("cyclosporineA").getValue();
    }
    
    public void setCyclosporineA(String cyclosporineA) {
        attributes.get("cyclosporineA").setValue(cyclosporineA);
    }

    public String getMethotrexate() {
        return (String) attributes.get("methotrexate").getValue();
    }
    
    public void setMethotrexate(String methotrexate) {
        attributes.get("methotrexate").setValue(methotrexate);
    }

    public String getMacrolideAntibiotic() {
        return (String) attributes.get("macrolideAntibiotic").getValue();
    }
    
    public void setMacrolideAntibiotic(String macrolideAntibiotic) {
        attributes.get("macrolideAntibiotic").setValue(macrolideAntibiotic);
    }

    public String getMycophenolateMofetil() {
        return (String) attributes.get("mycophenolateMofetil").getValue();
    }
    
    public void setMycophenolateMofetil(String mycophenolateMofetil) {
        attributes.get("mycophenolateMofetil").setValue(mycophenolateMofetil);
    }

    public String getAntiThymocyteGlobulin() {
        return (String) attributes.get("antiThymocyteGlobulin").getValue();
    }
    
    public void setAntiThymocyteGlobulin(String antiThymocyteGlobulin) {
        attributes.get("antiThymocyteGlobulin").setValue(antiThymocyteGlobulin);
    }
    
    public String getTotalLymphoidIrradiation() {
        return (String) attributes.get("totalLymphoidIrradiation").getValue();
    }
    
    public void setTotalLymphoidIrradiation(String totalLymphoidIrradiation) {
        attributes.get("totalLymphoidIrradiation").setValue(totalLymphoidIrradiation);
    }
    
    public String getOtherDrugs() {
        return (String) attributes.get("otherDrugs").getValue();
    }
    
    public void setOtherDrugs(String OtherDrugs) {
        attributes.get("otherDrugs").setValue(OtherDrugs);
    }

    public String getOtherDrugNames() {
        return (String) attributes.get("otherDrugNames").getValue();
    }
    
    public void setOtherDrugNames(String OtherDrugNames) {
        attributes.get("otherDrugNames").setValue(OtherDrugNames);
    }

    
    public String getTacrolimusActiveTreat() {
        return (String) attributes.get("tacrolimusActiveTreat").getValue();
    }
    
    public void setTacrolimusActiveTreat(String tacrolimusActiveTreat) {
        attributes.get("tacrolimusActiveTreat").setValue(tacrolimusActiveTreat);
    }

    public String getPrednisoneActiveTreat() {
        return (String) attributes.get("prednisoneActiveTreat").getValue();
    }
    
    public void setPrednisoneActiveTreat(String prednisoneActiveTreat) {
        attributes.get("prednisoneActiveTreat").setValue(prednisoneActiveTreat);
    }

    public String getAlemtuzumabActiveTreat() {
        return (String) attributes.get("alemtuzumabActiveTreat").getValue();
    }
    
    public void setAlemtuzumabActiveTreat(String alemtuzumabActiveTreat) {
        attributes.get("alemtuzumabActiveTreat").setValue(alemtuzumabActiveTreat);
    }

    public String getSirolimusActiveTreat() {
        return (String) attributes.get("sirolimusActiveTreat").getValue();
    }
    
    public void setSirolimusActiveTreat(String sirolimusActiveTreat) {
        attributes.get("sirolimusActiveTreat").setValue(sirolimusActiveTreat);
    }

    public String getEverolimusActiveTreat() {
        return (String) attributes.get("everolimusActiveTreat").getValue();
    }
    
    public void setEverolimusActiveTreat(String everolimusActiveTreat) {
        attributes.get("everolimusActiveTreat").setValue(everolimusActiveTreat);
    }

    public String getAzathioprineActiveTreat() {
        return (String) attributes.get("azathioprineActiveTreat").getValue();
    }
    
    public void setAzathioprineActiveTreat(String azathioprineActiveTreat) {
        attributes.get("azathioprineActiveTreat").setValue(azathioprineActiveTreat);
    }

    public String getCyclosporineAActiveTreat() {
        return (String) attributes.get("cyclosporineAActiveTreat").getValue();
    }
    
    public void setCyclosporineAActiveTreat(String cyclosporineAActiveTreat) {
        attributes.get("cyclosporineAActiveTreat").setValue(cyclosporineAActiveTreat);
    }

    public String getMethotrexateActiveTreat() {
        return (String) attributes.get("methotrexateActiveTreat").getValue();
    }
    
    public void setMethotrexateActiveTreat(String methotrexateActiveTreat) {
        attributes.get("methotrexateActiveTreat").setValue(methotrexateActiveTreat);
    }

    public String getMacrolideAntibioticActiveTreat() {
        return (String) attributes.get("macrolideAntibioticActiveTreat").getValue();
    }
    
    public void setMacrolideAntibioticActiveTreat(String macrolideAntibioticActiveTreat) {
        attributes.get("macrolideAntibioticActiveTreat").setValue(macrolideAntibioticActiveTreat);
    }

    public String getMycophenolateMofetilActiveTreat() {
        return (String) attributes.get("mycophenolateMofetilActiveTreat").getValue();
    }
    
    public void setMycophenolateMofetilActiveTreat(String mycophenolateMofetilActiveTreat) {
        attributes.get("mycophenolateMofetilActiveTreat").setValue(mycophenolateMofetilActiveTreat);
    }

    public String getAntiThymocyteGlobulinActiveTreat() {
        return (String) attributes.get("antiThymocyteGlobulinActiveTreat").getValue();
    }
    
    public void setAntiThymocyteGlobulinActiveTreat(String antiThymocyteGlobulinActiveTreat) {
        attributes.get("antiThymocyteGlobulinActiveTreat").setValue(antiThymocyteGlobulinActiveTreat);
    }

    public String getTotalLymphoidIrradiationActiveTreat() {
        return (String) attributes.get("totalLymphoidIrradiationActiveTreat").getValue();
    }
    
    public void setTotalLymphoidIrradiationActiveTreat(String totalLymphoidIrradiationActiveTreat) {
        attributes.get("totalLymphoidIrradiationActiveTreat").setValue(totalLymphoidIrradiationActiveTreat);
    }

    public String getOtherDrugsActiveTreat() {
        return (String) attributes.get("otherDrugsActiveTreat").getValue();
    }
    
    public void setOtherDrugsActiveTreat(String OtherDrugsActiveTreat) {
        attributes.get("otherDrugsActiveTreat").setValue(OtherDrugsActiveTreat);
    }

    public String getOtherDrugNamesActiveTreat() {
        return (String) attributes.get("otherDrugNamesActiveTreat").getValue();
    }
    
    public void setOtherDrugNamesActiveTreat(String OtherDrugNamesActiveTreat) {
        attributes.get("otherDrugNamesActiveTreat").setValue(OtherDrugNamesActiveTreat);
    }
    
    public String getPrednisoneEscalation() {
        return (String) attributes.get("prednisoneEscalation").getValue();
    }
    
    public void setPrednisoneEscalation(String prednisoneEscalation) {
        attributes.get("prednisoneEscalation").setValue(prednisoneEscalation);
    }    
    
    public String getPrednisoneTherapySixMonths() {
        return (String) attributes.get("prednisoneTherapySixMonths").getValue();
    }
    
    public void setPrednisoneTherapySixMonths(String prednisoneTherapySixMonths) {
        attributes.get("prednisoneTherapySixMonths").setValue(prednisoneTherapySixMonths);
    }

    public Integer getPrednisoneStartingDose() {
        return (Integer) attributes.get("prednisoneStartingDose").getValue();
    }
    
    public void setPrednisoneStartingDose(Integer prednisoneStartingDose) {
        attributes.get("prednisoneStartingDose").setValue(prednisoneStartingDose);
    }

    public Integer getPrednisoneIncreasedDailyDose() {
        return (Integer) attributes.get("prednisoneIncreasedDailyDose").getValue();
    }
    
    public void setPrednisoneIncreasedDailyDose(Integer prednisoneIncreasedDailyDose) {
        attributes.get("prednisoneIncreasedDailyDose").setValue(prednisoneIncreasedDailyDose);
    }

    public Integer getPrednisoneAverageDose() {
        return (Integer) attributes.get("prednisoneAverageDose").getValue();
    }
    
    public void setPrednisoneAverageDose(Integer prednisoneAverageDose) {
        attributes.get("prednisoneAverageDose").setValue(prednisoneAverageDose);
    }

    
    
            
    public Date getPostTransBOSDiagDate() {
        return (Date) attributes.get("postTransBOSDiagDate").getValue();
    }
    
    public void setPostTransBOSDiagDate(Date postTransBOSDiagDate) {
        attributes.get("postTransBOSDiagDate").setValue(postTransBOSDiagDate);
    }

    public String getPostTransBosStageDiag() {
        return (String) attributes.get("postTransBosStageDiag").getValue();
    }
    
    public void setPostTransBosStageDiag(String postTransBosStageDiag) {
        attributes.get("postTransBosStageDiag").setValue(postTransBosStageDiag);
    }
    
    public VerificationStatus getPostTransBosStageDiagVerificationStatus() {
        return attributes.get("postTransBosStageDiag").getVerificationStatus();
    }
    
    public void setPostTransBosStageDiagVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("postTransBosStageDiag").setVerificationStatus(verificationStatus);
    }

    public String getPostTransBosStageDiagDccComment() {
        return (String) attributes.get("postTransBosStageDiag").getDccComment();
    }
    
    public void setPostTransBosStageDiagDccComment(String dccComment) {
        attributes.get("postTransBosStageDiag").setDccComment(dccComment);
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

    public String getDonorSpecificAntibody() {
        return (String) attributes.get("donorSpecificAntibody").getValue();
    }
    
    public void setDonorSpecificAntibody(String donorSpecificAntibody) {
        attributes.get("donorSpecificAntibody").setValue(donorSpecificAntibody);
    }
    

    
    public String getOnAntiCoagPlatelet() {
        return (String) attributes.get("onAntiCoagPlatelet").getValue();
    }
    
    public void setOnAntiCoagPlatelet(String onAntiCoagPlatelet) {
        attributes.get("onAntiCoagPlatelet").setValue(onAntiCoagPlatelet);
    }

   public String getAntiCoagPlateletName1() {
        return (String) attributes.get("antiCoagPlateletName1").getValue();
    }
    
    public void setAntiCoagPlateletName1(String antiCoagPlateletName1) {
        attributes.get("antiCoagPlateletName1").setValue(antiCoagPlateletName1);
    }

    public String getAntiCoagPlateletName2() {
        return (String) attributes.get("antiCoagPlateletName2").getValue();
    }
    
    public void setAntiCoagPlateletName2(String antiCoagPlateletName2) {
        attributes.get("antiCoagPlateletName2").setValue(antiCoagPlateletName2);
    }

    public String getAntiCoagPlateletName3() {
        return (String) attributes.get("antiCoagPlateletName3").getValue();
    }
    
    public void setAntiCoagPlateletName3(String antiCoagPlateletName3) {
        attributes.get("antiCoagPlateletName3").setValue(antiCoagPlateletName3);
    }

    
    
    public Date getBaselineVitalSignsDate() {
        return (Date) attributes.get("baselineVitalSignsDate").getValue();
    }
    
    public void setBaselineVitalSignsDate(Date baselineVitalSignsDate) {
        attributes.get("baselineVitalSignsDate").setValue(baselineVitalSignsDate);
    }

    public Float getWeight() {
        return (Float) attributes.get("weight").getValue();
    }
    
    public void setWeight(Float weight) {
        attributes.get("weight").setValue(weight);
    }


            
    public Integer getBloodPressureSystolic() {
        return (Integer) attributes.get("systolic").getValue();
    }
    
    public void setBloodPressureSystolic(Integer systolic) {
        attributes.get("systolic").setValue(systolic);
    }

    public Integer getBloodPressureDiastolic() {
        return (Integer) attributes.get("diastolic").getValue();
    }
    
    public void setBloodPressureDiastolic(Integer diastolic) {
        attributes.get("diastolic").setValue(diastolic);
    }

    public Integer getHeartRate() {
        return (Integer) attributes.get("heartRate").getValue();
    }
    
    public void setHeartRate(Integer heartRate) {
        attributes.get("heartRate").setValue(heartRate);
    }

    public Integer getRespiratoryRate() {
        return (Integer) attributes.get("respiratoryRate").getValue();
    }
    
    public void setRespiratoryRate(Integer respiratoryRate) {
        attributes.get("respiratoryRate").setValue(respiratoryRate);
    }

    public Integer getOxygenSaturation() {
        return (Integer) attributes.get("oxygenSaturation").getValue();
    }
    
    public void setOxygenSaturation(Integer oxygenSaturation) {
        attributes.get("oxygenSaturation").setValue(oxygenSaturation);
    }



   
    public String getReceivingSupplementalOxygen() {
        return (String) attributes.get("receivingSupplementalOxygen").getValue();
    }
    
    public void setReceivingSupplementalOxygen(String receivingSupplementalOxygen) {
        attributes.get("receivingSupplementalOxygen").setValue(receivingSupplementalOxygen);
    }
    
    public Float getReceivingSupplementalOxygenAmount() {
        return (Float) attributes.get("receivingSupplementalOxygenAmount").getValue();
    }
    
    public void setReceivingSupplementalOxygenAmount(Float receivingSupplementalOxygenAmount) {
        attributes.get("receivingSupplementalOxygenAmount").setValue(receivingSupplementalOxygenAmount);
    }

    public String getReceivingSupplementalOxygenDelivery() {
        return (String) attributes.get("receivingSupplementalOxygenDelivery").getValue();
    }
    
    public void setReceivingSupplementalOxygenDelivery(String receivingSupplementalOxygenDelivery) {
        attributes.get("receivingSupplementalOxygenDelivery").setValue(receivingSupplementalOxygenDelivery);
    };

    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }

    public Boolean getTransplantCauseCopdIncludingEmphysema() {
        return (Boolean) attributes.get("transplantCauseCopdIncludingEmphysema").getValue();
    }

    public void setTransplantCauseCopdIncludingEmphysema(Boolean transplantCauseCopdIncludingEmphysema) {
        attributes.get("transplantCauseCopdIncludingEmphysema").setValue(transplantCauseCopdIncludingEmphysema);
    }

    public Boolean getTransplantCauseInterstitialLungDisease() {
        return (Boolean) attributes.get("transplantCauseInterstitialLungDisease").getValue();
    }

    public void setTransplantCauseInterstitialLungDisease(Boolean transplantCauseInterstitialLungDisease) {
        attributes.get("transplantCauseInterstitialLungDisease").setValue(transplantCauseInterstitialLungDisease);
    }

    public Boolean getTransplantCauseCysticFibrosis() {
        return (Boolean) attributes.get("transplantCauseCysticFibrosis").getValue();
    }

    public void setTransplantCauseCysticFibrosis(Boolean transplantCauseCysticFibrosis) {
        attributes.get("transplantCauseCysticFibrosis").setValue(transplantCauseCysticFibrosis);
    }

    public Boolean getTransplantCausePulmonaryHypertension() {
        return (Boolean) attributes.get("transplantCausePulmonaryHypertension").getValue();
    }

    public void setTransplantCausePulmonaryHypertension(Boolean transplantCausePulmonaryHypertension) {
        attributes.get("transplantCausePulmonaryHypertension").setValue(transplantCausePulmonaryHypertension);
    }

    public Boolean getTransplantCauseA1ADEmphysema() {
        return (Boolean) attributes.get("transplantCauseA1ADEmphysema").getValue();
    }

    public void setTransplantCauseA1ADEmphysema(Boolean transplantCauseA1ADEmphysema) {
        attributes.get("transplantCauseA1ADEmphysema").setValue(transplantCauseA1ADEmphysema);
    }

    public Boolean getTransplantCauseReplacingPrevTransplant() {
        return (Boolean) attributes.get("transplantCauseReplacingPrevTransplant").getValue();
    }

    public void setTransplantCauseReplacingPrevTransplant(Boolean transplantCauseReplacingPrevTransplant) {
        attributes.get("transplantCauseReplacingPrevTransplant").setValue(transplantCauseReplacingPrevTransplant);
    }

    public Boolean getTransplantCauseOther() {
        return (Boolean) attributes.get("transplantCauseOther").getValue();
    }

    public void setTransplantCauseOther(Boolean transplantCauseOther) {
        attributes.get("transplantCauseOther").setValue(transplantCauseOther);
    }
    
    public boolean isHeightNotAvailable() {
        return (boolean) attributes.get("heightNotAvailable").getValue();
    }
    
    public void setHeightNotAvailable( boolean b) {
        attributes.get("heightNotAvailable").setValue(b);
    }
    
    public boolean isWeightAtTransplantNA() {
        return (boolean) attributes.get("weightAtTransplantNA").getValue();
    }
    
    public void setWeightAtTransplantNA( boolean b) {
        attributes.get("weightAtTransplantNA").setValue(b);
    }
    
    public boolean isComorbidConditions() {
        return (boolean) attributes.get("comorbidConditions").getValue();
    }
    
    public void setComorbidConditions( boolean b) {
        attributes.get("comorbidConditions").setValue(b);
    }
    
    public VerificationStatus getComorbidConditionsVerificationStatus() {
        return attributes.get("comorbidConditions").getVerificationStatus();
    }
    
    public void setComorbidConditionsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("comorbidConditions").setVerificationStatus(verificationStatus);
    }

    public String getComorbidConditionsDccComment() {
        return (String) attributes.get("comorbidConditions").getDccComment();
    }
    
    public void setComorbidConditionsDccComment(String dccComment) {
        attributes.get("comorbidConditions").setDccComment(dccComment);
    }

    public boolean isMaintenancePrevention() {
        return (boolean) attributes.get("maintenancePrevention").getValue();
    }
    
    public void setMaintenancePrevention( boolean b) {
        attributes.get("maintenancePrevention").setValue(b);
    }
    
    public VerificationStatus getMaintenancePreventionVerificationStatus() {
        return attributes.get("maintenancePrevention").getVerificationStatus();
    }
    
    public void setMaintenancePreventionVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("maintenancePrevention").setVerificationStatus(verificationStatus);
    }

    public String getMaintenancePreventionDccComment() {
        return (String) attributes.get("maintenancePrevention").getDccComment();
    }
    
    public void setMaintenancePreventionDccComment(String dccComment) {
        attributes.get("maintenancePrevention").setDccComment(dccComment);
    }
    
    public boolean isActiveTreatment() {
        return (boolean) attributes.get("activeTreatment").getValue();
    }
    
    public void setActiveTreatment( boolean b) {
        attributes.get("activeTreatment").setValue(b);
    }
    
    public VerificationStatus getActiveTreatmentVerificationStatus() {
        return attributes.get("activeTreatment").getVerificationStatus();
    }
    
    public void setActiveTreatmentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("activeTreatment").setVerificationStatus(verificationStatus);
    }

    public String getActiveTreatmentDccComment() {
        return (String) attributes.get("activeTreatment").getDccComment();
    }
    
    public void setActiveTreatmentDccComment(String dccComment) {
        attributes.get("activeTreatment").setDccComment(dccComment);
    }
}
