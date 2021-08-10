#以python方法导入thrift相关类
from thrift.Thrift import TException
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.transport import TSocket, TTransport

from StudentServiceImpl import StudentServiceImpl
from genpy.thrift.generatecode import StudentService

try:
   studentService  =  StudentServiceImpl()
   processor = StudentService.Processor(studentService)
   server = TSocket.TServerSocket("127.0.0.1",port=8888)
   #指定传输方式
   transportFactory =  TTransport.TFramedTransportFactory()
   #指定传输格式
   protocolFactory = TBinaryProtocol.TBinaryProtocolFactory()
   tServer = TServer.TSimpleServer(processor,server,transportFactory,protocolFactory)
   #启动服务
   tServer.serve()

except TException as e:
    print(e)
