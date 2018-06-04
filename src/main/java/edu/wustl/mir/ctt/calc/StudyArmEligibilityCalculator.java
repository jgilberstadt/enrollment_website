package edu.wustl.mir.ctt.calc;

import edu.wustl.mir.ctt.form.StudyArmEligibilityForm;
import edu.wustl.mir.ctt.model.DeclineStrata;
import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.model.SpirometryStrata;
import edu.wustl.mir.ctt.util.DateUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

/**
 * Screen the participant for eligibility and study-arm assignment.
 * 
 * Uses all measurements provided. Only screens for minimum data requirements.
 *
 * @author drm
 */
public class StudyArmEligibilityCalculator implements Serializable {
    
    private enum EligibilityStatus {
        INELIGIBLE_TOO_FEW_FEV1S_BEFORE_ENROLLMENT, // fewer than five sufficiently spaced FEV1s before enrollment
        INELIGIBLE_LATEST_FEV1_TOO_OLD,             // latest fev1 more than 21 days old
        ELIGIBLE_STRATA_HIGH_DECLINE,               // slope < -30 and pvalue < 0.05
        ELIGIBLE_STRATA_LOW_DECLINE,                // slope < -30 and pvalue < 0.05
        INELIGIBLE_INSIGNIFICANT,                   // slope < -30 and pvalue >= 0.05
        INELIGIBLE_FEV1_DECLINE_NOT_ENOUGH,            // slope >= -30 
        INELIGIBLE_WBC_COUNT_TOO_LOW,             // wbc count below threshold for leukopenia (3000 cells/mm3)
        INELIGIBLE_LATEST_EXAM_TOO_OLD,           // latest physical exam is older than two weeks
        INELIGIBLE_LATEST_CBC_TOO_OLD,            // latest complete blood count is older than two weeks
        INELIGIBLE_MEASUREMENTS_NOT_REGULAR,      // fev1s in the past 4 months have interval greater than 8 weeks
        INELIGIBLE_TOO_FEW_FEV1S_BEFORE_DIAGNOSIS,  // fewer than three sufficiently spaced FEV1s before diagnosis
        INELIGIBLE_DIAGNOSIS_CRITERIA_NOT_MET     // diagnosis FEV1s are not 20% lower than ISHLT baseline
    }
    
    // Use status enumeration as key for message map for readability
    private static final Map<EligibilityStatus, String> rules;
    static { // Initialize static map, see https://stackoverflow.com/a/507658
        Map<EligibilityStatus, String> r = new EnumMap<>(EligibilityStatus.class);
        r.put(EligibilityStatus.INELIGIBLE_TOO_FEW_FEV1S_BEFORE_ENROLLMENT, "In the previous 6 months, there are not at least 5 measurements that are all spaced at least 7 days apart.");
        r.put(EligibilityStatus.INELIGIBLE_LATEST_FEV1_TOO_OLD, "The most recent measurement must be within three weeks prior to enrollment.");
        r.put(EligibilityStatus.ELIGIBLE_STRATA_HIGH_DECLINE, "meets the criteria (slope < -30 AND p-value < 0.05).");
        r.put(EligibilityStatus.ELIGIBLE_STRATA_LOW_DECLINE, "meets the criteria (slope < -30 AND p-value < 0.05).");
        r.put(EligibilityStatus.INELIGIBLE_INSIGNIFICANT, "The p-value >= 0.05 and the slope < -30.");
        r.put(EligibilityStatus.INELIGIBLE_FEV1_DECLINE_NOT_ENOUGH, "The slope >= -30.");
        r.put(EligibilityStatus.INELIGIBLE_WBC_COUNT_TOO_LOW, "The white blood cell count is less than 3000 cells/mm3.");
        r.put(EligibilityStatus.INELIGIBLE_LATEST_EXAM_TOO_OLD, "The documented physical assessment must be within two weeks prior to enrollment.");
        r.put(EligibilityStatus.INELIGIBLE_LATEST_CBC_TOO_OLD, "The documented complete blood count must be within two weeks prior to enrollment.");
		r.put(EligibilityStatus.INELIGIBLE_DIAGNOSIS_CRITERIA_NOT_MET, "The laboratory based FEV1 values used to diagnose BOS were not at least 20% lower than baseline FEV1.");
        r.put(EligibilityStatus.INELIGIBLE_MEASUREMENTS_NOT_REGULAR, "The measurements obtained in the last four months were not regular (interval between measurements exceeded eight weeks).");
        r.put(EligibilityStatus.INELIGIBLE_TOO_FEW_FEV1S_BEFORE_DIAGNOSIS, "In the 6 months before diagnosis, there are not at least 3 measurements that are all spaced at least 7 days apart.");
        
        rules = Collections.unmodifiableMap(r);
    }
   
