package com.yanqun.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private int stuno ;
	private String stuname;  
	private int gradeid ;
	
	public Student(int stuno, String stuname, int gradeid) {
		this.stuno = stuno;
		this.stuname = stuname;
		this.gradeid = gradeid;
	}
	public Student() {
	}
	public int getStuno() {
		return stuno;
	}
	public void setStuno(int stuno) {
		this.stuno = stuno;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public int getGradeid() {
		return gradeid;
	}
	public void setGradeid(int gradeid) {
		this.gradeid = gradeid;
	}
	@Override
	public String toString() {
		return "Student [stuno=" + stuno + ", stuname=" + stuname + ", gradeid=" + gradeid + "]";
	}
	
	
}
