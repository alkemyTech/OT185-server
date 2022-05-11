drop table if exists news;

create table news
(
    news_id bigint       not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    name       varchar(255) not null,
    content    varchar(255) not null,
    image      varchar(255) not null,
    category_id bigint      not null,
    primary key (news_id),
    key category_id (category_id),
    constraint fk_category_1
        foreign key (category_id) references category (category_id)
) engine = InnoDB;