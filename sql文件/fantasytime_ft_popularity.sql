create table ft_popularity
(
    id                          bigint auto_increment comment 'id'
        primary key,
    works_id                    bigint                             null comment '作品id',
    name                        char(50)                           null comment '作品名称',
    works_type                  tinyint                            null comment '作品类型 1-漫画 2-小说 3-插图',
    works_status                tinyint                            null comment '作品状态 1-更新中 2-完结',
    works_popularity_count      bigint                             null comment '总人气 观看十章 加一',
    works_popularity_today      bigint                             null comment '今天的人气 每个用户每天看一章加一 第二天清零',
    works_popularity_three_days bigint                             null comment '三天的人气 每个用户每天看一章加一 第四天天清零',
    works_popularity_thisWeek   bigint                             null comment '本周的人气 每个用户每天看一章加一 下一周清零',
    works_popularity_thisMonth  bigint                             null comment '本月的人气 每个用户每天看一章加一 下一月清零',
    delete_status               bigint   default 1                 null comment '逻辑删除 0-已删除 1-未删除',
    create_time                 datetime default CURRENT_TIMESTAMP not null comment '注册时间',
    edit_time                   datetime default CURRENT_TIMESTAMP null comment '修改时间'
)
    comment '作品人气';

INSERT INTO fantasytime.ft_popularity (id, works_id, name, works_type, works_status, works_popularity_count, works_popularity_today, works_popularity_three_days, works_popularity_thisWeek, works_popularity_thisMonth, delete_status, create_time, edit_time) VALUES (1, 1, '刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~', 1, 1, 5809, 50, 959, 1509, 1709, 1, '2023-01-06 08:03:28', '2023-02-15 00:00:01');
INSERT INTO fantasytime.ft_popularity (id, works_id, name, works_type, works_status, works_popularity_count, works_popularity_today, works_popularity_three_days, works_popularity_thisWeek, works_popularity_thisMonth, delete_status, create_time, edit_time) VALUES (7, 24, '为美好的世界献上祝福', 1, 1, 1, 0, 1, 1, 1, 1, '2023-07-08 08:31:38', '2023-07-08 08:37:07');