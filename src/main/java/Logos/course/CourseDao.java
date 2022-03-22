package Logos.course;

import Logos.subCategory.SubCategoryDTO;
import Logos.factory.ConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class CourseDao {
    private EntityManager em;

    public CourseDao() {
    }

    public CourseDao(EntityManager em) {
        this.em = em;
    }

    public Long insert(Course course) {
        this.em.persist(course);
        return course.getId();
    }

    private static final ConnectionFactory factory = new ConnectionFactory();

    public void insert_old(Course course) {
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
                preparedStatement.setLong(9, course.getSubCategoryId());
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
        existCourse(code);
        isNotBlankEmptyOrNull(code, "Código de curso é requerido");
        Query query = em.createQuery("DELETE FROM Course WHERE code = :code")
                .setParameter("code", code);
        query.executeUpdate();

    }

    public void turnAllPublic() {
        Query query = em.createQuery("UPDATE Course c SET c.visibility = true WHERE visibility=false");
        query.executeUpdate();


    }

    public List<Course> getAllPublic() {
        List<Course> courses = new ArrayList<>();
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.visibility=true",Course.class);
        courses = (query.getResultList());
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
