package com.yanqun.thrift;

import com.yanqun.thrift.generatecode.MyException;
import com.yanqun.thrift.generatecode.Student;
import com.yanqun.thrift.generatecode.StudentService;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService.Iface {
    @Override
    public List<Student> queryStudents() throws TException {
        System.out.println("--Java服务端，模拟查询操作--");
        Student Student1 = new Student();
        Student1.setName("zs") ;
        Student1.setAge(23) ;

        Student Student2 = new Student();
        Student2.setName("ls") ;
        Student2.setAge(24) ;

        List<Student> Students = new ArrayList<>();
        Students.add(Student1);
        Students.add(Student2);
        System.out.println("--查询完毕--");
        return Students;
    }

    @Override
    public boolean addStudent(String name, int age) throws MyException, TException {
            System.out.println("--Java服务端，模拟增加操作--");
            System.out.println("增加成功：" +name+","+age);
            return true;
    }



}
