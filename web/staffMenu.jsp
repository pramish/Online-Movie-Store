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
        <h1>Staff Menu</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="/index.jsp">Home</a>
            <a class="navbar-brand" href="/createStaff">Create Staff</a>
            <a class="navbar-brand" href="/readStaff">Read Staff</a>
            <br><br><br>
            
        </nav> 
    </body>
</html>
