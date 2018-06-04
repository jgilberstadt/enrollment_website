package edu.wustl.mir.ctt.beans;

import java.util.List;
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
public class ValidateEvaluationFreshness extends ValidateEvaluationsBean {
     
    @Override
    public boolean validateValues(FacesContext fc, List<UIInput> components, List<Object> values) {
        
        init( fc, components, values);
                            
        return ! studyArmEligibilityCalculator.isDataStale();
    }
    
}
