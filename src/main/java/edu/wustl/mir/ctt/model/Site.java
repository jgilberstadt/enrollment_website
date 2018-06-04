package edu.wustl.mir.ctt.model;

import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author drm
 */
public class Site implements Serializable {
    private int id;
    private String name;
    private int siteID;
    private SiteStatus status;
    private String crfVersion;      // The site table has an crfversion column so that we know the crf version for the basic form.
    protected String irbVersion;    // The site table has an irbversion column so that we know the irb version for the basic form.
    private Date irbSubmittedDate;   // Date when the WashU Protocol was submitted to the WashU IRB for approval.
    private int currentlyActive;    // Set to a 1 for the currently active IRB protocol, set to a zero for old versions of the irb protocol.
    
    public Site() {
        status = SiteStatus.ACTIVE;
        irbVersion = "4.0";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public SiteStatus getStatus() {
        return status;
    }

    public void setStatus(SiteStatus status) {
        this.status = status;
    }
    
    public String getCrfVersion() {
        return crfVersion;
    }

    public void setCrfVersion(String crfVersion) {
        this.crfVersion = crfVersion;
    }
    
    public String getIrbVersion() {
        return irbVersion;
    }

    public void setIrbVersion(String irbVersion) {
        this.irbVersion = irbVersion;
    }
    
    public Date getIrbSubmittedDate() {
        return irbSubmittedDate;
    }

    public void setIrbSubmittedDate(Date irbSubmittedDate) {
        this.irbSubmittedDate = irbSubmittedDate;
    }
    
    public int getCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(int currentlyActive) {
        this.currentlyActive = currentlyActive;
    }

    public int getEnrolledParticipantsCount() throws PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        return pm.getEnrolledParticipantsCount( this.id);
    }
    
}
