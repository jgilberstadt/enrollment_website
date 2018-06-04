package edu.wustl.mir.ctt.notification;

/**
 *
 * @author drm
 */
public class NotificationContent {
    
    private String siteName;
    private String participantId;
    private String studyArm;
    private String title;
    private String userName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getStudyArm() {
        return studyArm;
    }

    public void setStudyArm(String studyArm) {
        this.studyArm = studyArm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
