--  比特币行情项目

--  各个平台的比特币价格
CREATE TABLE `coin_price` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `huobi` DECIMAL(10,2) DEFAULT NULL COMMENT '火币网价格',
  `okex` DECIMAL(10,2) DEFAULT NULL COMMENT 'okex价格',
  `bithumb` DECIMAL(10,2) DEFAULT NULL COMMENT 'bithumb价格',

  `percent` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '最大价差百分比',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='价格行情表';

--  各个平台账号
CREATE TABLE `coin_account` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `app_key` varchar(128) NOT NULL COMMENT 'key',
  `app_secret` varchar(128) NOT NULL COMMENT '签名私钥',

  `platform` tinyint(2) NOT NULL DEFAULT '1' COMMENT '平台类型：1:火币网 ',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效 0：无效',
  `comment` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台账号表';


