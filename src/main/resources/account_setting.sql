CREATE TABLE `elephant`.`account_setting` (
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `nickname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '昵称',
    `wxid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信openid',
    `wxname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信昵称',
    `qqid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'qq号码',
    `qqname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'qq昵称',
    `wbid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微博id',
    `wbname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微博昵称',
    `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '性别：未知,男,女',
    `birthday` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '出生日期',
    `address` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '地址',
    `avatar_url` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '头像URL',
    `mod_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
    PRIMARY KEY (`accountid`) COMMENT '主键',
    UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='账户附加信息';
alter TABLE elephant.account_setting AUTO_INCREMENT=1;