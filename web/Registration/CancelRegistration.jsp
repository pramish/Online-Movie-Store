<%-- 
    Document   : cancel
    Created on : 24/05/2019, 4:01:31 AM
    Author     : Photato
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<!DOCTYPE html>
<html>
    <head>
        <title>OMS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h1><a href="/">Online Movie Store</a></h1>
            <hr />
            <h2 class="text-center">Your Account has been cancelled!</h1>
            <hr />
            <p class="text-center"><a href="/index.jsp">Click here to go back to the home page.</a></p>
            <hr />
        </div>
    </body>
</html>