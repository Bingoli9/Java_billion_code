package com.yanqun.netty.heartbeat;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Scanner;
public class MyNettyServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
    }
    //如果IdleStateHandler检测到了超时事件，则会触发userEventTriggered方法
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null ;
            //获取超时事件：READER_IDLE、WRITER_IDLE或ALL_IDLE
            switch(event.state()){
                case READER_IDLE:
                    eventType = "读空闲" ;
                    break ;
                case WRITER_IDLE:
                    eventType = "写空闲" ;
                    break ;
                case ALL_IDLE:
                    eventType = "读写空闲" ;
                    break ;
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件：" +eventType);
            ctx.channel().close() ;
        }
    }
}

