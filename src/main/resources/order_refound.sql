CREATE TABLE `elephant`.`order_refound` (
    `refoundid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '退款id',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '退款类型:游客自愿,未成行',
    `calculation` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '计算方式:退还百分比(付款*n%),扣除百分比(付款*(1-n%)),扣除(付款-n)',
    `value` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '数值',
    `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '退款描述',
    PRIMARY KEY (`refoundid`) COMMENT '主键',
    UNIQUE INDEX `refoundid_UNIQUE` (`refoundid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='退款政策';
alter TABLE elephant.order_refound AUTO_INCREMENT=1;
