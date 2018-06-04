package edu.wustl.mir.ctt.persistence;

import edu.wustl.mir.ctt.auditlog.AuditLogException;
import edu.wustl.mir.ctt.auditlog.AuditLogManager;
import edu.wustl.mir.ctt.form.AdverseEventWorksheetSAEForm;
import edu.wustl.mir.ctt.form.AnnualFollowUpForm;
import edu.wustl.mir.ctt.form.BaselineTherapyForm;
import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.ChangeTherapyForm;
import edu.wustl.mir.ctt.form.CrossoverSafetyCheckForm;
import edu.wustl.mir.ctt.form.DSCForm;
import edu.wustl.mir.ctt.form.DemoMedHistForm;
import edu.wustl.mir.ctt.form.ECPTreatmentForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EndOfStudyForm;
import edu.wustl.mir.ctt.form.HospitalizationForm;
import edu.wustl.mir.ctt.form.QualityOfLifeForm;
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
import edu.wustl.mir.ctt.model.DeclineStrata;
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
import edu.wustl.mir.ctt.model.SpirometryStrata;
import edu.wustl.mir.ctt.model.StudyArmStatus;
import edu.wustl.mir.ctt.model.VerificationStatus;
import edu.wustl.mir.versioncontrol.VersionControl;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.DelegatingConnection;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
//import org.apache.shiro.SecurityUtils;
//import org.apache.tomcat.dbcp.dbcp.DelegatingConnection;
import org.postgresql.jdbc4.Jdbc4Connection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

/**
 * A persistence manager using JDBC to a RDMS data base.
 * 
 * This implementation assumes Postgresql due to its use of custom BLOB 
 * interface to store documents.
 * 
 * @author drm
 */
public class JDBCPersistenceManager implements PersistenceManager {

    private DataSource ds;
    private Logger logger;

    /**
     * Use the Web Container to manage the DataSource.
     *
     * @throws PersistenceException
     */
    public JDBCPersistenceManager() throws PersistenceException {
        try {
            logger = Logger.getLogger(JDBCPersistenceManager.class);

            InitialContext ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ecpreg_db");
            if (ds == null) {
                String msg = "JDBCPersistenceManager failed to find DataSource: jdbc/ecpreg_db"; // Production Mode
                logger.error(msg);
                throw new PersistenceException(msg);
            }
            logger.info("Instantiated datasource: jdbc/ecpreg_db");
        } catch (NamingException e) {
            String msg = "JDBCPersistenceManager failed to create Naming Context: java:comp/env";
            logger.error(msg, e);
            throw new PersistenceException(msg, e);
        }
    }
    
