<%-- 
    Document   : register
    Created on : 24/05/2019, 3:16:55 AM
    Author     : Photato
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Array"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 

<%User user = (User)session.getAttribute("registerUser");%>
<%List<String> errors =(ArrayList)session.getAttribute("registerErrors");%>

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
            <h2>Register</h2>
            <hr>
            <div class="row">
                <div class="col-sm-4">
                    <form method="post" action="/register">
                        
                        <ul>
                            <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
                        </ul>
                        <div class="form-group">
                            <label>Email</label>
                            <input class="form-control" type="email" name="email" value="<%=user.getEmail()%>" >
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input class="form-control" type="text" name="name" value="<%=user.getName()%>">
                        </div>
                        <div class="form-group">
                            <label>Phone No.</label>
                            <input class="form-control" type="tel" name="phone" value="<%=user.getPhoneNumber()%>">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input class="form-control" type="password" name="password" value="<%=user.getPassword()%>" >
                        </div>
                        <hr />
                        <a href="/index.jsp" class="btn btn-default">Cancel</a>
                        <input type="submit" class="btn btn-success pull-right" value="Submit">

                    </form>
                </div>
            </div>
            <hr />
        </div>
    </body>
</html>