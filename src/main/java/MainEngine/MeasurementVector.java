package MainEngine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;

public class MeasurementVector implements IMeasurementVector {
    
    private String countryName;
    private String indicatorName;
    private List<Pair<Integer, Integer>> measurements = new ArrayList<>();

    DescriptiveStatistics stats = new DescriptiveStatistics();
    SimpleRegression regression = new SimpleRegression();
    

    
    public MeasurementVector(String countryName,  String indicatorName, List<Pair<Integer, Integer>> pair) {
        this.countryName = countryName;
        this.indicatorName = indicatorName;
        this.measurements = pair;

    }

    public String getCountryName() {
        return countryName;
    }
    
     public String getIndicatorString() {
        return indicatorName;
    }
    
    public List<Pair<Integer, Integer>> getMeasurements() {
        return measurements;
    }
    
    public String getDescriptiveStatsAsString() {
        stats.clear();
        for(Pair<Integer, Integer> w : measurements){
             stats.addValue(w.getValue());  
        }
        
        long count = stats.getN();
		double minD = stats.getMin();
		double gMean = stats.getGeometricMean();
		double mean = stats.getMean();
		double medianD = stats.getPercentile(50);
		double maxD = stats.getMax();
		double kurtosis = stats.getKurtosis();
		double stdev = stats.getStandardDeviation();
		double sumD = stats.getSum();
        return "Descriptive Stats " + "[Count=" + count + "\nmin=" + (int)minD +"\ngMean="+gMean + "\nmean="+mean+"\nmedian="+ (int)medianD + "\nmax=" + (int)maxD + "\nkurtosis=" + kurtosis + "\nstdev=" + stdev + "\nsum=" + (int)sumD+"]";
    }
    
    public String getRegressionResultAsString() {
        for(Pair<Integer, Integer> w : measurements){
             regression.addData(w.getKey(),w.getValue());  
        }
        double intercept = regression.getIntercept();
		double slope = regression.getSlope();
		double slopeError = regression.getSlopeStdErr();
        return "\nRegressionResult [Intercept: " +"= "+intercept + "\nslope: " + "= " + slope + "\nslopeError: " +"= " +slopeError+ "\nTrend " + "= " + getLabel(slope) + "]";
    }   
    public String getLabel(double slope) {
        if (Double.isNaN(slope))
        return "Tendency Undefined";
        else if(slope > 0.1)
        return "Increased Tendency";
        else if(slope < -0.1)
        return "Decreased Tendency";
        return "Tendency stable";
        } 
}