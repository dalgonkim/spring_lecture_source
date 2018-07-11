create table player(
	player_id varchar(30) NOT NULL,
	last_name varchar(30) NOT NULL,
	first_name varchar(30) NOT NULL,
	position varchar(30) NOT NULL,
	birth_year number NOT NULL,
	debut_year number NOT NULL,
	PRIMARY KEY(player_id)
);


create table orders(
	isbn varchar(30) NOT NULL,
	quantity number NOT NULL,
	price number(12,2) NOT NULL,
	customer varchar(30) NOT NULL,
	PRIMARY KEY(isbn)
);