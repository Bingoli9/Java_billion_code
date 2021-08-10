# -*- coding: utf-8 -*-
from concurrent import futures
import time

import grpc

import Student_pb2
import Student_pb2_grpc

_ONE_DAY_IN_SECONDS = 60 * 60 * 24

class StudentService(Student_pb2_grpc.StudentServiceServicer):
    def queryStudentNameById(self, request, context):
        print("接收到的请求:id=%s" % request.id)
        print("根据id=1，查询出对应的name,并返回给客户端")
        return Student_pb2.MyResponseName(name='zs')

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    Student_pb2_grpc.add_StudentServiceServicer_to_server(StudentService(), server)
    server.add_insecure_port('127.0.0.1:8888')
    server.start()
    try:
        while True:
            time.sleep(_ONE_DAY_IN_SECONDS)
    except KeyboardInterrupt:
        server.stop(0)


if __name__ == '__main__':
    serve()
