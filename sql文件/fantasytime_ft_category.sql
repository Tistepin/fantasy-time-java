create table ft_category
(
    cat_id        bigint auto_increment comment '分类id'
        primary key,
    name          char(50)         null comment '分类名称',
    sort          int              null comment '排序',
    works_type    tinyint          null comment '作品分类 1-漫画 2-小说',
    icon          char(255)        null comment '图标地址',
    delete_status bigint default 1 not null comment '逻辑删除状态 0-已删除 1-未删除'
)
    comment '作品类型';

INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (1, '全部', 1, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (2, '科幻', 2, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (3, '热血', 3, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (4, '搞笑', 4, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (5, '爱情', 5, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (6, '神魔', 6, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (7, '魔法', 7, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (8, '推理', 8, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (9, '科幻', 9, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (10, '治愈', 10, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (11, '运动', 11, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (12, '其他', 12, 1, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (13, '全部', 1, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (14, '科幻', 2, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (15, '热血', 3, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (16, '搞笑', 4, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (17, '爱情', 5, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (18, '神魔', 6, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (19, '魔法', 7, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (20, '推理', 8, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (21, '科幻', 9, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (22, '治愈', 10, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (23, '运动', 11, 2, null, 1);
INSERT INTO fantasytime.ft_category (cat_id, name, sort, works_type, icon, delete_status) VALUES (24, '其他', 12, 2, null, 1);