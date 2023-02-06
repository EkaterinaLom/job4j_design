create table tipe(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	expired_date text,
	price float,
	tipe_id int references tipe(id)
);

insert into tipe(name) values ('Сыр'), ('Молоко'), ('Овощи'), ('Фрукты');
insert into product(name, expired_date, price, tipe_id) values ('Сыр Моцарелла', 'true', 550, 1);
insert into product(name, expired_date, price, tipe_id) values ('Сыр Гауда', 'false', 750, 1);
insert into product(name, expired_date, price, tipe_id) values ('Сыр Домашний', 'false', 650, 1); 
insert into product(name, expired_date, price, tipe_id) values ('Сыр плавленный', 'false', 35, 1);
insert into product(name, expired_date, price, tipe_id) values ('Молоко', 'false', 90, 2);
insert into product(name, expired_date, price, tipe_id) values ('Авокадо мороженое', 'true', 65, 4);
insert into product(name, expired_date, price, tipe_id) values ('Манго мороженое', 'false', 45, 4);
insert into product(name, expired_date, price, tipe_id) values ('Сыр Качотта', 'false', 1500, 1);
insert into product(name, expired_date, price, tipe_id) values ('Сыр Тильзиттер', 'false', 890, 1);
insert into product(name, expired_date, price, tipe_id) values ('Сыр Адыгейский', 'false', 450, 1);
insert into product(name, expired_date, price, tipe_id) values ('Помело', 'false', 150, 4);
insert into product(name, expired_date, price, tipe_id) values ('Сыр из козьего молока', 'true', 2650, 1);
insert into product(name, expired_date, price, tipe_id) values ('Сыр Маасдам', 'false', 1550, 1);
insert into product(name, expired_date, price, tipe_id) values ('Сыр Качотта с пажитником', 'false', 2500, 1);
insert into product(name, expired_date, price, tipe_id) values ('Молоко топленое', 'false', 160, 2);
insert into product(name, expired_date, price, tipe_id) values ('Морковь мытая', 'false', 55, 3);
insert into product(name, expired_date, price, tipe_id) values ('Огурцы', 'false', 150, 3);
insert into product(name, expired_date, price, tipe_id) values ('Помидоры', 'true', 150, 3);

select p.name
from product p join tipe t
on p.tipe_id = t.id
where t.name = 'Сыр'
group by p.name;

select * from product where name like '%мороженое%';

select * from product where expired_date = 'true';

select name, price 
from product
where price in (select max(price) from product);

select t.name as Имя_типа, count(p.name) as Количество
from product p join tipe t
on p.tipe_id = t.id
group by t.name;

select p.name
from product p join tipe t
on p.tipe_id = t.id
where t.name in ('Сыр', 'Молоко')
group by p.name;

select t.name as Имя_типа, count(p.name) as Количество
from product p join tipe t
on p.tipe_id = t.id
group by t.name
having count(p.name) < 10;

select p.name as Имя_продукта, t.name as Тип_продукта
from product p join tipe t
on p.tipe_id = t.id
group by p.name, t.name
order by t.name;
