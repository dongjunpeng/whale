CREATE TABLE `elephant`.`group_leader` (
    `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `groupid` BIGINT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发团id',
    `leaderid` BIGINT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '领队id',
    `role` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '角色:主领队,副领队,实习,摄影师,亲友',
    `traffic` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '大交通',
    `room` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '房费',
    `ticket` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '门票',
    `food` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '餐补',
    `equipment` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '装备',
    `salary` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '工资',
    `other` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '其他',
    `total` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '总计',
    PRIMARY KEY (`id`) COMMENT '主键',
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) COMMENT '主键',
    INDEX `groupid_INDEX` (`groupid` ASC) COMMENT '发团索引',
    INDEX `leaderid_INDEX` (`leaderid` ASC) COMMENT '领队索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='发团领队表';
alter TABLE elephant.group_leader AUTO_INCREMENT=1;