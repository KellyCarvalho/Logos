package Logos.course;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class CourseDao {

    private EntityManager em;

    public CourseDao(EntityManager em) {
        this.em = em;
    }

    public Long insert(Course course) {
        this.em.persist(course);
        return course.getId();
    }

    public void delete(String code) {
        isNotBlankEmptyOrNull(code, "Código de curso é requerido");
        existCourse(code);
        Query query = em.createQuery("DELETE FROM Course WHERE code = :code")
                .setParameter("code", code);
        System.out.println("Curso deletado com sucesso: " + code);
        query.executeUpdate();
    }

    public void turnAllPublic() {
        Query query = em.createQuery("UPDATE Course c SET c.visibility = true WHERE visibility = false");
        query.executeUpdate();
    }

    public List<Course> getAllPublic() {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.visibility = true", Course.class);
        return query.getResultList();
    }

    public Course existCourse(String code) {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.code = :code", Course.class)
                .setParameter("code", code);
        return query.getSingleResult();
    }
}
