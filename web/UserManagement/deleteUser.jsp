<%-- 
    Document   : deleteUser
    Created on : 08/05/2019, 6:01:18 PM
    Author     : pramishluitel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Delete User</h1>
        
        <div class="container-fluid">
            <div class="col-sm-12 text-center">
                <button class="btn btn-primary btn-lg active" name="home" onclick="location.href = 'index.jsp';">Home</button>
            </div>
        </div>
        <br><br>
        <pre>
        <form class="form-inline" action="/deleteUser" method="post">
            <input class="form-control mr-sm-2" type="search" name="email" placeholder="enter email address" cc aria-label="Search">
            <br>
            <button class="btn btn-primary btn-lg active">Delete</button>
        </form>
        </pre>
    </body>
</html>
