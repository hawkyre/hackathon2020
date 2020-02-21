public class Coord {

    private double x;
    private double y;

    /*Constructor*/
    public Coord(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String toString(){
        return "Point x: " + x + "Point y" + y;
    }
}
