DROP TABLE user IF EXISTS;

CREATE TABLE user {
    id varchar(256) not null primary key,
    password varchar(256) not null,
    userName varchar(256)
};