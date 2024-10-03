CREATE TABLE orders
(
    order_id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    track_number     VARCHAR(255),
    entry            VARCHAR(255),
    date_created     TIMESTAMP,
    delivery_service VARCHAR(255),
    status           VARCHAR(255) DEFAULT 'NEW'
);

CREATE TABLE payment
(
    payment_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction   VARCHAR(255),
    currency      VARCHAR(3),
    provider      VARCHAR(255),
    amount        INT,
    payment_dt    TIMESTAMP,
    bank          VARCHAR(255),
    delivery_cost INT,
    goods_total   INT,
    custom_fee    INT,
    order_id      BIGINT,
    status        VARCHAR(255) DEFAULT 'NEW',
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE
);

CREATE TABLE delivery
(
    delivery_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255),
    phone       VARCHAR(20),
    zip         VARCHAR(10),
    city        VARCHAR(255),
    address     VARCHAR(255),
    region      VARCHAR(255),
    email       VARCHAR(255),
    order_id    BIGINT,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE
);

CREATE TABLE items
(
    item_id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    chrt_id      INT,
    track_number VARCHAR(255),
    price        INT,
    rid          VARCHAR(255),
    name         VARCHAR(255),
    sale         INT,
    size         VARCHAR(10),
    total_price  INT,
    nm_id        INT,
    brand        VARCHAR(255),
    status       INT,
    order_id     BIGINT,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE
);