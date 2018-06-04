package edu.wustl.mir.ctt.calc;

import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.form.CrossoverEligibilityWorkSheet;
import edu.wustl.mir.ctt.form.EligibilityForm;
import edu.wustl.mir.ctt.model.DeclineStrata;
import edu.wustl.mir.ctt.model.ECPEventTypes;
import edu.wustl.mir.ctt.model.Event;
import edu.wustl.mir.ctt.model.Participant;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.StudyArmStatus;
import edu.wustl.mir.ctt.persistence.PersistenceException;
import edu.wustl.mir.ctt.persistence.PersistenceManager;
import edu.wustl.mir.ctt.persistence.ServiceRegistry;
import edu.wustl.mir.ctt.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author drm
 */
public class CrossoverEligibilityCalculator {
    private enum CrossoverStatus {
        INELIGIBLE_TOO_SOON_AFTER_ENROLLMENT,         // no action 0
        //INELIGIBLE_TOO_FEW_FEV1S,        // no action 1
        ELIGIBLE_SLOPE_TIMING_OK,        // To safety check 2
        INELIGIBLE_SLOPE_NOT_OK  // no action 3
        //ELIGIBLE_MANY_FEV1S_OVERRIDE,           // To clinical override 5
    }
    
    private static final Map<CrossoverStatus, String> rules;
    static {
        Map<CrossoverStatus, String> r = new EnumMap<>(CrossoverStatus.class);
        r.put(CrossoverStatus.INELIGIBLE_TOO_SOON_AFTER_ENROLLMENT, "Not enough time has elapsed on current therapy, per study protocol.");
        r.put(CrossoverStatus.ELIGIBLE_SLOPE_TIMING_OK, "Your patient has met the criteria for crossover.");
        r.put(CrossoverStatus.INELIGIBLE_SLOPE_NOT_OK, "The FEV-1 or its rate of decline are not sufficient to permit crossover.");
        //r.put(CrossoverStatus.ELIGIBLE_MANY_FEV1S_OVERRIDE, "4 or more FEV1 values in the last 4 weeks and the slope is OK but not significant.");
        rules = r;
    }
   
    private static final Map<CrossoverStatus, String> msgs;
    static {
        Map<CrossoverStatus, String> m = new EnumMap<>(CrossoverStatus.class);
        m.put(CrossoverStatus.INELIGIBLE_TOO_SOON_AFTER_ENROLLMENT, "Participant ineligible for crossover assessment. This participant may not be assessed for crossover at this time because not enough time has elapsed since enrollment.  This participant remains in their current arm. Please contact your CCC nurse coordinator if you have questions.");
        m.put(CrossoverStatus.ELIGIBLE_SLOPE_TIMING_OK, "The participant is eligible to cross over into the %s.");
        m.put(CrossoverStatus.INELIGIBLE_SLOPE_NOT_OK, "This patient remains in their current study arm because they still do not meet the Protocol’s criteria to receive ECP (see Protocol Section 3.6). Please continue to follow the Protocol’s treatment and evaluation procedures for patients in their current study arm. Please contact your CCC nurse coordinator if you have questions.");
       //m.put(CrossoverStatus.ELIGIBLE_MANY_FEV1S_OVERRIDE, "The participant does not meet all of the criteria for inclusion in the ECP treatment arm. Do you wish to override this and move the participant anyway?");
        msgs = m;
    }
    
    private StudyArmEligibilityCalculator eligCalc;
    private CrossoverStatus index;
    private Participant p;
    private boolean displayCalculatorResults = false;
    private List<PulmonaryEvaluation> evals;
    private Date screenDate;
    private Logger logger;
    private double slope;
    private double pValue;
    
    public CrossoverEligibilityCalculator( CrossoverEligibilityWorkSheet form) throws PersistenceException {
        this( form.getEvaluations(), form.getDate(), form.getParticipantID());
    }
    
    public CrossoverEligibilityCalculator( List<PulmonaryEvaluation> evals, Date screenDate, String participantID) throws PersistenceException {
        logger = Logger.getLogger(CrossoverEligibilityCalculator.class);
        this.evals = evals;
        this.screenDate = screenDate;
        PersistenceManager pm = ServiceRegistry.getPersistenceManager();
        p = pm.getParticipant( participantID);
        index = screen();
    }
    
