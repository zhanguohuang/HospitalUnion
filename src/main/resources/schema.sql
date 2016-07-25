drop database if exists test;
create database test;
use test;
create table user(
	id int not null,
	username varchar(50),
	password varchar(200),
	email varchar(50),
	primary key(id)
);
insert into user(id,username,password,email) values(1,'zhanguohuang','QQQwwe125','779134714@qq.com');

create table userinfo(
	id int not null auto_increment,
	username varchar(50) not null,
	login_failure_count int default 0,
	image_url varchar(50) not null default 'image/default.png',
	ip int,
	primary key(id,username)
);


create table limuseinf(
	username varchar(50) not null,
	password varchar(50) not null,
	role_user varchar(50),
	primary key(username)
);
insert into limuseinf(username,password,role_user) values('admin','1','ROLE_admin');
insert into limuseinf(username,password,role_user) values('zhanguohuang','1','ROLE_user');
insert into limuseinf(username,password,role_user) values('zhoudi','1','ROLE_user');

create table chatinfo(
id int not null primary key auto_increment,
username varchar(50) not null,
message varchar(500) not null,
create_time datetime not null
);
insert into chatinfo(username,message,create_time) values('zhanguohuang','hello,can you see it','2016-06-05 00:00:00');

--以下的table仅用于Hibernate测试使用
drop table if exists spittle;
drop table if exists spitter;

create table spitter (
  id int auto_increment primary key,
  username varchar(25) not null,
  password varchar(25) not null,
  fullName varchar(100) not null,
  email varchar(50) not null,
  updateByEmail boolean not null
);

create table spittle (
  id integer auto_increment primary key,
  spitter integer not null,
  message varchar(2000) not null,
  postedTime datetime not null,
  foreign key (spitter) references spitter(id)
);
--以上的table仅用于Hibernate测试使用
--alter table user modify column username varchar(50);
--alter table user modify column password varchar(200);
--ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
--DEFAULT CHARSET utf8 COLLATE utf8_general_ci;