import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataProc {

    private ArrayList<Point> points;

    public DataProc(){
        points = readCsv();
    }

    /**Returns the points list**/
    public ArrayList<Point> getPoints(){
        return points;
    }

    public ArrayList<Point> readCsv(){
        ArrayList<Point> pointList = new ArrayList<Point>();

        String csvFile = "src/resources/opendata5524238908286670224.csv";
        BufferedReader bufferedReader;
        String line = "";
        String splitWith = ";";

        try{
            bufferedReader = new BufferedReader(new FileReader(csvFile));

            while((line = bufferedReader.readLine()) != null){

                String[] dataPlace = line.split(splitWith);

                Coord coordinate = new Coord(Double.parseDouble(dataPlace[0]), Double.parseDouble(dataPlace[1]));

                pointList.add(new Point(coordinate, 0));

            }

        }
        catch (FileNotFoundException e){System.out.println("File not Found");}
        catch (IOException e){System.out.println("IOException");}

        return pointList;
    }

    private static int[] downloadCSV(String url){
        BufferedInputStream inputStream;
        try {
            inputStream = new BufferedInputStream(new URL(url).openStream());
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String line = "";
            String splitWith = ";";
            String[] data;
            while(dataInputStream.readLine())
            System.out.println(dataInputStream.readLine());


        }
        catch (MalformedURLException e){System.out.println("MalformedURL");}
        catch (IOException e){System.out.println("IOException downloading CSV"); return null;}

        return null;
    }


    public static void main(String[] args){
        downloadCSV("http://mapas.valencia.es/WebsMunicipales/uploads/atmosferica/1A.csv");
    }


}
