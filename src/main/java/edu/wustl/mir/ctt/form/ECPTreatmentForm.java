package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeBoolean;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeInteger;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author pkc
 */
public class ECPTreatmentForm extends BasicForm {
    private transient Logger logger = Logger.getLogger(ECPTreatmentForm.class);
    
    // Different versions require different source document types so the variable below is no longer used
    // List is set in the constructors
    //public static String[] SourceDocumentTypes; 
    
    public ECPTreatmentForm() {
        // constructor
        super();
        
        this.formType = ECPFormTypes.ECP_TREATMENT;
        title = "ECP Treatment Visit Form";
        
        attributes.put("ecpTreatVisitDate", new AttributeDate("ecpTreatVisitDate", true, false, true));
        attributes.put("weight", new AttributeFloat("weight", true, false, true));
        attributes.put("bloodPressureSystolic", new AttributeInteger("bloodPressureSystolic", true, false, true));
        attributes.put("bloodPressureDiastolic", new AttributeInteger("bloodPressureDiastolic", true, false, true));
        attributes.put("heartRate", new AttributeInteger("heartRate", true, false, true));
        attributes.put("respiratoryRate", new AttributeInteger("respiratoryRate", true, false, true));
        attributes.put("oxygenSaturation", new AttributeInteger("oxygenSaturation", true, false, true));
        attributes.put("receivingSupplementalOxygen", new AttributeString("receivingSupplementalOxygen", false, false, true));
        attributes.put("receivingSupplementalOxygenAmount", new AttributeFloat("receivingSupplementalOxygenAmount", false, false, true));
        attributes.put("receivingSupplementalOxygenDelivery", new AttributeString("receivingSupplementalOxygenDelivery", false, false, true));
        attributes.put("completeBloodCountDate", new AttributeDate("completeBloodCountDate", true, false, true));
        attributes.put("wbcs", new AttributeFloat("wbcs", true, false, true));
        attributes.put("rbcs", new AttributeFloat("rbcs", true, false, true));
        attributes.put("hemoglobin", new AttributeFloat("hemoglobin", true, false, true));
        attributes.put("hematocrit", new AttributeFloat("hematocrit", true, false, true));
        attributes.put("platelets", new AttributeFloat("platelets", true, false, true));
        attributes.put("neutrophils", new AttributeFloat("neutrophils", true, false, true));
        attributes.put("lymphocytes", new AttributeFloat("lymphocytes", true, false, true));
        attributes.put("monocytes", new AttributeFloat("monocytes", true, false, true));
        attributes.put("eosinophils", new AttributeFloat("eosinophils", true, false, true));
        attributes.put("basophils", new AttributeFloat("basophils", true, false, true));
        attributes.put("hemocytometer", new AttributeString("hemocytometer", true, false, true));
        attributes.put("ecpTypeMachine", new AttributeString("ecpTypeMachine", true, false, true));
        attributes.put("typeAnticoagulant", new AttributeString("typeAnticoagulant", true, false, true));
        attributes.put("uvarFiveCycles", new AttributeString("uvarFiveCycles", true, false, true));
        attributes.put("uvarNumberCycles", new AttributeInteger("uvarNumberCycles"));
        attributes.put("cellex1500Plasma", new AttributeString("cellex1500Plasma", true, false, true));
        attributes.put("cellexVolumeProcessed", new AttributeInteger("cellexVolumeProcessed"));
        attributes.put("describeReasonWhy", new AttributeString("describeReasonWhy", true, false, true));
        attributes.put("typeVenousAccess", new AttributeString("typeVenousAccess", true, false, true));
        attributes.put("ecpTreatmentCompleted", new AttributeString("ecpTreatmentCompleted"));
        attributes.put("ecpNotCompletedWhy", new AttributeString("ecpNotCompletedWhy"));
        attributes.put("complications", new AttributeString("complications"));
        attributes.put("complicationDescription", new AttributeString("complicationDescription"));
        
        attributes.put("comment", new AttributeString("comment"));
        
        this.clear();
    }
    
