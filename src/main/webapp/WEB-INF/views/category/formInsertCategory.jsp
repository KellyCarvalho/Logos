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

        <form:form modelAttribute="categoryInsertDTO" class="md-3" method="post">
        <div class="md-4">
            <form:label path="name"  class="form-label" for="name">Nome</form:label>
            <form:input path="name" placeholder="Digite Aqui o nome da categoria" class="form-control" id="name" type="text" />
            <form:errors path="name" />
        </div>

        <div class="md-4">
            <form:label path="code" class="form-label" for="code">Código</form:label>
            <form:input path="code"  placeholder="por exemplo: desenvolvimento, mobile(não use letras maíusculas, acentos ou caracteres especiais)" class="form-control" id="code" type="text" />
            <form:errors  path="code" />
        </div>

        <div class="checkbox-inline" style="padding-top: 20px">
            <form:checkbox path="active" value="true" class="form-check-input" placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc"  id="active"/>
                <form:label path="active"  for="active">
                Categoria Ativa?
                <p style="float: right; font-size: 12px; color: darkgray; padding-top: 2px; padding-left: 2px">Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc</p>
            </form:label>
        </div>

        <div class="md-4">
            <form:label path="order" class="form-label" for="order">Ordem da categoria</form:label>
            <form:input  path="order" type="number" placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2" class="form-control" />
            <form:errors path="order" />
        </div>

        <div class="md-4">
            <form:label path="studyGuide" class="form-label" for="studyGuide">Guias de estudo</form:label>
            <form:textarea  path="studyGuide" placeholder="Um texto apontando para formações para ajudar pessoas perdidas" style="height: 100px" class="form-control" id="studyGuide" type="text" name="studyGuide"/>
        </div>

        <div class="md-4">
            <form:label path="imageUrl" class="form-label" for="imageUrl">Caminho da imagem</form:label>
            <form:input  path="imageUrl"  placeholder="por exemplo: /images/categorias/programacao.svg" class="form-control" id="imageUrl" type="text" name="imageUrl"/>
            <form:errors  path="imageUrl" />
        </div>

        <div class="md-4" style="padding-top: 10px">
            <form:label path="colorCode"  class="form-label" for="colorCode">Cor</form:label>
            <form:input  path="colorCode" placeholder="por exemplo #fcc14a" class="col-2" id="colorCode" type="color" name="colorCode"/>
            <form:errors path="colorCode" />
        </div>

        <div class="md-4">
            <form:label path="description" class="form-label" for="description">Descrição</form:label>
            <form:input  path="description"  placeholder="por exemplo: IOS, Android, PhoneGap e mais..." class="form-control" id="description" name="description"/>
        </div>

        <div class="md-4" style="padding-top: 20px">
            <input class="form-inline-block btn btn-success" value="Enviar" type="submit">
        </div>

        </form:form>
    </section>
    </body>
</html>