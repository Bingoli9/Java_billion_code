package com.yanqun.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CopyOnWriteArrayList;

public class NIOServerWith2Buffers {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8888)) ;

        ByteBuffer[] buffers = new ByteBuffer[2] ;
        buffers[0]  = ByteBuffer.allocate(4) ;
        buffers[1]  = ByteBuffer.allocate(8) ;

        int bufferSum = 4 + 8 ;
        SocketChannel socketChannel =   serverSocketChannel.accept();

        while(true){
            /*
                 读取客户端的消息：
                        eachReadbytes：每次读取到的字节数
                        totalReadBytes：当前时刻，一共读取的字节数
                 如果totalReadBytes小于"buffers能够容纳的最大字节数"，则循环累加读取；否则，清空buffers，重新读取
             */
            int totalReadBytes = 0 ;
            while(totalReadBytes < bufferSum){
                long eachReadbytes = socketChannel.read(buffers);
                totalReadBytes += eachReadbytes ;
                System.out.println("读取到的数据大小：" + eachReadbytes);
            }
            //如果buffers已满
            for(ByteBuffer buffer : buffers) {
                buffer.flip() ;
            }
        }
    }
}
