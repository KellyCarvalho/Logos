<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Categoria</title>
</head>
<body>
<form action="/alterarCategoria" method="post">

    <input type="hidden" name="id" value="${category.id}"/>
    <br>
    <br><label>Nome</label>
    <input type="text" name="name" value="${category.name}"/>
    <br>
    <br>
    <label>Código</label>
    <input type="text" name="code" value="${category.code}"/>
    <br>
    <br>
    <label>Descrição</label>
    <input name="description" value="${category.description}"/>
    <br>
    <br>
    <label>Guia de estudos</label>
    <input type="text" name="studyGuide" value="${category.studyGuide}"/>
    <br>
    <br>
    <label>Status</label>
    <input type="hidden" name="status" value="${category.status}"/>
    <br>
    <br>
    <label>Ordem</label>
    <input type="number" name="order" value="${category.order}">
    <br>
    <br>
    <label>Url da Imagem</label>
    <input type="text" name="imageUrl" value="${category.imageUrl}">
    <br>
    <br>
    <label>Código da cor</label>
    <input type="color" name="colorCode" value="${category.colorCode}">
    <br>
    <br>
    <input type="submit">
</form>
</body>
</html>
