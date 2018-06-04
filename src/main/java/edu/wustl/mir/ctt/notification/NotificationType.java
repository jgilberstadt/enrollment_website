package edu.wustl.mir.ctt.notification;

/**
 *
 * @author drm
 */
public enum NotificationType {
    PARTICIPANT_ENROLLED("Participant Enrolled"),
    PARTICIPANT_CROSSED_OVER("Participant Crossed Over"),
    SAE_CREATED("SAE Created"),
    SAE_UPDATED("SAE Updated"),
    UNKNOWN("Unknown");
    
    private final String name;
    
    private NotificationType( String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
