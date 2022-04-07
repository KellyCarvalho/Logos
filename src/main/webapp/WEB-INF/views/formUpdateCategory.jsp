<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Editar Categoria</title>
        <meta charset="UTF-8">
    </head>
<body>
    <section class="container">

        <h1>Editar Categoria</h1>
        <form:form modelAttribute="categoryUpdateDTO" class="mb-3" method="post">
            <form:input path="id" type="hidden" name="id"/>
            <label  class="form-label" for="name">Nome</label>
            <form:input  path="name" placeholder="Digite Aqui o nome da categoria" class="form-control" id="name" type="text" />
            <label class="form-label" for="code">Código</label>
            <form:input path="code" placeholder="por exemplo: desenvolvimento, mobile(não use letras maíusculas, acentos ou caracteres especiais)" class="form-control" id="code" type="text"  />
<%--                <div class="form-check">--%>
<%--                    <form:checkbox path="status" value="${categoryUpdateDTO.getStatus().equals('ACTIVE') ? checked : unchecked}"  class="form-check-input" placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc" id="status" />--%>
<%--                    <label  class="form-check-label visible-lg-inline-block" for="status">--%>
<%--                        Categoria Ativa?--%>
<%--                        <p style="float: right; font-size: 12px; color: darkgray; padding-top: 3px; padding-left: 5px">Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc</p>--%>
<%--                    </label>--%>
<%--                </div>--%>
            <label class="form-label form-inline" for="order">Ordem da categoria</label>
            <form:input path="order" placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2" class="form-control" id="order" type="number" />
            <label class="form-label" for="studyGuide">Guias de estudo</label>
            <form:textarea path="studyGuide" rows="10" class="form-control" placeholder="Um texto apontando para formações para ajudar pessoas perdidas"/>
            <label class="form-label" for="imageUrl">Caminho da imagem</label>
            <form:input path="imageUrl"  placeholder="por exemplo: /images/categorias/programacao.svg" class="form-control" id="imageUrl" type="text"/>
            <label class="form-label" for="colorCode">Cor</label>
            <form:input path="colorCode"  placeholder="por exemplo #fcc14a" class="form-control" id="colorCode" type="text" />
            <label class="form-label" for="description">Descrição</label>
            <form:input path="description"  placeholder="por exemplo: IOS, Android, PhoneGap e mais..." class="form-control" id="description" />
            <input class="btn btn-success" value="Enviar" onclick="checkStatus()" type="submit"/>
        </form:form>
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