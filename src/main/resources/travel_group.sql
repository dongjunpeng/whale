CREATE TABLE `elephant`.`travel_group` (
    `groupid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '发团id',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '发团状态:未发布,招募,成行,结束',
    `start_date` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '开始日期',
    `end_date` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '结束日期',
    `max_count` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '最大人数',
    `actual_count` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '实际人数',
    PRIMARY KEY (`groupid`) COMMENT '主键',
    UNIQUE INDEX `groupid_UNIQUE` (`groupid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='发团信息';
alter TABLE elephant.travel_group AUTO_INCREMENT=1;