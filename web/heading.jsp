<%-- 
    Document   : heading
    Created on : 28/05/2019, 10:40:00 PM
    Author     : pramishluitel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="Header.html" %>
    </head>
    <body>
        <h1>Admin Menu</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="/index.jsp">Home</a>
            <a class="navbar-brand" href="/createUser">Create Users</a>
            <a class="navbar-brand" href="/users">Read User</a>
            <a class="navbar-brand" href="/updateUser">Update Users</a>
            <a class="navbar-brand" href="/deleteUser">Delete Users</a>
            
            <br><br><br>
            
        </nav> 
    </body>
</html>
