package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeInteger;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pkc
 */
public class PulmEvalForm extends BasicForm implements Serializable {
    
    public static final String[] SourceDocumentTypes = new String[]{"Pulmonary Function Test Report"};
    
    public PulmEvalForm() {
        // constructor
        super();
        formType = ECPFormTypes.PULMONARY_EVAL;
        title = "Pulmonary Evaluation Form";
        this.sourceDocumentTypes = SourceDocumentTypes;

        attributes.put("pulmonaryFunctionTestingDate", new AttributeDate("pulmonaryFunctionTestingDate", true, false, true));        
        attributes.put("oxygenSaturation", new AttributeInteger("oxygenSaturation", true, false, true));
        attributes.put("receivingSupplementalOxygen", new AttributeString("receivingSupplementalOxygen", false, false, true));
        attributes.put("receivingSupplementalOxygenAmount", new AttributeFloat("receivingSupplementalOxygenAmount", false, false, true));
        attributes.put("receivingSupplementalOxygenDelivery", new AttributeString("receivingSupplementalOxygenDelivery", false, false, true));
        attributes.put("FEV1", new AttributeFloat("FEV1", true, false, true));
        attributes.put("FVC", new AttributeFloat("FVC", true, false, true));
        attributes.put("fev1FvcRatio", new AttributeFloat("fev1FvcRatio", true, false, true));

        attributes.put("comment", new AttributeString("comment"));

        this.clear();
    }
    
    public PulmEvalForm( BasicForm bf) {
        super(bf);		
        formType = ECPFormTypes.PULMONARY_EVAL;
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
	
    public Date getPulmonaryFunctionTestingDate() {
        return (Date) attributes.get("pulmonaryFunctionTestingDate").getValue();
    }
    
    public void setPulmonaryFunctionTestingDate(Date pulmonaryFunctionTestingDate) {
        attributes.get("pulmonaryFunctionTestingDate").setValue(pulmonaryFunctionTestingDate);
    }

    public VerificationStatus getPulmonaryFunctionTestingDateVerificationStatus() {
        return attributes.get("pulmonaryFunctionTestingDate").getVerificationStatus();
    }
    
    public void setPulmonaryFunctionTestingDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("pulmonaryFunctionTestingDate").setVerificationStatus(verificationStatus);
    }

    public String getPulmonaryFunctionTestingDateDccComment() {
        return (String) attributes.get("pulmonaryFunctionTestingDate").getDccComment();
    }
    
    public void setPulmonaryFunctionTestingDateDccComment(String dccComment) {
        attributes.get("pulmonaryFunctionTestingDate").setDccComment(dccComment);
    }

    public Integer getOxygenSaturation() {
        return (Integer) attributes.get("oxygenSaturation").getValue();
    }
    
    public void setOxygenSaturation(Integer oxygenSaturation) {
        attributes.get("oxygenSaturation").setValue(oxygenSaturation);
    }

    public VerificationStatus getOxygenSaturationVerificationStatus() {
//        System.out.println("getOxygenSaturationVerificationStatus was called containing: " + attributes.get("oxygenSaturation").getVerificationStatus());
        return attributes.get("oxygenSaturation").getVerificationStatus();
    }
    
    public void setOxygenSaturationVerificationStatus(VerificationStatus verificationStatus) {
//        System.out.println("SET OxygenSaturationVerificationStatus was called containing: " + verificationStatus);
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

    public Float getFev1() {
        return (Float) attributes.get("FEV1").getValue();
    }
    
    public void setFev1(Float fev1) {
        attributes.get("FEV1").setValue(fev1);
    }

    public VerificationStatus getFev1VerificationStatus() {
        return attributes.get("FEV1").getVerificationStatus();
    }
    
    public void setFev1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("FEV1").setVerificationStatus(verificationStatus);
    }

    public String getFev1DccComment() {
        return (String) attributes.get("FEV1").getDccComment();
    }
    
    public void setFev1DccComment(String dccComment) {
        attributes.get("FEV1").setDccComment(dccComment);
    }

    public Float getFvc() {
        return (Float) attributes.get("FVC").getValue();
    }
    
    public void setFvc(Float fvc) {
        attributes.get("FVC").setValue(fvc);
    }
    
    public VerificationStatus getFvcVerificationStatus() {
        return attributes.get("FVC").getVerificationStatus();
    }
    
    public void setFvcVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("FVC").setVerificationStatus(verificationStatus);
    }

    public String getFvcDccComment() {
        return (String) attributes.get("FVC").getDccComment();
    }
    
    public void setFvcDccComment(String dccComment) {
        attributes.get("FVC").setDccComment(dccComment);
    }

    public Float getFev1FvcRatio() {
        return (Float) attributes.get("fev1FvcRatio").getValue();
    }
    
    public void setFev1FvcRatio(Float fev1FvcRatio) {
        attributes.get("fev1FvcRatio").setValue(fev1FvcRatio);
    }

    public VerificationStatus getFev1FvcRatioVerificationStatus() {
        return attributes.get("fev1FvcRatio").getVerificationStatus();
    }
    
    public void setFev1FvcRatioVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev1FvcRatio").setVerificationStatus(verificationStatus);
    }

    public String getFev1FvcRatioDccComment() {
        return (String) attributes.get("fev1FvcRatio").getDccComment();
    }
    
    public void setFev1FvcRatioDccComment(String dccComment) {
        attributes.get("fev1FvcRatio").setDccComment(dccComment);
    }

    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }


}
