Drop table if exists address;
CREATE TABLE address
(
    address_id BIGINT AUTO_INCREMENT NOT NULL,
    street     VARCHAR(255),
    city       VARCHAR(255),
    state      VARCHAR(255),
    zip        VARCHAR(255),
    CONSTRAINT pk_address PRIMARY KEY (address_id)
);
Drop table if exists customer;
CREATE TABLE customer
(
    cust_id    BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    address_id BIGINT                NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY (cust_id)
);

ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (address_id);