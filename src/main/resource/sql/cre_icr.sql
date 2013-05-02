drop table usertable;
create table usertable 
( 
userid int primary key, 
username varchar2(32) not null, 
password varchar2(32) 
);
create sequence usertable_seq start with 1 increment by 1 nocache; 
commit;