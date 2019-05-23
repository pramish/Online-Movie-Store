<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MODEL.controller.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Menu</title>
    </head>
    <%@include file="Header.html" %>
    <body>
        <%
            
            String success1 = (String) request.getParameter("success1");
            String failure1 = (String) request.getParameter("failure1");
        %>
        
        <%if (success1 != null) { %>
        <div class="alert alert-success">
            <%out.print(success1);%> 
        </div>
        <%  }
        %>
         <%if (failure1 != null) { %>
        <div class="alert alert-success">
            <%out.print(failure1);%> 
        </div>
        <%  }
        %>
        <h1>Admin Menu</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="createUser.jsp">Create Users</a>
            <a class="navbar-brand" href="updateUser.jsp">Update Users</a>
            <a class="navbar-brand" href="deleteUser.jsp">Delete Users</a>
            <br><br><br>
            <div style="text-align: right;">
                <form class="form-inline my-2 my-lg-0" action="readUserController.jsp" method="post">
                <input class="form-control mr-sm-2" type="search" name="email" placeholder="enter email">
                <input class="form-control mr-sm-2" type="search" name="phoneNumber" placeholder="enter phonenumber">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            </div>
        </nav> 
        <jsp:include page="/ConnServlet" flush="true" /> 
                    
    </body>
</html>