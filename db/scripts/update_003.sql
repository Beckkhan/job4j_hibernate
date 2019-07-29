create table bodytype (
    id serial primary key,
    name varchar(200)
);

create table engine (
		id serial primary key,
		name varchar(200)
);

create table transmission (
		id serial primary key,
		name varchar(200)
);

create table users (
    id serial primary key,
    name varchar(50),
    login varchar(50),
    email varchar(50),
    password varchar(50),
    created date default now()
);

create table pictures (
    id serial primary key
);

create table cars (
		id serial primary key,
		brand varchar(200),
		bodytype_id integer references bodytype(id),
		engine_id integer references engine(id),
		transmission_id integer references transmission(id),
		picture_id integer references pictures(id),
		price numeric,
		sold boolean,
		location varchar(200),
		user_id integer references users(id)
);

alter table pictures add picture bytea;
alter table pictures add car_id int references cars(id);

insert into bodytype(name) values
('sedan'),
('suv'),
('liftback'),
('pickup'),
('hatchback');

insert into engine(name) values
('gasoline'),
('diesel'),
('gas'),
('electric'),
('hydrogen');

insert into transmission(name) values
('manual'),
('auto'),
('robot'),
('variator');

insert into users(name, login, email, password) values
('First', 'first', 'first@gmail.com', 'first'),
('Second', 'second', 'second@gmail.com','second'),
('Third', 'third', 'third@gmail.com', 'third');

insert into cars(brand, bodytype_id, engine_id, transmission_id, price, sold, location, user_id) values
('UAZ HUNTER', 2, 1, 1, 350000, false, 'Kostroma', 3),
('LADA 2107', 1, 1, 1, 150000, true, 'Voronezh', 3),
('FORD F-150', 4, 1, 2, 5000000, false, 'Krasnoyarsk', 1),
('TOYOTA PRIUS', 3, 4, 2, 1500000, true, 'Vladivostok', 1),
('SKODA OCTAVIA', 3, 1, 3, 1000000, false, 'Yaroslavl', 2);