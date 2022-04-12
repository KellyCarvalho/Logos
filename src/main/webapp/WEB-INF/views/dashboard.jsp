<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <title>Dashboard</title>
    <meta charset="utf-8">
</head>
<body>

<section class="container" style="padding-top: 20px">
    <h1>Cursos por Categoria</h1>
    <table class="table table-dark table-bordered">
        <thead>
        <th scope="col">Categoria</th>
        <th scope="col">Quantidade de cursos</th>
        </thead>
        <c:forEach items="${coursesByCategory}" var="coursesByCategory">
            <tbody>
            <tr scope="row">
                <td>${coursesByCategory.getCategoryName()}</td>
                <td>${coursesByCategory.getQuantity()}</td>
            </tr>
            </tbody>
        </c:forEach>

    </table>
</section>


<section class="container" style="padding-top: 20px">
    <h1>Cursos por Instrutor</h1>
    <table class="table table-dark table-bordered">
        <thead>
        <th scope="col">Instrutor</th>
        <th scope="col">Quantidade de cursos</th>
        </thead>
        <c:forEach items="${coursesByInstructor}" var="coursesByInstructor">
            <tbody>
            <tr scope="row">
                <td>${coursesByInstructor.getInstructorName()}</td>
                <td>${coursesByInstructor.getQuantity()}</td>
            </tr>
            </tbody>
        </c:forEach>

    </table>
</section>

</body>
</html>