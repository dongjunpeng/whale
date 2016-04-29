CREATE TABLE `elephant`.`account_info` (
    `accountid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `password` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '账户密码',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户类型:普通,领队,管理,代理',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '账户状态:待激活,正常,注销,异常',
    `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件号码',
    `id_type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '证件类型:身份证,港澳台,军官证',
    `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
    `mod_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
    PRIMARY KEY (`accountid`) COMMENT '主键',
    UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC) COMMENT '主键',
    INDEX `email_INDEX` (`email` ASC) COMMENT 'email索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='账户信息';
alter TABLE elephant.account_info AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`account_setting` (
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `nickname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '昵称',
    `wxid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信openid',
    `wxname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微信昵称',
    `qqid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'qq号码',
    `qqname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'qq昵称',
    `wbid` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微博id',
    `wbname` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微博昵称',
    `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '性别：未知,男,女',
    `birthday` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '出生日期',
    `address` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '地址',
    `avatar_url` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '头像URL',
    `mod_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
    PRIMARY KEY (`accountid`) COMMENT '主键',
    UNIQUE INDEX `accountid_UNIQUE` (`accountid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='账户附加信息';
alter TABLE elephant.account_setting AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`account_contacts` (
    `contactid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '出行人id',
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `is_default` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否默认出行人',
    `valid` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否有效',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件号码',
    `id_type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '证件类型:身份证0,护照1,港澳通行证2,台胞证3',
    `email` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机',
    `gender` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '性别:未知0,男1,女2',
    `birthday` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '出生日期',
    `address` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '地址',
    `emergency_contact` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '紧急联系人',
    `emergency_mobile` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '紧急联系手机',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
    `mod_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
    PRIMARY KEY (`contactid`) COMMENT '主键',
    UNIQUE INDEX `contactid_UNIQUE` (`contactid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT 'accountid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='出行人信息';
alter TABLE elephant.account_contacts AUTO_INCREMENT=1;

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
    `desc` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '路线描述',
    `imgs` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '头图地址',
    `max_price` BIGINT(20) NOT NULL COMMENT '最大价格',
    `min_price` BIGINT(20) NOT NULL COMMENT '最小价格',
    `wx_link` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '微信路线宣传链接',
    `imgtext` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '路线详细json存储地址',   
    PRIMARY KEY (`routeid`) COMMENT '主键',
    UNIQUE INDEX `routeid_UNIQUE` (`routeid` ASC) COMMENT '主键',
    INDEX `name_INDEX` (`name` ASC) COMMENT '名称索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='路线信息';
alter TABLE elephant.travel_route AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`travel_group` (
    `groupid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '发团id',
    `routeid` BIGINT(20) NOT NULL COMMENT '路线id',
    `start_date` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '开始日期',
    `end_date` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '结束日期',
    `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '一队二队三队',
    `price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '价格',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '发团状态:未发布,招募,成行,结束,取消',
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

CREATE TABLE `elephant`.`order_info` (
    `orderid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `groupid` BIGINT(20) NOT NULL COMMENT '发团id',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '订单状态:下单待付款,已取消,发起付款,已付款到账,已结束,已退款',
    `count` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '订单人数',
    `price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '订单总价',
    `actual_price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '实际付款',
    `is_agreement_ok` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否同意条款',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
    PRIMARY KEY (`orderid`) COMMENT '主键',
    UNIQUE INDEX `orderid_UNIQUE` (`orderid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT 'accountid索引',
    INDEX `groupid_INDEX` (`groupid` ASC) COMMENT 'groupid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单信息';
alter TABLE elephant.order_info AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`order_travellers` (
    `travellerid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '出行人id',
    `orderid` BIGINT(20) NOT NULL COMMENT '订单id',
    `accountid` BIGINT(20) NOT NULL COMMENT '账户id',
    `roommate` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '室友',
    PRIMARY KEY (`travellerid`) COMMENT '主键',
    UNIQUE INDEX `travellerid_UNIQUE` (`travellerid` ASC) COMMENT '主键',
    INDEX `accountid_INDEX` (`accountid` ASC) COMMENT 'accountid索引',
    INDEX `orderid_INDEX` (`orderid` ASC) COMMENT 'orderid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='出行人信息';
alter TABLE elephant.order_travellers AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`order_refound` (
    `refoundid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单退款id',
    `orderid` BIGINT(20) NOT NULL COMMENT '订单id',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '退款方式:全额,退95%,退80%,退50%',
    `desc` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '退款描述',
    `refound` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '实际退款金额',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',      
    PRIMARY KEY (`refoundid`) COMMENT '主键',
    UNIQUE INDEX `refoundid_UNIQUE` (`refoundid` ASC) COMMENT '主键',
    INDEX `orderid_INDEX` (`orderid` ASC) COMMENT 'orderid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单优惠';
alter TABLE elephant.order_refound AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`order_discount` (
    `order_discountid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单折扣id',
    `orderid` BIGINT(20) NOT NULL COMMENT '订单id',
    `discountid` BIGINT(20) NOT NULL COMMENT '折扣id',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '折扣类型:优惠券,满减,打折',
    `discount_code` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '优惠码',
    `discount_price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '优惠减少的价格',
    `desc` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '优惠描述',
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',      
    PRIMARY KEY (`order_discountid`) COMMENT '主键',
    UNIQUE INDEX `order_discountid_UNIQUE` (`order_discountid` ASC) COMMENT '主键',
    INDEX `orderid_INDEX` (`orderid` ASC) COMMENT 'orderid索引',
    INDEX `discountid_INDEX` (`discountid` ASC) COMMENT 'discountid索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单优惠';
alter TABLE elephant.order_discount AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`discount` (
    `discountid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '优惠id',
    `type` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '折扣类型:优惠券,满减,打折',
    `value` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '数值',
    `desc` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '优惠描述',
    `start_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '生效时间',      
    `end_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '结束时间',      
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',      
    PRIMARY KEY (`discountid`) COMMENT '主键',
    UNIQUE INDEX `discountid_UNIQUE` (`discountid` ASC) COMMENT '主键'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='订单优惠';
alter TABLE elephant.discount AUTO_INCREMENT=1;

CREATE TABLE `elephant`.`discount_code` (
    `codeid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '优惠码id',
    `discount_code` BIGINT(20) NOT NULL COMMENT '优惠码',
    `accountid` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '账户id',
    `status` SMALLINT(4) NOT NULL DEFAULT 0 COMMENT '优惠码状态:已发放,已过期,已使用',
    `agent` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '代理id',
    `discount_price` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '折扣价值',
    `start_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '生效时间',      
    `end_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '结束时间',      
    `add_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建时间',     
    `effect_time` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '使用时间',      
    PRIMARY KEY (`codeid`) COMMENT '主键',
    UNIQUE INDEX `codeid_UNIQUE` (`codeid` ASC) COMMENT '主键',
    INDEX `discount_code_INDEX` (`discount_code` ASC) COMMENT 'discount_code索引'
)  ENGINE=INNODB DEFAULT CHARACTER SET=UTF8 COMMENT='优惠码';
alter TABLE elephant.discount_code AUTO_INCREMENT=1;