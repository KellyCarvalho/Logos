<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Categories</title>
    <meta charset="UTF-8">
</head>

<body>

  <h1>Categorias</h1>
  <h2><a href="/formCreateCategory.jsp">Nova Categoria</a></h2>
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
          <td id="status_${category.id}">${category.status}</td>
          <td>${category.order}</td>
          <td><img style="width: 100px; height: 100px" src="${category.imageUrl}"></td>
          <td style="background-color:${category.colorCode} "></td>
          <td>
          <button><a style="text-decoration: none" href="/mostrarCategoria?id=${category.id}">Editar</a></button>
          </td>
          <td>
          <button onclick="disableCategory(${category.id})" class="disable">Desativar</button>
          </td>
        </tr>
    </c:forEach>
    <script>
       function disableCategory(id) {

            let status = document.querySelector("#status_" + id);
            let url = "/mudarStatus?id=" + id + "";
            if (status.textContent === "ACTIVE") {
                const request = new XMLHttpRequest();
                request.open("POST", url);
                request.addEventListener("load", function () {
                    if (request.status == 200) {
                        status.textContent = "DISABLED";
                    } else {
                        alert("Ocorreu um erro, nada foi alterado!");
                    }
                })
                request.send();
            }
       }
    </script>
    </tbody>
  </table>

</body>

</html>

