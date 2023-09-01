create table password_history
(
    Host               char(255) charset ascii default ''                   not null,
    User               char(32)                default ''                   not null,
    Password_timestamp timestamp(6)            default CURRENT_TIMESTAMP(6) not null,
    Password           text                                                 null,
    primary key (Host, User, Password_timestamp)
)
    comment 'Password history for user accounts' collate = utf8_bin;

