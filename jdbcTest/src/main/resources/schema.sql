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
    `tags`         json                                   DEFAULT NULL,
    `created_by`   bigint                                 DEFAULT NULL,
    `gmt_create`   datetime(6)                            DEFAULT NULL,
    `gmt_modified` datetime(6)                            DEFAULT NULL,
    `modified_by`  bigint                                 DEFAULT NULL,
    `version`      bigint                                 DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `jdbc_book_attr`
(
    `book_id` bigint not NULL,
    `attr_id` bigint not NULL,
    `name`    varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`book_id`, `attr_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `jdbc_book_attr_value`
(
    `book_id`       bigint not NULL,
    `attr_id`       bigint not NULL,
    `attr_value_id` bigint not NULL,
    `name`          varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`book_id`, `attr_id`, `attr_value_id`)
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