package MainEngine;


import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;

public class SingleMeasureRequest implements ISingleMeasureRequest {
    //import all abstract methods from ISingleMeasureRequest
    private String requestName;
    private String requestFilter;
    private boolean answeredFlag;
    private IMeasurementVector vector;
    private IMeasurementVector answer; 
    private String descriptiveStatsString;
    private String regressionResultString;


    public SingleMeasureRequest(String requestName, IMeasurementVector vector ){
        this.requestName = requestName;
        this.vector = vector;
    }
    public SingleMeasureRequest(String requestName, String requestFilter) {
        this.requestName = requestName;
        this.requestFilter = requestFilter;
    }
    public String getRequestName() {
        return requestName;
    }
    public String getRequestFilter() {
        return requestFilter;
    }
    public boolean isAnsweredFlag() {
         if(vector!= null) return true;
         else return false;
    }
    public IMeasurementVector getAnswer() {
        //here i want to return the pair of year and values for the specific country and indicator
       return vector;
    }
    public String getDescriptiveStatsString() {
        return descriptiveStatsString;
    }
    public String getRegressionResultString() {
        return regressionResultString;
    }

}
