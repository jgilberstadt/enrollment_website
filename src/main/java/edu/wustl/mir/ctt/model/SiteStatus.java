package edu.wustl.mir.ctt.model;

public enum SiteStatus {

    ACTIVE,
    PENDING,
    STOPPED,
    COMPLETED,
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
    public static SiteStatus getStatus(String s) {
        if("ACTIVE".equals(s))
            return ACTIVE;
        else if( "PENDING".equals(s))
            return PENDING;
        else if( "STOPPED".equals(s))
            return STOPPED;
        else if( "COMPLETED".equals(s))
            return COMPLETED;
        else
            return UNKNOWN;
    }
}
