create table linelock(
id int(5) primary key auto_increment,
name varchar(20)
)engine=innodb ;//行锁
insert into linelock(name) values('1')  ;
insert into linelock(name) values('2')  ;
--不存在id=3的数据，即id=3的数据是一个“间隙”
insert into linelock(name) values('4')  ;
insert into linelock(name) values('5')  ;
----此where范围中包含了id=3的数据，但实际上并不存在id=3的数据库
update linelock set name ='x' where id<6 ; 