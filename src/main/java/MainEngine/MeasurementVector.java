package MainEngine;

import java.util.List;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;

public class MeasurementVector implements IMeasurementVector {
    
    private String countryName;
    private String indicatorString;
    private List<Pair<Integer, Integer>> measurements;
    private String descriptiveStats;
    private String regressionResult;
    

    
    public MeasurementVector(String countryName, String indicatorName, int year, double value) {
    }

    public String getCountryName() {
        return countryName;
    }
    
    public String getIndicatorString() {
        return indicatorString;
    }
    
    public List<Pair<Integer, Integer>> getMeasurements() {
        return measurements;
    }
    
    public String getDescriptiveStatsAsString() {
        return descriptiveStats;
    }
    
    public String getRegressionResultAsString() {
        return regressionResult;
    }
    
    public void setDescriptiveStats(String descriptiveStats) {
        this.descriptiveStats = descriptiveStats;
    }
    
    public void setRegressionResult(String regressionResult) {
        this.regressionResult = regressionResult;
    }
}
