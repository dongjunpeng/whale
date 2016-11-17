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
-- Dumping data for table `account_contacts`
--

LOCK TABLES `account_contacts` WRITE;
/*!40000 ALTER TABLE `account_contacts` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出行人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_travellers`
--

LOCK TABLES `order_travellers` WRITE;
/*!40000 ALTER TABLE `order_travellers` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_travellers` ENABLE KEYS */;
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
  PRIMARY KEY (`routeid`) COMMENT '主键',
  UNIQUE KEY `routeid_UNIQUE` (`routeid`) COMMENT '主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route`
--

LOCK TABLES `travel_route` WRITE;
/*!40000 ALTER TABLE `travel_route` DISABLE KEYS */;
INSERT INTO `travel_route` VALUES (1,'西北','苍茫的天涯是我的爱~',1,0,9,1,'','北京','银川','','西北这三个字充满了神秘与魅力。祁连山，月牙泉，敦煌，青海湖，嘉峪关这些课本里反复出现的名词，像一次次遥远的呼唤，像一块块磁铁吸引着你前进向西。彻底放纵起来，豪迈起来，当一回地道西北汉子，甩开膀子，大口喝酒大口吃肉。','wo','/p01/p01_000.jpg',3800000,4600000),(2,'呼伦贝尔','透过呼吸眺望苍穹',1,0,9,1,'','北京','哈尔滨','','书里的“风吹草低见牛羊”，紫薇尔康骑马唱着“红尘作伴活的潇潇洒洒，策马奔腾共享人世繁华”，《狼图腾》“小狼小狼”的呼唤，草原从此在我们心里埋下了种子。总想有一天骑上匹高头骏马，脚踩铁镫，手擎长鞭，在无边无际的绿色波浪里，不去管方向，尽情驰骋向前。','ni','/p02/p02_000.jpg',3200000,4400000),(3,'云南','从大理走到丽江的风花雪月',1,0,7,3,'','北京','昆明','','这个曾经的梦想之地，如今却让很多人望而却步。这一次，避开偏见的源头，让稻稻还原一个最真实的滇西北给你，你一定会发现，整条路上全是让人惊艳的风景，那些藏在路线中的种种亮点，无不让滇西北依然绽放着无尽闪耀的光彩。 无论是大理古城的熙熙攘攘，苍山的云雾缭绕，喜洲超棒的海景和白族人真实的生活，还是沙溪厚重的历史变迁，石宝山归途的峡谷柔情，这才是南诏和大理古国的真实美丽；无论是虎跳峡江水奔腾的豪情壮志，还是拉市海深处世外桃源的村庄边骑马泛舟和发呆，这才是丽江自由自在的生活魅力彰显。这条几乎没太多游客的滇西北，宛若一次梦想之地回归真实的旅程，无比精彩！','ta','/p03/p03_000.jpg',2800000,3200000),(4,'甘南','由香巴拉草原到秘境扎尕那的藏地密境体验',1,0,7,0,'','北京','兰州','','作为一条经典的旅行路线，甘南被外界评为中国10大背包客路线之首，从美丽的高原，到辽阔的草原，到日落下的黄河蜿蜒，到神山下的桃源圣地，再到藏传佛教的洗礼。大气又不失灵性的高原风景和多元文化的碰撞结合，充分展现着她的魅力。','wo','/p04/p04_000.jpg',3300000,4800000),(5,'绚烂北疆','漫行于额尔齐斯河与图瓦人村落的岁月静好',1,0,10,1,'','北京','乌鲁木齐','','在这一段深入北疆喀纳斯的旅程中，我们会带你游览壮丽的魔鬼城，可可托海的河流、白桦林。当然最重要的，我们将进入最西北角，探索在那片与哈萨克斯坦接壤的土地上，深藏在大山大水中的湖泊、河流、森林和图瓦人的村落。全程将拜访三个独特的村落，从一开始走访喀纳斯河流经的几个神奇河湾，爬上观鱼亭一睹整个喀纳斯湖完整的美，随后到达中哈两国交接的哈巴河边，感受西北第一村——白哈巴的宁静，最后爬上禾木的观景台，在黄昏与日出的光晕中，感受炊烟与晨雾中的村落之美。这是一趟很简单的旅程，一路上你会为这里的美景深深打动。','ni','/p05/p05_000.jpg',4800000,5300000),(6,'西藏江南','在林芝地区开启你的西藏梦',1,0,9,6,'','北京','拉萨','','如果你对西藏心生向往，却又畏惧于世界屋脊的高度，不妨从林芝地区开始你的首次西藏之旅。相比于西藏其它地区，这里海拔最低。雅鲁藏布大峡谷就像是喜马拉雅山脉的通道，印度洋的水汽畅通无阻，因而这里空气湿润，氧气充分，被称作西藏的江南。也因为充足的水汽，形成了壮美的南迦巴瓦雪山群和美丽的高原湖泊巴松错。行程最后我们回到拉萨，前往羊卓雍错和纳木错，一睹雪域圣湖的风采！','wowowowo','/p06/p06_000.jpg',4800000,5600000);
/*!40000 ALTER TABLE `travel_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_route_days`
--

DROP TABLE IF EXISTS `travel_route_days`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel_route_days` (
  `routedaysid` bigint(20) NOT NULL AUTO_INCREMENT,
  `routeid` bigint(20) unsigned NOT NULL,
  `dayno` int(10) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `imgs` varchar(1000) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `detail` text NOT NULL,
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
-- Dumping data for table `travel_route_days`
--

LOCK TABLES `travel_route_days` WRITE;
/*!40000 ALTER TABLE `travel_route_days` DISABLE KEYS */;
INSERT INTO `travel_route_days` VALUES (1,1,1,' (集合日) 兰州','/p01/p01_201.jpg','第一个图片','今天是集合日，没有活动安排，当天到达即可！ \n到兰州直接入住酒店，领队会热情招待。详细报名后见微信群~ \n \n还没到兰州，就见黄土万壑、戈壁飞沙，粗犷豪放扑面而来。\n城市夹在黄河谷地间，风景就在两岸。霸气耸立【中山铁桥】横跨滚滚黄河，羊皮筏子随波漂流。尝尝地道八宝茶，逛逛水车博物馆、【黄河母亲】雕像，大西北我来啦。 \n \n除了牛肉面名声在外，钻进木塔巷、大众巷、正宁街，寻找当地独有的酿皮、晶糕、杏皮水、洋芋片、牛奶鸡蛋醪糟、羊肉手抓，美味那可是应接不暇。 \n \n晚上9：00召开【见面会】，认识领队和一路同行小伙伴，不要迟到哦。','给我一支兰州;中山桥;黄河母亲',NULL,NULL,NULL,'住宿：格林豪泰/派酒店（标准间）'),(2,1,2,'兰州-塔尔寺-贵德-西宁','/p01/p01_202.jpg;/p01/p01_203.jpg','1;2','早上出发，领队会带小伙伴们熟悉起来，欢声笑语中奔向【塔尔寺】。这里是宗喀巴大师诞生地，黄教六大寺院之一，感受藏传佛教的神秘和传奇。之后前往【贵德】，一路山峦草甸丹霞，海拔上升，慢慢适应高原反应。“天下黄河贵德清”，昨日兰州滚滚而去，今天她的清澈一定让你倍感惊奇。 \n \n傍晚回到【西宁】，这座被称为夏都的城市不但有东关大寺灿烂的灯光，更有数不清的好吃的！奔向莫家街、大十字，德禄酸奶、甜醅、马忠食府、益鑫羊肉手抓、面片，西宁羊肉毫无膻味，酸奶更是浓厚深邃冲击味蕾，口水都要流出来了！','塔尔寺;初上高原;拉鸡山;西宁夏城','亮点：高原上的纯粹信仰','含餐：兰州早餐','车程：400KM/5h','住宿：派酒店/景尚假日（标准间）'),(3,1,3,'西宁-祁连-卓尔山','/p01/p01_204.jpg;/p01/p01_205.jpg','1;2','跨越祁连山脉，牦牛，羊群，空旷的柏油路上我们一路向前。翻过【黑泉水库】，眼前是一片草丰水美，牛马撒欢的河滩牧场。这可是我们“独家”发现的绝美牧场，走进草甸野花，踏过流水浅滩，零距离接触牛群牧马。 \n \n然后一路翻山越岭，在3000海拔的祁连山中穿梭，俯瞰山脚广袤土地平原。下午来到【祁连大草原】，山峦叠嶂中竟然隐藏着如此平坦开阔草场。那延绵壮美一定让你惊讶的合不拢嘴。傍晚深入天境祁连【卓尔山】，360度俯瞰整个山谷，遥望终年积雪牛心山，脚下蜿蜒祁连县城。 \n \n当晚入住祁连山脚回族人家，一整面玻璃落地窗，看着雪山吃一顿地道回族大餐。夜幕来临，世界都清静了，抬头是银河漫天繁星，低头是皑皑祁连。风景绝美被誉为隐藏最好的香格里拉。','高原牧场;祁连山脉;祁连大草原;卓尔山;雪山脚下人家','亮点：祁连天境，皑皑雪山莽莽草原','含餐：西宁早餐/回族晚餐','车程：280KM/5h','住宿：雪山下回族人家（标准间）'),(4,1,4,'祁连山-七彩丹霞-张掖','/p01/p01_206.jpg;/p01/p01_207.jpg','1;2','今天继续穿越祁连山深处的草原，这里没有过往的游客，没有城市的喧嚣，天地间剩下的只有宁静和宽容。然后我们将跨越海拔3685米的【峨堡岭垭口】，作短暂的停留，像藏民一样在垭口挥洒龙达为来世祈福。慢慢行走，在这里感受无边无际的高山草原的美丽。 \n \n下午抵达张掖的【七彩丹霞】，深入这片神奇的地貌，在日光的映衬下，多变的色彩像是打翻了调色盘，红的如火，黄得似金，灰的如钢。各处造型奇特的山地丘陵色彩斑斓、气势磅礴。在这里呆上一整个下午，看夕阳在五彩的丹霞山脉间缓缓流淌。张掖【甘州市场】热闹非凡，卷子鸡、搓鱼儿、炒炮痛痛快快来顿大餐。','峨堡岭垭口;七彩丹霞;甘州市场','亮点：上天打翻调色盘染红七彩丹霞','含餐：祁连早餐','车程：280KM/4h','住宿： 吉吉/商汇/徽商（标准间'),(5,1,5,'张掖-嘉峪关-瓜州','/p01/p01_208.jpg;/p01/p01_209.jpg','1;2','早晨从张掖出发，沿着河西走廊一路往西，到达咽喉重镇【嘉峪关】。嘉峪关市与酒泉市连成一片，不仅有浓厚历史深度更是新兴工业城市，素有“钢城”美誉。规划整齐道路宽阔，其中【铁镜市场】可是当地人的最爱，眼镜烤肉，炒饼，卷边鸡，烤羊皮，杏皮水，快来大快朵颐。 \n \n巍峨的祁连雪山之下，是古老的【嘉峪关关城】，其连同山脊之上的【悬壁长城】，共同构筑了明长城西端的第一重关，也是明长城全线中保存最完好的一个。“严关百尺界天西，万里征人驻马蹄。”登上城楼远眺长城似游龙浮动于浩瀚戈壁滩，白雪覆盖的祁连山脉在天边绵延。出关到达【瓜州】，烤鱼串串香甜哈密瓜，夜市走你！','嘉峪关;悬壁长城;烤肉飘香;瓜州夜市','亮点：万里长城永不倒，嘉峪关已过千年','含餐：张掖早餐','车程：500KM/5h','住宿：融金洲海/国风（标准间）'),(6,1,6,'瓜州-莫高窟-鸣沙山','/p01/p01_210.jpg;/p01/p01_211.jpg','1;2','一早前往丝绸之路上的艺术瑰宝【莫高窟】，精美壁画塑像讲述着时间的故事，历史如同卷轴般展开。洞窟内鬼斧神工的艺术瑰宝、墙面跳跃的色彩、饱满的人物形象、充满意境的寓意，会让每个人凝神惊叹。 \n \n“锦缥细展墨花翻，河东二柳敦煌索。”【鸣沙山】又旅行的一个高潮。一汪形似月牙的泉水衬托着这个绿洲边缘的大沙丘。进入这浩瀚的沙漠，手脚并用爬到山顶，屹立沙丘之上远望日渐消退的那一弯孤月，观赏沙漠中的那一湾动人清泉。不用担心时间，酒店就在鸣沙山脚下。 \n \n夕阳落下天色渐晚，如果你对莫高窟的艺术震撼意犹未尽，那么不妨再去看一场“敦煌盛典”演出；如果你想继续吃吃吃节奏，那就去热闹非凡【沙洲夜市】，驴肉黄面、骆驼掌，杏皮水做好准备迎接舌尖上的盛宴。','莫高窟;鸣沙山;月牙泉;沙洲夜市','亮点：寻找时间里零落的丝绸之路','含餐：瓜州早餐','车程：120KM/1.5小时','住宿：映月山庄/敦煌人家（标准间）'),(7,1,7,'敦煌-柴达木盆地-茶卡盐湖','/p01/p01_212.jpg;/p01/p01_213.jpg','1;2','早餐后，驱车穿行于【柴达木盆地】之上，惊见延绵无际的戈壁滩，苍鹰盘旋于头顶，这便是来自青藏高原的礼遇。一路荒无人烟，笔直的公里延伸到视线尽头，宽阔的柏油公路上只有我们向前奔驰。仰望着拔地而起高高在上的铁路桥，那是通往青藏高原的“天路”，这个时候就一起大声歌唱吧，尽情感受西北的广袤和苍茫。 \n \n傍晚抵达【茶卡盐湖】，盐湖如雪堆积般的白色，湖水如镜，吸饱了盐，倒映出湛蓝的天巍峨的山，像极了玻利维亚的天空之境。踩着又像沙滩又像雪堆的盐滩，漫步到湖中心张开双臂拥抱倒影，挥舞丝巾肆意拍照，白色世界里留下耀眼的色彩绚烂。 \n \n跑遍茶卡，终于在村长家找到了满意住宿。今年新建三层楼远离灯光污染，顶层天台专门拍星空。夜幕降临，整个【银河】在天空中静静旋转，一抬头就清晰可见，“银河！真的是一条河！”“流星！快看流星！”支起三脚架对准夜空，听老青海人讲述茶卡历史，今夜注定难眠。','柴达木盆地;青藏铁路;茶卡盐湖;银河星空','亮点：茶卡盐湖边等银河里流星滑过','含餐：敦煌早餐/茶卡晚餐','车程：700KM/8h','住宿：茶卡村长家（标准间）'),(8,1,8,'茶卡-青海湖-兰州','/p01/p01_214.jpg;/p01/p01_215.jpg','1;2','茶卡不止有银河星空，还有日出等着你，金色的太阳，光芒万丈，从山的那边缓缓爬出来，云彩和湖面被照耀成一片金黄色，真是美不胜收！ \n \n翻过一座座山【青海湖】赫然出现。一眼望去一定以为自己到了海边，水天一色，遥遥无尽头，烟波浩淼、碧波连天。聆听3000米浪花拍打着沙滩。近处笔直的油柏路，金灿灿的油菜花，远处是蓝色飘带般的青海湖，更远的是连绵的山，山背后是青藏高原团团白云湛蓝青天。 \n \n傍晚我们回到【兰州】，最后大干一场，来一顿只属于西部的豪情晚宴！与小伙伴们依依不舍挥别吧，这场难忘旅行也就在这里结束啦~ \n（约晚6点到达，当晚出发小伙伴要定8点以后火车/飞机，以免堵车延误）','盐湖日出;青海湖','亮点：3000米海拔上的陆心之海','含餐：茶卡早餐','车程：400KM/5h','住宿：格林豪泰/派酒店（标准间）'),(9,1,9,'（解散日）兰州','/p01/p01_216.jpg','1;2','今天是解散日，这一天没有活动安排，大家洒泪说再见，根据自身情况选择合适时间的航班或火车回到温暖的家。\n \nPS：走之前别忘了【兰州拉面】。 \n正宗的牛肉面馆都是早上经营，下午两点就关门了，牛肉面在兰州是早餐、午餐的代言词。街边上随便一个小馆儿，味道都是独一无二的。一旦出了兰州，哪怕挂着“正宗”招牌，哪怕同样的回民经营，味道也是完全不同的，最正宗的牛肉面必须要在兰州吃！','兰州拉面;再见大西北',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `travel_route_days` ENABLE KEYS */;
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
  `local` text NOT NULL,
  `prepare` text NOT NULL,
  `traffic` text NOT NULL,
  `expense_include` text NOT NULL,
  `expense_exclude` text NOT NULL,
  `refund` text NOT NULL,
  PRIMARY KEY (`routeid`),
  UNIQUE KEY `routeid_UNIQUE` (`routeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品更多信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route_more`
--

LOCK TABLES `travel_route_more` WRITE;
/*!40000 ALTER TABLE `travel_route_more` DISABLE KEYS */;
INSERT INTO `travel_route_more` VALUES (1,'/p01/p01_101.jpg;/p01/p01_102.jpg;/p01/p01_103.jpg;/p01/p01_104.jpg;/p01/p01_105.jpg;/p01/p01_106.jpg;/p01/p01_107.jpg;/p01/p01_108.jpg;/p01/p01_109.jpg','黄河与美食，构成兰州这座典型西北城市;塔尔寺让灵魂沉醉于信仰的思考;千里牧场终年雪山，这里是天境祁连;夕阳的余晖掠过大地为丹霞又加了一笔色彩;醉卧沙场君莫笑，古来征战几人还;屹立沙丘之上遥望怀抱中那湾泉水;城市里没有的璀璨银河与漫天繁星;青海湖--陆心之海;再见了小伙伴，再见了大西北','【气候】 西北跨度广温差大，气候干燥阳光强烈。早晚最低15度，正午最高35度。要多喝水，注意防晒。    【住宿】 同价位旅行中住宿是最好的，没有之一。所有住宿都经过严格实地考察，并且90%以上好评。深入当地，住进祁连山脚回民家，住进茶卡村长家，融入真正生活。    【海拔】 3000米左右，夏季植被丰富，只有少部分人会轻度反应。我们会留出足够时间让你慢慢适应。  【民族】回族、藏族聚居，尊重他们宗教信仰和生活习惯。近距离拍摄人像一定要征得对方同意。  【饮食】西北肉食面食居多，小吃瓜果丰盛，但蔬菜较少。初上高原注意饮食，防止腹泻，及时补充维生素。','【衣服】\n上衣：冲锋衣/薄外套+长袖+短袖\n下衣：长裤/裙子+短裤+运动鞋\n换洗：内衣若干+袜子若干\n防晒：头巾+帽子+墨镜+防晒霜 \n注意：光照强温差大，昼夜温差约15度。\n\n【装备】\n必带：身份证+手机+钥匙+钱包 \n相机：带上三脚架可劲拍星空\n行李：背包、行李箱皆可\n\n【药品】\n自备应急药品，如维生素、感冒药、止泻药、晕车药。\n\n【美女专属】\n带鲜艳的衣服、丝巾，茶卡盐湖青海湖都是绝佳拍照地，有了色彩点缀最能拍的美美哒~','【美女专属】\n带鲜艳的衣服、丝巾，茶卡盐湖青海湖都是绝佳拍照地，有了色彩点缀最能拍的美美哒~\n\n【火车\n北京-兰州每天7趟车次，单程硬卧400左右，软卧600左右，抢不到火车票可以联系客服帮忙。\n\n【飞机\n北京-兰州单程机票1000左右，中川机场-兰州站直达城际，12306购票末班22：30。机场大巴下车点距入住酒店1.5km。\n\n【集合日/解散日酒店\n格林豪泰火车站东路店。火车站向东200米路北。','【交通】 \n全程正规空调包车费用，油费高速费司机食宿补贴。 \n \n【住宿】 \n全程住宿费用 （8晚），详情参见行程安排。 \n \n【门票】 \n旅行中的所有门票。（卓尔山/含景区车+七彩丹霞/含景区车+嘉峪关关城+悬臂长城+莫高窟+鸣沙山+茶卡盐湖） \n \n【用餐】 \n全程早餐，起床就有吃的；正餐参见行程 \n \n【领队】 \n领队交通，工资，食宿补贴。他们文艺逗逼靠谱专业，帮你解决一切旅途操心事儿。 \n \n【保险】 \n10万旅游意外险 \n \n【物资】 \n对讲机，公共物资，急救包，氧气瓶。','【往返大交通】 \n各地往返兰州的大交通费用。 \n \n【部分用餐】 \n未包含的正餐，每天约40-80元左右，含餐参见行程安排。','【退订活动】 \n1.出发前 **21天及以上** 退订活动，活动费用的 **5%** 将无法退还。 \n2.出发前 **20天至7天** 退订活动，活动费用的 **20%** 将无法退还。 \n3.出发前 **6天至1天** 退订活动，活动费用的 **50%** 将无法退还。 \n4.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。 \n5.出发日 **当天** 退订活动，活动费用的 **80%** 将无法退还。 \n\n 【退款流程】 \n 订单中点击退款。系统会时间自动计算出退款费用，确认无误后，3-15个工作日原路返还至您当时支付的账户。 \n\n 【放心报名】 \n 如因海逍遥原因导致行程取消，将与您协商调整日期或更换路线。 \n 如无法调整则全额退款，并承担相应机票、火车票退改签损失。同时补偿不低于300元优惠码，可在任意路线抵减团费。 \n （仅对支付成功用户哦，请先报名再买票） \n\n 【无购物承诺】 \n 购物和回扣神马的，海逍遥深恶痛绝，如发生任何性质推销、购物、回扣，团费全额退还外，领队携全体员裸奔致歉。');
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
  `introduction` text NOT NULL,
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
INSERT INTO `travel_route_pcinfo` VALUES (1,'年轻活力小团队，跟有趣小伙伴一起玩;八个夜晚五场夜市，吃货们满载而归;走进祁连独家河畔牧场，入住雪山脚下人家;3000海拔无边无际大草原和那碧蓝的天;张掖敦煌嘉峪关，追一场两年的梦;绝佳拍摄点，茶卡星空银河日出看个够','## 程序员会武术，谁也挡不住 ##\n嗯，又是几个拯救世界的程序员干的。海逍遥，从基因开始就别人家不一样。\n\n不要熊孩子夕阳红，不要导游购物套路，不要出门净操心。\n要跟同龄小伙伴一起玩，聊得起来才能玩的嗨。老司机们深藏不露，鲜肉小帅哥、气质大美女层出不穷。跟他们一起玩，光想想就开心~\n\n白天穿行林海雪原，淌过山花烂漫溪水；晚上雪山脚下泡温泉，湖边搭帐篷露营，动手烤鱼撸串；吃饭围坐一桌，好吃的端上来顾不上形象，七手八脚下筷子；\n车上老司机带节奏，K歌三国杀谁是卧底根本停不下来；深夜仰望满天繁星，银河真的是条河挂在天上，忍不住欢呼。\n\n这！才！叫！旅！行！\n\n离开禁锢自己的小圈子，走出格子间，去见见那些有趣又素未蒙面的人，去尝试那些不敢料想的生活。\n旅行不仅出来看看，更不只是吃住行，而是换一种人生。\n\n[请猛戳》》神秘程序员们凭啥做好5000块的旅行？](http://example.com/)\n\n## 西北有啥好玩的？##\n【江湖大侠梦】\n西北横跨甘肃、青海两省，由兰州踏上丝绸之路向西，至嘉峪关敦煌。南下青藏高原，绕青海湖返回。丝绸之路，边关故城，陆心之海，茶卡盐湖。辽阔苍茫，哪里都是江湖。回到拥挤地铁，周围一切毫不真实，像隔了一辈子，心灵沉醉在大侠梦中，久久不肯归来。\n\n【祁连天境】\n祁连山高耸入云，俯瞰广袤山川大地；回族人家就在山脚下，一整面玻璃墙对着雪山，看着积雪喝酸奶。荒芜广袤的戈壁沙漠，几百公里荒无人烟，公路直插天边尽头。\n\n【高原牧场】\n祁连草原老么大老么大了，从脚下到天边，望也望不见头；经幡在墨绿色草地上肆意飘扬，格外鲜艳刺眼。悬崖峭壁间漫山遍野的山羊，冰川融水旁壮硕黝黑牦牛；湛蓝湛蓝的天空下，身披红衣面色凝重的藏族喇嘛。\n\n【丝绸之路】\n丝绸之路上千年古迹，张掖鼓楼、卧佛寺，嘉峪关延绵的长城、敦煌莫高窟的飞天梦和鸣沙山下永不干涸的月牙泉。\n雪白盐堆积出茶卡盐湖的“天空之境”，蓝天白云和你一起倒影雪白湖中。高海拔天空异常澄澈，银河、流星抬头就能看到；青海湖遍3000米海拔上吹过海风与浪花。','',8,'1','1,2');
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
  PRIMARY KEY (`routeid`),
  UNIQUE KEY `routeid_UNIQUE` (`routeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route_wapinfo`
--

LOCK TABLES `travel_route_wapinfo` WRITE;
/*!40000 ALTER TABLE `travel_route_wapinfo` DISABLE KEYS */;
INSERT INTO `travel_route_wapinfo` VALUES (1,'1;2;3;4;5;6;7;8;9;10');
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
