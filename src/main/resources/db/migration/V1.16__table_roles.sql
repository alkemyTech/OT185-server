drop table if exists role;

create table role
(
    role_id     bigint  not null auto_increment,
    name        varchar(255) not null,
    description varchar(255),
    created_at  datetime(6) not null,
    primary key (role_id)
) engine = InnoDB;