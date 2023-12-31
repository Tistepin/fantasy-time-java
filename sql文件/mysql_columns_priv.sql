create table columns_priv
(
    Host        char(255) charset ascii                                       default ''                not null,
    Db          char(64)                                                      default ''                not null,
    User        char(32)                                                      default ''                not null,
    Table_name  char(64)                                                      default ''                not null,
    Column_name char(64)                                                      default ''                not null,
    Timestamp   timestamp                                                     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    Column_priv set ('Select', 'Insert', 'Update', 'References') charset utf8 default ''                not null,
    primary key (Host, Db, User, Table_name, Column_name)
)
    comment 'Column privileges' collate = utf8_bin;

