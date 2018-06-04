package edu.wustl.mir.ctt.util;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author drm
 */
public class DateUtil {
    
    public static final long DAY_IN_MILLISEC = 24 * 60 * 60 * 1000;
    
    /**
     * The number of days separating the two dates.
     * 
     * @param d1
     * @param d2
     * @return 
     */
    public static double intervalInDays( Date d1, Date d2) {
        
        long duration = Math.abs(d1.getTime() - d2.getTime());
        // If the casing of the long to double is removed from the right side of the equation,
        // then the days is rounded down.  Putting in the casing of the long division to (double)
        // results in a decimal days with the possibility of many significant digits.
        double days = (double) duration / DAY_IN_MILLISEC;
        
        return days;
    }
    
    public static boolean isDateInIntervalInclusive( Date d, Date startDate, Date stopDate) {

        return (d.compareTo(startDate) >= 0) && (d.compareTo(stopDate) <= 0);
    }
    
//    public static Date addDays( Date d, int days) {
//        return DateUtils.addDays(d, -182);
//    }
       
    public static Date addDays( Date d, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
    
    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date();
        
        int y = 2013 - 1900;
        
        d2.setYear(y);
        
        System.out.println( d1 + " " + d2 + " " + DateUtil.intervalInDays(d1, d2));
    }
}
