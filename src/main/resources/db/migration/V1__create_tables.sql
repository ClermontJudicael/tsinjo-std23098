CREATE TABLE donor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE beneficiary (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE payment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    amount DOUBLE NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE donation (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    donor_id BIGINT,
    payment_id BIGINT,
    donation_date TIMESTAMP NOT NULL,
    FOREIGN KEY (donor_id) REFERENCES donor(id),
    FOREIGN KEY (payment_id) REFERENCES payment(id)
);

CREATE TABLE help (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    beneficiary_id BIGINT,
    payment_id BIGINT,
    aid_date TIMESTAMP NOT NULL,
    accident_description TEXT NOT NULL,
    FOREIGN KEY (beneficiary_id) REFERENCES beneficiary(id),
    FOREIGN KEY (payment_id) REFERENCES payment(id)
);