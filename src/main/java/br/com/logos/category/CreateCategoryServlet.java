package br.com.logos.category;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static br.com.logos.utils.JPAUtil.getEntityManager;

@WebServlet("/novaCategoria")
public class CreateCategoryServlet extends HttpServlet {

    private CategoryDao categoryDao;
    private EntityManager entityManager=getEntityManager("logos");


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category(request.getParameter("name"),request.getParameter("code"));
        categoryDao = new CategoryDao(entityManager);
        System.out.println(category);
        categoryDao.insert(category);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listaCategorias");
        request.setAttribute("category",category);
        requestDispatcher.forward(request,response);
    }
}
