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
public class BannerBean {
    private String text;
    private String color;
    private Logger logger;
    
    public BannerBean() {
        logger = Logger.getLogger(BannerBean.class);
    }
    
    public void init() {
        InputStream is = null;
        Properties bannerProperties = new Properties();
        
        try {
            is = new FileInputStream("/etc/ecp/banner.properties");
            bannerProperties.load(is);
            
            text = bannerProperties.getProperty("text");
            color = bannerProperties.getProperty("color");
        } catch (IOException ex) {
            logger.error(ex);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    public String getText() {
        return text;
    }
    
    public String getColor() {
        return color;
    }
}
