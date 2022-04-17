drop table if exists news;

create table news
(
    news_id bigint       not null auto_increment,
    name       varchar(255) not null,
    content            text not null,
    image      varchar(255) not null,
    is_active  bit          not null,
    primary key (news_id)
    key category_id (category_id),
    constraint fk_category_1
         foreign key (category_id) references category (category_id),
) engine = InnoDB;