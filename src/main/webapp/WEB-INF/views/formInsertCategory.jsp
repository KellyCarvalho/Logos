<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <title>Criar Categoria</title>
    <meta charset="UTF-8">
</head>

<body>
    <section class="container">

        <h1>Nova Categoria</h1>
        <form:form modelAttribute="categoryInsertDTO" class="mb-3" method="post">
            <label  class="form-label" for="name">Nome</label>
            <form:input path="name" placeholder="Digite Aqui o nome da categoria" class="form-control" id="name" type="text" />
            <form:errors path="name" />

            <label class="form-label" for="code">Código</label>
            <form:input path="code"  placeholder="por exemplo: desenvolvimento, mobile(não use letras maíusculas, acentos ou caracteres especiais)" class="form-control" id="code" type="text" />
            <form:errors  path="code" />

            <%--            TODO pesquisar e fazer funcionar com enum--%>
            <div class="form-check">
                <form:checkbox path="status" value="DISABLED" onchange="checkStatus()"   class="form-check-input" placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc"  id="status" name="status"/>
                <label class="form-check-label visible-lg-inline-block" for="status">
                    Categoria Ativa?
                    <p style="float: right; font-size: 12px; color: darkgray; padding-top: 3px; padding-left: 5px">Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc</p>
                </label>
            </div>

            <label class="form-label form-inline" for="order">Ordem da categoria</label>
            <input path="order" placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2" class="form-control" id="order" type="number" name="order" required />

            <label class="form-label" for="studyGuide">Guias de estudo</label>
            <textarea path="studyGuide" placeholder="Um texto apontando para formações para ajudar pessoas perdidas" style="height: 100px" class="form-control" id="studyGuide" type="text" name="studyGuide"></textarea>

            <label class="form-label" for="imageUrl">Caminho da imagem</label>
            <input path="imageUrl"  placeholder="por exemplo: /images/categorias/programacao.svg" class="form-control" id="imageUrl" type="text" name="imageUrl"/>

            <label class="form-label" for="colorCode">Cor</label>
            <input path="colorCode" placeholder="por exemplo #fcc14a" class="form-control" id="colorCode" type="text" name="colorCode"/>

            <label class="form-label" for="description">Descrição</label>
            <input path="description"  placeholder="por exemplo: IOS, Android, PhoneGap e mais..." class="form-control" id="description" name="description"/>

            <input class="btn btn-success" value="Enviar" type="submit">
        </form:form>
    </section>
<script>
    function checkStatus(){
        let checkbox = document.querySelector("#status");
        if (checkbox.checked === true) {
            checkbox.value = "ACTIVE";
        }
    }
</script>
</body>

</html>