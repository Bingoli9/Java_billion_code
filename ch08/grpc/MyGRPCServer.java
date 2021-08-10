package com.yanqun.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MyGRPCServer {
    private Server server;

    //启动服务
    private void start() throws IOException {
        int port = 8888;
        server = ServerBuilder.forPort(port)
                .addService(new StudentServiceImpl())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
                System.err.println(Thread.currentThread().getName() + "，关闭JVM");
                //当JVM关闭时，也同时关闭MyGRPCServer服务
                MyGRPCServer.this.stop();
            }
        ));
    }

    //关闭服务
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            //等待服务结束
            server.awaitTermination();
//            server.awaitTermination(5,TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final MyGRPCServer server = new MyGRPCServer();
        server.start();
        server.blockUntilShutdown();
    }
}
