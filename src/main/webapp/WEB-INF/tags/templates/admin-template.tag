<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>
<%@attribute name="style" required="true" %>

<html>
    <head>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <script src="/webjars/jquery/3.6.0/jquery.js"></script>
        <meta charset="utf-8">
        <title>${title}</title>
        <link rel="stylesheet" href="${style}">
    </head>

    <body>
        <jsp:doBody />
    </body>
</html>
