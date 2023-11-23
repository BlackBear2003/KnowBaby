#
Create
Database
# ------------------------------------------------------------
CREATE
DATABASE IF NOT EXISTS KnowBabyDB DEFAULT CHARACTER SET = utf8mb4;

Use
KnowBabyDB;

DROP TABLE IF EXISTS `Customer`;

CREATE TABLE `Customer`
(
    `Id`       int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `Height`   varchar(32)           DEFAULT 'secret' COMMENT 'Height',
    `Weight`   varchar(32)           DEFAULT 'secret' COMMENT 'Weight',
    `Age`      varchar(32)           DEFAULT 'secret' COMMENT 'Age',
    `Features` varchar(1024)         DEFAULT 'nothing' COMMENT 'Features',
    `Username` varchar(64)  NOT NULL DEFAULT 'default' COMMENT 'Username',
    `Password` varchar(512) NOT NULL DEFAULT 'default' COMMENT 'Password',
    `Nickname` varchar(64)  NOT NULL DEFAULT 'default' COMMENT 'Nickname',
    `Email`    varchar(500) NOT NULL DEFAULT 'default' COMMENT 'Email',
    `AvatarUrl` varchar(256)          DEFAULT NULL COMMENT '头像url',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';



DROP TABLE IF EXISTS `Merchant`;

CREATE TABLE `Merchant`
(
    `Id`                     int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchantName`           varchar(64)  NOT NULL DEFAULT 'default' COMMENT '商家名称',
    `MainCategory`           varchar(32)           DEFAULT '' COMMENT '主营类别',
    `ReturnAndExchangeRules` varchar(1024)         DEFAULT '7天无理由' COMMENT '退换货政策',
    `Introduction`           varchar(512)          DEFAULT '' COMMENT '商家描述',
    `LogisticalArea`         varchar(32)           DEFAULT '全国可发' COMMENT '物流发货地区',
    `IsLogisticsSpecifiable` bit(1)                DEFAULT b'0' COMMENT '物流方是否可指定 1: specifiable, 0: not',

    `Username`               varchar(64)  NOT NULL DEFAULT 'default' COMMENT 'Username',
    `Password`               varchar(512) NOT NULL DEFAULT 'default' COMMENT 'Password',
    `Nickname`               varchar(64)  NOT NULL DEFAULT 'default' COMMENT 'Nickname',
    `Email`                  varchar(500) NOT NULL DEFAULT 'default' COMMENT 'Email',
    `CoverUrl`               varchar(256)          DEFAULT NULL COMMENT '封面图片url',

    `InitialMsg`             varchar(64)           DEFAULT '你好呀～' COMMENT '问候语句',
    `BotName`                varchar(64)           DEFAULT '客服小俞' COMMENT '客服Bot名字',
    `ChatStyle`              varchar(32)           DEFAULT '幽默风趣' COMMENT '聊天风格',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家表';

INSERT INTO Merchant (Id, MerchantName, MainCategory, ReturnAndExchangeRules, Introduction,
                      LogisticalArea, IsLogisticsSpecifiable, Username, Password, Nickname, Email,
                      InitialMsg, BotName, ChatStyle)
VALUES (1, '测试商店', '', '', '', '', '', 'test', '123456', '店家', '', '', '', '');

DROP TABLE IF EXISTS `ChatSession`;

CREATE TABLE `ChatSession`
(
    `Id`         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `ChatId`     varchar(128) DEFAULT NULL COMMENT '会话唯一ID',
    `CustomerId` int(10) unsigned NOT NULL COMMENT 'index of customer',
    `MerchantId` int(10) unsigned NOT NULL COMMENT 'index of merchant',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户商家关联表';


DROP TABLE IF EXISTS `ChatMessage`;

CREATE TABLE `ChatMessage`
(
    `Id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `ChatSessionId` int(10) unsigned NOT NULL COMMENT '会话ID',
    `Content`       varchar(8000) DEFAULT NULL COMMENT 'content',
    `Role`          varchar(16)   DEFAULT 'user' COMMENT 'role',
    `IsPrompt` bit(1) DEFAULT b'0' COMMENT '是否是提示词 1: true, 0: false',
    `CreateTime`    timestamp     DEFAULT current_timestamp COMMENT 'time',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户商家关联表';


DROP TABLE IF EXISTS `PresetQnA`;

CREATE TABLE `PresetQnA`
(
    `Id`         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchantId` int(10) unsigned NOT NULL COMMENT '对应商家Id',
    `Q`          varchar(128) NOT NULL DEFAULT 'default' COMMENT 'question',
    `A`          varchar(128) NOT NULL DEFAULT 'default' COMMENT 'answer',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预设问题表';


DROP TABLE IF EXISTS `Merchandise`;

CREATE TABLE `Merchandise`
(
    `Id`              int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchantId`      int(10) unsigned NOT NULL COMMENT 'key of merchant',
    `MerchandiseName` varchar(64) NOT NULL DEFAULT 'default' COMMENT '商品名',
    `Targeting`       varchar(32)          DEFAULT '' COMMENT '目标群体',
    `Strategy`        varchar(512)         DEFAULT '' COMMENT '优惠策略',
    `Price`           varchar(32) NOT NULL DEFAULT '0.0' COMMENT '价格',
    `Description`     varchar(512)         DEFAULT '' COMMENT '商品信息',
    `Color`           varchar(16)          DEFAULT '' COMMENT '颜色',
    PRIMARY KEY (`Id`),
    KEY               `IX_MERCHANT_ID` (`MerchantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';


DROP TABLE IF EXISTS `MerchandiseAttr`;

CREATE TABLE `MerchandiseAttr`
(
    `Id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchandiseId` int(10) unsigned NOT NULL COMMENT 'index of merchandise',
    `AttrJson`      varchar(2000) DEFAULT NULL COMMENT 'should be JSON',
    `AttrUrl`       varchar(256)  DEFAULT NULL COMMENT 'url',
    PRIMARY KEY (`Id`),
    KEY             `IX_MERCHANDISE_ID` (`MerchandiseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';


DROP TABLE IF EXISTS `MerchantCarousel`;

CREATE TABLE `MerchantCarousel`
(
    `Id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchantId` int(10) unsigned NOT NULL COMMENT 'index of merchant',
    `Content`      varchar(128) DEFAULT NULL COMMENT 'should be JSON',
    `ImageUrl`       varchar(256)  DEFAULT NULL COMMENT 'url',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商铺轮播图表';


DROP TABLE IF EXISTS `MerchandiseCarousel`;

CREATE TABLE `MerchandiseCarousel`
(
    `Id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchandiseId` int(10) unsigned NOT NULL COMMENT 'index of merchant',
    `Content`      varchar(128) DEFAULT NULL COMMENT 'should be JSON',
    `ImageUrl`       varchar(256)  DEFAULT NULL COMMENT 'url',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品轮播图表';