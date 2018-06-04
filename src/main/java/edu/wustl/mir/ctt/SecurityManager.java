package edu.wustl.mir.ctt;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import static org.omnifaces.util.Faces.getRequest;
import static org.omnifaces.util.Faces.getRequestHeader;
import org.omnifaces.util.Utils;

/**
 *
 * @author drm
 */
@ManagedBean
@SessionScoped
public class SecurityManager implements Serializable {
    
    private static final Logger logger = Logger.getLogger(SecurityManager.class);

    //----------------------------------------------------------------------------------------------
    // How to use the Apache shiro.ini file with SecurityUtils can be found at the following website:
    // http://shiro.apache.org/tutorial.html
    //-----------------------------------------------------------------------------------------------
    
    // The "allSites:view" can be found in the shiro.ini file on the line with    trial_coordinator_role = allSites:view, forms:verify ; \
    public boolean canViewAllSites() {
        return SecurityUtils.getSubject().isPermitted("allSites:view");
    }
    
    public static boolean canVerifyForms() {
        return SecurityUtils.getSubject().isPermitted("forms:verify");
    }
    
    // The "ownSite:canApprove" can be found in the shiro.ini file on the line with    pi_approved_role = ownSite:canApprove
    public static boolean canApproveForms() {
        return SecurityUtils.getSubject().isPermitted("ownSite:canApprove");
    }
    
    public String logoutAction() {
        System.out.println("The username is: " + SecurityUtils.getSubject().getPrincipal());
        SecurityUtils.getSubject().logout();
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/siteResources.xhtml?faces-redirect=true";
    }
    
    public String recordUserLogin(){
        System.out.println("I just ran the SecurityManager.recordUserLogin method!!!!!!!!!!");
        String s = "s";
        return s;
    }
    
    public String getUserName() {
        Subject s = SecurityUtils.getSubject();

        System.out.println("The username is: " + s.getPrincipal() + "\n");
        boolean b = s.hasRole("admin_role");  // Dave thinks this code was used for debugging purposes.  It do not appear to be used now.
        b = s.isPermitted("allSites:view");  // Dave thinks this code was used for debugging purposes.  It do not appear to be used now.
        return (String) SecurityUtils.getSubject().getPrincipal();
    }
    
    public String getLoginUserName() {
        Subject s = SecurityUtils.getSubject();

//        String ipaddress = getRemoteAddress();
//        System.out.println("\n\nThe ip address is: " + ipaddress + "\n");
        
        // NOTE: System.out.println commands are written to catalina.out on the ecp servers.
        System.out.println("The username is: " + s.getPrincipal() + "\n");

        return (String) SecurityUtils.getSubject().getPrincipal();
    }
    
    // Stack overflow code from BalusC 6-16-2015
    // http://stackoverflow.com/questions/15674315/get-user-ip-address-with-jsf-api-without-the-need-for-servlet-api
    public String getRemoteIPAddress() {
        String xForwardedFor = getRequestHeader("X-Forwarded-For");

        if (!Utils.isEmpty(xForwardedFor)) {
            System.out.println("The xForwardedFor address is: " + xForwardedFor + "\n");
            return xForwardedFor.split("\\s*,\\s*", 2)[0]; // The xForwardFor is a comma separated string: client,proxy1,proxy2,...
        }

        return getRequest().getRemoteAddr();
    }
}
