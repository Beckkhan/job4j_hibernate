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

create table cars (
		id serial primary key,
		brand varchar(200),
		bodytype_id integer references bodytype(id),
		engine_id integer references engine(id),
		transmission_id integer references transmission(id)
);

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

insert into cars(brand, bodytype_id, engine_id, transmission_id) values
('UAZ HUNTER', 2, 1, 1),
('LADA 2107', 1, 1, 1),
('FORD F-150', 4, 1, 2),
('TOYOTA PRIUS', 3, 4, 2),
('SKODA OCTAVIA', 3, 1, 3);