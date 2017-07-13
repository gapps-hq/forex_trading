drop table customers if exists;

create table customers (

  cust_id INT NOT NULL AUTO_INCREMENT,
  password VARCHAR(64) NOT NULL,
  name VARCHAR(20) NOT NULL UNIQUE,
  type_of_user VARCHAR(20) NOT NULL,
  PRIMARY KEY(cust_id)
);

DROP TABLE orders IF EXISTS;

CREATE TABLE orders
(
 order_id INT NOT NULL AUTO_INCREMENT,
 cust_id INT NOT NULL,
 currency_base VARCHAR(16) NOT NULL,
 currency_quote VARCHAR(16) NOT NULL,
 price DOUBLE DEFAULT NULL,
 lot_size INT NOT NULL,
 transaction_time TIMESTAMP NOT NULL,
 time_updated TIMESTAMP DEFAULT NULL,
 type_of_order VARCHAR(16),
 side VARCHAR(16) NOT NULL,
 limit_price DOUBLE DEFAULT NULL,
 status VARCHAR(20) NOT NULL,
 PRIMARY KEY(order_id),
 FOREIGN KEY(cust_id) REFERENCES customers(cust_id)
);

DROP TABLE historical_data IF exists;

CREATE TABLE historical_data
(

 ref_id INT NOT NULL AUTO_INCREMENT,
 order_id INT DEFAULT NULL,
 currency_base VARCHAR(16) NOT NULL,
 currency_quote VARCHAR(16) NOT NULL,
 price DOUBLE NOT NULL,
 lot_size INT NOT NULL,
 time_completed TIMESTAMP NOT NULL,
 PRIMARY KEY(ref_id)
);

DROP TABLE audit_table IF exists;

CREATE TABLE audit_table
(
    order_id INT NOT NULL AUTO_INCREMENT,
    audit_id INT NOT NULL AUTO_INCREMENT,
    currency_base VARCHAR(16) NOT NULL,
    currency_quote VARCHAR(16) NOT NULL,
    price DOUBLE NOT NULL,
    lot_size INT NOT NULL,
    time_created TIMESTAMP NOT NULL,
    time_updated TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY(audit_id)
);