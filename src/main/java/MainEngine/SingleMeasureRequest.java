package MainEngine;


import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;

public class SingleMeasureRequest implements ISingleMeasureRequest {
    private String requestName;
    private IMeasurementVector vector;


    public SingleMeasureRequest(String requestName, IMeasurementVector vector ){
        this.requestName = requestName;
        this.vector = vector;
    }
    public String getRequestName() {
        return requestName;
    }
    public String getRequestFilter() {
        return "Country"+"~"+vector.getCountryName()+" Indicator:"+ vector.getIndicatorString();
    }
    public boolean isAnsweredFlag() {
         if(vector!= null) return true;
         else return false;
    }
    public IMeasurementVector getAnswer() {
       return vector;
    }
    public String getDescriptiveStatsString() {
        return vector.getDescriptiveStatsAsString();
    }
    public String getRegressionResultString() {
        return vector.getRegressionResultAsString();
    }

}