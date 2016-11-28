-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: elephant
-- ------------------------------------------------------
-- Server version	5.6.25

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
/*!40000 ALTER TABLE `account_binding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_contact`
--

DROP TABLE IF EXISTS `account_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_contact` (
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
  `area` varchar(45) DEFAULT NULL COMMENT '省市地区',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `emergency` tinyint(1) DEFAULT '0' COMMENT '紧急联系人',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`contactid`) COMMENT '主键',
  UNIQUE KEY `contactid_UNIQUE` (`contactid`) COMMENT '主键',
  KEY `accountid_INDEX` (`accountid`) COMMENT 'accountid索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_contact`
--

LOCK TABLES `account_contact` WRITE;
/*!40000 ALTER TABLE `account_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_contact` ENABLE KEYS */;
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
  `area` varchar(45) DEFAULT NULL COMMENT '省市地区',
  `address` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`accountid`) COMMENT '主键',
  UNIQUE KEY `accountid_UNIQUE` (`accountid`) COMMENT '主键',
  KEY `email_INDEX` (`email`) COMMENT 'email索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单优惠';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,1,1,100000,'单人优惠',0,0,0),(2,2,1,200000,'两人同行优惠',0,1500000000000,0),(3,6,1,500000,'订单时间相关优惠',0,1500000000000,0),(4,7,1,150000,'出行时间相关优惠',0,1500000000000,0),(5,8,1,88000,'路线相关优惠',0,1500000000000,0),(6,3,1,600000,'三人同行减600',0,1500000000000,0),(7,9,1,40000,'学生证优惠',0,1500000000000,0);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_code`
--

LOCK TABLES `discount_code` WRITE;
/*!40000 ALTER TABLE `discount_code` DISABLE KEYS */;
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
  `trade_no` varchar(255) NOT NULL COMMENT '支付宝交易id',
  `trade_status` smallint(4) NOT NULL COMMENT '交易状态',
  `notify_id` varchar(255) NOT NULL COMMENT '支付宝通知id',
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
  `gmt_refund` bigint(20) DEFAULT NULL,
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
  `routeid` bigint(20) DEFAULT '0',
  `value` bigint(20) NOT NULL DEFAULT '0' COMMENT '优惠减少的价格',
  `description` varchar(255) DEFAULT NULL COMMENT '优惠描述',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`order_discountid`) COMMENT '主键',
  UNIQUE KEY `order_discountid_UNIQUE` (`order_discountid`) COMMENT '主键',
  UNIQUE KEY `orderid_discountid_UNIQUE` (`orderid`,`discountid`),
  KEY `orderid_INDEX` (`orderid`) COMMENT 'orderid索引',
  KEY `discountid_INDEX` (`discountid`) COMMENT 'discountid索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单优惠';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_discount`
--

LOCK TABLES `order_discount` WRITE;
/*!40000 ALTER TABLE `order_discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_history`
--

DROP TABLE IF EXISTS `order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_history` (
  `historyid` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) NOT NULL COMMENT '订单id',
  `type` int(11) NOT NULL COMMENT '修改类型，新建，保存订单，保存并支付，微信支付，支付宝支付，支付取消，退款等等',
  `attach` varchar(255) NOT NULL COMMENT '额外信息',
  `add_time` bigint(255) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`historyid`),
  UNIQUE KEY `historyid_UNIQUE` (`historyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单操作历史';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_history` ENABLE KEYS */;
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
  `roommate` tinyint(1) DEFAULT '0' COMMENT '睡友是否服从组织安排',
  `add_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `create_time` bigint(20) DEFAULT NULL,
  `mod_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`orderid`) COMMENT '主键',
  UNIQUE KEY `orderid_UNIQUE` (`orderid`) COMMENT '主键',
  KEY `accountid_INDEX` (`accountid`) COMMENT 'accountid索引',
  KEY `groupid_INDEX` (`groupid`) COMMENT 'groupid索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
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
-- Table structure for table `order_traveller`
--

DROP TABLE IF EXISTS `order_traveller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_traveller` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_traveller`
--

LOCK TABLES `order_traveller` WRITE;
/*!40000 ALTER TABLE `order_traveller` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_traveller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_wxpay`
--

