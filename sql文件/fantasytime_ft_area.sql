create table ft_area
(
    id            bigint auto_increment comment 'id'
        primary key,
    name          char(50)                           null comment '国家名字',
    delete_status bigint   default 1                 not null comment '逻辑删除状态 0-已删除 1-未删除',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    edit_time     datetime                           null comment '修改时间'
)
    comment '国家区域';

INSERT INTO fantasytime.ft_area (id, name, delete_status, create_time, edit_time) VALUES (1, '全部', 1, '2023-02-08 14:42:09', null);
INSERT INTO fantasytime.ft_area (id, name, delete_status, create_time, edit_time) VALUES (2, '日本', 1, '2023-02-08 14:42:09', null);
INSERT INTO fantasytime.ft_area (id, name, delete_status, create_time, edit_time) VALUES (3, '大陆', 1, '2023-02-08 14:42:09', null);
INSERT INTO fantasytime.ft_area (id, name, delete_status, create_time, edit_time) VALUES (4, '欧美', 1, '2023-02-08 14:42:09', null);