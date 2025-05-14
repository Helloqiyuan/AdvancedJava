package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Myclient {

	public static void main(String[] args) {
		
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		BufferedReader reader = null;
		
	    try {
			socket=new Socket("127.0.0.1",8888);
			System.out.println("客户端连接成功！");
			out=socket.getOutputStream();
			out.write("Hello Server!".getBytes());

			socket.shutdownOutput();   //关闭输出流
			Thread.sleep(5000);
			//接收服务器端信息的处理
			in=socket.getInputStream();
			reader = new BufferedReader( new InputStreamReader(in));
			String info = null;
			
			while ( (info =reader.readLine())!=null ) {
				System.out.println("I am client, 接收到服务器端信息是："+ info);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
			try {
				if(out!=null) out.close();
				if(in!=null) in.close();
				if(socket!=null) socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
