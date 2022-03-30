package br.com.logos.category;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/listaCategorias")
public class ListCategoryServlet extends HttpServlet {

    private CategoryDao categoryDao;

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryDao = new CategoryDao(getEntityManager("logos"));
        List<Category> categories = categoryDao.getAllCategories();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/categoriesList.jsp");
        request.setAttribute("categories", categories);
        requestDispatcher.forward(request, response);
    }
}
