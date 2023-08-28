create table ft_works_upload
(
    id            bigint auto_increment comment 'id'
        primary key,
    user_id       bigint                             not null comment '用户ID',
    works_id      bigint                             not null comment '作品id',
    review_status bigint   default 1                 not null comment '审核状态 0-审核中 1-审核成功 2-审核失败',
    delete_status bigint   default 1                 not null comment '逻辑删除状态 0-删除 1-存在',
    create_time   datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time     datetime                           null comment '修改时间'
)
    comment '作品上传信息';

INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (3, 1, 7, 0, 1, '2023-01-10 15:35:30', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (4, 1, 1, 1, 1, '2023-01-10 15:58:23', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (8, 1, 11, 1, 1, '2023-02-02 10:07:21', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (9, 1, 12, 1, 1, '2023-02-06 14:03:26', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (10, 1, 13, 1, 1, '2023-02-06 14:14:48', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (11, 1, 14, 1, 1, '2023-02-06 16:07:55', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (12, 1, 22, 0, 1, '2023-06-19 20:37:21', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (13, 1, 23, 0, 1, '2023-06-20 11:06:41', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (14, 1, 24, 1, 1, '2023-06-20 15:25:55', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (15, 1, 25, 1, 1, '2023-06-30 09:12:03', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (16, 1, 26, 1, 1, '2023-06-30 14:16:16', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (17, 1, 27, 1, 1, '2023-06-30 14:16:59', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (18, 1, 28, 1, 1, '2023-06-30 14:20:43', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (19, 1, 29, 1, 1, '2023-07-01 08:31:11', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (20, 1, 31, 1, 1, '2023-08-23 16:20:36', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (21, 1, 32, 1, 1, '2023-08-23 16:30:37', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (22, 1, 33, 1, 1, '2023-08-23 16:31:08', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (23, 1, 34, 1, 1, '2023-08-23 16:31:40', null);
INSERT INTO fantasytime.ft_works_upload (id, user_id, works_id, review_status, delete_status, create_time, edit_time) VALUES (24, 1, 35, 1, 1, '2023-08-23 16:32:05', null);