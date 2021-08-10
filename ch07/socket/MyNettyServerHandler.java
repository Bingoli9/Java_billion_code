package com.yanqun.netty.socket;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.Scanner;

public class MyNettyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String receiveMsg) throws Exception {
        //通过ctx获取远程（客户端）的端口号，并打印出对方（客户端）发来的消息
        System.out.println("【服务端】接收的请求来自：" + ctx.channel().remoteAddress()+",消息内容【"+receiveMsg+"】");
        System.out.println("请向【客户端】发送一条消息：");
        String sendMsg = new Scanner(System.in).nextLine() ;
        ctx.channel().writeAndFlush(sendMsg) ;
    }
}

