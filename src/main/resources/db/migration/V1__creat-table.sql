CREATE TABLE userr (
    user_id SERIAL NOT NULL,
    user_email varchar(50) NOT NULL,
    user_name varchar(100) NOT NULL,
    password varchar(16) NOT NULL,
    birthDate DATE NOT NULL,
    sex varchar(10) NOT NULL,
    PRIMARY KEY(user_id),
    UNIQUE(user_email)
);

CREATE TABLE seller (
    seller_id SERIAL NOT NULL,
    user_id integer NOT NULL UNIQUE,
    PRIMARY KEY(seller_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id)
);

CREATE TABLE buyer (
    buyer_id SERIAL NOT NULL,
    user_id integer NOT NULL UNIQUE,
    PRIMARY KEY(buyer_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id)
);

CREATE TABLE locationn (
    location_id SERIAL NOT NULL,
    seller_id integer NOT NULL,
    street text NOT NULL,
    neighborhood varchar(50) NOT NULL,
    number_house varchar(40) NOT NULL,
    city varchar(50) NOT NULL,
    PRIMARY KEY(location_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id)
);

CREATE TABLE product (
    product_id SERIAL NOT NULL,
    type_product varchar(50) NOT NULL,
    name_product varchar(100) NOT NULL,
    description_product text NOT NULL,
    PRIMARY KEY(product_id)
);

CREATE TABLE reservation (
    reservation_id SERIAL NOT NULL,
    buyer_id integer NOT NULL,
    seller_id integer NOT NULL,
    product_id integer NOT NULL,
    reservation_in date NOT NULL,
    total_price decimal NOT NULL,
    PRIMARY KEY(reservation_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    FOREIGN KEY(buyer_id) REFERENCES buyer(buyer_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id)
);

CREATE TABLE stock (
    stock_id SERIAL NOT NULL,
    product_id integer NOT NULL,
    seller_id integer NOT NULL,
    amount integer NOT NULL,
    PRIMARY KEY(stock_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    UNIQUE(product_id, seller_id)
);

CREATE TABLE historic (
    historic_id SERIAL NOT NULL,
    reservation_id integer NOT NULL,
    user_id integer NOT NULL,
    seller_id integer NOT NULL,
    bought_in timestamp NOT NULL,
    PRIMARY KEY(historic_id),
    FOREIGN KEY(reservation_id) REFERENCES reservation(reservation_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id)
);

CREATE TABLE assessment (
    assessment_id SERIAL NOT NULL,
    user_id integer NOT NULL,
    seller_id integer NOT NULL,
    assessment float8 NOT NULL,
    assessment_date timestamp NOT NULL,
    PRIMARY KEY(assessment_id),
    FOREIGN KEY(user_id) REFERENCES userr(user_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    UNIQUE(user_id, seller_id)
);

CREATE TABLE contact (
    contact_id SERIAL NOT NULL,
    buyer_id integer NOT NULL,
    seller_id integer NOT NULL,
    phone_number varchar(11) NOT NULL,
    PRIMARY KEY(contact_id),
    FOREIGN KEY(seller_id) REFERENCES seller(seller_id),
    FOREIGN KEY(buyer_id) REFERENCES buyer(buyer_id),
    UNIQUE(buyer_id, seller_id)
 );
