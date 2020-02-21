public class Converter {
    private final static Coord upvCoord = new Coord(39.480101,-0.344469);
    private final static Coord aytCoord = new Coord(39.470319,-0.377101);

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

    public Coord convert(Coord c) {
        Coord res = new Coord(0, 0);
        res.setX((c.getX() * relX) + despX);
        res.setY((c.getY() * relY) + despY);
        return res;
    }

    public static void main(String[] args){
        Coord viv = new Coord(39.480936,-0.367661);
        Coord upvN = new Coord(2.5, 15);
        Coord aytN = new Coord(5, 10);

        Converter c = new Converter(upvN, aytN);
        Coord n = c.convert(viv);
        System.out.println(n.getX());
        System.out.println(n.getY());
    }
}
