package homework.work8_internet.work;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException {
        //Socket socket = new Socket("113.219.237.121", 18244);
        Socket socket = new Socket("127.0.0.1", 6666);
        System.out.println(socket.getInetAddress() + ":" + socket.getLocalPort() + "客户端启动成功！");
        //测试连接
        System.out.println("/help获取帮助");
        //接收消息线程
        //注释
        new Thread(()->{
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while(true){
                    String msg = reader.readLine();
                    if(msg == null || "".equals(msg)){
                        continue;
                    }
                    System.out.println("->" + msg);
                }
            } catch (IOException e) {
                System.out.println("服务器连接丢失");
                System.exit(0);
            }
        }).start();
        //发送消息线程
        new Thread(()->{
            try {
                OutputStream out = socket.getOutputStream();
                Scanner sc = new Scanner(System.in);
                while(true){
                    String msg = sc.nextLine();
                    out.write((msg + "\n").getBytes());
                    out.flush();
                    if("/q".equals(msg)){
                        sc.close();
                        out.close();
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
