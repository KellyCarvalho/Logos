package br.com.logos.category;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/novaCategoria")
public class CreateCategoryServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager entityManager = getEntityManager("logos");
        entityManager.getTransaction().begin();
        CategoryService categoryService = new CategoryService();

        Category category = categoryService.toCategory(request);

        CategoryDao categoryDao = new CategoryDao(entityManager);
        categoryDao.insert(category);
        entityManager.getTransaction().commit();
        entityManager.close();
        response.sendRedirect("/listaCategorias");
    }
}
