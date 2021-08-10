package com.yanqun.grpc;

import com.yanqun.grpc.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class MyGRPCClient {
    public static void main(String[] args) throws Exception {
        ManagedChannel client = ManagedChannelBuilder.forAddress("127.0.0.1", 8888)
                .usePlaintext().build();
        //try {
           StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc
                    .newBlockingStub(client);

               // 请求Request，响应Response
            //创建一个客户端的代理对象，用于代表客户端去访问服务端提供的方法
            //调用服务端提供的方法，查询id为1的姓名
            MyResponseName responseName = stub.queryStudentNameById(MyRequestId.newBuilder()
                    .setId(1).build());
            System.out.println(responseName.getName());


            /* 请求Stream，响应Stream
          Iterator<MyResponseStudentsStream> students =   stub.queryStudentsByCourseName( MyRequestCourseName.newBuilder().setCourseName("java").build() ) ;
          while(students.hasNext()){
              MyResponseStudentsStream student = students.next();
              System.out.println(student.getId()+"\t"+student.getName()+"\t"+student.getCourseName());
          }

        } finally {
            client.shutdown();
        }
        */
        //在grpc中，如果是以Stream方式发出请求，则此请求是异步的。因此，不能再使用阻塞式stub对象。
//        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc
//                .newStub(client);

        /*请求stream，响应response对象
        //接收服务端返回的StreamObserver类型的响应结果
        StreamObserver<MyResponseStudents> students = new StreamObserver<MyResponseStudents>() {
            @Override
            public void onNext(MyResponseStudents value) {
                value.getStudentsList().forEach((student) ->{
                    System.out.println(student.getId()+"\t"+student.getName()+"\t"+student.getCourseName());
                });
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("查询结束");
            }
        };

        //准备一个StreamObserver流，用于向服务端发送请求
        StreamObserver<MyRequestCourseName>  myRequestObserver = stub.queryStudentsByCourseName2(students);
        myRequestObserver.onNext( MyRequestCourseName.newBuilder().setCourseName("java").build());
        // 如果是向服务端发出多个Stream请求，则可以写多个onNext()，如下
        // myRequestObserver.onNext( MyRequestCourseName.newBuilder().setCourseName("python").build());
        myRequestObserver.onCompleted();
        //因为请求是异步的，所以客户端在发出请求后不会立刻得到响应结果。本程序通过休眠来模拟等待服务端的执行过程。
        Thread.sleep(3000);
        client.shutdown();
        */


         /*请求一个Stream，响应一个Stream
         StreamObserver<MyRequestId> requestIdObserver = stub.queryStudentNameById2(new StreamObserver<MyResponseName>() {
            @Override
            public void onNext(MyResponseName value) {
                System.out.println("接收到的响应："+value.getName());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("查询结束");
            }
        });

        requestIdObserver.onNext( MyRequestId.newBuilder().setId(1).build());
        requestIdObserver.onCompleted();
        Thread.sleep(3000);
        client.shutdown();

        */
    }
}
