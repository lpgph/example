CREATE TABLE `people`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `created_by`   bigint                                  DEFAULT NULL,
    `gmt_create`   datetime(6)                             DEFAULT NULL,
    `gmt_modified` datetime(6)                             DEFAULT NULL,
    `modified_by`  bigint                                  DEFAULT NULL,
    `name`         varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `version`      bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `event_stored`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT,
    `event_body`  json                                             DEFAULT NULL,
    `event_id`    varchar(32) COLLATE utf8mb4_general_ci  NOT NULL,
    `gmt_create`  datetime(6)                             NOT NULL,
    `gmt_sender`  datetime(6)                                      DEFAULT NULL,
    `event_state` int                                     NOT NULL DEFAULT '0',
    `type`        varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `user`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `created_by`   bigint                                  DEFAULT NULL,
    `gmt_create`   datetime(6)                             DEFAULT NULL,
    `gmt_modified` datetime(6)                             DEFAULT NULL,
    `modified_by`  bigint                                  DEFAULT NULL,
    `name`         varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `user_state`   int    NOT NULL,
    `version`      bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `book_peoples`
(
    `book_id`   bigint NOT NULL,
    `people_id` bigint NOT NULL,
    PRIMARY KEY (`book_id`, `people_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;