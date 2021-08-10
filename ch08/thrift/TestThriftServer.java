package com.yanqun.thrift;

import com.yanqun.thrift.generatecode.StudentService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

public class TestThriftServer {
    public static void main(String[] args) throws TTransportException {
        //使用多线程、非阻塞式的工作模式
        TNonblockingServerSocket server = new TNonblockingServerSocket(8888);
        THsHaServer.Args ServerArgs = new THsHaServer.Args(server).minWorkerThreads(3).maxWorkerThreads(5);
        StudentService.Processor<StudentServiceImpl> processor = new StudentService.Processor<>(new StudentServiceImpl());
        //使用二进制格式传输数据
        ServerArgs.protocolFactory(new TBinaryProtocol.Factory());
        //使用TFramedTransport方式传输数据
        ServerArgs.transportFactory(new TFramedTransport.Factory());
        ServerArgs.processorFactory(new TProcessorFactory(processor));
        TServer tserver = new THsHaServer(ServerArgs);
        //启动服务
        tserver.serve();
    }
}