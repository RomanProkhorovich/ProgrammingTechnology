--changeset Vika:7
CREATE TABLE Restaurant
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE,
	address VARCHAR(200) NOT NULL,
	manager_id INTEGER REFERENCES Customer (id) UNIQUE,
	cuisine_type_id INTEGER REFERENCES Cuisine_type (id) NOT NULL,
	seats_number INTEGER,
	menu_id INTEGER REFERENCES Menu (id)  NOT NULL
);
