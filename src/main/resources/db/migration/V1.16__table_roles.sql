drop table if exists roles;

create table alkymer
(
    role_id bigint       not null auto_increment,
    name       varchar(255) not null,
    description       varchar(255),
    primary key (role_id)
) engine = InnoDB;