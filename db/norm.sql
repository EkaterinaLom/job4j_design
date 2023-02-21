create table movies (
	id serial primary key,
	name text
);

create table fe_male (
	id serial primary key,
	name text
);

create table users (
	id serial primary key,
	name text,
	address text,
	fe_male_id int references fe_male(id)
);

create table users_movies (
	id serial primary key,
	users_id int references users(id),
	movies_id int references movies(id)
);
