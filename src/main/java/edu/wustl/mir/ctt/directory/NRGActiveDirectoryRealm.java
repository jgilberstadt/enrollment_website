package edu.wustl.mir.ctt.directory;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import org.apache.isis.security.shiro.util.Util;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.activedirectory.ActiveDirectoryRealm;
import org.apache.shiro.realm.ldap.LdapContextFactory;
import org.apache.shiro.realm.ldap.LdapUtils;
import org.apache.shiro.subject.PrincipalCollection;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ActiveDirectoryRealm for NRG environment.
 *
 * 1. Adds @nrg.mir domain to usernames so users do not have to supply this when logging in.
 *
 * 2. Adapts the method used to set roles and permissions from shiro.ini that is used in IsisLdapDirectoryRealm.
 *
 * This object is instantiated when tomcat loads the app. Shiro.ini is reqd and the rolesByGroup and permissionsByRole
 * properties are set.
 *
 * Role names per user are cached to minimize the hits on the LDAP.
 *
 */
public class NRGActiveDirectoryRealm extends ActiveDirectoryRealm {

    private final Map<String,String> rolesByGroup = Maps.newLinkedHashMap();
    private final Map<String,List<String>> permissionsByRole = Maps.newLinkedHashMap();
    private final RoleCache roleCache;

    private static Logger logger = Logger.getLogger(NRGActiveDirectoryRealm.class);

    public NRGActiveDirectoryRealm() {
        roleCache = new RoleCache();
        logger.info("NRGActiveDirectory instantiated.");
    }

    /**
     * Provide AuthenticationInfo for the specified user.
     *
     * Shiro calls this every time it needs to authenticate a user. The user is authenticated if they can bind to the
     * ldap context with the specified credentials.
     *
     * @param token
     * @param ldapContextFactory
     * @return
     * @throws NamingException
     */
    @Override
    protected AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken token, LdapContextFactory ldapContextFactory) throws NamingException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String domainUserName = upToken.getUsername() + "@nrg.mir";

        // Search for entry by the username and password provided by the user.
        LdapContext ctx = null;
        try {
            logger.debug("Authenticating user " + domainUserName);
            ctx = ldapContextFactory.getLdapContext(domainUserName, String.valueOf(upToken.getPassword()));
            logger.debug("User " + domainUserName + " successfully bound to context " + ctx);
        } finally {
            LdapUtils.closeContext(ctx);
        }

        return buildAuthenticationInfo(upToken.getUsername(), upToken.getPassword());
    }

    /**
     * Provide authorization info for the specified user.
     *
     * Shiro calls this every time it needs to know permissions granted to a user.  Permission strings are assigned to
     * roles and users to roles.
     *
     * Role names and permission strings are defined in shiro.ini.
     *
     * @param principals
     *            the principals of the Subject whose AuthenticationInfo should
     *            be queried from the LDAP server.
     * @param ldapContextFactory
     *            factory used to retrieve LDAP connections.
     * @return an {@link AuthorizationInfo} instance containing information
     *         retrieved from the LDAP server.
     * @throws NamingException
     *             if any LDAP errors occur during the search.
     */
    @Override
    protected AuthorizationInfo queryForAuthorizationInfo(final PrincipalCollection principals, final LdapContextFactory ldapContextFactory) throws NamingException {
        final String username = (String) getAvailablePrincipal(principals);
        logger.debug( "Authorizing user " + username);
        final Set<String> roleNames = roleNamesForUser(principals, ldapContextFactory.getSystemLdapContext());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roleNames);
        Set<String> stringPermissions = permsFor(roleNames);
        simpleAuthorizationInfo.setStringPermissions(stringPermissions);
        logger.debug("User " + username + " has permissions " + stringPermissions.stream().collect( Collectors.joining(",")));
        return simpleAuthorizationInfo;
    }

    /**
     * Set the rolesByGroup property.
     *
     * This creates a property that the shiro.ini file can set.
     *
     * @param rolesByGroup The mapping from ldap group names that define roles to the internal role names.
     */
    public void setRolesByGroup(Map<String, String> rolesByGroup) {
        this.rolesByGroup.putAll(rolesByGroup);
    }

    /**
     * Set the permissionByRole property.
     *
     * This is a property defined in shiro.ini.  It maps the internal role names to strings that define the permissions.
     * Syntax is a single string like the following.
     * <role1-name> = <permission1string>,<permission2string>; <role2-name> = <permission3-string>,<permission4-string>;
     *
     * @param permissionsByRoleStr Map of internal role names to strings that define permissions.
     */
    public void setPermissionsByRole(String permissionsByRoleStr) {
        this.permissionsByRole.putAll(Util.parse(permissionsByRoleStr));
    }

    /**
     * Return the list of shiro internal names for roles to which the user belongs.
     *
     * Role names per user are cached to minimize the hits on the LDAP.
     *
     * @param principals
     * @param ldapContext
     * @return
     * @throws NamingException
     */
    private Set<String> roleNamesForUser( final PrincipalCollection principals, final LdapContext ldapContext) throws NamingException {
        final String username = (String) getAvailablePrincipal(principals);

        try {
            if( ! roleCache.hasRolesFor( username)) {
                Set<String> roleNames = Sets.newLinkedHashSet();
                DirectoryManager dm = ServiceRegistry.getDirectoryManager();
                User user = dm.getAccount(username);
                for (String ldapRoleName : user.getRoleNames()) {
                    roleNames.add(rolesByGroup.get(ldapRoleName));
                }
                roleCache.putRoles( username, roleNames);
                logger.debug("Add role names to cache: " + username + ", " + roleNames.stream().collect( Collectors.joining(",")));
            }
            return roleCache.getRolesFor( username);
        }
        catch (DirectoryManagerException e) {
            throw new NamingException( e.getMessage());
        }
    }

    /**
     * Return the set of permission strings given to the supplied roles.
     *
     * @param roleNames
     * @return
     */
    private Set<String> permsFor(Set<String> roleNames) {
        Set<String> perms = Sets.newLinkedHashSet();
        Iterator i$ = roleNames.iterator();

        while(i$.hasNext()) {
            String role = (String)i$.next();
            List<String> permsForRole = (List)this.permissionsByRole.get(role);
            if (permsForRole != null) {
                perms.addAll(permsForRole);
            }
        }

        return perms;
    }

}
