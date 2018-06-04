package edu.wustl.mir.ctt.model;

public enum StudyArmStatus {
    // Initializer to initial each entry in the enum.

    EPI_ARM("EPI Arm"),
    CONTROL_ARM("Control Arm"),
    EPI_PLUS_CONTROL("EPI Arm Plus"),
    CONTROL_PLUS_EPI("Control Arm Plus"),
	UNASSIGNED("Unassigned"),
    //ECP_TREATMENT_ARM("ECP Treatment Arm"),
    //OBSERVATIONAL_ARM("Observational Arm"),
    UNKNOWN("unknown");

    private final String name;

    // Constructor for the enum.  This constructor is called once for each initializer above.
    private StudyArmStatus( String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

}
