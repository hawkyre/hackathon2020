public class Converter {
    private final static Coord upvCoord = new Coord(-0.344469, 39.480101);
    private final static Coord aytCoord = new Coord(-0.377101, 39.470319);

    private double despX;
    private double despY;
    private double relX;
    private double relY;

    public Converter(Coord upv, Coord ayt) {
        this.relX = (upv.getX() - ayt.getX()) / (upvCoord.getX() - aytCoord.getX());
        this.relY = (upv.getY() - ayt.getY()) / (upvCoord.getY() - aytCoord.getY());

        this.despX = upv.getX() - (upvCoord.getX() * this.relX);
        this.despY = upv.getY() - (upvCoord.getY() * this.relY);
    }

    public Point convert(Point c) {
        Point res = new Point(0, 0, c.getData());
        res.setX((c.getX() * relX) + despX);
        res.setY((c.getY() * relY) + despY);
        return res;
    }
}
