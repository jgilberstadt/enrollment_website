package edu.wustl.mir.ctt.model;

public enum FormStatus {

    NEW("New", 1),
    STARTED("Started", 2),
    SUBMITTED("Submitted", 3),
    CRF_QUERY("CRF Query", 3),
    DCC_VERIFIED("DCC Verified", 4),
    PI_APPROVED("PI Approved", 5),
    NOT_REQUIRED("Not Required", 6),
    MISSED_VISIT("Missed Visit", 7),
    UNKNOWN("Unknown", 0);

    private final String name;
    
    /**
     * The status have an ordering based on the general progression through
     * the states.
     */
    private final int precedence;

    private FormStatus( String name, int precedence) {
        this.name = name;
        this.precedence = precedence;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPrecedence() {
        return precedence;
    }
    
    /**
     * Return matching enum based on string.
     * 
     * The enum is stored in an RDBMS as string. This allows the conversion back
     * to enum from the DB's stored string.
     * 
     * @param s
     * @return 
     */
//    public static FormStatus getFormStatus(String s) {
//        if("NEW".equals(s))
//            return NEW;
//        else if( "STARTED".equals(s))
//            return STARTED;
//        else if( "SUBMITTED".equals(s))
//            return SUBMITTED;
//        else if( "QUESTIONED".equals(s))
//            return QUESTIONED;
//        else if( "APPROVED".equals(s))
//            return APPROVED;
//        else if( "COMPLETED".equals(s))
//            return COMPLETED;
//        else
//            return UNKNOWN;
//    }
}
