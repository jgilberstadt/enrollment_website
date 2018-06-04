package edu.wustl.mir.ctt.model;

public enum ParticipantStatus {
    // Initializer to initial each entry in the enum.  A separate enum is created for each of these.
    // In this case, there will be 9 instances of this enum, one for each initialzer below.

    ENROLLED,
    INELIGIBLE,
    ACTIVE,
    COMPLETED,
    WITHDRAWN,
    UNKNOWN;

    /**
     * Return matching enum based on string.
     * 
     * The enum is stored in an RDBMS as string. This allows the conversion back
     * to enum from the DB's stored string.
     * 
     * @param s
     * @return 
     */
    // Constructor for the enum.  This constructor is called once for each initializer above.
    public static ParticipantStatus getStatus(String s) {
        if("ENROLLED".equals(s))
            return ENROLLED;
        else if("INELIGIBLE".equals(s))
            return INELIGIBLE;
        else if("ACTIVE".equals(s))
            return ACTIVE;
        else if( "COMPLETED".equals(s))
            return COMPLETED;
        else if( "WITHDRAWN".equals(s))
            return WITHDRAWN;
        else
            return UNKNOWN;
    }
}
