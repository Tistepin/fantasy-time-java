create table global_grants
(
    USER              char(32)                     default ''  not null,
    HOST              char(255) charset ascii      default ''  not null,
    PRIV              char(32) charset utf8        default ''  not null,
    WITH_GRANT_OPTION enum ('N', 'Y') charset utf8 default 'N' not null,
    primary key (USER, HOST, PRIV)
)
    comment 'Extended global grants' collate = utf8_bin;

INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.infoschema', 'localhost', 'AUDIT_ABORT_EXEMPT', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.infoschema', 'localhost', 'SYSTEM_USER', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'AUDIT_ABORT_EXEMPT', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'BACKUP_ADMIN', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'CLONE_ADMIN', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'CONNECTION_ADMIN', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'PERSIST_RO_VARIABLES_ADMIN', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'SESSION_VARIABLES_ADMIN', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'SYSTEM_USER', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.session', 'localhost', 'SYSTEM_VARIABLES_ADMIN', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.sys', 'localhost', 'AUDIT_ABORT_EXEMPT', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('mysql.sys', 'localhost', 'SYSTEM_USER', 'N');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'APPLICATION_PASSWORD_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'AUDIT_ABORT_EXEMPT', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'AUDIT_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'AUTHENTICATION_POLICY_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'BACKUP_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'BINLOG_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'BINLOG_ENCRYPTION_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'CLONE_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'CONNECTION_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'ENCRYPTION_KEY_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'FLUSH_OPTIMIZER_COSTS', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'FLUSH_STATUS', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'FLUSH_TABLES', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'FLUSH_USER_RESOURCES', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'GROUP_REPLICATION_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'GROUP_REPLICATION_STREAM', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'INNODB_REDO_LOG_ARCHIVE', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'INNODB_REDO_LOG_ENABLE', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'PASSWORDLESS_USER_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'PERSIST_RO_VARIABLES_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'REPLICATION_APPLIER', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'REPLICATION_SLAVE_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'RESOURCE_GROUP_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'RESOURCE_GROUP_USER', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'ROLE_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'SERVICE_CONNECTION_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'SESSION_VARIABLES_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'SET_USER_ID', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'SHOW_ROUTINE', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'SYSTEM_USER', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'SYSTEM_VARIABLES_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'TABLE_ENCRYPTION_ADMIN', 'Y');
INSERT INTO mysql.global_grants (USER, HOST, PRIV, WITH_GRANT_OPTION) VALUES ('root', 'localhost', 'XA_RECOVER_ADMIN', 'Y');