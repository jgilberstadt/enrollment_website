package edu.wustl.mir.ctt.model;

import java.util.Date;
import java.io.Serializable;

/**
 *
 * @author drm
 */
public class Participant implements Serializable {
    private int id;
    private int siteId;
    private String participantID;
    private Date enrolledDate;
    private ParticipantStatus status;
    private Date studyArmEnrollDate;
    private StudyArmStatus studyArmStatus;
    private DeclineStrata declineStrata;
    private SpirometryStrata spirometryStrata;
    private boolean holdStatus;
    private Date holdStartDate;
    private Date overrideDate;
    private boolean showStudyArm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getParticipantID() {
        return participantID;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public ParticipantStatus getStatus() {
        return status;
    }

    public void setStatus(ParticipantStatus status) {
        this.status = status;
    }
    
    public Date getStudyArmEnrollDate() {
        return studyArmEnrollDate;
    }

    public void setStudyArmEnrollDate(Date studyArmEnrollDate) {
        this.studyArmEnrollDate = studyArmEnrollDate;
    }

    public StudyArmStatus getStudyArmStatus() {
        return showStudyArm == true ? studyArmStatus : StudyArmStatus.UNASSIGNED;
    }
    
    public StudyArmStatus getRealStudyArmStatus() {
        return studyArmStatus;
    }

    public void setStudyArmStatus(StudyArmStatus studyArmStatus) {
        this.studyArmStatus = studyArmStatus;
    }
    
    public DeclineStrata getDeclineStrata() {
        return declineStrata;
    }

    public void setDeclineStrata(DeclineStrata ds) {
        this.declineStrata = ds;
    }
    
    public SpirometryStrata getSpirometryStrata() {
        return spirometryStrata;
    }

    public void setSpirometryStrata(SpirometryStrata ss) {
        this.spirometryStrata = ss;
    }

    public boolean isHoldStatus() {
        return holdStatus;
    }

    public void setHoldStatus(boolean holdStatus) {
        this.holdStatus = holdStatus;
    }

    public Date getHoldStartDate() {
        return holdStartDate;
    }

    public void setHoldStartDate(Date holdStartDate) {
        this.holdStartDate = holdStartDate;
    }

    public Date getOverrideDate() {
        return overrideDate;
    }

    public void setOverrideDate(Date overrideDate) {
        this.overrideDate = overrideDate;
    }
    
    public boolean isShowStudyArm() {
        return showStudyArm;
    }
    
    public void setShowStudyArm(boolean showStudyArm) {
        this.showStudyArm = showStudyArm;
    }
    
    public boolean isRenderedObservationalArm() {
//        System.out.println("The getStudyArmStatus is: " + this.studyArmStatus.getName());
        return getStudyArmStatus().getName().equals("Observational Arm");
    }

}
