public class Main {
    public static void main(String[] args) {
        while(true) {
            try {
                MapMaker m = new MapMaker();
                Thread.sleep(10 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
