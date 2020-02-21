public class Point {

    private Coord coord;
    private int data; //[-100, 100]

    public Point(Coord coord, int data){
        this.coord = coord;
        this.data = data;
    }

    public Coord getCoord() {
        return coord;
    }

    public int getData() {
        return data;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void setData(int data) {
        this.data = data;
    }
}
