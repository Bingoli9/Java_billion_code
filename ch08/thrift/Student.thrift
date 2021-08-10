namespace * thrift.generatecode
//namespace py thrift.generatecode

typedef i32 int
typedef string String
typedef bool boolean

//数据结构
struct Student{
    1:optional String name,
    2:optional int age
}

//异常
exception MyException{
    1:optional String data
}

//接口
service StudentService{
    list<Student> queryStudents() ,
    boolean addStudent(1:required String name,2:int age) throws(1:MyException e)
}