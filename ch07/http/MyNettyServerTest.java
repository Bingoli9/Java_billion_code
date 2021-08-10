package com.yanqun.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyNettyServerTest {
    public static void main(String[] args) {
            /*  EventLoopGroup：事件循环组，是一个线程池，也是一个死循环，用于不断的接收用户请求
            bossGroup：用于监听及建立连接，并把每一个连接抽象为一个channel，最后将连接再交给workerGroup处理
            workerGroup：真正的处理连接
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap：服务端启动时的初始化操作
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //将bossGroup和workerGroup注册到服务端的Channel上，并注册一个服务端的初始化器NettyServerInitializer（该初始化器中的initChannel()方法，会在连接被注册后立刻执行）；最后将端口号绑定到8888
            ChannelFuture channelFuture =serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new MyNettyServerInitializer()).bind(8888).sync() ;
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
