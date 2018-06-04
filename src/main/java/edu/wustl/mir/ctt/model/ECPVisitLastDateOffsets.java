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
public class ECPVisitLastDateOffsets {
    
    private int[] lastDateOffsets;
    
    // Originally, these dates most likely meant the last date due, but on the Paricipant.xhtml page,
    // the Overdue column is defined as the Projected Date + 7 days = 8 days plus 1 additional day = 9 days.
    // The ECP Treatments do not have an Overdue date shown on the Paricipant.xhtml page.
    // The ECP Treatments is not Overdue until the 9th day for the +/- 7 day offsets.
    // So a 6/1/2015 Projected Date would have an Overdue Date of 6/9/2015.
    //
    // NOTE: These lastDateOffsets are not being used in the database event table.
    // 
    public ECPVisitLastDateOffsets() {
        lastDateOffsets = new int[25];
        lastDateOffsets[0] = 3;
        lastDateOffsets[1] = 3;
        lastDateOffsets[2] = 3;
        lastDateOffsets[3] = 3;
        lastDateOffsets[4] = 3;
        lastDateOffsets[5] = 3;
        lastDateOffsets[6] = 3;
        lastDateOffsets[7] = 3;
        lastDateOffsets[8] = 3;
        lastDateOffsets[9] = 3;
        lastDateOffsets[10] = 7;
        lastDateOffsets[11] = 7;
        lastDateOffsets[12] = 7;
        lastDateOffsets[13] = 7;
        lastDateOffsets[14] = 7;
        lastDateOffsets[15] = 7;
        lastDateOffsets[16] = 7;
        lastDateOffsets[17] = 7;
        lastDateOffsets[18] = 7;
        lastDateOffsets[19] = 15;
        lastDateOffsets[20] = 15;
        lastDateOffsets[21] = 15;
        lastDateOffsets[22] = 15;
        lastDateOffsets[23] = 15;
        lastDateOffsets[24] = 15;
    }
    
    public int[] getLastDateOffsets() {
        return lastDateOffsets;
    }
    
    public void setvDateOffsets(int[] lastDateOffsets) {
        this.lastDateOffsets=lastDateOffsets;
    }

    public int getLastDateOffset(int i){
        return lastDateOffsets[i];
    }
    
}
