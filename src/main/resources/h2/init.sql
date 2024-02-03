create table users (
    id_user bigint auto_increment,
    nombre varchar(50),
    apellido varchar(50),
    edad int,
    dni varchar(9),
    primary key (id_user)
);

create table rol (id_rol bigint auto_increment,
                       nickname varchar(50),
                       password varchar(50),
                       email varchar(50),
                       dni varchar(9),
                       id_user bigint,
                       primary key (id_rol),
                       foreign key (id_user) references users (id_user) on delete cascade
);

create table product (id_product bigint auto_increment,
                  name_product varchar(50),
                  price double,
                  stock varchar(50),
                  id_user bigint,
                  primary key (id_product),
                  foreign key (id_user) references users (id_user) on delete cascade
);

insert into users
    (nombre,apellido,edad,dni)
    values
    ('Jose Jesus','Flore Bejarano',29,'76547078'),
    ('Melanie Georgina','Pena Rojas',25,'06101270');
