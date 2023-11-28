--changeset Vika:9
CREATE TABLE Status
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(15) UNIQUE
);

INSERT INTO Status (name)
VALUES
('В обработке'),
('Готовится'),
('Готово'),
('В доставке'),
('Получено'),
('Отменено');
