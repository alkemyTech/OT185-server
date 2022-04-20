drop table if exists testimonial;

create table testimonial
(
    testimonial_id bigint  not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    name       varchar(255) not null,
    image      varchar(255) not null,
    content    varchar(255) null,

    primary key (testimonial_id)
) engine = InnoDB;
