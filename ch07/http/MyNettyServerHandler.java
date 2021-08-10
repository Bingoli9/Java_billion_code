package com.yanqun.netty.http;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.HttpRetryException;
import java.net.URI;

//自定义处理器：用于输出hello netty
public class MyNettyServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    //channelRead0()方法：接收客户端请求，并且作出响应；类似于Servlet中的doGet()、doPost()等方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest)msg;
            URI uri = new URI(httpRequest.uri());
            if(!"/favicon.ico".equals( uri.getPath())) {
                System.out.println("channelRead0 invoke...");
                //ByteBuf对象：定义响应的内容
                ByteBuf content = Unpooled.copiedBuffer("Hello Netty", CharsetUtil.UTF_8);
                //FullHttpResponse对象：响应对象，定义响应的具体信息
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                //content.readableBytes() ：响应内容的长度
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
                //将响应 返回给客户端
                ctx.writeAndFlush(response);
            }
        }
    }
    //当增加新的处理器时，触发此方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("1.handlerAdded(),增加了新的处理器..." );
        super.handlerAdded(ctx);
    }
    //当通道被注册到一个事件循环组EventLoop上时，执行此方法
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("2.channelRegistered(),通道被注册...");
        super.channelRegistered(ctx);
    }
    //当通道处于活跃状态（连接到某个远端，可以收发数据）时，执行此方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("3.channelActive(),通道连接到了远端，处于活跃状态...");
        super.channelActive(ctx);
    }
    //当通道处于非活跃状态（与远端断开了连接）时，执行此方法
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("4.channelInactive(),通道远端断开了连接，处于非活跃状态... ");
        super.channelInactive(ctx);
    }
    //当通道被取消注册时，执行此方法
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("5.channelUnregistered(),通道被取消了注册...");
        super.channelUnregistered(ctx);
    }
    //当程序发生异常时，执行此方法
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

