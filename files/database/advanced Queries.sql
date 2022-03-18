-- os nomes e ordem das subcategorias ativas e que tem algum curso, na ordem
SELECT sub.name, sub.position
FROM Subcategory sub
INNER JOIN Course course
ON course.fk_subcategory = sub.id 
WHERE sub.status = "ACTIVE"
ORDER BY sub.position;

-- o nome e a quantidade de cursos do instrutor que tem mais cursos
SELECT instructor_name, COUNT(*) AS `quantity`
FROM Course c
GROUP BY instructor_name ORDER BY quantity DESC LIMIT 1;

-- os nomes de todas as categorias ativas com a respectiva quantidade de cursos (públicos e privados) e total de horas estimados dos cursos associados (sendo 0 se não existir nenhum curso)
SELECT category.name, count(*) `All Courses`, COALESCE(SUM(course.estimated_time), 0) `Total Hours`
FROM Category category
LEFT JOIN Subcategory subcategory
ON category.id = subcategory.fk_category
RIGHT JOIN Course course
ON subcategory.id = course.fk_subcategory
WHERE category.status = "ACTIVE"
GROUP BY category.id;







