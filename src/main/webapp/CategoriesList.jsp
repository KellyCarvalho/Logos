<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<h1>Categorias</h1>
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
    </tr>
    <tbody>

    <c:forEach items="${categories}" var="category">
    <tr>
        <td>${category.id}</td>
        <td>${category.code}</td>
        <td>${category.name}</td>
        <td>${category.description}</td>
        <td>${category.studyGuide}</td>
        <td>${category.status}</td>
        <td>${category.order}</td>
        <td>${category.imageUrl}</td>
        <td>${category.colorCode}</td>
    </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>

