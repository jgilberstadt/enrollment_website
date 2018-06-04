package edu.wustl.mir.ctt.beans;

import edu.wustl.mir.ctt.Controller;
import edu.wustl.mir.ctt.model.Site;
import edu.wustl.mir.ctt.model.SiteStatus;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author drm
 */
@ManagedBean
@RequestScoped
public class SiteBean implements Serializable {
    private Site site;
    
    @ManagedProperty(value="#{controller}")
    private Controller controller;
    
    public SiteBean() {
        site = new Site();
    }
    
    public Site getSite() {
        return site;
    }
    
    public void setController(Controller c) {
        this.controller = c;
    }
    
    public Controller getController() {
        return controller;
    }

    public void setId( int id) {
        site.setId(id);
    }
    
    public int getId() {
        return site.getId();
    }
    
    public void load() throws PersistenceException {
        if( site.getId() > 0) {
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
            site = pm.getSite( site.getId());
        }
    }
    
    public void setSiteID( int id) {
        site.setSiteID(id);
    }
    
    public int getSiteID() {
        return site.getSiteID();
    }
    
    public void setName( String name) {
        site.setName(name);
    }
    
    public String getName() {
        return site.getName();
    }
    
    public void setStatus( String status) {
        site.setStatus(SiteStatus.getStatus(status));
    }
    
    public String getStatus() {
        return site.getStatus().toString();
    }
    
    public String addSiteAction() throws PersistenceException {
        return controller.insertSiteAction(site);
    }
    
    public String cancelAddSiteAction() {
        return controller.cancelEditSiteAction();
    }
}
