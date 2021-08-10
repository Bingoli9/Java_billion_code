package com.yanqun.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NIODemo02 {
    //通道之间的数据传输(直接缓冲区)
    public static void test3() throws IOException{
        FileChannel inChannel = FileChannel.open(Paths.get("d:\\《MapReduce》实验指导书及评分标准.docx"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:/x.docx"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        inChannel.transferTo(0, inChannel.size(), outChannel);
        //	 outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }


    public static void test4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("D:\\abc.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        //mappedByteBuffer:代表了abc.txt在内存中的映射文件
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
        mappedByteBuffer.put(1, (byte) 'X');
        //只需关心内存中的数据
        mappedByteBuffer.put(3, (byte) 'Y');
        raf.close();
    }

    //MappedByteBuffer
    public static  void test10() throws IOException{
        FileChannel inChannel
                = FileChannel.open(Paths.get("d:\\《MapReduce》实验指导书及评分标准.docx"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:/c.docx"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inChannel.close();
        outChannel.close();
    }
    /*
      使用多个车厢解决(buffer)
     分散(Scatter)与聚集(Gather)
     （Scattering Reads）：
     （Gathering Writes）：
    */
    public static void test9() throws IOException{
        //其他的读取文件的方式
        RandomAccessFile raf1 = new RandomAccessFile("d:\\hello.txt", "rw");//r读  w写  rw读写

        //1. 获取通道
        FileChannel channel1 = raf1.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);//100byte
        ByteBuffer buf2 = ByteBuffer.allocate(1024*1024);//1mb

        //3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        //将文件 读入到2个缓冲区中
        channel1.read(bufs);

//        for(int i=0;i<bufs.length;i++){
//            bufs[i].flip() ;
//        }
        //foreach，增强for循环
        //for(元素类型   x :集合或数组)  ，其中x代表 数组中的每个元素
       for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }
        //System.out.println(  new String(bufs[0])    );
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("-----------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //4. 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("d:/world.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);
    }

    public static void test5(){//选学
        //map (key-value)                key: zs,  value: 身高  体重 年龄 姓名
        Map<String, Charset> map = Charset.availableCharsets();//获取计算机支持的编码类型

        Set<Entry<String, Charset>> set = map.entrySet();
        for (Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }





    public static void test6() throws IOException{

        Charset cs1 = Charset.forName("gbk");
        //获取编码器
        CharsetEncoder charsetEncoder = cs1.newEncoder();
        //获取解码器
        CharsetDecoder charsetDecoder = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(4);
        cBuf.put("一些汉字");//一些汉字  1010101010101010
        cBuf.flip();

        System.out.println("编码开始");
        //编码  ：String->二进制
        ByteBuffer bBuf = charsetEncoder.encode(cBuf);

        for (int i = 0; i < 8; i++) {//gbk编码格式下，一个汉字占2个字节
            System.out.println(bBuf.get());
        }
        System.out.println("编码结束");
        //解码：二进制->String
        bBuf.flip();

        System.out.println("---解码开始1---");
        CharBuffer cBuf2 = charsetDecoder.decode(bBuf);
        System.out.println(cBuf2.toString());
        System.out.println("---解码结束1---");
//
//        //编码器 和解码器 必须使用相同的字符集，否则乱码
//        Charset cs2 = Charset.forName("UTF-8");
//        bBuf.flip();
//        System.out.println("---解码开始2---");
//        CharBuffer cBuf3 = cs2.decode(bBuf);
//        System.out.println(cBuf3.toString());
//        System.out.println("---解码结束2---");
    }

    //byte可以存放任何类型
    public static void test7() throws IOException{
        //ctrl+alt+v   ctrl+alt+f
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.putShort((short)10);
        buffer.putInt(100);
        buffer.putLong(1000);
        buffer.putFloat(3.14f);
        buffer.putDouble(3.14159);
        buffer.putChar('颜');

        buffer.flip() ;
        //必须保证get和put的顺序一致
        System.out.println(buffer.getShort());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getFloat());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
    }

    //
    public static void test8() throws IOException{

    }




}
