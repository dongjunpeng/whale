CREATE TABLE `elephant`.`account_contacts` (
    `contactid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '出行人id',
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `is_default` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否默认出行人',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件号码',
    `id_type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '证件类型:身份证,港澳台,军官证',
    `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机',
    `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '性别:未知,男,女',
    `birthday` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '出生日期',
    `address` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '地址',
    `emergency_contact` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '紧急联系人',
    `emergency_mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '紧急联系手机',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
    `mod_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
    PRIMARY KEY (`contactid`) COMMENT '主键',
    UNIQUE INDEX `contactid_UNIQUE` (`contactid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT 'accountid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='出行人信息';
alter TABLE elephant.account_contacts AUTO_INCREMENT=1;