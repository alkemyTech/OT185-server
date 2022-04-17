drop table if exists contacts;


create table contacts
(
--  Fields
    contact_id bigint  not null auto_increment,
    name       varchar (100) not null,
    phone      varchar (100) not null,
    email      varchar (100) not null,
    message    varchar (255) not null,
--  SoftDelete
    deleted_at bool not null default false,

    constraint contacts_pk primary key (contact_id)
);