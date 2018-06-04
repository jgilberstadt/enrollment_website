package edu.wustl.mir.ctt.model;

public enum EventStatus {

    NEW("New"),
    STARTED("Started"),
    SUBMITTED("Submitted"),
    CRF_QUERY("CRF Query"),
    DCC_VERIFIED("DCC Verified"),
    PI_APPROVED("PI Approved"),
    NOT_REQUIRED("Not Required"),
    UNKNOWN("Unknown"),
    MISSED_VISIT("Missed Visit");

    private final String name;

    private EventStatus( String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Return matching enum based on string.
     * 
     * The enum is stored in an RDBMS as string. This allows the conversion back
     * to enum from the DB's stored string.
     * 
     * @param s
     * @return 
     * 
     * Mary Clare Defler said the ATTRACT Trial only has Projected, Completed and Missed visits.
     */
//    public static EventStatus getStatus(String s) {
//        if("PENDING".equals(s)) // In process or has not occurred yet.
//            return PENDING;
//        else if( "OVERDUE".equals(s))  // It is OVERDUE if there is an unsubmitted form in an 
                                         //event that is past the last projected date + offset.
//            return OVERDUE;
//        else if( "MISSED".equals(s))  // It is MISSED if the event never occurred and cannot 
                                        //occur (no longer possible).
//            return MISSED;
//        else if( "COMPLETED".equals(s))  // All forms have been completed.
//            return COMPLETED;
//        else
//            return UNKNOWN;
//    }
}
