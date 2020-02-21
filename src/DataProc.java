import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

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
        ArrayList<Point> pointList = new ArrayList<>();

        String csvFile = "src/resources/opendata5524238908286670224.csv";
        BufferedReader bufferedReader;
        String line = "";
        String splitWith = ";";

        try{
            bufferedReader = new BufferedReader(new FileReader(csvFile));

            while((line = bufferedReader.readLine()) != null){

                String[] dataPlace = line.split(splitWith);

                Coord coordinate = new Coord(Double.parseDouble(dataPlace[0]), Double.parseDouble(dataPlace[1]));

                pointList.add(new Point(coordinate, indexCalc(Objects.requireNonNull(downloadCSV(dataPlace[6])))));

            }

        }
        catch (FileNotFoundException e){System.out.println("File not Found");}
        catch (IOException e){System.out.println("IOException");}

        return pointList;
    }

    private static ArrayList<Integer> downloadCSV(String url){
        ArrayList<Integer> result = new ArrayList<Integer>();

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
                result.add(Integer.valueOf(data[i]));
            }


        }
        catch (MalformedURLException e){System.out.println("MalformedURL");}
        catch (IOException e){System.out.println("IOException downloading CSV"); return null;}

        return result;
    }

    public int indexCalc(ArrayList<Integer> arrayList){
        int result = 0;
        for(int i = 0; i < arrayList.size(); i++){
            switch (i){
                case 0:
                    result += arrayList.get(0) * 2;
                    break;

                case 1:
                    result += arrayList.get(1) * 0.8;
                    break;

                case 2:
                    break;


                case 3:
                    result += arrayList.get(3) * 0.5;
                    break;

                case 4:
                    result += arrayList.get(4) * 2;
                    break;

                case 5:
                    result += arrayList.get(5) * 0.5;
                    break;

                case 6:
                    result += arrayList.get(6) * 0.833;
                    break;

            }
        }
        return result / 6;
    }

    public static void main(String[] args){


    }


}
