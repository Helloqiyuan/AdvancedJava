package homework.work8_internet;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        BufferedReader reader = null;
        Scanner scan = new Scanner(System.in);
        //尝试建立连接
        socket = new Socket("127.0.0.1", 6666);
        out = socket.getOutputStream();
        in = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        String sendInfo;
        do{
            System.out.print("请输入:");
            sendInfo = scan.nextLine();
            System.out.println("正在发送...");
            out.write((sendInfo + "\n").getBytes());
            if(sendInfo.equals("exit")){
                out.close();
                break;
            }

            out.flush();
//            if(reader.readLine().equals("已收到")){
//                System.out.println("发送成功!");
//            }else{
//                System.out.println("发送失败!");
//            }
        }while(true);
    }
}
