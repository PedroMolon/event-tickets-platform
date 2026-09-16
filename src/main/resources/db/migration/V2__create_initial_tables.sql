CREATE TABLE tb_events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    location VARCHAR(150) NOT NULL,
    capacity INT NOT NULL,
    available_tickets INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE tb_customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE tb_tickets (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(36) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    customer_id BIGINT,
    event_id BIGINT,

    FOREIGN KEY (customer_id) REFERENCES tb_customers(id),
    FOREIGN KEY (event_id) REFERENCES tb_events(id)
);