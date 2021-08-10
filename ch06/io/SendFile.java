package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//服务端向客户端发送文件
public class SendFile implements Runnable{
	private Socket socket ;
	public SendFile(Socket socket) {
		this.socket = socket ;
	}
	@Override
	public void run() {
		try {
			System.out.println("连接成功！");
			OutputStream out = socket.getOutputStream() ;
			File file  = new File("d:\\xyz.png");
			InputStream fileIn = new FileInputStream(file) ;
			byte[] bs = new byte[64] ;
			int len = -1 ;
			while( (len=fileIn.read(bs)) !=-1   ) {
				out.write(bs,0,len);
			}
			fileIn.close();
			out.close();
			socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
