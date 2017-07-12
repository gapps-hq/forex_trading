INSERT INTO historical_data(currency_pair, price, lot_size, transaction_time) VALUES('USD/CAD', 1.3031, 400, '2017/07/10 23:04:59');
INSERT INTO historical_data(currency_pair, price, lot_size, transaction_time) VALUES('USD/CAD', 1.3028, 3000, '2017/07/10 23:05:03');
INSERT INTO historical_data(currency_pair, price, lot_size, transaction_time) VALUES('EUR/USD', 1.1351, 15500, '2017/07/11 00:01:59');
INSERT INTO historical_data(currency_pair, price, lot_size, transaction_time) VALUES('GPB/USD', 1.2341, 257000, '2017/07/11 01:04:23');
INSERT INTO historical_data(currency_pair, price, lot_size, transaction_time) VALUES('AUD/USD', 0.7642, 500, '2017/07/11 11:44:23');
INSERT INTO historical_data(currency_pair, price, lot_size, transaction_time) VALUES('EUR/USD', 1.1370, 7900, '2017/07/11 13:34:53' );


INSERT INTO customers(password, name, type_of_user) VALUES('sahil', 'sahil', 'TRADER');

INSERT INTO orders (cust_id, currency_base,currency_quote, price, lot_size, transaction_time, type_of_order, side, limit_price, status) 
VALUES(1, 'EUR', 'USD', 1.1370, 7900, CURRENT_TIMESTAMP(), 'Limit',  'BUY', 1.414, 'Completed');