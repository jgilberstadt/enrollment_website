/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wustl.mir.ctt.model;

/**
 *
 * @author Paul K. Commean
 */
public class ChangeTherapyDateOffsets {
    
    private int[] dateOffsets;
    
    public ChangeTherapyDateOffsets() {
        dateOffsets = new int[7];
        dateOffsets[0] = 0;
        dateOffsets[1] = 30;
        dateOffsets[2] = 60;
        dateOffsets[3] = 90;
        dateOffsets[4] = 120;
        dateOffsets[5] = 150;
        dateOffsets[6] = 180;
    }
    
    public int[] getDateOffsets() {
        return dateOffsets;
    }
    
    public void setDateOffsets(int[] dateOffsets) {
        this.dateOffsets=dateOffsets;
    }

    public int getDateOffset(int i){
        return dateOffsets[i];
    }
    
}
