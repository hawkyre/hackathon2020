public class Point extends Coord{
//HOLA
    private Coord coord;
    private int data; //[-100, 100]

    public Point(double x, double y, int data){
        super(x, y);
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
