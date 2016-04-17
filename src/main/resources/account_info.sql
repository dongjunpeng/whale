CREATE TABLE `elephant`.`account_info` (
    `accountid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账户id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户密码',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户类型:普通,领队,管理,合作,代理',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户状态:待激活,正常,注销',
    `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '身份证',
    `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'email',
    `wxid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信',
    `qqid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'qq',
    `wbid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微博',
    `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机',
    `add_time` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '创建时间',
    `mod_time` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '最后修改时间',
    PRIMARY KEY (`accountid`) COMMENT '主键',
    UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC) COMMENT '主键',
    INDEX `email_INDEX` (`email` ASC) COMMENT 'email索引',
    INDEX `wxid_INDEX` (`wxid` ASC) COMMENT '微信索引',
    INDEX `qqid_INDEX` (`qqid` ASC) COMMENT 'qq索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='账户主要信息';
alter TABLE elephant.account_info AUTO_INCREMENT=1;