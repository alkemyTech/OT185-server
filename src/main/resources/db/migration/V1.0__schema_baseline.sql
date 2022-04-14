drop table if exists alkymer;
drop table if exists skill;
drop table if exists alkymer_skill;

create table alkymer
(
    alkymer_id bigint       not null auto_increment,
    created_at datetime(6)  not null,
    is_active  bit          not null,
    updated_at datetime(6)  null,
    end_date   datetime(6)  null,
    name       varchar(255) not null,
    start_date datetime(6)  not null,
    primary key (alkymer_id)
) engine = InnoDB;

create table skill
(
    skill_id    bigint       not null,
    created_at  datetime(6)  not null,
    is_active   bit          not null,
    updated_at  datetime(6)  null,
    description varchar(255) null,
    name        varchar(255) null,
    primary key (skill_id)
) engine = InnoDB;

create table alkymer_skill
(
    alkymer_id bigint not null,
    skill_id   bigint not null,
    primary key (alkymer_id, skill_id),
    key skill_id (skill_id),
    constraint fk_alkymer_skill_1
        foreign key (alkymer_id) references alkymer (alkymer_id),
    constraint fk_alkymer_skill_2
        foreign key (skill_id) references skill (skill_id)
) engine = InnoDB;
