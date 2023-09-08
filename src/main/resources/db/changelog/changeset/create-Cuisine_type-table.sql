--changeset Vika:1
CREATE TABLE Cuisine_type
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE
);

INSERT INTO Role (name)
VALUES
('Итальянская'),
('Французская'),
('Японская'),
('Мексиканская'),
('Китайская'),
('Индийская'),
('Испанская'),
('Тайская'),
('Американская'),
('Греческая')