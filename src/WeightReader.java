import java.io.*;
import java.time.ZonedDateTime;
import java.util.function.Consumer;

public class WeightReader implements Runnable {
    private final String fileName;
    private Consumer<WeightData.WeightInfo> onWeightChange;

    public WeightReader(String fileName, Consumer<WeightData.WeightInfo> onWeightChange) {
        this.fileName = fileName;
        this.onWeightChange = onWeightChange;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(fileName)))) {
            String weight;
            while ((weight = bufferedReader.readLine()) != null) {
                Thread.sleep(1000);
                try {
                    onWeightChange.accept(new WeightData.WeightInfo(Double.parseDouble(weight), ZonedDateTime.now()));
                } catch (Exception exception) {
                    System.err.println("Ошибка при обработке данных о весе " + exception.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
