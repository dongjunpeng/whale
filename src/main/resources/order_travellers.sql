CREATE TABLE `elephant`.`order_travellers` (
    `travellerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '出行人id',
    `orderid` BIGINT(20) NOT NULL COMMENT '订单id',
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `roommate` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '室友',
    PRIMARY KEY (`travellerid`) COMMENT '主键',
    UNIQUE INDEX `travellerid_UNIQUE` (`travellerid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT 'accountid索引',
    INDEX `orderid_INDEX` (`orderid` ASC) COMMENT 'orderid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='出行人信息';
alter TABLE elephant.order_travellers AUTO_INCREMENT=1;