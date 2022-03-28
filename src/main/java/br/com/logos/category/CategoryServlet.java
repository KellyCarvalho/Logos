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

        PrintWriter out = response.getWriter();

        StringBuilder html = new StringBuilder("""
                <html>
                <head>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
                </head>
                <body>
                <h1>Categorias<h1>
                  <table>
                    <thead>
                      <th>Id</th>
                      <th>Código</th>
                      <th>Nome</th>
                      <th>Descrição</th>
                      <th>Guia de Estudo</th>
                      <th>Status</th>
                      <th>Ordem</th>
                      <th>Imagem</th>
                      <th>Código da cor</th>
                      </thead>
                      <tbody>
                """);

        categories.forEach(category -> {
            html.append("""
                    <tr>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    </tr>

                    """.formatted(category.getId(), category.getCode(), category.getName(), category.getDescription(),
                    category.getStudyGuide(), category.getStatus(), category.getOrder(), category.getImageUrl(), category.getColorCode()));
        });
        html.append("""
                </tbody>
                </body>
                </html>
                """);
        out.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
