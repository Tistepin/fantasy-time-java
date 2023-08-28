create table ft_works_watch_history
(
    id                                  bigint auto_increment comment 'id'
        primary key,
    user_id                             bigint                             not null comment '用户ID',
    works_id                            bigint                             not null comment '作品id',
    works_name                          varchar(64)                        null comment '作品名',
    default_image                       varchar(128)                       null comment '作品默认展示图片',
    works_history_viewing_chapter       bigint                             null comment '用户观看到第几章',
    works_history_viewing_chapter_id    bigint                             null comment '用户观看到第几章的ID',
    works_history_viewing_chapter_image bigint                             null comment '用户观看到第几章的的第几张图片',
    works_type                          tinyint                            null comment '作品分类 1-漫画 2-小说',
    works_status                        tinyint                            null comment '作品状态 1-更新中 2-完结',
    delete_status                       bigint   default 1                 not null comment '逻辑删除状态 0-删除 1-存在',
    create_time                         datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time                           datetime default CURRENT_TIMESTAMP null comment '修改时间'
)
    comment '观看历史记录';

INSERT INTO fantasytime.ft_works_watch_history (id, user_id, works_id, works_name, default_image, works_history_viewing_chapter, works_history_viewing_chapter_id, works_history_viewing_chapter_image, works_type, works_status, delete_status, create_time, edit_time) VALUES (2, 1, 1, '刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~', 'http://10.161.139.216/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=1', 1, 8, 23, 1, 1, 1, '2023-02-13 10:41:30', '2023-08-24 09:59:57');
INSERT INTO fantasytime.ft_works_watch_history (id, user_id, works_id, works_name, default_image, works_history_viewing_chapter, works_history_viewing_chapter_id, works_history_viewing_chapter_image, works_type, works_status, delete_status, create_time, edit_time) VALUES (3, 1, 24, '为美好的世界献上祝福', 'http://10.161.139.216/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=24', 3, 25, 9, 1, 1, 1, '2023-07-08 15:05:46', '2023-08-24 09:58:44');