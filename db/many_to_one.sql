create table category(
	id serial primary key,
	name varchar(255)
);

create table goods(
	id serial primary key,
	name varchar(255),
	category_id int references category(id)
);

insert into category(name) values ('vegetables');
insert into goods(name, category_id) VALUES ('carrots', 1);

select * from goods;

select * from category where id in (select category_id from goods);
