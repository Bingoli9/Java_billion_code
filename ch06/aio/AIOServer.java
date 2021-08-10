package com.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOServer {
    public static void main(String[] args) throws Exception {
        final AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel
                .open()
                .bind(new InetSocketAddress("127.0.0.1", 8888));

        while (true) {
            //接收客户端请求的连接
            channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                //当接收到连接时，触发completed()
                @Override
                public void completed(final AsynchronousSocketChannel client, Void attachment) {
                    channel.accept(null, this);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //开接收客户端发来的消息
                    client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        //当接收到消息时，触发completed()
                        @Override
                        public void completed(Integer result_num, ByteBuffer dataBuffer) {
                            dataBuffer.flip();
                            String receive = new String(dataBuffer.array(), 0, dataBuffer.limit());
                            System.out.println("接收到的客户端消息:" + receive);
                            try {
                                client.close();
                            } catch (Exception e) {
                                e.printStackTrace();//打印异常
                            }
                        }
                        @Override
                        public void failed(Throwable e, ByteBuffer attachment) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void failed(Throwable e, Void attachment) {
                    e.printStackTrace();
                }
            });
            for (; ; ) {
                System.out.println("main线程和用于读取客户端消息的线程是异步执行的...");
                Thread.sleep(1000);
            }
        }
    }
}
