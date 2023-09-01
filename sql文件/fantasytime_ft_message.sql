create table ft_message
(
    id          bigint unsigned auto_increment
        primary key,
    created_at  datetime(3) null,
    updated_at  datetime(3) null,
    deleted_at  datetime(3) null,
    user_id     bigint      not null comment '发送者ID',
    target_id   bigint      not null comment '接受者ID',
    type        bigint      not null comment '发送类型',
    media       bigint      not null comment '消息类型',
    content     longtext    null comment '消息内存',
    create_time datetime(3) not null comment '创建时间',
    read_time   datetime(3) not null comment '读取时间',
    pic         longtext    null comment '图片',
    url         longtext    null comment '浏览地址',
    `desc`      longtext    null comment '备注',
    amount      bigint      null comment '其他数字统计'
)
    comment '消息表';

create index idx_ft_message_deleted_at
    on ft_message (deleted_at);

