/**
 * Author:  Thomas Hartmann - tha@cphbusiness.dk
 * Created: Nov 11, 2016
 */
-- create database kayakbooking;
use kayakbooking;

drop table if exists image;
drop table if exists booking;
drop table if exists kayak;
drop table if exists user;

create table image(
id int(5) primary key auto_increment,
 image mediumblob, 
 name varchar(20), 
 extension varchar(40)
);


create table kayak(
id int(4) primary key auto_increment,
name varchar(20),
model varchar(20),
description varchar(40),
year int(4),
color varchar(20),
length decimal(5,2),
image blob
);

create table user(
id int(5) primary key auto_increment,
name varchar(20) unique,
password varchar(20)
);

create table booking(
userid int(5),
kayakid int(5),
bookingdate date,
primary key(userid, kayakid, bookingdate),
foreign key(userid) references user(id),
foreign key(kayakid) references kayak(id)
);

INSERT INTO kayak (name, model, description, year, color, length) VALUES 
('Delfinen', 'northwind', 'hurtig og levende', 1999, 'yellow', 5.11),
('Anden', 'northwind', 'stabil og god til begyndere', 1994, 'red', 5.05),
('Ørnen', 'scala', 'slank og hurtig', 1994, 'red', 5.05),
('Blishønen', 'seahawk', 'foruroligende ustabil og humørsyg', 2002, 'blue', 5.00);

INSERT INTO user (name, password) VALUES ('admin', 'admin123');
INSERT INTO user (name, password) VALUES ('test', 'test123');
SELECT * FROM user;

INSERT INTO booking(userid, kayakid, bookingdate) VALUES (1, 1, '2001-01-01');
SELECT * FROM kayak;
select userid, kayakid, bookingdate from booking;
select id, name, model, description, year, color, length from kayak;
SELECT userid, kayakid, bookingdate FROM booking;
SELECT userid, kayakid, bookingdate FROM booking WHERE userid = 1 AND kayakid = 1 AND bookingdate = '2001-01-01';