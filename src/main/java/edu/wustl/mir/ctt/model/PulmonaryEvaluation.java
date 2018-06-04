package edu.wustl.mir.ctt.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Paul K. Commean
 */
public class PulmonaryEvaluation implements Serializable /*implements Comparable<PulmonaryEvaluation>*/{
    private Date date;
    private Float fev1;
    private Float fvc;
    
    public PulmonaryEvaluation(Date date, Float fev1, Float fvc){
        this.date = date;
        this.fev1 = fev1;
        this.fvc = fvc;
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Float getFev1() {
        return fev1;
    }
    
    public void setFev1(Float fev1) {
        this.fev1 = fev1;
    }

    public Float getFvc() {
        return fvc;
    }
    
    public void setFvc(Float fvc) {
        this.fvc = fvc;
    }
/*    
    @Override
    public int compareTo(PulmonaryEvaluation pe){
        if(getDate() == null || pe.getDate() == null)
            return 0;
        return getDate().compareTo(pe.getDate());
    }
*/
}
