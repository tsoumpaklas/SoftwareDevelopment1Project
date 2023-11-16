package MainEngine;

import java.util.List;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;

public class MeasurementVector implements IMeasurementVector {
    
    private String countryName;
    private String indicatorName;
    private String ISO2;
    private String ISO3;
    private int year;
    private double value;
    private List<Pair<Integer, Integer>> measurements;
    private String descriptiveStats;
    private String regressionResult;

    
    public MeasurementVector(String countryName, String ISO2, String ISO3, String indicatorName, Pair<Integer, Integer> pair) {
        this.countryName = countryName;
        this.ISO2 = ISO3;
        this. ISO3 = ISO3;
        this.indicatorName = indicatorName;
        measurements.add(pair);

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
