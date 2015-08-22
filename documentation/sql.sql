create table files(
	id serial not null,
	filename varchar(300),
	md5 varchar(128),
	size varchar(20),
	insert_date varchar(30),
primary key(id)
);