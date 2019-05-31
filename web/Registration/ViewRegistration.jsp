<%-- 
    Document   : view
    Created on : 24/05/2019, 4:00:40 AM
    Author     : Photato
--%>

<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<%User user = (User)session.getAttribute("user");%>

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
            <h2>My Registration Details</h2>
            <hr />
            <div class="row">
                <div class="col-sm-4">
                    <a href="/" class="btn btn-default btn-block">Home</a>
                </div>
                <div class="col-sm-4">
                    <a href="/myaccount/edit" class="btn btn-primary btn-block">Update My Details</a>
                </div>
                <div class="col-sm-4">
                    <form method="post" action="/myaccount/cancel">
                        <input type="submit" value="CANCEL MY REGISTRATION" class="btn btn-danger btn-block" onclick="return confirm('Are you sure you want to cancel your account?')">
                    </form>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label>Email:</label>
                        <p class="form-control-static"><%=user.getEmail()%></p>
                    </div>
                    <div class="form-group">
                        <label>Name:</label>
                        <p class="form-control-static"><%=user.getName()%></p>
                    </div>
                    <div class="form-group">
                        <label>Phone No:</label>
                        <p class="form-control-static"><%=user.getPhoneNumber()%></p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
