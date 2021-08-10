package com.yanqun.netty.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

public class MyNettyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String receiveMsg) {
        System.out.println("【客户端】接收的请求来自：" + ctx.channel().remoteAddress()+",消息内容【"+receiveMsg+"】");
        System.out.println("请向【客户端】发送一条消息：");
        String sendMsg = new Scanner(System.in).nextLine() ;
        ctx.channel().writeAndFlush(sendMsg) ;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("打破僵局的第一条消息...");
    }
}
