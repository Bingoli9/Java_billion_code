package com.yanqun.thrift;

import com.yanqun.thrift.generatecode.Student;
import com.yanqun.thrift.generatecode.StudentService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class TestThriftClient {
    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("127.0.0.1",8888),1000) ;
        TProtocol protocol =  new TBinaryProtocol(transport) ;
        //创建用于访问服务端的对象
        StudentService.Client client =  new StudentService.Client(protocol) ;
        try{
            //与服务端建立连接
            transport.open();
            System.out.println("RPC调用服务端的queryStudents()方法");
            List<Student> Students = client.queryStudents() ;
            for(Student Student:Students){
                System.out.println(Student.getName()+"\t"+Student.getAge());
            }
            System.out.println("RPC调用服务端的addStudent()方法");
            boolean result = client.addStudent("ww",25) ;
            if(result){
                System.out.println("增加成功！");
            }else{
                System.out.println("增加失败！");
            }
        }catch (TException e){
            e.printStackTrace();
        }finally {
            transport.close();
        }
    }
}
