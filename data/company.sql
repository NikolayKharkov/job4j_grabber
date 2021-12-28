CREATE TABLE company
(   id serial primary key,
    name varchar(255)
);

CREATE TABLE person
(
    id serial primary key,
    name varchar(100),
    company_id integer references company(id)
);

insert into company (name) values ('Google');
insert into company (name) values ('Yandex');
insert into company (name) values ('Oracle');
insert into company (name) values ('SpaceX');
insert into company (name) values ('Apple');

insert into person (name, company_id) values ('Kate', 1);
insert into person (name, company_id) values ('Anna', 1);
insert into person (name, company_id) values ('Max', 1);
insert into person (name, company_id) values ('Bob', 1);
insert into person (name, company_id) values ('Mikel', 1);

insert into person (name, company_id) values ('Fedor', 2);
insert into person (name, company_id) values ('Vlad', 2);
insert into person (name, company_id) values ('Tanya', 2);


insert into person (name, company_id) values ('Billy', 3);
insert into person (name, company_id) values ('Oksana', 3);
insert into person (name, company_id) values ('Tanya', 3);

insert into person (name, company_id) values ('Mask', 4);

insert into person (name, company_id) values ('Jobs', 5);
insert into person (name, company_id) values ('Coock', 5);


select * from company;

select p.name, c.name
from person p
join company c on c.id = p.company_id and c.id != 5;

select c.name, count(p.id) as cnt
from person p
join company c on c.id = p.company_id
group by c.name
having count(p.id) in (select max(cnt) from (select count(p.id) as cnt
                                             from person p
                                             group by p.company_id) as count_empl);









