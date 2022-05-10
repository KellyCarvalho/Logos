<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib  prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<html>
    <head>
        <templates:admin-template></templates:admin-template>
        <title>Editar Curso</title>
        <link rel="stylesheet" href="/assets/css/form.css">
    </head>

    <body>

        <section class="container">

            <h1>Editar Curso</h1>

            <form:form modelAttribute="courseUpdateDTO" class="md-3" method="post">

                <div class="md-4">
                    <form:input path="id" type="hidden"/>
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
                    <form:input path="estimatedTime" class="form-control" placeholder="Tempo estimado de curso entre 1 e 20 horas" type="number"/>
                    <form:errors path="estimatedTime"/>
                </div>

                <div class="checkbox-inline checkbox_visibility">
                    <form:checkbox path="visibility" value="true" class="form-check-input" placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc"/>
                    <form:label path="visibility">
                        Curso Público?
                        <p class="p_visibility">Curso Público é visivel para todos</p>
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
                    <form:input path="description" placeholder="Descrição do Curso" class="form-control" type="text"/>
                    <form:errors path="description"/>
                </div>

                <div class="md-4">
                    <form:label path="developedSkills" class="form-label">Habilidades desenvolvidas</form:label>
                    <form:input path="developedSkills" placeholder="Habilidades desenvolvidas" class="form-control" type="text"/>
                    <form:errors path="developedSkills"/>
                </div>

                <h1>SubCategorias</h1>
                <form:select class="form-control" path="subCategory">
                    <form:options itemLabel="name" items="${subCategories}"></form:options>
                </form:select>

                <div class="md-4 button_submit">
                    <input class="form-inline-block btn btn-success" value="Enviar" type="submit">
                </div>

            </form:form>

        </section>

    </body>

</html>