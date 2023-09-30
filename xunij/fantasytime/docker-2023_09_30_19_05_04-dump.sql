-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 47.115.224.241    Database: fantasytime
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
-- Table structure for table `ft_area`
--

DROP TABLE IF EXISTS `ft_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_area` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(50) DEFAULT NULL COMMENT '国家名字',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国家区域';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_area`
--

LOCK TABLES `ft_area` WRITE;
/*!40000 ALTER TABLE `ft_area` DISABLE KEYS */;
INSERT INTO `ft_area` VALUES (1,'全部',1,'2023-02-08 14:42:09',NULL),(2,'日本',1,'2023-02-08 14:42:09',NULL),(3,'大陆',1,'2023-02-08 14:42:09',NULL),(4,'欧美',1,'2023-02-08 14:42:09',NULL);
/*!40000 ALTER TABLE `ft_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_cartoon_works_details`
--

DROP TABLE IF EXISTS `ft_cartoon_works_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_cartoon_works_details` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `works_id` bigint DEFAULT NULL COMMENT '作品id',
  `cartoon_chapter_id` bigint DEFAULT NULL COMMENT '漫画章节ID 第几话',
  `cartoon_chapter_name` varchar(64) DEFAULT NULL COMMENT '漫画章节名字',
  `cartoon_pages` char(20) DEFAULT NULL COMMENT '漫画页数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除',
  `review_status` bigint DEFAULT '0' COMMENT '审核状态 0-审核中 1-审核成功 2-审核失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='漫画作品章节数量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_cartoon_works_details`
--

LOCK TABLES `ft_cartoon_works_details` WRITE;
/*!40000 ALTER TABLE `ft_cartoon_works_details` DISABLE KEYS */;
INSERT INTO `ft_cartoon_works_details` VALUES (2,60,1,'第一章','23','2023-09-10 12:33:32','2023-09-10 12:37:53',1,1);
/*!40000 ALTER TABLE `ft_cartoon_works_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_category`
--

DROP TABLE IF EXISTS `ft_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_category` (
  `cat_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` char(50) DEFAULT NULL COMMENT '分类名称',
  `sort` int DEFAULT NULL COMMENT '排序',
  `works_type` tinyint DEFAULT NULL COMMENT '作品分类 1-漫画 2-小说',
  `icon` char(255) DEFAULT NULL COMMENT '图标地址',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除',
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作品类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_category`
--

LOCK TABLES `ft_category` WRITE;
/*!40000 ALTER TABLE `ft_category` DISABLE KEYS */;
INSERT INTO `ft_category` VALUES (1,'全部',1,1,NULL,1),(2,'科幻',2,1,NULL,1),(3,'热血',3,1,NULL,1),(4,'搞笑',4,1,NULL,1),(5,'爱情',5,1,NULL,1),(6,'神魔',6,1,NULL,1),(7,'魔法',7,1,NULL,1),(8,'推理',8,1,NULL,1),(9,'科幻',9,1,NULL,1),(10,'治愈',10,1,NULL,1),(11,'运动',11,1,NULL,1),(12,'其他',12,1,NULL,1),(13,'全部',1,2,NULL,1),(14,'科幻',2,2,NULL,1),(15,'热血',3,2,NULL,1),(16,'搞笑',4,2,NULL,1),(17,'爱情',5,2,NULL,1),(18,'神魔',6,2,NULL,1),(19,'魔法',7,2,NULL,1),(20,'推理',8,2,NULL,1),(21,'科幻',9,2,NULL,1),(22,'治愈',10,2,NULL,1),(23,'运动',11,2,NULL,1),(24,'其他',12,2,NULL,1);
/*!40000 ALTER TABLE `ft_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_contact`
--

DROP TABLE IF EXISTS `ft_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_contact` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  `owner_id` bigint unsigned DEFAULT NULL,
  `target_id` bigint unsigned DEFAULT NULL,
  `type` bigint DEFAULT NULL,
  `desc_info` longtext,
  PRIMARY KEY (`id`),
  KEY `idx_ft_contact_deleted_at` (`deleted_at`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_contact`
--

LOCK TABLES `ft_contact` WRITE;
/*!40000 ALTER TABLE `ft_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `ft_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_message`
--

DROP TABLE IF EXISTS `ft_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_message` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime(3) DEFAULT NULL,
  `updated_at` datetime(3) DEFAULT NULL,
  `deleted_at` datetime(3) DEFAULT NULL,
  `user_id` bigint NOT NULL COMMENT '发送者ID',
  `target_id` bigint NOT NULL COMMENT '接受者ID',
  `type` bigint NOT NULL COMMENT '发送类型',
  `media` bigint NOT NULL COMMENT '消息类型',
  `content` longtext COMMENT '消息内存',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `read_time` datetime(3) NOT NULL COMMENT '读取时间',
  `pic` longtext COMMENT '图片',
  `url` longtext COMMENT '浏览地址',
  `desc` longtext COMMENT '备注',
  `amount` bigint DEFAULT NULL COMMENT '其他数字统计',
  PRIMARY KEY (`id`),
  KEY `idx_ft_message_deleted_at` (`deleted_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_message`
--

LOCK TABLES `ft_message` WRITE;
/*!40000 ALTER TABLE `ft_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `ft_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_novel_works_details`
--

DROP TABLE IF EXISTS `ft_novel_works_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_novel_works_details` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `works_id` bigint DEFAULT NULL COMMENT '作品id',
  `novel_chapter_id` char(20) DEFAULT NULL COMMENT '小说章节ID 第几章',
  `novel_chapter_name` varchar(64) DEFAULT NULL COMMENT '小说章节名字',
  `novel_pages` char(20) DEFAULT NULL COMMENT '漫画页数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说作品章节数量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_novel_works_details`
--

LOCK TABLES `ft_novel_works_details` WRITE;
/*!40000 ALTER TABLE `ft_novel_works_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `ft_novel_works_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_popularity`
--

DROP TABLE IF EXISTS `ft_popularity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_popularity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `works_id` bigint DEFAULT NULL COMMENT '作品id',
  `name` char(50) DEFAULT NULL COMMENT '作品名称',
  `works_type` tinyint DEFAULT NULL COMMENT '作品类型 1-漫画 2-小说 3-插图',
  `works_status` tinyint DEFAULT NULL COMMENT '作品状态 1-更新中 2-完结',
  `works_popularity_count` bigint DEFAULT NULL COMMENT '总人气 观看十章 加一',
  `works_popularity_today` bigint DEFAULT NULL COMMENT '今天的人气 每个用户每天看一章加一 第二天清零',
  `works_popularity_three_days` bigint DEFAULT NULL COMMENT '三天的人气 每个用户每天看一章加一 第四天天清零',
  `works_popularity_thisWeek` bigint DEFAULT NULL COMMENT '本周的人气 每个用户每天看一章加一 下一周清零',
  `works_popularity_thisMonth` bigint DEFAULT NULL COMMENT '本月的人气 每个用户每天看一章加一 下一月清零',
  `delete_status` bigint DEFAULT '1' COMMENT '逻辑删除 0-已删除 1-未删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作品人气';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_popularity`
--

LOCK TABLES `ft_popularity` WRITE;
/*!40000 ALTER TABLE `ft_popularity` DISABLE KEYS */;
INSERT INTO `ft_popularity` VALUES (8,60,'刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~',1,1,1,0,1,1,1,1,'2023-09-14 14:45:03','2023-09-15 08:00:03');
/*!40000 ALTER TABLE `ft_popularity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_role_user`
--

DROP TABLE IF EXISTS `ft_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_role_user` (
  `uid` bigint DEFAULT NULL COMMENT '用户ID',
  `rid` bigint DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_role_user`
--

LOCK TABLES `ft_role_user` WRITE;
/*!40000 ALTER TABLE `ft_role_user` DISABLE KEYS */;
INSERT INTO `ft_role_user` VALUES (1,1,'2023-01-05 14:39:26',NULL,1),(2,1,'2023-02-13 15:43:06',NULL,1),(6,1,'2023-02-21 10:15:31',NULL,1),(7,1,'2023-02-21 10:19:32',NULL,1),(8,1,'2023-02-21 10:21:38',NULL,1),(11,1,'2023-08-29 15:53:21',NULL,1);
/*!40000 ALTER TABLE `ft_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_roles`
--

DROP TABLE IF EXISTS `ft_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role` varchar(50) NOT NULL COMMENT '角色',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_role` (`role_name`,`role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_roles`
--

LOCK TABLES `ft_roles` WRITE;
/*!40000 ALTER TABLE `ft_roles` DISABLE KEYS */;
INSERT INTO `ft_roles` VALUES (1,'管理员','ROLE_admin','2023-01-05 14:45:34',NULL,1);
/*!40000 ALTER TABLE `ft_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_user`
--

DROP TABLE IF EXISTS `ft_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` char(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `header` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` tinyint DEFAULT NULL COMMENT '性别',
  `birth` date DEFAULT NULL COMMENT '生日',
  `city` varchar(500) DEFAULT NULL COMMENT '所在城市',
  `job` varchar(255) DEFAULT NULL COMMENT '职业',
  `sign` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `source_type` tinyint DEFAULT NULL COMMENT '用户来源',
  `status` tinyint DEFAULT NULL COMMENT '启用状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除',
  `fans` int DEFAULT '0' COMMENT '粉丝',
  `care` int DEFAULT '0' COMMENT '关注人数',
  `likes` int DEFAULT '0' COMMENT '获得的点赞数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_user`
--

LOCK TABLES `ft_user` WRITE;
/*!40000 ALTER TABLE `ft_user` DISABLE KEYS */;
INSERT INTO `ft_user` VALUES (1,'徐国纪','202cb962ac59075b964b07152d234b70','提示特本','18574933063','2532878663@qq.com',NULL,1,'2001-05-02',NULL,NULL,'测试',1,1,'2023-01-05 14:42:41',NULL,1,0,0,0),(2,'徐国纪2','202cb962ac59075b964b07152d234b70','提示特本2','17680375392','2222222222@qq.com','',1,'2001-05-02','','','阿萨实打实大大大大',1,1,'2023-01-05 14:42:41',NULL,1,0,0,0),(6,'Niko','202cb962ac59075b964b07152d234b70','Test','17680375393',NULL,'',1,'2001-05-02',NULL,NULL,NULL,1,NULL,'2023-02-21 10:15:31','2023-02-21 10:15:31',1,0,0,0),(7,'Niko2','202cb962ac59075b964b07152d234b70','Test2','17680375394',NULL,'',1,'2001-05-02',NULL,NULL,NULL,1,NULL,'2023-02-21 10:19:32','2023-02-21 10:19:32',1,0,0,0),(8,'Test4','202cb962ac59075b964b07152d234b70','Niko3','17680375395',NULL,'',1,'2001-05-02',NULL,NULL,NULL,1,NULL,'2023-02-21 10:21:38','2023-02-21 10:21:38',1,0,0,0),(11,NULL,'202cb962ac59075b964b07152d234b70','','17778788213',NULL,'',NULL,NULL,NULL,NULL,NULL,1,NULL,'2023-08-29 15:53:21','2023-08-29 15:53:21',1,0,0,0);
/*!40000 ALTER TABLE `ft_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_works`
--

DROP TABLE IF EXISTS `ft_works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_works` (
  `works_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `works_name` varchar(64) DEFAULT NULL COMMENT '作品名',
  `default_image` varchar(128) DEFAULT NULL COMMENT '作品默认展示图片',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `works_creator` varchar(64) DEFAULT NULL COMMENT '作品创作者',
  `works_create_time` datetime DEFAULT NULL COMMENT '作品创作时间',
  `works_area` char(2) DEFAULT NULL COMMENT '作品创作地区',
  `works_type` bigint DEFAULT NULL COMMENT '作品类型 1-漫画 2-小说 3-插图',
  `works_score` float DEFAULT NULL COMMENT '作品评分 10分满分',
  `works_renew` bigint DEFAULT NULL COMMENT '作品更新至多少',
  `works_popularity` bigint DEFAULT '0' COMMENT '作品人气 用户阅读10章以上为1',
  `works_describe` varchar(64) DEFAULT NULL COMMENT '作品描述',
  `works_category` varchar(64) DEFAULT NULL COMMENT '作品分类',
  `status` tinyint DEFAULT NULL COMMENT '作品状态 1-可以看 2-不可以看',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-已删除 1-未删除',
  `works_status` tinyint DEFAULT NULL COMMENT '作品状态 1-更新中 2-完结',
  `review_status` bigint DEFAULT '0' COMMENT 'review_status审核状态 0-审核中 1-审核成功 2-审核失败',
  PRIMARY KEY (`works_id`),
  UNIQUE KEY `works_name` (`works_name`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_works`
--

LOCK TABLES `ft_works` WRITE;
/*!40000 ALTER TABLE `ft_works` DISABLE KEYS */;
INSERT INTO `ft_works` VALUES (59,'就叫作輝夜吧-远藤ユウ','http://47.115.224.241:8084/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=59','1','远藤ユウ','2023-09-10 00:00:00','2',1,0,0,0,'测试','2,3,4,',1,'2023-09-10 12:02:57','2023-09-10 12:02:57',1,1,1),(60,'刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~','http://47.115.224.241:8084/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=60','1','野春','2023-09-10 00:00:00','2',1,0,0,0,'测试','2,3,4,6,',1,'2023-09-10 12:21:44','2023-09-10 12:21:44',1,1,1);
/*!40000 ALTER TABLE `ft_works` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_works_bookshelf`
--

DROP TABLE IF EXISTS `ft_works_bookshelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_works_bookshelf` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `works_id` bigint NOT NULL COMMENT '作品id',
  `works_name` varchar(64) DEFAULT NULL COMMENT '作品名',
  `default_image` varchar(128) DEFAULT NULL COMMENT '作品默认展示图片',
  `works_renew` varchar(64) DEFAULT NULL COMMENT '作品更新至多少',
  `works_type` bigint DEFAULT NULL COMMENT '作品类型 1-漫画 2-小说',
  `works_creator` varchar(64) DEFAULT NULL COMMENT '作品创作者',
  `works_update_time` datetime DEFAULT NULL COMMENT '作品更新时间',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-删除 1-存在',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='书架';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_works_bookshelf`
--

LOCK TABLES `ft_works_bookshelf` WRITE;
/*!40000 ALTER TABLE `ft_works_bookshelf` DISABLE KEYS */;
/*!40000 ALTER TABLE `ft_works_bookshelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_works_chapter_detailed_viewing_content`
--

DROP TABLE IF EXISTS `ft_works_chapter_detailed_viewing_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_works_chapter_detailed_viewing_content` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `works_chapter_id` bigint DEFAULT NULL COMMENT '章节ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `works_id` bigint NOT NULL COMMENT '作品id',
  `image_id` bigint DEFAULT NULL COMMENT '该画画作品的该章节的第几个图片',
  `review_status` bigint NOT NULL DEFAULT '1' COMMENT '审核状态 0-审核中 1-审核成功 2-审核失败',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-删除 1-存在',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `works_chapter_Location` varchar(128) NOT NULL COMMENT '章节数据存储位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_works_chapter_detailed_viewing_content`
--

LOCK TABLES `ft_works_chapter_detailed_viewing_content` WRITE;
/*!40000 ALTER TABLE `ft_works_chapter_detailed_viewing_content` DISABLE KEYS */;
INSERT INTO `ft_works_chapter_detailed_viewing_content` VALUES (176,2,1,60,1,1,1,'2023-09-10 12:35:04',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/1.jpg'),(177,2,1,60,2,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/2.jpg'),(178,2,1,60,3,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/3.jpg'),(179,2,1,60,4,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/4.jpg'),(180,2,1,60,5,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/5.jpg'),(181,2,1,60,6,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/6.jpg'),(182,2,1,60,7,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/7.jpg'),(183,2,1,60,8,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/8.jpg'),(184,2,1,60,9,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/9.jpg'),(185,2,1,60,10,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/10.jpg'),(186,2,1,60,11,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/11.jpg'),(187,2,1,60,12,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/12.jpg'),(188,2,1,60,13,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/13.jpg'),(189,2,1,60,14,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/14.jpg'),(190,2,1,60,15,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/15.jpg'),(191,2,1,60,16,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/16.jpg'),(192,2,1,60,17,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/17.jpg'),(193,2,1,60,18,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/18.jpg'),(194,2,1,60,19,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/19.jpg'),(195,2,1,60,20,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/20.jpg'),(196,2,1,60,21,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/21.jpg'),(197,2,1,60,22,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/22.jpg'),(198,2,1,60,23,1,1,'2023-09-10 12:35:05',NULL,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/1/23.jpg');
/*!40000 ALTER TABLE `ft_works_chapter_detailed_viewing_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_works_default_image`
--

DROP TABLE IF EXISTS `ft_works_default_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_works_default_image` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `works_id` bigint NOT NULL COMMENT '作品id',
  `works_default_image` varchar(128) NOT NULL COMMENT '图片服务请求数据位置',
  `review_status` bigint NOT NULL DEFAULT '1' COMMENT '审核状态 0-审核中 1-审核成功 2-审核失败',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-删除 1-存在',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作品封面图片服务请求路径存储';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_works_default_image`
--

LOCK TABLES `ft_works_default_image` WRITE;
/*!40000 ALTER TABLE `ft_works_default_image` DISABLE KEYS */;
INSERT INTO `ft_works_default_image` VALUES (22,59,'/mydata/java/tu/就叫作輝夜吧-远藤ユウ/封面/第1页.jpg',1,1,'2023-09-10 12:02:57',NULL),(23,60,'/mydata/java/tu/刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~/封面/1.jpg',1,1,'2023-09-10 12:21:45',NULL);
/*!40000 ALTER TABLE `ft_works_default_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_works_upload`
--

DROP TABLE IF EXISTS `ft_works_upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_works_upload` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `works_id` bigint NOT NULL COMMENT '作品id',
  `review_status` bigint NOT NULL DEFAULT '1' COMMENT '审核状态 0-审核中 1-审核成功 2-审核失败',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-删除 1-存在',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作品上传信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_works_upload`
--

LOCK TABLES `ft_works_upload` WRITE;
/*!40000 ALTER TABLE `ft_works_upload` DISABLE KEYS */;
INSERT INTO `ft_works_upload` VALUES (32,1,59,1,1,'2023-09-10 12:02:57',NULL),(33,1,60,1,1,'2023-09-10 12:21:45',NULL);
/*!40000 ALTER TABLE `ft_works_upload` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft_works_watch_history`
--

DROP TABLE IF EXISTS `ft_works_watch_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ft_works_watch_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `works_id` bigint NOT NULL COMMENT '作品id',
  `works_name` varchar(64) DEFAULT NULL COMMENT '作品名',
  `default_image` varchar(128) DEFAULT NULL COMMENT '作品默认展示图片',
  `works_history_viewing_chapter` bigint DEFAULT NULL COMMENT '用户观看到第几章',
  `works_history_viewing_chapter_id` bigint DEFAULT NULL COMMENT '用户观看到第几章的ID',
  `works_history_viewing_chapter_image` bigint DEFAULT NULL COMMENT '用户观看到第几章的的第几张图片',
  `works_type` tinyint DEFAULT NULL COMMENT '作品分类 1-漫画 2-小说',
  `works_status` tinyint DEFAULT NULL COMMENT '作品状态 1-更新中 2-完结',
  `delete_status` bigint NOT NULL DEFAULT '1' COMMENT '逻辑删除状态 0-删除 1-存在',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `edit_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='观看历史记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft_works_watch_history`
--

LOCK TABLES `ft_works_watch_history` WRITE;
/*!40000 ALTER TABLE `ft_works_watch_history` DISABLE KEYS */;
INSERT INTO `ft_works_watch_history` VALUES (4,1,60,'刃牙外传 盖亚与西科尔斯基 ~有时候是野村 虽为二人的三人生活~','http://47.115.224.241:8084/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=60',1,2,23,1,1,1,'2023-09-14 14:46:22','2023-09-14 23:10:15');
/*!40000 ALTER TABLE `ft_works_watch_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-30 19:05:10
