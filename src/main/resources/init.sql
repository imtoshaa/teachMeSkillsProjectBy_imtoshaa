drop schema if exists parts_shop;
create schema if not exists parts_shop;

drop table if exists parts_shop.roles;

create table if not exists parts_shop.roles
(
    id   serial unique,
    role varchar(25) unique not null,
    primary key (id),
    unique index idx_roles_id_unique (id asc),
    unique index idx_roles_role_unique (role asc)
);

drop table if exists parts_shop.users;

create table if not exists parts_shop.users
(
    id                serial unique,
    login             varchar(20)     not null unique,
    password          varchar(200)    not null,
    name              varchar(20)     not null,
    surname           varchar(20)     not null,
    birthday          date,
    email             varchar(30)     not null unique,
    phone_number      varchar(13)     not null unique,
    balance           numeric(7, 2) unsigned,
    role_id           bigint unsigned not null,
    primary key (id),
    unique index idx_users_user_id_unique (id asc),
    unique index idx_users_login_unique (login asc),
    unique index idx_users_email_unique (email asc),
    unique index idx_users_phone_number_unique (phone_number asc),
    constraint fk_users_role_id_roles_id
        foreign key (role_id) references parts_shop.roles (id) on update cascade
);

drop table if exists parts_shop.telegram_bot;

create table if not exists parts_shop.telegram_bot
(
    id      serial unique,
    user_id bigint unsigned unique,
    chat_id bigint unsigned unique not null,
    primary key (id),
    unique index idx_bot_id_unique (id asc),
    unique index idx_bot_user_id_unique (user_id asc),
    unique index idx_bot_chat_id_unique (chat_id asc),
    constraint fk_bot_user_id_users_id
        foreign key (user_id) references parts_shop.users (id) on update cascade on delete cascade
);

drop table if exists parts_shop.brands;

create table if not exists parts_shop.brands
(
    id    serial unique,
    brand varchar(30) unique not null,
    image_path varchar(300) unique not null,
    primary key (id),
    unique index idx_brands_brand_id_unique (id asc),
    unique index idx_brands_brand_unique (brand asc),
    unique index idx_brands_image_path_unique (image_path asc)
);

drop table if exists parts_shop.models;

create table if not exists parts_shop.models
(
    id       serial unique,
    brand_id bigint unsigned    not null,
    model    varchar(50) unique not null,
    primary key (id),
    unique index idx_models_id_unique (id asc),
    unique index idx_models_model_unique (model asc),
    constraint fk_models_brand_id
        foreign key (brand_id) references parts_shop.brands (id) on delete cascade on update cascade
);

drop table if exists parts_shop.bodies;

create table if not exists parts_shop.bodies
(
    id   serial unique,
    body varchar(30) unique not null,
    primary key (id),
    unique index idx_bodies_body_id_unique (id asc)
);

drop table if exists parts_shop.transmissions;

create table if not exists parts_shop.transmissions
(
    id           serial unique,
    transmission varchar(30) unique not null,
    primary key (id),
    unique index idx_transmissions_transmission_id_unique (id asc)
);

drop table if exists parts_shop.fuel_types;

create table if not exists parts_shop.fuel_types
(
    id        serial unique,
    fuel_type varchar(20) unique not null,
    primary key (id),
    unique index idx_fuel_types_fuel_type_id_unique (id asc)
);

drop table if exists parts_shop.cars;

create table if not exists parts_shop.cars
(
    id              serial unique,
    car_code        varchar(10) unique not null,
    brand_id        bigint unsigned    not null,
    model_id        bigint unsigned    not null,
    body_id         bigint unsigned    not null,
    transmission_id bigint unsigned,
    engine_code     varchar(30),
    fuel_type_id    bigint unsigned,
    engine_cc       varchar(5),
    engine_features varchar(30),
    vin             varchar(20) unique,
    year            smallint unsigned,
    color           varchar(15),
    primary key (id),
    unique index idx_cars_car_id_unique (id asc),
    unique index idx_cars_car_index_unique (car_code asc),
    unique index idx_cars_car_vin_unique (vin asc),
    constraint fk_cars_brand_id
        foreign key (brand_id) references parts_shop.brands (id) on delete cascade on update cascade,
    constraint fk_cars_model_id
        foreign key (model_id) references parts_shop.models (id) on delete cascade on update cascade,
    constraint fk_cars_body_id
        foreign key (body_id) references parts_shop.bodies (id) on update cascade,
    constraint fk_cars_transm_id
        foreign key (transmission_id) references parts_shop.transmissions (id) on update cascade,
    constraint fk_cars_fuel_type_id
        foreign key (fuel_type_id) references parts_shop.fuel_types (id) on update cascade
);

drop table if exists parts_shop.part_type_sides;

