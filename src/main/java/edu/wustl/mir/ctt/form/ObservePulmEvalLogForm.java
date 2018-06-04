package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.VerificationStatus;
import edu.wustl.mir.ctt.util.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pkc
 */
public class ObservePulmEvalLogForm extends BasicForm implements Serializable {
    
    public static final String[] SourceDocumentTypes = new String[]{"Pulmonary Function Test Report"};
    
    public ObservePulmEvalLogForm() {
        // constructor
        super();
        formType = ECPFormTypes.PULMONARY_EVAL_LOG;
        title = "Observation Pulmonary Evaluation Log";
        this.sourceDocumentTypes = SourceDocumentTypes;
        this.requiresValidation = false;
        this.requiresVerification = false;
        this.submitButtonName = "Check ECP Treatment Eligibility";
        this.requiresSourceDoc = false;

        attributes.put("observePulmonaryEvaluationLogDate", new AttributeDate("observePulmonaryEvaluationLogDate"));        
        attributes.put("observePulmonaryFunctionDate1", new AttributeDate("observePulmonaryFunctionDate1"));        
        attributes.put("FEV11", new AttributeFloat("FEV11"));
        attributes.put("FVC1", new AttributeFloat("FVC1"));
        attributes.put("observePulmonaryFunctionDate2", new AttributeDate("observePulmonaryFunctionDate2"));        
        attributes.put("FEV12", new AttributeFloat("FEV12"));
        attributes.put("FVC2", new AttributeFloat("FVC2"));
        attributes.put("observePulmonaryFunctionDate3", new AttributeDate("observePulmonaryFunctionDate3"));        
        attributes.put("FEV13", new AttributeFloat("FEV13"));
        attributes.put("FVC3", new AttributeFloat("FVC3"));
        attributes.put("observePulmonaryFunctionDate4", new AttributeDate("observePulmonaryFunctionDate4"));        
        attributes.put("FEV14", new AttributeFloat("FEV14"));
        attributes.put("FVC4", new AttributeFloat("FVC4"));
        attributes.put("observePulmonaryFunctionDate5", new AttributeDate("observePulmonaryFunctionDate5"));        
        attributes.put("FEV15", new AttributeFloat("FEV15"));
        attributes.put("FVC5", new AttributeFloat("FVC5"));
        attributes.put("observePulmonaryFunctionDate6", new AttributeDate("observePulmonaryFunctionDate6"));        
        attributes.put("FEV16", new AttributeFloat("FEV16"));
        attributes.put("FVC6", new AttributeFloat("FVC6"));
        attributes.put("observePulmonaryFunctionDate7", new AttributeDate("observePulmonaryFunctionDate7"));        
        attributes.put("FEV17", new AttributeFloat("FEV17"));
        attributes.put("FVC7", new AttributeFloat("FVC7"));
        attributes.put("observePulmonaryFunctionDate8", new AttributeDate("observePulmonaryFunctionDate8"));        
        attributes.put("FEV18", new AttributeFloat("FEV18"));
        attributes.put("FVC8", new AttributeFloat("FVC8"));
        attributes.put("observePulmonaryFunctionDate9", new AttributeDate("observePulmonaryFunctionDate9"));        
        attributes.put("FEV19", new AttributeFloat("FEV19"));
        attributes.put("FVC9", new AttributeFloat("FVC9"));
        attributes.put("observePulmonaryFunctionDate10", new AttributeDate("observePulmonaryFunctionDate10"));        
        attributes.put("FEV110", new AttributeFloat("FEV110"));
        attributes.put("FVC10", new AttributeFloat("FVC10"));
        attributes.put("observePulmonaryFunctionDate11", new AttributeDate("observePulmonaryFunctionDate11"));        
        attributes.put("FEV111", new AttributeFloat("FEV111"));
        attributes.put("FVC11", new AttributeFloat("FVC11"));
        attributes.put("observePulmonaryFunctionDate12", new AttributeDate("observePulmonaryFunctionDate12"));        
        attributes.put("FEV112", new AttributeFloat("FEV112"));
        attributes.put("FVC12", new AttributeFloat("FVC12"));
        attributes.put("observePulmonaryFunctionDate13", new AttributeDate("observePulmonaryFunctionDate13"));        
        attributes.put("FEV113", new AttributeFloat("FEV113"));
        attributes.put("FVC13", new AttributeFloat("FVC13"));
        attributes.put("observePulmonaryFunctionDate14", new AttributeDate("observePulmonaryFunctionDate14"));        
        attributes.put("FEV114", new AttributeFloat("FEV114"));
        attributes.put("FVC14", new AttributeFloat("FVC14"));
        attributes.put("observePulmonaryFunctionDate15", new AttributeDate("observePulmonaryFunctionDate15"));        
        attributes.put("FEV115", new AttributeFloat("FEV115"));
        attributes.put("FVC15", new AttributeFloat("FVC15"));
        attributes.put("observePulmonaryFunctionDate16", new AttributeDate("observePulmonaryFunctionDate16"));        
        attributes.put("FEV116", new AttributeFloat("FEV116"));
        attributes.put("FVC16", new AttributeFloat("FVC16"));
        attributes.put("observePulmonaryFunctionDate17", new AttributeDate("observePulmonaryFunctionDate17"));        
        attributes.put("FEV117", new AttributeFloat("FEV117"));
        attributes.put("FVC17", new AttributeFloat("FVC17"));
        attributes.put("observePulmonaryFunctionDate18", new AttributeDate("observePulmonaryFunctionDate18"));        
        attributes.put("FEV118", new AttributeFloat("FEV118"));
        attributes.put("FVC18", new AttributeFloat("FVC18"));
        attributes.put("observePulmonaryFunctionDate19", new AttributeDate("observePulmonaryFunctionDate19"));        
        attributes.put("FEV119", new AttributeFloat("FEV119"));
        attributes.put("FVC19", new AttributeFloat("FVC19"));
        attributes.put("observePulmonaryFunctionDate20", new AttributeDate("observePulmonaryFunctionDate20"));        
        attributes.put("FEV120", new AttributeFloat("FEV120"));
        attributes.put("FVC20", new AttributeFloat("FVC20"));
        attributes.put("observePulmonaryFunctionDate21", new AttributeDate("observePulmonaryFunctionDate21"));        
        attributes.put("FEV121", new AttributeFloat("FEV121"));
        attributes.put("FVC21", new AttributeFloat("FVC21"));
        attributes.put("observePulmonaryFunctionDate22", new AttributeDate("observePulmonaryFunctionDate22"));        
        attributes.put("FEV122", new AttributeFloat("FEV122"));
        attributes.put("FVC22", new AttributeFloat("FVC22"));
        attributes.put("observePulmonaryFunctionDate23", new AttributeDate("observePulmonaryFunctionDate23"));        
        attributes.put("FEV123", new AttributeFloat("FEV123"));
        attributes.put("FVC23", new AttributeFloat("FVC23"));
        attributes.put("observePulmonaryFunctionDate24", new AttributeDate("observePulmonaryFunctionDate24"));        
        attributes.put("FEV124", new AttributeFloat("FEV124"));
        attributes.put("FVC24", new AttributeFloat("FVC24"));
        attributes.put("observePulmonaryFunctionDate25", new AttributeDate("observePulmonaryFunctionDate25"));        
        attributes.put("FEV125", new AttributeFloat("FEV125"));
        attributes.put("FVC25", new AttributeFloat("FVC25"));
        attributes.put("observePulmonaryFunctionDate26", new AttributeDate("observePulmonaryFunctionDate26"));        
        attributes.put("FEV126", new AttributeFloat("FEV126"));
        attributes.put("FVC26", new AttributeFloat("FVC26"));
        attributes.put("observePulmonaryFunctionDate27", new AttributeDate("observePulmonaryFunctionDate27"));        
        attributes.put("FEV127", new AttributeFloat("FEV127"));
        attributes.put("FVC27", new AttributeFloat("FVC27"));
        attributes.put("observePulmonaryFunctionDate28", new AttributeDate("observePulmonaryFunctionDate28"));        
        attributes.put("FEV128", new AttributeFloat("FEV128"));
        attributes.put("FVC28", new AttributeFloat("FVC28"));
        attributes.put("observePulmonaryFunctionDate29", new AttributeDate("observePulmonaryFunctionDate29"));        
        attributes.put("FEV129", new AttributeFloat("FEV129"));
        attributes.put("FVC29", new AttributeFloat("FVC29"));
        attributes.put("observePulmonaryFunctionDate30", new AttributeDate("observePulmonaryFunctionDate30"));        
        attributes.put("FEV130", new AttributeFloat("FEV130"));
        attributes.put("FVC30", new AttributeFloat("FVC30"));

        this.clear();
    }
    
