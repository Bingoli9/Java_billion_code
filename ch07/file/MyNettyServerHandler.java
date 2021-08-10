package com.yanqun.netty.file;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

public class MyNettyServerHandler extends SimpleChannelInboundHandler {
    private int readLenth;
    private  int start = 0;
    private String file_dir = "e:/upload";
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MySendFile) {
            MySendFile sendFile = (MySendFile) msg;
            byte[] bytes = sendFile.getBytes();
            readLenth = sendFile.getEnd();
            String fileName = sendFile.getFileName();
            String path = file_dir + File.separator + fileName;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(start);
            randomAccessFile.write(bytes);
            start = start + readLenth;
            if (readLenth > 0) {
                ctx.writeAndFlush(start);
                randomAccessFile.close();
            } else {
                ctx.flush();
                ctx.close();
            }

        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ctx.flush();
        ctx.close();
    }
}

