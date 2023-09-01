create table tables_priv
(
    Host        char(255) charset ascii                                                                                                                                     default ''                not null,
    Db          char(64)                                                                                                                                                    default ''                not null,
    User        char(32)                                                                                                                                                    default ''                not null,
    Table_name  char(64)                                                                                                                                                    default ''                not null,
    Grantor     varchar(288)                                                                                                                                                default ''                not null,
    Timestamp   timestamp                                                                                                                                                   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    Table_priv  set ('Select', 'Insert', 'Update', 'Delete', 'Create', 'Drop', 'Grant', 'References', 'Index', 'Alter', 'Create View', 'Show view', 'Trigger') charset utf8 default ''                not null,
    Column_priv set ('Select', 'Insert', 'Update', 'References') charset utf8                                                                                               default ''                not null,
    primary key (Host, Db, User, Table_name)
)
    comment 'Table privileges' collate = utf8_bin;

create index Grantor
    on tables_priv (Grantor);

INSERT INTO mysql.tables_priv (Host, Db, User, Table_name, Grantor, Timestamp, Table_priv, Column_priv) VALUES ('localhost', 'mysql', 'mysql.session', 'user', 'boot@', '2022-04-25 19:51:23', 'Select', '');
INSERT INTO mysql.tables_priv (Host, Db, User, Table_name, Grantor, Timestamp, Table_priv, Column_priv) VALUES ('localhost', 'sys', 'mysql.sys', 'sys_config', 'root@localhost', '2022-04-25 19:51:24', 'Select', '');