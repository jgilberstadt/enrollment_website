package edu.wustl.mir.ctt.calc;

import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.form.EnrollmentForm;
import edu.wustl.mir.ctt.form.StudyArmEligibilityForm;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.ECPEvents;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.PIDGeneratorECP;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.model.ParticipantStatus;
import edu.wustl.mir.ctt.model.Site;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import java.util.Date;
import java.util.List;

/**
 *
 * @author drm
 */
public class EnrollmentService {
    static EligibilityForm eligibilityForm = new EligibilityForm();
    static Participant participant = new Participant();

    /**
     * Take the eligibility form from the calculator and instantiate a 
     * participant in the registry.
     * @param arm
     */
    public String enroll(
        int arm,
        Site site,
        EligibilityForm form) throws PersistenceException {
        
            return enrollParticipant( site, form);
    }
    
    public static EligibilityForm getEligibilityForm(){
        return eligibilityForm;
    }
    
    public static Participant getParticipant(){
        return participant;
    }
    
    
    /**
     * Take the eligibility form from the calculator and instantiate a 
     * participant in the registry.
     * 
     * @param site
     * @param form
     * @return 
     * @throws edu.wustl.mir.ctt.persistence.PersistenceException
     */
    public static String enrollParticipant( Site site, EligibilityForm form) throws PersistenceException {
        
            Participant p = new Participant();
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        
            p.setSiteId( site.getId());
            p.setEnrolledDate(new Date());  

            p.setStatus(ParticipantStatus.ENROLLED);
            p.setParticipantID( PIDGeneratorECP.getNewParticipantID(site));

            Event e = ECPEvents.getInstance(ECPEventTypes.ELIGIBILITY);

            List<BasicForm> forms = ECPEvents.getForms(ECPEventTypes.ELIGIBILITY);

            EligibilityForm eform = (EligibilityForm) forms.get(0);
            
            eform.setAge( form.getAge());
            eform.setMedicare( form.getMedicare());
            eform.setLungTransplant( form.getLungTransplant());
            eform.setFiveFEV1sPostTrans( form.getFiveFEV1sPostTrans());
            eform.setAnotherTrial( form.getAnotherTrial());
            eform.setInterfereCondition( form.getInterfereCondition());
            eform.setKnownAllergy( form.getKnownAllergy());
            eform.setAcuteCondition( form.getAcuteCondition());
            eform.setOtherCondition( form.getOtherCondition());
            eform.setNoInformedConsent( form.getNoInformedConsent());
            eform.setAcuteDeclineTreatment( form.getAcuteDeclineTreatment());
            
            eform.setDate1( form.getDate1());
            eform.setDate2( form.getDate2());
            eform.setDate3( form.getDate3());
            eform.setDate4( form.getDate4());
            eform.setDate5( form.getDate5());
            eform.setDate6( form.getDate6());
            eform.setDate7( form.getDate7());
            eform.setDate8( form.getDate8());
            eform.setDate9( form.getDate9());
            eform.setDate10( form.getDate10());
            eform.setDate11( form.getDate11());
            eform.setDate12( form.getDate12());
            eform.setDate13( form.getDate13());
            eform.setDate14( form.getDate14());
            eform.setDate15( form.getDate15());
            eform.setDate16( form.getDate16());
            eform.setDate17( form.getDate17());
            eform.setDate18( form.getDate18());
            eform.setDate19( form.getDate19());
            eform.setDate20( form.getDate20());
            eform.setDate21( form.getDate21());
            eform.setDate22( form.getDate22());
            eform.setDate23( form.getDate23());
            eform.setDate24( form.getDate24());
            eform.setDate25( form.getDate25());
            eform.setDate26( form.getDate26());
            
            eform.setFev11( form.getFev11());
            eform.setFev12( form.getFev12());
            eform.setFev13( form.getFev13());
            eform.setFev14( form.getFev14());
            eform.setFev15( form.getFev15());
            eform.setFev16( form.getFev16());
            eform.setFev17( form.getFev17());
            eform.setFev18( form.getFev18());
            eform.setFev19( form.getFev19());
            eform.setFev110( form.getFev110());
            eform.setFev111( form.getFev111());
            eform.setFev112( form.getFev112());
            eform.setFev113( form.getFev113());
            eform.setFev114( form.getFev114());
            eform.setFev115( form.getFev115());
            eform.setFev116( form.getFev116());
            eform.setFev117( form.getFev117());
            eform.setFev118( form.getFev118());
            eform.setFev119( form.getFev119());
            eform.setFev120( form.getFev120());
            eform.setFev121( form.getFev121());
            eform.setFev122( form.getFev122());
            eform.setFev123( form.getFev123());
            eform.setFev124( form.getFev124());
            eform.setFev125( form.getFev125());
            eform.setFev126( form.getFev126());
            
            eform.setFvc1( form.getFvc1());
            eform.setFvc2( form.getFvc2());
            eform.setFvc3( form.getFvc3());
            eform.setFvc4( form.getFvc4());
            eform.setFvc5( form.getFvc5());
            eform.setFvc6( form.getFvc6());
            eform.setFvc7( form.getFvc7());
            eform.setFvc8( form.getFvc8());
            eform.setFvc9( form.getFvc9());
            eform.setFvc10( form.getFvc10());
            eform.setFvc11( form.getFvc11());
            eform.setFvc12( form.getFvc12());
            eform.setFvc13( form.getFvc13());
            eform.setFvc14( form.getFvc14());
            eform.setFvc15( form.getFvc15());
            eform.setFvc16( form.getFvc16());
            eform.setFvc17( form.getFvc17());
            eform.setFvc18( form.getFvc18());
            eform.setFvc19( form.getFvc19());
            eform.setFvc20( form.getFvc20());
            eform.setFvc21( form.getFvc21());
            eform.setFvc22( form.getFvc22());
            eform.setFvc23( form.getFvc23());
            eform.setFvc24( form.getFvc24());
            eform.setFvc25( form.getFvc25());
            eform.setFvc26( form.getFvc26());
            
            pm.addParticipant( p, e, forms);
            
            // The participant or patient id (pid) is a string of format xxxxxxx and is not the participant table id which is an autogenerated integer returned by the enrollment webservice.
            return p.getParticipantID();
    }
    
