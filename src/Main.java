public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new WeightReader("scales-1.txt"));
        thread.start();
    }
}
