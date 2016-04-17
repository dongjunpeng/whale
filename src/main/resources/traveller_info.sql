CREATE TABLE `elephant`.`traveller_info` (
    `travellerid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '游客id',
    `accountid` BIGINT(10) UNSIGNED NOT NULL COMMENT '账户id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件号码',
    `id_type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '证件类型:身份证,护照,港澳通行证,台胞证',
    `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '性别',
    `birthday` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '出生日期',
    `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机',
    `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'email',
    `wxid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信号',
    `address` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '地址',
    `emergency_contact` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '紧急联系人',
    `emergency_phone` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '紧急联系电话',
    `add_time` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '最后修改时间',
    `mod_time` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '最后修改时间',
    PRIMARY KEY (`travellerid`) COMMENT '主键',
    UNIQUE INDEX `travellerid_UNIQUE` (`travellerid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT '账户id索引',
    INDEX `id_INDEX` (`id` ASC) COMMENT '身份证索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='游客信息';
alter TABLE elephant.traveller_additional AUTO_INCREMENT=1000000;