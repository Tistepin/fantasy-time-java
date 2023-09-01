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

