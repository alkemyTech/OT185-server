drop table if exists slide;
drop table if exists slide_organization;

create table slide
(
    slide_id bigint       not null auto_increment,
    image_url VARCHAR(255) not null,
    text VARCHAR(255) not null,
    number INTEGER not null,
    organization_id bigint not null,
    primary key (slide_id)
) engine = InnoDB;

create table slide_organization
(
    slide_id bigint not null,
    organization_id   bigint not null,
    primary key (slide_id, organization_id),
    key organization_id (organization_id),
    constraint fk_slide_organization_1
        foreign key (slide_id) references slide (slide_id),
    constraint fk_slide_organization_2
        foreign key (organization_id) references organization (organization_id)
) engine = InnoDB;
