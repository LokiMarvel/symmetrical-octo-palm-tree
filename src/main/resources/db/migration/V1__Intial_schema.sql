create table users(user_id varchar(500) default gen_random_uuid(),
username varchar(500) not null ,
password varchar(500) not null ,
email varchar(500) not null unique primary key ,
enabled boolean default true,
account_non_locked boolean not null default true,
account_non_expired boolean not null default true,
credentials_non_expired boolean not null default true);

create table authorities(
    id serial primary key ,
    users_role varchar(50),
    email varchar(500) not null constraint fk_email references users(email)
);