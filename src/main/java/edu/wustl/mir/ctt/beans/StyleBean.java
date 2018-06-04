/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wustl.mir.ctt.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author lwalla01
 */
public class StyleBean {
    private String header;
    private Logger logger;
    
    public StyleBean() {
        logger = Logger.getLogger(StyleBean.class);
    }
    
    public void init() {
        InputStream is = null;
        Properties styleProperties = new Properties();
        
        try {
            is = new FileInputStream("/etc/ecp/style.properties");
            styleProperties.load(is);
            
            header = styleProperties.getProperty("header");
        } catch (IOException ex) {
            logger.error(ex);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    public String getHeader() {
        return header;
    }
}
