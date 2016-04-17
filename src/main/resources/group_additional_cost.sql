CREATE TABLE `elephant`.`group_additional_cost` (
    `costid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '支出id',
    `groupid` BIGINT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发团id',
    `item` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '支出项目',
    `remark` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '备注',
    `total` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '总价',
    PRIMARY KEY (`costid`) COMMENT '主键',
    UNIQUE INDEX `costid_UNIQUE` (`costid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='其他支出表';
alter TABLE elephant.group_additional_cost AUTO_INCREMENT=1;