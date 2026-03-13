import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class WeightData {

    private final AtomicReferenceArray<WeightInfo> weightInfos = new AtomicReferenceArray<>(4);
    private final List<Runnable> consumerList = new CopyOnWriteArrayList<>();

    public WeightData() {
        for (int i = 0; i < 4; i++) {
            weightInfos.compareAndSet(i, null, new WeightInfo(0.0, ZonedDateTime.now()));
        }
    }

    public void addConsumer(Runnable consumer) {
        consumerList.add(consumer);
    }

    public void removeConsumer(Runnable consumer) {
        consumerList.remove(consumer);
    }

    public void setScales1(WeightInfo weightInfo) {
        weightInfos.compareAndSet(0, weightInfos.get(0), weightInfo);
        notifyConsumers();
    }

    public void setScales2(WeightInfo weightInfo) {
        weightInfos.compareAndSet(1, weightInfos.get(1), weightInfo);
        notifyConsumers();
    }

    public void setScales3(WeightInfo weightInfo) {
        weightInfos.compareAndSet(2, weightInfos.get(2), weightInfo);
        notifyConsumers();
    }

    public void setScales4(WeightInfo weightInfo) {
        weightInfos.compareAndSet(3, weightInfos.get(3), weightInfo);
        notifyConsumers();
    }

    public WeightData.WeightInfo getScales1() {
        return weightInfos.get(0);
    }

    public WeightData.WeightInfo getScales2() {
        return weightInfos.get(1);
    }

    public WeightData.WeightInfo getScales3() {
        return weightInfos.get(2);
    }

    public WeightData.WeightInfo getScales4() {
        return weightInfos.get(3);
    }

    private void notifyConsumers() {
        for (Runnable runnable: consumerList) {
            runnable.run();
        }
    }

    public static class WeightInfo {
        private final double weight;
        private final ZonedDateTime weightTime;

        public WeightInfo(double weight, ZonedDateTime weightTime) {
            this.weight = weight;
            this.weightTime = weightTime;
        }

        @Override
        public String toString() {
            return weight + " " + weightTime;
        }
    }
}