    public ECPTreatmentForm( BasicForm bf) {
        super(bf);
        title = bf.getTitle();
        
//        logger.debug("In wrong constructor!?");
        
        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion())) {
            this.sourceDocumentTypes = new String[]{"Photopheresis Procedure Note/Report",
                                                    "CBC - Lab Report",
                                                    "Progress Note or Clinical Note describing complication (if applicable)"};
        } else {
            this.sourceDocumentTypes = new String[]{"Photopheresis Procedure Note/Report",
                                                    "CBC - Lab Report",
                                                    "ECP Pre-Procedure Assessment Form",
                                                    "Progress Note or Clinical Note describing complication (if applicable)",
                                                    "Source describing why whole blood processing not completed as required (if applicable)"};
        }
    }
    
    @Override
    public void versionControl() {
        if ("1.0".equals(this.crfVersion) || "5.0".equals(this.crfVersion)) {
//            logger.debug("In versionControl CRF version is 1 or 5");
            
            attributes.put("cbcNotAvailable", new AttributeBoolean("cbcNotAvailable", true, false, true));
            attributes.put("receivingSteroidTherapy", new AttributeString("receivingSteroidTherapy"));
            attributes.put("steroidDailyDose", new AttributeInteger("steroidDailyDose"));

            logger.debug("In versionControl Attributes contains cbcNotAvailable? " + attributes.containsKey("cbcNotAvailable"));
        } else if("7.0".equals(this.getCrfVersion())) {
            
//            logger.debug("In versionControl CRF version is not 1 or 5");
            
            attributes.put("cbcCollected", new AttributeString("cbcCollected", true, false, true));
            attributes.put("receivingSteroidTherapy", new AttributeString("receivingSteroidTherapy"));
            attributes.put("steroidDailyDose", new AttributeInteger("steroidDailyDose"));
            
//            logger.debug("In versionControl Attributes contains cbcCollected? " + attributes.containsKey("cbcCollected"));
        } else {
            attributes.put("cbcCollected", new AttributeString("cbcCollected", true, false, true));
            attributes.put("portLumen", new AttributeString("portLumen", false, true, true));
            attributes.put("portName", new AttributeString("portName", false, true, true));
            attributes.put("portNameComment", new AttributeString("portNameComment", false, true, true));
        }
        

    }

    public boolean getRenderPrednisone() {
        boolean renderPrednisone = true;
        if("1.0".equals(this.crfVersion) || "5.0".equals(this.crfVersion) || "7.0".equals(this.crfVersion)) {
            return renderPrednisone;
        } else {
            return renderPrednisone = false;            
        }
    }
    
    public Date getEcpTreatVisitDate() {
        return (Date) attributes.get("ecpTreatVisitDate").getValue();
    }
    
    public void setEcpTreatVisitDate(Date ecpTreatVisitDate) {
        attributes.get("ecpTreatVisitDate").setValue(ecpTreatVisitDate);
    }

    public VerificationStatus getEcpTreatVisitDateVerificationStatus() {
        return attributes.get("ecpTreatVisitDate").getVerificationStatus();
    }
    
    public void setEcpTreatVisitDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("ecpTreatVisitDate").setVerificationStatus(verificationStatus);
    }

    public String getEcpTreatVisitDateDccComment() {
        return (String) attributes.get("ecpTreatVisitDate").getDccComment();
    }
    
    public void setEcpTreatVisitDateDccComment(String dccComment) {
        attributes.get("ecpTreatVisitDate").setDccComment(dccComment);
    }

    public Float getWeight() {
        return (Float) attributes.get("weight").getValue();
    }
    
    public void setWeight(Float weight) {
        attributes.get("weight").setValue(weight);
    }

    public VerificationStatus getWeightVerificationStatus() {
        return attributes.get("weight").getVerificationStatus();
    }
    
    public void setWeightVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("weight").setVerificationStatus(verificationStatus);
    }

    public String getWeightDccComment() {
        return (String) attributes.get("weight").getDccComment();
    }
    
    public void setWeightDccComment(String dccComment) {
        attributes.get("weight").setDccComment(dccComment);
    }

    public Integer getBloodPressureSystolic() {
        return (Integer) attributes.get("bloodPressureSystolic").getValue();
    }
    
    public void setBloodPressureSystolic(Integer systolic) {
        attributes.get("bloodPressureSystolic").setValue(systolic);
    }

    public VerificationStatus getBloodPressureSystolicVerificationStatus() {
        return attributes.get("bloodPressureSystolic").getVerificationStatus();
    }
    
    public void setBloodPressureSystolicVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("bloodPressureSystolic").setVerificationStatus(verificationStatus);
    }

    public String getBloodPressureSystolicDccComment() {
        return (String) attributes.get("bloodPressureSystolic").getDccComment();
    }
    
    public void setBloodPressureSystolicDccComment(String dccComment) {
        attributes.get("bloodPressureSystolic").setDccComment(dccComment);
    }

    public Integer getBloodPressureDiastolic() {
        return (Integer) attributes.get("bloodPressureDiastolic").getValue();
    }
    
    public void setBloodPressureDiastolic(Integer diastolic) {
        attributes.get("bloodPressureDiastolic").setValue(diastolic);
    }

    public VerificationStatus getBloodPressureDiastolicVerificationStatus() {
        return attributes.get("bloodPressureDiastolic").getVerificationStatus();
    }
    
    public void setBloodPressureDiastolicVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("bloodPressureDiastolic").setVerificationStatus(verificationStatus);
    }

    public String getBloodPressureDiastolicDccComment() {
        return (String) attributes.get("bloodPressureDiastolic").getDccComment();
    }
    
    public void setBloodPressureDiastolicDccComment(String dccComment) {
        attributes.get("bloodPressureDiastolic").setDccComment(dccComment);
    }

    public Integer getHeartRate() {
        return (Integer) attributes.get("heartRate").getValue();
    }
    
    public void setHeartRate(Integer heartRate) {
        attributes.get("heartRate").setValue(heartRate);
    }

    public VerificationStatus getHeartRateVerificationStatus() {
        return attributes.get("heartRate").getVerificationStatus();
    }
    
    public void setHeartRateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("heartRate").setVerificationStatus(verificationStatus);
    }

    public String getHeartRateDccComment() {
        return (String) attributes.get("heartRate").getDccComment();
    }
    
    public void setHeartRateDccComment(String dccComment) {
        attributes.get("heartRate").setDccComment(dccComment);
    }

    public Integer getRespiratoryRate() {
        return (Integer) attributes.get("respiratoryRate").getValue();
    }
    
    public void setRespiratoryRate(Integer respiratoryRate) {
        attributes.get("respiratoryRate").setValue(respiratoryRate);
    }

    public VerificationStatus getRespiratoryRateVerificationStatus() {
        return attributes.get("respiratoryRate").getVerificationStatus();
    }
    
    public void setRespiratoryRateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("respiratoryRate").setVerificationStatus(verificationStatus);
    }

    public String getRespiratoryRateDccComment() {
        return (String) attributes.get("respiratoryRate").getDccComment();
    }
    
    public void setRespiratoryRateDccComment(String dccComment) {
        attributes.get("respiratoryRate").setDccComment(dccComment);
    }

    public Integer getOxygenSaturation() {
        return (Integer) attributes.get("oxygenSaturation").getValue();
    }
    
    public void setOxygenSaturation(Integer oxygenSaturation) {
        attributes.get("oxygenSaturation").setValue(oxygenSaturation);
    }

    public VerificationStatus getOxygenSaturationVerificationStatus() {
        return attributes.get("oxygenSaturation").getVerificationStatus();
    }
    
    public void setOxygenSaturationVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("oxygenSaturation").setVerificationStatus(verificationStatus);
    }

    public String getOxygenSaturationDccComment() {
        return (String) attributes.get("oxygenSaturation").getDccComment();
    }
    
    public void setOxygenSaturationDccComment(String dccComment) {
        attributes.get("oxygenSaturation").setDccComment(dccComment);
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


    public Float getNeutrophils() {
        return (Float) attributes.get("neutrophils").getValue();
    }
    
    public void setNeutrophils(Float neutrophils) {
        attributes.get("neutrophils").setValue(neutrophils);
    }

    public VerificationStatus getNeutrophilsVerificationStatus() {
        return attributes.get("neutrophils").getVerificationStatus();
    }
    
    public void setNeutrophilsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("neutrophils").setVerificationStatus(verificationStatus);
    }

    public String getNeutrophilsDccComment() {
        return (String) attributes.get("neutrophils").getDccComment();
    }
    
    public void setNeutrophilsDccComment(String dccComment) {
        attributes.get("neutrophils").setDccComment(dccComment);
    }


    public Float getLymphocytes() {
        return (Float) attributes.get("lymphocytes").getValue();
    }
    
    public void setLymphocytes(Float lymphocytes) {
        attributes.get("lymphocytes").setValue(lymphocytes);
    }

    public VerificationStatus getLymphocytesVerificationStatus() {
        return attributes.get("lymphocytes").getVerificationStatus();
    }
    
    public void setLymphocytesVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("lymphocytes").setVerificationStatus(verificationStatus);
    }

    public String getLymphocytesDccComment() {
        return (String) attributes.get("lymphocytes").getDccComment();
    }
    
    public void setLymphocytesDccComment(String dccComment) {
        attributes.get("lymphocytes").setDccComment(dccComment);
    }


    public Float getMonocytes() {
        return (Float) attributes.get("monocytes").getValue();
    }
    
    public void setMonocytes(Float monocytes) {
        attributes.get("monocytes").setValue(monocytes);
    }

    public VerificationStatus getMonocytesVerificationStatus() {
        return attributes.get("monocytes").getVerificationStatus();
    }
    
    public void setMonocytesVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("monocytes").setVerificationStatus(verificationStatus);
    }

    public String getMonocytesDccComment() {
        return (String) attributes.get("monocytes").getDccComment();
    }
    
    public void setMonocytesDccComment(String dccComment) {
        attributes.get("monocytes").setDccComment(dccComment);
    }


    public Float getEosinophils() {
        return (Float) attributes.get("eosinophils").getValue();
    }
    
    public void setEosinophils(Float eosinophils) {
        attributes.get("eosinophils").setValue(eosinophils);
    }

    public VerificationStatus getEosinophilsVerificationStatus() {
        return attributes.get("eosinophils").getVerificationStatus();
    }
    
    public void setEosinophilsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("eosinophils").setVerificationStatus(verificationStatus);
    }

    public String getEosinophilsDccComment() {
        return (String) attributes.get("eosinophils").getDccComment();
    }
    
    public void setEosinophilsDccComment(String dccComment) {
        attributes.get("eosinophils").setDccComment(dccComment);
    }


    public Float getBasophils() {
        return (Float) attributes.get("basophils").getValue();
    }
    
    public void setBasophils(Float basophils) {
        attributes.get("basophils").setValue(basophils);
    }
    
    public VerificationStatus getBasophilsVerificationStatus() {
        return attributes.get("basophils").getVerificationStatus();
    }
    
    public void setBasophilsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("basophils").setVerificationStatus(verificationStatus);
    }

    public String getBasophilsDccComment() {
        return (String) attributes.get("basophils").getDccComment();
    }
    
    public void setBasophilsDccComment(String dccComment) {
        attributes.get("basophils").setDccComment(dccComment);
    }


    public String getHemocytometer() {
        return (String) attributes.get("hemocytometer").getValue();
    }
    
    public void setHemocytometer(String hemocytometer) {
        attributes.get("hemocytometer").setValue(hemocytometer);
    }

    public VerificationStatus getHemocytometerVerificationStatus() {
        return attributes.get("hemocytometer").getVerificationStatus();
    }
    
    public void setHemocytometerVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("hemocytometer").setVerificationStatus(verificationStatus);
    }

    public String getHemocytometerDccComment() {
        return (String) attributes.get("hemocytometer").getDccComment();
    }
    
    public void setHemocytometerDccComment(String dccComment) {
        attributes.get("hemocytometer").setDccComment(dccComment);
    }

    public String getReceivingSteroidTherapy() {
        return (String) attributes.get("receivingSteroidTherapy").getValue();
    }
    
    public void setReceivingSteroidTherapy(String receivingSteroidTherapy) {
        attributes.get("receivingSteroidTherapy").setValue(receivingSteroidTherapy);
    }
    
    public Integer getSteroidDailyDose() {
        return (Integer) attributes.get("steroidDailyDose").getValue();
    }
    
    public void setSteroidDailyDose(Integer steroidDailyDose) {
        attributes.get("steroidDailyDose").setValue(steroidDailyDose);
    }    
    
    public String getEcpTypeMachine() {
        return (String) attributes.get("ecpTypeMachine").getValue();
    }
    
    public void setEcpTypeMachine(String ecpTypeMachine) {
        attributes.get("ecpTypeMachine").setValue(ecpTypeMachine);
        
        if ("uvar".equals(ecpTypeMachine)) {
            attributes.get("cellex1500Plasma").setValue("notapplicable");
            attributes.get("uvarFiveCycles").setValue(null);
        } else if ("cellex".equals(ecpTypeMachine)) {
            attributes.get("uvarFiveCycles").setValue("notapplicable");
            attributes.get("cellex1500Plasma").setValue(null);
        }
    }
    
    public VerificationStatus getEcpTypeMachineVerificationStatus() {
        return attributes.get("ecpTypeMachine").getVerificationStatus();
    }
    
    public void setEcpTypeMachineVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("ecpTypeMachine").setVerificationStatus(verificationStatus);
    }

    public String getEcpTypeMachineDccComment() {
        return (String) attributes.get("ecpTypeMachine").getDccComment();
    }
    
    public void setEcpTypeMachineDccComment(String dccComment) {
        attributes.get("ecpTypeMachine").setDccComment(dccComment);
    }

    public String getTypeAnticoagulant() {
        return (String) attributes.get("typeAnticoagulant").getValue();
    }
    
    public void setTypeAnticoagulant(String typeAnticoagulant) {
        attributes.get("typeAnticoagulant").setValue(typeAnticoagulant);
    }
    
    public VerificationStatus getTypeAnticoagulantVerificationStatus() {
        return attributes.get("typeAnticoagulant").getVerificationStatus();
    }
    
    public void setTypeAnticoagulantVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("typeAnticoagulant").setVerificationStatus(verificationStatus);
    }

    public String getTypeAnticoagulantDccComment() {
        return (String) attributes.get("typeAnticoagulant").getDccComment();
    }
    
    public void setTypeAnticoagulantDccComment(String dccComment) {
        attributes.get("typeAnticoagulant").setDccComment(dccComment);
    }

    public String getUvarFiveCycles() {
        return (String) attributes.get("uvarFiveCycles").getValue();
    }
    
    public void setUvarFiveCycles(String uvarFiveCycles) {
        attributes.get("uvarFiveCycles").setValue(uvarFiveCycles);
    }
    
    public VerificationStatus getUvarFiveCyclesVerificationStatus() {
        return attributes.get("uvarFiveCycles").getVerificationStatus();
    }
    
    public void setUvarFiveCyclesVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("uvarFiveCycles").setVerificationStatus(verificationStatus);
    }

    public String getUvarFiveCyclesDccComment() {
        return (String) attributes.get("uvarFiveCycles").getDccComment();
    }
    
    public void setUvarFiveCyclesDccComment(String dccComment) {
        attributes.get("uvarFiveCycles").setDccComment(dccComment);
    }

    public Integer getUvarNumberCycles() {
        return (Integer) attributes.get("uvarNumberCycles").getValue();
    }
    
    public void setUvarNumberCycles(Integer uvarNumberCycles) {
        attributes.get("uvarNumberCycles").setValue(uvarNumberCycles);
    }
    
    public String getCellex1500Plasma() {
        return (String) attributes.get("cellex1500Plasma").getValue();
    }
    
    public void setCellex1500Plasma(String cellex1500Plasma) {
        attributes.get("cellex1500Plasma").setValue(cellex1500Plasma);
    }
    
    public VerificationStatus getCellex1500PlasmaVerificationStatus() {
        return attributes.get("cellex1500Plasma").getVerificationStatus();
    }
    
    public void setCellex1500PlasmaVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("cellex1500Plasma").setVerificationStatus(verificationStatus);
    }

    public String getCellex1500PlasmaDccComment() {
        return (String) attributes.get("cellex1500Plasma").getDccComment();
    }
    
    public void setCellex1500PlasmaDccComment(String dccComment) {
        attributes.get("cellex1500Plasma").setDccComment(dccComment);
    }

    public Integer getCellexVolumeProcessed() {
        return (Integer) attributes.get("cellexVolumeProcessed").getValue();
    }
    
    public void setCellexVolumeProcessed(Integer cellexVolumeProcessed) {
        attributes.get("cellexVolumeProcessed").setValue(cellexVolumeProcessed);
    }
    
    public String getDescribeReasonWhy() {
        return (String) attributes.get("describeReasonWhy").getValue();
    }
    
    public void setDescribeReasonWhy(String describeReasonWhy) {
        attributes.get("describeReasonWhy").setValue(describeReasonWhy);
    }
    
    public VerificationStatus getDescribeReasonWhyVerificationStatus() {
        return attributes.get("describeReasonWhy").getVerificationStatus();
    }
    
    public void setDescribeReasonWhyVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("describeReasonWhy").setVerificationStatus(verificationStatus);
    }

    public String getDescribeReasonWhyDccComment() {
        return (String) attributes.get("describeReasonWhy").getDccComment();
    }
    
    public void setDescribeReasonWhyDccComment(String dccComment) {
        attributes.get("describeReasonWhy").setDccComment(dccComment);
    }

    public String getTypeVenousAccess() {
        return (String) attributes.get("typeVenousAccess").getValue();
    }
    
    public void setTypeVenousAccess(String typeVenousAccess) {
        attributes.get("typeVenousAccess").setValue(typeVenousAccess);
    }
    
    public VerificationStatus getTypeVenousAccessVerificationStatus() {
        return attributes.get("typeVenousAccess").getVerificationStatus();
    }
    
    public void setTypeVenousAccessVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("typeVenousAccess").setVerificationStatus(verificationStatus);
    }

    public String getTypeVenousAccessDccComment() {
        return (String) attributes.get("typeVenousAccess").getDccComment();
    }
    
    public void setTypeVenousAccessDccComment(String dccComment) {
        attributes.get("typeVenousAccess").setDccComment(dccComment);
    }

    public String getEcpTreatmentCompleted() {
        return (String) attributes.get("ecpTreatmentCompleted").getValue();
    }

    
    public String getPortLumen() {
        return (String) attributes.get("portLumen").getValue();
    }
    
    public void setPortLumen(String portLumen) {
        attributes.get("portLumen").setValue(portLumen);
    }
    
    public String getPortName() {
        return (String) attributes.get("portName").getValue();
    }
    
    public void setPortName(String portName) {
        attributes.get("portName").setValue(portName);
    }
    
    public String getPortNameComment() {
        return (String) attributes.get("portNameComment").getValue();
    }
    
    public void setPortNameComment(String portNameComment) {
        attributes.get("portNameComment").setValue(portNameComment);
    }
    
    
    public void setEcpTreatmentCompleted(String ecpTreatmentCompleted) {
        attributes.get("ecpTreatmentCompleted").setValue(ecpTreatmentCompleted);
    }
    
    public String getEcpNotCompletedWhy() {
        return (String) attributes.get("ecpNotCompletedWhy").getValue();
    }
    
    public void setEcpNotCompletedWhy(String ecpNotCompletedWhy) {
        attributes.get("ecpNotCompletedWhy").setValue(ecpNotCompletedWhy);
    }

    public String getComplications() {
        return (String) attributes.get("complications").getValue();
    }
    
    public void setComplications(String complications) {
        attributes.get("complications").setValue(complications);
    }
    
    public String getComplicationDescription() {
        return (String) attributes.get("complicationDescription").getValue();
    }
    
    public void setComplicationDescription(String complicationDescription) {
        attributes.get("complicationDescription").setValue(complicationDescription);
    }
    
    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }

    public boolean isCbcNotAvailable() {
        return (boolean) attributes.get("cbcNotAvailable").getValue();
    }
    
    public void setCbcNotAvailable( boolean b) {
        attributes.get("cbcNotAvailable").setValue(b);
    }
    
    public VerificationStatus getCBCNAVerificationStatus() {
        return attributes.get("cbcNotAvailable").getVerificationStatus();
    }
    
    public void setCBCNAVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("cbcNotAvailable").setVerificationStatus(verificationStatus);
    }

    public String getCBCNADccComment() {
        return (String) attributes.get("cbcNotAvailable").getDccComment();
    }
    
    public void setCBCNADccComment(String dccComment) {
        attributes.get("cbcNotAvailable").setDccComment(dccComment);
    }
    
    public String getCbcCollected() {
        return (String) attributes.get("cbcCollected").getValue();
    }
    
    public void setCbcCollected(String cbcCollected) {
        attributes.get("cbcCollected").setValue(cbcCollected);
    }
    
    public VerificationStatus getCbcCollectedVerificationStatus() {
        return attributes.get("cbcCollected").getVerificationStatus();
    }
    
    public void setCbcCollectedVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("cbcCollected").setVerificationStatus(verificationStatus);
    }

    public String getCbcCollectedDccComment() {
        return (String) attributes.get("cbcCollected").getDccComment();
    }
    
    public void setCbcCollectedDccComment(String dccComment) {
        attributes.get("cbcCollected").setDccComment(dccComment);
    }

    // CBC available listener used on CRF version 1.0 and 5.0
    public void cbcAvailableListener(AjaxBehaviorEvent event) {
        if( isCbcNotAvailable()) {

            attributes.get("completeBloodCountDate").setOptional(true);
            attributes.get("wbcs").setOptional(true);
            attributes.get("rbcs").setOptional(true);
            attributes.get("hemoglobin").setOptional(true);
            attributes.get("hematocrit").setOptional(true);
            attributes.get("platelets").setOptional(true);
            attributes.get("neutrophils").setOptional(true);
            attributes.get("lymphocytes").setOptional(true);
            attributes.get("monocytes").setOptional(true);
            attributes.get("eosinophils").setOptional(true);
            attributes.get("basophils").setOptional(true);
            
            attributes.get("hemocytometer").setOptional(true);
        }
        else {

            attributes.get("completeBloodCountDate").setOptional(false);
            attributes.get("wbcs").setOptional(false);
            attributes.get("rbcs").setOptional(false);
            attributes.get("hemoglobin").setOptional(false);
            attributes.get("hematocrit").setOptional(false);
            attributes.get("platelets").setOptional(false);
            attributes.get("neutrophils").setOptional(false);
            attributes.get("lymphocytes").setOptional(false);
            attributes.get("monocytes").setOptional(false);
            attributes.get("eosinophils").setOptional(false);
            attributes.get("basophils").setOptional(false);
            
            attributes.get("hemocytometer").setOptional(false);
        }
    }
    
    // Used on version 7.0
    public void cbcCollectedListener(AjaxBehaviorEvent event) {
        String cbcCollected = getCbcCollected();

        if (null != cbcCollected) {
            switch (cbcCollected) {
                case "cbcCollectedYesterday":
                case "cbcMissed":
                    attributes.get("completeBloodCountDate").setOptional(true);
                    attributes.get("wbcs").setOptional(true);
                    attributes.get("rbcs").setOptional(true);
                    attributes.get("hemoglobin").setOptional(true);
                    attributes.get("hematocrit").setOptional(true);
                    attributes.get("platelets").setOptional(true);
                    attributes.get("neutrophils").setOptional(true);
                    attributes.get("lymphocytes").setOptional(true);
                    attributes.get("monocytes").setOptional(true);
                    attributes.get("eosinophils").setOptional(true);
                    attributes.get("basophils").setOptional(true);
                    attributes.get("hemocytometer").setOptional(true);
                    break;
                case "diffMissed":
                    // cbc info other than diff is still required if diff missed
                    attributes.get("completeBloodCountDate").setOptional(false);
                    attributes.get("wbcs").setOptional(false);
                    attributes.get("rbcs").setOptional(false);
                    attributes.get("hemoglobin").setOptional(false);
                    attributes.get("hematocrit").setOptional(false);
                    attributes.get("platelets").setOptional(false);
                    attributes.get("hemocytometer").setOptional(false);
                    
                    // diff is not required
                    attributes.get("neutrophils").setOptional(true);
                    attributes.get("lymphocytes").setOptional(true);
                    attributes.get("monocytes").setOptional(true);
                    attributes.get("eosinophils").setOptional(true);
                    attributes.get("basophils").setOptional(true);
                    break;
                default:
                    attributes.get("completeBloodCountDate").setOptional(false);
                    attributes.get("wbcs").setOptional(false);
                    attributes.get("rbcs").setOptional(false);
                    attributes.get("hemoglobin").setOptional(false);
                    attributes.get("hematocrit").setOptional(false);
                    attributes.get("platelets").setOptional(false);
                    attributes.get("neutrophils").setOptional(false);
                    attributes.get("lymphocytes").setOptional(false);
                    attributes.get("monocytes").setOptional(false);
                    attributes.get("eosinophils").setOptional(false);
                    attributes.get("basophils").setOptional(false);
                    attributes.get("hemocytometer").setOptional(false);
                    break;
            }
        }
    }
}
