#以python方法导入thrift相关类
from thrift.Thrift import TException
from thrift.protocol import TCompactProtocol, TBinaryProtocol
from thrift.transport import TSocket, TTransport
from genpy.thrift.generatecode import StudentService

tSocket = TSocket.TSocket("127.0.0.1", 8888)
tSocket.setTimeout(1000)
# 指定传输方式
transport = TTransport.TFramedTransport(tSocket)
# 指定传输格式
protocol = TBinaryProtocol.TBinaryProtocol(transport)
client = StudentService.Client(protocol)
transport.open()
try:
    print("Python RPC调用 queryStudents()方法")
    students = client.queryStudents()
    for student in students:
        print ("姓名:%s，年龄:%d" % (student.name , student.age) )

    print("Python RPC调用 addStudent()方法")
    result = client.addStudent("zs",23)
    if result:
        print("增加成功")
    transport.close()
except TException as e:
    print(e)