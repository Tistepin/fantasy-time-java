create table ft_roles
(
    id            bigint auto_increment comment 'id'
        primary key,
    role_name     varchar(50)                        not null comment '角色名称',
    role          varchar(50)                        not null comment '角色',
    create_time   datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time     datetime                           null comment '修改时间',
    delete_status bigint   default 1                 not null comment '逻辑删除状态 0-已删除 1-未删除',
    constraint idx_user_role
        unique (role_name, role)
)
    comment '角色信息表';

INSERT INTO fantasytime.ft_roles (id, role_name, role, create_time, edit_time, delete_status) VALUES (1, '管理员', 'ROLE_admin', '2023-01-05 14:45:34', null, 1);