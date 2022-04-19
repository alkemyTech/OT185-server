drop table if exists activity;

create table activity
(
   activity_id    bigint       not null,
   name  varchar(255)  not null,
   content  varchar(255)  not null,
   image  varchar(255)  not null,

   created_at datetime(6)  not null,
   is_active  bit          not null,
   updated_at datetime(6)  null,

   primary key (activity_id)
) engine = InnoDB;