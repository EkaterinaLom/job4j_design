create table car_bodies(
	id  serial primary key,
	name text
);

create table car_engines(
	id  serial primary key,
	name text
);

create table car_transmissions(
	id  serial primary key,
	name text
);

create table cars(
	id  serial primary key,
	name text,
	car_bodies_id int references car_bodies(id),
	car_engines_id int references car_engines(id),
	car_transmissions_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan'), ('versatile'), ('pickup');
insert into car_engines(name) values ('petrol'), ('diesel'), ('hybrid');
insert into car_transmissions(name) values ('AKPP'), ('MKPP');

insert into cars(name, car_bodies_id, car_engines_id, car_transmissions_id) values ('Allion', 1, 1, 1);
insert into cars(name, car_bodies_id, car_engines_id, car_transmissions_id) values ('Whish', 2, 1, 1);
insert into cars(name, car_bodies_id, car_engines_id, car_transmissions_id) values ('Delica', null, 2, 1);
insert into cars(name, car_bodies_id, car_engines_id, car_transmissions_id) values ('Cresta', 1, null, 1);

select c.id, c.name as car_name, b.name as bodies_name,
e.name as engines_name, t.name as transmissions_name
from cars c left join car_bodies as b
on c.car_bodies_id = b.id
left join car_engines as e on c.car_engines_id = e.id
left join car_transmissions as t on c.car_transmissions_id = t.id;

select b.name as bodies_name
from cars c right join car_bodies as b
on c.car_bodies_id = b.id
where c.car_bodies_id is null;

select e.name as engines_name
from cars c right join car_engines as e
on c.car_engines_id = e.id
where c.car_engines_id is null;

select t.name as transmissions_name
from cars c right join car_transmissions as t
on c.car_transmissions_id = t.id
where c.car_transmissions_id is null;
