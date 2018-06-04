package edu.wustl.mir.ctt;

import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.ReportPersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import edu.wustl.mir.ctt.reports.ReportItem;
import edu.wustl.mir.ctt.reports.ReportItems;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;


@ManagedBean
@SessionScoped
public class ReportsController implements Serializable {

    private ReportItems reportItemsForEpiArm;
    private ReportItems reportItemsForControlArm;
    // The dataModel cannot be serialized and saved to a hard drive because a NotSerializableException error occurs, 
    // so the dataModel is saved in the ReportItems.java code. The ReportItems.java code is not serialized.
    private transient DataModel dataModelEpiArm;  // The transient causes the dataModelEpi to not be stored so you do not get a NotSerializableException error.
                                                        // But you need to do lazy loading for the getDataModelEpi() method below.
    private transient DataModel dataModelControlArm;  // The transient causes the dataModelEcpControl to not be stored so you do not get a NotSerializableException error.
                                                        // But you need to do lazy loading for the getDataModelEcpControl() method below.
    private ArrayList<String> columnNames;
    
    // Constructor
    public ReportsController() {
        
    }

    public ReportItems getReportItemsForEpiArm(){
        return reportItemsForEpiArm;
    }
    
    public ReportItems getReportItemsForControlArm(){
        return reportItemsForControlArm;
    }
    
    public String keyContacts() {
        return "/keyContacts.xhtml?faces-redirect=true";
    }
    
    public String supportStaff() {
        return "/supportStaff.xhtml?faces-redirect=true";
    }
/*    
    public void viewEvent( ActionEvent ae) {
        String eventId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedEventId");
        System.out.println( eventId);
    }
*/    
    public String editSiteAction() {
        return "/editSite.xhtml?faces-redirect=true";
    }
    
    public String clearFormAction() {
        return null;
    }
    
      
    public String homePage(){
        System.out.println("The home page button was selected!!");
        return "main.xhtml?faces-redirect=true";
    }
    
    public Date getCurrentDate(){
        Date currentDate = new Date();
        return currentDate;
    }
    
    // The getDataModelEpiArm() method is used by the monthlyAccrualReport.xhtml code to dynamically display varying numbers of columns in the table.
    public DataModel getDataModelEpiArm() throws PersistenceException {
        // The following three lines of code are needed for lazy loading if the dataModelEpi is transient.
        // Otherwise, if the dataModel is not transient, comment out these three lines.
        if(this.dataModelEpiArm == null){
//            this.dataModel = this.getAccrualBySiteByMonthReportItems();  // This line of code works also, but it runs the getAccrualBySiteByMonthReportItems() again which is not necessary.
            this.dataModelEpiArm = this.getReportItemsForEpiArm().getDataModel();
        }
            return dataModelEpiArm;
    }

    public void setDataModelEpiArm(DataModel dataModelEpiArm){
        this.dataModelEpiArm = dataModelEpiArm;
    }

    // The getDataModelControlArm() method is used by the monthlyAccrualReport.xhtml code to dynamically display varying numbers of columns in the table.
    public DataModel getDataModelControlArm() throws PersistenceException {
        // The following three lines of code are needed for lazy loading if the dataModelEcpControl is transient.
        // Otherwise, if the dataModel is not transient, comment out these three lines.
        if(this.dataModelControlArm == null){
//            this.dataModel = this.getAccrualBySiteByMonthReportItems();  // This line of code works also, but it runs the getAccrualBySiteByMonthReportItems() again which is not necessary.
            this.dataModelControlArm = this.getReportItemsForControlArm().getDataModel();
        }
            return dataModelControlArm;
    }

    public void setDataModelEcpControl(DataModel dataModelEcpControl){
        this.dataModelControlArm = dataModelEcpControl;
    }

    public ArrayList<String> getColumnNames() {
            return columnNames;
    }

    public void setColumnNames(ArrayList<String> columnNames) {
            this.columnNames = columnNames;
    }


    /**
     * Indicate if the ECP Report Menu items can be rendered if you are a technical coordinator.
     * 
     * True if you are logged in as a technical coordinator.
     * 
     * @return 
     */
    public boolean renderECPReportMenuItems() {
        return SecurityManager.canVerifyForms();
    }
    
    // Return the newly created totalAccuralReport.xhtml to the browswer for display. 
    // NOTE: the data within the totalAccrualReport.xhtml is created by each method call within the xhtml code.
    public String TotalAccrualReportAction() throws PersistenceException {
        // Remove the comment from the get method below if you want to display Table 4 in the totalAccrualReport.xhtml code.
        // The following method is used to show how the dataModel and columnNames are created from the raw database resultset for use in the Primefaces
        // dynamic column names dataTable code.
//        this.dataModel = this.getAccrualBySiteByMonthRawDatabaseRetrievalReportItems();
//// The following this.createParticipantFirstPaymentStatus method was added to start testing the First Payment Status software.
////        this.createParticipantFirstPaymentStatus();
        return "/totalAccrualReport.xhtml?faces-redirect=true";
    }
    
