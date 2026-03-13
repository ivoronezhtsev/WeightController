void main() {
    WeightData weightData = new WeightData();
    Thread thread = new Thread(new WeightReader("scales-1.txt", weightData::setScales1));
    Thread thread1 = new Thread(new WeightReader("scales-2.txt", weightData::setScales2));
    thread.start();
    thread1.start();
    Server server = new Server(weightData);
    server.start();
}
