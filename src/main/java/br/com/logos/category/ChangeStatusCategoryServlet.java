package br.com.logos.category;

import br.com.logos.category.enums.CategoryStatus;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/mudarStatus")
public class ChangeStatusCategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager entityManager = getEntityManager("logos");
        entityManager.getTransaction().begin();
        CategoryDao categoryDao = new CategoryDao(entityManager);

        Category category = categoryDao.getById(Long.parseLong(request.getParameter("id")));

        if (category.getStatus() == CategoryStatus.ACTIVE) {
            category.setStatus(CategoryStatus.DISABLED);
        }
        categoryDao.update(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
