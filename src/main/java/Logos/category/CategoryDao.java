package Logos.category;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public CategoryDao() {

    }

    public List<Category> getAllCategoriesActives(){
        List<Category> categories = new ArrayList<>();
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.status='ACTIVE' ORDER BY c.order",Category.class);
        categories = query.getResultList();
        return categories;
    }
}
