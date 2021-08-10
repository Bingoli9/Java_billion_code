package com.yanqun.protobuf.diff;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyNettyClientInitializer  extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast("ProtobufVarint32FrameDecoder",new ProtobufVarint32FrameDecoder()) ;
        pipeline.addLast("ProtobufVarint32LengthFieldPrepender",new ProtobufVarint32LengthFieldPrepender());
        //用于将MessageData类转为字节码
        pipeline.addLast("ProtobufEncoder",new ProtobufEncoder());
        //构建Person对象，并发送给服务端
        pipeline.addLast("MyNettyClientHandler", new MyNettyClientHandler());
    }
}
