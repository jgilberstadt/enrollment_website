package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Paul K. Commean
 */
public class CrossoverSafetyCheckForm extends BasicForm {
    
    private boolean safeToCrossoverResultsDisplay = true;
    public static final String[] SourceDocumentTypes = new String[]{"A Signed Crossover Safety Check Form must be uploaded"};
    private transient Logger logger;

    public CrossoverSafetyCheckForm() {
        // constructor
        super();
        
        logger = Logger.getLogger(CrossoverSafetyCheckForm.class);
        this.formType = ECPFormTypes.CROSSOVER_SAFETY_CHECK;
        title = "Crossover Safety Check Form";
        this.sourceDocumentTypes = SourceDocumentTypes;

        attributes.put("safeToCrossover", new AttributeString("safeToCrossover"));
        attributes.put("wasCrossoverSafetyCheckUploaded", new AttributeString("wasCrossoverSafetyCheckUploaded", true, false, true));

        attributes.put("patientClinicallyWorsening", new AttributeString("patientClinicallyWorsening"));
        attributes.put("crossoverRequested", new AttributeString("crossoverRequested"));
        attributes.put("allFev1sEntered", new AttributeString("allFev1sEntered"));
        attributes.put("patientStaysInProtocol", new AttributeString("patientStaysInProtocol"));
        
        attributes.put("date1", new AttributeDate("date1"));
        attributes.put("date2", new AttributeDate("date2"));
        attributes.put("date3", new AttributeDate("date3"));
        attributes.put("date4", new AttributeDate("date4"));
        attributes.put("date5", new AttributeDate("date5"));
        attributes.put("date6", new AttributeDate("date6"));
        attributes.put("date7", new AttributeDate("date7"));
        attributes.put("date8", new AttributeDate("date8"));
        attributes.put("date9", new AttributeDate("date9"));
        attributes.put("date10", new AttributeDate("date10"));
        attributes.put("date11", new AttributeDate("date11"));
        attributes.put("date12", new AttributeDate("date12"));
        attributes.put("date13", new AttributeDate("date13"));
        attributes.put("date14", new AttributeDate("date14"));
        attributes.put("date15", new AttributeDate("date15"));
        attributes.put("fev11", new AttributeFloat("fev11"));
        attributes.put("fev12", new AttributeFloat("fev12"));
        attributes.put("fev13", new AttributeFloat("fev13"));
        attributes.put("fev14", new AttributeFloat("fev14"));
        attributes.put("fev15", new AttributeFloat("fev15"));
        attributes.put("fev16", new AttributeFloat("fev16"));
        attributes.put("fev17", new AttributeFloat("fev17"));
        attributes.put("fev18", new AttributeFloat("fev18"));
        attributes.put("fev19", new AttributeFloat("fev19"));
        attributes.put("fev110", new AttributeFloat("fev110"));
        attributes.put("fev111", new AttributeFloat("fev111"));
        attributes.put("fev112", new AttributeFloat("fev112"));
        attributes.put("fev113", new AttributeFloat("fev113"));
        attributes.put("fev114", new AttributeFloat("fev114"));
        attributes.put("fev115", new AttributeFloat("fev115"));
        attributes.put("fvc1", new AttributeFloat("fvc1"));
        attributes.put("fvc2", new AttributeFloat("fvc2"));
        attributes.put("fvc3", new AttributeFloat("fvc3"));
        attributes.put("fvc4", new AttributeFloat("fvc4"));
        attributes.put("fvc5", new AttributeFloat("fvc5"));
        attributes.put("fvc6", new AttributeFloat("fvc6"));
        attributes.put("fvc7", new AttributeFloat("fvc7"));
        attributes.put("fvc8", new AttributeFloat("fvc8"));
        attributes.put("fvc9", new AttributeFloat("fvc9"));
        attributes.put("fvc10", new AttributeFloat("fvc10"));
        attributes.put("fvc11", new AttributeFloat("fvc11"));
        attributes.put("fvc12", new AttributeFloat("fvc12"));
        attributes.put("fvc13", new AttributeFloat("fvc13"));
        attributes.put("fvc14", new AttributeFloat("fvc14"));
        attributes.put("fvc15", new AttributeFloat("fvc15"));

        attributes.put("slope", new AttributeFloat("slope"));
        attributes.put("significance", new AttributeFloat("significance"));

        this.clear();
    }
    
