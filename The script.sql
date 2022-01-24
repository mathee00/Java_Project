create database demo;

create table users(
	id int(3) NOT NULL auto_increment,
    name varchar(120) NOT NULL,
    email varchar(120) NOT NULL,
    country varchar(120),
    PRIMARY KEY (id)
);