use ant;

-- -- -------------------------------------------------------------公用表开始------------------------------------------------------
-- DROP TABLE IF EXISTS cc_country;
-- CREATE TABLE `cc_country`
-- (
--     `id`          BIGINT(20)           NOT NULL AUTO_INCREMENT,
--     `name`        varchar(50)          NOT NULL COMMENT '名称',
--     `is_enable`   TINYINT(1)           NOT NULL DEFAULT 1 COMMENT '是否启用  0 停用 1 启用',
--     `is_del`      TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
--     `sort`        SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
--     `remark`      VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
--     `create_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
--     `update_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
--     PRIMARY KEY (`id`)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_unicode_ci COMMENT ='通用-国家表';
-- -- -------------------------------------------------------------公用表结束------------------------------------------------------

DROP TABLE IF EXISTS cc_picture;
DROP TABLE IF EXISTS cc_res_picture;
CREATE TABLE `cc_res_picture`
(
    `id`          BIGINT(20)          NOT NULL AUTO_INCREMENT COMMENT '自增长id',
    `name`        VARCHAR(250)        NOT NULL COMMENT '图片名称',
    `path`        VARCHAR(512)        NOT NULL COMMENT '图片路径',
    `full_url`    VARCHAR(512)        NOT NULL COMMENT '图片完整路径',
    `size`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片大小',
    `pixel`       VARCHAR(100)        NOT NULL COMMENT '图片分辨率',
    `is_del`      TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `create_time` DATETIME            NOT NULL COMMENT '创建时间',
    `update_time` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '资源图片';

-- -------------------------------------------------------------产品相关表开始------------------------------------------------------
DROP TABLE IF EXISTS cc_category;
CREATE TABLE `cc_category`
(
    `id`          BIGINT(20)                            NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(50)                           NOT NULL COMMENT '分类名称',
    `prop_tpl_id` BIGINT(20) UNSIGNED                   NOT NULL DEFAULT 0 COMMENT '属性模板ID',
    `is_parent`   TINYINT(1)                            NOT NULL DEFAULT 0 COMMENT '是否为父级类目 0 否 1 是, 用来判断是否是叶子类目',
    `level`       INT(1)                                NOT NULL DEFAULT 1 COMMENT '分类总层级',
    `parent_id`   BIGINT(20) UNSIGNED                   NOT NULL DEFAULT 0 COMMENT '父级分类ID，0为顶级分类',
    `path`        mediumtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类路径',
    `is_enable`   TINYINT(1)                            NOT NULL DEFAULT 1 COMMENT '是否启用  0 停用 1 启用',
    `is_del`      TINYINT(1)                            NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `sort`        SMALLINT(5) UNSIGNED                  NOT NULL DEFAULT '99' COMMENT '排序权重',
    `remark`      VARCHAR(255)                                   DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP                             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP                             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '分类';


DROP TABLE IF EXISTS cc_brand;
CREATE TABLE `cc_brand`
(
    `id`            BIGINT(20)           NOT NULL AUTO_INCREMENT,
--     `cn_name`       VARCHAR(50)          NOT NULL COMMENT '中文名称',
--     `en_name`       VARCHAR(50)          NOT NULL COMMENT '英文名称',

    `prop_id`       BIGINT(20) UNSIGNED  NOT NULL COMMENT '品牌绑定的属性项',
    `prop_value_id` BIGINT(20) UNSIGNED  NOT NULL COMMENT '品牌绑定的属性项值',

    `country_id`    BIGINT(20) UNSIGNED  NOT NULL DEFAULT '1' COMMENT '国家ID',
    `logo_pic`      VARCHAR(512) COMMENT 'logo URL',
    `ad_pic`        VARCHAR(512) COMMENT '广告图 URL',
    `origin`        VARCHAR(50) COMMENT '产地',

    `intro`         VARCHAR(255) COMMENT '简介',
    `is_enable`     TINYINT(1)           NOT NULL DEFAULT 1 COMMENT '是否启用  0 停用 1 启用',
    `is_del`        TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `sort`          SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
    `remark`        VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time`   TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '品牌';


DROP TABLE IF EXISTS cc_brand_category;
CREATE TABLE `cc_brand_category`
(
    `brand_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '品牌ID',
    `cat_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '分类ID',
    `create_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`brand_id`, `cat_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '品牌-关联-分类';


DROP TABLE IF EXISTS cc_product;
CREATE TABLE `cc_product`
(
    `id`             BIGINT(20)           NOT NULL AUTO_INCREMENT,
    `title`          VARCHAR(512)         NOT NULL COMMENT '标题',
    `cost_price`     BIGINT(20) UNSIGNED  NOT NULL COMMENT '成本价',
    `market_price`   BIGINT(20) UNSIGNED  NOT NULL COMMENT '市场价',

    `pic_url`        VARCHAR(255)         NOT NULL COMMENT '产品主图',

    `type`           TINYINT(4)           NOT NULL DEFAULT 0 COMMENT '发布类型  0 一口价 1 竞价  预留字段',
    `freight_payer`  TINYINT(4)           NOT NULL DEFAULT 0 COMMENT '运费承担方式  0 买家承担 1 卖家承担  预留字段',

    `location_state` VARCHAR(255)         NOT NULL COMMENT '所在地省份  预留字段',
    `location_city`  VARCHAR(255)         NOT NULL COMMENT '所在地城市 预留字段',
    `sell_point`     VARCHAR(255) COMMENT '卖点 预留字段',

    `num`            BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '产品数量，为所有sku数量之和',
    `desc`           LONGTEXT             NOT NULL COMMENT '产品描述',
    `cat_id`         BIGINT(20) UNSIGNED  NOT NULL COMMENT '叶子类目ID',

    `barcode`        VARCHAR(100)                  DEFAULT NULL COMMENT '产品条码',
    `outer_id`       VARCHAR(100)                  DEFAULT NULL COMMENT '商家编码',

    `task_time`      DATETIME                      DEFAULT NULL COMMENT '定时时间',

    `status`         TINYINT(4)           NOT NULL DEFAULT 1 COMMENT '产品状态  0 下架 2 上架',
    `is_del`         TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',

    `sort`           SMALLINT(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`         VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time`    TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品';

DROP TABLE IF EXISTS cc_product_picture;

DROP TABLE IF EXISTS cc_product_pic;
CREATE TABLE `cc_product_pic`
(
    `id`          BIGINT(20)           NOT NULL AUTO_INCREMENT COMMENT '图片id',
    `product_id`  BIGINT(20) UNSIGNED  NOT NULL COMMENT '产品ID',
    `url`         VARCHAR(512)         NOT NULL COMMENT '图片URL地址',
    `position`    SMALLINT(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片序号 产品里的图片展示顺序，数据越小越靠前。要求是正整数。',
    `create_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品-图片';


DROP TABLE IF EXISTS cc_product_property_picture;

DROP TABLE IF EXISTS cc_product_prop_pic;
CREATE TABLE `cc_product_prop_pic`
(
    `id`          BIGINT(20)           NOT NULL AUTO_INCREMENT COMMENT '图片id',
    `product_id`  BIGINT(20) UNSIGNED  NOT NULL COMMENT '产品ID',
    `props`       VARCHAR(512)         NOT NULL COMMENT '属性串(pid:vid)',
    `url`         VARCHAR(512)         NOT NULL COMMENT '图片URL地址',
    `position`    SMALLINT(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片序号 产品里的图片展示顺序，数据越小越靠前。要求是正整数。',
    `create_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品-属性图片';

#    &&
#
# prop_id > 0
# 销售类型 关键类型
# 输入属性且不为枚举 prop_id > 0 &&  prop_value = 0
# 枚举  prop_id > 0 && prop_value > 0
# 枚举 && 输入   枚举 + prop_id > 0 && prop_value < 0
#
# 表单提交的时候需要检验数据是否存在
#
# prop_id < 0  不需要做类型判断

DROP TABLE IF EXISTS cc_product_property;

DROP TABLE IF EXISTS cc_product_prop;
CREATE TABLE `cc_product_prop`
(
    `id`               BIGINT(20)           NOT NULL AUTO_INCREMENT,
    `product_id`       BIGINT(20) UNSIGNED  NOT NULL COMMENT '产品ID',

    `prop_id`          BIGINT(20)           NOT NULL COMMENT '属性项ID, 自定义属性ID为负',
    `input_prop_name`  VARCHAR(100)         NOT NULL COMMENT '输入属性名称，属性ID为负',

    `prop_value_id`    BIGINT(20)           NOT NULL DEFAULT 0 COMMENT '属性值ID，自定义属性值ID为负',
    `input_value`      VARCHAR(100)         NOT NULL COMMENT '输入属性值',
    `prop_value_alias` VARCHAR(100)                  DEFAULT NULL COMMENT '属性值别名',

    `sort`             SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
    `remark`           VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `status`           TINYINT(4)           NOT NULL DEFAULT 0 COMMENT '状态  预留字段',
    `is_del`           TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `create_time`      TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品属性';



DROP TABLE IF EXISTS cc_product_specification;
DROP TABLE IF EXISTS cc_product_sku;

CREATE TABLE `cc_product_sku`
(
    `id`              BIGINT(20)           NOT NULL AUTO_INCREMENT,
    `product_id`      BIGINT(20) UNSIGNED  NOT NULL COMMENT '产品ID',
    `quantity`        BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '数量',
    `price`           BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '销售价',
    `commission_rate` BIGINT(20)                    DEFAULT 0 COMMENT '分佣比例',

    `barcode`         VARCHAR(100)                  DEFAULT NULL COMMENT '产品条码',
    `outer_id`        VARCHAR(100)                  DEFAULT NULL COMMENT '商家编码',

    `is_del`          TINYINT(1)           NOT NULL DEFAULT 1 COMMENT '是否删除  0 正常 1 删除',
    `sort`            SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
    `remark`          VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time`     TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品规格';



DROP TABLE IF EXISTS cc_product_sku_property;

DROP TABLE IF EXISTS cc_product_sku_prop;
CREATE TABLE `cc_product_sku_prop`
(
    `product_sku_id`  BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `product_prop_id` BIGINT(20)          NOT NULL COMMENT '产品属性ID',
    `create_time`     TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`product_sku_id`, `product_prop_id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品sku属性';


-- -------------------------------------------------------------产品相关表结束------------------------------------------------------


-- -------------------------------------------------------------属性相关表开始------------------------------------------------------


DROP TABLE IF EXISTS cc_property_template_category;

# CREATE TABLE `cc_property_template_category`
# (
#     `prop_tpl_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '模板ID',
#     `cat_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '分类ID',
#     `create_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
#     `update_time` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
#     PRIMARY KEY (`prop_tpl_id`, `cat_id`)
# ) ENGINE = INNODB
#   AUTO_INCREMENT = 1
#   DEFAULT CHARSET = utf8mb4
#   COLLATE = utf8mb4_unicode_ci COMMENT '规格模板-关联-分类';


DROP TABLE IF EXISTS cc_property_template;
CREATE TABLE `cc_property_template`
(
    `id`                   BIGINT(20)           NOT NULL AUTO_INCREMENT,
    `name`                 VARCHAR(100)         NOT NULL COMMENT '名称',

    `is_allow_custom_prop` TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否允许自定义宝贝属性: 0否 1是',
    `is_allow_custom_spec` TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否允许自定义宝贝规格: 0否 1是',

    `is_enable`            TINYINT(1)           NOT NULL DEFAULT 1 COMMENT '是否启用  0 停用 1 启用',
    `is_del`               TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `sort`                 SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
    `remark`               VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time`          TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`          TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '规格模板';

DROP TABLE IF EXISTS cc_property_template_cat;

--  输入属性   枚举   是否多选

DROP TABLE IF EXISTS cc_property_template_item;

DROP TABLE IF EXISTS cc_property_template_prop;
CREATE TABLE `cc_property_template_prop`
(
    `prop_tpl_id`    BIGINT(20) UNSIGNED  NOT NULL COMMENT '模板ID',
    `prop_id`        BIGINT(20) UNSIGNED  NOT NULL COMMENT '属性ID',

    `parent_pid`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '父级属性ID',
    `parent_vid`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '父级属性值ID',

    `type`           TINYINT(4)           NOT NULL DEFAULT 0 COMMENT '属性类型：0.普通属性,1.颜色属性,2.年份季节,3.年份月份,4.年份日期,5.度量衡,6.材质',
    `is_must`        TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否是必须: 0否 1是',

    `is_enum_prop`   TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否是枚举属性: 0否 1是',
    `is_input_prop`  TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '在is_enum_prop是true的前提下，是否是输入属性: 0否 1是',

    `is_multiple`    TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否是多选: 0否 1是',

    `is_item_prop`   TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否是商品属性: 0否 1是',
    `is_search_prop` TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否是搜索属性: 0否 1是 如果是搜索属性 则是is_must必须的',
    `is_sale_prop`   TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否是销售属性: 0否 1是',
    `is_key_prop`    TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否是关键属性: 0否 1是  非关键属性不包含关键属性、销售属性、用户自定义属性、商品属性;',

    `is_allow_alias` TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否允许别名: 0否 1是 ',

    `order`          SMALLINT(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`         VARCHAR(255)                  DEFAULT NULL COMMENT '备注',

    `is_del`         TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `create_time`    TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`prop_tpl_id`, `prop_id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '规格模板-属性项';

DROP TABLE IF EXISTS cc_property_template_feature;

DROP TABLE IF EXISTS cc_property_template_prop_feature;
CREATE TABLE `cc_property_template_prop_feature`
(
    `prop_tpl_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '模板ID',
    `prop_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '属性ID',
    `attr_key`    VARCHAR(50) DEFAULT NULL COMMENT '属性键',
    `attr_value`  VARCHAR(50) DEFAULT NULL COMMENT '属性值',

#     `is_has_sort`             TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否可以排序: 0否 1是',
#     `is_show_upload_image`    TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否显示图片: 0否 1是',
#     `is_show_search`          TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否显示搜索: 0否 1是',
#     `is_show_remark`          TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否显示备注: 0否 1是',
#
#     `max_length`              INT(11)              NOT NULL DEFAULT 0 COMMENT '最大长度',
#     `mix_length`              INT(1)               NOT NULL DEFAULT 0 COMMENT '最小长度',
#     `max_items`               INT(1)               NOT NULL DEFAULT 0 COMMENT '最大选择数',
#     `max_custom_items`        INT(1)               NOT NULL DEFAULT 0 COMMENT '自定义最大数量',
#     `max_custom_input_length` INT(1)               NOT NULL DEFAULT 0 COMMENT '自定义最大输入长度',
#     `remark_max_length`       INT(1)               NOT NULL DEFAULT 0 COMMENT '备注长度',

    PRIMARY KEY (`prop_tpl_id`, `prop_id`, `attr_key`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '规格模板-属性项-特征';


DROP TABLE IF EXISTS cc_property_template_value_group;


DROP TABLE IF EXISTS cc_property_template_prop_value_group;
CREATE TABLE `cc_property_template_prop_value_group`
(
    `id`          BIGINT(20)           NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(100)         NOT NULL COMMENT '属性值组名称',
    `prop_tpl_id` BIGINT(20) UNSIGNED  NOT NULL COMMENT '模板ID',
    `prop_id`     BIGINT(20) UNSIGNED  NOT NULL COMMENT '属性项ID',
    `parent_id`   BIGINT(20) UNSIGNED  NOT NULL COMMENT '父级属性组',
    `is_parent`   TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否为父级组 0 否 1 是, 用来判断是否是叶子',

    `sort`        SMALLINT(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`      VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '规格模板-属性值组';

DROP TABLE IF EXISTS cc_property_template_item_value;

DROP TABLE IF EXISTS cc_property_template_prop_value;
CREATE TABLE `cc_property_template_prop_value`
(
    `prop_tpl_id`   BIGINT(20) UNSIGNED  NOT NULL COMMENT '模板ID',
    `prop_id`       BIGINT(20) UNSIGNED  NOT NULL COMMENT '属性项ID',
    `prop_group_id` BIGINT(20) UNSIGNED  NOT NULL default 0 COMMENT '属性组ID',
    `prop_value_id` BIGINT(20) UNSIGNED  NOT NULL COMMENT '属性值ID',
    `sort`          SMALLINT(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`        VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time`   TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`prop_tpl_id`, `prop_id`, `prop_value_id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '规格模板-属性项-属性值';



DROP TABLE IF EXISTS cc_property_group;
-- CREATE TABLE `cc_property_group`
-- (
--     `id`          BIGINT(20)           NOT NULL AUTO_INCREMENT,
--     `name`        VARCHAR(100)         NOT NULL COMMENT '名称',
--     `is_enable`   TINYINT(1)           NOT NULL DEFAULT 1 COMMENT '是否启用  0 停用 1 启用',
--     `is_del`      TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
--     `sort`        SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
--     `remark`      VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
--     `create_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
--     `update_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
--     PRIMARY KEY (`id`)
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 1
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_unicode_ci COMMENT '属性组';


DROP TABLE IF EXISTS cc_property_group_item;
-- CREATE TABLE `cc_property_group_item`
-- (
--     `prop_group_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '属性组ID',
--     `prop_id`  BIGINT(20) UNSIGNED NOT NULL COMMENT '属性项ID',
--     `create_time`   TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
--     `update_time`   TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
--     PRIMARY KEY (`prop_group_id`, `prop_id`)
-- ) ENGINE = INNODB
--   AUTO_INCREMENT = 1
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_unicode_ci COMMENT '属性组-属性项关系表';


DROP TABLE IF EXISTS cc_property_item;


DROP TABLE IF EXISTS cc_property;
CREATE TABLE `cc_property`
(
    `id`             BIGINT(20)           NOT NULL AUTO_INCREMENT,
    `name`           VARCHAR(100)         NOT NULL COMMENT '名称',
    `is_enable`      TINYINT(1)           NOT NULL DEFAULT 1 COMMENT '是否启用  0 停用 1 启用',
    `is_del`         TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `sort`           SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
    `remark`         VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time`    TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '属性项';

DROP TABLE IF EXISTS cc_property_value;
CREATE TABLE `cc_property_value`
(
    `id`          BIGINT(20)           NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(100)         NOT NULL COMMENT '属性值名称',
    `name_alias`  VARCHAR(100)         NOT NULL COMMENT '属性值别名 默认等于属性值名称',
    `prop_id`     BIGINT(20) UNSIGNED  NOT NULL COMMENT '属性项ID',
    `is_enable`   TINYINT(1)           NOT NULL DEFAULT 1 COMMENT '是否启用  0 停用 1 启用',
    `is_del`      TINYINT(1)           NOT NULL DEFAULT 0 COMMENT '是否删除  0 正常 1 删除',
    `sort`        SMALLINT(5) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序权重',
    `remark`      VARCHAR(255)                  DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '属性值';

-- -------------------------------------------------------------属性相关表结束------------------------------------------------------


-- -------------------------------------------------------------规格相关表开始------------------------------------------------------
DROP TABLE IF EXISTS cc_specification_template_category;
DROP TABLE IF EXISTS cc_specification_template;
DROP TABLE IF EXISTS cc_template_input_type_condition;
DROP TABLE IF EXISTS cc_specification_template_item;
DROP TABLE IF EXISTS cc_specification_template_item_value;
DROP TABLE IF EXISTS cc_specification_item;
DROP TABLE IF EXISTS cc_specification_value;

-- -------------------------------------------------------------规格相关表结束------------------------------------------------------