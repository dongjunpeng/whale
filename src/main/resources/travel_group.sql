CREATE TABLE `elephant`.`travel_group` (
    `groupid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '发团id',
    `routeid` BIGINT(20) NOT NULL COMMENT '路线id',
    `price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '价格',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '发团状态:未发布,招募,成行,结束,取消',
    `start_date` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '开始日期',
    `end_date` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '结束日期',
    `max_count` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '最大人数',
    `min_count` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '最小人数',
    `actual_count` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '实际人数',
    `wx_qrcode` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '微信群链接',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '添加时间',
    `mod_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
    PRIMARY KEY (`groupid`) COMMENT '主键',
    UNIQUE INDEX `groupid_UNIQUE` (`groupid` ASC) COMMENT '主键',
    INDEX `routeid_INDEX` (`routeid` ASC) COMMENT 'routeid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='发团信息';
alter TABLE elephant.travel_group AUTO_INCREMENT=1;