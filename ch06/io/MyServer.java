package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        //服务的地址： 本机ip:8888
        ServerSocket server = new ServerSocket(8888);
        //允许接收多个客户端连接
        while (true) {
            //一直阻塞，直到有客户端发来连接
            Socket socket = server.accept();
            //创建一个线程，用于给该客户端发送一个文件
            new Thread(new SendFile(socket)).start();
        }
    }
}
