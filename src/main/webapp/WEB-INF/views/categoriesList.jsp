<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <title>Todas as categorias</title>
    <meta charset="UTF-8">
</head>
<body>
    <section style="padding: 10px" class="container">
        <h3>Categorias</h3>
        <a href="/admin/categories/new"><button  class="btn btn-primary">Nova Categoria</button></a>

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
                    <td>${category.status}</td>
                    <td><a href="/admin/subcategories/${category.code}">SubCategorias</td>
                    <td>
                        <a style="text-decoration: none; color: #0c0101" href="/admin/categories/${category.code}">
                            <button class="btn btn-dark"> Editar</button>
                        </a>
                    </td>
                </tr>
            </tbody>
        </c:forEach></table>
    </section>
</body>
</html>