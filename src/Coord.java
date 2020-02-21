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

    /**Static methods**/
    public static double compX(Coord a, Coord b) {
        return a.getX() - b.getX();
    }

    public static double compY(Coord a, Coord b) {
        return a.getY() - b.getY();
    }

    public static double distance(Coord a, Coord b) {
        return Math.sqrt((Math.pow(compY(a,b), 2) + Math.pow(compX(a,b), 2)));
    }
}
