package homework.work8_internet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket  serverSocket = null;
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        BufferedReader reader = null;

        serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动！");
        //阻塞等待客户端连接
        while(true) {
            socket = serverSocket.accept();
            System.out.println("服务器端检测到IP:" + socket.getInetAddress().getHostName() + "连接");
            if(socket == null){
                break;
            }
        }
        in = socket.getInputStream();
        out = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        do{
            System.out.println("服务器端接收到的客户端信息是:" + reader.readLine());
            out.write("已收到\n".getBytes());
            out.flush();
        }while (true);
    }
}