create table if not exists parts_shop.part_type_sides
(
    id   serial unique,
    side varchar(15) unique not null,
    primary key (id),
    unique index idx_type_side_id_unique (id asc),
    unique index idx_type_sides_side_unique (side asc)
);

drop table if exists parts_shop.part_type_directions;

create table if not exists parts_shop.part_type_directions
(
    id        serial unique,
    direction varchar(20) unique not null,
    primary key (id),
    unique index idx_type_directions_id_unique (id asc),
    unique index idx_type_directions_direction_unique (direction asc)
);

drop table if exists parts_shop.part_types;

create table if not exists parts_shop.part_types
(
    id         serial unique,
    type       varchar(100) unique not null,
    primary key (id),
    unique index idx_part_type_id_unique (id asc),
    unique index idx_part_types_type_unique (type asc)
);

drop table if exists parts_shop.parts;

create table if not exists parts_shop.parts
(
    id                  serial unique,
    part_code           bigint unsigned unique not null,
    car_id              bigint unsigned        not null,
    type_id             bigint unsigned        not null,
    direction_id        bigint unsigned,
    side_id             bigint unsigned,
    construction_number varchar(100),
    description         varchar(500),
    price               bigint unsigned,
    in_stock            bit                    not null,
    primary key (id),
    unique index idx_parts_part_id_unique (id asc),
    unique index idx_parts_part_index_unique (part_code asc),
    constraint fk_parts_car_id_cars_id
        foreign key (car_id) references parts_shop.cars (id) on delete cascade on update cascade,
    constraint fk_parts_part_type_id_part_type_id
        foreign key (type_id) references parts_shop.part_types (id) on update cascade,
    constraint fk_parts_direction_id_directions_id
        foreign key (direction_id) references parts_shop.part_type_directions (id) on update cascade,
    constraint fk_parts_side_id_sides_id
        foreign key (side_id) references parts_shop.part_type_sides (id) on update cascade
);

drop table if exists parts_shop.images;

create table if not exists parts_shop.images
(
    id          serial unique,
    car_id      bigint unsigned unique,
    part_id     bigint unsigned unique,
    image_paths varchar(300) not null,
    primary key (id),
    unique index idx_images_id_unique (id asc),
    unique index idx_images_part_idx_unique (part_id asc),
#     unique index idx_images_image_paths_unique (image_paths asc),
    constraint fk_images_car_id_cars_id
        foreign key (car_id) references parts_shop.cars (id) on delete cascade on update cascade,
    constraint fk_images_part_id_parts_id
        foreign key (part_id) references parts_shop.parts (id) on delete cascade on update cascade
);

drop table if exists parts_shop.orders;

create table if not exists parts_shop.orders
(
    id          serial unique,
    user_id     bigint unsigned not null,
    order_date  date            not null,
    order_price bigint unsigned not null,
    primary key (id),
    unique index idx_orders_id_unique (id asc),
    constraint fk_orders_user_id_users_id
        foreign key (user_id) references parts_shop.users (id) on delete cascade on update cascade
);

drop table if exists parts_shop.order_details;

create table if not exists parts_shop.order_details
(
    id       serial unique,
    order_id bigint unsigned not null,
    part_id  bigint unsigned not null,
    primary key (id),
    constraint fk_order_details_part_id_parts_id
        foreign key (part_id) references parts_shop.parts (id) on update cascade,
    constraint fk_order_details_order_id_orders_id
        foreign key (order_id) references parts_shop.orders (id) on delete cascade on update cascade
);

insert into parts_shop.roles (role) value ('admin');
insert into parts_shop.roles (role) value ('client');

insert into parts_shop.users (login, password, name, surname, birthday, email, phone_number,
                              balance, role_id)
    value (
           'admin',
           '$2a$08$WGxe9TotkMf.LHS6UyFsTuUwdTbhf3De10tMumprWWxszzD3v.elu',
           'maximilian',
           'poltorzhickiy',
           '1990.01.01',
           'max@gmail.com',
           '+375259457864',
           32500.23,
           1
    );

# insert into parts_shop.users (login, password, name, surname, birthday, email, phone_number,
#                               balance)
#     value (
#            'anna',
#            '123456',
#            'anna',
#            'kovrizhnih',
#            '1992.07.14',
#            'anna@gmail.com',
#            '+375252542354',
#            500.01
#     );
# insert into parts_shop.users (login, password, name, surname, birthday, email, phone_number,
#                               balance)
#     value (
#            'alia',
#            '123456',
#            'alina',
#            'poltorzhickaya',
#            '1988.10.15',
#            'alina.box@gmail.com',
#            '+375293569784',
#            120.35
#     );
# insert into parts_shop.users (login, password, name, surname, birthday, email, phone_number,
#                               balance)
#     value (
#            'vas',
#            '123456',
#            'vasya',
#            'ivanov',
#            '1986.02.21',
#            'vasya.boss@gmail.com',
#            '+375337854312',
#            325.98
#     );