insert into roles (name) values ('customer');
insert into rules (name) values ('super urgent');
insert into category (name) values ('first');
insert into status (name) values ('in operation');
insert into roles_rules (name, roles_id, rules_id) values ('cstandart', 1, 1);
insert into users (name, roles_id) values ('Sidorov', 1);
insert into itemes(name, users_id, category_id, status_id) values ('1S', 1, 1, 1);
insert into coments(name, itemes_id) values ('urgent', 1);
insert into attached(name, itemes_id) values ('scans', 1);