        /**
     * Take the enrollment from enrollment determination and instantiate a 
     * participant in the registry.
     * 
     * @param site
     * @param form
     * @return 
     * @throws edu.wustl.mir.ctt.persistence.PersistenceException
     */
    public static String enrollParticipantIntoECPStudy( Site site, EnrollmentForm form) throws PersistenceException {
        
        System.out.println("The site name is: " + site.getName() + "\n");
        System.out.println("The site getId is: " + site.getId() + "\n");
        System.out.println("The site getSiteId is: " + site.getSiteID()+ "\n");
        
            Participant p = new Participant();
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        
            p.setSiteId( site.getId());
            p.setEnrolledDate(form.getEnrollmentDate());  

            p.setStatus(ParticipantStatus.ENROLLED);
            p.setParticipantID( PIDGeneratorECP.getNewParticipantID(site));
            
            participant = p;
 
            pm.addParticipant( p);
            
            // The participant or patient id (pid) is a string of format xxxxxxx and is not the participant table id which is an autogenerated integer returned by the enrollment webservice.
            return p.getParticipantID();
    }

    
            /**
     * Take the enrollment from enrollment determination and instantiate a 
     * participant in the registry.
     * 
     * @param site
     * @param form
     * @param studyArmEligibilityForm
     * @param p
     * @return 
     * @throws edu.wustl.mir.ctt.persistence.PersistenceException
     */
    public static String enrollParticipantIntoStudyArm( Site site, Participant p, EnrollmentForm form, StudyArmEligibilityForm studyArmEligibilityForm) throws PersistenceException {
        
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();

            Event e = ECPEvents.getInstance(ECPEventTypes.ELIGIBILITY);

            List<BasicForm> forms = ECPEvents.getForms(ECPEventTypes.ELIGIBILITY);

            EligibilityForm eform = (EligibilityForm) forms.get(0);
            
            //if(form.getCrfVersion().equals("5.0")){
                eform.addLeukopeniaAttribute();
                
                if (form.getLeukopenia() != null && !form.getLeukopenia().isEmpty()) {
                    eform.setLeukopenia(form.getLeukopenia());
                }
            //}
            
            eform.setCrfVersion( form.getCrfVersion());
            eform.versionControl();
            eform.setIrbVersion( form.getIrbVersion());
            eform.setIrbSubmittedDate( form.getIrbSubmittedDate());
            eform.setEnrollmentDate( form.getEnrollmentDate());
            eform.setAge( form.getAge());
            eform.setMedicare( form.getMedicare());
            eform.setLungTransplant( form.getLungTransplant());
            eform.setFiveFEV1sPostTrans( form.getFiveFEV1sPostTrans());
            eform.setAnotherTrial( form.getAnotherTrial());
            eform.setInterfereCondition( form.getInterfereCondition());
            eform.setKnownAllergy( form.getKnownAllergy());
            eform.setAcuteCondition( form.getAcuteCondition());
            eform.setOtherCondition( form.getOtherCondition());
            eform.setAphakia( form.getAphakia());
            eform.setPregnancy( form.getPregnancy());
            eform.setNoInformedConsent( form.getNoInformedConsent());
            eform.setDateInformedConsentSigned( form.getDateInformedConsentSigned());
            eform.setDateInformedConsentVersion( form.getDateInformedConsentVersion());
 
            eform.setDate1( studyArmEligibilityForm.getDate1());
            eform.setDate2( studyArmEligibilityForm.getDate2());
            eform.setDate3( studyArmEligibilityForm.getDate3());
            eform.setDate4( studyArmEligibilityForm.getDate4());
            eform.setDate5( studyArmEligibilityForm.getDate5());
            eform.setDate6( studyArmEligibilityForm.getDate6());
            eform.setDate7( studyArmEligibilityForm.getDate7());
            eform.setDate8( studyArmEligibilityForm.getDate8());
            eform.setDate9( studyArmEligibilityForm.getDate9());
            eform.setDate10( studyArmEligibilityForm.getDate10());
            eform.setDate11( studyArmEligibilityForm.getDate11());
            eform.setDate12( studyArmEligibilityForm.getDate12());
            eform.setDate13( studyArmEligibilityForm.getDate13());
            eform.setDate14( studyArmEligibilityForm.getDate14());
            eform.setDate15( studyArmEligibilityForm.getDate15());
            eform.setDate16( studyArmEligibilityForm.getDate16());
            eform.setDate17( studyArmEligibilityForm.getDate17());
            eform.setDate18( studyArmEligibilityForm.getDate18());
            eform.setDate19( studyArmEligibilityForm.getDate19());
            eform.setDate20( studyArmEligibilityForm.getDate20());
            eform.setDate21( studyArmEligibilityForm.getDate21());
            eform.setDate22( studyArmEligibilityForm.getDate22());
            eform.setDate23( studyArmEligibilityForm.getDate23());
            eform.setDate24( studyArmEligibilityForm.getDate24());
            eform.setDate25( studyArmEligibilityForm.getDate25());
            eform.setDate26( studyArmEligibilityForm.getDate26());
            
            eform.setFev11( studyArmEligibilityForm.getFev11());
            eform.setFev12( studyArmEligibilityForm.getFev12());
            eform.setFev13( studyArmEligibilityForm.getFev13());
            eform.setFev14( studyArmEligibilityForm.getFev14());
            eform.setFev15( studyArmEligibilityForm.getFev15());
            eform.setFev16( studyArmEligibilityForm.getFev16());
            eform.setFev17( studyArmEligibilityForm.getFev17());
            eform.setFev18( studyArmEligibilityForm.getFev18());
            eform.setFev19( studyArmEligibilityForm.getFev19());
            eform.setFev110( studyArmEligibilityForm.getFev110());
            eform.setFev111( studyArmEligibilityForm.getFev111());
            eform.setFev112( studyArmEligibilityForm.getFev112());
            eform.setFev113( studyArmEligibilityForm.getFev113());
            eform.setFev114( studyArmEligibilityForm.getFev114());
            eform.setFev115( studyArmEligibilityForm.getFev115());
            eform.setFev116( studyArmEligibilityForm.getFev116());
            eform.setFev117( studyArmEligibilityForm.getFev117());
            eform.setFev118( studyArmEligibilityForm.getFev118());
            eform.setFev119( studyArmEligibilityForm.getFev119());
            eform.setFev120( studyArmEligibilityForm.getFev120());
            eform.setFev121( studyArmEligibilityForm.getFev121());
            eform.setFev122( studyArmEligibilityForm.getFev122());
            eform.setFev123( studyArmEligibilityForm.getFev123());
            eform.setFev124( studyArmEligibilityForm.getFev124());
            eform.setFev125( studyArmEligibilityForm.getFev125());
            eform.setFev126( studyArmEligibilityForm.getFev126());
            
            eform.setFvc1( studyArmEligibilityForm.getFvc1());
            eform.setFvc2( studyArmEligibilityForm.getFvc2());
            eform.setFvc3( studyArmEligibilityForm.getFvc3());
            eform.setFvc4( studyArmEligibilityForm.getFvc4());
            eform.setFvc5( studyArmEligibilityForm.getFvc5());
            eform.setFvc6( studyArmEligibilityForm.getFvc6());
            eform.setFvc7( studyArmEligibilityForm.getFvc7());
            eform.setFvc8( studyArmEligibilityForm.getFvc8());
            eform.setFvc9( studyArmEligibilityForm.getFvc9());
            eform.setFvc10( studyArmEligibilityForm.getFvc10());
            eform.setFvc11( studyArmEligibilityForm.getFvc11());
            eform.setFvc12( studyArmEligibilityForm.getFvc12());
            eform.setFvc13( studyArmEligibilityForm.getFvc13());
            eform.setFvc14( studyArmEligibilityForm.getFvc14());
            eform.setFvc15( studyArmEligibilityForm.getFvc15());
            eform.setFvc16( studyArmEligibilityForm.getFvc16());
            eform.setFvc17( studyArmEligibilityForm.getFvc17());
            eform.setFvc18( studyArmEligibilityForm.getFvc18());
            eform.setFvc19( studyArmEligibilityForm.getFvc19());
            eform.setFvc20( studyArmEligibilityForm.getFvc20());
            eform.setFvc21( studyArmEligibilityForm.getFvc21());
            eform.setFvc22( studyArmEligibilityForm.getFvc22());
            eform.setFvc23( studyArmEligibilityForm.getFvc23());
            eform.setFvc24( studyArmEligibilityForm.getFvc24());
            eform.setFvc25( studyArmEligibilityForm.getFvc25());
            eform.setFvc26( studyArmEligibilityForm.getFvc26());
            
            eform.setBaselineFEV1(studyArmEligibilityForm.getBaselineFEV1());
            eform.setFirstComponentFEV1Date(studyArmEligibilityForm.getFirstComponentFEV1Date());
            eform.setFirstComponentFEV1(studyArmEligibilityForm.getFirstComponentFEV1());
            eform.setFirstComponentFVC(studyArmEligibilityForm.getFirstComponentFVC());
            eform.setSecondComponentFEV1Date(studyArmEligibilityForm.getSecondComponentFEV1Date());
            eform.setSecondComponentFEV1(studyArmEligibilityForm.getSecondComponentFEV1());
            eform.setSecondComponentFVC(studyArmEligibilityForm.getSecondComponentFVC());
            
            eform.setPostTransBOSDiagDate(studyArmEligibilityForm.getPostTransBOSDiagDate());
            eform.setFirstPostTransBOSDiagFEV1Date(studyArmEligibilityForm.getFirstPostTransBOSDiagFEV1Date());
            eform.setFirstPostTransBOSDiagFEV1(studyArmEligibilityForm.getFirstPostTransBOSDiagFEV1());
            eform.setFirstPostTransBOSDiagFVC(studyArmEligibilityForm.getFirstPostTransBOSDiagFVC());
            eform.setSecondPostTransBOSDiagFEV1Date(studyArmEligibilityForm.getSecondPostTransBOSDiagFEV1Date());
            eform.setSecondPostTransBOSDiagFEV1(studyArmEligibilityForm.getSecondPostTransBOSDiagFEV1());
            eform.setSecondPostTransBOSDiagFVC(studyArmEligibilityForm.getSecondPostTransBOSDiagFVC());
            eform.setThirdPostTransBOSDiagFEV1Date(studyArmEligibilityForm.getThirdPostTransBOSDiagFEV1Date());
            eform.setThirdPostTransBOSDiagFEV1(studyArmEligibilityForm.getThirdPostTransBOSDiagFEV1());
            eform.setThirdPostTransBOSDiagFVC(studyArmEligibilityForm.getThirdPostTransBOSDiagFVC());

            eform.setLungTransplantationDate(studyArmEligibilityForm.getLungTransplantationDate());
            
            eform.setMostRecentExamDate(studyArmEligibilityForm.getMostRecentExamDate());
            
            eform.setCompleteBloodCountDate(studyArmEligibilityForm.getCompleteBloodCountDate());
            eform.setWbcs(studyArmEligibilityForm.getWbcs());
            eform.setRbcs(studyArmEligibilityForm.getRbcs());
            eform.setHemoglobin(studyArmEligibilityForm.getHemoglobin());
            eform.setHematocrit(studyArmEligibilityForm.getHematocrit());
            eform.setPlatelets(studyArmEligibilityForm.getPlatelets());
            
            eform.setLabBasedNewBOSDiagnosis(form.getLabBasedNewBOSDiagnosis());
            eform.setPreDiagnosisMonitoring(form.getPreDiagnosisMonitoring());
            eform.setDocumentedClinicalAssessment(form.getDocumentedClinicalAssessment());
            eform.setLeukopenia3000(form.getLeukopenia3000());
            eform.setDeclineNotBOS(form.getDeclineNotBOS());
            eform.setAcuteDeclineTreatment(form.getAcuteDeclineTreatment());
            
            eform.setSlope(studyArmEligibilityForm.getSlope());
            eform.setSignificance(studyArmEligibilityForm.getSignificance());

            pm.addParticipantEnrollment( p, e, forms);
            
            // The getParticipantID is a string with a format of xxxxxx and is not the participant table id which is an autogenerated integer returned by the enrollment webservice.
            return p.getParticipantID();
    }

}
