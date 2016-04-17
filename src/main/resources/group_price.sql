CREATE TABLE `elephant`.`group_price` (
    `priceid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '价目id',
    `groupid` BIGINT(10) UNSIGNED NOT NULL COMMENT '发团id',
    `start_place` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '出发地点',
    `end_place` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '结束地点',
    `channel` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '游客渠道',
    `price` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '定价',
    PRIMARY KEY (`priceid`) COMMENT '主键',
    UNIQUE INDEX `priceid_UNIQUE` (`priceid` ASC) COMMENT '主键',
    INDEX `groupid_INDEX` (`groupid` ASC) COMMENT '发团索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='价目表';
alter TABLE elephant.price_list AUTO_INCREMENT=1;