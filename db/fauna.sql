create table fauna (
	id serial primary key,
	name text,
	avg_age int,
	discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('fish', 2117, '1800-11-04');
insert into fauna (name, avg_age, discovery_date) values ('mammalia', 12705, '1758-10-04');
insert into fauna (name, avg_age, discovery_date) values ('birds', 5445, '1530-10-04');
insert into fauna (name, avg_age, discovery_date) values ('insects', 150, '1960-10-04');
insert into fauna (name, avg_age, discovery_date) values ('worms', 150, null);

select * from fauna where name like '%fish%'; 
select * from fauna where avg_age >= 10000 AND avg_age <= 21000; 
select * from fauna where discovery_date is null; 
select * from fauna where discovery_date < '1950-01-01'; 
select * from fauna where discovery_date > '1950-01-01';
