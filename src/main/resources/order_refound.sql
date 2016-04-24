CREATE TABLE `elephant`.`order_refound` (
    `refoundid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单退款id',
    `orderid` BIGINT(20) NOT NULL COMMENT '订单id',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '退款方式:全额,退95%,退80%,退50%',
    `desc` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '退款描述',
    `refound` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '实际退款金额',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',      
    PRIMARY KEY (`refoundid`) COMMENT '主键',
    UNIQUE INDEX `refoundid_UNIQUE` (`refoundid` ASC) COMMENT '主键',
    INDEX `orderid_INDEX` (`orderid` ASC) COMMENT 'orderid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单优惠';
alter TABLE elephant.order_refound AUTO_INCREMENT=1;