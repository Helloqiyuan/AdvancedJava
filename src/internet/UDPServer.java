package internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Server launched");
        //创建接收端对象
        DatagramSocket server = new DatagramSocket(8888);
        //创建一个数据包对象来接收数据
        byte[] buffer = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
        //等待接收数据
        server.receive(packet);
        //获取收到的数据,获取多少输出多少
        int length = packet.getLength();
        String rs = new String(buffer,0,length);
        System.out.println("Receive:" + rs);
        server.close();
    }
}
