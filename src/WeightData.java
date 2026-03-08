import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class WeightData {

    private final AtomicReferenceArray<WeightInfo> weightInfos = new AtomicReferenceArray<>(4);

    public WeightData() {
        for (int i = 0; i < 4; i++) {
            weightInfos.compareAndSet(i, null, new WeightInfo(0.0, ZonedDateTime.now()));
        }
    }

    public void setScales1(WeightInfo weightInfo) {
        weightInfos.compareAndSet(0, weightInfos.get(0), weightInfo);
    }

    public void setScales2(WeightInfo weightInfo) {
        weightInfos.compareAndSet(1, weightInfos.get(1), weightInfo);
    }

    public void setScales3(WeightInfo weightInfo) {
        weightInfos.compareAndSet(2, weightInfos.get(2), weightInfo);
    }

    public void setScales4(WeightInfo weightInfo) {
        weightInfos.compareAndSet(3, weightInfos.get(3), weightInfo);
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
