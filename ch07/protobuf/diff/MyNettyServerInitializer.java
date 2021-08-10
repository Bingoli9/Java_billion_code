package com.yanqun.protobuf.diff;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast("ProtobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
        //用于将byte[]MessageData
        pipeline.addLast("ProtobufDecoder", new ProtobufDecoder(MyMessage.MessageData.getDefaultInstance()));
        pipeline.addLast("ProtobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender());
        //MessageData
        pipeline.addLast("MyNettyServerHandler", new MyNettyServerHandler());
    }
}
