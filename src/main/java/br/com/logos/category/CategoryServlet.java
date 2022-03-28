package br.com.logos.category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/listaCategorias")
public class CategoryServlet extends HttpServlet {

    private CategoryDao categoryDao;

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryDao = new CategoryDao(getEntityManager("logos"));
        List<Category> categories = categoryDao.getAllCategories();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/CategoriesList.jsp");
        request.setAttribute("categories",categories);
        requestDispatcher.forward(request,response);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
