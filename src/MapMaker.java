import java.awt.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.nio.file.*;
import java.util.*;

public class MapMaker {

    public static final int X_SIZE = 1280, Y_SIZE = 720;
    public static final int X_AYUN = 640, Y_AYUN = 360;
    public static final int X_UPV = 960, Y_UPV = 540;

    private String dMapSource = "./maps/mapTest.jpg";
    private String dMapRes = "./maps/mapResult.jpg";
    private BufferedImage map;
    private ArrayList<Point> cPoints;

    public static void main(String[] args) {
        MapMaker mp = new MapMaker();   
    }
    public MapMaker() {
/*        Path source = Paths.get(dMapSource);
        Path dest = Paths.get(dMapRes);

        try {
            InputStream fis = Files.newInputStream(source);
            OutputStream fos = Files.newOutputStream(dest);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

        } catch(Exception e){e.printStackTrace();}
*/

        cPoints = new ArrayList<Points>();
        cPoints.add(new Point(new Coord(100,100), 50));

        File result = new File(dMapRes);
        File img = new File(dMapSource);
        try {
            map = ImageIO.read(img);
        } catch(IOException e){
            e.printStackTrace();
        }
/*
        for (int i = 0; i < 100; i++){
            map.setRGB(100+i, 100, Color.BLACK.getRGB());
        }
*/
        try {
            ImageIO.write(map, "jpeg", result);
        } catch(IOException e){
            e.printStackTrace();
        }

        Graphics2D g2 = map.createGraphics();
    }

    /**Create the map**/
    public Image getMap(){
        return null;
    }
    /**Private methods**/

    public int getCLevel(Point p) {
        int cLevel = 0;
        for (int i = 0; i < cPoints.size(); i++) {
            Point curr = cPoints.get(i);
            if (Math.abs(Point.cmpX(curr, p)) < 100-Math.abs(curr.getData()) && Math.abs(Point.cmpY(curr, p)) < 100-Math.abs(curr.getData())) {
                int dist = Point.distance(curr, p);
                int dataAbs = Math.abs(curr.getData());
                if (dist < dataAbs) {
                    cLevel =  (curr.getData()) ? : ;
                }
            }
        }
        return cLevel;
    }


  /*  
    public void loadPoints(ArrayList<Point> points) {
        Converter toPixel = new Converter(new Coord(X_AYUN, Y_AYUN), new Coord(X_UPV, Y_UPV));
        for (int i = 0; i < points.size(); i++) {
            Coord pixel = toPixel.convert(points.get(i));//dentro la coordenada
            map.setRGB(pixel.getX(), pixel.getY(), Color.BLACK:getRGB());
            cPoints.add(pixel);
        }
    }
*/
}
