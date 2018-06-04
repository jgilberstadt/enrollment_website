package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeInteger;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;

/**
 *
 * @author pkc
 */
public class ChangeTherapyForm extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"Clinical Note or Medication Record Form"};
    public static final String[] SourceDocumentTypesV7_1 = new String[]{"Clinic or Progress Note and/or Medication Record"};

    public ChangeTherapyForm() {
        // constructor
        super();
        formType = ECPFormTypes.CHANGE_THERAPY;
        title = "Change in Therapy Form";

        this.clear();
    }
    
    public ChangeTherapyForm( BasicForm bf) {
        super(bf);		
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;

        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion())) {
            this.requiresSourceDoc = false;
            this.requiresValidation = false;
            this.requiresVerification = false;
        } else if ("7.0".equals(this.getCrfVersion())){
            this.sourceDocumentTypes = SourceDocumentTypes;
            this.requiresSourceDoc = true;
            this.requiresValidation = true;
            this.requiresVerification = true;
        } else if ("7.1".equals(this.getCrfVersion())){
            this.sourceDocumentTypes = SourceDocumentTypesV7_1;
            this.requiresSourceDoc = true;
            this.requiresValidation = true;
            this.requiresVerification = true;
        }
    }
    
    @Override
    public void versionControl() {
        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion())) {
            this.requiresSourceDoc = false;
            this.requiresValidation = false;
            this.requiresVerification = false;
            
            attributes.put("changeTherapyDate", new AttributeDate("changeTherapyDate")); 
            attributes.put("changeTherapy", new AttributeString("changeTherapy"));
            attributes.put("tacrolimusCurrent", new AttributeString("tacrolimusCurrent"));
            attributes.put("tacrolimusCurrentDosage", new AttributeString("tacrolimusCurrentDosage"));
            attributes.put("alemtuzumabCurrent", new AttributeString("alemtuzumabCurrent"));
            attributes.put("alemtuzumabCurrentDosage", new AttributeString("alemtuzumabCurrentDosage"));
            attributes.put("sirolimusCurrent", new AttributeString("sirolimusCurrent"));
            attributes.put("sirolimusCurrentDosage", new AttributeString("sirolimusCurrentDosage"));
            attributes.put("everolimusCurrent", new AttributeString("everolimusCurrent"));
            attributes.put("everolimusCurrentDosage", new AttributeString("everolimusCurrentDosage"));
            attributes.put("azathioprineCurrent", new AttributeString("azathioprineCurrent"));
            attributes.put("azathioprineCurrentDosage", new AttributeString("azathioprineCurrentDosage"));
            attributes.put("cyclosporineACurrent", new AttributeString("cyclosporineACurrent"));
            attributes.put("cyclosporineACurrentDosage", new AttributeString("cyclosporineACurrentDosage"));
            attributes.put("methotrexateCurrent", new AttributeString("methotrexateCurrent"));
            attributes.put("methotrexateCurrentDosage", new AttributeString("methotrexateCurrentDosage"));
            attributes.put("macrolideAntibioticCurrent", new AttributeString("macrolideAntibioticCurrent"));
            attributes.put("macrolideAntibioticCurrentDosage", new AttributeString("macrolideAntibioticCurrentDosage"));
            attributes.put("mycophenolateMofetilCurrent", new AttributeString("mycophenolateMofetilCurrent"));
            attributes.put("mycophenolateMofetilCurrentDosage", new AttributeString("mycophenolateMofetilCurrentDosage"));
            attributes.put("antiThymocyteGlobulinCurrent", new AttributeString("antiThymocyteGlobulinCurrent"));
            attributes.put("antiThymocyteGlobulinCurrentDosage", new AttributeString("antiThymocyteGlobulinCurrentDosage"));
            attributes.put("totalLymphoidIrradiationCurrent", new AttributeString("totalLymphoidIrradiationCurrent"));
            attributes.put("otherDrugsCurrent", new AttributeString("otherDrugsCurrent"));        
            attributes.put("otherDrugNamesCurrent", new AttributeString("otherDrugNamesCurrent"));        

            attributes.put("prednisoneCurrent", new AttributeString("prednisoneCurrent"));
            attributes.put("prednisoneCurrentDosage", new AttributeInteger("prednisoneCurrentDosage"));
            attributes.put("prednisoneDosageChanges", new AttributeInteger("prednisoneDosageChanges"));
            attributes.put("prednisoneLowestDose", new AttributeInteger("prednisoneLowestDose"));
            attributes.put("prednisoneHighestDose", new AttributeInteger("prednisoneHighestDose"));

            attributes.put("onAntiCoagulant", new AttributeString("onAntiCoagulant"));
            attributes.put("antiCoagulantName1", new AttributeString("antiCoagulantName1"));
            attributes.put("antiCoagulantName2", new AttributeString("antiCoagulantName2"));
            attributes.put("antiCoagulantName3", new AttributeString("antiCoagulantName3"));
            attributes.put("onAntiPlatelet", new AttributeString("onAntiPlatelet"));
            attributes.put("antiPlateletName1", new AttributeString("antiPlateletName1"));
            attributes.put("antiPlateletName2", new AttributeString("antiPlateletName2"));
            attributes.put("antiPlateletName3", new AttributeString("antiPlateletName3"));

            attributes.put("ecpTherapyDiscontinued", new AttributeString("ecpTherapyDiscontinued"));
            attributes.put("ecpTherapyDiscontinuedDate", new AttributeDate("ecpTherapyDiscontinuedDate"));        
            attributes.put("ecpTherapyDiscontinuedReason", new AttributeString("ecpTherapyDiscontinuedReason"));

            attributes.put("comment", new AttributeString("comment"));
        } else {
            if ("7.0".equals(this.getCrfVersion())){
            this.sourceDocumentTypes = SourceDocumentTypes;
            } else if ("7.1".equals(this.getCrfVersion())){
                this.sourceDocumentTypes = SourceDocumentTypesV7_1;
            }            
            
            this.requiresSourceDoc = true;
            this.requiresValidation = true;
            this.requiresVerification = true;
            
            attributes.put("changeTherapyDate", new AttributeDate("changeTherapyDate", true, false, true)); 
            attributes.put("changeTherapy", new AttributeString("changeTherapy"));
            attributes.put("tacrolimusCurrent", new AttributeString("tacrolimusCurrent", true, false, true));
            attributes.put("tacrolimusCurrentDosage", new AttributeString("tacrolimusCurrentDosage", true, false, true));
            attributes.put("alemtuzumabCurrent", new AttributeString("alemtuzumabCurrent", true, false, true));
            attributes.put("alemtuzumabCurrentDosage", new AttributeString("alemtuzumabCurrentDosage", true, false, true));
            attributes.put("sirolimusCurrent", new AttributeString("sirolimusCurrent", true, false, true));
            attributes.put("sirolimusCurrentDosage", new AttributeString("sirolimusCurrentDosage", true, false, true));
            attributes.put("everolimusCurrent", new AttributeString("everolimusCurrent", true, false, true));
            attributes.put("everolimusCurrentDosage", new AttributeString("everolimusCurrentDosage", true, false, true));
            attributes.put("azathioprineCurrent", new AttributeString("azathioprineCurrent", true, false, true));
            attributes.put("azathioprineCurrentDosage", new AttributeString("azathioprineCurrentDosage", true, false, true));
            attributes.put("cyclosporineACurrent", new AttributeString("cyclosporineACurrent", true, false, true));
            attributes.put("cyclosporineACurrentDosage", new AttributeString("cyclosporineACurrentDosage", true, false, true));
            attributes.put("methotrexateCurrent", new AttributeString("methotrexateCurrent", true, false, true));
            attributes.put("methotrexateCurrentDosage", new AttributeString("methotrexateCurrentDosage", true, false, true));
            attributes.put("macrolideAntibioticCurrent", new AttributeString("macrolideAntibioticCurrent", true, false, true));
            attributes.put("macrolideAntibioticCurrentDosage", new AttributeString("macrolideAntibioticCurrentDosage", true, false, true));
            attributes.put("mycophenolateMofetilCurrent", new AttributeString("mycophenolateMofetilCurrent", true, false, true));
            attributes.put("mycophenolateMofetilCurrentDosage", new AttributeString("mycophenolateMofetilCurrentDosage", true, false, true));
            attributes.put("antiThymocyteGlobulinCurrent", new AttributeString("antiThymocyteGlobulinCurrent", true, false, true));
            attributes.put("antiThymocyteGlobulinCurrentDosage", new AttributeString("antiThymocyteGlobulinCurrentDosage", true, false, true));
            attributes.put("totalLymphoidIrradiationCurrent", new AttributeString("totalLymphoidIrradiationCurrent", true, false, true));
            attributes.put("otherDrugsCurrent", new AttributeString("otherDrugsCurrent", true, false, true));        
            attributes.put("otherDrugNamesCurrent", new AttributeString("otherDrugNamesCurrent", true, false, true));        

            attributes.put("prednisoneCurrent", new AttributeString("prednisoneCurrent", true, false, true));
            attributes.put("prednisoneCurrentDosage", new AttributeInteger("prednisoneCurrentDosage", true, false, true));
            attributes.put("prednisoneDosageChanges", new AttributeInteger("prednisoneDosageChanges", true, false, true));
            attributes.put("prednisoneLowestDose", new AttributeInteger("prednisoneLowestDose", true, false, true));
            attributes.put("prednisoneHighestDose", new AttributeInteger("prednisoneHighestDose", true, false, true));

            attributes.put("onAntiCoagulant", new AttributeString("onAntiCoagulant", true, false, true));
            attributes.put("antiCoagulantName1", new AttributeString("antiCoagulantName1"));
            attributes.put("antiCoagulantName2", new AttributeString("antiCoagulantName2"));
            attributes.put("antiCoagulantName3", new AttributeString("antiCoagulantName3"));
            attributes.put("onAntiPlatelet", new AttributeString("onAntiPlatelet", true, false, true));
            attributes.put("antiPlateletName1", new AttributeString("antiPlateletName1"));
            attributes.put("antiPlateletName2", new AttributeString("antiPlateletName2"));
            attributes.put("antiPlateletName3", new AttributeString("antiPlateletName3"));

            attributes.put("ecpTherapyDiscontinued", new AttributeString("ecpTherapyDiscontinued", true, false, true));
            attributes.put("ecpTherapyDiscontinuedDate", new AttributeDate("ecpTherapyDiscontinuedDate", true, false, true));        
            attributes.put("ecpTherapyDiscontinuedReason", new AttributeString("ecpTherapyDiscontinuedReason", true, false, true));

            attributes.put("comment", new AttributeString("comment"));
        }
    }
	
    public Date getChangeTherapyDate() {
        return (Date) attributes.get("changeTherapyDate").getValue();
    }
    
    public void setChangeTherapyDate(Date changeTherapyDate) {
        attributes.get("changeTherapyDate").setValue(changeTherapyDate);
    }

    
    public String getChangeTherapy() {
//        System.out.println("getBaselineTherapyVerificationStatus was called containing: " + attributes.get("baselineTherapy").getVerificationStatus());
        return (String) attributes.get("changeTherapy").getValue();
    }
    
    public void setChangeTherapy(String changeTherapy) {
//        System.out.println("SET BaselineTherapyVerificationStatus was called containing: " + verificationStatus);
        attributes.get("changeTherapy").setValue(changeTherapy);
    }

    
    public String getTacrolimusCurrent() {
        return (String) attributes.get("tacrolimusCurrent").getValue();
    }
    
    public void setTacrolimusCurrent(String tacrolimusCurrent) {
        attributes.get("tacrolimusCurrent").setValue(tacrolimusCurrent);
    }

    public String getTacrolimusCurrentDosage() {
        return (String) attributes.get("tacrolimusCurrentDosage").getValue();
    }
    
    public void setTacrolimusCurrentDosage(String tacrolimusCurrentDosage) {
        attributes.get("tacrolimusCurrentDosage").setValue(tacrolimusCurrentDosage);
    }


    public String getPrednisoneCurrent() {
        return (String) attributes.get("prednisoneCurrent").getValue();
    }
    
    public void setPrednisoneCurrent(String prednisoneCurrent) {
        attributes.get("prednisoneCurrent").setValue(prednisoneCurrent);
    }


    public Integer getPrednisoneCurrentDosage() {
        return (Integer) attributes.get("prednisoneCurrentDosage").getValue();
    }
    
    public void setPrednisoneCurrentDosage(Integer prednisoneCurrentDosage) {
        attributes.get("prednisoneCurrentDosage").setValue(prednisoneCurrentDosage);
    }

    public Integer getPrednisoneDosageChanges() {
        return attributes.containsKey("prednisoneDosageChanges") ? (Integer)attributes.get("prednisoneDosageChanges").getValue() : null;
    }
    
    public void setPrednisoneDosageChanges(Integer prednisoneDosageChanges) {
        if (!attributes.containsKey("prednisoneDosageChanges")) {
            attributes.put("prednisoneDosageChanges", new AttributeInteger("prednisoneDosageChanges"));
        }
        
        attributes.get("prednisoneDosageChanges").setValue(prednisoneDosageChanges);
    }
    
    public Integer getPrednisoneLowestDose() {
        return attributes.containsKey("prednisoneLowestDose") ? (Integer) attributes.get("prednisoneLowestDose").getValue() : null;
    }
    
    public void setPrednisoneLowestDose(Integer prednisoneLowestDose) {
        if (!attributes.containsKey("prednisoneLowestDose")) {
            attributes.put("prednisoneLowestDose", new AttributeInteger("prednisoneLowestDose"));
        }
        
        attributes.get("prednisoneLowestDose").setValue(prednisoneLowestDose);
    }
    
    public Integer getPrednisoneHighestDose() {
        return attributes.containsKey("prednisoneHighestDose") ? (Integer) attributes.get("prednisoneHighestDose").getValue() : null;
    }
    
    public void setPrednisoneHighestDose(Integer prednisoneHighestDose) {
        if (!attributes.containsKey("prednisoneHighestDose")) {
            attributes.put("prednisoneHighestDose", new AttributeInteger("prednisoneHighestDose"));
        }
        
        attributes.get("prednisoneHighestDose").setValue(prednisoneHighestDose);
    }

    public String getAlemtuzumabCurrent() {
        return (String) attributes.get("alemtuzumabCurrent").getValue();
    }
    
    public void setAlemtuzumabCurrent(String alemtuzumabCurrent) {
        attributes.get("alemtuzumabCurrent").setValue(alemtuzumabCurrent);
    }

    public String getAlemtuzumabCurrentDosage() {
        return (String) attributes.get("alemtuzumabCurrentDosage").getValue();
    }
    
    public void setAlemtuzumabCurrentDosage(String alemtuzumabCurrentDosage) {
        attributes.get("alemtuzumabCurrentDosage").setValue(alemtuzumabCurrentDosage);
    }

    
    public String getSirolimusCurrent() {
        return (String) attributes.get("sirolimusCurrent").getValue();
    }
    
    public void setSirolimusCurrent(String sirolimusCurrent) {
        attributes.get("sirolimusCurrent").setValue(sirolimusCurrent);
    }

    public String getSirolimusCurrentDosage() {
        return (String) attributes.get("sirolimusCurrentDosage").getValue();
    }
    
    public void setSirolimusCurrentDosage(String sirolimusCurrentDosage) {
        attributes.get("sirolimusCurrentDosage").setValue(sirolimusCurrentDosage);
    }


    public String getEverolimusCurrent() {
        return (String) attributes.get("everolimusCurrent").getValue();
    }
    
    public void setEverolimusCurrent(String everolimusCurrent) {
        attributes.get("everolimusCurrent").setValue(everolimusCurrent);
    }

    public String getEverolimusCurrentDosage() {
        return (String) attributes.get("everolimusCurrentDosage").getValue();
    }
    
    public void setEverolimusCurrentDosage(String everolimusCurrentDosage) {
        attributes.get("everolimusCurrentDosage").setValue(everolimusCurrentDosage);
    }


    public String getAzathioprineCurrent() {
        return (String) attributes.get("azathioprineCurrent").getValue();
    }
    
    public void setAzathioprineCurrent(String azathioprineCurrent) {
        attributes.get("azathioprineCurrent").setValue(azathioprineCurrent);
    }

    public String getAzathioprineCurrentDosage() {
        return (String) attributes.get("azathioprineCurrentDosage").getValue();
    }
    
    public void setAzathioprineCurrentDosage(String azathioprineCurrentDosage) {
        attributes.get("azathioprineCurrentDosage").setValue(azathioprineCurrentDosage);
    }


    public String getCyclosporineACurrent() {
        return (String) attributes.get("cyclosporineACurrent").getValue();
    }
    
    public void setCyclosporineACurrent(String cyclosporineACurrent) {
        attributes.get("cyclosporineACurrent").setValue(cyclosporineACurrent);
    }

    public String getCyclosporineACurrentDosage() {
        return (String) attributes.get("cyclosporineACurrentDosage").getValue();
    }
    
    public void setCyclosporineACurrentDosage(String cyclosporineACurrentDosage) {
        attributes.get("cyclosporineACurrentDosage").setValue(cyclosporineACurrentDosage);
    }


    public String getMethotrexateCurrent() {
        return (String) attributes.get("methotrexateCurrent").getValue();
    }
    
    public void setMethotrexateCurrent(String methotrexateCurrent) {
        attributes.get("methotrexateCurrent").setValue(methotrexateCurrent);
    }

    public String getMethotrexateCurrentDosage() {
        return (String) attributes.get("methotrexateCurrentDosage").getValue();
    }
    
    public void setMethotrexateCurrentDosage(String methotrexateCurrentDosage) {
        attributes.get("methotrexateCurrentDosage").setValue(methotrexateCurrentDosage);
    }


    public String getMacrolideAntibioticCurrent() {
        return (String) attributes.get("macrolideAntibioticCurrent").getValue();
    }
    
    public void setMacrolideAntibioticCurrent(String macrolideAntibioticCurrent) {
        attributes.get("macrolideAntibioticCurrent").setValue(macrolideAntibioticCurrent);
    }

    public String getMacrolideAntibioticCurrentDosage() {
        return (String) attributes.get("macrolideAntibioticCurrentDosage").getValue();
    }
    
    public void setMacrolideAntibioticCurrentDosage(String macrolideAntibioticCurrentDosage) {
        attributes.get("macrolideAntibioticCurrentDosage").setValue(macrolideAntibioticCurrentDosage);
    }


    public String getMycophenolateMofetilCurrent() {
        return (String) attributes.get("mycophenolateMofetilCurrent").getValue();
    }
    
    public void setMycophenolateMofetilCurrent(String mycophenolateMofetilCurrent) {
        attributes.get("mycophenolateMofetilCurrent").setValue(mycophenolateMofetilCurrent);
    }

    public String getMycophenolateMofetilCurrentDosage() {
        return (String) attributes.get("mycophenolateMofetilCurrentDosage").getValue();
    }
    
    public void setMycophenolateMofetilCurrentDosage(String mycophenolateMofetilCurrentDosage) {
        attributes.get("mycophenolateMofetilCurrentDosage").setValue(mycophenolateMofetilCurrentDosage);
    }


    public String getAntiThymocyteGlobulinCurrent() {
        return (String) attributes.get("antiThymocyteGlobulinCurrent").getValue();
    }
    
    public void setAntiThymocyteGlobulinCurrent(String antiThymocyteGlobulinCurrent) {
        attributes.get("antiThymocyteGlobulinCurrent").setValue(antiThymocyteGlobulinCurrent);
    }

    public String getAntiThymocyteGlobulinCurrentDosage() {
        return (String) attributes.get("antiThymocyteGlobulinCurrentDosage").getValue();
    }
    
    public void setAntiThymocyteGlobulinCurrentDosage(String antiThymocyteGlobulinCurrentDosage) {
        attributes.get("antiThymocyteGlobulinCurrentDosage").setValue(antiThymocyteGlobulinCurrentDosage);
    }

    
    public String getTotalLymphoidIrradiationCurrent() {
        return (String) attributes.get("totalLymphoidIrradiationCurrent").getValue();
    }
    
    public void setTotalLymphoidIrradiationCurrent(String totalLymphoidIrradiationCurrent) {
        attributes.get("totalLymphoidIrradiationCurrent").setValue(totalLymphoidIrradiationCurrent);
    }
    
    public String getOtherDrugsCurrent() {
        return (String) attributes.get("otherDrugsCurrent").getValue();
    }
    
    public void setOtherDrugsCurrent(String OtherDrugsCurrent) {
        attributes.get("otherDrugsCurrent").setValue(OtherDrugsCurrent);
    }
        
    
    public String getOtherDrugNamesCurrent() {
        return (String) attributes.get("otherDrugNamesCurrent").getValue();
    }
    
    public void setOtherDrugNamesCurrent(String OtherDrugNamesCurrent) {
        attributes.get("otherDrugNamesCurrent").setValue(OtherDrugNamesCurrent);
    }
        
    
    public String getOnAntiCoagulant() {
        return (String) attributes.get("onAntiCoagulant").getValue();
    }
    
    public void setOnAntiCoagulant(String onAntiCoagulant) {
        attributes.get("onAntiCoagulant").setValue(onAntiCoagulant);
    }

    public String getAntiCoagulantName1() {
        return (String) attributes.get("antiCoagulantName1").getValue();
    }
    
    public void setAntiCoagulantName1(String antiCoagulantName1) {
        attributes.get("antiCoagulantName1").setValue(antiCoagulantName1);
    }

    public String getAntiCoagulantName2() {
        return (String) attributes.get("antiCoagulantName2").getValue();
    }
    
    public void setAntiCoagulantName2(String antiCoagulantName2) {
        attributes.get("antiCoagulantName2").setValue(antiCoagulantName2);
    }

    public String getAntiCoagulantName3() {
        return (String) attributes.get("antiCoagulantName3").getValue();
    }
    
    public void setAntiCoagulantName3(String antiCoagulantName3) {
        attributes.get("antiCoagulantName3").setValue(antiCoagulantName3);
    }

    
    
    public String getOnAntiPlatelet() {
        return (String) attributes.get("onAntiPlatelet").getValue();
    }
    
    public void setOnAntiPlatelet(String onAntiPlatelet) {
        attributes.get("onAntiPlatelet").setValue(onAntiPlatelet);
    }

    public String getAntiPlateletName1() {
        return (String) attributes.get("antiPlateletName1").getValue();
    }
    
    public void setAntiPlateletName1(String antiPlateletName1) {
        attributes.get("antiPlateletName1").setValue(antiPlateletName1);
    }

    public String getAntiPlateletName2() {
        return (String) attributes.get("antiPlateletName2").getValue();
    }
    
    public void setAntiPlateletName2(String antiPlateletName2) {
        attributes.get("antiPlateletName2").setValue(antiPlateletName2);
    }

    public String getAntiPlateletName3() {
        return (String) attributes.get("antiPlateletName3").getValue();
    }
    
    public void setAntiPlateletName3(String antiPlateletName3) {
        attributes.get("antiPlateletName3").setValue(antiPlateletName3);
    }
    
    
    

    public String getEcpTherapyDiscontinued() {
        return (String) attributes.get("ecpTherapyDiscontinued").getValue();
    }
    
    public void setEcpTherapyDiscontinued(String ecpTherapyDiscontinued) {
        attributes.get("ecpTherapyDiscontinued").setValue(ecpTherapyDiscontinued);
    }



    public Date getEcpTherapyDiscontinuedDate() {
        return (Date) attributes.get("ecpTherapyDiscontinuedDate").getValue();
    }
    
    public void setEcpTherapyDiscontinuedDate(Date ecpTherapyDiscontinuedDate) {
        attributes.get("ecpTherapyDiscontinuedDate").setValue(ecpTherapyDiscontinuedDate);
    }

    public String getEcpTherapyDiscontinuedReason() {
        return (String) attributes.get("ecpTherapyDiscontinuedReason").getValue();
    }
    
    public void setEcpTherapyDiscontinuedReason(String ecpTherapyDiscontinuedReason) {
        attributes.get("ecpTherapyDiscontinuedReason").setValue(ecpTherapyDiscontinuedReason);
    }

    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }

    public VerificationStatus getChangeTherapyDateVerificationStatus() {
        return attributes.get("changeTherapyDate").getVerificationStatus();
    }

    public void setChangeTherapyDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("changeTherapyDate").setVerificationStatus(verificationStatus);
    }

    public String getChangeTherapyDateDccComment() {
        return (String) attributes.get("changeTherapyDate").getDccComment();
    }

    public void setChangeTherapyDateDccComment(String dccComment) {
        attributes.get("changeTherapyDate").setDccComment(dccComment);
    }
    public VerificationStatus getTacrolimusCurrentVerificationStatus() {
        return attributes.get("tacrolimusCurrent").getVerificationStatus();
    }

    public void setTacrolimusCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("tacrolimusCurrent").setVerificationStatus(verificationStatus);
    }

    public String getTacrolimusCurrentDccComment() {
        return (String) attributes.get("tacrolimusCurrent").getDccComment();
    }

    public void setTacrolimusCurrentDccComment(String dccComment) {
        attributes.get("tacrolimusCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getTacrolimusCurrentDosageVerificationStatus() {
        return attributes.get("tacrolimusCurrentDosage").getVerificationStatus();
    }

    public void setTacrolimusCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("tacrolimusCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getTacrolimusCurrentDosageDccComment() {
        return (String) attributes.get("tacrolimusCurrentDosage").getDccComment();
    }

    public void setTacrolimusCurrentDosageDccComment(String dccComment) {
        attributes.get("tacrolimusCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getAlemtuzumabCurrentVerificationStatus() {
        return attributes.get("alemtuzumabCurrent").getVerificationStatus();
    }

    public void setAlemtuzumabCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("alemtuzumabCurrent").setVerificationStatus(verificationStatus);
    }

    public String getAlemtuzumabCurrentDccComment() {
        return (String) attributes.get("alemtuzumabCurrent").getDccComment();
    }

    public void setAlemtuzumabCurrentDccComment(String dccComment) {
        attributes.get("alemtuzumabCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getAlemtuzumabCurrentDosageVerificationStatus() {
        return attributes.get("alemtuzumabCurrentDosage").getVerificationStatus();
    }

    public void setAlemtuzumabCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("alemtuzumabCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getAlemtuzumabCurrentDosageDccComment() {
        return (String) attributes.get("alemtuzumabCurrentDosage").getDccComment();
    }

    public void setAlemtuzumabCurrentDosageDccComment(String dccComment) {
        attributes.get("alemtuzumabCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getSirolimusCurrentVerificationStatus() {
        return attributes.get("sirolimusCurrent").getVerificationStatus();
    }

    public void setSirolimusCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("sirolimusCurrent").setVerificationStatus(verificationStatus);
    }

    public String getSirolimusCurrentDccComment() {
        return (String) attributes.get("sirolimusCurrent").getDccComment();
    }

    public void setSirolimusCurrentDccComment(String dccComment) {
        attributes.get("sirolimusCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getSirolimusCurrentDosageVerificationStatus() {
        return attributes.get("sirolimusCurrentDosage").getVerificationStatus();
    }

    public void setSirolimusCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("sirolimusCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getSirolimusCurrentDosageDccComment() {
        return (String) attributes.get("sirolimusCurrentDosage").getDccComment();
    }

    public void setSirolimusCurrentDosageDccComment(String dccComment) {
        attributes.get("sirolimusCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getEverolimusCurrentVerificationStatus() {
        return attributes.get("everolimusCurrent").getVerificationStatus();
    }

    public void setEverolimusCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("everolimusCurrent").setVerificationStatus(verificationStatus);
    }

    public String getEverolimusCurrentDccComment() {
        return (String) attributes.get("everolimusCurrent").getDccComment();
    }

    public void setEverolimusCurrentDccComment(String dccComment) {
        attributes.get("everolimusCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getEverolimusCurrentDosageVerificationStatus() {
        return attributes.get("everolimusCurrentDosage").getVerificationStatus();
    }

    public void setEverolimusCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("everolimusCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getEverolimusCurrentDosageDccComment() {
        return (String) attributes.get("everolimusCurrentDosage").getDccComment();
    }

    public void setEverolimusCurrentDosageDccComment(String dccComment) {
        attributes.get("everolimusCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getAzathioprineCurrentVerificationStatus() {
        return attributes.get("azathioprineCurrent").getVerificationStatus();
    }

    public void setAzathioprineCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("azathioprineCurrent").setVerificationStatus(verificationStatus);
    }

    public String getAzathioprineCurrentDccComment() {
        return (String) attributes.get("azathioprineCurrent").getDccComment();
    }

    public void setAzathioprineCurrentDccComment(String dccComment) {
        attributes.get("azathioprineCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getAzathioprineCurrentDosageVerificationStatus() {
        return attributes.get("azathioprineCurrentDosage").getVerificationStatus();
    }

    public void setAzathioprineCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("azathioprineCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getAzathioprineCurrentDosageDccComment() {
        return (String) attributes.get("azathioprineCurrentDosage").getDccComment();
    }

    public void setAzathioprineCurrentDosageDccComment(String dccComment) {
        attributes.get("azathioprineCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getCyclosporineACurrentVerificationStatus() {
        return attributes.get("cyclosporineACurrent").getVerificationStatus();
    }

    public void setCyclosporineACurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("cyclosporineACurrent").setVerificationStatus(verificationStatus);
    }

    public String getCyclosporineACurrentDccComment() {
        return (String) attributes.get("cyclosporineACurrent").getDccComment();
    }

    public void setCyclosporineACurrentDccComment(String dccComment) {
        attributes.get("cyclosporineACurrent").setDccComment(dccComment);
    }
    public VerificationStatus getCyclosporineACurrentDosageVerificationStatus() {
        return attributes.get("cyclosporineACurrentDosage").getVerificationStatus();
    }

    public void setCyclosporineACurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("cyclosporineACurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getCyclosporineACurrentDosageDccComment() {
        return (String) attributes.get("cyclosporineACurrentDosage").getDccComment();
    }

    public void setCyclosporineACurrentDosageDccComment(String dccComment) {
        attributes.get("cyclosporineACurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getMethotrexateCurrentVerificationStatus() {
        return attributes.get("methotrexateCurrent").getVerificationStatus();
    }

    public void setMethotrexateCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("methotrexateCurrent").setVerificationStatus(verificationStatus);
    }

    public String getMethotrexateCurrentDccComment() {
        return (String) attributes.get("methotrexateCurrent").getDccComment();
    }

    public void setMethotrexateCurrentDccComment(String dccComment) {
        attributes.get("methotrexateCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getMethotrexateCurrentDosageVerificationStatus() {
        return attributes.get("methotrexateCurrentDosage").getVerificationStatus();
    }

    public void setMethotrexateCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("methotrexateCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getMethotrexateCurrentDosageDccComment() {
        return (String) attributes.get("methotrexateCurrentDosage").getDccComment();
    }

    public void setMethotrexateCurrentDosageDccComment(String dccComment) {
        attributes.get("methotrexateCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getMacrolideAntibioticCurrentVerificationStatus() {
        return attributes.get("macrolideAntibioticCurrent").getVerificationStatus();
    }

    public void setMacrolideAntibioticCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("macrolideAntibioticCurrent").setVerificationStatus(verificationStatus);
    }

    public String getMacrolideAntibioticCurrentDccComment() {
        return (String) attributes.get("macrolideAntibioticCurrent").getDccComment();
    }

    public void setMacrolideAntibioticCurrentDccComment(String dccComment) {
        attributes.get("macrolideAntibioticCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getMacrolideAntibioticCurrentDosageVerificationStatus() {
        return attributes.get("macrolideAntibioticCurrentDosage").getVerificationStatus();
    }

    public void setMacrolideAntibioticCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("macrolideAntibioticCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getMacrolideAntibioticCurrentDosageDccComment() {
        return (String) attributes.get("macrolideAntibioticCurrentDosage").getDccComment();
    }

    public void setMacrolideAntibioticCurrentDosageDccComment(String dccComment) {
        attributes.get("macrolideAntibioticCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getMycophenolateMofetilCurrentVerificationStatus() {
        return attributes.get("mycophenolateMofetilCurrent").getVerificationStatus();
    }

    public void setMycophenolateMofetilCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("mycophenolateMofetilCurrent").setVerificationStatus(verificationStatus);
    }

    public String getMycophenolateMofetilCurrentDccComment() {
        return (String) attributes.get("mycophenolateMofetilCurrent").getDccComment();
    }

    public void setMycophenolateMofetilCurrentDccComment(String dccComment) {
        attributes.get("mycophenolateMofetilCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getMycophenolateMofetilCurrentDosageVerificationStatus() {
        return attributes.get("mycophenolateMofetilCurrentDosage").getVerificationStatus();
    }

    public void setMycophenolateMofetilCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("mycophenolateMofetilCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getMycophenolateMofetilCurrentDosageDccComment() {
        return (String) attributes.get("mycophenolateMofetilCurrentDosage").getDccComment();
    }

    public void setMycophenolateMofetilCurrentDosageDccComment(String dccComment) {
        attributes.get("mycophenolateMofetilCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getAntiThymocyteGlobulinCurrentVerificationStatus() {
        return attributes.get("antiThymocyteGlobulinCurrent").getVerificationStatus();
    }

    public void setAntiThymocyteGlobulinCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("antiThymocyteGlobulinCurrent").setVerificationStatus(verificationStatus);
    }

    public String getAntiThymocyteGlobulinCurrentDccComment() {
        return (String) attributes.get("antiThymocyteGlobulinCurrent").getDccComment();
    }

    public void setAntiThymocyteGlobulinCurrentDccComment(String dccComment) {
        attributes.get("antiThymocyteGlobulinCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getAntiThymocyteGlobulinCurrentDosageVerificationStatus() {
        return attributes.get("antiThymocyteGlobulinCurrentDosage").getVerificationStatus();
    }

    public void setAntiThymocyteGlobulinCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("antiThymocyteGlobulinCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getAntiThymocyteGlobulinCurrentDosageDccComment() {
        return (String) attributes.get("antiThymocyteGlobulinCurrentDosage").getDccComment();
    }

    public void setAntiThymocyteGlobulinCurrentDosageDccComment(String dccComment) {
        attributes.get("antiThymocyteGlobulinCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getTotalLymphoidIrradiationCurrentVerificationStatus() {
        return attributes.get("totalLymphoidIrradiationCurrent").getVerificationStatus();
    }

    public void setTotalLymphoidIrradiationCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("totalLymphoidIrradiationCurrent").setVerificationStatus(verificationStatus);
    }

    public String getTotalLymphoidIrradiationCurrentDccComment() {
        return (String) attributes.get("totalLymphoidIrradiationCurrent").getDccComment();
    }

    public void setTotalLymphoidIrradiationCurrentDccComment(String dccComment) {
        attributes.get("totalLymphoidIrradiationCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getOtherDrugsCurrentVerificationStatus() {
        return attributes.get("otherDrugsCurrent").getVerificationStatus();
    }

    public void setOtherDrugsCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("otherDrugsCurrent").setVerificationStatus(verificationStatus);
    }

    public String getOtherDrugsCurrentDccComment() {
        return (String) attributes.get("otherDrugsCurrent").getDccComment();
    }

    public void setOtherDrugsCurrentDccComment(String dccComment) {
        attributes.get("otherDrugsCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getOtherDrugNamesCurrentVerificationStatus() {
        return attributes.get("otherDrugNamesCurrent").getVerificationStatus();
    }

    public void setOtherDrugNamesCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("otherDrugNamesCurrent").setVerificationStatus(verificationStatus);
    }

    public String getOtherDrugNamesCurrentDccComment() {
        return (String) attributes.get("otherDrugNamesCurrent").getDccComment();
    }

    public void setOtherDrugNamesCurrentDccComment(String dccComment) {
        attributes.get("otherDrugNamesCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getPrednisoneCurrentVerificationStatus() {
        return attributes.get("prednisoneCurrent").getVerificationStatus();
    }

    public void setPrednisoneCurrentVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("prednisoneCurrent").setVerificationStatus(verificationStatus);
    }

    public String getPrednisoneCurrentDccComment() {
        return (String) attributes.get("prednisoneCurrent").getDccComment();
    }

    public void setPrednisoneCurrentDccComment(String dccComment) {
        attributes.get("prednisoneCurrent").setDccComment(dccComment);
    }
    public VerificationStatus getPrednisoneCurrentDosageVerificationStatus() {
        return attributes.get("prednisoneCurrentDosage").getVerificationStatus();
    }

    public void setPrednisoneCurrentDosageVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("prednisoneCurrentDosage").setVerificationStatus(verificationStatus);
    }

    public String getPrednisoneCurrentDosageDccComment() {
        return (String) attributes.get("prednisoneCurrentDosage").getDccComment();
    }

    public void setPrednisoneCurrentDosageDccComment(String dccComment) {
        attributes.get("prednisoneCurrentDosage").setDccComment(dccComment);
    }
    public VerificationStatus getPrednisoneDosageChangesVerificationStatus() {
        return attributes.get("prednisoneDosageChanges").getVerificationStatus();
    }

    public void setPrednisoneDosageChangesVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("prednisoneDosageChanges").setVerificationStatus(verificationStatus);
    }

    public String getPrednisoneDosageChangesDccComment() {
        return (String) attributes.get("prednisoneDosageChanges").getDccComment();
    }

    public void setPrednisoneDosageChangesDccComment(String dccComment) {
        attributes.get("prednisoneDosageChanges").setDccComment(dccComment);
    }
    public VerificationStatus getPrednisoneLowestDoseVerificationStatus() {
        return attributes.get("prednisoneLowestDose").getVerificationStatus();
    }

    public void setPrednisoneLowestDoseVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("prednisoneLowestDose").setVerificationStatus(verificationStatus);
    }

    public String getPrednisoneLowestDoseDccComment() {
        return (String) attributes.get("prednisoneLowestDose").getDccComment();
    }

    public void setPrednisoneLowestDoseDccComment(String dccComment) {
        attributes.get("prednisoneLowestDose").setDccComment(dccComment);
    }
    public VerificationStatus getPrednisoneHighestDoseVerificationStatus() {
        return attributes.get("prednisoneHighestDose").getVerificationStatus();
    }

    public void setPrednisoneHighestDoseVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("prednisoneHighestDose").setVerificationStatus(verificationStatus);
    }

    public String getPrednisoneHighestDoseDccComment() {
        return (String) attributes.get("prednisoneHighestDose").getDccComment();
    }

    public void setPrednisoneHighestDoseDccComment(String dccComment) {
        attributes.get("prednisoneHighestDose").setDccComment(dccComment);
    }
    public VerificationStatus getOnAntiCoagulantVerificationStatus() {
        return attributes.get("onAntiCoagulant").getVerificationStatus();
    }

    public void setOnAntiCoagulantVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("onAntiCoagulant").setVerificationStatus(verificationStatus);
    }

    public String getOnAntiCoagulantDccComment() {
        return (String) attributes.get("onAntiCoagulant").getDccComment();
    }

    public void setOnAntiCoagulantDccComment(String dccComment) {
        attributes.get("onAntiCoagulant").setDccComment(dccComment);
    }
    public VerificationStatus getOnAntiPlateletVerificationStatus() {
        return attributes.get("onAntiPlatelet").getVerificationStatus();
    }

    public void setOnAntiPlateletVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("onAntiPlatelet").setVerificationStatus(verificationStatus);
    }

    public String getOnAntiPlateletDccComment() {
        return (String) attributes.get("onAntiPlatelet").getDccComment();
    }

    public void setOnAntiPlateletDccComment(String dccComment) {
        attributes.get("onAntiPlatelet").setDccComment(dccComment);
    }
    public VerificationStatus getEcpTherapyDiscontinuedVerificationStatus() {
        return attributes.get("ecpTherapyDiscontinued").getVerificationStatus();
    }

    public void setEcpTherapyDiscontinuedVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("ecpTherapyDiscontinued").setVerificationStatus(verificationStatus);
    }

    public String getEcpTherapyDiscontinuedDccComment() {
        return (String) attributes.get("ecpTherapyDiscontinued").getDccComment();
    }

    public void setEcpTherapyDiscontinuedDccComment(String dccComment) {
        attributes.get("ecpTherapyDiscontinued").setDccComment(dccComment);
    }
    public VerificationStatus getEcpTherapyDiscontinuedDateVerificationStatus() {
        return attributes.get("ecpTherapyDiscontinuedDate").getVerificationStatus();
    }

    public void setEcpTherapyDiscontinuedDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("ecpTherapyDiscontinuedDate").setVerificationStatus(verificationStatus);
    }

    public String getEcpTherapyDiscontinuedDateDccComment() {
        return (String) attributes.get("ecpTherapyDiscontinuedDate").getDccComment();
    }

    public void setEcpTherapyDiscontinuedDateDccComment(String dccComment) {
        attributes.get("ecpTherapyDiscontinuedDate").setDccComment(dccComment);
    }
    public VerificationStatus getEcpTherapyDiscontinuedReasonVerificationStatus() {
        return attributes.get("ecpTherapyDiscontinuedReason").getVerificationStatus();
    }

    public void setEcpTherapyDiscontinuedReasonVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("ecpTherapyDiscontinuedReason").setVerificationStatus(verificationStatus);
    }

    public String getEcpTherapyDiscontinuedReasonDccComment() {
        return (String) attributes.get("ecpTherapyDiscontinuedReason").getDccComment();
    }

    public void setEcpTherapyDiscontinuedReasonDccComment(String dccComment) {
        attributes.get("ecpTherapyDiscontinuedReason").setDccComment(dccComment);
    }

}
