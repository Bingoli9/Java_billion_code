package com.yanqun.netty.file;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class MyNettyClientInitializer  extends ChannelInitializer<SocketChannel> {
    MySendFile sendFile;

    public MyNettyClientInitializer(MySendFile fileUploadFile){
        this.sendFile = fileUploadFile;
    }

    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast(new ObjectEncoder()) ;
        pipeline.addLast(new ObjectDecoder(
                ClassResolvers
                        .weakCachingConcurrentResolver(null)));
        //自定义处理器
        pipeline.addLast( new MyNettyClientHandler(sendFile));
    }
}
