<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Editar Categoria</title>
    <meta charset="UTF-8">
</head>

<body>
  <h1>Atualizar Categoria</h1>
  <form action="/alterarCategoria" method="post">
    <input type="hidden" name="id" value="${category.id}"/>
    <label>Nome</label>
    <input type="text" name="name" value="${category.name}" required/>
    <br>
    <br>
    <label>Código</label>
    <input type="text" name="code" value="${category.code}" required/>
    <br>
    <br>
    <label>Descrição</label>
    <textarea name="description">${category.description}</textarea>
    <br>
    <br>
    <label>Guia de estudos</label>
    <input type="text" name="studyGuide" value="${category.studyGuide}"/>
    <br>
    <br>
    <label>Status</label>
    <select name="status" text="${category.status}">
      <option ${category.status=="ACTIVE" ? "selected" :""} value="ACTIVE">ATIVA</option>
      <option ${category.status=="DISABLED" ? "selected" :""} value="DISABLED">DESABILITADA</option>
    </select>
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
    <input value="Atualizar" type="submit">
  </form>
</body>
</html>