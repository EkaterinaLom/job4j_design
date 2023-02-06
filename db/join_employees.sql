create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	departments_id int references departments(id)
);

insert into departments(name) values ('Economics');
insert into departments(name) values ('HR');
insert into departments(name) values ('Accounts');

insert into employees(name, departments_id) values ('Ivanov', null);
insert into employees(name, departments_id) values ('Petrov', 2);
insert into employees(name, departments_id) values ('Sidorov', 3);
insert into employees(name, departments_id) values ('Zubin', null);
insert into employees(name, departments_id) values ('Semashko', 3);
insert into employees(name, departments_id) values ('Pechkin', 2);
insert into employees(name, departments_id) values ('Striz', 3);
insert into employees(name, departments_id) values ('Korneluk', null);

select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on e.departments_id = d.id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from employees e cross join departments d;

select d.name from departments d 
left join employees e on e.departments_id = d.id
where e.departments_id is null;

select * from employees e left join departments d on e.departments_id = d.id;
select e.id, e.name, e.departments_id, d.id, d.name
from departments d right join employees e 
on e.departments_id = d.id;



create table teens(
	name text,
	gender text
);

insert into teens(name, gender) values ('Vasya', 'man');
insert into teens(name, gender) values ('Vika', 'woman');
insert into teens(name, gender) values ('Egor', 'man');
insert into teens(name, gender) values ('Ira', 'woman');
insert into teens(name, gender) values ('Denis', 'man');

select * from teens as teens1 cross join teens as teens2
where teens1.gender <> teens2.gender;
