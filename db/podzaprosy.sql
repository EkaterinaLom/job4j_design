CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers (first_name, last_name, age, country) values ('Ivan', 'Ivanov', 32, 'Russia');
insert into customers (first_name, last_name, age, country) values ('Pavel', 'Petrov', 29, 'Australia');
insert into customers (first_name, last_name, age, country) values ('Lidya', 'Amro', 25, 'Afrika');
insert into customers (first_name, last_name, age, country) values ('Vasily', 'Shtil', 42, 'Russia');

select first_name, last_name, age from customers
where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders (amount, customer_id) values (2543, 1);
insert into orders (amount, customer_id) values (1745, 2);
insert into orders (amount, customer_id) values (980, 3);

select first_name, last_name from customers
where id not in (select customer_id from orders);
