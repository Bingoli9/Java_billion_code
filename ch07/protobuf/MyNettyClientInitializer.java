package com.yanqun.protobuf;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyNettyClientInitializer  extends ChannelInitializer<SocketChannel> {
    //连接被注册后，立刻执行此方法
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        /*思考：如何传递任何类型的数据呢？不要固定成PersonData.Person
            1.使用netty自定义协议：前几位编码，如果是a 则解码成....如果是b，则解码成...
            b.使用protobuf解决：
        */
//        pipeline.addLast("ProtobufDecoder",new ProtobufDecoder(PersonMessage.Person.getDefaultInstance()));//解码：字节->对象
        //PProtobufVarint32FrameDecoder和rotobufVarint32LengthFieldPrepender用于解决半包和粘包问题，这里仅做了解
        pipeline.addLast("ProtobufVarint32FrameDecoder",new ProtobufVarint32FrameDecoder()) ;
        pipeline.addLast("ProtobufVarint32LengthFieldPrepender",new ProtobufVarint32LengthFieldPrepender());
        //用于将PersonMessage类转为字节码
        pipeline.addLast("ProtobufEncoder",new ProtobufEncoder());
        //构建Person对象，并发送给服务端
        pipeline.addLast("MyNettyClientHandler", new MyNettyClientHandler());
    }
}
