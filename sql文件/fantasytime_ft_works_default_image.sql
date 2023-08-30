create table ft_works_default_image
(
    id                  bigint auto_increment comment 'id'
        primary key,
    works_id            bigint                             not null comment '作品id',
    works_default_image varchar(128)                       not null comment '图片服务请求数据位置',
    review_status       bigint   default 1                 not null comment '审核状态 0-审核中 1-审核成功 2-审核失败',
    delete_status       bigint   default 1                 not null comment '逻辑删除状态 0-删除 1-存在',
    create_time         datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time           datetime                           null comment '修改时间'
)
    comment '作品封面图片服务请求路径存储';

INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (1, 1, 'C:\\\\Users\\\\F3863479\\\\Desktop\\\\Test\\\\刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~\\\\0\\\\1.jpg', 1, 1, '2023-02-06 10:44:09', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (2, 2, 'C:\\\\Users\\\\F3863479\\\\Desktop\\\\Test\\\\刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~\\\\0\\\\1.jpg', 1, 1, '2023-02-06 10:44:20', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (3, 11, 'C:\\\\Users\\\\F3863479\\\\Desktop\\\\Test\\\\刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~\\\\0\\\\1.jpg', 1, 1, '2023-02-06 10:44:20', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (4, 12, 'C:\\\\Users\\\\F3863479\\\\Desktop\\\\Test\\\\刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~\\\\0\\\\1.jpg', 1, 1, '2023-02-06 14:03:41', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (5, 13, 'C:\\\\Users\\\\F3863479\\\\Desktop\\\\Test\\\\刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~\\\\0\\\\1.jpg', 1, 1, '2023-02-06 14:14:49', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (6, 14, 'C:\\\\Users\\\\F3863479\\\\Desktop\\\\Test\\\\刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~\\\\0\\\\1.jpg', 1, 1, '2023-02-06 16:07:55', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (7, 22, 'C:\\Users\\F3863479\\Desktop\\Test\\测试\\封面\\cpuzheng.jpg', 0, 1, '2023-06-19 20:37:21', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (8, 23, 'C:\\Users\\F3863479\\Desktop\\Test\\ccccc\\封面\\Snipaste_2023-05-09_14-24-03.jpg', 0, 1, '2023-06-20 11:06:41', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (9, 24, 'C:\\Users\\F3863479\\Desktop\\Test\\为美好的世界献上祝福\\封面\\1.jpg', 1, 1, '2023-06-20 15:25:55', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (10, 25, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试\\封面/1.png', 1, 1, '2023-06-30 09:12:03', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (11, 26, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试2\\封面/1.jpeg', 1, 1, '2023-06-30 14:16:16', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (12, 27, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试3\\封面/1.jpeg', 1, 1, '2023-06-30 14:16:59', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (13, 28, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试4\\封面/1.jpg', 1, 1, '2023-06-30 14:20:43', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (14, 29, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试6\\封面/1.jpeg', 1, 1, '2023-07-01 08:31:12', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (15, 31, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试1\\封面/1.jpeg', 1, 1, '2023-08-23 16:20:37', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (16, 32, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试2\\封面/1.jpeg', 1, 1, '2023-08-23 16:30:37', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (17, 33, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试3\\封面/1.jpeg', 1, 1, '2023-08-23 16:31:08', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (18, 34, 'C:\\Users\\F3863479\\Desktop\\Test\\封面/1.jpg', 1, 1, '2023-08-23 16:31:40', null);
INSERT INTO fantasytime.ft_works_default_image (id, works_id, works_default_image, review_status, delete_status, create_time, edit_time) VALUES (19, 35, 'C:\\Users\\F3863479\\Desktop\\Test\\插图测试5\\封面/1.png', 1, 1, '2023-08-23 16:32:05', null);