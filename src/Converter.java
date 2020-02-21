public class Converter {
    private final static Coord upvCoord = new Coord(729010.386599999852479 ,4373390.0215);
    private final static Coord aytCoord = new Coord(725685.211699999869, 4372281.7883);

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
        Coord upv = new Coord(729010.386599999852479 ,4373390.0215);
        Coord ayt = new Coord(725685.211699999869, 4372281.7883);
        Coord viv = new Coord(726236.403599999845028,4373308.10099999979);
        Coord vivN = new Coord(39.480783, -0.367737);
        Coord upvN = new Coord(39.479650, -0.337397);
        Coord aytN = new Coord(39.470069, -0.377746);

        Converter c = new Converter(upvN, aytN);
        Coord n = c.convert(viv);
        System.out.println(n.getX());
        System.out.println(n.getY());
    }
}
