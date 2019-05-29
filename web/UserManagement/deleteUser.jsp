<%-- 
    Document   : deleteUser
    Created on : 08/05/2019, 6:01:18 PM
    Author     : pramishluitel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.User"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <%
            String success = (String) request.getParameter("success");
            String failure = (String) request.getParameter("failure");
        %>
        <%if (success != null) { %>
        <div class="alert alert-success">
            <%out.print(success);%> 
        </div>
        <%  }
        %>
        <%if (failure != null) { %>
        <div class="alert alert-success">
            <%out.print(failure);%> 
        </div>
        <%  }
        %>
        

        <%@include file="/heading.jsp" %>
        <h1>Delete User</h1>
        <br><br>
        <div class="container-fluid">
            <div class="col-sm-12 text-center">
        <form class="form-inline" action="/deleteUser" method="post">
            <input class="form-control mr-sm-2" type="search" required name="id" placeholder="enter user ID" cc aria-label="Search">
            <button class="btn btn-danger">Delete</button>
        </form>
            </div>
        </div>
    </body>
</html>