    // The MonthlyAccrualReportAction() method is called from the template.xhtml sub-menu "Monthly Accrual Report" under the main menu "ECP Reports".
    // Return the newly created monthlyAccuralReport.xhtml to the browswer for display, but the dataModelEpi, dataModelControl and columnNames need to be created first. 
    // The dataModel and columnNames used in the monthlyAccrualReport.xhtml are created by the createAccrualBySiteByMonthReportItems() method.
    public String MonthlyAccrualReportAction() throws PersistenceException {
        this.createAccrualBySiteByMonthReportItems();
        return "/monthlyAccrualReport.xhtml?faces-redirect=true";
    }
    
    public List<ReportItem> getAccrualReportItems() throws PersistenceException {
        List<ReportItem> totalAccruals = new ArrayList<>(5);
        ReportPersistenceManager rpm = ServiceRegistry.getReportPersistenceManager();
        totalAccruals = rpm.getTotalAccruals();

        return totalAccruals;
    }
    
    public List<ReportItem> getAccrualLast30DaysReportItems() throws PersistenceException {
        List<ReportItem> totalAccrualsLast30Days = new ArrayList<>(5);
        ReportPersistenceManager rpm = ServiceRegistry.getReportPersistenceManager();
        totalAccrualsLast30Days = rpm.getTotalAccrualsLast30Days();

        return totalAccrualsLast30Days;
    }
    
    public List<ReportItem> getAccrualBySiteReportItems() throws PersistenceException {
        List<ReportItem> totalAccrualsBySite = new ArrayList<>(5);
        ReportPersistenceManager rpm = ServiceRegistry.getReportPersistenceManager();
        totalAccrualsBySite = rpm.getTotalAccrualsBySite();

        return totalAccrualsBySite;
    }
    
    // Create the dataModelEpi and columNames and save them here in the ReportsController to be called by the totalAccrualReport.xhtml Primefaces Table 4 making code. 
    // Table 4 is currently commented out in the totalAccrualReport.xhtml code.
    public DataModel getAccrualBySiteByMonthRawDatabaseRetrievalReportItems() throws PersistenceException {
        ReportItems reportItems = new ReportItems();
        ReportPersistenceManager rpm = ServiceRegistry.getReportPersistenceManager();
        reportItems = rpm.getTotalAccrualsBySiteByMonthRawDatabaseRetrieval();
        this.dataModelEpiArm = reportItems.getDataModel();
        this.columnNames = reportItems.getColumnNames();
        return reportItems.getDataModel();
    }

    // The createAccrualBySiteByMonthReportItems method is used to create the dataModelEpi and columNames.
    // It saves both of them here in the ReportsController to be called by the monthlyAccrualReport.xhtml Primefaces table making code. 
    public void createAccrualBySiteByMonthReportItems() throws PersistenceException {
//        ReportItems reportItems = new ReportItems();
        ReportPersistenceManager rpm = ServiceRegistry.getReportPersistenceManager();  // Get the ReportPersistenceManager.
        // Use the ReportPersistenceManager getTotalAccrualsBySiteByMonth() method to create the dataModel and columnNames for the Primefaces dataTable.
        ReportItems reportItems = rpm.getTotalAccrualsBySiteByMonth("epi_arm");
        // Save the dataModelEpi and columnNames locally after creation.
        this.dataModelEpiArm = reportItems.getDataModel();
        this.columnNames = reportItems.getColumnNames();

        // Use the ReportPersistenceManager getTotalAccrualsBySiteByMonth() method to create the dataModel and columnNames for the Primefaces dataTable.
        reportItems = rpm.getTotalAccrualsBySiteByMonth("control_arm");
        // Save the dataModelEcpControl locally after creation.
        this.dataModelControlArm = reportItems.getDataModel();

    }

    public String createParticipantFirstPaymentStatus() throws PersistenceException {
        
        Integer formCount = 0;
        int siteid;
        String pid;
        String eventName;
        
        ReportPersistenceManager rpm = ServiceRegistry.getReportPersistenceManager();
        
        siteid = 2;
        pid = "101001";
        eventName = "Confirmation of Eligibility";
        
        formCount = rpm.getParticipantFirstPaymentStatus(siteid, pid, eventName);
        System.out.println("The form count for event named " + eventName + " is: " + formCount + "\n");

        eventName = "ECP Treatment 1";
        
        formCount = rpm.getParticipantFirstPaymentStatus(siteid, pid, eventName);
        System.out.println("The form count for event named " + eventName + " is: " + formCount + "\n");

        return formCount.toString();
    }

}
