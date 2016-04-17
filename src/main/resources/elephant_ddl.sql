CREATE DATABASE elephant;

CREATE TABLE `elephant`.`account_info` (
  `accountid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户名',
  `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户密码',
  `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户类型，默认是普通用户',
  `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户状态，默认是待激活',
  `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户身份证',
  `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户email',
  `wxid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户微信号',
  `qqid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户qq号',
  `wbid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户微博号',
  `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户电话',
  `add_time` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '账户创建时间',
  `mod_time` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '账户最近一次修改时间',
  PRIMARY KEY (`accountid`)  COMMENT '主键',
  UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC)  COMMENT '主键',
  INDEX `id_INDEX` (`id` ASC)  COMMENT 'id索引',
  INDEX `email_INDEX` (`email` ASC)  COMMENT 'email索引')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '账户表';

CREATE TABLE `elephant`.`account_setting` (
  `accountid` BIGINT(10) UNSIGNED NOT NULL COMMENT '',
  `nickname` VARCHAR(255) NOT NULL COMMENT '',
  `wxname` VARCHAR(255) NULL COMMENT '',
  `qqname` VARCHAR(255) NULL COMMENT '',
  `wbname` VARCHAR(255) NULL COMMENT '',
  `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '0 位置 1 男 2 女',
  `birthday` VARCHAR(45) NULL COMMENT '',
  `avatar_url` VARCHAR(255) NULL COMMENT '',
  `mod_time` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`accountId`)  COMMENT '',
  UNIQUE INDEX `accountId_UNIQUE` (`accountId` ASC)  COMMENT '');

CREATE TABLE `account_contacts` (
  `contactid` bigint(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '联系人id',
  `accountid` varchar(45) NOT NULL COMMENT '账户id',
  `name` varchar(255) NOT NULL COMMENT '联系人姓名',
  `id` varchar(255) NOT NULL COMMENT 'id号',
  `id_type` smallint(4) NOT NULL COMMENT 'id类型，身份证,护照,港澳通行证,台胞证',
  `mobile` varchar(255) NOT NULL COMMENT '手机',
  `email` varchar(255) NOT NULL COMMENT '电子邮箱',
  `gender` smallint(4) NOT NULL COMMENT '性别',
  `birthday` varchar(45) NOT NULL COMMENT '生日',
  `add_time` varchar(45) NOT NULL COMMENT '添加时间',
  `mod_time` varchar(45) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`contactid`),
  UNIQUE KEY `contactid_UNIQUE` (`contactid`),
  KEY `accountid` (`contactid`)
) 
ENGINE=InnoDB 
DEFAULT CHARACTER SET = utf8
COMMENT = '账户联系人表';
