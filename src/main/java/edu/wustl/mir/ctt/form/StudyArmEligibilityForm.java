package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.Attribute;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.VerificationStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StudyArmEligibilityForm extends BasicForm {
    
    public static final String[] SourceDocumentTypes = new String[]{"History and Physical or Consultation Note", "Operative Report of Transplant Procedure", "Pulmonary Function Test Reports (for each FEV-1 submitted)"};
    
    public StudyArmEligibilityForm() {
        super();
        this.formType = ECPFormTypes.STUDY_ARM_ELIGIBILITY;
        title = "Confirmation of Eligibility Form";
        this.sourceDocumentTypes = SourceDocumentTypes;
        
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
        attributes.put("date16", new AttributeDate("date16"));
        attributes.put("date17", new AttributeDate("date17"));
        attributes.put("date18", new AttributeDate("date18"));
        attributes.put("date19", new AttributeDate("date19"));
        attributes.put("date20", new AttributeDate("date20"));
        attributes.put("date21", new AttributeDate("date21"));
        attributes.put("date22", new AttributeDate("date22"));
        attributes.put("date23", new AttributeDate("date23"));
        attributes.put("date24", new AttributeDate("date24"));
        attributes.put("date25", new AttributeDate("date25"));
        attributes.put("date26", new AttributeDate("date26"));
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
        attributes.put("fev116", new AttributeFloat("fev116"));
        attributes.put("fev117", new AttributeFloat("fev117"));
        attributes.put("fev118", new AttributeFloat("fev118"));
        attributes.put("fev119", new AttributeFloat("fev119"));
        attributes.put("fev120", new AttributeFloat("fev120"));
        attributes.put("fev121", new AttributeFloat("fev121"));
        attributes.put("fev122", new AttributeFloat("fev122"));
        attributes.put("fev123", new AttributeFloat("fev123"));
        attributes.put("fev124", new AttributeFloat("fev124"));
        attributes.put("fev125", new AttributeFloat("fev125"));
        attributes.put("fev126", new AttributeFloat("fev126"));
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
        attributes.put("fvc16", new AttributeFloat("fvc16"));
        attributes.put("fvc17", new AttributeFloat("fvc17"));
        attributes.put("fvc18", new AttributeFloat("fvc18"));
        attributes.put("fvc19", new AttributeFloat("fvc19"));
        attributes.put("fvc20", new AttributeFloat("fvc20"));
        attributes.put("fvc21", new AttributeFloat("fvc21"));
        attributes.put("fvc22", new AttributeFloat("fvc22"));
        attributes.put("fvc23", new AttributeFloat("fvc23"));
        attributes.put("fvc24", new AttributeFloat("fvc24"));
        attributes.put("fvc25", new AttributeFloat("fvc25"));
        attributes.put("fvc26", new AttributeFloat("fvc26"));
        
        attributes.put("slope", new AttributeFloat("slope"));
        attributes.put("significance", new AttributeFloat("significance"));
        attributes.put("fev1OldestPossibleDate", new AttributeString("fev1OldestPossibleDate"));
        
        attributes.put("baselineFEV1", new AttributeFloat("baselineFEV1", true, false, true));
        attributes.put("firstComponentFEV1Date", new AttributeDate("firstComponentFEV1Date", true, false, true));
        attributes.put("firstComponentFEV1", new AttributeFloat("firstComponentFEV1", true, false, true));
        attributes.put("firstComponentFVC", new AttributeFloat("firstComponentFVC", true, false, true));
        attributes.put("secondComponentFEV1Date", new AttributeDate("secondComponentFEV1Date", true, false, true));
        attributes.put("secondComponentFEV1", new AttributeFloat("secondComponentFEV1", true, false, true));
        attributes.put("secondComponentFVC", new AttributeFloat("secondComponentFVC", true, false, true));
        
        attributes.put("postTransBOSDiagDate", new AttributeDate("postTransBOSDiagDate"));
        attributes.put("firstPostTransBOSDiagFEV1Date", new AttributeDate("firstPostTransBOSDiagFEV1Date", true, false, true));
        attributes.put("firstPostTransBOSDiagFEV1", new AttributeFloat("firstPostTransBOSDiagFEV1", true, false, true));
        attributes.put("firstPostTransBOSDiagFVC", new AttributeFloat("firstPostTransBOSDiagFVC", true, false, true));
        attributes.put("secondPostTransBOSDiagFEV1Date", new AttributeDate("secondPostTransBOSDiagFEV1Date", true, false, true));
        attributes.put("secondPostTransBOSDiagFEV1", new AttributeFloat("secondPostTransBOSDiagFEV1", true, false, true));
        attributes.put("secondPostTransBOSDiagFVC", new AttributeFloat("secondPostTransBOSDiagFVC", true, false, true));
        attributes.put("thirdPostTransBOSDiagFEV1Date", new AttributeDate("thirdPostTransBOSDiagFEV1Date", true, true, true));
        attributes.put("thirdPostTransBOSDiagFEV1", new AttributeFloat("thirdPostTransBOSDiagFEV1", true, true, true));
        attributes.put("thirdPostTransBOSDiagFVC", new AttributeFloat("thirdPostTransBOSDiagFVC", true, true, true));

        attributes.put("lungTransplantationDate", new AttributeDate("lungTransplantationDate", true, false, true));
        
        attributes.put("mostRecentExamDate", new AttributeDate("mostRecentExamDate"));
        
        attributes.put("completeBloodCountDate", new AttributeDate("completeBloodCountDate", true, false, true));
        attributes.put("wbcs", new AttributeFloat("wbcs", true, false, true));
        attributes.put("rbcs", new AttributeFloat("rbcs", true, false, true));
        attributes.put("hematocrit", new AttributeFloat("hematocrit", true, false, true));
        attributes.put("hemoglobin", new AttributeFloat("hemoglobin", true, false, true));
        attributes.put("platelets", new AttributeFloat("platelets", true, false, true));
    }
	
    public StudyArmEligibilityForm( BasicForm bf) {
        super(bf);
        this.formType = ECPFormTypes.STUDY_ARM_ELIGIBILITY;
        title = bf.getTitle();
        this.sourceDocumentTypes = SourceDocumentTypes;
    }
    
    public List<PulmonaryEvaluation> getPulmEvaluations(){
        List<PulmonaryEvaluation> pulmEvals = new ArrayList<PulmonaryEvaluation>();
        List<PulmonaryEvaluation> pulmEvals2 = new ArrayList<PulmonaryEvaluation>();
        Date dateOfFEV1;
        Float fev1;
        Float fvc;
        String dateKey = "date";
        String fev1Key = "fev1";
        String fvcKey = "fvc";
        
        // Copy all of the attributes into an array containing PulmonaryEvaluation objects.
        for(int i = 1; i <= 26; i++){
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

        // Remove any dates older than six months from the list.
        // Turn this constraint off while GD does testing of calculator.
        PulmonaryEvaluation pe;
        int days = 0;
        Iterator iterator = pulmEvals.iterator();
        while(iterator.hasNext()){
            pe = (PulmonaryEvaluation) iterator.next();
            days = (int) dateDifferenceInDays(pe.getDate());
            if(days < 183){
                pulmEvals2.add(pe);
            }
        }

        return pulmEvals2;
//        return pulmEvals;
    }
    
    public long dateDifferenceInDays(Date date){
        long dateDiffInDays = (System.currentTimeMillis() - date.getTime())/(60*60*24*1000);
        System.out.println("The date difference is: " + dateDiffInDays + "\n");
        return dateDiffInDays;
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
    
    public Date getDate16() {
        return (Date) attributes.get("date16").getValue();
    }

    public void setDate16(Date date16) {
        attributes.get("date16").setValue(date16);
    }

    public Date getDate17() {
        return (Date) attributes.get("date17").getValue();
    }

    public void setDate17(Date date17) {
        attributes.get("date17").setValue(date17);
    }

    public Date getDate18() {
        return (Date) attributes.get("date18").getValue();
    }

    public void setDate18(Date date18) {
        attributes.get("date18").setValue(date18);
    }

    public Date getDate19() {
        return (Date) attributes.get("date19").getValue();
    }

    public void setDate19(Date date19) {
        attributes.get("date19").setValue(date19);
    }

    public Date getDate20() {
        return (Date) attributes.get("date20").getValue();
    }

    public void setDate20(Date date20) {
        attributes.get("date20").setValue(date20);
    }

    public Date getDate21() {
        return (Date) attributes.get("date21").getValue();
    }

    public void setDate21(Date date21) {
        attributes.get("date21").setValue(date21);
    }

    public Date getDate22() {
        return (Date) attributes.get("date22").getValue();
    }

    public void setDate22(Date date22) {
        attributes.get("date22").setValue(date22);
    }

    public Date getDate23() {
        return (Date) attributes.get("date23").getValue();
    }

    public void setDate23(Date date23) {
        attributes.get("date23").setValue(date23);
    }

    public Date getDate24() {
        return (Date) attributes.get("date24").getValue();
    }

    public void setDate24(Date date24) {
        attributes.get("date24").setValue(date24);
    }

    public Date getDate25() {
        return (Date) attributes.get("date25").getValue();
    }

    public void setDate25(Date date25) {
        attributes.get("date25").setValue(date25);
    }

    public Date getDate26() {
        return (Date) attributes.get("date26").getValue();
    }

    public void setDate26(Date date26) {
        attributes.get("date26").setValue(date26);
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
//        System.out.println("The fev16 value is: " + fev16 + "\n");
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

    public Float getFev116() {
       return (Float) attributes.get("fev116").getValue();
    }

    public void setFev116(Float fev116) {
        attributes.get("fev116").setValue(fev116);
    }

    public Float getFev117() {
        return (Float) attributes.get("fev117").getValue();
    }

    public void setFev117(Float fev117) {
        attributes.get("fev117").setValue(fev117);
    }

    public Float getFev118() {
        return (Float) attributes.get("fev118").getValue();
    }

    public void setFev118(Float fev118) {
        attributes.get("fev118").setValue(fev118);
    }

    public Float getFev119() {
        return (Float) attributes.get("fev119").getValue();
    }

    public void setFev119(Float fev119) {
        attributes.get("fev119").setValue(fev119);
    }

    public Float getFev120() {
        return (Float) attributes.get("fev120").getValue();
    }

    public void setFev120(Float fev120) {
        attributes.get("fev120").setValue(fev120);
    }

    public Float getFev121() {
        return (Float) attributes.get("fev121").getValue();
    }

    public void setFev121(Float fev121) {
        attributes.get("fev121").setValue(fev121);
    }

    public Float getFev122() {
        return (Float) attributes.get("fev122").getValue();
    }

    public void setFev122(Float fev122) {
        attributes.get("fev122").setValue(fev122);
    }

    public Float getFev123() {
        return (Float) attributes.get("fev123").getValue();
    }

    public void setFev123(Float fev123) {
        attributes.get("fev123").setValue(fev123);
    }

    public Float getFev124() {
        return (Float) attributes.get("fev124").getValue();
    }

    public void setFev124(Float fev124) {
        attributes.get("fev124").setValue(fev124);
    }

    public Float getFev125() {
        return (Float) attributes.get("fev125").getValue();
    }

    public void setFev125(Float fev125) {
        attributes.get("fev125").setValue(fev125);
    }

    public Float getFev126() {
        return (Float) attributes.get("fev126").getValue();
    }

    public void setFev126(Float fev126) {
        attributes.get("fev126").setValue(fev126);
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

    public Float getFvc16() {
        return (Float) attributes.get("fvc16").getValue();
    }

    public void setFvc16(Float fvc16) {
        attributes.get("fvc16").setValue(fvc16);
    }

    public Float getFvc17() {
        return (Float) attributes.get("fvc17").getValue();
    }

    public void setFvc17(Float fvc17) {
        attributes.get("fvc17").setValue(fvc17);
    }

    public Float getFvc18() {
        return (Float) attributes.get("fvc18").getValue();
    }

    public void setFvc18(Float fvc18) {
        attributes.get("fvc18").setValue(fvc18);
    }

    public Float getFvc19() {
        return (Float) attributes.get("fvc19").getValue();
    }

    public void setFvc19(Float fvc19) {
        attributes.get("fvc19").setValue(fvc19);
    }

    public Float getFvc20() {
        return (Float) attributes.get("fvc20").getValue();
    }

    public void setFvc20(Float fvc20) {
        attributes.get("fvc20").setValue(fvc20);
    }

    public Float getFvc21() {
        return (Float) attributes.get("fvc21").getValue();
    }

    public void setFvc21(Float fvc21) {
        attributes.get("fvc21").setValue(fvc21);
    }

    public Float getFvc22() {
        return (Float) attributes.get("fvc22").getValue();
    }

    public void setFvc22(Float fvc22) {
        attributes.get("fvc22").setValue(fvc22);
    }

    public Float getFvc23() {
        return (Float) attributes.get("fvc23").getValue();
    }

    public void setFvc23(Float fvc23) {
        attributes.get("fvc23").setValue(fvc23);
    }

    public Float getFvc24() {
        return (Float) attributes.get("fvc24").getValue();
    }

    public void setFvc24(Float fvc24) {
        attributes.get("fvc24").setValue(fvc24);
    }

    public Float getFvc25() {
        return (Float) attributes.get("fvc25").getValue();
    }

    public void setFvc25(Float fvc25) {
        attributes.get("fvc25").setValue(fvc25);
    }

    public Float getFvc26() {
        return (Float) attributes.get("fvc26").getValue();
    }

    public void setFvc26(Float fvc26) {
        attributes.get("fvc26").setValue(fvc26);
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
    
    // ISHLT baseline attributes
    public Float getBaselineFEV1() {
        return (Float) attributes.get("baselineFEV1").getValue();
    }
    
    public void setBaselineFEV1(Float baselineFEV1) {
        attributes.get("baselineFEV1").setValue(baselineFEV1);
    }

    public VerificationStatus getBaselineFEV1VerificationStatus() {
        return attributes.get("baselineFEV1").getVerificationStatus();
    }
    
    public void setBaselineFEV1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("baselineFEV1").setVerificationStatus(verificationStatus);
    }

    public String getBaselineFEV1DccComment() {
        return (String) attributes.get("baselineFEV1").getDccComment();
    }
    
    public void setBaselineFEV1DccComment(String dccComment) {
        attributes.get("baselineFEV1").setDccComment(dccComment);
    }

    public Date getFirstComponentFEV1Date() {
        return (Date) attributes.get("firstComponentFEV1Date").getValue();
    }
    
    public void setFirstComponentFEV1Date(Date firstComponentFEV1Date) {
        attributes.get("firstComponentFEV1Date").setValue(firstComponentFEV1Date);
    }

    public VerificationStatus getFirstComponentFEV1DateVerificationStatus() {
        return attributes.get("firstComponentFEV1Date").getVerificationStatus();
    }
    
    public void setFirstComponentFEV1DateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("firstComponentFEV1Date").setVerificationStatus(verificationStatus);
    }

    public String getFirstComponentFEV1DateDccComment() {
        return (String) attributes.get("firstComponentFEV1Date").getDccComment();
    }
    
    public void setFirstComponentFEV1DateDccComment(String dccComment) {
        attributes.get("firstComponentFEV1Date").setDccComment(dccComment);
    }

    public Float getFirstComponentFEV1() {
        return (Float) attributes.get("firstComponentFEV1").getValue();
    }
    
    public void setFirstComponentFEV1(Float firstComponentFEV1) {
        attributes.get("firstComponentFEV1").setValue(firstComponentFEV1);
        
        Float secondComponentFEV1 = getSecondComponentFEV1();
        if (secondComponentFEV1 == null) {
            setBaselineFEV1(firstComponentFEV1);
        } else {
            setBaselineFEV1((firstComponentFEV1 + secondComponentFEV1)/2.0f);
        }
    }

    public VerificationStatus getFirstComponentFEV1VerificationStatus() {
        return attributes.get("firstComponentFEV1").getVerificationStatus();
    }
    
    public void setFirstComponentFEV1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("firstComponentFEV1").setVerificationStatus(verificationStatus);
    }

    public String getFirstComponentFEV1DccComment() {
        return (String) attributes.get("firstComponentFEV1").getDccComment();
    }
    
    public void setFirstComponentFEV1DccComment(String dccComment) {
        attributes.get("firstComponentFEV1").setDccComment(dccComment);
    }

    public Float getFirstComponentFVC() {
        return (Float) attributes.get("firstComponentFVC").getValue();
    }
    
    public void setFirstComponentFVC(Float firstComponentFVC) {
        attributes.get("firstComponentFVC").setValue(firstComponentFVC);
    }

    public VerificationStatus getFirstComponentFVCVerificationStatus() {
        return attributes.get("firstComponentFVC").getVerificationStatus();
    }
    
    public void setFirstComponentFVCVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("firstComponentFVC").setVerificationStatus(verificationStatus);
    }

    public String getFirstComponentFVCDccComment() {
        return (String) attributes.get("firstComponentFVC").getDccComment();
    }
    
    public void setFirstComponentFVCDccComment(String dccComment) {
        attributes.get("firstComponentFVC").setDccComment(dccComment);
    }

    public Date getSecondComponentFEV1Date() {
        return (Date) attributes.get("secondComponentFEV1Date").getValue();
    }
    
    public void setSecondComponentFEV1Date(Date secondComponentFEV1Date) {
        attributes.get("secondComponentFEV1Date").setValue(secondComponentFEV1Date);
    }

    public VerificationStatus getSecondComponentFEV1DateVerificationStatus() {
        return attributes.get("secondComponentFEV1Date").getVerificationStatus();
    }
    
    public void setSecondComponentFEV1DateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("secondComponentFEV1Date").setVerificationStatus(verificationStatus);
    }

    public String getSecondComponentFEV1DateDccComment() {
        return (String) attributes.get("secondComponentFEV1Date").getDccComment();
    }
    
    public void setSecondComponentFEV1DateDccComment(String dccComment) {
        attributes.get("secondComponentFEV1Date").setDccComment(dccComment);
    }

    public Float getSecondComponentFEV1() {
        return (Float) attributes.get("secondComponentFEV1").getValue();
    }
    
    public void setSecondComponentFEV1(Float secondComponentFEV1) {
        attributes.get("secondComponentFEV1").setValue(secondComponentFEV1);
        
        Float firstComponentFEV1 = getFirstComponentFEV1();
        if (firstComponentFEV1 == null) {
            setBaselineFEV1(secondComponentFEV1);
        } else {
            setBaselineFEV1((firstComponentFEV1 + secondComponentFEV1)/2.0f);
        }
    }

    public VerificationStatus getSecondComponentFEV1VerificationStatus() {
        return attributes.get("secondComponentFEV1").getVerificationStatus();
    }
    
    public void setSecondComponentFEV1VerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("secondComponentFEV1").setVerificationStatus(verificationStatus);
    }

    public String getSecondComponentFEV1DccComment() {
        return (String) attributes.get("secondComponentFEV1").getDccComment();
    }
    
    public void setSecondComponentFEV1DccComment(String dccComment) {
        attributes.get("secondComponentFEV1").setDccComment(dccComment);
    }

    public Float getSecondComponentFVC() {
        return (Float) attributes.get("secondComponentFVC").getValue();
    }
    
    public void setSecondComponentFVC(Float secondComponentFVC) {
        attributes.get("secondComponentFVC").setValue(secondComponentFVC);
    }

    public VerificationStatus getSecondComponentFVCVerificationStatus() {
        return attributes.get("secondComponentFVC").getVerificationStatus();
    }
    
    public void setSecondComponentFVCVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("secondComponentFVC").setVerificationStatus(verificationStatus);
    }

    public String getSecondComponentFVCDccComment() {
        return (String) attributes.get("secondComponentFVC").getDccComment();
    }
    
    public void setSecondComponentFVCDccComment(String dccComment) {
        attributes.get("secondComponentFVC").setDccComment(dccComment);
    }
    
    public Date getLungTransplantationDate() {
        return (Date) attributes.get("lungTransplantationDate").getValue();
    }
    
    public void setLungTransplantationDate(Date lungTransplantationDate) {
        attributes.get("lungTransplantationDate").setValue(lungTransplantationDate);
    }

    public VerificationStatus getLungTransplantationDateVerificationStatus() {
        return attributes.get("lungTransplantationDate").getVerificationStatus();
    }
    
    public void setLungTransplantationDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("lungTransplantationDate").setVerificationStatus(verificationStatus);
    }

    public String getLungTransplantationDateDccComment() {
        return (String) attributes.get("lungTransplantationDate").getDccComment();
    }
    
    public void setLungTransplantationDateDccComment(String dccComment) {
        attributes.get("lungTransplantationDate").setDccComment(dccComment);
    }
    
    public Date getCompleteBloodCountDate() {
        return (Date) attributes.get("completeBloodCountDate").getValue();
    }
    
    public void setCompleteBloodCountDate(Date completeBloodCountDate) {
        attributes.get("completeBloodCountDate").setValue(completeBloodCountDate);
    }

    public VerificationStatus getCompleteBloodCountDateVerificationStatus() {
        return attributes.get("completeBloodCountDate").getVerificationStatus();
    }
    
    public void setCompleteBloodCountDateVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("completeBloodCountDate").setVerificationStatus(verificationStatus);
    }

    public String getCompleteBloodCountDateDccComment() {
        return (String) attributes.get("completeBloodCountDate").getDccComment();
    }
    
    public void setCompleteBloodCountDateDccComment(String dccComment) {
        attributes.get("completeBloodCountDate").setDccComment(dccComment);
    }

    public Float getWbcs() {
        return (Float) attributes.get("wbcs").getValue();
    }
    
    public void setWbcs(Float wbcs) {
        attributes.get("wbcs").setValue(wbcs);
    }

    public VerificationStatus getWbcsVerificationStatus() {
        return attributes.get("wbcs").getVerificationStatus();
    }
    
    public void setWbcsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("wbcs").setVerificationStatus(verificationStatus);
    }

    public String getWbcsDccComment() {
        return (String) attributes.get("wbcs").getDccComment();
    }
    
    public void setWbcsDccComment(String dccComment) {
        attributes.get("wbcs").setDccComment(dccComment);
    }

    public Float getRbcs() {
        return (Float) attributes.get("rbcs").getValue();
    }
    
    public void setRbcs(Float rbcs) {
        attributes.get("rbcs").setValue(rbcs);
    }

    public VerificationStatus getRbcsVerificationStatus() {
        return attributes.get("rbcs").getVerificationStatus();
    }
    
    public void setRbcsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("rbcs").setVerificationStatus(verificationStatus);
    }

    public String getRbcsDccComment() {
        return (String) attributes.get("rbcs").getDccComment();
    }
    
    public void setRbcsDccComment(String dccComment) {
        attributes.get("rbcs").setDccComment(dccComment);
    }

    public Float getHemoglobin() {
        return (Float) attributes.get("hemoglobin").getValue();
    }
    
    public void setHemoglobin(Float hemoglobin) {
        attributes.get("hemoglobin").setValue(hemoglobin);
    }

    public VerificationStatus getHemoglobinVerificationStatus() {
        return attributes.get("hemoglobin").getVerificationStatus();
    }
    
    public void setHemoglobinVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("hemoglobin").setVerificationStatus(verificationStatus);
    }

    public String getHemoglobinDccComment() {
        return (String) attributes.get("hemoglobin").getDccComment();
    }
    
    public void setHemoglobinDccComment(String dccComment) {
        attributes.get("hemoglobin").setDccComment(dccComment);
    }
    
    public Float getHematocrit() {
        return (Float) attributes.get("hematocrit").getValue();
    }
    
    public void setHematocrit(Float hematocrit) {
        attributes.get("hematocrit").setValue(hematocrit);
    }

    public VerificationStatus getHematocritVerificationStatus() {
        return attributes.get("hematocrit").getVerificationStatus();
    }
    
    public void setHematocritVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("hematocrit").setVerificationStatus(verificationStatus);
    }

    public String getHematocritDccComment() {
        return (String) attributes.get("hematocrit").getDccComment();
    }
    
    public void setHematocritDccComment(String dccComment) {
        attributes.get("hematocrit").setDccComment(dccComment);
    }
    
     public Float getPlatelets() {
        return (Float) attributes.get("platelets").getValue();
    }
    
    public void setPlatelets(Float platelets) {
        attributes.get("platelets").setValue(platelets);
    }

    public VerificationStatus getPlateletsVerificationStatus() {
        return attributes.get("platelets").getVerificationStatus();
    }
    
    public void setPlateletsVerificationStatus(VerificationStatus verificationStatus) {
        attributes.get("platelets").setVerificationStatus(verificationStatus);
    }

    public String getPlateletsDccComment() {
        return (String) attributes.get("platelets").getDccComment();
    }
    
    public void setPlateletsDccComment(String dccComment) {
        attributes.get("platelets").setDccComment(dccComment);
    }
    
    public Date getPostTransBOSDiagDate() {
       return (Date) attributes.get("postTransBOSDiagDate").getValue();
    }

    public void setPostTransBOSDiagDate(Date postTransBOSDiagDate) {
        attributes.get("postTransBOSDiagDate").setValue(postTransBOSDiagDate);
    }

    public Date getFirstPostTransBOSDiagFEV1Date() {
        return (Date) attributes.get("firstPostTransBOSDiagFEV1Date").getValue();
    }

    public void setFirstPostTransBOSDiagFEV1Date(Date firstPostTransBOSDiagFEV1Date) {
        attributes.get("firstPostTransBOSDiagFEV1Date").setValue(firstPostTransBOSDiagFEV1Date);
    }

    public Float getFirstPostTransBOSDiagFEV1() {
        return (Float) attributes.get("firstPostTransBOSDiagFEV1").getValue();
    }

    public void setFirstPostTransBOSDiagFEV1(Float firstPostTransBOSDiagFEV1) {
        attributes.get("firstPostTransBOSDiagFEV1").setValue(firstPostTransBOSDiagFEV1);
    }

    public Float getFirstPostTransBOSDiagFVC() {
        return (Float) attributes.get("firstPostTransBOSDiagFVC").getValue();
    }

    public void setFirstPostTransBOSDiagFVC(Float firstPostTransBOSDiagFVC) {
        attributes.get("firstPostTransBOSDiagFVC").setValue(firstPostTransBOSDiagFVC);
    }

    public Date getSecondPostTransBOSDiagFEV1Date() {
        return (Date) attributes.get("secondPostTransBOSDiagFEV1Date").getValue();
    }

    public void setSecondPostTransBOSDiagFEV1Date(Date secondPostTransBOSDiagFEV1Date) {
        attributes.get("secondPostTransBOSDiagFEV1Date").setValue(secondPostTransBOSDiagFEV1Date);
    }

    public Float getSecondPostTransBOSDiagFEV1() {
        return (Float) attributes.get("secondPostTransBOSDiagFEV1").getValue();
    }

    public void setSecondPostTransBOSDiagFEV1(Float secondPostTransBOSDiagFEV1) {
        attributes.get("secondPostTransBOSDiagFEV1").setValue(secondPostTransBOSDiagFEV1);
    }

    public Float getSecondPostTransBOSDiagFVC() {
        return (Float) attributes.get("secondPostTransBOSDiagFVC").getValue();
    }

    public void setSecondPostTransBOSDiagFVC(Float secondPostTransBOSDiagFVC) {
        attributes.get("secondPostTransBOSDiagFVC").setValue(secondPostTransBOSDiagFVC);
    }

    public Date getThirdPostTransBOSDiagFEV1Date() {
        return (Date) attributes.get("thirdPostTransBOSDiagFEV1Date").getValue();
    }

    public void setThirdPostTransBOSDiagFEV1Date(Date thirdPostTransBOSDiagFEV1Date) {
        attributes.get("thirdPostTransBOSDiagFEV1Date").setValue(thirdPostTransBOSDiagFEV1Date);
    }

    public Float getThirdPostTransBOSDiagFEV1() {
        return (Float) attributes.get("thirdPostTransBOSDiagFEV1").getValue();
    }

    public void setThirdPostTransBOSDiagFEV1(Float thirdPostTransBOSDiagFEV1) {
        attributes.get("thirdPostTransBOSDiagFEV1").setValue(thirdPostTransBOSDiagFEV1);
    }

    public Float getThirdPostTransBOSDiagFVC() {
        return (Float) attributes.get("thirdPostTransBOSDiagFVC").getValue();
    }

    public void setThirdPostTransBOSDiagFVC(Float thirdPostTransBOSDiagFVC) {
        attributes.get("thirdPostTransBOSDiagFVC").setValue(thirdPostTransBOSDiagFVC);
    }

    public Date getMostRecentExamDate() {
        return (Date) attributes.get("mostRecentExamDate").getValue();
    }

    public void setMostRecentExamDate(Date mostRecentExamDate) {
        attributes.get("mostRecentExamDate").setValue(mostRecentExamDate);
    }
}
