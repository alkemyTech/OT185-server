drop table if exists contact;


create table contact
(
    contact_id bigint     not null auto_increment,
    name    varchar (100) not null,
    phone   varchar (100) not null,
    email   varchar (100) not null,
    message varchar (255) not null,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,

     primary key (contact_id)

>>>>>>> main:src/main/resources/db/migration/V1.53__schema_contact.sql
) engine = InnoDB;