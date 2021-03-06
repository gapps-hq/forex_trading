INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('USD', 'CAD', 1.3031, 400, parsedatetime('2017-07-10 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('USD', 'CAD', 1.3028, 3000, parsedatetime('2017-07-09 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('EUR', 'USD', 1.1351, 15500, parsedatetime('2017-07-08 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('GBP', 'USD', 1.2341, 257000, parsedatetime('2017-07-11 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('AUD','USD', 0.7642, 500, parsedatetime('2017-07-08 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('EUR','USD', 1.1370, 7900, parsedatetime('2017-07-11 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO customers(password, name, type_of_user) VALUES('sahil', 'sahil', 'TRADER');
INSERT INTO customers(password, name, type_of_user) VALUES('ayu', 'ayu', 'AUDITOR');
INSERT INTO customers(password, name, type_of_user) VALUES('ganesh', 'ganesh', 'TRADER');
INSERT INTO customers(password, name, type_of_user) VALUES('parth', 'parth', 'TRADER');



INSERT INTO AUDIT_TABLE(currency_base, currency_quote, price, lot_size, time_created, time_updated, status) VALUES('EUR', 'USD', 1.1370, 7900, parsedatetime('2017-07-10 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS') ,parsedatetime('2017-07-10 23:05:59.004', 'yyyy-MM-dd hh:mm:ss.SS') , 'COMPLETED');


INSERT INTO orders (cust_id, currency_base,currency_quote, price, lot_size, transaction_time, type_of_order, side, limit_price, status)
VALUES(1, 'EUR', 'USD', 1.1370, 7900, CURRENT_TIMESTAMP(), 'LIMIT',  'BUY', 1.414, 'PENDING');




INSERT INTO orders (cust_id, currency_base,currency_quote, lot_size, transaction_time, type_of_order, side, limit_price, status)
VALUES(1, 'JPY', 'USD', 7900, CURRENT_TIMESTAMP(), 'LIMIT',  'BUY', 1.414, 'PENDING');

INSERT INTO orders (cust_id, currency_base,currency_quote, lot_size, transaction_time, type_of_order, side, limit_price, status)
VALUES(1, 'JPY', 'USD', 7900, CURRENT_TIMESTAMP(), 'LIMIT',  'SELL', 1.414, 'PENDING');





INSERT INTO orders (cust_id, currency_base,currency_quote, lot_size, transaction_time, type_of_order, side, status)
VALUES(1, 'GBP', 'USD', 7900, CURRENT_TIMESTAMP(), 'MARKET',  'BUY', 'PENDING');

INSERT INTO orders (cust_id, currency_base,currency_quote, lot_size, transaction_time, type_of_order, side, status)
VALUES(1, 'GBP', 'USD', 7900, CURRENT_TIMESTAMP(), 'MARKET',  'SELL',  'PENDING');




INSERT INTO orders (cust_id, currency_base,currency_quote, lot_size, transaction_time, type_of_order, side, status)
VALUES(1, 'USD', 'EUR', 7900, CURRENT_TIMESTAMP(), 'MARKET',  'SELL', 'PENDING');

INSERT INTO orders (cust_id, currency_base,currency_quote, lot_size, transaction_time, type_of_order, side, limit_price, status)
VALUES(1, 'USD', 'EUR', 7900, CURRENT_TIMESTAMP(), 'LIMIT',  'BUY', 1.414, 'PENDING');

INSERT INTO orders (cust_id, currency_base,currency_quote, price, lot_size, transaction_time, type_of_order, side, limit_price, status)
VALUES(1, 'EUR', 'JPY', 1.1370, 7900, CURRENT_TIMESTAMP(), 'LIMIT',  'BUY', 1.414, 'PENDING');