    /*
     * Use this constructor in tests.  Set the participant without a call to DB
     * and then call 'screen()'.
    */
    public CrossoverEligibilityCalculator( List<PulmonaryEvaluation> evals, Date screenDate) {
        logger = Logger.getLogger(CrossoverEligibilityCalculator.class);
        this.evals = evals;
        this.screenDate = screenDate;
    }
    
    /*
     * handy for testing.
    */
    public void setParticipant( Participant p) {
        this.p = p;
    }
    
    public String getOutcomeMessage() throws PersistenceException {
        String msg;
        switch (index) {
            case INELIGIBLE_TOO_SOON_AFTER_ENROLLMENT:
                msg = msgs.get(CrossoverStatus.INELIGIBLE_TOO_SOON_AFTER_ENROLLMENT);
                break;
            case ELIGIBLE_SLOPE_TIMING_OK:
                String newArmName = "";
                
                if (p.getStudyArmStatus() == StudyArmStatus.CONTROL_ARM) {
                    newArmName = StudyArmStatus.CONTROL_PLUS_EPI.getName();
                } else if (p.getStudyArmStatus() == StudyArmStatus.EPI_ARM) {
                    newArmName = StudyArmStatus.EPI_PLUS_CONTROL.getName();
                }
                
                msg = String.format(msgs.get(CrossoverStatus.ELIGIBLE_SLOPE_TIMING_OK), newArmName);
                //msg = msgs.get(CrossoverStatus.ELIGIBLE_SLOPE_TIMING_OK);
                break;
            case INELIGIBLE_SLOPE_NOT_OK:
                msg = msgs.get(CrossoverStatus.INELIGIBLE_SLOPE_NOT_OK);
                break;
            //case ELIGIBLE_MANY_FEV1S_OVERRIDE:
            //    msg = msgs.get(CrossoverStatus.ELIGIBLE_MANY_FEV1S_OVERRIDE);
            //    break;
            default:
                msg = "Unknown outcome from CrossoverEligibilityCalculator: status = " + index;
                break;
        }
        return msg;
    }
    
    public boolean getDisplayCalculatorResults() {
        return displayCalculatorResults;
    }
    
    public String getOutcomeRule() {
        return rules.get(index);
    }

    public float getSlope() {
//        System.out.println("The slope is: " + eligCalc.getSlope() + "\n");
        //return (float) eligCalc.getSlope();
        return (float) slope;
    }
    
    public float getPValue() {
        //return (float) eligCalc.getPValue();
        return (float) pValue;
    }
    
    public float getMinFev() {
        return eligCalc.getMinFev();
    }
    
    /*
    public Date getTimerStartDate() {
        if( p.getHoldStartDate() == null) {
            return p.getEnrolledDate();
        }
        else {
            return p.getHoldStartDate();
        }
    }*/

    /*
    public Date getCanCrossOverDate() {
        Date d = getTimerStartDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add( Calendar.DAY_OF_MONTH, 60);
        return cal.getTime();
    }
    */
    
    public Date getDateOfLastEvaluation() {
        Date d = null;
        if( evals.size() > 0) {
            d = evals.get(0).getDate();
            for( PulmonaryEvaluation e: evals) {
                if( e.getDate().after(d)) {
                    d = e.getDate();
                }
            }
        }
        return d;
    }
    
    public boolean isCrossoverEligible() {
        boolean b = false;
        if(index == CrossoverStatus.ELIGIBLE_SLOPE_TIMING_OK) {
            b = true;
        }
        return b;
    }
    
    /**
     * careful!  isNotCrossoverEligilble does not equal ( ! isCrossoverEligble)
     * due to existence of override eligible state.
     * 
     * @return 
     */
    public boolean isNotCrossoverEligible() {
        boolean b = false;
        if(index == CrossoverStatus.INELIGIBLE_TOO_SOON_AFTER_ENROLLMENT ||
           index == CrossoverStatus.INELIGIBLE_SLOPE_NOT_OK) {
            b = true;
        }
        return b;
    }
    
    /*
    public boolean isOverrideEligible() {
        boolean b = false;
        if( index == CrossoverStatus.ELIGIBLE_MANY_FEV1S_OVERRIDE ) {
            b = true;
        }
        return b;
    }
    */
    
    /*
    public boolean isKeepParticipantInObservationalArmEligible() {
        boolean b = false;
        if( index == CrossoverStatus.INELIGIBLE_MANY_FEV1S_SLOPE_SIG_NOT_OK ){
            b = true;
        }
        return b;
    }
    */
    
