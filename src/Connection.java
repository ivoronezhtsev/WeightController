import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Connection implements Runnable {

    private final BufferedWriter bufferedWriter;
    private final WeightData weightData;

    public Connection(OutputStream outputStream, WeightData weightData) {
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        this.weightData = weightData;
        weightData.addConsumer(this);
    }

    @Override
    public void run() {
        try {
            bufferedWriter.write(weightData.getScales1().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(weightData.getScales2().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(weightData.getScales3().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(weightData.getScales4().toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            weightData.removeConsumer(this);
            throw new RuntimeException(e);
        }
    }
}
