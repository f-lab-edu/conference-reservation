drop table if exists USER;

drop table if exists CORP;

create table USER
(
    ID        varchar(20),
    PASSWORD  varchar(100),
    USER_NAME varchar(50),
    PRIMARY KEY (ID)
);

create table CORP
(
    ID        varchar(20),
    PASSWORD  varchar(100),
    CORP_NAME varchar(50),
    CORP_EMAIL varchar(50),
    CORP_PHONE_NUMBER varchar(50),
    CORP_REG_NUMBER varchar(50),
    PRIMARY KEY (ID)
);