DROP TABLE IF EXISTS `order_wxpay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_wxpay` (
  `wxpayid` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) NOT NULL COMMENT '订单id',
  `type` smallint(1) NOT NULL DEFAULT '0' COMMENT '0 微信主动通知\n1 主动查询微信',
  `return_code` varchar(45) NOT NULL COMMENT '微信通信返回码',
  `result_code` varchar(45) NOT NULL COMMENT '微信支付结果码',
  `trade_status` varchar(45) DEFAULT NULL COMMENT '交易状态，主动查询微信支付信息的时候存在',
  `openid` varchar(255) NOT NULL COMMENT '支付的openid',
  `total_fee` bigint(20) NOT NULL COMMENT '支付金额',
  `transaction_id` varchar(255) NOT NULL COMMENT '微信订单id',
  `time_end` bigint(20) NOT NULL COMMENT '支付完成时间',
  `param` text NOT NULL COMMENT '微信支付通知参数',
  `add_time` bigint(20) NOT NULL COMMENT '记录添加时间',
  PRIMARY KEY (`wxpayid`),
  UNIQUE KEY `wxpayid_UNIQUE` (`wxpayid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信支付信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_wxpay`
--

LOCK TABLES `order_wxpay` WRITE;
/*!40000 ALTER TABLE `order_wxpay` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_wxpay` ENABLE KEYS */;
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
  `max_count` smallint(4) NOT NULL DEFAULT '0' COMMENT '最大人数',
  `min_count` smallint(4) NOT NULL DEFAULT '0' COMMENT '最小人数',
  `actual_count` smallint(4) NOT NULL DEFAULT '0' COMMENT '实际人数',
  `wx_qrcode` varchar(2000) NOT NULL DEFAULT '' COMMENT '微信群链接',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `mod_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`groupid`,`add_time`) COMMENT '主键',
  UNIQUE KEY `groupid_UNIQUE` (`groupid`) COMMENT '主键',
  KEY `routeid_INDEX` (`routeid`) COMMENT 'routeid索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发团信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_group`
--

LOCK TABLES `travel_group` WRITE;
/*!40000 ALTER TABLE `travel_group` DISABLE KEYS */;
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
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  `type` smallint(4) NOT NULL DEFAULT '0' COMMENT '类型:城市,近郊,短途,长途,国际',
  `days` smallint(4) NOT NULL DEFAULT '0' COMMENT '旅行天数',
  `area` smallint(4) NOT NULL DEFAULT '0' COMMENT '范围:西北,东北,西南,沿海',
  `season` varchar(255) NOT NULL,
  `departure` varchar(255) NOT NULL DEFAULT '' COMMENT '出发地点',
  `distination` varchar(255) NOT NULL DEFAULT '' COMMENT '结束地点',
  `route` varchar(1000) NOT NULL,
  `description` varchar(1000) NOT NULL DEFAULT '' COMMENT '路线描述',
  `cover` varchar(1000) NOT NULL,
  `head_img` varchar(1000) NOT NULL DEFAULT '' COMMENT '头图地址',
  `min_price` bigint(20) NOT NULL COMMENT '最小价格',
  `max_price` bigint(20) NOT NULL COMMENT '最大价格',
  `pchot` int(11) NOT NULL DEFAULT '0' COMMENT 'pc上的热度，用于排序等',
  `waphot` int(11) NOT NULL DEFAULT '0' COMMENT 'wap上的热度，用于排序等',
  PRIMARY KEY (`routeid`) COMMENT '主键',
  UNIQUE KEY `routeid_UNIQUE` (`routeid`) COMMENT '主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route`
--

LOCK TABLES `travel_route` WRITE;
/*!40000 ALTER TABLE `travel_route` DISABLE KEYS */;
INSERT INTO `travel_route` VALUES (1,'西北大纵贯','翻越祁连山仰望青海湖踏上丝绸之路',1,0,9,1,'6月-10月','兰州','兰州','兰州-塔尔寺-贵德-西宁-祁连草原-卓尔山-祁连县-七彩丹霞-张掖-嘉峪关-莫高窟-鸣沙山/月牙泉-敦煌-柴达木盆地-茶卡盐湖-青海湖-兰州','西北这三个字充满了神秘与魅力。祁连山，月牙泉，敦煌，青海湖，嘉峪关这些课本里反复出现的名词，像一次次遥远的呼唤，像一块块磁铁吸引着你前进向西。彻底放纵起来，豪迈起来，当一回地道西北汉子，甩开膀子，大口喝酒大口吃肉。','西北辽阔苍茫与豪放;祁连雪山牧场;丝绸之路;;青海茶卡连天公路;3000公里，追溯千年岁月','/p01/p01_000.jpg',3800000,4600000,7,7),(2,'呼伦金秋','金色牧场上透过呼吸眺望广袤苍穹',1,0,9,1,'6月-10月','哈尔滨','齐齐哈尔','哈尔滨-扎龙湿地-呼伦贝尔-金帐汗-额尔古纳-敖鲁古雅-根河-室韦-满洲里-呼伦湖-阿尔山市-阿尔山森林公园-齐齐哈尔','书里的“风吹草低见牛羊”，紫薇尔康骑马唱着“红尘作伴活的潇潇洒洒，策马奔腾共享人世繁华”，《狼图腾》“小狼小狼”的呼唤，草原从此在我们心里埋下了种子。总想有一天骑上匹高头骏马，脚踩铁镫，手擎长鞭，在无边无际的绿色波浪里，不去管方向，尽情驰骋向前。','秋天把大地染成一片金色;呼伦贝尔;大兴安岭;阿尔山 满洲里;;在童话世界里;做一场金色的梦','/p02/p02_000.jpg',3200000,4400000,5,5),(3,'彩云盛放','从大理走到丽江的风花雪月',1,0,7,3,'10月-3月','昆明','丽江','昆明-大理古城-洱海-苍山-喜洲-沙溪-香格里拉-普达措-上虎跳峡-拉市海-丽江','这里有毫无保留的阳光和蓝天，多彩的民族服饰迷人双眼；这里有着令人奢望的丰富纬度，从终年不化的高原雪山到四季一夏的热带河谷；这里还有让人割舍不下的悠然时光，每到一处都会有一段文艺的不得了的故事。丰富的物产，四季不断的水果，便捷的交通连接着忘情的山水，云南之于旅行者，便是天堂，彩云为之绽放。','彩云之南永远是温暖的地方;鲜花弥漫芬芳;;大理时光;洱海抱苍山;丽江风花雪月;;找寻消失了的香格里拉','/p03/p03_000.jpg',2800000,3200000,8,8),(4,'甘南遗梦','香巴拉草原到扎尕那的藏地体验',1,0,7,0,'6月-10月','兰州','兰州','兰州-夏河-拉卜楞寺-玛曲-黄河大桥-唐克-黄河九曲第一湾-若尔盖-迭部-扎尕那-郎木寺-尕海湖-合作-米拉日巴佛阁-兰州','蓝天白云、辽阔草原、风马经幡，美丽的高原风光和浓郁的藏地风情。甘南位于祖国西北，是黄土高原向青藏高原的过渡地带，同样也是汉、藏、回等多民族的融合地带。大气又不失灵性的高原风景和多元文化的碰撞结合，充满魅力与灵性，迷人却并未被大量游人涌入。独一无二的低海拔优势，被忽略而纯粹的风俗，相对适宜的气候，一百年前《国家地理》的照片，一百年后《天下无贼》，这里是流传千年“香巴拉王国“。','蓝天牧场，风马经幡;高原风光和浓郁的藏地风情;;九曲黄河、石城扎尕那;尕海湖、拉卜楞寺;;这里是香巴拉王国的密藏','/p04/p04_000.jpg',3300000,4800000,6,6),(5,'雪国漫步','千里冰封国度拥抱最浪漫的冬天',1,0,8,1,'12月-3月','长春','哈尔滨','长春-松花湖-五家山-吉林-雾凇岛-二道白河-长白山-雪乡-林海雪原-羊草山-雪谷-向阳-伏尔加庄园-哈尔滨','零下30℃，齐腰深的积雪，冬捕滑雪包饺子，雪国里才有真正浪漫的冬天。松花湖古老冬捕凿开冰层捞起鲜鱼下锅熬汤；雾凇岛暖暖阳光透过晶莹的雾凇洒在北国大地上；长白山冰与火之间温泉里享受“葛优躺”；雪乡搂着白雪覆盖的山村安然入眠；堆雪人打雪仗，一言不合把小伙伴丢进雪堆里；穿林海，跨雪原，当回杨子荣气冲霄汉；伏尔加庄园跟俄罗斯战斗民族来个结实熊抱。万里雪飘中，静静漫步过冬天。','穿行林海于雪原;长白山80℃温差冰与火之歌;;滑雪冬捕包饺子;晶莹雾凇;皑皑雪乡;;浪漫哈尔滨;战斗民族的激情熊抱','/p05/p05_XX_28.jpg',3280000,3580000,9,9),(6,'北境踏雪','穿雪乡跨雪山撒欢北境',1,0,4,1,'12月-3月','吉林','哈尔滨','吉林-雾凇岛-雪乡-林海雪原-羊草山-雪谷-向阳-哈尔滨 ','不能把小伙伴丢到雪堆里，算什么冬天的旅行！齐腰深的积雪里穿行，身体和灵魂一起燃烧，让日益麻木的自己，重新感受活着。一言不合拉着小伙伴冲进雪堆，撒泼打滚全身埋进去，欢乐和笑声在-30℃空气里沸腾。把下山的路当滑梯，从羊草山顶一路滑下来。走两步，滑两步，笑得合不拢嘴，你追我赶，像小时候，玩起来就忘了一切。','拉着小伙伴在雪堆里打滚;一屁股从山顶滑到山脚;;漫天大雪;回到小时候;玩起来就忘记一切;;夜幕下冰雪与美食一起闪耀;来了就不想走','/p05/p05_XX_13.jpg',1980000,2280000,10,10),(11,'绚烂北疆','漫行于额尔齐斯河与图瓦人村落的岁月静好',0,0,10,1,'','北京','乌鲁木齐','','在这一段深入北疆喀纳斯的旅程中，我们会带你游览壮丽的魔鬼城，可可托海的河流、白桦林。当然最重要的，我们将进入最西北角，探索在那片与哈萨克斯坦接壤的土地上，深藏在大山大水中的湖泊、河流、森林和图瓦人的村落。全程将拜访三个独特的村落，从一开始走访喀纳斯河流经的几个神奇河湾，爬上观鱼亭一睹整个喀纳斯湖完整的美，随后到达中哈两国交接的哈巴河边，感受西北第一村——白哈巴的宁静，最后爬上禾木的观景台，在黄昏与日出的光晕中，感受炊烟与晨雾中的村落之美。这是一趟很简单的旅程，一路上你会为这里的美景深深打动。','ni','/p05/p05_000.jpg',4800000,5300000,0,0),(12,'西藏江南','在林芝地区开启你的西藏梦',0,0,9,6,'','北京','拉萨','','如果你对西藏心生向往，却又畏惧于世界屋脊的高度，不妨从林芝地区开始你的首次西藏之旅。相比于西藏其它地区，这里海拔最低。雅鲁藏布大峡谷就像是喜马拉雅山脉的通道，印度洋的水汽畅通无阻，因而这里空气湿润，氧气充分，被称作西藏的江南。也因为充足的水汽，形成了壮美的南迦巴瓦雪山群和美丽的高原湖泊巴松错。行程最后我们回到拉萨，前往羊卓雍错和纳木错，一睹雪域圣湖的风采！','wowowowo','/p06/p06_000.jpg',4800000,5600000,0,0);
/*!40000 ALTER TABLE `travel_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_route_day`
--

DROP TABLE IF EXISTS `travel_route_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel_route_day` (
  `routedaysid` bigint(20) NOT NULL AUTO_INCREMENT,
  `routeid` bigint(20) unsigned NOT NULL,
  `dayno` int(10) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `imgs` varchar(1000) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `detail` varchar(2000) NOT NULL,
  `scenery` varchar(255) NOT NULL,
  `star` varchar(255) DEFAULT NULL,
  `food` varchar(255) DEFAULT NULL,
  `distance` varchar(255) DEFAULT NULL,
  `hotel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`routedaysid`),
  UNIQUE KEY `routedaysid_UNIQUE` (`routedaysid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线行程信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route_day`
--

LOCK TABLES `travel_route_day` WRITE;
/*!40000 ALTER TABLE `travel_route_day` DISABLE KEYS */;
INSERT INTO `travel_route_day` VALUES (1,1,1,' (集合日) 兰州','/p01/p01_201.jpg','第一个图片','今天是集合日，没有活动安排，当天到达即可！ \n到兰州直接入住酒店，领队会热情招待。详细报名后见微信群~ \n \n还没到兰州，就见黄土万壑、戈壁飞沙，粗犷豪放扑面而来。\n城市夹在黄河谷地间，风景就在两岸。霸气耸立**中山铁桥**横跨滚滚黄河，羊皮筏子随波漂流。尝尝地道八宝茶，逛逛水车博物馆、**黄河母亲**雕像，大西北我来啦。 \n \n除了牛肉面名声在外，钻进木塔巷、大众巷、正宁街，寻找当地独有的酿皮、晶糕、杏皮水、洋芋片、牛奶鸡蛋醪糟、羊肉手抓，美味那可是应接不暇。 \n \n晚上9：00召开**见面会**，认识领队和一路同行小伙伴，不要迟到哦。','给我一支兰州;中山桥;黄河母亲',NULL,NULL,NULL,'格林豪泰/派酒店（标准间）'),(2,1,2,'兰州-塔尔寺-贵德-西宁','/p01/p01_202.jpg;/p01/p01_203.jpg','1;2','早上出发，领队会带小伙伴们熟悉起来，欢声笑语中奔向**塔尔寺**。这里是宗喀巴大师诞生地，黄教六大寺院之一，感受藏传佛教的神秘和传奇。之后前往**贵德**，一路山峦草甸丹霞，海拔上升，慢慢适应高原反应。“天下黄河贵德清”，昨日兰州滚滚而去，今天她的清澈一定让你倍感惊奇。 \n \n傍晚回到**西宁**，这座被称为夏都的城市不但有东关大寺灿烂的灯光，更有数不清的好吃的！奔向莫家街、大十字，德禄酸奶、甜醅、马忠食府、益鑫羊肉手抓、面片，西宁羊肉毫无膻味，酸奶更是浓厚深邃冲击味蕾，口水都要流出来了！','塔尔寺;初上高原;拉鸡山;西宁夏城','亮点：高原上的纯粹信仰','兰州早餐','400KM/5h','派酒店/景尚假日（标准间）'),(3,1,3,'西宁-祁连-卓尔山','/p01/p01_204.jpg;/p01/p01_205.jpg','1;2','跨越祁连山脉，牦牛，羊群，空旷的柏油路上我们一路向前。翻过**黑泉水库**，眼前是一片草丰水美，牛马撒欢的河滩牧场。这可是我们“独家”发现的绝美牧场，走进草甸野花，踏过流水浅滩，零距离接触牛群牧马。 \n \n然后一路翻山越岭，在3000海拔的祁连山中穿梭，俯瞰山脚广袤土地平原。下午来到**祁连大草原**，山峦叠嶂中竟然隐藏着如此平坦开阔草场。那延绵壮美一定让你惊讶的合不拢嘴。傍晚深入天境祁连**卓尔山**，360度俯瞰整个山谷，遥望终年积雪牛心山，脚下蜿蜒祁连县城。 \n \n当晚入住祁连山脚回族人家，一整面玻璃落地窗，看着雪山吃一顿地道回族大餐。夜幕来临，世界都清静了，抬头是银河漫天繁星，低头是皑皑祁连。风景绝美被誉为隐藏最好的香格里拉。','高原牧场;祁连山脉;祁连大草原;卓尔山;雪山脚下人家','祁连天境，皑皑雪山莽莽草原','西宁早餐/回族晚餐','280KM/5h','雪山下回族人家（标准间）'),(4,1,4,'祁连山-七彩丹霞-张掖','/p01/p01_206.jpg;/p01/p01_207.jpg','1;2','今天继续穿越祁连山深处的草原，这里没有过往的游客，没有城市的喧嚣，天地间剩下的只有宁静和宽容。然后我们将跨越海拔3685米的**峨堡岭垭口**，作短暂的停留，像藏民一样在垭口挥洒龙达为来世祈福。慢慢行走，在这里感受无边无际的高山草原的美丽。 \n \n下午抵达张掖的**七彩丹霞**，深入这片神奇的地貌，在日光的映衬下，多变的色彩像是打翻了调色盘，红的如火，黄得似金，灰的如钢。各处造型奇特的山地丘陵色彩斑斓、气势磅礴。在这里呆上一整个下午，看夕阳在五彩的丹霞山脉间缓缓流淌。张掖**甘州市场**热闹非凡，卷子鸡、搓鱼儿、炒炮痛痛快快来顿大餐。','峨堡岭垭口;七彩丹霞;甘州市场','上天打翻调色盘染红七彩丹霞','祁连早餐','280KM/4h',' 吉吉/商汇/徽商（标准间'),(5,1,5,'张掖-嘉峪关-瓜州','/p01/p01_208.jpg;/p01/p01_209.jpg','1;2','早晨从张掖出发，沿着河西走廊一路往西，到达咽喉重镇**嘉峪关**。嘉峪关市与酒泉市连成一片，不仅有浓厚历史深度更是新兴工业城市，素有“钢城”美誉。规划整齐道路宽阔，其中**铁镜市场**可是当地人的最爱，眼镜烤肉，炒饼，卷边鸡，烤羊皮，杏皮水，快来大快朵颐。\n \n巍峨的祁连雪山之下，是古老的**嘉峪关关城**，其连同山脊之上的**悬壁长城**，共同构筑了明长城西端的第一重关，也是明长城全线中保存最完好的一个。“严关百尺界天西，万里征人驻马蹄。”登上城楼远眺长城似游龙浮动于浩瀚戈壁滩，白雪覆盖的祁连山脉在天边绵延。出关到达**瓜州**，烤鱼串串香甜哈密瓜，夜市走你！','嘉峪关;悬壁长城;烤肉飘香;瓜州夜市','万里长城永不倒，嘉峪关已过千年','张掖早餐','500KM/5h','融金洲海/国风（标准间）'),(6,1,6,'瓜州-莫高窟-鸣沙山','/p01/p01_210.jpg;/p01/p01_211.jpg','1;2','一早前往丝绸之路上的艺术瑰宝**莫高窟**，精美壁画塑像讲述着时间的故事，历史如同卷轴般展开。洞窟内鬼斧神工的艺术瑰宝、墙面跳跃的色彩、饱满的人物形象、充满意境的寓意，会让每个人凝神惊叹。 \n \n“锦缥细展墨花翻，河东二柳敦煌索。”**鸣沙山**又旅行的一个高潮。一汪形似月牙的泉水衬托着这个绿洲边缘的大沙丘。进入这浩瀚的沙漠，手脚并用爬到山顶，屹立沙丘之上远望日渐消退的那一弯孤月，观赏沙漠中的那一湾动人清泉。不用担心时间，酒店就在鸣沙山脚下。 \n \n夕阳落下天色渐晚，如果你对莫高窟的艺术震撼意犹未尽，那么不妨再去看一场“敦煌盛典”演出；如果你想继续吃吃吃节奏，那就去热闹非凡**沙洲夜市**，驴肉黄面、骆驼掌，杏皮水做好准备迎接舌尖上的盛宴。','莫高窟;鸣沙山;月牙泉;沙洲夜市','寻找时间里零落的丝绸之路','瓜州早餐','120KM/1.5小时','映月山庄/敦煌人家（标准间）'),(7,1,7,'敦煌-柴达木盆地-茶卡盐湖','/p01/p01_212.jpg;/p01/p01_213.jpg','1;2','早餐后，驱车穿行于**柴达木盆地**之上，惊见延绵无际的戈壁滩，苍鹰盘旋于头顶，这便是来自青藏高原的礼遇。一路荒无人烟，笔直的公里延伸到视线尽头，宽阔的柏油公路上只有我们向前奔驰。仰望着拔地而起高高在上的铁路桥，那是通往青藏高原的“天路”，这个时候就一起大声歌唱吧，尽情感受西北的广袤和苍茫。 \n \n傍晚抵达**茶卡盐湖**，盐湖如雪堆积般的白色，湖水如镜，吸饱了盐，倒映出湛蓝的天巍峨的山，像极了玻利维亚的天空之境。踩着又像沙滩又像雪堆的盐滩，漫步到湖中心张开双臂拥抱倒影，挥舞丝巾肆意拍照，白色世界里留下耀眼的色彩绚烂。 \n \n跑遍茶卡，终于在村长家找到了满意住宿。今年新建三层楼远离灯光污染，顶层天台专门拍星空。夜幕降临，整个**银河**在天空中静静旋转，一抬头就清晰可见，“银河！真的是一条河！”“流星！快看流星！”支起三脚架对准夜空，听老青海人讲述茶卡历史，今夜注定难眠。','柴达木盆地;青藏铁路;茶卡盐湖;银河星空','茶卡盐湖边等银河里流星滑过','敦煌早餐/茶卡晚餐','700KM/8h','茶卡村长家（标准间）'),(8,1,8,'茶卡-青海湖-兰州','/p01/p01_214.jpg;/p01/p01_215.jpg','1;2','茶卡不止有银河星空，还有日出等着你，金色的太阳，光芒万丈，从山的那边缓缓爬出来，云彩和湖面被照耀成一片金黄色，真是美不胜收！ \n \n翻过一座座山**青海湖**赫然出现。一眼望去一定以为自己到了海边，水天一色，遥遥无尽头，烟波浩淼、碧波连天。聆听3000米浪花拍打着沙滩。近处笔直的油柏路，金灿灿的油菜花，远处是蓝色飘带般的青海湖，更远的是连绵的山，山背后是青藏高原团团白云湛蓝青天。 \n \n傍晚我们回到**兰州**，最后大干一场，来一顿只属于西部的豪情晚宴！与小伙伴们依依不舍挥别吧，这场难忘旅行也就在这里结束啦~ \n（约晚6点到达，当晚出发小伙伴要定8点以后火车/飞机，以免堵车延误）','盐湖日出;青海湖','3000米海拔上的陆心之海','茶卡早餐','400KM/5h','格林豪泰/派酒店（标准间）'),(9,1,9,'(解散日)兰州','/p01/p01_216.jpg','1;2','今天是解散日，这一天没有活动安排，大家洒泪说再见，根据自身情况选择合适时间的航班或火车回到温暖的家。\n \nPS：走之前别忘了**兰州拉面**。 \n正宗的牛肉面馆都是早上经营，下午两点就关门了，牛肉面在兰州是早餐、午餐的代言词。街边上随便一个小馆儿，味道都是独一无二的。一旦出了兰州，哪怕挂着“正宗”招牌，哪怕同样的回民经营，味道也是完全不同的，最正宗的牛肉面必须要在兰州吃！','兰州拉面;再见大西北',NULL,NULL,NULL,NULL),(10,2,1,'(集合日) 哈尔滨','/p02/p02_201.jpg','1','今天是集合日，没有活动安排，当天到达即可！  \n到哈尔滨直接入住酒店，领队会热情招待。详细报名后见微信群~  \n              \n这里有浓厚俄罗斯风情，中央大街可是出大片的浪漫步行街，走到头就是松花江。300米就是圣·索菲亚大教堂。  \n              \n吃货更是有福啦，马迭尔餐厅，哈尔滨红肠，马迭尔冰棍，格瓦斯，当地人最爱的金刚山烧烤，眼花缭乱呐！  \n              \n晚上9：00召开**见面会**，认识领队和一路同行小伙伴，不要迟到哦。','哈尔滨;圣·索菲亚教堂;中央大街;松花江',NULL,NULL,NULL,'尚客优/派酒店（标准间）'),(11,2,2,'哈尔滨-齐齐哈尔','/p02/p02_202.jpg;/p02/p02_203.jpg','1,2','早上从哈尔滨出发，向着大兴安岭地区前进。一路上我们文艺二逼的领队会带着小伙伴们逐渐熟悉起来，欢声笑语中一路向北奔向草原奔向大兴安岭。6-9月正是观**丹顶鹤**的最佳时节，**扎龙自然保护区**作为世界最大的芦苇湿地，丹顶鹤的故乡，聚集了成千上万鸟类，更是有让人尖叫不已的鹤群放飞表演。景区内湖泽密布，苇草丛生，是水禽等鸟类栖息繁衍的天然乐园。这些生灵们浑身上下透着一股仙气，一抬颈，一投足，一个姿势就可以叫人凝神注目百看不厌。','扎龙湿地;丹顶鹤起舞;齐齐哈尔','丹顶鹤翩然起舞，比翼双飞','哈尔滨早餐','300KM/4h','尚客优/派酒店（标准间）'),(12,2,3,'齐齐哈尔-海拉尔-莫日格勒河（金帐汗）','/p02/p02_204.jpg;/p02/p02_205.jpg','1,2','今天我们的目的地是呼伦贝尔大草原！中午抵达呼伦贝尔市（原名海拉尔，当地人都这么称呼哦），一望无际的**呼伦贝尔大草原**即将映入眼帘。天那么蓝那么高远，成片的羊群、牛群、马群点缀在绿色的海洋上，除了一望无际的草原，就什么也看不到了，除了眼前的车辙，再也分不清东南西北。  \n\n傍晚抵达莫日格勒河边的游牧民族**金帐汗**部落，感受下马酒、祭敖包还有搏克（蒙古摔跤）表演。**莫日格勒河**可是被誉为“天下第一曲水”来吧一起放风筝、玩游戏，穿蒙古族服饰拍美美的照片！  \n\n漫步曲水河畔，尽情拥抱草原，近距离接触群牛羊，天气允许将看到最美的草原日落。晚上可是有烤全羊大餐，篝火晚会等着大家，打起鼓来唱起歌，用肉和欢笑填饱你的胃！','呼伦贝尔大草原;金帐汗部落;莫日格勒河;篝火烤全羊','当回草原上的蒙古人','齐齐哈尔早餐/烤全羊晚餐','500KM/6h','蒙古包帐篷（多人间）'),(13,2,4,'莫日格勒河-敖鲁古雅-室韦','/p02/p02_206.jpg;/p02/p02_207.jpg','1,2','清晨在草原中醒来，晨曦中漫步草原，仿佛还能听见远古时候游牧民族的歌声！今天我们去往一个神秘地方**敖鲁古雅**部落。这里是鄂温克族聚集地，仍然保持着原始社会末期的生产、生活方式，以驯鹿为生。在这里可以近距离接触**驯鹿**，拾一把草料，它们会像乖宝宝一样始终跟在你身边。  \n\n在**根河**午餐后，继续一路向北前往中国最具魅力小镇**室韦**。室韦紧邻中俄两国的界河**额尔古纳界河**，可以远眺对岸俄罗斯村庄，边境小镇远离都市，又受异域风情影响，极具特色。入住的房子叫**木刻楞**是极具俄罗斯特色的住宿，纯木质结构的房间，通常不用铁钉用木楔打造。用过晚饭热情的俄罗斯族大妈会教我们做列巴，讲讲这片土地的故事。','敖鲁古雅驯鹿;室韦;木刻楞;额尔古纳界河','抚摸驯鹿的犄角，住进俄罗斯族木屋','蒙古包早餐','400KM/5h','俄罗斯族木刻楞（标准间）'),(14,2,5,'室韦-额尔古纳-满洲里','/p02/p02_208.jpg;/p02/p02_209.jpg','1,2','今天早上的惊喜是什么？早起自己动手挤牛奶！煮熟后让大家品尝劳动果实。下一站是**黑山头**，让我们“策马奔腾共享人世繁华~”在专业马倌的带领下在草原里自由练习骑马，俯瞰草原与根河湿地间的牛马羊群，共同陶醉在美丽的大自然中。  \n\n继续乘车前往额尔古纳市，登上西山，远眺亚洲最大的湿地—**额尔古纳湿地**，湿地中曲水流淌，河道左右绿草茵茵，山坡上分布各色野花，还有一座心形岛屿坐落曲水之中。  \n\n晚上就到**满洲里**啦，地处边境，异国风情建筑、穿梭在街道上的俄罗斯车辆，擦身而过的俄罗斯男女，让人切身体会到这座城市的魅力。满洲里夜景非常赞，**婚姻宫**俄国婚礼，**中俄商贸街**值得一逛。吃上一顿美美的俄式料理，任我们漫步在这童话世界里。','挤牛奶;额尔古纳湿地;满洲里;婚姻宫俄式婚礼','异国风情街道上的那抹斜阳','室韦早餐','400KM/5h','尚客优/派酒店（标准间）'),(15,2,6,'满洲里-阿尔山','/p02/p02_210.jpg;/p02/p02_211.jpg','1,2','早上前往**国门景区**，参观国门、界碑，登国门观俄罗斯小镇后贝加尔斯克街景，中俄41号界碑，中共秘密交通线遗址，蒸汽火车头广场。  \n\n之后深入**呼伦贝尔草原**腹地，车子两边就是呼伦贝尔草原的腹地。蓝天青草在两旁连绵不绝、牛羊在悠闲自得的吃草。那样的美丽画卷会让大家心旷神异。过内蒙古第一大湖**呼伦湖**，蒙语“达赉湖”，意思是像海一样大，在湖岸，看浪涛拍打细沙，感受草原上的海。  \n\n傍晚到阿尔山住宿，领略森林之城的独特景色，整齐的街道、独特的木屋别墅，还有日式风格独特建筑的**阿尔山火车站**。有兴趣的人还可以去看看温泉博物馆泡下温泉，品味温泉的历史沿革。','中俄国门;套娃广场;呼伦湖金海岸','天苍苍野茫茫，风吹草低见牛羊','满洲里早餐','450KM/6h','尚客优/派酒店（标准间）'),(17,2,7,'阿尔山森林公园','/p02/p02_212.jpg;/p02/p02_213.jpg','1,2','今天可是超级重头戏，绝不容错过的美景。**阿尔山森林公园**由天池，杜鹃湖，石塘林，三潭峡，驼峰岭天池，柴河源大峡谷，鹿鸣湖，龟背岩，地池，哈拉哈河等十余处景观组成。每个景点都足够你细细品味。  \n\n**杜鹃湖**野凫成群，灰鹤、天鹅栖息，水清如镜，湖周围层林尽染，湖面金波荡漾。**天池**999级台阶，如晶莹碧玉镶嵌在雄伟瑰丽、林木苍翠的高山之巅，久旱不涸，久雨不溢，不升不降。**石塘林**火山喷发遗迹，错综崎岖、沟壑纵横的熔岩凝结块，矿物质堆积的池塘绚烂多彩，引人注目。  \n\n**哈拉哈河**日落映红了层层森林，参天古树，蜿蜒河道，更是绝美。今天我们就不走啦，直接住在阿尔山森林宾馆里，欣赏不完的美景明天继续！','阿尔山森林公园;杜鹃湖;天驼峰池;石塘林;哈拉哈河日落','迷失在无边童话森林里','阿尔山早餐','200KM/3h','阿尔山森林宾馆（标准间）'),(18,2,8,'阿尔山-乌兰浩特-齐齐哈尔','/p02/p02_214.jpg;/p02/p02_215.jpg','1,2','早上在鸟语花香中醒来，继续游览，**三潭峡**由映松潭、映壁潭、龙凤潭三个深潭组成，湍急的哈拉哈河从河谷穿行，珠飞玉卷，火山熔岩布满河床，水深处波平如镜，难见其底，水浅处人可踏石涉水而过。**驼峰岭天池**形成距今已有30万年。站在湖水边，清风徐徐，倒影绰绰，仿佛远离人间，天人相接，心旷神怡。  \n\n离开之前特别推荐**呼伦贝尔雪糕**5元一个，奶香四溢，大饱口福。出发前往**齐齐哈尔**，途径科尔沁大草原北麓--乌兰毛都草原，这里是与呼伦贝尔大草原的交汇处，从平原至山地的过渡段，经过**乌兰浩特**，俯瞰整个乌兰毛都草原，牛羊点点，九曲十八弯。  \n\n晚上回到齐齐哈尔，我们的旅程就要告一段落啦！（约晚8点到达齐齐哈尔，当晚出发小伙伴请务必买11点以后火车飞机，以防堵车延误。）','三潭峡;驼峰岭天池;天池;呼伦贝尔雪糕','清澈湖水配上甜美的山涧','阿尔山早餐','600KM/7h','尚客优/派酒店（标准间）'),(19,2,9,'(解散日)齐齐哈尔','/p02/p02_216.jpg','1','今天是解散日，这一天没有活动安排，大家依依不舍挥别，根据自身情况选择合适时间的航班或火车回到温暖的家。  \n\n美丽的大草原，美丽的阿尔山，就要跟你说再见啦！','解散日;再见大草原',NULL,NULL,NULL,NULL),(20,3,1,'(集合日) 昆明','/p03/p03_201.jpg','1','今天是集合日，没有活动安排，当天到达即可！  \n到昆明直接入住酒店，领队会热情招待。详细报名后见微信群~  \n\n西南联大传承至今的文艺气息；“翠堤春晓”，红嘴鸥飞翔的翠湖；亚洲最大鲜花市场斗南花市；“滇”字来源，昆明之魂滇池。春城之美流连忘返。  \n\n百年老字号建新园；舌尖中国-福照楼汽锅鸡；现烤芬芳清香，吃一口一辈子难忘的嘉华鲜花饼，吃货们千万不要放过它们！  \n\n晚上9：00召开**见面会**，认识领队和一路同行小伙伴，不要迟到哦。','春城昆明;翠湖;滇池海埂;云南大学;云南讲武堂',NULL,NULL,NULL,'尚客优/派酒店（标准间）'),(21,3,2,'昆明-大理','/p03/p03_202.jpg;/p03/p03_203.jpg','1,2','早餐后驱车前往**大理**，一路上我们文艺二逼的领队会带着小伙伴们逐渐熟悉起来，像一支队伍永远在美景，美食，欢脱第一线战斗~  \n\n这是一座面朝大海，春暖花开的古城，背靠苍山连绵山峦，坐拥洱海碧蓝湖水，四方的街道游人如织，路边溪水潺潺，行走在夏日的微风里探索这座南诏古国最辉煌的城池。忘记时间吧，感受尘封的岁月凝固的光阴。  \n\n入住古城内**特色客栈**，古香古色的三层阁楼，石栏杆木屏风，层层叠叠花草繁盛。去**人民路**寻找特色小店，去**洋人街**大快朵颐。去古城的夜色中寻找灯火，寻找酒吧，寻找别有一番风味的浪漫。','大理古城;人民路;洋人街;彝族风情楼','沉睡岁月中的古城，凝固了大理的时光','昆明早餐','350KM/4h','大理彝族风情楼（标准间）'),(22,3,3,'大理-喜洲','/p03/p03_204.jpg;/p03/p03_205.jpg','1,2','早晨的大理最静谧惬意，爬上城墙，东面洱海湛蓝，西面苍山巍峨，商户半闭未开，街上静悄悄鲜有游人，古意最是浓。  \n\n来到古城外的**苍山国家地质公园**，乘**感通索道**而上，看**清碧溪**娟娟清溪从潭中流出汇入洱海，走上一段美丽的**云游玉带路**，高处俯瞰大理古城和洱海全貌。  \n\n下午来到**喜洲**--白族的聚集地，浓郁的民族风情处处可见。我们特意挑选了离海最近的**海景客栈**，观洱海全景，远处的南诏岛，对岸的挖色镇，更远处的大理古城，一收眼底。  \n\n请白族老伯伯套上马车，带我们前往**海舌生态公园**，这是一处延伸到洱海中1公里的陆地，有着无敌的海景。最浪漫的时段，莫过于**洱海月**，在洱海边看月升，在我们住的海景客栈就能看到！','苍山公园;喜洲白族风情;海舌公园;无敌海景房','爬在床上看洱海，静静等一轮月升','大理早餐','30KM/1h','洱海无敌海景房（标准间）'),(23,3,4,'喜洲-沙溪','/p03/p03_206.jpg;/p03/p03_207.jpg','1,2','清晨就在客栈感受洱海的日出吧，慢悠悠的前往喜洲老街**四方街**，赶一趟**早集**。这里汇集了众多当地特色小吃，喜洲粑粑，米线，饵丝看着就流口水。之后钻进喜洲的街巷，感受白族人的传统建筑工艺，“三坊一照壁“、”四合五天井”走进白族望族大户的那些繁荣岁月。  \n\n走出喜洲，走向**周城**，另一处白族聚集地，这里**扎染**工艺源远流长，让你大开眼界。中午我们途径**茈碧湖**，洱海的水源之一，湖泊被两山夹抱，周边村庄不多，还保留着清幽的美。前往湖对岸的**世外梨园**，吃一顿安静舒适的当地农家饭。  \n\n午后前往**沙溪古镇**，这个被太多游客忽略的极品小镇，茶马古道唯一幸存的古街市。214国道没有经过沙溪，反而成了它的幸运，古镇建筑避开了现代化浪潮，完全保留了茶马古道百年前的风光，穿梭并陶醉在美丽的田园风光中，古朴宁静的气息让你来了就不想走。','四方街早集;周城扎染;茈碧湖;沙溪古镇','百年老巷里重走茶马古道沧桑','午餐世外梨园农家饭','120KM/2h','马帮庭院客栈（标准间）'),(24,3,5,'沙溪-香格里拉','/p03/p03_208.jpg;/p03/p03_209.jpg','1,2','早起逛逛沙溪古镇的周边村落，让你恍然回到百年前的老旧时光，慢慢的流淌。早上的时间留给大家，来过这里的小伙伴都不愿离开，悠闲的体验沙溪古镇的宁静，一杯咖啡，一场交谈，一次对寺登街老房子触摸…停留，感受时间，也是一种旅行方式。  \n\n一路向北，路过小中甸花海，见识传说中的狼毒花。抵达香格里拉，拜访云南藏传佛教的首要寺庙--**松赞林寺**，它依山而建，外形和布达拉宫很是相似，五层藏式雕镂建筑的大寺气势磅礴，闪亮的金顶老远就能看到。晚上我们抵达香格里拉古城，转经筒、酥油茶、藏餐感受神奇虔诚的藏族文化。','宁静沙溪;狼毒花;香格里拉;松赞林寺;月光古城','寻找消失的地平线与香格里拉','沙溪早餐','220KM/3h','香格里拉风情（标准间）'),(25,3,6,'香格里拉-普达措-虎跳峡','/p03/p03_210.jpg;/p03/p03_211.jpg','1,2','今天我们将前往**普达措国家公园**，香格里拉最美自然风光，原始森林完整保存下来，鸟语花香，水清天蓝。大片的草地铺满各式的碎花，**属都湖**和**碧塔海**犹如两面明亮的镜子，将周围的美景收录其中。  \n\n走在木栈道上，偶尔还会看到各种小动物在你眼皮底下溜达，高原湖泊的鱼儿也是十分调皮。午餐会在公园内解决，铺开桌布大家就着美景野餐吧！我们将有一整天的时间，待在这毫无污染的童话世界，感受这里温柔的风、迷人的光线和看不腻的风景。  \n\n旁晚离开普达措，顺流而下来到虎跳峡镇，入住临江特色藏族客栈。让我们枕着金沙江入眠。','普达措森林;碧塔海;金沙江','迷失普达措，森林参天湖水碧蓝','香格里拉早餐','120KM/1h','临江藏族客栈（标准间）'),(26,3,7,'虎跳峡-拉市海-丽江','/p03/p03_212.jpg;/p03/p03_213.jpg','1,2','美美的睡一觉，参观大气磅礴的**上虎跳峡**，金沙江水怒吼着铺天盖地袭来，撞击在山谷的峭壁，砸在横卧水中的巨石上，不由心生震撼。下一站是**拉市海**，我们要去划**皮划艇**！  \n\n旱鸭子？第一次？没关系，大胆露胳膊挽袖子，一分钟轻松上手！与水面零距离接触，湖水清澈见底，水草荡漾。方向就在手中，搅动船桨，细长的船身掠过水面，两道波浪由船尾悠扬地延伸，在开阔水域饱览一片一路湖光山色。夕阳西下，整个人坐在一片灿烂的金黄里，游泳划船竹筏都弱爆了！  \n\n晚上回到丽江，三五成群聚起来，尝一把腊排骨火锅、蒙自米线，我们的旅行于今天在这里结束啦。','虎跳峡;拉市海泛舟;丽江;大研古城','泛艇拉市海晒太阳，沉醉丽江夜色华灯初上','虎跳峡早餐','150KM/2h','丽江古城客栈（标准间）'),(27,3,8,'(解散日)丽江','/p03/p03_214.jpg','1','**大研古城** 丽江古城（即大研）作为云南旅游标志，如今却面临过度开发，商业化的尴尬。想寻找古城的美，不妨选择清晨或晚上。  \n\n夜晚古城灯火通明，人声鼎沸，热闹非凡。坐在咖啡馆楼顶，点杯鸡尾酒，俯瞰亭台楼阁怀抱中的古城。夜色里万家灯火，华灯初上夜未央，喧嚣中静静品味夜色。清晨还未苏醒，没了人群，少了吵闹，远处蓝天白云衬着玉龙雪山，近处青苔石板水流潺潺，老街白墙灰瓦，红灯绿柳木门牌坊。  \n\n**周围景点**束河古城，人相对较少，溪水清澈，小吃便宜。玉龙雪山印象丽江演出气势磅礴，遗憾的是山顶雪越来越少。泸沽湖是风景绝美，但丽江至宁蒗段修路，前往泸沽湖单程6-8小时，祈祷赶紧修好。','腊排骨火锅;蒙自米线;彩云再见！',NULL,NULL,NULL,NULL),(28,4,1,'(集合日) 兰州','/p04/p04_201.jpg','1','今天是集合日，没有活动安排，当天到达即可！  \n到兰州直接入住酒店，领队会热情招待。详细报名后见微信群~  \n\n还没到兰州，就见黄土万壑、戈壁飞沙，粗犷豪放扑面而来。\n城市夹在黄河谷地间，风景就在两岸。霸气耸立**中山铁桥**横跨滚滚黄河，羊皮筏子随波漂流。尝尝地道八宝茶，逛逛水车博物馆、**黄河母亲**雕像，大西北我来啦。  \n\n除了牛肉面名声在外，钻进木塔巷、大众巷、正宁街，寻找当地独有的酿皮、晶糕、杏皮水、洋芋片、牛奶鸡蛋醪糟、羊肉手抓，美味那可是应接不暇。  \n\n晚上9：00召开**见面会**，认识领队和一路同行小伙伴，不要迟到哦。','给我一支兰州;中山桥;黄河母亲',NULL,NULL,NULL,'格林豪泰/派酒店（标准间）'),(29,4,2,'兰州-夏河','/p04/p04_202.jpg;/p04/p04_203.jpg','1,2','旅行开始，离开兰州，深入甘南藏区。一路上我们文艺二逼的领队会带着小伙伴们逐渐熟悉起来，像一支队伍永远在美景，美食，欢脱第一线战斗~  \n\n第一站来到**夏河**，这是一个由金光闪闪的寺庙、密密麻麻的转经筒、身披红袍的喇嘛、香甜黏牙的糌粑构成的世界。《天下无贼》的故事就发生在这里！刘若英霞光中祭拜的金碧辉煌寺庙，就是我们拜访的**拉卜楞寺**。  \n\n它在藏区有极其重要地位，号称世界最大的藏传佛教学府。走进院墙，去经堂参观，与喇嘛交流，走一圈3公里世界最长的**转经道**，爬上晒佛台，一睹拉卜楞寺全貌。信仰这种虚无却又坚固的东西深深扎根在这些高原民族的精神领域里。那一张张诚挚简单的笑脸，诉说着信仰。','夏河;拉卜楞寺;晒佛台;藏族人家 ','高原上呼吸，感受藏民灵魂深处的信仰','兰州早餐','240KM/3h','藏族卓玛宾馆（标准间）'),(30,4,3,'夏河-玛曲大草原-若尔盖','/p04/p04_204.jpg;/p04/p04_205.jpg','1,2','离开了藏传佛教圣地夏河，我们一头扎进**玛曲草原**。热尔大坝是我国仅次于呼伦贝尔的第二大草原，海拔3468米，被我们称为玛曲草原或若尔盖草原。藏语念作“若尕”，据说意思是牦牛喜欢的地方。来到这里，一望无际的绿色版图可以满足你对草原所有的幻想。遍布着沼泽，湿地和途经的水域，给这片本应有着雄伟特质的土地以女性般柔美的一瞥。  \n\n中午来到玛曲，看黄河首曲**玛曲黄河大桥**。然后继续回到玛曲大草原怀抱，前往唐克，看**黄河九曲第一湾**。伴着夕阳西下，我们一起缓缓地爬上山头，黄河水弯弯曲曲流向天边，天气好就能欣赏真正的草原日落，太阳从地平线的另一端缓缓坠下，在蜿蜒的黄河水中，倒映出一片辉煌的光彩。发个呆吧，让潜意识永恒地记录此刻的美。','玛曲大草原;黄河大桥;黄河九曲;草原日落','绿色越过山丘跨过黄河，天地间无边蔓延','夏河早餐','400KM/5h','黄河九曲宾馆（标准间）'),(31,4,4,'若尔盖-扎尕那','/p04/p04_206.jpg;/p04/p04_207.jpg','1,2','早上从若尔盖出发，再次沉浸在玛曲大草原中，迭部县城用过午餐，去往神山深处的隐秘之地**扎尕那**。不知道该用如何的语言来形容这里，朝霞中微明的寺院、云雾蔓延的垭口、晚霞侵染的山巅和炊烟缕缕的藏族村寨....这里山势雄伟，四面围成了一座天然的石城。藏族人们沐浴着藏传佛教的圣光，世代生活在这里，不是天堂，胜似天堂。  \n\n入住我们包下的藏族小院，先跟着主人学做藏族传统美食**糌靶**，然后沿着当年美国探险家洛克的足迹，沿着群山之间的**古道**行走1小时，来到险峻的**一线天**，傍晚时分来到一片草地，俯瞰着扎尕那的4个村庄，在这世外桃源晒晒太阳，喝喝老乡为我们准备的下午茶，感受那一片清净安详的美好。晚上一顿美味的晚餐后，和老乡一起围着篝火聊聊天，伴着满天的繁星…','诺尔盖草原;迭部;扎尕那石城;一线天','见过它的美，亚当和夏娃会诞生在这里','若尔盖早餐，扎尕那藏式晚餐','160KM/2h','扎尕那云中旅馆（标准间）'),(32,4,5,'扎尕那-郎木寺','/p04/p04_208.jpg;/p04/p04_209.jpg','1,2','今天我们会有大把的时光”浪费“在扎尕那。漫步在村寨与古老的寺庙之间，看着远处直入云霄的山峰，仙境般的云雾，峡谷中蜿蜒而去的溪流和道路，近处骑着骏马的安多汉子和农忙时在地里摘菜的藏家女子，藏族老阿妈一手抱着孩子一手提着水壶，在村中缓缓走过，你会觉得眼前这一切美到不真实。  \n\n然后我们到达被誉为“东方小瑞士”的**郎木寺**，这可不仅是一个寺庙，当你进入这里的第一刻你就会感受到，可以说它更代表了一种宗教与旅行相融合的生活方式，到处都可以看到背着大包小包的旅行者。  \n\n在这个仙女居住的地方绝对值得好好感受，我们会带大家去行走，拜访属于四川境内的朴实无华却众多信徒的古老寺院--**格尔底寺**，探寻这里的人和信仰。然后进入这里的**那摩大峡谷**去探访**白龙江源头**，进一步的深入了解这里的神奇传说，经历和体味藏族小村的魅力。','云中仙境;郎木寺;那摩大峡谷;格尔底寺','郎木寺脚下，信仰与旅行融洽的生活方式','扎尕那早餐','100KM/2h','回族人家宾馆（标准间）'),(33,4,6,'郎木寺-尕海湖-兰州','/p04/p04_210.jpg;/p04/p04_211.jpg','1,2','今天是我们最后一次经过玛曲草原，沿途会经过美丽的**尕海湖**。“尕”(ga)是藏语中“小”的意思，不过尕海却是甘南第一大淡水湖湿地。我们会深入到湿地中仿佛通往天边的栈道上去行走，惊叹于高原海子之美。湖畔五彩缤纷，好像云霞委地，而湖中则开满了水妖一样的绚丽花朵，把纯蓝的湖水染成淡淡的藕色，时深时浅一抹酡红。  \n\n中午到达合作市，我们将会拜访**米拉日巴佛阁**，听听米拉日巴这位历史上真实存在过的藏族英雄的故事。然后就一路杀回兰州了，整个车程时间比较长，在路上大家可以分享这么多天的旅行感悟所见所闻。','尕海湖;合作;米拉日巴佛阁','亮点：尕海湖，玛曲大草原上滑过的一滴泪珠','郎木寺早餐','400KM/5h','兰州格林豪泰/派酒店（标准间）'),(34,4,7,'(解散日)兰州','/p04/p04_212.jpg','1','今天是解散日，这一天没有活动安排，大家依依不舍挥别，根据自身情况选择合适时间的航班或火车回到温暖的家。 \n\nPS：走之前别忘了**兰州拉面**。正宗的牛肉面馆都是早上经营，一般到下午两点就都关门了，牛肉面在兰州是早餐、午餐的代言词。街边上随便一个小馆儿，味道都是独一无二的。一旦出了兰州，哪怕挂着“正宗”招牌，哪怕同样的回民经营，味道也是完全不同的，最正宗的牛肉面必须要在兰州吃！','兰州拉面;再见大西北',NULL,NULL,NULL,NULL),(35,5,1,'(集合日) 长春','/p05/p05_QT_03.jpg','1','今天是集合日，没有活动安排，当天到达即可！  \n到长春直接入住酒店，领队会热情招待。集合信息留意短信通知~  \n\n雪国之旅的第一站，飕飕寒风和“哎呀妈呀”提醒各位我们踏上东北啦！  \n溥仪曾住的**伪满洲皇宫**，及隔壁**东北沦陷陈列馆**值得一逛。爱好电影的文艺青年们不要错过**长影旧址博物馆**，嗯，就是每次在电影片头那个长影。  \n\n美食嘛，东北特色餐馆有大年初一、社会主义新农村、王记酱骨值得推荐。还有地道的朝鲜族特色仁凤阁，他们家服务员都是朝鲜来的呢。  \n\n晚上9：00召开**见面会**，认识领队和一路同行小伙伴，不要迟到哦。','长春你好！;伪满洲皇宫;长影博物馆',NULL,NULL,NULL,'长春舒适快捷宾馆（标准间）'),(36,5,2,'长春-松花湖-吉林','/p05/p05_DB_03.jpg;/p05/p05_DB_02.jpg','1,2','迎着寒气上路，小伙伴却热闹起来。离开了平日小圈子，撞见各种有趣的人，及各自千奇百怪的人生。路上不仅有景色，更有故事与从不敢想的生活。  \n\n**松花湖**冬捕依然延续着古老传统。凿开冰洞，穿入渔网，慢慢撒开。近零度水里，鱼儿都在冰层下冬眠，不知不觉入了套。收网，活蹦乱跳鱼儿刚出水，没蹦跶几下就冻住。鲜鱼下锅炖成汤，热气腾腾。大家围坐一桌，好吃的上来哪顾得上形象，七手八脚下筷子！  \n\n酒足饭饱后，来到**五家山**滑雪场。第一次？没关系，专业教练带你起飞~这里滑雪是不限时滴，摔到尽性，滑到过瘾。除了高大上滑雪，还有更简单粗暴**滑雪圈**，干坐着就出溜下去，非常好玩，痛痛快快体验雪里的乐趣。','松花湖上冬捕;鲜鱼下锅;五家山雪场;不限时的滑雪;滑雪圈','千年冬捕，冰层下捞起鲜鱼下锅熬汤','长春早餐/全鱼宴午餐','150KM/2h','吉林舒适快捷宾馆（标准间）'),(37,5,3,'吉林-雾凇岛-二道白河','/p05/p05_WS_18.jpg;/p05/p05_WS_47.jpg','1,2','当地人老喜欢说：**“挂上了，挂上了！”**  \n\n指的就是树枝上挂上了白茫茫、亮晶晶的**雾凇**。暖暖的朝阳透过晶莹的雾凇散落成一道彩虹洒在雪白的地面上，北国用那古老的方式告知世界它的灵动，它那千里冰封与万里雪飘的壮美。雾凇不是每天都有，要冷暖空气交汇、无风、气温低，才能有缘分与它相见。所以有要平常心，保持合理期待~  \n\n在空旷**雾凇岛**上行走，世界格外安静。感受北国和煦阳光，广阔林海雪原。接下来是一段雪国公路旅行，跟着滚滚车轮压过冰雪一路向前。呼出白气在窗户上结成冰花，松花江畔的鸭子，茂密的森林，冻结的湖面在耳边呼啸而过。晚上到达**二道白河镇**入住，猜猜看，巷子里朝鲜美食哪家最好吃呢？','雾凇岛;北国暖阳;雪地公路之旅;二道白河','暖暖阳光透过晶莹雾凇，洒在洁白雪地上','吉林早餐','450KM/7h','二道白河朝鲜族人家（标准间）'),(38,5,4,'二道白河-长白山','/p05/p05_CB_01.jpg;/p05/p05_CB_07.jpg','1,2','今天上**长白山**！积雪像棉被结结实实盖住了这座满族圣山，白茫茫中岩石和雪松突兀挺立，甚是磅礴壮观。换越野车顶风迎雪冲到山顶，2700米海拔上十六座山峰矗立环绕着偌大**天池**，遥望四周云海缭绕山峰，霸气极了。  \n\n山顶下**聚龙温泉**，热气漂浮在冰雪间，蔓延开来犹如仙境。温泉煮蛋又白又嫩，好吃极了。踩着栈道上厚实积雪，路过长白冰瀑，小天池，绿源潭，到达山脚**谷底森林**。树木拔地而起，高耸参天，雪后空气清新沁人心脾。  \n\n跨过松花江源头小溪，回到奢华酒店。风雪里行走了一天，没有什么比疲惫身体泡进**温泉**更加惬意。山顶引下来40℃泉水VS-40℃呼啸而过寒风，不顾一切跳进热水里，冰与火之间碰撞的享受，简直痛快极了。','长白山;天池;温泉煮鸡蛋;谷底森林;冰天雪地温泉','冰与火之间的温泉，享受“葛优躺”','二道白河早餐','70KM/2h','长白山奢华温泉酒店（标准间）'),(39,5,5,'长白山-雪乡','/p05/p05_XX_27.jpg;/p05/p05_XX_01.jpg','1,2','奢华酒店必须舒服睡个懒觉再出发，今天去**雪乡**，就是爸爸去哪儿那个！  \n\n如果长白山是自然奇观，那雪乡便是一种生存哲学。一年有7个月被雪覆盖，一切都得围着雪展开。狗拉爬犁，马拉雪橇，热乎乎火炕。当地人用自己智慧与大自然反复斗争，妥协，讨价还价，把寒冷冰雪变成了生活中必不可少一部分。  \n\n家家户户房顶堆满了积雪，犹如奶油般化开，看的心都融化了。雪花片片飘落，大街上却是熙熙攘攘，冰糖葫芦、冻柿子、冰棍一箱箱排开，比过年赶集还热闹。夜空黑洞般深邃，却不觉寒冷，高高挂起的红灯笼和窗里万家灯火温暖了整个村庄。  \n\n端起小酒，咱们一起脱鞋上炕，盘腿唠嗑，肆意享受冰封万里中温暖与惬意。','被雪埋没山村;雪乡生活;万家灯火;东北大火炕','搂着皑皑白雪覆盖的山村，安然入眠','长白山早餐/东北大菜晚餐','450KM/7h','雪乡东北火炕（多人间）'),(40,5,6,'雪乡-林海雪原-雪谷-向阳','/p05/p05_XX_12.jpg;/p05/p05_XX_16.jpg','1,2','最刺激，最值得期待的一天。穿越林海雪原，在雪地里打滚！  \n\n速干、冰爪、雪套，对讲机，全副武装上路。深一脚浅一脚，踩进半米厚的积雪，听着自己心跳大口呼吸。白茫茫世界里，空气都变的格外安静。高兴起来，拉着小伙伴冲进雪堆打滚，放开手脚与雪亲热，热气欢笑沸腾了山林。快到山顶，路格外陡峭，手拉手奋力向上，照片霸气的像珠峰登顶。  \n\n迎风俯瞰大地，万亩林海不尽雪原，天空蔚蓝如洗。山顶小屋里喝口热水，简单路餐，准备下山。雪谷的道路早已结冰，一屁股坐下，往后一躺就能滑下去。**羊草山**突然变成了巨大的滑梯游乐场，走两步，滑两步，笑得合不拢嘴，你追我赶，像小时候那样玩得不亦乐乎。  \n\n到达山脚原生态**雪谷**，集合前往向阳镇，痛快洗个热水澡。大家一起包饺子，把水饺捏成各种千奇百怪的形状，开心的看着它们下锅再出锅。最难忘的一天，就这样在欢声笑语中度过。','林海雪原;雪地里打滚;翻过羊草山;天然滑梯;动手包饺子','变身杨子荣，穿林海跨雪原，气冲霄汉！','雪乡早餐/向阳饺子晚餐','15KM/6h','向阳东北人家（标准间）'),(41,5,7,'向阳-伏尔加庄园-哈尔滨','/p05/p05_HS_11.jpg;/p05/p05_HS_20.jpg','1,2','经历了一整天的穿越，当然要好好休整，今天睡懒觉！沐浴着冬日里的暖阳，打个哈欠，伸个懒腰，前往**伏尔加庄园**，体验战斗民族日常生活。  \n\n这座庄园老么大了，独一无二俄式建筑群。巴甫洛夫城堡、普希金沙龙、金鱼童话园，整个俄罗斯的精华都被搬过来了。尼古拉大教堂里，阳光穿透了刻满花纹大玻璃，映出了斑驳的光，加上俄罗斯人表演，整个庄园流淌着毛子的血液。  \n\n最幸福的是，没！有！游！客！走过冰封的阿什河，一片空旷里独自穿过大理石柱长廊，驻足美人鱼雕像旁，眼泪快下来了。傍晚回到哈尔滨，难忘的旅行即将结束，不得不与各位说再见，天呐，这就要走了？还没跟大家一起玩够呢...','伏尔加庄园;俄罗斯风情;中央大街;圣索菲亚教堂','整个俄罗斯的精华，都搬进了这座庄园','向阳早餐','250KM/ 4h','哈尔滨舒适快捷酒店（标准间）'),(42,5,8,'(解散日)哈尔滨','/p05/p05_HS_37.jpg','1','今天是解散日，没有活动安排，大家根据各自情况返回温暖的家。  \n说到哈尔滨，最大问题是...好吃好玩太多，一天哪儿特么够用...  \n  \n中央大街那么多好吃的肯定去；旁边圣索菲亚教堂顺便逛了吧；松花江要去看一眼；对岸是冰雪大世界，老出名了不能不去；老道外原生态老城区，哈尔滨发源地，不去太可惜了吧...  \n  \n华梅、波特曼、欧罗巴、塔道斯、露西亚...这么多西餐厅，不吃也得逛一逛啊；秋林红肠、大咧巴、格瓦斯可以带走；马迭尔冰棍、南极朗姆酒冰棍都得尝尝；锅包肉、老昌春饼、吴记酱骨、薛府一品、得莫利炖鱼；还有哈尔滨大烧烤...  \n  \n妈妈，我不想回家了！','好吃的太多了;哈尔滨再见！',NULL,NULL,NULL,NULL),(43,6,1,'(集合日) 吉林市','/p05/p05_QT_06.jpg','1','今天是集合日，没有活动安排，当天到达即可！\n到吉林直接入住酒店，领队会热情招待。集合信息留意短信通知~\n\n这座与省同名城市，像个没落贵族讲述着辉煌的曾经。  \n松花江畔的**天主教堂**由法国人1917年建造，距今整整一百年。这里还是乌拉满族发源地，**满族博物馆**静静回忆着历史。**龙潭山公园**里保留着高句丽遗址，登上山峰还能俯瞰整个吉林。  \n\n关于好吃的，河南街**福源馆**1628年开张卖糕点，明朝至今老字号。遍地的煎粉、鸡汤豆串，老好吃了出吉林就没了。晖春街热闹夜市，是地道东北生活。  \n晚上9：00召开**见面会**，认识领队和一路同行小伙伴。\n\n****如何节省一天时间？****  \n假期不够的童鞋，Day1可以不请假，买当晚发往吉林（长春）卧铺睡上一觉，Day2一大早跟大部队回合！(出发前务必跟领队说明~)','东北你好！;满族博物馆;吉林天主教堂;龙潭山公园',NULL,NULL,NULL,NULL),(44,6,2,'吉林-雾凇岛-雪乡','/p05/p05_WS_18.jpg;/p05/p05_XX_08.jpg','1,2','当地人老喜欢说：“挂上了，挂上了！”\n\n指的就是树枝上挂上了白茫茫、亮晶晶的**雾凇**。暖暖的朝阳透过晶莹的雾凇散落成一道彩虹洒在雪白的地面上，北国用那古老的方式告知世界它的灵动，它那千里冰封与万里雪飘的壮美。在空旷**雾凇岛**上行走，世界格外安静。感受北国和煦阳光，广阔林海雪原。\n\n一段雪国公路旅行，跟着滚滚车轮压过冰雪一路向前，来到一年7个月被雪覆盖的**雪乡**。狗拉爬犁，马拉雪橇，热乎乎火炕，在这里冰雪变成了生活中必不可少一部分。家家户户房顶堆满了积雪，犹如奶油般化开，看的心都融化了。\n\n雪花片片飘落，大街上却是熙熙攘攘，冰糖葫芦、冻柿子、冰棍一箱箱排开，比过年赶集还热闹。夜空黑洞般深邃，却不觉寒冷，高高挂起的红灯笼和窗里万家灯火温暖了整个村庄。端起小酒，咱们一起脱鞋上炕，盘腿唠嗑，肆意享受冰封万里中温暖与惬意。','雾凇岛暖阳;北境公路之旅;雪乡冰雪生活;万家灯火;东北大火炕','暖暖阳光透过晶莹雾凇；搂着白雪覆盖村庄入眠','吉林早餐/东北大菜晚餐','240KM/5h','雪乡东北火炕（多人间）'),(45,6,3,'雪乡-林海雪原-雪谷-向阳','/p05/p05_XX_12.jpg;/p05/p05_XX_16.jpg','1,2','最刺激，最值得期待的一天。穿越林海雪原，在雪地里打滚！\n\n速干、冰爪、雪套，对讲机，全副武装上路。深一脚浅一脚，踩进半米厚的积雪，听着自己心跳大口呼吸。白茫茫世界里，空气都变的格外安静。高兴起来，拉着小伙伴冲进雪堆打滚，放开手脚与雪亲热，热气欢笑沸腾了山林。快到山顶，路格外陡峭，手拉手奋力向上，照片霸气的像珠峰登顶。\n\n迎风俯瞰大地，万亩林海不尽雪原，天空蔚蓝如洗。山顶小屋里喝口热水，简单路餐，准备下山。雪谷的道路早已结冰，一屁股坐下，往后一躺就能滑下去。**羊草山**突然变成了巨大的滑梯游乐场，走两步，滑两步，笑得合不拢嘴，你追我赶，像小时候那样玩得不亦乐乎。\n\n到达山脚原生态**雪谷**，集合前往向阳镇，痛快洗个热水澡。大家一起包饺子，把水饺捏成各种千奇百怪的形状，开心的看着它们下锅再出锅。最难忘的一天，就这样在欢声笑语中度过。','林海雪原;雪地里打滚;翻过羊草山;天然滑梯;原生态雪乡;动手包饺子','变身杨子荣，穿林海跨雪原，气冲霄汉！','雪乡早餐/向阳饺子晚餐','85KM/1.5h','向阳东北人家（标准间）'),(46,6,4,'(解散日)向阳-哈尔滨','/p05/p05_HS_06.jpg;/p05/p05_MS_05.jpg;','1,2','经历了一整天的穿越，要好好睡个懒觉！沐浴着冬日里暖阳，前往冰城**哈尔滨**，在北境里寻找最浪漫的冬天。\n\n首先要说**中央大街**，因为有太多好吃的了！\n马迭尔冰棍、里道斯红肠、大咧巴、格瓦斯只是开胃零食；华梅、露西亚、波特曼、塔道斯、欧罗巴西，这么多俄式西餐厅，挨个走一遭看哪家有眼缘；老昌春饼，薄饼卷一切老好吃了；毛毛熏肉，大饼夹熏肉吃个够；薛府一品酱骨，东方饺子王，老六杀猪菜；还有锅包肉，得莫利炖鱼东北大菜，光听着口水就流出来了...\n\n吃饱喝足，踩着一块块工整发亮花岗岩石块，走过各式巴洛克、维多利亚风格建筑，在霓虹闪烁间欣赏异国风情百年老街的浪漫。隔壁**圣索菲亚教堂**，圆顶尖塔拜占庭式建筑，鸽子成群盘旋飞翔，美得一塌糊涂，四季的城市名片。尽头便是**松花江**，点亮的铁路大桥如游龙横跨两岸，江面早已冻得结结实实，越野车和游人们肆意嬉戏。\n\n旅程到此结束了。吃不够这座城舍不得一路小伙伴，洒泪挥别，下次旅行见！  \n**(约15点到达哈尔滨站解散，建议购买18点之后车次航班，以防冰雪天气延误)**','中央大街;俄式西餐厅;圣索菲亚教堂;松花江;铁路大桥','夜幕下哈尔滨，冰雪浪漫与美味一起闪耀','向阳早餐','180KM/3h','不含'),(47,6,5,'(附加日)多一天怎么玩！','/p05/p05_HS_29.jpg','1','不急着走的话，哈尔滨好玩的可太多啦~小景点去东北虎林园，太阳岛公园、俄罗斯风情小镇，繁华熙攘的果戈里大街。大景点更多啦：\n\n**老道外**是城市发源地，原生态古老房屋街道散发着浓厚市井生活气息，沿着大大小小门洞进去，便走入老哈尔滨人百年悲欢兴衰史。**伏尔加庄园**老么大了，河流城堡木屋组成俄式建筑群，少有游客格外静谧。皑皑白雪下，整个俄罗斯的精华都被搬过来了。\n\n太阳岛上**雪雕艺术博览会**，白雪堆砌成的巨大雕塑，用天马行空的想象演绎着雪国世界与梦想。**冰雪大世界**是哈尔滨名片，门票虽小贵，但能看到世界上最高、最美、最雄伟、最具魅力的冰雪奇观可谓不虚此行。\n\n好吃的推荐当地人最爱，服务pk海底捞的**金刚山**大烧烤，特好吃特实在，吃的那叫痛快。哈尔滨，来了就让你不想走...','老道外;冰雪大世界;伏尔加庄园;太阳岛','夜幕下哈尔滨，流光溢彩冰雪与美味一起闪耀',NULL,NULL,NULL);
/*!40000 ALTER TABLE `travel_route_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_route_more`
--

DROP TABLE IF EXISTS `travel_route_more`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel_route_more` (
  `routeid` bigint(20) NOT NULL AUTO_INCREMENT,
  `slider_imgs` varchar(1000) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `local` varchar(2000) NOT NULL,
  `prepare` varchar(2000) NOT NULL,
  `traffic` varchar(2000) NOT NULL,
  `expense_include` varchar(2000) NOT NULL,
  `expense_exclude` varchar(2000) NOT NULL,
  `refund` varchar(2000) NOT NULL,
  PRIMARY KEY (`routeid`),
  UNIQUE KEY `routeid_UNIQUE` (`routeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品更多信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route_more`
--

LOCK TABLES `travel_route_more` WRITE;
/*!40000 ALTER TABLE `travel_route_more` DISABLE KEYS */;
INSERT INTO `travel_route_more` VALUES (1,'/p01/p01_101.jpg;/p01/p01_102.jpg;/p01/p01_103.jpg;/p01/p01_104.jpg;/p01/p01_105.jpg;/p01/p01_106.jpg;/p01/p01_107.jpg;/p01/p01_108.jpg;/p01/p01_109.jpg','黄河与美食，构成兰州这座典型西北城市;塔尔寺让灵魂沉醉于信仰的思考;千里牧场终年雪山，这里是天境祁连;夕阳的余晖掠过大地为丹霞又加了一笔色彩;醉卧沙场君莫笑，古来征战几人还;屹立沙丘之上遥望怀抱中那湾泉水;城市里没有的璀璨银河与漫天繁星;青海湖--陆心之海;再见了小伙伴，再见了大西北','### 气候  \n西北跨度广温差大，气候干燥阳光强烈。早晚最低15度，正午最高35度。要多喝水，注意防晒。\n\n### 住宿   \n同价位旅行中住宿是最好的，没有之一。所有住宿都经过严格实地考察，并且90%以上好评。深入当地，住进祁连山脚回民家，住进茶卡村长家，融入真正生活。\n\n### 海拔   \n3000米左右，夏季植被丰富，只有少部分人会轻度反应。我们会留出足够时间让你慢慢适应。\n\n### 民族  \n回族、藏族聚居，尊重他们宗教信仰和生活习惯。近距离拍摄人像一定要征得对方同意。  \n\n### 饮食  \n西北肉食面食居多，小吃瓜果丰盛，但蔬菜较少。初上高原注意饮食，防止腹泻，及时补充维生素。','### 衣服  \n上衣：冲锋衣/薄外套+长袖+短袖  \n下衣：长裤/裙子+短裤+运动鞋  \n换洗：内衣若干+袜子若干  \n防晒：头巾+帽子+墨镜+防晒霜   \n注意：光照强温差大，昼夜温差约15度。  \n\n### 装备  \n必带：身份证+手机+钥匙+钱包   \n相机：带上三脚架可劲拍星空  \n行李：背包、行李箱皆可  \n\n### 药品  \n自备应急药品，如维生素、感冒药、止泻药、晕车药。  \n\n### 美女专属  \n带鲜艳的衣服、丝巾，茶卡盐湖青海湖都是绝佳拍照地，有了色彩点缀最能拍的美美哒~  ','### 美女专属  \n带鲜艳的衣服、丝巾，茶卡盐湖青海湖都是绝佳拍照地，有了色彩点缀最能拍的美美哒~  \n\n### 火车  \n北京-兰州每天7趟车次，单程硬卧400左右，软卧600左右，抢不到火车票可以联系客服帮忙。\n\n### 飞机  \n北京-兰州单程机票1000左右，中川机场-兰州站直达城际，12306购票末班22：30。机场大巴下车点距入住酒店1.5km。\n\n### 集合日/解散日酒店  \n格林豪泰火车站东路店。火车站向东200米路北。','### 交通   \n全程正规空调包车费用，油费高速费司机食宿补贴。 \n \n### 住宿   \n全程住宿费用 （8晚），详情参见行程安排。 \n \n### 门票   \n旅行中的所有门票。（卓尔山/含景区车+七彩丹霞/含景区车+嘉峪关关城+悬臂长城+莫高窟+鸣沙山+茶卡盐湖） \n \n### 用餐   \n全程早餐，起床就有吃的；正餐参见行程 \n \n### 领队   \n领队交通，工资，食宿补贴。他们文艺逗逼靠谱专业，帮你解决一切旅途操心事儿。 \n \n### 保险 \n10万旅游意外险 \n \n### 物资 \n对讲机，公共物资，急救包，氧气瓶。','### 往返大交通   \n各地往返兰州的大交通费用。 \n \n### 部分正餐   \n未包含的正餐，每天约40-80元左右，含餐参见行程安排。','### 退订活动  \n1.出发前 **21天及以上** 退订活动，活动费用的 **5%** 将无法退还。     \n2.出发前 **20天至7天** 退订活动，活动费用的 **20%** 将无法退还。     \n3.出发前 **6天至1天** 退订活动，活动费用的 **50%** 将无法退还。     \n4.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。     \n5.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。     \n\n ### 退款流程   \n 订单中点击退款。系统会时间自动计算出退款费用，确认无误后，3-15个工作日原路返还至您当时支付的账户。 \n\n ### 放心报名   \n 如因海逍遥原因导致行程取消，将与您协商调整日期或更换路线。 \n 如无法调整则全额退款，并承担相应机票、火车票退改签损失。同时补偿不低于300元优惠码，可在任意路线抵减团费。 \n （仅对支付成功用户哦，请先报名再买票） \n\n ### 无购物承诺 \n 购物和回扣神马的，海逍遥深恶痛绝，如发生任何性质推销、购物、回扣，团费全额退还外，领队携全体员裸奔致歉。'),(2,'/p02/p02_101.jpg;/p02/p02_102.jpg;/p02/p02_103.jpg;/p02/p02_104.jpg;/p02/p02_105.jpg;/p02/p02_106.jpg;/p02/p02_107.jpg;/p02/p02_108.jpg;/p02/p02_109.jpg','东方莫斯科,欧亚大陆桥上的明珠--哈尔滨;高贵的丹顶鹤湿地旁翩翩起舞;莫日格勒河曲水蜿蜒;驯鹿像乖宝宝一样跟着你满地跑;边境小镇室韦遥望对岸战斗民族;驰骋在呼伦贝尔大草原腹地;超美阿尔山森林公园，景区之内200公里路程！;科尔沁，草原一个都不能少;再见了小伙伴，再见了战斗民族，再见了大草原！','### 气候  \n呼伦贝尔草原跨度广温差大，气候干燥阳光强烈。早晚最低10度，正午最高30度。要多喝水，注意防晒。  \n  \n### 住宿  \n住一会蒙古，进一次木刻楞，睡在阿尔山森林公园里。旅程的住宿极具特色，绝对是一无二体验。  \n  \n### 民族  \n内蒙古地区以蒙古族和东北人为主，性情豪迈，边境地区还能遇见很多碧眼金发俄罗斯战斗民族哦！  \n  \n### 饮食  \n以东北菜和蒙餐为主，外加大量俄罗斯特色食物。非常适合大口喝酒大口吃肉小伙伴。肉食较多，注意补充青菜维生素。含餐见行程','### 衣服  \n上衣：冲锋衣/薄外套+长袖+短袖  \n下衣：长裤/裙子+短裤+运动鞋  \n换洗：内衣若干+袜子若干  \n防晒：头巾+帽子+墨镜+防晒霜   \n  \n注意：光照强温差大，昼夜温差约15度。  \n  \n### 装备  \n必带：身份证+手机+钥匙+钱包   \n望远镜：眺望河对岸俄罗斯小镇！  \n行李：背包、行李箱皆可  \n  \n### 药品  \n自备应急药品，如维生素、感冒药、止泻药、晕车药。  \n  \n### 美女专属  \n带鲜艳的衣服、丝巾，金色草原，俄罗斯风情小镇是绝佳拍照地，有了色彩点缀最能拍的美美哒~','### 哈尔滨集合  \n各地前往哈尔滨交通方便，北京至哈尔滨车次频繁，动车310，硬卧280，软卧430。抢不到火车票可以联系客服帮忙 ~  \n  \n### 齐齐哈尔解散  \n齐齐哈尔是东北西部中心城市，交通方便，动车D26、D30直达北京，二等座410。每天40余车次至哈尔滨，动车100，硬座50。\n','### 交通  \n全程正规空调包车费用，油费高速费司机食宿补贴。  \n  \n### 住宿  \n全程住宿费用 （8晚），详情参见行程安排。  \n  \n### 门票  \n旅行中的所有门票 （扎龙湿地/含环保车+金帐汗/祭祀敖包/下马酒+敖鲁古雅驯鹿+额尔古纳湿地/环保车+满洲里国门+阿尔山森林公园/环保车）  \n  \n### 用餐  \n全程早餐，起床就有吃的；正餐参见行程  \n  \n### 领队  \n领队交通，工资，食宿补贴。他们文艺逗逼靠谱专业，帮你解决一切旅途操心事儿。  \n  \n### 保险  \n10万旅游意外险   \n  \n### 物资  \n对讲机，公共物资，急救包，望远镜。','### 往返大交通  \n各地到达哈尔滨，离开齐齐哈尔大交通费用。  \n  \n### 部分正餐  \n未包含的正餐，每天约40-80元左右，含餐参见行程安排。','### 退订活动  \n 1.出发前 **21天及以上** 退订活动，活动费用的 **5%** 将无法退还。  \n 2.出发前 **20天至7天** 退订活动，活动费用的 **20%** 将无法退还。  \n 3.出发前 **6天至1天** 退订活动，活动费用的 **50%** 将无法退还。  \n 4.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。  \n  \n ### 退款流程  \n订单中点击退款。系统会时间自动计算出退款费用，确认无误后，3-15个工作日原路返还至您当时支付的账户。  \n  \n### 放心报名  \n如海逍遥导致未能成行，将与您协商更换日期或路线。  \n无法调整则全额退款，并承担相应机票、火车票退改签损失。  \n补偿不低于300元优惠码，可抵扣任意路线费用。  \n（仅对支付成功用户哦，请先报名再买票）  \n  \n### 无购物承诺  \n购物和回扣神马的，海逍遥深恶痛绝，如发生任何性质推销、购物、回扣，团费全额退还外，领队携全体员裸奔致歉。'),(3,'/p03/p03_101.jpg;/p03/p03_102.jpg;/p03/p03_103.jpg;/p03/p03_104.jpg;/p03/p03_105.jpg;/p03/p03_106.jpg;/p03/p03_107.jpg;/p03/p03_108.jpg','翠堤春晓鲜花盛放，春城昆明--梦开始的地方;大理古城，南诏国尘封岁月里的辉煌;无敌海景房，躺在床上看洱海月;沙溪古镇，茶马古道百年前的风光;松赞林寺，香格里拉古城里的布达拉宫;普达措森林公园，香格里拉最美自然风光;穿过虎跳峡，泛舟拉市海上;丽江风花雪月，艳遇大研古城时光','### 气候  \n南部昆明、大理四季如春，气温在15-25度上下。北部云贵高原丽江、香格里拉略冷，早晚温差较大约15度。  \n紫外线强，很容易晒伤，特别注意防晒。  \n\n### 住宿  \n整个旅程中住宿都颇具当地特色，大理彝族风情楼，喜洲海景房，沙溪马帮客栈，江边藏族客栈，别样风情让你不虚此行。  \n\n### 民族  \n云南是少数民族聚居地，会接触白族、纳西族、藏族。他们朴实友好，注意尊重他们生活习惯。  \n\n### 饮食  \n云南有各种各样美食，鲜花饼、汽锅鸡、过桥米线、腊排骨能让你大快朵颐。含餐见行程。','###衣服  \n上衣：冲锋衣/薄外套+长袖+短袖  \n下衣：长裤/裙子+短裤+运动鞋  \n换洗：内衣若干+袜子若干  \n防晒：头巾+帽子+墨镜+防晒霜   \n\n注意：云贵高原光照强温差大，昼夜温差约15度。  \n\n### 装备  \n必带：身份证+手机+钥匙+钱包   \n行李：背包、行李箱皆可  \n\n### 药品  \n自备应急药品，如维生素、感冒药、止泻药、晕车药。  \n\n### 美女专属  \n可以穿裙子，带好看衣服，把自己拍的美美哒~','### 昆明集合  \n北京-昆明航班价格相对稳定，700-1000之间。 \n\n### 丽江解散  \n丽江前往各大城市的飞机多由昆明中转。丽江前往昆明多次航班火车。硬卧150，软卧230，机票350。','### 交通  \n全程正规空调包车费用，油费高速费司机食宿补贴。  \n\n### 住宿  \n旅行全程住宿费用 （7晚），当地特色客栈、海景房。详情请参见行程安排。  \n\n### 门票  \n旅行中的所有门票（苍山/感通索道+松赞林寺+普达措/含环保车+上虎跳峡+拉市海）  \n\n### 当地体验  \n喜洲小马车、茈碧湖往返船费、扎染手工、拉市海皮划艇  \n\n### 用餐  \n全程早餐，起床就有吃的；正餐参见行程。  \n\n### 领队  \n领队交通，工资，食宿补贴。他们文艺逗逼靠谱专业，帮你解决一切旅途操心事儿。  \n\n### 物资+保险  \n10万旅游意外险，对讲机，公共物资，急救包。','### 往返大交通  \n到达昆明集合，以及丽江解散的大交通费用。  \n\n### 部分正餐  \n未包含的正餐，每天约40-80元左右，含餐参见行程安排。','### 退订活动  \n 1.出发前 **21天及以上** 退订活动，活动费用的 **5%** 将无法退还。  \n 2.出发前 **20天至7天** 退订活动，活动费用的 **20%** 将无法退还。  \n 3.出发前 **6天至1天** 退订活动，活动费用的 **50%** 将无法退还。  \n 4.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。  \n\n ### 退款流程  \n订单中点击退款。系统会时间自动计算出退款费用，确认无误后，3-15个工作日原路返还至您当时支付的账户。  \n\n### 放心报名  \n如海逍遥导致未能成行，将与您协商更换日期或路线。  \n无法调整则全额退款，并承担相应机票、火车票退改签损失。  \n补偿不低于300元优惠码，可抵扣任意路线费用。  \n（仅对支付成功用户哦，请先报名再买票）  \n\n### 无购物承诺  \n购物和回扣神马的，海逍遥深恶痛绝，如发生任何性质推销、购物、回扣，团费全额退还外，领队携全体员裸奔致歉。'),(4,'/p04/p04_101.jpg;/p04/p04_102.jpg;/p04/p04_103.jpg;/p04/p04_104.jpg;/p04/p04_105.jpg;/p04/p04_106.jpg;/p04/p04_107.jpg','黄河与美食，构成兰州这座典型西北城市;金光闪闪寺庙、密密麻麻转经筒、身披红袍喇嘛;数不清的河流在视野里肆意流淌蜿蜒;“我平生未见如此绮丽的景色。如果《创世纪》的作者曾看见迭部的美景，将会把亚当和夏娃的诞生地放在这里。”   --【美国】探险家洛克;东方小瑞士郎木寺-宗教与旅行相融合的生活方式;尕海湖，低调的美丽草原上的一滴泪珠;再见了小伙伴，再见了香巴拉王国','### 气候  \n甘南气温较低，约10-25度，早晚温差大，气候变化快。适当多带衣服，并注意防晒。  \n\n### 住宿  \n同价位旅行中住宿是最好的，没有之一。所有住宿都经过严格实地考察，并且90%以上好评。深入当地，住进夏河藏式宾馆，住进回族院子，融入真正生活。  \n\n### 海拔  \n3000米左右，植被丰富，有少部分人会轻度反应。我们会留出足够时间让你慢慢适应。  \n\n### 民族  \n回族、藏族聚居，尊重他们宗教信仰和生活习惯。近距离拍摄人像一定要征得对方同意。  \n\n### 饮食  \n藏餐回餐为主，肉食较多，蔬菜较少。初上高原注意饮食，防止腹泻，及时补充维生素。','### 衣服  \n 上衣：冲锋衣/薄外套+长袖+短袖  \n 下衣：长裤/裙子+短裤+运动鞋  \n 换洗：内衣若干+袜子若干  \n 防晒：头巾+帽子+墨镜+防晒霜   \n\n 注意：气候变化快，紫外线强。昼夜温差约15度。  \n\n ### 装备  \n 必带：身份证+手机+钥匙+钱包   \n 行李：背包、行李箱皆可  \n 帽子：防风防雨防晒防感冒，出门神器  \n\n ### 药品  \n 自备应急药品，如维生素、感冒药、止泻药、晕车药。','### 火车  \n北京-兰州每天7趟车次，单程硬卧400左右，软卧600左右，抢不到火车票可以联系客服帮忙。  \n\n### 飞机  \n北京-兰州单程机票1000左右，中川机场-兰州站直达城际，12306购票末班22：30。机场大巴下车点距入住酒店1.5km。  \n\n### 集合日/解散日酒店  \n格林豪泰火车站东路店。火车站向东200米路北。','### 交通  \n全程正规空调包车费用，油费高速费司机食宿补贴。  \n\n### 住宿  \n全程住宿费用 （6晚），详情参见行程安排。  \n\n### 门票  \n旅行中的所有门票。（拉卜楞寺+黄河九曲+扎尕那+郎木寺+纳摩大峡+尕海湖+米拉日巴佛阁）  \n\n### 用餐  \n全程早餐，起床就有吃的；正餐参见行程  \n\n### 领队  \n领队交通，工资，食宿补贴。他们文艺逗逼靠谱专业，帮你解决一切旅途操心事儿。  \n\n### 保险  \n10万旅游意外险  \n\n### 物资  \n对讲机，公共物资，急救包，氧气瓶。','### 往返大交通  \n各地往返兰州的大交通费用。  \n\n### 部分正餐  \n未包含的正餐，每天约40-80元左右，含餐参见行程安排。','### 退订活动  \n 1.出发前 **21天及以上** 退订活动，活动费用的 **5%** 将无法退还。  \n 2.出发前 **20天至7天** 退订活动，活动费用的 **20%** 将无法退还。  \n 3.出发前 **6天至1天** 退订活动，活动费用的 **50%** 将无法退还。  \n 4.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。  \n\n ### 退款流程  \n订单中点击退款。系统会时间自动计算出退款费用，确认无误后，3-15个工作日原路返还至您当时支付的账户。  \n\n### 放心报名  \n如因海逍遥原因导致行程取消，将与您协商调整日期或更换路线。  \n如无法调整则全额退款，并承担相应机票、火车票退改签损失。同时补偿不低于300元优惠码，可在任意路线抵减团费。  \n（仅对支付成功用户哦，请先报名再买票）  \n\n### 无购物承诺  \n购物和回扣神马的，海逍遥深恶痛绝，如发生任何性质推销、购物、回扣，团费全额退还外，领队携全体员裸奔致歉。'),(5,'/p05/p05_XX_12.jpg;/p05/p05_DB_06.jpg;/p05/p05_WS_17.jpg;/p05/p05_CB_09.jpg;/p05/p05_XX_28.jpg;/p05/p05_XX_16.jpg;/p05/p05_HS_12.jpg;/p05/p05_HS_18.jpg','穿林海跨雪原，让身体和灵魂一起燃烧;千年冬捕，冰层下捞起鲜鱼下锅熬汤;暖暖阳光透过晶莹雾凇洒在洁白雪地上;2700米海拔上十六座山峰矗立环绕着偌大天池;搂着雪乡皑皑白雪覆盖的山村，安然入眠;拉着小伙伴一起在雪地里打滚！;整个俄罗斯的精华都搬进了伏尔加庄园;夜幕下哈尔滨，吃不完的好吃的看不完的美景','### 气候  \n东北气温通常-10℃至-20℃，最冷-40℃。多阴天下雪。长白山等寒风非常可怕，一定注意防寒保暖。内外温差大，室内大都10℃以上，室外尤其夜间极为寒冷。  \n\n### 电池  \n-10℃以下手机/相机电池使用特别快，并且会自动关机，多带电池、充电宝，同时还可以用暖宝宝当热源。  \n\n### 住宿  \n长白山奢华温泉宾馆、雪乡东北特色火炕。长春、哈尔滨等大城市以快捷酒店为主，详细见行程安排。  \n\n### 餐饮  \n提供全程早餐，松花湖午餐，雪乡晚餐，雪谷晚餐。  \n其他领队会推荐，大家自行探索当地美食。由于冰雪封路，行程可能有所延误，建议路上自备些干粮。  \n\n### 文化  \n黑不溜秋的冻秋梨，冰棍直接铺在地上卖，酸甜锅包肉，“哎呀妈呀”东北话，“小沈阳”二人转。体验冰雪里的生活方式。','### 户外三层  \n速干内衣+保暖层+冲锋衣/外套。穿越/滑雪时穿太多，运动大量出汗，湿衣服结冰很容易感冒，一定要科学穿衣。  \n\n**速干内衣**：快速排汗，冬天运动神器，非常必要，一定要专门准备。淘宝100块的速干保暖，各大户外品牌都可以。  \n**保暖层**：防止热量流失，我们平时厚毛衣/厚抓绒/羽绒都可以。按照个人对温度敏感程度自行选择。  \n **冲锋衣/外套** ：要求防风防水，建议冲锋衣/厚外套+冲锋裤/雪裤。（国内“三合一”冲锋衣裤只能当防风层，不能替代速干/保暖。）  \n\n ### 雪国必备  \n手套：必须要触屏的！不能接电话，再厚也得摘。  \n墨镜：白茫茫雪地里防止亮瞎。  \n帽子：滑雪帽/雷锋帽，头部保暖防感冒。  \n泳衣：长白山泡温泉需要。  \n其他：围巾/口罩/厚棉袜，保暖防寒。  \n\n### 穿越套装  \n鞋子：**雪地靴/登山鞋**，要保暖，高帮，防水，应对半米积雪。（ugg属于时尚产品，不适合在雪地中行走）  \n冰爪：穿越必备，我们统一提供，不用准备。  \n雪套：配合鞋子套在腿上，防止积雪倒灌。淘宝30左右。  \n热水：保温杯/保温壶，天寒地冻热水不能少。  \n暖宝宝：5-10片，应对极寒天气，贴在肚子上，垫在脚下。手机低温关机时，贴在背后能让它活过来。  \n\n### 旅行常备  \n必带：身份证+手机+钥匙+钱包   \n电器：相机/充电宝/强光手电/吹风机  \n行李：大背包/行李箱+随身包/小双肩包  \n\n### 药品  \n自备应急药品，如维生素、感冒药、止泻药、晕车药。','费用中不包含往返大交通哦，需要自行订往返机票火车票。  \n领队、酒店、微信群等出发前5天通知，请留意短信~  \n\n### 长春集合  \n北京-长春，动车270元/高铁430元，约6小时。  \n飞机机票在300-400之间，飞行约2小时。  \n\n### 哈尔滨解散  \n哈尔滨-北京，动车310元/高铁540元，约8小时。  \n飞机机票在400-600之间，飞行约2小时。  ','### 当地交通  \n东北全程正规空调包车，油费高速费停车司机工资及食宿。  \n\n### 住宿  \n全程住宿费用 （7晚），详情参见行程安排。  \n\n### 门票  \n旅行中所有门票。（雾凇岛+长白山/环保车/越野车+雪乡+雪谷穿越）  \n\n### 特色活动  \n松花湖冬捕+五家山滑雪+长白山温泉  \n\n### 用餐  \n全程早餐，起床有吃的。松花湖午餐，雪乡晚餐，雪谷晚餐   \n\n### 领队  \n交通工资食宿。他们文艺逗逼靠谱专业，解决各种操心事。   \n\n### 保险  \n旅游责任险+10万意外险   \n\n### 物资  \n对讲机，公共物资，急救包','### 往返大交通  \n不含长春集合，哈尔滨解散火车票飞机票，需自行订购。  \n\n### 部分正餐  \n未包含的正餐，每天约40-80元左右，含餐参见行程安排。','### 退订活动  \n 1.出发前 **21天及以上** 退订活动，活动费用的 **5%** 将无法退还。  \n 2.出发前 **20天至7天** 退订活动，活动费用的 **20%** 将无法退还。  \n 3.出发前 **6天至1天** 退订活动，活动费用的 **50%** 将无法退还。  \n 4.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。  \n\n  ### 退款流程  \n  登陆网站/公众号，订单中点击退款。系统会时间自动计算出退款费用，确认无误后，3-15个工作日原路返还至您当时支付的账户。  \n\n  ### 放心报名  \n  如因海逍遥原因导致行程取消，将与您协商调整日期或更换路线。  \n如无法调整则全额退款，并承担相应机票、火车票退改签损失。  \n同时补偿不低于300元优惠码，可在任意路线抵减团费。  \n（仅对支付成功用户哦，请先报名再买票）  \n\n  ### 无购物承诺  \n  购物回扣、二次收费等传统旅游套路，海逍遥深恶痛绝，如发生任何性质推销、购物回扣、二次收费，团费立即全额退还，领队携全体员工裸奔致歉。'),(6,'/p05/p05_XX_12.jpg;/p05/p05_WS_17.jpg;/p05/p05_XX_28.jpg;/p05/p05_HS_18.jpg','穿林海跨雪原，让身体和灵魂一起燃烧;暖暖阳光透过晶莹雾凇洒在洁白雪地上;搂着雪乡皑皑白雪覆盖的山村，安然入眠;夜幕下哈尔滨，吃不完的好吃的看不完的美景','### 气候  \n东北气温通常-10℃至-20℃，最冷-40℃。多阴天下雪，一定注意防寒保暖。内外温差大，室内大都10℃以上，室外尤其夜间极为寒冷。  \n\n### 电池  \n-10℃以下手机/相机电池使用特别快，并且会自动关机，多带电池、充电宝，同时还可以用暖宝宝当热源。  \n\n### 住宿  \n吉林舒适快捷酒店、雪乡东北特色火炕、向阳东北人家    \n\n### 餐饮  \n提供全程早餐，雪乡晚餐，雪谷晚餐。  \n其他领队会推荐，大家自行探索当地美食。由于冰雪封路，行程可能有所延误，建议路上自备些干粮。  ','### 户外三层  \n速干内衣+保暖层+冲锋衣/外套。穿越时穿太多，运动大量出汗，湿衣服结冰很容易感冒，一定要科学穿衣。  \n\n**速干内衣**：快速排汗，冬天运动神器，非常必要，一定要专门准备。淘宝100块的速干保暖，各大户外品牌都可以。  \n**保暖层**：防止热量流失，我们平时厚毛衣/厚抓绒/羽绒都可以。按照个人对温度敏感程度自行选择。  \n **冲锋衣/外套** ：要求防风防水，建议冲锋衣/厚外套+冲锋裤/雪裤。（国内“三合一”冲锋衣裤只能当防风层，不能替代速干/保暖。）  \n\n ### 北境必备  \n手套：必须要触屏的！不能接电话，再厚也得摘。  \n墨镜：白茫茫雪地里防止亮瞎。  \n帽子：滑雪帽/雷锋帽，头部保暖防感冒。    \n其他：围巾/口罩/厚棉袜，保暖防寒。  \n\n### 穿越套装  \n鞋子：**雪地靴/登山鞋**，要保暖，高帮，防水，应对半米积雪。（ugg属于时尚产品，不适合在雪地中行走）  \n冰爪：穿越必备，我们统一提供，不用准备。  \n雪套：配合鞋子套在腿上，防止积雪倒灌。淘宝30左右。  \n热水：保温杯/保温壶，天寒地冻热水不能少。  \n暖宝宝：5-10片，应对极寒天气，贴在肚子上，垫在脚下。手机低温关机时，贴在背后能让它活过来。  \n\n### 旅行常备  \n必带：身份证+手机+钥匙+钱包   \n电器：相机/充电宝/强光手电/吹风机  \n行李：大背包/行李箱+随身包/小双肩包  \n\n### 药品  \n自备应急药品，如维生素、感冒药、止泻药、晕车药。','费用中不包含往返大交通哦，需要自行订往返机票火车票。  \n领队、酒店、微信群等出发前5天通知，请留意短信~  \n\n### 吉林集合  \n北京-吉林，机票300-400之间，飞行约2小时。  \n(长春与吉林共用嘉龙机场，可直接买飞长春班机)   \n动车290元/高铁470元，约7小时。  \n硬卧140元/软卧430元，早7：20到达。  \n(可先到长春，换15分钟一躺城际到吉林)       \n\n### 哈尔滨解散    \n哈尔滨-北京，机票400-600之间，飞行约2小时。   \n动车310元/高铁540元，约8小时。  \n软卧470元，早6：30到达。   \n\n','### 当地交通  \n东北全程正规空调包车，油费高速费停车司机工资及食宿。   \n\n### 住宿  \n全程住宿费用 （3晚），详情参见行程安排。  \n\n### 门票  \n旅行中所有门票。（雾凇岛+雪乡+雪谷穿越）  \n\n### 用餐  \n全程早餐，，雪乡晚餐，雪谷晚餐  \n\n### 领队  \n交通工资食宿。他们文艺逗逼靠谱专业，解决各种操心事。  \n\n### 保险  \n旅游责任险+10万意外险  ','### 往返大交通  \n不含吉林集合，哈尔滨解散火车票飞机票，需自行订购。  \n\n### 部分正餐  \n未包含的正餐，每天约40-80元左右，含餐参见行程安排。','### 退订活动】  \n 1.出发前 **21天及以上** 退订活动，活动费用的 **5%** 将无法退还。  \n 2.出发前 **20天至7天** 退订活动，活动费用的 **20%** 将无法退还。  \n 3.出发前 **6天至1天** 退订活动，活动费用的 **50%** 将无法退还。  \n 4.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。  \n\n### 退款流程  \n  登陆网站/公众号，订单中点击退款。系统会时间自动计算出退款费用，确认无误后，3-15个工作日原路返还至您当时支付的账户。  \n\n### 放心报名  \n  如因海逍遥原因导致行程取消，将与您协商调整日期或更换路线。  \n如无法调整则全额退款，并承担相应机票、火车票退改签损失。  \n同时补偿不低于300元优惠，可在任意路线抵减团费。  \n（仅对支付成功用户哦，请先报名再买票）  \n\n### 无购物承诺  \n  购物回扣、二次收费等传统旅游套路，海逍遥深恶痛绝，如发生任何性质推销、购物回扣、二次收费，团费立即全额退还，领队携全体员工裸奔致歉。');
/*!40000 ALTER TABLE `travel_route_more` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_route_pcinfo`
--

DROP TABLE IF EXISTS `travel_route_pcinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel_route_pcinfo` (
  `routeid` bigint(20) NOT NULL AUTO_INCREMENT,
  `spotlights` varchar(1000) NOT NULL,
  `introduction` varchar(4000) NOT NULL,
  `center` varchar(255) NOT NULL,
  `zoom` int(11) NOT NULL DEFAULT '8',
  `cities` varchar(255) NOT NULL,
  `points` varchar(1000) NOT NULL,
  PRIMARY KEY (`routeid`),
  UNIQUE KEY `routeid_UNIQUE` (`routeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route_pcinfo`
--

LOCK TABLES `travel_route_pcinfo` WRITE;
/*!40000 ALTER TABLE `travel_route_pcinfo` DISABLE KEYS */;
INSERT INTO `travel_route_pcinfo` VALUES (1,'年轻活力小团队，跟有趣小伙伴一起玩;八个夜晚五场夜市，吃货们满载而归;走进祁连独家河畔牧场，入住雪山脚下人家;3000海拔无边无际大草原和那碧蓝的天;张掖敦煌嘉峪关，追一场两年的梦;绝佳拍摄点，茶卡星空银河日出看个够','## 神秘程序员，带你走之 ##\n嗯，我们不但会写代码，拯救地球，还能带你看看这个世界。\n\n只约年轻、有趣、聊得来小伙伴；只做有品质的旅行。  \n不操心，不玩套路，不带熊孩子；一看就跟外面那些妖艳货色不一样。  \n\n穿林海跨雪原，手拉手冲进雪堆里打滚；围一桌吃饭，顾不上温文尔雅七手八脚下筷子；争先恐后跳进80℃温差，冰与火之间温泉；大雪封门，端着小酒盘腿上炕唠嗑  \n完整的银河挂在天上静静旋转，一起目瞪口呆对着夜空傻笑。  \n\n离开禁锢自己的小圈子，走出格子间，去见见那些有趣又素未蒙面的人，去尝试那些不敢料想的生活。  \n旅行不只去看看，更是换一种人生。\n\n[请猛戳》》神秘程序员们凭啥做好5000块的旅行？](http://example.com/)\n\n## 西北有啥好玩的？##\n【江湖大侠梦】\n西北横跨甘肃、青海两省，由兰州踏上丝绸之路向西，至嘉峪关敦煌。南下青藏高原，绕青海湖返回。丝绸之路，边关故城，陆心之海，茶卡盐湖。辽阔苍茫，哪里都是江湖。回到拥挤地铁，周围一切毫不真实，像隔了一辈子，心灵沉醉在大侠梦中，久久不肯归来。\n\n【祁连天境】\n祁连山高耸入云，俯瞰广袤山川大地；回族人家就在山脚下，一整面玻璃墙对着雪山，看着积雪喝酸奶。荒芜广袤的戈壁沙漠，几百公里荒无人烟，公路直插天边尽头。\n\n【高原牧场】\n祁连草原老么大老么大了，从脚下到天边，望也望不见头；经幡在墨绿色草地上肆意飘扬，格外鲜艳刺眼。悬崖峭壁间漫山遍野的山羊，冰川融水旁壮硕黝黑牦牛；湛蓝湛蓝的天空下，身披红衣面色凝重的藏族喇嘛。\n\n【丝绸之路】\n丝绸之路上千年古迹，张掖鼓楼、卧佛寺，嘉峪关延绵的长城、敦煌莫高窟的飞天梦和鸣沙山下永不干涸的月牙泉。\n雪白盐堆积出茶卡盐湖的“天空之境”，蓝天白云和你一起倒影雪白湖中。高海拔天空异常澄澈，银河、流星抬头就能看到；青海湖遍3000米海拔上吹过海风与浪花。','99.022057,38.277849',8,'兰州;西宁;祁连;张掖;嘉峪关;瓜州;敦煌;柴达木盆地;茶卡;青海湖;','103.845958,36.059732;101.796743,36.625564;100.283098,38.190297;100.480151,38.93613;98.227704,39.811563;95.784636,40.520159;94.677492,40.102019;95.369331,37.867364;99.086115,36.796802;100.506705,36.581425;103.845958,36.059732;'),(2,'年轻活力小团队，跟有趣小伙伴一起玩;扎龙湿地丹顶鹤故乡，看仙鹤翩翩起舞;金帐汗旁曲水蜿蜒，当回地道蒙古人;室韦木刻楞、满洲里夜色战斗民族魅力满满;呼伦贝尔、科尔沁26万平方公里大草原驰骋;两整天时间留给最美的阿尔山！','## 神秘程序员，带你走之 ##\n嗯，我们不但会写代码，拯救地球，还能带你看看这个世界。\n\n只约年轻、有趣、聊得来小伙伴；只做有品质的旅行。  \n不操心，不玩套路，不带熊孩子；一看就跟外面那些妖艳货色不一样。  \n\n穿林海跨雪原，手拉手冲进雪堆里打滚；围一桌吃饭，顾不上温文尔雅七手八脚下筷子；争先恐后跳进80℃温差，冰与火之间温泉；大雪封门，端着小酒盘腿上炕唠嗑  \n完整的银河挂在天上静静旋转，一起目瞪口呆对着夜空傻笑。  \n\n离开禁锢自己的小圈子，走出格子间，去见见那些有趣又素未蒙面的人，去尝试那些不敢料想的生活。  \n旅行不只去看看，更是换一种人生。\n  \n[请猛戳》》神秘程序员们凭啥做好5000块的旅行？](http://example.com/)  \n  \n## 呼伦贝尔有啥好玩的？ ##   \n【当回蒙古人】  \n不管是课本里的“风吹草低见牛羊”，还是《还珠格格》“策马奔腾共享人世繁华”，抛开城市的束缚，忘记那些昼夜不止的喧嚣，跟一群志同道合小伙伴向大草原，向大兴安岭，向边境线出发，逃离日常生活那个世界。骑上匹高头骏马，脚踩铁镫，手擎长鞭，在无边无际的绿色波浪里，不去管方向，尽情驰骋向前。野性放荡充满生机的马背文化，只有走进它的诞生地，看天高地广，看风起云卷，看曲水夕阳，住蒙古包，吃羊肉痛饮烈酒，才能体会蒙古力量源泉\n  \n【大兴安岭的秘密】  \n大兴安岭孕育了绝美的阿尔山，白桦林，溪流瀑布湿地。由此诞生了东北渔猎部落，强悍的女真人，满族从这里走出，也留下敖鲁古雅，驯鹿这样古老原始即将消失的文明。感受时间的安宁，内心的平静。融入另外的环境，感受河流，草原，森林，日落，马背，异国他乡。跨越几千公里，在不同时空里快速切换，发现世界的宽度，释然一个人的渺小和微不足道。等内心原始的力量复苏觉醒，不眠不休，尽情狂欢释放。  \n  \n【战斗民族日常】  \n哈尔滨的索菲亚教堂、马蒂尔餐厅，室韦的大列巴 、木刻楞，满洲里的国门，金发碧眼男女无不向我们展现着战斗民族的魅力。俄罗斯族大妈会教我们做列巴，早起自己动手挤牛奶！异国风情建筑、穿梭在街道上的俄罗斯车辆，擦身而过的俄罗斯男女，让人切身体会到这座城市的魅力。\n','122.993115,48.407647',8,'哈尔滨;齐齐哈尔;扎兰屯;呼伦贝尔;额尔古纳;根河;室韦;呼伦湖;满洲里;阿尔山;','126.624255,45.784876;124.000708,47.347136;122.755657,48.004082;119.752729,49.220719;120.184599,50.248208;121.526955,50.786454;119.911963,51.345565;117.490652,49.59279;116.840369,48.692593;119.908262,47.303341;124.000708,47.347136;'),(3,'年轻活力小团队，跟有趣小伙伴一起玩;大理古城把时间调慢感受百年岁月;喜洲海景房，躺在床上看洱海日出;驾着白族马车赶早市，茶马古道上沙溪学扎染;松赞林寺、普达措、虎跳峡，寻找香格里拉;大研古城夜色，亭台楼阁怀抱里华灯初上','## 神秘程序员，带你走之 ##\n嗯，我们不但会写代码，拯救地球，还能带你看看这个世界。\n\n只约年轻、有趣、聊得来小伙伴；只做有品质的旅行。  \n不操心，不玩套路，不带熊孩子；一看就跟外面那些妖艳货色不一样。  \n\n穿林海跨雪原，手拉手冲进雪堆里打滚；围一桌吃饭，顾不上温文尔雅七手八脚下筷子；争先恐后跳进80℃温差，冰与火之间温泉；大雪封门，端着小酒盘腿上炕唠嗑  \n完整的银河挂在天上静静旋转，一起目瞪口呆对着夜空傻笑。  \n\n离开禁锢自己的小圈子，走出格子间，去见见那些有趣又素未蒙面的人，去尝试那些不敢料想的生活。  \n旅行不只去看看，更是换一种人生。\n  \n[请猛戳》》神秘程序员们凭啥做好5000块的旅行？](http://example.com/)  \n  \n## 云南有啥好玩的 ##  \n【彩云之南】  \n云南，一个超越你想象力的旅行地。这里有着毫无保留的阳光和蓝天，多彩的民族服饰迷人双眼；这里有着令人奢望的丰富纬度，从终年不化的高原雪山到四季一夏的热带河谷；这里还有让人割舍不下的悠然时光，每到一处都会有一段文艺的不得了的故事。丰富的物产，四季不断的水果，便捷的交通连接着忘情的山水，云南之于旅行者，便是天堂，就是那个此生一定要去，要去很多次的地方。  \n  \n【古城时光】  \n初听云南，或许是因为大理和丽江。无论是喜洲超棒的海景和白族人真实的生活，还是沙溪厚重的历史变迁，石宝山归途的峡谷柔情，都能向你展示南诏和大理古国的真实美丽。丽江大研古城那最真实的古老岁月，藏在巷子深处暖人心的柔软时光，滇西北，无需前缀，她依然是每个人心中的那一座城，那一座山，那一湾水。  \n  \n【消失的地平线】  \n虎跳峡，高高仰望看那奔腾的金沙江水从眼前呼啸而过。香格里拉，藏着的秘境终于被我们发现，她由每个人的心里来到眼前，松赞林寺千年传承的藏传佛教信仰，普达措森林公园与森林湿地共同呼吸的行走，一切的一切，宛若一次梦想之地回归真实的旅程。  \n  \n【洱海月升】  \n风花雪月自然不能放过，选择最大理、靠洱海最近的喜洲，这里可是有临海的无敌海景客栈，窝在床上看洱海日出，赶白族人的早集，看白族人的传统扎染，原汁原味的云南味道。沙溪，探索这个茶马古道上的美丽驿站，接着来一段山谷行走，慢慢领略云贵高原秀美的田园风光。','101.602337,26.5922',8,'昆明;大理;苍山洱海;香格里拉;虎跳峡;丽江','102.709377,25.062549;100.249129,25.610201;100.163377,25.870026;99.71126,27.836446;100.07193,27.171633;100.24232,26.882388'),(4,'年轻活力小团队，跟有趣小伙伴一起玩;深入藏区，黑夜般牦牛与沉默如迷转经筒;“天下无贼”黄教圣地拉卜楞寺;扎尕那石城，人神共居之处;黄河蜿蜒九曲十八弯，惊叹日落;3000海拔玛曲大草原和那碧蓝纯净湖水','## 神秘程序员，带你走之 ##\n嗯，我们不但会写代码，拯救地球，还能带你看看这个世界。\n\n只约年轻、有趣、聊得来小伙伴；只做有品质的旅行。  \n不操心，不玩套路，不带熊孩子；一看就跟外面那些妖艳货色不一样。  \n\n穿林海跨雪原，手拉手冲进雪堆里打滚；围一桌吃饭，顾不上温文尔雅七手八脚下筷子；争先恐后跳进80℃温差，冰与火之间温泉；大雪封门，端着小酒盘腿上炕唠嗑  \n完整的银河挂在天上静静旋转，一起目瞪口呆对着夜空傻笑。  \n\n离开禁锢自己的小圈子，走出格子间，去见见那些有趣又素未蒙面的人，去尝试那些不敢料想的生活。  \n旅行不只去看看，更是换一种人生。\n\n[请猛戳》》神秘程序员们凭啥做好5000块的旅行？](http://example.com/)  \n\n## 甘南有啥好玩的？ ##  \n【藏地初体验】  \n蓝天白云、辽阔草原、风马经幡，美丽的高原风光和浓郁的藏地风情。甘南位于祖国西北，是黄土高原向青藏高原的过渡地带，同样也是汉、藏、回等多民族的融合地带。大气又不失灵性的高原风景和多元文化的碰撞结合，充满魅力与灵性，迷人却并未被大量游人涌入。独一无二的低海拔优势，被忽略而纯粹的风俗，相对适宜的气候，一百年前《国家地理》的照片，一百年后《天下无贼》，这里是流传千年“香巴拉王国“。  \n\n【永恒信仰】  \n拉卜楞寺世界最大的藏传佛教学府，《天下无贼》中刘若英霞光中祭拜的金碧辉煌寺庙。清晨徒步俯瞰全貌，请来寺里的阿卡，带我们进入佛学教育的大学殿堂；郎木寺，国际背包客中相当有名，被誉为东方小瑞士，深入那摩大峡谷追寻白龙江的源头，感受格尔底寺的朴实无华的真实。  \n\n【伊甸园扎尕那】  \n不知道该用如何的语言来形容这里，朝霞中微明的寺院、云雾蔓延的垭口、晚霞侵染的山巅和炊烟缕缕的藏族村寨。“我平生未见如此绮丽的景色。如果《创世纪》的作者曾看见迭部的美景，将会把亚当和夏娃的诞生地放在这里。”   --【美国】探险家洛克  \n\n【七色甘南】  \n蓝色的天与湖泊、红色寺庙院墙、黄色的酥油和上师的黄帽，牦牛是黑色的，静谧的夜晚是黑亮的，天空的云是纯白的，穆斯林象征着信仰白帽子，茫茫牧场、一路相伴草地与森林是无尽绿色。藏文化在这个汉、藏、回激烈碰撞但又亲密交融的区域里，具有极旺盛生命力和多样性，潜藏着别处没有的秘密，漫步其中，细细体味冲突与和谐所在。','103.096663,34.528565',8,'兰州;夏河;玛曲;黄河九曲;诺尔盖;扎尕那;郎木寺;尕海;合作;','103.845958,36.059732;102.515891,35.198403;102.083949,34.003344;102.471946,33.484491;102.945892,33.601482;103.198836,34.241982;102.652828,34.121445;102.370114,34.267159;102.913481,35.007367;103.845958,36.059732;'),(5,'古老冬捕凿开冰层捞起鲜鱼下锅熬汤;暖暖阳光透过晶莹的雾凇洒在雪地上;在冰与火之间温泉里享受“葛优躺”;搂着白雪覆盖的山村安然入眠;穿林海，跨雪原，当回杨子荣气冲霄汉;整个俄罗斯的精华都搬进了伏尔加庄园','## 神秘程序员，带你走之 ##\n嗯，我们不但会写代码，拯救地球，还能带你看看这个世界。\n\n只约年轻、有趣、聊得来小伙伴；只做有品质的旅行。  \n不操心，不玩套路，不带熊孩子；一看就跟外面那些妖艳货色不一样。  \n\n穿林海跨雪原，手拉手冲进雪堆里打滚；围一桌吃饭，顾不上温文尔雅七手八脚下筷子；争先恐后跳进80℃温差，冰与火之间温泉；大雪封门，端着小酒盘腿上炕唠嗑  \n完整的银河挂在天上静静旋转，一起目瞪口呆对着夜空傻笑。  \n\n离开禁锢自己的小圈子，走出格子间，去见见那些有趣又素未蒙面的人，去尝试那些不敢料想的生活。  \n旅行不只去看看，更是换一种人生。\n\n[请猛戳》》程序员们凭啥做好5000块的旅行？](http://example.com/)  \n\n## 东北那嘎达老好玩了 ##  \n【穿林海，跨雪原】  \n齐腰深的积雪里穿行6个小时，身体和灵魂一起燃烧，让日益麻木的自己，重新感受活着。一言不合拉着小伙伴冲进雪堆，撒泼打滚全身埋进去，欢乐和笑声在-30℃空气里沸腾。把下山的路当滑梯，从羊草山顶一路滑下来。走两步，滑两步，笑得合不拢嘴，你追我赶，像小时候，玩起来就忘了一切。  \n\n【冰与火之歌】  \n2700米长白山顶，天池云海缭绕，-40℃寒风彻骨；山脚下40℃温泉，热气翻滚蒸腾。“葛优瘫”在正负80℃温差里，尽情享受冰与火之间碰撞的刺激。  \n\n【冬捕&滑雪，玩个痛快！】  \n松花湖冬捕延续千年传统，凿开冰洞，洒下渔网，活蹦乱跳鱼儿出水，没蹦跶几下就冻得结结实实。鲜鱼下锅炖出汤热气腾腾，一起七手八脚下筷子。五家山雪场不限时滴，简单粗暴滑雪圈，高大上的滑雪，滑到过瘾，摔到开心，痛痛快快体验雪里的乐趣。  \n\n【和战斗民族来个熊抱】  \n偌大的伏尔加庄园，没有游客。安静的白雪掩映着巴甫洛夫城堡、普希金沙龙、尼古拉大教堂，整个俄罗斯的精华都被搬过来了。哈尔滨夜色下的圣索菲亚教堂，灯光霓虹中的中央大街，马迭尔冰棍，格瓦斯，红菜汤，跟战斗民族来个基情熊抱！','127.256949,44.572221',7,'长春;雾凇岛;松花湖;长白山;雪乡雪谷;向阳;伏尔加;哈尔滨','125.324421,43.832602;126.435627,44.145183;126.930629,43.515883;128.094283,42.112236;128.214501,44.338088;127.475052,44.615921;126.948865,45.672995;126.624182,45.784807'),(6,'当回杨子荣，穿林海跨雪原，气冲霄汉;拉着小伙伴在半米深雪堆里肆意打滚;暖暖阳光透过晶莹的雾凇洒在北国大地上;搂着白雪覆盖的山村安然入眠;体验一年七个月被冰雪覆盖的生活;夜幕下哈尔滨，流光溢彩冰雪与美味一起闪耀','## 神秘程序员，带你走之 ##\n嗯，我们不但会写代码，拯救地球，还能带你看看这个世界。\n\n只约年轻、有趣、聊得来小伙伴；只做有品质的旅行。  \n不操心，不玩套路，不带熊孩子；一看就跟外面那些妖艳货色不一样。  \n\n穿林海跨雪原，手拉手冲进雪堆里打滚；围一桌吃饭，顾不上温文尔雅七手八脚下筷子；争先恐后跳进80℃温差，冰与火之间温泉；大雪封门，端着小酒盘腿上炕唠嗑  \n完整的银河挂在天上静静旋转，一起目瞪口呆对着夜空傻笑。  \n\n离开禁锢自己的小圈子，走出格子间，去见见那些有趣又素未蒙面的人，去尝试那些不敢料想的生活。  \n旅行不只去看看，更是换一种人生。\n\n[请猛戳》》神秘程序员们凭啥做好5000块的旅行？](http://example.com/)  \n\n## 东北那嘎达老好玩了 ##  \n【穿林海，跨雪原】  \n齐腰深的积雪里穿行6个小时，身体和灵魂一起燃烧，让日益麻木的自己，重新感受活着。一言不合拉着小伙伴冲进雪堆，撒泼打滚全身埋进去，欢乐和笑声在-30℃空气里沸腾。把下山的路当滑梯，从羊草山顶一路滑下来。走两步，滑两步，笑得合不拢嘴，你追我赶，像小时候，玩起来就忘了一切。\n\n【雾凇雾凇】  \n暖暖的朝阳透过晶莹的雾凇散落成一道彩虹洒在雪白的地面上，北国用那古老的方式告知世界它的灵动，它那千里冰封与万里雪飘的壮美。\n\n【被雪埋没的雪乡】  \n如果雾凇是自然奇观，那雪乡便是一种生存哲学。一年七个月被雪覆盖的，生活一切都要围着雪展开。狗拉爬犁，马拉雪橇，热乎乎火炕。大雪封门日子里，端起小酒，咱们一起脱鞋上炕，盘腿唠嗑，肆意享受冰封万里中温暖与惬意。\n\n【浪漫哈尔滨】  \n飘落雪花、巴洛克建筑和俄罗斯风俗构成了【东方莫斯科】异域风情的浪漫。中央大街俄式西餐厅、马迭尔冰棍、红肠大咧巴；圣索菲亚教堂鸽子成群盘旋飞翔；冰雪大世界、雪雕博览会、太阳岛、老道外，夜幕下哈尔滨，冰雪浪漫与美味一起闪耀。','127.172458,44.928766',8,'吉林;雾凇岛;雪乡;雪谷;向阳;五常;哈尔滨','126.568991,43.825975;126.435627,44.145183;128.214501,44.338088;128.128238,44.405947;127.475052,44.615921;127.172458,44.928766;126.624182,45.784807');
/*!40000 ALTER TABLE `travel_route_pcinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_route_wapinfo`
--

DROP TABLE IF EXISTS `travel_route_wapinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel_route_wapinfo` (
  `routeid` bigint(20) NOT NULL AUTO_INCREMENT,
  `directory` varchar(255) NOT NULL COMMENT '目录',
  `map_img` varchar(1000) NOT NULL,
  PRIMARY KEY (`routeid`),
  UNIQUE KEY `routeid_UNIQUE` (`routeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route_wapinfo`
--

LOCK TABLES `travel_route_wapinfo` WRITE;
/*!40000 ALTER TABLE `travel_route_wapinfo` DISABLE KEYS */;
INSERT INTO `travel_route_wapinfo` VALUES (1,'1;2;3;4;5;6;7;8;9;10','/p01/wap_map.jpeg'),(2,'1;2;3;4;5;6;7;8;9;10','/p01/wap_map.jpeg'),(3,'1;2;3;4;5;6;7;8;9;10','/p01/wap_map.jpeg'),(4,'1;2;3;4;5;6;7;8;9;10','/p01/wap_map.jpeg'),(5,'1;2;3;4;5;6;7;8;9;10','/p01/wap_map.jpeg');
/*!40000 ALTER TABLE `travel_route_wapinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wx_id_mapping`
--

DROP TABLE IF EXISTS `wx_id_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_id_mapping` (
  `mappingid` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountid` bigint(20) NOT NULL,
  `openid` varchar(255) NOT NULL,
  `unionid` varchar(255) NOT NULL,
  `add_time` bigint(20) NOT NULL,
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


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-17 19:44:45
