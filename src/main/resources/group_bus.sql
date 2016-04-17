CREATE TABLE `elephant`.`group_bus` (
    `busid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '大巴id',
    `groupid` BIGINT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发团id',
    `plate_number` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '车牌号',
    `manager` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '经理',
    `manager_mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '经理电话',
    `driver` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '主司机',
    `driver_mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '电话',
    `assistant` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '副司机',
    `assistant_mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '电话',
    `daily_rent` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '日租金',
    `days` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '租车天数',
    `gas` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '加油',
    `road_toll` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '过路费',
    `food` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '餐饮',
    `room` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '房间',
    `other` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '其他',
    `total` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '总计',
    `remark` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`busid`) COMMENT '主键',
    UNIQUE INDEX `busid_UNIQUE` (`busid` ASC) COMMENT '主键',
    INDEX `groupid_INDEX` (`groupid` ASC) COMMENT '发团索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='大巴表';
alter TABLE elephant.group_bus AUTO_INCREMENT=1;