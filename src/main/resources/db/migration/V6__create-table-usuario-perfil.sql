create table usuario_perfil(

    id bigint not null auto_increment,
    usuario_id bigint not null,
    perfil_id bigint not null,

    primary key(id),
    constraint fk_usuario_perfil_usuario_id foreign key (usuario_id) references usuarios(id),
    constraint fk_usuario_perfil_perfil_id foreign key (perfil_id) references perfiles(id)

);