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
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `name`         varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `created_by`   bigint                                 DEFAULT NULL,
    `gmt_create`   datetime(6)                            DEFAULT NULL,
    `gmt_modified` datetime(6)                            DEFAULT NULL,
    `modified_by`  bigint                                 DEFAULT NULL,
    `version`      bigint                                 DEFAULT NULL,
    PRIMARY KEY (`id`),
    index idx_name (`name`)
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
    `type`     varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;