create table animal(
	id serial primary key,
	name varchar(255),
	area text,
	weight smallint
);
insert into animal(name, area, weight) values ('tiger', 'Amur', 100);
select * from animal;
update animal set name = 'Tiger';
select * from animal;
delete from animal;
select * from animal;