    private boolean hasPostEnrollmentSevereDecline() {
        return getSlopeSinceTreatmentStart() <= -200.0;
    }
    
    public boolean treatmentHasFailedInWindow(Date windowStart, Date windowEnd) {
        List<Date> datesInWindow = new ArrayList<>();
        List<Float> fevsInWindow = new ArrayList<>();
        
        for (PulmonaryEvaluation ev : evals) {
            if (ev.getDate().after(windowStart) && ev.getDate().before(windowEnd)) {
                datesInWindow.add(ev.getDate());
                fevsInWindow.add(ev.getFev1() * 1000f);
            }
        }
        
        SlopeEstimator estimator = new SlopeEstimator( datesInWindow, fevsInWindow);
        double slopeInWindow = estimator.getSlope();
        
        return (slopeInWindow < (getSlopeAtEnrollment() / 2.0));
    }
    
    private boolean canCrossoverEarly() {
        boolean canCrossoverEarly = false;
        Date lastEvalDate = getDateOfLastEvaluation();
        Float lastEval = null;
        
        for (PulmonaryEvaluation ev : evals) {
            if (ev.getDate() == lastEvalDate) {
                lastEval = ev.getFev1() * 1000f;
            }
        }
        
        double slopeAtEnrollment = getSlopeAtEnrollment();
        
        /*
        1. Patients with FEV-1 < 1200 ml:  initial rate of FEV-1 decline exceeds 200 ml/month  
        2. Patients with FEV-1 < 1600 ml:  initial rate of FEV-1 decline exceeds 300 ml/month 
        3. Patients with FEV-1 < 2000 ml:  initial rate of FEV-1 decline exceeds 400 ml/month 
        */
        
        if (lastEval < 1200f && slopeAtEnrollment < 200f) {
            canCrossoverEarly = true;
        } else if (lastEval < 1600f && slopeAtEnrollment < 300f) {
            canCrossoverEarly = true;
        } else if (lastEval < 2000f && slopeAtEnrollment < 400f) {
            canCrossoverEarly = true;
        }
        
        return canCrossoverEarly;
    }
    
    private Date getTreatmentStartDate() {
        Date treatmentStart = null;
        
        try {
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
            List<Event> events = pm.getEvents(p);
            Event firstTreatment = null;
            
            if (p.getStudyArmStatus() == StudyArmStatus.EPI_ARM) {
                for (Event e : events) {
                    if ("ECP Treatment 1".equals(e.getName())) {
                        firstTreatment = e;
                        break;
                    }
                }
            } else {
                for (Event e : events) {
                    if (e.getType() == ECPEventTypes.BASELINE_THERAPY) {
                        firstTreatment = e;
                        break;
                    }
                }
            }
            
            if (firstTreatment == null) {
                throw new PersistenceException("Failed to retrieve first treatment event");
            } else {
                treatmentStart = firstTreatment.getActualDate();
            }
        } catch (PersistenceException pe) {
            logger.error("Failed to fetch event history", pe);
        }
        
        return treatmentStart;
    }
    
    private double daysElapsedSinceTreatmentStart(Date treatmentStart) {
        return DateUtil.intervalInDays(new Date(), treatmentStart);
    }
    
    private double getSlopeAtEnrollment() {
        try {
            PersistenceManager pm = ServiceRegistry.getPersistenceManager();
            List<Event> events = pm.getEvents(p);
            Event coeEvent = null;
            for (Event e : events) {
                if (e.getType() == ECPEventTypes.ELIGIBILITY) {
                    coeEvent = e;
                    break;
                }
            }

            if (coeEvent != null) {
                List<BasicForm> basicforms = pm.getForms(coeEvent);
                
                BasicForm coeForm = basicforms.get(0);
                EligibilityForm coe = pm.getEligibilityForm(coeForm);

                slope = coe.getSlope();
                
                logger.debug("Slope from COE " + slope);
            }
        } catch (PersistenceException pe) {
            logger.error("Failed to fetch event history", pe);
        }
        
        return slope;
    }
    
