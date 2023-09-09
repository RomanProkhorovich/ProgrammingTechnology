--changeset Vika:2
CREATE TABLE Customer
(
	id SERIAL PRIMARY KEY,
	lastname VARCHAR(100) NOT NULL,
	firstname VARCHAR(100) NOT NULL,
	surname VARCHAR(100),
	email VARCHAR(100) UNIQUE,
	address VARCHAR(200) DEFAULT NULL,
	role_id INTEGER REFERENCES Role (id)
);
