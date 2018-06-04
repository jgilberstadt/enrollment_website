package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class CrossoverEligibilityWorkSheet extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"History and Physical or Consultation Note", "Operative Report of Transplant Procedure", "Pulmonary Function Test Reports (for each FEV-1 submitted)"};
    
    private String pid;
    
    public CrossoverEligibilityWorkSheet() {
        super();
        this.formType = ECPFormTypes.CROSSOVER_ELIGIBILITY_WORKSHEET;
        title = "Crossover Eligibility Work Sheet";
        this.sourceDocumentTypes = SourceDocumentTypes;
        
        Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug("In constructor ()");
        
        attributes.put("date1", new AttributeDate("date1", true, false, true));
        attributes.put("date2", new AttributeDate("date2", true, false, true));
        attributes.put("date3", new AttributeDate("date3", true, false, true));
        attributes.put("date4", new AttributeDate("date4", true, false, true));
        attributes.put("date5", new AttributeDate("date5", true, false, true));
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
        attributes.put("fev11", new AttributeFloat("fev11", true, false, true));
        attributes.put("fev12", new AttributeFloat("fev12", true, false, true));
        attributes.put("fev13", new AttributeFloat("fev13", true, false, true));
        attributes.put("fev14", new AttributeFloat("fev14", true, false, true));
        attributes.put("fev15", new AttributeFloat("fev15", true, false, true));
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
        attributes.put("fvc1", new AttributeFloat("fvc1", true, false, true));
        attributes.put("fvc2", new AttributeFloat("fvc2", true, false, true));
        attributes.put("fvc3", new AttributeFloat("fvc3", true, false, true));
        attributes.put("fvc4", new AttributeFloat("fvc4", true, false, true));
        attributes.put("fvc5", new AttributeFloat("fvc5", true, false, true));
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
    }
	
    public CrossoverEligibilityWorkSheet( BasicForm bf) {
        super(bf);
        
        Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug("In constructor (BasicForm)");
        
        this.formType = ECPFormTypes.CROSSOVER_ELIGIBILITY_WORKSHEET;
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
    
    // This work sheet is normally created from values in the log.
    public CrossoverEligibilityWorkSheet( String pid, List<PulmonaryEvaluation> pulmEvals) {
        this();
        this.pid = pid;
        
        Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug("Evals size " + pulmEvals.size());
        
        for (int i = 1; i < pulmEvals.size() + 1; ++i) {
            Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug(pulmEvals.get(i - 1).getDate());
            attributes.get("date" + i).setValue(pulmEvals.get(i - 1).getDate());
            Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug(pulmEvals.get(i - 1).getFev1());
            attributes.get("fev1" + i).setValue(pulmEvals.get(i - 1).getFev1());
            Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug(pulmEvals.get(i - 1).getFvc());
            attributes.get("fvc" + i).setValue(pulmEvals.get(i - 1).getFvc());
        }
        
        Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug("In constructor (pid, pulmEvals)");
        Logger.getLogger(CrossoverEligibilityWorkSheet.class).debug("Eval1: " + getDate1() + " " + getFev11() + " " + getFvc1());
        /*
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
        */
    }
    
    public String getParticipantID() {
        return pid;
    }
    
    public void setParticipantID( String id) {
        this.pid = id;
    }
    
    public Date getDate1() {
        return (Date) attributes.get("date1").getValue();
    }
    
    public void setDate1(Date date1) {
        attributes.get("date1").setValue(date1);
    }

    public VerificationStatus getDate1VerificationStatus() {
        return attributes.get("date1").getVerificationStatus();
    }
    
    public void setDate1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date1").setVerificationStatus(verificationStatus);
    }

    public String getDate1DccComment() {
        return (String) attributes.get("date1").getDccComment();
    }
    
    public void setDate1DccComment(String dccComment) {
        attributes.get("date1").setDccComment(dccComment);
    }

    public Date getDate2() {
        return (Date) attributes.get("date2").getValue();
    }
    
    public void setDate2(Date date2) {
        attributes.get("date2").setValue(date2);
    }

    public VerificationStatus getDate2VerificationStatus() {
        return attributes.get("date2").getVerificationStatus();
    }
    
    public void setDate2VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date2").setVerificationStatus(verificationStatus);
    }

    public String getDate2DccComment() {
        return (String) attributes.get("date2").getDccComment();
    }
    
    public void setDate2DccComment(String dccComment) {
        attributes.get("date2").setDccComment(dccComment);
    }

    public Date getDate3() {
        return (Date) attributes.get("date3").getValue();
    }
    
    public void setDate3(Date date3) {
        attributes.get("date3").setValue(date3);
    }

    public VerificationStatus getDate3VerificationStatus() {
        return attributes.get("date3").getVerificationStatus();
    }
    
    public void setDate3VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date3").setVerificationStatus(verificationStatus);
    }

    public String getDate3DccComment() {
        return (String) attributes.get("date3").getDccComment();
    }
    
    public void setDate3DccComment(String dccComment) {
        attributes.get("date3").setDccComment(dccComment);
    }

    public Date getDate4() {
        return (Date) attributes.get("date4").getValue();
    }
    
    public void setDate4(Date date4) {
        attributes.get("date4").setValue(date4);
    }

    public VerificationStatus getDate4VerificationStatus() {
        return attributes.get("date4").getVerificationStatus();
    }
    
    public void setDate4VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date4").setVerificationStatus(verificationStatus);
    }

    public String getDate4DccComment() {
        return (String) attributes.get("date4").getDccComment();
    }
    
    public void setDate4DccComment(String dccComment) {
        attributes.get("date4").setDccComment(dccComment);
    }

    public Date getDate5() {
        return (Date) attributes.get("date5").getValue();
    }
    
    public void setDate5(Date date5) {
        attributes.get("date5").setValue(date5);
    }

    public VerificationStatus getDate5VerificationStatus() {
        return attributes.get("date5").getVerificationStatus();
    }
    
    public void setDate5VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("date5").setVerificationStatus(verificationStatus);
    }

    public String getDate5DccComment() {
        return (String) attributes.get("date5").getDccComment();
    }
    
    public void setDate5DccComment(String dccComment) {
        attributes.get("date5").setDccComment(dccComment);
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

    public VerificationStatus getFev11VerificationStatus() {
        return attributes.get("fev11").getVerificationStatus();
    }
    
    public void setFev11VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev11").setVerificationStatus(verificationStatus);
    }

    public String getFev11DccComment() {
        return (String) attributes.get("fev11").getDccComment();
    }
    
    public void setFev11DccComment(String dccComment) {
        attributes.get("fev11").setDccComment(dccComment);
    }

    public Float getFev12() {
        return (Float) attributes.get("fev12").getValue();
    }
    
    public void setFev12(Float fev12) {
        attributes.get("fev12").setValue(fev12);
    }

    public VerificationStatus getFev12VerificationStatus() {
        return attributes.get("fev12").getVerificationStatus();
    }
    
    public void setFev12VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev12").setVerificationStatus(verificationStatus);
    }

    public String getFev12DccComment() {
        return (String) attributes.get("fev12").getDccComment();
    }
    
    public void setFev12DccComment(String dccComment) {
        attributes.get("fev12").setDccComment(dccComment);
    }

    public Float getFev13() {
        return (Float) attributes.get("fev13").getValue();
    }
    
    public void setFev13(Float fev13) {
        attributes.get("fev13").setValue(fev13);
    }

    public VerificationStatus getFev13VerificationStatus() {
        return attributes.get("fev13").getVerificationStatus();
    }
    
    public void setFev13VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev13").setVerificationStatus(verificationStatus);
    }

    public String getFev13DccComment() {
        return (String) attributes.get("fev13").getDccComment();
    }
    
    public void setFev13DccComment(String dccComment) {
        attributes.get("fev13").setDccComment(dccComment);
    }

    public Float getFev14() {
        return (Float) attributes.get("fev14").getValue();
    }
    
    public void setFev14(Float fev14) {
        attributes.get("fev14").setValue(fev14);
    }

    public VerificationStatus getFev14VerificationStatus() {
        return attributes.get("fev14").getVerificationStatus();
    }
    
    public void setFev14VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev14").setVerificationStatus(verificationStatus);
    }

    public String getFev14DccComment() {
        return (String) attributes.get("fev14").getDccComment();
    }
    
    public void setFev14DccComment(String dccComment) {
        attributes.get("fev14").setDccComment(dccComment);
    }

    public Float getFev15() {
        return (Float) attributes.get("fev15").getValue();
    }
    
    public void setFev15(Float fev15) {
        attributes.get("fev15").setValue(fev15);
    }

    public VerificationStatus getFev15VerificationStatus() {
        return attributes.get("fev15").getVerificationStatus();
    }
    
    public void setFev15VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fev15").setVerificationStatus(verificationStatus);
    }

    public String getFev15DccComment() {
        return (String) attributes.get("fev15").getDccComment();
    }
    
    public void setFev15DccComment(String dccComment) {
        attributes.get("fev15").setDccComment(dccComment);
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

    public VerificationStatus getFvc1VerificationStatus() {
        return attributes.get("fvc1").getVerificationStatus();
    }
    
    public void setFvc1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc1").setVerificationStatus(verificationStatus);
    }
  
    public String getFvc1DccComment() {
        return (String) attributes.get("fvc1").getDccComment();
    }
    
    public void setFvc1DccComment(String dccComment) {
        attributes.get("fvc1").setDccComment(dccComment);
    }

    public Float getFvc2() {
        return (Float) attributes.get("fvc2").getValue();
    }
    
    public void setFvc2(Float fvc2) {
        attributes.get("fvc2").setValue(fvc2);
    }

    public VerificationStatus getFvc2VerificationStatus() {
        return attributes.get("fvc2").getVerificationStatus();
    }
    
    public void setFvc2VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc2").setVerificationStatus(verificationStatus);
    }

    public String getFvc2DccComment() {
        return (String) attributes.get("fvc2").getDccComment();
    }
    
    public void setFvc2DccComment(String dccComment) {
        attributes.get("fvc2").setDccComment(dccComment);
    }

    public Float getFvc3() {
        return (Float) attributes.get("fvc3").getValue();
    }
    
    public void setFvc3(Float fvc3) {
        attributes.get("fvc3").setValue(fvc3);
    }

    public VerificationStatus getFvc3VerificationStatus() {
        return attributes.get("fvc3").getVerificationStatus();
    }
    
    public void setFvc3VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc3").setVerificationStatus(verificationStatus);
    }

    public String getFvc3DccComment() {
        return (String) attributes.get("fvc3").getDccComment();
    }
    
    public void setFvc3DccComment(String dccComment) {
        attributes.get("fvc3").setDccComment(dccComment);
    }

    public Float getFvc4() {
        return (Float) attributes.get("fvc4").getValue();
    }
    
    public void setFvc4(Float fvc4) {
        attributes.get("fvc4").setValue(fvc4);
    }

    public VerificationStatus getFvc4VerificationStatus() {
        return attributes.get("fvc4").getVerificationStatus();
    }
    
    public void setFvc4VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc4").setVerificationStatus(verificationStatus);
    }

    public String getFvc4DccComment() {
        return (String) attributes.get("fvc4").getDccComment();
    }
    
    public void setFvc4DccComment(String dccComment) {
        attributes.get("fvc4").setDccComment(dccComment);
    }

    public Float getFvc5() {
        return (Float) attributes.get("fvc5").getValue();
    }
    
    public void setFvc5(Float fvc5) {
        attributes.get("fvc5").setValue(fvc5);
    }

    public VerificationStatus getFvc5VerificationStatus() {
        return attributes.get("fvc5").getVerificationStatus();
    }
    
    public void setFvc5VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("fvc5").setVerificationStatus(verificationStatus);
    }

    public String getFvc5DccComment() {
        return (String) attributes.get("fvc5").getDccComment();
    }
    
    public void setFvc5DccComment(String dccComment) {
        attributes.get("fvc5").setDccComment(dccComment);
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


    public List<PulmonaryEvaluation> getEvaluations() {
        String dateKey = "date";
        String fev1Key = "fev1";
        String fvcKey = "fvc";
        Date d;
        List<PulmonaryEvaluation> evals = new ArrayList<PulmonaryEvaluation>();
        
        // Copy all of the attributes into an array containing PulmonaryEvaluation objects.
        for(int i = 1; i <= 15; i++){
            d = (Date) attributes.get(dateKey + i).getValue();
            if(d != null){
                Float fev1 = (Float) attributes.get(fev1Key + i).getValue();
                Float fvc = (Float) attributes.get(fvcKey + i).getValue();
                PulmonaryEvaluation pulmEval = new PulmonaryEvaluation(d, fev1, fvc);
                evals.add(pulmEval);
            }
        }
        return evals;
    }


}
