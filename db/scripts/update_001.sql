create table users (
  id serial primary key not null,
  name varchar(100),
  expired timestamp
);