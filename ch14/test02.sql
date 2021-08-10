--准备数据
create table test02
(
	a1 char(3),
	a2 char(3),
	a3 char(3),
	index idx_a1(a1),
	index idx_a2(a2),
	index idx_a3(a3)
);
--不会出现Using filesort 
explain select * from test02 where a1 ='' order by a1 ;
--会出现Using filesort 
explain select * from test02 where a1 ='' order by a2 ;