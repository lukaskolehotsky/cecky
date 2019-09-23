CREATE TABLE files
(
id varchar(100) NULL,
 img_path varchar(1000) NULL,
 file_name varchar(100) NULL,
 file_type varchar(100) NULL,
 guid varchar(100) NULL,
 PRIMARY KEY (id)
);

CREATE TABLE items
(
id varchar(100) NULL,
 brand varchar(100) NULL,
 type varchar(100) NULL,
 guid varchar(100) NULL,
 created_date_time timestamp default NULL,
 email varchar(100) NULL,
 authentication_code varchar(100) NULL,
 price bigint NULL,
 description varchar(5000) NULL,
 fuel_type varchar(100) NULL,
 speedometer_condition numeric NULL,
 production_year numeric NULL,
 PRIMARY KEY (id)
);
--ALTER TABLE public.items add COLUMN price bigint;