<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<html>
    <head>
        <templates:admin-template></templates:admin-template>
        <title>404 - Error</title>
    </head>

    <body>

        <section class="container">

            <div class="container">
                <h1>Página não encontrada</h1>
                <a href="/admin/categories"><button class="btn btn-success">Voltar</button></a>
            </div>

        </section>

    </body>
</html>