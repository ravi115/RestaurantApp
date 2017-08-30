create database restaurant;

use restaurant;

create table restaurantinfo (
id int not null primary key,
name varchar(250) not null
);

create table address (
id int not null primary key,
street varchar(250),
city varchar(250),
state varchar(250),
country varchar(250),
pincode integer(6),
mobile varchar(13),
aid int references id(restaurantinfo) on delete cascade on update cascade
);

create table mealtype (
id int not null primary key,
type varchar(250),
tid int references id(restaurantinfo) on delete cascade on update cascade
);

insert into restaurantinfo (id, name)  values(2,"Sherton Hotel");
insert into restaurantinfo (id, name)  values(3,"Devi Bar & Restaurants Account");
insert into restaurantinfo (id, name)  values(4,"Rustico");
insert into restaurantinfo (id, name)  values(5,"Hydrabadi spices");
insert into restaurantinfo (id, name)  values(6,"Truffles");
insert into restaurantinfo (id, name)  values(7,"Maurya Lok");

insert into address (id, street,city,state,country,pincode,mobile,aid)  values(2,"Regimental Bazar","Hydrabad","andhra pradesh","india",500003,"+91 9212340202",2);
insert into address (id, street,city,state,country,pincode,mobile,aid)  values(3,"Moula-ali","Hydrabad","andhra pradesh","india",500012,"+91 9212340202",3);
insert into address (id, street,city,state,country,pincode,mobile,aid)  values(4,"Fort","south mumbai","Maharastra","india",400001,"+91 9212340202",4);
insert into address (id, street,city,state,country,pincode,mobile,aid)  values(5,"Electronic city","Bangalore","Karnataka","india",560100,"+91 9212340202",5);
insert into address (id, street,city,state,country,pincode,mobile,aid)  values(6,"Kormangla","bangalore","Karnataka","india",560095,"080 4146 6565",6);
insert into address (id, street,city,state,country,pincode,mobile,aid)  values(7,"New Dak Bungalow Road","patna","Bihar","india",800001,"+91 9212340202",7);


insert into mealtype (id,type,tid)  values(2,"andhra",2);
insert into mealtype (id,type,tid)  values(3,"andhra",3);
insert into mealtype (id,type,tid)  values(4,"south indian",4);
insert into mealtype (id,type,tid)  values(5,"north indian",5);
insert into mealtype (id,type,tid)  values(6,"south indian",6);
insert into mealtype (id,type,tid)  values(7,"north indian",7);


