package br.com.logos.subCategory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SubcategoryDao {

    private EntityManager em;

    public SubcategoryDao(EntityManager em) {
        this.em = em;
    }

    public SubCategory getByCode(String code) {
        TypedQuery<SubCategory> query = em.createQuery("SELECT c FROM SubCategory c WHERE c.code=:code", SubCategory.class)
                .setParameter("code", code);
        return query.getSingleResult();
    }

    public List<SubCategory> getAllActiveSubcategories() {
        TypedQuery<SubCategory> query = em.createQuery("SELECT c FROM SubCategory c WHERE c.status = 'ACTIVE' ORDER BY c.order", SubCategory.class);
        return query.getResultList();
    }

    public List<String> getSubcategoriesWithoutDescription() {
        TypedQuery<String> query = em.createQuery("SELECT c.name FROM SubCategory c WHERE c.description = '' or c.description is null ORDER BY c.order", String.class);
        return query.getResultList();
    }
}
