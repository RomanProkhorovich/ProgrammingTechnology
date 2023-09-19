--changeset Vika:6
CREATE TABLE Order_Dish
(
	id SERIAL PRIMARY KEY,
	dish_id INTEGER REFERENCES dish (id),
	Count INTEGER NOT NULL,
);