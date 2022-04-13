<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Todas cursos</title>
        <meta charset="utf-8">
    </head>
    <body>
    <section class="container" style="padding-top: 50px">
        <h3>${subcategory}</h3>
        <h1>Cursos</h1>
        <table class="table table-dark table-bordered">
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
                        <a style="text-decoration: none; color: #0c0101" href="/admin/course/${course.code}">
                            <button class="btn btn-dark">Editar</button>
                        </a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <nav aria-label="Navegação de página exemplo">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="?page=${courses.first ? 0: courses.number -1 }">Anterior</a>
                </li>
                    <c:forEach begin="1" end="${courses.totalPages}" var="page">
                        <li class="page-item">
                            <a href="?page=${page-1}" class="page-link">${page-1}</a>
                        </li>
                    </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="?page=${courses.last ? courses.number : courses.number + 1 }">Próximo</a>
                </li>
            </ul>
        </nav>
    </section>
    </body>
</html>