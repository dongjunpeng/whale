CREATE TABLE `elephant`.`order_travellers` (
    `travellerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '出行人id',
    `orderid` BIGINT(20) NOT NULL COMMENT '订单id',
    `contactid` BIGINT(20) NOT NULL COMMENT '联系人id',
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件号码',
    `id_type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '证件类型:身份证0,护照1,港澳通行证2,台胞证3',
    `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机',
    `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '性别:未知0,男1,女2',
    `birthday` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '出生日期',
    `address` VARCHAR(2000) COMMENT '地址',
    `emergency_contact` VARCHAR(255) COMMENT '紧急联系人',
    `emergency_mobile` VARCHAR(255)  COMMENT '紧急联系手机',
    `roommate` VARCHAR(255) COMMENT '室友',
    PRIMARY KEY (`travellerid`) COMMENT '主键',
    UNIQUE INDEX `travellerid_UNIQUE` (`travellerid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT 'accountid索引',
    INDEX `orderid_INDEX` (`orderid` ASC) COMMENT 'orderid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='出行人信息';
alter TABLE elephant.order_travellers AUTO_INCREMENT=1;