    private static final Map<EligibilityStatus, String> msgs;
    static { // Initialize static map, see https://stackoverflow.com/a/507658
        Map<EligibilityStatus, String> m = new EnumMap<>(EligibilityStatus.class);
        m.put(EligibilityStatus.INELIGIBLE_TOO_FEW_FEV1S_BEFORE_ENROLLMENT, "Patient not eligible");
        m.put(EligibilityStatus.INELIGIBLE_LATEST_FEV1_TOO_OLD, "Patient not eligible");
        m.put(EligibilityStatus.ELIGIBLE_STRATA_HIGH_DECLINE, "Patient is eligible. Complete enrollment eligibility forms on line to confirm eligibility");
        m.put(EligibilityStatus.ELIGIBLE_STRATA_HIGH_DECLINE, "Patient is eligible. Complete enrollment eligibility forms on line to confirm eligibility");
        m.put(EligibilityStatus.INELIGIBLE_INSIGNIFICANT, "Patient not eligible");
        m.put(EligibilityStatus.INELIGIBLE_FEV1_DECLINE_NOT_ENOUGH, "Patient not eligible");
        m.put(EligibilityStatus.INELIGIBLE_WBC_COUNT_TOO_LOW, "Patient not eligible");
        m.put(EligibilityStatus.INELIGIBLE_LATEST_EXAM_TOO_OLD, "Patient not eligible");
        m.put(EligibilityStatus.INELIGIBLE_MEASUREMENTS_NOT_REGULAR, "Patient not eligible");
        m.put(EligibilityStatus.INELIGIBLE_LATEST_CBC_TOO_OLD, "Patient not eligible");
        m.put(EligibilityStatus.INELIGIBLE_DIAGNOSIS_CRITERIA_NOT_MET, "Patient not eligible");
        msgs = Collections.unmodifiableMap(m);
    }

    private SlopeEstimator estimator;
    private EligibilityStatus status;
    private float minFev;
    private float lastFev;
    private float slope;
    private float pvalue;
    private List<PulmonaryEvaluation> evals;
    private List<PulmonaryEvaluation> evalsWithQualifyingDates;
    private Date screenDate;
    private String fev1OldestPossibleDate;
    private Date lastExamDate;
    private Date lastCbcDate;
    private Float wbcs;
    private Float baseline;
    private List<Float> diagFEVs;
    private Date diagDate;
    private DeclineStrata declineStrata;
    private SpirometryStrata spirometryStrata = SpirometryStrata.LAB_SPIROMETRY_STRATA; // All subjects are lab spirometry until protocol revision
    
    private Logger logger;
    
    public StudyArmEligibilityCalculator( StudyArmEligibilityForm form) {
        this.logger = Logger.getLogger(StudyArmEligibilityCalculator.class);
        
        this.evals = form.getPulmEvaluations();
        this.screenDate = form.getDate();
        this.lastExamDate = form.getMostRecentExamDate();
        this.lastCbcDate = form.getCompleteBloodCountDate();
        this.wbcs = form.getWbcs();
        
        this.baseline = form.getBaselineFEV1();
        
        this.diagFEVs = new ArrayList<>();
        this.diagFEVs.add(form.getFirstPostTransBOSDiagFEV1());
        this.diagFEVs.add(form.getSecondPostTransBOSDiagFEV1());
        if(form.getThirdPostTransBOSDiagFEV1() != null) {
            this.diagFEVs.add(form.getThirdPostTransBOSDiagFEV1());
        }
        
        this.diagDate = form.getPostTransBOSDiagDate();
        
        status = initialScreen();
    }
    
