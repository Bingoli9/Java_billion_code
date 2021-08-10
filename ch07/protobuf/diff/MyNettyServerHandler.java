package com.yanqun.protobuf.diff;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
//注意，此时SimpleChannelInboundHandler的泛型是MessageData类型
public class MyNettyServerHandler extends SimpleChannelInboundHandler<MyMessage.MessageData> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage.MessageData receiveMsg) throws Exception {
        MyMessage.MessageData.MessageType messageType = receiveMsg.getMessageType() ;
        //判断
        if(messageType == MyMessage.MessageData.MessageType.PersonType){
            MyMessage.PersonData person = receiveMsg.getPerson() ;
            System.out.println(person.getPname()+"--" + person.getPage());
        }else{
            MyMessage.DogData dog = receiveMsg.getDog() ;
            System.out.println(dog.getDname()+"--" + dog.getDcolor());
        }
    }
}

