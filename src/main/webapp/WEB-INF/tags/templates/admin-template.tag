<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>
<%@attribute name="button-name" required="true" %>

<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <script src="/webjars/jquery/3.6.0/jquery.js"></script>
    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" href="/assets/css/listPage.css">
</head>

<body>
<jsp:doBody />
</body>


<%--TODO Passar a tag head e usar o jsp do body e receber o título como atributo e fechamentos das tags separar os nomes como ex:
admin-template-header/admin-template-footer--%>