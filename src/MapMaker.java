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

    public static final int X_SIZE = 1271, Y_SIZE = 715;
    public static final int X_AYUN = 669, Y_AYUN = 400;
    public static final int X_UPV = 959, Y_UPV = 291;

    private String dMapSource = "./maps/mapa3.png";
    private String dMapRes = "./mapResult.png";
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

        cPoints = new ArrayList<Point>();

        File result = new File(dMapRes);
        File img = new File(dMapSource);
        try {
            map = ImageIO.read(img);
        } catch(IOException e){
            e.printStackTrace();
        }

        getPoints();
        draw();

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

    public void draw() {
        byte max = 0;
        for (int i = 0; i < X_SIZE; i++){
            for (int j = 0; j < Y_SIZE ; j++) {
                Color col = new Color(map.getRGB(i, j));
                int pollution = getCLevel(new Coord(i,j));
                if (pollution != 0){
                Color overlay = (pollution < 0) ? Color.RED : Color.GREEN;
                Integer pollutionB = Integer.valueOf(Math.abs(pollution*2));
                Color finalColor = null;
                if (pollutionB.byteValue() > max) {max = pollutionB.byteValue();}
                if (pollutionB.byteValue() < 0) {
                    finalColor = mixColorsWithAlpha(col, overlay, (byte)126);
                }
                else{finalColor = mixColorsWithAlpha(col, overlay, Math.abs(pollutionB.byteValue()));}
                map.setRGB(i,j,finalColor.getRGB());}
                //map.setRGB(i,j,(blend(col, overlay, 0.5f)).getRGB());
            }
        }
        System.out.println(max);
    }


    public static Color mixColorsWithAlpha(Color color1, Color color2, int alpha)
    {
        float factor = alpha / 255f;
        int red = (int) (color1.getRed() * (1 - factor) + color2.getRed() * factor);
        int green = (int) (color1.getGreen() * (1 - factor) + color2.getGreen() * factor);
        int blue = (int) (color1.getBlue() * (1 - factor) + color2.getBlue() * factor);
        return new Color(red, green, blue);
    }


    public int getCLevel(Coord p) {
        int maxCLevel = 0;
        for (int i = 0; i < cPoints.size(); i++) {
            Point curr = cPoints.get(i);
            Coord currPos = curr;
            int dataAbs = Math.abs(curr.getData());
            if (Math.abs(Coord.compX(currPos, p)) < dataAbs && Math.abs(Coord.compY(currPos, p)) < dataAbs) {
                int dist = (int)(Coord.distance(currPos, p));
                if (dist < dataAbs) {
                    int currData = curr.getData();
                    int currCLevel = (currData<0) ? currData + dist : currData - dist;
                    maxCLevel = (Math.abs(currCLevel) > Math.abs(maxCLevel)) ? currCLevel : maxCLevel;
                }
            }
        }
        return maxCLevel;
    }

    public Color setAlpha(Color c, byte alpha) {
        alpha %= 0xff;
        int color = c.getRGB();

        int mc = (alpha << 24) | 0x00ffffff;
        int newcolor = color & mc;
        return new Color(newcolor);
    }

    public Color blend( Color c1, Color c2, float ratio ) {
        if ( ratio > 1f ) ratio = 1f;
        else if ( ratio < 0f ) ratio = 0f;
        float iRatio = 1.0f - ratio;

        int i1 = c1.getRGB();
        int i2 = c2.getRGB();

        int a1 = (i1 >> 24 & 0xff);
        int r1 = ((i1 & 0xff0000) >> 16);
        int g1 = ((i1 & 0xff00) >> 8);
        int b1 = (i1 & 0xff);

        int a2 = (i2 >> 24 & 0xff);
        int r2 = ((i2 & 0xff0000) >> 16);
        int g2 = ((i2 & 0xff00) >> 8);
        int b2 = (i2 & 0xff);

        int a = (int)((a1 * iRatio) + (a2 * ratio));
        int r = (int)((r1 * iRatio) + (r2 * ratio));
        int g = (int)((g1 * iRatio) + (g2 * ratio));
        int b = (int)((b1 * iRatio) + (b2 * ratio));

        return new Color( a << 24 | r << 16 | g << 8 | b );
    }

    public void loadPoints(ArrayList<Point> points) {
        Converter toPixel = new Converter(new Coord(X_UPV, Y_UPV), new Coord(X_AYUN, Y_AYUN));
        for (int i = 0; i < points.size(); i++) {
            Point pixel = toPixel.convert(points.get(i));//dentro la coordenada
            cPoints.add(pixel);
        }
    }

    public void getPoints() {
        DataProc dp = new DataProc();
        loadPoints(dp.getPoints());
    }
    public BufferedImage getMapBuffer() {
        return map;
    }
}
