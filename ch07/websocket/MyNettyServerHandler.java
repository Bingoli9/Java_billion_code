package com.yanqun.netty.websocket;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;
import java.util.UUID;

//泛型TextWebSocketFrame：WebSocket处理的处理文本类型
public class MyNettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //接收WebSocket客户端发送来的数据（Websocket以frame的形式传递数据）
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        System.out.println("Server收到消息：" + frame.text());
        //向WebSocket客户端发送数据
        ctx.channel().writeAndFlush(new TextWebSocketFrame("helo client...") ) ;
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端加入：id=" + ctx.channel().id());
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端离开：id=" + ctx.channel().id());
    }
}

