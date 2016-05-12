CREATE TABLE `elephant`.`order_info` (
    `orderid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `routeid` BIGINT(20) NOT NULL COMMENT '路线id',
    `groupid` BIGINT(20) NOT NULL COMMENT '发团id',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '订单状态:下单待付款,已取消,发起付款,已付款到账,已结束,已退款',
    `count` SMALLINT(4) NOT NULL COMMENT '订单人数',
    `student_count` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '订单人数',
    `price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '订单总价',
    `actual_price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '实际付款',
    `is_agreement_ok` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否同意条款',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
    PRIMARY KEY (`orderid`) COMMENT '主键',
    UNIQUE INDEX `orderid_UNIQUE` (`orderid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT 'accountid索引',
    INDEX `groupid_INDEX` (`groupid` ASC) COMMENT 'groupid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单信息';
alter TABLE elephant.order_info AUTO_INCREMENT=1;