    public ObservePulmEvalLogForm( BasicForm bf) {
        super(bf);		
        title = bf.getTitle();;
        this.sourceDocumentTypes = SourceDocumentTypes;
        this.requiresValidation = false;
        this.requiresVerification = false;
        this.submitButtonName = "Check ECP Treatment Eligibility";
        this.requiresSourceDoc = false;
    }
    
    public void prePopulate(EligibilityForm eligibilityForm) {
        setObservePulmonaryFunctionDate1(eligibilityForm.getDate1());
        setObservePulmonaryFunctionDate2(eligibilityForm.getDate2());
        setObservePulmonaryFunctionDate3(eligibilityForm.getDate3());
        setObservePulmonaryFunctionDate4(eligibilityForm.getDate4());
        setObservePulmonaryFunctionDate5(eligibilityForm.getDate5());
        setObservePulmonaryFunctionDate6(eligibilityForm.getDate6());
        setObservePulmonaryFunctionDate7(eligibilityForm.getDate7());
        setObservePulmonaryFunctionDate8(eligibilityForm.getDate8());
        setObservePulmonaryFunctionDate9(eligibilityForm.getDate9());
        setObservePulmonaryFunctionDate10(eligibilityForm.getDate10());
        setObservePulmonaryFunctionDate11(eligibilityForm.getDate11());
        setObservePulmonaryFunctionDate12(eligibilityForm.getDate12());
        setObservePulmonaryFunctionDate13(eligibilityForm.getDate13());
        setObservePulmonaryFunctionDate14(eligibilityForm.getDate14());
        setObservePulmonaryFunctionDate15(eligibilityForm.getDate15());
        setFev11(eligibilityForm.getFev11());
        setFev12(eligibilityForm.getFev12());
        setFev13(eligibilityForm.getFev13());
        setFev14(eligibilityForm.getFev14());
        setFev15(eligibilityForm.getFev15());
        setFev16(eligibilityForm.getFev16());
        setFev17(eligibilityForm.getFev17());
        setFev18(eligibilityForm.getFev18());
        setFev19(eligibilityForm.getFev19());
        setFev110(eligibilityForm.getFev110());
        setFev111(eligibilityForm.getFev111());
        setFev112(eligibilityForm.getFev112());
        setFev113(eligibilityForm.getFev113());
        setFev114(eligibilityForm.getFev114());
        setFev115(eligibilityForm.getFev115());
        setFvc1(eligibilityForm.getFvc1());
        setFvc2(eligibilityForm.getFvc2());
        setFvc3(eligibilityForm.getFvc3());
        setFvc4(eligibilityForm.getFvc4());
        setFvc5(eligibilityForm.getFvc5());
        setFvc6(eligibilityForm.getFvc6());
        setFvc7(eligibilityForm.getFvc7());
        setFvc8(eligibilityForm.getFvc8());
        setFvc9(eligibilityForm.getFvc9());
        setFvc10(eligibilityForm.getFvc10());
        setFvc11(eligibilityForm.getFvc11());
        setFvc12(eligibilityForm.getFvc12());
        setFvc13(eligibilityForm.getFvc13());
        setFvc14(eligibilityForm.getFvc14());
        setFvc15(eligibilityForm.getFvc15());
    }
   
