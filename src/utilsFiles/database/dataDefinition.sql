CREATE
DATABASE logos;
-- DROP DATABASE logos;
USE
logos;

CREATE TABLE `Category`
(
    `id`              BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`            VARCHAR(255)        NOT NULL,
    `identifier_code` VARCHAR(255) UNIQUE NOT NULL,
    `study_guide`     TEXT,
    `description`     TEXT,
    `status`          ENUM('ACTIVE','DISABLED') DEFAULT 'DISABLED',
    `position`        INT,
    `image_url`       VARCHAR(255),
    `color_code`      VARCHAR(7)
);

CREATE TABLE `Subcategory`
(
    `id`              BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`            VARCHAR(255)        NOT NULL,
    `identifier_code` VARCHAR(255) UNIQUE NOT NULL,
    `study_guide`     TEXT,
    `description`     TEXT,
    `status`          ENUM('ACTIVE','DISABLED') DEFAULT 'DISABLED',
    `position`        INT,
    `category_id`     BIGINT              NOT NULL,
    FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
);

CREATE TABLE `Course`
(
    `id`               BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`             VARCHAR(255)        NOT NULL,
    `identifier_code`  VARCHAR(255) UNIQUE NOT NULL,
    `estimated_time`   INT,
    `visibility`       BOOLEAN DEFAULT FALSE,
    `target_audience`  VARCHAR(255),
    `instructor_name`  VARCHAR(255)        NOT NULL,
    `description`      TEXT,
    `developed_skills` TEXT,
    `subcategory_id`   BIGINT              NOT NULL,
    FOREIGN KEY (`subcategory_id`) REFERENCES `Subcategory` (`id`)
);

CREATE TABLE `Section`
(
    `id`              BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`            VARCHAR(255)        NOT NULL,
    `identifier_code` VARCHAR(255) UNIQUE NOT NULL,
    `position`        INT,
    `active`          BOOLEAN DEFAULT FALSE,
    `test`            BOOLEAN,
    `course_id`       BIGINT              NOT NULL,
    FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`)
);

CREATE TABLE `Explanation`
(
    `id`              BIGINT PRIMARY KEY AUTO_INCREMENT,
    `title`           VARCHAR(255)        NOT NULL,
    `identifier_code` VARCHAR(255) UNIQUE NOT NULL,
    `active`          BOOLEAN DEFAULT FALSE,
    `position`        INT,
    `description`     TEXT                NOT NULL,
    `section_id`      BIGINT              NOT NULL,
    FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`)
);


CREATE TABLE `Video`
(
    `id`                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    `title`               VARCHAR(255)        NOT NULL,
    `identifier_code`     VARCHAR(255) UNIQUE NOT NULL,
    `active`              BOOLEAN DEFAULT FALSE,
    `position`            INT,
    `url`                 VARCHAR(255)        NOT NULL,
    `duration_in_minutes` INT,
    `transcription`       TEXT,
    `section_id`          BIGINT              NOT NULL,
    FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`)
);

CREATE TABLE `Question`
(
    `id`              BIGINT PRIMARY KEY AUTO_INCREMENT,
    `title`           VARCHAR(255)        NOT NULL,
    `identifier_code` VARCHAR(255) UNIQUE NOT NULL,
    `active`          BOOLEAN DEFAULT FALSE,
    `position`        INT,
    `description`     TEXT                NOT NULL,
    `type_question`   ENUM('SINGLE_ANSWER','MULTIPLE_CHOICE','TRUE_OR_FALSE') NOT NULL,
    `section_id`      BIGINT              NOT NULL,
    FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`)
);

CREATE TABLE `Alternative`
(
    `id`                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    `description`        TEXT   NOT NULL,
    `position`           INT,
    `correct`            BOOLEAN DEFAULT FALSE,
    `explanation_answer` VARCHAR(255),
    `question_id`        BIGINT NOT NULL,
    FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`)
);