drop table if exists member;

create table member (
    member_id bigint not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    name varchar(255) not null,
    facebook_url varchar(255) null,
    instagram_url varchar(255) null,
    linkedin_url varchar(255) null,
    image varchar(255) not null,
    description varchar(255) null,
    primary key (member_id)
) engine = InnoDB
