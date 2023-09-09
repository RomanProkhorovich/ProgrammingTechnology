--changeset Vika:6
CREATE TABLE app_Order
(
	id SERIAL PRIMARY KEY,
	order_time TIMESTAMP NOT NULL,
	status_id INTEGER REFERENCES Status (id) ,
	delivery_time TIMESTAMP DEFAULT NULL,
	courier_id INTEGER REFERENCES Customer (id) ,
	client_id INTEGER REFERENCES Customer (id) ,
	receiving_type INTEGER REFERENCES Receiving_type(id) NOT NULL,
	address varchar(255) NOT NULL
);