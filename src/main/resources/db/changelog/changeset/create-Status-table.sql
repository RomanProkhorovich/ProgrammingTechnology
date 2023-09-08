--changeset Vika:9
CREATE TABLE Status
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(15) UNIQUE
);

INSERT INTO Status (Name)
VALUES
('В обработке'),
('Готовится'),
('Готово'),
('В доставке'),
('Получено'),
('Отменено')
