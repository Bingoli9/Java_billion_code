// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.yanqun.grpc.proto;

public interface MyResponseStudentsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.yanqun.grpc.proto.MyResponseStudents)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *服务端的响应结果是集合类型，因此需要加上repeated
   * </pre>
   *
   * <code>repeated .com.yanqun.grpc.proto.MyStudent students = 1;</code>
   */
  java.util.List<com.yanqun.grpc.proto.MyStudent> 
      getStudentsList();
  /**
   * <pre>
   *服务端的响应结果是集合类型，因此需要加上repeated
   * </pre>
   *
   * <code>repeated .com.yanqun.grpc.proto.MyStudent students = 1;</code>
   */
  com.yanqun.grpc.proto.MyStudent getStudents(int index);
  /**
   * <pre>
   *服务端的响应结果是集合类型，因此需要加上repeated
   * </pre>
   *
   * <code>repeated .com.yanqun.grpc.proto.MyStudent students = 1;</code>
   */
  int getStudentsCount();
  /**
   * <pre>
   *服务端的响应结果是集合类型，因此需要加上repeated
   * </pre>
   *
   * <code>repeated .com.yanqun.grpc.proto.MyStudent students = 1;</code>
   */
  java.util.List<? extends com.yanqun.grpc.proto.MyStudentOrBuilder> 
      getStudentsOrBuilderList();
  /**
   * <pre>
   *服务端的响应结果是集合类型，因此需要加上repeated
   * </pre>
   *
   * <code>repeated .com.yanqun.grpc.proto.MyStudent students = 1;</code>
   */
  com.yanqun.grpc.proto.MyStudentOrBuilder getStudentsOrBuilder(
      int index);
}