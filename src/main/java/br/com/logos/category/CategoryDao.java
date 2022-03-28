package br.com.logos.category;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public List<Category> getAllActiveCategories() {
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.status = 'ACTIVE' ORDER BY c.order", Category.class);
        return query.getResultList();
    }

    public List<Category> getAllCategories(){
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c  ORDER BY c.order", Category.class);
        return query.getResultList();
    }
}
