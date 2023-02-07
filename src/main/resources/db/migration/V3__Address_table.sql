create table address(addres_id serial primary key ,
country varchar(25) ,
state varchar(25),
distric varchar(25),mandal varchar(25),
village varchar(200),
street varchar(300),
landmarks text,
zip_code varchar(20),
email varchar(255) not null constraint fk_email references users(email))