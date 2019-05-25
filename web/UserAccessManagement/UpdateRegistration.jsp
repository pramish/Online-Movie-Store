<%-- 
    Document   : update
    Created on : 24/05/2019, 4:01:00 AM
    Author     : Photato
--%>


<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.User"%>
<%User user = (User)session.getAttribute("editMyAccountUser");%>
<%List<String> errors =(ArrayList)session.getAttribute("editMyAccountErrors");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Online Movie System</h1>
            <h2 class="text-center">Update My Registration Details</h2>
            <hr />
            <div class="row">
                <div class="col-md-6 col-md-push-3">
                    <form method="post" action="/myaccount/edit">
                        <div class="row">
                            <div class="col-sm-6 col-sm-push-3">
                                <ul>
                                    <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
                                </ul>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input class="form-control" type="text" name="email" value="<%=user.getEmail()%>" required />
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" type="text" name="name" value="<%=user.getName()%>" />
                                </div>
                                <div class="form-group">
                                    <label>Phone No.</label>
                                    <input class="form-control" type="tel" name="phone" value="<%=user.getPhoneNumber()%>" />
                                </div>
                                <div class="form-group">
                                    <label>New Password</label>
                                    <input class="form-control" type="text" name="password" value="<%=user.getPassword()%>" required />
                                </div>
                                <hr>
                                <a href="/myaccount" class="btn btn-default">Cancel</a>
                                <input type="submit" class="btn btn-success pull-right" value="Save">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <hr />
        </div>
    </body>
</html>