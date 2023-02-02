create table course(
    id serial primary key,
    name text
);

create table student(
    id serial primary key,
    name text,
    course_id int references course(id)
);

insert into course(name) values ('1');
insert into course(name) values ('2');
insert into course(name) values ('3');

insert into student(name, course_id) values ('Petr', 3);
insert into student(name, course_id) values ('Vasya', 1);
insert into student(name, course_id) values ('Lev', 2);
insert into student(name, course_id) values ('Masha', 1);
insert into student(name, course_id) values ('Ivan', 3);

select st.name, cr.name 
from student as st join course as cr on st.course_id = cr.id;

select st.name as Имя, cr.name as Номер_курса
from student as st join course as cr on st.course_id = cr.id;

select st.name as "Имя студента", cr.name "Номер курса"
from student st join course cr on st.course_id = cr.id;