    private double getSlopeSinceTreatmentStart() {
        double slopeSinceTreatmentStart;
        
        List<Date> dates = new ArrayList<>();
        List<Float> fevs = new ArrayList<>();
        
        for( PulmonaryEvaluation eval: evals) {
            dates.add(eval.getDate());
            fevs.add(eval.getFev1() * 1000f);
            logger.debug("Date " + eval.getDate() + " FEV1 " + eval.getFev1());
        }
        
        SlopeEstimator estimator = new SlopeEstimator( dates, fevs);
        slopeSinceTreatmentStart = estimator.getSlope();
        
        slope = estimator.getSlope();
        pValue = estimator.getPValue();
        
        return slopeSinceTreatmentStart;
    }
    
    private boolean treatmentHasFailed() {
        double slopeAtEnrollment = getSlopeAtEnrollment();
        double slopeSinceTreatmentStart = getSlopeSinceTreatmentStart();
        
        logger.debug("Slope at enrollment: " + slopeAtEnrollment);
        logger.debug("Slope since enrollment: " + slopeSinceTreatmentStart);
        
        // If decline is has not reduced by at least 50%, then treatment has failed
        return (slopeSinceTreatmentStart < (slopeAtEnrollment / 2.0));
    }
    
    private CrossoverStatus screen()throws PersistenceException {
        CrossoverStatus status = null;
        
        Date firstTreatmentDate = getTreatmentStartDate();
        Double daysSinceTreatmentStart = daysElapsedSinceTreatmentStart(firstTreatmentDate);
        
        // 9 mo = 274 days
        // 6 mo = 183 days
        // 3 mo = 92 days
        
        Date windowStart;
        Date windowEnd;
        
        // Anyone can try to cross over after 9 mos, eligible if treatment failed
        if (daysSinceTreatmentStart >= 274) {
            windowStart = DateUtils.addDays(firstTreatmentDate, 273);
            windowEnd = new Date();
            
            if (treatmentHasFailedInWindow(windowStart, windowEnd)) {
                status = CrossoverStatus.ELIGIBLE_SLOPE_TIMING_OK;
            } else {
                status = CrossoverStatus.INELIGIBLE_SLOPE_NOT_OK;
            }
        } else if (daysSinceTreatmentStart >= 183 && p.getStudyArmStatus() == StudyArmStatus.EPI_ARM) {
            // Capture evals from 4 months (120 day) through 6 months (180 day) +/- week window
            windowStart = DateUtils.addDays(firstTreatmentDate, 113);
            windowEnd = DateUtils.addDays(firstTreatmentDate, 188);

            if (treatmentHasFailedInWindow(windowStart, windowEnd)) {
                status = CrossoverStatus.ELIGIBLE_SLOPE_TIMING_OK;
            } else {
                status = CrossoverStatus.INELIGIBLE_SLOPE_NOT_OK;
            }
        } else if (daysSinceTreatmentStart >= 92 && (p.getDeclineStrata() == DeclineStrata.HIGH_DECLINE_STRATA || ((evals.size() >= 3) && hasPostEnrollmentSevereDecline()))) {
            if(treatmentHasFailedInWindow(firstTreatmentDate, new Date()) && canCrossoverEarly()) {
                // not treatment failure
                status = CrossoverStatus.INELIGIBLE_SLOPE_NOT_OK;
            }
        } else {
            // Not enough time elapsed
            status = CrossoverStatus.INELIGIBLE_TOO_SOON_AFTER_ENROLLMENT;
        }
        
        return status;
    }
    
//    private int getNumberOfMeasurementsInInterval( List<PulmonaryEvaluation> evals, Date startDate, int periodInDays) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(startDate);
//        cal.add(Calendar.DAY_OF_MONTH, periodInDays);
//        Date stopDate = cal.getTime();
//        if( periodInDays >=0) {
//            return getNumberOfMeasurementsInInterval( evals, startDate, stopDate);
//        }
//        else {
//            return getNumberOfMeasurementsInInterval( evals, stopDate, startDate);
//        }
//        
//    }
    
    private int getNumberOfMeasurementsInInterval( List<PulmonaryEvaluation> evals, Date startDate, Date stopDate) {
        int count = 0;
        
        for( PulmonaryEvaluation eval: evals) {
            if( DateUtil.isDateInIntervalInclusive(eval.getDate(), startDate, stopDate)) {
                count++;
            }
        }
        return count;
    }
       
    /*
    public String getNextCrossoverDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 60);
        Date date = cal.getTime();
        SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy");
        return ft.format(date);
    }*/
    
}
