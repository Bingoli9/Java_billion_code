package com.yanqun.protobuf;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyNettyServerHandler extends SimpleChannelInboundHandler<StudentMessage.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentMessage.Student receiveMsg) throws Exception {
        System.out.println(receiveMsg.getName()+"--" + receiveMsg.getAge());
    }
}

