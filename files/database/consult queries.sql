SELECT * FROM `Category` WHERE status='ACTIVE' order by position;
SELECT * FROM `Subcategory` WHERE status='ACTIVE' order by position;
SELECT * FROM `Course` WHERE visibility=1;
SELECT `name` FROM `Subcategory` WHERE description="";

SELECT *,subcat.name  subcategoryName 
FROM `Course`  course
INNER JOIN `Subcategory` subcat
ON subcat.id = course.fk_subcategory
 WHERE course.visibility=1;