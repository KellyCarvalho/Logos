package Logos.course;

import Logos.subCategory.SubCategoryDTO;
import Logos.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class CourseDao {
    
    private static final ConnectionFactory factory = new ConnectionFactory();

    public void insert(Course course) {
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
                preparedStatement.setInt(9, course.getSubCategoryId());
                preparedStatement.execute();
                connection.commit();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    System.out.println("Id do curso: " + resultSet.getInt(+1));
                }
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String code) {
        isNotBlankEmptyOrNull(code, "Código de curso é requerido");
        if (!existCourse(code)) throw new IllegalArgumentException("Código não existe no registro de cursos");
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                     DELETE FROM `Course` WHERE `identifier_code`='%s';
                    """.formatted(code);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.execute();
                System.out.println("Curso deletado com sucesso");
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void turnAllPublic() {
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                      UPDATE Course SET visibility = TRUE WHERE visibility = FALSE;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("Linhas afetadas: " + preparedStatement.executeUpdate());
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CourseDTO> getAllPublic() {
        List<CourseDTO> courses = new ArrayList<>();
        try (Connection connection = factory.recoverConnection()) {
            connection.setAutoCommit(false);
            String sql = """
                    SELECT course.id, course.name, course.estimated_time, subcategory.id id_subcategory, subcategory.name subcategory_name
                    FROM `Course`  course
                    INNER JOIN `Subcategory` subcategory ON subcategory.id = course.fk_subcategory
                    WHERE course.visibility=1 ORDER BY course.id;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                SubCategoryDTO subCategoryDTO = new SubCategoryDTO(resultSet.getInt("id_subcategory"),
                        resultSet.getString("subcategory_name"));

                CourseDTO courseDTO = new CourseDTO(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("estimated_time"), subCategoryDTO);

                courses.add(courseDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public boolean existCourse(String code) {
        boolean result = false;
        try (Connection connection = factory.recoverConnection()) {
            String sql = """
                    SELECT identifier_code FROM Course WHERE identifier_code="%s";
                    """.formatted(code);
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            result = resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
