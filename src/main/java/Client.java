import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        var socket = new Socket("localhost", 8888);
        new Thread(new ClientThread(socket)).start();//同时开启客户端线程接收消息
        var bw = new PrintWriter(socket.getOutputStream());
        while (true) {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var msg = br.readLine();
            bw.println(msg);
            bw.flush();
        }
    }
}
