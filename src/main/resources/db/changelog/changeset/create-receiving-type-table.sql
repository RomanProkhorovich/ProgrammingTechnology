--changeset Roma: create_receiving_table
create table Receiving-type(
 id SERIAL PRIMARY KEY,
 name varchar(50) UNIQUE NOT NULL
)