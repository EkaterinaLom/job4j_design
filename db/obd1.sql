create table students(
	id serial primary key,
	name varchar(50)
);

insert into students (name) values ('Ivan');
insert into students (name) values ('Petr');

create table authors(
	id serial primary key,
	name varchar(50)
);

insert into authors (name) values ('Aleksandr Pushkin');
insert into authors (name) values ('Nikolay Gogol');

create table books(
	id serial primary key,
	name varchar(200),
	authors_id int references authors(id)
);

insert into books (name, authors_id) values ('Evgeni Onegin', 1);
insert into books (name, authors_id) values ('Kapitanskaya dochka', 1);
insert into books (name, authors_id) values ('Dubovsky', 1);
insert into books (name, authors_id) values ('Mertvie dushi', 2);
insert into books (name, authors_id) values ('Viy', 2);

create table orders(
	id serial primary key,
	active boolean default true,
	books_id int references books(id),
	students_id int references students(id)
);

insert into orders (books_id, students_id) values (1, 1);rte
insert into orders (books_id, students_id) values (3, 1);
insert into orders (books_id, students_id) values (5, 2);
insert into orders (books_id, students_id) values (4, 1);
insert into orders (books_id, students_id) values (2, 2);

select s.name, count(a.name), a.name from students as s
join orders o on s.id= o.students_id
join books b on o.books_id = b.id
join authors a on b.authors_id = a.id
group by (s.name, a.name) having count (a.name) >= 2;

create view show_students_with_2_or_more_books
as select s.name as student, count(a.name), a.name from students as s
join orders o on s.id= o.students_id
join books b on o.books_id = b.id
join authors a on b.authors_id = a.id
group by (s.name, a.name) having count (a.name) >= 2;

select * from show_students_with_2_or_more_books;

create table responses_books(
	liked_books text,
	orders_id int references orders(id),
	students_id int references students(id)
);

insert into responses_books (liked_books, orders_id, students_id) values ('no', 1, 1);
insert into responses_books (liked_books, orders_id, students_id) values ('yes', 2, 1);
insert into responses_books (liked_books, orders_id, students_id) values ('no', 3, 2);
insert into responses_books (liked_books, orders_id, students_id) values ('yes', 4, 1);
insert into responses_books (liked_books, orders_id, students_id) values ('no', 5, 2);

select s.name, a.name, b.name, r.liked_books from students as s
join orders o on s.id= o.students_id
join books b on o.books_id = b.id
join authors a on b.authors_id = a.id
join responses_books as r on o.id = r.orders_id
group by s.name, a.name, b.name, r.liked_books;

create view show_all_books_read_by_students_with_reviews
as select s.name as student, a.name as autor, b.name as name_book, r.liked_books as liked_the_book from students as s
join orders o on s.id= o.students_id
join books b on o.books_id = b.id
join authors a on b.authors_id = a.id
join responses_books as r on o.id = r.orders_id
group by s.name, a.name, b.name, r.liked_books;

select * from show_all_books_read_by_students_with_reviews;

