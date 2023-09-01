create table help_category
(
    help_category_id   smallint unsigned not null
        primary key,
    name               char(64)          not null,
    parent_category_id smallint unsigned null,
    url                text              not null,
    constraint name
        unique (name)
)
    comment 'help categories' charset = utf8;

INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (0, 'Contents', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (1, 'Help Metadata', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (2, 'Data Types', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (3, 'Administration', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (4, 'Functions', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (5, 'Enterprise Encryption Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (6, 'Language Structure', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (7, 'Geographic Features', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (8, 'MBR', 7, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (9, 'WKT', 7, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (10, 'Comparison Operators', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (11, 'Logical Operators', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (12, 'Flow Control Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (13, 'Numeric Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (14, 'Date and Time Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (15, 'String Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (16, 'Cast Functions and Operators', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (17, 'XML', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (18, 'Bit Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (19, 'Encryption Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (20, 'Locking Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (21, 'Information Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (22, 'Spatial Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (23, 'WKT Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (24, 'WKB Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (25, 'Geometry Constructors', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (26, 'Geometry Property Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (27, 'Point Property Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (28, 'LineString Property Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (29, 'Polygon Property Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (30, 'GeometryCollection Property Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (31, 'Geometry Relation Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (32, 'MBR Functions', 22, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (33, 'GTID', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (34, 'Aggregate Functions and Modifiers', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (35, 'GROUP BY Functions and Modifiers', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (36, 'Window Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (37, 'Performance Schema Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (38, 'Internal Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (39, 'Miscellaneous Functions', 4, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (40, 'Data Definition', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (41, 'Data Manipulation', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (42, 'Transactions', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (43, 'Replication Statements', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (44, 'Prepared Statements', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (45, 'Compound Statements', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (46, 'Account Management', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (47, 'Table Maintenance', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (48, 'Loadable Functions', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (49, 'Components', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (50, 'Plugins', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (51, 'Utility', 0, '');
INSERT INTO mysql.help_category (help_category_id, name, parent_category_id, url) VALUES (52, 'Storage Engines', 0, '');