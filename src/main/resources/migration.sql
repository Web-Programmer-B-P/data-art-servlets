create table users
(
    id       serial not null
        constraint user_pk
            primary key,
    login    varchar(100),
    password varchar(200)
);

create table ticket
(
    id          serial not null
        constraint ticket_pk
            primary key,
    name        varchar(100),
    description text,
    status      boolean default true,
    user_id     integer
        constraint ticket_users_id_fk
            references users
);

comment on table ticket is 'Заметки пользователя';