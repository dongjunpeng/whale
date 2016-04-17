CREATE TABLE `elephant`.`group_accommodation` (
    `accommodationid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '住宿id',
    `groupid` BIGINT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发团id',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '状态:预定,付定金,结束',
    `date` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '入住日期',
    `city` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '所在城市',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '宾馆名称',
    `address` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '地址',
    `phone` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '电话',
    `room_type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '类型:单人间,2人间,3人间',
    `room_price` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '单价',
    `room_quantity` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '数量',
    `total` DECIMAL(10 , 2 ) NOT NULL DEFAULT 0 COMMENT '总价',
    `remark` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`accommodationid`) COMMENT '主键',
    UNIQUE INDEX `accommodationid_UNIQUE` (`accommodationid` ASC) COMMENT '主键',
    INDEX `groupid_INDEX` (`groupid` ASC) COMMENT '发团索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='大巴表';
alter TABLE elephant.group_accommodation AUTO_INCREMENT=1;