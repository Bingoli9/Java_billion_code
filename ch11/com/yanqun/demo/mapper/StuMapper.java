package com.yanqun.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yanqun.entity.Student;
//@Mapper
public interface StuMapper {
	@Insert("insert into student values(#{stuno},#{stuname},#{gradeid})")
	public boolean addStu(Student student);
	@Delete("delete from student where stuno = #{stuno}")
	public boolean deleteStuByStuno(int stuno);
	@Update("update student set stuname=#{stuname},gradeid=#{gradeid} where stuno = #{stuno}")
	public boolean updateStuByStuno(Student student);
	@Select("select * from student")
	public List<Student> queryStus() ;
	@Select("select * from student where stuno = #{stuno}")
	public Student queryStuByStuno(Integer stuno) ;
}
