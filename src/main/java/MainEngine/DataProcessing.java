package MainEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
   List<SingleMeasureRequest> requests = new ArrayList<>();
   
   


    public DataProcessing(){}
    //loading the file and returning a list of measurement vectors
    public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException{
        BufferedReader inputStream = null;
        inputStream = new BufferedReader(new FileReader(fileName));
        while(true){
            String line = inputStream.readLine();
            if(line == null){
                break;
            }
            String[] tokens = line.split(delimiter);
            String countryName = tokens[1];
            String indicatorName = tokens[4];
            /*Here we read the first line which contains only the years
              and then we read the second line which contains the measurements */
            if(chronoList.isEmpty()){
                for(int i = 5; i < tokens.length; i++){
                    chronoList.add(Integer.parseInt(tokens[i]));
                }
            }
            else{
                List<Pair<Integer, Integer>> measurements = new ArrayList<>();
                for(int i =5; i < tokens.length; i++){
                    if(tokens[i].equals("")){
                        tokens[i] = "0";
                    }
                    pair = new Pair<Integer, Integer>(chronoList.get(i-5), Integer.parseInt(tokens[i]));
                    measurements.add(pair);
                }
                IMeasurementVector vector = new MeasurementVector(countryName ,indicatorName, measurements);
                 list.add(vector);
            }
           
           
        }

        inputStream.close();
        return list;
        
        }
          
        
    
    public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName, String indicatorString)throws IllegalArgumentException{
        for(IMeasurementVector vector : list){
            if(vector.getCountryName().equals(countryName) && vector.getIndicatorString().equals(indicatorString)){
                 SingleMeasureRequest request = new SingleMeasureRequest(requestName, vector);
                 requests.add(request);
                 return request;
            }
        }
      return null;  
    }
    public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,String indicatorString, int year1, int year2) throws IllegalArgumentException{
        for(IMeasurementVector vector : list){
            if(vector.getCountryName().equals(countryName) && vector.getIndicatorString().equals(indicatorString)){
                 List<Pair<Integer, Integer>> allYears = new ArrayList<>();
                 allYears = vector.getMeasurements();
                 List<Pair<Integer, Integer>> requestedYears = new ArrayList<>(); 
                 int downLimit = year1 - 1980;
                 int upLimit = year2 - 1980;
                 if(upLimit > allYears.size()){
                    upLimit = allYears.size();
                 }
                 for(int i= downLimit; i <= upLimit; i++){
                    requestedYears.add(allYears.get(i));
                 }
                 IMeasurementVector filteredVector = new MeasurementVector(countryName ,indicatorString, requestedYears);
                 SingleMeasureRequest request = new SingleMeasureRequest(requestName, filteredVector);
                 requests.add(request);
                 return request;
            }
        }
        return null;
    }
    public Set<String> getAllRequestNames(){
        Set<String> nameOfRequests = new HashSet<>();
        for(SingleMeasureRequest w : requests){
          nameOfRequests.add(w.getRequestName());
        }
        return nameOfRequests;
    }

    public ISingleMeasureRequest getRequestByName(String requestName){
        for(SingleMeasureRequest w : requests){
          if(w.getRequestName() == requestName){
               return w;
            }
        }
        return null;
        
    }


    public ISingleMeasureRequest getRegression(String requestName){
        for(SingleMeasureRequest w : requests){
            if(w.getRequestName() == requestName){
                if(w.isAnsweredFlag()){
                    w.getRegressionResultString();
                    return w;
                }
                else{
                    return null;
                }
            }
        }
        return null;  
    }
    public ISingleMeasureRequest getDescriptiveStats(String requestName){
        for(SingleMeasureRequest w : requests){
                if(w.getRequestName() == requestName){
                    if(w.isAnsweredFlag()){
                        w.getDescriptiveStatsString();
                        return w;
                    }
                    else{
                        return null;
                    }
                    
                }
        }
        return null;  
    }
    public int reportToFile(String outputFilePath, String requestName, String reportType) throws IOException {
        if (getAllRequestNames().contains(requestName)) {
            ISingleMeasureRequest requested = getRequestByName(requestName);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath, false))) {
                if ("text".equals(reportType)) {
                    return formatAndOutputInputStringListText(requested.getAnswer().getMeasurements(), bufferedWriter, requested);
                } else if ("html".equals(reportType)) {
                    return formatAndOutputInputStringListHtml(requested.getAnswer().getMeasurements(), bufferedWriter, requested);
                } else if ("md".equals(reportType)) {
                    return formatAndOutputInputStringListMd(requested.getAnswer().getMeasurements(), bufferedWriter, requested);
                }
            }
        }
        return -1;
    }
    private int formatAndOutputInputStringListHtml(List<Pair<Integer, Integer>> pairsList,BufferedWriter bufferedWriter,ISingleMeasureRequest requested)throws IOException{
        bufferedWriter.write("<!doctype html>" + 
        "\n" + 
        "<html>" + 
        "\n" + 
        "<head>" + 
        "\n" + 
        "<meta http-equiv=\"Content-Type\" content\"text/html; charset=windows-1253\">" + 
        "\n" + 
        "<title>NatutalDisaster</title>" + 
        "\n" + 
        "</head>" + 
        "\n" + 
        "<body>" + 
        "\n");

        bufferedWriter.write("<p><b>"+requested.getRequestName()+ "</b></p>" + "\n");
        bufferedWriter.write("<p><i>"+requested.getRequestFilter() +"</i></p>"+ "\n");
        bufferedWriter.write("<table>" + "\n");
        bufferedWriter.write("<tr>" + "\n");
        bufferedWriter.write("<td>Year</td>"+"<td> value"+"</td>"+"</tr>"+ "\n\n");
        int i =3;
        for(Pair<Integer, Integer> pair : pairsList){
            bufferedWriter.write("<tr>" + "\n");
            bufferedWriter.write("<td>"+pair.getKey()+"</td>"+" "+"<td>"+pair.getValue() + "</td>" +"\n");
            i++;
            bufferedWriter.write("</tr>" +"\n");
        }
        bufferedWriter.write("</table><p>" +requested.getDescriptiveStatsString()+"<p>");
        bufferedWriter.write(requested.getRegressionResultString()+"</body>" + "\n");
        bufferedWriter.write("</html>");
        bufferedWriter.newLine();
        i += 1; 
        return i;
    }

    private int formatAndOutputInputStringListText(List<Pair<Integer, Integer>> pairsList,BufferedWriter bufferedWriter,ISingleMeasureRequest requested)throws IOException{
        bufferedWriter.write(requested.getRequestName()+ "\n");
        bufferedWriter.write(requested.getRequestFilter() + "\n");
        bufferedWriter.write("Year "+"value"+ "\n");
        int i =3;
        for(Pair<Integer, Integer> pair : pairsList){
            bufferedWriter.write( pair.getKey()+" "+ pair.getValue() + "\n");
            i++;
        }
        bufferedWriter.write(requested.getDescriptiveStatsString());
        bufferedWriter.write(requested.getRegressionResultString());
        bufferedWriter.newLine();
        i += 1; 
        return i;
    }
    private int formatAndOutputInputStringListMd(List<Pair<Integer, Integer>> pairsList,BufferedWriter bufferedWriter,ISingleMeasureRequest requested)throws IOException{
        bufferedWriter.write("**"+requested.getRequestName()+"**"+ "\n\n");
        bufferedWriter.write("_"+requested.getRequestFilter()+"_" + "\n");
        bufferedWriter.write("|*Year*"+"|*value*|"+ "\n");
        bufferedWriter.write("|----|----|" +"\n");
        int i =3;
        for(Pair<Integer, Integer> pair : pairsList){
            bufferedWriter.write( "|"+pair.getKey()+" |"+pair.getValue()+"|" + "\n");
            i++;
        }
        bufferedWriter.write("\n"+requested.getDescriptiveStatsString() +"\n");
        bufferedWriter.write(requested.getRegressionResultString());
        bufferedWriter.newLine();
        i += 1; 
        return i;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException{
        DataProcessing test = new DataProcessing();
        test.load("src/main/resources/InputData/ClimateRelatedDisasters.tsv","\t");
        test.findSingleCountryIndicator("example", "Greece", "TOTAL");
        //test.findSingleCountryIndicatorYearRange("example", "Greece", "TOTAL", 1985, 1989);
       // test.findSingleCountryIndicatorYearRange("example2", "Zimbabwe", "Drought", 1985, 1989);
        System.out.println(test.getRequestByName("example").getDescriptiveStatsString());
        System.out.println(test.getRequestByName("example").getRegressionResultString());
        test.reportToFile("allha.txt", "example" , "txt" );

    }
}
   

