/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wustl.mir.ctt.model;

/**
 *
 * @author lwalla01
 */
public enum SpirometryStrata {
    LAB_SPIROMETRY_STRATA("Lab Spirometry Strata"),
    HOME_SPIROMETRY_STRATA("Home Spirometry Strata"),
    UNKNOWN("unknown");
    
    private final String name;

    // Constructor for the enum.  This constructor is called once for each initializer above.
    private SpirometryStrata( String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
