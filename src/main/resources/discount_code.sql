CREATE TABLE `elephant`.`discount_code` (
    `codeid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '优惠码id',
    `discount_code` VARCHAR(255) NOT NULL COMMENT '优惠码',
    `accountid` BIGINT(20) COMMENT '账户id',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '优惠码状态:已发放,已过期,已使用',
    `agent` BIGINT(20)  COMMENT '代理id',
    `value` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '折扣价值',
    `start_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '生效时间',      
    `end_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '结束时间',      
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',     
    `effect_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '使用时间',      
    PRIMARY KEY (`codeid`) COMMENT '主键',
    UNIQUE INDEX `codeid_UNIQUE` (`codeid` ASC) COMMENT '主键',
    INDEX `discount_code_INDEX` (`discount_code` ASC) COMMENT 'discount_code索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='优惠码';
alter TABLE elephant.discount_code AUTO_INCREMENT=1;