<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Cursos</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="/assets/css/listPage.css">
    </head>

    <body>

        <section class="container block_container">
            <h1>${subcategoryName}</h1>
            <h4>Cursos</h4>
            <a href="/admin/courses/new">
                <button class="btn btn-primary">Novo Curso</button>
            </a>
        </section>

        <section class="container block_container">
            <table class="table table-dark table-bordered container block_container">
                <thead>
                    <th scope="col">Nome</th>
                    <th scope="col">Código</th>
                    <th scope="col">Status</th>
                </thead>

                <c:forEach items="${courses.content}" var="course">
                    <tbody>
                        <tr scope="row">
                            <td>${course.name}</td>
                            <td>${course.code}</td>
                            <td>${course.visibility ? 'Público' : 'Privado'}</td>
                            <td>
                                <a href="/admin/subcategories/${course.categoryCode}/${course.subCategoryCode}/${course.code}">
                                    <button class="btn btn-dark action_button">Editar</button>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>

            <c:if test="${courses.totalPages > 0}">
                <nav>
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="?page=${courses.first ? 0 : courses.number-1}">Anterior</a>
                        </li>
                        <c:forEach begin="1" end="${courses.totalPages}" var="page">
                            <li class="page-item">
                                <a href="?page=${page-1}" class="page-link">${page}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item">
                            <a class="page-link" href="?page=${courses.last ? courses.number : courses.number+1}">Próximo</a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </section>

    </body>

</html>