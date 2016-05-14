CREATE TABLE `elephant`.`travel_route` (
    `routeid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '路线id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '路线名称',
    `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '副标题',
    `visible` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否可见',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '类型:城市,近郊,短途,长途,国际',
    `days` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '旅行天数',
    `area` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '范围:西北,东北,西南,沿海',
    `departure` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '出发地点',
    `distination` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '结束地点',
    `route` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '经过城市、景点',
    `description` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '路线描述',
    `imgs` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '头图地址',
    `min_price` BIGINT(20) NOT NULL COMMENT '最小价格',
    `max_price` BIGINT(20) NOT NULL COMMENT '最大价格',
    `wx_link` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '微信路线宣传链接',
    `imgtext` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '路线详细json存储地址',   
    PRIMARY KEY (`routeid`) COMMENT '主键',
    UNIQUE INDEX `routeid_UNIQUE` (`routeid` ASC) COMMENT '主键',
    INDEX `name_INDEX` (`name` ASC) COMMENT '名称索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='路线信息';
alter TABLE elephant.travel_route AUTO_INCREMENT=1;