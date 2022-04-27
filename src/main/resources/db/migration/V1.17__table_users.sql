drop table if exists user;

create table user
(
    user_id     bigint  not null auto_increment,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    email       varchar(255) not null,
    password    varchar(255) not null,
    photo       varchar(255) not null,
    created_at  datetime(6) not null,
    updated_at  datetime(6) null,
    is_active   bit          not null,
    role_id     bigint  not null,
    primary key (user_id),
    key role_id (role_id),
    constraint fk_role
    foreign key (role_id) references role (role_id)

) engine = InnoDB;
