CREATE TABLE `elephant`.`travel_route` (
    `routeid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '路线id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '路线名称',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '类型:城市,近郊,短途,长途,国际',
    `days` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '旅行天数',
    `area` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '范围:西北,东北,西南,沿海',
    `start_place` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '出发地点',
    `end_place` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '结束地点',
    `views` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '经过城市、景点',
    `price_interval` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '价格区间',
    `description` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '路线描述',
    PRIMARY KEY (`routeid`) COMMENT '主键',
    UNIQUE INDEX `routeid_UNIQUE` (`routeid` ASC) COMMENT '主键',
    INDEX `title_INDEX` (`title` ASC) COMMENT '名称索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='路线信息';
alter TABLE elephant.travel_route AUTO_INCREMENT=1;