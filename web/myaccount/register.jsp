<%-- 
    Document   : register
    Created on : 24/05/2019, 3:16:55 AM
    Author     : Photato
--%>

<%@page import="MODEL.User"%>
<%
    User user = (User)session.getAttribute("user");
    if(user == null) {
        user = new User();
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Register</h1>
            <hr />
            <div class="row">
                <div class="col-md-6 col-md-push-3">
                    <form method="post" action="registerController">
                        <div class="row">
                            <div class="col-sm-6 col-sm-push-3 col-md-4 col-md-push-4">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input class="form-control" type="email" name="email" value="<%=user.getEmail()%>">
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" type="text" name="name" value="<%=user.getName()%>">
                                </div>
                                <div class="form-group">
                                    <label>Phone No.</label>
                                    <input class="form-control" type="tel" name="phone" value="<%=user.getPhoneNumber()%>">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input class="form-control" type="text" name="password" value="<%=user.getPassword()%>">
                                </div>
                            </div>
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