insert into itemes(name) values ('1');
insert into coments(name, itemes_id) values ('urgent', 1);
insert into attached(name, itemes_id) values ('scans', 1);
insert into users (name, itemes_id) values ('Sidorov', 1);
insert into roles (name, users_id) values ('customer', 1);
insert into rules (name) values ('super urgent');
insert into roles_rules (name, roles_id, rules_id) values ('cstandart', 1, 1);
insert into category (name, itemes_id) values ('first', 1);
insert into status (name, itemes_id) values ('in operation', 1);
