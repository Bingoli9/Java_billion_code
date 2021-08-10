create table test01
(
	tid int(3),
	tname varchar(20)
);
--只插入一条数据
insert into test01 values(1,'a') ;
--创建主键索引
alter table test01 add constraint tid_pk primary key(tid) ;
--查看索引类型
explain select * from (select * from test01 )t where tid =1 ;