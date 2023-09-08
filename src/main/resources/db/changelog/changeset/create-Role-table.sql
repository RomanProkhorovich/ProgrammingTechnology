--changeset Vika:8
CREATE TABLE Role
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(15) UNIQUE
);

INSERT INTO Role (Name)
VALUES
('Manager'),
('Courier'),
('Administrator'),
('Client')