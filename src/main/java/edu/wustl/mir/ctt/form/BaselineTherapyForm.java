package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeInteger;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;
//import javax.faces.event.AjaxBehaviorEvent;

/********
 *
 * @author pkc
 */
public class BaselineTherapyForm extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"Clinical Note or Medication Record Form"};
    public static final String[] SourceDocumentTypesV7_1 = new String[]{"Clinic Note or Medication Record"};
//    private boolean dataMissing;
    
    public BaselineTherapyForm() {
        // constructor
        super();
        formType = ECPFormTypes.BASELINE_THERAPY;
        title = "Baseline Therapy Form";
        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion()) || "7.0".equals(this.getCrfVersion())) {
            this.sourceDocumentTypes = SourceDocumentTypes;
        } else {
            this.sourceDocumentTypes = SourceDocumentTypesV7_1;
        }
//        dataMissing = false;
        
        attributes.put("baselineTherapyDate", new AttributeDate("baselineTherapyDate", true, false, true));        
        attributes.put("baselineTherapy", new AttributeString("baselineTherapy"));
        attributes.put("tacrolimusCurrent", new AttributeString("tacrolimusCurrent", true, false, true));
        attributes.put("prednisoneCurrent", new AttributeString("prednisoneCurrent", true, false, true));
        attributes.put("prednisoneCurrentDosage", new AttributeInteger("prednisoneCurrentDosage"));
        attributes.put("alemtuzumabCurrent", new AttributeString("alemtuzumabCurrent", true, false, true));
        attributes.put("sirolimusCurrent", new AttributeString("sirolimusCurrent", true, false, true));
        attributes.put("everolimusCurrent", new AttributeString("everolimusCurrent", true, false, true));
        attributes.put("azathioprineCurrent", new AttributeString("azathioprineCurrent", true, false, true));
        attributes.put("cyclosporineACurrent", new AttributeString("cyclosporineACurrent", true, false, true));
        attributes.put("methotrexateCurrent", new AttributeString("methotrexateCurrent", true, false, true));
        attributes.put("macrolideAntibioticCurrent", new AttributeString("macrolideAntibioticCurrent", true, false, true));
        attributes.put("mycophenolateMofetilCurrent", new AttributeString("mycophenolateMofetilCurrent", true, false, true));
        attributes.put("antiThymocyteGlobulinCurrent", new AttributeString("antiThymocyteGlobulinCurrent", true, false, true));
        attributes.put("totalLymphoidIrradiationCurrent", new AttributeString("totalLymphoidIrradiationCurrent", false, false, true));

        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion())) {
            attributes.put("otherDrugsCurrent", new AttributeString("otherDrugsCurrent", false, false, true));
            attributes.put("otherDrugNamesCurrent", new AttributeString("otherDrugNamesCurrent", false, false, true));  
            
            attributes.put("onAntiCoagulant", new AttributeString("onAntiCoagulant"));
            attributes.put("antiCoagulantName1", new AttributeString("antiCoagulantName1"));
            attributes.put("antiCoagulantName2", new AttributeString("antiCoagulantName2"));
            attributes.put("antiCoagulantName3", new AttributeString("antiCoagulantName3"));
            
            attributes.put("onAntiPlatelet", new AttributeString("onAntiPlatelet"));
            attributes.put("antiPlateletName1", new AttributeString("antiPlateletName1"));
            attributes.put("antiPlateletName2", new AttributeString("antiPlateletName2"));
            attributes.put("antiPlateletName3", new AttributeString("antiPlateletName3"));
        } else {
            attributes.put("otherDrugsCurrent", new AttributeString("otherDrugsCurrent", true, false, true));
            attributes.put("otherDrugNamesCurrent", new AttributeString("otherDrugNamesCurrent", false, false, true));  
            
            attributes.put("onAntiCoagulant", new AttributeString("onAntiCoagulant", true, false, true));
            attributes.put("antiCoagulantName1", new AttributeString("antiCoagulantName1", false, true, true));
            attributes.put("antiCoagulantName2", new AttributeString("antiCoagulantName2", false, true, true));
            attributes.put("antiCoagulantName3", new AttributeString("antiCoagulantName3", false, true, true));
            
            attributes.put("onAntiPlatelet", new AttributeString("onAntiPlatelet", true, false, true));
            attributes.put("antiPlateletName1", new AttributeString("antiPlateletName1", false, true, true));
            attributes.put("antiPlateletName2", new AttributeString("antiPlateletName2", false, true, true));
            attributes.put("antiPlateletName3", new AttributeString("antiPlateletName3", false, true, true));
        }
        
        attributes.put("comment", new AttributeString("comment"));

        this.clear();
    }

    public BaselineTherapyForm( BasicForm bf) {
        super(bf);		
        formType = ECPFormTypes.BASELINE_THERAPY;
        title = bf.getTitle();
        
        if ("1.0".equals(this.getCrfVersion()) || "5.0".equals(this.getCrfVersion()) || "7.0".equals(this.getCrfVersion())) {
            this.sourceDocumentTypes = SourceDocumentTypes;
        } else {
            this.sourceDocumentTypes = SourceDocumentTypesV7_1;
        }
    }
	
    public Date getBaselineTherapyDate() {
        return (Date) attributes.get("baselineTherapyDate").getValue();
    }
    
    public void setBaselineTherapyDate(Date baselineTherapyDate) {
        attributes.get("baselineTherapyDate").setValue(baselineTherapyDate);
    }


    
    public VerificationStatus getBaselineTherapyDateVerificationStatus() {
        return attributes.get("baselineTherapyDate").getVerificationStatus();
    }
    
    public void setBaselineTherapyDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("baselineTherapyDate").setVerificationStatus(verificationStatus);
    }

    public String getBaselineTherapyDateDccComment() {
        return (String) attributes.get("baselineTherapyDate").getDccComment();
    }
    
    public void setBaselineTherapyDateDccComment(String dccComment) {
        attributes.get("baselineTherapyDate").setDccComment(dccComment);
    }
    
    public String getTacrolimusCurrent() {
        return (String) attributes.get("tacrolimusCurrent").getValue();
    }
    
    public void setTacrolimusCurrent(String tacrolimusCurrent) {
        attributes.get("tacrolimusCurrent").setValue(tacrolimusCurrent);
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

    
    public String getPrednisoneCurrent() {
        return (String) attributes.get("prednisoneCurrent").getValue();
    }
    
    public void setPrednisoneCurrent(String prednisoneCurrent) {
        attributes.get("prednisoneCurrent").setValue(prednisoneCurrent);
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

    
    public Integer getPrednisoneCurrentDosage() {
        return (Integer) attributes.get("prednisoneCurrentDosage").getValue();
    }
    
    public void setPrednisoneCurrentDosage(Integer prednisoneCurrentDosage) {
        attributes.get("prednisoneCurrentDosage").setValue(prednisoneCurrentDosage);
    }

    public String getAlemtuzumabCurrent() {
        return (String) attributes.get("alemtuzumabCurrent").getValue();
    }
    
    public void setAlemtuzumabCurrent(String alemtuzumabCurrent) {
        attributes.get("alemtuzumabCurrent").setValue(alemtuzumabCurrent);
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

    
    public String getSirolimusCurrent() {
        return (String) attributes.get("sirolimusCurrent").getValue();
    }
    
    public void setSirolimusCurrent(String sirolimusCurrent) {
        attributes.get("sirolimusCurrent").setValue(sirolimusCurrent);
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

    
    public String getEverolimusCurrent() {
        return (String) attributes.get("everolimusCurrent").getValue();
    }
    
    public void setEverolimusCurrent(String everolimusCurrent) {
        attributes.get("everolimusCurrent").setValue(everolimusCurrent);
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

    
    public String getAzathioprineCurrent() {
        return (String) attributes.get("azathioprineCurrent").getValue();
    }
    
    public void setAzathioprineCurrent(String azathioprineCurrent) {
        attributes.get("azathioprineCurrent").setValue(azathioprineCurrent);
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

    
    public String getCyclosporineACurrent() {
        return (String) attributes.get("cyclosporineACurrent").getValue();
    }
    
    public void setCyclosporineACurrent(String cyclosporineACurrent) {
        attributes.get("cyclosporineACurrent").setValue(cyclosporineACurrent);
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

    
    public String getMethotrexateCurrent() {
        return (String) attributes.get("methotrexateCurrent").getValue();
    }
    
    public void setMethotrexateCurrent(String methotrexateCurrent) {
        attributes.get("methotrexateCurrent").setValue(methotrexateCurrent);
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

    
    public String getMacrolideAntibioticCurrent() {
        return (String) attributes.get("macrolideAntibioticCurrent").getValue();
    }
    
    public void setMacrolideAntibioticCurrent(String macrolideAntibioticCurrent) {
        attributes.get("macrolideAntibioticCurrent").setValue(macrolideAntibioticCurrent);
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

    
    public String getMycophenolateMofetilCurrent() {
        return (String) attributes.get("mycophenolateMofetilCurrent").getValue();
    }
    
    public void setMycophenolateMofetilCurrent(String mycophenolateMofetilCurrent) {
        attributes.get("mycophenolateMofetilCurrent").setValue(mycophenolateMofetilCurrent);
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

  
    public String getAntiThymocyteGlobulinCurrent() {
        return (String) attributes.get("antiThymocyteGlobulinCurrent").getValue();
    }
    
    public void setAntiThymocyteGlobulinCurrent(String antiThymocyteGlobulinCurrent) {
        attributes.get("antiThymocyteGlobulinCurrent").setValue(antiThymocyteGlobulinCurrent);
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

    
    public String getTotalLymphoidIrradiationCurrent() {
        return (String) attributes.get("totalLymphoidIrradiationCurrent").getValue();
    }
    
    public void setTotalLymphoidIrradiationCurrent(String totalLymphoidIrradiationCurrent) {
        attributes.get("totalLymphoidIrradiationCurrent").setValue(totalLymphoidIrradiationCurrent);
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


    public String getOtherDrugsCurrent() {
        return (String) attributes.get("otherDrugsCurrent").getValue();
    }
    
    public void setOtherDrugsCurrent(String OtherDrugsCurrent) {
        attributes.get("otherDrugsCurrent").setValue(OtherDrugsCurrent);
    }
        
    
    public String getOtherDrugNamesCurrent() {
        return (String) attributes.get("otherDrugNamesCurrent").getValue();
    }
    
    public void setOtherDrugNamesCurrent(String otherDrugNamesCurrent) {
        attributes.get("otherDrugNamesCurrent").setValue(otherDrugNamesCurrent);
    }
   
    
    public String getBaselineTherapy() {
//        System.out.println("getBaselineTherapyVerificationStatus was called containing: " + attributes.get("baselineTherapy").getVerificationStatus());
        return (String) attributes.get("baselineTherapy").getValue();
    }
    
    public void setBaselineTherapy(String baselineTherapy) {
//        System.out.println("SET BaselineTherapyVerificationStatus was called containing: " + verificationStatus);
        attributes.get("baselineTherapy").setValue(baselineTherapy);
    }

    public VerificationStatus getBaselineTherapyVerificationStatus() {
//        System.out.println("getBaselineTherapyVerificationStatus was called containing: " + attributes.get("baselineTherapy").getVerificationStatus());
        return attributes.get("baselineTherapy").getVerificationStatus();
    }
    
    public void setBaselineTherapyVerificationStatus(VerificationStatus verificationStatus) {
//        System.out.println("SET BaselineTherapyVerificationStatus was called containing: " + verificationStatus);
        attributes.get("baselineTherapy").setVerificationStatus(verificationStatus);
    }

    public String getBaselineTherapyDccComment() {
        return (String) attributes.get("baselineTherapy").getDccComment();
    }
    
    public void setBaselineTherapyDccComment(String dccComment) {
        attributes.get("baselineTherapy").setDccComment(dccComment);
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
    
    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
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
    
/*
    // The following coded was added with the idea of allowing a study coordinator to select a checkbox stating "Data Missing" at the 
    // bottom of the baselineTherapy.xhtml form next to the Submit button if any data could not be input into the form.  Most of the
    // questions have validators which require a value to be entered, but with the "Data Missing" checkbox, the xhtml form could check
    // to see if the checkbox was checked.  If so, the validators could be disabled in the baselineTherapy.xhtml code depending on
    // which question(s) or parts of a question you wish to disable with the isDataMissing method.
    public boolean isDataMissing() {
        System.out.println("IS DataMissing value is: " + dataMissing + "\n");
        return (boolean) dataMissing;
    }
    
    public void setDataMissing(boolean dataMissing) {
        System.out.println("SET DataMissing value is: " + dataMissing + "\n");
        this.dataMissing = dataMissing;
    }

    public void dataMissingListener(AjaxBehaviorEvent event) {
        if( isDataMissing()) {
            System.out.println("The dataMissingListener value for isDataMissing is: " + dataMissing + "\n");
            setDataMissing(true);
        }
    }
*/
}
