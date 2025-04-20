public class Main {
    public static void main(String[] args) {
        Map m = new Map();


        try {
            wave w = new wave(m);
            w.wave1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}