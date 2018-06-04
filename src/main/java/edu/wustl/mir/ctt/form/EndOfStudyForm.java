package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.Date;


/**
 *
 * @author pkc
 */
public class EndOfStudyForm extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"Clinical or Progress Note", "Autopsy Report If Available"};

    public EndOfStudyForm() {
        // constructor
        super();
        formType = ECPFormTypes.END_OF_STUDY;
        title = "End of Study Form";
        this.sourceDocumentTypes = SourceDocumentTypes;
        
        attributes.put("terminationDate", new AttributeDate("terminationDate"));        
        attributes.put("terminationReason", new AttributeString("terminationReason", true, false, true));
        attributes.put("otherSpecify", new AttributeString("otherSpecify"));
        
        attributes.put("comment", new AttributeString("comment"));

       this.clear();

    }
    
    public EndOfStudyForm( BasicForm bf) {
        super(bf);		
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
	
    public Date getTerminationDate() {
        return (Date) attributes.get("terminationDate").getValue();
    }
    
    public void setTerminationDate(Date terminationDate) {
        attributes.get("terminationDate").setValue(terminationDate);
    }

    public String getTerminationReason() {
        return (String) attributes.get("terminationReason").getValue();
    }
    
    public void setTerminationReason(String terminationReason) {
        System.out.println("setTerminationReason was called!!!");
        attributes.get("terminationReason").setValue(terminationReason);
    }

    public VerificationStatus getTerminationReasonVerificationStatus() {
//        System.out.println("getTerminationReasonVerify was called containing: " + attributes.get("terminationReason").getVerificationStatus());
        return attributes.get("terminationReason").getVerificationStatus();
    }
    
    public void setTerminationReasonVerificationStatus(VerificationStatus verificationStatus) {
//        System.out.println("SET TerminationReasonVerify was called containing: " + verificationStatus);
        attributes.get("terminationReason").setVerificationStatus(verificationStatus);
    }

    public String getTerminationReasonDccComment() {
        return (String) attributes.get("terminationReason").getDccComment();
    }
    
    public void setTerminationReasonDccComment(String dccComment) {
        attributes.get("terminationReason").setDccComment(dccComment);
    }

    public String getOtherSpecify() {
        return (String) attributes.get("otherSpecify").getValue();
    }
    
    public void setOtherSpecify(String otherSpecify) {
        attributes.get("otherSpecify").setValue(otherSpecify);
    }

    public String getComment() {
        return (String) attributes.get("comment").getValue();
    }
    
    public void setComment(String comment) {
        attributes.get("comment").setValue(comment);
    }


  
    
}
