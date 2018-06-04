package edu.wustl.mir.ctt.directory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.io.ResourceUtils;

/**
 *
 * @author drm
 */
public class LDAPDirectoryManager implements DirectoryManager {
    private Hashtable<String, String> env;

    private static final Logger logger = Logger.getLogger(LDAPDirectoryManager.class);

    private static LDAPDirectoryManager ldapDirectoryManager = null;

    public static LDAPDirectoryManager getDirectoryManager() throws DirectoryManagerException {
        if( ldapDirectoryManager == null) {
            InputStream is = null;
            try {
                is = ResourceUtils.getInputStreamForPath("file:/etc/ecp/shiro.ini");
                Ini ini = new Ini();
                ini.load( is);
                String ldapURL = ini.getSectionProperty( "main", "contextFactory.url");
                String systemUsername = ini.getSectionProperty( "main", "contextFactory.systemUsername");
                String systemPassword = ini.getSectionProperty( "main", "contextFactory.systemPassword");
                ldapDirectoryManager = new LDAPDirectoryManager(ldapURL, systemUsername, systemPassword);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(LDAPDirectoryManager.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(LDAPDirectoryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ldapDirectoryManager;
    }

    private LDAPDirectoryManager(String ldapURL, String securityPrincipal, String securityCredentials) {
        env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapURL);

        // Enable connection pooling
        env.put("com.sun.jndi.ldap.connect.pool", "true");

        if( securityPrincipal == null || "".equals(securityPrincipal)) {
            env.put(Context.SECURITY_AUTHENTICATION, "none");
        }
        else {
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
            env.put(Context.SECURITY_CREDENTIALS, securityCredentials);
        }
    }
    
    /**
     * Return the login name of the current user.
     * 
     * @return the username
     */
    @Override
    public String getUserName() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * Return the name of the current user's site.
     * 
     * @return
     * @throws DirectoryManagerException 
     */
    @Override
    public String getSiteName() throws DirectoryManagerException {
        String siteName = null;
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        
        if( userName != null) {
            List<String> siteNames = getSiteName(userName);
            if( siteNames.size() > 0) {
                siteName = siteNames.get(0);
            }
            if( siteNames.size() > 1) {
                logger.warn("user " + userName + " is associated with multiple sites: " + siteNames);
            }
            if( siteNames.size() == 0) {
                logger.warn("user " + userName + " is not associated with any site.");
            }
        }
        
        return siteName;
    }

    private List<String> getSiteName( String userName) throws DirectoryManagerException {
        try {
            User user = getAccount( userName);
            return user.getSiteNames();
        } catch (Exception e) {
            String msg = "Error reading site names from LDAP directory.";
            throw new DirectoryManagerException( msg, e);
        }
    }

    @Override
    public List<User> getAccounts() throws DirectoryManagerException {
        LdapContext ctx = null;
        NamingEnumeration<?> namingEnum = null;
        try {
            List<User> users = new ArrayList<User>();

            ctx = new InitialLdapContext(env, null);

            namingEnum = ctx.search("ou=users,ou=ECP Registry,dc=nrg,dc=mir", "(objectclass=*)", getSimpleSearchControls());

            while (namingEnum.hasMore()) {
                User user = new User();
                SearchResult result = (SearchResult) namingEnum.next();
                Attributes attrs = result.getAttributes();
                Attribute att = attrs.get("givenName");
                user.setFirstName( (att == null) ? "null" : (String) att.get());
                att = attrs.get("sn");
                user.setLastName( (att == null) ? "null" : (String) att.get());
                att = attrs.get("cn");
                user.setUserName( (att == null) ? "null" : (String) att.get());
                att = attrs.get("o");
                user.setOrganization( (att == null) ? "null" : (String) att.get());
                att = attrs.get("mail");
                user.setEmail( (att == null) ? "null" : (String) att.get());
                user.setSiteNames( getMemberOfSites( attrs));
                user.setRoleNames( getMemberOfRoles( attrs));
                users.add( user);
            }
            return users;
        } catch (Exception e) {
            String msg = "Error reading accounts from LDAP directory.";
            throw new DirectoryManagerException( msg, e);
        }
        finally {
            if( namingEnum != null) try {namingEnum.close();} catch(NamingException ignore) {}
            if( ctx != null) try {ctx.close();} catch(NamingException ignore) {}
        }
    }

    @Override
    public User getAccount( String userName) throws DirectoryManagerException {
        LdapContext ctx = null;
        NamingEnumeration<?> namingEnum = null;
        try {

            ctx = new InitialLdapContext(env, null);
            logger.debug("Get account for user " + userName + " in context " + ctx);

            namingEnum = ctx.search("ou=users,ou=ECP Registry,dc=nrg,dc=mir", "(sAMAccountName=" + userName+ ")", getSimpleSearchControls());

            User user = new User();
            while (namingEnum.hasMore()) {
                SearchResult result = (SearchResult) namingEnum.next();
                Attributes attrs = result.getAttributes();
                Attribute att = attrs.get("givenName");
                user.setFirstName( (att == null) ? "null" : (String) att.get());
                att = attrs.get("sn");
                user.setLastName( (att == null) ? "null" : (String) att.get());
                att = attrs.get("cn");
                user.setUserName( (att == null) ? "null" : (String) att.get());
                att = attrs.get("o");
                user.setOrganization( (att == null) ? "null" : (String) att.get());
                att = attrs.get("mail");
                user.setEmail( (att == null) ? "null" : (String) att.get());
                user.setSiteNames( getMemberOfSites( attrs));
                user.setRoleNames( getMemberOfRoles( attrs));
            }
            return user;
        } catch (Exception e) {
            String msg = "Error reading accounts from LDAP directory.";
            throw new DirectoryManagerException( msg, e);
        }
        finally {
            if( namingEnum != null) try {namingEnum.close();} catch(NamingException ignore) {}
            if( ctx != null) try {ctx.close();} catch(NamingException ignore) {}
        }
    }

    /**
     * Return the name of the site to which this user is a member.
     *
     * The user's attributes can contain multiple 'memberof' entries as the user is both a member of groups and sites.
     * Filter the 'memberof' list for entries of type 'OU=sites'.
     *
     * @param userAttributes
     * @return List of site names to which this user is a member.
     * @throws NamingException
     */
    private List<String> getMemberOfSites( Attributes userAttributes) throws NamingException {
        Attribute attribute = userAttributes.get("memberof");
        List<String> siteNames = new ArrayList<>();
        if( attribute != null) {
            NamingEnumeration<?> all = attribute.getAll();
            while (all.hasMore()) {
                String value = (String) all.next();
                if( value.contains("OU=Sites,")) {
                    String[] tokens = value.split(",");
                    if( tokens[0] != null && tokens[0].startsWith("CN=")) {
                        siteNames.add( tokens[0].substring(3));
                        break;
                    }
                }
            }
        }
        return siteNames;
    }

    /**
     * Return the name of the roles to which this user is a member.
     *
     * The user's attributes can contain multiple 'memberof' entries as the user is both a member of groups and sites.
     * Filter the 'memberof' list for entries of type 'OU=Groups'.
     *
     * @param userAttributes
     * @return List of site names to which this user is a member.
     * @throws NamingException
     */
    private List<String> getMemberOfRoles( Attributes userAttributes) throws NamingException {
        Attribute attribute = userAttributes.get("memberof");
        List<String> siteNames = new ArrayList<>();
        if( attribute != null) {
            NamingEnumeration<?> all = attribute.getAll();
            while (all.hasMore()) {
                String value = (String) all.next();
                if( value.contains("OU=Groups,")) {
                    String[] tokens = value.split(",");
                    if( tokens[0] != null && tokens[0].startsWith("CN=")) {
                        siteNames.add( tokens[0].substring(3));
                        break;
                    }
                }
            }
        }
        return siteNames;
    }

    @Override
    public void printAccounts(PrintStream ps) throws DirectoryManagerException {
        List<User> users = getAccounts();
        for( User user: users) {
            String s = user.getFirstName();
            ps.println("First Name: " + ((s == null) ? "null" : s));
            s = user.getLastName();
            ps.println("Last Name: " + ((s == null) ? "null" : s));
            s = user.getUserName();
            ps.println("User Name: " + ((s == null) ? "null" : s));
            s = user.getOrganization();
            ps.println("Organization: " + ((s == null) ? "null" : s));
            s = user.getEmail();
            ps.println("Email: " + ((s == null) ? "null" : s));
            ps.println();
        }
    }

    private static SearchControls getSimpleSearchControls() {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setTimeLimit(30000);
        //String[] attrIDs = {"objectGUID"};
        //searchControls.setReturningAttributes(attrIDs);
        return searchControls;
    }

    /**
     * Test
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        try {
//            LDAPDirectoryManager ldap = new LDAPDirectoryManager("ldaps://10.28.16.21", "ecp_ldap@nrg.mir", "OLD Key");

//            System.out.println( ldap.getAccounts());
//            System.out.println(ldap.getAccount("drm_sc"));

//            System.out.println( ldap.getSiteName("foo"));
//            ldap.printAccounts(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

