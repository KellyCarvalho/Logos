<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template-header style="/assets/css/dashboardPage.css" title="Dashboard Administrativo">

        <section class="container main_container">

            <h1>Cursos por Categoria</h1>
            <table class="table table-dark table-bordered">
                <thead>
                    <th scope="col">Categoria</th>
                    <th scope="col">Quantidade de cursos</th>
                </thead>
                <c:forEach items="${coursesByCategory}" var="courseByCategory">
                    <tbody>
                        <tr scope="row">
                            <td>${courseByCategory.getCategoryName()}</td>
                            <td>${courseByCategory.getQuantity()}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>

        </section>

        <section class="container main_container">

            <h1>Instrutor com mais cursos</h1>
            <table class="table table-dark table-bordered">
                <thead>
                    <th scope="col">Instrutor</th>
                    <th scope="col">Quantidade de cursos</th>
                </thead>
                <tbody>
                    <tr scope="row">
                        <td>${coursesByInstructor.getInstructorName()}</td>
                        <td>${coursesByInstructor.getQuantity()}</td>
                    </tr>
                </tbody>
            </table>

        </section>

</templates:admin-template-header>

<templates:admin-template-footer></templates:admin-template-footer>