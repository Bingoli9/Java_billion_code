--使用到了authorid、typeid两个字段的索引
explain select * from book where authorid = 1 and typeid = 2 ;
--对type字段进行了数值计算，因此type上的索引失效，即只用到了authorid一个字段的索引
explain select * from book where authorid = 1 and typeid*2 = 2 ;
--所有的索引均进行了数值计算，因此索引全部失效
explain select * from book where authorid*2 = 1 and typeid*2 = 2 ;
----用到了0个索引。原因：对于复合索引，如果其中某个索引失效，则该索引及右侧索引全部失效。例如索引(a,b,c)，如果 b失效，则会导致b、c同时失效。
explain select * from book where authorid*2 = 1 and typeid = 2 ;
--测试类型转换
create table test04
(
  id int(4),
  name varchar(10)
);
alter table test04 add index idx_name (name) ;
--SQL执行时进行了类型转换（显式，字符转数字），导致索引失效
explain select * from teacher where tname = CONVERT('123',SIGNED); 
--SQL执行时进行了类型转换（隐式，字符转数字），导致索引失效
explain select * from teacher where tname = 123 ;