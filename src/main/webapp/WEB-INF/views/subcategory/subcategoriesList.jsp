<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Subcategorias</title>
        <script src="/webjars/jquery/3.6.0/jquery.js"></script>
        <meta charset="utf-8">
    </head>
    <body>
        <section style="padding: 10px" class="container">
            <h1>${category.name}</h1>
            <h3>SubCategoria</h3>
            <a href="/admin/subcategories/new">
                <button  class="btn btn-primary">Nova Subcategoria</button>
            </a>
        </section>

        <section class="container">
            <table class="table table-dark table-bordered">
                <thead>
                    <th scope="col">Nome</th>
                    <th scope="col">Código</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </thead>

                <c:forEach items="${subcategories}" var="subcategory">
                    <tbody>
                        <tr scope="row">
                            <td>${subcategory.name}</td>
                            <td>${subcategory.code}</td>
                            <td id="status_${subcategory.code}">${subcategory.status == 'ACTIVE' ? 'Ativa' : 'Inativa'}</td>
                            <td>
                                <a href="/admin/courses/${category.code}/${subcategory.code}">
                                    Cursos
                                </a>
                            </td>
                            <td>
                                <a style="text-decoration: none; color: #0c0101" href="/admin/subcategories/${category.code}/${subcategory.code}">
                                    <button class="btn btn-dark">Editar</button>
                                </a>
                            </td>
                            <td>
                                <a style="text-decoration: none; color: #0c0101">
                                    <c:if test="${subcategory.status == 'ACTIVE'}">
                                        <button onclick="disable('${subcategory.code}')" id="disableButton_${subcategory.code}" class="btn btn-dark">Desativar</button>
                                    </c:if>
                                </a>
                            </td>
                        </tr>
                    </tbody>
               </c:forEach>
            </table>
        </section>
        <script>
            function disable(subcategoryCode){
                let url = "/admin/subcategories/disable/"+subcategoryCode;
                $.post(url, function (){
                    $("#disableButton_"+subcategoryCode).hide();
                    $("#status_"+subcategoryCode).text("Inativa");
                });
            }
        </script>
    </body>
</html>