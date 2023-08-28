create table ft_cartoon_works_details
(
    id                   bigint auto_increment comment 'id'
        primary key,
    works_id             bigint                             null comment '作品id',
    cartoon_chapter_id   bigint                             null comment '漫画章节ID 第几话',
    cartoon_chapter_name varchar(64)                        null comment '漫画章节名字',
    cartoon_pages        char(20)                           null comment '漫画页数',
    create_time          datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time            datetime                           null comment '修改时间',
    delete_status        bigint   default 1                 not null comment '逻辑删除状态 0-已删除 1-未删除',
    review_status        bigint   default 0                 null comment '审核状态 0-审核中 1-审核成功 2-审核失败'
)
    comment '漫画作品章节数量';

INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (8, 1, 1, '第一话', '23', '2023-01-11 13:43:35', '2023-06-21 09:54:17', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (12, 11, 1, '自拍', '1', '2023-02-02 10:13:08', null, 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (15, 14, 1, '第一话', '6', '2023-02-06 16:18:57', null, 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (23, 24, 1, '笨蛋', '28', '2023-06-21 10:01:33', '2023-06-21 10:08:59', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (24, 24, 2, '笨蛋2', '23', '2023-06-21 13:50:38', '2023-06-21 13:52:34', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (25, 24, 3, '3', '22', '2023-06-21 15:34:54', '2023-06-21 15:35:07', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (33, 31, 1, '插图测试1', '1', '2023-08-23 16:29:06', '2023-08-23 16:29:06', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (34, 32, 1, '插图测试2', '1', '2023-08-23 16:32:22', '2023-08-23 16:32:23', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (35, 33, 1, '插图测试3', '1', '2023-08-23 16:32:27', '2023-08-23 16:32:27', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (36, 34, 1, '插图测试4', '1', '2023-08-23 16:32:29', '2023-08-23 16:32:30', 1, 1);
INSERT INTO fantasytime.ft_cartoon_works_details (id, works_id, cartoon_chapter_id, cartoon_chapter_name, cartoon_pages, create_time, edit_time, delete_status, review_status) VALUES (37, 35, 1, '插图测试5', '1', '2023-08-23 16:32:31', '2023-08-23 16:32:32', 1, 1);