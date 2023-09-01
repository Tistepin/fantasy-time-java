create table replication_group_configuration_version
(
    name    char(255) charset ascii not null comment 'The configuration name.'
        primary key,
    version bigint unsigned         not null comment 'The version of the configuration name.'
)
    comment 'The group configuration version.';

INSERT INTO mysql.replication_group_configuration_version (name, version) VALUES ('replication_group_member_actions', 1);