    @Override
    public List<Site> getSites() throws PersistenceException {
        Connection conn = null;
        String sql = "select * from site order by id;";
        
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
                site.setCrfVersion( rs.getString("crfversion"));
                site.setIrbVersion( rs.getString("irbversion"));
                site.setIrbSubmittedDate(rs.getDate("irbsubmitteddate"));;

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
                site.setCrfVersion( rs.getString("crfversion"));
                site.setIrbVersion( rs.getString("irbversion"));
                site.setIrbSubmittedDate(rs.getDate("irbsubmitteddate"));
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
                site.setCrfVersion( rs.getString("crfversion"));
                site.setIrbVersion( rs.getString("irbversion"));
                site.setIrbSubmittedDate(rs.getDate("irbsubmitteddate"));

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
    public int insertSite( Site site) throws PersistenceException {
        Connection conn = null;
        String sql = "insert into site (siteId, name, status) values (?,?,?) returning id;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Insert site = " + site);
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, site.getSiteID());
            ps.setString(2, site.getName());
            ps.setString(3, site.getStatus().name());
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                site.setId(id);
                return id;
            }
            
            return -1;
        } catch (SQLException ex) {
            String msg = "Error inserting site = " + site;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    
    @Override
    public StudyArmStatus randomizeParticipant(int siteId, String participantId, DeclineStrata d, SpirometryStrata s) throws PersistenceException {
        String randomization = "";
        StudyArmStatus arm = StudyArmStatus.UNKNOWN;
        
        int group = 0;
        
        if (s == SpirometryStrata.LAB_SPIROMETRY_STRATA) {
            if (d == DeclineStrata.LOW_DECLINE_STRATA) {
                group = 1;
            } else if (d == DeclineStrata.HIGH_DECLINE_STRATA) {
                group = 2;
            }
        } else if (s == SpirometryStrata.HOME_SPIROMETRY_STRATA) {
            if (d == DeclineStrata.LOW_DECLINE_STRATA) {
                group = 3;
            } else if (d == DeclineStrata.HIGH_DECLINE_STRATA) {
                group = 4;
            }
        }
        
        String sql = "select assignment from randomization "
                   + "where siteid = ? and participantid = ? and studygroup = ?;";
                
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, siteId);
            ps.setString(2, participantId);
            ps.setInt(3, group);
            
            logger.debug(sql);
            logger.debug(siteId);
            logger.debug(participantId);
            logger.debug(group);
            logger.debug(ps.toString());

            rs = ps.executeQuery();
            if( rs.next()) {
                randomization = rs.getString("assignment");
            }
            else {
                logger.error("Failed to randomize subject");
            }
           
            switch (randomization) {
                case "T":
                    arm = StudyArmStatus.EPI_ARM;
                    break;
                case "C":
                    arm = StudyArmStatus.CONTROL_ARM;
                    break;
                default:
                    throw new Exception();
            }
            
            return arm;
        } catch (Exception ex) {
            String msg = "Failed to randomize subject";
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
    // NOTE: This method returns the participant table ID which is an integer, not the patient id whic is a string.
    private int insertParticipant( Connection conn, Participant p) throws PersistenceException {
        String sql = "insert into participant "
                + "(siteId, participantId, enrolledDate, status, studyArmEnrollDate, studyArmStatus, holdStatus, holdStartDate, overrideDate, spirometrystrata, declinestrata, showStudyArm) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?) returning id;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Insert participant = " + p);
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getSiteId());
            ps.setString(2, p.getParticipantID());
            if( p.getEnrolledDate() == null)
                ps.setNull(3, java.sql.Types.DATE);
            else
                ps.setDate(3, new java.sql.Date(p.getEnrolledDate().getTime()));
            ps.setString(4, p.getStatus().name());
            if( p.getStudyArmEnrollDate() == null)
                ps.setNull(5, java.sql.Types.DATE);
            else
                ps.setDate(5, new java.sql.Date(p.getStudyArmEnrollDate().getTime()));
            if( p.getRealStudyArmStatus() == null)
                ps.setNull(6, java.sql.Types.VARCHAR);
            else
                ps.setString(6, p.getRealStudyArmStatus().name());
            ps.setBoolean(7, p.isHoldStatus());
            if( p.getHoldStartDate() == null)
                ps.setNull(8, java.sql.Types.DATE);
            else
                ps.setDate(8, new java.sql.Date(p.getHoldStartDate().getTime()));
            if( p.getOverrideDate() == null)
                ps.setNull(9, java.sql.Types.DATE);
            else
                ps.setDate(9, new java.sql.Date(p.getOverrideDate().getTime()));
            if( p.getSpirometryStrata() == null)
                ps.setNull(10, java.sql.Types.VARCHAR);
            else
                ps.setString(10, p.getSpirometryStrata().name());
            if( p.getDeclineStrata() == null)
                ps.setNull(11, java.sql.Types.VARCHAR);
            else
                ps.setString(11, p.getDeclineStrata().name());
			ps.setBoolean(12, p.isShowStudyArm());
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                p.setId(id);
                return id;
            }
            
            return -1;
        } catch (Exception ex) {
            String msg = "Error inserting participant = " + p;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }
	
    private int updateParticipant( Connection conn, Participant p) throws PersistenceException {
        String sql = "update participant "
                + "set siteId=?, participantId=?, enrolledDate=?, status=?, "
                + "studyArmEnrollDate=?, studyArmStatus=?, holdStatus=?, holdStartDate=?, overrideDate=?, "
                + "spirometrystrata=?, declinestrata=? "
                + "where id =?;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Update participant = " + p);
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getSiteId());
            ps.setString(2, p.getParticipantID());
            if( p.getEnrolledDate() == null)
                ps.setNull(3, java.sql.Types.DATE);
            else
                ps.setDate(3, new java.sql.Date(p.getEnrolledDate().getTime()));
            ps.setString(4, p.getStatus().name());
            if( p.getStudyArmEnrollDate() == null)
                ps.setNull(5, java.sql.Types.DATE);
            else
                ps.setDate(5, new java.sql.Date(p.getStudyArmEnrollDate().getTime()));
            if( p.getRealStudyArmStatus() == null)
                ps.setNull(6, java.sql.Types.VARCHAR);
            else
                ps.setString(6, p.getRealStudyArmStatus().name());
            ps.setBoolean(7, p.isHoldStatus());
            if( p.getHoldStartDate() == null)
                ps.setNull(8, java.sql.Types.DATE);
            else
                ps.setDate(8, new java.sql.Date(p.getHoldStartDate().getTime()));
            if( p.getOverrideDate() == null)
                ps.setNull(9, java.sql.Types.DATE);
            else
                ps.setDate(9, new java.sql.Date(p.getOverrideDate().getTime()));
            if( p.getSpirometryStrata() == null)
                ps.setNull(10, java.sql.Types.VARCHAR);
            else
                ps.setString(10, p.getSpirometryStrata().name());
            if( p.getDeclineStrata() == null)
                ps.setNull(11, java.sql.Types.VARCHAR);
            else
                ps.setString(11, p.getDeclineStrata().name());
            ps.setInt(12, p.getId());
            int i = ps.executeUpdate();
            if( i != 1) {
                throw new SQLException("Error updating participant = " + p);
            }
            
            return -1;
        } catch (Exception ex) {
            String msg = "Error updating participant = " + p;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }
	
    private int insertEvent( Connection conn, Event e) throws PersistenceException {
        String sql = "insert into event (participantId, eventType, name, label, status, expected, baseDate, baseDateOffset, firstDateOffset, lastDateOffset, actualDate, showBaseDate, showFirstAvailDate, showLastAvailDate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) returning id;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Insert event = " + e);
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getParticipantId());
            ps.setString(2, e.getType().name());
            ps.setString(3, e.getName());
            ps.setString(4, e.getLabel());
            ps.setString(5, e.getStatus().name());
            ps.setBoolean(6, e.isExpected());
            if( e.getBaseDate() == null) {
                ps.setNull(7, java.sql.Types.DATE);
            }
            else {
                ps.setDate(7, new java.sql.Date(e.getBaseDate().getTime()));
            }
            ps.setInt(8, e.getOffsetFromBaseDateInDays());
            ps.setInt(9, e.getOffsetToFirstAvailableDayInDays());
            ps.setInt(10, e.getOffsetToLastAvailableDayInDays());
            if( e.getActualDate() == null) {
                ps.setNull(11, java.sql.Types.DATE);
            }
            else {
                ps.setDate(11, new java.sql.Date(e.getActualDate().getTime()));
            }
            ps.setBoolean(12, e.getShowBaseDate());
            ps.setBoolean(13, e.getShowFirstAvaiableDate());
            ps.setBoolean(14, e.getShowLastAvailableDate());
            rs = ps.executeQuery();
            if (rs.next()) {
                int eid = rs.getInt("id");
                e.setId(eid);
                return eid;
            }
            
            return -1;
        } catch (SQLException ex) {
            String msg = "Error inserting event = " + e;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }
	
     private int updateEvent( Connection conn, Event e) throws PersistenceException {
        String sql = "update event set participantId=?, eventType=?, name=?, label=?, status=?, expected=?, baseDate=?, baseDateOffset=?, firstDateOffset=?, lastDateOffset=?, actualDate=?, showBaseDate=?, showFirstAvailDate=?, showLastAvailDate=? where id=?;";
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Update event = " + e);
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getParticipantId());
            ps.setString(2, e.getType().name());
            ps.setString(3, e.getName());
            ps.setString(4, e.getLabel());
            ps.setString(5, e.getStatus().name());
            ps.setBoolean(6, e.isExpected());
            if( e.getBaseDate() == null) {
                ps.setNull(7, java.sql.Types.DATE);
            }
            else {
                ps.setDate(7, new java.sql.Date(e.getBaseDate().getTime()));
            }
            ps.setInt(8, e.getOffsetFromBaseDateInDays());
            ps.setInt(9, e.getOffsetToFirstAvailableDayInDays());
            ps.setInt(10, e.getOffsetToLastAvailableDayInDays());
            if( e.getActualDate() == null) {
                ps.setNull(11, java.sql.Types.DATE);
            }
            else {
                ps.setDate(11, new java.sql.Date(e.getActualDate().getTime()));
            }
            ps.setBoolean(12, e.getShowBaseDate());
            ps.setBoolean(13, e.getShowFirstAvaiableDate());
            ps.setBoolean(14, e.getShowLastAvailableDate());
            ps.setInt(15, e.getId());
            System.out.println("The update Event SQL statement is: " + ps.toString());
            int i = ps.executeUpdate();
            if( i != 1) {
                throw new SQLException("Error updating event = " + e);
            }
            
            return i;
        } catch (SQLException ex) {
            String msg = "Error inserting event = " + e;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }
    
    @Override
    public void updateEvents( List<Event> events) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            for( Event e: events) {
                updateEvent( conn, e);
            }
            
            conn.commit();
                    
        } catch (Exception ex) {
            String msg = "Error updating events. ";
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of events update failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after events update.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
    @Override
    public void updateEvent( Event event) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            updateEvent( conn, event);
            
            conn.commit();
                    
        } catch (Exception ex) {
            String msg = "Error updating event. ";
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of event update failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after event update.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
    @Override
    public void updateEventStatus( Event event) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            updateEventStatus( conn, event);
            
            conn.commit();
                    
        } catch (Exception ex) {
            String msg = "Error updating event STATUS. ";
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of event STATUS update failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after event STATUS update.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
     private int updateEventStatus( Connection conn, Event e) throws PersistenceException {
        String sql = "update event set status=? where id=?;";
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Update event STATUS = " + e);
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, e.getStatus().name());
            ps.setInt(2, e.getId());
            System.out.println("The update Event STATUS SQL statement is: " + ps.toString());
            int i = ps.executeUpdate();
            if( i != 1) {
                throw new SQLException("Error updating event STATUS = " + e);
            }
            
            return i;
        } catch (SQLException ex) {
            String msg = "Error inserting event STATUS = " + e;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }
    
    @Override
    public int addParticipant( Participant p, Event e, List<BasicForm> forms) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            int pid = insertParticipant( conn, p);
            e.setParticipantId(pid);
            
            int eid = insertEvent( conn, e);
            
            for( BasicForm f: forms) {
                f.setEventId(eid);
                int fid = insertForm( conn, f);
            }
            
            conn.commit();
            
            return pid;
        
        } catch (Exception ex) {
            String msg = "Error adding participant: " + p;
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of participant addition failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after participant addition.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public int addParticipant( Participant p) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            int pid = insertParticipant( conn, p);
            
            conn.commit();
            
            return pid;
        
        } catch (Exception ex) {
            String msg = "Error adding participant: " + p;
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of participant addition failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after participant addition.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public int addParticipantEnrollment( Participant p, Event e, List<BasicForm> forms) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            e.setParticipantId(p.getId());
            int returnStatus = updateParticipant(conn, p);
            int eid = insertEvent( conn, e);
            
            for( BasicForm f: forms) {
                f.setEventId(eid);
                int fid = insertForm( conn, f);
            }
            
            conn.commit();
            
            return p.getId();
        
        } catch (Exception ex) {
            String msg = "Error adding participant: " + p;
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of participant addition failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after participant addition.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public int addEvent(Participant p, Event e, List<BasicForm> forms, Site selectedSite) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            int pid = p.getId();
            e.setParticipantId(pid);
            
            int eid = insertEvent( conn, e);
            
            for( BasicForm f: forms) {
                f.setEventId(eid);
                f.setCrfVersion(selectedSite.getCrfVersion());
                f.versionControl();
                f.setIrbVersion(selectedSite.getIrbVersion());
                f.setIrbSubmittedDate(selectedSite.getIrbSubmittedDate());
                int fid = insertForm( conn, f);
            }
            
            conn.commit();
            
            return pid;
        
        } catch (Exception ex) {
            String msg = "Error adding event: " + e;
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of event addition failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after event addition.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public int addInitialSAEEvent(Participant p, Event e, List<BasicForm> forms, Site selectedSite) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            int pid = p.getId();
            e.setParticipantId(pid);
            
            int eid = insertEvent( conn, e);
            
            for( BasicForm f: forms) {
                f.setEventId(eid);
                f.setCrfVersion(selectedSite.getCrfVersion());
                f.setIrbVersion(selectedSite.getIrbVersion());
                f.setIrbSubmittedDate(selectedSite.getIrbSubmittedDate());
                int fid = insertForm( conn, f);
            }
            
            conn.commit();
            
            return pid;
        
        } catch (Exception ex) {
            String msg = "Error adding event: " + e;
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of event addition failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after event addition.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public void enrollParticipant( Participant p, List<Event> events, Site selectedSite) throws PersistenceException {
        Connection conn = null;
        
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            p.setStatus(ParticipantStatus.ENROLLED);
            updateParticipant( conn, p);
            
            for (Event e : events) {
                int eid = insertEvent(conn, e);
                for( BasicForm f: e.getForms()) {
                    f.setEventId(eid);
                    f.setCrfVersion(selectedSite.getCrfVersion());
                    f.versionControl();
                    f.setIrbVersion(selectedSite.getIrbVersion());
                    f.setIrbSubmittedDate(selectedSite.getIrbSubmittedDate());
                    int fid = insertForm( conn, f);
                }
            }
 
            conn.commit();
                    
        } catch (Exception ex) {
            String msg = "Error enrolling participant: " + p;
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of participant enrolling failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after participant addition.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
    @Override
    public List<Participant> getParticipants(Site site) throws PersistenceException {
        Connection conn = null;
        String sql = "select id, siteId, participantId, enrolledDate, status, studyarmenrolldate, studyarmstatus, showstudyarm from participant where siteid = ? order by id;";
        
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
				participant.setShowStudyArm(rs.getBoolean("showstudyarm"));
                
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
        String sql = "select count(*) from participant where siteid = ?;";
        
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
	
    @Override
    public List<Event> getEvents( Participant p) throws PersistenceException {

        Connection conn = null;
        String sql = "select * from event where participantId = ? order by actualdate, basedate, basedateoffset, id;";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Get events for participant " + p);

        try {
            List<Event> events = new ArrayList<Event>();
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Event event = new Event();
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

                events.add(event);
            }

            return events;
        } catch (SQLException ex) {
            logger.error("Error selecting events.", ex);
            throw new PersistenceException("Error selecting events.", ex);
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
        String sql = "select id, eventId, formType, title, creationDate, status, sequencenumber, lastsubmitteddate, versionid, crfversion, irbversion, irbsubmitteddate from basicForm where eventId = ? order by sequencenumber, creationdate;";
//        String sql = "select id, eventId, formType, title, creationDate, status, sequencenumber, lastsubmitteddate from basicForm where eventId = ? order by creationdate, sequencenumber;";

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
                form.setVersionControlId(rs.getInt("versionid"));  // Software version control ID from the versioncontrol table.
                form.setCrfVersion(rs.getString("crfversion"));     // CRF Version from the basicform table is the crf version.
                form.setIrbVersion(rs.getString("irbversion"));     // IRB Version from the basicform table is the IRB version.
                form.setIrbSubmittedDate(rs.getDate("irbsubmitteddate"));  // Date when the WashU protocol was submitted to the WashU IRB for approval. Same as the CRF date.
                
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
        String sql = "select id, eventId, formType, title, creationDate, status, sequenceNumber, lastsubmitteddate, versionid, crfversion, irbversion, irbsubmitteddate from basicForm where id = ?;";
//        String sql = "select id, eventId, formType, title, creationDate, status, sequenceNumber, lastsubmitteddate from basicForm where id = ?;";

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
                form.setVersionControlId(rs.getInt("versionid"));  // Software version control ID from the versioncontrol table.
                form.setCrfVersion(rs.getString("crfversion"));     // CRF Version from the basicform table is the crf version.
                form.setIrbVersion(rs.getString("irbversion"));     // IRB Version from the basicform table is the IRB version.
                form.setIrbSubmittedDate(rs.getDate("irbsubmitteddate"));  // Date when the WashU protocol was submitted to the WashU IRB for approval. Same as the CRF date.

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
    
    @Override
    public Integer saveForm( BasicForm f) throws PersistenceException {
        if( f.getId() == null) {
            System.out.println("The JDBC-PM.saveForm will now insertForm");
            return insertForm(f);
        }
        else {
            System.out.println("The JDBC-PM.saveForm will now updateForm");
            updateForm(f);
            return f.getId();
        }
    }
    
    @Override
    public int insertForm( BasicForm f) throws PersistenceException {

        Connection conn = null;

        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            
            insertForm( conn, f);
            
            conn.commit();
            
            return f.getId();
        
        } catch (Exception ex) {
            logger.error("Error inserting form: " + f, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of form insertion failed.", sqlex);
            }
            throw new PersistenceException("Error inserting form: " + f, ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after form insertion.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    
    protected int insertForm( Connection conn, BasicForm f) throws PersistenceException, AuditLogException {

        VersionControl versionControl;
        try {
            versionControl = new VersionControl();
            f.setVersionControlId(versionControl.getVersionControlId());
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(JDBCPersistenceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String formSql = "insert into basicForm (eventId, formType, title, creationDate, status, sequenceNumber, lastsubmitteddate, versionid, crfversion, irbversion, irbsubmitteddate) values (?,?,?,?,?,?,?,?,?,?,?) returning id;";
//        String formSql = "insert into basicForm (eventId, formType, title, creationDate, status, sequenceNumber, lastsubmitteddate) values (?,?,?,?,?,?,?) returning id;";
        String attSql = "insert into formAttributes ( formId, name, valueType, booleanValue, intValue, floatValue, dateValue, timeValue, timestampValue, stringValue, verify, optional, verificationstatus, dcccomment) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) returning id";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Insert form: " + f);

        try {
            ps = conn.prepareStatement(formSql);
            ps.setInt(1, f.getEventId());
            ps.setString(2, f.getFormType().name());
            ps.setString(3, f.getTitle());
            ps.setDate(4, new java.sql.Date(f.getDate().getTime()));
            ps.setString(5, f.getStatus().name());
            ps.setInt(6, f.getSequenceNumber());
            ps.setTimestamp(7, new java.sql.Timestamp(f.getLastSubmittedDate().getTime()));
            ps.setInt(8, f.getVersionControlId());
            ps.setString(9, f.getCrfVersion());
            ps.setString(10, f.getIrbVersion());
            ps.setDate(11, new java.sql.Date(f.getIrbSubmittedDate().getTime()));
            System.out.println("The insertForm SQL statement is: " + ps.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                f.setId( rs.getInt("id"));
            }
            
            ps = conn.prepareStatement(attSql);
            for( Attribute a: f.getAttributes()) {
                if( a.isPersistent()) {
                    ps.setInt(1, f.getId());
                    ps.setString(2, a.getName());
                    ps.setString(3, a.getValueType().getType());
                    switch(a.getValueType()) {
                        case BOOLEAN:
                            Boolean b = (Boolean) a.getValue();
                            if( b == null) {
                                ps.setNull(4, java.sql.Types.BOOLEAN);
                            } else {
                                ps.setBoolean(4, b);
                            }
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case INTEGER:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            Integer i = (Integer) a.getValue();
                            if( i == null) {
                                ps.setNull(5, java.sql.Types.INTEGER);
                            } else {
                                ps.setInt(5, i);
                            }
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case FLOAT:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            Float v = (Float) a.getValue();
                            if( v == null) {
                                ps.setNull(6, java.sql.Types.FLOAT);
                            } else {
                                ps.setFloat(6, v);
                            }
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case DATE:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            Date d = (Date) a.getValue();
                            if( d == null) {
                                ps.setNull(7, java.sql.Types.DATE);
                            } else {
                                ps.setDate(7, new java.sql.Date(d.getTime()));
                            }
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case TIME:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            Date t = (Date) a.getValue();
                            if( t == null) {
                                ps.setNull(8, java.sql.Types.TIME);
                            } else {
                                ps.setTime(8, new java.sql.Time(t.getTime()));
                            }
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case TIMESTAMP:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            Date ts = (Date) a.getValue();
                            if( ts == null) {
                                ps.setNull(9, java.sql.Types.TIMESTAMP);
                            } else {
                                ps.setTimestamp(9, new java.sql.Timestamp(ts.getTime()));
                            }
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case STRING:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setString(10, (String) a.getValue());
                            break;
                        default:
                            String msg = "Error inserting form with unknown valueType: " + a.getValueType();
                            throw new SQLException(msg);
                    }
                    ps.setBoolean(11, a.isVerify());
                    ps.setBoolean(12, a.isOptional());
                    ps.setString(13, a.getVerificationStatus().name());
                    ps.setString(14, a.getDccComment());

                    rs = ps.executeQuery();
                    if( rs.next()) {
                        a.setId( rs.getInt("id"));
                    }
                }
            }
            
            AuditLogManager alm = ServiceRegistry.getAuditLogManager();
            alm.log( f);
            
            return f.getId();
        
        } catch (SQLException ex) {
            String msg = "Error inserting form: " + f;
            logger.error( msg, ex);
            throw new PersistenceException( msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }
    
    protected void updateForm( BasicForm f) throws PersistenceException {

        Connection conn = null;
        String formSql = "update basicForm set eventId =?, formType =?, title =?, creationDate =?, status =?, sequenceNumber=?, lastsubmitteddate=? where id = ?";
        String attSql = "update formAttributes set formId =?, name =?, valueType =?, booleanValue =?, intValue =?, floatValue =?, dateValue =?, timeValue =?, timestampValue =?, stringValue =?, verify =?, optional =?, verificationstatus =?, dcccomment =? where id =?";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Update form: " + f);

        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(formSql);
            ps.setInt(1, f.getEventId());
            ps.setString(2, f.getFormType().name());
            ps.setString(3, f.getTitle());
            ps.setDate(4, new java.sql.Date(f.getDate().getTime()));
            ps.setString(5, f.getStatus().name());
            ps.setInt(6, f.getSequenceNumber());
            ps.setTimestamp(7, new java.sql.Timestamp(f.getLastSubmittedDate().getTime()));
            ps.setInt(8, f.getId());
            System.out.println("The updated BasicForm SQL statement is: " + ps.toString());
            int i = ps.executeUpdate();
            if( i != 1) {
                throw new SQLException("Failed to find form with id = " + f.getId());
            }
            
            ps = conn.prepareStatement(attSql);
            for( Attribute a: f.getAttributes()) {
                if( a.isPersistent()) {
                    ps.setInt(1, f.getId());
                    ps.setString(2, a.getName());
                    ps.setString(3, a.getValueType().getType());
                    switch(a.getValueType()) {
                        case BOOLEAN:
                            Boolean b = (Boolean) a.getValue();
                            if( b == null) {
                                ps.setNull(4, java.sql.Types.BOOLEAN);
                            } else {
                                ps.setBoolean(4, b);
                            }
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case INTEGER:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            Integer j = (Integer) a.getValue();
                            if( j == null) {
                                ps.setNull(5, java.sql.Types.INTEGER);
                            } else {
                                ps.setInt(5, j);
                            }
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case FLOAT:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            Float v = (Float) a.getValue();
                            if( v == null) {
                                ps.setNull(6, java.sql.Types.FLOAT);
                            } else {
                                ps.setFloat(6, v);
                            }
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case DATE:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            Date d = (Date) a.getValue();
                            if( d == null) {
                                ps.setNull(7, java.sql.Types.DATE);
                            } else {
                                ps.setDate(7, new java.sql.Date(d.getTime()));
                            }
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case TIME:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            Date t = (Date) a.getValue();
                            if( t == null) {
                                ps.setNull(8, java.sql.Types.TIME);
                            } else {
                                ps.setTime(8, new java.sql.Time(t.getTime()));
                            }
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case TIMESTAMP:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            Date ts = (Date) a.getValue();
                            if( ts == null) {
                                ps.setNull(9, java.sql.Types.DATE);
                            } else {
                                ps.setTimestamp(9, new java.sql.Timestamp(ts.getTime()));
                            }
                            ps.setNull(10, java.sql.Types.VARCHAR);
                            break;
                        case STRING:
                            ps.setNull(4, java.sql.Types.BOOLEAN);
                            ps.setNull(5, java.sql.Types.INTEGER);
                            ps.setNull(6, java.sql.Types.FLOAT);
                            ps.setNull(7, java.sql.Types.DATE);
                            ps.setNull(8, java.sql.Types.TIME);
                            ps.setNull(9, java.sql.Types.TIMESTAMP);
                            ps.setString(10, (String) a.getValue());
                            break;
                        default:
                            String msg = "Error inserting form with unknown valueType: " + a.getValueType();
                            throw new SQLException(msg);
                    }
                    ps.setBoolean(11, a.isVerify());
                    ps.setBoolean(12, a.isOptional());
                    ps.setString(13, a.getVerificationStatus().name());
                    ps.setString(14, a.getDccComment());
                    ps.setInt(15, a.getId());
//                    System.out.println("The updated FormAttributes SQL statement is: " + ps.toString());

                    i = ps.executeUpdate();
                    if( i != 1) {
                        throw new SQLException("Error updating form attribute " + a.getName() +  " with id = " + a.getId() + " for form with id = " + f.getId());
                    }
                }
            }
                    
            AuditLogManager alm = ServiceRegistry.getAuditLogManager();
            alm.log( f);
            
        } catch (Exception ex) {
            logger.error("Error inserting form: " + f, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of form insertion failed.", sqlex);
            }
            throw new PersistenceException("Error inserting form: " + f.getTitle(), ex);
        } finally {
            try {
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after form insertion.", sqlex);
            }
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    
    @Override
    public SimpleForm getSimpleForm( BasicForm bf) throws PersistenceException {
        SimpleForm sf = new SimpleForm( getForm(bf));
        return sf;
    }
    
    @Override
    public SimpleEligibilityForm getSimpleEligibilityForm( BasicForm bf) throws PersistenceException {
        SimpleEligibilityForm sf = new SimpleEligibilityForm( getForm(bf));
        return sf;
    }
    
    @Override
    public EligibilityForm getEligibilityForm( BasicForm bf) throws PersistenceException {
        EligibilityForm f = new EligibilityForm( getForm(bf));
        return f;
    }
    
    @Override
    public ECPTreatmentForm getECPTreatmentForm( BasicForm bf) throws PersistenceException {
        ECPTreatmentForm f = new ECPTreatmentForm( getForm(bf));
        return f;
    }
    
    @Override
    public DemoMedHistForm getDemoMedHistForm( BasicForm bf) throws PersistenceException {
        //DemoMedHistForm f = new DemoMedHistForm( getForm(bf));
        
        DemoMedHistForm f = new DemoMedHistForm( getForm(bf));
        EligibilityForm ef;
        System.out.println("In the PM.getDemoMedHistForm method, the PM.getDemoMedHistForm BasicForm bf.status is: " + bf.getStatus());
        
        if(FormStatus.NEW.equals(bf.getStatus())){
            int i = getCOEFormIDfromDemoMedHistFormEventID(bf);
            ef = new EligibilityForm();
            ef.setId(i);
            ef = getEligibilityForm(ef);
            f = updateDemoMedHistToContainCOEFormValues(f, ef); // Copy some COE values to Demographics form if available
        }
        
//        f = new ChangeTherapyForm( getForm(bf));
        
        return f;
    }
    
    @Override
    public PulmEvalForm getPulmEvalForm( BasicForm bf) throws PersistenceException {
        PulmEvalForm f = new PulmEvalForm( getForm(bf));
        return f;
    }
    
    @Override
    public CrossoverSafetyCheckForm getCrossoverSafetyCheckForm( BasicForm bf) throws PersistenceException {
        CrossoverSafetyCheckForm f = new CrossoverSafetyCheckForm( getForm(bf));
        return f;
    }
    
    @Override
    public AdverseEventWorksheetSAEForm getAdverseEventWorksheetSAEForm( BasicForm bf) throws PersistenceException {
        AdverseEventWorksheetSAEForm f = new AdverseEventWorksheetSAEForm( getForm(bf));
        return f;
    }
    
    @Override
    public BaselineTherapyForm getBaselineTherapyForm( BasicForm bf) throws PersistenceException {
        BaselineTherapyForm f = new BaselineTherapyForm( getForm(bf));
        return f;
    }
    
    @Override
    public EndOfStudyForm getEndOfStudyForm( BasicForm bf) throws PersistenceException {
        EndOfStudyForm f = new EndOfStudyForm( getForm(bf));
        return f;
    }
    
    @Override
    public QualityOfLifeForm getQualityOfLifeForm( BasicForm bf) throws PersistenceException {
        QualityOfLifeForm f = new QualityOfLifeForm( getForm(bf));
        return f;
    }
    
    @Override
    public HospitalizationForm getHospitalizationForm( BasicForm bf) throws PersistenceException {
        HospitalizationForm f = new HospitalizationForm( getForm(bf));
        return f;
    }
    
    @Override
    public DSCForm getDiseaseSpecificCategorizationForm( BasicForm bf) throws PersistenceException {
        DSCForm f = new DSCForm( getForm(bf));
        return f;
    }
    
    @Override
    public AnnualFollowUpForm getAnnualFollowUpForm( BasicForm bf) throws PersistenceException {
        AnnualFollowUpForm f = new AnnualFollowUpForm( getForm(bf));
        return f;
    }

    @Override
    public ObservePulmEvalLogForm getObservePulmEvalLogForm( BasicForm bf) throws PersistenceException {
        ObservePulmEvalLogForm f = new ObservePulmEvalLogForm( getForm(bf));
        return f;
    }
    
    public ChangeTherapyForm getPreviousChangeTherapyForm( BasicForm bf) throws PersistenceException {
        ChangeTherapyForm f = new ChangeTherapyForm( getForm(bf));
        return f;
    }
    
    public ChangeTherapyForm updateChangeTherapyFormToContainOldChangeTherapyFormValues(ChangeTherapyForm ctf, ChangeTherapyForm oldCTF) throws PersistenceException {
        logger.debug("Update JDBCPersistenceManager ChangeTherapyForm: " + ctf);

        List<String> ignoreList = Arrays.asList("prednisoneCurrent", "prednisoneCurrentDosage", "prednisoneDosageChanges",
                                                "prednisoneLowestDose", "prednisoneHighestDose", "ecpTherapyDiscontinued",
                                                "ecpTherapyDiscontinuedDate", "ecpTherapyDiscontinuedReason", "comment");
        
        try {
            for( Attribute ab: oldCTF.getAttributes()) {
                if( ab.isPersistent() && !ignoreList.contains(ab.getName())) {
                    for( Attribute ac: ctf.getAttributes()){
                        if(ab.getName().equals(ac.getName())){
                            switch(ab.getValueType()) {
                                case BOOLEAN:
                                    Boolean b = (Boolean) ab.getValue();
                                    ac.setValue(b);
                                    break;
                                case INTEGER:
                                    Integer j = (Integer) ab.getValue();
                                    ac.setValue(j);
                                    break;
                                case FLOAT:
                                    Float v = (Float) ab.getValue();
                                    ac.setValue(v);
                                    break;
                                case DATE:
                                    Date d = (Date) ab.getValue();
                                    ac.setValue(d);
                                    break;
                                case STRING:
                                    ac.setValue(ab.getValue());
                                    break;
                                default:
                                    String msg = "Error updating ChangeTherapyFrom from BaselineTherapyForm with unknown valueType: " + ab.getValueType();
                                    throw new SQLException(msg);
                            }
                        }
                    }
                }
            }
                    
        } catch (Exception ex) {
            logger.error("Error updating ChangeTherapyForm from previous ChangeTherapyForm: " + oldCTF, ex);
        }
        return ctf;
    }
    
    public DemoMedHistForm updateDemoMedHistToContainCOEFormValues(DemoMedHistForm dmh, EligibilityForm e) {
        // List of fields to copy over
        List<String> copyList = Arrays.asList("baselineFEV1",
                                              "firstComponentFEV1", "firstComponentFEV1Date", "firstComponentFVC",
                                              "secondComponentFEV1", "secondComponentFEV1Date", "secondComponentFVC",
                                              "lungTransplantationDate", "postTransBOSDiagDate");
        
        logger.debug("Update JDBCPersistenceManager DemoMedHistForm: " + dmh);
        
        try {
            for( Attribute ab : e.getAttributes()) {
                if( ab.isPersistent() && copyList.contains(ab.getName())) {
                    for( Attribute ac: dmh.getAttributes()){
                        if(ab.getName().equals(ac.getName())){
                            switch(ab.getValueType()) {
                                case BOOLEAN:
                                    Boolean b = (Boolean) ab.getValue();
                                    ac.setValue(b);
                                    break;
                                case INTEGER:
                                    Integer j = (Integer) ab.getValue();
                                    ac.setValue(j);
                                    break;
                                case FLOAT:
                                    Float v = (Float) ab.getValue();
                                    ac.setValue(v);
                                    break;
                                case DATE:
                                    Date d = (Date) ab.getValue();
                                    ac.setValue(d);
                                    break;
                                case STRING:
                                    ac.setValue(ab.getValue());
                                    break;
                                default:
                                    String msg = "Error updating DemoMedHistForm from COEForm with unknown valueType: " + ab.getValueType();
                                    throw new SQLException(msg);
                            }
                        }
                    }
                }
            }
                    
        } catch (Exception ex) {
            logger.error("Error updating DemoMedHistForm from COEForm: " + dmh, ex);
        }
        
        return dmh;
    }
    
    // Copy most of the baseline therapy values to the change in therapy form    
    public ChangeTherapyForm updateChangeTherapyFormToContainBaselineTherapyFormValues(ChangeTherapyForm ctf, BaselineTherapyForm btf) throws PersistenceException {
        logger.debug("Update JDBCPersistenceManager ChangeTherapyForm: " + ctf);

        List<String> ignoreList = Arrays.asList("prednisoneCurrent", "prednisoneCurrentDosage", "prednisoneDosageChanges",
                                                "prednisoneLowestDose", "prednisoneHighestDose", "comment");
        
        try {
            for( Attribute ab: btf.getAttributes()) {
                if( ab.isPersistent() && !ignoreList.contains(ab.getName())) {
                    for( Attribute ac: ctf.getAttributes()){
                        if(ab.getName().equals(ac.getName())){
                            switch(ab.getValueType()) {
                                case BOOLEAN:
                                    Boolean b = (Boolean) ab.getValue();
                                    ac.setValue(b);
                                    break;
                                case INTEGER:
                                    Integer j = (Integer) ab.getValue();
                                    ac.setValue(j);
                                    break;
                                case FLOAT:
                                    Float v = (Float) ab.getValue();
                                    ac.setValue(v);
                                    break;
                                case DATE:
                                    Date d = (Date) ab.getValue();
                                    ac.setValue(d);
                                    break;
                                case STRING:
                                    ac.setValue(ab.getValue());
                                    break;
                                default:
                                    String msg = "Error updating ChangeTherapyFrom from BaselineTherapyForm with unknown valueType: " + ab.getValueType();
                                    throw new SQLException(msg);
                            }
                        }
                    }
                }
            }
                    
        } catch (Exception ex) {
            logger.error("Error updating ChangeTherapyForm from BaselineTherapyForm: " + btf, ex);
        }
        return ctf;
    }
    
    public int getCOEFormIDfromDemoMedHistFormEventID(BasicForm bf) throws PersistenceException {
        Connection conn = null;
        String sql = "select id from basicform where eventid = (select id from event where name = 'Confirmation of Eligibility' and participantid = (select participantid from event where id = ?));";

        PreparedStatement ps = null;
        ResultSet rs;
        String coeFormID = "";

        logger.debug("Get Form with id = " + bf.getEventId());

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bf.getEventId());
            rs = ps.executeQuery();
            while (rs.next()) {
                coeFormID = rs.getString("id");
            }
            System.out.println("The COE ID is: " + coeFormID);
            int i = Integer.parseInt(coeFormID);
            return i;
        } catch (SQLException ex) {
            logger.error("Error selecting COE with DemoMedHist event id = " + bf.getEventId(), ex);
            throw new PersistenceException("Error selecting DemoMedHist with event id = " + bf.getEventId(), ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }            
    }
    
    public int getBaselineTherapyFormIDfromChangeTherapyFormEventID(BasicForm bf) throws PersistenceException {
        Connection conn = null;
        String sql = "select id from basicform where eventid = (select id from event where name = 'Baseline Therapy' and participantid = (select participantid from event where id = ?));";

        PreparedStatement ps = null;
        ResultSet rs;
        String baselineTherapyFormID = "";

        logger.debug("Get Form with id = " + bf.getEventId());

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bf.getEventId());
            rs = ps.executeQuery();
            while (rs.next()) {
                baselineTherapyFormID = rs.getString("id");
            }
            System.out.println("The BaselineTherapyForm ID is: " + baselineTherapyFormID);
            int i = Integer.parseInt(baselineTherapyFormID);
            return i;
        } catch (SQLException ex) {
            logger.error("Error selecting BaselineTherapyForm with ChangeTherapyForm event id = " + bf.getEventId(), ex);
            throw new PersistenceException("Error selecting ChangeTherapyForm with event id = " + bf.getEventId(), ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }            
    }

    @Override
    public ChangeTherapyForm getChangeTherapyForm( BasicForm bf) throws PersistenceException {
        ChangeTherapyForm ctf;
        ChangeTherapyForm oldCTF;
        ChangeTherapyForm oldCTF2;
        BaselineTherapyForm btf;
        int originalBasicFormID = bf.getId();
        int changeTherapyFormCount = getChangeTherapyFormCount(originalBasicFormID, bf.getFormType());
        System.out.println("In the PM.getChangeTherapyForm method, the PM.getChangeTherapyFormCount is : " + changeTherapyFormCount);
        System.out.println("In the PM.getChangeTherapyForm method, the PM.getChangeTherapyForm BasicForm bf.status is: " + bf.getStatus());
        
        List<Integer> changeTherapyFormIds = getChangeTherapyFormIds(originalBasicFormID, bf.getFormType());
        int index = changeTherapyFormIds.indexOf(originalBasicFormID);
        
        if(FormStatus.NEW.equals(bf.getStatus()) && (index == 0)){
            ctf = new ChangeTherapyForm( getForm(bf));
            int i = getBaselineTherapyFormIDfromChangeTherapyFormEventID(bf);
            btf = new BaselineTherapyForm();
            btf.setId(i);
            btf = getBaselineTherapyForm(btf);
            ctf = updateChangeTherapyFormToContainBaselineTherapyFormValues(ctf, btf);  // Copy most of the baseline therapy values to the change in therapy form
        } else {  
            if((FormStatus.NEW.equals(bf.getStatus())) && (index >= 1)){
                ctf = new ChangeTherapyForm( getForm(bf));
                int previousChangeTherapyFormId = changeTherapyFormIds.get(index - 1);
                oldCTF = new ChangeTherapyForm();
                oldCTF.setId(previousChangeTherapyFormId);
                BasicForm oldBF = getForm(oldCTF);
                oldCTF2 = new ChangeTherapyForm(oldBF);
                ctf = updateChangeTherapyFormToContainOldChangeTherapyFormValues(ctf, oldCTF2);
            } else { 
                ctf = new ChangeTherapyForm( getForm(bf));
            }
        }
//        f = new ChangeTherapyForm( getForm(bf));
        return ctf;
    }
    
    public List<Integer> getChangeTherapyFormIds(int originalBasicFormID, ECPFormTypes formType) throws PersistenceException {
        Connection conn = null;
        String sql = "select basicform.id from basicform, event where event.id = basicform.eventid and basicform.formtype like ? and event.participantid = (select participantid from event where id = (select eventid from basicform where id = ?)) order by event.id asc;";

        PreparedStatement ps = null;
        ResultSet rs;
        List<Integer> therapyFormIds = new ArrayList<Integer>();

//        logger.debug("Get Form with id = " + bf.getEventId());
 
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, formType.toString());
            ps.setInt(2, originalBasicFormID);
            rs = ps.executeQuery();
            while (rs.next()) {
                therapyFormIds.add(rs.getInt("id"));
                logger.debug("Found previous change in therapy " + rs.getString("id"));
            }
            //System.out.println("In the PM.getPreviousChangeTherapyFormID method, the previousChangeTherapyFormIDstr is: " + previousChangeTherapyFormIDstr);
            //return previousChangeTherapyFormID;
            return therapyFormIds;
        } catch (SQLException ex) {
            logger.error("Error in fetching change in therapy form IDs", ex);
            throw new PersistenceException("Error in fetching change in therapy form IDs", ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
    
    public int getChangeTherapyFormCount(int originalBasicFormID, ECPFormTypes formType) throws PersistenceException {
        Connection conn = null;
//        String sql = "select count(id) as count from event where eventtype like 'CHANGE_THERAPY';";
        String sql = "select count(id) as count from event where eventtype like ? and participantid = (select participantid from event where id = (select eventid from basicform where id = ?));";
        PreparedStatement ps = null;
        ResultSet rs;
        String changeTherapyFormCount = "";
        int i = 0;

//        logger.debug("Get Form with id = " + bf.getEventId());

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, formType.toString());
            ps.setInt(2, originalBasicFormID);
            rs = ps.executeQuery();
            while (rs.next()) {
                changeTherapyFormCount = rs.getString("count");
            }
            System.out.println("In the PM.getChangeTherapyFormCount method, the ChangeTherapyForm count is: " + changeTherapyFormCount);
            i = Integer.parseInt(changeTherapyFormCount);
            return i;
        } catch (SQLException ex) {
            logger.error("Error in determining the changeTherapyFormCount " + i, ex);
            throw new PersistenceException("Error in determining the changeTherapyFormCount " + i, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }            
    }

    public int getPreviousChangeTherapyFormID(int changeTherapyFormCount, int originalBasicFormID, ECPFormTypes formType) throws PersistenceException {
        Connection conn = null;
        String sql = "select basicform.id from basicform, event where event.id = basicform.eventid and basicform.formtype like ? and event.participantid = (select participantid from event where id = (select eventid from basicform where id = ?)) order by event.id asc limit 1 offset ?;";

        PreparedStatement ps = null;
        ResultSet rs;
        String previousChangeTherapyFormIDstr = "";
        int previousChangeTherapyFormID = 0;

//        logger.debug("Get Form with id = " + bf.getEventId());
 
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            int offset = changeTherapyFormCount - 2;
            ps.setString(1, formType.toString());
            ps.setInt(2, originalBasicFormID);
            ps.setInt(3, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                previousChangeTherapyFormIDstr = rs.getString("id");
            }
            System.out.println("In the PM.getPreviousChangeTherapyFormID method, the previousChangeTherapyFormIDstr is: " + previousChangeTherapyFormIDstr);
            previousChangeTherapyFormID = Integer.parseInt(previousChangeTherapyFormIDstr);
            return previousChangeTherapyFormID;
        } catch (SQLException ex) {
            logger.error("Error in determining the previousChangeTherapyFormID " + previousChangeTherapyFormID, ex);
            throw new PersistenceException("Error in determining the previousChangeTherapyFormID " + previousChangeTherapyFormID, ex);
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
    public int saveDocument(BasicForm f, String name, InputStream is, String sourceDocType) throws PersistenceException {

        Connection conn = null;
        String sql = "insert into documents (formId, name, submissionDate, lo_oid, sourcedoctype) values (?,?,?,?,?) returning id;";
        PreparedStatement ps = null;
        ResultSet rs;

        try {
            conn = ds.getConnection();

            if( conn.isWrapperFor(Jdbc4Connection.class)) {
                org.postgresql.jdbc4.Jdbc4Connection jpconn = conn.unwrap(org.postgresql.jdbc4.Jdbc4Connection.class);
                LargeObjectManager lom = jpconn.getLargeObjectAPI();
                // All LargeObject API calls must be within a transaction.
                conn.setAutoCommit(false);

                // create new large object
                long oid = lom.createLO(LargeObjectManager.READWRITE);
                LargeObject obj = lom.open(oid, LargeObjectManager.WRITE);

                // Copy the data from the file to the large object
                byte buf[] = new byte[4096];
                int s, tl = 0;
                while ((s = is.read(buf, 0, 4096)) > 0) {
                    obj.write(buf, 0, s);
                    tl += s;
                }

                // Close the large object
                obj.close();

                ps = conn.prepareStatement(sql);
                ps.setInt(1, f.getId());
                ps.setString(2, name);
                ps.setDate(3, new java.sql.Date(new Date().getTime()));
                ps.setLong(4, oid);
                ps.setString(5, sourceDocType);

                int id = -1;
                rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("id");
                }

                conn.commit();

                return id;
            }
            else {
                StringBuffer msg = new StringBuffer();
                msg.append("Error inserting document: ").append(name);
                msg.append(" Require driver of type 'org.postgresql.jdbc4.Jdbc4Connection'");
                msg.append(" Encountered type '").append(conn.getClass().getName()).append("'");
                logger.error( msg.toString());
                throw new PersistenceException(msg.toString());
            }

        } catch (Exception ex) {
            logger.error("Error inserting document: " + name, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of document insertion failed.", sqlex);
            }
            throw new PersistenceException("Error inserting document: " + name, ex);
        } finally {
            IOUtils.closeQuietly(is);
            try {
                if( ps != null) { try {ps.close();} catch (SQLException ignore) {}}
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after document insertion.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public List<Document> getDocuments(BasicForm f) throws PersistenceException {

        Connection conn = null;
        String sql = "select * from documents where formid = ? order by submissionDate asc;";
        
        PreparedStatement ps = null;
        ResultSet rs;
        List<Document> docs = new ArrayList<Document>();

        logger.debug("Get all documents for form id = " + f.getId());

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, f.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Document doc = new Document();
                doc.setId( rs.getInt("id"));
                doc.setFormId( rs.getInt("formId"));
                doc.setName( rs.getString("name"));
                doc.setSubmissionDate( rs.getDate("submissionDate"));
                doc.setSourceDocumentType(rs.getString("sourcedoctype"));
                docs.add( doc);
            }

            return docs;
        } catch (Exception ex) {
            String msg = "Error selecting all documents for form with id = " + f.getId();
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public void writeDocument(int id, OutputStream os) throws PersistenceException {

        Connection conn = null;
        String sql = "select lo_oid from documents where id = ?;";
        PreparedStatement ps = null;
        ResultSet rs;

        try {
            conn = ds.getConnection();
            if( conn.isWrapperFor(Jdbc4Connection.class)) {
                org.postgresql.jdbc4.Jdbc4Connection jpconn = conn.unwrap(org.postgresql.jdbc4.Jdbc4Connection.class);
                LargeObjectManager lom = jpconn.getLargeObjectAPI();
                // All LargeObject API calls must be within a transaction.
                conn.setAutoCommit(false);

                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                rs = ps.executeQuery();
                if (rs.next()) {
                    long oid = rs.getLong("lo_oid");
                    LargeObject obj = lom.open(oid, LargeObjectManager.READ);

                    byte buf[] = new byte[4096];
                    int s, tl = 0;
                    while ((s = obj.read(buf, 0, 4096)) > 0) {
                        os.write(buf, 0, s);
                        tl += s;
                    }

                    // Close the object
                    obj.close();
                } else {
                    logger.warn("Failed to find document with id = " + id);
                }

                conn.commit();
            }
            else {
                StringBuffer msg = new StringBuffer();
                msg.append("Error retrieving document. ");
                msg.append(" Require driver of type 'org.postgresql.jdbc4.Jdbc4Connection'");
                msg.append(" Encountered type '").append(conn.getClass().getName()).append("'");
                logger.error( msg.toString());
                throw new PersistenceException(msg.toString());
            }

        } catch (Exception ex) {
            String msg = "Error reading document with id = " + id;
            logger.error( msg, ex);
            try {
                if( conn != null) conn.rollback();
            }
            catch( SQLException sqlex) {
                throw new PersistenceException("Rollback of document read failed.", sqlex);
            }
            throw new PersistenceException( msg, ex);
        } finally {
            try {
                if( ps != null) { try {ps.close();} catch (SQLException ignore) {}}
                if( conn != null) conn.setAutoCommit(true);
            }
            catch(SQLException sqlex) {
                throw new PersistenceException("Error restoring autocommit after document insertion.", sqlex);
            }
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }

    @Override
    public void deleteDocument(int id) throws PersistenceException {

        Connection conn = null;
        String sql = "delete from documents where id = ?;";
        PreparedStatement ps = null;

        try {
            conn = ds.getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
        
        } catch (SQLException ex) {
            String msg = "Error deleting document with id = " + id;
            logger.error( msg, ex);
            throw new PersistenceException( msg, ex);
        } finally {
            if( ps != null) { try {ps.close();} catch (SQLException ignore) {}}
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
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
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
                p.setSpirometryStrata( SpirometryStrata.valueOf(rs.getString("spirometrystrata")));
                p.setDeclineStrata( DeclineStrata.valueOf(rs.getString("declinestrata")));
                p.setHoldStatus( rs.getBoolean("holdstatus"));
                p.setHoldStartDate( rs.getDate("holdstartdate"));
                p.setShowStudyArm( rs.getBoolean("showstudyarm"));
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
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }
	
    
    @Override
    public void insertUserLoginInformation(int siteIdDeterminedFromUsername, String loginUsername, String remoteIPAddress, Date loginDate) throws PersistenceException{

        Connection conn = null;
        String userLoginInfoSql = "insert into logininfo (siteid, username, ipaddress, logindate) values (?,?,?,?) returning id;";

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("Insert userLoginInformation sql statement: " + userLoginInfoSql);

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(userLoginInfoSql);
            ps.setInt(1, siteIdDeterminedFromUsername);
            ps.setString(2, loginUsername);
            ps.setString(3, remoteIPAddress);
            ps.setTimestamp(4, new java.sql.Timestamp(loginDate.getTime()));

            System.out.println("The insertForm SQL statement is: " + ps.toString());
            rs = ps.executeQuery();
        
        } catch (SQLException ex) {
            String msg = "Error inserting userLoginInformation sql statement!!!!";
            logger.error( msg, ex);
            throw new PersistenceException( msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }

    }

    
    @Override
    public int getVersionControlId(Date protocolDate, String protocolVersionNumber, Date crfReleaseDate, Date crfUpdate, String crfVersionNumber, Date softwareReleaseDate, String softwareVersionNumber) throws PersistenceException {
        
        Connection conn = null;
        String sql = "select id from versioncontrol where protocolDate = ? and protocolvernum = ? and crfReleaseDate = ? and crfUpdate = ? and crfVerNum = ? and softwareReleaseDate = ? and softwareVerNum = ?;";
        PreparedStatement ps = null;
        ResultSet rs;
        int versionControlId = 0;
        
//        logger.debug("Select participant with pid = " + pid);
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(protocolDate.getTime()));
            ps.setString(2, protocolVersionNumber);
            ps.setDate(3, new java.sql.Date(crfReleaseDate.getTime()));
            ps.setDate(4, new java.sql.Date(crfUpdate.getTime()));
            ps.setString(5, crfVersionNumber);
            ps.setDate(6, new java.sql.Date(softwareReleaseDate.getTime()));
            ps.setString(7, softwareVersionNumber);
            System.out.println("The select versioncontrol SQL statement is: " + ps.toString());

            rs = ps.executeQuery();
            if( !rs.isBeforeFirst()) {
                return versionControlId;
            } else if( rs.next()) {
                versionControlId = rs.getInt("id");
            }
           
            return versionControlId;
        } catch (SQLException ex) {
            String msg = "Failed to find version control id = " + versionControlId;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }

    }

    @Override
    public void insertVersionControlId(Date protocolDate, String protocolVersionNumber, Date crfReleaseDate, Date crfUpdate, String crfVersionNumber, Date softwareReleaseDate, String softwareVersionNumber) throws PersistenceException {
        
        Connection conn = null;
        String sql = "insert into versioncontrol (protocolDate, protocolvernum, crfReleaseDate, crfUpdate, crfVerNum, softwareReleaseDate, softwareVerNum) values (?,?,?,?,?,?,?) returning id;;";
        PreparedStatement ps = null;
        ResultSet rs;
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(protocolDate.getTime()));
            ps.setString(2, protocolVersionNumber);
            ps.setDate(3, new java.sql.Date(crfReleaseDate.getTime()));
            ps.setDate(4, new java.sql.Date(crfUpdate.getTime()));
            ps.setString(5, crfVersionNumber);
            ps.setDate(6, new java.sql.Date(softwareReleaseDate.getTime()));
            ps.setString(7, softwareVersionNumber);
            System.out.println("The insert versioncontrol SQL statement is: " + ps.toString());

            rs = ps.executeQuery();
           
        } catch (SQLException ex) {
            String msg = "Failed to insert version control information";
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }

    }
    
    @Override
    public void setShowArm( String pid, boolean showStudyArm) throws PersistenceException {
        logger.debug("Inside setShowArm method");
        
        Connection conn = null;
        String showarmsql = "update participant set showstudyarm=? where participantid =?;";
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Update participant " + pid + " to showarm = " + showStudyArm);
        
        try {
            conn = ds.getConnection();
            
            ps = conn.prepareStatement(showarmsql);
            ps.setBoolean(1, showStudyArm);
            ps.setString(2, pid);

            int i = ps.executeUpdate();
            if( i != 1) {
                throw new SQLException("Error updating participant " + pid + " to showarm = " + showStudyArm);
            }
            
            logger.debug("I guess it worked");
            
            return;
        } catch (Exception ex) {
            String msg = "Error updating participant " + pid + " to showarm = " + showStudyArm;
            logger.error(msg, ex);
            throw new PersistenceException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
        }
    }

}