package com.yanqun.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

public class NIOCoder {
    public static void test3() throws IOException {
        FileChannel inChannel
                = FileChannel.open(Paths.get("d:\\abc.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:\\abc2.txt"),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());


        Charset asciiCharset = Charset.forName("iso-8859-1");
        CharsetEncoder charsetEncoder = asciiCharset.newEncoder();
        CharsetDecoder charsetDecoder = asciiCharset.newDecoder();

        //用iso-8859-1对abc.txt中的字符进行解码
        CharBuffer decodeResult = charsetDecoder.decode(inMappedBuf);
        //iso-8859-1本身不能存储中文，因此会出现乱码
        for (int i = 0; i < decodeResult.length(); i++) {
            System.out.print(decodeResult.get(i) +"\t");
        }
        //用iso-8859-1对解码后的字符，再次进行编码
        ByteBuffer encodeBuffer = charsetEncoder.encode(decodeResult);
        outMappedBuf.put(encodeBuffer);
        inChannel.close();
        outChannel.close();
    }


    public static void test2() throws CharacterCodingException {
            ByteBuffer buffer = ByteBuffer.allocate(32);
            buffer.putShort((short) 10);
            buffer.putInt(100);
            buffer.putLong(1000);
            buffer.putFloat(3.14f);
            buffer.putDouble(3.14159);
            buffer.putChar('颜');

            buffer.flip();
            //必须保证get和put的顺序一致
            System.out.println(buffer.getShort());
            System.out.println(buffer.getInt());
            System.out.println(buffer.getLong());
            System.out.println(buffer.getFloat());
            System.out.println(buffer.getDouble());
            System.out.println(buffer.getChar());
    }
    public static void test21() throws CharacterCodingException {
        Charset charset = Charset.forName("utf-8");
        //获取utf-8编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();
        //获取utf-8解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();
        //后续存储的“颜群JAVA讲师”共16字节：UTF中，每个常见汉字占3个字节；每个英文占1个字节。
        CharBuffer charBuffer = CharBuffer.allocate(16);
        charBuffer.put("颜群JAVA讲师");
        charBuffer.flip();

        System.out.println("编码开始");
        //编码  ：String->byte[]，使用utf-8类型的编码器
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);
        for (int i = 0; i < 16; i++) {//一个汉字占3个字节
            System.out.print(byteBuffer.get() + "\t");
        }
        System.out.println("\n编码结束\n");


        byteBuffer.flip();
        //解码：二进制->String，也使用utf-8类型的编码器
        System.out.println("---解码开始1---");
        charBuffer = charsetDecoder.decode(byteBuffer);
        System.out.println(charBuffer.toString());
        System.out.println("---解码结束1---\n");

        //byteBuffer中存储的是经过UTF-8类型编码器编码后的字节码，但以下却是使用gbk类型的解码器解码，因此会出现乱码
        Charset charset2 = Charset.forName("gbk");
        byteBuffer.flip();
        System.out.println("---解码开始2---");
        charBuffer = charset2.decode(byteBuffer);
        System.out.println(charBuffer.toString());
        System.out.println("---解码结束2---\n");
    }

    public static void test1(){
        System.out.println("当前环境默认的编码类型："+Charset.defaultCharset());
        Charset.forName("utf-8") ;

        Set<Map.Entry<String, Charset>> entries = Charset.availableCharsets().entrySet();
        System.out.println("当前jdk共支持编码类型数：" +entries.size());

        System.out.println("当前环境支持的所有编码类型：");
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println("key:"+entry.getKey()+"\tvalue:"+entry.getValue());
        }
    }

//    {
//         try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\abc.txt"), "utf-8"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }



}
