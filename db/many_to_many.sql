create table people(
     id serial primary key,
     name varchar(255)
 );
 
 create table shops(
     id serial primary key,
     name varchar(255)
 );
 
 create table people_shops(
     id serial primary key,
     people_id int references people(id),
     shops_id int references shops(id)
 );