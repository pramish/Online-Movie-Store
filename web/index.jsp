<%-- 
    Document   : index
    Created on : 15/05/2019, 12:57:44 AM
    Author     : pramishluitel
--%>

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
            String success = (String) request.getParameter("success");
            String failure = (String) request.getParameter("failure");
            String success1 = (String) request.getParameter("success1");
            String failure1 = (String) request.getParameter("failure1");
            User user = (User) session.getAttribute("user1");
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
        <% if (user!=null){%>
        <div align="center">
        <table class="table table-borderless table-dark">
            <caption><h2>Users Record</h2></caption>
             <tr class="table-success">
                <th>Email</th>
                <th>Name</th>
                <th>Password</th>
                <th>Phone Number</th>
            </tr>
                <tr class="table-success">
                    <td> <%=user.getEmail() %> </td>
                    <td> <%=user.getName() %> </td>
                    <td><%=user.getPassword() %></td>
                    <td><%=user.getPhoneNumber() %></td>
                </tr>
        </table>
    </div>  
                <%}%>
    </body>
</html>
