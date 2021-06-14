create table attributes
(
    attribute_id   bigserial unique   not null,
    attribute_name varchar(15) unique not null,
    primary key (attribute_id)
);

create table document
(
    document_id        varchar(255) unique not null,
    creation_date      timestamp           not null,
    document_name      varchar(15) unique  not null,
    document_data_id   varchar(255) unique not null,
    document_folder_id bigserial           not null,
    document_type_id   bigserial           not null,
    user_id            varchar(255)        not null,
    primary key (document_id)
);

create table document_attributes
(
    document_id  varchar(255) not null,
    attribute_id bigserial    not null,
    primary key (document_id, attribute_id)
);

create table document_data
(
    document_data_id varchar(255) unique not null,
    document_data    bytea unique        not null,
    primary key (document_data_id)
);

create table document_type
(
    documents_type_id   bigserial unique   not null,
    documents_type_name varchar(15) unique not null,
    primary key (documents_type_id)
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

create table roles_privileges
(
    role_id      bigserial unique not null,
    privilege_id bigserial unique not null,
    primary key (role_id, privilege_id)
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

create table users_roles
(
    user_id varchar(255) not null,
    role_id bigserial    not null,
    primary key (user_id, role_id)
);

alter table if exists document
    add constraint unique_document_data_id unique (document_data_id);

alter table if exists document
    add constraint fk_document_document_data foreign key (document_data_id) references document_data;

alter table if exists document
    add constraint fk_document_documents_folder foreign key (document_folder_id) references documents_folder;

alter table if exists document
    add constraint fk_document_document_type foreign key (document_type_id) references document_type;

alter table if exists document
    add constraint fk_document_users foreign key (user_id) references users;

alter table if exists document_attributes
    add constraint fk_document_attributes_attributes foreign key (attribute_id) references attributes;

alter table if exists document_attributes
    add constraint fk_document_attributes_document foreign key (document_id) references document;

alter table if exists roles_privileges
    add constraint fk_roles_privileges_privileges foreign key (privilege_id) references privileges;

alter table if exists roles_privileges
    add constraint fk_roles_privileges_roles foreign key (role_id) references roles;

alter table if exists users_roles
    add constraint fk_users_roles_roles foreign key (role_id) references roles;

alter table if exists users_roles
    add constraint fk_users_roles_users foreign key (user_id) references users;