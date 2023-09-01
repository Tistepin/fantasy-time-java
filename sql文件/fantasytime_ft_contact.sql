create table ft_contact
(
    id         bigint unsigned auto_increment
        primary key,
    created_at datetime(3)     null,
    updated_at datetime(3)     null,
    deleted_at datetime(3)     null,
    owner_id   bigint unsigned null,
    target_id  bigint unsigned null,
    type       bigint          null,
    desc_info  longtext        null
)
    comment '好友关系表';

create index idx_ft_contact_deleted_at
    on ft_contact (deleted_at);

INSERT INTO fantasytime.ft_contact (id, created_at, updated_at, deleted_at, owner_id, target_id, type, desc_info) VALUES (1, null, null, null, 1, 2, 1, '1关注2');
INSERT INTO fantasytime.ft_contact (id, created_at, updated_at, deleted_at, owner_id, target_id, type, desc_info) VALUES (2, null, null, null, 2, 1, 1, '2关注1');