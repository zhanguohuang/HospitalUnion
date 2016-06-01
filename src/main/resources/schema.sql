use test;
create table user(
	id int not null,
	username varchar(50),
	password varchar(200),
	email varchar(50),
	primary key(id)
);

create table limuseinf(
	username varchar(50) not null,
	password varchar(50) not null,
	role_user varchar(50),
	primary key(username)
);
insert into limuseinf(username,password,role_user) values('admin','1','ROLE_admin');

--alter table user modify column username varchar(50);
--alter table user modify column password varchar(200);