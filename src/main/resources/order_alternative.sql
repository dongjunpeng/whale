CREATE TABLE `elephant`.`order_alternative` (
    `alternativeid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '支出id',
    `orderid` BIGINT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发团id',
    `alternative_travellerid` BIGINT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发团id',
    `remark` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`alternativeid`) COMMENT '主键',
    UNIQUE INDEX `alternativeid_UNIQUE` (`alternativeid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='替换表';
alter TABLE elephant.order_alternative AUTO_INCREMENT=1;