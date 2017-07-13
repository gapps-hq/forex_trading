INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('USD', 'CAD', 1.3031, 400, parsedatetime('2017-07-10 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS');
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('USD', 'CAD', 1.3028, 3000, parsedatetime('2017-07-09 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS');
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('EUR', 'USD', 1.1351, 15500, parsedatetime('2017-07-08 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS');
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('GPB', 'USD', 1.2341, 257000, parsedatetime('2017-07-11 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS');
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('AUD','USD', 0.7642, 500, parsedatetime('2017-07-08 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS');
INSERT INTO historical_data(currency_base, currency_quote, price, lot_size, time_completed) VALUES('EUR','USD', 1.1370, 7900, parsedatetime('2017-07-11 23:04:59.004', 'yyyy-MM-dd hh:mm:ss.SS');


INSERT INTO customers(password, name, type_of_user) VALUES('sahil', 'sahil', 'TRADER');

INSERT INTO orders (cust_id, currency_base,currency_quote, price, lot_size, transaction_time, type_of_order, side, limit_price, status)
VALUES(1, 'EUR', 'USD', 1.1370, 7900, CURRENT_TIMESTAMP(), 'Limit',  'BUY', 1.414, 'Completed');