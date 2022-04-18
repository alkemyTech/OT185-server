drop table if exists contact;


create table contact
(
    id_contact bigint     not null auto_increment,
    name    varchar (100) not null,
    phone   varchar (100) not null,
    email   varchar (100) not null,
    message varchar (255) not null,
    deleted_at bit not null,

     primary key (id_contact)
) engine = InnoDB;