    public List<PulmonaryEvaluation> getPulmEvaluations(){
        List<PulmonaryEvaluation> pulmEvals = new ArrayList<PulmonaryEvaluation>();
        List<PulmonaryEvaluation> pulmEvals2 = new ArrayList<PulmonaryEvaluation>();
        List<PulmonaryEvaluation> pulmEvals3 = new ArrayList<PulmonaryEvaluation>();
        Date dateOfFEV1;
        Float fev1;
        Float fvc;
        String dateKey = "observePulmonaryFunctionDate";
        String fev1Key = "FEV1";
        String fvcKey = "FVC";
        
        // Copy all of the attributes into an array containing PulmonaryEvaluation objects.
        for(int i = 1; i <= 30; i++){
            dateOfFEV1 = (Date) attributes.get(dateKey + i).getValue();
            fev1 = (Float) attributes.get(fev1Key + i).getValue();
            fvc = (Float) attributes.get(fvcKey + i).getValue();
            if(dateOfFEV1 != null){
                PulmonaryEvaluation pulmEval = new PulmonaryEvaluation(dateOfFEV1, fev1, fvc);
                pulmEvals.add(pulmEval);
            }
//            System.out.println("The integer output is: " + i + "\n");
        }
        
        // Sort the array of PulmonaryEvaluation objects by date in ascending order.
        Collections.sort(pulmEvals, new Comparator<PulmonaryEvaluation>() {
            @Override
            public int compare(PulmonaryEvaluation pe1, PulmonaryEvaluation pe2) {
                if (pe1.getDate() == null || pe2.getDate() == null)
                    return 0;
                return pe1.getDate().compareTo(pe2.getDate());
            }
        });
        
        // Since only 15 pulmonary evaluations are used in the calculator, limit the return to only 15 at the most.
        if(pulmEvals.size() > 15){
            int size = pulmEvals.size();
            int diff = size - 15;
            for(int i = diff; i <size; i++){
                pulmEvals2.add(pulmEvals.get(i));
            }
        } else {
            pulmEvals2 = pulmEvals;
        }
        
        // Remove any dates older than six months from the list.
        PulmonaryEvaluation pe;
        // Changed days from double to int so if the DateUtil returns 182.?? days
        // the int will drop the decimal. 
        int days = 0;
        Iterator iterator = pulmEvals2.iterator();
        while(iterator.hasNext()){
            pe = (PulmonaryEvaluation) iterator.next();
            days = (int) dateDifferenceInDays(pe.getDate());
            if(days < 183){
                pulmEvals3.add(pe);
            }
        }

/*        
        Iterator keyIterator = attributes.keySet().iterator();
        while(keyIterator.hasNext()){
            String s = keyIterator.next().toString();
            System.out.println("The key value is: " + s + "\n\n");
        }
*/
        return pulmEvals3;
    }
    
