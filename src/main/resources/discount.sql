CREATE TABLE `elephant`.`discount` (
    `discountid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '优惠id',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '折扣类型:优惠券,满减,打折',
    `routeid` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '路线id',
    `value` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '数值',
    `description` VARCHAR(255) COMMENT '优惠描述',
    `start_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '生效时间',      
    `end_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '结束时间',      
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',      
    PRIMARY KEY (`discountid`) COMMENT '主键',
    UNIQUE INDEX `discountid_UNIQUE` (`discountid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单优惠';
alter TABLE elephant.discount AUTO_INCREMENT=1;