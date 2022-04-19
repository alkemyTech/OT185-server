drop table if exists activity;

create table activity
(
   activity_id    bigint       not null auto_increment,
    name  varchar(255)  not null,
    content  varchar(255)  not null,
    image  varchar(255)  not null,

    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    end_date   datetime(6)  null,
    start_date datetime(6)  not null,
    primary key (activity_id)
) engine = InnoDB;