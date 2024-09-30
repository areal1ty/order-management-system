INSERT INTO orders (order_id, track_number, entry, date_created, locale, customer_id, delivery_service, shardkey, sm_id, oof_shard)
VALUES
    (1, 'WBILMTESTTRACK1', 'WBIL', now(), 'en', 'cust_1', 'meest', 9, 99, 1),
    (2, 'WBILMTESTTRACK2', 'WBIL', now(), 'ru', 'cust_2', 'dhl', 7, 98, 2),
    (3, 'WBILMTESTTRACK3', 'WBIL', now(), 'en', 'cust_3', 'ups', 8, 97, 1),
    (4, 'WBILMTESTTRACK4', 'WBIL', now(), 'ru', 'cust_4', 'fedex', 6, 96, 2),
    (5, 'WBILMTESTTRACK5', 'WBIL', now(), 'en', 'cust_5', 'meest', 5, 95, 1),
    (6, 'WBILMTESTTRACK6', 'WBIL', now(), 'ru', 'cust_6', 'dhl', 4, 94, 2),
    (7, 'WBILMTESTTRACK7', 'WBIL', now(), 'en', 'cust_7', 'ups', 3, 93, 1),
    (8, 'WBILMTESTTRACK8', 'WBIL', now(), 'ru', 'cust_8', 'fedex', 2, 92, 2),
    (9, 'WBILMTESTTRACK9', 'WBIL', now(), 'en', 'cust_9', 'meest', 1, 91, 1),
    (10, 'WBILMTESTTRACK10', 'WBIL', now(), 'ru', 'cust_10', 'dhl', 10, 90, 2);

INSERT INTO payments (payment_id, transaction, currency, provider, amount, payment_dt, bank, delivery_cost, goods_total, custom_fee, order_id)
VALUES
    (1, 'trans_1', 'USD', 'wbpay', 1000, now(), 'alpha', 150, 850, 0, 1),
    (2, 'trans_2', 'EUR', 'paypal', 2000, now(), 'beta', 200, 1800, 0, 2),
    (3, 'trans_3', 'USD', 'wbpay', 1500, now(), 'alpha', 100, 1400, 0, 3),
    (4, 'trans_4', 'RUB', 'yandex', 2500, now(), 'sberbank', 250, 2250, 0, 4),
    (5, 'trans_5', 'USD', 'paypal', 3000, now(), 'alpha', 300, 2700, 0, 5),
    (6, 'trans_6', 'EUR', 'wbpay', 1200, now(), 'beta', 180, 1020, 0, 6),
    (7, 'trans_7', 'USD', 'yandex', 1100, now(), 'sberbank', 170, 930, 0, 7),
    (8, 'trans_8', 'RUB', 'wbpay', 500, now(), 'tinkoff', 50, 450, 0, 8),
    (9, 'trans_9', 'EUR', 'paypal', 4000, now(), 'alpha', 400, 3600, 0, 9),
    (10, 'trans_10', 'USD', 'wbpay', 900, now(), 'beta', 90, 810, 0, 10);

INSERT INTO deliveries (delivery_id, name, phone, zip, city, address, region, email, order_id)
VALUES
    (1, 'Ivan Ivanov', '+79150000000', '123456', 'Moscow', 'Lenina st. 1', 'Moscow Region', 'ivanov@gmail.com', 1),
    (2, 'Petr Petrov', '+79260000000', '654321', 'Saint Petersburg', 'Nevsky st. 10', 'Leningrad Region', 'petrov@gmail.com', 2),
    (3, 'Sidor Sidorov', '+79370000000', '111222', 'Kazan', 'Kremlin st. 5', 'Tatarstan', 'sidorov@gmail.com', 3),
    (4, 'Alex Smirnov', '+79480000000', '333444', 'Novosibirsk', 'Lenina st. 20', 'Siberia', 'smirnov@gmail.com', 4),
    (5, 'Sergey Sergeev', '+79590000000', '555666', 'Vladivostok', 'Pushkina st. 15', 'Primorye', 'sergeev@gmail.com', 5),
    (6, 'Andrey Andreev', '+79660000000', '777888', 'Samara', 'Sovetskaya st. 2', 'Samara Region', 'andreev@gmail.com', 6),
    (7, 'Oleg Olegov', '+79770000000', '999000', 'Perm', 'Kompros st. 11', 'Perm Region', 'olegov@gmail.com', 7),
    (8, 'Vladimir Vladimirov', '+79880000000', '123789', 'Rostov-on-Don', 'Donetskaya st. 7', 'Rostov Region', 'vladimirov@gmail.com', 8),
    (9, 'Roman Romanov', '+79990000000', '456789', 'Sochi', 'Kurortny st. 19', 'Krasnodar Region', 'romanov@gmail.com', 9),
    (10, 'Denis Denisov', '+79010000000', '789123', 'Yekaterinburg', 'Malysheva st. 3', 'Ural', 'denisov@gmail.com', 10);

INSERT INTO items (item_id, chrt_id, track_number, price, rid, name, sale, size, total_price, nm_id, brand, status, order_id)
VALUES
    (1, 123456, 'WBILMTESTTRACK1', 500, 'rid_1', 'Shampoo', 10, 'M', 450, 101, 'BrandA', 200, 1),
    (2, 234567, 'WBILMTESTTRACK1', 700, 'rid_2', 'Conditioner', 15, 'S', 595, 102, 'BrandB', 200, 1),
    (3, 345678, 'WBILMTESTTRACK2', 800, 'rid_3', 'Soap', 5, 'L', 760, 103, 'BrandC', 200, 2);
