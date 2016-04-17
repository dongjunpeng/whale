CREATE TABLE `elephant`.`leader_info` (
    `leaderid` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '领队id',
    `accountid` BIGINT(10) UNSIGNED NOT NULL COMMENT '账户id',
    `special` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '特长',
    `remark` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`leaderid`) COMMENT '主键',
    UNIQUE INDEX `leaderid_UNIQUE` (`leaderid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT '账户id索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='领队信息';
alter TABLE elephant.leader_info AUTO_INCREMENT=1;