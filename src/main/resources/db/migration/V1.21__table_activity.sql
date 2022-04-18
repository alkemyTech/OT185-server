drop table if exists activity;

create table activity
(
   activity_id    bigint       not null,
    name  varchar(255)  not null,
    content  varchar(255)  not null,
    image  varchar(255)  not null,
    primary key (activity_id)
) engine = InnoDB;