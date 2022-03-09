CREATE DATABASE LOGOS;
-- DROP DATABASE LOGOS;
USE LOGOS;

CREATE TABLE `Category`(
`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`code` VARCHAR(255) UNIQUE NOT NULL,
`study_guide` LONGTEXT,
`description` LONGTEXT,
`status` VARCHAR(255), -- Trocar para enum
`order` INT,
`image_url` VARCHAR(255),
`color_code` VARCHAR(7)
);

CREATE TABLE `Subcategory`(
`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`code` VARCHAR(255) UNIQUE NOT NULL,
`study_guide` LONGTEXT,
`description` LONGTEXT,
`status` VARCHAR(255), -- trocar para enum
`order` INT,
`fk_category` BIGINT NOT NULL,
FOREIGN KEY(`fk_category`) REFERENCES `Category`(`id`)
);

CREATE TABLE `Course`(
`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`code` VARCHAR(255) UNIQUE NOT NULL,
`estimated_time` INT NOT NULL,
`visibility`BOOLEAN,
`target_audience` LONGTEXT,
`instructor_name` LONGTEXT NOT NULL,
`course_program_description` LONGTEXT,
`developed_skills` LONGTEXT,
`fk_subcategory`  BIGINT NOT NULL,
FOREIGN KEY(`fk_subcategory`) REFERENCES `Subcategory`(`id`)
);

CREATE TABLE `Section`(
`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`code` VARCHAR(255) UNIQUE NOT NULL,
`order` INT,
`active` BOOLEAN,
`test` BOOLEAN,
`fk_course`  BIGINT NOT NULL,
FOREIGN KEY(`fk_course`) REFERENCES `Course`(`id`)
);

CREATE TABLE `Explanation`(
`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(255) NOT NULL,
`code` VARCHAR(255) UNIQUE NOT NULL,
`active` BOOLEAN,
`order` INT,
`description`LONGTEXT NOT NULL,
`fk_section_explanation`  BIGINT NOT NULL,
FOREIGN KEY(`fk_section_explanation`) REFERENCES `Section`(`id`)
);


CREATE TABLE `Video`(
`id`BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(255) NOT NULL,
`code` VARCHAR(255) UNIQUE  NOT NULL,
`active` BOOLEAN,
`order` INT,
`url` LONGTEXT NOT NULL,
`duration_in_minutes` INT,
`transcription` LONGTEXT,
`fk_section_video`  BIGINT NOT NULL,
FOREIGN KEY (`fk_section_video`) REFERENCES `Section`(`id`)
);

CREATE TABLE `Question`(
`id` BIGINT NOT  NULL PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(255) NOT NULL,
`code`  VARCHAR(255) UNIQUE NOT NULL,
`active` BOOLEAN,
`order` INT,
`description`LONGTEXT NOT NULL,
`type` VARCHAR(30), -- Trocar pra enum
`fk_section_question` BIGINT NOT NULL,
FOREIGN KEY(`fk_section_question`) REFERENCES `Section`(`id`)
);

CREATE TABLE `Alternative`(
`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`description` LONGTEXT NOT NULL,
`order` INT,
`correct` BOOLEAN, -- Testar o valor default
`explanation_answer` LONGTEXT,
`fk_question` BIGINT NOT NULL,
FOREIGN KEY(`fk_question`) REFERENCES `Question`(`id`)
);
