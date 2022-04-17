drop table if exists activities;

create table activities
(
   activity_id    bigint       not null,
    name  varchar(255)  not null,
    content  varchar(255)  not null,
    image  varchar(255)  not null,
    primary key (activity_id)
) engine = InnoDB;