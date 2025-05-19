package homework.work8_internet.work;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class ServerV2 {
    //记录所有注册用户的在线状态
    private static final HashMap<User,Boolean> users = new HashMap<>();
    //登录成功时 记录客户端,退出一个客户端对应的数据会删除
    private static final HashSet<Socket> sockets = new HashSet<>();
    //登录成功时 用户映射客户端,退出一个客户端对应的数据会删除
    private static final HashMap<User,Socket> userSocket = new HashMap<>();

    public static final int PORT = 6666;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("服务器在" + PORT + "监听");

        while(true){
            Socket socket = serverSocket.accept();
            if(socket == null){
                continue;
            }
            System.out.println("服务器端检测到客户端" + socket.getInetAddress() + ":" + socket.getPort() + "连接");
            broadcastTo(socket,"服务器连接成功!");
            new Thread(()->logIn(socket)).start();
        }
    }
    public static void logIn(Socket socket){
        //System.out.println("logIn检查点");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;

            while(true) {
                msg = reader.readLine();
                System.out.println("服务器端接收到客户端" + socket.getInetAddress() + ":" + socket.getPort() + "发来的消息:" + msg);
                if(msg == null || msg.isEmpty()){
                    continue;
                }
                if("/q".equals(msg)) {
                    throw new IOException();
                }else if(msg.startsWith("/help")){
                    broadcastTo(socket, "/login account password name 登录及注册\n/q 下线");
                }else if(msg.startsWith("/login")){
                    /*
                    /login account password name
                     */
                    String[] arr = msg.split(" ");

                    if(arr.length < 4){
                        broadcastTo(socket,"格式错误/login account password name");
                        continue;
                    }
                    User user = new User(arr[1],arr[2],arr[3]);
                    if(users.containsKey(user)){
                        //User finalUser = user;
                        User oldUser = users
                                .keySet()
                                .stream()
                                .filter(x->x.getAccount().equals(user.getAccount()))
                                .toList()
                                .getFirst();
                        if(oldUser.getPassword().equals(user.getPassword())){
                            if(users.get(oldUser)){
                                broadcastTo(socket,"账号在别处登录,登录失败!");
                                continue;
                            }
                            broadcastTo(socket, "登录成功！");
                            if (!oldUser.getName().equals(user.getName())) {
                                oldUser.setName(user.getName());
                                broadcastTo(socket, "昵称已更新为" + user.getName());
                            }
                        }else{
                            broadcastTo(socket,"密码错误");
                            continue;
                        }
                    }else{
                        broadcastTo(socket,"注册成功！");
                    }
                    users.put(user,true);
                    sockets.add(socket);
                    userSocket.put(user,socket);
                    broadcastUnless(socket,user.getName() + "上线了");
                    new Thread(()->receive(socket,user)).start();
                    break;
                }else {
                    broadcastTo(socket, "请先登录!");
                }
            }
        } catch (IOException e) {
            System.out.println("客户端:" + getIPPort(socket) + "断开连接");
        }
    }
    public static void receive(Socket socket,User user){
        //System.out.println("receive检查点");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String msg = reader.readLine().toLowerCase();
                if(msg == null || msg.isEmpty()){continue;}
                System.out.println("服务器接收到用户:" + user.toString() + "使用客户端:" + getIPPort(socket) + "发来的消息:" + msg);
                if(msg.startsWith("/help")){
                    broadcastTo(socket,"/to account message 给某人说悄悄话\n/ls 列出在线用户\n/au 列出全部用户\n/q 下线");
                }else if("/q".equals(msg)){
                    throw new IOException();
                }else if(msg.startsWith("/to")){
                    /*
                    /to name message
                     */
                    String[] x = msg.split(" ");
                    User toUser = new User(x[1],null,null);
                    if(!users.containsKey(toUser)){
                        broadcastTo(socket,"用户不存在");
                        continue;
                    }
                    broadcastTo(userSocket.get(toUser),user.getName() + "对你说:" + x[2]);
                }else if(msg.startsWith("/ls")){
                    broadcastTo(socket,"在线用户:");
                    for(User x: users.keySet()){
                        if(users.get(x)){
                            broadcastTo(socket,"账号:" + x.getAccount() + "昵称:" + x.getName());
                        }
                    }
                }else if(msg.startsWith("/au")){
                    broadcastTo(socket,"历史用户:");
                    for(User x: users.keySet()){
                        broadcastTo(socket,"账号:" + x.getAccount() + "昵称:" + x.getName());
                    }
                }else{
                    broadcastUnless(socket,user.getName() + "说:" + msg);
                }
            }
        } catch (IOException e) {
            users.put(user,false);
            sockets.remove(socket);
            userSocket.remove(user);
            System.out.println("客户端:" + getIPPort(socket) + "断开连接,用户:" + user.toString() + "离线");
        } catch (Exception e) {
            System.err.println("客户端:" + getIPPort(socket) + ",用户:" + user.toString() + "线程发生未知错误");
            System.err.println("Err!!!" + e);
        }
    }
    //广播给其他用户
    public static void broadcastUnless(Socket socket,String msg){
        for(Socket x:sockets){
            if(x != socket){
                //给其他登录的用户发送
                broadcastTo(x,msg);
            }
        }
    }
    //广播给指定用户
    public static void broadcastTo(Socket socket,String msg){
        try {
            //System.out.println("broadcastTo检查点");
            OutputStream out = socket.getOutputStream();
            out.write((msg + "\n").getBytes());
            out.flush();
        } catch (IOException e) {
            System.out.println("broadcastTo广播失败:" + msg);
        }
    }
    public static String getIPPort(Socket socket){
        return socket.getInetAddress() + ":" + socket.getPort();
    }
}
