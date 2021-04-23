CREATE TABLE `jdbc_user`
(
    `id`           bigint                                 NOT NULL AUTO_INCREMENT,
    `name`         varchar(50) COLLATE utf8mb4_general_ci not NULL,
    `created_by`   bigint                                 not NULL,
    `gmt_create`   datetime                               not NULL,
    `gmt_modified` datetime                               not NULL,
    `modified_by`  bigint                                 not NULL,
    `version`      bigint                                 not null,
    `is_delete`    tinyint(1) default 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;



CREATE TABLE `jdbc_book`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `name`          varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `state`         tinyint                                DEFAULT NULL,

    `created_by`    bigint                                 DEFAULT NULL,
    `created_date`  datetime                               DEFAULT NULL,
    `modified_date` datetime                               DEFAULT NULL,
    `modified_by`   bigint                                 DEFAULT NULL,
    `version`       bigint                                 DEFAULT NULL,
    PRIMARY KEY (`id`),
    index idx_name (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE `jdbc_book_attr`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `book_id` bigint not NULL,
    `prop_id` bigint not NULL,
    `name`    varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    unique INDEX (`book_id`, `prop_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `jdbc_book_attr_value`
(
    `book_attr_id`  bigint not NULL,
    `prop_value_id` bigint not NULL,
    PRIMARY KEY (`book_attr_id`, `prop_value_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE `jdbc_book_info`
(
    `book_id` bigint         not NULL,
    `ad`      varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `price`   decimal(30, 2) NOT NULL                DEFAULT 0,
    PRIMARY KEY (`book_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `jdbc_book_tag`
(
    `book_id` bigint      not NULL,
    `name`    varchar(50) not NULL default ''
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE `jdbc_book_auditor`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT,
    `book_id`     bigint   not NULL,
    `event_type`  tinyint  not NULL default 0,
    `old_value`   json     NULL     default null,
    `new_value`   json     NULL     default null,
    `user_id`     bigint   not NULL,
    `create_date` datetime not NULL,
    PRIMARY KEY (`id`),
    INDEX idx_book (`book_id`, `create_date`),
    INDEX idx_user (`user_id`, `create_date`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `jdbc_user_book`
(
    `user_id`    bigint   not NULL,
    `book_id`    bigint   not NULL,
    `gmt_create` datetime not NULL,
    PRIMARY KEY (`user_id`, `book_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `jdbc_event_stored`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT,
    `event_body`  json                                             DEFAULT NULL,
    `event_id`    varchar(32) COLLATE utf8mb4_general_ci  NOT NULL,
    `gmt_create`  datetime                                NOT NULL,
    `gmt_sender`  datetime                                         DEFAULT NULL,
    `event_state` int                                     NOT NULL DEFAULT '0',
    `type`        varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;




CREATE TABLE `resource_server_policy`
(
    `id`            bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长id,委派标识',
    `client_id`     bigint unsigned NOT NULL COMMENT '客户端ID',
    `name`          varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
    `description`   varchar(255)    NOT NULL DEFAULT '' COMMENT '描述',
    `type`          tinyint         NOT NULL DEFAULT 0 COMMENT '策略类型 0 角色 1 客户端 2 时间 3 用户 4 用户组',

    `created_by`     bigint unsigned NOT NULL default 0 COMMENT '创建人',
    `created_date`  DATETIME        NOT NULL COMMENT '创建时间',
    `modified_by`   bigint unsigned NOT NULL default 0 COMMENT '修改人',
    `modified_date` DATETIME        NOT NULL COMMENT '修改时间',
    `version`       bigint unsigned NOT NULL DEFAULT 0 COMMENT '版本号',
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_name (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '资源服务授权策略';

CREATE TABLE `policy_role`
(
    `policy_id`   bigint unsigned NOT NULL COMMENT '授权策略ID',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色ID',
    `is_required` bit(1)          NOT NULL DEFAULT b'0' COMMENT '是否必须 存在一个或多个角色的时候 用户或客户端作用域必须同时拥有该角色',
    PRIMARY KEY (`policy_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '角色授权策略';

CREATE TABLE `policy_user`
(
    `policy_id` bigint unsigned NOT NULL COMMENT '授权策略ID',
    `user_id`   bigint unsigned NOT NULL COMMENT '用户ID',
    PRIMARY KEY (`policy_id`, `user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '用户授权策略';