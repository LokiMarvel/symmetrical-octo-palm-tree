create table profile_photo(
    profile_photo_id serial primary key ,
    profile_image bytea,
    email varchar(255) not null constraint fk_email references users(email)
)