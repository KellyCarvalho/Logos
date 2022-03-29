<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Categoria</title>
</head>
<body>
<form action="/novaCategoria" method="post">
    <br><label>Nome</label>
    <input type="text" name="name"/>
    <br>
    <br>
    <label>Código</label>
    <input type="text" name="code"/>
    <br>
    <br>
    <label>Descrição</label>
    <input name="description"/>
    <br>
    <br>
    <label>Guia de estudos</label>
    <input type="text" name="studyGuide"/>
    <br>
    <br>
    <br>
    <label>Ordem</label>
    <input type="number" name="order">
    <br>
    <br>
    <label>Url da Imagem</label>
    <input type="text" name="imageUrl">
    <br>
    <br>
    <label>Código da cor</label>
    <input type="color" name="colorCode">
    <br>
    <br>
    <input type="submit">
</form>
</body>
</html>
