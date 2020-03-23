drop schema if exists `tourist-helper`;

create schema `tourist-helper`
    character set `utf8`;

use `tourist-helper`;

create table city
(
    id        integer primary key not null auto_increment,
    city_name varchar(24)         not null unique
);

create table city_info
(
    id        integer primary key not null auto_increment,
    info varchar(255)        not null,
    fk_city   integer             not null,

    constraint fk_city_to_city_info foreign key (fk_city) references city (id)
);

INSERT INTO city (city_name)
VALUES ('Москва'),
       ('Минск');

INSERT INTO city_info (info, fk_city)
VALUES ('Не забудьте посетить Красную Площадь.', 1),
       ('Не ходите в ЦУМ. Расстроитесь))', 1),
       ('Посмотрите на Национальную библиотеку', 2);



