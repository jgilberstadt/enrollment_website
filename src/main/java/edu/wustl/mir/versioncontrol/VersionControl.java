/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wustl.mir.versioncontrol;

import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Paul K. Commean
 */
@ManagedBean
@SessionScoped
public class VersionControl implements Serializable {
    
    private Date protocolDate;
    private String protocolVersionNumber;
    private Date crfReleaseDate;
    private Date crfUpdate;
    private String crfVersionNumber;
    private Date softwareReleaseDate;
    private String softwareVersionNumber;
    private int versionControlId;

    
    //-------------------------------------------------------------------------------------------------------------------------------
    // The version control was added to keep track of the protcol changes, crf changes, and the software release changes.
    // These changes have an affect of the (basic) form(s) (ie. CRFs) database table and the version control table.  
    // Anytime one of the seven variables below is changed, a new copy of all seven variables are placed into the versionControl
    // table in the database.  .  As of now, the CCC wants all of the CRFs to have the same
    // version number on each (basic) form even if only one form (CRF) changes.
    // 
    // The electronic version of the CRFs have printed at the bottom of each the CRF Version Number and the Protocol Date.  
    // The formTemplate.xhtml has these the CRF Version Number and the Protocol Date in it.  As of 7/16/2015, the CCC does 
    // not want the CRF Version number and Protocol Date to change except when a protocol change occurs. 
    // All of the changes we have made to date are administrative not requiring a change in the CRF or protocol.
    // My current understanding of what the CCC wants is to change the CRF Version Number when the protocol changes.
    //
    // The crfUpdate and the software variable listed below deal with changes in the software.  Anytime a new software version
    // is released to ECP Registry Production server, the softwareReleaseDate and softwareVersion number should be changed.
    // Changing the crfUpdate date depends on whether any changes occurred that affect the CRFs directory.
    //
    //
    // Version Control Variables Explained
    //
    // protocolDate - the date should only be changed with a new version of the protocol has been approved by WashU and the CCC.
    // protocolVersionNumber - the version number should only be changed with a new version of the protocol has been approved by WashU and the CCC.
    //
    // crfReleaseDate - ideally the date should only be changed with a new version of the protocol has been approved by WashU and the CCC,
    //                  but the date could be changed if the CCC decided a change in a CRF is not administrative and a new protocol version
    //                  has not been release.  The CCC needs to approve when this date will be changed.
    // crfUpdate - The date of this variable should agree with the softwareReleaseDate only if the changes in the software directly
    //             affects the CRF.  For example, if the wording in a CRF changes, or the behavior of the CRF changes such as in the case
    //             of the V1.2.11 softwareVersionNumber.  For V1.2.11, a new SAE was created by the Add Event button, the SAE Event Date was 
    //             displaying the event actual date and not the form onsetDate.  I changed the code to pass the form onset date to the Event Date.
    // crfVersionNumber - ideally the version number should only be changed with a new version of the protocol has been approved by WashU and 
    //                  the CCC, but the version number could be changed if the CCC decided a change in a CRF is not administrative and a new  
    //                  protocol version has not been release.  The CCC needs to approve when this version number will be changed.
    // softwareReleaseDate - Anytime a new software version is released to ECP Registry Production server, the softwareReleaseDate 
    //                      should be changed.
    // softwareVersionNumber - Anytime a new software version is released to ECP Registry Production server, the softwareVersionNumber 
    //                      should be changed.
    //
    // versioncontrolid - is the id number in the software versioncontrol table.  Each time a new form is created, the version control id is
    //                written into the basicform table in order to know what version the form was created under.
    //-------------------------------------------------------------------------------------------------------------------------------

    public VersionControl() throws ParseException, PersistenceException {
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        this.protocolDate = sdf.parse("02/28/2018");
//        System.out.println("The protocolDate is: " + protocolDate.toString());
        this.protocolVersionNumber = "7.1";
        this.crfReleaseDate = sdf.parse("02/28/2018");
        this.crfUpdate = sdf.parse("04/25/2018");
        this.crfVersionNumber = "7.1";
        this.softwareReleaseDate = sdf.parse("04/25/2018");
        this.softwareVersionNumber = "v7.1.0";
        
        // Get the software version control id if it has not changed.  versionControlID will return a zero (0) if the version information above is not in the database.
        versionControlId = pm.getVersionControlId(protocolDate, protocolVersionNumber, crfReleaseDate, crfUpdate, crfVersionNumber, softwareReleaseDate, softwareVersionNumber);
        if(versionControlId == 0){
            // Insert the new software version into the database versionControl table.
            pm.insertVersionControlId(protocolDate, protocolVersionNumber, crfReleaseDate, crfUpdate, crfVersionNumber, softwareReleaseDate, softwareVersionNumber);
            // Get the new version control id number.
            // The versionControlId is used in the basicform table to 
            versionControlId = pm.getVersionControlId(protocolDate, protocolVersionNumber, crfReleaseDate, crfUpdate, crfVersionNumber, softwareReleaseDate, softwareVersionNumber);
        }
    }


    public Date getProtocolDate() {
        return this.protocolDate;
    }
    
    public void setProtocolDate(Date protocolDate) {
        this.protocolDate = protocolDate;
    }
    
    public String getProtocolVersionNumber() {
        return this.protocolVersionNumber;
    }
    
    public void setProtocolVersionNumber(String protocolVersionNumber) {
        this.protocolVersionNumber = protocolVersionNumber;
    }
    
    public Date getCRFReleaseDate() {
        return this.crfReleaseDate;
    }
    
    public void setCRFReleaseDate(Date crfReleaseDate) {
        this.crfReleaseDate = crfReleaseDate;
    }
    
    public Date getCRFUpdate() {
        return this.crfUpdate;
    }
    
    public void setCRFUpdate(Date crfUpdate) {
        this.crfUpdate = crfUpdate;
    }
    
    public String getCrfVersionNumber() {
        return this.crfVersionNumber;
    }
    
    public void setCrfVersionNumber(String crfVersionNumber) {
        this.crfVersionNumber = crfVersionNumber;
    }
    
    public Date getSoftwareReleaseDate() {
        return this.softwareReleaseDate;
    }
    
    public void setSoftwareReleaseDate(Date softwareReleaseDate) {
        this.softwareReleaseDate = softwareReleaseDate;
    }
    
    public String getSoftwareVersionNumber() {
        return this.softwareVersionNumber;
    }
    
    public void setSoftwareVersionNumber(String softwareVersionNumber) {
        this.softwareVersionNumber = softwareVersionNumber;
    }
    
    public int getVersionControlId() throws PersistenceException {
        return this.versionControlId;
    }
}
