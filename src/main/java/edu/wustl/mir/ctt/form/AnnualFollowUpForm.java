package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeInteger;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;
import org.apache.log4j.Logger;


/**
 *
 * @author pkc
 */
public class AnnualFollowUpForm extends BasicForm {
    private transient Logger logger = Logger.getLogger(ECPTreatmentForm.class);

    public static final String[] SourceDocumentTypes = new String[]{"ECP Report - Date Page"};

    public AnnualFollowUpForm() {
        // constructor
        super();
        formType = ECPFormTypes.ANNUAL_FOLLOW_UP;
        title = "Annual Follow-Up Form";
        this.sourceDocumentTypes = SourceDocumentTypes;
        
        //
        // NOTE: This Annual FollowUp CRF is not complete.  It still needs more work.
        //
        
        attributes.put("annualFollowUpDate", new AttributeDate("annualFollowUpDate"));        
        attributes.put("annualFollowUpReceivingECPTx", new AttributeString("annualFollowUpReceivingECPTx"));
        attributes.put("annualFollowUpNumberECPTx", new AttributeInteger("annualFollowUpNumberECPTx"));
        attributes.put("annualFollowUpECPTxDate1", new AttributeDate("annualFollowUpECPTxDate1", true, false, true));
        attributes.put("annualFollowUpECPTxDate2", new AttributeDate("annualFollowUpECPTxDate2"));
        attributes.put("annualFollowUpECPTxDate3", new AttributeDate("annualFollowUpECPTxDate3"));
        attributes.put("annualFollowUpECPTxDate11", new AttributeDate("annualFollowUpECPTxDate11"));
        attributes.put("annualFollowUpECPTxDate12", new AttributeDate("annualFollowUpECPTxDate12"));
        attributes.put("annualFollowUpECPTxDate13", new AttributeDate("annualFollowUpECPTxDate13"));
        attributes.put("annualFollowUpECPTxDate21", new AttributeDate("annualFollowUpECPTxDate21"));
        attributes.put("annualFollowUpECPTxDate22", new AttributeDate("annualFollowUpECPTxDate22"));
        attributes.put("annualFollowUpECPTxDate23", new AttributeDate("annualFollowUpECPTxDate23"));
        attributes.put("annualFollowUpPFTDate", new AttributeDate("annualFollowUpPFTDate"));        

       this.clear();

    }
    
