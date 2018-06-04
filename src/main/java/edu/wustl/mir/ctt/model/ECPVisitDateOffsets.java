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
public class ECPVisitDateOffsets {
    
    private int[] dateOffsets;
    
    public ECPVisitDateOffsets() {
        dateOffsets = new int[25];
        dateOffsets[0] = 0;
        dateOffsets[1] = 0;
        dateOffsets[2] = 3;
        dateOffsets[3] = 7;
        dateOffsets[4] = 10;
        dateOffsets[5] = 14;
        dateOffsets[6] = 17;
        dateOffsets[7] = 21;
        dateOffsets[8] = 24;
        dateOffsets[9] = 27;
        dateOffsets[10] = 30;
        dateOffsets[11] = 42;
        dateOffsets[12] = 48;
        dateOffsets[13] = 54;
        dateOffsets[14] = 60;
        dateOffsets[15] = 66;
        dateOffsets[16] = 72;
        dateOffsets[17] = 78;
        dateOffsets[18] = 84;
        dateOffsets[19] = 100;
        dateOffsets[20] = 115;
        dateOffsets[21] = 130;
        dateOffsets[22] = 145;
        dateOffsets[23] = 160;
        dateOffsets[24] = 175;
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
