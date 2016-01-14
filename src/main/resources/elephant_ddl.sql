CREATE DATABASE elephant;

CREATE TABLE `elephant`.`accountinfo` (
  `accountid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户名',
  `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户密码',
  `atype` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户类型，默认是普通用户',
  `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户状态，默认是待激活',
  `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户身份证',
  `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户email',
  `wxid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户微信号',
  `qqid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户qq号',
  `wbid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户微博号',
  `cellphone` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户电话',
  `addtime` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '账户创建时间',
  `modtime` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '账户最近一次修改时间',
  PRIMARY KEY (`accountid`)  COMMENT '主键',
  UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC)  COMMENT '主键',
  INDEX `id_INDEX` (`id` ASC)  COMMENT 'id索引',
  INDEX `email_INDEX` (`email` ASC)  COMMENT 'email索引')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '账户表';

