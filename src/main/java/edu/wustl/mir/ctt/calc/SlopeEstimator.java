package edu.wustl.mir.ctt.calc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.SimpleRegression;


/**
 * Calculate the slope and p-value for the linear regression fit to the data.
 * 
 * The date-order in which the data is added is not important.
 * The x values are dates. The values are converted internally to months (30 days)
 * so the slope output has units of 'per month'.
 *
 * @author drm
 */
public class SlopeEstimator {
    private List<Date> dates = new ArrayList<Date>();
    private List<Double> times = new ArrayList<Double>();
    private List<Double> yValues = new ArrayList<Double>();
    private final SimpleRegression regression = new SimpleRegression();
    
    private static final double MILLISEC_IN_MONTH = 30 * 24 * 60 * 60 * 1000d;
    
    public SlopeEstimator() {
        dates = new ArrayList<Date>();
        times = new ArrayList<Double>();
        yValues = new ArrayList<Double>();
    }
    
    public SlopeEstimator( List<Date> dates, List<Float> values) {
        this();
        for( int i = 0; i < dates.size(); i++) {
            addData( dates.get(i), values.get(i));
        }
    }
    
    public void addData( Date d, double y) {
        dates.add(d);
        double time = getTime(d);
        times.add( time);
        yValues.add(y);
        regression.addData(time, y);
    }
    
    private double getTime( Date d) {
        double time;
        if( times.isEmpty()) {
            time = 0.0d;
        }
        else {
            time = ( d.getTime() - dates.get(0).getTime()) / MILLISEC_IN_MONTH ;
        }
        return time;
    }
    
    public double getSlope() {
        return regression.getSlope();
    }
    
    public double predict( Date d) {
        Date dd = d;
        return regression.predict( getTime(d));
    }
    
    public double getR() {
        return regression.getR();
    }
    
    public double getPValue() {
        double R = regression.getR();
        long n = regression.getN();
        // If n = 2, you get a degrees of freedom error for the TDistribution tdist command below.
        if(n < 3) {
            return R;
        }
//        System.out.println("Slope: " + regression.getSlope());
//        System.out.println( "R: " + R);
//        System.out.println( "R^2: " + R * R);
        
//        double x = - R * Math.sqrt( (n-2) / (1 - R * R));
        double x = Math.abs( R * Math.sqrt( (n-2) / (1 - R * R)) );
        TDistribution tdist = new TDistribution(n-2);
        double p = 1.0d - tdist.cumulativeProbability(x);
        
        return p;
    }
    
    /**
     * The number of data points.
     * 
     * @return 
     */
    public long getN() {
        return regression.getN();
    }
    
    private List<Double> getTimes() {
        return times;
    }
    
    public List<Date> getDates() {
        return dates;
    }
    
