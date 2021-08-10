package com.yanqun.netty.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyNettyClientTest {
    public static void main(String[] args) {
        //服务端有2个EventLoopGroup，bossGroup用于获取连接并将连接分发给workerGroup；而workerGroup负责真正的处理连接；但客户端仅仅需要连接服务端（相当于服务端的bossGroup），因此只需要一个EventLoopGroup
        EventLoopGroup eventLoopGroup =  new NioEventLoopGroup() ;
        try{
            Bootstrap bootstrap = new Bootstrap();
            /*
                注意：下条语句用到了handler()，但在服务端MyNettyServerTest中用到的是childHandler()，二者的区别如下：
                 bossGroup获取并分发连接：使用handler()
                 workerGroup实际处理连接：用childHandler()
             */
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyNettyClientInitializer());
            ChannelFuture channelFuture =  bootstrap.connect("127.0.0.1",8888).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}