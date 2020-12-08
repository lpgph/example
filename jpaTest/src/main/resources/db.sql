use ant;

-- -- -------------------------------------------------------------公用表开始------------------------------------------------------

DROP TABLE IF EXISTS cc_product_event;
CREATE TABLE `cc_product_event`
(
    `id`         varchar(36) NOT NULL COMMENT '自增id',
    `content`    json        NOT NULL COMMENT 'json内容',
    `state`    json        NOT NULL COMMENT 'json内容',
    `gmt_create` DATETIME    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='产品服务事件表';

DROP TABLE IF EXISTS cc_product_event_recording;
CREATE TABLE `cc_product_event_recording`
(
    `id`         varchar(36) NOT NULL COMMENT '自增id',
    `content`    json        NOT NULL COMMENT 'json内容',
    `gmt_create` DATETIME    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='产品服务事件表记录表';


DROP TABLE IF EXISTS cc_continent;
CREATE TABLE `cc_continent`
(
    `id`           int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`         varchar(16)     NOT NULL COMMENT '英文名',
    `cname`        varchar(16)     NOT NULL COMMENT '中文名',
    `code`         varchar(64)     NOT NULL DEFAULT '' COMMENT '英文缩写名称，全大写',
    `gmt_create`   DATETIME        NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME        NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='通用-大洲表';

DROP TABLE IF EXISTS cc_country;
CREATE TABLE `cc_country`
(
    `id`           int(8) unsigned     NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `continent_id` int(8) UNSIGNED     NOT NULL COMMENT '对应七大陆continent表的id',
    `name`         varchar(256)        NOT NULL COMMENT '英文常用标准名称，主要用于显示',
    `cname`        varchar(256)        NOT NULL COMMENT '中文常用标准名称，通常简称',
    `code`         varchar(64)         NOT NULL DEFAULT '' COMMENT '英文缩写名称，全大写',

    `is_deleted`   TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',
    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='通用-国家表';


DROP TABLE IF EXISTS cc_region;
CREATE TABLE `cc_region`
(
    `id`           int(11) UNSIGNED    NOT NULL AUTO_INCREMENT,
    `country_id`   int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '国家ID',

    `name`         varchar(256)        NOT NULL COMMENT '地区英文名称',
    `cname`        varchar(256)        NOT NULL COMMENT '地区中文名称',
    `code`         varchar(64)         NOT NULL COMMENT '地区代码',

    `is_parent`    TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是父级 0 否 1 是',
    `parent_id`    int(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '父级地区ID',

    `is_deleted`   TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',
    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='通用-地区表';



DROP TABLE IF EXISTS cc_picture_category;
CREATE TABLE `cc_picture_category`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
    `cat_name`     VARCHAR(250)        NOT NULL COMMENT '分类名称',
    `parent_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级分类ID，0为顶级分类',
    `cat_path`     mediumtext          NOT NULL COMMENT '分类路径',
    `sort`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',

    `is_deleted`   TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',
    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '图片分类';


DROP TABLE IF EXISTS cc_picture;
CREATE TABLE `cc_picture`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增长id',
    `pic_title`     VARCHAR(250)        NOT NULL COMMENT '图片文件名称',
    `pic_cat_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片分类',
    `pic_path`      VARCHAR(512)        NOT NULL COMMENT '图片路径',
    `full_url`      VARCHAR(512)        NOT NULL COMMENT '图片完整路径',
    `size`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片大小,bite单位',
    `pixel`         VARCHAR(100)        NOT NULL COMMENT '图片相素,格式:长x宽，如450x150',

    `is_referenced` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否被引用 0 否 1 是',
    `is_deleted`    TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',

    `gmt_create`    DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`  DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB

  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '图片文件';



DROP TABLE IF EXISTS cc_brand;
CREATE TABLE `cc_brand`
(
    `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,

    `brand_code`     VARCHAR(50)         NOT NULL COMMENT '品牌编号',

    `brand_name`     VARCHAR(50)         NOT NULL COMMENT '品牌名称',
    `cn_name`        VARCHAR(50)         NOT NULL COMMENT '中文名称',
    `en_name`        VARCHAR(50)         NOT NULL COMMENT '英文名称',

    `country_id`     int(8) UNSIGNED     NOT NULL COMMENT '品牌国家',

    `logo_url`       VARCHAR(512)        NOT NULL DEFAULT '' COMMENT 'logo URL',

    `sort`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`         VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `is_deactivated` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已停用 0 否 1 是',
    `is_deleted`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',

    `gmt_create`     DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`   DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '品牌';



DROP TABLE IF EXISTS cc_property_group;
CREATE TABLE `cc_property_group`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `group_name`   VARCHAR(100)        NOT NULL COMMENT '属性值组名称',
    `sort`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`       VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',
    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '属性组';

DROP TABLE IF EXISTS cc_property;
CREATE TABLE `cc_property`
(
    `id`                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `prop_name`            VARCHAR(100)        NOT NULL COMMENT '属性名称，不可以重复',
    `alias_name`           VARCHAR(100)        NOT NULL COMMENT '属性别名，正式展示名称',

    `prop_group_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT '属性组ID',

    `is_exist_value`       TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否存在值 0 否 1 是',
    `is_exist_value_group` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否存在值组 0 否 1 是, 存在属性值时有效',

    `sort`                 BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`               VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `is_deactivated`       TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已停用 0 否 1 是',
    `is_deleted`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',

    `gmt_create`           DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`         DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '属性项';


DROP TABLE IF EXISTS cc_property_value_group;
CREATE TABLE `cc_property_value_group`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `prop_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '属性ID',
    `group_name`   VARCHAR(100)        NOT NULL COMMENT '属性值组名称',
    `sort`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`       VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',
    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '属性值组';

DROP TABLE IF EXISTS cc_property_value;
CREATE TABLE `cc_property_value`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `prop_id`             BIGINT(20) UNSIGNED NOT NULL COMMENT '属性项ID',
    `prop_value_group_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性值组ID',

    `value_name`          VARCHAR(100)        NOT NULL COMMENT '属性值名称',
    `alias_name`          VARCHAR(100)        NOT NULL COMMENT '属性值别名 默认等于属性值名称',

    `sort`                BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`              VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `is_deactivated`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已停用 0 否 1 是',
    `is_deleted`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',

    `gmt_create`          DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`        DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `uk_pid_name` (`prop_id`, `value_name`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '属性值';


-- -- -------------------------------------------------------------公用表结束------------------------------------------------------

-- -------------------------------------------------------------类目属性表开始------------------------------------------------------

DROP TABLE IF EXISTS cc_category;
CREATE TABLE `cc_category`
(
    `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `cat_name`       VARCHAR(50)         NOT NULL COMMENT '分类名称',

    `is_parent`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否为父级类目 0 否 1 是, 用来判断是否是叶子类目',
    `parent_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级分类ID，0为顶级分类',
    `cat_path`       mediumtext          NOT NULL COMMENT '分类路径',
    `cat_depth`      INT(11) UNSIGNED    NOT NULL COMMENT '节点深度，等于上级节点深度+1',

    `sort`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`         VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `is_deactivated` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已停用 0 否 1 是',
    `is_deleted`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',
    `gmt_create`     DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`   DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '分类';



DROP TABLE IF EXISTS cc_category_feature;
CREATE TABLE `cc_category_feature`
(
    `cat_id`       BIGINT(20) UNSIGNED NOT NULL COMMENT '属性ID',
    `attr_key`     tinyint(4)          NOT NULL COMMENT '属性键',
    `attr_value`   VARCHAR(50)         NOT NULL COMMENT '属性值',

    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_cid_key` (`cat_id`, `attr_key`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '类目特殊属性';


DROP TABLE IF EXISTS cc_category_brand;
CREATE TABLE `cc_category_brand`
(
    `cat_id`       BIGINT(20) UNSIGNED NOT NULL COMMENT '分类ID',
    `brand_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '品牌ID',
    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_cid_bid` (`cat_id`, `brand_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '分类-关联-品牌';

-- -------------------------------------------------------------属性相关表开始------------------------------------------------------


DROP TABLE IF EXISTS cc_category_property;
CREATE TABLE `cc_category_property`
(
    `cat_id`           BIGINT(20) UNSIGNED NOT NULL COMMENT '分类ID',
    `prop_id`          BIGINT(20) UNSIGNED NOT NULL COMMENT '属性ID',

    `parent_pid`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级属性ID',
    `parent_vid`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级属性值ID',

    `is_must`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是必须: 0否 1是',

    `prop_type`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性类型 0.非关键属性 1.关键属性 2.商品属性 3.销售属性',

    `is_enum_prop`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是枚举属性: 0否 1是',
    `is_enum_multiple` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '枚举是否是多选: 0否 1是 ',
    `is_enum_input`    TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '枚举是否可输入: 0否 1是',

#     `input_type`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入方式: 0.输入 1.枚举',
#     `is_enum_multiple` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '枚举是否是多选: 0否 1是',
#     `input_format` TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入格式 输入: 0 文本 1 数字 2 年份 3 月份 4 日期 5 日期时间;',

#     `input_type`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入方式: 0.输入 1.枚举 ',
#     `input_format` TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入格式 输入: 0 文本 1 数字 2 年份 3 月份 4 日期 5 日期时间; 枚举: 0 单选 1 多选 2 单选可输入 3 多选可输入',

#     `input_type`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入方式: 0.输入 1.单选 2 多选 3 单选可输入 4 多选可输入 ',

#     `input_type`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入方式: 0.输入 1.单选 2 多选 ',
#     `is_input`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可输入: 0否 1是, 在类型是单选或多选前提下',

#     `input_type`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入方式: 0.输入 1.单选 2 多选 ',
#     `is_input`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可输入: 0否 1是, 单选多选有效',
#     `input_format` TINYINT(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '录入格式 输入: 0 文本 1 数字 2 年份 3 月份 4 日期 5 日期时间;',

    `sort`             BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`           VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `is_deactivated`   TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已停用 0 否 1 是',
    `is_deleted`       TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',

    `gmt_create`       DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`     DATETIME            NOT NULL COMMENT '最后修改时间',

    PRIMARY KEY `pk_cid_pid` (`cat_id`, `prop_id`)

) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '分类-属性';

DROP TABLE IF EXISTS cc_category_property_feature;
CREATE TABLE `cc_category_property_feature`
(
    `cat_id`       BIGINT(20) UNSIGNED NOT NULL COMMENT '分类ID',
    `prop_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '属性ID',

    `attr_key`     tinyint(4)          NOT NULL COMMENT '属性键',
    `attr_value`   VARCHAR(50)         NOT NULL COMMENT '属性值',

    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_cid_pid_key` (`cat_id`, `prop_id`, `attr_key`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '规格模板-属性项-特殊属性';


DROP TABLE IF EXISTS cc_category_property_value;
CREATE TABLE `cc_category_property_value`
(
    `cat_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT '分类ID',
    `prop_id`       BIGINT(20) UNSIGNED NOT NULL COMMENT '属性ID',
    `prop_value_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '属性值ID',
    `sort`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`        VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',
    `gmt_create`    DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`  DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_cid_pid_pvid` (`cat_id`, `prop_id`, `prop_value_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '分类属性属性值';



-- -------------------------------------------------------------属性相关表结束------------------------------------------------------


-- -------------------------------------------------------------产品表开始------------------------------------------------------

DROP TABLE IF EXISTS cc_product;
CREATE TABLE `cc_product`
(
    `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `cat_id`         BIGINT(20) UNSIGNED NOT NULL COMMENT '类目ID',
    `title`          VARCHAR(512)        NOT NULL COMMENT '标题',
    `cost_price`     BIGINT(20) UNSIGNED NOT NULL COMMENT '成本价',
    `market_price`   BIGINT(20) UNSIGNED NOT NULL COMMENT '市场价',

    `brand_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '品牌ID',
    `brand_name`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '品牌名称',

    `product_type`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发布类型  0 一口价 1 竞价  预留字段',
    `freight_payer`  TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '运费承担方式  0 买家承担 1 卖家承担  预留字段',

    `location_state` VARCHAR(255)        NOT NULL COMMENT '所在地省份  预留字段',
    `location_city`  VARCHAR(255)        NOT NULL COMMENT '所在地城市 预留字段',
    `sell_point`     VARCHAR(255) COMMENT '卖点 预留字段',

    `num`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '产品数量，为所有sku数量之和',
    `description`    LONGTEXT            NOT NULL COMMENT '产品描述',

    `barcode`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '产品条码',
    `outer_id`       VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '商家编码',

    `gmt_up`         DATETIME                     DEFAULT NULL COMMENT '定时上架时间',
    `gmt_down`       DATETIME                     DEFAULT NULL COMMENT '定时下架时间',

    `sort`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`         VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `is_deactivated` TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否下架  0 上架 1 下架',
    `is_deleted`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',
    `gmt_create`     DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`   DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品';

DROP TABLE IF EXISTS cc_product_picture;
CREATE TABLE `cc_product_picture`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '图片id',
    `product_id`   BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `pic_url`      VARCHAR(512)        NOT NULL COMMENT '图片URL地址',
    `pic_position` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片序号 产品里的图片展示顺序，数据越小越靠前。要求是正整数。',
    `is_major`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是主图 0 否 1 是',
    `gmt_create`   DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品-图片';

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
CREATE TABLE `cc_product_property`
(
    `product_id`            BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',

    `prop_id`               BIGINT(20)          NOT NULL COMMENT '属性项ID, 自定义属性ID为负',
    `input_prop_name`       VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '自定义属性名称',

    `prop_value_id`         BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '属性值ID，自定义属性值ID为负',
    `input_prop_value_name` VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '自定义属性值名称或属性值别称',

    `is_custom`             TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是自定义属性 0 否 1 是',
    `is_spec`               TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是规格 0 否 1 是',

    `sort`                  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`                VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `gmt_create`            DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`          DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_iid_pid_pvid` (`product_id`, `prop_id`, `prop_value_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品属性';


DROP TABLE IF EXISTS cc_product_property_picture;
CREATE TABLE `cc_product_property_picture`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '图片id',

    `product_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `prop_id`       BIGINT(20)          NOT NULL COMMENT '属性项ID',
    `prop_value_id` BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '属性值ID',

    `pic_url`       VARCHAR(512)        NOT NULL COMMENT '图片URL地址',
    `pic_position`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片序号 产品里的图片展示顺序，数据越小越靠前。要求是正整数。',
    `gmt_create`    DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`  DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `pk_iid_pid_pvid` (`product_id`, `prop_id`, `prop_value_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品-属性图片';

DROP TABLE IF EXISTS cc_product_sku;
CREATE TABLE `cc_product_sku`
(
    `id`              BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `quantity`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数量',
    `price`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售价',
    `commission_rate` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分佣比例',

    `barcode`         VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '产品条码',
    `outer_id`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '商家编码',

    `sort`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
    `remark`          VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '备注',

    `is_deactivated`  TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否无效  0 有效 1 无效',
    `is_deleted`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除 0 否 1 是',

    `gmt_create`      DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`    DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品规格';


DROP TABLE IF EXISTS cc_product_sku_property;
CREATE TABLE `cc_product_sku_property`
(
    `product_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',

    `product_sku_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '产品SKU ID',
    `prop_id`        BIGINT(20)          NOT NULL COMMENT '属性项ID',
    `prop_value_id`  BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '属性值ID',

    `gmt_create`     DATETIME            NOT NULL COMMENT '创建时间',
    `gmt_modified`   DATETIME            NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY `pk_psid_pid_pvid` (`product_sku_id`, `prop_id`, `prop_value_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '产品sku属性';

# DROP TABLE IF EXISTS cc_product_sku_property;
# CREATE TABLE `cc_product_sku_property`
# (
#     `product_sku_id`  BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
#     `product_prop_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '产品属性ID',
#     `gmt_create`      DATETIME            NOT NULL COMMENT '创建时间',
#     `gmt_modified`    DATETIME            NOT NULL COMMENT '最后修改时间',
#     PRIMARY KEY (`product_sku_id`, `product_prop_id`)
# ) ENGINE = INNODB
#
#   DEFAULT CHARSET = utf8mb4
#   COLLATE = utf8mb4_unicode_ci COMMENT '产品sku属性';


-- -------------------------------------------------------------产品相关表结束------------------------------------------------------


-- -------------------------------------------------------------其他表结束------------------------------------------------------

#
# DROP TABLE IF EXISTS cc_virtual_category;
# CREATE TABLE `cc_virtual_category`
# (
#     `id`           BIGINT(20) UNSIGNED  NOT NULL AUTO_INCREMENT,
#     `cat_name`     VARCHAR(50)          NOT NULL COMMENT '分类名称',
#     `is_parent`    TINYINT(1) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '是否为父级类目 0 否 1 是, 用来判断是否是叶子类目',
#     `parent_id`    BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '父级分类ID，0为顶级分类',
#     `path`         mediumtext           NOT NULL COMMENT '分类路径',
#     `sort`         BIGINT(20)  UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序权重',
#     `remark`       VARCHAR(255)         NOT NULL DEFAULT '' COMMENT '备注',
#
#     `is_deleted`   TINYINT(1) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '是否已删除  0 否 1 是',
#     `gmt_create`   DATETIME             NOT NULL COMMENT '创建时间',
#     `gmt_modified` DATETIME             NOT NULL COMMENT '最后修改时间',
#     PRIMARY KEY `pk_id` (`id`)
# ) ENGINE = INNODB
#
#   DEFAULT CHARSET = utf8mb4
#   COLLATE = utf8mb4_unicode_ci COMMENT '虚拟分类';
#
#
# DROP TABLE IF EXISTS cc_virtual_category_to_category;
# CREATE TABLE `cc_virtual_category_to_category`
# (
#     `virtual_cat_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '虚拟类目ID',
#     `cat_id`         BIGINT(20) UNSIGNED NOT NULL COMMENT '后台类目ID',
#     PRIMARY KEY `pk_id` (`virtual_cat_id`, `cat_id`)
# ) ENGINE = INNODB
#
#   DEFAULT CHARSET = utf8mb4
#   COLLATE = utf8mb4_unicode_ci COMMENT '虚拟分类和分类关联';
