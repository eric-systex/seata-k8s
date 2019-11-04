# From 
DROP SCHEMA IF EXISTS seata_tcc_transfer_from_db;
CREATE SCHEMA seata_tcc_transfer_from_db;
USE seata_tcc_transfer_from_db;

CREATE TABLE business_activity
(
    tx_id varchar(128) NOT NULL, 
    gmt_create TIMESTAMP(6), 
    gmt_modified TIMESTAMP(6), 
    instance_id varchar(128), 
    business_type varchar(32),
    business_id varchar(128), 
    state varchar(2), 
    app_name varchar(32), 
    timeout int(11),  
    context varchar(2000), 
    is_sync varchar(1), 
    PRIMARY KEY ( tx_id )
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE business_action 
( 
    tx_id varchar(128), 
    action_id varchar(64),
    gmt_create TIMESTAMP(6),
    gmt_modified TIMESTAMP(6), 
    instance_id varchar(128), 
    rs_id varchar(128), 
    rs_desc varchar(512), 
    rs_type smallint(6), 
    state varchar(2), 
    context varchar(2000), 
    PRIMARY KEY ( action_id )
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table account (
    account_no varchar(255), 
    amount DOUBLE,
    freezed_amount DOUBLE, 
    PRIMARY KEY (account_no)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into account(account_no, amount, freezed_amount) values('A', 100, 0);
insert into account(account_no, amount, freezed_amount) values('B', 100, 0);



# To 
DROP SCHEMA IF EXISTS seata_tcc_transfer_to_db;
CREATE SCHEMA seata_tcc_transfer_to_db;
USE seata_tcc_transfer_to_db;

CREATE TABLE business_activity
(   
    tx_id varchar(128) NOT NULL,
    gmt_create TIMESTAMP(6), 
    gmt_modified TIMESTAMP(6),
    instance_id varchar(128), 
    business_type varchar(32),
    business_id varchar(128),
    state varchar(2), 
    app_name varchar(32),
    timeout int(11),  
    context varchar(2000),
    is_sync varchar(1), 
    PRIMARY KEY ( tx_id )
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE business_action 
(  
    tx_id varchar(128),   
    action_id varchar(64),
    gmt_create TIMESTAMP(6),
    gmt_modified TIMESTAMP(6),   
    instance_id varchar(128),    
    rs_id varchar(128),   
    rs_desc varchar(512),   
    rs_type smallint(6),    
    state varchar(2),   
    context varchar(2000),   
    PRIMARY KEY ( action_id )
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table account (
    account_no varchar(255), 
    amount DOUBLE,
    freezed_amount DOUBLE, 
    PRIMARY KEY (account_no)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into account(account_no, amount, freezed_amount) values('C', 100, 0);



