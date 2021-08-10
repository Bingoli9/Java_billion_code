package com.yanqun.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyNettyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String receiveMsg) {
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StudentMessage.Student Student =  StudentMessage.Student.newBuilder().setName("zs").setAge(23).build() ;
        //发送给服务单
        ctx.channel().writeAndFlush(Student) ;
    }
}
