package edu.wustl.mir.ctt.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author drm
 */
 @ManagedBean
 @RequestScoped
public class ValidateEvaluationQuantity extends ValidateEvaluationsBean {
    protected String fev1OldestPossibleDate;

    @Override
    public boolean validateValues(FacesContext fc, List<UIInput> components, List<Object> values) {
        
        // The ValidateEvaluationBean.init creates the StudyArmEligibilityCalculator object where the FEV1 oldest possible date is calculated. 
        init( fc, components, values);

        // Get the oldest possible date from the StudyArmEligibilityCalculator.
        fev1OldestPossibleDate = studyArmEligibilityCalculator.getFev1OldestPossibleDate();
                            
        return ! studyArmEligibilityCalculator.isDataTooFew();
    }
    
    // Paul K. Commean created the following getter and setter so the Omnifaces o:validateMultiple validator located in the enrollStudyArmEligForm.xhtml
    // can retrieve the oldest FEV1 date and place it into its message.
    public String getFev1OldestPossibleDate() {
        return fev1OldestPossibleDate;
    }
    
    public void setFev1OldestPossibleDate(String fev1OldestPossibleDate) {
        this.fev1OldestPossibleDate = fev1OldestPossibleDate;
    }
}
