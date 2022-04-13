<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Categorias</title>
        <script src="/webjars/jquery/3.6.0/jquery.js"></script>
        <meta charset="utf-8">
    </head>

    <body>
        <section style="padding: 10px" class="container">
            <h3>Categorias</h3>
            <a href="/admin/categories/new">
                <button class="btn btn-primary">Nova Categoria</button>
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
                <c:forEach items="${categories}" var="category">
                    <tbody>
                        <tr scope="row">
                            <td>${category.name}</td>
                            <td>${category.code}</td>
                            <td id="status_${category.code}">${category.status == 'ACTIVE' ? 'Ativa' : 'Inativa'}</td>
                            <td>
                                <a href="/admin/subcategories/${category.code}">SubCategorias</a>
                            </td>
                            <td>
                                <a style="text-decoration: none; color: #0c0101" href="/admin/categories/${category.code}">
                                    <button class="btn btn-dark">Editar</button>
                                </a>
                            </td>
                            <td>
                                <a style="text-decoration: none; color: #0c0101">
                                  <c:if test="${category.status == 'ACTIVE'}">
                                      <button onclick="disable('${category.code}')" id="disableButton_${category.code}" class="btn btn-dark">Desativar</button>
                                  </c:if>
                                </a>
                            </td>
                        </tr>
                    </tbody>
               </c:forEach>
            </table>
        </section>

        <script>
            function disable(categoryCode){
                let url = "/admin/categories/disable/"+categoryCode;
                $.post(url, function (){
                    $("#disableButton_"+categoryCode).hide();
                    $("#status_"+categoryCode).text("Inativa")
                });
            }
        </script>

    </body>

</html>