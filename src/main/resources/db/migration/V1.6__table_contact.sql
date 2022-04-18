drop table if exists contacts;


create table contacts
(
--  Fields
    id_contact bigint  not null auto_increment,
    name    varchar (100) not null,
    phone   varchar (100) not null,
    email   varchar (100) not null,
    message varchar (255) not null,
--  SoftDelete
    deleted_at bit not null,

     primary key (id_contact)
) engine = InnoDB;