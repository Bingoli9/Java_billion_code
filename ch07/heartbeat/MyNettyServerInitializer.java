package com.yanqun.netty.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class MyNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        /*
        IdleStateHandler：心跳机制处理器，主要用来检测远端是否读写超时，如果超时则将超时事件传入到userEventTriggered(ctx,evt)方法的evt参数中
        IdleStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds,int allIdleTimeSeconds,TimeUnit unit)的参数含义如下：
            readerIdleTimeSeconds：如果指定时间内没有检测到远端的读操作，则会触发形成IdleState.READER_IDLE(读空闲)状态；
            writerIdleTimeSeconds：如果指定时间内没有检测到远端的写操作，则会触发形成IdleState.WRITER_IDLE(写空闲)状态；
            allIdleTimeSeconds：如果指定时间内没有检测到远端的读和写操作，则会触发形成IdleState.ALL_IDLE(读写空闲)状态；
            unit:时间单位（默认：秒）
        */
        pipeline.addLast("IdleStateHandler", new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
        //自定义处理器
        pipeline.addLast("MyNettyServerHandler", new MyNettyServerHandler());
    }
}