    public StudyArmEligibilityCalculator( List<PulmonaryEvaluation> evals, Date screenDates) {
        this.logger = Logger.getLogger(StudyArmEligibilityCalculator.class);
        
        this.evals = evals;
        this.screenDate = screenDates;
       
        this.lastExamDate = null;
        this.lastCbcDate = null;
        this.wbcs = null;
        this.baseline = null;
        this.diagFEVs = null;
        
//        System.out.println("The screenDate is the form.getDate: " + screenDate);
        // do not do inclusion/exclusion criteria checks
        status = screen();
    }

    public List<PulmonaryEvaluation> getEvaluations() {
        return evals;
    }

    public List<PulmonaryEvaluation> getEvalsWithQualifyingDates() {
        return evalsWithQualifyingDates;
    }

    public Date getScreenDate() {
        return screenDate;
    }

    public void setScreenDate(Date screenDate) {
        this.screenDate = screenDate;
    }
    
    public String getOutcomeMessage() {
        return msgs.get(status);
    }
    
    public String getOutcomeRule() {
        return rules.get(status);
    }
    
    public float getPValue() {
        return pvalue;
    }
    
    public float getSlope() {
        return slope;
    }
    
    public double predict( Date d) {
        // If you get an error message whne trying to run the estimator.predict(d) command, it most likely is due to the esitmator not being
        // instantiated in the initialScreen() method below (see the line of code 'estimator = new SlopeEstimator( dates, fevs)' in the initialScreen() method).
        return estimator.predict(d);
    }
    
    public float getMinFev() {
        return minFev;
    }
    
    public float getLastFev() {
        return lastFev;
    }
    
    public String getFev1OldestPossibleDate() {
        return fev1OldestPossibleDate;
    }
    
    public SpirometryStrata getSpirometryStrata() {
        return spirometryStrata;
    }
    
    public DeclineStrata getDeclineStrata() {
        return declineStrata;
        }
        
    public boolean isDeclineScreeningPassed() {
        return (status == EligibilityStatus.ELIGIBLE_STRATA_HIGH_DECLINE ||
                status == EligibilityStatus.ELIGIBLE_STRATA_LOW_DECLINE);
    }
    
    public boolean isSlopeOKandStatsSig() {
        return (status == EligibilityStatus.ELIGIBLE_STRATA_HIGH_DECLINE ||
                status == EligibilityStatus.ELIGIBLE_STRATA_LOW_DECLINE);
    }
    
    public boolean isSlopeOKandStatsNotSig() {
        return (status == EligibilityStatus.INELIGIBLE_INSIGNIFICANT);
    }
    
    public boolean isDataStale() {
        return status == EligibilityStatus.INELIGIBLE_LATEST_FEV1_TOO_OLD;
    }
    
    public boolean isDataTooFew() {
        return status == EligibilityStatus.INELIGIBLE_TOO_FEW_FEV1S_BEFORE_ENROLLMENT;
    }
    
    public int numberOfMinimallySpacedMeasurementsInPeriod( List<PulmonaryEvaluation> evals, Date startDate, Date stopDate, float spacingInDays) {
        evalsWithQualifyingDates = filterForMinimalSpacing( evals, spacingInDays);
        
//        int j;
//        for(int i=0; i < qualifyingDates.size(); i++){
//            j = i + 1;
//            System.out.println("The qualifying dates are: " + j + "   " + qualifyingDates.get(i).getDate().toString());
//        }
        return countInInterval( evalsWithQualifyingDates, startDate, stopDate);
    }
    