    // Count the number of FEV1s that have been entered into the Observational Log form.
    public int getNumberOfObservationPulmEvaluations(){
        int count = 0;
        Date dateOfFEV1;
        String dateKey = "observePulmonaryFunctionDate";
        
        // Count the number of attributes containing PulmonaryEvaluation objects.
        for(int i = 1; i <= 30; i++){
            dateOfFEV1 = (Date) attributes.get(dateKey + i).getValue();
            if(dateOfFEV1 != null){
                count++;
            }
        }
        return count;
    }

    private int getNumberOfPulmonaryEvalMeasurements( List<PulmonaryEvaluation> evals) {
        int count = 0;
        
        for( PulmonaryEvaluation eval: evals) {
            count++;
        }
        return count;
    }
    
    public void writePulmFuncTestValuesIntoLog(BasicForm selectedForm, int currentNumberOfFev1s){
        // Insert a new FEV1 into the log which is one more than the current number of FEV1s in the log.
        int newFev1NumberToInsert = currentNumberOfFev1s + 1;
        String dateNumberToRecord = "observePulmonaryFunctionDate" + newFev1NumberToInsert;
        String fev1NumberToRecord = "FEV1" + newFev1NumberToInsert;
        String fvcNumberToRecord = "FVC" + newFev1NumberToInsert;
        
        PulmEvalForm pef = (PulmEvalForm) selectedForm;
        attributes.get(dateNumberToRecord).setValue(pef.getDate());
        attributes.get(fev1NumberToRecord).setValue(pef.getFev1());
        attributes.get(fvcNumberToRecord).setValue(pef.getFvc());
        
        Date d = (Date) attributes.get(dateNumberToRecord).getValue();
        Float fev1 = (Float) attributes.get(fev1NumberToRecord).getValue();
        Float fvc = (Float) attributes.get(fvcNumberToRecord).getValue();
    }
    
