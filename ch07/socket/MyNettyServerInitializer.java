package com.yanqun.netty.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class MyNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    //连接被注册后，立刻执行此方法
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //LengthFieldBasedFrameDecoder：用于解析带固定长度数据包。TCP发送的数据规则：可以将数据进行拆分或合并，因此对端接收到的数据包可能不是发送时的格式；一般的做法是在包头设置length字段，指明包长度，再由接受方根据length拼接或者剪裁收到的数据，从而形成完整的数据包
        pipeline.addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,8,0,8));
        //将上条语句的length加入到传递的数据中
        pipeline.addLast("LengthFieldPrepender", new LengthFieldPrepender(8));
        //传递字符串的编码解码器
        pipeline.addLast("StringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("StringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        //自定义处理器
        pipeline.addLast("MyNettyServerHandler", new MyNettyServerHandler());
    }
}
