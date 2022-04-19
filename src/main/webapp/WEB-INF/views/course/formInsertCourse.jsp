<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Criar Curso</title>
        <meta charset="UTF-8">
    </head>

    <body>

        <section class="container">

            <h1>Novo Curso</h1>

            <form:form modelAttribute="courseInsertDTO" class="md-3" method="post">

                <div class="md-4">
                    <form:label path="name" class="form-label">Nome</form:label>
                    <form:input path="name" placeholder="Digite Aqui o nome do curso" class="form-control" type="text"/>
                    <form:errors path="name"/>
                </div>

                <div class="md-4">
                    <form:label path="code" class="form-label">Código</form:label>
                    <form:input path="code" placeholder="por exemplo: desenvolvimento, mobile(não use letras maíusculas, acentos ou caracteres especiais)" class="form-control" type="text"/>
                    <form:errors path="code"/>
                </div>

                <div class="md-4">
                    <form:label path="estimatedTime" class="form-label">Tempo estimado</form:label>
                    <form:input path="estimatedTime" placeholder="Tempo estimado de curso entre 1 e 20 horas" type="number"/>
                    <form:errors path="estimatedTime"/>
                </div>

                <div class="checkbox-inline" style="padding-top: 20px">
                    <form:checkbox path="visibility" value="true" class="form-check-input" placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc"/>
                    <form:label path="visibility">
                        Curso Público?
                        <p style="float: right; font-size: 12px; color: darkgray; padding-top: 2px; padding-left: 2px">Curso Público é visivel para todos</p>
                    </form:label>
                </div>

                <div class="md-4">
                    <form:label path="targetAudience" class="form-label">Público Alvo</form:label>
                    <form:input path="targetAudience" placeholder="Público Alvo" class="form-control" type="text"/>
                    <form:errors path="targetAudience"/>
                </div>

                <div class="md-4">
                    <form:label path="instructorName" class="form-label">Nome do Instrutor</form:label>
                    <form:input path="instructorName" placeholder="Nome do Instrutor" class="form-control" type="text"/>
                    <form:errors path="instructorName"/>
                </div>

                <div class="md-4">
                    <form:label path="description" class="form-label">Ementa</form:label>
                    <form:input path="description" placeholder="Nome do Instrutor" class="form-control" type="text"/>
                    <form:errors path="description"/>
                </div>

                <div class="md-4">
                    <form:label path="developedSkills" class="form-label">Habilidades desenvolvidas</form:label>
                    <form:input path="developedSkills" placeholder="Nome do Instrutor" class="form-control" type="text"/>
                    <form:errors path="developedSkills"/>
                </div>

                <h1>SubCategorias</h1>
                <form:select class="form-control" path="subCategory">
                    <form:options itemLabel="name" items="${subcategories}"></form:options>
                </form:select>
                <form:errors path="subCategory"/>


                <div class="md-4" style="padding-top: 20px">
                    <input class="form-inline-block btn btn-success" value="Enviar" type="submit">
                </div>

            </form:form>
        </section>

    </body>

</html>