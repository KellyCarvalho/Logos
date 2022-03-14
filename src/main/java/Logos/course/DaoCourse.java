package Logos.course;

import Logos.category.Category;
import Logos.subCategory.SubCategory;
import Logos.utils.ConnectionFactory;

import java.sql.*;

public class DaoCourse {
    private static ConnectionFactory factory = new ConnectionFactory();

    public static int insert() throws SQLException {
        try (Connection connection = factory.recoverConnection()) {
            Category category = new Category("programação", "programacao");
            SubCategory subCategory = new SubCategory("java", "java", category);
            Course course = new Course("java", "java-t7", 10, "Paulo", subCategory);
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
        } catch (SQLException e) {
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                result = preparedStatement.executeUpdate();
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
