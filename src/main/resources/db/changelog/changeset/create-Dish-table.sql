--changeset Vika:3
CREATE TABLE Dish
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	price MONEY NOT NULL,
	calories REAL NOT NULL,
	weight REAL NOT NULL
);

