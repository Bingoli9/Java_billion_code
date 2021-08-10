package com.yanqun.grpc;

import com.yanqun.grpc.proto.*;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void queryStudentNameById(MyRequestId request, StreamObserver<MyResponseName> responseObserver) {
        System.out.println("模拟查询此id的用户名：" + request.getId());
        //假设此id的name是“zs”
        responseObserver.onNext(MyResponseName.newBuilder().setName("zs").build());
        responseObserver.onCompleted();
    }
    //通过Stream的方式响应客户端
    @Override
    public void queryStudentsByCourseName(MyRequestCourseName request, StreamObserver<MyResponseStudentsStream> responseObserver) {
        //接收到的courseName是"java"
        String courseName = request.getCourseName();
        //假设有3个Student选修了"java"课程
        MyResponseStudentsStream student1 = MyResponseStudentsStream.newBuilder().setId(1).setName("zs").setCourseName("java").build();
        MyResponseStudentsStream student2 = MyResponseStudentsStream.newBuilder().setId(2).setName("ls").setCourseName("java").build();
        MyResponseStudentsStream student3 = MyResponseStudentsStream.newBuilder().setId(3).setName("ww").setCourseName("java").build();
        //将查询到的3个Student，放入responseObserver中
        responseObserver.onNext(student1);
        responseObserver.onNext(student2);
        responseObserver.onNext(student3);

        responseObserver.onCompleted();
    }

    //向客户端返回一个StreamObserver对象
    @Override
        public StreamObserver<MyRequestCourseName> queryStudentsByCourseName2(StreamObserver<MyResponseStudents> responseObserver) {
        MyStreamObserver observer = new MyStreamObserver();
        observer.setResponseObserver(responseObserver);

        return observer;
    }

    @Override
    public StreamObserver<MyRequestId> queryStudentNameById2(StreamObserver<MyResponseName> responseObserver) {
        MyStreamObserver2 observer = new MyStreamObserver2();
        observer.setResponseObserver(responseObserver);
        return observer;
    }

    class MyStreamObserver2 implements StreamObserver<MyRequestId> {
        private StreamObserver<MyResponseName> responseObserver;
        private MyResponseName responseStudentName;

        public void setResponseObserver(StreamObserver<MyResponseName> responseObserver) {
            this.responseObserver = responseObserver;
        }

        @Override
        public void onNext(MyRequestId value) {
            System.out.println("接收到的请求参数是：" + value.getId());
            //假设查到的结果是“zs”
            this.responseStudentName = MyResponseName.newBuilder().setName("zs").build();
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onCompleted() {
            responseObserver.onNext(responseStudentName);
            responseObserver.onCompleted();
        }
    }

    class MyStreamObserver implements StreamObserver<MyRequestCourseName> {
        private StreamObserver<MyResponseStudents> responseObserver;
        private MyResponseStudents responseStudents;

        public void setResponseObserver(StreamObserver<MyResponseStudents> responseObserver) {
            this.responseObserver = responseObserver;
        }

        @Override
        public void onNext(MyRequestCourseName value) {
            System.out.println("接收到的请求参数是：" + value.getCourseName());
            //根据value.getCourseName()模拟查询操作...
            MyStudent student1 = MyStudent.newBuilder().setId(1).setName("zs").setCourseName("java").build();
            MyStudent student2 = MyStudent.newBuilder().setId(2).setName("ls").setCourseName("java").build();
            //将查询结果放入responseStudents中
            this.responseStudents = MyResponseStudents.newBuilder().addStudents(student1).addStudents(student2).build();
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onCompleted() {
            //从将查询结果放入responseStudents中获取查询到的结果，以Stream的方式返回给客户端
            responseObserver.onNext(responseStudents);
            responseObserver.onCompleted();
        }
    }
}