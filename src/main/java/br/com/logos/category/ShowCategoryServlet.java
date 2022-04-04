package br.com.logos.category;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/mostrarCategoria")
public class ShowCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = getEntityManager("logos");
        CategoryDao categoryDao = new CategoryDao(entityManager);
        Category category = categoryDao.getById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("category", category);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/formUpdateCategory.jsp");
        requestDispatcher.forward(request, response);
    }
}
