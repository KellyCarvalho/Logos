<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <title>Criar Categoria</title>
    <meta charset="UTF-8">
</head>

<body>
    <section class="container">

        <h1>Editar Categoria</h1>

        <form class="mb-3" action="/admin/categories/<c:out value="${category.code}"/>" method="post">
            <input type="hidden" name="id" value="${category.id}">
            <label  class="form-label" for="name">Nome</label>
            <input  value="${category.name}" placeholder="Digite Aqui o nome da categoria" class="form-control" id="name" type="text" name="name" />
            <br>
            <label class="form-label" for="code">Código</label>
            <input value="${category.code}" placeholder="por exemplo: desenvolvimento, mobile(não use letras maíusculas, acentos ou caracteres especiais)" class="form-control" id="code" type="text" name="code" />
            <br>
                <div class="form-check">
                    <input ${category.status == "ACTIVE" ? "checked" : ""} class="form-check-input" placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc" type="checkbox" value="DISABLED" id="status" name="status">
                    <label  class="form-check-label visible-lg-inline-block" for="status">
                        Categoria Ativa?
                        <p style="float: right; font-size: 12px; color: darkgray; padding-top: 3px; padding-left: 5px">Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc</p>
                    </label>
                </div>
            <br>
            <label class="form-label form-inline" for="order">Ordem da categoria</label>
            <input value="${category.order}"  placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2" class="form-control" id="order" type="number" name="order" required>
            <br>
            <label class="form-label" for="studyGuide">Guias de estudo</label>
            <textarea placeholder="Um texto apontando para formações para ajudar pessoas perdidas" style="height: 100px" class="form-control" id="studyGuide" type="text" name="studyGuide">${category.studyGuide}</textarea>
            <br>
            <label class="form-label" for="imageUrl">Caminho da imagem</label>
            <input value="${category.imageUrl}" placeholder="por exemplo: /images/categorias/programacao.svg" class="form-control" id="imageUrl" type="text" name="imageUrl">
            <br>

            <label class="form-label" for="colorCode">Cor</label>
            <input  value="${category.colorCode}" placeholder="por exemplo #fcc14a" class="form-control" id="colorCode" type="text" name="colorCode">


            <br>
            <label class="form-label" for="description">Descrição</label>
            <input value="${category.description}" placeholder="por exemplo: IOS, Android, PhoneGap e mais..." class="form-control" id="description" name="description"/>
            <br>
            <input class="btn btn-success" value="Enviar" onclick="checkStatus()" type="submit">
        </form>
    </section>
    <script>
        function checkStatus() {
            let checkBox = document.querySelector("#status");
            if (checkBox.checked === true) {
                checkBox.value = "ACTIVE";
            }
        }
    </script>
</body>

</html>