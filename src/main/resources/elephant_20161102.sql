-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: elephant
-- ------------------------------------------------------
-- Server version	5.6.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_binding`
--

DROP TABLE IF EXISTS `account_binding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_binding` (
  `accountid` bigint(20) NOT NULL COMMENT '账户id',
  `wxid` varchar(255) NOT NULL DEFAULT '' COMMENT '微信openid',
  `qqid` varchar(255) NOT NULL DEFAULT '' COMMENT 'qq号码',
  `wbid` varchar(255) NOT NULL DEFAULT '' COMMENT '微博id',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`accountid`) COMMENT '主键',
  UNIQUE KEY `accountid_UNIQUE` (`accountid`) COMMENT '主键',
  UNIQUE KEY `wxid_UNIQUE` (`wxid`),
  KEY `wxid_INDEX` (`wxid`) COMMENT 'wxid索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户附加信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_binding`
--

LOCK TABLES `account_binding` WRITE;
/*!40000 ALTER TABLE `account_binding` DISABLE KEYS */;
INSERT INTO `account_binding` VALUES (29,'o6_bmasdasdsad6_2sgVt7hMZOPfL','','',1476074017552);
/*!40000 ALTER TABLE `account_binding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_contacts`
--

DROP TABLE IF EXISTS `account_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_contacts` (
  `contactid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出行人id',
  `accountid` bigint(20) NOT NULL COMMENT '账户id',
  `valid` tinyint(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `id` varchar(255) NOT NULL DEFAULT '' COMMENT '证件号码',
  `id_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '证件类型:身份证,港澳台,军官证',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) NOT NULL DEFAULT '' COMMENT '手机',
  `gender` smallint(4) NOT NULL DEFAULT '0' COMMENT '性别:未知,男,女',
  `birthday` bigint(20) NOT NULL DEFAULT '0' COMMENT '出生日期',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `emergency` tinyint(1) DEFAULT '0' COMMENT '紧急联系人',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`contactid`) COMMENT '主键',
  UNIQUE KEY `contactid_UNIQUE` (`contactid`) COMMENT '主键',
  KEY `accountid_INDEX` (`accountid`) COMMENT 'accountid索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='出行人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_contacts`
--

LOCK TABLES `account_contacts` WRITE;
/*!40000 ALTER TABLE `account_contacts` DISABLE KEYS */;
INSERT INTO `account_contacts` VALUES (1,3,0,'测试','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,1473343882638,1473343882638),(2,3,0,'ABC','370104199109185519',0,'b@126.com','18510248672',1,685123200000,NULL,NULL,1473864828862,1473864828862),(3,3,0,'赵伟','371526199210155235',0,'695910428@qq.com','13901319378',1,719078400000,NULL,1,1475129682651,1475129682651),(4,29,0,'赵伟','371526199210155235',0,'695910428@qq.com','13901319378',1,719078400000,NULL,NULL,1476861050904,1476861050904),(5,29,0,'dongju','371526199210155235',0,'695910428@qq.com','13901319378',1,719078400000,NULL,1,1476864003353,1476864003353),(6,29,1,'嘿嘿嘿','371526199210155235',0,'6959@qq.com','13901319378',1,719078400000,NULL,NULL,1476864109041,1477650521955),(7,29,0,'xing','371526199210155235',0,'3265@qq.com','13701319378',1,719078400000,NULL,1,1476864186554,1476864186554);
/*!40000 ALTER TABLE `account_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_info`
--

DROP TABLE IF EXISTS `account_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_info` (
  `accountid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '账户密码',
  `type` smallint(4) NOT NULL DEFAULT '0' COMMENT '账户类型:普通,领队,管理,代理',
  `status` smallint(4) NOT NULL DEFAULT '0' COMMENT '账户状态:待激活,正常,注销,异常',
  `id` varchar(255) NOT NULL DEFAULT '' COMMENT '证件号码',
  `id_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '证件类型:身份证,港澳台,军官证',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(255) NOT NULL DEFAULT '' COMMENT '手机',
  `nickname` varchar(255) DEFAULT NULL,
  `gender` smallint(4) DEFAULT NULL,
  `birthday` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`accountid`) COMMENT '主键',
  UNIQUE KEY `accountid_UNIQUE` (`accountid`) COMMENT '主键',
  KEY `email_INDEX` (`email`) COMMENT 'email索引'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='账户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
INSERT INTO `account_info` VALUES (3,'董俊鹏','',0,1,'370104199109185519',0,'brent24@126.com','18510248672','NICKNAME',1,685123200000,'北京市丰台区优龙路洋房小镇D-102','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1472476781202,1474969323929),(4,'','',0,0,'',0,'','','NICKNAME',1,1475288852566,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475288852566,1475288852566),(5,'','',0,0,'',0,'','','NICKNAME',1,1475288864987,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475288864987,1475288864987),(6,'','',0,0,'',0,'','','NICKNAME',1,1475289027563,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475289027563,1475289027563),(7,'','',0,0,'',0,'','','NICKNAME',1,1475289274402,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475289274402,1475289274402),(8,'','',0,0,'',0,'','','NICKNAME',1,1475289307985,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475289307985,1475289307985),(9,'','',0,0,'',0,'','','NICKNAME',1,1475289631263,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475289631263,1475289631263),(10,'','',0,0,'',0,'','','NICKNAME',1,1475305833642,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475305833642,1475305833642),(11,'','',0,0,'',0,'','','NICKNAME',1,1475381243841,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475381243841,1475381243841),(12,'','',0,0,'',0,'','','NICKNAME',1,1475381254295,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475381254295,1475381254295),(13,'','',0,0,'',0,'','','NICKNAME',1,1475381377841,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475381377841,1475381377841),(14,'','',0,0,'',0,'','','NICKNAME',1,1475381386318,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475381386318,1475381386318),(15,'','',0,0,'',0,'','','NICKNAME',1,1475392986185,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475392986185,1475392986185),(16,'','',0,0,'',0,'','','NICKNAME',1,1475938088835,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475938088835,1475938088835),(17,'','',0,0,'',0,'','','NICKNAME',1,1475981163667,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1475981163667,1475981163667),(18,'','',0,0,'',0,'','','NICKNAME',1,1476023730770,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476023730770,1476023730770),(19,'','',0,0,'',0,'','','NICKNAME',1,1476073453526,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073453526,1476073453526),(20,'','',0,0,'',0,'','','NICKNAME',1,1476073518367,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073518367,1476073518367),(21,'','',0,0,'',0,'','','NICKNAME',1,1476073525740,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073525740,1476073525740),(22,'','',0,0,'',0,'','','NICKNAME',1,1476073575640,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073575640,1476073575640),(23,'','',0,0,'',0,'','','NICKNAME',1,1476073704826,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073704826,1476073704826),(24,'','',0,0,'',0,'','','NICKNAME',1,1476073744100,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073744100,1476073744100),(25,'','',0,0,'',0,'','','NICKNAME',1,1476073761837,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073761837,1476073761837),(26,'','',0,0,'',0,'','','NICKNAME',1,1476073777661,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073777661,1476073777661),(27,'','',0,0,'',0,'','','NICKNAME',1,1476073881235,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073881235,1476073881235),(28,'','',0,0,'',0,'','','NICKNAME',1,1476073973140,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476073973140,1476073973140),(29,'赵伟','',0,1,'371526199210155235',0,'695910428@qq.com','13901319378','NICKNAME',1,719078400000,'北京市','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476074017552,1476688960983),(30,'','',0,0,'',0,'','','NICKNAME',1,1476074024809,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476074024809,1476074024809),(31,'','',0,0,'',0,'','','NICKNAME',1,1476074025223,'','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0',1476074025223,1476074025223);
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assembly_info`
--

DROP TABLE IF EXISTS `assembly_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assembly_info` (
  `assemblyid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '集合id',
  `groupid` bigint(20) NOT NULL COMMENT '团id，一一对应关系',
  `ready` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否准备完毕',
  `send` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '已发送',
  `leader` varchar(255) DEFAULT NULL COMMENT '领队姓名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '领队电话/微信',
  `hotel` varchar(2000) DEFAULT NULL COMMENT '集合酒店名称',
  `address` varchar(2000) DEFAULT NULL COMMENT '集合酒店地址',
  `hotel_tel` varchar(255) DEFAULT NULL COMMENT '酒店电话',
  `wx_qrcode` varchar(2000) DEFAULT NULL COMMENT '群二维码地址',
  `file` varchar(2000) DEFAULT NULL COMMENT '集合文件地址',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`assemblyid`,`add_time`),
  UNIQUE KEY `assemblyid_UNIQUE` (`assemblyid`),
  UNIQUE KEY `groupid_UNIQUE` (`groupid`),
  KEY `groupid_INDEX` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assembly_info`
--

LOCK TABLES `assembly_info` WRITE;
/*!40000 ALTER TABLE `assembly_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `assembly_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `discountid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '优惠id',
  `type` smallint(4) NOT NULL DEFAULT '0' COMMENT '折扣类型:优惠券,满减,打折',
  `routeid` bigint(20) DEFAULT NULL,
  `value` bigint(20) NOT NULL DEFAULT '0' COMMENT '数值',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '优惠描述',
  `start_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '生效时间',
  `end_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '结束时间',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`discountid`) COMMENT '主键',
  UNIQUE KEY `discountid_UNIQUE` (`discountid`) COMMENT '主键'
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8 COMMENT='订单优惠';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,1,NULL,200000,'单人优惠',0,1504062229000,1468317107000),(2,2,NULL,300000,'两人同行优惠',0,1504062229000,1468317107000),(3,6,NULL,150000,'订单时间相关优惠',0,1504062229000,1468317107000),(4,7,NULL,250000,'出行时间相关优惠',0,1504062229000,1468317107000),(5,8,1,350000,'路线相关优惠',0,1504062229000,1468317107000),(10001,3,NULL,400000,'三人同行减400',0,1504062229000,1468317107000),(10002,9,1,400000,'学生证优惠',0,1504062229000,1468317107000);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_code`
--

DROP TABLE IF EXISTS `discount_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount_code` (
  `codeid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '优惠码id',
  `discount_code` varchar(255) DEFAULT NULL COMMENT '优惠码',
  `accountid` bigint(20) DEFAULT NULL COMMENT '账户id',
  `status` smallint(4) NOT NULL DEFAULT '0' COMMENT '优惠码状态:已发放,已过期,已使用',
  `agent` bigint(20) DEFAULT NULL COMMENT '代理id',
  `value` bigint(20) NOT NULL DEFAULT '0' COMMENT '折扣价值',
  `start_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '生效时间',
  `end_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '结束时间',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `effect_time` bigint(20) DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`codeid`) COMMENT '主键',
  UNIQUE KEY `codeid_UNIQUE` (`codeid`) COMMENT '主键',
  KEY `discount_code_INDEX` (`discount_code`) COMMENT 'discount_code索引'
) ENGINE=InnoDB AUTO_INCREMENT=1135 DEFAULT CHARSET=utf8 COMMENT='优惠码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_code`
--

LOCK TABLES `discount_code` WRITE;
/*!40000 ALTER TABLE `discount_code` DISABLE KEYS */;
INSERT INTO `discount_code` VALUES (1125,'0F681ED792',3,3,NULL,100000,0,1500808000000,1469980800000,NULL),(1126,'D1C6E5C7B5',1,2,NULL,100000,0,1500808000000,1469980800000,NULL),(1127,'A6FC52C46B',NULL,0,NULL,100000,0,1500808000000,1469980800000,NULL),(1128,'65AD0209C3',NULL,0,NULL,100000,0,1500808000000,1469980800000,NULL),(1129,'DCB72EB2E7',NULL,0,NULL,100000,0,1500808000000,1469980800000,NULL),(1130,'40428E9F18',29,4,NULL,100000,0,1500808000000,1469980800000,NULL),(1131,'09EE8A0425',29,1,NULL,100000,0,1500808000000,1469980800000,NULL),(1132,'317D48F308',29,2,NULL,100000,0,1500808000000,1469980800000,NULL),(1133,'6489B16839',29,3,NULL,100000,0,1500808000000,1469980800000,NULL),(1134,'72C8A3F345',29,4,NULL,100000,0,1500808000000,1469980800000,NULL);
/*!40000 ALTER TABLE `discount_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_alipay`
--

DROP TABLE IF EXISTS `order_alipay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_alipay` (
  `alipayid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `orderid` bigint(20) NOT NULL COMMENT '订单id',
  `trade_no` varchar(45) NOT NULL COMMENT '支付宝交易id',
  `trade_status` smallint(4) NOT NULL COMMENT '交易状态',
  `notify_id` varchar(45) NOT NULL COMMENT '支付宝通知id',
  `notify_type` smallint(4) NOT NULL,
  `notify_time` bigint(20) NOT NULL,
  `total_fee` bigint(20) NOT NULL COMMENT '总金额',
  `seller_id` varchar(45) NOT NULL COMMENT '卖家id',
  `buyer_id` varchar(45) NOT NULL COMMENT '买家id',
  `buyer_email` varchar(45) NOT NULL COMMENT '买家邮箱',
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_payment` bigint(20) DEFAULT NULL,
  `gmt_close` bigint(20) DEFAULT NULL,
  `refund_status` smallint(4) DEFAULT NULL,
  `gmt_refund` varchar(45) DEFAULT NULL,
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`alipayid`),
  UNIQUE KEY `alipayid_UNIQUE` (`alipayid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付宝支付信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_alipay`
--

LOCK TABLES `order_alipay` WRITE;
/*!40000 ALTER TABLE `order_alipay` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_alipay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_discount`
--

DROP TABLE IF EXISTS `order_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_discount` (
  `order_discountid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单折扣id',
  `orderid` bigint(20) NOT NULL COMMENT '订单id',
  `discountid` bigint(20) DEFAULT NULL COMMENT '折扣id',
  `discount_code` varchar(255) DEFAULT NULL COMMENT '优惠码',
  `type` smallint(4) NOT NULL DEFAULT '0' COMMENT '折扣类型:优惠券,满减,打折',
  `routeid` bigint(20) DEFAULT NULL,
  `value` bigint(20) NOT NULL DEFAULT '0' COMMENT '优惠减少的价格',
  `description` varchar(255) DEFAULT NULL COMMENT '优惠描述',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`order_discountid`) COMMENT '主键',
  UNIQUE KEY `order_discountid_UNIQUE` (`order_discountid`) COMMENT '主键',
  KEY `orderid_INDEX` (`orderid`) COMMENT 'orderid索引',
  KEY `discountid_INDEX` (`discountid`) COMMENT 'discountid索引'
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='订单优惠';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_discount`
--

LOCK TABLES `order_discount` WRITE;
/*!40000 ALTER TABLE `order_discount` DISABLE KEYS */;
INSERT INTO `order_discount` VALUES (1,2,5,NULL,8,NULL,350000,'路线相关优惠',1472538041929),(2,4,5,NULL,8,NULL,350000,'路线相关优惠',1472781564829),(3,5,5,NULL,8,NULL,350000,'路线相关优惠',1472782724197),(4,6,5,NULL,8,NULL,350000,'路线相关优惠',1472795309813),(5,39,3,NULL,6,NULL,150000,'订单时间相关优惠',1472814796565),(6,40,5,NULL,8,NULL,350000,'路线相关优惠',1472969767621),(7,41,5,NULL,8,NULL,350000,'路线相关优惠',1472969799291),(8,42,5,NULL,8,NULL,350000,'路线相关优惠',1472985993483),(9,44,5,NULL,8,NULL,350000,'路线相关优惠',1473004091070),(10,45,5,NULL,8,NULL,350000,'路线相关优惠',1473046168374),(11,49,5,NULL,8,NULL,350000,'路线相关优惠',1473244077392),(12,51,5,NULL,8,NULL,350000,'路线相关优惠',1473344043533),(13,55,2,NULL,2,NULL,300000,'两人同行优惠',1473344888599),(14,59,5,NULL,8,NULL,350000,'路线相关优惠',1473407300728),(15,60,5,NULL,8,NULL,350000,'路线相关优惠',1473424160204),(19,64,5,NULL,8,NULL,350000,'路线相关优惠',1473864636984),(20,65,5,NULL,8,NULL,350000,'路线相关优惠',1473864843956),(21,70,5,NULL,8,NULL,350000,'路线相关优惠',1474943538205),(22,70,NULL,'0F681ED792',0,NULL,100000,NULL,1474943538205),(23,71,5,NULL,8,NULL,350000,'路线相关优惠',1474943579456),(24,71,NULL,'D1C6E5C7B5',0,NULL,100000,NULL,1474943579456),(25,72,5,NULL,8,NULL,350000,'路线相关优惠',1474943613415),(26,75,5,NULL,8,NULL,350000,'路线相关优惠',1474957761869),(27,75,NULL,'0F681ED792',0,NULL,100000,NULL,1474957761869),(28,80,5,NULL,8,NULL,350000,'路线相关优惠',1476851645866),(29,86,5,NULL,8,NULL,350000,'路线相关优惠',1477984045637),(30,86,10002,NULL,9,NULL,400000,'学生证优惠',1477984045637),(31,86,NULL,'40428E9F18',0,NULL,100000,NULL,1477984045637),(32,87,2,NULL,2,NULL,300000,'两人同行优惠',1477987574418),(33,87,10002,NULL,9,NULL,400000,'学生证优惠',1477987574418),(34,87,NULL,'40428E9F18',0,NULL,100000,NULL,1477987574418),(35,92,2,NULL,2,NULL,300000,'两人同行优惠',1478097122901),(36,92,10002,NULL,9,NULL,400000,'学生证优惠',1478097122901),(37,92,NULL,'40428E9F18',0,NULL,100000,NULL,1478097122901);
/*!40000 ALTER TABLE `order_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_info` (
  `orderid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `accountid` bigint(20) NOT NULL COMMENT '账户id',
  `routeid` bigint(20) NOT NULL,
  `groupid` bigint(20) NOT NULL COMMENT '发团id',
  `status` smallint(4) NOT NULL DEFAULT '0' COMMENT '订单状态:下单待付款,已取消,发起付款,已付款到账,已结束,已退款',
  `count` smallint(4) NOT NULL DEFAULT '0' COMMENT '订单人数',
  `student_count` smallint(4) NOT NULL DEFAULT '0',
  `price` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单总价',
  `actual_price` bigint(20) NOT NULL DEFAULT '0' COMMENT '实际付款',
  `is_agreed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否同意条款',
  `emergency_contact` varchar(255) DEFAULT NULL,
  `emergency_mobile` varchar(255) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `create_time` bigint(20) DEFAULT NULL,
  `mod_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`orderid`) COMMENT '主键',
  UNIQUE KEY `orderid_UNIQUE` (`orderid`) COMMENT '主键',
  KEY `accountid_INDEX` (`accountid`) COMMENT 'accountid索引',
  KEY `groupid_INDEX` (`groupid`) COMMENT 'groupid索引'
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='订单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES (92,29,1,44,1,2,1,6760000,5960000,1,'1315','13901319378',1478097091563,1478097122901,1478097122901);
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_refund`
--

DROP TABLE IF EXISTS `order_refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_refund` (
  `refundid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单退款id',
  `orderid` bigint(20) NOT NULL COMMENT '订单id',
  `status` smallint(4) NOT NULL,
  `type` smallint(4) NOT NULL COMMENT '退款方式:全额,退95%,退80%,退50%',
  `refund` bigint(20) NOT NULL COMMENT '实际退款金额',
  `description` varchar(255) DEFAULT NULL COMMENT '退款描述',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `finish_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`refundid`) COMMENT '主键',
  UNIQUE KEY `refoundid_UNIQUE` (`refundid`) COMMENT '主键',
  KEY `orderid_INDEX` (`orderid`) COMMENT 'orderid索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单优惠';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_refund`
--

LOCK TABLES `order_refund` WRITE;
/*!40000 ALTER TABLE `order_refund` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_travellers`
--

DROP TABLE IF EXISTS `order_travellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_travellers` (
  `travellerid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出行人id',
  `orderid` bigint(20) NOT NULL COMMENT '订单id',
  `contactid` bigint(20) NOT NULL COMMENT '联系人id',
  `accountid` bigint(20) NOT NULL COMMENT '账户id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `id` varchar(255) NOT NULL DEFAULT '' COMMENT '证件号码',
  `id_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '证件类型:身份证0,护照1,港澳通行证2,台胞证3',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(255) NOT NULL DEFAULT '' COMMENT '手机',
  `gender` smallint(4) NOT NULL DEFAULT '0' COMMENT '性别:未知0,男1,女2',
  `birthday` bigint(20) NOT NULL DEFAULT '0' COMMENT '出生日期',
  `address` varchar(2000) DEFAULT '' COMMENT '地址',
  `emergency_contact` varchar(255) DEFAULT '' COMMENT '紧急联系人',
  `emergency_mobile` varchar(255) DEFAULT '' COMMENT '紧急联系手机',
  `roommate` varchar(255) DEFAULT '' COMMENT '室友',
  PRIMARY KEY (`travellerid`) COMMENT '主键',
  UNIQUE KEY `travellerid_UNIQUE` (`travellerid`) COMMENT '主键',
  KEY `accountid_INDEX` (`accountid`) COMMENT 'accountid索引',
  KEY `orderid_INDEX` (`orderid`) COMMENT 'orderid索引'
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='出行人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_travellers`
--

LOCK TABLES `order_travellers` WRITE;
/*!40000 ALTER TABLE `order_travellers` DISABLE KEYS */;
INSERT INTO `order_travellers` VALUES (1,1,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(2,1,13,10002,'董董','370104199109185519',0,'bre@125.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(3,3,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(4,3,14,10002,'赵伟','371526199210155235',0,'695910428@qq.com','13260319531',1,719078400000,NULL,NULL,NULL,NULL),(5,5,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(6,5,14,10002,'赵伟','371526199210155235',0,'695910428@qq.com','13260319531',1,719078400000,NULL,NULL,NULL,NULL),(7,4,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(8,4,14,10002,'赵伟','371526199210155235',0,'695910428@qq.com','13260319531',1,719078400000,NULL,NULL,NULL,NULL),(9,2,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(10,2,14,10002,'赵伟','371526199210155235',0,'695910428@qq.com','13260319531',1,719078400000,NULL,NULL,NULL,NULL),(11,6,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(12,6,14,10002,'赵伟','371526199210155235',0,'695910428@qq.com','13260319531',1,719078400000,NULL,NULL,NULL,NULL),(13,7,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(14,8,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(15,9,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(16,11,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(17,11,14,10002,'赵伟','371526199210155235',0,'695910428@qq.com','13260319531',1,719078400000,NULL,NULL,NULL,NULL),(18,12,0,10002,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(19,27,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(20,28,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(21,28,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(22,28,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(23,1,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(24,2,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(25,2,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(26,3,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(27,4,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(28,4,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(29,2,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(30,4,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(31,4,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(32,5,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(33,5,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(34,6,15,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(35,6,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(36,39,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(37,40,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(38,41,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(39,42,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(40,44,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(41,45,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(42,49,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(43,51,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(44,55,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(45,55,1,3,'测试','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(46,59,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(47,60,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(51,64,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(52,65,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(53,65,1,3,'测试','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(54,70,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(55,71,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(56,72,0,3,'董俊鹏','370104199109185519',0,'brent24@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(57,75,2,3,'ABC','370104199109185519',0,'b@126.com','18510248672',1,685123200000,NULL,NULL,NULL,NULL),(58,80,0,29,'赵伟','371526199210155235',0,'695910428@qq.com','13901319378',1,719078400000,NULL,NULL,NULL,NULL),(59,86,0,29,'赵伟','371526199210155235',0,'695910428@qq.com','13901319378',1,719078400000,'北京市',NULL,NULL,'赵璐'),(60,86,6,29,'嘿嘿嘿','371526199210155235',0,'6959@qq.com','13901319378',1,719078400000,NULL,NULL,NULL,'赵伟'),(61,87,0,29,'赵伟','371526199210155235',0,'695910428@qq.com','13901319378',1,719078400000,'北京市',NULL,NULL,'嘿嘿嘿'),(62,87,6,29,'嘿嘿嘿','371526199210155235',0,'6959@qq.com','13901319378',1,719078400000,NULL,NULL,NULL,'赵伟'),(63,92,0,29,'赵伟','371526199210155235',0,'695910428@qq.com','13901319378',1,719078400000,'北京市',NULL,NULL,'1'),(64,92,6,29,'嘿嘿嘿','371526199210155235',0,'6959@qq.com','13901319378',1,719078400000,NULL,NULL,NULL,'2');
/*!40000 ALTER TABLE `order_travellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_group`
--

DROP TABLE IF EXISTS `travel_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel_group` (
  `groupid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '发团id',
  `routeid` bigint(20) NOT NULL COMMENT '路线id',
  `start_date` bigint(20) NOT NULL DEFAULT '0' COMMENT '开始日期',
  `end_date` bigint(20) NOT NULL DEFAULT '0' COMMENT '结束日期',
  `title` varchar(255) NOT NULL DEFAULT '',
  `price` bigint(20) NOT NULL DEFAULT '0' COMMENT '价格',
  `status` smallint(4) NOT NULL DEFAULT '0' COMMENT '发团状态:未发布,招募,成行,结束,取消',
  `real` smallint(4) NOT NULL DEFAULT '0',
  `max_count` smallint(4) NOT NULL DEFAULT '0' COMMENT '最大人数',
  `min_count` smallint(4) NOT NULL DEFAULT '0' COMMENT '最小人数',
  `actual_count` smallint(4) NOT NULL DEFAULT '0' COMMENT '实际人数',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`groupid`,`add_time`) COMMENT '主键',
  UNIQUE KEY `groupid_UNIQUE` (`groupid`) COMMENT '主键',
  KEY `routeid_INDEX` (`routeid`) COMMENT 'routeid索引'
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='发团信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_group`
--

LOCK TABLES `travel_group` WRITE;
/*!40000 ALTER TABLE `travel_group` DISABLE KEYS */;
INSERT INTO `travel_group` VALUES (1,1,1467388800000,1468080000000,'一队',4480000,5,0,0,0,0,1473844427000,1473844427000),(2,1,1467993600000,1468684800000,'一队',4480000,5,0,0,0,0,1473844427000,1473844427000),(3,1,1468598400000,1469289600000,'一队',4480000,5,0,0,0,0,1473844427000,1473844427000),(4,1,1469203200000,1469894400000,'一队',4480000,5,0,0,0,0,1473844427000,1473844427000),(5,1,1469808000000,1470499200000,'一队',4480000,5,0,0,0,0,1473844427000,1473844427000),(6,1,1470412800000,1471104000000,'',4480000,5,0,0,0,0,1473844427000,1473844427000),(7,1,1471017600000,1471708800000,'',4480000,5,0,0,0,0,1473844427000,1473844427000),(8,1,1471622400000,1472313600000,'',4480000,5,0,0,0,0,1473844427000,1473844427000),(9,1,1472227200000,1472918400000,'',4480000,5,0,0,0,0,1473844427000,1473844427000),(10,1,1472832000000,1473523200000,'',4180000,5,0,0,0,0,1473844427000,1473844427000),(11,1,1473436800000,1474128000000,'',4180000,5,0,0,0,0,1473844427000,1473844427000),(12,1,1474041600000,1474732800000,'',4180000,5,0,0,0,0,1473844427000,1473844427000),(13,1,1474992000000,1475683200000,'报名',4680000,5,0,2,0,1,1473844427000,1473844427000),(14,1,1475078400000,1475769600000,'一队',4680000,5,0,0,0,0,1473844427000,1473844427000),(15,1,1475078400000,1475769600000,'二队',4680000,5,0,0,0,0,1473844427000,1473844427000),(16,1,1475164800000,1475856000000,'一队',4680000,5,0,0,0,0,1473844427000,1473844427000),(17,1,1475164800000,1475856000000,'二队',4680000,5,0,0,0,0,1473844427000,1473844427000),(18,1,1475251200000,1475942400000,'一队',4680000,5,0,0,0,0,1473844427000,1473844427000),(19,1,1475251200000,1475942400000,'测试',1000,5,0,2,0,0,1473844427000,1473844427000),(20,2,1472832000000,1473523200000,'',3980000,5,0,0,0,0,1473844427000,1473844427000),(21,2,1473436800000,1474128000000,'',3980000,5,0,0,0,0,1473844427000,1473844427000),(22,2,1474041600000,1474732800000,'',3980000,5,0,0,0,0,1473844427000,1473844427000),(23,2,1475078400000,1475769600000,'',3980000,5,0,0,0,0,1473844427000,1473844427000),(26,2,1475164800000,1475856000000,'',3980000,5,0,0,0,0,1473844427000,1473844427000),(28,2,1475251200000,1475942400000,'',3980000,5,0,0,0,0,1473844427000,1473844427000),(30,3,1478880000000,1479484800000,'',2980000,1,0,0,0,0,1473844427000,1473844427000),(31,3,1480089600000,1478102400000,'',2980000,1,0,0,0,0,1473844427000,1473844427000),(32,3,1481904000000,1482508800000,'',2980000,1,0,0,0,0,1473844427000,1473844427000),(33,3,1483113600000,1483718400000,'',3280000,1,0,0,0,0,1473844427000,1473844427000),(34,3,1483200000000,1483804800000,'',3280000,1,0,0,0,0,1473844427000,1473844427000),(40,4,1471622400000,1472140800000,'',3180000,5,0,0,0,0,1473844427000,1473844427000),(41,4,1472227200000,1472745600000,'',3180000,5,0,0,0,0,1473844427000,1473844427000),(42,4,1473436800000,1473955200000,'',3180000,5,0,0,0,0,1473844427000,1473844427000),(43,4,1474646400000,1475164800000,'报名',3380000,5,0,0,0,0,1473844427000,1473844427000),(44,1,1483200000000,1483718400000,'一队',3380000,1,0,10,0,2,1473844427000,1473844427000),(46,1,1483200000000,1483718400000,'二队',3380000,1,0,10,0,0,1473844427000,1473844427000),(48,1,1483200000000,1483718400000,'三队',3380000,1,0,10,0,0,1473844427000,1473844427000);
/*!40000 ALTER TABLE `travel_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_route`
--

DROP TABLE IF EXISTS `travel_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel_route` (
  `routeid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '路线id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '路线名称',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '副标题',
  `cover` varchar(2000) NOT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  `type` smallint(4) NOT NULL DEFAULT '0' COMMENT '类型:城市,近郊,短途,长途,国际',
  `days` smallint(4) NOT NULL DEFAULT '0' COMMENT '旅行天数',
  `area` smallint(4) NOT NULL DEFAULT '0' COMMENT '范围:西北,东北,西南,沿海',
  `departure` varchar(255) NOT NULL DEFAULT '' COMMENT '出发地点',
  `distination` varchar(255) NOT NULL DEFAULT '' COMMENT '结束地点',
  `route` varchar(2000) NOT NULL DEFAULT '' COMMENT '经过城市、景点',
  `description` varchar(2000) NOT NULL DEFAULT '' COMMENT '路线描述',
  `head_img` varchar(2000) NOT NULL DEFAULT '' COMMENT '头图地址',
  `map_img` varchar(2000) DEFAULT NULL,
  `min_price` bigint(20) NOT NULL COMMENT '最小价格',
  `max_price` bigint(20) NOT NULL COMMENT '最大价格',
  `wx_link` varchar(2000) NOT NULL DEFAULT '' COMMENT '微信路线宣传链接',
  PRIMARY KEY (`routeid`) COMMENT '主键',
  UNIQUE KEY `routeid_UNIQUE` (`routeid`) COMMENT '主键',
  KEY `name_INDEX` (`name`) COMMENT '名称索引'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='路线信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route`
--

LOCK TABLES `travel_route` WRITE;
/*!40000 ALTER TABLE `travel_route` DISABLE KEYS */;
INSERT INTO `travel_route` VALUES (0,'样例','样例','样例',1,0,7,1,'北京','北京','','','',NULL,0,0,''),(1,'西北大纵贯','翻越祁连山仰望青海湖踏上丝绸之路','西北辽阔苍茫与豪放<br>祁连雪山牧场<br>丝绸之路<br><br>青海茶卡连天公路<br>3000公里，追溯千年岁月',1,0,9,1,'兰州','兰州','兰州-祁连草原-卓尔山-祁连县-七彩丹霞-张掖-嘉峪关-莫高窟-鸣沙山月牙泉-敦煌-茶卡盐湖-青海湖-鸟岛-贵德-坎布拉-兰州','西北这三个字充满了神秘与魅力。祁连山，月牙泉，敦煌，青海湖，嘉峪关这些课本里反复出现的名词，像一次次遥远的呼唤，像一块块磁铁吸引着你前进向西。彻底放纵起来，豪迈起来，当一回地道西北汉子，甩开膀子，大口喝酒大口吃肉。','/p01/p01_000.jpg','/p01/p01_001.png',4480000,4680000,''),(2,'呼伦金秋','金色牧场上透过呼吸眺望广袤苍穹','秋天把大地染成一片金色<br>呼伦贝尔<br>大兴安岭<br>阿尔山 满洲里<br><br>在童话世界里<br>做一场金色的梦',1,0,9,1,'哈尔滨','齐齐哈尔','哈尔滨-扎龙湿地丹顶鹤故乡-齐齐哈尔-金帐汗-敖鲁古雅-室韦-黑山头-额尔古纳-满洲里-呼伦湖-阿尔山-阿尔山森林公园-乌兰浩特-齐齐哈尔','书里的“风吹草低见牛羊”，紫薇尔康骑马唱着“红尘作伴活的潇潇洒洒，策马奔腾共享人世繁华”，《狼图腾》“小狼小狼”的呼唤，草原从此在我们心里埋下了种子。总想有一天骑上匹高头骏马，脚踩铁镫，手擎长鞭，在无边无际的绿色波浪里，不去管方向，尽情驰骋向前。','/p02/p02_000.jpg','/p02/p02_001.png',3200000,3800000,''),(3,'云南盛放','大理苍山洱海走到丽江风花雪月','彩云之南永远是温暖的地方<br>鲜花弥漫芬芳<br><br>大理时光<br>洱海抱苍山<br>丽江风花雪月<br><br>找寻消失了的香格里拉',1,0,8,3,'昆明','丽江','昆明-大理古城-苍山国家地质公园-喜洲-沙溪-香格里拉-普达措-上虎跳峡-拉市海-丽江古城','这个曾经的梦想之地，如今却让很多人望而却步。这一次，避开偏见的源头，让稻稻还原一个最真实的滇西北给你，你一定会发现，整条路上全是让人惊艳的风景，那些藏在路线中的种种亮点，无不让滇西北依然绽放着无尽闪耀的光彩。 无论是大理古城的熙熙攘攘，苍山的云雾缭绕，喜洲超棒的海景和白族人真实的生活，还是沙溪厚重的历史变迁，石宝山归途的峡谷柔情，这才是南诏和大理古国的真实美丽；无论是虎跳峡江水奔腾的豪情壮志，还是拉市海深处世外桃源的村庄边骑马泛舟和发呆，这才是丽江自由自在的生活魅力彰显。这条几乎没太多游客的滇西北，宛若一次梦想之地回归真实的旅程，无比精彩！','/p03/p03_000.jpg','/p03/p03_001.png',2800000,3200000,''),(4,'寻梦甘南','香巴拉藏地王国到伊甸园扎尕那','蓝天牧场，风马经幡<br>高原风光和浓郁的藏地风情<br><br>九曲黄河、石城扎尕那<br>尕海湖、拉卜楞寺<br><br>这里是香巴拉王国的密藏',1,0,7,0,'兰州','兰州','兰州-夏河-拉卜楞寺-玛曲大草原-玛曲-黄河大桥-唐克-黄河九曲第一湾-若尔盖-迭部-扎尕那-郎木寺-尕海湖-合作-米拉日巴佛阁-兰州','作为一条经典的旅行路线，甘南被外界评为中国10大背包客路线之首，从美丽的高原，到辽阔的草原，到日落下的黄河蜿蜒，到神山下的桃源圣地，再到藏传佛教的洗礼。大气又不失灵性的高原风景和多元文化的碰撞结合，充分展现着她的魅力。','/p04/p04_000.jpg','/p04/p04_001.png',3300000,4800000,''),(5,'雪国漫步','进入雪的世界玩个痛快',' ',1,0,7,1,'长春','哈尔滨','','','',NULL,3280000,3580000,''),(7,'绚烂北疆','漫行于额尔齐斯河与图瓦人村落的岁月静好',' ',0,0,10,1,'北京','乌鲁木齐','乌鲁木齐-可可苏里湖-可可托海-乌伦古湖-布尔津-禾木-白哈巴-观鱼亭-喀纳斯湖-三道湾-贾登峪-乌尔禾魔鬼城-乌鲁木齐','在这一段深入北疆喀纳斯的旅程中，我们会带你游览壮丽的魔鬼城，可可托海的河流、白桦林。当然最重要的，我们将进入最西北角，探索在那片与哈萨克斯坦接壤的土地上，深藏在大山大水中的湖泊、河流、森林和图瓦人的村落。全程将拜访三个独特的村落，从一开始走访喀纳斯河流经的几个神奇河湾，爬上观鱼亭一睹整个喀纳斯湖完整的美，随后到达中哈两国交接的哈巴河边，感受西北第一村——白哈巴的宁静，最后爬上禾木的观景台，在黄昏与日出的光晕中，感受炊烟与晨雾中的村落之美。这是一趟很简单的旅程，一路上你会为这里的美景深深打动。','/p05/p05_000.jpg','/p05/p05_001.png',4800000,5300000,''),(8,'西藏江南','在林芝地区开启你的西藏梦',' ',0,0,9,6,'北京','拉萨','拉萨-尼洋河-巴松错-林芝-雅鲁藏布江大峡谷-南迦巴瓦峰-喇嘛岭寺-林芝-拉萨-纳木错-羊卓雍错-拉萨','如果你对西藏心生向往，却又畏惧于世界屋脊的高度，不妨从林芝地区开始你的首次西藏之旅。相比于西藏其它地区，这里海拔最低。雅鲁藏布大峡谷就像是喜马拉雅山脉的通道，印度洋的水汽畅通无阻，因而这里空气湿润，氧气充分，被称作西藏的江南。也因为充足的水汽，形成了壮美的南迦巴瓦雪山群和美丽的高原湖泊巴松错。行程最后我们回到拉萨，前往羊卓雍错和纳木错，一睹雪域圣湖的风采！','/p07/p07_000.jpg','/p07/p07_001.png',4800000,5600000,'');
/*!40000 ALTER TABLE `travel_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_id_mapping`
--

DROP TABLE IF EXISTS `wx_id_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_id_mapping` (
  `mappingid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '映射id',
  `accountid` bigint(20) NOT NULL COMMENT '账户id',
  `openid` varchar(255) NOT NULL COMMENT 'openid',
  `unionid` varchar(255) NOT NULL COMMENT 'unionid',
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`mappingid`),
  UNIQUE KEY `mappingid_UNIQUE` (`mappingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信的openid，union与accountid的映射';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_id_mapping`
--

LOCK TABLES `wx_id_mapping` WRITE;
/*!40000 ALTER TABLE `wx_id_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_id_mapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-02 22:41:19
