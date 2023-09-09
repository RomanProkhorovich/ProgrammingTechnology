--changeset Vika:6
CREATE TABLE Order_Dish
(
	id SERIAL PRIMARY KEY,
	order_id INTEGER REFERENCES app_Order (id),
	dish_id INTEGER REFERENCES dish (id),
	Count INTEGER NOT NULL,
	UNIQUE(Order_id, Dish_id)
);