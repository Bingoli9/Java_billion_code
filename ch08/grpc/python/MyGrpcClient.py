# -*- coding: utf-8 -*-
from __future__ import print_function

import grpc

import Student_pb2
import Student_pb2_grpc


def run():
    with grpc.insecure_channel('127.0.0.1:8888') as channel:
        stub = Student_pb2_grpc.StudentServiceStub(channel)
        print("向服务端发出请求id=1 ")
        response = stub.queryStudentNameById(Student_pb2.MyRequestId(id=1))
    print("接收到服务端的响应name=%s " % response.name)


if __name__ == '__main__':
    run()
