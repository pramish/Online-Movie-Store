<%-- 
    Document   : view
    Created on : 24/05/2019, 4:00:40 AM
    Author     : Photato
--%>

<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%User user = (User)session.getAttribute("user");%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Online Movie System</h1>
            <h2 class="text-center">My Registration Details</h2>
            <hr />
            <div class="row">
                <div class="col-md-6 col-md-push-3">
                    
                        <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label>Email:</label>
                                <p class="form-control-static"><%=user.getEmail()%></p>
                            </div>
                            <div class="form-group">
                                <label>Name:</label>
                                <p class="form-control-static"><%=user.getName()%></p>
                            </div>
                            <div class="form-group">
                                <label>Phone No.:</label>
                                <p class="form-control-static"><%=user.getPhoneNumber()%></p>
                            </div>
                            <div class="form-group">
                                <label>Password:</label>
                                <p class="form-control-static"><%=user.getPassword()%></p>
                            </div>
                            
                        </div>
                        <div class="col-sm-6">
                            <a href="/" class="btn btn-default btn-block">Home</a>
                            <a href="/myaccount/edit" class="btn btn-primary btn-block">Update My Details</a>
                            <form method="post" action="/myaccount/cancel">
                                <input type="submit" value="CANCEL MY REGISTRATION" class="btn btn-danger btn-block" onclick="return confirm('Are you sure you want to cancel your account?')">
                            </form>
                        </div>
                    </div>
                        
                </div>
            </div>
            <hr />
        </div>
    </body>
</html>
