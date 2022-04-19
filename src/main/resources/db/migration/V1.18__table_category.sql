drop table if exists category;

create table category
(
    category_id bigint       not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    name       varchar(255) not null,
    description       varchar(255)  null,
    image varchar(255) null,
    primary key (category_id)
) engine = InnoDB;
