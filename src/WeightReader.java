import java.io.*;

public class WeightReader implements Runnable {
    private final String fileName;

    public WeightReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(fileName)))) {
            String weight;
            while((weight = bufferedReader.readLine()) != null) {
                System.out.println(weight);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
