package com.yanqun.cloud.entity;

import java.io.Serializable;
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	//学号
	private Integer stuno ; 
	//姓名
	private String stuname ; 
	//微服务中可以存在很多数据库，用db指定对应的那个数据库
	private String db ;
	
	public Student() {
	}
	
	public Student(Integer stuno, String stuname, String db) {
		this.stuno = stuno;
		this.stuname = stuname;
		this.db = db;
	}

	public Integer getStuno() {
		return stuno;
	}

	public void setStuno(Integer stuno) {
		this.stuno = stuno;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	@Override
	public String toString() {
		return "Student [stuno=" + stuno + ", stuname=" + stuname + ", db=" + db + "]";
	}

	
}
