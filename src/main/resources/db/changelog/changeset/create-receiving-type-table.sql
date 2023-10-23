--changeset Roma: createReceivingTable
create table Receiving_type(
 id SERIAL PRIMARY KEY,
 name varchar(50) UNIQUE NOT NULL
)

insert into Receiving_type (name)
values
("В зале"),
("Самовывоз"),
("Доставка"),