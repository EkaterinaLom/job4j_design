insert into coments(name) values ('urgent');
insert into attached(name) values ('scans');
insert into itemes(name, coments_id, attached_id) VALUES ('1', 1, 1);
insert into users (name) values ('Sidorov');
insert into roles (name, users_id) values ('customer', 1);
insert into rules (name) values ('super urgent');
insert into roles_rules (name, roles_id, rules_id) values ('cstandart', 1, 1);
insert into category (name, itemes_id) values ('first', 1);
insert into status (name, itemes_id) values ('in operation', 1);