    public List<Double> getYValues() {
        return yValues;
    }
    
//    public static void main(String[] args) {
//        try {
//            SlopeEstimator calc = new SlopeEstimator();
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//            calc.addData( df.parse("2014-03-22"), 3400d);
//            calc.addData( df.parse("2014-03-11"), 3130d);
//            calc.addData( df.parse("2014-04-20"), 2500d);
//            calc.addData( df.parse("2014-04-30"), 2400d);
//            
////            calc.addData( df.parse("2014-03-01"), 3130d);
////            calc.addData( df.parse("2014-03-15"), 3400d);
////            calc.addData( df.parse("2014-04-01"), 2500d);
////            calc.addData( df.parse("2014-04-15"), 2400d);
//            
//            for( Double t: calc.getTimes()) {
//                System.out.println(t);
//            }
//            System.out.println( "Slope: " + calc.getSlope());
//            System.out.println( "P-Value: " + calc.getPValue());
//            
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    public static void main(String[] args) {
        try {
            SlopeEstimator calc = new SlopeEstimator();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            calc.addData( df.parse("2006-08-12"), 2350d);
            calc.addData( df.parse("2006-08-31"), 2100d);
            calc.addData( df.parse("2006-09-21"), 2350d);
            calc.addData( df.parse("2006-10-07"), 2160d);
                        
//            for( Double t: calc.getTimes()) {
//                System.out.println(t);
//            }
            System.out.println( "N: " + calc.getN());
            System.out.println( "Slope: " + calc.getSlope());
            System.out.println( "R: " + calc.getR());
            System.out.println( "P-Value: " + calc.getPValue());
            
            calc.addData( df.parse("2006-10-24"), 2150d);
            System.out.println( "N: " + calc.getN());
            System.out.println( "Slope: " + calc.getSlope());
            System.out.println( "R: " + calc.getR());
            System.out.println( "P-Value: " + calc.getPValue());

            calc.addData( df.parse("2006-11-04"), 2400d);
            System.out.println( "N: " + calc.getN());
            System.out.println( "Slope: " + calc.getSlope());
            System.out.println( "R: " + calc.getR());
            System.out.println( "P-Value: " + calc.getPValue());

            calc.addData( df.parse("2006-11-22"), 2100d);
            System.out.println( "N: " + calc.getN());
            System.out.println( "Slope: " + calc.getSlope());
            System.out.println( "R: " + calc.getR());
            System.out.println( "P-Value: " + calc.getPValue());

            calc.addData( df.parse("2006-12-07"), 2200d);
            System.out.println( "N: " + calc.getN());
            System.out.println( "Slope: " + calc.getSlope());
            System.out.println( "R: " + calc.getR());
            System.out.println( "P-Value: " + calc.getPValue());

            calc.addData( df.parse("2006-12-21"), 1800d);
            System.out.println( "N: " + calc.getN());
            System.out.println( "Slope: " + calc.getSlope());
            System.out.println( "R: " + calc.getR());
            System.out.println( "P-Value: " + calc.getPValue());

            calc.addData( df.parse("2007-01-07"), 2300d);
            System.out.println( "N: " + calc.getN());
            System.out.println( "Slope: " + calc.getSlope());
            System.out.println( "R: " + calc.getR());
            System.out.println( "P-Value: " + calc.getPValue());

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//        try {
//            SlopeEstimator calc = new SlopeEstimator();
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//            calc.addData( df.parse("2006-04-12"), 2500d);
//            calc.addData( df.parse("2006-05-07"), 2200d);
//            calc.addData( df.parse("2006-05-27"), 1900d);
//            calc.addData( df.parse("2006-06-12"), 2140d);
//                        
//            System.out.println( "N: " + calc.getN());
//            System.out.println( "Slope: " + calc.getSlope());
//            System.out.println( "R: " + calc.getR());
//            System.out.println( "P-Value: " + calc.getPValue());
//            
//            calc.addData( df.parse("2006-06-30"), 2100d);
//            System.out.println( "N: " + calc.getN());
//            System.out.println( "Slope: " + calc.getSlope());
//            System.out.println( "R: " + calc.getR());
//            System.out.println( "P-Value: " + calc.getPValue());
//
//            calc.addData( df.parse("2006-07-08"), 2300d);
//            System.out.println( "N: " + calc.getN());
//            System.out.println( "Slope: " + calc.getSlope());
//            System.out.println( "R: " + calc.getR());
//            System.out.println( "P-Value: " + calc.getPValue());
//
//            calc.addData( df.parse("2006-07-18"), 1960d);
//            System.out.println( "N: " + calc.getN());
//            System.out.println( "Slope: " + calc.getSlope());
//            System.out.println( "R: " + calc.getR());
//            System.out.println( "P-Value: " + calc.getPValue());
//
//            calc.addData( df.parse("2006-08-01"), 2000d);
//            System.out.println( "N: " + calc.getN());
//            System.out.println( "Slope: " + calc.getSlope());
//            System.out.println( "R: " + calc.getR());
//            System.out.println( "P-Value: " + calc.getPValue());
//
//            calc.addData( df.parse("2006-08-14"), 2000d);
//            System.out.println( "N: " + calc.getN());
//            System.out.println( "Slope: " + calc.getSlope());
//            System.out.println( "R: " + calc.getR());
//            System.out.println( "P-Value: " + calc.getPValue());
//
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
//    }
    
}
