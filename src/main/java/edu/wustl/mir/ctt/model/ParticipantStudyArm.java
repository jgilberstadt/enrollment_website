package edu.wustl.mir.ctt.model;

public enum ParticipantStudyArm {

    ECP_TREATMENT_ARM,
    OBSERVATIONAL_ARM,
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
    public static ParticipantStudyArm getArm(String s) {
        if("ECP_TREATMENT_ARM".equals(s))
            return ECP_TREATMENT_ARM;
        else if("OBSERVATIONAL_ARM".equals(s))
            return OBSERVATIONAL_ARM;
        else
            return UNKNOWN;
    }
}
