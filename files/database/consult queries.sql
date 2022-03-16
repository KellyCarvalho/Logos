SELECT * FROM `Category` WHERE status = 'ACTIVE' order by position;
SELECT * FROM `Subcategory` WHERE status = 'ACTIVE' order by position;
SELECT * FROM `Course` WHERE visibility = true;
SELECT `name` FROM `Subcategory` WHERE description = "" or description is null;


























SELECT course.id,course.identifier_code,course.name, course.estimated_time,
course.instructor_name,subcategory.id id_subcategory,
subcategory.name subcategory_name,subcategory.identifier_code subcategory_code,subcategory.id fk_subcategory,
category.name category_name,category.identifier_code category_code
FROM `Course`  course
INNER JOIN `Subcategory` subcategory
ON subcategory.id = course.fk_subcategory
INNER JOIN Category category
ON category.id = subcategory.fk_category
 WHERE course.visibility=1 ORDER BY subcategory.position;
 

 
 
 
 
 