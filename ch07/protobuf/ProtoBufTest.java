package com.yanqun.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author yanqun
 * @create 2018/9/4  19:06
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        StudentMessage.Student Student = StudentMessage.Student.newBuilder().setName("zs").setAge(23).build() ;
        //将对象序列化为byte数组
        byte[]  StudentBytes = Student.toByteArray() ;
        StudentMessage.Student per = StudentMessage.Student.parseFrom(StudentBytes) ;
        System.out.println(per.getName()+"--"+per.getAge());
    }
}
