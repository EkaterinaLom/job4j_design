create table passportTS(
    id serial primary key,
    seria int,
    number int
);

create table transport(
    id serial primary key,
    name varchar(255),
    passportTS_id int references passportTS(id) unique
);