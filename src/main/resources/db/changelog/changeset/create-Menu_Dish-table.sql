--changeset Vika:5
CREATE TABLE Menu_Dish
(
	id SERIAL PRIMARY KEY,
	dish_id INTEGER REFERENCES Dish (id),
	menu_id INTEGER REFERENCES Menu (id),
	UNIQUE(Dish_id, Menu_id)
);