    public AnnualFollowUpForm( BasicForm bf) {
        super(bf);		
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
	
    public Date getAnnualFollowUpDate() {
        return (Date) attributes.get("annualFollowUpDate").getValue();
    }
    
    public void setAnnualFollowUpDate(Date annualFollowUpDate) {
        attributes.get("annualFollowUpDate").setValue(annualFollowUpDate);
    }

    
    public String getAnnualFollowUpReceivingECPTx() {
        return (String) attributes.get("annualFollowUpReceivingECPTx").getValue();
    }
    
    public void setAnnualFollowUpReceivingECPTx(String annualFollowUpReceivingECPTx) {
        attributes.get("annualFollowUpReceivingECPTx").setValue(annualFollowUpReceivingECPTx);
    }


    public Integer getAnnualFollowUpNumberECPTx() {
        return (Integer) attributes.get("annualFollowUpNumberECPTx").getValue();
    }
    
    public void setAnnualFollowUpNumberECPTx(Integer annualFollowUpNumberECPTx) {
//        System.out.println("setTerminationReason was called!!!");
        attributes.get("annualFollowUpNumberECPTx").setValue(annualFollowUpNumberECPTx);
    }

    
    public Date getAnnualFollowUpECPTxDate1() {
        return (Date) attributes.get("annualFollowUpECPTxDate1").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate1(Date annualFollowUpECPTxDate1) {
        attributes.get("annualFollowUpECPTxDate1").setValue(annualFollowUpECPTxDate1);
    }

    public Date getAnnualFollowUpECPTxDate2() {
        return (Date) attributes.get("annualFollowUpECPTxDate2").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate2(Date annualFollowUpECPTxDate2) {
        attributes.get("annualFollowUpECPTxDate2").setValue(annualFollowUpECPTxDate2);
    }

    public Date getAnnualFollowUpECPTxDate3() {
        return (Date) attributes.get("annualFollowUpECPTxDate3").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate3(Date annualFollowUpECPTxDate3) {
        attributes.get("annualFollowUpECPTxDate3").setValue(annualFollowUpECPTxDate3);
    }


    public Date getAnnualFollowUpECPTxDate11() {
        return (Date) attributes.get("annualFollowUpECPTxDate11").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate11(Date annualFollowUpECPTxDate11) {
        attributes.get("annualFollowUpECPTxDate11").setValue(annualFollowUpECPTxDate11);
    }

    public Date getAnnualFollowUpECPTxDate12() {
        return (Date) attributes.get("annualFollowUpECPTxDate12").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate12(Date annualFollowUpECPTxDate12) {
        attributes.get("annualFollowUpECPTxDate2").setValue(annualFollowUpECPTxDate12);
    }

    public Date getAnnualFollowUpECPTxDate13() {
        return (Date) attributes.get("annualFollowUpECPTxDate13").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate13(Date annualFollowUpECPTxDate13) {
        attributes.get("annualFollowUpECPTxDate13").setValue(annualFollowUpECPTxDate13);
    }


    public Date getAnnualFollowUpECPTxDate21() {
        return (Date) attributes.get("annualFollowUpECPTxDate21").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate21(Date annualFollowUpECPTxDate21) {
        attributes.get("annualFollowUpECPTxDate21").setValue(annualFollowUpECPTxDate21);
    }

    public Date getAnnualFollowUpECPTxDate22() {
        return (Date) attributes.get("annualFollowUpECPTxDate22").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate22(Date annualFollowUpECPTxDate22) {
        attributes.get("annualFollowUpECPTxDate22").setValue(annualFollowUpECPTxDate22);
    }

    public Date getAnnualFollowUpECPTxDate23() {
        return (Date) attributes.get("annualFollowUpECPTxDate23").getValue();
    }
    
    public void setAnnualFollowUpECPTxDate23(Date annualFollowUpECPTxDate23) {
        attributes.get("annualFollowUpECPTxDate23").setValue(annualFollowUpECPTxDate23);
    }





    public VerificationStatus getAnnualFollowUpECPTxDate1VerificationStatus() {
        logger.debug("AnnualFollowUpForm, getAnnualFollowUpECPTxDate1VerificationStatus");
        logger.debug("AnnualFollowUpForm, getAnnualFollowUpECPTxDate1VerificationStatus" + attributes.get("annualFollowUpECPTxDate1").getVerificationStatus());

//        System.out.println("getTerminationReasonVerify was called containing: " + attributes.get("terminationReason").getVerificationStatus());
        return attributes.get("annualFollowUpECPTxDate1").getVerificationStatus();
    }
    
    public void setAnnualFollowUpECPTxDate1VerificationStatus(VerificationStatus verificationStatus) {
//        System.out.println("SET TerminationReasonVerify was called containing: " + verificationStatus);
        attributes.get("annualFollowUpECPTxDate1").setVerificationStatus(verificationStatus);
    }

    public String getAnnualFollowUpECPTxDate1DccComment() {
        return (String) attributes.get("annualFollowUpECPTxDate1").getDccComment();
    }
    
    public void setAnnualFollowUpECPTxDate1DccComment(String dccComment) {
        attributes.get("annualFollowUpECPTxDate1").setDccComment(dccComment);
    }

    
    public Date getAnnualFollowUpPFTDate() {
        return (Date) attributes.get("annualFollowUpPFTDate").getValue();
    }
    
    public void setAnnualFollowUpPFTDate(Date annualFollowUpPFTDate) {
        attributes.get("annualFollowUpPFTDate").setValue(annualFollowUpPFTDate);
    }

    
    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }


  
    
}