    public List<PulmonaryEvaluation> filterForMinimalSpacing( List<PulmonaryEvaluation> evals, float spacingInDays) {
        evalsWithQualifyingDates = new ArrayList<>();
        
        if( evals.size() > 1) {
            evalsWithQualifyingDates.add( evals.get(0));
            Date d1, d2;
            for( int i = 0; i < evals.size(); i++) {
                d1 = evals.get(i).getDate();
                for( int j = i+1; j < evals.size(); j++) {
                    d2 = evals.get(j).getDate();
                    // The minus 0.1 needed to be taken away from the spacingInDays because of the change
                    // in Central Standard Time (CST) to Daylight Savings Time (DST).  The day of the time  
                    // change, the number of hours in a day are 23 not 24 so a day is less than 1, but we 
                    // expect the days in the interval to all have 24 hours.  The minus 0.1 takes care of 
                    // the change in time in the spring.
                    if( DateUtil.intervalInDays( d1, d2) >= spacingInDays - 0.1) {
                        evalsWithQualifyingDates.add( evals.get(j));
                        i = j - 1;
                        break;
                    }
                }
            }
        }
        
        return evalsWithQualifyingDates;
    }
    
    public int countInInterval( List<PulmonaryEvaluation> evals, Date startDate, Date stopDate) {
        int cnt = 0;
        double days;
        days = DateUtil.intervalInDays(startDate, stopDate);
        for( PulmonaryEvaluation e: evals) {
            if( DateUtil.isDateInIntervalInclusive(e.getDate(), startDate, stopDate)) {
                cnt++;
            }
        }
        System.out.println("The count in the 6 month interval is: " + cnt);
        return cnt;
    }
    
    public boolean wereMeasurementsObtainedRegularly(List<PulmonaryEvaluation> evals, int startDate, double maxInterval) {
        boolean measurementsSufficientlySpaced = true;
        List<PulmonaryEvaluation> filteredEvals = new ArrayList<>();
        
        // Sort the array of PulmonaryEvaluation objects by date in ascending order.
        Collections.sort(evals, new Comparator<PulmonaryEvaluation>() {
            @Override
            public int compare(PulmonaryEvaluation pe1, PulmonaryEvaluation pe2) {
                if (pe1.getDate() == null || pe2.getDate() == null)
                    return 0;
                return pe1.getDate().compareTo(pe2.getDate());
            }
        });

        // Remove any dates older than six months from the list.
        // Changed days from double to int so if the DateUtil returns 182.?? days
        // the int will drop the decimal. 
        int days;
        for (PulmonaryEvaluation p : evals) {
            days = (int) DateUtil.intervalInDays(p.getDate(), screenDate);
            if(days < startDate){
                filteredEvals.add(p);
            }
        }
        
        for (int i = 0; (i + 1) < filteredEvals.size(); i++) {
            double d = DateUtil.intervalInDays(filteredEvals.get(i).getDate(), filteredEvals.get(i + 1).getDate());
            
            // are measurements too far apart (greater than required spacing?)
            if (d > (maxInterval - 0.1)) {
                measurementsSufficientlySpaced = false;
            }
        }
        
        return measurementsSufficientlySpaced;
    }
    
