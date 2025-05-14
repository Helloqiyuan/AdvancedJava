package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Myserver {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;	
		InputStream in = null;
		OutputStream out = null;
		BufferedReader reader = null;
				
		//1。建立连接
		try {
			serverSocket=new ServerSocket(8888);
		    System.out.println("服务器启动！");
		    socket = serverSocket.accept();
		    System.out.println("服务器端检测到客户端连接成功！");
		    
			//2.通过socket生产inputStream/outputstream
			in=socket.getInputStream();
		    //字节流转换为字符流
			reader = new BufferedReader(new InputStreamReader(in));
			
			String info = null;
			
			while ((info=reader.readLine())!=null) {
				System.out.println("I am 服务器，接收到的客户端信息是:" + info);
			}

			socket.shutdownInput();    //关闭输出流
			
			//3.发送数据
			//服务器端做出反馈
			out = socket.getOutputStream();
			out.write("Welcome client ...".getBytes());
					
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{   				//4.关闭
			try {
				if(reader!=null) reader.close();
				if(out!=null) out.close();
				if(in!=null) in.close();
				if(socket!=null) socket.close();
				if(serverSocket!=null) serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
