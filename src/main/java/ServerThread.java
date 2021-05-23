
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread implements Runnable {
    private Socket socket;
    private List<Socket> socketList;

    public ServerThread(Socket socket, List<Socket> socketList) {
        this.socket = socket;
        this.socketList = socketList;
    }

    @Override
    public void run() {
        //获取当前线程客户端输入流，不停的发送给客户端列表的其他所有对象
        try {
            var br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                var data = br.readLine();
                for (Socket s : socketList) {
                    var pw = new PrintWriter(s.getOutputStream());
                    pw.println(data);
                    pw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
