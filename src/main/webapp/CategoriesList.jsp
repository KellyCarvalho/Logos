<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <%@ page import="br.com.logos.category.Category" %>
    <%
        List<Category> categories = (List<Category>) request.getAttribute("categories");
        for (Category category : categories) {
    %>
    <tr>
        <td><%= category.getId()%>
        </td>
        <td><%= category.getCode()%>
        </td>
        <td><%= category.getName()%>
        </td>
        <td><%= category.getDescription()%>
        </td>
        <td><%= category.getStudyGuide()%>
        </td>
        <td><%= category.getStatus()%>
        </td>
        <td><%= category.getOrder()%>
        </td>
        <td><%= category.getImageUrl()%>
        </td>
        <td><%= category.getColorCode()%>
        </td>
    </tr>
    <%
        }
    %>

    </tbody>
</table>
</body>
</html>