    public long dateDifferenceInDays(Date date){
        long dateDiffInDays = (System.currentTimeMillis() - date.getTime())/(60*60*24*1000);
        System.out.println("The date difference is: " + dateDiffInDays + "\n");
        return dateDiffInDays;
    }
	
    public Date getObservePulmonaryEvaluationLogDate() {
        return (Date) attributes.get("observePulmonaryEvaluationLogDate").getValue();
    }
    
    public void setObservePulmonaryEvaluationLogDate(Date observePulmonaryEvaluationLogDate) {
        attributes.get("observePulmonaryEvaluationLogDate").setValue(observePulmonaryEvaluationLogDate);
    }

    public Date getObservePulmonaryFunctionDate1() {
        return (Date) attributes.get("observePulmonaryFunctionDate1").getValue();
    }
    
    public void setObservePulmonaryFunctionDate1(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate1").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev11() {
        return (Float) attributes.get("FEV11").getValue();
    }
    
    public void setFev11(Float fev1) {
        attributes.get("FEV11").setValue(fev1);
    }

    public Float getFvc1() {
        return (Float) attributes.get("FVC1").getValue();
    }
    
    public void setFvc1(Float fvc) {
        attributes.get("FVC1").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate2() {
        return (Date) attributes.get("observePulmonaryFunctionDate2").getValue();
    }
    
    public void setObservePulmonaryFunctionDate2(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate2").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev12() {
        return (Float) attributes.get("FEV12").getValue();
    }
    
    public void setFev12(Float fev1) {
        attributes.get("FEV12").setValue(fev1);
    }

    public Float getFvc2() {
        return (Float) attributes.get("FVC2").getValue();
    }
    
    public void setFvc2(Float fvc) {
        attributes.get("FVC2").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate3() {
        return (Date) attributes.get("observePulmonaryFunctionDate3").getValue();
    }
    
    public void setObservePulmonaryFunctionDate3(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate3").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev13() {
        return (Float) attributes.get("FEV13").getValue();
    }
    
    public void setFev13(Float fev1) {
        attributes.get("FEV13").setValue(fev1);
    }

    public Float getFvc3() {
        return (Float) attributes.get("FVC3").getValue();
    }
    
    public void setFvc3(Float fvc) {
        attributes.get("FVC3").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate4() {
        return (Date) attributes.get("observePulmonaryFunctionDate4").getValue();
    }
    
    public void setObservePulmonaryFunctionDate4(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate4").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev14() {
        return (Float) attributes.get("FEV14").getValue();
    }
    
    public void setFev14(Float fev1) {
        attributes.get("FEV14").setValue(fev1);
    }

    public Float getFvc4() {
        return (Float) attributes.get("FVC4").getValue();
    }
    
    public void setFvc4(Float fvc) {
        attributes.get("FVC4").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate5() {
        return (Date) attributes.get("observePulmonaryFunctionDate5").getValue();
    }
    
    public void setObservePulmonaryFunctionDate5(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate5").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev15() {
        return (Float) attributes.get("FEV15").getValue();
    }
    
    public void setFev15(Float fev1) {
        attributes.get("FEV15").setValue(fev1);
    }

    public Float getFvc5() {
        return (Float) attributes.get("FVC5").getValue();
    }
    
    public void setFvc5(Float fvc) {
        attributes.get("FVC5").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate6() {
        return (Date) attributes.get("observePulmonaryFunctionDate6").getValue();
    }
    
    public void setObservePulmonaryFunctionDate6(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate6").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev16() {
        return (Float) attributes.get("FEV16").getValue();
    }
    
    public void setFev16(Float fev1) {
        attributes.get("FEV16").setValue(fev1);
    }

    public Float getFvc6() {
        return (Float) attributes.get("FVC6").getValue();
    }
    
    public void setFvc6(Float fvc) {
        attributes.get("FVC6").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate7() {
        return (Date) attributes.get("observePulmonaryFunctionDate7").getValue();
    }
    
    public void setObservePulmonaryFunctionDate7(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate7").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev17() {
        return (Float) attributes.get("FEV17").getValue();
    }
    
    public void setFev17(Float fev1) {
        attributes.get("FEV17").setValue(fev1);
    }

    public Float getFvc7() {
        return (Float) attributes.get("FVC7").getValue();
    }
    
    public void setFvc7(Float fvc) {
        attributes.get("FVC7").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate8() {
        return (Date) attributes.get("observePulmonaryFunctionDate8").getValue();
    }
    
    public void setObservePulmonaryFunctionDate8(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate8").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev18() {
        return (Float) attributes.get("FEV18").getValue();
    }
    
    public void setFev18(Float fev1) {
        attributes.get("FEV18").setValue(fev1);
    }

    public Float getFvc8() {
        return (Float) attributes.get("FVC8").getValue();
    }
    
    public void setFvc8(Float fvc) {
        attributes.get("FVC8").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate9() {
        return (Date) attributes.get("observePulmonaryFunctionDate9").getValue();
    }
    
    public void setObservePulmonaryFunctionDate9(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate9").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev19() {
        return (Float) attributes.get("FEV19").getValue();
    }
    
    public void setFev19(Float fev1) {
        attributes.get("FEV19").setValue(fev1);
    }

    public Float getFvc9() {
        return (Float) attributes.get("FVC9").getValue();
    }
    
    public void setFvc9(Float fvc) {
        attributes.get("FVC9").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate10() {
        return (Date) attributes.get("observePulmonaryFunctionDate10").getValue();
    }
    
    public void setObservePulmonaryFunctionDate10(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate10").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev110() {
        return (Float) attributes.get("FEV110").getValue();
    }
    
    public void setFev110(Float fev1) {
        attributes.get("FEV110").setValue(fev1);
    }

    public Float getFvc10() {
        return (Float) attributes.get("FVC10").getValue();
    }
    
    public void setFvc10(Float fvc) {
        attributes.get("FVC10").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate11() {
        return (Date) attributes.get("observePulmonaryFunctionDate11").getValue();
    }
    
    public void setObservePulmonaryFunctionDate11(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate11").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev111() {
        return (Float) attributes.get("FEV111").getValue();
    }
    
    public void setFev111(Float fev1) {
        attributes.get("FEV111").setValue(fev1);
    }

    public Float getFvc11() {
        return (Float) attributes.get("FVC11").getValue();
    }
    
    public void setFvc11(Float fvc) {
        attributes.get("FVC11").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate12() {
        return (Date) attributes.get("observePulmonaryFunctionDate12").getValue();
    }
    
    public void setObservePulmonaryFunctionDate12(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate12").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev112() {
        return (Float) attributes.get("FEV112").getValue();
    }
    
    public void setFev112(Float fev1) {
        attributes.get("FEV112").setValue(fev1);
    }

    public Float getFvc12() {
        return (Float) attributes.get("FVC12").getValue();
    }
    
    public void setFvc12(Float fvc) {
        attributes.get("FVC12").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate13() {
        return (Date) attributes.get("observePulmonaryFunctionDate13").getValue();
    }
    
    public void setObservePulmonaryFunctionDate13(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate13").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev113() {
        return (Float) attributes.get("FEV113").getValue();
    }
    
    public void setFev113(Float fev1) {
        attributes.get("FEV113").setValue(fev1);
    }

    public Float getFvc13() {
        return (Float) attributes.get("FVC13").getValue();
    }
    
    public void setFvc13(Float fvc) {
        attributes.get("FVC13").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate14() {
        return (Date) attributes.get("observePulmonaryFunctionDate14").getValue();
    }
    
    public void setObservePulmonaryFunctionDate14(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate14").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev114() {
        return (Float) attributes.get("FEV114").getValue();
    }
    
    public void setFev114(Float fev1) {
        attributes.get("FEV114").setValue(fev1);
    }

    public Float getFvc14() {
        return (Float) attributes.get("FVC14").getValue();
    }
    
    public void setFvc14(Float fvc) {
        attributes.get("FVC14").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate15() {
        return (Date) attributes.get("observePulmonaryFunctionDate15").getValue();
    }
    
    public void setObservePulmonaryFunctionDate15(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate15").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev115() {
        return (Float) attributes.get("FEV115").getValue();
    }
    
    public void setFev115(Float fev1) {
        attributes.get("FEV115").setValue(fev1);
    }

    public Float getFvc15() {
        return (Float) attributes.get("FVC15").getValue();
    }
    
    public void setFvc15(Float fvc) {
        attributes.get("FVC15").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate16() {
        return (Date) attributes.get("observePulmonaryFunctionDate16").getValue();
    }
    
    public void setObservePulmonaryFunctionDate16(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate16").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev116() {
        return (Float) attributes.get("FEV116").getValue();
    }
    
    public void setFev116(Float fev1) {
        attributes.get("FEV116").setValue(fev1);
    }

    public Float getFvc16() {
        return (Float) attributes.get("FVC16").getValue();
    }
    
    public void setFvc16(Float fvc) {
        attributes.get("FVC16").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate17() {
        return (Date) attributes.get("observePulmonaryFunctionDate17").getValue();
    }
    
    public void setObservePulmonaryFunctionDate17(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate17").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev117() {
        return (Float) attributes.get("FEV117").getValue();
    }
    
    public void setFev117(Float fev1) {
        attributes.get("FEV117").setValue(fev1);
    }

    public Float getFvc17() {
        return (Float) attributes.get("FVC17").getValue();
    }
    
    public void setFvc17(Float fvc) {
        attributes.get("FVC17").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate18() {
        return (Date) attributes.get("observePulmonaryFunctionDate18").getValue();
    }
    
    public void setObservePulmonaryFunctionDate18(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate18").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev118() {
        return (Float) attributes.get("FEV118").getValue();
    }
    
    public void setFev118(Float fev1) {
        attributes.get("FEV118").setValue(fev1);
    }

    public Float getFvc18() {
        return (Float) attributes.get("FVC18").getValue();
    }
    
    public void setFvc18(Float fvc) {
        attributes.get("FVC18").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate19() {
        return (Date) attributes.get("observePulmonaryFunctionDate19").getValue();
    }
    
    public void setObservePulmonaryFunctionDate19(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate19").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev119() {
        return (Float) attributes.get("FEV119").getValue();
    }
    
    public void setFev119(Float fev1) {
        attributes.get("FEV119").setValue(fev1);
    }

    public Float getFvc19() {
        return (Float) attributes.get("FVC19").getValue();
    }
    
    public void setFvc19(Float fvc) {
        attributes.get("FVC19").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate20() {
        return (Date) attributes.get("observePulmonaryFunctionDate20").getValue();
    }
    
    public void setObservePulmonaryFunctionDate20(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate20").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev120() {
        return (Float) attributes.get("FEV120").getValue();
    }
    
    public void setFev120(Float fev1) {
        attributes.get("FEV120").setValue(fev1);
    }

    public Float getFvc20() {
        return (Float) attributes.get("FVC20").getValue();
    }
    
    public void setFvc20(Float fvc) {
        attributes.get("FVC20").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate21() {
        return (Date) attributes.get("observePulmonaryFunctionDate21").getValue();
    }
    
    public void setObservePulmonaryFunctionDate21(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate21").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev121() {
        return (Float) attributes.get("FEV121").getValue();
    }
    
    public void setFev121(Float fev1) {
        attributes.get("FEV121").setValue(fev1);
    }

    public Float getFvc21() {
        return (Float) attributes.get("FVC21").getValue();
    }
    
    public void setFvc21(Float fvc) {
        attributes.get("FVC21").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate22() {
        return (Date) attributes.get("observePulmonaryFunctionDate22").getValue();
    }
    
    public void setObservePulmonaryFunctionDate22(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate22").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev122() {
        return (Float) attributes.get("FEV122").getValue();
    }
    
    public void setFev122(Float fev1) {
        attributes.get("FEV122").setValue(fev1);
    }

    public Float getFvc22() {
        return (Float) attributes.get("FVC22").getValue();
    }
    
    public void setFvc22(Float fvc) {
        attributes.get("FVC22").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate23() {
        return (Date) attributes.get("observePulmonaryFunctionDate23").getValue();
    }
    
    public void setObservePulmonaryFunctionDate23(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate23").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev123() {
        return (Float) attributes.get("FEV123").getValue();
    }
    
    public void setFev123(Float fev1) {
        attributes.get("FEV123").setValue(fev1);
    }

    public Float getFvc23() {
        return (Float) attributes.get("FVC23").getValue();
    }
    
    public void setFvc23(Float fvc) {
        attributes.get("FVC23").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate24() {
        return (Date) attributes.get("observePulmonaryFunctionDate24").getValue();
    }
    
    public void setObservePulmonaryFunctionDate24(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate24").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev124() {
        return (Float) attributes.get("FEV124").getValue();
    }
    
    public void setFev124(Float fev1) {
        attributes.get("FEV124").setValue(fev1);
    }

    public Float getFvc24() {
        return (Float) attributes.get("FVC24").getValue();
    }
    
    public void setFvc24(Float fvc) {
        attributes.get("FVC24").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate25() {
        return (Date) attributes.get("observePulmonaryFunctionDate25").getValue();
    }
    
    public void setObservePulmonaryFunctionDate25(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate25").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev125() {
        return (Float) attributes.get("FEV125").getValue();
    }
    
    public void setFev125(Float fev1) {
        attributes.get("FEV125").setValue(fev1);
    }

    public Float getFvc25() {
        return (Float) attributes.get("FVC25").getValue();
    }
    
    public void setFvc25(Float fvc) {
        attributes.get("FVC25").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate26() {
        return (Date) attributes.get("observePulmonaryFunctionDate26").getValue();
    }
    
    public void setObservePulmonaryFunctionDate26(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate26").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev126() {
        return (Float) attributes.get("FEV126").getValue();
    }
    
    public void setFev126(Float fev1) {
        attributes.get("FEV126").setValue(fev1);
    }

    public Float getFvc26() {
        return (Float) attributes.get("FVC26").getValue();
    }
    
    public void setFvc26(Float fvc) {
        attributes.get("FVC26").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate27() {
        return (Date) attributes.get("observePulmonaryFunctionDate27").getValue();
    }
    
    public void setObservePulmonaryFunctionDate27(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate27").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev127() {
        return (Float) attributes.get("FEV127").getValue();
    }
    
    public void setFev127(Float fev1) {
        attributes.get("FEV127").setValue(fev1);
    }

    public Float getFvc27() {
        return (Float) attributes.get("FVC27").getValue();
    }
    
    public void setFvc27(Float fvc) {
        attributes.get("FVC27").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate28() {
        return (Date) attributes.get("observePulmonaryFunctionDate28").getValue();
    }
    
    public void setObservePulmonaryFunctionDate28(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate28").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev128() {
        return (Float) attributes.get("FEV128").getValue();
    }
    
    public void setFev128(Float fev1) {
        attributes.get("FEV128").setValue(fev1);
    }

    public Float getFvc28() {
        return (Float) attributes.get("FVC28").getValue();
    }
    
    public void setFvc28(Float fvc) {
        attributes.get("FVC28").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate29() {
        return (Date) attributes.get("observePulmonaryFunctionDate29").getValue();
    }
    
    public void setObservePulmonaryFunctionDate29(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate29").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev129() {
        return (Float) attributes.get("FEV129").getValue();
    }
    
    public void setFev129(Float fev1) {
        attributes.get("FEV129").setValue(fev1);
    }

    public Float getFvc29() {
        return (Float) attributes.get("FVC29").getValue();
    }
    
    public void setFvc29(Float fvc) {
        attributes.get("FVC29").setValue(fvc);
    }
    

    public Date getObservePulmonaryFunctionDate30() {
        return (Date) attributes.get("observePulmonaryFunctionDate30").getValue();
    }
    
    public void setObservePulmonaryFunctionDate30(Date observePulmonaryFunctionDate) {
        attributes.get("observePulmonaryFunctionDate30").setValue(observePulmonaryFunctionDate);
    }

    public Float getFev130() {
        return (Float) attributes.get("FEV130").getValue();
    }
    
    public void setFev130(Float fev1) {
        attributes.get("FEV130").setValue(fev1);
    }

    public Float getFvc30() {
        return (Float) attributes.get("FVC30").getValue();
    }
    
    public void setFvc30(Float fvc) {
        attributes.get("FVC30").setValue(fvc);
    }
    
}
