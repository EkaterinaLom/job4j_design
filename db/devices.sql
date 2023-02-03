create table devices(
	id serial primary key,
	name varchar(255),
	price float
);

create table people(
	id serial primary key,
	name varchar(255)
);

create table devices_people(
	id serial primary key,
	devices_id int references devices(id),
	people_id int references people(id)
);

insert into devices(name, price) values ('player', 1500), ('vacuum cleaner', 15000), ('iron', 2550);
insert into people(name) values ('Masha'), ('Vasya'), ('Egor'), ('Denis');

insert into devices_people(devices_id, people_id) values (1, 1), (1, 2), (1, 3), (null, 4);
insert into devices_people(devices_id, people_id) values (2, 1), (2, 2), (null, 3), (2, 4);
insert into devices_people(devices_id, people_id) values (3, 1), (null, 2), (3, 3), (3, 4);


select avg(price) from devices;

select p.name, avg(d.price) 
from devices_people pd
join devices as d
on pd.devices_id = d.id
join people p
on pd.people_id = p.id
group by p.name;

select p.name, avg(d.price) 
from devices_people pd
join devices as d
on pd.devices_id = d.id
join people p
on pd.people_id = p.id
group by p.name
having avg(d.price) > 5000;
