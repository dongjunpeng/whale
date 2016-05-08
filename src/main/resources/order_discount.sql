CREATE TABLE `elephant`.`order_discount` (
    `order_discountid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单折扣id',
    `orderid` BIGINT(20) NOT NULL COMMENT '订单id',
    `discountid` BIGINT(20) COMMENT '折扣id',
    `discount_code` VARCHAR(255) COMMENT '优惠码',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '折扣类型:优惠券,满减,打折',
    `routeid` BIGINT(20)  COMMENT '路线id',
    `value` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '优惠减少的价格',
    `description` VARCHAR(255) COMMENT '优惠描述',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
    PRIMARY KEY (`order_discountid`) COMMENT '主键',
    UNIQUE INDEX `order_discountid_UNIQUE` (`order_discountid` ASC) COMMENT '主键',
    INDEX `orderid_INDEX` (`orderid` ASC) COMMENT 'orderid索引',
    INDEX `discountid_INDEX` (`discountid` ASC) COMMENT 'discountid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单优惠';
alter TABLE elephant.order_discount AUTO_INCREMENT=1;