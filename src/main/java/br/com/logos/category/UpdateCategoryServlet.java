package br.com.logos.category;

import javax.persistence.EntityManager;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/alterarCategoria")
public class UpdateCategoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager entityManager = getEntityManager("logos");
        entityManager.getTransaction().begin();
        CategoryService categoryService = new CategoryService();

        Category category = categoryService.toCategory(request.getParameter("id"), request.getParameter("name"), request.getParameter("code"),
                request.getParameter("description"), request.getParameter("studyGuide"), request.getParameter("status"), request.getParameter("order"),
                request.getParameter("imageUrl"), request.getParameter("colorCode"));
        CategoryDao categoryDao = new CategoryDao(entityManager);
        categoryDao.update(category);
        entityManager.getTransaction().commit();
        entityManager.close();
        response.sendRedirect("/listaCategorias");
    }
}
