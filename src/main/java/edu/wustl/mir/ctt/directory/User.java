package edu.wustl.mir.ctt.directory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drm
 */
public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String userName;
    private String organization;
    private String email;
    private List<String> siteNames = new ArrayList<>();
    private List<String> roleNames = new ArrayList<>();

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSiteNames() {
        return siteNames;
    }

    public void setSiteNames(List<String> siteNames) {
        this.siteNames = siteNames;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserName: ").append(userName);
        sb.append(" firstName: ").append(firstName);
        sb.append(" lastName: ").append(lastName);
        sb.append(" organization: ").append(organization);
        sb.append(" email: ").append(email);
        for( String name: siteNames) {
            sb.append(" siteName: ").append(name);
        }
        for( String name: roleNames) {
            sb.append(" roleName: ").append(name);
        }
        return sb.toString();
    }
}