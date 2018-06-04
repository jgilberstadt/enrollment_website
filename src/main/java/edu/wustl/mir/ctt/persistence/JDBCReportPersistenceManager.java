package edu.wustl.mir.ctt.persistence;

import edu.wustl.mir.ctt.auditlog.AuditLogException;
import edu.wustl.mir.ctt.auditlog.AuditLogManager;
import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.BaselineTherapyForm;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.ChangeTherapyForm;
import edu.wustl.mir.ctt.form.CrossoverSafetyCheckForm;
import edu.wustl.mir.ctt.form.DemoMedHistForm;
import edu.wustl.mir.ctt.form.ECPTreatmentForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EndOfStudyForm;
import edu.wustl.mir.ctt.form.ObservePulmEvalLogForm;
import edu.wustl.mir.ctt.form.PulmEvalForm;
import edu.wustl.mir.ctt.form.SimpleEligibilityForm;
import edu.wustl.mir.ctt.form.SimpleForm;
import edu.wustl.mir.ctt.model.Attribute;
import edu.wustl.mir.ctt.model.AttributeBoolean;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeInteger;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.AttributeTime;
import edu.wustl.mir.ctt.model.AttributeTimestamp;
import edu.wustl.mir.ctt.model.AttributeValueType;
import edu.wustl.mir.ctt.model.Document;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.EventStatus;
import edu.wustl.mir.ctt.model.FormStatus;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.model.ParticipantStatus;
import edu.wustl.mir.ctt.model.Site;
import edu.wustl.mir.ctt.model.SiteStatus;
import edu.wustl.mir.ctt.model.StudyArmStatus;
import edu.wustl.mir.ctt.model.VerificationStatus;
import edu.wustl.mir.ctt.reports.ReportItem;
import edu.wustl.mir.ctt.reports.ReportItems;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
//import org.apache.shiro.SecurityUtils;
//import org.apache.tomcat.dbcp.dbcp.DelegatingConnection;
//import org.postgresql.largeobject.LargeObject;
//import org.postgresql.largeobject.LargeObjectManager;

/**
 * A persistence manager using JDBC to a RDMS data base.
 * 
 * This implementation assumes Postgresql due to its use of custom BLOB 
 * interface to store documents.
 * 
 * @author drm
 */
public class JDBCReportPersistenceManager implements ReportPersistenceManager {

    private DataSource ds;
    private Logger logger;

    /**
     * Use the Web Container to manage the DataSource.
     *
     * @throws PersistenceException
     */
    public JDBCReportPersistenceManager() throws PersistenceException {
        try {
            logger = Logger.getLogger(JDBCReportPersistenceManager.class);

            InitialContext ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ecpreg_db");
            if (ds == null) {
                String msg = "JDBCReportPersistenceManager failed to find DataSource: jdbc/ecpreg_db"; // Production Mode
                logger.error(msg);
                throw new PersistenceException(msg);
            }
            logger.info("Instantiated datasource: jdbc/ecpreg_db");
        } catch (NamingException e) {
            String msg = "JDBCReportPersistenceManager failed to create Naming Context: java:comp/env";
            logger.error(msg, e);
            throw new PersistenceException(msg, e);
        }
    }
    
    @Override
    public List<Site> getSites() throws PersistenceException {
        Connection conn = null;
        String sql = "select id, siteId, name, status from site order by id;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Get sites.");
        
        try {
            List<Site> sites = new ArrayList<Site>();
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Site site = new Site();
                site.setId( rs.getInt("id"));
                site.setSiteID(rs.getInt("siteId"));
                site.setName( rs.getString("name"));
                site.setStatus( SiteStatus.getStatus( rs.getString("status")));
                
                sites.add(site);
            }
            
