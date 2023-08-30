create table ft_works_bookshelf
(
    id                bigint auto_increment comment 'id'
        primary key,
    user_id           bigint                             not null comment '用户ID',
    works_id          bigint                             not null comment '作品id',
    works_name        varchar(64)                        null comment '作品名',
    default_image     varchar(128)                       null comment '作品默认展示图片',
    works_renew       varchar(64)                        null comment '作品更新至多少',
    works_type        bigint                             null comment '作品类型 1-漫画 2-小说',
    works_creator     varchar(64)                        null comment '作品创作者',
    works_update_time datetime                           null comment '作品更新时间',
    delete_status     bigint   default 1                 not null comment '逻辑删除状态 0-删除 1-存在',
    create_time       datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time         datetime                           null comment '修改时间'
)
    comment '书架';

INSERT INTO fantasytime.ft_works_bookshelf (id, user_id, works_id, works_name, default_image, works_renew, works_type, works_creator, works_update_time, delete_status, create_time, edit_time) VALUES (1, 1, 1, '刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~', 'http://10.161.139.216/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=1', '3', 1, 'Test', '2023-06-21 09:55:15', 1, '2023-02-14 14:40:29', '2023-02-14 14:42:47');
INSERT INTO fantasytime.ft_works_bookshelf (id, user_id, works_id, works_name, default_image, works_renew, works_type, works_creator, works_update_time, delete_status, create_time, edit_time) VALUES (4, 2, 1, '刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~', 'http://10.161.139.216/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=1', '3', 1, 'Test', '2023-06-21 09:55:15', 1, '2023-02-14 14:40:29', '2023-02-14 14:42:47');
INSERT INTO fantasytime.ft_works_bookshelf (id, user_id, works_id, works_name, default_image, works_renew, works_type, works_creator, works_update_time, delete_status, create_time, edit_time) VALUES (11, 1, 24, '为美好的世界献上祝福', 'http://10.161.139.216/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=24', '3', 1, '未知', '2023-06-21 15:35:08', 1, '2023-07-07 17:14:04', '2023-07-07 17:16:54');