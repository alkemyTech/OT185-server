drop table if exists members;

create table members (
    member_id bigint not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    name varchar(255) not null,
    facebookUrl varchar(255) null,
    instagramUrl varchar(255) null,
    linkedinUrl varchar(255) null,
    image varchar(255) not null,
    description varchar(255) null,
    primary key (member_id)
) engine = InnoDB
