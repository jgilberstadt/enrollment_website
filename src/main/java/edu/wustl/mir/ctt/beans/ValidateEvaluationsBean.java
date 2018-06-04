package edu.wustl.mir.ctt.beans;

import edu.wustl.mir.ctt.calc.StudyArmEligibilityCalculator;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.omnifaces.validator.MultiFieldValidator;

/**
 *
 * @author drm
 */
 @ManagedBean
 @RequestScoped
public class ValidateEvaluationsBean implements MultiFieldValidator {
    protected List<PulmonaryEvaluation> evals;
    protected Date screenDate;
    protected StudyArmEligibilityCalculator studyArmEligibilityCalculator;
    
    private static final int MAX_EVALUATION_COUNT = 26;


    protected void init( FacesContext fc, List<UIInput> list, List<Object> list1) {
        DateFormat evalDateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
        DateFormat screenDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        evals = new ArrayList<PulmonaryEvaluation>();
        
        try {
            String d = fc.getExternalContext().getRequestParameterMap().get("form:screenDate");
            screenDate = screenDateFormat.parse(d);
        
            for( int i = 0; i < MAX_EVALUATION_COUNT; i++) {
                Object value = list1.get(i);
                if( value != null) {
                        PulmonaryEvaluation eval = new PulmonaryEvaluation( evalDateFormat.parse(value.toString()), Float.parseFloat( list1.get(MAX_EVALUATION_COUNT + i).toString()), 0.0f);
                        evals.add( eval);
                }
            }
            
            studyArmEligibilityCalculator = new StudyArmEligibilityCalculator( evals, screenDate);

        } catch (ParseException ex) {
            Logger.getLogger(ValidateEvaluationsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean validateValues(FacesContext fc, List<UIInput> list, List<Object> list1) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
