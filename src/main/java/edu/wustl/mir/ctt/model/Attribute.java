package edu.wustl.mir.ctt.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author drm
 * @param <T>  The T stands for the Type of parameter used such as boolean, String, int, float, etc.  So value is based on the T when it this attribute was created.
 */
public abstract class Attribute <T> implements Serializable {
    private int id;
    private String name;
    private T value;
    private boolean verify;    // may participate in validation
    private boolean optional;  // affects participation in validation.
    private VerificationStatus verificationStatus;
    private boolean persistent;  // is this attribute persisted?
    private String dccComment;
    
    private static final long serialVersionUID = 1L;
    
    public Attribute() {
        this( null, false, false, true, VerificationStatus.UNVERIFIED, null);
    }
    
    public Attribute( String name) {
        this( name, false, false, true, VerificationStatus.UNVERIFIED, null);
    }
    
    public Attribute( String name, boolean verify, boolean optional, boolean persistent) {
        this( name, verify, optional, persistent, VerificationStatus.UNVERIFIED, null);
    }

    protected Attribute( String name, boolean verify, boolean optional, boolean persistent, VerificationStatus verificationStatus, String dccComment) {
        this.name = name;
        this.verify = verify; 
        this.optional = optional;
        this.persistent = persistent;
        this.verificationStatus = verificationStatus;
        this.dccComment = dccComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract AttributeValueType getValueType();

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    /**
     * isVerifiable - read-only property indicating if this attribute must
     * participate in the verification QC process.
     * 
     * isVerifiable depends on verify, optional, and this attribute's value.
     * <table>
     *   <tr><th>verify</th><th>optional</th><th>value</th><th>output</th></tr>
     *   <tr><td>T</td><td>T</td><td>not-null</td><td>T</td></tr>
     *   <tr><td>T</td><td>T</td><td>null</td><td>F</td></tr>
     *   <tr><td>T</td><td>F</td><td>not-null</td><td>T</td></tr>
     *   <tr><td>T</td><td>F</td><td>null</td><td>T</td></tr>
     *   <tr><td>F</td><td>T/F</td><td>null/not-null</td><td>F</td></tr>
     * </table>
     * 
     * @return true if attribute is marked verify AND ( not-optional OR ( optional and has non-null value))
     */
    public boolean isVerifiable() {
        return verify && ((! optional) || (optional && (value != null)));
    }

    /**
     * isVerified - read-only property indicating if this attribute has been marked verified.
     * 
     * An attribute is verified if 
     * 1. not verifiable (i.e. all not-verifiable attributes are automatically considered verified.)
     * 2. is verifiable and its status is VERIFIED.
     * 
     * @return false if verifiable and status is not VERIFIED, true otherwise.
     */
    public boolean isVerified() {
        boolean b = (isVerifiable())? VerificationStatus.VERIFIED.equals(verificationStatus): true;
        return b;
    }
    
    /**
     * isReviewed - read-only property indicating if has been reviewed in the validation process.
     * 
     * @return 
     */
    public boolean isReviewed() {
        boolean b = (isVerifiable())?  ( verificationStatus.isReviewed()): true;
        return b;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }
    
    public String getDccComment() {
        return dccComment;
    }

    public void setDccComment(String dccComment) {
        this.dccComment = dccComment;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
    
    /**
     * isQuestioned.
     * 
     * An attribute is questioned if it is verifiable and questioned.
     * 
     * @return true if verifiable and questioned, false otherwise.
     */
    public boolean isQuestioned() {
        boolean b = false;
        // Check to see if the attribute is verifiable.
        if( isVerifiable()) {
            b = verificationStatus.isQuestioned();
        }
        
        return b;
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