    private boolean getAreMeasurementsInTimeBounds() {
        for( PulmonaryEvaluation e: evals) {
            Date startDate = DateUtils.addDays(screenDate, -182);
            if( ! DateUtil.isDateInIntervalInclusive(e.getDate(), startDate, screenDate)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isMeasurementRecentEnough( Date d, Date now) {
        double days = DateUtil.intervalInDays(d, now);
        return days < 22;
//        Date startDate = DateUtil.addDays(now, -7);
//        return DateUtil.isDateInIntervalInclusive( d, startDate, now);
    }
      
    private Date getDateOfLastEvaluation() {
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
    
    public List<PulmonaryEvaluation> getEvalsWithinLastSixMonths(List<PulmonaryEvaluation> pulmEvals, Date screenDate) throws ParseException{
//        List<PulmonaryEvaluation> pulmEvals = new ArrayList<PulmonaryEvaluation>();
        List<PulmonaryEvaluation> pulmEvals2 = new ArrayList<>();
        
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
        PulmonaryEvaluation pe;
        // Changed days from double to int so if the DateUtil returns 182.?? days
        // the int will drop the decimal. 
        int days;
        Iterator iterator = pulmEvals.iterator();
        while(iterator.hasNext()){
            pe = (PulmonaryEvaluation) iterator.next();
            days = (int) DateUtil.intervalInDays(pe.getDate(), screenDate);
            if(days < 183){
                pulmEvals2.add(pe);
            }
        }

        return pulmEvals2;

    }
    
    private EligibilityStatus screen() {
        List<Date> dates = new ArrayList<>();
        List<Float> fevs = new ArrayList<>();
        EligibilityStatus i;
        
        // Format the start of the six month period so the oldest possible FEV1 date can be determined.
        // The fev1OldestPossibleDate is used enrollStudyArmElig.xhtml form in the Omnifaces o:validateMultiple
        // validators to let the individual filling out the form know the oldest possible FEV1 date that can be
        // entered into the form.
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String fev1OldestDate = sdf.format(DateUtil.addDays(screenDate, -183));
        // If a oldest date is 182 days ago, use the date below to show the person entering the dates on the Elig Form.
        // We do not want to show them a date of 183 days old as above.
        fev1OldestPossibleDate = sdf.format(DateUtil.addDays(screenDate, -182));

        // I had to change the fev1OldestDate and startDate to be 183 days because the countInInterval method along
        // with most all of the other methods use hours with the dates.  The hours different in a day or the CST vs CDT
        // can also mess up the calulation of 182 days.  If 183 days is used then it should let through 182 days.
        Date startDate = DateUtil.addDays(screenDate, -183);
        System.out.println("The startDate is the screenDate - 183 days: " + startDate);
        System.out.println("The stopDate is the screenDate: " + screenDate);
        
        try {
            // Get the FEV1 Dates and values within the last six months from the initialScreen date.
            evalsWithQualifyingDates = this.getEvalsWithinLastSixMonths(evals, screenDate);
        } catch (ParseException ex) {
            logger.error(ex);
        }
        
        // SPECIAL NOTE: The number fourteen (14) used in the call to numberOfMinimallySpacedMeasurementsInPeriod refers to the
        // number of days in the spacing between measurement periods.
        // NOTE: The change from Central Standard Time to Central Daylight Time in the spring causes one day to be only 23 hours long, so
        // a full period does not occur between two dates if the spring time date change is between the dates.
        // If you follow the method numberOfMinimallySpacedMeasurementsInPeriod you will find we subtracted 0.1 days from the period to account
        // for the change in time.
        int countMeasurementsSufficientlySpaced = numberOfMinimallySpacedMeasurementsInPeriod( evalsWithQualifyingDates, startDate, screenDate, 14);
        System.out.println("The countMeasurementsSufficientlySpaced is: " + countMeasurementsSufficientlySpaced + "\n");
        if( countMeasurementsSufficientlySpaced < 5) {
            i = EligibilityStatus.INELIGIBLE_TOO_FEW_FEV1S_BEFORE_ENROLLMENT;
            return i;
        }
    
        // Was the last FEV1 (Pulmonary Evaluation) performed within the last three weeks?
        if(  ! isMeasurementRecentEnough( getDateOfLastEvaluation(), screenDate) ) {
            i = EligibilityStatus.INELIGIBLE_LATEST_FEV1_TOO_OLD;
            return i;
        }

        for( PulmonaryEvaluation eval: evalsWithQualifyingDates) {
            dates.add(eval.getDate());
            fevs.add(eval.getFev1() * 1000f);
        }
                
        int nValues = fevs.size();
        lastFev = fevs.get(nValues - 1);
        int indexOfMinValue = fevs.indexOf( Collections.min(fevs));
        minFev = fevs.get(indexOfMinValue);
        
        estimator = new SlopeEstimator( dates, fevs);
        
        slope = (float) estimator.getSlope();
        pvalue = (float) estimator.getPValue();

        if( pvalue < 0.05) {
            if( slope < -30) {
                if (slope >= -200) {
                    i = EligibilityStatus.ELIGIBLE_STRATA_HIGH_DECLINE; // -200 >= slope < -30 and pvalue < 0.05
                    declineStrata = DeclineStrata.LOW_DECLINE_STRATA;
                } else {
                    i = EligibilityStatus.ELIGIBLE_STRATA_LOW_DECLINE;  // slope < -200 and pvalue < 0.05
                    declineStrata = DeclineStrata.HIGH_DECLINE_STRATA;
                }
            }
            else { // slope >= -30
                i = EligibilityStatus.INELIGIBLE_FEV1_DECLINE_NOT_ENOUGH;    // minFev >= 1200 and slope >= -30
            }
                }
                else { // pvalue >= 0.05
            i = EligibilityStatus.INELIGIBLE_INSIGNIFICANT;    // minFev >= 1200 and slope < -30 and pvalue >= 0.05
        }
            
        return i;
    }
    
    private EligibilityStatus initialScreen() {
        List<Date> dates = new ArrayList<>();
        List<Float> fevs = new ArrayList<>();
        EligibilityStatus i;
        
        if (wbcs != null && wbcs < 3.0) {
            i = EligibilityStatus.INELIGIBLE_WBC_COUNT_TOO_LOW;
            return i;
        }
        
        if (lastExamDate != null && DateUtil.intervalInDays(lastExamDate, screenDate) > 14) {
            i = EligibilityStatus.INELIGIBLE_LATEST_EXAM_TOO_OLD;
            return i;
        }
        
        if (lastCbcDate != null && DateUtil.intervalInDays(lastCbcDate, screenDate) > 14) {
            i = EligibilityStatus.INELIGIBLE_LATEST_CBC_TOO_OLD;
            return i;
        }
        
        // Check that BOS diagnosis values are 20% less than baseline
        if (baseline != null && diagFEVs != null) {
            Float threshold = baseline * 0.8f;
            
            for (Float diagFEV : diagFEVs) {
                if (diagFEV > threshold) {
                    i = EligibilityStatus.INELIGIBLE_DIAGNOSIS_CRITERIA_NOT_MET;
                    return i;
                }
            }
        }
        
        // Format the start of the six month period so the oldest possible FEV1 date can be determined.
        // The fev1OldestPossibleDate is used enrollStudyArmElig.xhtml form in the Omnifaces o:validateMultiple
        // validators to let the individual filling out the form know the oldest possible FEV1 date that can be
        // entered into the form.
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String fev1OldestDate = sdf.format(DateUtil.addDays(screenDate, -183));
        // If a oldest date is 182 days ago, use the date below to show the person entering the dates on the Elig Form.
        // We do not want to show them a date of 183 days old as above.
        fev1OldestPossibleDate = sdf.format(DateUtil.addDays(screenDate, -182));

        // I had to change the fev1OldestDate and startDate to be 183 days because the countInInterval method along
        // with most all of the other methods use hours with the dates.  The hours different in a day or the CST vs CDT
        // can also mess up the calulation of 182 days.  If 183 days is used then it should let through 182 days.
        Date startDate = DateUtil.addDays(screenDate, -183);
        System.out.println("The startDate is the screenDate - 183 days: " + startDate);
        System.out.println("The stopDate is the screenDate: " + screenDate);
        
        try {
            // Get the FEV1 Dates and values within the last six months from the initialScreen date.
            evalsWithQualifyingDates = this.getEvalsWithinLastSixMonths(evals, screenDate);
        } catch (ParseException ex) {
            logger.error(ex);
        }
        
        // SPECIAL NOTE: The number fourteen (14) used in the call to numberOfMinimallySpacedMeasurementsInPeriod refers to the
        // number of days in the spacing between measurement periods.
        // NOTE: The change from Central Standard Time to Central Daylight Time in the spring causes one day to be only 23 hours long, so
        // a full period does not occur between two dates if the spring time date change is between the dates.
        // If you follow the method numberOfMinimallySpacedMeasurementsInPeriod you will find we subtracted 0.1 days from the period to account
        // for the change in time.
        int countMeasurementsSufficientlySpaced = numberOfMinimallySpacedMeasurementsInPeriod( evalsWithQualifyingDates, startDate, screenDate, 14);
        System.out.println("The countMeasurementsSufficientlySpaced is: " + countMeasurementsSufficientlySpaced + "\n");
        if( countMeasurementsSufficientlySpaced < 5) {
            status = EligibilityStatus.INELIGIBLE_TOO_FEW_FEV1S_BEFORE_ENROLLMENT;
            return status;
        }
        
        // Check to make sure that there are measurements 
        if (! wereMeasurementsObtainedRegularly(evalsWithQualifyingDates, 122, 57)) {
            status = EligibilityStatus.INELIGIBLE_MEASUREMENTS_NOT_REGULAR;
            return status;
        }
            
        int countMeasurementsSpacedBeforeDiagnosis = numberOfMinimallySpacedMeasurementsInPeriod( evalsWithQualifyingDates, startDate, diagDate, 14);
        System.out.println("The countMeasurementsSpacedBeforeDiagnosis is: " + countMeasurementsSpacedBeforeDiagnosis + "\n");
        if( countMeasurementsSpacedBeforeDiagnosis < 3) {
            status = EligibilityStatus.INELIGIBLE_TOO_FEW_FEV1S_BEFORE_DIAGNOSIS;
            return status;
        }
        
        // Was the last FEV1 (Pulmonary Evaluation) performed within the last 7 days?
        if(  ! isMeasurementRecentEnough( getDateOfLastEvaluation(), screenDate) ) {
            status = EligibilityStatus.INELIGIBLE_LATEST_FEV1_TOO_OLD;
            return status;
        }

        for( PulmonaryEvaluation eval: evalsWithQualifyingDates) {
            dates.add(eval.getDate());
            fevs.add(eval.getFev1() * 1000f);
        }
                
        int nValues = fevs.size();
        lastFev = fevs.get(nValues - 1);
        int indexOfMinValue = fevs.indexOf( Collections.min(fevs));
        minFev = fevs.get(indexOfMinValue);
        
        estimator = new SlopeEstimator( dates, fevs);
        
        slope = (float) estimator.getSlope();
        pvalue = (float) estimator.getPValue();
        
        if( pvalue < 0.05) {
            if( slope < -30) {
                if (slope >= -200) {
                    i = EligibilityStatus.ELIGIBLE_STRATA_HIGH_DECLINE; // -200 >= slope < -30 and pvalue < 0.05
                    declineStrata = DeclineStrata.LOW_DECLINE_STRATA;
                } else {
                    i = EligibilityStatus.ELIGIBLE_STRATA_LOW_DECLINE;  // slope < -200 and pvalue < 0.05
                    declineStrata = DeclineStrata.HIGH_DECLINE_STRATA;
                }
            }
            else { // slope >= -30
                i = EligibilityStatus.INELIGIBLE_FEV1_DECLINE_NOT_ENOUGH;    // minFev >= 1200 and slope >= -30
            }
                    }
                    else { // pvalue >= 0.05
            i = EligibilityStatus.INELIGIBLE_INSIGNIFICANT;    // minFev >= 1200 and slope < -30 and pvalue >= 0.05
            }

            
        return i;
    }
    
//    public static void main( String[] args) {
//        List<PulmonaryEvaluation> evals = new ArrayList<PulmonaryEvaluation>();
//        
//        Calendar cal = Calendar.getInstance();
//        PulmonaryEvaluation eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 7);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 8);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 10);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 5);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 5);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 5);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 5);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        cal.add(Calendar.DAY_OF_YEAR, 8);
//        eval = new PulmonaryEvaluation(cal.getTime(), 0.0f, 0.0f);
//        evals.add(eval);
//        
//        StudyArmEligibilityCalculator calc = new StudyArmEligibilityCalculator( evals);
//        
//        for( PulmonaryEvaluation e: evals) {
//            System.out.println(e.getDate());
//        }
//        System.out.println( calc.measurementIntervalCount(evals, 6.5f));
//    }
}
