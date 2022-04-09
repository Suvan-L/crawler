# 创建表
CREATE TABLE `crawler_company_shorter` (
     `id` int NOT NULL AUTO_INCREMENT COMMENT 'id（自增主键）',
     `name` varchar(50) NOT NULL COMMENT '[必填] 公司简称',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司简称（词库）';


# 创建表
CREATE TABLE `crawler_company_list` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'id（自增主键）',
    `ent_name` varchar(50) NOT NULL COMMENT '[必填] 公司名',
    `uni_scid` varchar(80) NOT NULL COMMENT '[必填] 统一社会信用代码',
    `reg_no` varchar(80) NULL COMMENT '[选填] 注册号',
    `le_rep` varchar(50) NULL COMMENT '[选填] 法定代表人',
    `est_date` timestamp NULL COMMENT '[选填] 成立日期',
    `op_state_ccn` varchar(50) NULL COMMENT '[选填] 企业状态',
    `reg_capital` decimal(16, 2) NULL COMMENT '[选填] 注册资金（2 位小数点）（万元为单位）',
    `re_capital_coin` varchar(10) NULL COMMENT '[选填] 注册资金单位(例：人名币元)',
    `link` varchar(200) NULL COMMENT '[选填] 公司详情页 url',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业工商信息列表';
CREATE UNIQUE INDEX index_uni_scid ON crawler_company_list(uni_scid);




