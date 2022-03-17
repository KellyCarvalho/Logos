package Logos.course;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.subCategory.SubCategoryDTO;
import Logos.utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class CourseDao {
    private static ConnectionFactory factory = new ConnectionFactory();

    public void insert(SubCategoryDTO subCategoryDTO, CourseDTO course) throws SQLException {
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                    INSERT INTO Course(`name`,`identifier_code`,`estimated_time`,`visibility`,`target_audience`,
                    `instructor_name`, `course_program_description`,`developed_skills`,`fk_subcategory`)
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
                preparedStatement.setInt(9, subCategoryDTO.getCategoryId());
                preparedStatement.execute();
                connection.commit();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt(1));
                    }
                }
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }

    public void delete(String code) throws SQLException {
        isNotBlankEmptyOrNull(code, "Código de curso é requerido");
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                     DELETE FROM `Course` WHERE `identifier_code`='%s';
                    """.formatted(code);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.execute();
                System.out.println("Query executada com sucesso");
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        }
    }

    public void turnPublic() {
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                      UPDATE Course SET visibility = TRUE WHERE visibility = FALSE;
                    """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                System.out.println(preparedStatement.executeUpdate());
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CourseDTO> courses() {
        List<CourseDTO> courses = new ArrayList<>();
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
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
                    SubCategory subCategory = new SubCategory(fkSubcategory, subcategoryName, subcategoryCode, category);
                    isValidCourse(courses, id, name, code, estimatedTime, instructorName, subCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private static void isValidCourse(List<CourseDTO> courses, int id, String name, String code, int estimatedTime, String instructorName,
                                      SubCategory subCategory) {
        if ((name != "" && name != null) && (code != "" && code != null) && subCategory != null) {
            courses.add(new CourseDTO(id, name, code, estimatedTime, instructorName, subCategory));
        }
    }
}
