package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//客户端 连接服务端发布的服务
		Socket socket = new Socket("127.0.0.1",8888);
		//接受服务端发来到文件
		InputStream in = socket.getInputStream() ;
		byte[] bs = new byte[64] ;
		int len = -1 ;
		OutputStream fileOut = new FileOutputStream("d:\\xyz_copy.png") ;
		while( (len =in.read(bs))!=-1 ) {
			fileOut.write(bs,0,len);
		}
		System.out.println("文件接收成功！");
        fileOut.close();
		in.close();
		socket.close();
	}
}
