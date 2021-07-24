drop table if exists USER;

create table USER
(
    ID              varchar(20) NOT NULL ,
    PASSWORD        varchar(256) NOT NULL ,
    USER_NAME       varchar(50),
    EMAIL           varchar(50),
    PHONE_NUMBER    varchar(50),
    ORGANIZATION    varchar(50),
    GENDER          varchar(20),
    DATE_BIRTH      varchar(20),

    PRIMARY KEY (ID)
);