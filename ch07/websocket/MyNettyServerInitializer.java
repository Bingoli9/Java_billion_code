package com.yanqun.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class MyNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast("HttpServerCodec",new HttpServerCodec()) ;
        /*
          HttpObjectAggregator：把多个HttpMessage组装成一个完整的Http请求（FullHttpRequest）或者响应（FullHttpResponse）。
          如果自定义处理器是“Inbound”，则表示请求；
          如果是Outbound，就表示响应。
         */
        pipeline.addLast("HttpObjectAggregator",new HttpObjectAggregator(4096)) ;
        //处理websocket的netty处理器，可以通过构造方法绑定webSocket的服务端地址
        pipeline.addLast("WebSocketServerProtocolHandler",new WebSocketServerProtocolHandler("/myWebSocket")) ;
        //自定义处理器
        pipeline.addLast("MyNettyServerHandler", new MyNettyServerHandler());
    }
}
