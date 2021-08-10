package com.yanqun.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class NIODemo {
    public static void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(100) ;
        System.out.println("---allocate----");
        System.out.println("position："+buffer.position());
        System.out.println("limit："+buffer.limit());
        System.out.println("capacity（定义之后，不会再改变）："+buffer.capacity());

        //向Buffer中存放数据
        System.out.println("---put()----");
        buffer.put("helloworld".getBytes()) ;
        System.out.println("position："+buffer.position());
        System.out.println("limit："+buffer.limit());

        //切换到读模式
        System.out.println("---flip()----");
        buffer.flip();
        System.out.println("position："+buffer.position());
        System.out.println("limit："+buffer.limit());

        //从Buffer中读取数据
        System.out.println("---get()----");
        byte[] bs = new byte[buffer.limit()];
        buffer.get(bs);//读取数据，同时会后移position
        System.out.println("读取到的数据："+ new String(bs)  );
        System.out.println("position：" + buffer.position());
        System.out.println("limit：" + buffer.limit());


        System.out.println("----slice()---");
        buffer = ByteBuffer.allocate(8);
        //buffer：0,1,2,3,4,5,6,7
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        buffer.position(2);
        buffer.limit(6);
        //sliceBuffer：2,3,4,5；获取从position到limit之间buffer的引用。
        ByteBuffer sliceBuffer = buffer.slice();
        //sliceBuffer与原Buffer共享相同的数据；即修改sliceBuffer中的数据时，buffer也会改变。
        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            b += 100 ;
            sliceBuffer.put(i,b);
        }
        //测试
        System.out.println("当修改了sliceBuffer之后，查看buffer：");
        buffer.position(0) ;
        buffer.limit(buffer.capacity());
        while (buffer.hasRemaining()) {//{x,x,x,x,x,x}  buffer.hasRemaining()：判断是否有剩余元素
            System.out.print( buffer.get() +",");
        }
        System.out.println();

        System.out.println("----mark--------");
        ByteBuffer buffer2 = ByteBuffer.allocate(100) ;
        buffer2.put("abcdefg".getBytes()) ;

        //在此时的position位置处，做一个标记mark
        buffer2.mark() ;
        System.out.println("position：" + buffer2.position());
        System.out.println("mark：" + buffer2.mark().position());
        /*
         通过get(byte[] dst, int offset, int length)方法，读取buffer中的“cde”。
         注意，此方法可以直接从Buffer中的指定位置offset开始读取数据，而不需要flip()或rewind()。
        */
        buffer2.get(bs,2,3) ;
        buffer2.reset();//恢复到mark的位置 2
        System.out.println("position：" + buffer2.position());
        System.out.println("mark：" + buffer2.mark().position());

        //判断缓冲区是否有剩余数据
        if(buffer2.hasRemaining()) {
            System.out.println("Buffer中的剩余空间数：" +buffer2.remaining()   );
        }

        //重复读rewind() : 1.postion=0，2.取消mark()
        System.out.println("---rewind()----");
        buffer2.rewind() ;
        System.out.println("position:"+buffer2.position());

        //clear()"清空"缓冲区
        System.out.println("-------clear()--------");
        ByteBuffer buffer3 = ByteBuffer.allocate(100) ;
        buffer3.put("abc".getBytes()) ;
        buffer3.clear() ;//"清空"缓冲区 ：position=0，但数据并没有真正被删除，只是处于废弃状态
        System.out.println("position:"+buffer3.position());
        System.out.println("limit:"+buffer3.limit());
        System.out.println( "clear()之后，仍然可以获取到Buffer中的数据："+(char)buffer3.get(1)  );
    }

    //使用非直接缓冲区复制文件
    public static void test2() throws IOException{
        long start = System.currentTimeMillis();
        FileInputStream input= new FileInputStream("e:\\JDK_API.CHM");
        FileOutputStream out= new FileOutputStream("e:\\JDK_API_COPY.CHM");
        //获取通道
        FileChannel inChannel = input.getChannel() ;
        FileChannel outChannel =  out.getChannel() ;
        ByteBuffer buffer = ByteBuffer.allocate(1024) ;

        while(inChannel.read(buffer) != -1){
            buffer.flip() ;
            outChannel.write(buffer );
            buffer.clear() ;
        }
        if(outChannel!=null) outChannel.close();
        if(inChannel!=null) inChannel.close();
        if(out!=null) out.close();
        if(input!=null) input.close();
        long end = System.currentTimeMillis();
        System.out.println("复制操作消耗的时间（毫秒）："+(end-start));
    }

    //使用直接缓冲区复制文件
    public static void test2_2() throws IOException{
        long start = System.currentTimeMillis();
        FileInputStream input= new FileInputStream("e:\\JDK_API.CHM");
        FileOutputStream out= new FileOutputStream("e:\\JDK_API_COPY.CHM");
        //获取通道
        FileChannel inChannel = input.getChannel() ;
        FileChannel outChannel =  out.getChannel() ;
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024) ;

        while(inChannel.read(buffer) != -1){
            buffer.flip() ;
            outChannel.write(buffer );
            buffer.clear() ;
        }
        if(outChannel!=null) outChannel.close();
        if(inChannel!=null) inChannel.close();
        if(out!=null) out.close();
        if(input!=null) input.close();
        long end = System.currentTimeMillis();
        System.out.println("复制操作消耗的时间（毫秒）："+(end-start));
    }


    public static  void test3() throws IOException{
        long start = System.currentTimeMillis();
        //用文件的输入通道
        FileChannel inChannel
                = FileChannel.open(Paths.get("e:\\JDK_API.CHM"), StandardOpenOption.READ);
        //用文件的输出通道
        FileChannel outChannel = FileChannel.open(Paths.get("e:\\JDK_API2.CHM"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        //输入通道和输出通道之间的内存映射文件（内存映射文件处于堆外内存中）
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        //直接对内存映射文件进行读写
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("复制操作消耗的时间（毫秒）："+(end-start));
    }
    //在直接缓冲区中，将输入通道的数据直接转发给输出通道
    public static void test4() throws IOException{
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("e:\\JDK_API.CHM"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("e:\\JDK_API.CHM3"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        inChannel.transferTo(0, inChannel.size(), outChannel);
        /*
         也可以使用输出通道完成复制，即上条语句等价于以下写法：
         outChannel.transferFrom(inChannel, 0, inChannel.size());
        */
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("复制操作消耗的时间（毫秒）："+(end-start));
    }

    public static void testPipe() throws IOException{
        //创建管道
        Pipe pipe = Pipe.open();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //通过SinkChannel，向Pipe中写数据
        Pipe.SinkChannel  sinkChannel = pipe.sink();
        buf.put("helloworld".getBytes());
        buf.flip();
        sinkChannel.write(buf);
        // 通过SourceChannel，从Pipe中读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        int len = sourceChannel.read(buf);
        System.out.println( new String(buf.array(),0,len));
        sourceChannel.close();
        sinkChannel.close();
    }

	public static void test5() {
		//分配直接缓冲区
		ByteBuffer buf = ByteBuffer.allocateDirect(1024) ;
		System.out.println(   buf.isDirect()  );
	}

	public static void main(String[] args) throws IOException {
		test1() ;
	}
}
