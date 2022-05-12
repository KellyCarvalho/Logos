<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib  prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template style="/assets/css/form.css" title="Criar SubCategoria">

        <section class="container">

            <h1>Nova SubCategoria</h1>
            <form:form modelAttribute="subCategoryInsertDTO" class="md-3" method="post">
                <div class="md-4">
                    <form:label path="name" class="form-label">Nome</form:label>
                    <form:input path="name" placeholder="Digite Aqui o nome da subcategoria" class="form-control" type="text"/>
                    <form:errors path="name"/>
                </div>

                <div class="md-4">
                    <form:label path="code" class="form-label">Código</form:label>
                    <form:input path="code" placeholder="por exemplo: desenvolvimento, mobile(não use letras maíusculas, acentos ou caracteres especiais)" class="form-control" type="text"/>
                    <form:errors path="code"/>
                </div>

                <div class="checkbox-inline checkbox_visibility">
                    <form:checkbox path="active" value="true" class="form-check-input" placeholder="Mostra ou deixa de mostrar a subcategoria na listagem dos alunos, de formações, etc"/>
                    <form:label path="active">
                        SubCategoria Ativa?
                        <p class="p_visibility">Mostra ou deixa de mostrar a subcategoria na listagem dos alunos, de formações, etc</p>
                    </form:label>
                </div>

                <div class="md-4">
                    <form:label path="order" class="form-label">Ordem da subcategoria</form:label>
                    <form:input path="order" type="number" placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2" class="form-control"/>
                    <form:errors path="order"/>
                </div>

                <div class="md-4">
                    <form:label path="studyGuide" class="form-label">Guias de estudo</form:label>
                    <form:textarea path="studyGuide" placeholder="Um texto apontando para formações para ajudar pessoas perdidas" style="height: 100px" class="form-control" type="text"/>
                </div>

                <div class="md-4">
                    <form:label path="description" class="form-label">Descrição</form:label>
                    <form:input path="description" placeholder="por exemplo: IOS, Android, PhoneGap e mais..." class="form-control"/>
                </div>

                <h1>Categoria</h1>
                    <form:select class="form-control" path="category">
                        <form:options itemLabel="name" items="${categories}"></form:options>
                    </form:select>

                <div class="md-4 button_submit">
                    <input class="form-inline-block btn btn-success" value="Enviar" type="submit">
                </div>

            </form:form>

        </section>

</templates:admin-template>