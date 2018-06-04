package edu.wustl.mir.ctt.auditlog;

import edu.wustl.mir.ctt.form.BasicForm;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;

/**
 * An audit log manager using JDBC to a RDMS data base.
 * 
 * @author drm
 */
public class JDBCAuditLogManager implements AuditLogManager {

    private DataSource ds;
    private String userName;
    private Integer formID;
    private String formName;
    
    private static final Logger logger = Logger.getLogger(JDBCAuditLogManager.class);

    /**
     * Use the Web Container to manage the DataSource.
     *
     * @throws AuditLogException
     */
    public JDBCAuditLogManager() throws AuditLogException {
        try {
            InitialContext ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ecpreg_db");
            if (ds == null) {
                String msg = "JDBCAuditLogManager failed to find DataSource: jdbc/ecpreg_db"; // Production Mode
                logger.error(msg);
                throw new AuditLogException(msg);
            }
            logger.info("Instantiated datasource: jdbc/ecpreg_db");
        } catch (NamingException e) {
            String msg = "JDBCAuditLogManager failed to create Naming Context: java:comp/env";
            logger.error(msg, e);
            throw new AuditLogException(msg, e);
        }
    }
    
    @Override
    public void log( Object obj) throws AuditLogException {
        
        if( obj instanceof BasicForm) {
            BasicForm f = (BasicForm) obj;
            formID = f.getId();
            formName = f.getTitle();
        }
        else {
            formID = null;
            formName = null;
        }
        
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        log( userName, obj);
    }
    
    private static final String writeLogEntrySQL =
        "insert into auditLog "
      + " (user_name, submission_timestamp, form_id, form_name, class_name, object_value) "
      + " values (?, ?, ?, ?, ?, ?) returning id;";

    private static final String readLogEntrySQL =
        "select "
      + " user_name, submission_timestamp, form_id, form_name, class_name, object_value "
      + " from auditLog where id = ?;";

    private void log(String userName, Object obj) throws AuditLogException {
        Connection conn = null;
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        logger.debug("Insert audit log entry = " + obj);
        
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement( writeLogEntrySQL);
            ps.setString(1, userName);
            ps.setTimestamp(2, new java.sql.Timestamp((new Date()).getTime()));
            ps.setInt(3, formID);
            ps.setString(4, formName);
            ps.setString(5, obj.getClass().getName());
            
            // Boo.  Postgresql does not support Types.JAVA_OBJECT
            // Use byteArrayInputStream approach.
            // Nor does it support the version of setBinaryStream that
            // doesn't need the size of the byte array.
//            ps.setObject(6, obj, Types.JAVA_OBJECT);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos);
            oos.writeObject(obj);
            oos.close();
            byte[] byteArray = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream( byteArray);
            ps.setBinaryStream(6, bais, byteArray.length);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                // we get the record's id but ignore it.
                int id = rs.getInt("id");
            }
        } catch (SQLException | IOException ex) {
            String msg = "Error inserting log entry.";
            logger.error(msg, ex);
            throw new AuditLogException(msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }
    }   

    public Object readObject( int id) throws AuditLogException {
        Connection conn = null;

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("read auditLog object with id = " + id);

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement( readLogEntrySQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            Object obj = null;
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("object_value");
                ObjectInputStream ois = new ObjectInputStream( is);
                obj = ois.readObject();
            }
            return obj;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            String msg = "Error reading AuditLog object with id = " + id;
            logger.error( msg, ex);
            throw new AuditLogException( msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }     
    }
    
    public static Object readObject( Connection conn, int id) throws AuditLogException {

        PreparedStatement ps = null;
        ResultSet rs;

        logger.debug("read auditLog object with id = " + id);

        try {
            ps = conn.prepareStatement( readLogEntrySQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            Object obj = null;
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("object_value");
                ObjectInputStream ois = new ObjectInputStream( is);
                obj = ois.readObject();
            }
            return obj;
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            String msg = "Error reading AuditLog object with id = " + id;
            logger.error( msg, ex);
            throw new AuditLogException( msg, ex);
        } finally {
            if (ps != null) { try {ps.close();} catch (SQLException ignore) {}}
            if (conn != null) { try {conn.close();} catch (SQLException ignore) {}}
        }            
    }
    
    public static void main(String[] args) {
        try {
            Connection conn;
            conn = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/ecpreg_prod_db?user=postgres");
            
            Object obj = JDBCAuditLogManager.readObject( conn, 953);
//            System.out.println( ToStringBuilder.reflectionToString(obj,ToStringStyle.MULTI_LINE_STYLE ));
            System.out.println( ReflectionToStringBuilder.toString(obj,RecursiveToStringStyle.MULTI_LINE_STYLE ));
        } catch (SQLException | AuditLogException ex) {
//            logger.error( null, ex);
            ex.printStackTrace();
        }
        
    }
}
