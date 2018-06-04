package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.SecurityManager;
import edu.wustl.mir.ctt.model.Attribute;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.FormStatus;
import edu.wustl.mir.ctt.model.VerificationStatus;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.versioncontrol.VersionControl;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BasicForm is the base class for all forms.
 * 
 * A form is always created in the context of an Event.
 * The form is new if it is passed a null id at construction.
 * 
 * @author drm
 *
 */
public class BasicForm implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BasicForm.class);
    
    protected Integer id;
    protected int eventId;
    protected String title;
    protected ECPFormTypes formType;
    protected Date date;
    protected FormStatus status;
    protected int sequenceNumber;
    protected Date lastSubmittedDate;
    protected boolean locked;
    protected boolean commentLocked;
    protected boolean verificationDisabled;
    protected String verificationType;
    protected HashMap<String, Attribute> attributes;
    protected String[] sourceDocumentTypes;
    protected String sourceDocumentType;
    // Not all forms require source docs. This enables asking for docs
    // only when they are required. Default is true.
    protected boolean requiresSourceDoc;
    
    // somes forms need to skip form validation. Default is true;
    protected boolean requiresValidation;
    
    // Somes forms need to skip form verification. Default is true;
    protected boolean requiresVerification;
    
    // "Submit" is not the appropriate name for all the forms' submit button.
    // Let the form provide this name. Default is "Submit"
    protected String submitButtonName;
    
    private static final long serialVersionUID = 1L;
    
    protected VersionControl versionControl;
    protected int versionControlId; // The Version Control id is used to software version the basicforms that are created from the versioncontrol table.
    protected String crfVersion;    // The basicform table has an crfversion column so that we know the crf version for the basic form.
    protected String irbVersion;    // The basicform table has an irbversion column so that we know the irb version for the basic form.
    protected Date irbSubmittedDate; // The basicform table has an irbSubmittedDate column so that we know the protocol was submitted to the WashU IRB for approval.
                                    // The crfDate will be the same as the irbSubmittedDate as indicated in the basicform table.  
    
    public BasicForm() {
        attributes = new HashMap<String, Attribute>();
        date = new Date();
        status = FormStatus.NEW;
        sequenceNumber = 1;
        lastSubmittedDate = new Date();
        locked = false;
        commentLocked = false;
        formType = ECPFormTypes.BASIC;
        title = "Basic Form";
        verificationDisabled = true;
        sourceDocumentTypes = new String[]{"test"};
        sourceDocumentType = "";
        requiresSourceDoc = true;
        submitButtonName = "Submit";
        requiresValidation = true;
        requiresVerification = true;
        crfVersion = ""; 
        irbVersion = ""; 
        irbSubmittedDate = null; 
    }  //        the constructor below copies the value from the form f into the new Basicform being created.  Otherwise,
       //        I found the variable above that is given a true value is somehow by the system given a false value if 
       //        the variable is not listed below.
  
    /**
     * Constructor that duplicates the supplied basic form. This facilitates 
     * the construction of form subclasses. The attributes are
     * not copied, just reused. 
     * 
     * @param f 
     */
    public BasicForm( BasicForm f) {
        this.id = f.id;
        this.eventId = f.eventId;
        this.formType = f.formType;
        this.title = f.title;
        this.date = f.date;
        this.status = f.status;
        this.sequenceNumber = f.sequenceNumber;
        this.lastSubmittedDate = f.lastSubmittedDate;
        this.locked = f.locked; // Database table basicform does not contain this value
        this.commentLocked = f.commentLocked; // Database table basicform does not contain this value
        this.requiresSourceDoc = f.requiresSourceDoc; // Database table basicform does not contain this value
        this.submitButtonName = f.submitButtonName; // Database table basicform does not contain this value
        this.requiresValidation = f.requiresValidation;
        this.verificationDisabled = f.verificationDisabled; // Database table basicform does not contain this value
        this.verificationType = f.verificationType; // Database table basicform does not contain this value
        this.sourceDocumentType = f.sourceDocumentType; // Database table basicform does not contain this value
        this.sourceDocumentTypes = f.sourceDocumentTypes; // Database table basicform does not contain this value
        this.requiresVerification = f.requiresVerification;
        this.versionControlId = f.versionControlId;
        this.crfVersion = f.crfVersion;  // The crfversion number for this form.
        this.irbSubmittedDate = f.irbSubmittedDate; // The date when the protocl was submitted to the IRB for approval.
        attributes = f.attributes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
    public void addAttribute( String name, Attribute a) {
        attributes.put( name, a);
    }
    
    public Collection<Attribute> getAttributes() {
        return attributes.values();
    }
    
//    public void setAttributeValue( String attributeName, Attribute attributeValue) {
//        attributes.get(attributeName).setValue(attributeValue);
//    }
//    
//    public String getAttributeValue( String attributeName) {
//        return attributes.get(attributeName).getValue();
//    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
//        System.out.println("The BasicForm.setStatus is: " + status + "\n");
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getType() {
        return formType.getName();
    }
    
    public ECPFormTypes getFormType() {
        return formType;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    public Date getLastSubmittedDate() {
        return lastSubmittedDate;
    }

    public void setLastSubmittedDate(Date lastSubmittedDate) {
        this.lastSubmittedDate = lastSubmittedDate;
    }

    public int getVersionControlId() {
        return this.versionControlId;
    }
    
    public void setVersionControlId(int versionControlId) {
        this.versionControlId = versionControlId;
    }
    
    public String getCrfVersion() {
        return this.crfVersion;
    }
    
    public void setCrfVersion(String crfVersion) {
        this.crfVersion = crfVersion;
    }
    
    public String getIrbVersion() {
        return irbVersion;
    }

    public void setIrbVersion(String irbVersion) {
        this.irbVersion = irbVersion;
    }
    
    public Date getIrbSubmittedDate() {
        return this.irbSubmittedDate;
    }
    
    public void setIrbSubmittedDate(Date irbSubmittedDate) {
        this.irbSubmittedDate = irbSubmittedDate;
    }

    /*
     * This is needed because we are reading/writing generic forms from a DB.
     * Different forms will have different strings.
    */
    public void setFormType( ECPFormTypes formType) {
        this.formType = formType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttributeQuestioned( String attributeName) {
        return attributes.get( attributeName).isQuestioned();
    }
    
    public boolean isAttributeVerifiable( String attributeName) {
        return attributes.get( attributeName).isVerifiable();
    }
    
    public boolean isAttributeOptional( String attributeName) {
        return attributes.get( attributeName).isOptional();
    }
    
    public boolean isVerificationDisabled() {
        return verificationDisabled;
    }

    public void setVerificationDisabled(boolean verificationDisabled) {
        this.verificationDisabled = verificationDisabled;
    }
    
    // Are all of the questions on a CRF locked so you cannot change them?
    // Currently this lock is being used to lock/unlock all of the questions on a CRF at once.
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCommentLocked() {
        return commentLocked;
    }

    public void setCommentLocked(boolean commentLocked) {
        this.commentLocked = commentLocked;
    }

    public String[] getSourceDocumentTypes(){
        System.out.println("The Basicform.getSourceDocumentTypes value zero is: " + sourceDocumentTypes[0] + "\n");
        return this.sourceDocumentTypes;
    }
    
    public String getSourceDocumentType(){
       System.out.println("The Basicform.getSourceDocumentType = " + sourceDocumentType + "\n");
       return this.sourceDocumentType;
    }
    
    public void setSourceDocumentType(String sourceDocumentType){
        System.out.println("The Basicform.setSourceDocumentType = " + sourceDocumentType + "\n");
        this.sourceDocumentType = sourceDocumentType;
    }

    public boolean isRequiresSourceDoc() {
        return requiresSourceDoc;
    }

    public void setRequiresSourceDoc(boolean requiresSourceDoc) {
        this.requiresSourceDoc = requiresSourceDoc;
    }

    public String getSubmitButtonName() {
        return submitButtonName;
    }

    public void setSubmitButtonName(String submitButtonName) {
        this.submitButtonName = submitButtonName;
    }

    // Most of the forms require many of the questions to be validated, but a few forms do not require any of
    // the questions to be validated.  The requiresValidation variable is for the whole form not individual questions.
    // But requiresValidation is used at the individual question level on the xhtml forms.  You just cannot turn on or
    // off individual questions for validation since this veriable is for the whole form.
    public boolean isRequiresValidation() {
        return requiresValidation;
    }

    public void setRequiresValidation(boolean requiresValidation) {
        this.requiresValidation = requiresValidation;
    }
    
    // Most of the forms require many of the questions to be DCC_VERIFIED, but a few forms do not require any of
    // the questions to be verified.  The requiresVerification variable is for the whole form not individual questions.
    public boolean isRequiresVerification() {
        return requiresVerification;
    }

    public void setRequiresVerification(boolean requiresVerification){ 
        this.requiresVerification = requiresVerification;
    }
    
    // Print out the Upper case type values for Enum
    public VerificationStatus[] getVerificationTypesUpperCase() {
        for(VerificationStatus vt: VerificationStatus.values()){
            System.out.println("VerificatinTyes[] name is: " + vt.name() + " verification type is: " + vt.getName());
        }
        return VerificationStatus.values();
    }

    public VerificationStatus[] getVerificationTypes() {
        if( SecurityManager.canVerifyForms()) {
            return VerificationStatus.getVerifiersList();
        }
        else {
            return VerificationStatus.getSubmittersList();
        }
    }
    
    public ArrayList getReceivingSupplementalOxygenDeliveryTypes() {
        ArrayList list;
        list = new ArrayList();
        list.add("");
        list.add("liters per minute from nasal prong");
        list.add("percent oxygen from mask");
        return list;
    }

    public void versionControl() {
        // Used in case there are CRF version differences
    }

    public void clear() {
        for( Attribute a: attributes.values()) {
            a.setValue(null);
        }
    }

    /**
     * A form is questioned if any of its attributes are questioned.
     * 
     * @return 
     */
    public boolean isQuestioned() {
        boolean q = false;
        
        for( Attribute a: attributes.values()) {
            if( a.isQuestioned()) {
                q = true;
                break;
            }
        }
        return q;
    }

    /**
     * A form is verified if all of its attributes are verified.
     * 
     * @return 
     */
    public boolean isVerified() {
        boolean q = true;
        
        for( Attribute a: attributes.values()) {
            if( ! a.isVerified()) {
                logger.debug("Attribute " + a.getName() + " needs verification but is not verified");
                q = false;
                break;
            }
        }
        return q;
    }

    /**
     * A form is curated if all of its attributes are in a reviewed state.
     * 
     * @return 
     */
    public boolean isCurated() {
        boolean q = true;
        
        for( Attribute a: attributes.values()) {
            if( ! a.isReviewed()) {
                q = false;
                break;
            }
        }
        return q;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "[Form type: " + formType.getName());
        for( Attribute a: attributes.values()) {
            sb.append( a.getName() + "=" + a.getValue());
        }
        sb.append("]");
        return sb.toString();
    }
}
