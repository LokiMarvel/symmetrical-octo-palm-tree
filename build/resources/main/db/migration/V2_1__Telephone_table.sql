create table telephone(telephone_id serial primary key ,
mobile_number varchar(15),
country_code varchar(5),email varchar not null constraint fk_email references users(email)
                      );