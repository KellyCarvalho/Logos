package br.com.logos.category;

import javax.persistence.EntityManager;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/mudarStatus")
public class ChangeStatusCategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)  {
        EntityManager entityManager = getEntityManager("logos");
        entityManager.getTransaction().begin();
        CategoryDao categoryDao = new CategoryDao(entityManager);

        Category category = categoryDao.getById(Long.parseLong(request.getParameter("id")));

        category.disableCategory();
        categoryDao.update(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
