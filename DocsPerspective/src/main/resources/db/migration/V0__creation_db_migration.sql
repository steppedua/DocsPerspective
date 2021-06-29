create table attributes
(
    attribute_id   bigserial unique   not null,
    attribute_name varchar(15) unique not null,
    primary key (attribute_id)
);

create table documents_folder
(
    document_folder_id   bigserial unique   not null,
    document_folder_name varchar(15) unique not null,
    primary key (document_folder_id)
);

create table privileges
(
    privilege_id   bigserial unique   not null,
    privilege_name varchar(15) unique not null,
    primary key (privilege_id)
);

create table roles
(
    role_id   bigserial unique   not null,
    role_name varchar(15) unique not null,
    primary key (role_id)
);

create table users
(
    user_id       varchar(255) unique not null,
    creation_date timestamp           not null,
    login         varchar(15) unique  not null,
    password      varchar(15)         not null,
    user_name     varchar(15)         not null,
    primary key (user_id)
);

create table roles_privileges
(
    role_id      int8 unique not null,
    privilege_id int8 unique not null,
    primary key (role_id, privilege_id),
    foreign key (role_id) references roles (role_id),
    foreign key (privilege_id) references privileges (privilege_id)
);

create table document
(
    document_id        varchar(255) unique not null,
    creation_date      timestamp           not null,
    document_name      varchar(15) unique  not null,
    document_type      varchar(20)         not null,
    document_data      bytea               not null,
    document_folder_id int8                not null,
    user_id            varchar(255)        not null,
    primary key (document_id),
    foreign key (user_id) references users (user_id),
    foreign key (document_folder_id) references documents_folder (document_folder_id)
);

create table document_attributes
(
    document_id  varchar(255) not null,
    attribute_id bigserial    not null,
    primary key (document_id, attribute_id),
    foreign key (document_id) references document (document_id),
    foreign key (attribute_id) references attributes (attribute_id)
);

create table users_roles
(
    user_id varchar(255) not null,
    role_id bigserial    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (user_id),
    foreign key (role_id) references roles (role_id)
);