import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class DataProc {

    private ArrayList<Point> points;

    public DataProc(){
        points = readCSV();
    }

    /**Returns the points list**/
    public ArrayList<Point> getPoints(){
        return points;
    }

    /**Returns the point array reading the csv database**/
    public ArrayList<Point> readCSV(){
        ArrayList<Point> pointList = new ArrayList<>();
        String csvFile = "src/resources/opendata5524238908286670224.csv";
        BufferedReader bufferedReader;
        String line = "";
        String splitWith = ";";

        try{
            bufferedReader = new BufferedReader(new FileReader(csvFile));

            while((line = bufferedReader.readLine()) != null){

                String[] dataPlace = line.split(splitWith);

                pointList.add(new Point(Double.parseDouble(dataPlace[0]), Double.parseDouble(dataPlace[1]) ,
                        indexCalc(Objects.requireNonNull(downloadCSV(dataPlace[7])))));
            }

        }
        catch (FileNotFoundException e){System.out.println("File not Found.");}
        catch (IOException e){System.out.println("IOException");}

        adaptMeasures(pointList);
        adaptMeasures(pointList);
        return pointList;
    }

    /**Adapt the pollution measures to represent them in the map**/
    private void adaptMeasures(ArrayList<Point> pointList){
        double max = 0.0;
        double min = 100.0;

        //Consigo el maximo de la lista
        for(Point point : pointList){
            double data = point.getData();
            if(data > max){
                max = point.getData();
            }
            else if(data < min){
                min = data;
            }
        }

        for(Point point : pointList){
            double newValue = point.getData();
            newValue = -(((newValue - min) / (max - min)) * 200 - 100);
            point.setData((int)newValue);
        }



    }

    private static ArrayList<Integer> downloadCSV(String url){
        ArrayList<Integer> result = new ArrayList<>();

        BufferedInputStream inputStream;
        try {
            inputStream = new BufferedInputStream(new URL(url).openStream());
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String line = "";
            String separator = ";";
            String[] data;
            String lastLine  = "";

            while((line = dataInputStream.readLine()) != null){
                lastLine = line;
            }

            data = lastLine.split(separator);

            for(int i = 1; i < data.length; i++){
                if(!data[i].equals("") && data[i].matches("[0-9]+")) {
                    result.add(Integer.valueOf(data[i]));
                }
            }
        }
        catch (MalformedURLException e){System.out.println("MalformedURL");}
        catch (IOException e){System.out.println(e.getMessage()); return null;}

        return result;
    }

    /**Returns the punctuation of a point given the pollution data**/
    private int indexCalc(ArrayList<Integer> arrayList){
        double result = 0;
        for (Integer integer : arrayList) {
            result += integer;
        }
        result = result / arrayList.size();

        return (int) result;
    }

}
