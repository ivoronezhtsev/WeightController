import java.io.*;

public class Connection implements Runnable {

    private final BufferedWriter bufferedWriter;
    private final WeightData weightData;

    public Connection(OutputStream outputStream, WeightData weightData) {
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        this.weightData = weightData;
    }

    @Override
    public void run() {
        while (true) {
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
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