            return sites;
        } catch (SQLException ex) {
            logger.error("Error selecting sites.", ex);
            throw new PersistenceException("Error selecting sites.", ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }

    }
	
    @Override
    public Site getSite( int id) throws PersistenceException {
        Connection conn = null;
        String sql = "select * from site where id = ?;";
        System.out.println("The site id is: " + id + "\n\n");
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Get site with id = " + id);
        
        try {
            Site site = new Site();
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                site.setId( rs.getInt("id"));
                site.setSiteID(rs.getInt("siteid"));
                site.setName( rs.getString("name"));
                site.setStatus( SiteStatus.getStatus( rs.getString("status")));
            }
            
            return site;
        } catch (SQLException ex) {
            String msg = "Error selecting site with id = " + id;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
    @Override
    public Site getSite( String siteName) throws PersistenceException {
        Connection conn = null;
        String sql = "select * from site where name = ?;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Get site with name = " + siteName);
        
        try {
            Site site = new Site();
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, siteName);
            rs = ps.executeQuery();
            if (rs.next()) {
                site.setId( rs.getInt("id"));
                site.setSiteID(rs.getInt("siteId"));
                site.setName( rs.getString("name"));
                site.setStatus( SiteStatus.getStatus( rs.getString("status")));
            }
            
            return site;
        } catch (SQLException ex) {
            String msg = "Error selecting site with name = " + siteName;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
	
    @Override
    public List<Participant> getParticipants(Site site) throws PersistenceException {
        Connection conn = null;
        String sql = "select id, siteId, participantId, enrolledDate, status, studyarmenrolldate, studyarmstatus from participant where siteid = ? and showstudyarm = true order by id;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Get participants.");
        
        try {
            List<Participant> participants = new ArrayList<Participant>();
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, site.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Participant participant = new Participant();
                participant.setId( rs.getInt("id"));
                participant.setSiteId(rs.getInt("siteId"));
                participant.setParticipantID( rs.getString("participantId"));
                participant.setEnrolledDate( rs.getDate("enrolledDate"));
                participant.setStatus( ParticipantStatus.getStatus( rs.getString("status")));
                participant.setStudyArmEnrollDate(rs.getDate("studyarmenrolldate"));
                participant.setStudyArmStatus(StudyArmStatus.valueOf( rs.getString("studyarmstatus")));
                
                participants.add(participant);
            }
            
            return participants;
        } catch (SQLException ex) {
            logger.error("Error selecting participants for site id = " + site.getId(), ex);
            throw new PersistenceException("Error selecting participants for site id = " + site.getId(), ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }

    }
	
    @Override
    public int getEnrolledParticipantsCount( int siteId) throws PersistenceException {
        Connection conn = null;
        String sql = "select count(*) from participant where siteid = ? and showstudyarm = true;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Get enrolled participants count for site where siteid =" + siteId);
        
        try {
            int count = -1;
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, siteId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
            
            return count;
        } catch (SQLException ex) {
            logger.error("Error selecting enrolled participants count for site id =" + siteId, ex);
            throw new PersistenceException("Error selecting enrolled participants count for site id =" + siteId, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }

    }
	
    
    
    
    
    
            
    /**
     * DB is constrained to have one event per (pid, title, label).
     * 
     * @param pid
     * @param eventTitle
     * @return
     * @throws PersistenceException 
     */
    @Override
    public Event getEvent(String pid, String eventTitle, String label) throws PersistenceException {
        Connection conn = null;
        String sql = "select e.id, e.participantId, e.eventType, e.name, e.label, e.status, e.expected, e.baseDate, e.baseDateOffset, e.firstDateOffset, e.lastDateOffset, e.actualDate, e.showBaseDate, e.showFirstAvailDate, e.showLastAvailDate from participant p, event e where p.id = e.participantId and p.participantId = ? and e.name = ? and e.label = ?;";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Get event for pid " + pid + ", title = " + eventTitle + " and label " + label);

        try {
            Event event = null;
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pid);
            ps.setString(2, eventTitle);
            ps.setString(3, label);
            rs = ps.executeQuery();

            if (rs.next()) {
                event = new Event();
//                System.out.println("The JDBCPersistenceManager.getEvents method for rs.getInt(id) is: " + rs.getInt("id"));
                event.setId( rs.getInt("id"));
                event.setParticipantId( rs.getInt("participantId"));
                event.setType( ECPEventTypes.valueOf(rs.getString("eventType")));
//                System.out.println("The JDBCPersistenceManager.getEvents method for rs.getString(name) is: " + rs.getString("name"));
                event.setName( rs.getString("name"));
                event.setLabel( rs.getString("label"));
                event.setStatus( EventStatus.valueOf( rs.getString("status")));
                event.setExpected( rs.getBoolean("expected"));
                event.setBaseDate( rs.getDate("baseDate"));
                event.setOffsetFromBaseDateInDays( rs.getInt("baseDateOffset"));
                event.setOffsetToFirstAvailableDayInDays( rs.getInt("firstDateOffset"));
                event.setOffsetToLastAvailableDayInDays( rs.getInt("lastDateOffset"));
//                System.out.println("The JDBCPersistenceManager.getEvents method for rs.getDate(actualDate) is: " + rs.getDate("actualDate"));
                event.setActualDate( rs.getDate("actualDate"));
                event.setShowBaseDate( rs.getBoolean("showBaseDate"));
                event.setShowFirstAvaiableDate( rs.getBoolean("showFirstAvailDate"));
                event.setShowLastAvailableDate( rs.getBoolean("showLastAvailDate"));
            }

            return event;
        } catch (SQLException ex) {
            String msg = "Error selecting event for pid = " + pid + ", title = " + eventTitle + " and label " + label;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    	
    @Override
    public List<BasicForm> getForms( Event e) throws PersistenceException {

        Connection conn = null;
        String sql = "select id, eventId, formType, title, creationDate, status, sequencenumber, lastsubmitteddate from basicForm where eventId = ? order by creationdate, sequencenumber;";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Get basicForms for event = " + e);

        try {
            List<BasicForm> forms = new ArrayList<BasicForm>();
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                BasicForm form = new BasicForm();
                form.setId( rs.getInt("id"));
//                form.setType( rs.getString("formType"));
                form.setFormType( ECPFormTypes.valueOf( rs.getString("formType")));
                form.setTitle( rs.getString("title"));
                form.setEventId( rs.getInt("eventId"));
                form.setDate(rs.getDate("creationDate"));
                form.setStatus( FormStatus.valueOf(rs.getString("status")));
                form.setSequenceNumber( rs.getInt("sequenceNumber"));
                form.setLastSubmittedDate(rs.getTimestamp("lastSubmittedDate"));
                
                forms.add(form);
            }

            return forms;
        } catch (SQLException ex) {
            logger.error("Error selecting simpleForms for event = " + e, ex);
            throw new PersistenceException("Error selecting simpleForms for event = " + e, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    
    public BasicForm getBasicForm( int formId) throws PersistenceException {

        Connection conn = null;
        String sql = "select id, eventId, formType, title, creationDate, status, sequenceNumber, lastsubmitteddate from basicForm where id = ?;";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Get basicForm with id = " + formId);

        try {
            BasicForm form = null;
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, formId);
            rs = ps.executeQuery();
            if (rs.next()) {
                form = new BasicForm();
                form.setId( rs.getInt("id"));
                form.setFormType( ECPFormTypes.valueOf(rs.getString("formType")));
                form.setTitle( rs.getString("title"));
                form.setEventId( rs.getInt("eventId"));
                form.setDate( rs.getDate("creationDate"));
                form.setStatus( FormStatus.valueOf(rs.getString("status")));
                form.setSequenceNumber( rs.getInt("sequenceNumber"));
                form.setLastSubmittedDate(rs.getDate("lastSubmittedDate"));
            }

            return form;
        } catch (SQLException ex) {
            logger.error("Error selecting basicForm with id = " + formId, ex);
            throw new PersistenceException("Error selecting basicForm with id = " + formId, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    

    public BasicForm getForm( BasicForm form) throws PersistenceException {

        Connection conn = null;
        String sql = "select * from formAttributes where formid = ?;";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Get Form with id = " + form.getId());

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, form.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Attribute a;
                String type = rs.getString("valueType");
                if( AttributeValueType.STRING.getType().equals(type)) {
                    a = new AttributeString();
                    a.setValue( rs.getString("stringValue"));
                }
                else if( AttributeValueType.INTEGER.getType().equals(type)) {
                    a = new AttributeInteger();
                    // If the value is SQL NULL, the value returned is zero.
                    // The value in the database is SQL NULL which is set in the insertForm method above.
//                    System.out.println("The interger value is: " + rs.getInt("intValue") + "\n\n");
                    Integer i = rs.getInt("intValue");
                    // If the returned value from the database is null, don't use the 0 value but make the integer value null
                    // so the text boxes located in the forms are blank and do not contain a zero value.
                    if(rs.wasNull()){
                        i = null;
                    }
                    a.setValue( i);
//                    a.setValue( rs.getInt("intValue"));
                }
                else if( AttributeValueType.FLOAT.getType().equals(type)) {
                    a = new AttributeFloat();
                    // If the value is SQL NULL, the value returned is zero (0.0).
                    // The value in the database is SQL NULL which is set in the insertForm method above.
//                    System.out.println("The float value is: " + rs.getFloat("floatValue") + "\n\n");
                    Float f = rs.getFloat("floatValue");
                    // If the returned value from the database is null, don't use the 0.0 value but make the float value null.
                    // so the text boxes located in the forms are blank and do not contain a zero value.
                    if(rs.wasNull()){
                        f = null;
                        
    // NOTE: The following two lines of code are needed for Paul K. Commean's Rehab3 computer to remove zeros from float variables found in the COE.
////                    } else if (0.0f == f) {
////                        f = null;
                    }
                    a.setValue( f);
//                    a.setValue( rs.getFloat("floatValue"));
                }
                else if( AttributeValueType.DATE.getType().equals(type)) {
                    a = new AttributeDate();
                    a.setValue( rs.getTimestamp( "dateValue"));
                }
                else if( AttributeValueType.TIME.getType().equals(type)) {
                    a = new AttributeTime();
                    a.setValue( rs.getTimestamp( "timeValue"));
                }
                else if( AttributeValueType.TIMESTAMP.getType().equals(type)) {
                    a = new AttributeTimestamp();
                    a.setValue( rs.getTimestamp( "timestampValue"));
                }
                else if( AttributeValueType.BOOLEAN.getType().equals(type)) {
                    a = new AttributeBoolean();
                    a.setValue( rs.getBoolean( "booleanValue"));
                }
                else {
                    throw new PersistenceException("Unknown attribute valueType (" + type + ") for form with id = " + form.getId());
                }
                a.setId( rs.getInt("id"));
                a.setName( rs.getString("name"));
                a.setVerify( rs.getBoolean("verify"));
                a.setOptional( rs.getBoolean("optional"));
                a.setVerificationStatus(VerificationStatus.valueOf(rs.getString("verificationstatus")));
                a.setDccComment(rs.getString("dcccomment"));
                form.addAttribute( a.getName(), a);
            }

            return form;
        } catch (SQLException ex) {
            logger.error("Error selecting simpleForm with id = " + form.getId(), ex);
            throw new PersistenceException("Error selecting simpleForms with id = " + form.getId(), ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    
    @Override
    public List<String> getAllParticipantIDs(Site s) throws PersistenceException {

        Connection conn = null;
        String sql = "select participantid from participant where siteid = ? order by participantid desc;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        List<String> pids = new ArrayList<String>();

        logger.debug("Get all pids for site  id = " + s.getId());

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, s.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                pids.add( rs.getString("participantId"));
            }

            return pids;
        } catch (Exception ex) {
            String msg = "Error selecting all pids for site with id = " + s.getId();
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }


    @Override
    public int getEventCount( Participant p, EventStatus status) throws PersistenceException {

        Connection conn = null;
        String sql = "select count(status) from event where participantid = ? and status = ?;";
        PreparedStatement ps = null;
        ResultSet rs;
        int count = 0;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, status.name());
            
            rs = ps.executeQuery();
            if( rs.next()) {
                count = rs.getInt("count");
            }
            else {
                logger.warn("Failed to find event counts for participant id = " + p.getId());
            }
            
            return count;
        
        } catch (Exception ex) {
            String msg = "Error finding event counts for participant id = " + p.getId();
            logger.error( msg, ex);
            throw new PersistenceException( msg, ex);
        } finally {
            if( ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public int getEventCount( Site s, EventStatus status) throws PersistenceException {

        Connection conn = null;
        String sql = "select count(e.status) from event e, site s, participant p where s.id = ? and p.siteid = s.id and e.participantid = p.id and e.status = ?;";
        PreparedStatement ps = null;
        ResultSet rs;
        int count = 0;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, s.getId());
            ps.setString(2, status.name());
            
            rs = ps.executeQuery();
            if( rs.next()) {
                count = rs.getInt("count");
            }
            else {
                logger.warn("Failed to find event counts for site id = " + s.getId());
            }
            
            return count;
        
        } catch (Exception ex) {
            String msg = "Error finding event counts for site id = " + s.getId();
            logger.error( msg, ex);
            throw new PersistenceException( msg, ex);
        } finally {
            if( ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    /**************************************************************************************************
     * Get the number of events of a particular type for a participant from the database.
     * The returned number is used to add a number to the end of the Event Title shown in the
     * Participant Summary page under "Event Title".
     * @param p
     * @param e
     * @return
     * @throws PersistenceException 
     */
    @Override
    public int getEventTypeCount( Participant p, Event e) throws PersistenceException {

        Connection conn = null;
        String sql = "select count(e.eventtype) from event e, participant p where p.id = ? and e.participantid = p.id and e.eventtype = ?;";
        PreparedStatement ps = null;
        ResultSet rs;
        int count = 0;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, e.getType().toString());
            
            System.out.println("The getEventTypeCount sql statement is : " + ps.toString());
            
            rs = ps.executeQuery();
            if( rs.next()) {
                count = rs.getInt("count");
            }
            else {
                logger.warn("Failed to find eventtype counts for participant id = " + p.getId());
            }
            
            return count;
        
        } catch (Exception ex) {
            String msg = "Error finding eventtype counts for participant id = " + p.getId();
            logger.error( msg, ex);
            throw new PersistenceException( msg, ex);
        } finally {
            if( ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    /**
     * return the participant's on-hold status.
     * 
     * @param pid
     * @return
     * @throws PersistenceException 
     */
    @Override
    public boolean isOnHold( String pid) throws PersistenceException {

        Connection conn = null;
        String sql = "select holdstatus from participant p where participantid = ?;";
        PreparedStatement ps = null;
        ResultSet rs;
        boolean b = false;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pid);
                        
            rs = ps.executeQuery();
            if( rs.next()) {
                b = rs.getBoolean("holdstatus");
            }
            else {
                logger.warn("Failed to find holdstatus for participant id = " + pid);
            }
            
            return b;
        
        } catch (Exception ex) {
            String msg = "Error finding holdstatus for participant id = " + pid;
            logger.error( msg, ex);
            throw new PersistenceException( msg, ex);
        } finally {
            if( ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    public void setOnHold( String pid, boolean onhold, Date holdStartDate) throws PersistenceException {
        
        Connection conn = null;
        String onhold_sql = "update participant set holdstatus=?, holdstartdate=? where participantid =?;";
        String notonhold_sql = "update participant set holdstatus=? where participantid =?;";
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Update participant " + pid + " to holdstatus = " + onhold);
        
        try {
            conn = ds.getConnection();
            if( onhold ) {
                ps = conn.prepareStatement(onhold_sql);
                ps.setBoolean(1, onhold);
                ps.setDate(2, new java.sql.Date(holdStartDate.getTime()));
                ps.setString(3, pid);
            }
            else {
                ps = conn.prepareStatement(notonhold_sql);
                ps.setBoolean(1, onhold);
                ps.setString(2, pid);
            }

            int i = ps.executeUpdate();
            if( i != 1) {
                throw new SQLException("Error updating participant " + pid + " to holdstatus = " + onhold);
            }
            
            return;
        } catch (Exception ex) {
            String msg = "Error updating participant " + pid + " to holdstatus = " + onhold;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }
	
    @Override
    public Participant getParticipant(String pid) throws PersistenceException {
        
        Connection conn = null;
        String sql = "select * from participant where participantid =?;";
        PreparedStatement ps = null;
        ResultSet rs;
        Participant p = null;
        
        logger.debug("Select participant with pid = " + pid);
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pid);

            rs = ps.executeQuery();
            if( rs.next()) {
                p = new Participant();
                p.setId( rs.getInt("id"));
                p.setSiteId( rs.getInt("siteid"));
                p.setParticipantID( rs.getString("participantid"));
                p.setEnrolledDate( rs.getDate("enrolleddate"));
                p.setStatus( ParticipantStatus.valueOf(rs.getString("status")));
                p.setStudyArmEnrollDate( rs.getDate("studyarmenrolldate"));
                p.setStudyArmStatus( StudyArmStatus.valueOf(rs.getString("studyarmstatus")));
                p.setHoldStatus( rs.getBoolean("holdstatus"));
                p.setHoldStartDate( rs.getDate("holdstartdate"));
            }
            else {
                logger.error("Failed to find participant with pid = " + pid);
            }
           
            return p;
        } catch (Exception ex) {
            String msg = "Failed to find participant with pid = " + pid;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }

    
    
    
    @Override
    public List<ReportItem> getTotalAccruals() throws PersistenceException {

        List<ReportItem> reportItems = new ArrayList<>();
        ReportItem reportItem = new ReportItem();

        Connection conn = null;
        String sql1 = "select count(participantid) as TotalAccrualEPIArm from participant where studyarmstatus like 'EPI_ARM' and siteid > 1 and participant.showstudyarm = true;";
        String sql2 = "select count(participantid) as TotalAccrualControlArm from participant where studyarmstatus = 'CONTROL_ARM' and siteid > 1 and participant.showstudyarm = true;";
        String sql3 = "select count(participantid) as TotalAccrualCrossedOver from participant where overridedate is not null and siteid > 1 and participant.showstudyarm = true;";
        String sql4 = "select count(participantid) as TotalAccrual from participant where siteid > 1 and participant.showstudyarm = true;";
//        String sql5 = "select count(participantid) as TotalAccrualControlArm from participant where studyarmstatus = 'CONTROL_ARM' or overridedate is not null;";

        PreparedStatement ps = null;
        ResultSet rs;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
                reportItem.setReportItemName( "EPI Arm");
                reportItem.setReportItemCount( rs.getInt("TotalAccrualEPIArm"));

                reportItems.add(reportItem);
            }

            ps = conn.prepareStatement(sql2);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
                reportItem.setReportItemName( "Control Arm");
                reportItem.setReportItemCount( rs.getInt("TotalAccrualControlArm"));

                reportItems.add(reportItem);
            }
            
            ps = conn.prepareStatement(sql3);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
                reportItem.setReportItemName( "CrossedOver");
                reportItem.setReportItemCount( rs.getInt("TotalAccrualCrossedOver"));

                reportItems.add(reportItem);
            }
            
            reportItem = new ReportItem();
            reportItem.setReportItemName("");
            reportItem.setReportItemCount(null);
            reportItems.add(reportItem);
            
            ps = conn.prepareStatement(sql4);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
//                System.out.println("The JDBCPersistenceManager.getEvents method for rs.getInt(id) is: " + rs.getInt("id"));
                reportItem.setReportItemName( "Total");
                reportItem.setReportItemCount( rs.getInt("TotalAccrual"));

                reportItems.add(reportItem);
            }
            
            return reportItems;
            
        } catch (SQLException ex) {
            logger.error("Error selecting reportItems.", ex);
            throw new PersistenceException("Error selecting reportItems.", ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    
    @Override
    public List<ReportItem> getTotalAccrualsLast30Days() throws PersistenceException {

        List<ReportItem> reportItems = new ArrayList<>();
        ReportItem reportItem = new ReportItem();

        Connection conn = null;
        // NOTE: The ECP Registry 'Test Site' is where siteid = 1 in the participant database table.  Looking for siteid > 1 elimiates the 'Test Site' from the results.
        String sql1 = "select count(participantid) as TotalAccrualLast30DaysEPIArm from participant where studyarmstatus like 'EPI_ARM' and enrolleddate > (CURRENT_DATE - integer '30') and siteid > 1 and showstudyarm = true;";
        String sql2 = "select count(participantid) as TotalAccrualLast30DaysControlArm from participant where studyarmstatus like 'CONTROL_ARM' and enrolleddate > (CURRENT_DATE - integer '30') and siteid > 1 and showstudyarm = true;";
        String sql3 = "select count(participantid) as TotalAccrualLast30Days from participant where enrolleddate > (CURRENT_DATE - integer '30') and siteid > 1 and showstudyarm = true;";
        String sql4 = "select count(participantid) as TotalAccrualLast30DaysCrossedOver from participant where overridedate is not null and enrolleddate > (CURRENT_DATE - integer '30') and siteid > 1 and showstudyarm = true;";
//        String sql5 = "select count(participantid) as TotalAccrualLast30DaysControlArm from participant where (studyarmstatus like 'CONTROL_ARM' and enrolleddate > (CURRENT_DATE - integer '30') and siteid > 1) or (overridedate is not null and enrolleddate > (CURRENT_DATE - integer '30') and siteid > 1);";

        PreparedStatement ps = null;
        ResultSet rs;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
                reportItem.setReportItemName( "EPI Arm");
                reportItem.setReportItemCount( rs.getInt("TotalAccrualLast30DaysEPIArm"));

                reportItems.add(reportItem);
            }

            ps = conn.prepareStatement(sql2);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
                reportItem.setReportItemName( "Control Arm");
                reportItem.setReportItemCount( rs.getInt("TotalAccrualLast30DaysControlArm"));

                reportItems.add(reportItem);
            }

            ps = conn.prepareStatement(sql3);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
//                System.out.println("The JDBCPersistenceManager.getEvents method for rs.getInt(id) is: " + rs.getInt("id"));
                reportItem.setReportItemName( "Total Last 30 Days");
                reportItem.setReportItemCount( rs.getInt("TotalAccrualLast30Days"));

                reportItems.add(reportItem);
            }
            
            reportItem = new ReportItem();
            reportItem.setReportItemName("");
            reportItem.setReportItemCount(null);
            reportItems.add(reportItem);
            
            ps = conn.prepareStatement(sql4);
            rs = ps.executeQuery();
            while (rs.next()) {
                reportItem = new ReportItem();
                reportItem.setReportItemName( "CrossedOver");
                reportItem.setReportItemCount( rs.getInt("TotalAccrualLast30DaysCrossedOver"));

                reportItems.add(reportItem);
            }

            return reportItems;
            
        } catch (SQLException ex) {
            logger.error("Error selecting reportItems.", ex);
            throw new PersistenceException("Error selecting reportItems.", ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    //***************************************************************************************************************
    // Currently, there are only two study arms in the ECP registry (EPI and Control).
    // The database sql command below returns at most two rows per site (one for each study arm if participants are
    // enrolled into both arms).
    // If a site only has participants in a single study arm, only one row is returned for ths site.
    //***************************************************************************************************************
    @Override
    public List<ReportItem> getTotalAccrualsBySite() throws PersistenceException {

        List<ReportItem> listOfReportItems = new ArrayList<>();
        ReportItem reportItem = new ReportItem();

        Connection conn = null;
        String sql = "select site.id, name, studyarmstatus, count(overridedate) as crossedOverCountBySiteByStudyArm, count(participantid) as TotalAccrualBySiteByStudyArm from site, participant where site.id = participant.siteid and site.id > 1 and participant.showstudyarm = true group by site.id, name, studyarmstatus order by site.id;";
        // The following sql statement will group by month
        // ecpreg_prod_db=# select date_trunc('month', enrolleddate) as month, count(siteid) as site from participant where enrolleddate > now() - interval '3 months' group by month order by month;
        // month                  | site 
        //------------------------+------
        // 2015-04-01 00:00:00-05 |    6
        // 2015-05-01 00:00:00-05 |    1
        
        // ecpreg_prod_db=# select extract('month' from enrolleddate) as month, count(siteid) from participant group by month order by month;
        // month | count 
        // -------+-------
        //     4 |     6
        //     5 |     1

        // ecpreg_prod_db=# select name, studyarmstatus, count(participantid) as Total, extract('month' from enrolleddate) as month from site, participant where site.id = participant.siteid group by name, studyarmstatus, month order by name, month;
        //   name    |  studyarmstatus   | total | month 
        //-----------+-------------------+-------+-------
        // Test Site | EPI_ARM           |     3 |     4
        // Test Site | CONTROL_ARM       |     2 |     4
        // Test Site | EPI_ARM           |     1 |     5
        // WashU     | CONTROL_ARM       |     1 |     4

        PreparedStatement ps = null;
        ResultSet rs;
        String previousSite = null;
        boolean rsNext = false;
        Integer totalCrossedOver = 0;

        try {
            conn = ds.getConnection();
            
            // Make the resultset scrollable so you can move backword in the result set and not just forward.
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.first(); // Move resultset cursor to the first row.
            previousSite = rs.getString("name"); // Initialize the previousSite with the site listed in the first row of the resultset.
            rs.beforeFirst(); // Move the resultset cursor before the first row.
            reportItem = new ReportItem();
//            System.out.println("\n\n");
            while (rs.next()) {
                if(previousSite.equals(rs.getString("name"))){
                    reportItem.setReportItemSiteName(rs.getString("name"));
                    //System.out.println("The Total Accrual Site Name is 1: " + reportItem.getReportItemSiteName());
                    if("EPI_ARM".equals(rs.getString("studyarmstatus"))){
                        reportItem.setReportItemEPIArmCount(rs.getInt("totalaccrualbysitebystudyarm"));
                        //reportItem.setReportItemCrossedOverCount(rs.getInt("crossedovercountbysitebystudyarm"));
                    } else if("CONTROL_ARM".equals(rs.getString("studyarmstatus"))) {
                        reportItem.setReportItemControlArmCount(rs.getInt("totalaccrualbysitebystudyarm"));
                    } else {
                        totalCrossedOver += rs.getInt("crossedovercountbysitebystudyarm");
                        reportItem.setReportItemCrossedOverCount(totalCrossedOver);
                    }
                    reportItem.setReportItemTotalCount( reportItem.getReportItemTotalCount());
                    //System.out.println("\nSite name 1: " + reportItem.getReportItemSiteName() + "   EPI Arm Count: " + reportItem.getReportItemEPIArmCount() + "   observation arm count: " + reportItem.getReportItemControlArmCount() + "   Total count: " + reportItem.getReportItemTotalCount());
                    previousSite = reportItem.getReportItemSiteName();
                    rsNext = rs.next();  // Move forward one row to determine you are at the end of the list or if the next row has a new site or old site listed.
                    if(rsNext != false){  // Check to see if you are past the last row in the list.
                        if(!previousSite.equals(rs.getString("name"))){ // Determine if a new site or old site is listed.  If new site is listed, save the reportItem.
                            //System.out.println("\nAdded to ReportItems 1: Site name " + reportItem.getReportItemSiteName() + " EPI Arm Count " + reportItem.getReportItemEPIArmCount() + " observation arm count " + reportItem.getReportItemControlArmCount());
                            listOfReportItems.add(reportItem);
                        }
                    } else if(rsNext == false){  // If you are past the last row in the list, save the reportItem and the while loop should stop.
                            listOfReportItems.add(reportItem);                        
                    }
                    rs.previous();
                    
                } else {

                    reportItem = new ReportItem();
                    reportItem.setReportItemSiteName(rs.getString("name"));
                    //System.out.println("The Total Accrual Site Name is 2: " + reportItem.getReportItemSiteName());
                    previousSite = reportItem.getReportItemSiteName();
                    if(previousSite.equals(reportItem.getReportItemSiteName())){
                        if("EPI_ARM".equals(rs.getString("studyarmstatus"))){
                            reportItem.setReportItemEPIArmCount(rs.getInt("totalaccrualbysitebystudyarm"));
                            //reportItem.setReportItemCrossedOverCount(rs.getInt("crossedovercountbysitebystudyarm"));
                        } else if("CONTROL_ARM".equals(rs.getString("studyarmstatus"))) {
                            reportItem.setReportItemControlArmCount(rs.getInt("totalaccrualbysitebystudyarm"));
                        } else {
                            totalCrossedOver += rs.getInt("crossedovercountbysitebystudyarm");
                            reportItem.setReportItemCrossedOverCount(totalCrossedOver);
                        }
                    }
                    reportItem.setReportItemTotalCount( reportItem.getReportItemTotalCount());
                    //System.out.println("\nSite name 2: " + reportItem.getReportItemSiteName() + "   EPI Arm Count: " + reportItem.getReportItemEPIArmCount() + "   observation arm count: " + reportItem.getReportItemControlArmCount() + "   Total count: " + reportItem.getReportItemTotalCount());
                    rsNext = rs.next();
                    if(rsNext != false){
                        if(!previousSite.equals(rs.getString("name"))){
                            //System.out.println("\nAdded to ReportItems 2: Site name " + reportItem.getReportItemSiteName() + " EPI Arm Count " + reportItem.getReportItemEPIArmCount() + " observation arm count " + reportItem.getReportItemControlArmCount());
                            listOfReportItems.add(reportItem);
                        }
                    } else if(rsNext == false){
                            listOfReportItems.add(reportItem);                        
                    }
                    rs.previous();
                }
            }
            // Total all of the columns and place at the bottom of the list (bottom of the table).
            reportItem = new ReportItem();
            Integer treatmentCount = 0;
            Integer observationalCount = 0;
            Integer crossedOverCount = 0;
            reportItem.setReportItemSiteName("Total");
            for(ReportItem ri : listOfReportItems){
                treatmentCount = treatmentCount + ri.getReportItemEPIArmCount();
                observationalCount = observationalCount + ri.getReportItemControlArmCount();
                crossedOverCount = crossedOverCount + ri.getReportItemCrossedOverCount();
            }
            reportItem.setReportItemEPIArmCount(treatmentCount);
            reportItem.setReportItemControlArmCount(observationalCount);
            reportItem.setReportItemTotalCount(treatmentCount + observationalCount);
            reportItem.setReportItemCrossedOverCount(crossedOverCount);
            listOfReportItems.add(reportItem);

            return listOfReportItems;
            
        } catch (SQLException ex) {
            logger.error("Error selecting reportItems.", ex);
            throw new PersistenceException("Error selecting reportItems.", ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public ReportItems getTotalAccrualsBySiteByMonthRawDatabaseRetrieval() throws PersistenceException {
        Connection conn = null;
        ArrayList<String> columnNames = new ArrayList<String>(); 
        ArrayList<Map<String, Object>> dataRows = new ArrayList<Map<String, Object>>(); 

//        String sql1 = "select extract('month' from enrolleddate) as month from site, participant where site.id = participant.siteid and studyarmstatus = 'EPI_ARM' group by studyarmstatus, month order by month;";
        String sql2 = "select name, studyarmstatus, count(participantid) as Total, extract('month' from enrolleddate) as month from site, participant where site.id = participant.siteid and site.id > 1 and participant.showstudyarm = true group by name, studyarmstatus, month order by name, month;";
        PreparedStatement ps = null;

        ReportItems reportItems;

        try {
            conn = ds.getConnection();
            
            ps = conn.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int numberColumns = rsmd.getColumnCount();
            for(int i = 1; i < numberColumns + 1; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            
//            ps = conn.prepareStatement(sql2);
//            rs = ps.executeQuery();

            while( rs.next()) {
                Map<String, Object> columnNamesToAccrualTotalMap = new HashMap<String, Object>();
                
                for(int j = 1; j <= rsmd.getColumnCount(); j++){

                    String stringValue = rs.getString(j);
                    Object objectValue = null;

                    switch (rsmd.getColumnType(j)) {
                        case Types.INTEGER:
                        case Types.SMALLINT:
                        case Types.BIGINT:
                            if( stringValue != null) {
                                    objectValue = Long.valueOf(stringValue);
                            }
                            break;
                        case Types.DECIMAL:
                        case Types.FLOAT:
                        case Types.DOUBLE:
                            if( stringValue != null) {
                                    objectValue = Double.valueOf(stringValue);
                            }
                            break;
                        default:
                            objectValue = stringValue;
                            break;
                    }
                    columnNamesToAccrualTotalMap.put(columnNames.get(j-1), objectValue);
                }
                dataRows.add( columnNamesToAccrualTotalMap);
            }

            reportItems = new ReportItems( columnNames, dataRows);

            return reportItems;
        } catch (SQLException ex) {
            logger.error("Error selecting reportItems.", ex);
            throw new PersistenceException("Error selecting reportItems.", ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }   
    
    @Override
    public ReportItems getTotalAccrualsBySiteByMonth(String studyArm) throws PersistenceException {
        Connection conn = null;
        String sql1 = "";
        ArrayList<String> columnNames = new ArrayList<String>(); 
        ArrayList<Map<String, Object>> dataRows = new ArrayList<Map<String, Object>>(); 
        ReportItems reportItems;
        Integer jan = 0;
        Integer feb = 0;
        Integer mar = 0;
        Integer apr = 0;
        Integer may = 0;
        Integer jun = 0;
        Integer jul = 0;
        Integer aug = 0;
        Integer sep = 0;
        Integer oct = 0;
        Integer nov = 0;
        Integer dec = 0;
                
//        String sql1 = "select extract('month' from enrolleddate) as month from site, participant where site.id = participant.siteid and studyarmstatus = 'EPI_ARM' group by studyarmstatus, month order by month;";
        if(studyArm.equals("epi_arm")){
            sql1 = "select site.siteid, name, count(participantid) as Total, extract('month' from enrolleddate) as month, extract('year' from enrolleddate) as year from site, participant where site.id = participant.siteid and studyarmstatus = 'EPI_ARM' and site.id > 1 and participant.showstudyarm = true group by site.siteid, name, studyarmstatus, month, year order by site.siteid, name, month, year;";
        } else if(studyArm.equals("control_arm")){
            sql1 = "select site.siteid, name, count(participantid) as Total, extract('month' from enrolleddate) as month, extract('year' from enrolleddate) as year from site, participant where site.id = participant.siteid and studyarmstatus = 'CONTROL_ARM' and site.id > 1 and participant.showstudyarm = true group by site.siteid, name, studyarmstatus, month, year order by site.siteid, name, month, year;";
        }
            PreparedStatement ps = null;

        try {
            conn = ds.getConnection();
            
            ps = conn.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            // Dynamically order the headers based on current month
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            Integer thisMonth = cal.get(Calendar.MONTH) + 1; // Add 1 to sync because postgres months start at 1
            Integer thisYear = cal.get(Calendar.YEAR);
            
            String[] monthColumns = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
            
            // Calculate year for each month column
            /*
            for (int monthIndex = 0; monthIndex < monthColumns.length; monthIndex++) {
               String monthColumn = monthColumns[monthIndex];
               Integer monthNumber = monthIndex + 1;
               
               Integer yearForMonth = (monthNumber <= thisMonth ? thisYear : thisYear - 1);
               String monthWithYear = monthColumn + " " + yearForMonth.toString();
               
               monthColumns[monthIndex] = monthWithYear;
            }
            */
            
            int offset = -(thisMonth); // Negate to shift/rotate array left
            ArrayUtils.shift(monthColumns, offset);
            
            columnNames.add("Site");
            columnNames.add("Historical");
            
            for (String monthColumn : Arrays.asList(monthColumns)) {
               columnNames.add(monthColumn);
            }
            
            columnNames.add("Total");
            
            rs.next();
            String siteName = rs.getString("name");
            rs.beforeFirst();
            int first = 0;
            Integer total = 0;
            Integer historicalForSite = 0;
            Integer historicalTotal = 0;

            Map<String, Object> columnNamesToAccrualTotalMap = new HashMap<String, Object>();
            Map<String, Object> monthlyAccrualTotals = new HashMap<String, Object>();

            while( rs.next()) {
                String resultsetSiteName = rs.getString("name");

//                columnNamesToAccrualTotalMap.put("May", "0");
//                columnNamesToAccrualTotalMap.put("Jun", "0");
//                columnNamesToAccrualTotalMap.put("Jul", "0");

                if(resultsetSiteName.equals(siteName)){
                    if(first == 0) {
                        Integer siteId = rs.getInt("siteid");  // Get the site id from the returned results.
                        String stringObject = siteId + " - " + rs.getString("name");  // Get the site name from the returned results.
                        columnNamesToAccrualTotalMap.put(columnNames.get(0), stringObject);  // Create the site names in the first column in the table with the format 'siteid - sitename'.
                    }
                    Integer monthNumber = Integer.parseInt(rs.getString("month"));
                    Integer year = Integer.parseInt(rs.getString("year"));
                    
                    logger.debug("Yes sitename match");
                    logger.debug("This year " + thisYear + " query year " + year);
                    logger.debug("This month " + thisMonth + " query month " + monthNumber);
                    
                    // My idea was to try and put zeros into the rows/columns were no values appeared in the monthly accrual table.
                    // I tried to use if(rs.getString("total") == null or "" or isEmpty with a System.out.println
                    // Only columns with non-zero values were printed. If you copy the SQL statement above and 
                    // run it in psql, you get the number of rows (months) with actual numbers in it. 
                    // Months with no accrual numbers do not return rows, and therefore, no monthNumber exists.
                    if ((year < (thisYear - 1)) || ((year == (thisYear - 1)) && monthNumber <= thisMonth)) {
                        historicalForSite = historicalForSite + Integer.parseInt(rs.getString("total"));
                        historicalTotal =  historicalTotal + Integer.parseInt(rs.getString("total"));
                        columnNamesToAccrualTotalMap.put("Historical", historicalForSite.toString());
                        logger.debug("Adding  to " + siteName + " historical " + rs.getString("total"));
                    } else {
                        // Get index into month columns
                        //int monthIndex = ((monthNumber - 1) + offset + (monthColumns.length)) - monthColumns.length;
                        
                        switch(monthNumber){
                            case 1: columnNamesToAccrualTotalMap.put("Jan", rs.getString("total"));
                                //columnNamesToAccrualTotalMap.put(monthColumns[monthNumber - 1], rs.getString("total"));
                                jan = jan + Integer.parseInt(rs.getString("total"));
                                break;
                            case 2: columnNamesToAccrualTotalMap.put("Feb", rs.getString("total"));
                                feb = feb + Integer.parseInt(rs.getString("total"));
                                break;
                            case 3: columnNamesToAccrualTotalMap.put("Mar", rs.getString("total"));
                                mar = mar + Integer.parseInt(rs.getString("total"));
                                break;
                            case 4: columnNamesToAccrualTotalMap.put("Apr", rs.getString("total"));
                                apr = apr + Integer.parseInt(rs.getString("total"));
                                break;
                            case 5: columnNamesToAccrualTotalMap.put("May", rs.getString("total"));
                                may = may + Integer.parseInt(rs.getString("total"));
                                break;
                            case 6: columnNamesToAccrualTotalMap.put("Jun", rs.getString("total"));
                                jun = jun + Integer.parseInt(rs.getString("total"));
                                break;
                            case 7: columnNamesToAccrualTotalMap.put("Jul", rs.getString("total"));
                                jul = jul + Integer.parseInt(rs.getString("total"));
                                break;
                            case 8: columnNamesToAccrualTotalMap.put("Aug", rs.getString("total"));
                                aug = aug + Integer.parseInt(rs.getString("total"));
                                break;
                            case 9: columnNamesToAccrualTotalMap.put("Sep", rs.getString("total"));
                                sep = sep + Integer.parseInt(rs.getString("total"));
                                break;
                            case 10: columnNamesToAccrualTotalMap.put("Oct", rs.getString("total"));
                                oct = oct + Integer.parseInt(rs.getString("total"));
                                break;
                            case 11: columnNamesToAccrualTotalMap.put("Nov", rs.getString("total"));
                                nov = nov + Integer.parseInt(rs.getString("total"));
                                break;
                            case 12: columnNamesToAccrualTotalMap.put("Dec", rs.getString("total"));
                                dec = dec + Integer.parseInt(rs.getString("total"));
                                break;
                        }
                    }
                    total = total + Integer.parseInt(rs.getString("total"));
                    first++;
                } else if(!resultsetSiteName.equals(siteName)){
                    columnNamesToAccrualTotalMap.put("Total", total.toString());
                    dataRows.add( columnNamesToAccrualTotalMap);
                    first = 0;
                    total = 0;
                    historicalForSite = 0;
                    columnNamesToAccrualTotalMap = new HashMap<String, Object>();
                    if(first == 0) {
                        Integer siteId = rs.getInt("siteid");
                        String stringObject = siteId + " - " + rs.getString("name");
                        siteName = rs.getString("name");
                        columnNamesToAccrualTotalMap.put(columnNames.get(0), stringObject);
                    }
                    Integer monthNumber = Integer.parseInt(rs.getString("month"));
                    Integer year = Integer.parseInt(rs.getString("year"));
                    
                    logger.debug("No sitename match");
                    logger.debug("This year " + thisYear + " query year " + year);
                    logger.debug("This month " + thisMonth + " query month " + monthNumber);
                    
                    if ((year < (thisYear - 1)) || ((year == (thisYear - 1)) && monthNumber <= thisMonth)) {
                        historicalForSite = historicalForSite + Integer.parseInt(rs.getString("total"));
                        historicalTotal =  historicalTotal + Integer.parseInt(rs.getString("total"));
                        columnNamesToAccrualTotalMap.put("Historical", historicalForSite.toString());
                        logger.debug("Adding to " + siteName + " historical " + rs.getString("total"));
                    } else {
                        switch(monthNumber){
                            case 1: columnNamesToAccrualTotalMap.put("Jan", rs.getString("total"));
                                jan = jan + Integer.parseInt(rs.getString("total"));
                                break;
                            case 2: columnNamesToAccrualTotalMap.put("Feb", rs.getString("total"));
                                feb = feb + Integer.parseInt(rs.getString("total"));
                                break;           
                            case 3: columnNamesToAccrualTotalMap.put("Mar", rs.getString("total"));
                                mar = mar + Integer.parseInt(rs.getString("total"));
                                break;
                            case 4: columnNamesToAccrualTotalMap.put("Apr", rs.getString("total"));
                                apr = apr + Integer.parseInt(rs.getString("total"));
                                break;
                            case 5: columnNamesToAccrualTotalMap.put("May", rs.getString("total"));
                                may = may + Integer.parseInt(rs.getString("total"));
                                break;
                            case 6: columnNamesToAccrualTotalMap.put("Jun", rs.getString("total"));
                                jun = jun + Integer.parseInt(rs.getString("total"));
                                break;
                            case 7: columnNamesToAccrualTotalMap.put("Jul", rs.getString("total"));
                                jul = jul + Integer.parseInt(rs.getString("total"));
                                break;
                            case 8: columnNamesToAccrualTotalMap.put("Aug", rs.getString("total"));
                                aug = aug + Integer.parseInt(rs.getString("total"));
                                break;
                            case 9: columnNamesToAccrualTotalMap.put("Sep", rs.getString("total"));
                                sep = sep + Integer.parseInt(rs.getString("total"));
                                break;
                            case 10: columnNamesToAccrualTotalMap.put("Oct", rs.getString("total"));
                                oct = oct + Integer.parseInt(rs.getString("total"));
                                break;
                            case 11: columnNamesToAccrualTotalMap.put("Nov", rs.getString("total"));
                                nov = nov + Integer.parseInt(rs.getString("total"));
                                break;
                            case 12: columnNamesToAccrualTotalMap.put("Dec", rs.getString("total"));
                                dec = dec + Integer.parseInt(rs.getString("total"));
                                break;
                        }
                    }
                    total = total + Integer.parseInt(rs.getString("total"));
                    first++;
                }
            }
            columnNamesToAccrualTotalMap.put("Total", total.toString());
            dataRows.add( columnNamesToAccrualTotalMap);

            // Determine the total number of accrued participants for all sites from summing the Total for each row.
            Integer totalAccrualAllSites = 0;
            
            Iterator rows = dataRows.listIterator();
            while(rows.hasNext()){
                Map<String, Object> row = (Map<String, Object>) rows.next();
                String totalForRow = (String) row.get("Total");
                totalAccrualAllSites = totalAccrualAllSites + Integer.parseInt(totalForRow);
            }

            // Add the totals for each month to the last data row.
            monthlyAccrualTotals.put("Site", "Total");
            monthlyAccrualTotals.put("Historical", historicalTotal.toString());
            monthlyAccrualTotals.put("Mar", mar.toString());
            monthlyAccrualTotals.put("Apr", apr.toString());
            monthlyAccrualTotals.put("May", may.toString());
            monthlyAccrualTotals.put("Jun", jun.toString());
            monthlyAccrualTotals.put("Jul", jul.toString());
            monthlyAccrualTotals.put("Aug", aug.toString());
            monthlyAccrualTotals.put("Sep", sep.toString());
            monthlyAccrualTotals.put("Oct", oct.toString());
            monthlyAccrualTotals.put("Nov", nov.toString());
            monthlyAccrualTotals.put("Dec", dec.toString());
            monthlyAccrualTotals.put("Jan", jan.toString());
            monthlyAccrualTotals.put("Feb", feb.toString());
            monthlyAccrualTotals.put("Mar", mar.toString());
            monthlyAccrualTotals.put("Total", totalAccrualAllSites.toString());
            dataRows.add( monthlyAccrualTotals);

            reportItems = new ReportItems( columnNames, dataRows);

            return reportItems;
        } catch (SQLException ex) {
            logger.error("Error selecting reportItems.", ex);
            throw new PersistenceException("Error selecting reportItems.", ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }   
    
    @Override
    public int getParticipantFirstPaymentStatus(int siteid, String pid, String eventName) throws PersistenceException {
        
        Connection conn = null;
        String sql = "select siteid, participant.participantid, studyarmstatus, eventtype, event.name, event.status, basicform.status, formtype, creationdate, actualdate, basedate, lastsubmitteddate from participant, event, basicform where participant.id = event.participantid and event.id = basicform.eventid and actualdate notnull and basicform.status = 'DCC_VERIFIED' and siteid = ? and participant.participantid = ? and event.name = ? and participant.showstudyarm = true order by siteid, participant.participantid, event.id;";
        PreparedStatement ps = null;
        ResultSet rs;
        int formCount = 0;
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, siteid);
            ps.setString(2, pid);
            ps.setString(3, eventName);

            System.out.println("The sql statement for the getParticipantFirstPaymentStatus is: " + ps.toString());
            logger.debug(ps.toString());
        
            rs = ps.executeQuery();
            if( rs.next()) {
                formCount++;
            }
            else {
                logger.error("Failed to execute the statement " + ps.toString());
            }
           
            return formCount;
        } catch (Exception ex) {
            String msg = "Failed to execute the statement " + ps.toString();
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

     
    
    

}
