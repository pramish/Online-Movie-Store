
<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<%
    User user = (User)session.getAttribute("user");
%>

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
            <div class="row">
                <div class="col-sm-4">
                    <a href="/movie/list" class="btn btn-primary btn-lg btn-block">Movie Catalogue</a>
                </div>
                <%if (user.isRegistered()){%>
                    <div class="col-sm-4">
                        <a href="/order/history" class="btn btn-primary btn-lg btn-block">My Orders</a>
                    </div>
                    <div class="col-sm-4">
                        <a href="/myaccount" class="btn btn-primary btn-lg btn-block">My Registration Details</a>
                    </div>
                    <div class="col-sm-4">
                        <a href="/listAccessLogs" class="btn btn-primary btn-lg btn-block">My Access Logs</a>
                    </div>
                    <div class="col-sm-12">
                        <hr />
                    </div>
                <%//if (staff != null){%>
                    <div class="col-sm-4">
                        <a href="/users" class="btn btn-warning btn-lg btn-block">User Management</a>
                    </div>
                    <div class="col-sm-4">
                        <a href="/customer/list" class="btn btn-warning btn-lg btn-block">Customer Management</a>
                    </div>
                <%//}%>
                    <div class="col-sm-12">
                        <hr />
                    </div>
                    <div class="col-sm-4">
                        <a href="/logout" class="btn btn-default btn-lg btn-block">Logout</a>
                    </div>
                <%}else{%>
                    <div class="col-sm-4">
                        <a href="/register" class="btn btn-primary btn-lg btn-block">Register</a>
                    </div>
                    <div class="col-sm-4">
                        <a href="/login" class="btn btn-primary btn-lg btn-block">Login</a>
                    </div>
                <%}%>
            </div>
        </div>
    </body>
</html>