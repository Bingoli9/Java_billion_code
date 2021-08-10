#以python方法导入thrift相关类
from  genpy.thrift.generatecode import  ttypes

class StudentServiceImpl:
        #实现查询方法
        def queryStudents(self):
            print("-----Python服务端，模拟查询操作------")
            student1 = ttypes.Student()
            student1.name = 'zs'
            student1.age = 23

            student2 = ttypes.Student()
            student2.name = 'ls'
            student2.age = 24

            list = [student1,student2]
            print("-----查询完毕------")
            return list

        # 实现增加方法
        def saveStudent(self,name,age):
            print("-----Python服务端,模拟增加操作------")
            print ("姓名:%s，年龄:%d" % (name ,age) )
            print("-----模拟成功------")
            return True