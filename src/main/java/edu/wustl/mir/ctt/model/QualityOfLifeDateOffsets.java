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
public class QualityOfLifeDateOffsets {
    
    private int[] dateOffsets;
    
    public QualityOfLifeDateOffsets() {
        dateOffsets = new int[4];
        dateOffsets[0] = 0;
        dateOffsets[1] = 0;
        dateOffsets[2] = 90;
        dateOffsets[3] = 180;
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
