CREATE TABLE files
(
id varchar(100) NULL,
 file_name varchar(100) NULL,
 file_type varchar(100) NULL,
 guid varchar(100) NULL,
 data BIGINT,
 PRIMARY KEY (id)
);

CREATE TABLE items
(
id varchar(100) NULL,
 brand varchar(100) NULL,
 type varchar(100) NULL,
 guid varchar(100) NULL,
 createdDateTime timestamp default NULL,
 PRIMARY KEY (id)
);