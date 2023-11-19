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
    `Height`   varchar(32)   NOT NULL DEFAULT 'default' COMMENT 'Height',
    `Weight`   varchar(32)   NOT NULL DEFAULT 'default' COMMENT 'Weight',
    `Age`      varchar(32)   NOT NULL DEFAULT 'default' COMMENT 'Age',
    `Features` varchar(1024) NOT NULL DEFAULT 'default' COMMENT 'Features',
    `Username` varchar(64)   NOT NULL DEFAULT 'default' COMMENT 'Username',
    `Password` varchar(512)  NOT NULL DEFAULT 'default' COMMENT 'Password',
    `Nickname` varchar(64)   NOT NULL DEFAULT 'default' COMMENT 'Nickname',
    `Email`    varchar(500)  NOT NULL DEFAULT 'default' COMMENT 'Email',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';



DROP TABLE IF EXISTS `Merchant`;

CREATE TABLE `Merchant`
(
    `Id`                     int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchantName`           varchar(512)  NOT NULL DEFAULT 'default' COMMENT '商家名称',
    `MainCategory`           varchar(32)   NOT NULL DEFAULT 'default' COMMENT '主营类别',
    `ReturnAndExchangeRules` varchar(1024) NOT NULL DEFAULT 'default' COMMENT '退换货政策',
    `Introduction`           varchar(512)  NOT NULL DEFAULT 'default' COMMENT '商家描述',
    `LogisticalArea`         varchar(32)   NOT NULL DEFAULT 'default' COMMENT '物流发货地区',
    `IsLogisticsSpecifiable` bit(1)        NOT NULL DEFAULT b'0' COMMENT '物流品牌是否可指定 1: specifiable, 0: not',

    `Username`               varchar(64)   NOT NULL DEFAULT 'default' COMMENT 'Username',
    `Password`               varchar(512)  NOT NULL DEFAULT 'default' COMMENT 'Password',
    `Nickname`               varchar(64)   NOT NULL DEFAULT 'default' COMMENT 'Nickname',
    `Email`                  varchar(500)  NOT NULL DEFAULT 'default' COMMENT 'Email',

    `InitialMsg`             varchar(64)   NOT NULL DEFAULT 'default' COMMENT '问候语句',
    `Theme`                  varchar(32)   NOT NULL DEFAULT 'default' COMMENT 'Theme',
    `BotName`                varchar(64)   NOT NULL DEFAULT 'default' COMMENT '客服Bot名字',
    `ChatStyle`              varchar(32)   NOT NULL DEFAULT 'default' COMMENT '聊天风格',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家表';

INSERT INTO Merchant (Id, MerchantName, MainCategory, ReturnAndExchangeRules, Introduction,
                      LogisticalArea, IsLogisticsSpecifiable, Username, Password, Nickname, Email,
                      InitialMsg, Theme, BotName, ChatStyle)
VALUES (1, '测试商店', '', '', '', '', '', 'test', '123456', '店家', '', '', '', '', '');

CREATE TABLE  `CustomerMerchant`
(
    `Id`         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `ChatId`    varchar(128) NOT NULL DEFAULT 'default' COMMENT '会话唯一ID，由',
    `Histories`    varchar(16000) NOT NULL DEFAULT 'default' COMMENT 'History dict',
    `CustomerId` int(10) unsigned NOT NULL COMMENT 'index of customer',
    `MerchantId` int(10) unsigned NOT NULL COMMENT 'index of merchant',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户商家关联表';

DROP TABLE IF EXISTS `PresetQnA`;

CREATE TABLE `PresetQnA`
(
    `Id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchantId` int(10) unsigned NOT NULL COMMENT '对应商家Id',
    `Q`             varchar(128) NOT NULL DEFAULT 'default' COMMENT 'question',
    `A`             varchar(128) NOT NULL DEFAULT 'default' COMMENT 'answer',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预设问题表';


DROP TABLE IF EXISTS `Merchandise`;

CREATE TABLE `Merchandise`
(
    `Id`              int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchantId`      int(10) unsigned NOT NULL COMMENT 'key of merchant',
    `MerchandiseName` varchar(512)  NOT NULL DEFAULT 'default' COMMENT '商品名',
    `Targeting`       varchar(32)   NOT NULL DEFAULT 'default' COMMENT '目标群体',
    `Strategy`        varchar(512) NOT NULL DEFAULT 'default' COMMENT '优惠策略',
    `Price`           varchar(32)   NOT NULL DEFAULT 'default' COMMENT '价格',
    `Description`     varchar(512)  NOT NULL DEFAULT 'default' COMMENT '商品信息',
    `Color`           varchar(16)   NOT NULL DEFAULT 'default' COMMENT '颜色',
    PRIMARY KEY (`Id`),
    KEY               `IX_MERCHANT_ID` (`MerchantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';


DROP TABLE IF EXISTS `MerchandiseAttr`;

CREATE TABLE `MerchandiseAttr`
(
    `Id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MerchandiseId` int(10) unsigned NOT NULL COMMENT 'index of merchandise',
    `Attr`           text NOT NULL DEFAULT 'default' COMMENT 'Url',
    PRIMARY KEY (`Id`),
    KEY             `IX_MERCHANDISE_ID` (`MerchandiseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';


-- DROP TABLE IF EXISTS `SizeRecommend`;
--
-- CREATE TABLE `SizeRecommend`
-- (
--     `Id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
--     `MerchandiseId` int(10) unsigned NOT NULL COMMENT 'index of merchandise',
--     `SizeName`      varchar(16)   NOT NULL DEFAULT 'default' COMMENT 'SizeName',
--     `Recommends`    varchar(1024) NOT NULL DEFAULT 'default' COMMENT 'Recommends',
--     PRIMARY KEY (`Id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品尺码表';

--
-- DROP TABLE IF EXISTS `CustomerServiceBotSetting`;
--
-- CREATE TABLE `CustomerServiceBotSetting`
-- (
--     `Id`         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
--     `MerchantId` int(10) unsigned NOT NULL COMMENT 'index of merchant',
--     `InitialMsg` varchar(64)   NOT NULL DEFAULT 'default' COMMENT 'InitialMsg',
--     `PresetQnA`  varchar(8000) NOT NULL DEFAULT 'default' COMMENT 'PresetQnA',
--     `Theme`      varchar(32)   NOT NULL DEFAULT 'default' COMMENT 'Theme',
--     `BotName`    varchar(64)   NOT NULL DEFAULT 'default' COMMENT 'BotName',
--     `ChatStyle`  varchar(32)   NOT NULL DEFAULT 'default' COMMENT 'ChatStyle',
--     PRIMARY KEY (`Id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品尺码表';


DROP TABLE IF EXISTS `ChatSession`;

CREATE TABLE `ChatSession`
(
    `Id`         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `CustomerId` int(10) unsigned NOT NULL COMMENT 'index of customer',
    `MerchantId` int(10) unsigned NOT NULL COMMENT 'index of merchant',
    `Histories`    varchar(16000) NOT NULL DEFAULT 'default' COMMENT 'History dict',
    PRIMARY KEY (`Id`),
    KEY          `IX_UNION_ID` (`CustomerId`, `MerchantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='与商家会话表';

