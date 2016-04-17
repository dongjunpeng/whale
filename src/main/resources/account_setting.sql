CREATE TABLE `elephant`.`account_setting` (
    `accountid` BIGINT(10) UNSIGNED NOT NULL COMMENT '账户id',
    `nickname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '昵称',
    `wxname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信昵称',
    `qqname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'qq昵称',
    `wbname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微博昵称',
    `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '性别',
    `birthday` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '出生日期',
    `avatar_url` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '头像URL',
    `mod_time` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '最后修改时间',
    PRIMARY KEY (`accountid`) COMMENT '主键',
    UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='账户附加信息';
alter TABLE elephant.account_setting AUTO_INCREMENT=1;