    public CrossoverSafetyCheckForm( BasicForm bf) {
        super(bf);
        logger = Logger.getLogger(CrossoverSafetyCheckForm.class);
        this.formType = ECPFormTypes.CROSSOVER_SAFETY_CHECK;
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
	
   public void setPulmonaryEvaluationValues( CrossoverEligibilityWorkSheet cews) {
        List<PulmonaryEvaluation> pulmEvals = cews.getEvaluations();
                    
        int i = 0;
        if(i < pulmEvals.size()){
            setDate1(pulmEvals.get(0).getDate());
            setFev11(pulmEvals.get(0).getFev1());
            setFvc1(pulmEvals.get(0).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate2(pulmEvals.get(1).getDate());
            setFev12(pulmEvals.get(1).getFev1());
            setFvc2(pulmEvals.get(1).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate3(pulmEvals.get(2).getDate());
            setFev13(pulmEvals.get(2).getFev1());
            setFvc3(pulmEvals.get(2).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate4(pulmEvals.get(3).getDate());
            setFev14(pulmEvals.get(3).getFev1());
            setFvc4(pulmEvals.get(3).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate5(pulmEvals.get(4).getDate());
            setFev15(pulmEvals.get(4).getFev1());
            setFvc5(pulmEvals.get(4).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate6(pulmEvals.get(5).getDate());
            setFev16(pulmEvals.get(5).getFev1());
            setFvc6(pulmEvals.get(5).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate7(pulmEvals.get(6).getDate());
            setFev17(pulmEvals.get(6).getFev1());
            setFvc7(pulmEvals.get(6).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate8(pulmEvals.get(7).getDate());
            setFev18(pulmEvals.get(7).getFev1());
            setFvc8(pulmEvals.get(7).getFvc());
        }
         i++;
        if(i < pulmEvals.size()){
            setDate9(pulmEvals.get(8).getDate());
            setFev19(pulmEvals.get(8).getFev1());
            setFvc9(pulmEvals.get(8).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate10(pulmEvals.get(9).getDate());
            setFev110(pulmEvals.get(9).getFev1());
            setFvc10(pulmEvals.get(9).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate11(pulmEvals.get(10).getDate());
            setFev111(pulmEvals.get(10).getFev1());
            setFvc11(pulmEvals.get(10).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate12(pulmEvals.get(11).getDate());
            setFev112(pulmEvals.get(11).getFev1());
            setFvc12(pulmEvals.get(11).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate13(pulmEvals.get(12).getDate());
            setFev113(pulmEvals.get(12).getFev1());
            setFvc13(pulmEvals.get(12).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate14(pulmEvals.get(13).getDate());
            setFev114(pulmEvals.get(13).getFev1());
            setFvc14(pulmEvals.get(13).getFvc());
        }
        i++;
        if(i < pulmEvals.size()){
            setDate15(pulmEvals.get(14).getDate());
            setFev115(pulmEvals.get(14).getFev1());
            setFvc15(pulmEvals.get(14).getFvc());
        }
    }

    public String getSafeToCrossover() {
        return (String) attributes.get("safeToCrossover").getValue();
    }
    
    public void setSafeToCrossover(String safeToCrossover) {
        attributes.get("safeToCrossover").setValue(safeToCrossover);
        this.setSafeToCrossoverResultsDisplay();
    }

    public boolean isSafeToCrossoverResultsDisplay() {
        return safeToCrossoverResultsDisplay;
    }
    
    public void setSafeToCrossoverResultsDisplay() {
        if("false".equals(getSafeToCrossover()) ||
           "false".equals(getPatientClinicallyWorsening()) ||
           "false".equals(getCrossoverRequested()) ||
           "false".equals(getAllFev1sEntered()) ||
           "false".equals(getPatientStaysInProtocol())) {
                safeToCrossoverResultsDisplay = true;
        } else {
            safeToCrossoverResultsDisplay = false;
        }
    }

    public String getPatientClinicallyWorsening() {
        return (String) attributes.get("patientClinicallyWorsening").getValue();
    }
    
    public void setPatientClinicallyWorsening(String patientClinicallyWorsening) {
        attributes.get("patientClinicallyWorsening").setValue(patientClinicallyWorsening);
        this.setSafeToCrossoverResultsDisplay();
    }
    
    public String getCrossoverRequested() {
        return (String) attributes.get("crossoverRequested").getValue();
    }
    
    public void setCrossoverRequested(String crossoverRequested) {
        attributes.get("crossoverRequested").setValue(crossoverRequested);
        this.setSafeToCrossoverResultsDisplay();
    }
    
    public String getAllFev1sEntered() {
        return (String) attributes.get("allFev1sEntered").getValue();
    }
    
    public void setAllFev1sEntered(String allFev1sEntered) {
        attributes.get("allFev1sEntered").setValue(allFev1sEntered);
        this.setSafeToCrossoverResultsDisplay();
    }
    
    public String getPatientStaysInProtocol() {
        return (String) attributes.get("patientStaysInProtocol").getValue();
    }
    
    public void setPatientStaysInProtocol(String patientStaysInProtocol) {
        attributes.get("patientStaysInProtocol").setValue(patientStaysInProtocol);
        this.setSafeToCrossoverResultsDisplay();
    }
    
    public String getWasCrossoverSafetyCheckUploaded() {
        return (String) attributes.get("wasCrossoverSafetyCheckUploaded").getValue();
    }
    
    public void setWasCrossoverSafetyCheckUploaded(String wasCrossoverSafetyCheckUploaded) {
        attributes.get("wasCrossoverSafetyCheckUploaded").setValue(wasCrossoverSafetyCheckUploaded);
    }

    public VerificationStatus getWasCrossoverSafetyCheckUploadedVerificationStatus() {
        System.out.println("getWasCrossoverSafetyCheckUploadedVerificationStatus was called containing: " + attributes.get("wasCrossoverSafetyCheckUploaded").getVerificationStatus());
        return attributes.get("wasCrossoverSafetyCheckUploaded").getVerificationStatus();
    }
    
    public void setWasCrossoverSafetyCheckUploadedVerificationStatus(VerificationStatus verificationStatus) {
        System.out.println("SET WasCrossoverSafetyCheckUploadedVerificationStatus was called containing: " + verificationStatus);
        attributes.get("wasCrossoverSafetyCheckUploaded").setVerificationStatus(verificationStatus);
    }

    public String getWasCrossoverSafetyCheckUploadedDccComment() {
        return (String) attributes.get("wasCrossoverSafetyCheckUploaded").getDccComment();
    }
    
    public void setWasCrossoverSafetyCheckUploadedDccComment(String dccComment) {
        attributes.get("wasCrossoverSafetyCheckUploaded").setDccComment(dccComment);
    }

    

    public Date getDate1() {
        return (Date) attributes.get("date1").getValue();
    }
    
    public void setDate1(Date date1) {
        attributes.get("date1").setValue(date1);
    }

    public Date getDate2() {
        return (Date) attributes.get("date2").getValue();
    }
    
    public void setDate2(Date date2) {
        attributes.get("date2").setValue(date2);
    }

    public Date getDate3() {
        return (Date) attributes.get("date3").getValue();
    }
    
    public void setDate3(Date date3) {
        attributes.get("date3").setValue(date3);
    }

    public Date getDate4() {
        return (Date) attributes.get("date4").getValue();
    }
    
    public void setDate4(Date date4) {
        attributes.get("date4").setValue(date4);
    }

    public Date getDate5() {
        return (Date) attributes.get("date5").getValue();
    }
    
    public void setDate5(Date date5) {
        attributes.get("date5").setValue(date5);
    }

    public Date getDate6() {
        return (Date) attributes.get("date6").getValue();
    }
    
    public void setDate6(Date date6) {
        attributes.get("date6").setValue(date6);
    }

    public Date getDate7() {
        return (Date) attributes.get("date7").getValue();
    }
    
    public void setDate7(Date date7) {
        attributes.get("date7").setValue(date7);
    }

    public Date getDate8() {
        return (Date) attributes.get("date8").getValue();
    }
    
    public void setDate8(Date date8) {
        attributes.get("date8").setValue(date8);
    }

    public Date getDate9() {
        return (Date) attributes.get("date9").getValue();
    }
    
    public void setDate9(Date date9) {
        attributes.get("date9").setValue(date9);
    }

    public Date getDate10() {
        return (Date) attributes.get("date10").getValue();
    }
    
    public void setDate10(Date date10) {
        attributes.get("date10").setValue(date10);
    }

    public Date getDate11() {
        return (Date) attributes.get("date11").getValue();
    }
    
    public void setDate11(Date date11) {
        attributes.get("date11").setValue(date11);
    }

    public Date getDate12() {
        return (Date) attributes.get("date12").getValue();
    }
    
    public void setDate12(Date date12) {
        attributes.get("date12").setValue(date12);
    }

    public Date getDate13() {
        return (Date) attributes.get("date13").getValue();
    }
    
    public void setDate13(Date date13) {
        attributes.get("date13").setValue(date13);
    }

    public Date getDate14() {
        return (Date) attributes.get("date14").getValue();
    }
    
    public void setDate14(Date date14) {
        attributes.get("date14").setValue(date14);
    }

    public Date getDate15() {
        return (Date) attributes.get("date15").getValue();
    }
    
    public void setDate15(Date date15) {
        attributes.get("date15").setValue(date15);
    }

    
    public Float getFev11() {
        return (Float) attributes.get("fev11").getValue();
    }
    
    public void setFev11(Float fev11) {
        attributes.get("fev11").setValue(fev11);
    }

    public Float getFev12() {
        return (Float) attributes.get("fev12").getValue();
    }
    
    public void setFev12(Float fev12) {
        attributes.get("fev12").setValue(fev12);
    }

    public Float getFev13() {
        return (Float) attributes.get("fev13").getValue();
    }
    
    public void setFev13(Float fev13) {
        attributes.get("fev13").setValue(fev13);
    }

   public Float getFev14() {
        return (Float) attributes.get("fev14").getValue();
    }
    
    public void setFev14(Float fev14) {
        attributes.get("fev14").setValue(fev14);
    }

    public Float getFev15() {
        return (Float) attributes.get("fev15").getValue();
    }
    
    public void setFev15(Float fev15) {
        attributes.get("fev15").setValue(fev15);
    }

    public Float getFev16() {
        return (Float) attributes.get("fev16").getValue();
    }
    
    public void setFev16(Float fev16) {
        attributes.get("fev16").setValue(fev16);
    }

    public Float getFev17() {
        return (Float) attributes.get("fev17").getValue();
    }
    
    public void setFev17(Float fev17) {
        attributes.get("fev17").setValue(fev17);
    }

    public Float getFev18() {
        return (Float) attributes.get("fev18").getValue();
    }
    
    public void setFev18(Float fev18) {
        attributes.get("fev18").setValue(fev18);
    }

    public Float getFev19() {
        return (Float) attributes.get("fev19").getValue();
    }
    
    public void setFev19(Float fev19) {
        attributes.get("fev19").setValue(fev19);
    }

    public Float getFev110() {
        return (Float) attributes.get("fev110").getValue();
    }
    
    public void setFev110(Float fev110) {
        attributes.get("fev110").setValue(fev110);
    }

    public Float getFev111() {
        return (Float) attributes.get("fev111").getValue();
    }
    
    public void setFev111(Float fev111) {
        attributes.get("fev111").setValue(fev111);
    }

    public Float getFev112() {
        return (Float) attributes.get("fev112").getValue();
    }
    
    public void setFev112(Float fev112) {
        attributes.get("fev112").setValue(fev112);
    }

    public Float getFev113() {
        return (Float) attributes.get("fev113").getValue();
    }
    
    public void setFev113(Float fev113) {
        attributes.get("fev113").setValue(fev113);
    }

    public Float getFev114() {
        return (Float) attributes.get("fev114").getValue();
    }
    
    public void setFev114(Float fev114) {
        attributes.get("fev114").setValue(fev114);
    }

    public Float getFev115() {
        return (Float) attributes.get("fev115").getValue();
    }
    
    public void setFev115(Float fev115) {
        attributes.get("fev115").setValue(fev115);
    }


    public Float getFvc1() {
        return (Float) attributes.get("fvc1").getValue();
    }
    
    public void setFvc1(Float fvc1) {
        attributes.get("fvc1").setValue(fvc1);
    }

    public Float getFvc2() {
        return (Float) attributes.get("fvc2").getValue();
    }
    
    public void setFvc2(Float fvc2) {
        attributes.get("fvc2").setValue(fvc2);
    }

    public Float getFvc3() {
        return (Float) attributes.get("fvc3").getValue();
    }
    
    public void setFvc3(Float fvc3) {
        attributes.get("fvc3").setValue(fvc3);
    }

    public Float getFvc4() {
        return (Float) attributes.get("fvc4").getValue();
    }
    
    public void setFvc4(Float fvc4) {
        attributes.get("fvc4").setValue(fvc4);
    }

    public Float getFvc5() {
        return (Float) attributes.get("fvc5").getValue();
    }
    
    public void setFvc5(Float fvc5) {
        attributes.get("fvc5").setValue(fvc5);
    }

    public Float getFvc6() {
        return (Float) attributes.get("fvc6").getValue();
    }
    
    public void setFvc6(Float fvc6) {
        attributes.get("fvc6").setValue(fvc6);
    }

    public Float getFvc7() {
        return (Float) attributes.get("fvc7").getValue();
    }
    
    public void setFvc7(Float fvc7) {
        attributes.get("fvc7").setValue(fvc7);
    }

    public Float getFvc8() {
        return (Float) attributes.get("fvc8").getValue();
    }
    
    public void setFvc8(Float fvc8) {
        attributes.get("fvc8").setValue(fvc8);
    }

    public Float getFvc9() {
        return (Float) attributes.get("fvc9").getValue();
    }
    
    public void setFvc9(Float fvc9) {
        attributes.get("fvc9").setValue(fvc9);
    }

    public Float getFvc10() {
        return (Float) attributes.get("fvc10").getValue();
    }
    
    public void setFvc10(Float fvc10) {
        attributes.get("fvc10").setValue(fvc10);
    }

    public Float getFvc11() {
        return (Float) attributes.get("fvc11").getValue();
    }
    
    public void setFvc11(Float fvc11) {
        attributes.get("fvc11").setValue(fvc11);
    }

    public Float getFvc12() {
        return (Float) attributes.get("fvc12").getValue();
    }
    
    public void setFvc12(Float fvc12) {
        attributes.get("fvc12").setValue(fvc12);
    }

    public Float getFvc13() {
        return (Float) attributes.get("fvc13").getValue();
    }
    
    public void setFvc13(Float fvc13) {
        attributes.get("fvc13").setValue(fvc13);
    }

    public Float getFvc14() {
        return (Float) attributes.get("fvc14").getValue();
    }
    
    public void setFvc14(Float fvc14) {
        attributes.get("fvc14").setValue(fvc14);
    }

    public Float getFvc15() {
        return (Float) attributes.get("fvc15").getValue();
    }
    
    public void setFvc15(Float fvc15) {
        attributes.get("fvc15").setValue(fvc15);
    }


    public Float getSlope() {
        return (Float) attributes.get("slope").getValue();
    }
    
    public void setSlope(Float slope) {
        attributes.get("slope").setValue(slope);
    }

    public Float getSignificance() {
        return (Float) attributes.get("significance").getValue();
    }
    
    public void setSignificance(Float significance) {
        attributes.get("significance").setValue(significance);
    }


public List<PulmonaryEvaluation> getPulmEvaluations() {
        String dateKey = "date";
        String fev1Key = "fev1";
        String fvcKey = "fvc";
        Date d;
        List<PulmonaryEvaluation> evals = new ArrayList<PulmonaryEvaluation>();
        
        // Copy all of the attributes into an array containing PulmonaryEvaluation objects.
        for(int i = 1; i <= 15; i++){
            d = (Date) attributes.get(dateKey + i).getValue();
            if(d != null){
                float fev1 = (Float) attributes.get(fev1Key + i).getValue();
                float fvc = (Float) attributes.get(fvcKey + i).getValue();
                PulmonaryEvaluation pulmEval = new PulmonaryEvaluation(d, fev1, fvc);
                evals.add(pulmEval);
            }
        }
        return evals;
    }


}
