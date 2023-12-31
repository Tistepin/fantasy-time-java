create table server_cost
(
    cost_name     varchar(64)                         not null
        primary key,
    cost_value    float                               null,
    last_update   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    comment       varchar(1024)                       null,
    default_value float as ((case `cost_name`
                                 when _utf8mb4'disk_temptable_create_cost' then 20.0
                                 when _utf8mb4'disk_temptable_row_cost' then 0.5
                                 when _utf8mb4'key_compare_cost' then 0.05
                                 when _utf8mb4'memory_temptable_create_cost' then 1.0
                                 when _utf8mb4'memory_temptable_row_cost' then 0.1
                                 when _utf8mb4'row_evaluate_cost' then 0.1
                                 else NULL end))
)
    charset = utf8;

INSERT INTO mysql.server_cost (cost_name, cost_value, last_update, comment) VALUES ('disk_temptable_create_cost', null, '2022-04-25 19:51:21', null);
INSERT INTO mysql.server_cost (cost_name, cost_value, last_update, comment) VALUES ('disk_temptable_row_cost', null, '2022-04-25 19:51:21', null);
INSERT INTO mysql.server_cost (cost_name, cost_value, last_update, comment) VALUES ('key_compare_cost', null, '2022-04-25 19:51:21', null);
INSERT INTO mysql.server_cost (cost_name, cost_value, last_update, comment) VALUES ('memory_temptable_create_cost', null, '2022-04-25 19:51:21', null);
INSERT INTO mysql.server_cost (cost_name, cost_value, last_update, comment) VALUES ('memory_temptable_row_cost', null, '2022-04-25 19:51:21', null);
INSERT INTO mysql.server_cost (cost_name, cost_value, last_update, comment) VALUES ('row_evaluate_cost', null, '2022-04-25 19:51:21', null);