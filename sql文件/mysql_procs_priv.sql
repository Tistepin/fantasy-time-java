create table procs_priv
(
    Host         char(255) charset ascii                                default ''                not null,
    Db           char(64)                                               default ''                not null,
    User         char(32)                                               default ''                not null,
    Routine_name char(64) charset utf8                                  default ''                not null,
    Routine_type enum ('FUNCTION', 'PROCEDURE')                                                   not null,
    Grantor      varchar(288)                                           default ''                not null,
    Proc_priv    set ('Execute', 'Alter Routine', 'Grant') charset utf8 default ''                not null,
    Timestamp    timestamp                                              default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    primary key (Host, Db, User, Routine_name, Routine_type)
)
    comment 'Procedure privileges' collate = utf8_bin;

create index Grantor
    on procs_priv (Grantor);

