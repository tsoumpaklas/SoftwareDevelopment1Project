package MainEngine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import engine.IMainController;


public class DataProcessing implements IMainController {

   public static List<IMeasurementVector> list = new ArrayList<>();
    //keeping the years in a list
   private List<Integer> chronoList = new ArrayList<>();
    //matching years and values with hashmap
   private Pair<Integer, Integer> pair;


    public DataProcessing(){}
    //loading the file and returning a list of measurement vectors
    public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException{
        BufferedReader inputStream = null;
        try{
            inputStream = new BufferedReader(new FileReader(fileName));
        }
        catch(FileNotFoundException e){
            throw new FileNotFoundException("unable to open file " + fileName);
        }
        catch(IOException e){
            throw new IOException("unable to read file " + fileName);
        }
        while(true){
            String line = inputStream.readLine();
            if(line == null){
                break;
            }
            String[] tokens = line.split(delimiter);
            String countryName = tokens[1];
            String ISO2 = tokens[2];
            String ISO3 = tokens[3];
            String indicatorName = tokens[4];
            /*Here we read the first line which contains only the years
              and then we read the second line which contains the measurements */
            if(chronoList.isEmpty()){
                for(int i = 5; i < tokens.length; i++){
                    chronoList.add(Integer.parseInt(tokens[i]));
                }
            }
           else{
                for(int i =5; i < tokens.length; i++){
                     if(tokens[i].equals("")){
                                tokens[i] = "0";
                     }
                      pair = new Pair<Integer, Integer>(chronoList.get(i-5), Integer.parseInt(tokens[i]));
                }

           }
           IMeasurementVector vector = new MeasurementVector(countryName, ISO2, ISO3, indicatorName, pair);
            list.add(vector);
        }

        inputStream.close();
        return list;
        
        }
          
        
    
    public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName, String indicatorString){
        
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
    public static void main(String[] args) throws FileNotFoundException, IOException{
        DataProcessing test = new DataProcessing();
        test.load("src/main/resources/InputData/ClimateRelatedDisasters.tsv","\t");
        test.showList(list);

    }

}
   

