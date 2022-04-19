drop table if exists organization;

create table organization
(
    organization_id bigint  not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    name       varchar(255) not null,
    image      varchar(255) not null,
    address    varchar(255) null,
    phone           INTEGER  null,
    email      varchar(255) not null,
    welcome_text text        not null,
    about_us_text text        null,
    primary key (organization_id)
) engine = InnoDB;
