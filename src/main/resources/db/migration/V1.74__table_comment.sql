drop table if exists comment;

create table comment
(
    comment_id bigint not null,
    comment_body text not null,
    news_id   bigint not null,
    primary key (comment_id),
    key news_id (news_id),
    constraint fk_news_id_1
        foreign key (news_id) references news (news_id),
) engine = InnoDB;