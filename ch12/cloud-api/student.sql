create database cloud character set utf8 ;
use cloud ;
create table student
(
	stuNo int not null primary key auto_increment ,
	stuName varchar(20),
	db varchar(60)
);
--插入数据，database()会获取当前数据库名
insert into student(stuName,db) values('zs',database()) ;
insert into student(stuName,db) values('ls',database()) ;
insert into student(stuName,db) values('ww',database()) ;
insert into student(stuName,db) values('zl',database()) ;
insert into student(stuName,db) values('sq',database()) ;