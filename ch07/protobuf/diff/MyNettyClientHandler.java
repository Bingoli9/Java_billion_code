package com.yanqun.protobuf.diff;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyNettyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String receiveMsg) {
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //如果num=1，向服务端发送person对象，否则发送dog对象
        int num = 1 ;
        MyMessage.MessageData message = null;
        MyMessage.MessageData.Builder messageBuilder = MyMessage.MessageData.newBuilder();
        MyMessage.PersonData.Builder personBuilder =MyMessage.PersonData.newBuilder() ;
        MyMessage.DogData.Builder dogBuilder =MyMessage.DogData.newBuilder() ;
        if(num == 1) {
            //构建person对象
            MyMessage.PersonData person = personBuilder.setPname("zs").setPage(23).build();
            message = messageBuilder.setMessageType(MyMessage.MessageData.MessageType.PersonType).setPerson(person).build();
        }else{
            //构建dog对象
            MyMessage.DogData dog = dogBuilder.setDname("wc").setDcolor("red").build() ;
            message = messageBuilder.setMessageType(MyMessage.MessageData.MessageType.DogType).setDog(dog).build();
        }
        ctx.channel().writeAndFlush(message) ;
    }
}
