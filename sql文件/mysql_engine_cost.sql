create table engine_cost
(
    engine_name   varchar(64)                         not null,
    device_type   int                                 not null,
    cost_name     varchar(64)                         not null,
    cost_value    float                               null,
    last_update   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    comment       varchar(1024)                       null,
    default_value float as ((case `cost_name`
                                 when _utf8mb4'io_block_read_cost' then 1.0
                                 when _utf8mb4'memory_block_read_cost' then 0.25
                                 else NULL end)),
    primary key (cost_name, engine_name, device_type)
)
    charset = utf8;

INSERT INTO mysql.engine_cost (engine_name, device_type, cost_name, cost_value, last_update, comment) VALUES ('default', 0, 'io_block_read_cost', null, '2022-04-25 19:51:21', null);
INSERT INTO mysql.engine_cost (engine_name, device_type, cost_name, cost_value, last_update, comment) VALUES ('default', 0, 'memory_block_read_cost', null, '2022-04-25 19:51:21', null);