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
 mobile_number numeric NULL,
 PRIMARY KEY (id)
);
--ALTER TABLE public.items add COLUMN mobile_number numeric null;

--ALTER TABLE public.items DROP COLUMN price;
--ALTER TABLE public.items DROP COLUMN speedometer_condition;
--ALTER TABLE public.items DROP COLUMN mobile_number;
--ALTER TABLE public.items ADD COLUMN price VARCHAR(100) null;
--ALTER TABLE public.items ADD COLUMN speedometer_condition VARCHAR(100) null;
--ALTER TABLE public.items ADD COLUMN mobile_number VARCHAR(100) null;
--UPDATE public.items set price = '1500' where price is null;
--UPDATE public.items set speedometer_condition = '150495' where speedometer_condition is null;
--UPDATE public.items set mobile_number = '0907397135' where mobile_number is null;