create table coments (
	id serial primary key,
	name text
);

create table attached (
	id serial primary key,
	name text
);

create table itemes (
	id serial primary key,
	name text,
	coments_id int references coments(id),
	attached_id int references attached(id)
);

create table users (
	id serial primary key,
	name text
);

create table roles (
	id serial primary key,
	name text,
	users_id int references users(id)
);

create table rules (
	id serial primary key,
	name text
);

create table roles_rules (
	id serial primary key,
	name text,
	roles_id int references roles(id),
	rules_id int references rules(id)
);


create table category (
	id serial primary key,
	name text,
	itemes_id int references itemes(id)
);

create table status (
	id serial primary key,
	name text,
	itemes_id int references itemes(id)
);