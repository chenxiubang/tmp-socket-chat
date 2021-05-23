import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            var bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                var msg = bf.readLine();
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
