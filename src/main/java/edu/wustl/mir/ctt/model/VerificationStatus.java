package edu.wustl.mir.ctt.model;

public enum VerificationStatus {
    UNVERIFIED("Unverified", false),         // in need of verification. May be better named "unreviewed".
                                             // logically, "unverified" should means any state except VERIFIED, which
                                             // are more states then just the state "UNVERIFIED".
//    NON_VERIFIED("Non-verified", false),     // not involved with verification process.
    VERIFIED("Verified", false),             // completed verification successfully.
    CRFQUERY("CRF Query", true),             // one type of failure to successfully verify.
    SOURCEMISSING("Source Missing", true);   // one type of failure to successfully verify.

    private final String name;
    /**
     * indicates if this state constitutes a question that must be resolved.
     * Question states block submission of forms.
     */
    private final boolean questioned;

    private VerificationStatus( String name, boolean questioned) {
        this.name = name;
        this.questioned = questioned;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isQuestioned() {
        return questioned;
    }
    
    /**
     * true if this status is the state used to denote "in need of review".
     * 
     * We don't use "isVerified" here because that denotes the wrong thing.
     * 
     * @return 
     */
    public boolean isReviewed() {
        return ! UNVERIFIED.equals(this);
    }
    
    /**
     * The list of states needed by form submitters.
     * 
     * @return 
     */
    public static VerificationStatus[] getSubmittersList() {        
        VerificationStatus[] list = {UNVERIFIED, CRFQUERY, SOURCEMISSING};
        return list;
    }
    
    /**
     * The list of states needed by form verifiers.
     * 
     * @return 
     */
    public static VerificationStatus[] getVerifiersList() {        
        VerificationStatus[] list = {UNVERIFIED, VERIFIED, CRFQUERY, SOURCEMISSING};
        return list;
    }
    
}
