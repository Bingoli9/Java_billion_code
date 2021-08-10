package com.yanqun.grpc.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *定义接口
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.yanqun.grpc.proto.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestId,
      com.yanqun.grpc.proto.MyResponseName> getQueryStudentNameByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryStudentNameById",
      requestType = com.yanqun.grpc.proto.MyRequestId.class,
      responseType = com.yanqun.grpc.proto.MyResponseName.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestId,
      com.yanqun.grpc.proto.MyResponseName> getQueryStudentNameByIdMethod() {
    io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestId, com.yanqun.grpc.proto.MyResponseName> getQueryStudentNameByIdMethod;
    if ((getQueryStudentNameByIdMethod = StudentServiceGrpc.getQueryStudentNameByIdMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getQueryStudentNameByIdMethod = StudentServiceGrpc.getQueryStudentNameByIdMethod) == null) {
          StudentServiceGrpc.getQueryStudentNameByIdMethod = getQueryStudentNameByIdMethod = 
              io.grpc.MethodDescriptor.<com.yanqun.grpc.proto.MyRequestId, com.yanqun.grpc.proto.MyResponseName>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.yanqun.grpc.proto.StudentService", "queryStudentNameById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyRequestId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyResponseName.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("queryStudentNameById"))
                  .build();
          }
        }
     }
     return getQueryStudentNameByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestCourseName,
      com.yanqun.grpc.proto.MyResponseStudentsStream> getQueryStudentsByCourseNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryStudentsByCourseName",
      requestType = com.yanqun.grpc.proto.MyRequestCourseName.class,
      responseType = com.yanqun.grpc.proto.MyResponseStudentsStream.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestCourseName,
      com.yanqun.grpc.proto.MyResponseStudentsStream> getQueryStudentsByCourseNameMethod() {
    io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestCourseName, com.yanqun.grpc.proto.MyResponseStudentsStream> getQueryStudentsByCourseNameMethod;
    if ((getQueryStudentsByCourseNameMethod = StudentServiceGrpc.getQueryStudentsByCourseNameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getQueryStudentsByCourseNameMethod = StudentServiceGrpc.getQueryStudentsByCourseNameMethod) == null) {
          StudentServiceGrpc.getQueryStudentsByCourseNameMethod = getQueryStudentsByCourseNameMethod = 
              io.grpc.MethodDescriptor.<com.yanqun.grpc.proto.MyRequestCourseName, com.yanqun.grpc.proto.MyResponseStudentsStream>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.yanqun.grpc.proto.StudentService", "queryStudentsByCourseName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyRequestCourseName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyResponseStudentsStream.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("queryStudentsByCourseName"))
                  .build();
          }
        }
     }
     return getQueryStudentsByCourseNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestCourseName,
      com.yanqun.grpc.proto.MyResponseStudents> getQueryStudentsByCourseName2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryStudentsByCourseName2",
      requestType = com.yanqun.grpc.proto.MyRequestCourseName.class,
      responseType = com.yanqun.grpc.proto.MyResponseStudents.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestCourseName,
      com.yanqun.grpc.proto.MyResponseStudents> getQueryStudentsByCourseName2Method() {
    io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestCourseName, com.yanqun.grpc.proto.MyResponseStudents> getQueryStudentsByCourseName2Method;
    if ((getQueryStudentsByCourseName2Method = StudentServiceGrpc.getQueryStudentsByCourseName2Method) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getQueryStudentsByCourseName2Method = StudentServiceGrpc.getQueryStudentsByCourseName2Method) == null) {
          StudentServiceGrpc.getQueryStudentsByCourseName2Method = getQueryStudentsByCourseName2Method = 
              io.grpc.MethodDescriptor.<com.yanqun.grpc.proto.MyRequestCourseName, com.yanqun.grpc.proto.MyResponseStudents>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.yanqun.grpc.proto.StudentService", "queryStudentsByCourseName2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyRequestCourseName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyResponseStudents.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("queryStudentsByCourseName2"))
                  .build();
          }
        }
     }
     return getQueryStudentsByCourseName2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestId,
      com.yanqun.grpc.proto.MyResponseName> getQueryStudentNameById2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryStudentNameById2",
      requestType = com.yanqun.grpc.proto.MyRequestId.class,
      responseType = com.yanqun.grpc.proto.MyResponseName.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestId,
      com.yanqun.grpc.proto.MyResponseName> getQueryStudentNameById2Method() {
    io.grpc.MethodDescriptor<com.yanqun.grpc.proto.MyRequestId, com.yanqun.grpc.proto.MyResponseName> getQueryStudentNameById2Method;
    if ((getQueryStudentNameById2Method = StudentServiceGrpc.getQueryStudentNameById2Method) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getQueryStudentNameById2Method = StudentServiceGrpc.getQueryStudentNameById2Method) == null) {
          StudentServiceGrpc.getQueryStudentNameById2Method = getQueryStudentNameById2Method = 
              io.grpc.MethodDescriptor.<com.yanqun.grpc.proto.MyRequestId, com.yanqun.grpc.proto.MyResponseName>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.yanqun.grpc.proto.StudentService", "queryStudentNameById2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyRequestId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yanqun.grpc.proto.MyResponseName.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("queryStudentNameById2"))
                  .build();
          }
        }
     }
     return getQueryStudentNameById2Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   * <pre>
   *定义接口
   * </pre>
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *请求一个Requset对象，响应一个Response对象
     * </pre>
     */
    public void queryStudentNameById(com.yanqun.grpc.proto.MyRequestId request,
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseName> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryStudentNameByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     *请求一个Requset对象，响应一个Stream
     * </pre>
     */
    public void queryStudentsByCourseName(com.yanqun.grpc.proto.MyRequestCourseName request,
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseStudentsStream> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryStudentsByCourseNameMethod(), responseObserver);
    }

    /**
     * <pre>
     *请求一个Stream，响应一个StreamObserver类型的Response对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyRequestCourseName> queryStudentsByCourseName2(
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseStudents> responseObserver) {
      return asyncUnimplementedStreamingCall(getQueryStudentsByCourseName2Method(), responseObserver);
    }

    /**
     * <pre>
     *请求一个Stream，响应一个Stream
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyRequestId> queryStudentNameById2(
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseName> responseObserver) {
      return asyncUnimplementedStreamingCall(getQueryStudentNameById2Method(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryStudentNameByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.yanqun.grpc.proto.MyRequestId,
                com.yanqun.grpc.proto.MyResponseName>(
                  this, METHODID_QUERY_STUDENT_NAME_BY_ID)))
          .addMethod(
            getQueryStudentsByCourseNameMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.yanqun.grpc.proto.MyRequestCourseName,
                com.yanqun.grpc.proto.MyResponseStudentsStream>(
                  this, METHODID_QUERY_STUDENTS_BY_COURSE_NAME)))
          .addMethod(
            getQueryStudentsByCourseName2Method(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.yanqun.grpc.proto.MyRequestCourseName,
                com.yanqun.grpc.proto.MyResponseStudents>(
                  this, METHODID_QUERY_STUDENTS_BY_COURSE_NAME2)))
          .addMethod(
            getQueryStudentNameById2Method(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.yanqun.grpc.proto.MyRequestId,
                com.yanqun.grpc.proto.MyResponseName>(
                  this, METHODID_QUERY_STUDENT_NAME_BY_ID2)))
          .build();
    }
  }

  /**
   * <pre>
   *定义接口
   * </pre>
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *请求一个Requset对象，响应一个Response对象
     * </pre>
     */
    public void queryStudentNameById(com.yanqun.grpc.proto.MyRequestId request,
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseName> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryStudentNameByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *请求一个Requset对象，响应一个Stream
     * </pre>
     */
    public void queryStudentsByCourseName(com.yanqun.grpc.proto.MyRequestCourseName request,
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseStudentsStream> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getQueryStudentsByCourseNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *请求一个Stream，响应一个StreamObserver类型的Response对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyRequestCourseName> queryStudentsByCourseName2(
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseStudents> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getQueryStudentsByCourseName2Method(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *请求一个Stream，响应一个Stream
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyRequestId> queryStudentNameById2(
        io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseName> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getQueryStudentNameById2Method(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *定义接口
   * </pre>
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *请求一个Requset对象，响应一个Response对象
     * </pre>
     */
    public com.yanqun.grpc.proto.MyResponseName queryStudentNameById(com.yanqun.grpc.proto.MyRequestId request) {
      return blockingUnaryCall(
          getChannel(), getQueryStudentNameByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *请求一个Requset对象，响应一个Stream
     * </pre>
     */
    public java.util.Iterator<com.yanqun.grpc.proto.MyResponseStudentsStream> queryStudentsByCourseName(
        com.yanqun.grpc.proto.MyRequestCourseName request) {
      return blockingServerStreamingCall(
          getChannel(), getQueryStudentsByCourseNameMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *定义接口
   * </pre>
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *请求一个Requset对象，响应一个Response对象
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.yanqun.grpc.proto.MyResponseName> queryStudentNameById(
        com.yanqun.grpc.proto.MyRequestId request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryStudentNameByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_STUDENT_NAME_BY_ID = 0;
  private static final int METHODID_QUERY_STUDENTS_BY_COURSE_NAME = 1;
  private static final int METHODID_QUERY_STUDENTS_BY_COURSE_NAME2 = 2;
  private static final int METHODID_QUERY_STUDENT_NAME_BY_ID2 = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_STUDENT_NAME_BY_ID:
          serviceImpl.queryStudentNameById((com.yanqun.grpc.proto.MyRequestId) request,
              (io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseName>) responseObserver);
          break;
        case METHODID_QUERY_STUDENTS_BY_COURSE_NAME:
          serviceImpl.queryStudentsByCourseName((com.yanqun.grpc.proto.MyRequestCourseName) request,
              (io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseStudentsStream>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_STUDENTS_BY_COURSE_NAME2:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.queryStudentsByCourseName2(
              (io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseStudents>) responseObserver);
        case METHODID_QUERY_STUDENT_NAME_BY_ID2:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.queryStudentNameById2(
              (io.grpc.stub.StreamObserver<com.yanqun.grpc.proto.MyResponseName>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.yanqun.grpc.proto.StudentData.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getQueryStudentNameByIdMethod())
              .addMethod(getQueryStudentsByCourseNameMethod())
              .addMethod(getQueryStudentsByCourseName2Method())
              .addMethod(getQueryStudentNameById2Method())
              .build();
        }
      }
    }
    return result;
  }
}
