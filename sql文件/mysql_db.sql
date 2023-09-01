create table db
(
    Host                  char(255) charset ascii      default ''  not null,
    Db                    char(64)                     default ''  not null,
    User                  char(32)                     default ''  not null,
    Select_priv           enum ('N', 'Y') charset utf8 default 'N' not null,
    Insert_priv           enum ('N', 'Y') charset utf8 default 'N' not null,
    Update_priv           enum ('N', 'Y') charset utf8 default 'N' not null,
    Delete_priv           enum ('N', 'Y') charset utf8 default 'N' not null,
    Create_priv           enum ('N', 'Y') charset utf8 default 'N' not null,
    Drop_priv             enum ('N', 'Y') charset utf8 default 'N' not null,
    Grant_priv            enum ('N', 'Y') charset utf8 default 'N' not null,
    References_priv       enum ('N', 'Y') charset utf8 default 'N' not null,
    Index_priv            enum ('N', 'Y') charset utf8 default 'N' not null,
    Alter_priv            enum ('N', 'Y') charset utf8 default 'N' not null,
    Create_tmp_table_priv enum ('N', 'Y') charset utf8 default 'N' not null,
    Lock_tables_priv      enum ('N', 'Y') charset utf8 default 'N' not null,
    Create_view_priv      enum ('N', 'Y') charset utf8 default 'N' not null,
    Show_view_priv        enum ('N', 'Y') charset utf8 default 'N' not null,
    Create_routine_priv   enum ('N', 'Y') charset utf8 default 'N' not null,
    Alter_routine_priv    enum ('N', 'Y') charset utf8 default 'N' not null,
    Execute_priv          enum ('N', 'Y') charset utf8 default 'N' not null,
    Event_priv            enum ('N', 'Y') charset utf8 default 'N' not null,
    Trigger_priv          enum ('N', 'Y') charset utf8 default 'N' not null,
    primary key (Host, Db, User)
)
    comment 'Database privileges' collate = utf8_bin;

create index User
    on db (User);

INSERT INTO mysql.db (Host, Db, User, Select_priv, Insert_priv, Update_priv, Delete_priv, Create_priv, Drop_priv, Grant_priv, References_priv, Index_priv, Alter_priv, Create_tmp_table_priv, Lock_tables_priv, Create_view_priv, Show_view_priv, Create_routine_priv, Alter_routine_priv, Execute_priv, Event_priv, Trigger_priv) VALUES ('localhost', 'performance_schema', 'mysql.session', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO mysql.db (Host, Db, User, Select_priv, Insert_priv, Update_priv, Delete_priv, Create_priv, Drop_priv, Grant_priv, References_priv, Index_priv, Alter_priv, Create_tmp_table_priv, Lock_tables_priv, Create_view_priv, Show_view_priv, Create_routine_priv, Alter_routine_priv, Execute_priv, Event_priv, Trigger_priv) VALUES ('localhost', 'sys', 'mysql.sys', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y');