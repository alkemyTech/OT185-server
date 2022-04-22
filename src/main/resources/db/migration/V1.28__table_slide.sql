drop table if exists slide;

create table slide
(
    slide_id bigint       not null auto_increment,
    image_url VARCHAR(255) not null,
    text VARCHAR(255) not null,
    number INTEGER not null,
    organization_id bigint not null,
    primary key (slide_id),
    key organization_id (organization_id),
    constraint fk_organization_slide
        foreign key (organization_id) references organization (organization_id)
) engine = InnoDB;