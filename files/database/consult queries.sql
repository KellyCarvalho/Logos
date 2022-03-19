USE LOGOS;
SELECT * FROM `Category` WHERE status = 'ACTIVE' order by position;
SELECT * FROM `Subcategory` WHERE status = 'ACTIVE' order by position;
SELECT * FROM `Course` WHERE visibility = true;
SELECT `name` FROM `Subcategory` WHERE description = "" OR description is null;