CREATE DATABASE LOGOS;
-- DROP DATABASE LOGOS;
USE LOGOS;

CREATE TABLE `Category`(
`id` BIGINT  PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`identifier_code` VARCHAR(255) UNIQUE NOT NULL,
`study_guide` TEXT,
`description` TEXT,
`status` ENUM('ACTIVE','DISABLED') DEFAULT 'DISABLED',
`position` INT,
`image_url` VARCHAR(255),
`color_code` VARCHAR(7)
);

CREATE TABLE `Subcategory`(
`id` BIGINT  PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`identifier_code` VARCHAR(255) UNIQUE NOT NULL,
`study_guide` TEXT,
`description` TEXT,
`status` ENUM('ACTIVE','DISABLED') DEFAULT 'DISABLED',
`position` INT,
`fk_category` BIGINT NOT NULL,
FOREIGN KEY(`fk_category`) REFERENCES `Category`(`id`)
);

CREATE TABLE `Course`(
`id` BIGINT  PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`identifier_code` VARCHAR(255) UNIQUE NOT NULL,
`estimated_time` INT,
`visibility`BOOLEAN DEFAULT FALSE,
`target_audience` VARCHAR(255),
`instructor_name` VARCHAR(255) NOT NULL,
`description` TEXT,
`developed_skills` TEXT,
`fk_subcategory`  BIGINT NOT NULL,
FOREIGN KEY(`fk_subcategory`) REFERENCES `Subcategory`(`id`)
);

CREATE TABLE `Section`(
`id` BIGINT  PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL,
`identifier_code` VARCHAR(255) UNIQUE NOT NULL,
`position` INT,
`active` BOOLEAN DEFAULT FALSE,
`test` BOOLEAN,
`fk_course`  BIGINT NOT NULL,
FOREIGN KEY(`fk_course`) REFERENCES `Course`(`id`)
);

CREATE TABLE `Explanation`(
`id` BIGINT  PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(255) NOT NULL,
`identifier_code` VARCHAR(255) UNIQUE NOT NULL,
`active` BOOLEAN DEFAULT FALSE,
`position` INT,
`description` TEXT NOT NULL,
`fk_section`  BIGINT NOT NULL,
FOREIGN KEY(`fk_section`) REFERENCES `Section`(`id`)
);


CREATE TABLE `Video`(
`id`BIGINT  PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(255) NOT NULL,
`identifier_code` VARCHAR(255) UNIQUE  NOT NULL,
`active` BOOLEAN DEFAULT FALSE,
`position` INT,
`url` VARCHAR(255) NOT NULL,
`duration_in_minutes` INT,
`transcription` TEXT,
`fk_section`  BIGINT NOT NULL,
FOREIGN KEY (`fk_section`) REFERENCES `Section`(`id`)
);

CREATE TABLE `Question`(
`id` BIGINT  PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(255) NOT NULL,
`identifier_code`  VARCHAR(255) UNIQUE NOT NULL,
`active` BOOLEAN DEFAULT FALSE,
`position` INT,
`description` TEXT NOT NULL,
`type_question` ENUM('SINGLE_ANSWER','MULTIPLE_CHOICE','TRUE_OR_FALSE') NOT NULL,
`fk_section` BIGINT NOT NULL,
FOREIGN KEY(`fk_section`) REFERENCES `Section`(`id`)
);

CREATE TABLE `Alternative`(
`id` BIGINT  PRIMARY KEY AUTO_INCREMENT,
`description` TEXT NOT NULL,
`position` INT,
`correct` BOOLEAN DEFAULT FALSE,
`explanation_answer` VARCHAR(255),
`fk_question` BIGINT NOT NULL,
FOREIGN KEY(`fk_question`) REFERENCES `Question`(`id`)
);