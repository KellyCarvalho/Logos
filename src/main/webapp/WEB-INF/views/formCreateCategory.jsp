<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Categoria</title>
  <meta charset="UTF-8">
</head>

<body>
<span>
  <form:errors path="category.code" />
</span>
  <h1>Nova Categoria</h1>
  <form class="row g-3" action="/admin/categories" method="post">
    <label for="name">Nome</label>
    <input id="name" type="text" name="name" />

    <label for="code">Código</label>
    <input id="code" type="text" name="code" required/>

    <label for="description">Descrição</label>
    <textarea id="description" name="description"></textarea>

    <label for="studyGuide">Guia de estudos</label>
    <input id="studyGuide" type="text" name="studyGuide"/>

    <label for="status">Status</label>
    <div>
      <input type="checkbox" id="status" name="status" checked>
    </div>

    <label for="order">Ordem</label>
    <input id="order" type="number" name="order">

    <label for="imageUrl">Url da Imagem</label>
    <input id="imageUrl" type="text" name="imageUrl">

    <label for="colorCode">Código da cor</label>
    <input id="colorCode" type="color" name="colorCode">

    <input onclick="checkStatus()"  value="Salvar" type="submit">
  </form>
</body>
<script>

  function checkStatus(){
    let checkbox = document.querySelector("#status");
    if (checkbox.value=="on"){
      checkbox.value="ACTIVE";
    }else{
      checkbox.value="DISABLED";
    }
  }
</script>
</html>