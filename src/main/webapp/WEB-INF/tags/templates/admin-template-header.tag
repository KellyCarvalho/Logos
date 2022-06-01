<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>

<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <script src="/webjars/jquery/3.6.0/jquery.js"></script>
        <meta charset="utf-8">
        <title>${title}</title>

        <jsp:doBody />
    </head>

