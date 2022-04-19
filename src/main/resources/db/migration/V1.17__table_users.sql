drop table if exists user;

create table user
(
    user_id     bigint  not null auto_increment,
    first_Name  varchar(255) not null,
    last_name   varchar(255) not null,
    email       varchar(255) not null,
    password    varchar(255) not null,
    photo       varchar(255) not null,
    created_at  datetime(6) not null,
    constraint fk_role
        foreign key (role_id) references role (role_id),
    primary key (user_id)
) engine = InnoDB;