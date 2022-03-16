package Logos.course;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCourse {
    private static ConnectionFactory factory = new ConnectionFactory();

    public static int insert() throws SQLException {
        try (Connection connection = factory.recoverConnection()) {
            Category category = new Category("programação", "programacao");
            SubCategory subCategory = new SubCategory("java", "java", category);
            Course course = new Course("java", "java-t8", 10, "Paulo", subCategory);
            connection.setAutoCommit(false);
            String sql = """
                    INSERT INTO Course(`name`,`identifier_code`,`estimated_time`,`visibility`,`target_audience`,`instructor_name`,
                    `course_program_description`,`developed_skills`,`fk_subcategory`)
                    VALUES(?,?,?,?,?,?,?,?,?);                             
                    """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, course.getName());
                preparedStatement.setString(2, course.getCode());
                preparedStatement.setInt(3, course.getEstimatedTime());
                preparedStatement.setBoolean(4, course.isVisibility());
                preparedStatement.setString(5, course.getTargetAudience());
                preparedStatement.setString(6, course.getInstructorName());
                preparedStatement.setString(7, course.getCourseProgramDescription());
                preparedStatement.setString(8, course.getDevelopedSkills());
                preparedStatement.setInt(9, 1);
                preparedStatement.execute();
                connection.commit();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                } catch (Exception e) {
                    connection.rollback();
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void delete(String code) {
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                     DELETE FROM `Course`  WHERE `identifier_code`='%s';
                    """.formatted(code);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.execute();
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int turnPublic() {
        int result = 0;
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                      UPDATE Course set visibility=TRUE where visibility=FALSE;
                    """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                result = preparedStatement.executeUpdate();
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Course> courses() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = factory.recoverConnection()) {
            String sql = """
                    SELECT course.id,course.identifier_code,course.name, course.estimated_time,
                    course.instructor_name,subcategory.id id_subcategory,
                    subcategory.name subcategory_name,subcategory.identifier_code subcategory_code,subcategory.id fk_subcategory,
                    category.name category_name,category.identifier_code category_code
                    FROM `Course`  course
                    INNER JOIN `Subcategory` subcategory
                    ON subcategory.id = course.fk_subcategory
                    INNER JOIN Category category
                    ON category.id = subcategory.fk_category
                     WHERE course.visibility=1 ORDER BY course.id;
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String code = resultSet.getString("identifier_code").strip();
                    int estimatedTime = resultSet.getInt("estimated_time");
                    String instructorName = resultSet.getString("instructor_name");
                    String subcategoryCode = resultSet.getString("subcategory_code");
                    String subcategoryName = resultSet.getString("subcategory_name");
                    int fkSubcategory = resultSet.getInt("fk_subcategory");
                    String categoryCode = resultSet.getString("category_code");
                    String categoryName = resultSet.getString("category_name");
                    Category category = new Category(categoryName, categoryCode);
                    SubCategory subCategory = new SubCategory(fkSubcategory,subcategoryName, subcategoryCode, category);
                    isValidCourse(courses,id, name, code, estimatedTime, instructorName, subCategory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    private static void isValidCourse(List<Course> courses,int id, String name, String code, int estimatedTime, String instructorName,
                                      SubCategory subCategory) {
        if ((name != "" && name != null) && (code != "" && code != null) && subCategory != null) {
            courses.add(new Course(id,name, code, estimatedTime, instructorName, subCategory));
        }
    }
}
