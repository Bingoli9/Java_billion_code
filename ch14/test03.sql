create table test03
(
  a1 int(4) not null,
  a2 int(4) not null,
  a3 int(4) not null,
  a4 int(4) not null
);
--注意复合索引的顺序是：a1、a2、a3、a4
alter table test03 add index idx_a1_a2_a3_4(a1,a2,a3,a4) ;