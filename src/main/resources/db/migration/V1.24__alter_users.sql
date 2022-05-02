ALTER TABLE user MODIFY COLUMN photo varchar(255) null;
ALTER TABLE user MODIFY COLUMN email varchar(255) not null unique;
