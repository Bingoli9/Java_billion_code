package com.yanqun.netty.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.RandomAccessFile;

public class MyNettyClientHandler extends SimpleChannelInboundHandler {
    private  int readLength;
    private  int start = 0;
    private  int lastLength = 0;
    public RandomAccessFile randomAccessFile;
    private MySendFile sendFile;


    public MyNettyClientHandler(MySendFile ef) {
        this.sendFile = ef;
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("【客户端】文件发送完毕");
    }

    public void channelActive(ChannelHandlerContext ctx) {
        try {
            randomAccessFile = new RandomAccessFile(sendFile.getFile(),
                    "r");
            randomAccessFile.seek(sendFile.getStart());
            lastLength = 1024 * 1024;
            byte[] bytes = new byte[lastLength];
            if ((readLength = randomAccessFile.read(bytes)) != -1) {
                sendFile.setEnd(readLength);
                sendFile.setBytes(bytes);
                ctx.writeAndFlush(sendFile);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
       if (msg instanceof Integer) {
            start = (Integer) msg;
            if (start != -1) {
                randomAccessFile = new RandomAccessFile(
                        sendFile.getFile(), "r");
                randomAccessFile.seek(start);
                int length = (int) (randomAccessFile.length() - start);
                if (length < lastLength) {
                    lastLength = length;
                }
                byte[] bytes = new byte[lastLength];
                if ((readLength = randomAccessFile.read(bytes)) != -1
                        && (randomAccessFile.length() - start) > 0) {
                    sendFile.setEnd(readLength);
                    sendFile.setBytes(bytes);
                    try {
                        ctx.writeAndFlush(sendFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    randomAccessFile.close();
                    ctx.close();
                    System.out.println("本地文件准备完毕" );
                }
            }
        }
    }


    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
