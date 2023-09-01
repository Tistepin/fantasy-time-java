create table ft_role_user
(
    uid           bigint                             null comment '用户ID',
    rid           bigint                             null comment '角色ID',
    create_time   datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time     datetime                           null comment '修改时间',
    delete_status bigint   default 1                 not null comment '逻辑删除状态 0-已删除 1-未删除'
)
    comment '用户角色关联表';

INSERT INTO fantasytime.ft_role_user (uid, rid, create_time, edit_time, delete_status) VALUES (1, 1, '2023-01-05 14:39:26', null, 1);
INSERT INTO fantasytime.ft_role_user (uid, rid, create_time, edit_time, delete_status) VALUES (2, 1, '2023-02-13 15:43:06', null, 1);
INSERT INTO fantasytime.ft_role_user (uid, rid, create_time, edit_time, delete_status) VALUES (6, 1, '2023-02-21 10:15:31', null, 1);
INSERT INTO fantasytime.ft_role_user (uid, rid, create_time, edit_time, delete_status) VALUES (7, 1, '2023-02-21 10:19:32', null, 1);
INSERT INTO fantasytime.ft_role_user (uid, rid, create_time, edit_time, delete_status) VALUES (8, 1, '2023-02-21 10:21:38', null, 1);
INSERT INTO fantasytime.ft_role_user (uid, rid, create_time, edit_time, delete_status) VALUES (11, 1, '2023-08-29 15:53:21', null, 1);