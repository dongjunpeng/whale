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


CREATE TABLE `elephant`.`wx_id_mapping` (
  `mappingid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '映射id',
  `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
  `openid` VARCHAR(255) NOT NULL COMMENT 'openid',
  `unionid` VARCHAR(255) NOT NULL COMMENT 'unionid',
  `add_time` BIGINT(20) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`mappingid`)  COMMENT '',
  UNIQUE INDEX `mappingid_UNIQUE` (`mappingid` ASC)  COMMENT '')
COMMENT = '微信的openid，union与accountid的映射';


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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='订单优惠';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付宝支付信息表'
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='订单信息';
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='出行人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_travellers`
--

LOCK TABLES `order_travellers` WRITE;
/*!40000 ALTER TABLE `order_travellers` DISABLE KEYS */;
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
  `real` smallint(4) NOT NULL DEFAULT '0' COMMENT '发团状态:未发布,招募,成行,结束,取消',
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
  `cover` varchar(255) NOT NULL,
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
  UNIQUE KEY `routeid_UNIQUE` (`routeid`) COMMENT '主键'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='路线信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_route`
--

LOCK TABLES `travel_route` WRITE;
/*!40000 ALTER TABLE `travel_route` DISABLE KEYS */;
INSERT INTO `travel_route` VALUES (1,'西北','苍茫的天涯是我的爱~','wo',1,0,9,1,'北京','银川','兰州-祁连草原-卓尔山-祁连县-七彩丹霞-张掖-嘉峪关-莫高窟-鸣沙山月牙泉-敦煌-茶卡盐湖-青海湖-鸟岛-贵德-坎布拉-兰州','西北这三个字充满了神秘与魅力。祁连山，月牙泉，敦煌，青海湖，嘉峪关这些课本里反复出现的名词，像一次次遥远的呼唤，像一块块磁铁吸引着你前进向西。彻底放纵起来，豪迈起来，当一回地道西北汉子，甩开膀子，大口喝酒大口吃肉。','/p01/p01_000.jpg','/p01/p01_001.png',3800000,4600000,''),(2,'呼伦贝尔','透过呼吸眺望苍穹','ni',1,0,9,1,'北京','哈尔滨','哈尔滨-扎龙湿地丹顶鹤故乡-齐齐哈尔-金帐汗-敖鲁古雅-室韦-黑山头-额尔古纳-满洲里-呼伦湖-阿尔山-阿尔山森林公园-乌兰浩特-齐齐哈尔','书里的“风吹草低见牛羊”，紫薇尔康骑马唱着“红尘作伴活的潇潇洒洒，策马奔腾共享人世繁华”，《狼图腾》“小狼小狼”的呼唤，草原从此在我们心里埋下了种子。总想有一天骑上匹高头骏马，脚踩铁镫，手擎长鞭，在无边无际的绿色波浪里，不去管方向，尽情驰骋向前。','/p02/p02_000.jpg','/p02/p02_001.png',3200000,4400000,''),(3,'云南','从大理走到丽江的风花雪月','ta',1,0,7,3,'北京','昆明','昆明-大理古城-苍山国家地质公园-喜洲-沙溪-香格里拉-普达措-上虎跳峡-拉市海-丽江古城','这个曾经的梦想之地，如今却让很多人望而却步。这一次，避开偏见的源头，让稻稻还原一个最真实的滇西北给你，你一定会发现，整条路上全是让人惊艳的风景，那些藏在路线中的种种亮点，无不让滇西北依然绽放着无尽闪耀的光彩。 无论是大理古城的熙熙攘攘，苍山的云雾缭绕，喜洲超棒的海景和白族人真实的生活，还是沙溪厚重的历史变迁，石宝山归途的峡谷柔情，这才是南诏和大理古国的真实美丽；无论是虎跳峡江水奔腾的豪情壮志，还是拉市海深处世外桃源的村庄边骑马泛舟和发呆，这才是丽江自由自在的生活魅力彰显。这条几乎没太多游客的滇西北，宛若一次梦想之地回归真实的旅程，无比精彩！','/p03/p03_000.jpg','/p03/p03_001.png',2800000,3200000,''),(4,'甘南','由香巴拉草原到秘境扎尕那的藏地密境体验','wo',1,0,7,0,'北京','兰州','兰州-夏河-拉卜楞寺-玛曲大草原-玛曲-黄河大桥-唐克-黄河九曲第一湾-若尔盖-迭部-扎尕那-郎木寺-尕海湖-合作-米拉日巴佛阁-兰州','作为一条经典的旅行路线，甘南被外界评为中国10大背包客路线之首，从美丽的高原，到辽阔的草原，到日落下的黄河蜿蜒，到神山下的桃源圣地，再到藏传佛教的洗礼。大气又不失灵性的高原风景和多元文化的碰撞结合，充分展现着她的魅力。','/p04/p04_000.jpg','/p04/p04_001.png',3300000,4800000,''),(5,'绚烂北疆','漫行于额尔齐斯河与图瓦人村落的岁月静好','ni',1,0,10,1,'北京','乌鲁木齐','乌鲁木齐-可可苏里湖-可可托海-乌伦古湖-布尔津-禾木-白哈巴-观鱼亭-喀纳斯湖-三道湾-贾登峪-乌尔禾魔鬼城-乌鲁木齐','在这一段深入北疆喀纳斯的旅程中，我们会带你游览壮丽的魔鬼城，可可托海的河流、白桦林。当然最重要的，我们将进入最西北角，探索在那片与哈萨克斯坦接壤的土地上，深藏在大山大水中的湖泊、河流、森林和图瓦人的村落。全程将拜访三个独特的村落，从一开始走访喀纳斯河流经的几个神奇河湾，爬上观鱼亭一睹整个喀纳斯湖完整的美，随后到达中哈两国交接的哈巴河边，感受西北第一村——白哈巴的宁静，最后爬上禾木的观景台，在黄昏与日出的光晕中，感受炊烟与晨雾中的村落之美。这是一趟很简单的旅程，一路上你会为这里的美景深深打动。','/p05/p05_000.jpg','/p05/p05_001.png',4800000,5300000,''),(7,'西藏江南','在林芝地区开启你的西藏梦','wowowowo',1,0,9,6,'北京','拉萨','拉萨-尼洋河-巴松错-林芝-雅鲁藏布江大峡谷-南迦巴瓦峰-喇嘛岭寺-林芝-拉萨-纳木错-羊卓雍错-拉萨','如果你对西藏心生向往，却又畏惧于世界屋脊的高度，不妨从林芝地区开始你的首次西藏之旅。相比于西藏其它地区，这里海拔最低。雅鲁藏布大峡谷就像是喜马拉雅山脉的通道，印度洋的水汽畅通无阻，因而这里空气湿润，氧气充分，被称作西藏的江南。也因为充足的水汽，形成了壮美的南迦巴瓦雪山群和美丽的高原湖泊巴松错。行程最后我们回到拉萨，前往羊卓雍错和纳木错，一睹雪域圣湖的风采！','/p07/p07_000.jpg','/p07/p07_001.png',4800000,5600000,'');
/*!40000 ALTER TABLE `travel_route` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 15:47:40
