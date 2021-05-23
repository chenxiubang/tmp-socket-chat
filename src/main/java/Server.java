import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    //存放连接上的客户端
    private static final List<Socket> CLIENT_LIST = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //指定一个端口的构造方法
        //ServerSocket不是Socket的子类
        var socket = new ServerSocket(8888);
        while (true) {
            System.out.println("等待中...");

            var client = socket.accept();
            CLIENT_LIST.add(client);
            System.out.println("有一个主机连接上了！地址：" + client.getInetAddress() + "\t端口:" + client.getPort());
            //启动一个线程为客户端服务
            new Thread(new ServerThread(client, CLIENT_LIST)).start();
        }
    }
}
