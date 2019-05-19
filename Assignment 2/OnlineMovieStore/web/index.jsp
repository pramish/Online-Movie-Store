<%-- 
    Document   : index
    Created on : 15/05/2019, 12:57:44 AM
    Author     : pramishluitel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MODEL.controller.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Menu</title>
    </head>
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <body>
        <%
            String success = (String) request.getParameter("success");
        %>
        <%if (success != null) { %>
        <div class="alert alert-success">
            <%out.print(success);%> 
        </div>
        <%  }
        %>
        <h1>Admin Menu</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="createUser.jsp">Create Users</a>
            <a class="navbar-brand" href="updateUser.jsp">Update Users</a>
            <a class="navbar-brand" href="deleteUser.jsp">Delete Users</a>
            <form class="form-inline" action="readUser.jsp" method="post">
                <input class="form-control mr-sm-2" type="search" placeholder="Search Users" value="users" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </nav>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
