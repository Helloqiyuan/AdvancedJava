package internet;

import java.net.InetAddress;

public class Test {
    public static void main(String[] args) throws Exception{
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());

        InetAddress remoteAddress = InetAddress.getByName("www.jju.edu.cn");
        System.out.println(remoteAddress.getHostAddress());

    }
}
