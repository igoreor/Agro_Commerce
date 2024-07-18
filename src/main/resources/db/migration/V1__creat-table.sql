CREATE TABLE userr (
    user_id SERIAL  NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    password VARCHAR(16) NOT NULL,
    birthDate DATE NOT NULL,
    sex VARCHAR(10) NOT NULL,
    PRIMARY KEY(user_id),
    UNIQUE(user_email)
);

CREATE TABLE seller (
    seller_id SERIAL NOT NULL,
    user_id INTEGER NOT NULL UNIQUE,
    PRIMARY KEY(seller_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id)
);

CREATE TABLE buyer (
    buyer_id SERIAL NOT NULL,
    user_id INTEGER NOT NULL UNIQUE,
    PRIMARY KEY(buyer_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id)
);

CREATE TABLE locationn (
    location_id SERIAL NOT NULL,
    seller_id INTEGER NOT NULL,
    street TEXT NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,
    number_house VARCHAR(40) NOT NULL,
    city VARCHAR(50) NOT NULL,
    PRIMARY KEY(location_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id)
);

CREATE TABLE product (
    product_id SERIAL NOT NULL,
    type_product VARCHAR(50) NOT NULL,
    name_product VARCHAR(100) NOT NULL,
    value_product DECIMAL NOT NULL,
    description_product TEXT NOT NULL,
    PRIMARY KEY(product_id)
);

CREATE TABLE reservation (
    reservation_id SERIAL NOT NULL,
    buyer_id INTEGER NOT NULL,
    seller_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    reservation_in DATE NOT NULL,
    total_price DECIMAL NOT NULL,
    PRIMARY KEY(reservation_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    FOREIGN KEY(buyer_id) REFERENCES buyer(buyer_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id)
);

CREATE TABLE stock (
    stock_id SERIAL NOT NULL,
    product_id INTEGER NOT NULL,
    seller_id INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    PRIMARY KEY(stock_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    UNIQUE(product_id, seller_id)
);

CREATE TABLE historic (
    historic_id SERIAL NOT NULL,
    reservation_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    seller_id INTEGER NOT NULL,
    bought_in TIMESTAMP NOT NULL,
    PRIMARY KEY(historic_id),
    FOREIGN KEY(reservation_id) REFERENCES reservation(reservation_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id)
);

CREATE TABLE assessment (
    assessment_id SERIAL NOT NULL,
    user_id INTEGER NOT NULL,
    seller_id INTEGER NOT NULL,
    assessment FLOAT8 NOT NULL,
    assessment_date TIMESTAMP NOT NULL,
    PRIMARY KEY(assessment_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    UNIQUE(user_id, seller_id)
);

CREATE TABLE contact (
    contact_id SERIAL NOT NULL,
    buyer_id INTEGER NOT NULL,
    seller_id INTEGER NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    PRIMARY KEY(contact_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    FOREIGN KEY(buyer_id) REFERENCES buyer(buyer_id),
    UNIQUE(buyer_id, seller_id)
);
