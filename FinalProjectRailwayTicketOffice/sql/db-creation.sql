USE final_project; 
DROP TABLE IF EXISTS route_point;
DROP TABLE IF EXISTS train_station;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS train;

CREATE TABLE train_station (
	id INT PRIMARY KEY AUTO_INCREMENT ,
	name VARCHAR(12) UNIQUE NOT NULL 
);

CREATE TABLE train (
	id INT PRIMARY KEY AUTO_INCREMENT ,
    train_number INT UNIQUE NOT NULL,
    coupe INT NOT NULL CHECK(coupe >= 0),
	reserved_seat INT NOT NULL CHECK (reserved_seat >= 0),
	common INT NOT NULL CHECK(common >= 0),
    coupe_price INT NOT NULL,
	reserved_seat_price INT NOT NULL,
	common_price INT NOT NULL,
    status_of_train VARCHAR(8) DEFAULT 'active'
);

CREATE TABLE route_point (
	train_id INT,
	FOREIGN KEY (train_id)  REFERENCES train (id) ON DELETE CASCADE ON UPDATE cascade,
	train_station_id INT,
	FOREIGN KEY (train_station_id)  REFERENCES train_station (id) ON DELETE CASCADE ON UPDATE cascade,
	PRIMARY KEY (train_id, train_station_id),
    arrive_datetime datetime,
    depart_datetime datetime
);

-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------
CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(10) NOT NULL UNIQUE
);
-- this two commands insert data into roles table
-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Role entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------
CREATE TABLE users(

	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	
-- 'UNIQUE' means logins values should not be repeated in login column of table	
	login VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(40) NOT NULL UNIQUE,
	
-- not null string columns	
	password VARCHAR(100) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	
-- this declaration contains the foreign key constraint	
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id) 

-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ROLES_ID=ID
		ON DELETE CASCADE 

-- the same as previous but updating is used insted deleting
		ON UPDATE RESTRICT
);

CREATE TABLE tickets (
	id INT PRIMARY KEY AUTO_INCREMENT ,
    ticket_number INT UNIQUE NOT NULL,
    train_number INT NOT NULL,
    destinationStation VARCHAR(20) NOT NULL,
    departureStation VARCHAR(20) NOT NULL,
    arrive_datetime datetime,
    depart_datetime datetime,
    place VARCHAR(15) NOT NULL,
    price INT NOT NULL,
    user_id INT,
	FOREIGN KEY (user_id)  REFERENCES users (id) ON DELETE CASCADE ON UPDATE cascade,
    FOREIGN KEY (id)  REFERENCES train (id) ON DELETE CASCADE ON UPDATE cascade
);


insert into users (login, email, password, first_name, last_name, role_id) 
values ('admin', 'admin@ukr.net', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin', 'admin', 0);

insert into train (train_number, coupe, reserved_seat, common, coupe_price, reserved_seat_price,common_price) 
values (4123, 0, 5, 1, 200, 300, 100);

insert into train (train_number, coupe, reserved_seat, common, coupe_price, reserved_seat_price,common_price) 
values (5123, 4, 5, 1, 12, 421, 21);

insert into train (train_number, coupe, reserved_seat, common, coupe_price, reserved_seat_price,common_price) 
values (1241, 4, 5, 1, 12, 421, 21);

insert into train_station (name) 
values ("Харьков");

insert into train_station (name) 
values ("Киев");

insert into train_station (name) 
values ("Львов");

insert into train_station (name) 
values ("Мариуполь");

insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (1, 1, NULL, '2020-02-02 14:14:00');
insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (1, 2, '2020-05-05 15:32:00', NULL);

insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (2, 1, NULL, '2020-01-01 12:32:00');
insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (2, 3, '2020-04-04 17:32:00', NULL);
insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (2, 2, '2020-02-02 15:15:00', '2020-02-02 16:16:00');

insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (3, 1, NULL, '2020-03-03 13:32:00');
insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (3, 2, '2020-04-04 13:32:00', '2020-04-04 14:32:00');
insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (3, 3, '2020-05-05 15:32:00', '2020-05-05 18:32:00');
insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values (3, 4, '2020-07-14 15:32:00', NULL);
