<%-- 
    Document   : view
    Created on : 24/05/2019, 4:00:40 AM
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
            <h1 class="text-center">My Details</h1>
            <hr />
            <div class="row">
                <div class="col-md-6 col-md-push-3">
                    <form method="post" action="registerController">
                        <div class="well well-lg">
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
                                <p><a href="update.jsp">Update My Details</a></p>
                                <p><a href="cancel.jsp" onclick="return confirm('Are you sure you want to cancel your account?')">Cancel My Registration</a></p>
                            </div>
                        </div>
                        </well>
                        
                        <hr />
                    </form>
                </div>
            </div>
            <hr />
        </div>
    </body>
</html>
