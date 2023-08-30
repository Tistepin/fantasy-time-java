create table ft_user
(
    id            bigint auto_increment comment 'id'
        primary key,
    username      char(64)                           null comment '用户名',
    password      varchar(64)                        null comment '密码',
    nickname      varchar(64)                        null comment '昵称',
    phone         varchar(20)                        null comment '手机号码',
    email         varchar(64)                        null comment '邮箱',
    header        varchar(500)                       null comment '头像',
    gender        tinyint                            null comment '性别',
    birth         date                               null comment '生日',
    city          varchar(500)                       null comment '所在城市',
    job           varchar(255)                       null comment '职业',
    sign          varchar(255)                       null comment '个性签名',
    source_type   tinyint                            null comment '用户来源',
    status        tinyint                            null comment '启用状态',
    create_time   datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time     datetime default CURRENT_TIMESTAMP null comment '修改时间',
    delete_status bigint   default 1                 not null comment '逻辑删除状态 0-已删除 1-未删除',
    constraint email
        unique (email),
    constraint phone
        unique (phone)
)
    comment '用户信息表';

INSERT INTO fantasytime.ft_user (id, username, password, nickname, phone, email, header, gender, birth, city, job, sign, source_type, status, create_time, edit_time, delete_status) VALUES (1, '徐国纪', '202cb962ac59075b964b07152d234b70', '提示特本', '18574933063', '2532878663@qq.com', null, 1, '2001-05-02', null, null, '测试', 1, 1, '2023-01-05 14:42:41', null, 1);
INSERT INTO fantasytime.ft_user (id, username, password, nickname, phone, email, header, gender, birth, city, job, sign, source_type, status, create_time, edit_time, delete_status) VALUES (2, '徐国纪2', '202cb962ac59075b964b07152d234b70', '提示特本2', '17680375392', '2222222222@qq.com', '', 1, '2001-05-02', '', '', '阿萨实打实大大大大', 1, 1, '2023-01-05 14:42:41', null, 1);
INSERT INTO fantasytime.ft_user (id, username, password, nickname, phone, email, header, gender, birth, city, job, sign, source_type, status, create_time, edit_time, delete_status) VALUES (6, 'Niko', '202cb962ac59075b964b07152d234b70', 'Test', '17680375393', null, '', 1, '2001-05-02', null, null, null, 1, null, '2023-02-21 10:15:31', '2023-02-21 10:15:31', 1);
INSERT INTO fantasytime.ft_user (id, username, password, nickname, phone, email, header, gender, birth, city, job, sign, source_type, status, create_time, edit_time, delete_status) VALUES (7, 'Niko2', '202cb962ac59075b964b07152d234b70', 'Test2', '17680375394', null, '', 1, '2001-05-02', null, null, null, 1, null, '2023-02-21 10:19:32', '2023-02-21 10:19:32', 1);
INSERT INTO fantasytime.ft_user (id, username, password, nickname, phone, email, header, gender, birth, city, job, sign, source_type, status, create_time, edit_time, delete_status) VALUES (8, 'Test4', '202cb962ac59075b964b07152d234b70', 'Niko3', '17680375395', null, '', 1, '2001-05-02', null, null, null, 1, null, '2023-02-21 10:21:38', '2023-02-21 10:21:38', 1);