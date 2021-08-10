package com.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

public class AIOClient {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 8888)).get();
        ByteBuffer buffer = ByteBuffer.wrap("Hello Server".getBytes());
        //向服务端发送消息
        Future<Integer> future = channel.write(buffer);
        while (!future.isDone()) {
            System.out.println("在channel将消息发送完毕以前，main可以异步处理其他事情..");
            Thread.sleep(1000);
        }
        Integer len = future.get();
        System.out.println("发送完毕！共发送字节数："+len);

    }
}