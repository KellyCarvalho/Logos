<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Categoria</title>
    <meta charset="UTF-8">
</head>
<body>
  <h1>Nova Categoria</h1>
  <form action="/novaCategoria" method="post">
    <label>Nome</label>
    <input type="text" name="name" required/>
    <br>
    <br>
    <label>Código</label>
    <input type="text" name="code" required/>
    <br>
    <br>
    <label>Descrição</label>
    <textarea name="description"></textarea>
    <br>
    <br>
    <label>Guia de estudos</label>
    <input type="text" name="studyGuide"/>
    <br>
    <br>
    <label>Status</label>
    <select name="status"  text="${category.status}">
        <option value="ACTIVE">ATIVA</option>
        <option value="DISABLED">DESABILITADA</option>
    </select>
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
    <input value="Salvar" type="submit">
  </form>
</body>
</html>