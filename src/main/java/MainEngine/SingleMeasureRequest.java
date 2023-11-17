package MainEngine;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;

public class SingleMeasureRequest implements ISingleMeasureRequest {
    //import all abstract methods from ISingleMeasureRequest
    private String requestName;
    private String requestFilter;
    private boolean answeredFlag;
    private IMeasurementVector answer; 
    private String descriptiveStatsString;
    private String regressionResultString;

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
        return answeredFlag;
    }
    public IMeasurementVector getAnswer() {
        return answer;
    }
    public String getDescriptiveStatsString() {
        return descriptiveStatsString;
    }
    public String getRegressionResultString() {
        return regressionResultString;
    }
    public void setAnsweredFlag(boolean answeredFlag) {
        this.answeredFlag = answeredFlag;
    }

}
