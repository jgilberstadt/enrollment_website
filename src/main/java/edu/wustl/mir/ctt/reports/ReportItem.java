package edu.wustl.mir.ctt.reports;

import edu.wustl.mir.ctt.model.*;
import edu.wustl.mir.ctt.form.BasicForm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model the events that occur to a participant during the clinical trial.
 * 
 * Participant id, name, label uniquely identify an event.
 * 
 * Not all events are 'expected' (scheduled) events, but we keep it all in one
 * class to ease the mapping to the DB. Only expected events can have 
 * non-null projectedDate values
 * 
 * @author drm
 */
public class ReportItem implements Serializable {
    // The following variables are called member variables in the class or fields.
    private String name;
    private Integer count;
    private String siteName;
    private Integer EPIArmCount;
    private Integer controlArmCount;
    private Integer totalCount;
    private Integer crossedOverCount;
    private Integer janCount;
    private Integer febCount;
    private Integer marCount;
    private Integer aprCount;
    private Integer mayCount;
    private Integer junCount;
    private Integer julCount;
    private Integer augCount;
    private Integer sepCount;
    private Integer octCount;
    private Integer novCount;
    private Integer decCount;


    // Constructor
    public ReportItem() {
        name = null;
        count = 0;
        siteName = null;
        EPIArmCount = 0;
        controlArmCount = 0;
        totalCount = 0;
        crossedOverCount = 0;
        janCount = 0;
        febCount = 0;
        marCount = 0;
        aprCount = 0;
        mayCount = 0;
        junCount = 0;
        julCount = 0;
        augCount = 0;
        sepCount = 0;
        octCount = 0;
        novCount = 0;
        decCount = 0;
    }
    
     public ReportItem(String reportItemName, int reportItemCount, String reportItemSiteName) {
        this.name = reportItemName;
        this.count = reportItemCount;
        this.siteName = reportItemSiteName;
    }
    
   public String getReportItemName() {
        return name;
    }

    public void setReportItemName(String reportItemName) {
        this.name = reportItemName;
    }

    public Integer getReportItemCount() {
        return count;
    }

    public void setReportItemCount(Integer reportItemCount) {
        this.count = reportItemCount;
    }

    public String getReportItemSiteName() {
        return siteName;
    }

    public void setReportItemSiteName(String reportItemSiteName) {
        this.siteName = reportItemSiteName;
    }

    public Integer getReportItemEPIArmCount() {
        return EPIArmCount;
    }

    public void setReportItemEPIArmCount(Integer reportItemEPIArmCount) {
        this.EPIArmCount = reportItemEPIArmCount;
    }

    public Integer getReportItemControlArmCount() {
        return controlArmCount;
    }

    public void setReportItemControlArmCount(Integer reportItemControlArmCount) {
        this.controlArmCount = reportItemControlArmCount;
    }

    public Integer getReportItemTotalCount() {
        totalCount = EPIArmCount + controlArmCount + crossedOverCount;
        return totalCount;
    }

    public void setReportItemTotalCount(Integer reportItemTotalCount) {
        this.totalCount = reportItemTotalCount;
    }

    public Integer getReportItemCrossedOverCount() {
        return crossedOverCount;
    }

    public void setReportItemCrossedOverCount(Integer reportItemCrossedOverCount) {
        this.crossedOverCount = reportItemCrossedOverCount;
    }

    public Integer getReportItemJanCount() {
        return janCount;
    }

    public void setReportItemJanCount(Integer reportItemJanCount) {
        this.janCount = reportItemJanCount;
    }

    public Integer getReportItemFebCount() {
        return febCount;
    }

    public void setReportItemFebCount(Integer reportItemFebCount) {
        this.febCount = reportItemFebCount;
    }

    public Integer getReportItemMarCount() {
        return marCount;
    }

    public void setReportItemMarCount(Integer reportItemMarCount) {
        this.marCount = reportItemMarCount;
    }

    public Integer getReportItemAprCount() {
        return aprCount;
    }

    public void setReportItemAprCount(Integer reportItemAprCount) {
        this.aprCount = reportItemAprCount;
    }

    public Integer getReportItemMayCount() {
        return mayCount;
    }

    public void setReportItemMayCount(Integer reportItemMayCount) {
        this.mayCount = reportItemMayCount;
    }

}
