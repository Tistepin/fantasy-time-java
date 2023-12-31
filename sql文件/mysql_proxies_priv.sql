create table proxies_priv
(
    Host         char(255) charset ascii default ''                not null,
    User         char(32)                default ''                not null,
    Proxied_host char(255) charset ascii default ''                not null,
    Proxied_user char(32)                default ''                not null,
    With_grant   tinyint(1)              default 0                 not null,
    Grantor      varchar(288)            default ''                not null,
    Timestamp    timestamp               default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    primary key (Host, User, Proxied_host, Proxied_user)
)
    comment 'User proxy privileges' collate = utf8_bin;

create index Grantor
    on proxies_priv (Grantor);

INSERT INTO mysql.proxies_priv (Host, User, Proxied_host, Proxied_user, With_grant, Grantor, Timestamp) VALUES ('localhost', 'root', '', '', 1, 'boot@', '2022-04-25 19:51:21');