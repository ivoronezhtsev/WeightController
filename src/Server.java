import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final WeightData weightData;

    public Server(WeightData weightData) {
        this.weightData = weightData;
    }

    public void start() {
        try {
            try(ServerSocket serverSocket = new ServerSocket(1024)) {
                System.out.println("Start listening  " + serverSocket.getLocalPort());
                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = serverSocket.accept();
                    System.out.println("New connection accepted");
                    Thread thread = new Thread(new Connection(socket.getOutputStream(), weightData));
                    thread.start();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
