--课程表
create table course
(
cid int(3),
cname varchar(20),
tid int(3)
);
--教师表
create table teacher
(
    tid int(3),
    tname varchar(20),
    tcid int(3)
);
--教师证表
create table teacherCard
(
    tcid int(3),
    tcdesc varchar(200)
);
--插入测试数据
insert into course values(1,'java',1);
insert into course values(2,'html',1);
insert into course values(3,'sql',2);
insert into course values(4,'web',3);

insert into teacher values(1,'tz',1);
insert into teacher values(2,'tw',2);
insert into teacher values(3,'tl',3);

insert into teacherCard values(1,'tzdesc') ;
insert into teacherCard values(2,'twdesc') ;
insert into teacherCard values(3,'tldesc') ;