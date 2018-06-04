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
public class PulmEvalVisitLastDateOffsets {
    
    private int[] lastDateOffsets;
    
    // Originally, these dates most likely meant the last date due, but in the Paricipant.xhtml page,
    // the Overdue column is defined as the Projected Date + 7 days = 8 days plus 1 additional day = 9 days.
    // The Pulmonary Evaluation is not Overdue until the 9th day for the +/- 7 day offsets.
    // So a 6/1/2015 Projected Date would have an Overdue Date of 6/9/2015.
    // Originally, the values were 7 and 14, but now they are 8 and 15, respectively on 6/12/2015.
    public PulmEvalVisitLastDateOffsets() {
        lastDateOffsets = new int[7];
        lastDateOffsets[0] = 8;
        lastDateOffsets[1] = 8;
        lastDateOffsets[2] = 8;
        lastDateOffsets[3] = 8;
        lastDateOffsets[4] = 8;
        lastDateOffsets[5] = 15;
        lastDateOffsets[6] = 15;
        /*lastDateOffsets[7] = 15;
        lastDateOffsets[8] = 15;
        lastDateOffsets[9] = 15;
        lastDateOffsets[10] = 15;
        lastDateOffsets[11] = 15;
        lastDateOffsets[12] = 15;*/
    }
    
    public int[] getLastDateOffsets() {
        return lastDateOffsets;
    }
    
    public void setLastDateOffsets(int[] lastDateOffsets) {
        this.lastDateOffsets=lastDateOffsets;
    }

    public int getLastDateOffset(int i){
        return lastDateOffsets[i];
    }
    
}
