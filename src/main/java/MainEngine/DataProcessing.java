package MainEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import engine.IMainController;


public class DataProcessing implements IMainController {
    
    List<IMeasurementVector> list = new ArrayList<>();

    //loading the file and returning a list of measurement vectors
    public List<IMeasurementVector> load(String fileName, String delimiter){
       //generate for me somthing that implements IMeasurementVector
        List<String> lines = new ArrayList<>();
    for (String line : lines) {
        String[] tokens = line.split(delimiter);
        String countryName = tokens[0];
        String indicatorName = tokens[1];
        int year = Integer.parseInt(tokens[2]);
        double value = Double.parseDouble(tokens[3]);
        MeasurementVector vector = new MeasurementVector(countryName, indicatorName, year, value);
        list.add(vector);
    }
        return list;

        
    }
    public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName, String indicatorString){
       return null;
    }
    public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName){
        return null;
    }
    public Set<String> getFindRequestsNames(){
        return null;
    }
    public ISingleMeasureRequest getFindRequest(String requestName){
        return null;
    }
    public ISingleMeasureRequest getRegression(String requestName){
        return null;
    }
    public ISingleMeasureRequest getDescriptiveStats(String requestName){
        return null;
    }
    public int reportToFile(String outputFilePath, String requestName, String reportType){
        return 0;
    }
    @Override
    public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
            String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSingleCountryIndicatorYearRange'");
    }
    @Override
    public Set<String> getAllRequestNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRequestNames'");
    }
    @Override
    public ISingleMeasureRequest getRequestByName(String requestName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRequestByName'");
    }
    //show the inside of list
    public void showList(List<IMeasurementVector> list){
        for (IMeasurementVector vector : list) {
            System.out.println(vector.getCountryName() + " " + vector.getIndicatorString() + " " + vector.getMeasurements());
        }
    }

    public void main(String[] args) {
        DataProcessing dataProcessing = new DataProcessing();
        dataProcessing.load( "data.tsv", "," );
        dataProcessing.showList(list);;

    }
}
