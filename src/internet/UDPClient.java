package internet;

import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception
    {
        System.out.println("Client launched");
        //创建发送端对象
        DatagramSocket client = new DatagramSocket(7777);
        //创建数据包对象
        String msg = "LML玩原神";
        byte[] data = msg.getBytes();
        InetAddress address = InetAddress.getByName("2409:8938:3652:ad75:b58d:bd3d:2996:a485");
        DatagramPacket packet = new DatagramPacket(data,data.length, address,8888);
        //发送数据
        client.send(packet);
        System.out.println("Sent:" + msg);
        //释放资源
        client.close();
    }
}
