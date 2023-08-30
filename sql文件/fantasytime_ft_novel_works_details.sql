create table ft_novel_works_details
(
    id                 bigint auto_increment comment 'id'
        primary key,
    works_id           bigint                             null comment '作品id',
    novel_chapter_id   char(20)                           null comment '小说章节ID 第几章',
    novel_chapter_name varchar(64)                        null comment '小说章节名字',
    novel_pages        char(20)                           null comment '漫画页数',
    create_time        datetime default CURRENT_TIMESTAMP null comment '注册时间',
    edit_time          datetime                           null comment '修改时间',
    delete_status      bigint   default 1                 not null comment '逻辑删除状态 0-已删除 1-未删除'
)
    comment '小说作品章节数量';

