CREATE TABLE `elephant`.`account_info` (
    `accountid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户密码',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户类型:普通,领队,管理,代理',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户状态:待激活,正常,注销,异常',
    `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件号码',
    `id_type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '证件类型:身份证,港澳台,军官证',
    `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
    `mod_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
    PRIMARY KEY (`accountid`) COMMENT '主键',
    UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC) COMMENT '主键',
    INDEX `email_INDEX` (`email` ASC) COMMENT 'email索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='账户信息';
alter TABLE elephant.account_info AUTO_INCREMENT=1;