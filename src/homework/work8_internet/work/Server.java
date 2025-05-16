package homework.work8_internet.work;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
@Deprecated
public class Server {
    /*
    <User,boolean>:记录用户
    <Socket>:记录客户端用来群发
    <Socket,User>:映射
     */
    //一个ip:port对应一个writer
    private static final HashMap<String,BufferedWriter> users = new HashMap<>();
    //一个ip:port对应一个User
    private static final HashMap<String,User> pwd = new HashMap<>();
    private static final HashMap<User,String> client = new HashMap<>();
    private static final int PORT = 6666;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("服务器在" + PORT + "监听");

        while(true){
            Socket socket = serverSocket.accept();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("服务器端检测到" + socket.getInetAddress() + ":" + socket.getPort() + "连接");
            broadcastTo(writer,"连接服务器成功!");
            new Thread(()->receive(socket,writer)).start();
        }
    }
    public static void receive(Socket socket,BufferedWriter writer) {
         try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
             String msg;
             while (true){
                 msg = reader.readLine();
                 if(msg == null || msg.isEmpty()){
                     continue;
                 }
                 if(msg.contains("/login")){
                     if(users.containsKey(getIPPort(socket))){
                        broadcastTo(writer,"");
                        break;
                     }
                 }
             }
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
        try{

            while (true){
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = reader.readLine();
                System.out.println("服务器端接收到的" + (users.containsKey(getIPPort(socket)) ? "":"未") + "登录用户:" + socket.getInetAddress() + ":" + socket.getPort() + "发的:" + msg);
                if(msg.contains("/help")){
                    broadcastTo(writer,"/login username password username 登录\n/to user message 给谁说悄悄话\n/ls 列出所有在线用户");
                    continue;
                }
                if("/q".equals(msg)){
                    throw new IOException();
                }
                if(!users.containsKey(getIPPort(socket)) && msg.contains("/login")){
                    String[] x = msg.split(" ");
                    if(x.length < 3){
                        broadcastTo(writer,"请输入/login username password username");
                        continue;
                    }
                    users.put(getIPPort(socket),writer);
                    //pwd.put(getIPPort(socket),x[1] + " " + x[2] + " " + x[3]);
                    broadcastTo(writer,"登录成功");
                    broadcast(writer,/*socket.getInetAddress() + ":" + socket.getPort() */ getName(getIPPort(socket))+ "上线了");
                    continue;
                }
                if(!users.containsKey(getIPPort(socket))){
                    broadcastTo(writer,"请先登录");
                    continue;
                }
                if(users.containsKey(getIPPort(socket)) && "/login".equals(msg)){
                    broadcastTo(writer,"你已登录");
                    continue;
                }
                if("/to".equals(msg)){
                    String[] x = msg.split(" ");
                    if(users.get(x[1]) == null){
                        broadcastTo(writer,"该用户不在线或不存在");
                        continue;
                    }
                    broadcastTo(users.get(x[1]), getName(getIPPort(socket)) + "对你说:" + x[2]);
                    continue;
                }
                if("/ls".equals(msg)){
                    broadcastTo(writer,"在线用户:");
                    for(String x:users.keySet()){
                        //broadcastTo(writer,x + ":" + pwd.get(x).split(" ")[2]);
                    }
//                    broadcastTo(writer,"用户密码:");
//                    for(String x:pwd.keySet()){
//                        broadcastTo(writer,x + ":" + pwd.get(x));
//                    }
                    continue;
                }
                broadcast(writer,/*socket.getInetAddress() + ":" + socket.getPort() */ getName(getIPPort(socket)) + "说:" + msg);
            }
        } catch (IOException e) {
            users.remove(getIPPort(socket));
            System.out.println("客户端:" + getIPPort(socket) + "断开连接");
        }
    }
    //广播给非target的在线用户
    public static void broadcast(BufferedWriter target,String msg){
        try {
            for (BufferedWriter x : users.values()) {
                if(x != target){
                    x.write(msg + "\n");
                    x.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //广播给target用户
    public static void broadcastTo(BufferedWriter target,String msg){
        try {
            target.write(msg + "\n");
            target.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //获取IP:Port
    public static String getIPPort(Socket socket){
        return socket.getInetAddress() + ":" + socket.getPort();
    }
    public static String getName(String x){
        //return pwd.get(x).split(" ")[2];
        return null;
    }
}
