CREATE TABLE `elephant`.`order_discount` (
    `discountid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '优惠id',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '优惠类型:临时,优惠券,团体',
    `calculation` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '计算方式:百分比(原价*n%),减百分比(原价*(1-n%)),减少(原价-n)',
    `value` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '数值',
    `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '优惠描述',
    PRIMARY KEY (`discountid`) COMMENT '主键',
    UNIQUE INDEX `discountid_UNIQUE` (`discountid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='优惠政策';
alter TABLE elephant.order_discount AUTO_INCREMENT=1;