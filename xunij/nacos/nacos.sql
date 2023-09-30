-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 47.115.224.241    Database: nacos
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  `encrypted_data_key` text COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (3,'fantasytime-works.yaml','dev','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 172.24.177.140\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://172.24.177.140:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.24.177.140:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 172.24.177.140\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata//java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','497fa0a24f1f1da3226c7c9a9c8d3feb','2023-09-03 03:43:50','2023-09-14 14:20:12','nacos','27.46.59.232','','1078c902-8008-4989-9857-d3aec2398b95','','','','yaml','',''),(4,'fantasytime-search.yaml','dev','spring:\r\n  application:\r\n    name: fantasytime-search\r\n  redis:\r\n    port: 6379\r\n    password: xu20010502\r\n    host: 47.115.224.241\r\n#  zipkin:\r\n#    base-url: http://localhost:9411\r\n#  sleuth:\r\n#    sampler:\r\n#      rate: 1\r\nlogging:\r\n  level:\r\n    com.xu: debug\r\n    org.elasticsearch: debug\r\n    tracer: trace\r\nserver:\r\n  port: 8083\r\n','ea66846ce1a87b61d5bb2a8ea36d4427','2023-09-03 03:45:40','2023-09-03 03:45:40',NULL,'27.38.188.202','','1078c902-8008-4989-9857-d3aec2398b95',NULL,NULL,NULL,'yaml',NULL,''),(5,'fantasytime-gateway.yaml','dev','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n\n    loadbalancer:\n      retry:\n        enabled: true\n    gateway:\n      routes:\n\n        - id: fantasytime-admin  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-works\n          predicates:\n            - Path=/api/FT/**       # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n\n\n        - id: fantasytime-works  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-works\n          predicates:\n            - Path=/api/works/**       # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-third-party  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-third-party\n          predicates:\n            - Path=/api/oss/**      # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-search  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-search\n          predicates:\n            - Path=/api/search/**      # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: renren-fast  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://renren-fast\n          predicates:\n            - Path=/api/**         # 断言，路径相匹配的进行路由renren-fast\n          filters:\n            - RewritePath=/api/(?<segment>.*),/renren-fast/$\\{segment}\n\n\n\n\n\n\n  application:\n    name: fantasytime-gateway\nserver:\n  port: 8084\n\n','ae5f39b59eda140aa60d930d471d8716','2023-09-03 03:48:13','2023-09-03 13:31:12','nacos','27.38.188.202','','1078c902-8008-4989-9857-d3aec2398b95','','','','yaml','',''),(7,'fantasytime-third-party.yaml','dev','spring:\n  application:\n    name: fantasytime-third-party\n  servlet:\n    multipart:\n      max-file-size: 100MB\n      max-request-size: 500MB\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\nserver:\n  port: 9100\n\n','e098b322a7dacb1d1849b13e404cafac','2023-09-03 03:52:01','2023-09-03 14:41:30','nacos','27.38.188.202','','1078c902-8008-4989-9857-d3aec2398b95','','','','yaml','','');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (0,1,'application.yaml','dev','','server:\r\n  port: 8081\r\nspring:\r\n  redis:\r\n    port: 6379\r\n    host: 47.115.224.241\r\n    password: xu20010502\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name:  com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: xu20010502\r\n  application:\r\n    name: fantasytime-works\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 47.115.224.241:8848\r\n  rabbitmq:\r\n    username: guest\r\n    password: guest\r\n    host: 127.0.0.1\r\n    port: 5672\r\n    virtual-host: /\r\n    # 开启消息成功发送回调\r\n    publisher-confirm-type: correlated\r\n    # 开启消息成功被队列接收回调\r\n    publisher-returns: true\r\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\r\n    listener:\r\n      simple:\r\n        acknowledge-mode: manual\r\n    # 如果消息成功抵达队列 就以异步的方式优先回调\r\n    template:\r\n      mandatory: true\r\n#  zipkin:\r\n#    base-url: http://localhost:9411\r\n#  sleuth:\r\n#    sampler:\r\n#      rate: 1\r\n#  jackson:\r\n#    date-format: yyyy-mm-dd hh:mm:ss\r\n\r\n\r\n\r\n## 配置ThreadPollExecutor 数据\r\nfantasytime:\r\n  thread:\r\n    core-size: 20\r\n    max-size: 200\r\n    keep-alive-time: 10\r\n\r\n## mybatis配置\r\nmybatis-plus:\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n    call-setters-on-nulls: true\r\n  mapper-locations: classpath*:/mapper/**/*.xml\r\n  type-aliases-package: com.xu.works.entity.*\r\n\r\ntest:\r\n  aa: 1231','571dd45624fdec5914a6c909ee748cef','2023-09-02 15:09:06','2023-09-02 15:09:06',NULL,'27.38.188.202','I','1078c902-8008-4989-9857-d3aec2398b95',''),(1,2,'application.yaml','dev','','server:\r\n  port: 8081\r\nspring:\r\n  redis:\r\n    port: 6379\r\n    host: 47.115.224.241\r\n    password: xu20010502\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name:  com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: xu20010502\r\n  application:\r\n    name: fantasytime-works\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 47.115.224.241:8848\r\n  rabbitmq:\r\n    username: guest\r\n    password: guest\r\n    host: 127.0.0.1\r\n    port: 5672\r\n    virtual-host: /\r\n    # 开启消息成功发送回调\r\n    publisher-confirm-type: correlated\r\n    # 开启消息成功被队列接收回调\r\n    publisher-returns: true\r\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\r\n    listener:\r\n      simple:\r\n        acknowledge-mode: manual\r\n    # 如果消息成功抵达队列 就以异步的方式优先回调\r\n    template:\r\n      mandatory: true\r\n#  zipkin:\r\n#    base-url: http://localhost:9411\r\n#  sleuth:\r\n#    sampler:\r\n#      rate: 1\r\n#  jackson:\r\n#    date-format: yyyy-mm-dd hh:mm:ss\r\n\r\n\r\n\r\n## 配置ThreadPollExecutor 数据\r\nfantasytime:\r\n  thread:\r\n    core-size: 20\r\n    max-size: 200\r\n    keep-alive-time: 10\r\n\r\n## mybatis配置\r\nmybatis-plus:\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n    call-setters-on-nulls: true\r\n  mapper-locations: classpath*:/mapper/**/*.xml\r\n  type-aliases-package: com.xu.works.entity.*\r\n\r\ntest:\r\n  aa: 1231','571dd45624fdec5914a6c909ee748cef','2023-09-02 15:31:01','2023-09-02 15:31:01','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(1,3,'application.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 127.0.0.1\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev','1ddc34519c0228b714699647fec497e9','2023-09-03 03:43:27','2023-09-03 03:43:27',NULL,'27.38.188.202','D','1078c902-8008-4989-9857-d3aec2398b95',''),(0,4,'fantasytime-works.yaml','dev','','server:\r\n  port: 8081\r\nspring:\r\n  redis:\r\n    port: 6379\r\n    host: 47.115.224.241\r\n    password: xu20010502\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name:  com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: xu20010502\r\n  application:\r\n    name: fantasytime-works\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 47.115.224.241:8848\r\n  rabbitmq:\r\n    username: guest\r\n    password: guest\r\n    host: 127.0.0.1\r\n    port: 5672\r\n    virtual-host: /\r\n    # 开启消息成功发送回调\r\n    publisher-confirm-type: correlated\r\n    # 开启消息成功被队列接收回调\r\n    publisher-returns: true\r\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\r\n    listener:\r\n      simple:\r\n        acknowledge-mode: manual\r\n    # 如果消息成功抵达队列 就以异步的方式优先回调\r\n    template:\r\n      mandatory: true\r\n#  zipkin:\r\n#    base-url: http://localhost:9411\r\n#  sleuth:\r\n#    sampler:\r\n#      rate: 1\r\n#  jackson:\r\n#    date-format: yyyy-mm-dd hh:mm:ss\r\n\r\n\r\n\r\n## 配置ThreadPollExecutor 数据\r\nfantasytime:\r\n  thread:\r\n    core-size: 20\r\n    max-size: 200\r\n    keep-alive-time: 10\r\n\r\n## mybatis配置\r\nmybatis-plus:\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n    call-setters-on-nulls: true\r\n  mapper-locations: classpath*:/mapper/**/*.xml\r\n  type-aliases-package: com.xu.works.entity.*\r\n\r\ntest:\r\n  aa: dev','e642fa0a6dcb8e4aca39b40d2ab50791','2023-09-03 03:43:50','2023-09-03 03:43:50',NULL,'27.38.188.202','I','1078c902-8008-4989-9857-d3aec2398b95',''),(0,5,'fantasytime-search.yaml','dev','','spring:\r\n  application:\r\n    name: fantasytime-search\r\n  redis:\r\n    port: 6379\r\n    password: xu20010502\r\n    host: 47.115.224.241\r\n#  zipkin:\r\n#    base-url: http://localhost:9411\r\n#  sleuth:\r\n#    sampler:\r\n#      rate: 1\r\nlogging:\r\n  level:\r\n    com.xu: debug\r\n    org.elasticsearch: debug\r\n    tracer: trace\r\nserver:\r\n  port: 8083\r\n','ea66846ce1a87b61d5bb2a8ea36d4427','2023-09-03 03:45:39','2023-09-03 03:45:40',NULL,'27.38.188.202','I','1078c902-8008-4989-9857-d3aec2398b95',''),(0,6,'fantasytime-gateway.yaml','dev','','spring:\r\n  cloud:\r\n    loadbalancer:\r\n      retry:\r\n        enabled: true\r\n    gateway:\r\n      routes:\r\n\r\n        - id: fantasytime-admin  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-works\r\n          predicates:\r\n            - Path=/api/FT/**       # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n\r\n\r\n        - id: fantasytime-works  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-works\r\n          predicates:\r\n            - Path=/api/works/**       # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n        - id: fantasytime-third-party  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-third-party\r\n          predicates:\r\n            - Path=/api/oss/**      # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n        - id: fantasytime-search  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-search\r\n          predicates:\r\n            - Path=/api/search/**      # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n        - id: renren-fast  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://renren-fast\r\n          predicates:\r\n            - Path=/api/**         # 断言，路径相匹配的进行路由renren-fast\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/renren-fast/$\\{segment}\r\n\r\n\r\n\r\n\r\n\r\n\r\n  application:\r\n    name: fantasytime-gateway\r\nserver:\r\n  port: 8084\r\n\r\n','6da32ae48cac248dd24ba92dd978aff2','2023-09-03 03:48:13','2023-09-03 03:48:13',NULL,'27.38.188.202','I','1078c902-8008-4989-9857-d3aec2398b95',''),(0,7,'fantasytime-third-party','dev','','spring:\r\n  application:\r\n    name: fantasytime-third-party\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 100MB\r\n      max-request-size: 500MB\r\n\r\nserver:\r\n  port: 9100\r\n\r\n','27bd38a7e8b9cbe74a11898366eee4a4','2023-09-03 03:51:27','2023-09-03 03:51:28',NULL,'27.38.188.202','I','1078c902-8008-4989-9857-d3aec2398b95',''),(0,8,'fantasytime-third-party.yaml','dev','','spring:\r\n  application:\r\n    name: fantasytime-third-party\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 100MB\r\n      max-request-size: 500MB\r\nserver:\r\n  port: 9100\r\n\r\n','c16ca768123578d50af814006225751b','2023-09-03 03:52:00','2023-09-03 03:52:01',NULL,'27.38.188.202','I','1078c902-8008-4989-9857-d3aec2398b95',''),(5,9,'fantasytime-gateway.yaml','dev','','spring:\r\n  cloud:\r\n    loadbalancer:\r\n      retry:\r\n        enabled: true\r\n    gateway:\r\n      routes:\r\n\r\n        - id: fantasytime-admin  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-works\r\n          predicates:\r\n            - Path=/api/FT/**       # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n\r\n\r\n        - id: fantasytime-works  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-works\r\n          predicates:\r\n            - Path=/api/works/**       # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n        - id: fantasytime-third-party  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-third-party\r\n          predicates:\r\n            - Path=/api/oss/**      # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n        - id: fantasytime-search  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://fantasytime-search\r\n          predicates:\r\n            - Path=/api/search/**      # 断言，路径相匹配的进行路由\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\r\n\r\n        - id: renren-fast  #路由的ID，没有固定规则但要求唯一，建议配合服务名\r\n          uri: lb://renren-fast\r\n          predicates:\r\n            - Path=/api/**         # 断言，路径相匹配的进行路由renren-fast\r\n          filters:\r\n            - RewritePath=/api/(?<segment>.*),/renren-fast/$\\{segment}\r\n\r\n\r\n\r\n\r\n\r\n\r\n  application:\r\n    name: fantasytime-gateway\r\nserver:\r\n  port: 8084\r\n\r\n','6da32ae48cac248dd24ba92dd978aff2','2023-09-03 09:47:39','2023-09-03 09:47:40','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(5,10,'fantasytime-gateway.yaml','dev','','spring:\n  cloud:\n    loadbalancer:\n      retry:\n        enabled: true\n    gateway:\n      routes:\n\n        - id: fantasytime-admin  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-works\n          predicates:\n            - Path=/api/FT/**       # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-works  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-works\n          predicates:\n            - Path=/api/works/**       # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-third-party  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-third-party\n          predicates:\n            - Path=/api/oss/**      # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-search  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-search\n          predicates:\n            - Path=/api/search/**      # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: renren-fast  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://renren-fast\n          predicates:\n            - Path=/api/**         # 断言，路径相匹配的进行路由renren-fast\n          filters:\n            - RewritePath=/api/(?<segment>.*),/renren-fast/$\\{segment}\n','961e8a09c4fc1718d072fd732adadbcc','2023-09-03 12:58:16','2023-09-03 12:58:17','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(5,11,'fantasytime-gateway.yaml','dev','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n    gateway:\n      routes:\n\n        - id: fantasytime-admin  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-works\n          predicates:\n            - Path=/api/FT/**       # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-works  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-works\n          predicates:\n            - Path=/api/works/**       # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-third-party  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-third-party\n          predicates:\n            - Path=/api/oss/**      # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: fantasytime-search  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://fantasytime-search\n          predicates:\n            - Path=/api/search/**      # 断言，路径相匹配的进行路由\n          filters:\n            - RewritePath=/api/(?<segment>.*),/$\\{segment}\n\n        - id: renren-fast  #路由的ID，没有固定规则但要求唯一，建议配合服务名\n          uri: lb://renren-fast\n          predicates:\n            - Path=/api/**         # 断言，路径相匹配的进行路由renren-fast\n          filters:\n            - RewritePath=/api/(?<segment>.*),/renren-fast/$\\{segment}\n','a4b64dbc4c4b6e9cbc02ad8a88e547f5','2023-09-03 13:31:12','2023-09-03 13:31:12','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(6,12,'fantasytime-third-party','dev','','spring:\r\n  application:\r\n    name: fantasytime-third-party\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 100MB\r\n      max-request-size: 500MB\r\n\r\nserver:\r\n  port: 9100\r\n\r\n','27bd38a7e8b9cbe74a11898366eee4a4','2023-09-03 14:20:47','2023-09-03 14:20:47',NULL,'27.38.188.202','D','1078c902-8008-4989-9857-d3aec2398b95',''),(7,13,'fantasytime-third-party.yaml','dev','','spring:\r\n  application:\r\n    name: fantasytime-third-party\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 100MB\r\n      max-request-size: 500MB\r\nserver:\r\n  port: 9100\r\n\r\n','c16ca768123578d50af814006225751b','2023-09-03 14:21:17','2023-09-03 14:21:17','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(7,14,'fantasytime-third-party.yaml','dev','','spring:\n  application:\n    name: fantasytime-third-party\n  servlet:\n    multipart:\n      max-file-size: 100MB\n      max-request-size: 500MB\n\n  #  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\nserver:\n  port: 9100\n\n','464eb43b32d47a6c01bf3311ef0586d4','2023-09-03 14:33:03','2023-09-03 14:33:03','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(7,15,'fantasytime-third-party.yaml','dev','','spring:\n  application:\n    name: fantasytime-third-party\n  servlet:\n    multipart:\n      max-file-size: 100MB\n      max-request-size: 500MB\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\nserver:\n  port: 9100\n\n','e098b322a7dacb1d1849b13e404cafac','2023-09-03 14:36:23','2023-09-03 14:36:23','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(7,16,'fantasytime-third-party.yaml','dev','','spring:\n  application:\n    name: fantasytime-third-party\n  servlet:\n    multipart:\n      max-file-size: 100MB\n      max-request-size: 500MB\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.24.177.140:8848\nserver:\n  port: 9100\n\n','4760308cd000a6e54bcaccf1a55a6293','2023-09-03 14:41:30','2023-09-03 14:41:30','nacos','27.38.188.202','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,17,'fantasytime-works.yaml','dev','','server:\r\n  port: 8081\r\nspring:\r\n  redis:\r\n    port: 6379\r\n    host: 47.115.224.241\r\n    password: xu20010502\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name:  com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\r\n    username: root\r\n    password: xu20010502\r\n  application:\r\n    name: fantasytime-works\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 47.115.224.241:8848\r\n  rabbitmq:\r\n    username: guest\r\n    password: guest\r\n    host: 127.0.0.1\r\n    port: 5672\r\n    virtual-host: /\r\n    # 开启消息成功发送回调\r\n    publisher-confirm-type: correlated\r\n    # 开启消息成功被队列接收回调\r\n    publisher-returns: true\r\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\r\n    listener:\r\n      simple:\r\n        acknowledge-mode: manual\r\n    # 如果消息成功抵达队列 就以异步的方式优先回调\r\n    template:\r\n      mandatory: true\r\n#  zipkin:\r\n#    base-url: http://localhost:9411\r\n#  sleuth:\r\n#    sampler:\r\n#      rate: 1\r\n#  jackson:\r\n#    date-format: yyyy-mm-dd hh:mm:ss\r\n\r\n\r\n\r\n## 配置ThreadPollExecutor 数据\r\nfantasytime:\r\n  thread:\r\n    core-size: 20\r\n    max-size: 200\r\n    keep-alive-time: 10\r\n\r\n## mybatis配置\r\nmybatis-plus:\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n    call-setters-on-nulls: true\r\n  mapper-locations: classpath*:/mapper/**/*.xml\r\n  type-aliases-package: com.xu.works.entity.*\r\n\r\ntest:\r\n  aa: dev','e642fa0a6dcb8e4aca39b40d2ab50791','2023-09-07 10:25:08','2023-09-07 10:25:09','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,18,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev','46dda3f66c9b3173c62208304a659569','2023-09-10 10:08:55','2023-09-10 10:08:55','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,19,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','3e1772818a27dc0e3a52e7294bbde490','2023-09-10 10:09:04','2023-09-10 10:09:04','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,20,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','f37b1bacc9f8bc61304d27dd489dfad6','2023-09-10 10:10:29','2023-09-10 10:10:30','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,21,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu123131231\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','66c6fbacd9078b72afc00e52b1f166e3','2023-09-10 10:32:57','2023-09-10 10:32:58','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,22,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','f37b1bacc9f8bc61304d27dd489dfad6','2023-09-10 10:34:05','2023-09-10 10:34:05','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,23,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','f37b1bacc9f8bc61304d27dd489dfad6','2023-09-10 10:55:38','2023-09-10 10:55:38','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,24,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','f37b1bacc9f8bc61304d27dd489dfad6','2023-09-10 11:38:47','2023-09-10 11:38:47','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,25,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','cb07d7e2d0b428123f9faa4fe988f531','2023-09-10 11:58:51','2023-09-10 11:58:52','nacos','163.125.189.74','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,26,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','f37b1bacc9f8bc61304d27dd489dfad6','2023-09-13 13:10:28','2023-09-13 13:10:28','nacos','27.46.59.232','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,27,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 172.24.177.140\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.24.177.140:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 172.24.177.140\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 172.24.177.140\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','e36a647ef95e2137a2ae968b37977f84','2023-09-13 14:50:04','2023-09-13 14:50:04','nacos','27.46.59.232','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,28,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 172.24.177.140\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://172.24.177.140:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.24.177.140:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 172.24.177.140\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 172.24.177.140\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','7bf4ed249c36eaaebb2eabb82c4f879e','2023-09-13 14:54:13','2023-09-13 14:54:14','nacos','27.46.59.232','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,29,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 47.115.224.241\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://47.115.224.241:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 47.115.224.241:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 47.115.224.241\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','f37b1bacc9f8bc61304d27dd489dfad6','2023-09-14 13:57:18','2023-09-14 13:57:19','nacos','27.46.59.232','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,30,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 172.24.177.140\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://172.24.177.140:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.24.177.140:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 172.24.177.140\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/java/tu/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','49d34b64ac23a6731499ad3b55dc7076','2023-09-14 14:17:39','2023-09-14 14:17:40','nacos','27.46.59.232','U','1078c902-8008-4989-9857-d3aec2398b95',''),(3,31,'fantasytime-works.yaml','dev','','server:\n  port: 8081\nspring:\n  redis:\n    port: 6379\n    host: 172.24.177.140\n    password: xu20010502\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name:  com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://172.24.177.140:3306/fantasytime?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8\n    username: root\n    password: xu20010502\n  application:\n    name: fantasytime-works\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.24.177.140:8848\n  rabbitmq:\n    username: guest\n    password: guest\n    host: 172.24.177.140\n    port: 5672\n    virtual-host: /\n    # 开启消息成功发送回调\n    publisher-confirm-type: correlated\n    # 开启消息成功被队列接收回调\n    publisher-returns: true\n    # 消息确认模式  NONE 关闭, MANUAL手动, AUTO自动\n    listener:\n      simple:\n        acknowledge-mode: manual\n    # 如果消息成功抵达队列 就以异步的方式优先回调\n    template:\n      mandatory: true\n#  zipkin:\n#    base-url: http://localhost:9411\n#  sleuth:\n#    sampler:\n#      rate: 1\n#  jackson:\n#    date-format: yyyy-mm-dd hh:mm:ss\n\n\n\n## 配置ThreadPollExecutor 数据\nfantasytime:\n  thread:\n    core-size: 20\n    max-size: 200\n    keep-alive-time: 10\n\n## mybatis配置\nmybatis-plus:\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    call-setters-on-nulls: true\n  mapper-locations: classpath*:/mapper/**/*.xml\n  type-aliases-package: com.xu.works.entity.*\n\ntest:\n  aa: dev\n\n\n\nfile:\n  gs:\n#    domain: 127.0.0.1\n#    path: C:/Users/Tistben/Desktop/Test/\n#    Zip: C:\\Users\\Tistben\\Desktop\\Test\n    domain: 47.115.224.241\n    path: /mydata/\n    Zip: /mydata/java/tu\n  dev:\n    domain: 47.115.224.241\n    path: /mydata/java/tu\n    Zip: /mydata/java/tu\n  docker-gs:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics\n  docker-home:\n    domain: http://172.11.1.1:9300(服务器ip:端口)\n    path: /home/admin/test(文件在linux的存放地址)\n    prefix: /statics','246b8652ae4694d16b151c99bd8aa75c','2023-09-14 14:20:13','2023-09-14 14:20:12','nacos','27.46.59.232','U','1078c902-8008-4989-9857-d3aec2398b95','');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (1,'1','1078c902-8008-4989-9857-d3aec2398b95','dev','生产环境','nacos',1693482101454,1693482101454),(2,'1','fc6a9752-d58e-414e-b713-58dcd689fa13','test','测试环境','nacos',1693482123224,1693482123224),(3,'1','47f1efee-ef98-42ab-a556-03f5def645f3','home','自己电脑','nacos',1693667294315,1693667294315);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('nacos','$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-30 19:11:28
