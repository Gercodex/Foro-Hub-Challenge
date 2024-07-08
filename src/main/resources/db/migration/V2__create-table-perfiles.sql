create table perfiles(

    id bigint not null auto_increment,
    nombre varchar(100) not null unique,

    primary key(id)

);