drop table if exists comment;

create table comment
(
    comment_id bigint not null,
    comment_body varchar(255) not null,
    is_active  bit          not null,
    created_at datetime(6)  not null,
    updated_at datetime(6)  null,
    end_date   datetime(6)  null,
    user_id   bigint not null,
    news_id   bigint not null,
    primary key (comment_id),
    key user_id (user_id),
    key news_id (news_id),
    constraint fk_user_id_1
        foreign key (user_id) references user (user_id),
    constraint fk_news_id_1
        foreign key (news_id) references news (news_id)
) engine = InnoDB;